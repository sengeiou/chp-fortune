package com.creditharmony.fortune.deduct.dao;

import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.FortuneBatisDao;
import com.creditharmony.fortune.deduct.entity.DeductLog;

/**
 * 划扣申请dao
 * @Class Name DeductLogDao
 * @author 韩龙
 * @Create In 2015年11月27日
 */
@FortuneBatisDao
public interface DeductLogDao extends CrudDao<DeductLog> {

	/**
	 * 插入审批记录表
	 * 2015年12月1日
	 * By 韩龙
	 * @param deductLog
	 */
	public int insertSelective(DeductLog deductLog);
}