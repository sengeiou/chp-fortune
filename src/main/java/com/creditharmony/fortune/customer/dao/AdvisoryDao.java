package com.creditharmony.fortune.customer.dao;

import java.util.List;

import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.FortuneBatisDao;
import com.creditharmony.fortune.customer.entity.Advisory;

/**
 * @Class Name AdvisoryDao
 * @author 孙凯文
 * @Create In 2015年12月2日
 */
@FortuneBatisDao
public interface AdvisoryDao extends CrudDao<Advisory> {

	public List<Advisory> findPage(Advisory record);
	
	public List<Advisory>getMaxModTimeByCustCode(String customerCode);
}