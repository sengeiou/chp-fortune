package com.creditharmony.fortune.look.apply.util;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.tools.ant.util.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.creditharmony.common.util.Encodes;
import com.creditharmony.common.util.SpringContextHolder;
import com.creditharmony.core.fortune.type.WorkingState;
import com.creditharmony.core.mybatis.sql.MyBatisSql;
import com.creditharmony.core.mybatis.util.MyBatisSqlUtil;

/**
 * 导出回息数据帮助类
 * 
 * @Class Name ExporLendExcel
 * @author 张永生
 * @Create In 2016年4月14日
 */
public class ExporLendExcel {

	private static Logger logger = LoggerFactory
			.getLogger(ExporLendExcel.class);

	/**
	 * 导出工具 2016年4月15日 by 李志伟
	 * 
	 * @param queryMap
	 *            查询条件
	 * @param response
	 *            响应对象
	 * @param fileName
	 *            导出文件名字
	 * @param sqlNameSpace
	 *            查询数据用的sql名称及语句路劲
	 * @param sheetName
	 *            sheet名称
	 */
	public static void exportData(Map<String, Object> queryMap,
			HttpServletResponse response) {

		SqlSessionFactory sqlSessionFactory = (SqlSessionFactory) SpringContextHolder
				.getBean("sqlSessionFactory");
		SqlSession sqlSession = sqlSessionFactory.openSession();
		Connection connection = sqlSession.getConnection();
		try {

			// 执行数据库查询语句，带条件
			MyBatisSql batisSql = MyBatisSqlUtil
					.getMyBatisSql(
							"com.creditharmony.fortune.look.apply.dao.LendApplyLookDao.getExportLendExcelList",
							queryMap, sqlSessionFactory);
			PreparedStatement ps = connection.prepareStatement(batisSql
					.toString());
			ResultSet resultSet = ps.executeQuery();

			SXSSFWorkbook wb = new SXSSFWorkbook();
			Sheet dataSheet = wb.createSheet("出借申请查看（出借）");
			wrapperHeader(dataSheet);
			assembleExcelCell(resultSet, dataSheet);
			response.reset();
			response.setContentType("application/octet-stream; charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setHeader(
					"Content-Disposition",
					"attachment; filename="
							+ Encodes.urlEncode("出借申请查看（出借）.xlsx")
							+ ";filename*=UTF-8''"
							+ Encodes.urlEncode("出借申请查看（出借）.xlsx"));
			wb.write(response.getOutputStream());
			wb.dispose();
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("exportData()导出数据出现异常");
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private static void assembleExcelCell(ResultSet resultSet, Sheet dataSheet)
			throws SQLException {
		int row = 1;
		// 备注 不需要赋值
		String remark;
		// 财富中心
		String fortunCenter;
		// 门店
		String storeName;
		// 客户姓名
		String custName;
		// 客户编号
		String custCode;
		// 出借编号
		String lendCode;
		// 审核日期
		String shenPi;
		// 划扣日期
		String huakou;
		// 首次出借日期
		String applyLendDay;
		// 初始出借金额
		String firstAmount;
		// 出借模式
		String productName;
		// 产品到期日
		String applyExpireDay;
		// 付款方式
		String payType; // apply_pay
		// 协议版本
		String applyAgreementEdition;
		// 账单日
		String applyBillday;
		// 第一个账单日
		String firstBill;
		// 划扣行别
		String openBank;
		// 划扣开户行
		String branch;
		// 划扣账号
		String cardNo;
		// 回款行别
		String backOpenBank;
		// 回款开户行
		String backBranch;
		// 回款账号
		String backCardNo;
		// 账号类型
		String accountCardOrBooklet;
		// 所在城市
		String accountAddrcity;
		// 回款日期
		String back;
		// 回款金额
		String backMoney;
		// 状态
		String status; // 出借中。。。。
		// 完结状态
		String dictApplyEndState;
		// 合同编号
		String applyContractNo;
		// 信和宝类型
		String xinhebaoType;
		// 年化收益率
		String yearRate;
		// 理财经理
		String managerCode;
		// 账单收取方式
		String loanBillRecv;
		//回款状态
		String backMoeyType;
		//在职状态
		String  workingState;
		//自转审核日期
		String zzApproveDate;//zz_approve_date
		
		Row dataRow;
		
		// 备注 不需要赋值
		Cell remarkCell;
		// 财富中心
		Cell fortunCenterCell;
		// 门店
		Cell storeNameCell;
		// 客户姓名
		Cell custNameCell;
		// 客户编号
		Cell custCodeCell;
		// 出借编号
		Cell lendCodeCell;
		// 审核日期
		Cell shenPiCell;
		// 划扣日期
		Cell huakouCell;
		// 首次出借日期
		Cell applyLendDayCell;
		// 初始出借金额
		Cell firstAmountCell;
		// 出借模式
		Cell productNameCell;
		// 产品到期日
		Cell applyExpireDayCell;
		// 付款方式
		Cell payTypeCell; // apply_pay
		// 协议版本
		Cell applyAgreementEditionCell;
		// 账单日
		Cell applyBilldayCell;
		// 第一个账单日
		Cell firstBillCell;
		// 划扣行别
		Cell openBankCell;
		// 划扣开户行
		Cell branchCell;
		// 划扣账号
		Cell cardNoCell;
		// 回款行别
		Cell backOpenBankCell;
		// 回款开户行
		Cell backBranchCell;
		// 回款账号
		Cell backCardNoCell;
		// 账号类型
		Cell accountCardOrBookletCell;
		// 所在城市
		Cell accountAddrcityCell;
		// 回款日期
		Cell backCell;
		// 回款金额
		Cell backMoneyCell;
		// 状态
		Cell statusCell; // 出借中。。。。
		// 完结状态
		Cell dictApplyEndStateCell;
		// 合同编号
		Cell applyContractNoCell;
		// 信和宝类型
		Cell xinhebaoTypeCell;
		// 年化收益率
		Cell yearRateCell;
		// 理财经理
		Cell managerCodeCell;
		// 账单收取方式
		Cell loanBillRecvCell;
		//回款状态
		Cell backMoeyTypeCell;
		//在职状态
		Cell workingStateCell;
		//自转审核日期
		Cell zzApproveDateCell;
		while (resultSet.next()) {
			// 备注 不需要赋值
			remark = resultSet.getString("remark");
			// 财富中心
			fortunCenter = resultSet.getString("fortunCenter");
			// 门店
			storeName = resultSet.getString("storeName");
			// 客户姓名
			custName = resultSet.getString("custName");
			// 屏蔽客户姓名/手机号/身份证号
			custName = "***";
			// 客户编号
			custCode = resultSet.getString("custCode");
			// 出借编号
			lendCode = resultSet.getString("lendCode");
			// 审核日期
			shenPi = resultSet.getString("shenPi");
			// 划扣日期
			huakou = resultSet.getString("huakou");
			// 首次出借日期
			applyLendDay = resultSet.getString("applyLendDay");
			// 初始出借金额
			firstAmount = resultSet.getString("firstAmount");
			// 出借模式
			productName = resultSet.getString("productName");
			// 产品到期日
			applyExpireDay = resultSet.getString("applyExpireDay");
			// 付款方式
			payType = resultSet.getString("payType"); // apply_pay
			// 协议版本
			applyAgreementEdition = resultSet
					.getString("applyAgreementEdition");
			// 账单日
			applyBillday = resultSet.getString("applyBillday");
			// 第一个账单日
			firstBill = resultSet.getString("firstBill");
			// 划扣行别
			openBank = resultSet.getString("openBank");
			// 划扣开户行
			branch = resultSet.getString("branch");
			// 划扣账号
			cardNo = resultSet.getString("cardNo");
			// 回款行别
			backOpenBank = resultSet.getString("backOpenBank");
			// 回款开户行
			backBranch = resultSet.getString("backBranch");
			// 回款账号
			backCardNo = resultSet.getString("backCardNo");
			// 账号类型
			accountCardOrBooklet = resultSet.getString("accountCardOrBooklet");
			// 所在城市
			accountAddrcity = resultSet.getString("accountAddrcity");
			// 回款日期
			back = resultSet.getString("back");
			// 回款金额
			backMoney = resultSet.getString("backMoney");
			// 状态
			status = resultSet.getString("status"); // 出借中。。。。
			// 完结状态
			dictApplyEndState = resultSet.getString("dictApplyEndState");
			// 合同编号
			applyContractNo = resultSet.getString("applyContractNo");
			// 信和宝类型
			xinhebaoType = resultSet.getString("xinhebaoType");
			// 年化收益率
			yearRate = resultSet.getString("yearRate");
			// 理财经理
			managerCode = resultSet.getString("managerCode");
			// 账单收取方式
			loanBillRecv = resultSet.getString("loanBillRecv");
			//回款状态
			backMoeyType=resultSet.getString("backMoeyType");
			//在职状态
			workingState=resultSet.getString("workingState");
			if (StringUtils.isNotEmpty(workingState)) {
				workingState = WorkingState.workingStateMap.get(workingState);
			}
			//自转审核日期
			if(StringUtils.isNotEmpty(resultSet.getString("zzApproveDate"))){
				zzApproveDate = com.creditharmony.common.util.DateUtils.formatDate(resultSet.getDate("zzApproveDate"), "yyyy-MM-dd");
			}else{
				zzApproveDate = "";
			}
			
			dataRow = dataSheet.createRow(row);
			// 备注 不需要赋值
			remarkCell = dataRow.createCell(0);
			remarkCell.setCellValue("");
			// 财富中心
			fortunCenterCell = dataRow.createCell(1);
			fortunCenterCell.setCellValue(fortunCenter);
			// 门店
			storeNameCell = dataRow.createCell(2);
			storeNameCell.setCellValue(storeName);
			// 客户姓名
			custNameCell = dataRow.createCell(3);
			custNameCell.setCellValue(custName);
			// 客户编号
			custCodeCell = dataRow.createCell(4);
			custCodeCell.setCellValue(custCode);
			// 出借编号
			lendCodeCell = dataRow.createCell(5);
			lendCodeCell.setCellValue(lendCode);
			// 审核日期
			shenPiCell = dataRow.createCell(6);
			shenPiCell.setCellValue(shenPi);
			// 划扣日期
			huakouCell = dataRow.createCell(7);
			huakouCell.setCellValue(huakou);
			// 首次出借日期
			applyLendDayCell = dataRow.createCell(8);
			applyLendDayCell.setCellValue(applyLendDay);
			// 初始出借金额
			firstAmountCell = dataRow.createCell(9);
			firstAmountCell.setCellValue(getRound2(firstAmount));
			// 出借模式
			productNameCell = dataRow.createCell(10);
			productNameCell.setCellValue(productName);
			// 产品到期日
			applyExpireDayCell = dataRow.createCell(11);
			applyExpireDayCell.setCellValue(applyExpireDay);
			// 付款方式
			payTypeCell = dataRow.createCell(12);
			payTypeCell.setCellValue(payType);
			// 协议版本
			applyAgreementEditionCell = dataRow.createCell(13);
			applyAgreementEditionCell.setCellValue(applyAgreementEdition);
			// 账单日
			applyBilldayCell = dataRow.createCell(14);
			applyBilldayCell.setCellValue(applyBillday);
			// 第一个账单日
			firstBillCell = dataRow.createCell(15);
			firstBillCell.setCellValue(firstBill);
			// 划扣行别
			openBankCell = dataRow.createCell(16);
			openBankCell.setCellValue(openBank);
			// 划扣开户行
			branchCell = dataRow.createCell(17);
			branchCell.setCellValue(branch);
			// 划扣账号
			cardNoCell = dataRow.createCell(18);
			cardNoCell.setCellValue(cardNo);
			// 回款行别
			backOpenBankCell = dataRow.createCell(19);
			backOpenBankCell.setCellValue(backOpenBank);
			// 回款开户行
			backBranchCell = dataRow.createCell(20);
			backBranchCell.setCellValue(backBranch);
			// 回款账号
			backCardNoCell = dataRow.createCell(21);
			backCardNoCell.setCellValue(backCardNo);
			// 账号类型
			accountCardOrBookletCell = dataRow.createCell(22);
			accountCardOrBookletCell.setCellValue(accountCardOrBooklet);
			// 所在城市
			accountAddrcityCell = dataRow.createCell(23);
			accountAddrcityCell.setCellValue(accountAddrcity);
			// 回款日期
			backCell = dataRow.createCell(24);
			backCell.setCellValue(back);
			// 回款金额
			backMoneyCell = dataRow.createCell(25);
			backMoneyCell.setCellValue(getRound2(backMoney));
			// 状态
			statusCell = dataRow.createCell(26);
			statusCell.setCellValue(status);
			// 完结状态
			dictApplyEndStateCell = dataRow.createCell(27);
			dictApplyEndStateCell.setCellValue(dictApplyEndState);
			// 合同编号
			applyContractNoCell = dataRow.createCell(28);
			applyContractNoCell.setCellValue(applyContractNo);
			// 信和宝类型
			xinhebaoTypeCell = dataRow.createCell(29);
			xinhebaoTypeCell.setCellValue(xinhebaoType);
			// 年化收益率
			yearRateCell = dataRow.createCell(30);
			yearRateCell.setCellValue(yearRate);
			// 理财经理
			managerCodeCell = dataRow.createCell(31);
			managerCodeCell.setCellValue(managerCode);
			// 账单收取方式
			loanBillRecvCell = dataRow.createCell(32);
			loanBillRecvCell.setCellValue(loanBillRecv);
			
			//回款状态
			backMoeyTypeCell = dataRow.createCell(33);
			backMoeyTypeCell.setCellValue(backMoeyType);
			
			//在职状态
			workingStateCell = dataRow.createCell(34);
			workingStateCell.setCellValue(workingState);
			
			//自转审核日期
			zzApproveDateCell = dataRow.createCell(35);
			zzApproveDateCell.setCellValue(zzApproveDate);
			
			row = row + 1;
		}
	}
	/**
	 * 获取2位精度
	 * 2016年5月31日
	 * By 肖长伟
	 * @param numberStr
	 * @return
	 */
	private static String getRound2(String numberStr) {
		if (StringUtils.isBlank(numberStr)) {
			return "";
		} else {
			BigDecimal bigDecimal = new BigDecimal(numberStr).setScale(2, BigDecimal.ROUND_HALF_UP);
			return bigDecimal.toString();
		}
	}
	private static void wrapperHeader(Sheet dataSheet) {
		Row headerRow = dataSheet.createRow(0);
		// 备注 不需要赋值
		Cell remarkHeader = headerRow.createCell(0);
		remarkHeader.setCellValue("备注");
		// 财富中心
		Cell fortunCenterHeader = headerRow.createCell(1);
		fortunCenterHeader.setCellValue("财富中心");
		// 门店
		Cell storeNameHeader = headerRow.createCell(2);
		storeNameHeader.setCellValue("门店");
		// 客户姓名
		Cell custNameHeader = headerRow.createCell(3);
		custNameHeader.setCellValue("客户姓名");
		// 客户编号
		Cell custCodeHeader = headerRow.createCell(4);
		custCodeHeader.setCellValue("客户编号");
		// 出借编号
		Cell lendCodeHeader = headerRow.createCell(5);
		lendCodeHeader.setCellValue("出借编号");
		// 审核日期
		Cell shenPiHeader = headerRow.createCell(6);
		shenPiHeader.setCellValue("审核日期");
		// 划扣日期
		Cell huakouHeader = headerRow.createCell(7);
		huakouHeader.setCellValue("划扣日期");
		// 首次出借日期
		Cell applyLendDayHeader = headerRow.createCell(8);
		applyLendDayHeader.setCellValue("首次出借日期");
		// 初始出借金额
		Cell firstAmountHeader = headerRow.createCell(9);
		firstAmountHeader.setCellValue("初始出借金额");
		// 出借模式
		Cell productNameHeader = headerRow.createCell(10);
		productNameHeader.setCellValue("出借模式");
		// 产品到期日
		Cell applyExpireDayHeader = headerRow.createCell(11);
		applyExpireDayHeader.setCellValue("产品到期日");
		// 付款方式
		Cell payTypeHeader = headerRow.createCell(12); // apply_pay
		payTypeHeader.setCellValue("付款方式");
		// 协议版本
		Cell applyAgreementEditionHeader = headerRow.createCell(13);
		applyAgreementEditionHeader.setCellValue("协议版本");
		// 账单日
		Cell applyBilldayHeader = headerRow.createCell(14);
		applyBilldayHeader.setCellValue("账单日");
		// 第一个账单日
		Cell firstBillHeader = headerRow.createCell(15);
		firstBillHeader.setCellValue("第一个账单日");
		// 划扣行别
		Cell openBankHeader = headerRow.createCell(16);
		openBankHeader.setCellValue("划扣行别");
		// 划扣开户行
		Cell branchHeader = headerRow.createCell(17);
		branchHeader.setCellValue("划扣开户行");
		// 划扣账号
		Cell cardNoHeader = headerRow.createCell(18);
		cardNoHeader.setCellValue("划扣账号");
		// 回款行别
		Cell backOpenBankHeader = headerRow.createCell(19);
		backOpenBankHeader.setCellValue("回款行别");
		// 回款开户行
		Cell backBranchHeader = headerRow.createCell(20);
		backBranchHeader.setCellValue("回款开户行");
		// 回款账号
		Cell backCardNoHeader = headerRow.createCell(21);
		backCardNoHeader.setCellValue("回款账号");
		// 账号类型
		Cell accountCardOrBookletHeader = headerRow.createCell(22);
		accountCardOrBookletHeader.setCellValue("账号类型");
		// 所在城市
		Cell accountAddrcityHeader = headerRow.createCell(23);
		accountAddrcityHeader.setCellValue("所在城市");
		// 回款日期
		Cell backHeader = headerRow.createCell(24);
		backHeader.setCellValue("回款日期");
		// 回款金额
		Cell backMoneyHeader = headerRow.createCell(25);
		backMoneyHeader.setCellValue("回款金额");
		// 状态
		Cell statusHeader = headerRow.createCell(26); // 出借中。。。。
		statusHeader.setCellValue("状态");
		// 完结状态
		Cell dictApplyEndStateHeader = headerRow.createCell(27);
		dictApplyEndStateHeader.setCellValue("完结状态");
		// 合同编号
		Cell applyContractNoHeader = headerRow.createCell(28);
		applyContractNoHeader.setCellValue("合同编号");
		// 信和宝类型
		Cell xinhebaoTypeHeader = headerRow.createCell(29);
		xinhebaoTypeHeader.setCellValue("信和宝类型");
		// 年化收益率
		Cell yearRateHeader = headerRow.createCell(30);
		yearRateHeader.setCellValue("年化收益率");
		// 理财经理
		Cell managerCodeHeader = headerRow.createCell(31);
		managerCodeHeader.setCellValue("理财经理");
		// 账单收取方式
		Cell loanBillRecvHeader = headerRow.createCell(32);
		loanBillRecvHeader.setCellValue("账单收取方式");
		// 回款状态
		Cell backMoeyTypeHeader = headerRow.createCell(33);
		backMoeyTypeHeader.setCellValue("回款状态");
		//在职状态
		Cell workingStateHeader = headerRow.createCell(34);
		workingStateHeader.setCellValue("在职状态");
		//自转审核日期
		Cell zzApproveDateHeader = headerRow.createCell(35);
		zzApproveDateHeader.setCellValue("自转审批日期");
	}

}
