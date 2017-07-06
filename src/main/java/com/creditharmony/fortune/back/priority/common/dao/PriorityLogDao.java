package com.creditharmony.fortune.back.priority.common.dao;

import com.creditharmony.core.persistence.annotation.FortuneBatisDao;
import com.creditharmony.fortune.back.priority.common.view.PriorityBackLog;

/**
 * 优先回款审批审批纪录Dao
 * @Class Name PriorityDetailDao
 * @author 郭强
 * @Create In 2017年3月28日
 */
@FortuneBatisDao
public interface PriorityLogDao {

	/**
	 * 增加审批纪录
	 */
	public int addPriorityLog(PriorityBackLog log);
	
}
