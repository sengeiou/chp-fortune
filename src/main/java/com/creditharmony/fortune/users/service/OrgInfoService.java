package com.creditharmony.fortune.users.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.core.service.CoreManager;
import com.creditharmony.fortune.users.dao.OrgInfoDao;
import com.creditharmony.fortune.users.entity.OrgInfo;


@Component
@Service
@Transactional(value = "fortuneTransactionManager",readOnly = false)
public class OrgInfoService extends CoreManager<OrgInfoDao, OrgInfo> {
	
	public OrgInfo getOrg(String id){
		return dao.get(id);
	}

	public void saveOrg(OrgInfo orgInfo){
		dao.insert(orgInfo);
	}
	
	public void update(OrgInfo orgInfo){
		dao.update(orgInfo);
	}
	
	public void delete(String id){
		dao.delete(id);
	}
	
	
}
