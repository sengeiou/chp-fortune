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
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;

import com.creditharmony.bpm.utils.SpringUtil;
import com.creditharmony.common.util.Encodes;
import com.creditharmony.common.util.SpringContextHolder;
import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.deduct.type.CardOrBookType;
import com.creditharmony.core.fortune.type.BackMoneyPlat;
import com.creditharmony.core.fortune.type.BackType;
import com.creditharmony.core.fortune.type.ContractVesion;
import com.creditharmony.core.fortune.type.FortuneLogLevel;
import com.creditharmony.core.fortune.type.FortuneLogNode;
import com.creditharmony.core.fortune.type.OpenBank;
import com.creditharmony.core.fortune.type.WorkingState;
import com.creditharmony.core.mybatis.sql.MyBatisSql;
import com.creditharmony.core.mybatis.util.MyBatisSqlUtil;
import com.creditharmony.fortune.back.money.common.view.ListItemView;
import com.creditharmony.fortune.back.money.constant.BmConstant;
import com.creditharmony.fortune.back.money.utils.BmUtils;
import com.creditharmony.fortune.common.dao.FortuneExceptionDao;
import com.creditharmony.fortune.common.entity.FortuneException;
import com.creditharmony.fortune.utils.FileZip;

/**
 * 网银导出需拆分的模板，拆分基数50000
 * @Class Name BmWySplitExportor
 * @author 陈广鹏
 * @Create In 2017年2月27日
 */
public class BmWySplitExportor {
	
	/**
	 * 导出文件名
	 */
	private String filename;
	/**
	 * 文件数据总金额
	 */
//	private BigDecimal sumMoney = BigDecimal.ZERO;
	/**
	 * 单条数据金额
	 */
	private BigDecimal cellMoney = BigDecimal.ZERO;
	/**
	 * 单笔限额
	 */
	private BigDecimal singleLimitMoney = new BigDecimal(50000);
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

	public BmWySplitExportor(String filename) {
		this.filename = filename;
		// 单文件最大限制数量
		fileLimit = BmConstant.splitNum.get(BackMoneyPlat.WY.value);
		
//		PlatformBankEntity entity = BmUtils.getLimitMoney(BackMoneyPlat.WY.value);
//		if (entity.getSingleLimitMoney()!=null && entity.getSingleLimitMoney()!=0) {
//			singleLimitMoney = BigDecimal.valueOf(entity.getSingleLimitMoney()).divide(BigDecimal.valueOf(100));
//		}
//		if (entity.getDayLimitMoney()!=null && entity.getDayLimitMoney()!=0) {
//			dayLimitMoney = BigDecimal.valueOf(entity.getDayLimitMoney()).divide(BigDecimal.valueOf(100));
//		}
	}

	private static Logger logger = LoggerFactory.getLogger(BmWySplitExportor.class);

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
			MyBatisSql batisSql = MyBatisSqlUtil.getMyBatisSql(
					"com.creditharmony.fortune.back.money.common.dao.BackMoneyListDao.findExportList", 
					view, sqlSessionFactory);
			PreparedStatement ps = connection.prepareStatement(batisSql.toString());
			ResultSet resultSet = ps.executeQuery();

			createWorkBook(resultSet);
			if(fileMap.size()==0){
				SXSSFWorkbook wb = new SXSSFWorkbook();
				wb.createSheet("待回款列表1")
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
							"attachment; filename=" + Encodes.urlEncode(filename) + ".xlsx"
									+ ";filename*=UTF-8''" + Encodes.urlEncode(filename) + ".xlsx");
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
		forException.setRemark("线下回款网银拆分导出失败");
		
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
		SXSSFWorkbook wb = new SXSSFWorkbook();
		Sheet dataSheet = wb.createSheet("待回款列表1");
		wrapperHeader(dataSheet);
		
		int row = 1;
		String lendCode;
		String accountNo;
		String accountName;
		BigDecimal backActualbackMoney;
		String backMoneyType;
		String remark;
		String accountBank;
		String accountBranch;
		String accountCardOrBooklet;
		String accountAddrprovince;
		String accountAddrcity;
		String fkAccount;
		String fkBank;
		String fkAccountNo;
		String fkDate;
		String applyAgreementEdition;
		String workingState;

		Row dataRow;
		while (resultSet.next()) {
			lendCode = resultSet.getString("lend_code");
			accountNo = resultSet.getString("account_no");
			accountName = resultSet.getString("account_name");
			BigDecimal suceess = resultSet.getBigDecimal("success_money");
			BigDecimal backMoney = resultSet.getBigDecimal("back_actualback_money");
			suceess = suceess==null?BigDecimal.ZERO:suceess;
			backMoney = backMoney==null?BigDecimal.ZERO:backMoney;
			backActualbackMoney = backMoney.subtract(suceess);
			
			backMoneyType = resultSet.getString("back_money_type");
			if (null != backMoneyType) {
				backMoneyType = BackType.backTypeMap.get(backMoneyType);
			}
			// remark = lendCode + "_" + backMoneyType;
			remark = BmUtils.getRemark(lendCode);
			accountBank = resultSet.getString("account_bank");
			if (StringUtils.isNotEmpty(accountBank)) {
				accountBank = OpenBank.openBankMap.get(accountBank);
			}
			accountBranch = resultSet.getString("account_branch");
			accountCardOrBooklet = resultSet.getString("account_card_or_booklet");
			if (StringUtils.isNotEmpty(accountCardOrBooklet)) {
				accountCardOrBooklet = CardOrBookType.parseByCode(accountCardOrBooklet).getName();
			}
			accountAddrprovince = resultSet.getString("accountAddrprovince");
			accountAddrcity = resultSet.getString("accountAddrcity");
			// 合同版本号
			applyAgreementEdition = resultSet.getString("apply_agreement_edition");
			applyAgreementEdition = ContractVesion.getContractVesion(applyAgreementEdition);
			if (StringUtils.isEmpty(applyAgreementEdition)) {
				applyAgreementEdition = "";
			}
			fkAccount = "";
			fkBank = "";
			fkAccountNo = "";
			fkDate = "";
			workingState = resultSet.getString("working_state");
			if (StringUtils.isNotEmpty(workingState)){
				workingState = WorkingState.workingStateMap.get(workingState);
			}
			
			if (backActualbackMoney.compareTo(dayLimitMoney)==1) {
				// 回款金额大于单日限额，不处理
				continue;
			} else {
				int rs = backActualbackMoney.divide(singleLimitMoney, 0, RoundingMode.UP).intValue();
				if ((row+rs-1) > fileLimit) {
					// 如果数据条数大于单文件限制数量，生成新文件
					addInputStream(wb, fileMap);
					fileIndex ++;
//					sumMoney =BigDecimal.ZERO;
					wb = new SXSSFWorkbook();
					dataSheet = wb.createSheet("待回款列表1");
					wrapperHeader(dataSheet);
					
					row = 1;
				}
				
				for(int i=0;i<rs;i++){
					if (i == (rs-1)) {
						cellMoney = backActualbackMoney;
					} else {
						cellMoney = singleLimitMoney;
						backActualbackMoney = backActualbackMoney.subtract(singleLimitMoney);
					}
//					sumMoney = sumMoney.add(cellMoney);
					dataRow = dataSheet.createRow(row);
					dataRow.createCell(0).setCellValue(lendCode);
					dataRow.createCell(1).setCellValue(accountNo);
					dataRow.createCell(2).setCellValue(accountName);
					dataRow.createCell(3).setCellValue(StringUtils.doNumFormat(cellMoney, "#.00"));
					dataRow.createCell(4).setCellValue(remark);
					dataRow.createCell(5).setCellValue(accountBank);
					dataRow.createCell(6).setCellValue(accountBranch);
					dataRow.createCell(7).setCellValue(accountCardOrBooklet);
					dataRow.createCell(8).setCellValue(BmUtils.deleteTail(accountAddrprovince));
					dataRow.createCell(9).setCellValue(BmUtils.deleteTail(accountAddrcity));
					dataRow.createCell(10).setCellValue(fkAccount);
					dataRow.createCell(11).setCellValue(fkBank);
					dataRow.createCell(12).setCellValue(fkAccountNo);
					dataRow.createCell(13).setCellValue(fkDate);
					dataRow.createCell(14).setCellValue(workingState);
					dataRow.createCell(15).setCellValue(applyAgreementEdition);
					row ++;
				}
			}
		}
		if (row>1) {
			// 有数据时导出文件
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
		Row headerRow = dataSheet.createRow(0);
		headerRow.createCell(0).setCellValue("出借编号");
		headerRow.createCell(1).setCellValue("收款账户");
		headerRow.createCell(2).setCellValue("收款户名");
		headerRow.createCell(3).setCellValue("实际回款金额");
		headerRow.createCell(4).setCellValue("备注"); // 备注 = 出借编号+回款
		headerRow.createCell(5).setCellValue("收款银行");
		headerRow.createCell(6).setCellValue("收款银行支行");
		headerRow.createCell(7).setCellValue("卡或折");
		headerRow.createCell(8).setCellValue("收款省/直辖市");
		headerRow.createCell(9).setCellValue("收款县");
		headerRow.createCell(10).setCellValue("放款账户");
		headerRow.createCell(11).setCellValue("开户行");
		headerRow.createCell(12).setCellValue("银行账号");
		headerRow.createCell(13).setCellValue("放款日期");
		headerRow.createCell(14).setCellValue("在职状态");
		headerRow.createCell(15).setCellValue("合同版本号");
	}
	
	
	/**
	 * 封装压缩excel对象
	 * 2016年4月19日
	 * By 陈广鹏
	 * @param wb
	 * @param map
	 */
	private void addInputStream(SXSSFWorkbook wb,Map<String,InputStream> map){
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
