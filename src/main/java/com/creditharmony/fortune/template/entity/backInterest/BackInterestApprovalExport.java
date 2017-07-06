package com.creditharmony.fortune.template.entity.backInterest;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import com.creditharmony.core.fortune.type.ContractVesion;
import com.creditharmony.core.fortune.type.OpenBank;
import com.creditharmony.core.fortune.type.PayMent;
import com.creditharmony.fortune.deduct.common.DeductUtils;

/**
 * 待回息审批导出
 * @Class Name BackInterestApprovalExport 
 * @author 李志伟
 * @Create In 2016年4月16日
 */
public class BackInterestApprovalExport {
	
	/**
	 * 审批列表导出
	 * 2016年4月15日
	 * by 李志伟
	 * @param resultSet
	 * @param dataSheet
	 * @throws SQLException
	 */
	public static void assembleExcelCell(ResultSet resultSet, Sheet dataSheet)
			throws SQLException {
		int row = 1;
		String customerName;
		String lendCode;
		String applyLendDay;
		String applyLendMoney;
		String productName;
		String applyExpireDay;
		String managerName;
		String orgName;
		String accountBank;
		String accountBranch;
		String accountNo;
		String backRealMoney;
		String applyPay;
		String applyAgreementEdition;
		
		Row dataRow;
		
		Cell customerNameCell;
		Cell lendCodeCell;
		Cell applyLendDayCell;
		Cell applyLendMoneyCell;
		Cell productNameCell;
		Cell applyHostedStatusCell;
		Cell applyExpireDayCell;
		Cell managerNameCell;
		Cell orgNameCell;
		Cell accountBankCell;
		Cell accountBranchCell;
		Cell accountNoCell;
		Cell backRealMoneyCell;
		Cell applyAgreementEditionCell;
		while (resultSet.next()) {
			
			customerName = resultSet.getString("customer_name");
			// 屏蔽客户姓名/手机号/身份证号
			customerName = "***";
			lendCode = resultSet.getString("lend_code");
			accountNo = resultSet.getString("account_no");
			applyLendDay = resultSet.getString("apply_lend_day");
			applyLendMoney = resultSet.getString("apply_lend_money");
			productName = resultSet.getString("product_name");
			applyPay = resultSet.getString("apply_pay");
			applyExpireDay = resultSet.getString("apply_expire_day");
			managerName = resultSet.getString("manager_name");
			orgName = resultSet.getString("org_name");
			accountBank = resultSet.getString("account_bank");
			accountBranch = resultSet.getString("account_branch");
			accountNo = resultSet.getString("account_no");
			backRealMoney = resultSet.getString("back_real_money");
			applyAgreementEdition = resultSet.getString("apply_agreement_edition");
			
			dataRow = dataSheet.createRow(row);
			
			customerNameCell = dataRow.createCell(0);
			customerNameCell.setCellValue(DeductUtils.isNull(customerName));
			
			lendCodeCell = dataRow.createCell(1);
			lendCodeCell.setCellValue(DeductUtils.isNull(lendCode));
			
			applyLendDayCell = dataRow.createCell(2);
			applyLendDayCell.setCellValue(DeductUtils.isNull(applyLendDay));
			
			applyLendMoneyCell = dataRow.createCell(3);
			applyLendMoneyCell.setCellValue(DeductUtils.isNull(applyLendMoney));
			
			productNameCell = dataRow.createCell(4);
			productNameCell.setCellValue(DeductUtils.isNull(productName));
			
			applyHostedStatusCell = dataRow.createCell(5);
			if(applyPay != null && !("").equals(applyPay)){
				if(applyPay.equals(PayMent.ZJTG.value)){
					applyHostedStatusCell.setCellValue("TG");
				}else{
					applyHostedStatusCell.setCellValue("非TG");
				}
			}else{
				applyHostedStatusCell.setCellValue("");
			}
			
			applyExpireDayCell = dataRow.createCell(6);
			applyExpireDayCell.setCellValue(DeductUtils.isNull(applyExpireDay));
			
			managerNameCell = dataRow.createCell(7);
			managerNameCell.setCellValue(DeductUtils.isNull(managerName)); 
			
			orgNameCell = dataRow.createCell(8);
			orgNameCell.setCellValue(DeductUtils.isNull(orgName));
			
			accountBankCell = dataRow.createCell(9);
			if(accountBank != null && !("").equals(accountBank)){
				accountBankCell.setCellValue(OpenBank.getOpenBank(accountBank));
			}else{
				accountBankCell.setCellValue("");
			}
			
			accountBranchCell = dataRow.createCell(10);
			accountBranchCell.setCellValue(DeductUtils.isNull(accountBranch));

			accountNoCell = dataRow.createCell(11);
			accountNoCell.setCellValue(DeductUtils.isNull(accountNo));
			
			backRealMoneyCell = dataRow.createCell(12);
			backRealMoneyCell.setCellValue(DeductUtils.isNull(backRealMoney));
			
			applyAgreementEditionCell = dataRow.createCell(13);
			if(StringUtils.isNotBlank(applyAgreementEdition)){
				applyAgreementEditionCell.setCellValue(ContractVesion.getContractVesion(applyAgreementEdition));
			}
			
			row = row + 1;
		}
	}
	
	/**
	 * 设置标题
	 * 2016年4月16日
	 * by 李志伟
	 * @param dataSheet
	 * @param swb
	 * @param title
	 */
	public static void wrapperHeader(Sheet dataSheet,  SXSSFWorkbook swb, String title) {
		
		Row headerRow = dataSheet.createRow(0);
		String[] tit = title.split(",");
		CellStyle cellStyle = swb.createCellStyle();
		cellStyle.setFillForegroundColor(HSSFColor.CORNFLOWER_BLUE.index);
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// //居中显示
		
		for (int i = 0; i < tit.length; i++) {
			Cell header = headerRow.createCell(i);
			header.setCellValue(tit[i]);
			header.setCellStyle(cellStyle);
		}
	}
}
