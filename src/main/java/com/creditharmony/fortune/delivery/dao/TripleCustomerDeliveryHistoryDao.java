
package com.creditharmony.fortune.delivery.dao;

import java.util.List;

import com.creditharmony.core.mybatis.paginator.domain.PageBounds;
import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.FortuneBatisDao;
import com.creditharmony.fortune.triple.system.entity.ext.IntDeliveryEx;

/**
 * 三网客户交割历史查询
 * @Class Name TripleCustomerDeliveryHistoryDao
 * @author 胡体勇
 * @Create In 2016年2月16日
 */
@FortuneBatisDao
public interface TripleCustomerDeliveryHistoryDao extends CrudDao<IntDeliveryEx>{
	
	/**
	 * 分页查询
	 * 2016年2月23日
	 * By 胡体勇
	 * @param intDeliveryEx
	 * @param pageBounds
	 * @return
	 */
	public List<IntDeliveryEx> findList(IntDeliveryEx intDeliveryEx,PageBounds pageBounds);

	public List<IntDeliveryEx> findCardList(IntDeliveryEx intDeliveryEx);
}
