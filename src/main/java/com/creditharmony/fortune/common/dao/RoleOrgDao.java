package com.creditharmony.fortune.common.dao;

import java.util.List;
import java.util.Map;

import com.creditharmony.core.persistence.annotation.FortuneBatisDao;
import com.creditharmony.fortune.common.entity.Org;
import com.creditharmony.fortune.common.entity.UserRoleOrgEx;

/**
 * 角色，权限管理-Dao
 * 
 * @Class Name RoleOrgDao
 * @author 朱杰
 * @Create In 2016年1月6日
 */
@FortuneBatisDao
public interface RoleOrgDao {

	/**
	 * 查找自身关联的指定的组织机构
	 * 例如：理财经理查找团队角色机构或者营业部角色机构等
	 * 
	 * 2015年12月29日
	 * By 朱杰
	 * @param map
	 * @return
	 */
	public List<UserRoleOrgEx> findRoleOrg(Map<String, Object> map);
	
	/**
	 * 查找自身关联的范围内的组织机构
	 * 
	 * 2015年12月29日
	 * By 朱杰
	 * @param map
	 * @return
	 */
	public List<UserRoleOrgEx> findRoleOrgRange(Map<String, Object> map);
	
	/**
	 * 根据用户id和用户角色查询机构类型
	 * 2016年1月25日
	 * By 朱杰
	 * @param map
	 * @return
	 */
	public Org getRoleTypeByUserAndRole(Map<String, Object> map);
		
}
