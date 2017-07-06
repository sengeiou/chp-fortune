package com.creditharmony.fortune.back.interest.applyConfrim.dao;

import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.FortuneBatisDao;
import com.creditharmony.fortune.back.interest.entity.BackInterestPool;

/**
 * 回息申请确认
 * @Class Name BackInterestApplyConfrimDao 
 * @author 李志伟
 * @Create In 2015年12月2日
 */
@FortuneBatisDao
public interface BackInterestApplyConfrimDao extends CrudDao<BackInterestPool>{
	
	/**
	 * 上传回息金额
	 * 2016年1月20日
	 * by 李志伟
	 * @param pool
	 */
	public void uploadMoney(BackInterestPool pool);
}