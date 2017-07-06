package com.creditharmony.fortune.back.money.excute.service;

import java.math.BigDecimal;
import java.sql.ResultSet;

import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.fortune.type.BackType;
import com.creditharmony.fortune.back.money.utils.BmTxtExportor;

/**
 * 执行回款列表：中金导出txt
 * @Class Name BmZjTxtExportor
 * @author 陈广鹏
 * @Create In 2016年4月19日
 */
public class BmZjTxtExportor extends BmTxtExportor{

	public BmZjTxtExportor(String filename) {
		super(filename);
	}

	/**
	 * 组装TXT内容
	 */
	@Override
	public void assembleContent(ResultSet resultSet, StringBuffer sb)
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
		
		while (resultSet.next()) {
			serialNum = StringUtils.doNumFormat(row, "00000");
			suceessMoney = resultSet.getString("success_money");
			if (StringUtils.isEmpty(suceessMoney)) {
				suceessMoney = "0";
			} 
			backActualbackMoney = resultSet.getString("back_actualback_money");
			if (StringUtils.isNotEmpty(backActualbackMoney)) {
				backActualbackMoney = StringUtils.doNumFormat(
						new BigDecimal(backActualbackMoney).subtract(new BigDecimal(suceessMoney))
						.multiply(BigDecimal.valueOf(100)),"##0");// 金额应是实际回款金额的100倍
			}
			// 银行应为代码
			accountBank = resultSet.getString("account_bank");
			if (StringUtils.isEmpty(accountBank)) {
				accountBank = "";
			}
			accountType = "11";
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
			dictCustomerCertType = resultSet.getString("dict_customer_cert_type"); // 导出TXT模版时，证件类型为代码
			customerCertNum = resultSet.getString("customer_cert_num");
			customerMobilephone = ""; // 中金导出EXCEL、TXT模版时，手机号与邮箱不是必填项，需为空
			customerEmail = ""; // 中金导出EXCEL、TXT模版时，手机号与邮箱不是必填项，需为空
			
			sb.append(serialNum).append("|");
			sb.append(backActualbackMoney).append("|");
			sb.append(accountBank).append("|");
			sb.append(accountType).append("|");
			sb.append(accountName).append("|");
			sb.append(accountNo).append("|");
			sb.append(accountBranch).append("|");
			sb.append(accountAddrprovince).append("|");
			sb.append(accountAddrcity).append("|");
			sb.append(remark).append("|");
			sb.append(dictCustomerCertType).append("|");
			sb.append(customerCertNum).append("|");
			sb.append(customerMobilephone).append("|");
			sb.append(customerEmail).append("\r\n");
			
			row++;
		}
	}
	
}
