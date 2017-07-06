package com.creditharmony.fortune.triple.system.dao;

import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.FortuneBatisDao;
import com.creditharmony.fortune.triple.system.entity.IntOrgBean;

/**
 * 组织机构同步三网
 * @Class Name TripleOrgSync
 * @author 胡体勇
 * @Create In 2016年3月2日
 */
@FortuneBatisDao
public interface TripleOrgSyncDao extends CrudDao<IntOrgBean>{
	
	/**
	 * 组织机构同步三网组织发送信息表
	 * 2016年3月2日
	 * By 胡体勇
	 * @param intOrgBean
	 * @return
	 */
	public int insertIntOrg(IntOrgBean intOrgBean);
	
	/**
	 * 根据三网消息反馈结果修改消息发送状态
	 * 2016年3月17日
	 * By 胡体勇
	 * @param bean
	 * @return
	 */
	public int updateSendStatus(IntOrgBean bean);

}
