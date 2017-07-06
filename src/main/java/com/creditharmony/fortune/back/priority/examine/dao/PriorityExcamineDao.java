package com.creditharmony.fortune.back.priority.examine.dao;

import com.creditharmony.core.mybatis.paginator.domain.PageBounds;
import com.creditharmony.core.mybatis.paginator.domain.PageList;
import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.FortuneBatisDao;
import com.creditharmony.fortune.back.priority.common.view.PriorityDetailItem;
import com.creditharmony.fortune.back.priority.common.view.PriorityListItemView;

/**
 * 优先回款审批
 * @author 郭强
 * 2017年3月30日
 *
 */
@FortuneBatisDao
public interface  PriorityExcamineDao extends CrudDao<PriorityListItemView> { 

	/**
	 * 优先回款审批列表
	 * @author 郭强
	 * 2017年3月30日
	 */
	public PageList<PriorityListItemView> findByParams(PriorityListItemView view, PageBounds pageBounds);

	/**
	 *优先回款审批
	 * 2017年3月27日
	 * By 郭强
	 * @param model
	 * @param priorityId
	 * @return
	 */
	public int updateExamine(PriorityDetailItem view);

}
