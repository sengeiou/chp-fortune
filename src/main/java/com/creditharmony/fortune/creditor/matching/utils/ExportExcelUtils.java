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
import com.creditharmony.core.fortune.type.BillState;
import com.creditharmony.core.fortune.type.DeductPlat;
import com.creditharmony.core.fortune.type.MatchingFlag;
import com.creditharmony.core.fortune.type.MatchingStatus;
import com.creditharmony.core.fortune.type.OpenBank;
import com.creditharmony.core.fortune.type.PayMent;
import com.creditharmony.core.mybatis.sql.MyBatisSql;
import com.creditharmony.core.mybatis.util.MyBatisSqlUtil;
import com.creditharmony.fortune.back.interest.util.ExportBackInterestHelper;
import com.creditharmony.fortune.creditor.matching.entity.ext.MatchingCreditorEx;

/**
 * 导表工具类
 * by高士芳
 */
public class ExportExcelUtils {

	// 日志类
	private static Logger logger = LoggerFactory.getLogger(ExportBackInterestHelper.class);
	
	/**
	 * 导出Excel
	 */
	public static void exportData(MatchingCreditorEx search,HttpServletResponse response,String namespace,String fileName){
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
		String orgName;// 营业部
		String province;// 省份
		String city;// 城市
		String customerName;// 客户姓名
		String productName;// 出借产品
		String startApplyLendDay;// 初始出借日期
		String startApplyLendMoney;// 初始出借金额
		String matchingInterestStart;// 本期出借日期
		String matchingBorrowQuota;// 本期推荐金额
		String matchingFirstdayFlag;// 账单类型
		String applyExpireDay;// 到期日期
		String dictApplyDeductType;//划扣平台
		String accountBank;//出借银行
		String applyDeductDay;//计划划扣日期
		String matchingUserName;// 匹配人
		String applyPay;//付款方式
		String matchingStatus;// 债权状态
		String matchingFlag; // 匹配标识
		Row dataRow;
		Cell lendCodeCell;
		Cell orgNameCell;
		Cell provinceCell;
		Cell cityCell;
		Cell customerNameCell;
		Cell productNameCell;
		Cell startApplyLendDayCell;
		Cell startApplyLendMoneyCell;
		Cell matchingInterestStartCell;
		Cell matchingBorrowQuotaCell;
		Cell matchingFirstdayFlagCell;
		Cell applyExpireDayCell;	
		Cell dictApplyDeductTypeCell;
		Cell accountBankCell;
		Cell applyDeductDayCell;
		Cell matchingUserNameCell;
		Cell applyPayCell;
		Cell matchingStatusCell;
		Cell matchingFlagCell;
		//初始化债权匹配状态
		MatchingStatus.initMatchingStatus();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		while(resultSet.next()){
			dataRow = dataSheet.createRow(row);
				// 出借编号
			    lendCode = resultSet.getString("lendCode");
			    lendCodeCell = dataRow.createCell(0);
			    lendCodeCell.setCellValue(lendCode);
				// 营业部
			    orgName = resultSet.getString("orgName");
				orgNameCell = dataRow.createCell(1);
				orgNameCell.setCellValue(orgName);
				// 省份
				province = resultSet.getString("province");
				provinceCell = dataRow.createCell(2);
				provinceCell.setCellValue(province);
				// 城市
				city = resultSet.getString("city");
				cityCell = dataRow.createCell(3);
				cityCell.setCellValue(city);
				// 客户姓名
				customerName = resultSet.getString("customerName");
				// 屏蔽客户姓名/手机号/身份证号
				customerName = "***";
				customerNameCell = dataRow.createCell(4);
				customerNameCell.setCellValue(customerName);
				// 出借产品
				productName = resultSet.getString("productName");
				productNameCell = dataRow.createCell(5);
				productNameCell.setCellValue(productName);
				// 初始出借日期
				startApplyLendDay = resultSet.getString("startApplyLendDay");
				startApplyLendDayCell = dataRow.createCell(6);
				startApplyLendDayCell.setCellValue(startApplyLendDay);
				// 初始出借金额
				startApplyLendMoney = resultSet.getString("startApplyLendMoney");
				startApplyLendMoneyCell = dataRow.createCell(7);
				startApplyLendMoneyCell.setCellValue(startApplyLendMoney);
				// 本期出借日期
				matchingInterestStart = resultSet.getString("matchingInterestStart");
			    matchingInterestStart = new SimpleDateFormat("yyyy-MM-dd").format(sdf.parse(matchingInterestStart)); 
				matchingInterestStartCell = dataRow.createCell(8);
				matchingInterestStartCell.setCellValue(matchingInterestStart);
				// 本期推荐金额
				matchingBorrowQuota = resultSet.getString("matchingBorrowQuota");
				matchingBorrowQuotaCell = dataRow.createCell(9);
				matchingBorrowQuotaCell.setCellValue(matchingBorrowQuota);
				// 账单类型
				if(resultSet.getString("matchingFirstdayFlag") != null){
					matchingFirstdayFlag = resultSet.getString("matchingFirstdayFlag");
					matchingFirstdayFlag = BillState.billStateMap.get(matchingFirstdayFlag);
				}else{
					matchingFirstdayFlag = "";
				}
				matchingFirstdayFlagCell = dataRow.createCell(10);
				matchingFirstdayFlagCell.setCellValue(matchingFirstdayFlag);
				// 到期日期
				applyExpireDay = resultSet.getString("applyExpireDay");
				applyExpireDayCell = dataRow.createCell(11);
				applyExpireDayCell.setCellValue(applyExpireDay);
				// 划扣平台
				if(resultSet.getString("dictApplyDeductType") != null){
					dictApplyDeductType = resultSet.getString("dictApplyDeductType");
					dictApplyDeductType = DeductPlat.getDeductPlat(dictApplyDeductType);
				}else{
					dictApplyDeductType = "";
				}
				dictApplyDeductTypeCell = dataRow.createCell(12);
				dictApplyDeductTypeCell.setCellValue(dictApplyDeductType);
				// 出借银行
				if(resultSet.getString("accountBank") != null){
					accountBank = resultSet.getString("accountBank");
					accountBank = OpenBank.getOpenBank(accountBank);
				}else{
					accountBank = "";
				}
				accountBankCell = dataRow.createCell(13);
				accountBankCell.setCellValue(accountBank);
				// 计划划扣日期
				applyDeductDay = resultSet.getString("applyDeductDay");
				applyDeductDayCell = dataRow.createCell(14);
				applyDeductDayCell.setCellValue(applyDeductDay);
				// 匹配人
				matchingUserName = resultSet.getString("matchingUserName");
				matchingUserNameCell = dataRow.createCell(15);
				matchingUserNameCell.setCellValue(matchingUserName);
				// 匹配标识
				if(resultSet.getString("matchingFlag") != null){
					matchingFlag = resultSet.getString("matchingFlag");
					matchingFlag = MatchingFlag.matchingFlagMap.get(matchingFlag);
				}else{
					matchingFlag = "";
				}
				matchingFlagCell = dataRow.createCell(16);
				matchingFlagCell.setCellValue(matchingFlag);
				// 付款方式
				if(resultSet.getString("applyPay") != null){
					applyPay = resultSet.getString("applyPay");
					applyPay = PayMent.payMentMap.get(applyPay);
				}else{
					applyPay = "";
				}
				applyPayCell = dataRow.createCell(17);
				applyPayCell.setCellValue(applyPay);
				// 债权状态
				if(resultSet.getString("matchingStatus") != null){
					matchingStatus = resultSet.getString("matchingStatus");
					matchingStatus = MatchingStatus.matchingStatusMap.get(matchingStatus);
				}else{
					matchingStatus = "";
				}
				matchingStatusCell = dataRow.createCell(18);
				matchingStatusCell.setCellValue(matchingStatus);
			
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
		Cell orgNameHeader = headerRow.createCell(1);
		orgNameHeader.setCellValue("营业部");
		Cell cityHeader = headerRow.createCell(2);
		cityHeader.setCellValue("省份");
		Cell provinceHeader = headerRow.createCell(3);
		provinceHeader.setCellValue("城市");
		Cell customerNameHeader = headerRow.createCell(4);
		customerNameHeader.setCellValue("客户姓名");
		Cell productNameHeader = headerRow.createCell(5);
		productNameHeader.setCellValue("出借产品");
		Cell startApplyLendDayHeader = headerRow.createCell(6);
		startApplyLendDayHeader.setCellValue("初始出借日期");
		Cell startApplyLendMoneyHeader = headerRow.createCell(7);
		startApplyLendMoneyHeader.setCellValue("初始出借金额");
		Cell matchingInterestStartHeader = headerRow.createCell(8);
		matchingInterestStartHeader.setCellValue("本期出借日期");
		Cell matchingBorrowQuotaHeader = headerRow.createCell(9);
		matchingBorrowQuotaHeader.setCellValue("本期推荐金额");
		Cell matchingFirstdayFlagHeader = headerRow.createCell(10);
		matchingFirstdayFlagHeader.setCellValue("账单类型");
		Cell applyExpireDayHeader = headerRow.createCell(11);
		applyExpireDayHeader.setCellValue("到期日期");
		Cell dictApplyDeductTypeHeader = headerRow.createCell(12);
		dictApplyDeductTypeHeader.setCellValue("划扣平台");
		Cell accountBankHeader = headerRow.createCell(13);
		accountBankHeader.setCellValue("出借银行");
		Cell applyDeductDayHeader = headerRow.createCell(14);
		applyDeductDayHeader.setCellValue("计划划扣日期");
		Cell matchingUserNameHeader = headerRow.createCell(15);
		matchingUserNameHeader.setCellValue("匹配人");
		Cell matchingFlagNameHeader = headerRow.createCell(16);
		matchingFlagNameHeader.setCellValue("匹配标识");
		Cell applyPayHeader = headerRow.createCell(17);
		applyPayHeader.setCellValue("付款方式");
		Cell matchingStatusHeader = headerRow.createCell(18);
		matchingStatusHeader.setCellValue("状态");

		}
		

}
