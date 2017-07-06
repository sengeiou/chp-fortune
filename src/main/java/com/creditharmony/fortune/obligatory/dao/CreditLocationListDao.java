package com.creditharmony.fortune.obligatory.dao;

import java.util.List;
import java.util.Map;

import com.creditharmony.core.mybatis.paginator.domain.PageBounds;
import com.creditharmony.core.persistence.annotation.FortuneBatisDao;
import com.creditharmony.fortune.borrow.entity.Borrow;
import com.creditharmony.fortune.common.entity.Org;
import com.creditharmony.fortune.obligatory.entity.CreditOkListObj;

/**
 * 可用债权配置列表
 * @Class Name CreditLocationListDao 
 * @author 李志伟
 * @Create In 2015年12月16日
 */
@FortuneBatisDao
public interface CreditLocationListDao{
	
	/**
	 * 初始化可用债权配置列表
	 * 2016年1月11日
	 * by 李志伟
	 * @param map
	 * @param pageBounds
	 * @return
	 */
	public List<CreditOkListObj> loadCreditLocationList(Map<String, Object> map, PageBounds pageBounds);
	
	/**
	 * 查询符合条件的数据总量
	 * 2015年12月17日
	 * by 李志伟
	 * @param coo
	 * @return
	 */
	public long findCount(CreditOkListObj coo);
	
	/**
	 * 可用债权配置列表添加页面检索
	 * 2015年12月17日
	 * by 李志伟
	 * @param map
	 * @return
	 */
	public List<CreditOkListObj> addPageSearch(Map<String, Object> map, PageBounds pageBounds);
	
	/**
	 * 添加新的可用债权配置
	 * 2015年12月19日
	 * by 李志伟
	 * @param coo
	 */
	public void add(CreditOkListObj coo);
	
	/**
	 * 根据借款编号查询借款人信息
	 * 2015年12月19日
	 * by 李志伟
	 * @param loanCode
	 * @return
	 */
	public Borrow selectLoanMsg(String loanCode);
	
	/**
	 * 去配置页面
	 * 2015年12月19日
	 * by 李志伟
	 * @param code
	 * @return
	 */
	public CreditOkListObj goEdit(String code);
	
	/**
	 * 可用债权配置修改
	 * 2015年12月21日
	 * by 李志伟
	 * @param coo
	 */
	public void updateMest(CreditOkListObj coo);
	
	/**
	 * 批量删除
	 * 2015年12月22日
	 * by 李志伟
	 * @param map
	 */
	public void batchCreditDel(Map<String,Object> map);
	
	/**
	 * 查询须过滤的的债权人列表
	 * 2016年2月29日
	 * By 柳慧
	 * @param map
	 * @return
	 */
	public List<String> getloanIdCards(Map<String, Object> map);
	
	/**
	 * 根据省份城市ID查找营业部
	 * 2016年3月8日
	 * by 李志伟
	 * @param prctId
	 * @return
	 */
	public List<Org> findOrgList(Map<String, Object> map);
	
	/**
	 * 根据营业部ID获取营业部名称
	 * 2016年3月12日
	 * by 李志伟
	 * @param string
	 * @return
	 */
	public String selectNameById(String string);
	
	/**
	 * 根据省份ID获取营业部
	 * 2016年3月31日
	 * by 李志伟
	 * @param map
	 * @return
	 */
	public List<Org> getOrgByProvince(Map<String, Object> map);
}