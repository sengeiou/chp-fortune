package com.creditharmony.fortune.back.priority.common.dao;

import java.util.List;
import java.util.Map;

import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.FortuneBatisDao;
import com.creditharmony.fortune.back.priority.common.view.PriorityBackLog;
import com.creditharmony.fortune.back.priority.common.view.PriorityDetailItem;
import com.creditharmony.fortune.back.priority.common.view.PriorityListItemView;
import com.creditharmony.fortune.common.entity.Attachment;

/**
 * 处理详情页的Dao
 * @Class Name PriorityDetailDao
 * @author 郭强
 * @Create In 2017年3月28日
 */
@FortuneBatisDao
public interface PriorityDetailDao extends CrudDao<PriorityDetailItem> {

	/**
	 * 查询优先回款申请详情
	 * 2017年3月28日
	 * By 郭强
	 * @param priorityId
	 * @return
	 */
	PriorityDetailItem getDetail(String priorityId);

	
	/**
	 * 撤销申请
	 * 2017年3月31日
	 * 郭强
	 */
	int updateApply(PriorityListItemView view);


	void insertLog(PriorityBackLog log);


	/**
	 * 获取要操作的数据
	 * @param view
	 * @return
	 */
	List<PriorityDetailItem> getPriortyList(PriorityListItemView view);


	/**
	 * 获取附件
	 */
	List<Attachment> findFileList(Map<String, Object> map);


	PriorityBackLog searchLog(PriorityListItemView view);
	
	
}
