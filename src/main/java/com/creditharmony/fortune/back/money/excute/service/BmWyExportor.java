package com.creditharmony.fortune.back.money.excute.service;

import java.math.BigDecimal;
import java.sql.ResultSet;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.deduct.type.CardOrBookType;
import com.creditharmony.core.fortune.type.BackType;
import com.creditharmony.core.fortune.type.ContractVesion;
import com.creditharmony.core.fortune.type.OpenBank;
import com.creditharmony.core.fortune.type.WorkingState;
import com.creditharmony.fortune.back.money.utils.BmExportor;
import com.creditharmony.fortune.back.money.utils.BmUtils;
import com.creditharmony.fortune.redemption.constant.RedeemConstant;

/**
 * 执行回款，网银导出
 * @Class Name BmWyExportor
 * @author 陈广鹏
 * @Create In 2016年4月18日
 */
public class BmWyExportor extends BmExportor {

	public BmWyExportor(String filename) {
		super(filename,"待回款列表1");
	}

	/**
	 * 填充单元格数据
	 */
	@Override
	public void assembleExcelCell(ResultSet resultSet, Sheet dataSheet)
			throws Exception {
		int row = 1;
		String lendCode;
		String accountNo;
		String accountName;
		String suceessMoney;
		String backActualbackMoney;
		String backMoneyType;
		String remark;
		String accountBank;
		String accountBranch;
		String accountCardOrBooklet;
		String accountAddrprovince;
		String accountAddrcity;
		String fkAccount;
		String fkBank;
		String fkAccountNo;
		String fkDate;
		String applyAgreementEdition;
		String workingState;
		
		Row dataRow;
		while (resultSet.next()) {
			lendCode = resultSet.getString("lend_code");
			accountNo = resultSet.getString("account_no");
			accountName = resultSet.getString("account_name");
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
			backMoneyType = resultSet.getString("back_money_type");
			if (null != backMoneyType) {
				backMoneyType = BackType.backTypeMap.get(backMoneyType);
			}
			// remark = lendCode + "_" + backMoneyType;
			remark = BmUtils.getRemark(lendCode);
			accountBank = resultSet.getString("account_bank");
			if (StringUtils.isNotEmpty(accountBank)) {
				accountBank = OpenBank.openBankMap.get(accountBank);
			}
			accountBranch = resultSet.getString("account_branch");
			accountCardOrBooklet = resultSet.getString("account_card_or_booklet");
			if (StringUtils.isNotEmpty(accountCardOrBooklet)) {
				accountCardOrBooklet = CardOrBookType.parseByCode(accountCardOrBooklet).getName();
			}
			accountAddrprovince = resultSet.getString("accountAddrprovince");
			accountAddrcity = resultSet.getString("accountAddrcity");
			// 合同版本号
			applyAgreementEdition = resultSet.getString("apply_agreement_edition");
			applyAgreementEdition = ContractVesion.getContractVesion(applyAgreementEdition);
			if (StringUtils.isEmpty(applyAgreementEdition)) {
				applyAgreementEdition = "";
			}
			fkAccount = "";
			fkBank = "";
			fkAccountNo = "";
			fkDate = "";
			workingState = resultSet.getString("working_state");
			if (StringUtils.isNotEmpty(workingState)){
				workingState = WorkingState.workingStateMap.get(workingState);
			}
			dataRow = dataSheet.createRow(row);
			dataRow.createCell(0).setCellValue(lendCode);
			dataRow.createCell(1).setCellValue(accountNo);
			dataRow.createCell(2).setCellValue(accountName);
			dataRow.createCell(3).setCellValue(backActualbackMoney);
			dataRow.createCell(4).setCellValue(remark);
			dataRow.createCell(5).setCellValue(accountBank);
			dataRow.createCell(6).setCellValue(accountBranch);
			dataRow.createCell(7).setCellValue(accountCardOrBooklet);
			dataRow.createCell(8).setCellValue(BmUtils.deleteTail(accountAddrprovince));
			dataRow.createCell(9).setCellValue(BmUtils.deleteTail(accountAddrcity));
			dataRow.createCell(10).setCellValue(fkAccount);
			dataRow.createCell(11).setCellValue(fkBank);
			dataRow.createCell(12).setCellValue(fkAccountNo);
			dataRow.createCell(13).setCellValue(fkDate);
			dataRow.createCell(14).setCellValue(workingState);
			dataRow.createCell(15).setCellValue(applyAgreementEdition);
			row ++;
		}
	}

	/**
	 * 设置表头
	 */
	@Override
	public void wrapperHeader(Sheet dataSheet) {
		Row headerRow = dataSheet.createRow(0);
		headerRow.createCell(0).setCellValue("出借编号");
		headerRow.createCell(1).setCellValue("收款账户");
		headerRow.createCell(2).setCellValue("收款户名");
		headerRow.createCell(3).setCellValue("实际回款金额");
		headerRow.createCell(4).setCellValue("备注"); // 备注 = 出借编号+回款
		headerRow.createCell(5).setCellValue("收款银行");
		headerRow.createCell(6).setCellValue("收款银行支行");
		headerRow.createCell(7).setCellValue("卡或折");
		headerRow.createCell(8).setCellValue("收款省/直辖市");
		headerRow.createCell(9).setCellValue("收款县");
		headerRow.createCell(10).setCellValue("放款账户");
		headerRow.createCell(11).setCellValue("开户行");
		headerRow.createCell(12).setCellValue("银行账号");
		headerRow.createCell(13).setCellValue("放款日期");
		headerRow.createCell(14).setCellValue("在职状态");
		headerRow.createCell(15).setCellValue("合同版本号");
	}

}
