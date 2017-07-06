package com.creditharmony.fortune.template.entity.backInterest;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.fortune.type.OpenBank;
import com.creditharmony.fortune.back.interest.contants.GlobalConstant;
import com.creditharmony.fortune.deduct.common.DeductUtils;

/**
 * 中金导出
 * @Class Name ZJExport 
 * @author 李志伟
 * @Create In 2016年4月21日
 */
public class ZJExport {
	
	/**
	 * 中金导出
	 * 2016年4月16日
	 * by 李志伟
	 * @param resultSet
	 * @param dataSheet
	 * @throws SQLException
	 */
	public static void assembleExcelCell(ResultSet resultSet, Sheet dataSheet)
			throws SQLException {
		int row = 1;
		
		String accountBank;
		String accountName;
		String accountNo;
		String accountBranch;
		String province;
		String city;
		//String backiId;
//		String dictCustomerCertType; // 证件类型
//		String customerCertNum; 
//		String customerMobilephone;
//		String customerEmail;
		String lendCode;
		String currentBillday;
		
		Row dataRow;
		
		Cell idCell;
		Cell backRealMoneyCell;
		Cell accountBankCell;
		Cell accTypeCell;
		Cell accountNameCell;
		Cell accountNoCell;
		Cell accountBranchCell;
		Cell provinceCell;
		Cell cityCell;
		Cell backiIdCell;
		Cell dictCustomerCertTypeCell; // 证件类型
		Cell customerCertNumCell; 
		Cell customerMobilephoneCell;
		Cell customerEmailCell;
		
		int i = 1;
		while (resultSet.next()) {
			
			accountBank = resultSet.getString("account_bank");
			accountName = resultSet.getString("account_name");
			accountNo = resultSet.getString("account_no");
			accountBranch = resultSet.getString("account_branch");
			province = resultSet.getString("province");
			city = resultSet.getString("city");
			//backiId = resultSet.getString("backi_id");
			lendCode = resultSet.getString("lend_code");
			currentBillday = resultSet.getString("current_billday");
//			dictCustomerCertType = resultSet.getString("dict_customer_cert_type");
//			customerCertNum = resultSet.getString("customer_cert_num"); 
//			customerMobilephone = resultSet.getString("customer_mobilephone");
//			customerEmail = resultSet.getString("customer_email");
			
			
			// ----------------------------------
			BigDecimal bi = resultSet.getBigDecimal("back_real_money");
			bi = bi==null?BigDecimal.ZERO:bi;
			
			BigDecimal su = resultSet.getBigDecimal("success_money");
			su = su==null?BigDecimal.ZERO:su;
			
			BigDecimal sd = bi.subtract(su);
			// ----------------------------------
			int to = sd.compareTo(BigDecimal.ZERO);
			if(to == 1){
				
				dataRow = dataSheet.createRow(row);
				
				idCell = dataRow.createCell(0);
				idCell.setCellValue(StringUtils.doNumFormat(i, "00000"));
				
				backRealMoneyCell = dataRow.createCell(1);
				backRealMoneyCell.setCellValue(sd.toString());
				
				accountBankCell = dataRow.createCell(2);
				if(accountBank != null && !("").equals(accountBank)){
					accountBankCell.setCellValue(OpenBank.getOpenBank(accountBank));
				}else{
					accountBankCell.setCellValue("");
				}
				
				accTypeCell = dataRow.createCell(3);
				accTypeCell.setCellValue("个人");
				
				accountNameCell = dataRow.createCell(4);
				accountNameCell.setCellValue(DeductUtils.isNull(accountName));
	
				accountNoCell = dataRow.createCell(5);
				accountNoCell.setCellValue(DeductUtils.isNull(accountNo));
	
				accountBranchCell = dataRow.createCell(6);
				accountBranchCell.setCellValue(DeductUtils.isNull(accountBranch));
	
				provinceCell = dataRow.createCell(7);
				provinceCell.setCellValue(DeductUtils.isNull(province));
				
				cityCell = dataRow.createCell(8);
				cityCell.setCellValue(DeductUtils.isNull(city));
				
				backiIdCell = dataRow.createCell(9);
				backiIdCell.setCellValue(lendCode+"回息"+currentBillday);// 出借编号+回息+账单日+唯一标识
				
				dictCustomerCertTypeCell= dataRow.createCell(10); // 证件类型
				dictCustomerCertTypeCell.setCellValue("");
				
				customerCertNumCell = dataRow.createCell(11); 
				customerCertNumCell.setCellValue("");
				
				customerMobilephoneCell = dataRow.createCell(12);
				customerMobilephoneCell.setCellValue("");
				
				customerEmailCell = dataRow.createCell(13);
				customerEmailCell.setCellValue("");
				
				row = row + 1;
				++i;
			}else{
				dataRow = dataSheet.createRow(row);
				Cell cl = dataRow.createCell(0);
				cl.setCellValue(lendCode);
				Cell cl2 = dataRow.createCell(1);
				cl2.setCellValue(GlobalConstant.ERROR);
				row = row + 1;
				++i;
			}
		}
	}
}
