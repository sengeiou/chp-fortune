package com.creditharmony.fortune.customer.dao;

import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.FortuneBatisDao;
import com.creditharmony.fortune.customer.entity.EmergencyContact;

/**
 * 客户紧急联系人操作类
 * @Class Name EmergencyContactDao
 * @author 孙凯文
 * @Create In 2015年12月2日
 */
@FortuneBatisDao
public interface EmergencyContactDao extends CrudDao<EmergencyContact> {
}