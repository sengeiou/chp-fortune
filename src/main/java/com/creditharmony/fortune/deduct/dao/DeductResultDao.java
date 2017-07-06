package com.creditharmony.fortune.deduct.dao;

import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.FortuneBatisDao;
import com.creditharmony.fortune.deduct.entity.DeductResult;

/**
 * 划扣申请dao
 * @Class Name DeductApplyDao
 * @author 韩龙
 * @Create In 2015年11月27日
 */
@FortuneBatisDao
public interface DeductResultDao extends CrudDao<DeductResult>{
	
}