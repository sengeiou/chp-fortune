package com.creditharmony.fortune.back.money.history.service;

import java.math.BigDecimal;
import java.sql.ResultSet;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.fortune.type.BackMoneyPlat;
import com.creditharmony.core.fortune.type.BackType;
import com.creditharmony.core.fortune.type.ContractVesion;
import com.creditharmony.core.fortune.type.OpenBank;
import com.creditharmony.core.fortune.type.PayMent;
import com.creditharmony.core.fortune.type.WorkingState;
import com.creditharmony.core.fortune.type.YoN;
import com.creditharmony.fortune.back.money.utils.BmExportor;
import com.creditharmony.fortune.redemption.constant.RedeemConstant;
import com.creditharmony.core.fortune.type.FortuneChannelFlag;

/**
 * 回款已办导表
 * @Class Name BmHistoryExportor
 * @author 陈广鹏
 * @Create In 2016年4月16日
 */
public class BmHistoryExportor extends BmExportor {

	public BmHistoryExportor(String filename) {
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
		String applyPay;
		String applyAgreementEdition;
		String manager;
		String orgName;
		String accountBank;
		String accountBranch;
		String accountNo;
		String trusteeshipNo = "***********";
		String backActualbackMoney;
		String isSupplemented;  
		String backMoneyType;
		String supplementedDays;
		String backMoneyDay;
		String platformId;
		//渠道标识
		String dictFortunechannelflag;
		String workingState;
		
		//受让人出借编号
		String srrLendCode;
		//受让人姓名
		String ssrCustomerName;
		
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
			applyPay = resultSet.getString("apply_pay");
			if (StringUtils.isNotEmpty(applyPay)) {
				applyPay = PayMent.payMentMap.get(applyPay);
			}
			applyAgreementEdition = resultSet.getString("apply_agreement_edition");
			if (StringUtils.isNotEmpty(applyAgreementEdition)) {
				applyAgreementEdition = ContractVesion.contractVesionMap.get(applyAgreementEdition);
			}
			manager = resultSet.getString("manager");
			orgName = resultSet.getString("orgName");
			accountBank = resultSet.getString("account_bank");
			if (StringUtils.isNotEmpty(accountBank)) {
				accountBank = OpenBank.openBankMap.get(accountBank);
			}
			accountBranch = resultSet.getString("account_branch");
			accountNo = resultSet.getString("account_no");
//			trusteeshipNo = resultSet.getString("trusteeship_no");
			if (!PayMent.ZJTG.value.equals(resultSet.getString("apply_pay"))){
				trusteeshipNo = "";
			}
			backActualbackMoney = resultSet.getString("back_actualback_money");
			if (StringUtils.isNotEmpty(backActualbackMoney)) {
				backActualbackMoney = StringUtils.doNumFormat(new BigDecimal(backActualbackMoney),
						RedeemConstant.MONEY_FORMAT);
			}
			isSupplemented = resultSet.getString("is_supplemented");
			if (StringUtils.isEmpty(isSupplemented)) {
				isSupplemented = YoN.FOU.value;
			}else{
				isSupplemented = YoN.yoNMap.get(isSupplemented);
			}
			backMoneyType = resultSet.getString("back_money_type");
			if (StringUtils.isNotEmpty(backMoneyType)) {
				backMoneyType = BackType.backTypeMap.get(backMoneyType);
			}
			supplementedDays = resultSet.getString("supplemented_days");
			backMoneyDay = resultSet.getString("back_money_day");
			platformId = resultSet.getString("platform_id");
			if (StringUtils.isNotEmpty(platformId)) {
				platformId = BackMoneyPlat.backMoneyPlatMap.get(platformId);
			}
			//渠道标识
			dictFortunechannelflag = resultSet.getString("dict_fortunechannelflag");
			if (StringUtils.isNotEmpty(dictFortunechannelflag)) {
				dictFortunechannelflag = FortuneChannelFlag.channalFlagMap.get(dictFortunechannelflag).name;
			}
			workingState = resultSet.getString("working_state");
			if(StringUtils.isNotEmpty(workingState)){
				workingState = WorkingState.workingStateMap.get(workingState);
			}
			
			srrLendCode = resultSet.getString("srr_lendcode");
			ssrCustomerName = resultSet.getString("srr_customer_name");
			
			dataRow = dataSheet.createRow(row);
			dataRow.createCell(0).setCellValue(customerName);
			dataRow.createCell(1).setCellValue(lendCode);
			dataRow.createCell(2).setCellValue(applyLendDay);
			dataRow.createCell(3).setCellValue(applyLendMoney);
			dataRow.createCell(4).setCellValue(productName);
			dataRow.createCell(5).setCellValue(finalLinitDate);
			dataRow.createCell(6).setCellValue(applyPay);
			dataRow.createCell(7).setCellValue(applyAgreementEdition);
			dataRow.createCell(8).setCellValue(ssrCustomerName);
			dataRow.createCell(9).setCellValue(srrLendCode);
			dataRow.createCell(10).setCellValue(manager);
			dataRow.createCell(11).setCellValue(orgName);
			dataRow.createCell(12).setCellValue(accountBank);
			dataRow.createCell(13).setCellValue(accountBranch);
			dataRow.createCell(14).setCellValue(accountNo);
			dataRow.createCell(15).setCellValue(trusteeshipNo);
			dataRow.createCell(16).setCellValue(backActualbackMoney);
			dataRow.createCell(17).setCellValue(isSupplemented);
			dataRow.createCell(18).setCellValue(backMoneyType);
			dataRow.createCell(19).setCellValue(supplementedDays);
			dataRow.createCell(20).setCellValue(backMoneyDay);
			dataRow.createCell(21).setCellValue(platformId);
			dataRow.createCell(22).setCellValue(dictFortunechannelflag);
			dataRow.createCell(23).setCellValue(workingState);
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
		headerRow.createCell(6).setCellValue("付款方式");
		headerRow.createCell(7).setCellValue("协议版本号");
		headerRow.createCell(8).setCellValue("受让人姓名");
		headerRow.createCell(9).setCellValue("受让人出借编号");
		headerRow.createCell(10).setCellValue("理财经理");
		headerRow.createCell(11).setCellValue("营业部");
		headerRow.createCell(12).setCellValue("回款开户行");
		headerRow.createCell(13).setCellValue("回款支行名称");
		headerRow.createCell(14).setCellValue("账号");
		headerRow.createCell(15).setCellValue("金账户账号");
		headerRow.createCell(16).setCellValue("实际回款金额");
		headerRow.createCell(17).setCellValue("是否补息");
		headerRow.createCell(18).setCellValue("回款类型");
		headerRow.createCell(19).setCellValue("回款补息天数");
		headerRow.createCell(20).setCellValue("回款日期");
		headerRow.createCell(21).setCellValue("放款平台");
		headerRow.createCell(22).setCellValue("渠道标识");
		headerRow.createCell(23).setCellValue("在职状态");
	}

}
