package com.creditharmony.fortune.back.money.excute.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
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

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;

import com.creditharmony.bpm.utils.SpringUtil;
import com.creditharmony.common.util.DateUtils;
import com.creditharmony.common.util.Encodes;
import com.creditharmony.common.util.SpringContextHolder;
import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.deduct.entity.PlatformBankEntity;
import com.creditharmony.core.fortune.type.BackMoneyPlat;
import com.creditharmony.core.fortune.type.BackType;
import com.creditharmony.core.fortune.type.FortuneLogLevel;
import com.creditharmony.core.fortune.type.FortuneLogNode;
import com.creditharmony.core.fortune.type.OpenBank;
import com.creditharmony.core.mybatis.sql.MyBatisSql;
import com.creditharmony.core.mybatis.util.MyBatisSqlUtil;
import com.creditharmony.fortune.back.money.common.view.ListItemView;
import com.creditharmony.fortune.back.money.constant.BmConstant;
import com.creditharmony.fortune.back.money.utils.BmUtils;
import com.creditharmony.fortune.common.dao.FortuneExceptionDao;
import com.creditharmony.fortune.common.entity.FortuneException;
import com.creditharmony.fortune.utils.FileZip;

public class BmTlExportor {
	
	/**
	 * 导出文件名
	 */
	private String filename;
	/**
	 * 文件数据总金额
	 */
	private BigDecimal sumMoney = BigDecimal.ZERO;
	/**
	 * 单条数据金额
	 */
	private BigDecimal cellMoney = BigDecimal.ZERO;
	/**
	 * 单笔限额
	 */
	private BigDecimal singleLimitMoney = BigDecimal.valueOf(Integer.MAX_VALUE);
	/**
	 * 单日限额
	 */
	private BigDecimal dayLimitMoney = BigDecimal.valueOf(Integer.MAX_VALUE);
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

	public BmTlExportor(String filename) {
		this.filename = filename;
		// 单文件最大限制数量
		fileLimit = BmConstant.splitNum.get(BackMoneyPlat.TL.value);
		
		PlatformBankEntity entity = BmUtils.getLimitMoney(BackMoneyPlat.TL.value);
		if (entity.getSingleLimitMoney()!=null && entity.getSingleLimitMoney()!=0) {
			singleLimitMoney = BigDecimal.valueOf(entity.getSingleLimitMoney()).divide(BigDecimal.valueOf(100));
		}
		if (entity.getDayLimitMoney()!=null && entity.getDayLimitMoney()!=0) {
			dayLimitMoney = BigDecimal.valueOf(entity.getDayLimitMoney()).divide(BigDecimal.valueOf(100));
		}
	}

	private static Logger logger = LoggerFactory.getLogger(BmTlExportor.class);

	/**
	 * 导出Excel方法 
	 * 2016年4月15日 
	 * By 陈广鹏
	 * @param view
	 * @param response
	 */
	public void exportData(ListItemView view, HttpServletResponse response) {

		// 设置搜索条件
		view = BmUtils.assembleExportCondition(view);

		SqlSessionFactory sqlSessionFactory = (SqlSessionFactory) SpringContextHolder
				.getBean("sqlSessionFactory");
		SqlSession sqlSession = sqlSessionFactory.openSession();
		Connection connection = sqlSession.getConnection();
		try {
			// 执行数据库查询语句，带条件
			MyBatisSql batisSql = MyBatisSqlUtil
					.getMyBatisSql(
							"com.creditharmony.fortune.back.money.common.dao.BackMoneyListDao.findExportList",
							view, sqlSessionFactory);
			PreparedStatement ps = connection.prepareStatement(batisSql
					.toString());
			ResultSet resultSet = ps.executeQuery();

			createWorkBook(resultSet);
			if(fileMap.size()==0){
				HSSFWorkbook wb = new HSSFWorkbook();
				wb.createSheet("DataList")
				.createRow(0)
				.createCell(0)
				.setCellValue("导出该空文件原因：数据超过平台限额或没有符合条件的数据");
				addInputStream(wb, fileMap);
			}
			// 下载到客户端
			if(fileMap.size()>1){
				try {
					// 设置Header信息
					response.setHeader("Content-Disposition",
							"attachment; filename=" + Encodes.urlEncode(filename) + ".zip"
							+ ";filename*=UTF-8''" + Encodes.urlEncode(filename) + ".zip");
					out = response.getOutputStream();
				} catch (Exception e) {
					recordException(e);
				}
				FileZip.zipFiles(out, fileMap);
			} else {
				try {
					// 设置Header信息
					response.setHeader("Content-Disposition",
							"attachment; filename=" + Encodes.urlEncode(filename) + "_" + StringUtils.doNumFormat(fileIndex, "00000") + ".xls"
									+ ";filename*=UTF-8''" + Encodes.urlEncode(filename) + "_" + StringUtils.doNumFormat(fileIndex, "00000") + ".xls");
					out = response.getOutputStream();
				} catch (Exception e) {
					recordException(e);
				}
				for (String key : fileMap.keySet()) {
					 FileCopyUtils.copy(fileMap.get(key), out);
				}
			}
		} catch (Exception e) {
			recordException(e);
			logger.debug(filename + "导出数据出现异常");
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				recordException(e);
			}
		}
	}

	/**
	 * 记录异常
	 * 2016年5月5日
	 * By 陈广鹏
	 * @param e
	 */
	private void recordException(Exception e) {
		FortuneException forException = new FortuneException();
		forException.setLoanCode(null);
		forException.setMessage(e.getMessage());
		forException.setStackTrace(ExceptionUtils.getFullStackTrace(e));
		forException.setNode(FortuneLogNode.MONEY_EXCUTE.getCode());
		forException.setImportantLevel(FortuneLogLevel.YELLOW.value);
		forException.setRemark("线下回款通联导出失败");
		
		forException.preInsert();
		FortuneExceptionDao forDao = SpringUtil.getCtx().getBean(FortuneExceptionDao.class);
		forDao.insert(forException);
	}
	
	/**
	 * 创建工作簿
	 * 2016年4月19日
	 * By 陈广鹏
	 * @param resultSet
	 * @param dataSheet
	 * @throws Exception
	 */
	private void createWorkBook(ResultSet resultSet) throws Exception {
		HSSFWorkbook wb = new HSSFWorkbook();
		Sheet dataSheet = wb.createSheet("DataList");
		wrapperHeader(dataSheet);
		
		int row = 2;
		String customerCode;
		String accountBank;
		String accountCardOrBooklet;
		String accountNo;
		String accountName;
		String accountAddrprovince;
		String accountAddrcity;
		String accountBankName = "";
		String accType = "0";
		BigDecimal backActualbackMoney;
		String currencyType = "CNY";
		String agreementNo = "";
		String agreeCustomerCode = "";
		String dictCustomerCertType;
		String customerCertNum = "";
		String customerMobilephone = "";
		String customerNo = "";
		String backMoneyType;
		String lendCode;
		String tlRemark = "";
		String responseCode = "";
		String reason = "";

		Row dataRow;
		while (resultSet.next()) {
			customerCode = resultSet.getString("customer_code");
			accountBank = resultSet.getString("account_bank");
			accountCardOrBooklet = resultSet.getString("account_card_or_booklet");
			accountNo = resultSet.getString("account_no");
			accountName = resultSet.getString("account_name");
			accountAddrprovince = resultSet.getString("accountAddrprovince");
			accountAddrcity = resultSet.getString("accountAddrcity");
			if (StringUtils.isNotEmpty(accountBank)) {
				accountBankName = OpenBank.openBankMap.get(accountBank);
			}
			BigDecimal suceess = resultSet.getBigDecimal("success_money");
			BigDecimal backMoney = resultSet.getBigDecimal("back_actualback_money");
			suceess = suceess==null?BigDecimal.ZERO:suceess;
			backMoney = backMoney==null?BigDecimal.ZERO:backMoney;
			backActualbackMoney = backMoney.subtract(suceess);
			dictCustomerCertType = resultSet.getString("dict_customer_cert_type");
			lendCode = resultSet.getString("lend_code");
			backMoneyType = resultSet.getString("back_money_type");
			if (null != backMoneyType) {
				backMoneyType = BackType.backTypeMap.get(backMoneyType);
			}
			tlRemark = lendCode + "_" + backMoneyType;
			
			if (backActualbackMoney.compareTo(dayLimitMoney)==1) {
				// 回款金额大于单日限额，不处理
				continue;
			} else {
				int rs = backActualbackMoney.divide(singleLimitMoney, 0, RoundingMode.UP).intValue();
				if ((row+rs-2) > fileLimit) {
					// 如果数据条数大于单文件限制数量，生成新文件
					wrapperTitle(dataSheet, row);
					addInputStream(wb, fileMap);
					fileIndex ++;
					sumMoney =BigDecimal.ZERO;
					wb = new HSSFWorkbook();
					dataSheet = wb.createSheet("DataList");
					wrapperHeader(dataSheet);
					
					row = 2;
				}
				
				for(int i=0;i<rs;i++){
					if (i == (rs-1)) {
						cellMoney = backActualbackMoney;
					} else {
						cellMoney = singleLimitMoney;
						backActualbackMoney = backActualbackMoney.subtract(singleLimitMoney);
					}
					sumMoney = sumMoney.add(cellMoney);
					dataRow = dataSheet.createRow(row);
					dataRow.createCell(0).setCellValue((row-1) + ""); // 序号
					dataRow.createCell(1).setCellValue(customerCode);
					dataRow.createCell(2).setCellValue(accountBank);
					dataRow.createCell(3).setCellValue(accountCardOrBooklet);
					dataRow.createCell(4).setCellValue(accountNo);
					dataRow.createCell(5).setCellValue(accountName);
					dataRow.createCell(6).setCellValue(accountAddrprovince);
					dataRow.createCell(7).setCellValue(accountAddrcity);
					dataRow.createCell(8).setCellValue(accountBankName);
					dataRow.createCell(9).setCellValue(accType);
					dataRow.createCell(10).setCellValue(StringUtils.doNumFormat(cellMoney.multiply(new BigDecimal("100")), "##0"));
					dataRow.createCell(11).setCellValue(currencyType);
					dataRow.createCell(12).setCellValue(agreementNo);
					dataRow.createCell(13).setCellValue(agreeCustomerCode);
					dataRow.createCell(14).setCellValue(dictCustomerCertType);
					dataRow.createCell(15).setCellValue(customerCertNum);
					dataRow.createCell(16).setCellValue(customerMobilephone);
					dataRow.createCell(17).setCellValue(customerNo);
					dataRow.createCell(18).setCellValue(tlRemark);
					dataRow.createCell(19).setCellValue(responseCode);
					dataRow.createCell(20).setCellValue(reason);
					row ++;
				}
			}
		}
		if (row>2) {
			// 有数据时导出文件
			wrapperTitle(dataSheet, row);
			addInputStream(wb, fileMap);
		}
	}

	/**
	 * 设置表头
	 * 2016年4月19日
	 * By 陈广鹏
	 * @param dataSheet
	 */
	private void wrapperHeader(Sheet dataSheet) {
		Row headerRow = dataSheet.createRow(1);
		headerRow.createCell(0).setCellValue("序号*");
		headerRow.createCell(1).setCellValue("用户编号");
		headerRow.createCell(2).setCellValue("银行代码*");
		headerRow.createCell(3).setCellValue("账号类型");
		headerRow.createCell(4).setCellValue("账号*");
		headerRow.createCell(5).setCellValue("户名*");
		headerRow.createCell(6).setCellValue("省");
		headerRow.createCell(7).setCellValue("市");
		headerRow.createCell(8).setCellValue("开户行名称");
		headerRow.createCell(9).setCellValue("账户类型");
		headerRow.createCell(10).setCellValue("金额*");
		headerRow.createCell(11).setCellValue("货币类型");
		headerRow.createCell(12).setCellValue("协议号");
		headerRow.createCell(13).setCellValue("协议用户编号");
		headerRow.createCell(14).setCellValue("开户证件类型");
		headerRow.createCell(15).setCellValue("证件号");
		headerRow.createCell(16).setCellValue("手机号/小灵通");
		headerRow.createCell(17).setCellValue("自定义用户号");
		headerRow.createCell(18).setCellValue("备注"); // 备注 = 出借编号_回款类型
		headerRow.createCell(19).setCellValue("反馈码");
		headerRow.createCell(20).setCellValue("原因");
	}
	
	/**
	 * 设置title
	 * 2016年4月19日
	 * By 陈广鹏
	 * @param dataSheet
	 * @param row
	 */
	private void wrapperTitle(Sheet dataSheet, int row){
		Row titleRow = dataSheet.createRow(0);
		titleRow.createCell(0).setCellValue("F");
		titleRow.createCell(1).setCellValue("200100000015107");
		titleRow.createCell(2).setCellValue(DateUtils.getDate("yyyyMMdd"));
		titleRow.createCell(3).setCellValue(row -2);
		titleRow.createCell(4).setCellValue(StringUtils.doNumFormat(sumMoney.multiply(new BigDecimal("100")), "##0"));
		titleRow.createCell(5).setCellValue("09900");
	}
	
	/**
	 * 封装压缩excel对象
	 * 2016年4月19日
	 * By 陈广鹏
	 * @param wb
	 * @param map
	 */
	private void addInputStream(HSSFWorkbook wb,Map<String,InputStream> map){
		String key = filename + "_" + StringUtils.doNumFormat(fileIndex, "00000") + ".xls";
		try {
			// 初始化内存流对象
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			wb.write(out);
//			wb.dispose();
			// 转换成内存输出流
			InputStream input = new ByteArrayInputStream(out.toByteArray());
			map.put(key, input);
			out.flush();
			out.close();
		} catch (Exception e) {
			recordException(e);
		}
	}

}
