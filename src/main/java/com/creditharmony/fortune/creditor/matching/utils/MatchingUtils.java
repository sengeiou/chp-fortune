package com.creditharmony.fortune.creditor.matching.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;


/**
 * 债权匹配工具类
 * @Class Name CommonUtils
 * @author 柳慧
 * @Create In 2015年11月24日
 */
public class MatchingUtils {

	/**
	 * 通过开始日期和期数算出 结束日期
	 * 2016年1月19日
	 * By 柳慧
	 * @param startDate 开始日期
	 * @param num 期数
	 * @return
	 */
	
	public static Date getEndDay(Date startDate,int num){
		Calendar tempDate = Calendar.getInstance();
		tempDate.setTime(startDate);
		tempDate.set(Calendar.MONTH, tempDate.get(Calendar.MONTH)+num);
		return  tempDate.getTime();
	}
	/** 比较两个日期大小
	  * by 柳慧
	 * @param smdate 较小的时间 
	 * @param bdate  较大的时间 
	 * @return 相差天数 
	 * @throws ParseException 
	  */    
	public static boolean daysBetween(Date smdate,Date bdate) throws ParseException{    
	    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
	    smdate=sdf.parse(sdf.format(smdate));  
	    bdate=sdf.parse(sdf.format(bdate));  
	    Calendar cal = Calendar.getInstance();    
	    cal.setTime(smdate);    
	    long time1 = cal.getTimeInMillis();                 
	    cal.setTime(bdate);    
	    long time2 = cal.getTimeInMillis();  
	    if(time1-time2>=0){
	    	 return true;
	    }else{
	    	 return false;
	    }
	   
	}
	
	/**
	 * Integer类型多选参数封装
	 * 2016年5月2日
	 * By 朱杰
	 * @param condition
	 * @param split
	 * @return
	 */
	public static List<Integer> mulityIntOptionForSearch(String condition,String split){
		List<Integer> list = new ArrayList<Integer>();
		if(StringUtils.isNotEmpty(condition)){
			String [] elem =  condition.split(split);
			for(int i = 0 ; i<elem.length; i++){
				list.add(Integer.valueOf(elem[i]));
			}
		}
		return list;		
	}
	
	/**
	 * String类型多选参数封装
	 * 2016年5月2日
	 * By 朱杰
	 * @param condition
	 * @param split
	 * @return
	 */
	public static List<String> mulityStringOptionForSearch(String condition,String split){
		List<String> list = new ArrayList<String>();
		if(StringUtils.isNotEmpty(condition)){
			String [] elem =  condition.split(split);
			for(int i = 0 ; i<elem.length; i++){
				list.add(elem[i]);
			}
		}
		return list;		
	}
}
