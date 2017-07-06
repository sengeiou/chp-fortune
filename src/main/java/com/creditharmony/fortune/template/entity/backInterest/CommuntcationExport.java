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
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.validator.internal.util.privilegedactions.GetClassLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;

import com.creditharmony.common.util.DateUtils;
import com.creditharmony.common.util.Encodes;
import com.creditharmony.common.util.PropertyUtil;
import com.creditharmony.common.util.SpringContextHolder;
import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.fortune.type.OpenBank;
import com.creditharmony.core.mybatis.sql.MyBatisSql;
import com.creditharmony.core.mybatis.util.MyBatisSqlUtil;
import com.creditharmony.fortune.back.interest.contants.ExportConstant;
import com.creditharmony.fortune.back.interest.contants.GlobalConstant;
import com.creditharmony.fortune.back.interest.util.FileNameMadeFactory;
import com.creditharmony.fortune.back.money.utils.BmExportor;
import com.creditharmony.fortune.deduct.common.DeductUtils;
import com.creditharmony.fortune.utils.FileZip;

/**
 * 通联导出
 * @Class Name CommuntcationExport 
 * @author 李志伟
 * @Create In 2016年4月20日
 */
public class CommuntcationExport {

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
	 * 输出流
	 */
	private OutputStream out;
	/**
	 * 文件流集合
	 */
	private Map<String,InputStream> fileMap = new HashMap<String,InputStream>();
	/**
	 * 文件数据数量限额，超出该数量生成新文件
	 */
	private int fileLimit;
	
	/**
	 * 每笔回息需要拆分次数
	 */
	private int time = BigDecimal.ZERO.intValue();
	
	/**
	 * 文件名
	 */
	private String fileName;

	public CommuntcationExport(){
		
		// 单文件最大限制数量
		int totalLimit = GlobalConstant.fileNo;
		// 一条数据最大拆分数量
		int maxSplitNum = GlobalConstant.maxSplitNum;
		fileLimit = totalLimit - maxSplitNum;
		fileName = getFileName();
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
							ExportConstant.tl_id,
							map, sqlSessionFactory);
			PreparedStatement ps = connection.prepareStatement(batisSql
					.toString());
			ResultSet resultSet = ps.executeQuery();

			createWorkBook(resultSet);
			// 下载到客户端
			if(fileMap.size()>1){
				try {
					// 设置Header信息
					response.setHeader("Content-Disposition",
							"attachment; fileName=" + Encodes.urlEncode("执行回息通联导出") + ".zip"
							+ ";fileName*=UTF-8''" + Encodes.urlEncode("执行回息通联导出") + ".zip");
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
			logger.debug(getFileName() + "导出数据出现异常");
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
		XSSFWorkbook wb = new XSSFWorkbook();
		Sheet dataSheet = wb.createSheet("DataList");
		dataSheet.setDefaultColumnWidth(50000);
		wrapperHeader(dataSheet, wb);
		
		int row = 2;
		String customerCode;
		String accountBank;
		String accountCardOrBooklet;
		String accountNo;
		String accountName;
		String province;
		String city;
		
		String accountBankName = "";
		String accType = "0";// 账户类型
		BigDecimal backRealMoney;
		BigDecimal successMoney;
		String currencyType = "CNY";
		String agreementNo = "";
		String agreeCustomerCode = "";
		
		//String dictCustomerCertType="";
		String customerCertNum = "";
		//String customerMobilephone = "";
		String customerNo = "";
		
		String lendCode;
		//String backiId;
		String currentBillday;// 账单日
		String responseCode = "";
		String reason = "";
		// 单笔限额
		BigDecimal singleLimitMoney = GlobalConstant.limitMaxMoney;// 单笔限额20W
		// 单日限额
		BigDecimal dayLimitMoney = GlobalConstant.daysMaxMoney;// 单日限额300W
		
		Row dataRow;
		while (resultSet.next()) {
			
			customerCode = resultSet.getString("customer_code");
			accountBank = resultSet.getString("account_bank");
			accountCardOrBooklet = resultSet.getString("account_card_or_booklet");
			accountNo = resultSet.getString("account_no");
			accountName = resultSet.getString("account_name");
			province = resultSet.getString("province");
			city = resultSet.getString("city");
			lendCode = resultSet.getString("lend_code");
			
			if (StringUtils.isNotEmpty(accountBank)) {
				accountBankName = OpenBank.openBankMap.get(accountBank);
			}else{
				accountBankName = "";
			}
			
			backRealMoney = resultSet.getBigDecimal("back_real_money");
			backRealMoney = backRealMoney==null? new BigDecimal(0) : backRealMoney;
			successMoney = resultSet.getBigDecimal("success_money");
			successMoney = successMoney==null? new BigDecimal(0) : successMoney;
			backRealMoney = backRealMoney.subtract(successMoney);
			
			//dictCustomerCertType = resultSet.getString("dict_customer_cert_type");
			//customerCertNum = resultSet.getString("customer_cert_num");
			//customerMobilephone = resultSet.getString("customer_mobilephone");
			//lendCode = resultSet.getString("lend_code");
			//backiId = resultSet.getString("backi_id");
			
			currentBillday = resultSet.getString("current_billday");
			
			int to = backRealMoney.compareTo(BigDecimal.ZERO);
			if(to==1){
				if (backRealMoney.compareTo(dayLimitMoney)==1) {
					// 回款金额大于单日限额，不处理
					Row ro = dataSheet.createRow(row);
					ro.createCell(0).setCellValue(lendCode);
					ro.createCell(1).setCellValue(GlobalConstant.OUT_OF_LIMIT);
					row++;
					continue;
				} else {
					
					BigDecimal [] rs = backRealMoney.divideAndRemainder(singleLimitMoney);
					int quotient = rs[0].intValue();// 商
					int remainder = rs[1].intValue();// 余数
					if(remainder== BigDecimal.ZERO.intValue()){
						time = quotient;
					}else{
						time = quotient + BigDecimal.ONE.intValue();
					}
					
					/*
					 *  如果当前需要拆分笔数加上文件中数据笔数大于500笔或者当前文件中数据条数减去标题条数大于等于单文件数据条数，
					 *  则生成新文件，
					 */
					if ((row-2) >= fileLimit || (row-2+time) > fileLimit) {
						// 如果数据条数大于单文件限制数量，生成新文件
						wrapperTitle(dataSheet, row, wb);
						addInputStream(wb, fileMap);
						fileIndex ++;
						sumMoney =BigDecimal.ZERO;
						wb = new XSSFWorkbook();
						dataSheet = wb.createSheet("DataList");
						wrapperHeader(dataSheet, wb);
						row = 2;
					}
					
					for(int i=0;i < time;i++){
						
						if (i == time-1) {
							cellMoney = backRealMoney;
						} else {
							cellMoney = singleLimitMoney;
							backRealMoney = backRealMoney.subtract(singleLimitMoney);
						}
						// 第一行标题总金额
						sumMoney = sumMoney.add(cellMoney);
						dataRow = dataSheet.createRow(row);
						dataRow.createCell(0).setCellValue((row-1) + ""); // 序号
						dataRow.createCell(1).setCellValue(DeductUtils.isNull(customerCode));
						dataRow.createCell(2).setCellValue(DeductUtils.isNull(accountBank));
						dataRow.createCell(3).setCellValue(DeductUtils.isNull(accountCardOrBooklet));
						dataRow.createCell(4).setCellValue(DeductUtils.isNull(accountNo));
						dataRow.createCell(5).setCellValue(DeductUtils.isNull(accountName));
						dataRow.createCell(6).setCellValue(DeductUtils.isNull(province));
						dataRow.createCell(7).setCellValue(DeductUtils.isNull(city));
						dataRow.createCell(8).setCellValue(DeductUtils.isNull(accountBankName));
						dataRow.createCell(9).setCellValue(DeductUtils.isNull(accType));
						dataRow.createCell(10).setCellValue(StringUtils.doNumFormat(cellMoney.multiply(GlobalConstant.ONE), "##0"));
						dataRow.createCell(11).setCellValue(DeductUtils.isNull(currencyType));
						dataRow.createCell(12).setCellValue(DeductUtils.isNull(agreementNo));
						dataRow.createCell(13).setCellValue(DeductUtils.isNull(agreeCustomerCode));
						dataRow.createCell(14).setCellValue(DeductUtils.isNull(""));
						dataRow.createCell(15).setCellValue(DeductUtils.isNull(customerCertNum));
						dataRow.createCell(16).setCellValue(DeductUtils.isNull(""));
						dataRow.createCell(17).setCellValue(DeductUtils.isNull(customerNo));
						dataRow.createCell(18).setCellValue(DeductUtils.isNull(lendCode+"回息"+currentBillday));// 备注-- 出借编号回息账单日
						dataRow.createCell(19).setCellValue(DeductUtils.isNull(responseCode));
						dataRow.createCell(20).setCellValue(DeductUtils.isNull(reason));
						row ++;
					}
				}
			}else{
				dataRow = dataSheet.createRow(row);
				dataRow.createCell(0).setCellValue(lendCode);
				dataRow.createCell(1).setCellValue(GlobalConstant.ERROR);
				row++;
			}
		}
		if (row>2) {
			// 有数据时导出文件
			wrapperTitle(dataSheet, row, wb);
			addInputStream(wb, fileMap);
		}
	}

	/**
	 * 设置title
	 * 2016年4月19日
	 * By Mr.Li
	 * @param dataSheet
	 * @param row
	 */
	private void wrapperTitle(Sheet dataSheet, int row, XSSFWorkbook swb){
		
		CellStyle cellStyle = swb.createCellStyle();
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);//居中显示
		cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND); // 填充单元格
		cellStyle.setFillForegroundColor(HSSFColor.CORNFLOWER_BLUE.index);
		
		Row titleRow = dataSheet.createRow(0);
		
		dataSheet.setColumnWidth(0, 4000);
		Cell cell1 = titleRow.createCell(0);
		cell1.setCellValue("F");
		cell1.setCellStyle(cellStyle);
		
		dataSheet.setColumnWidth(1, 4000);
		Cell cell2 = titleRow.createCell(1);
		cell2.setCellValue("200100000015107");
		cell2.setCellStyle(cellStyle);
		
		dataSheet.setColumnWidth(2, 4000);
		Cell cell3 = titleRow.createCell(2);
		cell3.setCellValue(DateUtils.getDate("yyyyMMdd"));
		cell3.setCellStyle(cellStyle);
		
		dataSheet.setColumnWidth(3, 4000);
		Cell cell4 = titleRow.createCell(3);
		cell4.setCellValue(row -2);
		cell4.setCellStyle(cellStyle);
		
		dataSheet.setColumnWidth(4, 4000);
		Cell cell5 = titleRow.createCell(4);
		cell5.setCellValue(StringUtils.doNumFormat(sumMoney.multiply(new BigDecimal("100")), "##0"));
		cell5.setCellStyle(cellStyle);
		
		dataSheet.setColumnWidth(5, 4000);
		Cell cell6 = titleRow.createCell(5);
		cell6.setCellValue("09900");
		cell6.setCellStyle(cellStyle);
	}
	
	/**
	 * 封装压缩excel对象
	 * 2016年4月19日
	 * By Mr.Li
	 * @param wb
	 * @param map
	 */
	private void addInputStream(XSSFWorkbook wb, Map<String,InputStream> map){
		String key = getFileName()+"_"+ fileIndex + ".xlsx";
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
	 * 通联标题
	 * 2016年4月20日
	 * by 李志伟
	 * @param dataSheet
	 */
	public static void wrapperHeader(Sheet dataSheet, XSSFWorkbook swb) {
	
		String s = ExportConstant.TL_TITILE;
		String[] split = s.split(",");
		Row headerRow = dataSheet.createRow(1);
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
	
	/**
	 * 获取导出文件名
	 * 2016年5月7日
	 * by 李志伟
	 * @return
	 */
	public String getFileName() {
		
		Properties property = PropertyUtil.getProperties("fileNameNo.properties");
		
		String value = property.getProperty("tongLian_platForm_No");
		int s = Integer.valueOf(value).intValue();
		String currentTime = DateUtils.getDate("yyyyMMdd");
		String date = property.getProperty("tongLian_time");
		
		if(!date.equals(currentTime)){
			value="1";
			s = 1;
			PropertyUtil.updateProperties("fileNameNo.properties", "tongLian_time", currentTime);
		}
		//重新写入配置文件(不要放在if里边)
		PropertyUtil.updateProperties("fileNameNo.properties", "tongLian_platForm_No", String.valueOf(++s));

		FileNameMadeFactory fnmf = new FileNameMadeFactory(ExportConstant.TL_EXPORT, value);
		String name = fnmf.zjAndFyMadeFileName();
		return name;
	}
}
