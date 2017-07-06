package com.creditharmony.fortune.verify.dao;

import java.util.Map;

import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.FortuneBatisDao;
import com.creditharmony.fortune.verify.entity.CustomerVerify;

@FortuneBatisDao
public interface CustomerVerifyDao extends CrudDao<CustomerVerify>{
	
    int insert(CustomerVerify record);

    CustomerVerify selectByParams(Map<String,Object> param);

    int update(CustomerVerify record);
}