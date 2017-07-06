package com.creditharmony.fortune.borrow.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.creditharmony.core.mybatis.paginator.domain.PageBounds;
import com.creditharmony.core.mybatis.paginator.domain.PageList;
import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.FortuneBatisDao;
import com.creditharmony.fortune.borrow.entity.BorrowMonth;
import com.creditharmony.fortune.borrow.view.BorrowMonthBackToolView;
import com.creditharmony.fortune.template.entity.BorrowMonthOutExcel;
/**
 * 月满盈债权
 * @Class Name BorrowMonthDao
 * @author 周俊
 * @Create In 2015年12月3日
 */
@FortuneBatisDao
public interface BorrowMonthDao extends CrudDao<BorrowMonth>{
 
	/**
	 * 查询总金额
	 * 2016年2月16日
	 * By 周俊
	 * @param map
	 * @return
	 */
	public BigDecimal findAllMoney(Map<String, Object> map);
	
	/**
	 * 查询可用债权列表
	 * 2015年12月3日
	 * By 周俊
	 * @param map
	 * @param pageBounds
	 * @return
	 */
	public PageList<BorrowMonth> findBorrowMonth(Map<String, Object> map,PageBounds pageBounds);
	
	/**
	 * 导出Excel
	 * 2015年12月21日
	 * By 周俊
	 * @param map
	 * @return
	 */
	public List<BorrowMonthOutExcel> outExcel(Map<String, Object> map);
	
	/**
	 * 获取月满盈债权回池信息
	 * 2016年4月29日
	 * By 周俊
	 * @param creditMonId
	 * @return
	 */
	public BorrowMonthBackToolView findBorrowMonthBackTool(String creditMonId);
}
