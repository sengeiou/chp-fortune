package com.creditharmony.fortune.redemption.common.dao;

import java.util.List;

import com.creditharmony.core.mybatis.paginator.domain.PageBounds;
import com.creditharmony.core.mybatis.paginator.domain.PageList;
import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.FortuneBatisDao;
import com.creditharmony.fortune.redemption.common.entity.ext.Redemptionex;
import com.creditharmony.fortune.redemption.common.view.RedemptionApplyEntity;

/**
 * 赎回已办Dao
 * @Class Name RedemptionHistoryDao
 * @author 陈广鹏
 * @Create In 2015年12月1日
 */
@FortuneBatisDao
public interface RedemptionHistoryDao extends CrudDao<Redemptionex> {
	
	/**
	 * 获取审批已办分页数据
	 * 2015年12月9日
	 * By 陈广鹏
	 * @param redemptionex
	 * @param pageBounds
	 * @return
	 */
	public PageList<Redemptionex> findApprovalList(Redemptionex redemptionex,
			PageBounds pageBounds);

	/**
	 * 获取申请已办分页数据
	 * 2015年12月22日
	 * By 陈广鹏
	 * @param redemptionex
	 * @param pageBounds
	 * @return
	 */
	public PageList<Redemptionex> findApplyList(Redemptionex redemptionex,
			PageBounds pageBounds);
	
	/**
	 * 获取申请查看分页数据
	 * 2015年12月22日
	 * By 陈广鹏
	 * @param redemptionex
	 * @param pageBounds
	 * @return
	 */
	public PageList<Redemptionex> findApplyCheck(Redemptionex redemptionex,
			PageBounds pageBounds);
	
	/**
	 * 获取审批查看分页数据
	 * 2015年12月22日
	 * By 陈广鹏
	 * @param redemptionex
	 * @param pageBounds
	 * @return
	 */
	public PageList<Redemptionex> findApprovalCheck(Redemptionex redemptionex,
			PageBounds pageBounds);
	
	/**
	 * 根据赎回ID获取一条申请记录
	 * 2015年12月2日
	 * By 陈广鹏
	 * @param lendCode
	 * @return
	 */
	public RedemptionApplyEntity getRedemptionByLendCode(String lendCode);

	/**
	 * 获取申请查看导出列表
	 * 2015年12月24日
	 * By 陈广鹏
	 * @param redemptionex
	 * @return
	 */
	public List<Redemptionex> findApplyCheck(Redemptionex redemptionex);

	/**
	 * 获取审批查看导出列表
	 * 2015年12月24日
	 * By 陈广鹏
	 * @param redemptionex
	 * @return
	 */
	public List<Redemptionex> findApprovalCheck(Redemptionex redemptionex);



}
