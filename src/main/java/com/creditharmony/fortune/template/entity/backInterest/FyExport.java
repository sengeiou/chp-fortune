package com.creditharmony.fortune.template.entity.backInterest;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;

import com.creditharmony.common.util.DateUtils;
import com.creditharmony.common.util.Encodes;
import com.creditharmony.common.util.PropertyUtil;
import com.creditharmony.common.util.SpringContextHolder;
import com.creditharmony.core.deduct.entity.PlatformBankEntity;
import com.creditharmony.core.fortune.type.BackMoneyPlat;
import com.creditharmony.core.fortune.type.OpenBank;
import com.creditharmony.core.mybatis.sql.MyBatisSql;
import com.creditharmony.core.mybatis.util.MyBatisSqlUtil;
import com.creditharmony.fortune.back.interest.contants.ExportConstant;
import com.creditharmony.fortune.back.interest.contants.GlobalConstant;
import com.creditharmony.fortune.back.interest.util.FileNameMadeFactory;
import com.creditharmony.fortune.back.money.utils.BmExportor;
import com.creditharmony.fortune.back.money.utils.BmUtils;
import com.creditharmony.fortune.deduct.common.DeductUtils;
import com.creditharmony.fortune.utils.FileZip;

public class FyExport {

	/**
	 * 文件数据总金额
	 */
	private BigDecimal sumMoney = BigDecimal.ZERO;
	/**
	 * 单条数据金额
	 */
	private BigDecimal cellMoney = BigDecimal.ZERO;
	/**
	 * 文件序号
	 */
	private int fileIndex = 1; 
	/**
	 * 单笔限额
	 */
	BigDecimal singleLimitMoney = GlobalConstant.FY_LIMIT_MAX_MONEY;
	/**
	 * 单文件限额
	 */
	BigDecimal fileLimitMoney = GlobalConstant.FY_FILE_MAX_MONEY;
	/**
	 * 输出流
	 */
	private OutputStream out;
	/**
	 * 文件流集合
	 */
	private Map<String,InputStream> fileMap = new HashMap<String,InputStream>();

	public FyExport(){
		
		PlatformBankEntity entity = BmUtils.getLimitMoney(BackMoneyPlat.FYPT.value);
		if (entity.getSingleLimitMoney()!=null && entity.getSingleLimitMoney()!=0) {
			singleLimitMoney = BigDecimal.valueOf(entity.getSingleLimitMoney()).divide(BigDecimal.valueOf(100));
		}
		if (entity.getDayLimitMoney()!=null && entity.getDayLimitMoney()!=0) {
			fileLimitMoney = BigDecimal.valueOf(entity.getDayLimitMoney()).divide(BigDecimal.valueOf(100));
		}
	}
	
	/**
	 * 获取导出文件名
	 * 2016年5月7日
	 * by 李志伟
	 * @return
	 */
	public String getFileName() {
		
		Properties property = PropertyUtil.getProperties("fileNameNo.properties");
		String value = property.getProperty("fuYou_platForm_No");
		String date = property.getProperty("fuYou_time");
		
		int s = Integer.valueOf(value).intValue();
		String currentTime = DateUtils.getDate("yyyyMMdd");
		if(!date.equals(currentTime)){
			value="1";
			s = 1;
			PropertyUtil.updateProperties("fileNameNo.properties", "fuYou_time", currentTime);
		}
		int i = ++s;
		PropertyUtil.updateProperties("fileNameNo.properties", "fuYou_platForm_No", String.valueOf(i));
		FileNameMadeFactory fnmf = new FileNameMadeFactory(ExportConstant.FY_EXPORT, value);
		String fileName = fnmf.zjAndFyMadeFileName();
		return fileName;
	}

	private static Logger logger = LoggerFactory.getLogger(BmExportor.class);

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
		String fileName = "";
		try {

			// 执行数据库查询语句，带条件
			MyBatisSql batisSql = MyBatisSqlUtil
					.getMyBatisSql(
							ExportConstant.fy_id,
							map, sqlSessionFactory);
			PreparedStatement ps = connection.prepareStatement(batisSql
					.toString());
			ResultSet resultSet = ps.executeQuery();
			fileName = this.getFileName();
			createWorkBook(resultSet);
			// 下载到客户端
			if(fileMap.size()>1){
				try {
					// 设置Header信息
					response.setHeader("Content-Disposition",
							"attachment; fileName=" + fileName+"_"+fileIndex + ".zip"
							+ ";fileName*=UTF-8''" + fileName+"_"+fileIndex + ".zip");
					out = response.getOutputStream();
				} catch (IOException e) {
					e.printStackTrace();
				}
				FileZip.zipFiles(out, fileMap);
			} else {
				try {
					// 设置Header信息
					response.setHeader("Content-Disposition",
							"attachment; fileName=" + Encodes.urlEncode(fileName)  + ".xlsx"
									+ ";fileName*=UTF-8''" + Encodes.urlEncode(fileName) +".xlsx");
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
			logger.debug(fileName + "导出数据出现异常");
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 创建工作簿
	 * 2016年4月19日
	 * By Mr.Li
	 * @param resultSet
	 * @param dataSheet
	 * @throws Exception
	 */
	private void createWorkBook(ResultSet resultSet) throws Exception {
		SXSSFWorkbook wb = new SXSSFWorkbook();
		Sheet dataSheet = wb.createSheet("DataList");
		dataSheet.setDefaultColumnWidth(50000);
		wrapperHeader(dataSheet, wb, ExportConstant.FY_TITLE);
		
		int row = 1;
		
		String accountBank;
		String province;
		String accountBranch;
		String accountNo;
		String accountName;
		//BigDecimal backRealMoney;
		//String backiId;
		String customerMobilephone;
		//String productName;
		String currentBillday;
		String lendCode;
		
		Row dataRow;
		
		Cell idCell;
		Cell accountBankCell;
		Cell provinceCell;
		Cell accountBranchCell;
		Cell accountNoCell;
		Cell accountNameCell;
		Cell backRealMoneyCell;
		Cell backiIdCell;
		Cell memoCell;
		Cell customerMonbilephoneCell;
		
		while (resultSet.next()) {
			
			accountNo = resultSet.getString("account_no");
			accountName = resultSet.getString("account_name");
			//productName = resultSet.getString("product_name");
			accountBank = resultSet.getString("account_bank");
			accountBranch = resultSet.getString("account_branch");
			//backiId = resultSet.getString("backi_id");
			customerMobilephone = resultSet.getString("customer_mobilephone");
			province = resultSet.getString("province");
			lendCode = resultSet.getString("lend_code");
			currentBillday = DeductUtils.isNull(resultSet.getString("current_billday"));
			// ------------------------
			
			// 当前金额
			BigDecimal bi = resultSet.getBigDecimal("back_real_money");
			bi = bi==null?BigDecimal.ZERO:bi;
			
			// 成功金额
			BigDecimal su = resultSet.getBigDecimal("success_money");
			su = su==null?BigDecimal.ZERO:su;
			// 剩余金额
			BigDecimal surplusMoney = bi.subtract(su);
			
			int to = surplusMoney.compareTo(BigDecimal.ZERO);
			if(to==1){
				if (surplusMoney.compareTo(fileLimitMoney)==1) {
					
					// 回息金额大于单文件限额，不处理
					dataRow = dataSheet.createRow(row);
					idCell = dataRow.createCell(0);
					idCell.setCellValue(row+"");
					Cell mem = dataRow.createCell(1);
					mem.setCellValue(lendCode+"回息金额大于单文件限额，无法导出");
					row ++;
					continue;
				} else {
					
					// rs:拆分次数
					int rs = surplusMoney.divide(singleLimitMoney, 0, RoundingMode.UP).intValue();
					if (sumMoney.add(surplusMoney).compareTo(fileLimitMoney)==1) {
						// 如果数据条数大于单文件限制数量，生成新文件
						addInputStream(wb, fileMap);
						fileIndex ++;
						sumMoney =BigDecimal.ZERO;
						wb = new SXSSFWorkbook();
						dataSheet = wb.createSheet("DataList");
						wrapperHeader(dataSheet, wb, ExportConstant.FY_TITLE);
						row = 1;
					}
					sumMoney = sumMoney.add(surplusMoney);
					for(int i=0;i<rs;i++){
						if (i == (rs-1)) {
							cellMoney = surplusMoney;
						} else {
							cellMoney = singleLimitMoney;
							surplusMoney = surplusMoney.subtract(singleLimitMoney);
						}
						dataRow = dataSheet.createRow(row);
						
						
						idCell = dataRow.createCell(0);
						idCell.setCellValue(row +"");
						
						accountBankCell = dataRow.createCell(1);
						if(accountBank != null && !("").equals(accountBank)){
							accountBankCell.setCellValue(OpenBank.getOpenBank(accountBank));
						}else{
							accountBankCell.setCellValue("");
						}
						
						provinceCell = dataRow.createCell(2);
						provinceCell.setCellValue(DeductUtils.isNull(province));
						
						accountBranchCell = dataRow.createCell(3);
						accountBranchCell.setCellValue(DeductUtils.isNull(accountBranch));
						
						accountNoCell = dataRow.createCell(4);
						accountNoCell.setCellValue(DeductUtils.isNull(accountNo));
			
						accountNameCell = dataRow.createCell(5);
						accountNameCell.setCellValue(DeductUtils.isNull(accountName));
						
						backRealMoneyCell = dataRow.createCell(6);
						backRealMoneyCell.setCellValue(DeductUtils.isNull(cellMoney.toString()));
						
						backiIdCell = dataRow.createCell(7);
						backiIdCell.setCellValue(lendCode+"回息"+currentBillday);//账单日期回息_唯一标识 
						
						memoCell = dataRow.createCell(8);
						memoCell.setCellValue("");
						
						customerMonbilephoneCell = dataRow.createCell(9);
						customerMonbilephoneCell.setCellValue(customerMobilephone);
						
						row ++;
					}
				}
			}else{// 回息金额为0不处理
				
				dataRow = dataSheet.createRow(row);
				idCell = dataRow.createCell(0);
				idCell.setCellValue(row+"");
				Cell mem = dataRow.createCell(1);
				mem.setCellValue(lendCode+GlobalConstant.ERROR);
				row ++;
				continue;
			}
		}
		if (row>1) {
			// 有数据时导出文件
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
	private void addInputStream(SXSSFWorkbook wb, Map<String,InputStream> map){
		String key = this.getFileName() + ".xlsx";
		try {
			// 初始化内存流对象
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			wb.write(out);
			wb.dispose();
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
	 * 富友标题
	 * 2016年4月20日
	 * by 李志伟
	 * @param dataSheet
	 */
	public static void wrapperHeader(Sheet dataSheet, SXSSFWorkbook wb, String title) {
		
		Row headerRow = dataSheet.createRow(0);
		CellStyle cellStyle = wb.createCellStyle();
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);//居中显示
		cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND); // 填充单元格
		cellStyle.setFillForegroundColor(HSSFColor.CORNFLOWER_BLUE.index);
		
		String[] tit = title.split(",");
		for (int i = 0; i < tit.length; i++) {
			Cell lendCodeHeader = headerRow.createCell(i);
			lendCodeHeader.setCellValue(tit[i]);
			lendCodeHeader.setCellStyle(cellStyle);
		}
	}
}
