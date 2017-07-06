package com.creditharmony.fortune.callcenter.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.adapter.service.triple.bean.TripleNewCustomerInBean;
import com.creditharmony.core.common.type.SystemConfigConstant;
import com.creditharmony.core.common.util.PageUtil;
import com.creditharmony.core.fortune.type.CallCenterStatus;
import com.creditharmony.core.fortune.type.CustomerScource;
import com.creditharmony.core.mybatis.paginator.domain.PageBounds;
import com.creditharmony.core.mybatis.paginator.domain.PageList;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.role.type.FortuneRole;
import com.creditharmony.core.service.CoreManager;
import com.creditharmony.core.users.entity.User;
import com.creditharmony.core.users.util.UserUtils;
import com.creditharmony.fortune.callcenter.dao.CallCenterDao;
import com.creditharmony.fortune.callcenter.entity.CallCenterCustomer;
import com.creditharmony.fortune.common.CustomerCodeGenerator;
import com.creditharmony.fortune.common.entity.Org;
import com.creditharmony.fortune.common.util.BeanUtil;
import com.creditharmony.fortune.customer.dao.CommonDao;
import com.creditharmony.fortune.customer.dao.CustomerDao;
import com.creditharmony.fortune.customer.entity.Customer;
import com.creditharmony.fortune.triple.system.entity.IntPhone;
import com.creditharmony.fortune.triple.system.service.TripleNewCustomerService;

/**
 * 呼叫中心 Manager
 * @Class Name CallCenterManager
 * @author 肖长伟
 * @Create In 2016年3月1日
 */
@Service
@Transactional(value = "fortuneTransactionManager", readOnly = true)
public class CallCenterManager extends CoreManager<CallCenterDao, CallCenterCustomer> {
	
	@Autowired
	private CallCenterDao callCenterDao;
	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private TripleNewCustomerService tripleNewCustomerService;
	@Autowired
	private CommonDao commonDao;
	
	/**
	 * 获取呼叫中心客户信息 分页
	 * 2016年3月1日
	 * By 肖长伟
	 * @param page
	 * @param customer
	 * @return
	 */
	@Transactional(value = "fortuneTransactionManager", readOnly = true)
	public Page<CallCenterCustomer> getCallCenterCustomerList(Page<CallCenterCustomer> page, CallCenterCustomer customer) {
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("customerName", customer.getCustomerName());
		params.put("customerMobilephone", customer.getCustomerMobilephone());
		params.put("customerTel", customer.getCustomerTel());
		params.put("dictCustomerSex", customer.getDictCustomerSex());
		params.put("province", customer.getProvince());
		params.put("city", customer.getCity());
		params.put("status", customer.getStatus());
		params.put("managerId", customer.getManagerId());
		params.put("storeOrgId", customer.getStoreOrgId());
		params.put("teamId", customer.getTeamId());
		params.put("cityManagerId", customer.getCityManagerId());
		
		PageBounds pageBounds = new PageBounds(page.getPageNo(), 	page.getPageSize());
		PageList<CallCenterCustomer> pageList = (PageList<CallCenterCustomer>) callCenterDao.getCallCenterCustomerList(params, pageBounds);
		PageUtil.convertPage(pageList, page);
		return page;
	}

	/**
	 * 获取呼叫中心客户信息
	 * 2016年3月1日
	 * By 肖长伟
	 * @param customer
	 * @return
	 */
	public List<CallCenterCustomer> getCallCenterCustomerList(CallCenterCustomer customer) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("customerName", customer.getCustomerName());
		params.put("customerMobilephone", customer.getCustomerMobilephone());
		params.put("customerTel", customer.getCustomerTel());
		params.put("dictCustomerSex", customer.getDictCustomerSex());
		params.put("province", customer.getProvince());
		params.put("city", customer.getCity());
		params.put("status", customer.getStatus());
		params.put("managerId", customer.getManagerId());
		params.put("storeOrgId", customer.getStoreOrgId());
		params.put("teamId", customer.getTeamId());
		params.put("cityManagerId", customer.getCityManagerId());
		
		List<CallCenterCustomer> rsList = callCenterDao.getCallCenterCustomerList(params);
		return rsList;
	}
	
	/**
	 * 根据客户Id，查找客户
	 * 2016年3月1日
	 * By 肖长伟
	 * @param id
	 * @return
	 */
	public CallCenterCustomer getCallCenterCustomerById(String id) {
		CallCenterCustomer customer = new CallCenterCustomer();
		customer.setId(id);
		List<CallCenterCustomer> list = getCallCenterCustomerList(customer);
		if (list != null && list.size() > 0) {
			return list.get(0);
		} else {
			return customer;
		}
		
	}
	
	/**
	 * 根据客户Id，查找客户
	 * 2016年3月1日
	 * By 肖长伟
	 * @param customer
	 * @return
	 */
	public CallCenterCustomer getCallCenterCustomerById(CallCenterCustomer customer) {
		List<CallCenterCustomer> list = getCallCenterCustomerList(customer);
		if (list != null && list.size() > 0) {
			return list.get(0);
		} else {
			return customer;
		}
		
	}
	
	/**
	 * 根据城市经理的 cityId查对应的呼叫中心客户
	 * 2016年3月1日
	 * By 肖长伟
	 * @param page
	 * @param customer
	 * @return
	 */
	public Page<CallCenterCustomer> getList4CityManager(	Page<CallCenterCustomer> page, CallCenterCustomer customer) {
		PageBounds pageBounds = new PageBounds(page.getPageNo(), 	page.getPageSize());
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("customerName", customer.getCustomerName());
		params.put("customerMobilephone", customer.getCustomerMobilephone());
		params.put("customerTel", customer.getCustomerTel());
		params.put("dictCustomerSex", customer.getDictCustomerSex());
		params.put("province", customer.getProvince());
		params.put("city", customer.getCity());
		params.put("status", CallCenterStatus.IN_CITY.id);
		params.put("managerId", customer.getManagerId());
		params.put("storeOrgId", customer.getStoreOrgId());
		params.put("teamId", customer.getTeamId());
		params.put("cityManagerId", customer.getCityManagerId());
		
		List<Map<String, Object>> userCitys = getUserCityOrg(FortuneRole.CITY_MANAGER.id);  
		
		if (userCitys != null && userCitys.size() > 0) {
			List<String> cityListP = new ArrayList<String>();
			params.put("cityList", cityListP);
			
			for (Map<String, Object> item : userCitys) {
				String cityId = String.valueOf(item.get("cityid"));  //userId, orgId, provinceId, cityId 
				cityListP.add(cityId);
			}
			if (cityListP.size() == 0) {  //为空时，添加空字符，避免sql报错
				cityListP.add("");
			}
		}
		PageList<CallCenterCustomer> pageList = (PageList<CallCenterCustomer>) callCenterDao.getCallCenterCustomerList4City(params, pageBounds);
		PageUtil.convertPage(pageList, page);
		return page;
	}

	
	/**
	 * 获取城市经理的UserCity 、Org， 
	 * 2016年3月1日  userid, orgid, provinceid, cityid 
	 * By 肖长伟
	 * @return
	 */
	private List<Map<String, Object>> getUserCityOrg( String roleId) {
		User user = (User)UserUtils.getSession().getAttribute(SystemConfigConstant.SESSION_USER_INFO);
		return getUserCityOrg(roleId, user.getId());
	}
	
	/**
	 * 获取用户的UserCity 、Org， 
	 * 2016年3月2日
	 * By 肖长伟
	 * @param roleId
	 * @param userId
	 * @return   userid, orgid, provinceid, cityid
	 */
	private List<Map<String, Object>> getUserCityOrg( String roleId, String userId) {
		Map<String, Object> userCityParam = new HashMap<String, Object>();
		userCityParam.put("roleId", roleId);
		userCityParam.put("userId", userId);
		return callCenterDao.getUserCity(userCityParam);
	}

	/**
	 * 获取当前用户部门的子部门（下一级）
	 * 2016年3月1日
	 * By 肖长伟
	 * @return
	 */
	public List<Org> getChildrenOrg() {
		List<Map<String, Object>> orgList = getUserCityOrg(FortuneRole.CITY_MANAGER.id);  //当前城市经理角色的orgId
		List<String> orgListParam = new ArrayList<String>();
		for (Map<String, Object> item : orgList) {
			String orgId = String.valueOf(item.get("orgid"));
			orgListParam.add(orgId);
		}
		if (orgListParam.size() == 0) {
			orgListParam.add("");
		}
		List<Org> orgListRs = callCenterDao.getChildrenOrg(orgListParam);
		return orgListRs;
	}

	/**
	 * 分配门店
	 * 2016年3月1日
	 * By 肖长伟
	 * @param customer
	 */
	@Transactional(value = "fortuneTransactionManager", readOnly = false)
	public int distributeStore(CallCenterCustomer customer) {
		customer.preUpdate();
		customer.setCityManagerId(UserUtils.getUserId());
		customer.setStatus(CallCenterStatus.IN_STORE.id);
		int rs = callCenterDao.update(customer);
		return rs;
	}

	/**
	 * 查询门店的呼叫中心客户
	 * 2016年3月1日
	 * By 肖长伟
	 * @param page
	 * @param customer
	 * @return
	 */
	public Page<CallCenterCustomer> getList4StoreManager(Page<CallCenterCustomer> page, CallCenterCustomer customer) {
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("customerName", customer.getCustomerName());
		params.put("customerMobilephone", customer.getCustomerMobilephone());
		params.put("customerTel", customer.getCustomerTel());
		params.put("dictCustomerSex", customer.getDictCustomerSex());
		params.put("province", customer.getProvince());
		params.put("city", customer.getCity());
		params.put("status", CallCenterStatus.IN_STORE.id);
		params.put("managerId", customer.getManagerId());
		params.put("storeOrgId", customer.getStoreOrgId());
		params.put("teamId", customer.getTeamId());
		params.put("cityManagerId", customer.getCityManagerId());
		
		List<Map<String, Object>> userStoreRoleOrgList = getUserCityOrg(FortuneRole.STORE_MANAGER.id);  
		
		if (userStoreRoleOrgList != null && userStoreRoleOrgList.size() > 0) {
			List<String> orgListP = new ArrayList<String>();
			params.put("orgList", orgListP);
			
			for (Map<String, Object> item : userStoreRoleOrgList) {
				String orgId = String.valueOf(item.get("orgid"));  //userid, orgid, provinceid, cityid 
				orgListP.add(orgId); 
			}
			if (orgListP.size() == 0) {  //为空时，添加空字符，避免sql报错
				orgListP.add("");
			}
		}
		PageBounds pageBounds = new PageBounds(page.getPageNo(), 	page.getPageSize());
		PageList<CallCenterCustomer> pageList = (PageList<CallCenterCustomer>) callCenterDao.getCallCenterCustomerList4Store(params, pageBounds);
		PageUtil.convertPage(pageList, page);
		return page;
	}

	/**
	 * 获取下属 理财经理
	 * 2016年3月2日
	 * By 肖长伟
	 * @return
	 */
	public List<User> getSubFinancialManagerList() {
		
		Map<String, Object> params = new HashMap<String, Object>();
		List<String> orgListP = getOrgOfUserRole(FortuneRole.STORE_MANAGER.id);
		params.put("orgList", orgListP);
		params.put("userId", UserUtils.getUserId());
		List<String> roleIdList = new ArrayList<String>();
		roleIdList.add(FortuneRole.FINANCING_MANAGER.id);
		params.put("roleIdList", roleIdList);
		List<User> userList = callCenterDao.getSubFinancialManagerList(params);
		return userList;
	}

	/**
	 * 获取用户指定角色的机构list
	 * 2016年3月2日
	 * By 肖长伟
	 * @param roleId
	 * @return
	 */
	private List<String> getOrgOfUserRole(String roleId) {
		List<Map<String, Object>> storeManagerOrgList = getUserCityOrg(roleId);
		List<String> orgListP = new ArrayList<String>();
		if (storeManagerOrgList != null) {
			for (Map<String, Object> item : storeManagerOrgList) {
				String orgId = String.valueOf(item.get("orgid"));  //userid, orgid, provinceid, cityid 
				orgListP.add(orgId); 
			}
			if (orgListP.size() == 0) {  //为空时，添加空字符，避免sql报错
				orgListP.add("");
			}
		}
		if (orgListP.size() == 0) {
			orgListP.add("");
		}
		return orgListP;
	}
	
	/**
	 * 获取用户指定角色的机构list
	 * 2016年3月2日
	 * By 肖长伟
	 * @param roleId
	 * @param userId
	 * @return
	 */
	private List<String> getOrgOfUserRole(String roleId, String userId) {
		List<Map<String, Object>> storeManagerOrgList = getUserCityOrg(roleId, userId);
		List<String> orgListP = new ArrayList<String>();
		if (storeManagerOrgList != null) {
			for (Map<String, Object> item : storeManagerOrgList) {
				String orgId = String.valueOf(item.get("orgid"));  //userid, orgid, provinceid, cityid 
				orgListP.add(orgId); 
			}
		}
		return orgListP;
	}
	
	/**
	 * 分配理财经理
	 * 2016年3月2日
	 * By 肖长伟
	 * @param customer
	 */
	@Transactional(value = "fortuneTransactionManager", readOnly = false)
	public String distributeManager(CallCenterCustomer callCustomer) {
		//判断是否存在此客户（根据客户手机号）
		Customer customer = new Customer(); 
		customer.setCustMobilephone(callCustomer.getCustomerMobilephone());
		Customer customerQuery = customerDao.getCustomer(customer);
		if (customerQuery != null && StringUtils.isNotBlank(customerQuery.getId())) {  //存在则，什么都不做
			logger.debug("" + "客户已存在，");
			return "客户已存在";
		} else {  //不存在时，则新增。调用三网，判断是否有理财经理，若没有则使用指定的，若有理财经理则更新成已有的理财经理
			//TODO 三网验证
			CallCenterCustomer callCenterCustomer = getCallCenterCustomerById(callCustomer.getId());  //库中的全数据
			//三网注册，若已有理财经理则返回，若没有则返回
			TripleNewCustomerInBean tripleNewCustomerBean = new TripleNewCustomerInBean();
			tripleNewCustomerBean.setPhone(callCenterCustomer.getCustomerMobilephone());
			tripleNewCustomerBean.setEmpCode(callCustomer.getManagerId());
			IntPhone intPhone = tripleNewCustomerService.registerCustomer(BeanUtil.conv2IntCustomerBean(tripleNewCustomerBean,null));
			if (intPhone == null) {
				logger.error("三网注册用户有异常");
				return "三网注册用户有异常";
			}
			Customer customerInsert = new Customer();
			customerInsert.preInsert();
			customerInsert.setCustCode(CustomerCodeGenerator.getNextCode());
			customerInsert.setCustName(callCenterCustomer.getCustomerName());
			customerInsert.setDictCustSex(callCenterCustomer.getDictCustomerSex());
			customerInsert.setCustMobilephone(callCenterCustomer.getCustomerMobilephone());
			customerInsert.setCustPhone(callCenterCustomer.getCustomerTel());
			customerInsert.setProvinceId(callCenterCustomer.getProvince());
			customerInsert.setCityId(callCenterCustomer.getCity());
			customerInsert.setManagerCode(intPhone.getEmpCode());
			customerInsert.setDictCustSource(CustomerScource.HJZX.value);  //  客户信息来源 呼叫中心
			
			//根据理财经理id查所在的机构
			List<String> orgList = getOrgOfUserRole(FortuneRole.FINANCING_MANAGER.id, intPhone.getEmpCode());  //理财经理角色的 org_id
			if (orgList == null || orgList.size() == 0) {
				return "取理财经理的机构有误";
			}
			customerInsert.setOrgCode(orgList.get(0));  //理财经理所在的机构   TODO --修改成理财经理所在的部门
			customerDao.insert(customerInsert); // 新增客户
			//更新呼叫中心表，当前理财经理、状态、分配人
			callCenterCustomer.preUpdate();
			callCenterCustomer.setStoreManagerId(UserUtils.getUserId());
			//callCenterCustomer.setManagerId(intPhone.getEmpCode());
			callCenterCustomer.setStatus(CallCenterStatus.HAVE_MANAGER.id);
			callCenterDao.update(callCenterCustomer);
		}
		
		return "分配成功";
	}
	
}
