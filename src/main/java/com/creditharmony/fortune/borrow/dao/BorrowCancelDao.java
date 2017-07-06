package com.creditharmony.fortune.borrow.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.creditharmony.core.mybatis.paginator.domain.PageBounds;
import com.creditharmony.core.persistence.annotation.FortuneBatisDao;
import com.creditharmony.fortune.borrow.entity.Borrow;
import com.creditharmony.fortune.borrow.entity.ex.BorrowCanceEx;
import com.creditharmony.fortune.borrow.entity.ex.BorrowReplaceEx;
import com.creditharmony.fortune.borrow.entity.ex.LoanApplyEx;
import com.creditharmony.fortune.borrow.view.BorrowCancelExcel;
import com.creditharmony.fortune.borrow.view.BorrowCancelView;
import com.creditharmony.fortune.creditor.matching.entity.ext.MatchingCreditorEx;

/**
 * 撤销债权
 * @Class Name BorrowCancelDao
 * @author 周俊
 * @Create In 2015年12月8日
 */
@FortuneBatisDao
public interface BorrowCancelDao {
	
	/**
	 * 查询债权撤销列表
	 * 2015年12月8日
	 * By 周俊
	 * @param map
	 * @param pageBounds
	 * @return
	 */
	public List<BorrowCancelView> findBorrowCancel(Map<String, Object> map);
	
	/**
	 * 查询债权撤销列表待替换
	 * 2016年7月27日
	 * By 高士芳
	 * @param map
	 * @param pageBounds
	 * @return
	 */
	public List<BorrowCancelView> findBorrowCancelForNo(Map<String, Object> map);
	
	/**
	 * 查询债权撤销列表已替换
	 * 2016年7月27日
	 * By 高士芳
	 * @param map
	 * @param pageBounds
	 * @return
	 */
	public List<BorrowCancelView> findBorrowCancelForYes(Map<String, Object> map);
	
	/**
	 * 查询债权撤销列表所有
	 * 2016年7月27日
	 * By 高士芳
	 * @param map
	 * @param pageBounds
	 * @return
	 */
	public List<BorrowCancelView> findBorrowCancelForAll(Map<String, Object> map);
	/**
	 * 查询总计录数
	 * 2016年7月1日
	 * By 周俊
	 * @param map
	 * @return
	 */
	public int findCount(Map<String, Object> map);
	
	/**
	 * 查询总金额
	 * 2015年12月9日
	 * By 周俊
	 * @param map
	 * @return
	 */
	public BigDecimal findCountMoney(Map<String, Object> map);
	
	/**
	 * 查询债权替换列表
	 * 2015年12月15日
	 * By 周俊
	 * @param map
	 * @return
	 */
	public List<BorrowReplaceEx> findBorrowReplace(Map<String, Object> map);
	
	/**
	 * 根据债权价值id获得列表
	 * 2015年12月17日
	 * By 周俊
	 * @param array
	 * @return
	 */
	public List<BorrowReplaceEx> getBorrowList(String[] array);
	
	/**
	 * 获得交易中的带推荐
	 * 2015年12月25日
	 * By 周俊
	 * @param map
	 * @return
	 */
	public List<BorrowCanceEx> findMatchingCreditorBycreditorTrade (Map<String, Object> map);
	
	/**
	 * 获得出借信息
	 * 2015年12月25日
	 * By 周俊
	 * @param map
	 * @return
	 */
	public LoanApplyEx findLoanApplyEx(Map<String, Object> map);
	
	/**
	 * 根据当期推荐id获得可用债权
	 * 2015年12月26日
	 * By 周俊
	 * @param map
	 * @param pageBounds
	 * @return
	 */
	public List<Borrow> findExistingBorrow(Map<String, Object> map,PageBounds pageBounds);
	
	/**
	 * 根据出借编号和最大的期数查询带推荐信息
	 * 2016年1月12日
	 * By 周俊
	 * @param map
	 * @return
	 */
	public MatchingCreditorEx findMatchingCreditorByMaxMatchingNow(Map<String, Object> map);
	
	/**
	 * 查询最大带推荐信息
	 * 2016年1月12日
	 * By 周俊
	 * @param map
	 * @return
	 */
	public List<MatchingCreditorEx> findMaxMatchingCreditor(Map<String, Object> map);
	
	/**
	 * 修改本期债权带推荐信息表
	 * 2016年1月13日
	 * By 周俊
	 * @param map
	 */
	public void updateMatchingCreditorByMatchingId(Map<String, Object> map);

	/**
	 * 修改待替换状态
	 * 2016年12月19日
	 * By 常亚运
	 * 
	 */
	public void updateTopageReplaceStatus();
	
	/**
	 * 导出Excel
	 * 2017年3月22日
	 * By 常亚运
	 * @param search_cxlb
	 * @return
	 */
	public List<BorrowCancelExcel> outExcelForNo(Map<String, Object> map);
	
	public List<BorrowCancelExcel> outExcelForYes(Map<String, Object> map);

	public List<BorrowCancelExcel> outExcelForAll(Map<String, Object> map);

	/**
	 * 查询本期待替换总金额
	 * 2017年3月22日
	 * By 常亚运
	 * @param map
	 * @return
	 */
	public List<BorrowCancelView> findSumCCLMoneyForNo(Map<String, Object> map);

	public List<BorrowCancelView> findSumCCLMoneyForYes(Map<String, Object> map);

	public List<BorrowCancelView> findSumCCLMoneyForAll(Map<String, Object> map);

	/**
	 * 更新替换日期
	 * 2017年3月28日
	 * By 常亚运
	 * @param map
	 */
	public void updateReplaceDay(Map<String, Object> map);

}
