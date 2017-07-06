package com.creditharmony.fortune.exituserorg.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.core.service.CoreManager;
import com.creditharmony.fortune.exituserorg.dao.UserOrgInfoDao;
import com.creditharmony.fortune.exituserorg.entity.UserOrgInfo;


@Service
@Transactional(value = "fortuneTransactionManager",readOnly = false)
public class UserOrgInfoService extends CoreManager<UserOrgInfoDao, UserOrgInfo> {

	public int insert(UserOrgInfo userOrgInfo) {
		return dao.insert(userOrgInfo);
	}
}