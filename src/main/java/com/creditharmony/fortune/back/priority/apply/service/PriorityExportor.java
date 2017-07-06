package com.creditharmony.fortune.back.priority.apply.service;

import java.math.BigDecimal;
import java.sql.ResultSet;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.fortune.type.PayMent;
import com.creditharmony.core.fortune.type.PriorityBackState;
import com.creditharmony.fortune.back.priority.utils.PriExportor;
import com.creditharmony.fortune.redemption.constant.RedeemConstant;

/**
 * 优先回款
 * @Class Name PriorityExportor
 * @Create In 2016年4月16日
 */
public class PriorityExportor extends PriExportor {

	public PriorityExportor(String filename) {
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
		String customerCode;
		String lendCode;
		String applyLendDay;
		String applyBillDay = null;
		String applyExpireDay;
		String applyLendMoney;
		String productName;
		String manager;
		String applyPay;
		String account;
		String orgName;
		String fortuneCentre;
		String checkTime;
		String priorityBackState;
		
		Row dataRow;
		while (resultSet.next()) {
			customerName = resultSet.getString("customerName");
			// 屏蔽客户姓名/手机号/身份证号
			customerName = "***";
			lendCode = resultSet.getString("lendCode");
			customerCode = resultSet.getString("customerCode");
			applyLendDay = resultSet.getString("applyLendDay");
			applyBillDay = resultSet.getString("applyBillDay");
			
			applyExpireDay = resultSet.getString("applyExpireDay");
			applyLendMoney = resultSet.getString("applyLendMoney");
			if (StringUtils.isNotEmpty(applyLendMoney)) {
				applyLendMoney = StringUtils.doNumFormat(new BigDecimal(applyLendMoney), 
						RedeemConstant.MONEY_FORMAT);
			}
			productName = resultSet.getString("productName");
			manager = resultSet.getString("manager");
			applyPay = resultSet.getString("applyPay");
			if (StringUtils.isNotEmpty(applyPay)) {
				applyPay = PayMent.payMentMap.get(applyPay);
			}
			account = resultSet.getString("accountAddrprovince")+"|"+resultSet.getString("accountAddrcity");
			orgName = resultSet.getString("orgName");
			fortuneCentre = resultSet.getString("fortuneCentre");
			//fortuneCentre = resultSet.getString("");
			checkTime = resultSet.getString("checkTime");
			priorityBackState = resultSet.getString("priorityBackState");
			if(StringUtils.isNotEmpty(priorityBackState)){
				priorityBackState = PriorityBackState.getPrioritybackstateMap(priorityBackState);
			}
			
			dataRow = dataSheet.createRow(row);
			dataRow.createCell(0).setCellValue(customerName);
			dataRow.createCell(1).setCellValue(customerCode);
			dataRow.createCell(2).setCellValue(lendCode);
			dataRow.createCell(3).setCellValue(applyLendDay);
			dataRow.createCell(4).setCellValue(applyBillDay);
			dataRow.createCell(5).setCellValue(applyExpireDay);
			dataRow.createCell(6).setCellValue(applyLendMoney);
			dataRow.createCell(7).setCellValue(productName);
			dataRow.createCell(8).setCellValue(manager);
			dataRow.createCell(9).setCellValue(applyPay);
			dataRow.createCell(10).setCellValue(account);
			dataRow.createCell(11).setCellValue(orgName);
			dataRow.createCell(12).setCellValue(fortuneCentre);
			dataRow.createCell(13).setCellValue(checkTime);
			dataRow.createCell(14).setCellValue(priorityBackState);
			
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
		headerRow.createCell(1).setCellValue("客户编号");
		headerRow.createCell(2).setCellValue("出借编号");
		headerRow.createCell(3).setCellValue("计划出借日期");
		headerRow.createCell(4).setCellValue("账单日");
		headerRow.createCell(5).setCellValue("到期日");
		headerRow.createCell(6).setCellValue("计划出借金额");
		headerRow.createCell(7).setCellValue("出借产品");
		headerRow.createCell(8).setCellValue("客户经理");
		headerRow.createCell(9).setCellValue("付款方式");
		headerRow.createCell(10).setCellValue("省份|城市");
		headerRow.createCell(11).setCellValue("营业部");
		headerRow.createCell(12).setCellValue("财富中心");
		headerRow.createCell(13).setCellValue("审批日期");
		headerRow.createCell(14).setCellValue("状态");

	}

}
