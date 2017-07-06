package com.creditharmony.fortune.redemption.common.dao;

import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.FortuneBatisDao;
import com.creditharmony.fortune.redemption.common.entity.LoanWorkflow;

/**
 * 出借流程状态表对应Dao
 * @Class Name LoanWorkflowDao
 * @author 陈广鹏
 * @Create In 2015年12月2日
 */
@FortuneBatisDao
public interface LoanWorkflowDao extends CrudDao<LoanWorkflow> {

	/**
	 * 在出借流程状态表插入一条数据
	 * 2015年12月2日
	 * By 陈广鹏
	 * @param workflow
	 */
	public void insertWorkflow(LoanWorkflow workflow);
}
