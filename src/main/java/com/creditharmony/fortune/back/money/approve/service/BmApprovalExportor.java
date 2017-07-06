package com.creditharmony.fortune.back.money.approve.service;

import java.math.BigDecimal;
import java.sql.ResultSet;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.fortune.type.BackType;
import com.creditharmony.core.fortune.type.FortuneChannelFlag;
import com.creditharmony.core.fortune.type.OpenBank;
import com.creditharmony.core.fortune.type.PayMent;
import com.creditharmony.core.fortune.type.RedeemBackDeadline;
import com.creditharmony.core.fortune.type.RedeemCostType;
import com.creditharmony.core.fortune.type.WorkingState;
import com.creditharmony.fortune.back.money.utils.BmExportor;
import com.creditharmony.fortune.redemption.constant.RedeemConstant;

/**
 * 回款审批列表，非金账户导出
 * @Class Name BmApprovalExportor
 * @author 陈广鹏
 * @Create In 2016年4月16日
 */
public class BmApprovalExportor extends BmExportor {

	public BmApprovalExportor(String filename) {
		super(filename);
	}

	/**
	 * 填充单元格
	 */
	@Override
	public void assembleExcelCell(ResultSet resultSet, Sheet dataSheet)
			throws Exception {
		int row = 1;
		String customerName;
		String lendCode;
		String applyLendDay;
		String applyLendMoney;
		String productName;
		String finalLinitDate;
		String manager;
		String orgName;
		String accountAddrprovince;
		String accountAddrcity;
		String accountBank;
		String accountBranch;
		String accountNo;
		String backActualbackMoney;
		String backMoneyType;
		String supplementedDays;
		String applyTransferMoney;
		String backMoneyRemarks;
		String fApplyCode;
		String redemptionReceType;
		String redemptionBackLimit = "";
		String applyPay;
		String workingState;
		String dictFortunechannelflag;
		
		Row dataRow;
		while (resultSet.next()) {
			customerName = resultSet.getString("customer_name");
			// 屏蔽客户姓名/手机号/身份证号
			customerName = "***";
			lendCode = resultSet.getString("lend_code");
			applyLendDay = resultSet.getString("apply_lend_day");
			applyLendMoney = resultSet.getString("apply_lend_money");
			if (StringUtils.isNotEmpty(applyLendMoney)) {
				applyLendMoney = StringUtils.doNumFormat(new BigDecimal(applyLendMoney), 
						RedeemConstant.MONEY_FORMAT);
			}
			productName = resultSet.getString("product_name");
			finalLinitDate = resultSet.getString("final_linit_date");
			manager = resultSet.getString("manager");
			orgName = resultSet.getString("orgName");
			accountAddrprovince = resultSet.getString("accountAddrprovince");
			accountAddrcity = resultSet.getString("accountAddrcity");
			accountBank = resultSet.getString("account_bank");
			if (StringUtils.isNotEmpty(accountBank)) {
				accountBank = OpenBank.openBankMap.get(accountBank);
			}
			accountBranch = resultSet.getString("account_branch");
			backActualbackMoney = resultSet.getString("back_actualback_money");
			if (StringUtils.isNotEmpty(backActualbackMoney)) {
				backActualbackMoney = StringUtils.doNumFormat(new BigDecimal(backActualbackMoney),
						RedeemConstant.MONEY_FORMAT);
			}
			backMoneyType = resultSet.getString("back_money_type");
			if (StringUtils.isNotEmpty(backMoneyType)) {
				backMoneyType = BackType.backTypeMap.get(backMoneyType);
			}
			supplementedDays = resultSet.getString("supplemented_days");
			applyTransferMoney = resultSet.getString("apply_transfer_money");
			if (StringUtils.isNotEmpty(applyTransferMoney)) {
				applyTransferMoney = StringUtils.doNumFormat(new BigDecimal(applyTransferMoney),
						RedeemConstant.MONEY_FORMAT);
			}
			backMoneyRemarks = resultSet.getString("back_money_remarks");
			fApplyCode = resultSet.getString("f_apply_code");
			redemptionReceType = resultSet.getString("redemption_rece_type");
			if (StringUtils.isNotEmpty(redemptionReceType) 
					&& BackType.TQSH.value.equals(resultSet.getString("back_money_type"))) {
				redemptionBackLimit = RedeemBackDeadline.parseByCode(redemptionReceType).getValue();
				if (StringUtils.isNotEmpty(redemptionBackLimit)) {
					redemptionBackLimit += "，服务费";
				} else {
					redemptionBackLimit += "服务费";
				}
				redemptionBackLimit += StringUtils.doNumFormat(RedeemCostType.parseByCode(redemptionReceType).getValue(), "0%");
			} else {
				redemptionBackLimit = "";
			}
			applyPay = resultSet.getString("apply_pay");
			if (StringUtils.isNotEmpty(applyPay)) {
				applyPay = PayMent.payMentMap.get(applyPay);
			}
			if (PayMent.ZJTG.value.equals(resultSet.getString("apply_pay"))) {
				applyPay = "TG";
				accountNo = resultSet.getString("trusteeship_no");
			} else {
				applyPay = "非TG";
				accountNo = resultSet.getString("account_no");
			}
			workingState = resultSet.getString("working_state");
			if(StringUtils.isNotEmpty(workingState)){
				workingState = WorkingState.workingStateMap.get(workingState);
			}
			dictFortunechannelflag = resultSet.getString("dict_fortunechannelflag");
			if(StringUtils.isNotEmpty(dictFortunechannelflag)){
				dictFortunechannelflag = FortuneChannelFlag.channalFlagMap.get(dictFortunechannelflag).name;
			}
			
			dataRow = dataSheet.createRow(row);
			dataRow.createCell(0).setCellValue(customerName);
			dataRow.createCell(1).setCellValue(lendCode);
			dataRow.createCell(2).setCellValue(applyLendDay);
			dataRow.createCell(3).setCellValue(applyLendMoney);
			dataRow.createCell(4).setCellValue(productName);
			dataRow.createCell(5).setCellValue(finalLinitDate);
			dataRow.createCell(6).setCellValue(manager);
			dataRow.createCell(7).setCellValue(orgName);
			dataRow.createCell(8).setCellValue(accountAddrprovince);
			dataRow.createCell(9).setCellValue(accountAddrcity);
			dataRow.createCell(10).setCellValue(accountBank);
			dataRow.createCell(11).setCellValue(accountBranch);
			dataRow.createCell(12).setCellValue(accountNo);
			dataRow.createCell(13).setCellValue(backActualbackMoney);
			dataRow.createCell(14).setCellValue(backMoneyType);
			dataRow.createCell(15).setCellValue(supplementedDays);
			dataRow.createCell(16).setCellValue(applyTransferMoney);
			dataRow.createCell(17).setCellValue(backMoneyRemarks);
			dataRow.createCell(18).setCellValue(fApplyCode);
			dataRow.createCell(19).setCellValue(redemptionBackLimit);
			dataRow.createCell(20).setCellValue(applyPay);
			dataRow.createCell(21).setCellValue(workingState);
			dataRow.createCell(22).setCellValue(dictFortunechannelflag);
			row++;
		}

	}

	/**
	 * 设置表头
	 */
	@Override
	public void wrapperHeader(Sheet dataSheet) {

		Row headerRow = dataSheet.createRow(0);
		headerRow.createCell(0).setCellValue("客户姓名");
		headerRow.createCell(1).setCellValue("出借编号");
		headerRow.createCell(2).setCellValue("首次出借日期");
		headerRow.createCell(3).setCellValue("初始出借金额");
		headerRow.createCell(4).setCellValue("产品类型");
		headerRow.createCell(5).setCellValue("产品到期日期");
		headerRow.createCell(6).setCellValue("理财经理");
		headerRow.createCell(7).setCellValue("营业部");
		headerRow.createCell(8).setCellValue("账户所在省");
		headerRow.createCell(9).setCellValue("账户所在城市");
		headerRow.createCell(10).setCellValue("回款开户行");
		headerRow.createCell(11).setCellValue("回款支行名称");
		headerRow.createCell(12).setCellValue("账号");
		headerRow.createCell(13).setCellValue("实际回款金额");
		headerRow.createCell(14).setCellValue("回款类型");
		headerRow.createCell(15).setCellValue("回款补息天数");
		headerRow.createCell(16).setCellValue("内转金额");
		headerRow.createCell(17).setCellValue("备注");
		headerRow.createCell(18).setCellValue("内转出借编号");
		headerRow.createCell(19).setCellValue("回款期限");
		headerRow.createCell(20).setCellValue("债权标识");
		headerRow.createCell(21).setCellValue("在职状态");
		headerRow.createCell(22).setCellValue("渠道标识");
	}

}
