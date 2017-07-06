package com.creditharmony.fortune.redemption.common.dao;

import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.FortuneBatisDao;
import com.creditharmony.fortune.redemption.common.entity.RedemptionApply;

/**
 * 赎回申请表对应Dao
 * @Class Name RedemptionApplyDao
 * @author 陈广鹏
 * @Create In 2015年12月2日
 */
@FortuneBatisDao
public interface RedemptionApplyDao extends CrudDao<RedemptionApply> {

	/**
	 * 在提前赎回申请表中添加一条记录
	 * 
	 * @param itemView
	 */
	public void insetApply(RedemptionApply itemView);

	/**
	 * 根据申请ID查询申请信息
	 * 2015年11月30日
	 * By 陈广鹏
	 * @param redemptionId
	 * @return
	 */
	public RedemptionApply getRedemptionApplyById(String redemptionId);

	/**
	 * 更新赎回申请信息
	 * 2015年12月1日
	 * By 陈广鹏
	 * @param apply
	 */
	public void updateRedemptionApply(RedemptionApply apply);

	/**
	 * 查看已发起赎回流程数量
	 * 2016年10月24日
	 * By 陈广鹏
	 * @param lendCode
	 * @return
	 */
	public int countApply(String lendCode);
	
}
