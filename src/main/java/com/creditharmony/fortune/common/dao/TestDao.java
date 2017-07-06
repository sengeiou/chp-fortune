package com.creditharmony.fortune.common.dao;

import java.util.Map;

import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.FortuneBatisDao;
import com.creditharmony.fortune.common.entity.Check;

/**
 * 内部测试用
 * @Class Name TestDao
 * @author 朱杰
 * @Create In 2016年5月4日
 */
@FortuneBatisDao
public interface TestDao{
	
	/**
	 * 插入全程流痕
	 * 2015年12月23日
	 * By 韩龙
	 * @param check
	 * @return
	 */
	public Map<String,Object> select(Map<String,Object> param);

}
