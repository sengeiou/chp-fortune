package com.creditharmony.fortune.change.lend.apply.dao;

import java.util.List;

import com.creditharmony.core.mybatis.paginator.domain.PageBounds;
import com.creditharmony.core.mybatis.paginator.domain.PageList;
import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.FortuneBatisDao;
import com.creditharmony.fortune.change.lend.apply.entity.LendChangeInfo;
import com.creditharmony.fortune.change.lend.apply.entity.LendChangeLog;
import com.creditharmony.fortune.change.lend.apply.entity.LendLaunchView;
import com.creditharmony.fortune.change.lend.apply.entity.LendLoanApply;
import com.creditharmony.fortune.change.lend.apply.entity.LendLoanApplyEx;
import com.creditharmony.fortune.change.lend.apply.entity.LendLoanExSearch;
import com.creditharmony.fortune.change.lend.apply.entity.LendQueryView;
import com.creditharmony.fortune.change.lender.apply.entity.ext.CustomerEx;
import com.creditharmony.fortune.customer.entity.CustomerAccount;

/**
 * 出借信息修改DAO层
 * @Class Name LendChangeDao
 * @author 刘雄武
 * @Create In 2015年12月1日
 */
@FortuneBatisDao
public interface LendChangeDao extends CrudDao<LendLoanApplyEx> {

	/**
	 * 获取发起出借信息表单数据
	 * 2015年12月2日
	 * By 刘雄武
	 * @param applyCode
	 * @return
	 */
	public LendLaunchView getLaunchFrom(String applyCode);

	/**
	 * 根据出借编号获取出借申请信息
	 * 2015年12月2日
	 * By 刘雄武
	 * @param applyCode
	 * @return
	 */
	public LendLoanApply getLoanApplyInfo(String applyCode);

	/**
	 * 根据申请ID获取申请变更信息
	 * 2015年12月2日
	 * By 刘雄武
	 * @param applyId
	 * @return
	 */
	public LendChangeInfo getLendChangeInfo(String applyId);

	/**
	 * 保存变更信息
	 * 2015年12月2日
	 * By 刘雄武
	 * @param changeInfo
	 */
	public void saveLendChangeInfo(LendChangeInfo changeInfo);

	/**
	 * 保存审批记录
	 * 2015年12月4日
	 * By 刘雄武
	 * @param changeLog
	 */
	public void saveApproveInfo(LendChangeLog changeLog);

	/**
	 * 根据出借编号获取出借人账户信息(变更前回款银行)
	 * 2015年12月8日
	 * By 刘雄武
	 * @param applyCode
	 * @return
	 */
	public CustomerAccount getCustomerAccountbefore(String applyCode);

	/**
	 * 根据申请ID获取审批记录表（门店审批记录，限一条）
	 * 2015年12月8日
	 * By 刘雄武
	 * @param changeLog
	 * @return
	 */
	public LendChangeLog getLendChangeLog(LendChangeLog changeLog);

	/**
	 * 更新出借人账户信息（只能更改回款银行）
	 * 2015年12月8日
	 * By 刘雄武
	 * @param customerAccount
	 */
	public void updateCustomerAccount(CustomerAccount customerAccount);

	/**
	 * 获取出借信息变更已办	
	 * 2015年12月28日
	 * By 刘雄武
	 * @param queryView
	 * @param pageBounds
	 * @return
	 */
	public List<LendLoanApply> getLendChangeFinish(LendQueryView queryView,PageBounds pageBounds);

	/**
	 * 查询审批信息列表
	 * 2015年12月9日
	 * By 刘雄武
	 * @param changeLog
	 * @return
	 */
	public List<LendChangeLog> getApproveInfoList(LendChangeLog changeLog);

	/**
	 * 更新出借申请回款银行
	 * 2015年12月10日
	 * By 刘雄武
	 * @param apply
	 */
	public void updateLoanApplyAccount(LendLoanApply apply);
	
	/**
	 * 获取出借变更历史
	 * 2015年12月10日
	 * By 刘雄武
	 * @param lendloanapply
	 * @param pageBounds
	 * @return
	 */
	public List<LendLoanApply> getLendChangeHistory(LendLoanApply lendloanapply,PageBounds pageBounds);
	
    /**
     * 更新变更状态信息
     * 2015年12月11日
     * By 刘雄武
     * @param changeInfo
     */
	public void updateChangeState(LendChangeInfo changeInfo);
	
	/**
	 * 获取出借申请表单数据
	 * 2016年1月21日
	 * By 刘雄武
	 * @param map
	 * @param pageBounds
	 * @return
	 */
	public PageList<LendLoanApplyEx> findList(LendQueryView queryView,PageBounds pageBounds);
	
	/**
	 * 获取出借申请表单数据
	 * 2016年9月26日
	 * By liusl
	 * @param queryView
	 * @param pageBounds
	 * @return
	 */
	public PageList<LendLoanApplyEx> findList2(LendQueryView queryView,PageBounds pageBounds);
	
   /**
    * 获取出借信息查询列表
    * 2016年1月21日
    * By 刘雄武
    * @param map
    * @param pageBounds
    * @return
    */
	public PageList<LendLoanApply> queryList(LendQueryView Query, PageBounds pageBounds);
	
	/**
	 * 更新变更信息
	 * 2015年12月16日
	 * By 刘雄武
	 * @param changeInfo
	 */
	public void updateChangeInfo(LendChangeInfo changeInfo);
	
	/**
	 * 根据变更ID查询具体变更数据
	 * 2015年12月16日
	 * By 刘雄武
	 * @param changeId
	 * @return
	 */
	public LendChangeInfo getChangeInfoById(String changeId);
	
	/**
	 * 获取出借付款银行信息
	 * 2016年1月19日
	 * By 刘雄武
	 * @param applyCode
	 * @return
	 */
	public CustomerAccount getCustomerPayAccount(String applyCode);
	
	/**
	 * 获取出借回款银行信息
	 * 2016年1月19日
	 * By 刘雄武
	 * @param applyCode
	 * @return
	 */
	public CustomerAccount getCustomerReyAccount(String applyCode);
	
	/**
	 * 根据客户编号获取最新的一个出借编号
	 * 2016年2月26日
	 * By 郭才林
	 * @param custCode
	 * @return
	 */
	public LendLoanApply getLendByCustCode(String custCode);

	 /**
     * 更据出借编号获取回款状态
     * 2016年3月1日
     * By 郭才林
     * @param LendLoanApply
     * @return
     */
	public LendLoanApplyEx backMoneyStatus(LendLoanApply lendLoanApply);
	
	/**
	 * 出借信息变更查询导出Excel
	 * 2016年3月2日
	 * By 刘雄武
	 * @param Query
	 * @return
	 */
	public List<LendLoanExSearch> queryExport(LendQueryView Query);

	/**
	 * 更新金账户银行卡号
	 * 2016年3月10日
	 * By 郭才林
	 * @param apply
	 */
	public void updateTrusteeshipCard(CustomerEx customer);

	/**
	 * 资金托管下的银行变更
	 * 2016年3月10日
	 * By 郭才林
	 * @param lendLoanApply
	 */
	public void updateZjAccount(LendLoanApply lendLoanApply);
}
