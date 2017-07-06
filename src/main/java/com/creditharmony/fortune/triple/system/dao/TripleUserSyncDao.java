package com.creditharmony.fortune.triple.system.dao;

import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.FortuneBatisDao;
import com.creditharmony.fortune.triple.system.entity.IntEmployeeBean;

/**
 * 三网理财经理发送信息
 * @Class Name TripleUserSync
 * @author 胡体勇
 * @Create In 2016年3月2日
 */
@FortuneBatisDao
public interface TripleUserSyncDao extends CrudDao<IntEmployeeBean>{
	
	/**
	 * 三网理财经理信息同步
	 * 2016年3月2日
	 * By 胡体勇
	 * @param intUserBean
	 * @return
	 */
	public int insertIntUser(IntEmployeeBean bean);
	
	/**
	 * 根据三网消息反馈结果修改发送状态
	 * 2016年3月17日
	 * By 胡体勇
	 * @param bean
	 * @return
	 */
	public int updateSendStatus(IntEmployeeBean bean);

}
