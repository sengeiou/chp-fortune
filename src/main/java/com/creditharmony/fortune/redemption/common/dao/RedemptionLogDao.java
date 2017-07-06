package com.creditharmony.fortune.redemption.common.dao;

import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.FortuneBatisDao;
import com.creditharmony.fortune.redemption.common.entity.RedemptionLog;

/**
 * 赎回审批记录对应Log
 * @Class Name RedemptionLogDao
 * @author 陈广鹏
 * @Create In 2015年12月2日
 */
@FortuneBatisDao
public interface RedemptionLogDao extends CrudDao<RedemptionLog> {
	
	/**
	 * 描述：在赎回审批记录表插入一条数据
	 * 2015年12月11日
	 * By 陈广鹏
	 * @param log
	 */
	public void insertRedemptionLog(RedemptionLog log);

}
