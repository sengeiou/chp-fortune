package com.creditharmony.fortune.app.webservice.development.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 工具类,需要的同事在这个类里写工具方法
 * @author 王俊杰
 * @date 2016-5-10
 */
public class Util {

	public static List<Map<String,Object>> keyToUpperCase(List<Map<String,Object>> list){ 
		if(list == null ){ 
		return list; 
		} 
		List<Map<String,Object>> result = new ArrayList<Map<String,Object>>(); 
		for(Map<String,Object> map:list){ 
		Map<String,Object> rMap = new HashMap<String,Object>(); 
		for(Entry<String, Object> entry : map.entrySet()){ 
		rMap.put(entry.getKey().toUpperCase(), entry.getValue()); 
		} 
		result.add(rMap); 
		} 
		return result; 
	}
}
