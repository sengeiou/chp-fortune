package com.creditharmony.fortune.customer.service;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.creditharmony.adapter.service.triple.bean.TripleNewCustomerInBean;
import com.creditharmony.common.util.ArrayHelper;
import com.creditharmony.common.util.IdGen;
import com.creditharmony.common.util.ListUtils;
import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.common.type.EffectiveFlag;
import com.creditharmony.core.common.util.PageUtil;
import com.creditharmony.core.excel.util.ImportExcel;
import com.creditharmony.core.fortune.type.CustomerState;
import com.creditharmony.core.fortune.type.DataSource;
import com.creditharmony.core.fortune.type.InvestState;
import com.creditharmony.core.fortune.type.LendchgType;
import com.creditharmony.core.fortune.type.OsType;
import com.creditharmony.core.fortune.type.TrustState;
import com.creditharmony.core.loan.type.SexFlag;
import com.creditharmony.core.mybatis.paginator.domain.PageBounds;
import com.creditharmony.core.mybatis.paginator.domain.PageList;
import com.creditharmony.core.ocr.dto.RecognizeResult;
import com.creditharmony.core.ocr.util.RecognizeHelper;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.role.type.FortuneRole;
import com.creditharmony.core.service.CoreManager;
import com.creditharmony.core.system.util.DataScopeUtil;
import com.creditharmony.core.type.SystemFlag;
import com.creditharmony.core.users.entity.User;
import com.creditharmony.core.users.util.UserUtils;
import com.creditharmony.dm.service.DmService;
import com.creditharmony.fortune.common.CustomerCodeGenerator;
import com.creditharmony.fortune.common.dao.AttachmentDao;
import com.creditharmony.fortune.common.entity.Attachment;
import com.creditharmony.fortune.common.util.BeanUtil;
import com.creditharmony.fortune.constants.FileConstants;
import com.creditharmony.fortune.customer.CustomerConstants;
import com.creditharmony.fortune.customer.dao.AddressDao;
import com.creditharmony.fortune.customer.dao.AdvisoryDao;
import com.creditharmony.fortune.customer.dao.ChangeTracesDao;
import com.creditharmony.fortune.customer.dao.CustomerDao;
import com.creditharmony.fortune.customer.dao.EmergencyContactDao;
import com.creditharmony.fortune.customer.dao.LoanConfigurationDao;
import com.creditharmony.fortune.customer.entity.Address;
import com.creditharmony.fortune.customer.entity.Advisory;
import com.creditharmony.fortune.customer.entity.ChangeTraces;
import com.creditharmony.fortune.customer.entity.CustMode;
import com.creditharmony.fortune.customer.entity.CustName;
import com.creditharmony.fortune.customer.entity.Customer;
import com.creditharmony.fortune.customer.entity.EmergencyContact;
import com.creditharmony.fortune.customer.entity.FortuneOrg;
import com.creditharmony.fortune.customer.entity.LoanConfiguration;
import com.creditharmony.fortune.customer.util.CustomerJsonObject;
import com.creditharmony.fortune.customer.util.RelationShipUtil;
import com.creditharmony.fortune.customer.view.CustomerView;
import com.creditharmony.fortune.customer.view.OpenAccountView;
import com.creditharmony.fortune.delivery.service.TripleCustomerDeliveryManager;
import com.creditharmony.fortune.remind.entity.ext.CustomerBirthdayEx;
import com.creditharmony.fortune.triple.system.constant.Constant;
import com.creditharmony.fortune.triple.system.dao.TripleLoanAskSyncDao;
import com.creditharmony.fortune.triple.system.dao.TripleNewCustomerDao;
import com.creditharmony.fortune.triple.system.entity.IntCard;
import com.creditharmony.fortune.triple.system.entity.IntCustomerBean;
import com.creditharmony.fortune.triple.system.entity.IntLoanAskBean;
import com.creditharmony.fortune.triple.system.entity.IntPhone;
import com.creditharmony.fortune.triple.system.entity.ext.IntDeliveryEx;
import com.creditharmony.fortune.triple.system.service.TripleCheckInfoService;
import com.creditharmony.fortune.triple.system.service.TripleCommonService;
import com.creditharmony.fortune.triple.system.service.TripleNewCustomerService;
import com.creditharmony.fortune.users.dao.UserInfoDao;
import com.creditharmony.fortune.users.entity.UserInfo;
import com.creditharmony.fortune.utils.FileUtil;
import com.creditharmony.fortune.utils.SerialNum;

/**
 * @Class Name CustomerManager
 * @author 孙凯文
 * @Create In 2015年12月2日
 */
@Service
public class CustomerManager extends CoreManager<CustomerDao, Customer> {
	private Logger log = LoggerFactory.getLogger(getClass());
	// 咨询数据表操作类
	@Autowired
	private AdvisoryDao advisoryDao;
	// 地址表操作类
	@Autowired
	private AddressDao addressDao;
	// 客戶信息变更存储类
	@Autowired
	private ChangeTracesDao changeTracesDao;
	// 客户信息抢单
	@Autowired
	private CustomerDao customerDao;
	// 出借信息
	@Autowired
	private LoanConfigurationDao loanConfigurationDao;
	// 紧急联系人
	@Autowired
	private EmergencyContactDao emergencyContactDao;
	// 三网接口
	@Resource
	TripleCheckInfoService tripleCheckInfoService;
	// 转赠Dao
	// 三网注册
	@Autowired
	private TripleNewCustomerService tripleNewCustomerService;
	@Autowired
	private TripleNewCustomerDao tripleNewCustomerDao;
	@Autowired
	private AttachmentDao attachmentDao;
	@Autowired
	private TripleCustomerDeliveryManager tripleCustomerDeliveryManager;

	@Autowired
	private UserInfoDao  userInfoDao;
	@Autowired
	private TripleLoanAskSyncDao  tripleLoanAskDao;
	
	@Autowired
	private TripleCommonService  tripleCommonService;
	
	/**
	 * 根据入参查询实体类 2015年12月4日 By 孙凯文
	 * 
	 * @param customer
	 * @return
	 */
	public Customer getCustomer(Customer customer) {
		return customerDao.getCustomer(customer);// 默认从CoreManager继承dao,无需自定义
	}

	/**
	 * 获取出借信息 2015年12月21日
	 * 
	 * @author 孙凯文
	 * @param customerCode
	 * @return
	 */
	public LoanConfiguration getLoanConfiguration(String customerCode) {
		LoanConfiguration config = new LoanConfiguration();
		config.setCustCode(customerCode);
		return loanConfigurationDao.get(config);
	}

	/**
	 * 获取紧急联系人 2015年12月21日
	 * 
	 * @author 孙凯文
	 * @param customerCode
	 * @return
	 */
	public EmergencyContact getEmergencyContact(String customerCode) {
		EmergencyContact emer = new EmergencyContact();
		emer.setCustCode(customerCode);
		return emergencyContactDao.get(emer);
	}

	/**
	 * 2015年12月2日 By 孙凯文
	 * 
	 * @param page
	 * @param customer
	 * @return
	 */
	public Page<Customer> findInvestList(Page<Customer> page, Customer customer) {
		String dataRights = DataScopeUtil.getDataScope("ci",
				SystemFlag.FORTUNE.value);
		if (StringUtils.isNotEmpty(dataRights)) {
			Map<String, String> sqlMap = new HashMap<String, String>();
			sqlMap.put("dataRights", dataRights);
			logger.info("findInvestList权限：" + dataRights);
			customer.setSqlMap(sqlMap);
		}

		PageBounds pageBounds = new PageBounds(page.getPageNo(),
				page.getPageSize());
		PageList<Customer> pageList = (PageList<Customer>) customerDao
				.findInvestList(customer, pageBounds);
		PageUtil.convertPage(pageList, page);
		// List<Customer> list = page.getList();
		// assemblePreIntoPoolTime(list); // 拼装预计进入公共池时间
		return page;
	}

	/**
	 * 拼装“预计进入公共池时间”到Customer中 2016年2月18日 By 肖长伟
	 * 
	 * @param list
	 */
//	public void assemblePreIntoPoolTime(List<Customer> list) {
//		// 遍历list，获取出借信息
//		for (Customer customer : list) {
//			String customerCode = customer.getCustCode();
//			List<LoanApply> applyList = lendFinishManager
//					.getLoanApplyList(customerCode);
//			Date lastTime = customer.getModifyTime();
//			if (lastTime == null) { // 针对老数据
//				lastTime = customer.getCreateTime();
//			}
//			if (applyList != null && applyList.size() > 0) { // 有出借信息，已成交客户
//																// （赎回完结、客户放弃）2个月内没有变动（修改信息、添加咨询）
//				boolean isEnd = true;
//				for (LoanApply loanApply : applyList) {
//					String endState = loanApply.getApplyEndStatus();
//					if (FinishState.WWJ.value.equals(endState)) { // 有未完结的出借信息，不能进入公共池
//						isEnd = false;
//						break;
//					} else {
//						if (loanApply.getModifyTime() != null) {
//							if (lastTime == null) {
//								lastTime = loanApply.getModifyTime();
//							} else {
//								lastTime = loanApply.getModifyTime().compareTo(
//										lastTime) > 0 ? loanApply
//										.getModifyTime() : lastTime;
//							}
//						}
//					}
//				}
//				if (isEnd) { // 不含有未完结的
//					// 判断是否有新的咨询
//					List<Advisory> advisories = advisoryDao
//							.getMaxModTimeByCustCode(customerCode);
//					if (advisories != null && advisories.size() > 0) { // 存在咨询，
//																		// 可以设置时间了
//						Date advTime = advisories.get(0).getModifyTime();
//						if (advTime != null) { // 时间不存在
//							lastTime = advTime.compareTo(lastTime) > 0 ? advTime
//									: lastTime;
//						}
//					}
//					Date preTime = DateUtils.addMonths(lastTime, 2);
//					customer.setPreIntoPoolTime(preTime);
//				}
//			} else { // 未成交客户 1个月内没有变动（修改信息、添加咨询）
//				List<Advisory> advisories = advisoryDao
//						.getMaxModTimeByCustCode(customerCode);
//				if (advisories != null && advisories.size() > 0) { // 存在咨询，
//																	// 可以设置时间了
//					Date advTime = advisories.get(0).getModifyTime();
//					if (advTime != null) { // 时间不存在
//						lastTime = advTime.compareTo(lastTime) > 0 ? advTime
//								: lastTime;
//					}
//				}
//				if (lastTime != null) {
//					Date preTime = DateUtils.addMonths(lastTime, 1);
//					customer.setPreIntoPoolTime(preTime);
//				}
//			}
//
//		}
//	}

	/**
	 * 查询已开户的客户，用于出借申请查询列表 2016年4月26日 By 肖长伟
	 * 
	 * @param page
	 * @param customer
	 * @return
	 */
	public Page<Customer> getOpenAccountCustomer(Page<Customer> page,
			Customer customer) {
		// 默认条件：已开户
		customer.setCustState(CustomerState.YKH.value);
		// 默认条件：理财经理
		customer.setManagerCode(UserUtils.getUserId());
		String dataRights = DataScopeUtil.getDataScope("c",
				SystemFlag.FORTUNE.value);
		if (StringUtils.isNotEmpty(dataRights)) {
			Map<String, String> sqlMap = new HashMap<String, String>();
			sqlMap.put("dataRights", dataRights);
			customer.setSqlMap(sqlMap);
		}

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("customer", customer);
		params.put("teamManagerRoleId", FortuneRole.TEAM_MANAGER.id); // 团队经理
		PageBounds pageBounds = new PageBounds(page.getPageNo(),
				page.getPageSize());
		PageList<Customer> pageList = (PageList<Customer>) customerDao
				.getCustomer4LendApply(params, pageBounds);
		PageUtil.convertPage(pageList, page);
		return page;
	}

	/**
	 * 2015年12月2日 By 孙凯文
	 * 
	 * @param customer
	 * @return
	 * @throws Exception 
	 */
	@Transactional(readOnly = false, value = "fortuneTransactionManager")
	public void update(Customer customer) throws Exception {
		Customer param = new Customer();
		param.setId(customer.getId());
		param.setCustCode(customer.getCustCode());
		log.error("更新客户信息:" + param.toString());
		Customer customerFromDB = this.getCustomer(param);
		customer.setCustMobilephone(customerFromDB.getCustMobilephone());

		CustomerJsonObject changeBegin = new CustomerJsonObject();
		CustomerJsonObject changeAfter = new CustomerJsonObject();
		try {
			BeanUtils.copyProperties(changeBegin, customerFromDB);
			BeanUtils.copyProperties(changeBegin, customerFromDB.getAddress());
			changeBegin.setId(customerFromDB.getId());
			BeanUtils.copyProperties(changeAfter, customer);
			BeanUtils.copyProperties(changeAfter, customer.getAddress());
			changeAfter.setId(customer.getId());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			log.error("更新客户信息错误：" + customer.getCustCode(), e);
			throw e;
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			log.error("更新客户信息错误：" + customer.getCustCode(), e);
			throw e;
		}

		String changeBeginJson = JSONObject.toJSONString(changeBegin);
		String changeAfterJson = JSONObject.toJSONString(changeAfter);

		ChangeTraces changeTraces = new ChangeTraces();
		changeTraces.setAssociateId(customer.getCustCode());
		changeTraces.setApplyId(CustomerConstants.WorkFlow.DefaultFlowApplyId);
		changeTraces.setChangeType(LendchgType.CUSTOMER_CHG.value);
		changeTraces.setChangeBegin(changeBeginJson);
		changeTraces.setChangeAfter(changeAfterJson);
		changeTraces.preInsert();
		changeTracesDao.insert(changeTraces);

		try {
			BeanUtils.copyProperties(customerFromDB, changeAfter);
			customerFromDB.preUpdate();
			customerFromDB.setManagerCode(UserUtils.getUser().getId());
			tripleCommonService.sendCustomerInfo(BeanUtil.conv2IntCustomerBean(customerFromDB));
			super.dao.update(customerFromDB);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			log.error("更新客户信息错误：" + customer.getCustCode(), e);
			throw e;
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			log.error("更新客户信息错误：" + customer.getCustCode(), e);
			throw e;
		}

		customer.getAddress().preUpdate();
		addressDao.update(customer.getAddress());

	}

	/**
	 * 新增判断两网
	 * 
	 * @param addView
	 * @return String[] String[0] 客户编号 String[1] 用户id
	 */
	@Transactional(readOnly = false, value = "fortuneTransactionManager")
	public String saveCustomer(CustomerView addView) {
		String result = null;

		IntPhone intPhone = new IntPhone();
		intPhone.setPhone(addView.getCustomer().getCustMobilephone());
		List<IntDeliveryEx> list = tripleCheckInfoService
				.findAllInfoByPhoneList(intPhone);

		if (list.size() == 0) {
			Customer customer = addView.getCustomer();
			Advisory advisor = addView.getAdvisory();
			Address address = addView.getAddress();

			address.preInsert();
			addressDao.insert(address);

			String custCode = CustomerCodeGenerator.getNextCode();
			customer.setCustCode(custCode);
			customer.setManagerCode(UserUtils.getUserId());
			customer.setDataTerminal(DataSource.PCD.value);
			customer.setCustFirstContactdate(advisor.getAskDate());
			customer.setCustState(CustomerState.ZXGT.value);
			customer.setCustLending(InvestState.WT.value);
			customer.setApplyHostedStatus(TrustState.WKH.value);
			customer.setAddress(address);

			// 理财经理ID
			customer.setManagerCode(UserUtils.getUserId());
			// 所属营业部ID
			FortuneOrg org = RelationShipUtil.getStoresOrg(customer
					.getManagerCode());
			if (null != org) {
				customer.setOrgCode(org.getId());
			}

			// 团队机构Id
			FortuneOrg teamOrg = RelationShipUtil.getTeamOrg(UserUtils
					.getUser().getId());
			if (null != teamOrg) {
				customer.setTeamOrgId(teamOrg.getId());
			}

			customer.preInsert();
			super.dao.insert(customer);

			// chp客户注册时向三网插入信息
			TripleNewCustomerInBean paramBean = new TripleNewCustomerInBean();
			paramBean.setOperation("C");
			paramBean.setOsId(custCode);
			paramBean.setUserName(addView.getCustomer().getCustName());
			paramBean.setOsType("XH_CHP");
			paramBean.setLoginName(addView.getCustomer().getCustName());
			paramBean.setPhone(addView.getCustomer().getCustMobilephone());
			paramBean.setEmpCode(UserUtils.getUser().getUserCode());
			tripleNewCustomerService.registerCustomer(BeanUtil.conv2IntCustomerBean(paramBean,addView.getCustomer())); // 插入数据

			advisor.setCustCode(custCode);
			advisor.setManagerId(UserUtils.getUserId());
			advisor.preInsert();
			advisoryDao.insert(advisor);
			//---将客户访问记录插入到客户访问记录同步表（start）   张新民  20160627---
//			Customer c=new Customer();c.setCustCode(custCode);
//			List<Customer> cusList = customerDao.getCusByCode(c) ;
			
			UserInfo userInfo = userInfoDao.get(advisor.getManagerId());
			IntLoanAskBean intLoanAskBean = getLoanAskBean(advisor);
			if(null!=userInfo){
				intLoanAskBean.setManagerCode(userInfo.getUserCode());
				intLoanAskBean.setManagerName(userInfo.getName());
			}
//			if(cusList!=null&&cusList.size()!=0){
//				intLoanAskBean.setCustomerCode(cusList.get(0).getId());
//			}
			tripleLoanAskDao.insertIntLoanAsk(intLoanAskBean);
			//---将客户访问记录插入到客户访问记录同步表（end）   张新民  20160627---
			
			return result;
		} else if (list.size() == 1) {

			if (list.get(0).getOsType().equals(OsType.CHP.value)) {
				result = list.get(0).getEmpCode();
				return result; // 弹出理财经理信息
			} else {
				if (list.get(0).getEmpCode()
						.equals(UserUtils.getUser(UserUtils.getUserId()).getUserCode())) {
					Customer customer = addView.getCustomer();
					Advisory advisor = addView.getAdvisory();
					Address address = addView.getAddress();

					address.preInsert();
					addressDao.insert(address);

					String custCode = CustomerCodeGenerator.getNextCode();
					customer.setCustCode(custCode);
					customer.setManagerCode(UserUtils.getUserId());
					customer.setDataTerminal(DataSource.PCD.value);
					customer.setCustFirstContactdate(advisor.getAskDate());
					customer.setCustState(CustomerState.ZXGT.value);
					customer.setCustLending(InvestState.WT.value);
					customer.setApplyHostedStatus(TrustState.WKH.value);
					customer.setAddress(address);

					// 理财经理ID
					customer.setManagerCode(UserUtils.getUserId());
					// 所属营业部ID
					FortuneOrg org = RelationShipUtil.getStoresOrg(customer
							.getManagerCode());
					if (null != org) {
						customer.setOrgCode(org.getId());
					}
					
					// 团队机构Id
					FortuneOrg teamOrg = RelationShipUtil.getTeamOrg(UserUtils
							.getUser().getId());
					if (null != teamOrg) {
						customer.setTeamOrgId(teamOrg.getId());
					}
					customer.preInsert();
					super.dao.insert(customer);
					
					//三网逻辑开始//
					TripleNewCustomerInBean paramBean = new TripleNewCustomerInBean();
					paramBean.setOperation("C");
					paramBean.setOsId(custCode);
					paramBean.setOsType("XH_CHP");
					paramBean.setLoginName(addView.getCustomer().getCustName());
					paramBean.setPhone(addView.getCustomer().getCustMobilephone());
					paramBean.setEmpCode(UserUtils.getUser().getUserCode());
					tripleNewCustomerService.registerCustomer(BeanUtil.conv2IntCustomerBean(paramBean,addView.getCustomer())); // 插入数据
					//三网逻辑结束//
					
					advisor.setCustCode(custCode);
					advisor.setManagerId(UserUtils.getUserId());
					advisor.preInsert();
					advisoryDao.insert(advisor);

					// 协议库插入数据
					// HashMap<String, Object> map = new HashMap<String,
					// Object>();
					// map.put("id", IdGen.uuid());
					// map.put("customerCode", custCode);
					// map.put("businessType", "AC01(代收");
					// map.put("voiceDail", "否");
					// map.put("agreementRemarks", "客户协议库");
					// map.put("createBy", UserUtils.getUser().getName());
					// map.put("createTime", new Date());
					// super.dao.insertAgreementLib(map);

					return result;
				} else {
					result = list.get(0).getEmpCode();
					return result; // 弹出理财经理信息
				}
			}

		} else {
			result = "1"; // 该手机号有多个理财经理，请先通知业务经行交割
			return result;
		}
	}
	
	/**
	 * 根据advisory对象生成IntLoanAskBean实例
	 * @param advisory
	 * 2016年7月24日
	 * By 张新民
	 * @return
	 */
	private  IntLoanAskBean getLoanAskBean(Advisory advisory){
		IntLoanAskBean intLoanAskBean = new  IntLoanAskBean();
		intLoanAskBean.setId(IdGen.uuid());
		intLoanAskBean.setAskBeginDay(advisory.getAskBeginDate());
		intLoanAskBean.setAskDay(advisory.getAskDate());
		intLoanAskBean.setAskDes(advisory.getAskDes());
		intLoanAskBean.setAskEndDay(advisory.getAskEndDate());
		intLoanAskBean.setAskId(advisory.getId());
		intLoanAskBean.setAskInputDay(advisory.getAskInputDate());
		intLoanAskBean.setAskInputMoney(advisory.getAskInputMoney());
		intLoanAskBean.setAskNextDay(advisory.getAskNextDate());
		intLoanAskBean.setAskNextType(advisory.getAskNextType());
		intLoanAskBean.setAskProduct(advisory.getAskProduct());
		intLoanAskBean.setCreateBy(advisory.getCreateBy());
		intLoanAskBean.setCreateTime(advisory.getCreateTime());
		intLoanAskBean.setCustomerCode(advisory.getCustCode());
		intLoanAskBean.setDictAskType(advisory.getAskType());
		intLoanAskBean.setModifyBy(advisory.getModifyBy());
		intLoanAskBean.setModifyTime(advisory.getModifyTime());
		intLoanAskBean.setManagerId(advisory.getManagerId());
		intLoanAskBean.setSendStatus(Constant.SEND_STATUS_FAIL);
		intLoanAskBean.setSendType(Constant.SYSNCNUM_KHGTJLTB);
		intLoanAskBean.setOperation(Constant.OPERATION_TYPE_C);
		intLoanAskBean.setUniqueNum(SerialNum.getSerialNum());
		return intLoanAskBean;
	}
	/**
	 * 客户生日提醒列表 2015年12月2日 By 韩龙
	 * 
	 * @param page
	 * @param customerBirthdayEx
	 * @return
	 */
	public Page<CustomerBirthdayEx> findPage(Page<CustomerBirthdayEx> page,
			CustomerBirthdayEx customerBirthdayEx) {
		customerBirthdayEx.setPage(page);
		page.setList(super.dao.findList(customerBirthdayEx));
		return page;
	}

	/**
	 * 校验手机号码 2016年3月9日 By 刘雄武
	 * 
	 * @param addView
	 * @return
	 */
	public String checkCustomer(CustomerView addView) {
		String result = null;

		IntPhone intPhone = new IntPhone();
		intPhone.setPhone(addView.getCustomer().getCustMobilephone());
		List<IntDeliveryEx> list = tripleCheckInfoService
				.findAllInfoByPhoneList(intPhone);

		if (list.size() == 0) {

			return result;
		} else if (list.size() == 1) {

			if (list.get(0).getOsType().equals(OsType.CHP.value)) {
				result = list.get(0).getEmpCode();
				return result; // 弹出理财经理信息
			} else {
				if (list.get(0).getEmpCode()
						.equals(UserUtils.getUser(UserUtils.getUserId()).getUserCode())) {

					return result;
				} else {
					result = list.get(0).getEmpCode();
					return result; // 弹出理财经理信息
				}
			}

		} else {
			result = "1"; // 该手机号有多个理财经理，请先通知业务经行交割
			return result;
		}
	}

	/**
	 * 查身份证号出现的次数 2016年3月21日 By 肖长伟
	 * 
	 * @param customerParam
	 * @return
	 */
	public int getCustomerCertNumCnt(Customer customerParam) {
		return customerDao.getCustomerCertNumCnt(customerParam);
	}

	/**
	 * 查证件号在三网中出现的次数 2016年5月22日 By 朱杰
	 * 
	 * @param customerParam
	 * @return
	 */
	public int getTripleCustomerCertNumCnt(IntCard intCard) {
		return tripleNewCustomerDao.searchCustomerByCertNum(intCard).size();
	}

	/**
	 * 转出借，修改客户状态为已开户，同时添加紧急联系人和出借配置信息 2016年3月29日
	 * 
	 * @author 孙凯文
	 * @param view
	 * @throws Exception
	 */
	@Transactional(value = "fortuneTransactionManager", readOnly = false, rollbackFor = Exception.class)
	public void transferLend(Customer customer,
			LoanConfiguration configuration, EmergencyContact emergencyContact,
			HttpServletRequest request) throws Exception {

		User userInfo = UserUtils.getUser(UserUtils.getUserId());
		TripleNewCustomerInBean tBean = new TripleNewCustomerInBean();
		Customer param = new Customer();
		// 客户编号
		param.setCustCode(customer.getCustCode());
		Customer customerDB = customerDao.getCustomer(param);
		// 属性拷贝
		copyCustomerProperty(customer, customerDB);

		/** 各自系统中的ID. */
		tBean.setOsId(customerDB.getCustCode());
		/** 理财经理员工编号(更新手机号对应的理财经理). */
		tBean.setEmpCode(userInfo.getUserCode());
		/** 系统类型 */
		tBean.setOsType(OsType.CHP.value);
		/** 登录用户名. */
		tBean.setLoginName(customerDB.getCustName());
		/** 手机号码. */
		tBean.setPhone(customerDB.getCustMobilephone());
		/** 用户真实姓名. */
		tBean.setUserName(customerDB.getCustName());
		/** 证件类型 */
		tBean.setCardType(customerDB.getCustCertType());
		/** 证件号码. */
		tBean.setCardId(customerDB.getCustCertNum());
		/** 操作类型 */
		tBean.setOperation("M");
		
		IntCustomerBean inBean=BeanUtil.conv2IntCustomerBean(tBean,customerDB);
		BeanUtil.setEmerInfo2IntCustomerBean(inBean, emergencyContact);
				
		IntCard card = tripleNewCustomerService.authenticateCustomer(inBean);
		if (null != card && null != card.getEmpCode() && 
				!card.getEmpCode().equals(userInfo.getUserCode())) {
			// 需要变更理财经理,营业部,团队
			List<IntDeliveryEx> list = tripleCustomerDeliveryManager
					.findEmpInfoByCode(card.getEmpCode());
			if (ArrayHelper.isNotEmpty(list)) {
				String managerCode = list.get(0).getNewEmpId();
				customerDB.setManagerCode(managerCode);
				customerDB.setOrgCode(list.get(0).getNewOrgId());
				customerDB.setTeamOrgId(list.get(0).getNewTeamId());
			}
		}

		// 不需要生成开户申请书
		// customerDB.setCustState(CustomerState.WJHCZ.value);
		customerDB.setCustState(CustomerState.YKH.value);
		customerDB.preUpdate();
		customerDao.update(customerDB);

		// EmergencyContact emergencyContact = view.getEmergencyContact();
		// LoanConfiguration configuration = view.getLoanConfiguration();
		Address address = emergencyContact.getAddress();

		emergencyContact.setCustCode(customerDB.getCustCode());
		configuration.setCustCode(customerDB.getCustCode());

		EmergencyContact emergencyContactDB = emergencyContactDao
				.get(emergencyContact);
		if (null != emergencyContactDB) {
			if (null != emergencyContactDB.getAddress()) {
				address.preUpdate();
				addressDao.update(address);
			} else {
				address.preInsert();
				addressDao.insert(address);
			}

			emergencyContact.preUpdate();
			emergencyContactDao.update(emergencyContact);
		} else {
			address.preInsert();
			addressDao.insert(address);

			emergencyContact.preInsert();
			emergencyContactDao.insert(emergencyContact);
		}

		LoanConfiguration configurationDB = loanConfigurationDao
				.get(configuration);
		if (null != configurationDB) {
			configuration.preUpdate();
			loanConfigurationDao.update(configuration);
		} else {
			configuration.preInsert();
			loanConfigurationDao.insert(configuration);
		}
	}


	/**
	 * 将source参数拷贝到target对象中去 2016年1月8日
	 * 
	 * @author 孙凯文
	 * @param source
	 * @param target
	 */
	private void copyCustomerProperty(Customer source, Customer target) {
		target.setCustName(source.getCustName());
		target.setCustRealname(source.getCustName());
		target.setCustCertType(source.getCustCertType());
		target.setCustCertNum(source.getCustCertNum());
		target.setCustCertOrg(source.getCustCertOrg());
		target.setCustCertIssuedate(source.getCustCertIssuedate());
		target.setCustCertFailuredate(source.getCustCertFailuredate());
		target.setCustCertPermanent(source.getCustCertPermanent());
		target.setCustBirthday(source.getCustBirthday());
		target.setCustEducation(source.getCustEducation());
		target.setCustMarriage(source.getCustMarriage());
		target.setCustOccupation(source.getCustOccupation());
		target.setCustUnitScale(source.getCustUnitScale());
		target.setCustWorkExperience(source.getCustWorkExperience());
		target.setCustMotherName(source.getCustMotherName());
		target.setCustEname(source.getCustEname());
		target.setCustEmail(source.getCustEmail());
	}

	/**
	 * OCR上传保存附件 2016年5月3日 By 周俊
	 * 
	 * @param file
	 * @param openAccountView
	 */
	@Transactional(readOnly = false, value = "fortuneTransactionManager")
	public void saveUploadOcr(MultipartFile file,
			OpenAccountView openAccountView) {
		try {
			Attachment attachmen = FileUtil.saveFiles(file, openAccountView
					.getCustomer().getCustCode(), "客户信息");
			if (StringUtils.isNotEmpty(attachmen.getAttaFilepath())) {
				// 将其余的身份证类型文件设置成无效
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("attaFlag", FileConstants.FILE_TYPE_IDCARD);
				map.put("lendCode", openAccountView.getCustomer().getCustCode());
				map.put("isDiscard", EffectiveFlag.wx.value);
				map.put("modifyTime", new Date());
				map.put("modifyBy", UserUtils.getUserId());
				attachmentDao.updateSelectiveByIds(map);
				// 修改后保存
				attachmen.setDictAttaFlag(FileConstants.FILE_TYPE_IDCARD);// 文件类型：身份证
				attachmen.setAttaId(IdGen.uuid());// ID
				attachmen.setAttaFileOwner("t_tz_customer");
				attachmen.setAttaTableId(openAccountView.getCustomer().getId());
				attachmen.setLoanCode(openAccountView.getCustomer()
						.getCustCode());
				attachmen.setIsDiscard(EffectiveFlag.yx.value);
				attachmen.preInsert();
				attachmentDao.insert(attachmen);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 上传的身份证与页面参数的比较 2016年5月4日 By 周俊
	 * 
	 * @param attaTableId
	 * @param custRealname
	 * @param custCertNum
	 * @param sex
	 * @return
	 * @throws IOException
	 */
	public boolean uploadOcrEqParam(String attaTableId, String custRealname,
			String custCertNum, String sex) throws IOException {
		logger.info(custRealname+"======"+custCertNum+"======="+sex);
		// 根据attaTableId获取附件
		Attachment attachment = new Attachment();
		attachment.setAttaTableId(attaTableId);
		attachment.setIsDiscard(EffectiveFlag.yx.value);
		attachment.setAttaFileOwner("t_tz_customer");
		List<Attachment> list = attachmentDao.findList(attachment);
		if (ArrayHelper.isNotEmpty(list)) {
			attachment = list.get(0);
			String attaFilepath = attachment.getAttaFilepath();
			String attaNewfilename = attachment.getAttaNewfilename();
			DmService dmService = DmService.getInstance();
			InputStream inputStream = dmService.downloadDocument(attaFilepath);
			MultipartFile file = new MockMultipartFile(attaNewfilename,
					StreamUtils.copyToByteArray(inputStream));
			RecognizeResult result = RecognizeHelper.recognize(UserUtils
					.getUser().getId(), file);
			logger.info(result.getName()+"-----"+result.getCardnum()+"-----"+result.getSex());
			if (StringUtils.isEmpty(result.getName())
					|| StringUtils.isEmpty(result.getCardnum())
					|| StringUtils.isEmpty(result.getSex())) {
				logger.info("姓名，证件号，性别为空的情况"); 
				return false;
			}
			if (result.getName().equals(custRealname)
					&& result.getCardnum().equals(custCertNum)
					&& result.getSex().equals(SexFlag.parseByCode(sex))) {
				logger.info("姓名，证件号，性别相等"); 
				return true;
			}
			return false;
		} else {
			// 未上传身份证照片
			return true;
		}

	}

	public void updateCustomer(Customer customer) {
		customerDao.updateCustomer(customer);
	}

	
	/**
	 * 应对电销我的客户列表
	 * @param page
	 * @param customer
	 * @return
	 */
	public Page<Customer> findInvestListElectric(Page<Customer> page, Customer customer) {


		PageBounds pageBounds = new PageBounds(page.getPageNo(),
				page.getPageSize());
		PageList<Customer> pageList = (PageList<Customer>) customerDao
				.findInvestElectricList(customer, pageBounds);
		PageUtil.convertPage(pageList, page);
		// List<Customer> list = page.getList();
		// assemblePreIntoPoolTime(list); // 拼装预计进入公共池时间
		return page;
	}

	/**
	 * 上传客户信息
	 * 2016年12月24日
	 * 郭强
	 */
	public CustMode uploadexcel(MultipartFile file) {
		CustMode  cust = new  CustMode();
		String  rtn="";
		try {
			ImportExcel ie = new ImportExcel(file, 0, 0);
			List<CustName> list = ie.getDataList(CustName.class);
			if (ListUtils.isEmptyList(list)) {
				rtn = "请检查上传的数据是否填写有误";
				return cust;
			}
			cust = selectCustomer(list);
		} catch (Exception e) {
			rtn = "请检查上传的数据是否填写有误";
			logger.error("我的客户，上传excel，文件处理失败" + e.getMessage(), e);
		}
		cust.setRtn(rtn);
		return cust;
	}
	
	/**
	 * 根据客户信息获取客户编号
	 * 2016年12月24日
	 * 郭强
	 */
	public CustMode selectCustomer(List<CustName> list) {
		//上传数据的条数
		CustMode  cust = new  CustMode();
		cust.setNum(list.size());
		int count = 0;
		List<String>  lecdlist=new  ArrayList<String>();
		for (CustName model : list) {
			List<String> selectCustomer = tripleNewCustomerDao.selectCustomer(model);
			if(null!=selectCustomer&&0!=selectCustomer.size()){
				lecdlist.addAll(selectCustomer);
				count++;
			}
		}
		cust.setCount(count);
		cust.setList(lecdlist);
		return cust;
	}

}
