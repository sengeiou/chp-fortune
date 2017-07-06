package com.creditharmony.fortune.app.webservice.ocr.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ExceptionUtil {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try{
			System.out.println(1/0);
		}catch (Exception e) {
			//String s = getStackTrace(e);
			//System.out.println(s);
			e.printStackTrace();
		}

	}
	
	public static String getStackTrace(Throwable t){
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		try{
			t.printStackTrace(pw);
			return sw.toString();
		}finally{
			pw.close();
		}
	}

}
