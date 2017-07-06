package com.creditharmony.fortune.deduct.common;

import java.io.Serializable;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.creditharmony.common.util.StringUtils;

/**
 * 查询条件过滤
 * @Class Name QueryFilter
 * @author 韩龙
 * @Create In 2015年12月4日
 */
public class QueryFilter implements Serializable {

	/**
	 * long serialVersionUID 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	private Logger logger = LoggerFactory.getLogger(QueryFilter.class);

	/**
	 * 过滤参数
	 */
	private Map<String, Object> filters = new HashMap<String, Object>();
	
	/**
	 * 初始化
	 * @param request
	 */
	public QueryFilter(HttpServletRequest request){
		setFilter(request);
	}
	
	/**
	 * 添加过滤字段条件
	 * 2015年12月4日
	 * By 韩龙
	 * @param filterName 参数名
	 * @param params 参数值
	 */
	public void addFilter(String filterName, Object params) {
		this.filters.put(filterName, params);
	}
	
	/**
	 * 取得查询条件。
	 * 2015年12月4日
	 * By 韩龙
	 * @return
	 */
	public Map<String, Object> getFilters() {
		return filters;
	}
	
	/**
	 * 将参数放到map中
	 * 2015年12月4日
	 * By 韩龙
	 * @param request
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public void setFilter(HttpServletRequest request) {
		Enumeration params = request.getParameterNames();
		while (params.hasMoreElements()) {
			String key = params.nextElement().toString();
			String[] values = request.getParameterValues(key);
			if (values.length > 0 && StringUtils.isNotEmpty(values[0])) {
				if (values.length == 1) {
					String val = values[0].trim();
					if (StringUtils.isNotEmpty(val)) {
						filters.put(key, values[0].trim());
					}
				} else {
					filters.put(key, values);
				}
			}
		}
	}
	
	/**
	 * 获取参数
	 * 2015年12月28日
	 * By 韩龙
	 * @param key
	 * @return
	 */
	public String getFilteMapValue(String key) {
		if(filters.get(key) != null){
			return StringUtils.toString(filters.get(key));
		}
		return null;
	}
}
