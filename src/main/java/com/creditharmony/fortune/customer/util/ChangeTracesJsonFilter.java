package com.creditharmony.fortune.customer.util;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.PropertyPreFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * @Class Name ChangeTracesJsonFilter
 * @author 孙凯文
 * @Create In 2015年12月8日
 */
public class ChangeTracesJsonFilter implements PropertyPreFilter {
	private Map<Class<?>, String[]> includes = new HashMap<Class<?>, String[]>();
	private Map<Class<?>, String[]> excludes = new HashMap<Class<?>, String[]>();

	static {
		JSON.DEFAULT_GENERATE_FEATURE |= SerializerFeature.DisableCircularReferenceDetect
				.getMask();
	}

	public ChangeTracesJsonFilter() {
	}

	public ChangeTracesJsonFilter(Map<Class<?>, String[]> includes) {
		super();
		this.includes = includes;
	}

	public ChangeTracesJsonFilter addInclude(Class<?> clazz, String[] name) {
		includes.put(clazz, name);
		return this;
	}

	public ChangeTracesJsonFilter addExclude(Class<?> clazz, String[] name) {
		excludes.put(clazz, name);
		return this;
	}

	/**
	 * 2015年12月8日 By 孙凯文
	 * 
	 * @param serializer
	 * @param object
	 * @param name
	 * @return
	 */
	public boolean apply(JSONSerializer serializer, Object object, String name) {
		if (null == object) {
			return true;
		}

		if (this.includes.isEmpty() && this.excludes.isEmpty()) {
			return true;
		}

		for (Map.Entry<Class<?>, String[]> entry : this.excludes.entrySet()) {
			if (!entry.getKey().isInstance(object)) {
				// object不是entry.getKey实例
				return true;
			} else {
				String[] fields = entry.getValue();
				if (contains(fields, name)) {
					return false;
				}
			}
		}

		for (Map.Entry<Class<?>, String[]> entry : this.includes.entrySet()) {
			if (entry.getKey().isInstance(object)) {
				if (contains(entry.getValue(), name)) {
					return true;
				}
			}
		}

		return false;
	}

	private static boolean contains(String[] source, String target) {
		for (String s : source) {
			if (target.equals(s)) {
				return true;
			}
		}
		return false;
	}
}
