package com.creditharmony.fortune.template.entity.backInterest;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.creditharmony.core.deduct.type.CardOrBookType;
import com.creditharmony.core.fortune.type.OpenBank;
import com.creditharmony.core.fortune.type.PayMent;
import com.creditharmony.fortune.back.interest.util.StaticMethodUtil;
import com.creditharmony.fortune.deduct.common.DeductUtils;

public class BackInterestApplyExport {

	/**
	 * 申请列表和申请确认列表导出 
	 * 2016年4月15日
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
		String accountCardOrBooklet;
		String province;
		String city;
		String productName;
		String backiId;
		String currentBillday;
		String applyLendDay;
		String applyLendMoney;
		String applyPay;
		String orgName;
		Row dataRow;
		Cell lendCodeCell;
		Cell accountNoCell;
		Cell accountNameCell;
		Cell backRealMoneyCell;
		Cell bankCodeCell;
		Cell accountBankCell;
		Cell accountBranchCell;
		Cell accountCardOrBookletCell;
		Cell provinceCell;
		Cell cityCell;
		Cell productNameCell;
		Cell backiIdCell;
		Cell currentBilldayCell;
		Cell applyLendDayCell;
		Cell applyLendMoneyCell;
		Cell applyPayCell;
		Cell orgNameCell;
		while (resultSet.next()) {
			
			lendCode = resultSet.getString("lend_code");
			accountNo = resultSet.getString("account_no");
			accountName = resultSet.getString("account_name");
			
			backRealMoney = resultSet.getString("back_real_money");
			accountBank = resultSet.getString("account_bank");
			accountBranch = resultSet.getString("account_branch");
			accountCardOrBooklet = resultSet
					.getString("account_card_or_booklet");
			province = resultSet.getString("province");
			city = resultSet.getString("city");
			productName = resultSet.getString("product_name");
			backiId = resultSet.getString("backi_id");
			currentBillday = resultSet.getString("current_billday");
			applyLendDay = resultSet.getString("apply_lend_day");
			applyLendMoney = resultSet.getString("apply_lend_money");
			applyPay = resultSet.getString("apply_pay");
			orgName = resultSet.getString("org_name");
			
			dataRow = dataSheet.createRow(row);
			
			lendCodeCell = dataRow.createCell(0);
			lendCodeCell.setCellValue(DeductUtils.isNull(lendCode));
			
			accountNoCell = dataRow.createCell(1);
			accountNoCell.setCellValue(DeductUtils.isNull(accountNo));
			
			accountNameCell = dataRow.createCell(2);
			accountNameCell.setCellValue(DeductUtils.isNull(accountName));
			
			backRealMoneyCell = dataRow.createCell(3);
			backRealMoneyCell.setCellValue(DeductUtils.isNull(backRealMoney));
			
			bankCodeCell = dataRow.createCell(4);
			bankCodeCell.setCellValue(DeductUtils.isNull(accountBank));
			
			accountBankCell = dataRow.createCell(5);
			if(accountBank != null && !("").equals(accountBank)){
				accountBankCell.setCellValue(OpenBank.getOpenBank(accountBank));
			}else{
				accountBankCell.setCellValue("");
			}
			
			accountBranchCell = dataRow.createCell(6);
			accountBranchCell.setCellValue(DeductUtils.isNull(accountBranch));
			
			accountCardOrBookletCell = dataRow.createCell(7);
			if(accountCardOrBooklet != null && !("").equals(accountCardOrBooklet)){
				accountCardOrBookletCell.setCellValue(CardOrBookType.parseByCode(accountCardOrBooklet).getName());
			}else{
				accountCardOrBookletCell.setCellValue("");
			}
			
			provinceCell = dataRow.createCell(8);
			if(null != province && !("").equals(province)){
				provinceCell.setCellValue(DeductUtils.isNull(StaticMethodUtil.spiltProvince(province)));
			}else{
				provinceCell.setCellValue("");
			}
			
			cityCell = dataRow.createCell(9);
			if(null != city && !("").equals(city)){
				cityCell.setCellValue(DeductUtils.isNull(StaticMethodUtil.spiltProvince(city)));
			}else{
				cityCell.setCellValue("");
			}
			
			productNameCell = dataRow.createCell(10);
			productNameCell.setCellValue(productName+"回息");
			
			backiIdCell = dataRow.createCell(11);
			backiIdCell.setCellValue(DeductUtils.isNull(backiId));
			
			currentBilldayCell = dataRow.createCell(12);
			currentBilldayCell.setCellValue(currentBillday+""+productName+""+"利息");
			
			applyLendDayCell = dataRow.createCell(13);
			applyLendDayCell.setCellValue(DeductUtils.isNull(applyLendDay));
			
			applyLendMoneyCell = dataRow.createCell(14);
			applyLendMoneyCell.setCellValue(DeductUtils.isNull(applyLendMoney));
			
			applyPayCell = dataRow.createCell(15);
			if(applyPay != null && !("").equals(applyPay)){
				applyPayCell.setCellValue(PayMent.getPayMent(DeductUtils.isNull(applyPay)));
			}else{
				applyPayCell.setCellValue("");
			}
			
			orgNameCell = dataRow.createCell(16);
			orgNameCell.setCellValue(DeductUtils.isNull(orgName));
			row = row + 1;
		}
	}
}
