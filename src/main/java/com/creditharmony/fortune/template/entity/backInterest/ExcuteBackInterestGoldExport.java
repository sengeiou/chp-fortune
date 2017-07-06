package com.creditharmony.fortune.template.entity.backInterest;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.creditharmony.fortune.back.interest.contants.GlobalConstant;
import com.creditharmony.fortune.back.interest.util.StaticMethodUtil;
import com.creditharmony.fortune.back.money.constant.BmConstant;
import com.creditharmony.fortune.deduct.common.DeductUtils;
import com.creditharmony.fortune.utils.AppPropertiesUtil;

/**
 * 待回息金账户导出
 * @Class Name ExcuteBackInterestGoldExport 
 * @author 李志伟
 * @Create In 2016年4月18日
 */
public class ExcuteBackInterestGoldExport {
	
	/**
	 * 待回息金账户导出
	 * 2016年4月15日
	 * by 李志伟
	 * @param resultSet
	 * @param dataSheet
	 * @throws SQLException
	 */
	public static void assembleExcelCell(ResultSet resultSet, Sheet dataSheet) throws Exception {
		
		int row = 1;
		
		String trusteeshipNo;
		String accountName;
		String payLoadName = AppPropertiesUtil.getString("jzh_fk_account");
		String chnName = AppPropertiesUtil.getString("jzh_fk_name");
		String payMoneyFrom = BmConstant.OUT_FREEZE;// 付款资金来自冻结
		String receiptFrozen = BmConstant.IN_FREEZE;// 收款后立即冻结
		BigDecimal backRealMoney;
		String lendCode;
		String currentBillday;
		BigDecimal successMoney;
		
		//-====================================================
		
		Row dataRow;
		Cell noCell;
		Cell payLoadNameCell;// 付款方登录名
		Cell payChinaNameCell;// 付款方中文名称
		Cell payMoneyFromCell;// 付款资金来自冻结
		Cell trusteeshipNoCell;// 收款方登录名
		Cell accountNameCell;
		Cell receiptFrozenCell;// 收款后立即冻结
		Cell backRealMoneyCell;// 回息金额
		Cell memoCell;// 备注
		Cell contractNoCell;// 预授权合同号
		
		int i = 1;
		while (resultSet.next()) {
			
			trusteeshipNo = resultSet.getString("trusteeship_no");
			accountName = resultSet.getString("customer_realname");
			backRealMoney = resultSet.getBigDecimal("back_real_money");
			Date dt = resultSet.getDate("current_billday");
			currentBillday = StaticMethodUtil.parseDate(dt, GlobalConstant.YYYY_MM_DD);
			lendCode = resultSet.getString("lend_code");
			
			successMoney = resultSet.getBigDecimal("success_money");
			
			backRealMoney = backRealMoney==null? BigDecimal.ZERO : backRealMoney;
			successMoney = resultSet.getBigDecimal("success_money");
			successMoney = successMoney==null? BigDecimal.ZERO : successMoney;
			backRealMoney = backRealMoney.subtract(successMoney);
			
			int to = backRealMoney.compareTo(BigDecimal.ZERO);
			
			if(to==1){
			
				//------------------------------设置值----------------------------------
				dataRow = dataSheet.createRow(row);
				
				noCell = dataRow.createCell(0);
				noCell.setCellValue(i+"");
				
				payLoadNameCell = dataRow.createCell(1);
				payLoadNameCell.setCellValue(payLoadName);
				
				payChinaNameCell = dataRow.createCell(2);
				payChinaNameCell.setCellValue(chnName);
				
				payMoneyFromCell = dataRow.createCell(3);
				payMoneyFromCell.setCellValue(payMoneyFrom);
				
				trusteeshipNoCell = dataRow.createCell(4);
				trusteeshipNoCell.setCellValue(DeductUtils.isNull(trusteeshipNo));
				
				accountNameCell = dataRow.createCell(5);
				accountNameCell.setCellValue(DeductUtils.isNull(accountName));
				
				receiptFrozenCell = dataRow.createCell(6);
				receiptFrozenCell.setCellValue(receiptFrozen);
				
				backRealMoneyCell = dataRow.createCell(7);
				backRealMoneyCell.setCellValue(DeductUtils.isNull(backRealMoney.toString()));
				
				memoCell = dataRow.createCell(8);
				memoCell.setCellValue(lendCode+"回息"+currentBillday);
	
				contractNoCell = dataRow.createCell(9);
				contractNoCell.setCellValue("");
				
				row = row + 1;
				i++;
			}else{// 如果回息金额为0或者金额为负数，要进行提醒
				dataRow = dataSheet.createRow(row);
				
				noCell = dataRow.createCell(0);
				noCell.setCellValue(i+"");
				
				Cell ce = dataRow.createCell(1);
				ce.setCellValue(GlobalConstant.ERROR);
				row = row + 1;
				i++;
			}
		}
	}
}