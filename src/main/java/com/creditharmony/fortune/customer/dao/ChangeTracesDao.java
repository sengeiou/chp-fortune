package com.creditharmony.fortune.customer.dao;

import java.util.List;

import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.FortuneBatisDao;
import com.creditharmony.fortune.customer.entity.ChangeTraces;

/**
 * @Class Name CustomerModifyHisDao
 * @author 孙凯文
 * @Create In 2015年11月30日
 */
@FortuneBatisDao
public interface ChangeTracesDao extends CrudDao<ChangeTraces> {
	/**
	 * 分页查询变更历史
	 * 2015年12月7日
	 * By 孙凯文
	 * @param record
	 * @return 
	 */
	public List<ChangeTraces> findPage(ChangeTraces changeTraces);
}
