package com.creditharmony.fortune.look.approve.dao;

import java.util.List;
import java.util.Map;

import com.creditharmony.core.mybatis.paginator.domain.PageBounds;
import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.FortuneBatisDao;
import com.creditharmony.fortune.customer.entity.Customer;
import com.creditharmony.fortune.customer.entity.LoanApply;
import com.creditharmony.fortune.customer.workflow.entity.LendApplyLog;
import com.creditharmony.fortune.look.apply.entity.LendApplyLookExportApprovalPassDetailExcelEx;
import com.creditharmony.fortune.look.apply.view.LendLookListView;
import com.creditharmony.fortune.look.approve.entity.LendApprovalLookExportExcelEx;

/**
 * 出借申请审批查看列表
 * @Class Name LendApplyApprovalLookDao 
 * @author 李志伟
 * @Create In 2015年12月24日
 */
@FortuneBatisDao
public interface LendApplyApprovalLookDao extends CrudDao<LoanApply>{
	
	/**
	 * 查看出借申请审批查看列表
	 * 2016年1月21日
	 * by 李志伟
	 * @param map
	 * @param pageBounds
	 * @return
	 */
	public List<LoanApply> loadLendApplyApprovalLookList(Map<String, Object> map,  PageBounds pageBounds);
	
	/**
	 * 初始化出借申请审批查看页客户信息
	 * 2015年12月28日
	 * by 李志伟
	 * @param code
	 * @return
	 */
	public Customer goLendApplyApprovalPage(String code);
	
	/**
	 * 初始化出借审批详情页出借申请数据
	 * 2015年12月29日
	 * by 李志伟
	 * @param id
	 * @return
	 */
	public LoanApply loadLendApprovalPage(String id);
	
	/**
	 * 初始化出借审批信息
	 * 2015年12月29日
	 * by 李志伟
	 * @param code
	 * @return
	 */
	public LendApplyLog loadLendApprovalMesg(String code);
	
	/**
	 * 初始化银行信息
	 * 2015年12月29日
	 * by 李志伟
	 * @param map
	 * @return
	 */
	public List<LoanApply> loadBanMesg(Map<String, Object> map);
	
	/**
	 * 查询提前赎回出借部分
	 * 2016年3月4日
	 * by 李志伟
	 * @param map
	 * @return
	 */
	public List<LendLookListView> findRedeemLendMesg(Map<String, Object> map);

	/**
	 * 导出excel
	 * 2016年3月24日
	 * By 肖长伟
	 * @param exportListParam
	 * @return
	 */
	public List<LendApprovalLookExportExcelEx> getExportExcelList(	Map<String, Object> exportListParam);
	
	/**
	 * 获取要导出的excel数据条数
	 * 2016年4月25日
	 * By 肖长伟
	 * @param exportListParam
	 * @return
	 */
	public int getExportExcelListCnt(Map<String, Object> exportListParam);

	/**
	 * 导出审核通过明细
	 * 2016年3月24日
	 * By 肖长伟
	 * @param exportListParam
	 * @return
	 */
	public List<LendApplyLookExportApprovalPassDetailExcelEx> getExportApprovalPassDetailExcelList(Map<String, Object> exportListParam);
	
	/**
	 * 出借申请审批检索列表专用于计算总金额 
	 * 2016年4月19日
	 * By 刘雄武
	 * @param map
	 * @return
	 */
	public String  loadLendApplyApprovalLookListForMoney(Map<String, Object> map);

	public int getExportApprovalPassDetailExcelListCnt(Map<String, Object> exportListParam);
}