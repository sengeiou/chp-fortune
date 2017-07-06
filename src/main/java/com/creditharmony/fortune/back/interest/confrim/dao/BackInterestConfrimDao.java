package com.creditharmony.fortune.back.interest.confrim.dao;

import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.FortuneBatisDao;
import com.creditharmony.fortune.back.interest.entity.BackInterestPool;
import com.creditharmony.fortune.customer.entity.LoanApply;

/**
 * 回息确认
 * @Class Name BackInterestConfrimDao 
 * @author 李志伟
 * @Create In 2015年12月2日
 */
@FortuneBatisDao
public interface BackInterestConfrimDao extends CrudDao<BackInterestPool> {
	
	/**
	 * 查询当前回息日期
	 * 2016年4月14日
	 * by 李志伟
	 * @param lendCode
	 */
	public LoanApply selectMesg(String lendCode);
	
	/**
	 * 更新本期回息日期和回息状态
	 * 2016年4月14日
	 * by 李志伟
	 * @param la
	 */
	public void updateMesg(LoanApply la);

}