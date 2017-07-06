package com.creditharmony.fortune.triple.system.dao;

import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.FortuneBatisDao;
import com.creditharmony.fortune.triple.system.entity.IntLoanAskBean;

/**
 * 客户咨询记录同步三网
 * @Class Name TripleLoanAskDao
 * @author 张新民
 * @Create In 2016年3月2日
 */
@FortuneBatisDao
public interface TripleLoanAskSyncDao extends CrudDao<IntLoanAskBean>{
	
	/**
	 * 客户咨询同步三网组织发送信息表
	 * 2016年7月21日
	 * By 张新民
	 * @param intOrgBean
	 * @return
	 */
	public int insertIntLoanAsk(IntLoanAskBean intLoanAskBean);
	
	/**
	 * 根据三网消息反馈结果修改消息发送状态
	 * 2016年7月21日
	 * By 张新民
	 * @param bean
	 * @return
	 */
	public int updateSendStatus(IntLoanAskBean bean);

}
