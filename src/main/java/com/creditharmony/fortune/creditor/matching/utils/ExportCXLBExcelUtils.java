package com.creditharmony.fortune.creditor.matching.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Map;

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
import com.creditharmony.core.fortune.type.BillDay;
import com.creditharmony.core.fortune.type.BillState;
import com.creditharmony.core.fortune.type.ReplaceStatus;
import com.creditharmony.core.mybatis.sql.MyBatisSql;
import com.creditharmony.core.mybatis.util.MyBatisSqlUtil;
import com.creditharmony.fortune.back.interest.util.ExportBackInterestHelper;
import com.creditharmony.fortune.borrow.entity.ex.BorrowCanceEx;
import com.creditharmony.fortune.borrow.service.BorrowCancelManager;

/**
 * 债权撤销列表导出工具类
 * 2017年3月22日
 * By 常亚运
 *
 */
public class ExportCXLBExcelUtils {

	private static Logger logger = LoggerFactory.getLogger(ExportBackInterestHelper.class);
	
	
	/**
	 * 导出Excel
	 */
	public static void exportData(Map<String, Object> search,HttpServletResponse response,String namespace,String fileName,BorrowCancelManager bcm){
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
			assembleExcelCell(resultSet, dataSheet,bcm);
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
	public static void assembleExcelCell(ResultSet resultSet, Sheet dataSheet,BorrowCancelManager bcm) throws SQLException, ParseException{
		int row = 1;
		String matchingId; //匹配id
		String lendCode;// 出借编号
		String customerName;// 客户姓名
		String orgName;// 营业部
		String applyLendDay;// 计划出借日期
		String applyLendMoney;// 计划出借金额
		String matchingInterestStart;// 本期出借日期
		String currentCreditLinesMoney;// 本期待替换金额
		String applyExpireDay;// 出借到期日期
		String applyBillday;// 账单日
		String procuctName;// 出借产品
		String replaceDay;// 替换日期
		String matchingFirstdayFlag;// 账单类型
		String replaceStatus;// 替换状态
		
		Row dataRow;
		Cell matchingIdCell;
		Cell lendCodeCell;
		Cell customerNameCell;
		Cell orgNameCell;
		Cell applyLendDayCell;
		Cell applyLendMoneyCell;
		Cell matchingInterestStartCell;
		Cell currentCreditLinesMoneyCell;
		Cell applyExpireDayCell;
		Cell applyBilldayCell;
		Cell procuctNameCell;
		Cell replaceDayCell;
		Cell matchingFirstdayFlagCell;
		Cell replaceStatusCell;
		
		
		while(resultSet.next()){
			dataRow = dataSheet.createRow(row);
			
				//匹配id
				matchingId = resultSet.getString("matchingId");
				
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
				// 营业部
			    orgName = resultSet.getString("orgName");
			    orgNameCell = dataRow.createCell(2);
			    orgNameCell.setCellValue(orgName);
				// 计划出借日期
			    applyLendDay = resultSet.getString("applyLendDay");
			    applyLendDayCell = dataRow.createCell(3);
			    applyLendDayCell.setCellValue(applyLendDay);
				// 计划出借金额
			    applyLendMoney = resultSet.getString("applyLendMoney");
			    applyLendMoneyCell = dataRow.createCell(4);
			    applyLendMoneyCell.setCellValue(applyLendMoney);
			    // 本期出借日期
				matchingInterestStart = resultSet.getString("matchingInterestStart");
				matchingInterestStartCell = dataRow.createCell(5);
				matchingInterestStartCell.setCellValue(matchingInterestStart);
				// 本期待替换金额
				String lendCode_matchingId = lendCode+":"+matchingId; 
				BorrowCanceEx borrowCanceEx = bcm.getBorrowCanceEx(lendCode_matchingId); 
				currentCreditLinesMoneyCell = dataRow.createCell(6);
				currentCreditLinesMoneyCell.setCellValue(borrowCanceEx.getCurrentCreditLines()+"");

				// 出借到期日期
				applyExpireDay = resultSet.getString("applyExpireDay");
				applyExpireDayCell = dataRow.createCell(7);
				applyExpireDayCell.setCellValue(applyExpireDay);
				// 账单日
				if (resultSet.getString("applyBillday") != null) {
					applyBillday = resultSet.getString("applyBillday");
					applyBillday = BillDay.billDayMap.get(applyBillday);
					
				}else {
					applyBillday = "";
				}
				applyBilldayCell  = dataRow.createCell(8);
				applyBilldayCell.setCellValue(applyBillday);
				
				// 出借产品
				procuctName = resultSet.getString("procuctName");
				procuctNameCell = dataRow.createCell(9);
				procuctNameCell.setCellValue(procuctName);
				// 替换日期
				replaceDay = resultSet.getString("replaceDay");
				replaceDayCell = dataRow.createCell(10);
				replaceDayCell.setCellValue(replaceDay);
				
				// 账单类型
				if(resultSet.getString("matchingFirstdayFlag") != null){
					matchingFirstdayFlag = resultSet.getString("matchingFirstdayFlag");
					matchingFirstdayFlag = BillState.billStateMap.get(matchingFirstdayFlag);
				}else{
					matchingFirstdayFlag = "";
				}
				matchingFirstdayFlagCell = dataRow.createCell(11);
				matchingFirstdayFlagCell.setCellValue(matchingFirstdayFlag);
				
				// 替换状态
				if (resultSet.getString("toPageReplaceStatus") != null) {
					replaceStatus = resultSet.getString("toPageReplaceStatus");
					replaceStatus = ReplaceStatus.replaceStatusMap.get(replaceStatus);
				}else {
					replaceStatus = "";
				}
				replaceStatusCell = dataRow.createCell(12);
				replaceStatusCell.setCellValue(replaceStatus);
				
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
		Cell orgNameHeader = headerRow.createCell(2);
		orgNameHeader.setCellValue("营业部");
		Cell applyLendDayHeader = headerRow.createCell(3);
		applyLendDayHeader.setCellValue("计划出借日期");
		Cell applyLendMoneyHeader = headerRow.createCell(4);
		applyLendMoneyHeader.setCellValue("计划出借金额");
		Cell matchingInterestStartHeader = headerRow.createCell(5);
		matchingInterestStartHeader.setCellValue("本期出借日期");
		Cell currentCreditLinesMoneyHeader = headerRow.createCell(6);
		currentCreditLinesMoneyHeader.setCellValue("本期待替换金额");
		Cell applyExpireDayHeader = headerRow.createCell(7);
		applyExpireDayHeader.setCellValue("出借到期日期");
		Cell applyBilldayHeader = headerRow.createCell(8);
		applyBilldayHeader.setCellValue("账单日");
		Cell procuctNameHeader = headerRow.createCell(9);
		procuctNameHeader.setCellValue("出借产品");
		Cell replaceDayHeader = headerRow.createCell(10);
		replaceDayHeader.setCellValue("替换日期");
		Cell matchingFirstdayFlagHeader = headerRow.createCell(11);
		matchingFirstdayFlagHeader.setCellValue("账单类型");
		Cell replaceStatusHeader = headerRow.createCell(12);
		replaceStatusHeader.setCellValue("替换状态");
		
		}
}
