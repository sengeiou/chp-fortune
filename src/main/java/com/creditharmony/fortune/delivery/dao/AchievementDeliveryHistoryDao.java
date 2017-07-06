
package com.creditharmony.fortune.delivery.dao;

import java.util.List;

import com.creditharmony.core.mybatis.paginator.domain.PageBounds;
import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.FortuneBatisDao;
import com.creditharmony.fortune.delivery.entity.ext.DeliveryEx;

/**
 * 业绩交割历史查询
 * @Class Name DeliveryAchievementHistory
 * @Author hutiyong
 * @Create 2015年11月23日
 */
@FortuneBatisDao
public interface AchievementDeliveryHistoryDao extends CrudDao<DeliveryEx> {
	/**
	 * 分页查询
	 * 2015年12月3日
	 * By 胡体勇
	 * @param deliveryEx
	 * @param pageBounds
	 * @return
	 */
	public List<DeliveryEx> findList(DeliveryEx deliveryEx,PageBounds pageBounds);

}
