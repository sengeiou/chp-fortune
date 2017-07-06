package com.creditharmony.fortune.deduct.dao;

import java.util.List;
import java.util.Map;

import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.FortuneBatisDao;
import com.creditharmony.fortune.deduct.entity.PlatformRule;

/**
 * 三方平台规则表dao
 * @Class Name PlatformRuleDao
 * @author 韩龙
 * @Create In 2015年11月27日
 */
@FortuneBatisDao
public interface PlatformRuleDao extends CrudDao<PlatformRule> {
	
	/**
	 * 查询三方平台规则表
	 * 2015年12月17日
	 * By 韩龙
	 * @param map
	 * @return
	 */
	public List<PlatformRule> getPlatformRuleList(Map<String,Object> map);
	
	/**
	 * 查询三方平台规则表
	 * 2015年12月17日
	 * By 韩龙
	 * @param map
	 * @return
	 */
	public PlatformRule getPlatformRule(Map<String,Object> map);

}