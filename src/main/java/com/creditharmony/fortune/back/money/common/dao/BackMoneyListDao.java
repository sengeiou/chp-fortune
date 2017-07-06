package com.creditharmony.fortune.back.money.common.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.creditharmony.core.mybatis.paginator.domain.PageBounds;
import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.FortuneBatisDao;
import com.creditharmony.fortune.back.money.common.entity.BackMoneyPool;
import com.creditharmony.fortune.back.money.common.entity.ext.ExportBean;
import com.creditharmony.fortune.back.money.common.view.ListItemView;

/**
 * 处理列表操作的Dao
 * @Class Name BackMoneyListDao
 * @author 陈广鹏
 * @Create In 2015年12月2日
 */
@FortuneBatisDao
public interface BackMoneyListDao extends CrudDao<ListItemView> {

	/**
	 * 获取分页对象
	 * 2015年12月4日
	 * By 陈广鹏
	 * @param view
	 * @param pageBounds
	 * @return
	 */
	List<ListItemView> findByParams(ListItemView view, PageBounds pageBounds);

	/**
	 * 获取回款列表，导出数据
	 * 2015年12月25日
	 * By 陈广鹏
	 * @param view
	 * @return
	 */
	List<ExportBean> findExportList(ListItemView view);
	
	/**
	 * 获取操作线上回款所需数据 
	 * 2016年4月29日
	 * By 陈广鹏
	 * @param view
	 * @return
	 */
	List<ExportBean> findBackMoneyDataList(ListItemView view);
	
	/**
	 * 获取导出数据的数量
	 * 2016年3月5日
	 * By 陈广鹏
	 * @param view
	 * @return
	 */
	int countExport(ListItemView view);

	/**
	 * 获取符合条件的累计实际回款金额
	 * 2016年1月13日
	 * By 陈广鹏
	 * @param view
	 * @return
	 */
	BigDecimal getTotalAcutalbackmoney(ListItemView view);

	/**
	 * 获取符合条件的累计出借金额
	 * 2016年1月13日
	 * By 陈广鹏
	 * @param view
	 * @return
	 */
	BigDecimal getTotalLendMoney(ListItemView view);

	/**
	 * 获取符合条件的累计应回款金额
	 * 2016年1月13日
	 * By 陈广鹏
	 * @param view
	 * @return
	 */
	BigDecimal getTotalBackmoney(ListItemView view);

	/**
	 * 查询回款ID列表
	 * 2016年2月2日
	 * By 陈广鹏
	 * @param view
	 * @return
	 */
	List<String> findIdList(ListItemView view);

	/**
	 * 查询要操作的数据
	 * 2016年4月26日
	 * By 陈广鹏
	 * @param view
	 * @return
	 */
	List<BackMoneyPool> findDataList(ListItemView view);

	/**
	 * 获取符合条件的累计应成功金额
	 * 2016年5月16日
	 * By 陈广鹏
	 * @param view
	 * @return
	 */
	BigDecimal getTotalSuccessMoney(ListItemView view);

	/**
	 * 获取符合条件的累计补息后回款金额
	 * 2016年11月7日
	 * By 陈广鹏
	 * @param view
	 * @return
	 */
	BigDecimal getTotalSupplementedMoney(ListItemView view);

	/**
	 * 获取需要补息的数据
	 * 2016年11月9日
	 * By 陈广鹏
	 * @param seachView
	 * @return
	 */
	List<ListItemView> getSuplementList(ListItemView seachView);

	/**
	 * 获取最大回款日期 
	 * 2017年3月1日
	 * By 陈广鹏
	 * @param view
	 * @return
	 */
	Date getMaxBackMoneyDay(ListItemView view);

	/**
	 * 获取数据不同回款日期的天数
	 * 2017年3月1日
	 * By 陈广鹏
	 * @param view
	 * @return
	 */
	int getDiffBackMoneyDays(ListItemView view);

}
