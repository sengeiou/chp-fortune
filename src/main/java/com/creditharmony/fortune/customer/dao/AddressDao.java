package com.creditharmony.fortune.customer.dao;

import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.FortuneBatisDao;
import com.creditharmony.fortune.customer.entity.Address;

/**
 * 地址操作类
 * 
 * @Class Name AddressDao
 * @author 孙凯文
 * @Create In 2015年12月2日
 */
@FortuneBatisDao
public interface AddressDao extends CrudDao<Address> {
}