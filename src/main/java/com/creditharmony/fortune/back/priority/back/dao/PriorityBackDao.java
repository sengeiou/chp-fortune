package com.creditharmony.fortune.back.priority.back.dao;

import com.creditharmony.core.mybatis.paginator.domain.PageBounds;
import com.creditharmony.core.mybatis.paginator.domain.PageList;
import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.FortuneBatisDao;
import com.creditharmony.fortune.back.priority.common.view.PriorityDetailItem;
import com.creditharmony.fortune.back.priority.common.view.PriorityListItemView;

/**
 * 优先回款申请(被退回)
 * @author 郭强
 * 2017年3月30日
 *
 */
@FortuneBatisDao
public interface  PriorityBackDao extends CrudDao<PriorityListItemView> { 

	/**
	 * 优先回款申请(被退回)
	 * @author 郭强
	 * 2017年3月30日
	 */
	public PageList<PriorityListItemView> findByParams(PriorityListItemView view, PageBounds pageBounds);

	public int updateBack(PriorityDetailItem view);

}
