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
 * 呼叫中心Dao
 * @Class Name CallCenterDao
 * @author 肖长伟
 * @Create In 2016年3月1日
 */
@FortuneBatisDao
public interface CallCenterDao extends CrudDao<CallCenterCustomer>  {

	
	/**
	 * 添加呼叫中心客户信息
	 * 2016年6月3日
	 * By 张鸿飞
	 * @param customer
	 * @return
	 */
	void addCustomerInfo4CallCenter(CallCenterCustomer customer);
	/**
	 * 获取呼叫中心客户信息，分页方式
	 * 2016年3月1日
	 * By 肖长伟
	 * @param paramMap
	 * @param pageBounds
	 * @return
	 */
	PageList<CallCenterCustomer> getCallCenterCustomerList(Map<String, Object> params, PageBounds pageBounds);
	
	/**
	 * 获取呼叫中心客户信息
	 * 2016年3月1日
	 * By 肖长伟
	 * @param params
	 * @return
	 */
	List<CallCenterCustomer> getCallCenterCustomerList(Map<String, Object> params);
	
	/**
	 * 获取呼叫中心客户信息(城市经理查询)，分页方式
	 * 2016年3月1日
	 * By 肖长伟
	 * @param params
	 * @param pageBounds
	 * @return
	 */
	PageList<CallCenterCustomer> getCallCenterCustomerList4City(Map<String, Object> params, PageBounds pageBounds);
	
	
	/**
	 * 获取用户部门所在的城市信息
	 * 2016年3月1日
	 * By 肖长伟
	 * @param params
	 * @return   userid, orgid, provinceid, cityid 
	 */
	List<Map<String, Object>> getUserCity(Map<String, Object> params);

	/**
	 * 获取子机构
	 * 2016年3月1日
	 * By 肖长伟
	 * @param orgListParam
	 * @return
	 */
	List<Org> getChildrenOrg(List<String> orgListParam);

	/**
	 * 分配门店
	 * 2016年3月1日
	 * By 肖长伟
	 * @param customer
	 * @return
	 */
	int update(CallCenterCustomer customer);

	/**
	 * 获取呼叫中心客户信息(部门经理查询)，分页方式
	 * 2016年3月1日
	 * By 肖长伟
	 * @param params
	 * @param pageBounds
	 * @return
	 */
	PageList<CallCenterCustomer> getCallCenterCustomerList4Store(Map<String, Object> params, PageBounds pageBounds);

	/**
	 * 查询下级的理财经理
	 * 2016年3月3日
	 * By 肖长伟
	 * @param params
	 * @return
	 */
	List<User> getSubFinancialManagerList(Map<String, Object> params);
	
	/**
	 * 查询回访的客户信息（呼叫中心）
	 * 2016年3月3日
	 * By 肖长伟
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getFeedBackList(Map<String, Object> params);
	
	/**
	 * 查询客户信息（呼叫中心）
	 * 2016年3月3日
	 * By 肖长伟
	 * @param params    customerName、phone、certNum、accountNo、storeName
	 * @return
	 */
	List<Csh_SearchCustomerDeatilByCFOutBean> getCustomerInfo4CallCenter(Map<String, Object> params);
	
}
