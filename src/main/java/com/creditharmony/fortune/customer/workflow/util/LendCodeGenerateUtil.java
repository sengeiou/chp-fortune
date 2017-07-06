package com.creditharmony.fortune.customer.workflow.util;

import com.creditharmony.bpm.utils.SpringUtil;
import com.creditharmony.fortune.customer.CustomerConstants;
import com.creditharmony.fortune.customer.dao.LoanApplyDao;
import com.creditharmony.fortune.customer.entity.LoanApply;

/**
 * 生成出借编号工具类
 * 
 * @Class Name LendCodeGenerateUtil
 * @author 孙凯文
 * @Create In 2015年12月23日
 */
public class LendCodeGenerateUtil {
	/**
	 * 根据客户编号生成出借编号 2015年12月23日
	 * 
	 * @author 孙凯文
	 * @param customerCode
	 * @param productCode
	 * @return
	 */
	public static String generate(String customerCode, String productCode) {
		LoanApplyDao dao = (LoanApplyDao) SpringUtil.getCtx().getBean(
				LoanApplyDao.class);
		LoanApply lendApply = dao.getLastestLend(customerCode);
		long count = 0;
		String prefix = "";
		String suffix = "";
		if (null == lendApply) {
			count = count + CustomerConstants.LENDCODE_GROWTHRATE;
		} else {
			String lendCode = lendApply.getApplyCode();
			int _index = lendCode.lastIndexOf("-");
			if (_index >= 0) {
				String serialNum = lendCode.substring(_index + 1);
				if (serialNum.length() > 3) {
					prefix = serialNum.substring(0, 3);
					suffix = serialNum.substring(3);
				} else {
					prefix = serialNum;
				}
				count = Long.parseLong(prefix) + 1;
			} else {
				return (Long.parseLong(lendCode) + 1) + "";
			}
		}
		String num = String.format(
				CustomerConstants.DataViewConfig.LENDCODE_SERIALPATTERN, count);
		return productCode + customerCode + "-" + num;
	}

}
