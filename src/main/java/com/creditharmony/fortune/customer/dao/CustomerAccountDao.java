package com.creditharmony.fortune.customer.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.FortuneBatisDao;
import com.creditharmony.fortune.customer.entity.CustomerAccount;

/**
 * 客户账户数据操作类
 * @Class Name CustomerAccountDao
 * @author 孙凯文
 * @Create In 2015年12月2日
 */
@FortuneBatisDao
public interface CustomerAccountDao extends CrudDao<CustomerAccount> {

	/**
	 * 判断银行是否被使用 2016年1月26日
	 * 
	 * @author 孙凯文
	 * @param bankId
	 * @param status
	 * @return int
	 */
	public int countStatus(@Param("bankId") String bankId,
			@Param("status") String status);
	
	public List<CustomerAccount> findListByCustomerCode(Map<String, Object> params);
	
	/**
	 * 根据客户、卡号，查询是否已存在此卡信息
	 * 2016年3月9日
	 * By 肖长伟
	 * @param paramMap
	 * @return
	 */
	public int getCardNoCunt(Map<String, Object> paramMap);

	public List<CustomerAccount> getAccountByCardNo(Map<String, Object> params);

	public int logicDelete(Map<String, Object> params);
	
}