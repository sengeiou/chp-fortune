package com.creditharmony.fortune.users.dao;

import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.FortuneBatisDao;
import com.creditharmony.fortune.users.entity.RoleInfo;

/**
 * 角色DAO接口
 * @Class Name RoleInfoDao
 * @author 陈伟东
 * @Create In 2015年11月27日
 */
@FortuneBatisDao
public interface RoleInfoDao extends CrudDao<RoleInfo> {
	
}
