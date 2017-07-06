package com.creditharmony.fortune.triple.system.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.core.service.CoreManager;
import com.creditharmony.fortune.triple.system.dao.TripleUserSyncDao;
import com.creditharmony.fortune.triple.system.entity.IntEmployeeBean;

/**
 * 三网同步理财经理信息
 * @Class Name TripleEmployeeSyncService
 * @author 胡体勇
 * @Create In 2016年3月2日
 */
@Component
@Service
public class TripleUserSyncService extends CoreManager<TripleUserSyncDao,IntEmployeeBean>{
	
	/**
	 * 三网同步理财经理信息
	 * 2016年3月2日
	 * By 胡体勇
	 * @param intEmployeeBean
	 */
	@Transactional(value = "fortuneTransactionManager",readOnly = false)
	public void insertIntUser(IntEmployeeBean intUserBean){
		super.dao.insertIntUser(intUserBean);
	}

}
