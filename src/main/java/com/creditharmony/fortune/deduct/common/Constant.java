package com.creditharmony.fortune.deduct.common;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import com.creditharmony.core.fortune.type.DeductPlat;

/**
 * 划扣申请Constant常量
 * @Class Name Constant
 * @author 韩龙
 * @Create In 2015年11月27日
 */
public class Constant {
	
	/**成功*/
	public final static String SUCCESS="ok";
	
	/**失败*/
	public final static String FAIL="ng";
	
	/**配置文件名*/
	private final static String FILE_NAME="/email-conf.xml.properties";
	
	/**模块标识-->划扣审批*/
	public final static String APPLY_OR_APPROVE_FALG="approve";
	
	/**模块标识-->划扣结算与划扣失败*/
	public final static String DEDUCT_BALANCE_OR_FAIL_FALG="fail";
	
	/**读取配置文件*/
	public static Map<String, String> getProperties = new HashMap<String, String>();
	
	/**导出文件笔数*/
	public static Map<String,Integer> splitNum = new HashMap<String,Integer>();
	
	/**过滤数据sql*/
	public static String filterSql = "and (a.dict_apply_end_state <> '2' or a.dict_apply_end_state is null)";
	
	/**
	 * 读取配置文件
	 */
	static{
		// 初始化配置文件
		Properties prop = new Properties();
		try {
			prop.load(Constant.class.getResourceAsStream(FILE_NAME));
			// 返回Properties中包含的key-value的Set视图  
	        Set<Entry<Object, Object>> set = prop.entrySet();  
	        // 返回在此Set中的元素上进行迭代的迭代器  
	        Iterator<Map.Entry<Object, Object>> it = set.iterator();  
	        String key = null, value = null;  
	        // 循环取出key-value  
	        while (it.hasNext()) {  
	            Entry<Object, Object> entry = it.next();  
	            key = String.valueOf(entry.getKey());  
	            value = String.valueOf(entry.getValue());  
	            key = key == null ? key : key.trim();  
	            value = value == null ? value : value.trim();  
	            // 将key-value放入map中  
	            getProperties.put(key, value);  
	        }  
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// 初始化
//		splitNum.put(DeductPlat.FYPT.value, 60000);
//		splitNum.put(DeductPlat.HYLPT.value, 100000);
//		splitNum.put(DeductPlat.ZJPT.value, 999);
//		splitNum.put(DeductPlat.TL.value, 500);
		
		splitNum.put(DeductPlat.FYPT.value, 100);
		splitNum.put(DeductPlat.HYLPT.value, 100);
		splitNum.put(DeductPlat.ZJPT.value, 100);
		splitNum.put(DeductPlat.TL.value, 100);
	}
	
//	public static void main(String[] args) {
//		System.out.println(Constant.class.getResourceAsStream(""));
//		System.out.println(EMAIL_TEMPEMPLT_ID_VALUE);
//	}
//	
	/**提醒页面标识*/
	public final static String REMIND_FLAG="remindFlag";
}
