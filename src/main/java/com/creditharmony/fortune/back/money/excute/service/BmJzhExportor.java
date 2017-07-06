package com.creditharmony.fortune.back.money.excute.service;

import java.math.BigDecimal;
import java.sql.ResultSet;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.fortune.type.BackType;
import com.creditharmony.fortune.back.money.constant.BmConstant;
import com.creditharmony.fortune.back.money.utils.BmExportor;
import com.creditharmony.fortune.redemption.constant.RedeemConstant;
import com.creditharmony.fortune.utils.AppPropertiesUtil;

public class BmJzhExportor extends BmExportor {

	public BmJzhExportor(String filename) {
		super(filename);
	}

	/**
	 * 填充单元格数据
	 */
	@Override
	public void assembleExcelCell(ResultSet resultSet, Sheet dataSheet)
			throws Exception {
		int row = 1;
		String serialNum;
		String outLoginName;
		String outChineseName;
		String outFreeze;
		String trusteeshipNo;
		String customerName;
		String inFreeze;
		String backActualbackMoney;
		String suceessMoney;
		String lendCode;
		String backMoneyType;
		String remark;
		String preContractNo;
		
		Row dataRow;
		while (resultSet.next()) {
			serialNum = row + "";
			outLoginName = AppPropertiesUtil.getString("jzh_fk_account");
			outChineseName = AppPropertiesUtil.getString("jzh_fk_name");
			outFreeze = BmConstant.OUT_FREEZE;
			trusteeshipNo = resultSet.getString("trusteeship_no");
			customerName = resultSet.getString("customer_name");
			inFreeze = BmConstant.IN_FREEZE;
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
			lendCode = resultSet.getString("lend_code");
			backMoneyType = resultSet.getString("back_money_type");
			if (StringUtils.isNotEmpty(backMoneyType)) {
				backMoneyType = BackType.backTypeMap.get(backMoneyType);
			}
			remark = lendCode + "_" + backMoneyType;
			preContractNo = "";
			
			dataRow = dataSheet.createRow(row);
			dataRow.createCell(0).setCellValue(serialNum);
			dataRow.createCell(1).setCellValue(outLoginName);
			dataRow.createCell(2).setCellValue(outChineseName);
			dataRow.createCell(3).setCellValue(outFreeze);
			dataRow.createCell(4).setCellValue(trusteeshipNo);
			dataRow.createCell(5).setCellValue(customerName);
			dataRow.createCell(6).setCellValue(inFreeze);
			dataRow.createCell(7).setCellValue(backActualbackMoney);
			dataRow.createCell(8).setCellValue(remark);
			dataRow.createCell(9).setCellValue(preContractNo);
			row ++;
		}

	}

	/**
	 * 设置表头
	 */
	@Override
	public void wrapperHeader(Sheet dataSheet) {
		Row headerRow = dataSheet.createRow(0);
		headerRow.createCell(0).setCellValue("序号");
		headerRow.createCell(1).setCellValue("付款方登录名");
		headerRow.createCell(2).setCellValue("付款方中文名称");
		headerRow.createCell(3).setCellValue("付款资金来自冻结");
		headerRow.createCell(4).setCellValue("收款方登录名");
		headerRow.createCell(5).setCellValue("收款方中文名称");
		headerRow.createCell(6).setCellValue("收款后立即冻结");
		headerRow.createCell(7).setCellValue("交易金额");
		headerRow.createCell(8).setCellValue("备注信息"); // 备注 = 出借编号 + "_" + 回款类型
		headerRow.createCell(9).setCellValue("预授权合同号");
	}

}
