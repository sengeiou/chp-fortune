package com.creditharmony.fortune.constants;

/**
 * 
 * @Class Name BorrowConstant
 * @author 周俊
 * @Create In 2015年12月2日
 */
public interface BorrowConstant {
	// 零
	public final static Object ZERO = 0;
	// 1
	public final static Integer FIRST = 1;
	// -1
	public final static Integer NEGATIVE_ONE = -1;
	// 百分比
	public final static String PERCENTAGE = "0.00";
	// 默认拆分利率
	public final static double SPLIT_RATE = 0.483;
	// 可用债权
	public final static String BORROW_EXPORT = "com.creditharmony.fortune.borrow.dao.BorrowDao.exportExcel";
	// 月满盈债权
	public final static String BORROWMONTH_EXPORT = "com.creditharmony.fortune.borrow.dao.BorrowMonthDao.exportExcel";
	// 月满盈可用债权
	public final static String BORROWMONTHABLE_EXPORT = "com.creditharmony.fortune.borrow.dao.BorrowMonthableDao.exportExcel";
	// 冻结债权节点
	public final static String BORROWFREEZE_NODE = "3";
	
	public final static String BORROWFREEZE_EXPORT = "com.creditharmony.fortune.borrow.dao.BorrowFreezeDao.exportExcel";

	// 债权标识
	public final static String TG ="TG";
	
	public final static String FTG ="非TG";
}
