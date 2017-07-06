package com.creditharmony.fortune.common;

import com.creditharmony.common.util.SpringContextHolder;
import com.creditharmony.fortune.customer.dao.CommonDao;

/**
 * 客户编号生成工具
 * 
 * @Class Name CustomerCodeGenerator
 * @Create In 2016年5月19日
 */
public class CustomerCodeGenerator {

	public static String getCurrentCode() {
		CommonDao dao = SpringContextHolder.getBean(CommonDao.class);
		return "C" + dao.getCurrentCustCode();
	}

	public static String getNextCode() {
		CommonDao dao = SpringContextHolder.getBean(CommonDao.class);
		return "C" + dao.getNextCustCode();
	}

}
