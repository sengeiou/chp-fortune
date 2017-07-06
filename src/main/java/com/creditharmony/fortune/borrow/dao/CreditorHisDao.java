package com.creditharmony.fortune.borrow.dao;

import java.util.Map;

import com.creditharmony.core.mybatis.paginator.domain.PageBounds;
import com.creditharmony.core.mybatis.paginator.domain.PageList;
import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.FortuneBatisDao;
import com.creditharmony.fortune.borrow.entity.CreditorHis;
import com.creditharmony.fortune.borrow.entity.ex.BorrowMonthSplitHisEx;

/**
 * 历史记录
 * @Class Name CreditorHisDao
 * @author 周俊
 * @Create In 2015年12月7日
 */
@FortuneBatisDao
public interface CreditorHisDao extends CrudDao<CreditorHis>{

	
	/**
	 * 查看分配历史记录
	 * 2015年12月4日
	 * By 周俊
	 * @param map
	 * @param pageBounds
	 * @return
	 */
	PageList<CreditorHis> findBorrowAllotLog(Map<String, Object> map,PageBounds pageBounds);
	
	/**
	 * 查看拆分历史记录
	 * 2015年12月4日
	 * By 周俊
	 * @param map
	 * @param pageBounds
	 * @return
	 */
	PageList<BorrowMonthSplitHisEx> findBorrowMonthSplitLog(Map<String, Object> map,PageBounds pageBounds);
}
