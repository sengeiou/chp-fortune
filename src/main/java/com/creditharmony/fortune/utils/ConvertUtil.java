package com.creditharmony.fortune.utils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * 转换工具类
 * 
 * @Class Name AttachmentUtil
 * @author 朱杰
 * @Create In 2016年1月31日
 */
public class ConvertUtil {
	
	/**
	 * 对象转换成map
	 * 2016年1月25日
	 * By 柳慧
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public static Map<String, Object> ObjectToMap(Object obj) {
		if(obj == null){    
            return null;    
        }   
        Map<String, Object> map = new HashMap<String, Object>();    
        Field[] declaredFields = obj.getClass().getDeclaredFields();  
        try {
	        for (Field field : declaredFields) {    
	            field.setAccessible(true);  
	            map.put(field.getName(), field.get(obj));
			}
        } catch (Exception e) {
        	e.printStackTrace();
        }
        return map; 
	}
}
