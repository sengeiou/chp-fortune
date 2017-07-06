package com.creditharmony.fortune.back.money.applyconfirm.service;

import java.math.BigDecimal;
import java.sql.ResultSet;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.deduct.type.CardOrBookType;
import com.creditharmony.core.fortune.type.BackType;
import com.creditharmony.core.fortune.type.OpenBank;
import com.creditharmony.core.fortune.type.PayMent;
import com.creditharmony.core.fortune.type.WorkingState;
import com.creditharmony.fortune.back.money.utils.BmExportor;
import com.creditharmony.fortune.redemption.constant.RedeemConstant;

/**
 * 回款申请确认列表，非金账户导出
 * @Class Name BmApplyConfirmExportor
 * @author 陈广鹏
 * @Create In 2016年4月16日
 */
public class BmApplyConfirmExportor extends BmExportor {

	public BmApplyConfirmExportor(String filename) {
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
		String backMoney;
		String backActualbackMoney;
		String supplementedMoney;
		String accountAddrprovince;
		String accountAddrcity;
		String accountBank;
		String accountBranch;
		String accountNo;
		String backMoneyType;
		String applyPay;
		String accountCardOrBooklet;
		String feedbackRemark;
		String feedbackMoney;
		String workingState;
		String approveDate;
		String interestDay;
		
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
			backMoney = resultSet.getString("back_money");
			if (StringUtils.isNotEmpty(backMoney)) {
				backMoney = StringUtils.doNumFormat(new BigDecimal(backMoney), RedeemConstant.MONEY_FORMAT);
			}
			backActualbackMoney = resultSet.getString("back_actualback_money");
			if (StringUtils.isNotEmpty(backActualbackMoney)) {
				backActualbackMoney = StringUtils.doNumFormat(new BigDecimal(backActualbackMoney),
						RedeemConstant.MONEY_FORMAT);
			}
			//补息后回款金额
			supplementedMoney = resultSet.getString("supplemented_money");
			//判断是否为空
			if (StringUtils.isNotEmpty(supplementedMoney)) {
				supplementedMoney = StringUtils.doNumFormat(new BigDecimal(supplementedMoney),
						RedeemConstant.MONEY_FORMAT);
			}
			accountAddrprovince = resultSet.getString("accountAddrprovince");
			accountAddrcity = resultSet.getString("accountAddrcity");
			accountBank = resultSet.getString("account_bank");
			if (StringUtils.isNotEmpty(accountBank)) {
				accountBank = OpenBank.openBankMap.get(accountBank);
			}
			accountBranch = resultSet.getString("account_branch");
			accountNo = resultSet.getString("account_no");
			backMoneyType = resultSet.getString("back_money_type");
			if (StringUtils.isNotEmpty(backMoneyType)) {
				backMoneyType = BackType.backTypeMap.get(backMoneyType);
			}
			applyPay = resultSet.getString("apply_pay");
			if (StringUtils.isNotEmpty(applyPay)) {
				applyPay = PayMent.payMentMap.get(applyPay);
			}
			accountCardOrBooklet = resultSet.getString("account_card_or_booklet");
			if (StringUtils.isNotEmpty(accountCardOrBooklet)) {
				accountCardOrBooklet = CardOrBookType.parseByCode(accountCardOrBooklet).getName();
			}
			feedbackRemark = resultSet.getString("feedback_remark");
			if (StringUtils.isEmpty(feedbackRemark)) {
				feedbackRemark = "";
			}
			feedbackMoney = resultSet.getString("feedback_money");
			if (StringUtils.isNotEmpty(feedbackMoney) && 
					BackType.TQSH.value.equals(resultSet.getString("back_money_type"))
					&& new BigDecimal(feedbackMoney).compareTo(BigDecimal.ZERO)>0) {
				feedbackMoney = StringUtils.doNumFormat(new BigDecimal(feedbackMoney),"#0.00")
						+ "_" + feedbackRemark;
			} else {
				feedbackMoney = "";
			}
			workingState = resultSet.getString("working_state");
			if(StringUtils.isNotEmpty(workingState)){
				workingState = WorkingState.workingStateMap.get(workingState);
			}
			approveDate = resultSet.getString("approve_date");
			interestDay = resultSet.getString("interest_day");
			
			dataRow = dataSheet.createRow(row);
			dataRow.createCell(0).setCellValue(customerName);
			dataRow.createCell(1).setCellValue(lendCode);
			dataRow.createCell(2).setCellValue(applyLendDay);
			dataRow.createCell(3).setCellValue(applyLendMoney);
			dataRow.createCell(4).setCellValue(productName);
			dataRow.createCell(5).setCellValue(finalLinitDate);
			dataRow.createCell(6).setCellValue(manager);
			dataRow.createCell(7).setCellValue(orgName);
			dataRow.createCell(8).setCellValue(backMoney);
			dataRow.createCell(9).setCellValue(backActualbackMoney);
			dataRow.createCell(10).setCellValue(supplementedMoney);
			dataRow.createCell(11).setCellValue(accountAddrprovince);
			dataRow.createCell(12).setCellValue(accountAddrcity);
			dataRow.createCell(13).setCellValue(accountBank);
			dataRow.createCell(14).setCellValue(accountBranch);
			dataRow.createCell(15).setCellValue(accountNo);
			dataRow.createCell(16).setCellValue(backMoneyType);
			dataRow.createCell(17).setCellValue(applyPay);
			dataRow.createCell(18).setCellValue(accountCardOrBooklet);
			dataRow.createCell(19).setCellValue(feedbackMoney);
			dataRow.createCell(20).setCellValue(approveDate);
			dataRow.createCell(21).setCellValue(interestDay);
			dataRow.createCell(22).setCellValue(workingState);
			row++;
		}

	}

	/**
	 * 设置表头
	 */
	@Override
	public void wrapperHeader(Sheet dataSheet) {
		Row headerRow = dataSheet.createRow(0);
		headerRow.createCell(0).setCellValue("姓名");
		headerRow.createCell(1).setCellValue("出借编号");
		headerRow.createCell(2).setCellValue("首次出借日期");
		headerRow.createCell(3).setCellValue("初始出借金额");
		headerRow.createCell(4).setCellValue("产品类型");
		headerRow.createCell(5).setCellValue("产品到期日");
		headerRow.createCell(6).setCellValue("理财经理");
		headerRow.createCell(7).setCellValue("营业部");
		headerRow.createCell(8).setCellValue("应回款金额");
		headerRow.createCell(9).setCellValue("实际回款金额");
		headerRow.createCell(10).setCellValue("补息后回款金额");
		headerRow.createCell(11).setCellValue("账户所在省");
		headerRow.createCell(12).setCellValue("账户所在城市");
		headerRow.createCell(13).setCellValue("回款开户行");
		headerRow.createCell(14).setCellValue("回款支行名称");
		headerRow.createCell(15).setCellValue("账号");
		headerRow.createCell(16).setCellValue("回款类型");
		headerRow.createCell(17).setCellValue("付款方式");
		headerRow.createCell(18).setCellValue("卡/折");
		headerRow.createCell(19).setCellValue("备注"); // 客户回馈金额__回馈事项备注
		headerRow.createCell(20).setCellValue("大金融审核日期");
		headerRow.createCell(21).setCellValue("补息天数");
		headerRow.createCell(22).setCellValue("在职状态");
	}

}
