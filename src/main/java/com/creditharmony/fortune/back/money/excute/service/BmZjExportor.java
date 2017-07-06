package com.creditharmony.fortune.back.money.excute.service;

import java.math.BigDecimal;
import java.sql.ResultSet;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.common.type.CertificateType;
import com.creditharmony.core.fortune.type.BackType;
import com.creditharmony.core.fortune.type.OpenBank;
import com.creditharmony.fortune.back.money.utils.BmExportor;
import com.creditharmony.fortune.redemption.constant.RedeemConstant;

/**
 * 中金导出工具
 * @Class Name BmZjExportor
 * @author 陈广鹏
 * @Create In 2016年4月16日
 */
public class BmZjExportor extends BmExportor {

	public BmZjExportor(String filename) {
		super(filename);
	}

	/**
	 * 填充单元格
	 */
	@Override
	public void assembleExcelCell(ResultSet resultSet, Sheet dataSheet)
			throws Exception {
		int row = 1;
		String serialNum;
		String backActualbackMoney;
		String suceessMoney;
		String accountBank;
		String accountType;
		String accountName;
		String accountNo;
		String accountBranch;
		String accountAddrprovince;
		String accountAddrcity;
		String lendCode;
		String backMoneyType;
		String remark;
		String dictCustomerCertType;
		String customerCertNum;
		String customerMobilephone;
		String customerEmail;
		
		Row dataRow;
		while (resultSet.next()) {
			serialNum = StringUtils.doNumFormat(row, "00000");
			suceessMoney = resultSet.getString("success_money");
			if (StringUtils.isEmpty(suceessMoney)) {
				suceessMoney = "0";
			} 
			backActualbackMoney = resultSet.getString("back_actualback_money");
			if (StringUtils.isNotEmpty(backActualbackMoney)) {
				backActualbackMoney = StringUtils.doNumFormat(
						new BigDecimal(backActualbackMoney).subtract(new BigDecimal(suceessMoney)),
						RedeemConstant.MONEY_FORMAT);
			}
			accountBank = resultSet.getString("account_bank");
			if (StringUtils.isNotEmpty(accountBank)) {
				accountBank = OpenBank.openBankMap.get(accountBank);
			}
			accountType = "个人";
			accountName = resultSet.getString("account_name");
			accountNo = resultSet.getString("account_no");
			accountBranch = resultSet.getString("account_branch");
			accountAddrprovince = resultSet.getString("accountAddrprovince");
			accountAddrcity = resultSet.getString("accountAddrcity");
			lendCode = resultSet.getString("lend_code");
			backMoneyType = resultSet.getString("back_money_type");
			if (StringUtils.isNotEmpty(backMoneyType)) {
				backMoneyType = BackType.backTypeMap.get(backMoneyType);
			}
			// 备注= 出借编号_回款类型
			remark = lendCode + "_" + backMoneyType;
			dictCustomerCertType = resultSet.getString("dict_customer_cert_type");
			if (StringUtils.isNotEmpty(dictCustomerCertType)) {
				dictCustomerCertType = CertificateType.parseByCode(dictCustomerCertType).getName();
			}
			customerCertNum = ""; // resultSet.getString("customer_cert_num");
			customerMobilephone = ""; // 中金导出EXCEL、TXT模版时，手机号与邮箱不是必填项，需为空
			customerEmail = ""; // 中金导出EXCEL、TXT模版时，手机号与邮箱不是必填项，需为空
			
			dataRow = dataSheet.createRow(row);
			dataRow.createCell(0).setCellValue(serialNum);
			dataRow.createCell(1).setCellValue(backActualbackMoney);
			dataRow.createCell(2).setCellValue(accountBank);
			dataRow.createCell(3).setCellValue(accountType);
			dataRow.createCell(4).setCellValue(accountName);
			dataRow.createCell(5).setCellValue(accountNo);
			dataRow.createCell(6).setCellValue(accountBranch);
			dataRow.createCell(7).setCellValue(accountAddrprovince);
			dataRow.createCell(8).setCellValue(accountAddrcity);
			dataRow.createCell(9).setCellValue(remark);
			dataRow.createCell(10).setCellValue(dictCustomerCertType);
			dataRow.createCell(11).setCellValue(customerCertNum);
			dataRow.createCell(12).setCellValue(customerMobilephone);
			dataRow.createCell(13).setCellValue(customerEmail);
			
			row++;
		}
		

	}

	/**
	 * 设置表头
	 */
	@Override
	public void wrapperHeader(Sheet dataSheet) {
		Row headerRow = dataSheet.createRow(0);
		
		headerRow.createCell(0).setCellValue("明细流水号");
		headerRow.createCell(1).setCellValue("金额(元)"); // 实际回款金额 - 回款成功金额
		headerRow.createCell(2).setCellValue("银行代码"); // 回款开户行
		headerRow.createCell(3).setCellValue("账户类型");
		headerRow.createCell(4).setCellValue("账户名称");
		headerRow.createCell(5).setCellValue("账户号码");
		headerRow.createCell(6).setCellValue("分支行");
		headerRow.createCell(7).setCellValue("省份");
		headerRow.createCell(8).setCellValue("城市");
		headerRow.createCell(9).setCellValue("备注"); // 备注= 出借编号_回款类型
		headerRow.createCell(10).setCellValue("证件类型");
		headerRow.createCell(11).setCellValue("证件号码");
		headerRow.createCell(12).setCellValue("手机号");
		headerRow.createCell(13).setCellValue("电子邮箱");

	}

}
