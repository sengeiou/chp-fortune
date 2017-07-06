package com.creditharmony.fortune.delivery.dao;


import java.util.List;

import com.creditharmony.core.mybatis.paginator.domain.PageBounds;
import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.FortuneBatisDao;
import com.creditharmony.fortune.delivery.entity.ext.DeliveryEx;
/**
 * 客户交割历史查询接口
 * @Class Name CustomerDeliveryHistoryDao
 * @author hutiyong
 * @Create In 2015年12月2日
 */
@FortuneBatisDao
public interface CustomerDeliveryHistoryDao extends CrudDao<DeliveryEx> {
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