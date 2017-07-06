package com.creditharmony.fortune.back.money.common.dao;

import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.FortuneBatisDao;
import com.creditharmony.fortune.back.money.common.entity.ThirdPlatform;
/**
 * 第三方平台对应Dao
 * @Class Name PlatformDao
 * @author 陈广鹏
 * @Create In 2015年12月3日
 */
@FortuneBatisDao
public interface ThirdPlatformDao extends CrudDao<ThirdPlatform> {

	/**
	 * 根据银行账号获取银行Code
	 * 2016年1月7日
	 * By 陈广鹏
	 * @param fkAccountNo
	 * @return
	 */
	String getIdByBankCode(String bankCode);

}
