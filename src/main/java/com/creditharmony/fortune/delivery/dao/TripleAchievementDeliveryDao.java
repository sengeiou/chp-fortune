
package com.creditharmony.fortune.delivery.dao;

import java.util.List;

import com.creditharmony.core.mybatis.paginator.domain.PageBounds;
import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.FortuneBatisDao;
import com.creditharmony.fortune.triple.system.entity.ext.IntDeliveryEx;

/**
 * 三网业绩交割
 * @Class Name TripleAchievementDeliveryDao
 * @author 胡体勇
 * @Create In 2016年2月22日
 */
@FortuneBatisDao
public interface TripleAchievementDeliveryDao extends CrudDao<IntDeliveryEx> {
	
	/**
	 * 分页查询
	 * 2016年2月23日
	 * By 胡体勇
	 * @param intDeliveryEx
	 * @param pageBounds
	 * @return
	 */
	public List<IntDeliveryEx> findList(IntDeliveryEx intDeliveryEx,PageBounds pageBounds);
	
	/**
	 * 三网业绩交割
	 * 2016年2月24日
	 * By 胡体勇
	 * @param intDeliveryEx
	 * @return
	 */
	public int updateAchievement(IntDeliveryEx intDeliveryEx);
	
	/**
	 * 统计总金额
	 * 2016年2月24日
	 * By 胡体勇
	 * @param intDeliveryEx
	 * @return
	 */
	public IntDeliveryEx countMoney(IntDeliveryEx intDeliveryEx);

}
