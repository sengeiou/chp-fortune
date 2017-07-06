package com.creditharmony.fortune.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import com.creditharmony.common.util.ArrayHelper;
import com.creditharmony.core.dict.entity.Dict;
import com.creditharmony.core.dict.util.DictUtils;

public class FortuneDictUtil {
	
	public static void addDicts(Model model,String[] types) {
		Map<String, List<Dict>> map = new HashMap<String, List<Dict>>();
		for (String type : types) {
			map.put(type, DictUtils.getDictList(type));
		}
		model.addAttribute("dicts",map);
	}
	
	/**
	 * 字典表数据集合取得
	 * 2016年4月18日
	 * By 朱杰
	 * @param model
	 * @param types
	 * @return
	 */
	public static Map<String,List<Dict>> getDicts(String[] types) {
		Map<String, List<Dict>> map = new HashMap<String, List<Dict>>();
		for (String type : types) {
			map.put(type, DictUtils.getDictList(type));
		}
		return map;
	}
	
	public static String dictName(List<Dict> dicts, String dictId, String defaultValue) {
		if (ArrayHelper.isNotEmpty(dicts)) {
			for (Dict d : dicts) {
				if (d.getValue().equals(dictId)) {
					return d.getLabel();
				}
			}
		}
		return defaultValue;
	}
	
	public static void addDicts(ModelAndView model,String[] types) {
		Map<String, List<Dict>> map = new HashMap<String, List<Dict>>();
		for (String type : types) {
			map.put(type, DictUtils.getDictList(type));
		}
		model.addObject("dicts",map);
	}
	
	public static Map<String, List<Dict>> getDictMap(String[] types) {
		Map<String, List<Dict>> map = new HashMap<String, List<Dict>>();
		for (String type : types) {
			map.put(type, DictUtils.getDictList(type));
		}
		return map;
	}
	
}
