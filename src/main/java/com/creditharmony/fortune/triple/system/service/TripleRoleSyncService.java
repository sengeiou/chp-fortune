package com.creditharmony.fortune.triple.system.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.core.service.CoreManager;
import com.creditharmony.fortune.triple.system.dao.TripleRoleSyncDao;
import com.creditharmony.fortune.triple.system.entity.IntRoleBean;

/**
 * @Class Name TripleRoleSyncService
 * @author 张新民
 * @Create In 2016年7月21日
 */
@Component
@Service
public class TripleRoleSyncService extends CoreManager<TripleRoleSyncDao, IntRoleBean> {
	
	/**
	 * 保存三网发送角色变更信息
	 * 2016年3月2日
	 * By 胡体勇
	 * @param intOrgBean
	 */
	@Transactional(value = "fortuneTransactionManager",readOnly = false)
	public void insertIntRole(IntRoleBean intRoleBean){
		super.dao.insertIntRole(intRoleBean);
	}

}
