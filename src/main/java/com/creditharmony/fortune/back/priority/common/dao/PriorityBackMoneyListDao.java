package com.creditharmony.fortune.back.priority.common.dao;

import java.util.List;

import com.creditharmony.core.mybatis.paginator.domain.PageBounds;
import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.FortuneBatisDao;
import com.creditharmony.fortune.back.priority.common.view.PriorityListItemView;

/**
 * 处理列表操作的Dao
 * @Class Name PriorityBackMoneyListDao
 * @author 郭强
 * @Create In 2017年3月27日
 */
@FortuneBatisDao
public interface PriorityBackMoneyListDao extends CrudDao<PriorityListItemView> {

	/**
	 * 获取分页对象
	 * 2017年3月27日
	 * By 郭强
	 * @param view
	 * @param pageBounds
	 * @return
	 */
	List<PriorityListItemView> findByParams(PriorityListItemView view, PageBounds pageBounds);

	/**
	 * 导出数据优先回款
	 */

	List<PriorityListItemView> findExportList(PriorityListItemView view);
}
