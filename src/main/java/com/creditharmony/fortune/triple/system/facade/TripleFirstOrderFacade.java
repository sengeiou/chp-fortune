package com.creditharmony.fortune.triple.system.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.fortune.triple.system.dao.TripleNewCustomerDao;
import com.creditharmony.fortune.triple.system.entity.IntCard;
import com.creditharmony.fortune.triple.system.entity.IntCustomerBean;
import com.creditharmony.fortune.triple.system.entity.IntPhone;
import com.creditharmony.fortune.triple.system.entity.IntPhoneCard;

/**
 * @Class Name TripleFirstOrderFacade
 * @author 胡体勇
 * @Create In 2016年5月4日
 */
@Service
public class TripleFirstOrderFacade {
	
	@Autowired
	private TripleNewCustomerDao tripleNewCustomerDao;
	
	/**
	 * 插入三网证件号表信息
	 * 2016年5月4日
	 * By 胡体勇
	 * @param id
	 * @return
	 */
	@Transactional(value="fortuneTransactionManager", readOnly = false)
	public int insertIntCard(IntCard id ){
		return tripleNewCustomerDao.insertIntCard(id);
	}
	
	/**
	 * 插入手机号证件号关联表信息
	 * 2016年5月4日
	 * By 胡体勇
	 * @param intPhoneCard
	 * @return
	 */
	@Transactional(value="fortuneTransactionManager", readOnly = false)
	public int insertIntPhoneCard(IntPhoneCard intPhoneCard){
		return tripleNewCustomerDao.insertIntPhoneCard(intPhoneCard);
	}
	
	/**
	 * 修改证件号对应的理财经理
	 * 2016年5月4日
	 * By 胡体勇
	 * @return
	 */
	@Transactional(value="fortuneTransactionManager", readOnly = false)
	public int updateEmpCodeByCertNum(IntCard intCard){
		return tripleNewCustomerDao.updateEmpCodeByCertNum(intCard);
	}
	
	/**
	 * 修改手机号对应的理财经理
	 * 2016年5月4日
	 * By 胡体勇
	 * @return
	 */
	@Transactional(value="fortuneTransactionManager", readOnly = false)
	public int updateEmpCodeByPhone(IntPhone p){
		return tripleNewCustomerDao.updateEmpCodeByPhone(p);
	}
	
	/**
	 * 插入信息到待发送表
	 * 2016年5月4日
	 * By 胡体勇
	 * @param cust
	 * @return
	 */
	@Transactional(value="fortuneTransactionManager", readOnly = false)
	public int insertTripleCustomer(IntCustomerBean cust){
		return tripleNewCustomerDao.insertTripleCustomer(cust);
	}

}
