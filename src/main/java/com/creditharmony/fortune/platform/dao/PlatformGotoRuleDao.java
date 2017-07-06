package com.creditharmony.fortune.platform.dao;

import java.util.List;

import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.FortuneBatisDao;
import com.creditharmony.fortune.platform.entity.PlatformGotoRule;

/**
 * 省市平台跳转限制Dao
 * @Class Name PlatformGotoRuleDao
 * @author 周俊
 * @Create In 2016年3月4日
 */
@FortuneBatisDao
public interface PlatformGotoRuleDao extends CrudDao<PlatformGotoRule>{

	/**
	 * 查询
	 * 2016年3月4日
	 * By 周俊
	 * @param platformGotoRule
	 * @return
	 */
	public List<PlatformGotoRule> findPlatformGotoRule(PlatformGotoRule platformGotoRule);
}
