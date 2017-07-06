package com.creditharmony.fortune.creditor.matching.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.creditharmony.common.util.Encodes;
import com.creditharmony.common.util.SpringContextHolder;
import com.creditharmony.core.common.type.EmailState;
import com.creditharmony.core.fortune.type.BillState;
import com.creditharmony.core.fortune.type.FilecpState;
import com.creditharmony.core.fortune.type.FinishState;
import com.creditharmony.core.fortune.type.MatchingFlag;
import com.creditharmony.core.fortune.type.PayMent;
import com.creditharmony.core.fortune.type.TakenMode;
import com.creditharmony.core.mybatis.sql.MyBatisSql;
import com.creditharmony.core.mybatis.util.MyBatisSqlUtil;
import com.creditharmony.core.type.SettleStats;
import com.creditharmony.fortune.back.interest.util.ExportBackInterestHelper;
import com.creditharmony.fortune.creditor.matching.entity.ext.CreditorTradeEx;

/**
 * 导表工具类
 * by高士芳
 */
public class ExportExcelUtilsOfSend {

	// 日志类
	private static Logger logger = LoggerFactory.getLogger(ExportBackInterestHelper.class);
	
	/**
	 * 导出Excel
	 */
	public static void exportData(CreditorTradeEx search,HttpServletResponse response,String namespace,String fileName){
		SqlSessionFactory sqlSessionFactory = (SqlSessionFactory) SpringContextHolder.getBean("sqlSessionFactory");
		SqlSession sqlSession = sqlSessionFactory.openSession();
		Connection connection = sqlSession.getConnection();
		try {
			SXSSFWorkbook wb = new SXSSFWorkbook();
			Sheet dataSheet = wb.createSheet("ExportList");
			wrapperHeader(dataSheet);
			MyBatisSql batisSql = MyBatisSqlUtil
					.getMyBatisSql(namespace,search, sqlSessionFactory);
			PreparedStatement ps = connection.prepareStatement(batisSql.toString());
			ResultSet resultSet = ps.executeQuery();
			assembleExcelCell(resultSet, dataSheet);
			response.reset();
			response.setContentType("application/octet-stream; charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setHeader(
					"Content-Disposition",
					"attachment; filename="
							+ Encodes.urlEncode(fileName)
							+ ";filename*=UTF-8''"
							+ Encodes.urlEncode(fileName));
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
	
	/**
	 * 获取导出数据
	 * @throws ParseException 
	 */
	public static void assembleExcelCell(ResultSet resultSet, Sheet dataSheet) throws SQLException, ParseException{
		int row = 1;
		String lendCode;// 出借编号
		String customerName;// 客户姓名
		String customerCode;// 客户编号
		String addrCity;// 所在城市
		String org;// 营业部
		String applyLendDay;// 本期出借日期
		String startApplyLendDay;// 初始出借日期
		String startApplyLendMoney;// 初始出借金额
		String productName;// 出借产品
		String applyPay;// 付款方式
		String applyEndStatus;// 出借完结状态
		String matchingPayStatus;// 结算状态
		String matchingFirstdayFlag;//账单类型
		String matchingMatchMoney;//已推荐金额
		String matchingFileSendStatus;//账单发送状态
		String loanBillRecv;// 账单收取方式
		String matchingFileStatus;//制作状态
		String customerEmail;// 邮箱地址
		String matchingFlag; // 匹配标识
		Row dataRow;
		Cell lendCodeCell;
		Cell customerNameCell;
		Cell customerCodeCell;
		Cell addrCityCell;
		Cell orgCell;
		Cell applyLendDayCell;
		Cell startApplyLendDayCell;
		Cell startApplyLendMoneyCell;
		Cell productNameCell;
		Cell applyPayCell;
		Cell applyEndStatusCell;
		Cell matchingPayStatusCell;
		Cell matchingFirstdayFlagCell;
		Cell matchingMatchMoneyCell;
		Cell matchingFileSendStatusCell;
		Cell loanBillRecvCell;
		Cell matchingFileStatusCell;
		Cell customerEmailCell;
		Cell matchingFlagCell;
		//初始状态
		FinishState.initFinishState();
		SettleStats.initSettleStats();
		EmailState.initEmailState();
		FilecpState.initFilecpState();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
		while(resultSet.next()){
			dataRow = dataSheet.createRow(row);
				// 出借编号
			    lendCode = resultSet.getString("lendCode");
			    lendCodeCell = dataRow.createCell(0);
			    lendCodeCell.setCellValue(lendCode);
				// 客户姓名
			    customerName = resultSet.getString("customerName");
			    // 屏蔽客户姓名/手机号/身份证号
			    customerName = "***";
			    customerNameCell = dataRow.createCell(1);
			    customerNameCell.setCellValue(customerName);
				// 客户编号
			    customerCode = resultSet.getString("customerCode");
			    customerCodeCell = dataRow.createCell(2);
			    customerCodeCell.setCellValue(customerCode);
				// 所在城市
			    addrCity = resultSet.getString("addrCity");
			    addrCityCell = dataRow.createCell(3);
			    addrCityCell.setCellValue(addrCity);
				// 营业部
			    org = resultSet.getString("org");
			    orgCell = dataRow.createCell(4);
			    orgCell.setCellValue(org);
				// 本期出借日期
			    applyLendDay = resultSet.getString("applyLendDay"); 
			    applyLendDay = new SimpleDateFormat("yyyy-MM-dd").format(sdf.parse(applyLendDay)); 
			    applyLendDayCell = dataRow.createCell(5);
			    applyLendDayCell.setCellValue(applyLendDay);
				// 初始出借日期
			    startApplyLendDay = resultSet.getString("startApplyLendDay");
			    startApplyLendDayCell = dataRow.createCell(6);
			    startApplyLendDayCell.setCellValue(startApplyLendDay);
				// 初始出借金额
				startApplyLendMoney = resultSet.getString("startApplyLendMoney");
				startApplyLendMoneyCell = dataRow.createCell(7);
				startApplyLendMoneyCell.setCellValue(startApplyLendMoney);
				// 出借产品
				productName = resultSet.getString("productName");
				productNameCell = dataRow.createCell(8);
				productNameCell.setCellValue(productName);
				// 付款方式
				if(resultSet.getString("applyPay") != null){
					applyPay = resultSet.getString("applyPay");
					applyPay = PayMent.payMentMap.get(applyPay);
				}else{
					applyPay = "";
				}
				applyPayCell = dataRow.createCell(9);
				applyPayCell.setCellValue(applyPay);
				
				// 匹配标识
				if(resultSet.getString("matchingFlag") != null){
					matchingFlag = resultSet.getString("matchingFlag");
					matchingFlag = MatchingFlag.matchingFlagMap.get(matchingFlag);
				}else{
					matchingFlag = "";
				}
				matchingFlagCell = dataRow.createCell(10);
				matchingFlagCell.setCellValue(matchingFlag);
				
				
				// 出借完结状态
				if(resultSet.getString("applyEndStatus")!= null){
					applyEndStatus = resultSet.getString("applyEndStatus");
					applyEndStatus = FinishState.getFinishState(applyEndStatus);
				}else{
					applyEndStatus = "";
				}
				applyEndStatusCell = dataRow.createCell(11);
				applyEndStatusCell.setCellValue(applyEndStatus);
				
			
				// 结算状态
				if(resultSet.getString("matchingPayStatus") != null){
					matchingPayStatus = resultSet.getString("matchingPayStatus");
					matchingPayStatus = SettleStats.getSettleStats(matchingPayStatus);
				}else{
					matchingPayStatus = "";
				}
				matchingPayStatusCell = dataRow.createCell(12);
				matchingPayStatusCell.setCellValue(matchingPayStatus);
				// 账单类型
				if(resultSet.getString("matchingFirstdayFlag") != null){
					matchingFirstdayFlag = resultSet.getString("matchingFirstdayFlag");
					matchingFirstdayFlag = BillState.getBillState(matchingFirstdayFlag);
				}else{
					matchingFirstdayFlag = "";
				}
				matchingFirstdayFlagCell = dataRow.createCell(13);
				matchingFirstdayFlagCell.setCellValue(matchingFirstdayFlag);
				// 已推荐金额
				matchingMatchMoney = resultSet.getString("matchingMatchMoney");
				matchingMatchMoneyCell = dataRow.createCell(14);
				matchingMatchMoneyCell.setCellValue(matchingMatchMoney);
				// 账单发送状态
				if(resultSet.getString("matchingFileSendStatus") != null){
					matchingFileSendStatus = resultSet.getString("matchingFileSendStatus");
					matchingFileSendStatus = EmailState.getEmailState(matchingFileSendStatus);
				}else{
					matchingFileSendStatus = "";
				}
				matchingFileSendStatusCell = dataRow.createCell(15);
				matchingFileSendStatusCell.setCellValue(matchingFileSendStatus);
				// 账单收取方式
				if(resultSet.getString("loanBillRecv") != null){
					loanBillRecv = resultSet.getString("loanBillRecv");
					loanBillRecv = TakenMode.getTakenMode(loanBillRecv);
				}else{
					loanBillRecv = "";
				}
				loanBillRecvCell = dataRow.createCell(16);
				loanBillRecvCell.setCellValue(loanBillRecv);
				// 制作状态
				if(resultSet.getString("matchingFileStatus") != null){
					matchingFileStatus = resultSet.getString("matchingFileStatus");
					matchingFileStatus = FilecpState.getFilecpState(matchingFileStatus);
				}else{
					matchingFileStatus = "";
				}
				matchingFileStatusCell = dataRow.createCell(17);
				matchingFileStatusCell.setCellValue(matchingFileStatus);
				// 邮箱地址
				customerEmail = resultSet.getString("customerEmail");
				customerEmailCell = dataRow.createCell(18);
				customerEmailCell.setCellValue(customerEmail);
				
				
			row = row + 1;
		}
	}
	
	/**
	 * 设置表头
	 */
	public static void wrapperHeader(Sheet dataSheet){
		Row headerRow = dataSheet.createRow(0);

		Cell lendCodeHeader = headerRow.createCell(0);
		lendCodeHeader.setCellValue("出借编号");
		Cell customerNameHeader = headerRow.createCell(1);
		customerNameHeader.setCellValue("客户姓名");
		Cell customerCodeHeader = headerRow.createCell(2);
		customerCodeHeader.setCellValue("客户编号");
		Cell addrCityHeader = headerRow.createCell(3);
		addrCityHeader.setCellValue("所在城市");
		Cell orgHeader = headerRow.createCell(4);
		orgHeader.setCellValue("营业部");
		Cell applyLendDayHeader = headerRow.createCell(5);
		applyLendDayHeader.setCellValue("本期出借日期");
		Cell startApplyLendDayHeader = headerRow.createCell(6);
		startApplyLendDayHeader.setCellValue("初始出借日期");
		Cell startApplyLendMoneyHeader = headerRow.createCell(7);
		startApplyLendMoneyHeader.setCellValue("初始出借金额");
		Cell productNameHeader = headerRow.createCell(8);
		productNameHeader.setCellValue("出借产品");
		Cell applyPayHeader = headerRow.createCell(9);
		applyPayHeader.setCellValue("付款方式");
		Cell matchingFlagNameHeader = headerRow.createCell(10);
		matchingFlagNameHeader.setCellValue("匹配标识");
		Cell applyEndStatusHeader = headerRow.createCell(11);
		applyEndStatusHeader.setCellValue("出借完结状态");
		Cell matchingPayStatusHeader = headerRow.createCell(12);
		matchingPayStatusHeader.setCellValue("结算状态");
		Cell matchingFirstdayFlagHeader = headerRow.createCell(13);
		matchingFirstdayFlagHeader.setCellValue("账单类型");
		Cell matchingMatchMoneyHeader = headerRow.createCell(14);
		matchingMatchMoneyHeader.setCellValue("已推荐金额");
		Cell matchingFileSendStatusHeader = headerRow.createCell(15);
		matchingFileSendStatusHeader.setCellValue("账单发送状态");
		Cell loanBillRecvHeader = headerRow.createCell(16);
		loanBillRecvHeader.setCellValue("账单收取方式");
		Cell matchingFileStatusHeader = headerRow.createCell(17);
		matchingFileStatusHeader.setCellValue("制作状态");
		Cell customerEmailHeader = headerRow.createCell(18);
		customerEmailHeader.setCellValue("邮箱地址");
		}
		

}
