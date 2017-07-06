package com.creditharmony.fortune.back.money.confirm.service;

import java.math.BigDecimal;
import java.sql.ResultSet;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.fortune.type.BackMoneyPlat;
import com.creditharmony.core.fortune.type.BackState;
import com.creditharmony.core.fortune.type.BackType;
import com.creditharmony.core.fortune.type.WorkingState;
import com.creditharmony.fortune.back.money.utils.BmExportor;
import com.creditharmony.fortune.redemption.constant.RedeemConstant;

/**
 * 回款确认列表导出
 * @Class Name BmConfirmExportor
 * @author 陈广鹏
 * @Create In 2016年4月16日
 */
public class BmConfirmExportor extends BmExportor {

	public BmConfirmExportor(String filename) {
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
		String productName;
		String backMoney;
		String backActualbackMoney;
		String platformId;
		String dictBackStatus;
		String finalLinitDate;
		String backMoneyDay;
		String backMoneyType;
		String workingState;
		
		Row dataRow;
		while (resultSet.next()) {
			
			customerName = resultSet.getString("customer_name");
			// 屏蔽客户姓名/手机号/身份证号
			customerName = "***";
			lendCode = resultSet.getString("lend_code");
			productName = resultSet.getString("product_name");
			backMoney = resultSet.getString("back_money");
			if (StringUtils.isNotEmpty(backMoney)) {
				backMoney = StringUtils.doNumFormat(new BigDecimal(backMoney),
						RedeemConstant.MONEY_FORMAT);
			}
			backActualbackMoney = resultSet.getString("back_actualback_money");
			if (StringUtils.isNotEmpty(backActualbackMoney)) {
				backActualbackMoney = StringUtils.doNumFormat(new BigDecimal(backActualbackMoney),
						RedeemConstant.MONEY_FORMAT);
			}
			platformId = resultSet.getString("platform_id");
			if (StringUtils.isNotEmpty(platformId)) {
				platformId = BackMoneyPlat.backMoneyPlatMap.get(platformId);
			}
			dictBackStatus = resultSet.getString("dict_back_status");
			if (StringUtils.isNotEmpty(dictBackStatus)) {
				dictBackStatus = BackState.backStateMap.get(dictBackStatus);
			}
			finalLinitDate = resultSet.getString("final_linit_date");
			backMoneyDay = resultSet.getString("back_money_day");
			backMoneyType = resultSet.getString("back_money_type");
			if (StringUtils.isNotEmpty(backMoneyType)) {
				backMoneyType = BackType.backTypeMap.get(backMoneyType);
			}
			workingState = resultSet.getString("working_state");
			if (StringUtils.isNotEmpty(workingState)) {
				workingState = WorkingState.workingStateMap.get(workingState);
			}
			
			dataRow = dataSheet.createRow(row);
			dataRow.createCell(0).setCellValue(customerName);
			dataRow.createCell(1).setCellValue(lendCode);
			dataRow.createCell(2).setCellValue(productName);
			dataRow.createCell(3).setCellValue(backMoney);
			dataRow.createCell(4).setCellValue(backActualbackMoney);
			dataRow.createCell(5).setCellValue(platformId);
			dataRow.createCell(6).setCellValue(dictBackStatus);
			dataRow.createCell(7).setCellValue(finalLinitDate);
			dataRow.createCell(8).setCellValue(backMoneyDay);
			dataRow.createCell(9).setCellValue(backMoneyType);
			dataRow.createCell(10).setCellValue(workingState);
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
		headerRow.createCell(2).setCellValue("出借方式");
		headerRow.createCell(3).setCellValue("应回款金额");
		headerRow.createCell(4).setCellValue("实际回款金额");
		headerRow.createCell(5).setCellValue("回款平台");
		headerRow.createCell(6).setCellValue("回款状态");
		headerRow.createCell(7).setCellValue("到期日期");
		headerRow.createCell(8).setCellValue("回款日期");
		headerRow.createCell(9).setCellValue("回款类型");
		headerRow.createCell(10).setCellValue("在职状态");
	}

}
