package com.creditharmony.fortune.app.webservice.ocr.utils;

import com.alibaba.fastjson.JSONObject;
import com.creditharmony.core.mapper.JsonMapper;


public class Utils {
	
	public static String returnMessage(boolean result, String memo) {
		JSONObject obj = new JSONObject();
		obj.put("result", result);
		obj.put("memo", memo);
		return obj.toString();
	}
	
	/**
	 * 返回信息并做日志 通用方法
	 */
	public static String returnMessage(boolean result, String memo,
			String objkey1, Object obj1) {
		
		JSONObject obj = new JSONObject();
		obj.put("result", result);
		obj.put("memo", memo);
		obj.put(objkey1, JsonMapper.nonDefaultMapper().toJson(obj1));
		return obj.toString();
	}
	
	public static String returnMessage(boolean result, String memo,String error) {
		JSONObject obj = new JSONObject();
		obj.put("result", result);
		obj.put("memo", memo);
		obj.put("error", error);
		return obj.toString();
	}
	
	/**
	 * 判断一堆字符串 全空返回0 全非空返回1 有空有非空返回2
	 * */
	public static int hasNull(String... strs) {
		int i = 0;// 空的个数
		int j = 0;// 非空的个数
		for (String str : strs) {
			if (str == null || "".equals(str.trim())) {
				i++;
			} else {
				j++;
			}
		}
		if (i == 0) {
			return 1;
		} else if (j == 0) {
			return 0;
		} else {
			return 2;
		}
	}
}
