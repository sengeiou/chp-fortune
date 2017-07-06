/**
 *
 */
package com.creditharmony.fortune.creditor.matching.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * 批处理泛用公共方法类
 */
public class ComUtils {
	
	/**
	 * 获取当前系统时间
	 * @param patten yyyy/MM/dd 或  "yyyy-MM-dd HH:mm:ss"
	 * @return String
	 */
	public static String nowTime(String patten) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(new Date().getTime());
		SimpleDateFormat dateFormat = new SimpleDateFormat(patten);
		return dateFormat.format(c.getTime());
	}

}
