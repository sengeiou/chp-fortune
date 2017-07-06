package com.creditharmony.fortune.appweishang.dao;

import com.creditharmony.core.persistence.annotation.FortuneBatisDao;
import com.creditharmony.fortune.appweishang.bean.EmpUser;

/**
 * 我的微服
 * @author colin
 *
 */
@FortuneBatisDao
public interface EmpUserDao {
	
	/*
	 * 通过用户的员工编号取得用户信息
	 */
	EmpUser getInfoByUserCode(String userCode);
}
