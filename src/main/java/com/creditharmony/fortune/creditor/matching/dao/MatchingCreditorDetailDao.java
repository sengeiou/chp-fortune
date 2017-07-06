/**
 * @Probject Name: chp-fortune
 * @Path: com.creditharmony.fortune.creditor.matching.daoMatchingCreditorDetailDao.java
 * @Create By 胡体勇
 * @Create In 2015年12月11日 下午3:20:59
 */
package com.creditharmony.fortune.creditor.matching.dao;

import java.util.List;

import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.FortuneBatisDao;
import com.creditharmony.fortune.creditor.matching.entity.ext.BorrowEx;
import com.creditharmony.fortune.creditor.matching.entity.ext.CreditorTradeEx;

/**
 * 已推荐债权查看
 * @Class Name MatchingCreditorDetailDao
 * @author 胡体勇
 * @Create In 2015年12月11日
 */
@FortuneBatisDao
public interface MatchingCreditorDetailDao extends CrudDao<BorrowEx> {
	
	/**
	 * 根据债权推荐id查询对应的出借信息
	 * 2015年12月14日
	 * By 胡体勇
	 * @param creditorTradeEx
	 * @return
	 */
	public CreditorTradeEx findloanInfoByMatchingId(CreditorTradeEx creditorTradeEx);
	
	/**
	 * 根据推荐id查询对应借款人
	 * 2015年12月19日
	 * By 胡体勇
	 * @param creditorTradeE
	 * @return
	 */
	public List<BorrowEx> findTradeidByMatchingId(CreditorTradeEx creditorTradeE);
	
	/**
	 * 根据债权池借款匹配可用债权表
	 * 2015年12月14日
	 * By 胡体勇
	 * @param borrowEx
	 * @return
	 */
	public BorrowEx joinBorrow(BorrowEx borrowEx);
	
	/**
	 * 根据月满盈债权节点匹配月满盈可用债权表
	 * 2015年12月14日
	 * By 胡体勇
	 * @param borrowMonthAbleEx
	 * @return
	 */
	public BorrowEx joinBorrowMonthAble(BorrowEx borrowEx);
	
	/**
	 * 根据债权池借款匹配可用债权表
	 * 2015年12月14日
	 * By 胡体勇
	 * @param borrowEx
	 * @return
	 */
	public BorrowEx joinBorrowForFinishedMc(BorrowEx borrowEx);
	
	/**
	 * 根据月满盈债权节点匹配月满盈可用债权表
	 * 2015年12月14日
	 * By 胡体勇
	 * @param borrowMonthAbleEx
	 * @return
	 */
	public BorrowEx joinBorrowMonthAbleForFinishedMc(BorrowEx borrowEx);
	
	/**
	 * 查询撤销记录
	 * 2015年12月16日
	 * By 胡体勇
	 * @param creditorTradeEx
	 * @return
	 */
	public List<BorrowEx> isCancel(CreditorTradeEx creditorTradeEx);
}
