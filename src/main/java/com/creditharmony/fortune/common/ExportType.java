package com.creditharmony.fortune.common;

import java.util.HashMap;
import java.util.Map;

/**
 * 导出类型
 * @Class Name ExportType 
 * @author 李志伟
 * @Create In 2016年3月19日
 */
public enum ExportType {
	XLSX("0", ".xlsx"),
	TXT("1", ".txt");
	
	private static Map<String, ExportType> nameMap = new HashMap<String, ExportType>();
	private static Map<String, ExportType> codeMap = new HashMap<String, ExportType>();
	static {
		ExportType[] allValues = ExportType.values();
		for (ExportType obj : allValues) {
			nameMap.put(obj.getName(), obj);
			codeMap.put(obj.getCode(), obj);
		}
	}
	private String name;
	private String code;

	private ExportType(String code, String name) {
		this.name = name;
		this.code = code;

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public static ExportType parseByName(String name) {
		return nameMap.get(name);
	}

	public static ExportType parseByCode(String code) {
		return codeMap.get(code);
	}

	@Override
	public String toString() {
		return this.name;
	}
}
