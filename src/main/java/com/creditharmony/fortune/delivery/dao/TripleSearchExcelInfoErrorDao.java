package com.creditharmony.fortune.delivery.dao;

import java.util.List;

import com.creditharmony.core.mybatis.paginator.domain.PageBounds;
import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.FortuneBatisDao;
import com.creditharmony.fortune.triple.system.entity.ext.IntDeliveryEx;

/**
 * 导表错误查询
 * @Class Name TripleSearchExcelInfoErrorDao
 * @author 胡体勇
 * @Create In 2016年3月2日
 */
@FortuneBatisDao
public interface TripleSearchExcelInfoErrorDao extends CrudDao<IntDeliveryEx>{
	
	/**
	 * 查询列表
	 * 2016年3月2日
	 * By 胡体勇
	 * @return
	 */
	public List<IntDeliveryEx> findList(IntDeliveryEx intDeliveryEx,PageBounds pageBounds);

}
