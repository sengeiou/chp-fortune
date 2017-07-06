package com.creditharmony.fortune.look.apply.dao;

import java.util.List;
import java.util.Map;

import com.creditharmony.core.mybatis.paginator.domain.PageBounds;
import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.FortuneBatisDao;
import com.creditharmony.fortune.customer.entity.Customer;
import com.creditharmony.fortune.customer.entity.CustomerAccount;
import com.creditharmony.fortune.customer.entity.LoanApply;
import com.creditharmony.fortune.look.apply.entity.LendApplyLookExportLendExcelEx;
import com.creditharmony.fortune.look.apply.view.LendApplyLookListView;
import com.creditharmony.fortune.look.apply.view.LendLookListView;

/**
 * 出借申请查看
 * @Class Name LendApplyLookDao 
 * @author 李志伟
 * @Create In 2015年12月24日
 */
@FortuneBatisDao
public interface LendApplyLookDao extends CrudDao<LoanApply>{

	/**
	 * 出借申请查看列表检索
	 * 2016年1月21日
	 * by 李志伟
	 * @param map
	 * @param pageBounds
	 * @return
	 */
	public List<LendApplyLookListView> loadLendApplyLookList(Map<String, Object> map,  PageBounds pageBounds);

	/**
	 * 查询数据库中的数据数量
	 * 2015年12月25日
	 * by 李志伟
	 * @return
	 */
	public long findCount();
	
	/**
	 * 查询客户信息
	 * 2015年12月25日
	 * by 李志伟
	 * @param code
	 * @return
	 */
	public Customer findCustomerMesg(String code);
	
	/**
	 * 初始化出借信息
	 * 2015年12月25日
	 * by 李志伟
	 * @param code
	 * @return
	 */
	public LoanApply findLendMesg(String code);

	/**
	 * 查询客户的银行信息
	 * 2016年1月21日
	 * by 李志伟
	 * @param map
	 * @return
	 */
	public List<CustomerAccount> findBankMesg(Map<String, Object> map);
	
	/**
	 * 查询提前赎回的出借信息
	 * 2016年3月1日
	 * by 李志伟
	 * @param map
	 * @return
	 */
	public List<LendLookListView> findRedeemLendMesg(Map<String, Object> map);
	
	
	public List<LendApplyLookExportLendExcelEx> getExportLendExcelList(Map<String, Object> params);
	
	
	public Map<String, Object> getApplyStatistics(Map<String, Object> params);

	/**
	 * 获取出借总金额
	 * 2016年4月20日
	 * By 郭才林
	 * @param map
	 * @param pageBounds
	 */
	public String sumLendAmount(Map<String, Object> map, PageBounds pageBounds);

	/**
	 * 查询导出出借Excel的数据量
	 * 2016年4月24日
	 * By 肖长伟
	 * @param exportListParam
	 * @return
	 */
	public int getTotalCntOfExportLendExcelList(Map<String, Object> exportListParam);

	/**
	 * 校验这条出借的状态
	 * 2017年3月29日
	 * 郭强
	 * @param lendCode
	 * @return
	 */
	public LendApplyLookListView searchLendState(String lendCode);

	
	
}