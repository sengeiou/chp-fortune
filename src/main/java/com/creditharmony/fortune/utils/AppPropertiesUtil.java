package com.creditharmony.fortune.utils;

import java.util.Properties;

public class AppPropertiesUtil {

	private static String filename = "/application.properties";
	private static Properties pro = new Properties();
	static {
		try {
			pro.load(AppPropertiesUtil.class.getResourceAsStream(filename));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Load application.properties “"+filename+"” file error.");
		}
	}

	public static int getInt(String key) {
		int i = 0;
		try {
			i = Integer.parseInt(getString(key));
		} catch (Exception e) {
		}
		return i;
	}

	public static String getString(String key) {
		return pro == null ? null : pro.getProperty(key);
	}

}
