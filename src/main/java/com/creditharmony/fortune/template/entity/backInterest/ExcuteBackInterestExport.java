package com.creditharmony.fortune.template.entity.backInterest;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.creditharmony.core.deduct.type.CardOrBookType;
import com.creditharmony.core.fortune.type.ContractVesion;
import com.creditharmony.core.fortune.type.OpenBank;
import com.creditharmony.fortune.back.interest.contants.GlobalConstant;
import com.creditharmony.fortune.back.interest.util.StaticMethodUtil;
import com.creditharmony.fortune.deduct.common.DeductUtils;

/**
 * 执行回息导出
 * @Class Name ExcuteBackInterestExport 
 * @author 李志伟
 * @Create In 2016年4月16日
 */
public class ExcuteBackInterestExport {
	
	/**
	 * 执行回息导出
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
		BigDecimal backRealMoney;
		BigDecimal successMoney;
		//String backiId;
		String accountBank;
		String accountBranch;
		String accountCardOrBooklet;
		String province;
		String city;
		String currentBillday;
		//String productName;
		String applyAgreementEdition;
		
		Row dataRow;
		
		Cell lendCodeCell;
		Cell accountNoCell;
		Cell accountNameCell;
		Cell backRealMoneyCell;
		Cell backiIdCell;
		Cell accountBankCell;
		Cell accountBranchCell;
		Cell accountCardOrBookletCell;
		Cell provinceCell;
		Cell cityCell;
		Cell outNameCell;
		Cell outBankCell;
		Cell outBankNoCell;
		Cell outDateCell;
		Cell currentBilldayCell;
		Cell applyAgreementEditionCell;
		
		while (resultSet.next()) {
			
			lendCode = resultSet.getString("lend_code");
			accountNo = resultSet.getString("account_no");
			accountName = resultSet.getString("account_name");
			//productName = resultSet.getString("product_name");
			accountBank = resultSet.getString("account_bank");
			accountBranch = resultSet.getString("account_branch");
			accountNo = resultSet.getString("account_no");
			//backiId = resultSet.getString("backi_id");
			accountCardOrBooklet = resultSet.getString("account_card_or_booklet");
			province = resultSet.getString("province");
			city = resultSet.getString("city");
			currentBillday = resultSet.getString("current_billday");
			
			backRealMoney = resultSet.getBigDecimal("back_real_money");
			backRealMoney = backRealMoney==null? new BigDecimal(0) : backRealMoney;
			successMoney = resultSet.getBigDecimal("success_money");
			successMoney = successMoney==null? new BigDecimal(0) : successMoney;
			backRealMoney = backRealMoney.subtract(successMoney);
			applyAgreementEdition = resultSet.getString("apply_agreement_edition");
			int to = backRealMoney.compareTo(BigDecimal.ZERO);
			if(to==1){
			
				dataRow = dataSheet.createRow(row);
				
				lendCodeCell = dataRow.createCell(0);
				lendCodeCell.setCellValue(DeductUtils.isNull(lendCode));
				
				accountNoCell = dataRow.createCell(1);
				accountNoCell.setCellValue(DeductUtils.isNull(accountNo));
	
				accountNameCell = dataRow.createCell(2);
				accountNameCell.setCellValue(DeductUtils.isNull(accountName));
				
				backRealMoneyCell = dataRow.createCell(3);
				backRealMoneyCell.setCellValue(DeductUtils.isNull(backRealMoney.toString()));
				
				backiIdCell = dataRow.createCell(4);
				String  backiIdcell=lendCode+"回息"+currentBillday;
				backiIdcell=backiIdcell.replace("-", "");
				backiIdCell.setCellValue(backiIdcell);
				
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
				provinceCell.setCellValue(StaticMethodUtil.spiltProvince(DeductUtils.isNull(province)));
				
				cityCell = dataRow.createCell(9);
				cityCell.setCellValue(StaticMethodUtil.spiltProvince(DeductUtils.isNull(city)));
				
				outNameCell = dataRow.createCell(10);
				outNameCell.setCellValue("");
				
				outBankCell = dataRow.createCell(11);
				outBankCell.setCellValue("");
				
				outBankNoCell = dataRow.createCell(12);
				outBankNoCell.setCellValue("");
				
				outDateCell = dataRow.createCell(13);
				outDateCell.setCellValue("");
				
				currentBilldayCell = dataRow.createCell(14);
				currentBilldayCell.setCellValue(currentBillday);
				
				applyAgreementEditionCell = dataRow.createCell(15);
				if(StringUtils.isNotBlank(applyAgreementEdition)){
					applyAgreementEditionCell.setCellValue(ContractVesion.getContractVesion(applyAgreementEdition));
				}
				
				row = row + 1;
			}else{// 如果回息金额为0或者金额为负数，要进行提醒
				dataRow = dataSheet.createRow(row);
				
				lendCodeCell = dataRow.createCell(0);
				lendCodeCell.setCellValue(lendCode+"");
				
				Cell ce = dataRow.createCell(1);
				ce.setCellValue(GlobalConstant.ERROR);
				row = row + 1;
			}
		}
	}
}
