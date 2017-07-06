package com.creditharmony.fortune.common;

import com.creditharmony.common.util.SpringContextHolder;
import com.creditharmony.fortune.customer.dao.LoanApplyDao;

/**
 * @Class Name ContractCodeGenerator
 * @Create In 2016年5月19日
 */
public class ContractCodeGenerator {

	/**
	 * 获取合同编号 2016年5月19日
	 * 
	 * @param productCode
	 */
	public static String getNextCode(String productCode) {
		LoanApplyDao dao = SpringContextHolder.getBean(LoanApplyDao.class);
		
		String contractCode = dao.getContractCode();
		contractCode = productCode + "C" + contractCode;
		return contractCode;
	}
}
