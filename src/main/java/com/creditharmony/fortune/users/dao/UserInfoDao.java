package com.creditharmony.fortune.users.dao;


import java.util.List;
import java.util.Map;


import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.FortuneBatisDao;
import com.creditharmony.core.users.entity.UserRoleOrg;
import com.creditharmony.fortune.users.entity.UserInfo;

/**
 * 用户dao
 * @Class Name UserInfoDao
 * @author 张永生
 * @Create In 2015年12月8日
 */
@FortuneBatisDao
public interface UserInfoDao extends CrudDao<UserInfo> {

	/**
	 * 建立用户与机构组的关系
	 * @param user
	 * @return
	 */
	public void insertUserOrg(UserInfo userInfo);
	
	
	/**
	 * 建立用户、角色、组织间关系
	 * 2016年1月7日
	 * By 陈伟东
	 * @param userRoleOrg
	 */
	public void insertUserRoleOrg(UserRoleOrg userRoleOrg);


	/**
	 * 删除用户与机构组的关系
	 * @param userId
	 * @return
	 */
	public void deleteUserOrg(String userId);
	
	/**
	 * 删除用户角色的关系
	 * @param userId
	 * @return
	 */
	public void deleteUserRole(String userId);
	
	/**
	 * 删除用户角色组织的关系
	 * 2016年1月7日
	 * By 陈伟东
	 * @param userId
	 */
	public void deleteUserRoleOrg(String userId);
	
	/**
	 * 新增用户与角色的关系
	 * @param userInfo
	 * @return
	 */
	public void insertUserRole(UserInfo userInfo);


	/**
	 * 查询审核人
	 * 
	 */
	public List<Map<String, String>> getAuditor(Map<String, Object> map);

	
	/**
	 * 根据用户编码查询出编码下用户的离职状态（只要有一个为离职 就是离职）
	 * @param userCode
	 * @return
	 */
	public UserInfo getStatusByCode(String userCode);

}
