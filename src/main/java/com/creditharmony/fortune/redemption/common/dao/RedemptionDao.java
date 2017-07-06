package com.creditharmony.fortune.redemption.common.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.creditharmony.core.mybatis.paginator.domain.PageBounds;
import com.creditharmony.core.mybatis.paginator.domain.PageList;
import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.FortuneBatisDao;
import com.creditharmony.fortune.back.interest.entity.BackInterestPool;
import com.creditharmony.fortune.creditor.matching.entity.MatchingCreditor;
import com.creditharmony.fortune.customer.entity.LoanApply;
import com.creditharmony.fortune.customer.entity.Product;
import com.creditharmony.fortune.redemption.common.entity.ext.Redemptionex;
import com.creditharmony.fortune.redemption.common.view.RedemptionApplyEntity;

/**
 * 提前赎回申请审批过程 DAO
 * @Class RedemptionDao
 * @author 陈广鹏
 * @Create 2015-11-23
 */
@FortuneBatisDao
public interface RedemptionDao extends CrudDao<Redemptionex> {

	/**
	 * 根据流程申请编码获取提前赎回申请相关信息
	 * 2015年12月3日
	 * By 陈广鹏
	 * @param applyId
	 * @return 赎回信息
	 */
	public RedemptionApplyEntity getRedemptionByApplyId(String applyId);
	
	/**
	 * 根据出借编号获取提前赎回申请相关信息
	 * 2015年12月3日
	 * By 陈广鹏
	 * @param lendCode
	 * @return 赎回信息
	 */
	public RedemptionApplyEntity getRedemptionByLendCode(String lendCode);

	/**
	 * 描述：获取赎回已办列表
	 * 2015年12月1日
	 * By 陈广鹏
	 * @param userId
	 * @param redemptionex
	 * @return
	 */
	public List<Redemptionex> getHistoryList(String userId, Redemptionex redemptionex);

	/**
	 * 获取分页数据
	 * 2015年12月4日
	 * By 陈广鹏
	 * @param redemptionex
	 * @param pageBounds
	 * @return
	 */
	public List<Redemptionex> findByParams(Redemptionex redemptionex,
			PageBounds pageBounds);
	
	/**
	 * 更新出借申请表状态
	 * 2015年12月31日
	 * By 陈广鹏
	 * @param loanApply
	 */
	public void updateLoanApply(LoanApply loanApply);
	
	/**
	 * 根据产品ID获取产品信息
	 * 2016年1月18日
	 * By 陈广鹏
	 * @param productCode
	 * @return 产品对象
	 */
	public Product getProductByCode(String productCode);
	
	/**
	 * 获取最后一条回息信息
	 * 2016年1月18日
	 * By 陈广鹏
	 * @param lendCode
	 * @return 回息对象
	 */
	public BackInterestPool getLastBackInterest(String lendCode);
	
	/**
	 * 更新回息状态
	 * 2016年1月18日
	 * By 陈广鹏
	 * @param backInterestPool
	 */
	public void updateInterestPool(BackInterestPool backInterestPool);
	
	/**
	 * 获取最后一笔已回息数据的本期到期日
	 * 2016年1月19日
	 * By 陈广鹏
	 * @param backInterestPool
	 * @return
	 */
	public Date getLastBackInterestDay(BackInterestPool backInterestPool);
	
	/**
	 * 获取已成功回息的次数
	 * @author xurongsheng
	 * @date 2016年10月17日 下午2:02:49
	 * @param backInterestPool
	 * @return
	 */
	public int getBackInterestCount(BackInterestPool backInterestPool);


	/**
	 * 特殊提前赎回管理列表
	 * 2016年3月1日
	 * By 陈广鹏
	 * @param redemptionex
	 * @param pageBounds
	 * @return
	 */
	public PageList<Redemptionex> findPushList(Redemptionex redemptionex,
			PageBounds pageBounds);

	/**
	 * 更新带推荐债权信息的结束日期为赎回到期日期
	 * 2016年5月27日
	 * By 陈广鹏
	 * @param mce
	 */
	public int updateMatchingEnddayBylendCode(MatchingCreditor matchingCreditor);

	/**
	 * 删除非首期未发送债权文件
	 * 2016年7月6日
	 * By 陈广鹏
	 * @param map
	 */
	public void deleteEmail(Map<String, Object> map);

}
