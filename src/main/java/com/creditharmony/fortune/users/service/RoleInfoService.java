package com.creditharmony.fortune.users.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.core.service.CoreManager;
import com.creditharmony.fortune.users.dao.RoleInfoDao;
import com.creditharmony.fortune.users.entity.RoleInfo;

/**
 * 角色管理Service
 * @Class Name RoleInfoService
 * @author 陈伟东
 * @Create In 2015年12月25日
 */
@Service
@Transactional(value = "fortuneTransactionManager",readOnly = false)
public class RoleInfoService extends CoreManager<RoleInfoDao, RoleInfo> {
	
	public RoleInfo getRole(String id){
		return dao.get(id);
	}

	public void saveRole(RoleInfo roleInfo){
		dao.insert(roleInfo);
	}
	
	public void update(RoleInfo roleInfo){
		dao.update(roleInfo);
	}
}
