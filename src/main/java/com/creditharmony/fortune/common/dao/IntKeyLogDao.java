package com.creditharmony.fortune.common.dao;

import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.FortuneBatisDao;
import com.creditharmony.fortune.common.entity.IntKeyLog;

/**
 * 异常操作dao层
 * 
 * @Class Name FortuneExceptionDao
 * @author 孙凯文
 * @Create In 2016年5月3日
 */
@FortuneBatisDao
public interface IntKeyLogDao extends CrudDao<IntKeyLog> {
}
