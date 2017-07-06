package com.creditharmony.fortune.customer.dao;

import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.FortuneBatisDao;
import com.creditharmony.fortune.customer.entity.LoanConfiguration;

/**
 * 出借信息操作类
 * @Class Name LoanConfigurationDao
 * @author 孙凯文
 * @Create In 2015年12月2日
 */
@FortuneBatisDao
public interface LoanConfigurationDao extends CrudDao<LoanConfiguration>{
	
}