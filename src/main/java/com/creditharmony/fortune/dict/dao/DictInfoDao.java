package com.creditharmony.fortune.dict.dao;

import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.FortuneBatisDao;
import com.creditharmony.fortune.dict.entity.DictInfo;

/**
 * 字典DAO接口
 * @Class Name DictInfoDao
 * @author 陈伟东
 * @Create In 2015年12月28日
 */
@FortuneBatisDao
public interface DictInfoDao extends CrudDao<DictInfo> {

}
