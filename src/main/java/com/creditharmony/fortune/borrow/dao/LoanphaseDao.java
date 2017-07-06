package com.creditharmony.fortune.borrow.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.FortuneBatisDao;
import com.creditharmony.fortune.borrow.entity.Loanphase;
import com.creditharmony.fortune.creditor.matching.entity.MatchingCreditor;
import com.creditharmony.fortune.creditor.matching.view.LoanphaseBorrowView;

/**
 * 分期收益
 * @Class Name LoanphaseDao
 * @author 周俊
 * @Create In 2015年12月16日
 */
@FortuneBatisDao
public interface LoanphaseDao extends CrudDao<Loanphase> {
	
	/**
	 * 获取当期出借编号下月还本息
	 * 2015年12月16日
	 * By 周俊
	 * @param map
	 * @return
	 */
	public BigDecimal findAllMoney(Map<String,Object> map);
	
	/**
	 * 通过待推荐ID获取借款人编号集合
	 * 2015年12月16日
	 * By 柳慧
	 * @param matchingId 待推荐ID
	 * @return
	 */
	public List<String> getLoanIdsbyMatchingId(String matchingId);
	
	/**
	 * 通过债权推荐ID获取既有及历史债权列表 非月满盈首期
	 * 2015年12月17日
	 * By 柳慧
	 * @param matchingId 债权推荐ID
	 * @return
	 */
	public List<LoanphaseBorrowView> getLoanphaseBorrow(String matchingId);
	
	/**
	 * 修改分期收益表的状态
	 * 2015年12月21日
	 * By 周俊
	 * @param map
	 */
	public void updateStatus(Map<String, Object> map);
	
	/**
	 * 获得分期收益表实例
	 * 2015年12月23日
	 * By 周俊
	 * @param map
	 */
	public Loanphase get(Map<String, Object> map);
	
	/**
	 * 通过债权推荐ID获取既有及历史债权列表 非月满盈非首期
	 * 2015年12月24日
	 * By 柳慧
	 * @param matchingId
	 * @return
	 */
	public List<LoanphaseBorrowView> getLoanphaseBorrowNoFrist(String matchingId);
	
	/**
	 * 通过出借编号和 债权ID 获取该债权上期分期收益信息
	 * 2015年12月29日
	 * By 柳慧
	 * @param lendCode
	 * @param creditValueId
	 * @return
	 */
	public Loanphase getLastloanphase(Map<String, String> map);
	
	/**
	 * 获得分期收益表实例
	 * 2016年1月9日
	 * By 周俊
	 * @param map
	 * @return
	 */
	public Loanphase findLoanphase(Map<String, Object> map);
	
	/**
	 * 查询最大期数
	 * 2016年1月12日
	 * By 周俊
	 * @param map
	 * @return
	 */
	public Integer findMaxPhaseNumber(Map<String, Object> map);
	
	/**
	 * 查询最小期数
	 * 2016年1月12日
	 * By 周俊
	 * @param map
	 * @return
	 */
	public Loanphase findMinPhaseNumberLoanphase(Map<String, Object> map);

	/**
	 * 赎回释放债权时，修改状态
	 * 2016年2月24日
	 * By 陈广鹏
	 * @param loanphase
	 */
	public void redeemUpdateStatus(Loanphase loanphase);

	/**
	 * 获取matchingId关联的分期收益数据
	 * 2016年2月24日
	 * By 陈广鹏
	 * @param matchingId
	 * @return
	 */
	public List<Loanphase> getLoanphasebyMatchingId(Loanphase loanphase);

	/**
	 * 根据债权匹配ID，获取当期总收益
	 * 2016年3月31日
	 * By 陈广鹏
	 * @param matchingCreditor
	 */
	public BigDecimal getTotalInterest(MatchingCreditor matchingCreditor);

	/**
	 * 排它
	 * 2016年4月29日
	 * By 韩龙
	 * @param loanphase
	 * @return
	 */
	public Loanphase getForUpdate(Loanphase loanphase);
	
	/**
	 * 根据MatchingId删除分期收益数据
	 * 2016年5月13日
	 * By 陈广鹏
	 * @param loanphase
	 * @return
	 */
	public int deleteByMatchingId(Loanphase loanphase);
	
	/**
	 * 获取上期的matching_id
	 * 2016年5月23日
	 * By 朱杰
	 * @param map
	 * @return
	 */
	public String getLastMatchingId(Map<String, Object> map);
}
