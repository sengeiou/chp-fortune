package com.creditharmony.fortune.template.entity.backInterest;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;

import com.creditharmony.common.util.Encodes;
import com.creditharmony.common.util.SpringContextHolder;
import com.creditharmony.core.deduct.type.CardOrBookType;
import com.creditharmony.core.fortune.type.OpenBank;
import com.creditharmony.core.mybatis.sql.MyBatisSql;
import com.creditharmony.core.mybatis.util.MyBatisSqlUtil;
import com.creditharmony.fortune.back.interest.contants.ExportConstant;
import com.creditharmony.fortune.back.interest.contants.GlobalConstant;
import com.creditharmony.fortune.back.interest.util.StaticMethodUtil;
import com.creditharmony.fortune.deduct.common.DeductUtils;
import com.creditharmony.fortune.utils.FileZip;

/**
 * 卡联支付
 * @Class Name CardPaymentExport 
 * @author Mr
 * @Create In 2016年7月28日
 */
public class CardPaymentExport {

	/** 输出流 */
	private OutputStream out;
	/** 文件流集合  */
	private Map<String,InputStream> fileMap = new HashMap<String,InputStream>();
	
	/** 文件数据数量限额，超出该数量生成新文件 */
	private int fileLimit;
	
	private BigDecimal limit = GlobalConstant.KA_LIAN_LIMIT;
	
	/**
	 * 文件名
	 */
	private String fileName;
	private int i= 1;
	
	public CardPaymentExport(){
		this.fileLimit = GlobalConstant.KALIAN_FILE_LIMIT;
		fileName = "卡联支付导出";
	}
	
	protected Logger logger = LoggerFactory.getLogger(getClass());
	/**
	 * 导出Excel方法 
	 * 2016年4月15日 
	 * By Mr.Li
	 * @param view
	 * @param response
	 */
	public void exportData(Map<String,Object> map, HttpServletResponse response) {

		// 设置搜索条件
		SqlSessionFactory sqlSessionFactory = (SqlSessionFactory) SpringContextHolder
				.getBean("sqlSessionFactory");
		SqlSession sqlSession = sqlSessionFactory.openSession();
		Connection connection = sqlSession.getConnection();
		try {

			// 执行数据库查询语句，带条件
			MyBatisSql batisSql = MyBatisSqlUtil
					.getMyBatisSql(
							ExportConstant.CARD_ID,
							map, sqlSessionFactory);
			PreparedStatement ps = connection.prepareStatement(batisSql
					.toString());
			ResultSet resultSet = ps.executeQuery();

			exportMethod(resultSet);
			// 下载到客户端
			if(fileMap.size()>1){
				try {
					// 设置Header信息
					response.setHeader("Content-Disposition",
							"attachment; fileName=" + Encodes.urlEncode(fileName) + ".zip"
							+ ";fileName*=UTF-8''" + Encodes.urlEncode(fileName) + ".zip");
					out = response.getOutputStream();
				} catch (IOException e) {
					e.printStackTrace();
				}
				FileZip.zipFiles(out, fileMap);
			} else {
				try {
					// 设置Header信息
					response.setHeader("Content-Disposition",
							"attachment; fileName=" + Encodes.urlEncode(fileName) + ".xlsx"
									+ ";fileName*=UTF-8''" + Encodes.urlEncode(fileName) + ".xlsx");
					out = response.getOutputStream();
				} catch (IOException e) {
					e.printStackTrace();
				}
				for (String key : fileMap.keySet()) {
					 FileCopyUtils.copy(fileMap.get(key), out);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("卡联支付导出数据出现异常"+e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 卡联支付导出
	 * 2016年7月28日
	 * by Mr
	 * @param resultSet
	 * @param dataSheet
	 * @throws Exception 
	 */
	public void exportMethod(ResultSet resultSet) throws Exception{
		
		XSSFWorkbook wb = new XSSFWorkbook();
		Sheet dataSheet = wb.createSheet("卡联支付导出");
		dataSheet.setDefaultColumnWidth(50000);
		wrapperHeader(dataSheet, wb);
		
		int row = 1;
		
		Row dataRow;
		while(resultSet.next()){
			
			BigDecimal pm = resultSet.getBigDecimal("back_real_money");
			BigDecimal sm = resultSet.getBigDecimal("success_money");
			
			pm = pm == null ? BigDecimal.ZERO : pm;
			sm = sm == null ? BigDecimal.ZERO : sm;
			
			BigDecimal diff = pm.subtract(sm).setScale(2, BigDecimal.ROUND_HALF_UP);
			int ct = diff.compareTo(BigDecimal.ZERO);
			
			String lendCode = resultSet.getString("lend_code");
			if(ct == 1){
				int lm = diff.compareTo(limit);
				if(lm < 1){	
					if ((row-1) >= fileLimit) {
						addInputStream(wb, fileMap);
						wb = new XSSFWorkbook();
						dataSheet = wb.createSheet("卡联支付导出");
						wrapperHeader(dataSheet, wb);
						row = 1;
					}
					dataRow = dataSheet.createRow(row);
					// 业务对象类型 暂时为空
					dataRow.createCell(0).setCellValue("对私");
					dataRow.createCell(1).setCellValue(DeductUtils.isNull(resultSet.getString("account_name")));
					dataRow.createCell(2).setCellValue(DeductUtils.isNull(resultSet.getString("account_no")));
					String accb = DeductUtils.isNull(resultSet.getString("account_bank"));
					if(StringUtils.isNotBlank(accb)){
						accb = OpenBank.getOpenBank(accb);
					}else{
						accb = "";
					}
					dataRow.createCell(3).setCellValue(accb);
					dataRow.createCell(4).setCellValue(DeductUtils.isNull(resultSet.getString("province")));
					dataRow.createCell(5).setCellValue(DeductUtils.isNull(resultSet.getString("city")));
					dataRow.createCell(6).setCellValue(DeductUtils.isNull(resultSet.getString("account_branch")));
					//dataRow.createCell(7).setCellValue(DeductUtils.isNull(resultSet.getString("")));
					dataRow.createCell(7).setCellValue("支行代码");
					dataRow.createCell(8).setCellValue(diff.toString());
					dataRow.createCell(9).setCellValue(DeductUtils.isNull(resultSet.getString("lend_code")+"回息"
								+resultSet.getString("current_billday")));
					String card = resultSet.getString("account_card_or_booklet");
					if(StringUtils.isNotBlank(card)){
						card = CardOrBookType.parseByCode(card).getName();
					}else{
						card = "";
					}
					dataRow.createCell(10).setCellValue(card);
					row++;
				}else{
					dataRow = dataSheet.createRow(row);
					Cell cl = dataRow.createCell(0);
					cl.setCellValue(lendCode);
					Cell cl2 = dataRow.createCell(1);
					cl2.setCellValue(GlobalConstant.OUT_OF_LIMIT);
					row++;
				}
			}else{
				
				dataRow = dataSheet.createRow(row);
				Cell cl = dataRow.createCell(0);
				cl.setCellValue(lendCode);
				Cell cl2 = dataRow.createCell(1);
				cl2.setCellValue(GlobalConstant.ERROR);
				row++;
			}
		}
		if (row>1) {
			addInputStream(wb, fileMap);
		}
	}
	
	/**
	 * 封装压缩excel对象
	 * 2016年4月19日
	 * By Mr.Li
	 * @param wb
	 * @param map
	 */
	private void addInputStream(XSSFWorkbook wb, Map<String,InputStream> map){
		String key = "卡联支付导出_"+ StaticMethodUtil.serNoMade(String.valueOf(i)) +".xlsx";
		++i;
		try {
			// 初始化内存流对象
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			wb.write(out);
			// 转换成内存输出流
			InputStream input = new ByteArrayInputStream(out.toByteArray());
			map.put(key, input);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 卡联标题
	 * 2016年4月20日
	 * by 李志伟
	 * @param dataSheet
	 */
	public void wrapperHeader(Sheet dataSheet, XSSFWorkbook swb) {
	
		String s = ExportConstant.KA_ZHE_TITLE;
		String[] split = s.split(",");
		Row headerRow = dataSheet.createRow(0);
		CellStyle cellStyle = swb.createCellStyle();
		cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND); // 填充单元格
		cellStyle.setFillForegroundColor(HSSFColor.CORNFLOWER_BLUE.index);
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// //居中显示
		
		for (int i = 0; i < split.length; i++) {
			dataSheet.setColumnWidth(i, 4000);
			Cell lendCodeHeader = headerRow.createCell(i);
			lendCodeHeader.setCellValue(split[i]);
			lendCodeHeader.setCellStyle(cellStyle);
		}
	}
}
