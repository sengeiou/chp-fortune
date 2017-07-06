package com.creditharmony.fortune.customer.workflow.util;

import java.util.Calendar;
import java.util.Date;

import com.creditharmony.common.util.DateUtils;

/**
 * 账单日工具类
 * 
 * @Class Name BillDateUtil
 * @author 孙凯文
 * @Create In 2016年1月7日
 */
public class BillDateUtil {
	/**
	 * 根据出借日期获取账单日 2016年1月7日
	 * 
	 * @author 孙凯文
	 * @param date
	 * @return
	 */
	public static Integer getBillDate(Date date) {
		String day = DateUtils.formatDate(date, "dd");
		Integer day1 = Integer.parseInt(day);
		if (isBetween(day1, 5, 7)) {
			return 4;
		} else if (isBetween(day1, 8, 11)) {
			return 7;
		} else if (isBetween(day1, 12, 15)) {
			return 11;
		} else if (isBetween(day1, 16, 19)) {
			return 15;
		} else if (isBetween(day1, 20, 23)) {
			return 19;
		} else if (isBetween(day1, 24, 26)) {
			return 23;
		} else if (isBetween(day1, 27, 30)) {
			return 26;
		} else {
			return 30;
		}
	}

	private static boolean isBetween(int source, int start, int end) {
		return (source >= start) && (source <= end);
	}

	/**
	 * 获取到期日期 2016年1月25日
	 * 
	 * @author 孙凯文
	 * @param date
	 * @param num
	 * @return
	 */
	public static Date getExpireDate(Date date, int num) {
		Calendar tempDate = Calendar.getInstance();
		tempDate.setTime(date);
		tempDate.add(Calendar.MONTH, num);
		tempDate.add(Calendar.DAY_OF_YEAR, - 1);
		return tempDate.getTime();
	}
	
	public static Date getExpireDateOfYMY(Date date, int days) {
		Calendar tempDate = Calendar.getInstance();
		tempDate.setTime(date);
		tempDate.add(Calendar.DAY_OF_YEAR, days);
		return tempDate.getTime();
	}

	/**
	 * 2016年2月20日
	 * @author 孙凯文
	 * @param date
	 * @param months
	 * @return
	 */
	public static Date addMonth(Date date, int months) {
		Calendar tempDate = Calendar.getInstance();
		tempDate.setTime(date);
		tempDate.add(Calendar.MONTH, months);
		return tempDate.getTime();
	}
	
	/**
	 * 获取下一个自然日 2016年2月18日 By 陈广鹏
	 * 
	 * @param date
	 * @return
	 */
	public static Date getNextDate(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DAY_OF_YEAR, 1);
		return c.getTime();
	}
	
	public static Date addDays(Date d, int num){
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		c.add(Calendar.DAY_OF_YEAR, num);
		return c.getTime();
		
	}
	
}
