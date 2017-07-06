package com.creditharmony.fortune.triple.system.dao;

import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.FortuneBatisDao;
import com.creditharmony.fortune.triple.system.entity.IntRoleBean;

/**
 * 角色同步三网
 * @Class Name TripleOrgSync
 * @author 张新民
 * @Create In 2016年3月2日
 */
@FortuneBatisDao
public interface TripleRoleSyncDao extends CrudDao<IntRoleBean>{
	
	/**
	 * 角色同步三网组织发送信息表
	 * 2016年7月21日
	 * By 张新民
	 * @param intOrgBean
	 * @return
	 */
	public int insertIntRole(IntRoleBean intRoleBean);
	
	/**
	 * 根据三网消息反馈结果修改消息发送状态
	 * 2016年7月21日
	 * By 张新民
	 * @param bean
	 * @return
	 */
	public int updateSendStatus(IntRoleBean bean);

}
