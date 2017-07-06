package com.creditharmony.fortune.template.entity.backInterest;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.creditharmony.common.util.StringUtils;
import com.creditharmony.fortune.back.interest.contants.GlobalConstant;
import com.creditharmony.fortune.back.interest.util.StaticMethodUtil;
import com.creditharmony.fortune.deduct.common.DeductUtils;

/**
 * 中金导出
 * @Class Name ChinaFinanceExport 
 * @author 李志伟
 * @Create In 2016年4月18日
 */
public class ChinaFinanceExport {
	
	/**
	 * 中金导出Txt
	 * 2016年4月21日
	 * by 李志伟
	 * @param resultSet
	 * @param dataSheet
	 * @throws SQLException
	 */
	public static void exportTxt(ResultSet resultSet, StringBuffer sb)
			throws SQLException {
		
		String accountBank;
		String accountName;
		String accountNo;
		String accountBranch;
		String province;
		String city;
		//String backiId;
//		String dictCustomerCertType; // 证件类型
//		String customerCertNum; 
//		String customerMobilephone;
//		String customerEmail;
		String lendCode;
		String currentBillday;
		
		
		
		int i = 1;
		while (resultSet.next()) {
			
			accountBank = resultSet.getString("account_bank");
			accountName = resultSet.getString("account_name");
			accountNo = resultSet.getString("account_no");
			accountBranch = resultSet.getString("account_branch");
			province = resultSet.getString("province");
			city = resultSet.getString("city");
			//backiId = resultSet.getString("backi_id");
			lendCode = resultSet.getString("lend_code");
			currentBillday = resultSet.getString("current_billday");
//			customerMobilephone = resultSet.getString("customer_mobilephone");
//			dictCustomerCertType = resultSet.getString("dict_customer_cert_type");
//			customerCertNum = resultSet.getString("customer_cert_num"); 
//			customerEmail = resultSet.getString("customer_email");
			
			
			// ----------------------------------
			BigDecimal bi = resultSet.getBigDecimal("back_real_money");
			bi = bi==null?new BigDecimal(0):bi;
			
			BigDecimal su = resultSet.getBigDecimal("success_money");
			su = su==null?new BigDecimal(0):su;
			
			BigDecimal bd = bi.subtract(su);
			bd = bd.multiply(GlobalConstant.ONE);
			bd = bd.setScale(0, BigDecimal.ROUND_HALF_UP);
			// ----------------------------------
			
			int to = bd.compareTo(BigDecimal.ZERO);
			if(to == 1){
				sb.append(StringUtils.doNumFormat(i, "00000")).append("|");
				sb.append(bd).append("|");
				if(accountBank != null && !("").equals(accountBank)){
					sb.append(accountBank).append("|");
				}else{
					sb.append("-----").append("|");
				}
				sb.append("11").append("|");
				sb.append(StaticMethodUtil.isNull(accountName)).append("|");
				sb.append(StaticMethodUtil.isNull(accountNo)).append("|");
				sb.append(StaticMethodUtil.isNull(accountBranch)).append("|");
				sb.append(StaticMethodUtil.isNull(province)).append("|");
				sb.append(StaticMethodUtil.isNull(city)).append("|");
				// 出借编号+回息+账单日+唯一标识
				sb.append(DeductUtils.isNull(lendCode)+"回息"+DeductUtils.isNull(currentBillday));
				sb.append(" |");
				sb.append(" |");
				sb.append(" | ").append("\r\n");
	//			sb.append(customerEmail);
	//			sb.append(customerMobilephone);
	//			sb.append(customerCertNum);
	//			sb.append(dictCustomerCertType);
				
				++i;
			}else{
				sb.append(StringUtils.doNumFormat(i, "00000")).append("|");
				sb.append(GlobalConstant.ERROR).append("|").append("|").append("|").append("|")
				.append("|").append("|").append("|").append("|").append("|")
				.append("|").append("|").append("\r\n");
				++i;
			}
		}
	}
	
}
