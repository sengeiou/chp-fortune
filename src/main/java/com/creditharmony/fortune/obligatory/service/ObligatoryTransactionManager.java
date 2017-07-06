package com.creditharmony.fortune.obligatory.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.fortune.obligatory.dao.CreditLocationListDao;
import com.creditharmony.fortune.obligatory.entity.CreditOkListObj;

/**
 * 可用债权配置事务方法
 * @Class Name ObligatoryTransactionManager 
 * @author 李志伟
 * @Create In 2016年5月10日
 */
@Service
public class ObligatoryTransactionManager {

	@Autowired
	private  CreditLocationListDao creditLocationListDao;
	
	/**
	 * 添加新的可用债权配置
	 * 2015年12月19日
	 * by 李志伟
	 * @param coo
	 */
	@Transactional(value = "fortuneTransactionManager", readOnly = false)
	public void add(CreditOkListObj coo) {
		creditLocationListDao.add(coo);
	}
}
