package com.creditharmony.fortune.callcenter.dao;

import java.util.List;
import java.util.Map;

import com.creditharmony.adapter.service.csh.bean.Csh_SaveCustomerByCFInBean;
import com.creditharmony.adapter.service.csh.bean.Csh_SearchCustomerDeatilByCFOutBean;
import com.creditharmony.core.mybatis.paginator.domain.PageBounds;
import com.creditharmony.core.mybatis.paginator.domain.PageList;
import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.FortuneBatisDao;
import com.creditharmony.core.users.entity.User;
import com.creditharmony.fortune.callcenter.entity.CallCenterCustomer;
import com.creditharmony.fortune.common.entity.Org;

/**
 * gaoxu
 * 2016-8-31 14:14:10
 * 电销查询数据
 */
@FortuneBatisDao
public interface CshPhoneSalesDao extends CrudDao<CallCenterCustomer>  {

	
	/**
	 * 查询客户信息（电销）
	 * 2016年8月31日09:48:08
	 * By gaoxu
	 * @param Map 
	 * @return
	 */
	List<Map<String, String>> getCustomerInfoByMap(Map<String, Object> params);
	/**
	 * 插入客户信息（电销）历史表
	 * 2016年8月31日09:48:08
	 * By gaoxu
	 * @param Map 
	 * @return 
	 * @return
	 */
	void addElectricSend(Map<String, Object> params);
	
}
