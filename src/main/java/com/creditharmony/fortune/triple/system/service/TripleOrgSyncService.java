package com.creditharmony.fortune.triple.system.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.core.service.CoreManager;
import com.creditharmony.fortune.triple.system.dao.TripleOrgSyncDao;
import com.creditharmony.fortune.triple.system.entity.IntOrgBean;

/**
 * @Class Name TripleOrgSysnManager
 * @author 胡体勇
 * @Create In 2016年3月2日
 */
@Component
@Service
public class TripleOrgSyncService extends CoreManager<TripleOrgSyncDao, IntOrgBean> {
	
	/**
	 * 保存三网发送组织机构变更信息
	 * 2016年3月2日
	 * By 胡体勇
	 * @param intOrgBean
	 */
	@Transactional(value = "fortuneTransactionManager",readOnly = false)
	public void insertIntOrg(IntOrgBean intOrgBean){
		super.dao.insertIntOrg(intOrgBean);
	}

}
