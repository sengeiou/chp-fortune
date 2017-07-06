package com.creditharmony.fortune.trusteeship.dao;

import java.util.List;
import java.util.Map;

import com.creditharmony.core.mybatis.paginator.domain.PageBounds;
import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.FortuneBatisDao;
import com.creditharmony.fortune.trusteeship.entity.ProtocalExportExcel;
import com.creditharmony.fortune.trusteeship.entity.TrusteeshipAccount;

/**
 * 金账户开户列表
 * @Class Name TrusteeshipAccountDao
 * @author 朱杰
 * @Create In 2016年2月14日
 */
@FortuneBatisDao
public interface TrusteeshipAccountDao extends CrudDao<TrusteeshipAccount>{
	
	List<TrusteeshipAccount> findList(Map<String, Object> map,PageBounds pageBounds);
	
	List<TrusteeshipAccount> findList(Map<String, Object> map);
	
	List<ProtocalExportExcel> findProtocalList(Map<String, Object> map);
	
	TrusteeshipAccount getAccountByCustomerCode(String customerCode);
	
}