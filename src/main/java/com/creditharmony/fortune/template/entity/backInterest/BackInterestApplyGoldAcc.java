package com.creditharmony.fortune.template.entity.backInterest;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import com.creditharmony.core.deduct.type.CardOrBookType;
import com.creditharmony.fortune.back.interest.util.StaticMethodUtil;
import com.creditharmony.fortune.deduct.common.DeductUtils;

public class BackInterestApplyGoldAcc {

	
	/**
	 * 申请列表和申请确认列表金账户导出 
	 * 2016年4月15日
	 * by 李志伟
	 * @param resultSet
	 * @param dataSheet
	 * @throws SQLException
	 */
	public  static void assembleExcelCell(ResultSet resultSet, Sheet dataSheet) throws Exception
	{
		int row = 1;
		//String trusteeshipNo;
		String accountName;
		String backRealMoney;
		String memo;
		String jProv;
		String jCity;
		String lendCode;
		String applyLendMoney;
		String applyLendDay;
		String orgName;
		String applyBillday;
		String accountCardOrBooklet;
		String applyExpireDay;
		String isInterest;
//-====================================================
		Row dataRow;
		Cell trusteeshipNoCell;
		Cell accountNameCell;
		Cell backRealMoneyCell;
		Cell memoCell;
		Cell provCell;
		Cell cityCell;
		Cell lendCodeCell;
		Cell applyLendMoneyCell;
		Cell applyLendDayCell;
		Cell orgNameCell;
		Cell applyBilldayCell;
		Cell accountCardOrBookletCell;
		Cell applyExpireDayCell;
		Cell isInterestCell;
		
		while (resultSet.next()) {
			
			//trusteeshipNo = resultSet.getString("trusteeship_no");
			accountName = resultSet.getString("account_name");
			backRealMoney = resultSet.getString("back_real_money");
			memo = resultSet.getString("current_billday")+"_"+
					resultSet.getString("product_name")+"回息"+resultSet.getString("backi_id");
			jProv = resultSet.getString("jProvince");
			jCity = resultSet.getString("jCity");
			lendCode = resultSet.getString("lend_code");
			applyLendMoney = resultSet.getString("apply_lend_money");
			applyLendDay = resultSet.getString("apply_lend_day");
			orgName = resultSet.getString("org_name");
			applyBillday = resultSet.getString("current_billday");
			accountCardOrBooklet = resultSet.getString("account_card_or_booklet");
			applyExpireDay = resultSet.getString("apply_expire_day");
			isInterest = resultSet.getString("isInterest");

			//------------------------------设置值----------------------------------
			dataRow = dataSheet.createRow(row);
			
			trusteeshipNoCell = dataRow.createCell(0);
			trusteeshipNoCell.setCellValue(DeductUtils.isNull("***********"));
			
			accountNameCell = dataRow.createCell(1);
			accountNameCell.setCellValue(DeductUtils.isNull(accountName));
			
			backRealMoneyCell = dataRow.createCell(2);
			backRealMoneyCell.setCellValue(DeductUtils.isNull(backRealMoney));
			
			memoCell = dataRow.createCell(3);
			memoCell.setCellValue(DeductUtils.isNull(memo));
			
			provCell = dataRow.createCell(4);
			if(null != jProv && !("").equals(jProv)){
				provCell.setCellValue(StaticMethodUtil.spiltProvince(jProv));
			}else{
				provCell.setCellValue("");
			}
			
			cityCell = dataRow.createCell(5);
			if(null != jCity && !("").equals(jCity)){
				cityCell.setCellValue(StaticMethodUtil.spiltProvince(jCity));
			}else{
				cityCell.setCellValue("");
			}
			
			lendCodeCell = dataRow.createCell(6);
			lendCodeCell.setCellValue(DeductUtils.isNull(lendCode));
			
			applyLendMoneyCell = dataRow.createCell(7);
			applyLendMoneyCell.setCellValue(DeductUtils.isNull(applyLendMoney));
			
			applyLendDayCell = dataRow.createCell(8);
			applyLendDayCell.setCellValue(DeductUtils.isNull(applyLendDay));
			
			orgNameCell = dataRow.createCell(9);
			orgNameCell.setCellValue(DeductUtils.isNull(orgName));
			
			applyBilldayCell = dataRow.createCell(10);
			applyBilldayCell.setCellValue(DeductUtils.isNull(applyBillday));
			
			accountCardOrBookletCell = dataRow.createCell(11);
			if(accountCardOrBooklet != null && !("").equals(accountCardOrBooklet)){
				accountCardOrBookletCell.setCellValue(DeductUtils.isNull(CardOrBookType.parseByCode(accountCardOrBooklet).getName()));
			}else{
				accountCardOrBookletCell.setCellValue("");
			}
			applyExpireDayCell = dataRow.createCell(12);
			applyExpireDayCell.setCellValue(DeductUtils.isNull(applyExpireDay));
			
			isInterestCell = dataRow.createCell(13);
			isInterestCell.setCellValue(DeductUtils.isNull(isInterest));
			row = row + 1;
		}
	}
	
	/**
	 * 待回息申请及待回息申请确认待金账户回息明细
	 * 2016年3月20日
	 * by 李志伟
	 * @param dataSheet
	 * @param sw
	 * @param title
	 */
	public static void wrapperHeader(Sheet dataSheet, SXSSFWorkbook sw, String title) {
		
		Row headerRow = dataSheet.createRow(0);
		CellStyle cellStyle = sw.createCellStyle();
		cellStyle.setFillForegroundColor(HSSFColor.CORNFLOWER_BLUE.index);
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// //居中显示
		String[] tit = title.split(",");
		
		for (int i = 0; i < tit.length; i++) {
			Cell header = headerRow.createCell(i);
			header.setCellValue(tit[i]);
			header.setCellStyle(cellStyle);
		}
	}
}
