package com.creditharmony.fortune.teleSale.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.common.util.ListUtils;
import com.creditharmony.core.common.util.PageUtil;
import com.creditharmony.core.fortune.type.CustomerMarkState;
import com.creditharmony.core.fortune.type.TeleCustomPoolType;
import com.creditharmony.core.mybatis.paginator.domain.PageBounds;
import com.creditharmony.core.mybatis.paginator.domain.PageList;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.role.type.FortuneRole;
import com.creditharmony.core.service.CoreManager;
import com.creditharmony.core.users.dao.UserRoleOrgDao;
import com.creditharmony.core.users.entity.Role;
import com.creditharmony.core.users.entity.User;
import com.creditharmony.core.users.entity.UserRoleOrg;
import com.creditharmony.core.users.util.UserUtils;
import com.creditharmony.fortune.common.entity.UserRoleOrgEx;
import com.creditharmony.fortune.customer.entity.Advisory;
import com.creditharmony.fortune.customer.entity.Customer;
import com.creditharmony.fortune.customer.service.CustomerManager;
import com.creditharmony.fortune.delivery.service.CustomerDeliveryManager;
import com.creditharmony.fortune.look.apply.util.ExportExcel4BD;
import com.creditharmony.fortune.teleSale.dao.CustomerPoolDao;
import com.creditharmony.fortune.teleSale.entity.CustomerPool;
import com.creditharmony.fortune.teleSale.entity.TeleOperation;
import com.creditharmony.fortune.teleSale.entity.ex.CustomerPoolEx;
import com.creditharmony.fortune.teleSale.entity.ex.CustomerPoolExportModel;

/**
 * 电销公共池服务
 * @Class Name TeleSalePoolManager
 * @author 肖长伟
 * @Create In 2016年2月1日
 */
@Service
public class CustomerPoolManager extends CoreManager<CustomerPoolDao, CustomerPool> {
	
	@Autowired
	private CustomerPoolDao customerPoolDao;
	@Autowired
	private  CustomerManager customerManager;
	@Autowired
	private TeleOperationManager teleOperationManager;
	@Autowired
	CustomerDeliveryManager customerDeliveryManager;
	
	@Autowired
	private UserRoleOrgDao userRoleOrgDao;
	
	/**
	 * 取公共池数据，在池中的数据
	 * 2016年2月16日
	 * By 肖长伟
	 * @param page
	 * @param customerPool
	 * @return
	 */
	public Page<CustomerPool> getCustomerInPool(Page<CustomerPool> page, CustomerPool customerPool) {
		
		//查当前登录人的角色，若为电销专员，则只查分配给此人的公共池客户
		
//		Role role = UserUtils.getUser().getRole();
//		//TODO --去掉此注释
////		String isTelManager = FortuneRole.TELE_FINANCING_MANAGER.id.equals(role.getRoleType()) ? "1" : "0";
//		String isTelManager = "1";
//		String teleManagerId = UserUtils.getUser().getId(); //当前登录人的id
		
		//设置城市条件，解析出省份、城市，set到customerPool中
		if (StringUtils.isNotBlank(customerPool.getCityIdTemp())) {
			String[] provecity = customerPool.getCityIdTemp().trim().split("_");
			if (provecity != null && provecity.length == 2) {
				customerPool.setProvinceId(provecity[0]);
				customerPool.setCityId(provecity[1]);
			}
		}
		//从公共池查询数据，按条件分页
		PageBounds pageBounds = new PageBounds(page.getPageNo(), 	page.getPageSize());
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("dataType", TeleCustomPoolType.IN_POOL.value);
		paramMap.put("customerName", customerPool.getCustomerName());
		paramMap.put("customerTel", customerPool.getCustomerTel());
		paramMap.put("card", customerPool.getCard());
		paramMap.put("cityId", customerPool.getCityId());
		paramMap.put("provinceId", customerPool.getProvinceId());
//		paramMap.put("isTelManager", isTelManager);
//		paramMap.put("teleManagerId", teleManagerId);
		PageList<CustomerPool> pageList = (PageList<CustomerPool>) customerPoolDao.selectList(paramMap, pageBounds);
		PageUtil.convertPage(pageList, page);
		
		//处理查询的公共池客户列表数据
		processCustomerPoolData(page.getList());
		return page;
	}
	
	
	/**
	 * 处理查询的公共池客户列表数据
	 * 2016年4月12日
	 * By 肖长伟
	 * @param list
	 */
	private void processCustomerPoolData(List<CustomerPool> list) {
		//手机号加*号
		if (ListUtils.isEmptyList(list)) {
			return;
		}
		for (CustomerPool item : list) {
			String phoneNo = item.getCustomerTel();
			if (StringUtils.isNotBlank(phoneNo)) {
				//格式化手机号
				char [] charArr = item.getCustomerTel().toCharArray();
				item.setCustomerTel(new String(formatePhone(charArr)));
			}
		}
	}



	/**
	 * 获取用户的电销角色类型  0无电销角色、1 电销理财专员、2电销其他角色
	 * 2016年2月23日
	 * By 肖长伟
	 * @return   0无电销角色、1 电销理财专员、2电销其他角色
	 */
	public String getUserTeleRoleType() {
		List<Role> roleList = UserUtils.getUser().getCurrentUser().getRoleList();
		//判断角色
		if (roleList == null || roleList.size() == 0) {
			return "0";
		}
		String teleRoleType = "0";
		int flag = 0;
		for (Role role : roleList) {
			String roleId = role.getId();
			
			if (FortuneRole.TELE_FINANCING_MANAGER.id.equals(roleId)) {  //  电销理财专员
				flag += 1;
			} else if (FortuneRole.TELE_TEAM_MANAGER.id.equals(roleId)) {  //电销经理及以上
				flag += 2;
				break;
			} else if (FortuneRole.TELE_STORE_MANAGER.id.equals(roleId)) {  //电销经理及以上
				flag += 2;
				break;
			} else if (FortuneRole.TELE_DEPARTMENT_MANAGER.id.equals(roleId)) {  //电销经理及以上
				flag += 2;
				break;
			}  else if (FortuneRole.TELE_DATA_MANAGER.id.equals(roleId)) {  //电销经理及以上
				flag += 2;
				break;
			} else {
				flag += 0;
			}
		}
		if (flag == 0) {
			teleRoleType = "0";
		} else if (flag == 1) {
			teleRoleType = "1";
		} else if (flag >= 2) {
			teleRoleType = "2";
		}
		return teleRoleType;
	}
	
	/**
	 * 根据公共池的Id，获取客户信息
	 * 2016年2月1日
	 * By 肖长伟
	 * @param pool
	 * @return
	 */
	public CustomerPool getCustomerInPoolByPoolId(CustomerPool pool) {
		return customerPoolDao.selectByPrimaryKey(pool.getId());
	}

	/**
	 * 分配理财经理操作 
	 * 	1.	新增分配操作记录到  tele_operation
	 * 	2.	更新customer表中电销相关的信息
	 * 	3.	理财经理交割
	 *  4.	发消息到三网
	 *  5. 更新公共池中此客户的状态为2
	 * 2016年2月1日
	 * By 肖长伟
	 * @param customerPoolEx
	 */
	@Transactional(readOnly = false, value = "fortuneTransactionManager")
	public void distributeManger(CustomerPoolEx customerPoolEx) {
		//查找客户
		Customer customer = new Customer();
		customer.setCustCode(customerPoolEx.getCustomerCode());
		customer = customerManager.getCustomer(customer);
		//1.	新增分配操作记录到  tele_operation  【原理财经理、新理财经理、客户id、操作员Id】
		createTeleOperation(customer, customerPoolEx.getManagerId());
		//2.	更新customer表中电销相关的信息  
		modifyCustomerTeleSaleInfo(customerPoolEx.getUserId(), customer, CustomerMarkState.DX.value); //电销
		//3.	理财经理交割 
		deliveryManager(customerPoolEx, customer);
		//4.	更新公共池中此客户的状态为2
		modifyTeleSalePoolCustomerStatus(customerPoolEx, customer, TeleCustomPoolType.DISTRIBUTE.value);
		
	}
	
	/**
	 * 交割理财经理
	 * 2016年2月16日
	 * By 肖长伟
	 * @param customerPoolEx
	 * @param customer
	 */
	@Transactional(readOnly = false, value = "fortuneTransactionManager")
	private void deliveryManager(CustomerPoolEx customerPoolEx,	Customer customer) {
		//理财经理交割 
		Map<String, String> params = new HashMap<String, String>();
		params.put("custCode", customer.getCustCode());
		params.put("fManagerCode", customer.getManagerCode());
		params.put("nfManagerCode", customerPoolEx.getManagerId());
		params.put("toDelDate", "");
		params.put("delDate", "");
		customerDeliveryManager.custDelivery(params);
	}

	/**
	 * 更新客户表中电销状态信息
	 * 2016年2月16日
	 * By 肖长伟
	 * @param customerPoolEx
	 * @param customer
	 */
	@Transactional(readOnly = false, value = "fortuneTransactionManager")
	private void modifyCustomerTeleSaleInfo(String teleManagerId, Customer customer, String custMark) {
		//-更新customer表中电销相关的信息   
		customer.setTeleManagerId(teleManagerId); //电销专员的ID
		customer.setTeleTime(new Date());
		customer.setCustMark(custMark);
		customer.preUpdate();
		customerManager.save(customer);
	}

	/**
	 *  更新公共池中xx客户的状态为2
	 * 2016年2月3日
	 * By 肖长伟
	 * @param customer
	 */
	@Transactional(readOnly = false, value = "fortuneTransactionManager")
	private void modifyTeleSalePoolCustomerStatus(CustomerPoolEx customerPoolEx, Customer customer, String dataType) {
		CustomerPool customerPool = new CustomerPool();
		customerPool.setId(customerPoolEx.getId());
		customerPool.setDataType(dataType);
		customerPool.preUpdate();
		super.dao.update(customerPool);
	}

	/**
	 * 新增“理财经理分配”记录
	 * 2016年2月1日
	 * By 肖长伟
	 */
	@Transactional(readOnly = false, value = "fortuneTransactionManager")
	private void createTeleOperation(Customer customer, String newManagerId) { 
		TeleOperation operation = new TeleOperation();
		operation.setCustomerId(customer.getCustCode());       //客户的Code
		operation.setOldManagerId(customer.getManagerCode());         //原理财经理
		operation.setNewManagerId(newManagerId);  //新理财经理
		operation.setOperationType(TeleCustomPoolType.DISTRIBUTE.value); //操作类型为：2分配
		teleOperationManager.insertTeleOperation(operation);
	}
	
	/**
	 * 查理财经理
	 * 2016年2月2日
	 * By 肖长伟
	 * @param managerBeanEx
	 * @param page
	 * @return
	 */
	public Page<UserRoleOrgEx> findManager(UserRoleOrgEx managerBeanEx, Page<UserRoleOrgEx> page) {
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("teamRoleId", FortuneRole.TEAM_MANAGER.id);
		List<String> roleIdList = new ArrayList<String>();
		roleIdList.add(FortuneRole.FINANCING_MANAGER.id);

		params.put("roleIdList", roleIdList);
		params.put("userName", managerBeanEx.getUserName());
		params.put("userCode", managerBeanEx.getUserCode());
		params.put("orgName", managerBeanEx.getOrgName());
		params.put("delFlag", "0");  //用户删除标记，0未删除、1删除
		
		PageBounds pageBounds = new PageBounds(page.getPageNo(), 	page.getPageSize());
		PageList<UserRoleOrgEx> pageList = (PageList<UserRoleOrgEx>) customerPoolDao.findManager(params, pageBounds);
		PageUtil.convertPage(pageList, page);
		return page;
	}
	
	/**
	 * 查找电销人员
	 * 2016年2月17日
	 * By 肖长伟
	 * @param managerBeanEx
	 * @param page
	 * @return
	 */
	public Page<UserRoleOrgEx> findTelManager(UserRoleOrgEx managerBeanEx, Page<UserRoleOrgEx> page) {
		PageBounds pageBounds = new PageBounds(page.getPageNo(), 	page.getPageSize());
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("roleId", FortuneRole.TELE_FINANCING_MANAGER.id); //电销专员
		params.put("userName", managerBeanEx.getUserName());
		params.put("userCode", managerBeanEx.getUserCode());
		params.put("orgName", managerBeanEx.getOrgName());
		PageList<UserRoleOrgEx> pageList = (PageList<UserRoleOrgEx>) customerPoolDao.findTeleManager(params, pageBounds);
		PageUtil.convertPage(pageList, page);
		return page;
	}

	/**
	 * 获取电销分配的客户信息，包括已分配、退回电销两个状态的数据
	 * 2016年2月16日
	 * By 肖长伟
	 * @param page
	 * @param customerPool
	 * @return
	 */
	public Page<CustomerPoolEx> getDistributeCustomers(Page<CustomerPoolEx> page,	CustomerPoolEx customerPoolEx) {
		//查数据并组装
		//1.查基础数据
		Map<String, Object> params = new HashMap<String, Object>();
		//设置条件，电销公共池分配后的列表
		
		List<String> dataTypeList = new ArrayList<String>();
		String dataType = customerPoolEx.getDataType();
		if (StringUtils.isBlank(dataType)) {  //公共池数据类型 为空时，默认为2、3
			dataTypeList.add(TeleCustomPoolType.DISTRIBUTE.value);
			dataTypeList.add(TeleCustomPoolType.SEND_BACK.value);
		} else if(! TeleCustomPoolType.DISTRIBUTE.value.equals(dataType) && ! TeleCustomPoolType.SEND_BACK.value.equals(dataType)){  //不为2、3时, 默认为2、3
			//异常情况
			dataTypeList.add(TeleCustomPoolType.DISTRIBUTE.value);
			dataTypeList.add(TeleCustomPoolType.SEND_BACK.value);
		} else {
			if (StringUtils.isNotBlank(dataType)) {
				dataTypeList.add(dataType);
			}
		}
		
		//登录人的角色若是团队经理、部门经理时，可看其所有下属的数据
		String maxOrgId = getMaxRoleOrgId();
		
		params.put("storeRoleId", FortuneRole.STORE_MANAGER.id);
		params.put("teamRoleId", FortuneRole.TEAM_MANAGER.id);
		params.put("managerRoleId", FortuneRole.FINANCING_MANAGER.id);
		params.put("dataTypeList", dataTypeList);
		params.put("customerName", customerPoolEx.getCustomerName());
		params.put("card", customerPoolEx.getCard());
		params.put("customerTel", customerPoolEx.getCustomerTel());
		params.put("teleManagerName", customerPoolEx.getTeleManagerName());
		params.put("managerName", customerPoolEx.getManagerName());
		if (StringUtils.isNotBlank(maxOrgId)) {
			params.put("maxOrgId", maxOrgId);
			
		} else {
			User user = UserUtils.getUser();
			params.put("userId", user.getId());
		}
		PageBounds pageBounds = new PageBounds(page.getPageNo(), 	page.getPageSize());
		PageList<CustomerPoolEx> pageList = (PageList<CustomerPoolEx>)customerPoolDao.getDistibuteCustomers(params, pageBounds);
		PageUtil.convertPage(pageList, page);
		
		//处理数据
		processDistributeCustomers(page.getList());
		
		return page;
	}
	
	/**
	 * 处理查出的电销客户列表数据
	 * 2016年4月12日
	 * By 肖长伟
	 * @param list
	 */
	private void processDistributeCustomers(List<CustomerPoolEx> list) {
		//手机号加*号
		if (ListUtils.isEmptyList(list)) {
			return;
		}
		for (CustomerPoolEx item : list) {
			String phoneNo = item.getCustomerTel();
			if (StringUtils.isNotBlank(phoneNo)) {
				//格式化手机号
				char [] charArr = item.getCustomerTel().toCharArray();
				item.setCustomerTel(new String(formatePhone(charArr)));
			}
		}
	}
	
	/**
	 * 格式化手机号
	 * 2016年4月12日
	 * By 肖长伟
	 * @param phone
	 */
	private  char[] formatePhone(char[] phone) {
		char [] charArr = phone;
		int length = charArr.length;
		for (int i = length / 3; i < length - 4; i++) {
			charArr[i] = '*';
		}
		return charArr;
	}

	/**
	 * 获取下属
	 * 2016年2月29日
	 * By 肖长伟
	 * @return
	 */
	private String getMaxRoleOrgId() {
		User user = UserUtils.getUser();
		List<Role> userRoleList = UserUtils.getUser().getRoleList();
		if (userRoleList == null || userRoleList.size() == 0) {
			return "";
		}
		List<String> roleList = new ArrayList<String>();
		for (Role userRoleOrg : userRoleList) {  //取出最大级别的orgId
			String roleId = userRoleOrg.getId();
			if (FortuneRole.TELE_TEAM_MANAGER.id.equals(roleId)) {  //团队经理
				roleList.add(roleId);
			} else if (FortuneRole.TELE_STORE_MANAGER.id.equals(roleId)) { //部门经理
				roleList.remove(FortuneRole.TELE_DEPARTMENT_MANAGER.id);
				roleList.add(roleId);
				break;
			} 
		}
		if (roleList.size() > 0) {  //是团队经理或部门经理, 取出其所有的下属
			String roleId = roleList.get(0);
			UserRoleOrg tempU = new UserRoleOrg();
			tempU.setUserId(user.getId());
			tempU.setRoleId(roleId);
			List<UserRoleOrg> userRoleOrgList = userRoleOrgDao.selUserRoleOrg(tempU);
			for (UserRoleOrg userRoleOrg : userRoleOrgList) {  //取出所有的orgId
				String orgId = userRoleOrg.getOrgId();
				if (StringUtils.isNotBlank(orgId)) {
					return orgId;
				}
			}
			
		} 
		return "";
	}
	

	/**
	 * 退回电销
	 * 2016年2月16日
	 * By 肖长伟
	 * @param custCode
	 */
	@Transactional(value = "fortuneTransactionManager", readOnly = false)
	public void sendBack2CustomerPool(String custCode) {
		// 1.更新customer_pool的状态
		modifyTeleSalePoolCustomerStatus(custCode, TeleCustomPoolType.SEND_BACK.value);  //状态为3， 退回电销
		// 2.更新 customer 相关信息
		Customer customer = new Customer();
		customer.setCustCode(custCode);
		customer = customerManager.getCustomer(customer);
		modifyCustomerTeleSaleInfo(customer.getTeleManagerId(), customer, CustomerMarkState.GGC.value);  // 公共池
		// 3.记录到tele_operation表
		CustomerPoolEx customerPoolEx = new CustomerPoolEx();
		customerPoolEx.setManagerId(customer.getManagerCode());
		createTeleOperation(customer, customer.getManagerCode());
	}
	
	/**
	 * 记录进入公共池信息
	 * 2016年2月19日
	 * By 肖长伟
	 * @param pool
	 * @param dataType
	 */
	@Transactional(readOnly = false, value = "fortuneTransactionManager")
	public void recordTeleCustomerOperation4In(CustomerPool pool) {
		TeleOperation operation = new TeleOperation();
		operation.setCustomerId(pool.getCustomerCode());       //客户的Code
		operation.setOperationType(TeleCustomPoolType.IN_POOL.value); //操作类型为：2分配
		operation.preInsert();
		teleOperationManager.insertTeleOperation(operation);
	}
	
	/**
	 * 更新公共池数据的状态， 根据客户的code
	 * 2016年2月16日
	 * By 肖长伟
	 * @param custCode
	 * @param dataType
	 * @return int
	 */
	@Transactional(readOnly = false, value = "fortuneTransactionManager")
	private int modifyTeleSalePoolCustomerStatus(String custCode, String dataType) {
		CustomerPool customerPool = new CustomerPool();
		customerPool.setCustomerCode(custCode);
		customerPool.setDataType(dataType);
		customerPool.preUpdate();
		return customerPoolDao.updateCustomerByCode(customerPool);
	}
	
	/**
	 * 更新咨询信息录入的时间，最新时间
	 * 2016年2月16日
	 * By 肖长伟
	 * @param advisory
	 */
	@Transactional(readOnly = false, value = "fortuneTransactionManager")
	public void updateLendInputDate(Advisory advisory) {
		// 更新咨询信息录入的时间，最新时间
		String custCode = advisory.getCustCode();
		CustomerPool customerPool = new CustomerPool();
		customerPool.setCustomerCode(custCode); 
		customerPool.setLendInputDate(new Date());	//最新时间
		customerPool.preUpdate();
		customerPoolDao.updateCustomerByCode(customerPool);
	}

	/**
	 * 获取公共池数据，导出excel
	 * 2016年2月17日
	 * By 肖长伟
	 * @param customerPool  查询条件
	 * @param checkedCodes 勾选的要导出的行id
	 * @return
	 * @throws IOException 
	 */
	public ExportExcel4BD getCustomerPoolData4ExportExcel(CustomerPool customerPool, String checkedCodes) {
		
		ExportExcel4BD exportExcel4BD = new ExportExcel4BD(CustomerPoolExportModel.class);
		
		//获取要导出的数据
		List<CustomerPool> list;
		if (StringUtils.isNotBlank(checkedCodes)) {  //1.勾选要导出的行数据
			String[] codes = checkedCodes.trim().split(",");
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("checkIds", codes);
			params.put("dataType", TeleCustomPoolType.IN_POOL.value);
			list = customerPoolDao.getCustomerPoolByIds(params);
			
			List<CustomerPoolExportModel> exportList = new ArrayList<CustomerPoolExportModel>();
			for (CustomerPool item : list) { 	//遍历，复制属性到 CustomerPoolExportModel
				CustomerPoolExportModel export = new CustomerPoolExportModel();
				//屏蔽客户姓名/手机号/身份证号
				item.setCustomerName("***");
				item.setCustomerTel("*******");
				item.setCard("*******");
				BeanUtils.copyProperties(item, export);
				exportList.add(export);
			}
			exportExcel4BD.setDataList(exportList);
			
		} else { //2.查询条件
			//设置城市条件，解析出省份、城市，set到customerPool中
			if (StringUtils.isNotBlank(customerPool.getCityIdTemp())) {
				String[] provecity = customerPool.getCityIdTemp().trim().split("_");
				if (provecity != null && provecity.length == 2) {
					customerPool.setProvinceId(provecity[0]);
					customerPool.setCityId(provecity[1]);
				}
			}
			
			//从公共池查询数据，按条件分页
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("dataType", TeleCustomPoolType.IN_POOL.value);
			paramMap.put("customerName", customerPool.getCustomerName());
			paramMap.put("customerTel", customerPool.getCustomerTel());
			paramMap.put("card", customerPool.getCard());
			paramMap.put("cityId", customerPool.getCityId());
			paramMap.put("provinceId", customerPool.getProvinceId());
//			paramMap.put("isTelManager", isTelManager);
//			paramMap.put("teleManagerId", teleManagerId);
			
			int pageSize = 30000;
			int curPageNo = 1;  //当前页
			int totalPage = 0;
			Page<CustomerPool> page = new Page<CustomerPool>();
			do {
				PageBounds bounds = new PageBounds(curPageNo, pageSize);
				PageList<CustomerPool> pageList = (PageList<CustomerPool>) customerPoolDao.selectList(paramMap, bounds);
				totalPage = pageList.getPaginator().getTotalPages();
				PageUtil.convertPage(pageList, page);
				curPageNo ++;
				
				List<CustomerPoolExportModel> exportList = new ArrayList<CustomerPoolExportModel>();
				for (CustomerPool item : page.getList()) { 	//遍历，复制属性到 CustomerPoolExportModel
					CustomerPoolExportModel export = new CustomerPoolExportModel();
					//屏蔽客户姓名/手机号/身份证号
					item.setCustomerName("***");
					item.setCustomerTel("*******");
					item.setCard("*******");
					BeanUtils.copyProperties(item, export);
					exportList.add(export);
				}
				exportExcel4BD.setDataList(exportList);
				
			} while(curPageNo < totalPage);
		}
		return exportExcel4BD;
	}
	
}
