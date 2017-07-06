package com.creditharmony.fortune.back.priority.common.dao;

import com.creditharmony.core.persistence.annotation.FortuneBatisDao;
import com.creditharmony.fortune.back.money.common.view.ItemView;
import com.creditharmony.fortune.back.money.common.view.ListItemView;

/**
 * 操作回款Dao
 * @Class Name PriorityBackMoneyDao
 * @author 郭强
 * @Create In 2017年3月28日
 */
@FortuneBatisDao
public interface PriorityBackMoneyDao {


	/**
	 * 更新回款表
	 */
	int updateBackMoney(ItemView itemView);

	/**
	 * 获取回款数据
	 * @param string 
	 * @return
	 */
	ListItemView searchBackMoney(String string);
}
