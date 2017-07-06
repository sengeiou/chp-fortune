package com.creditharmony.fortune.users.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.core.service.CoreManager;
import com.creditharmony.core.users.entity.UserRoleOrg;
import com.creditharmony.fortune.users.dao.UserInfoDao;
import com.creditharmony.fortune.users.entity.UserInfo;


@Component
@Service
@Transactional(value = "fortuneTransactionManager",readOnly = false)
public class UserInfoService extends CoreManager<UserInfoDao, UserInfo> {
	
	public UserInfo getUser(String id){
		return dao.get(id);
	}

	public void saveUser(UserInfo userInfo){
		dao.insert(userInfo);
	}
	
	public void update(UserInfo userInfo){
		dao.update(userInfo);
	}
	
	public void delete(String id){
		dao.delete(id);
	}
	
	/**
	 * 新增用户与组织机构的关系
	 * @param userInfo
	 * @return
	 */
	public void insertUserOrg(UserInfo userInfo){
		dao.insertUserOrg(userInfo);
	}
	
	/**
	 * 新增用户角色组织关系
	 * 2016年1月7日
	 * By 陈伟东
	 * @param userRoleOrg
	 */
	public void insertUserRoleOrg(UserRoleOrg userRoleOrg){
		dao.insertUserRoleOrg(userRoleOrg);
	}


	/**
	 * 删除用户与机构组的关系
	 * @param userId
	 * @return
	 */
	public void deleteUserOrg(String userId){
		dao.deleteUserOrg(userId);
	}
	

	/**
	 * 删除用户角色的关系
	 * @param userId
	 * @return
	 */
	public void deleteUserRole(String userId){
		dao.deleteUserRole(userId);
	}
	
	
	/**
	 * 删除用户角色组织的关系
	 * 2016年1月7日
	 * By 陈伟东
	 * @param userId
	 */
	public void deleteUserRoleOrg(String userId){
		dao.deleteUserRoleOrg(userId);
	}
	/**
	 * 新增用户与角色的关系
	 * @param userInfo
	 * @return
	 */
	public void insertUserRole(UserInfo userInfo){
		dao.insertUserRole(userInfo);
	}

	/**
	 * 查询审核人
	 * 
	 */
	public List<Map<String, String>> getAuditor(Map<String, Object> map) {
		return dao.getAuditor(map);
	}

	
	/**
	 * 根据用户编码查询出用户离职状态 （用户编码下只要有一个用户是在职 就是在职状态）
	 * @param userCode
	 * @return
	 */
	public UserInfo getStatusByCode(String userCode){
		return dao.getStatusByCode(userCode);
	}

}
