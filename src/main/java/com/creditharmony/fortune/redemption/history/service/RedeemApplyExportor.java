package com.creditharmony.fortune.redemption.history.service;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Date;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.creditharmony.common.util.DateUtils;
import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.fortune.type.ContractVesion;
import com.creditharmony.core.fortune.type.PayMent;
import com.creditharmony.core.fortune.type.RedeemBackDeadline;
import com.creditharmony.core.fortune.type.RedeemCostType;
import com.creditharmony.core.fortune.type.RedeemType;
import com.creditharmony.fortune.redemption.common.service.RedeemExportor;

/**
 * 赎回申请查看导出
 * @Class Name RedeemApplyExportor
 * @author 陈广鹏
 * @Create In 2016年4月19日
 */
public class RedeemApplyExportor extends RedeemExportor {

	public RedeemApplyExportor(String filename) {
		super(filename, "com.creditharmony.fortune.redemption.common.dao.RedemptionHistoryDao.findApplyCheck");
	}

	/**
	 * 填充单元格内容
	 */
	@Override
	public void assembleExcelCell(ResultSet resultSet, Sheet dataSheet)
			throws Exception {
		int row = 1;
		String redemptionTime = "";
		String customerName;
		String customerCode;
		String lendCode;
		String applyLendDay;
		String applyLendMoney;
		String redemptionMoney;
		String productName;
		String applyPay;
		String addrProvince;
		String addrCity;
		String orgName;
		String teamManager;
		String userName;
		String applyBillday;
		String linitDay;
		String applyAgreementEdition;
		String redemptionReceType;
		String redeemCost;
		String redeemBackDeadline;
		String redemptionType;
		
		Row dataRow;
		while (resultSet.next()) {
			Date time = resultSet.getDate("redemption_time");
			if (null != time) {
				redemptionTime = DateUtils.formatDate(time, "yyyy-MM-dd");
			}
			customerName = resultSet.getString("customer_name");
			customerCode = resultSet.getString("customer_code");
			lendCode = resultSet.getString("lend_code");
			applyLendDay = resultSet.getString("apply_lend_day");
			applyLendMoney = resultSet.getString("apply_lend_money");
			if (StringUtils.isNotEmpty(applyLendMoney)) {
				applyLendMoney = StringUtils.doNumFormat(new BigDecimal(applyLendMoney), "#0.00");
			}
			redemptionMoney = resultSet.getString("redemption_money");
			if (StringUtils.isNotEmpty(redemptionMoney)) {
				redemptionMoney = StringUtils.doNumFormat(new BigDecimal(redemptionMoney), "#0.00");
			}
			productName = resultSet.getString("product_name");
			applyPay = resultSet.getString("apply_pay");
			if (StringUtils.isNotEmpty(applyPay)) {
				applyPay = PayMent.getPayMent(applyPay);
			}
			addrProvince = resultSet.getString("addrProvince");
			addrCity = resultSet.getString("addrCity");
			orgName = resultSet.getString("orgName");
			teamManager = resultSet.getString("teamManager");
			userName = resultSet.getString("userName");
			applyBillday = resultSet.getString("apply_billday");
			linitDay = resultSet.getString("linit_day");
			applyAgreementEdition = resultSet.getString("apply_agreement_edition");
			if (StringUtils.isNotEmpty(applyAgreementEdition)) {
				applyAgreementEdition = ContractVesion.contractVesionMap.get(applyAgreementEdition);
			}
			redemptionReceType = resultSet.getString("redemption_rece_type");
			redeemCost = "";
			if (StringUtils.isNotEmpty(redemptionReceType)) {
				BigDecimal cost = RedeemCostType.parseByCode(redemptionReceType).getValue();
				redeemCost = StringUtils.doNumFormat(cost,"0.00%");
			}
			redeemBackDeadline = "";
			if (StringUtils.isNotEmpty(redemptionReceType)) {
				redeemBackDeadline = RedeemBackDeadline.parseByCode(redemptionReceType).getValue();
			}
			redemptionType = resultSet.getString("redemption_type");
			if (StringUtils.isNotEmpty(redemptionType)) {
				redemptionType = RedeemType.getRedeemType(redemptionType);
			}
			
			dataRow = dataSheet.createRow(row);
			dataRow.createCell(0).setCellValue(redemptionTime);
			dataRow.createCell(1).setCellValue(customerName);
			dataRow.createCell(2).setCellValue(customerCode);
			dataRow.createCell(3).setCellValue(lendCode);
			dataRow.createCell(4).setCellValue(applyLendDay);
			dataRow.createCell(5).setCellValue(applyLendMoney);
			dataRow.createCell(6).setCellValue(redemptionMoney);
			dataRow.createCell(7).setCellValue(productName);
			dataRow.createCell(8).setCellValue(applyPay);
			dataRow.createCell(9).setCellValue(addrProvince);
			dataRow.createCell(10).setCellValue(addrCity);
			dataRow.createCell(11).setCellValue(orgName);
			dataRow.createCell(12).setCellValue(teamManager);
			dataRow.createCell(13).setCellValue(userName);
			dataRow.createCell(14).setCellValue(applyBillday);
			dataRow.createCell(15).setCellValue(linitDay);
			dataRow.createCell(16).setCellValue(applyAgreementEdition);
			dataRow.createCell(17).setCellValue(redeemCost);
			dataRow.createCell(18).setCellValue(redeemBackDeadline);
			dataRow.createCell(19).setCellValue(redemptionType);
			row++;
		}

	}

	/**
	 * 设置表头 
	 */
	@Override
	public void wrapperHeader(Sheet dataSheet) {
		Row headerRow = dataSheet.createRow(0);
		headerRow.createCell(0).setCellValue("申请日期");
		headerRow.createCell(1).setCellValue("客户姓名");
		headerRow.createCell(2).setCellValue("客户编号");
		headerRow.createCell(3).setCellValue("出借编号");
		headerRow.createCell(4).setCellValue("计划出借日期");
		headerRow.createCell(5).setCellValue("计划出借金额");
		headerRow.createCell(6).setCellValue("赎回金额");
		headerRow.createCell(7).setCellValue("出借方式");
		headerRow.createCell(8).setCellValue("付款方式");
		headerRow.createCell(9).setCellValue("省份");
		headerRow.createCell(10).setCellValue("城市");
		headerRow.createCell(11).setCellValue("营业部");
		headerRow.createCell(12).setCellValue("团队经理");
		headerRow.createCell(13).setCellValue("理财经理");
		headerRow.createCell(14).setCellValue("账单日");
		headerRow.createCell(15).setCellValue("到期日期");
		headerRow.createCell(16).setCellValue("协议版本");
		headerRow.createCell(17).setCellValue("扣费标准");
		headerRow.createCell(18).setCellValue("回款期限");
		headerRow.createCell(19).setCellValue("赎回类型");
	}

}
