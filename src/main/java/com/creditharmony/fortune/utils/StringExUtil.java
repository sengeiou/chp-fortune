package com.creditharmony.fortune.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * String工具类扩展
 * 
 * @Class Name StringExUtil
 * @author zhujie
 * @Create In 2015年11月27日
 */
public class StringExUtil {
	
	/**
	 * 返回一个值是否在以逗号分割的文字中存在
	 * 
	 * 2015年12月28日
	 * By 朱杰
	 * @param values 被分割的文字串
	 * @param item 判断一致的值
	 * @return
	 */
	public static boolean contains(String values, String item) {
		return StringExUtil.contains(values, item, ",");
	}

	/**
	 * 返回一个值是否在文字分割中存在
	 * 
	 * 2015年12月28日
	 * By 朱杰
	 * @param values 被分割的文字串
	 * @param item 判断一致的值
	 * @param split 分割文字
	 * @return
	 */
	public static boolean contains(String values, String item, String split) {
		if(StringUtils.isNotEmpty(values)){
			String[] splitStrs = StringUtils.split(values, split);
			for (String str : splitStrs) {
				if(str.equals(item)){
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * 判断一个Object类型的String是否为空
	 * 2016年1月31日
	 * By 朱杰
	 * @param obj
	 * @return
	 */
	public static String getTrimString(Object obj){
		if(obj == null || StringUtils.isBlank((String)obj)){
			return "";
		}
		return (String)obj;
	}
	
	/**
	 * 去除最后字符（串）
	 * 2016年4月14日
	 * By 朱杰
	 * @param str
	 * @param trimStr
	 * @return
	 */
	public static String trimLast(String str, String trimStr){
		if (str.endsWith(trimStr)) {
			str = str.substring(0, str.length() - trimStr.length());
		}
		return str;
	}
}
