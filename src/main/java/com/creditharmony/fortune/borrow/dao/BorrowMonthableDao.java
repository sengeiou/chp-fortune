package com.creditharmony.fortune.borrow.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.creditharmony.core.mybatis.paginator.domain.PageBounds;
import com.creditharmony.core.mybatis.paginator.domain.PageList;
import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.FortuneBatisDao;
import com.creditharmony.fortune.borrow.entity.BorrowMonthable;
import com.creditharmony.fortune.borrow.entity.ex.BorrowMonthableLookEx;
import com.creditharmony.fortune.borrow.view.BorrowMonthableBackToolView;
import com.creditharmony.fortune.borrow.view.BorrowMonthableView;
import com.creditharmony.fortune.template.entity.BorrowMonthableOutExcel;

/**
 * 月满盈可用债权
 * @Class Name BorrowMonthableDao
 * @author 周俊
 * @Create In 2015年12月7日
 */
@FortuneBatisDao
public interface BorrowMonthableDao extends CrudDao<BorrowMonthable>{
	
	/**
	 * 分页查询总记录数
	 * 2015年12月2日
	 * By 周俊
	 * @param map
	 * @param pageBounds
	 * @return
	 */
	public List<BorrowMonthable> findBorrowMonthable(Map<String, Object> map,PageBounds pageBounds);
	
	/**
	 * 不分页查询所有
	 * 2016年4月20日
	 * By 周俊
	 * @param map
	 * @return
	 */
	public List<BorrowMonthable> findBorrowMonthable(Map<String, Object> map);
	
	/**
	 * 查询总记录数
	 * 2015年12月2日
	 * By 周俊
	 * @param map
	 * @param pageBounds
	 * @return
	 */
	public long findAllCount(Map<String, Object> map,PageBounds pageBounds);
	
	/**
	 * 查看月满盈可用债权信息
	 * 2015年12月3日
	 * By 周俊
	 * @param map
	 * @param pageBounds
	 * @return
	 */
	public PageList<BorrowMonthableLookEx> borrowMonthableLook(Map<String, Object> map, PageBounds pageBounds);
	
	/**
	 * 查询总金额数
	 * 2015年12月3日
	 * By 周俊
	 * @param map
	 * @return
	 */
	public BigDecimal findAllMoney(Map<String, Object> map);
	
	/**
	 * 查询总金额数
	 * 2016年5月4日
	 * By 高士芳
	 * @param map
	 * @return
	 */
	public BigDecimal findAllMoney2(Map<String, Object> map);
	
	/**
	 * 获得月满盈可用债权回池信息
	 * 2015年12月7日
	 * By 周俊
	 * @param map
	 * @return
	 */
	public BorrowMonthableBackToolView getBorrowMonthableBackTool(Map<String, Object> map);
	
	/**
	 * 导出Excel
	 * 2015年12月21日
	 * By 周俊
	 * @param map
	 * @return
	 */
	public List<BorrowMonthable> outExcel(Map<String, Object> map);

	/**
	 * 获取满足匹配债权的月满盈数据
	 * 2015年12月29日
	 * By 柳慧
	 * @param borrowMonthable
	 * @return
	 */
	public List<BorrowMonthable> getPpBorrowMonthable(
			BorrowMonthable borrowMonthable);

	/**
	 * 查询满足条件的的月满盈可用债权池集合信息
	 *  by 柳慧
	 * @param map
	 * @param pageBounds
	 * @return
	 */
	 public List<BorrowMonthable> findBorrowMonthableDetail(
			 BorrowMonthableView borrowMonthable, PageBounds pageBounds);
	 /**
	  * 通过债权ID集合 获取月满盈可用债权
	  * @param creditMonableIdsLst
	  * @return
	  */
	public List<BorrowMonthable> getBorrowMonthablesByCreditMonableIds(
			List<String> creditMonableIdsLst);
	
	/**
	 * 三表联查获得月满盈可用债权id集合
	 * 2016年2月29日
	 * By 周俊
	 * @param creditCode
	 * @return
	 */
	public List<String> getCreditMonableIdByJoin(String creditCode);
	
}
