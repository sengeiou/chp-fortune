package com.creditharmony.fortune.back.money.apply.service;

import java.math.BigDecimal;
import java.sql.ResultSet;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.fortune.type.BackType;
import com.creditharmony.core.fortune.type.PayMent;
import com.creditharmony.core.fortune.type.WorkingState;
import com.creditharmony.fortune.back.money.utils.BmExportor;
import com.creditharmony.fortune.redemption.constant.RedeemConstant;

/**
 * 回款申请列表，金账户导出
 * @Class Name BmApplyJzhExportor
 * @author 陈广鹏
 * @Create In 2016年4月16日
 */
public class BmApplyJzhExportor extends BmExportor {

	public BmApplyJzhExportor(String filename) {
		super(filename);
	}

	/**
	 * 
	 */
	@Override
	public void assembleExcelCell(ResultSet resultSet, Sheet dataSheet)
			throws Exception {
		int row = 1;
		String trusteeshipNo = "***********";
		String customerName;
		String lendCode;
		String applyLendDay;
		String applyLendMoney;
		String productName;
		String finalLinitDate;
		String orgName;
		String backMoney;
		String backActualbackMoney;
		String backMoneyType;
		String applyPay;
		String feedbackRemark;
		String feedbackMoney;
		String approveDate;
		String interestDay;
		String workingState;
		//自转审批日期
		String zzApproveDate;
		
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
//			trusteeshipNo = resultSet.getString("trusteeship_no");
			backMoneyType = resultSet.getString("back_money_type");
			if (StringUtils.isNotEmpty(backMoneyType)) {
				backMoneyType = BackType.backTypeMap.get(backMoneyType);
			}
			applyPay = resultSet.getString("apply_pay");
			if (StringUtils.isNotEmpty(applyPay)) {
				applyPay = PayMent.payMentMap.get(applyPay);
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
			approveDate = resultSet.getString("approve_date");
			interestDay = resultSet.getString("interest_day");
			workingState = resultSet.getString("working_state");
			if(StringUtils.isNotEmpty(workingState)){
				workingState = WorkingState.workingStateMap.get(workingState);
			}
			
			//自转审核日期
			if(StringUtils.isNotEmpty(resultSet.getString("zzApproveDate"))){
				zzApproveDate = com.creditharmony.common.util.DateUtils.formatDate(resultSet.getDate("zzApproveDate"), "yyyy-MM-dd");
			}else{
				zzApproveDate = "";
			}
			
			dataRow = dataSheet.createRow(row);
			dataRow.createCell(0).setCellValue(trusteeshipNo);
			dataRow.createCell(1).setCellValue(customerName);
			dataRow.createCell(2).setCellValue(lendCode);
			dataRow.createCell(3).setCellValue(applyLendDay);
			dataRow.createCell(4).setCellValue(applyLendMoney);
			dataRow.createCell(5).setCellValue(productName);
			dataRow.createCell(6).setCellValue(finalLinitDate);
			dataRow.createCell(7).setCellValue(backActualbackMoney);
			dataRow.createCell(8).setCellValue(orgName);
			dataRow.createCell(9).setCellValue(backMoneyType);
			dataRow.createCell(10).setCellValue(applyPay);
			dataRow.createCell(11).setCellValue(feedbackMoney);
			dataRow.createCell(12).setCellValue(approveDate);
			dataRow.createCell(13).setCellValue(interestDay);
			dataRow.createCell(14).setCellValue(workingState);
			dataRow.createCell(15).setCellValue(zzApproveDate);
			row++;
		}
	}

	/**
	 * 设置表头
	 */
	@Override
	public void wrapperHeader(Sheet dataSheet) {
		Row headerRow = dataSheet.createRow(0);
		headerRow.createCell(0).setCellValue("金账户账号");
		headerRow.createCell(1).setCellValue("姓名");
		headerRow.createCell(2).setCellValue("出借编号");
		headerRow.createCell(3).setCellValue("首次出借日期");
		headerRow.createCell(4).setCellValue("初始出借金额");
		headerRow.createCell(5).setCellValue("产品类型");
		headerRow.createCell(6).setCellValue("产品到期日");
		headerRow.createCell(7).setCellValue("实际回款金额");
		headerRow.createCell(8).setCellValue("营业部");
		headerRow.createCell(9).setCellValue("回款类型");
		headerRow.createCell(10).setCellValue("付款方式");
		headerRow.createCell(11).setCellValue("备注"); // 客户回馈金额__回馈事项备注
		headerRow.createCell(12).setCellValue("大金融审核日期");
		headerRow.createCell(13).setCellValue("补息天数");
		headerRow.createCell(14).setCellValue("在职状态");
		headerRow.createCell(15).setCellValue("自转审核日期");
	}

}
