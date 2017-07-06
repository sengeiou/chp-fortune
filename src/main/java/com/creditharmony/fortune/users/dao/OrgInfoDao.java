package com.creditharmony.fortune.users.dao;

import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.FortuneBatisDao;
import com.creditharmony.fortune.users.entity.OrgInfo;


/**
 * 组织机构dao
 * @Class Name OrgInfoDao
 * @author 张永生
 * @Create In 2015年12月8日
 */
@FortuneBatisDao
public interface OrgInfoDao extends CrudDao<OrgInfo> {

	
}
