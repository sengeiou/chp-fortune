package com.creditharmony.fortune.template.entity.backInterest;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.creditharmony.core.fortune.type.OpenBank;
import com.creditharmony.fortune.deduct.common.DeductUtils;

/**
 * 已回息列表导出
 * @Class Name FinishBackInterestExport 
 * @author 李志伟
 * @Create In 2016年4月18日
 */
public class FinishBackInterestExport {

	/**
	 * 已回息导出
	 * 2016年4月16日
	 * by 李志伟
	 * @param resultSet
	 * @param dataSheet
	 * @throws SQLException
	 */
	public static void assembleExcelCell(ResultSet resultSet, Sheet dataSheet)
			throws SQLException {
		int row = 1;
		String lendCode;
		String accountNo;
		String accountName;
		String backRealMoney;
		String accountBank;
		String accountBranch;
		String province;
		String city;
		String trusteeshipNo;
		String backMoneyActualDay;
		String currentBillday;
		String isRetrunInterest;
		
		Row dataRow;
		
		Cell lendCodeCell;
		Cell accountNoCell;
		Cell accountNameCell;
		Cell backRealMoneyCell;
		Cell accountBankCell;
		Cell accountBranchCell;
		Cell provinceCell;
		Cell cityCell;
		Cell outNameCell;
		Cell outBankCell;
		Cell outBankNoCell;
		Cell outDateCell;
		Cell trusteeshipNoCell;
		Cell backMoneyActualDayCell;
		Cell currentBilldayCell;
		Cell isRetrunInterestCell;
		
		while (resultSet.next()) {
			
			lendCode = resultSet.getString("lend_code");
			accountNo = resultSet.getString("account_no");
			accountName = resultSet.getString("account_name");
			backRealMoney = resultSet.getString("back_real_money_round");
			accountBank = resultSet.getString("account_bank");
			accountBranch = resultSet.getString("account_branch");
			province = resultSet.getString("province");
			city = resultSet.getString("city");
			trusteeshipNo = resultSet.getString("trusteeship_no");
			backMoneyActualDay = resultSet.getString("back_money_day");
			currentBillday = resultSet.getString("current_billday");
			isRetrunInterest = resultSet.getString("is_retrun_interest");
			
			
			dataRow = dataSheet.createRow(row);
			
			lendCodeCell = dataRow.createCell(0);
			lendCodeCell.setCellValue(DeductUtils.isNull(lendCode));

			
			accountNoCell = dataRow.createCell(1);
			accountNoCell.setCellValue(DeductUtils.isNull(accountNo));

			accountNameCell = dataRow.createCell(2);
			accountNameCell.setCellValue(DeductUtils.isNull(accountName));
			
			backRealMoneyCell = dataRow.createCell(3);
			backRealMoneyCell.setCellValue(DeductUtils.isNull(backRealMoney));
			
			accountBankCell = dataRow.createCell(4);
			if(accountBank != null && !("").equals(accountBank)){
				accountBankCell.setCellValue(OpenBank.getOpenBank(accountBank));
			}else{
				accountBankCell.setCellValue("");
			}
			
			accountBranchCell = dataRow.createCell(5);
			accountBranchCell.setCellValue(DeductUtils.isNull(accountBranch));
			
			provinceCell = dataRow.createCell(6);
			provinceCell.setCellValue(DeductUtils.isNull(province));
			
			cityCell = dataRow.createCell(7);
			cityCell.setCellValue(DeductUtils.isNull(city));
			
			outNameCell = dataRow.createCell(8);
			outNameCell.setCellValue("");
			
			outBankCell = dataRow.createCell(9);
			outBankCell.setCellValue("");
			
			outBankNoCell = dataRow.createCell(10);
			outBankNoCell.setCellValue("");
			
			outDateCell = dataRow.createCell(11);
			outDateCell.setCellValue("");
			
			trusteeshipNoCell = dataRow.createCell(12);
			trusteeshipNoCell.setCellValue(DeductUtils.isNull(trusteeshipNo));
			
			backMoneyActualDayCell = dataRow.createCell(13);
			backMoneyActualDayCell.setCellValue(DeductUtils.isNull(backMoneyActualDay));
			
			currentBilldayCell = dataRow.createCell(14);
			currentBilldayCell.setCellValue(DeductUtils.isNull(currentBillday));
			
			isRetrunInterestCell = dataRow.createCell(15);
			isRetrunInterestCell.setCellValue(DeductUtils.isNull(isRetrunInterest));
			
			row = row + 1;
		}
	}
}