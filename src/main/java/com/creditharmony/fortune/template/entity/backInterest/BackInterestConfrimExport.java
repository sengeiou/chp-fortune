package com.creditharmony.fortune.template.entity.backInterest;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.creditharmony.core.fortune.type.BackInterestPlat;
import com.creditharmony.core.fortune.type.BacksmsState;
import com.creditharmony.core.fortune.type.OpenBank;
import com.creditharmony.fortune.deduct.common.DeductUtils;

/**
 * 回息确认导出
 * @Class Name backInterestConfrimExport 
 * @author 李志伟
 * @Create In 2016年4月18日
 */
public class BackInterestConfrimExport {
	
	/**
	 * 回息确认列表导出
	 * 2016年4月18日
	 * by 李志伟
	 * @param resultSet
	 * @param dataSheet
	 * @throws SQLException
	 */
	public static void assembleExcelCell(ResultSet resultSet, Sheet dataSheet)
			throws SQLException {
		int row = 1;
		String lendCode;
		String customerName;
		String productName;
		String accountBank;
		String platFormId;
		String backMoneyDay;
		String backRealMoney;
		String backMoneyStatus;

		Row dataRow;
		
		Cell lendCodeCell;
		Cell customerNameCell;
		Cell productNameCell;
		Cell accountBankCell;
		Cell platFormIdCell;
		Cell backMoneyDayCell;
		Cell backMoneyActualDayCell;
		Cell backRealMoneyCell;
		Cell backMoneyStatusCell;
		
		while (resultSet.next()) {
			
			customerName = resultSet.getString("customer_name");
			// 屏蔽客户姓名/手机号/身份证号
			customerName = "***";
			lendCode = resultSet.getString("lend_code");
			productName = resultSet.getString("product_name");
			accountBank = resultSet.getString("account_bank");
			platFormId = resultSet.getString("platform_id");
			backMoneyDay = resultSet.getString("back_money_day");
			backRealMoney = resultSet.getString("back_real_money");
			backMoneyStatus = resultSet.getString("back_money_status");
			backRealMoney = resultSet.getString("back_real_money");
			
			dataRow = dataSheet.createRow(row);
			
			lendCodeCell = dataRow.createCell(0);
			lendCodeCell.setCellValue(DeductUtils.isNull(lendCode));
			
			customerNameCell = dataRow.createCell(1);
			customerNameCell.setCellValue(DeductUtils.isNull(customerName));
			
			productNameCell = dataRow.createCell(2);
			productNameCell.setCellValue(DeductUtils.isNull(productName));
			
			accountBankCell = dataRow.createCell(3);
			if(accountBank != null && !("").equals(accountBank)){
				accountBankCell.setCellValue(OpenBank.getOpenBank(accountBank));
			}else{
				accountBankCell.setCellValue("");
			}
			
			platFormIdCell = dataRow.createCell(4);
			if(platFormId != null && !("").equals(platFormId)){
				platFormIdCell.setCellValue(BackInterestPlat.getBackInterestPlat(platFormId));
			}else{
				platFormIdCell.setCellValue("");
			}
			
			backMoneyDayCell = dataRow.createCell(5);
			backMoneyDayCell.setCellValue(DeductUtils.isNull(backMoneyDay));
			
			backMoneyActualDayCell = dataRow.createCell(6);
			backMoneyActualDayCell.setCellValue(DeductUtils.isNull(backMoneyDay));

			backRealMoneyCell = dataRow.createCell(7);
			backRealMoneyCell.setCellValue(DeductUtils.isNull(backRealMoney));
			
			backMoneyStatusCell = dataRow.createCell(8);
			if(backMoneyStatus != null && !("").equals(backMoneyStatus)){
				backMoneyStatusCell.setCellValue(BacksmsState.getBacksmsState(backMoneyStatus));
			}else{
				backMoneyStatusCell.setCellValue("");
			}
			
			row = row + 1;
		}
	}
}