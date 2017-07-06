package com.creditharmony.fortune.back.money.approve.service;

import java.math.BigDecimal;
import java.sql.ResultSet;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.fortune.type.BackType;
import com.creditharmony.core.fortune.type.RedeemBackDeadline;
import com.creditharmony.fortune.back.money.utils.BmExportor;
import com.creditharmony.fortune.redemption.constant.RedeemConstant;

/**
 * 回款审批列表，金账户导出
 * @Class Name BmApprovalJzhExportor
 * @author 陈广鹏
 * @Create In 2016年4月16日
 */
public class BmApprovalJzhExportor extends BmExportor {

	public BmApprovalJzhExportor(String filename) {
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
		String trusteeshipNo;
		String city;
		String backActualbackMoney;
		String orgName;
		String backMoneyType;
		String remark;
		String redemptionReceType;
		
		Row dataRow;
		while (resultSet.next()) {
			customerName = resultSet.getString("customer_name");
			lendCode = resultSet.getString("lend_code");
			applyLendDay = resultSet.getString("apply_lend_day");
			applyLendMoney = resultSet.getString("apply_lend_money");
			if (StringUtils.isNotEmpty(applyLendMoney)) {
				applyLendMoney = StringUtils.doNumFormat(new BigDecimal(applyLendMoney), 
						RedeemConstant.MONEY_FORMAT);
			}
			productName = resultSet.getString("product_name");
			finalLinitDate = resultSet.getString("final_linit_date");
			trusteeshipNo = resultSet.getString("trusteeship_no");
			city = resultSet.getString("city");
			backActualbackMoney = resultSet.getString("back_actualback_money");
			if (StringUtils.isNotEmpty(backActualbackMoney)) {
				backActualbackMoney = StringUtils.doNumFormat(new BigDecimal(backActualbackMoney),
						RedeemConstant.MONEY_FORMAT);
			}
			orgName = resultSet.getString("orgName");
			backMoneyType = resultSet.getString("back_money_type");
			if (StringUtils.isNotEmpty(backMoneyType)) {
				backMoneyType = BackType.backTypeMap.get(backMoneyType);
			}
			remark = "";
			redemptionReceType = resultSet.getString("redemption_rece_type");
			if (StringUtils.isNotEmpty(redemptionReceType) 
					&& BackType.TQSH.value.equals(resultSet.getString("back_money_type"))) {
				redemptionReceType = RedeemBackDeadline.parseByCode(redemptionReceType).getValue();
			}
			
			dataRow = dataSheet.createRow(row);
			dataRow.createCell(0).setCellValue(customerName);
			dataRow.createCell(1).setCellValue(lendCode);
			dataRow.createCell(2).setCellValue(applyLendDay);
			dataRow.createCell(3).setCellValue(applyLendMoney);
			dataRow.createCell(4).setCellValue(productName);
			dataRow.createCell(5).setCellValue(finalLinitDate);
			dataRow.createCell(6).setCellValue(trusteeshipNo);
			dataRow.createCell(7).setCellValue(city);
			dataRow.createCell(8).setCellValue(backActualbackMoney);
			dataRow.createCell(9).setCellValue(orgName);
			dataRow.createCell(10).setCellValue(backMoneyType);
			dataRow.createCell(11).setCellValue(remark);
			dataRow.createCell(12).setCellValue(redemptionReceType);
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
		headerRow.createCell(5).setCellValue("产品到期日期");
		headerRow.createCell(6).setCellValue("金账户账号");
		headerRow.createCell(7).setCellValue("客户所在城市");
		headerRow.createCell(8).setCellValue("实际回款金额");
		headerRow.createCell(9).setCellValue("营业部");
		headerRow.createCell(10).setCellValue("回款类型");
		headerRow.createCell(11).setCellValue("备注");
		headerRow.createCell(12).setCellValue("回款期限");
	}

}
