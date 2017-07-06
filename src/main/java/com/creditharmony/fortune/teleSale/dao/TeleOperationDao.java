package com.creditharmony.fortune.teleSale.dao;

import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.FortuneBatisDao;
import com.creditharmony.fortune.teleSale.entity.TeleOperation;

/**
 *  公共池操作记录Dao，分配、退回
 * @Class Name TeleOperationDao
 * @author 肖长伟
 * @Create In 2016年2月3日
 */
@FortuneBatisDao
public interface TeleOperationDao extends CrudDao<TeleOperation> {
	
    int insert(TeleOperation record);

    int insertSelective(TeleOperation record);

    TeleOperation selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TeleOperation record);

    int updateByPrimaryKey(TeleOperation record);
}