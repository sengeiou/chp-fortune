package com.creditharmony.fortune.borrow.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.creditharmony.core.mybatis.paginator.domain.PageBounds;
import com.creditharmony.core.persistence.annotation.FortuneBatisDao;
import com.creditharmony.fortune.borrow.entity.ex.BorrowFreezeEx;
import com.creditharmony.fortune.borrow.entity.ex.BorrowFreezeLookEx;

/**
 * 债权冻结
 * @Class Name BorrowFreezeDao
 * @author 周俊
 * @Create In 2015年12月10日
 */
@FortuneBatisDao
public interface BorrowFreezeDao {
	
	/**
	 * 查询冻结债权列表
	 * 2015年12月10日
	 * By 周俊
	 * @param map
	 * @param pageBounds
	 * @return
	 */
	public List<BorrowFreezeEx> findBorrowFreeze(Map<String, Object> map, PageBounds pageBounds);
	
	/**
	 * 查询总金额
	 * 2015年12月10日
	 * By 周俊
	 * @param map
	 * @return
	 */
	public BigDecimal findAllMoney(Map<String, Object> map);
	
	/**
	 * 被冻结债权的查看
	 * 2016年2月17日
	 * By 周俊
	 * @param map
	 * @param pageBounds
	 * @return
	 */
	public List<BorrowFreezeLookEx> borrowFreezeLook(Map<String, Object> map,PageBounds pageBounds);
	
	/**
	 * 导出Excel
	 * 2015年12月24日
	 * By 周俊
	 * @param map
	 * @return
	 */
	public List<BorrowFreezeEx> outExcel(Map<String, Object> map);
	
}
