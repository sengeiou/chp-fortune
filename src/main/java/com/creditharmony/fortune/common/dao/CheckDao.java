package com.creditharmony.fortune.common.dao;

import org.apache.ibatis.annotations.Param;

import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.FortuneBatisDao;
import com.creditharmony.fortune.common.entity.Check;

/**
 * 全程流程
 * @Class Name CheckDao
 * @author 韩龙
 * @Create In 2015年12月10日
 */
@FortuneBatisDao
public interface CheckDao extends CrudDao<Check>{
	
	/**
	 * 插入全程流痕
	 * 2015年12月23日
	 * By 韩龙
	 * @param check
	 * @return
	 */
	public int insertSelective(Check check);
	
	public void updateLendCode(@Param("oldLendCode")String oldLendCode, @Param("newLendCode")String newLendCode);

}
