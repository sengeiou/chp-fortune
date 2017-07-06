package com.creditharmony.fortune.back.money.common.dao;

import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.FortuneBatisDao;
import com.creditharmony.fortune.back.money.common.entity.BackMoneyLog;

/**
 * 回款审批记录对应Dao
 * @Class Name BackMoneyLogDao
 * @author 陈广鹏
 * @Create In 2015年12月2日
 */
@FortuneBatisDao
public interface BackMoneyLogDao extends CrudDao<BackMoneyLog> {

}
