package com.creditharmony.fortune.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.users.util.UserUtils;

/**
 * 格式化的utils
 * @Class Name LoanIdcardUtils
 * @author 郭才林
 * @Create In 2015年12月29日
 */
public class FormatUtils {
	
	/**
	 * 格式化日期
	 * 2016年3月19日
	 * By 郭才林
	 * @param date
	 * @param ft
	 * @return
	 */
	public static String getFormatDate(Date date,String ft){ 
		if(date==null){
			return "";
		}
        SimpleDateFormat sdf = new SimpleDateFormat(ft);
		return sdf.format(date);
	}
	
	/**
	 * 格式化货币(逗号,2位)
	 * 2016年1月22日
	 * By 郭才林
	 * @param number
	 * @return
	 */
	public static String getFormatNumber(BigDecimal number){
		NumberFormat format = NumberFormat.getCurrencyInstance();  
		return format.format(number);
	}
	
	/**
	 * 更据表达式格式化(百分数,货币,小数...)
	 * 2016年1月22日
	 * By 郭才林
	 * @param number
	 * @return
	 */
	public static String getFormatNumber(BigDecimal number,String f){
		DecimalFormat d = new DecimalFormat(f);
		return d.format(number);
	}
	
	/**
	 * 脱敏操作
	 * 2016年6月19日
	 * By 朱杰
	 * @param managerCode 数据所属理财经理
	 * @param value 值
	 * @return
	 */
	public static String valueDesensitize(String managerCode,String value){
		if(StringUtils.isNotEmpty(managerCode) && UserUtils.getUserId().equals(managerCode)){
			// 数据所属理财经理不为空，且是当前登录人,返回不脱敏信息
			return value;
		}
		// 返回脱敏信息
		String rtn = "";
		for (int i=0 ; i < String.valueOf(value).length() ; i++) {
			rtn += "*";
		}
		return rtn;
	}
	
}
