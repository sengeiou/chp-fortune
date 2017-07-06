package com.creditharmony.fortune.trusteeship.dao;

import java.util.List;
import java.util.Map;

import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.FortuneBatisDao;
import com.creditharmony.fortune.trusteeship.entity.FyCode;

/**
 * 富有金账户码表转换
 * @Class Name FyCodeDao
 * @author 朱杰
 * @Create In 2016年2月29日
 */
@FortuneBatisDao
public interface FyCodeDao extends CrudDao<FyCode>{
	/**
	 * 富有地区码查询
	 * 2016年2月29日
	 * By 朱杰
	 * @param map
	 * @return
	 */
	List<FyCode> findAreaCodeBySysCode(Map<String, Object> map);
	
	/**
	 * 富有银行卡查询
	 * 2016年2月29日
	 * By 朱杰
	 * @param map
	 * @return
	 */
	FyCode findBankCodeBySysCode(Map<String, Object> map);
	
}