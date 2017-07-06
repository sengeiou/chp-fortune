package com.creditharmony.fortune.customer.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.common.util.ArrayHelper;
import com.creditharmony.core.service.CoreManager;
import com.creditharmony.fortune.customer.dao.LoanApplyDao;
import com.creditharmony.fortune.customer.entity.LoanApply;

/**
 * 我的客户-查看投资信息
 * 
 * @Class Name LoanApplyManager
 * @author 孙凯文
 * @Create In 2015年12月2日
 */
@Service
@Transactional(readOnly = true)
public class LoanApplyManager extends CoreManager<LoanApplyDao, LoanApply> {

	/**
	 * 根据出借编号获得单个出借 2015年12月24日 By 周俊
	 * 
	 * @param lendCode
	 * @return
	 */
	public BigDecimal getLoanApplyByLendMoney(String lendCode) {
		String[] array = lendCode.split(",");
		if (ArrayHelper.isNotNull(array)) {
			if (array.length == 1) {
				LoanApply loanApply = new LoanApply();
				loanApply.setApplyCode(lendCode);
				loanApply = dao.get(loanApply);
				return loanApply.getLendMoney();
			}
			BigDecimal lendMoney = new BigDecimal(0);
			for (String lendCode1 : array) {
				LoanApply loanApply = new LoanApply();
				loanApply.setApplyCode(lendCode1);
				loanApply = dao.get(loanApply);
				lendMoney = lendMoney.add(loanApply.getLendMoney());
			}
			return lendMoney;
		}
		return BigDecimal.ZERO;
	}

	/**
	 * 获取出借列表 2016年2月16日
	 * 
	 * @author 孙凯文
	 * @param customerCode
	 * @return
	 */
	public List<LoanApply> getLoanApplyList(String customerCode) {
		if ("".equals(customerCode) || null == customerCode) {
			return null;
		}
		return dao.getLoanApplyList(customerCode);
	}
}
