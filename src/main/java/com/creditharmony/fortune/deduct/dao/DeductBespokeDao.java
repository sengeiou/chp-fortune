package com.creditharmony.fortune.deduct.dao;

import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.FortuneBatisDao;
import com.creditharmony.fortune.deduct.entity.DeductBespoke;

/**
 * 划扣申请dao
 * @Class Name DeductApplyDao
 * @author 韩龙
 * @Create In 2015年11月27日
 */
@FortuneBatisDao
public interface DeductBespokeDao extends CrudDao<DeductBespoke> {

	
	/**
	 * 获取预约对象
	 * 2016年2月18日
	 * By 韩龙
	 * @param tempId
	 * @return
	 */
	public DeductBespoke findDeductBespokeByLendCode(String lendCode);
	
}