package com.creditharmony.fortune.app.webservice.ocr.server;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.activation.DataHandler;
import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.creditharmony.adapter.service.triple.bean.TripleNewCustomerInBean;
import com.creditharmony.bpm.filenet.utils.ConfigUtils;
import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.common.type.CertificateType;
import com.creditharmony.core.fortune.type.CustomerState;
import com.creditharmony.core.fortune.type.DataSource;
import com.creditharmony.core.fortune.type.TrustState;
import com.creditharmony.core.mapper.JsonMapper;
import com.creditharmony.core.role.type.FortuneRole;
import com.creditharmony.core.users.service.UserManager;
import com.creditharmony.core.users.util.PasswordUtil;
import com.creditharmony.dm.bean.DocumentBean;
import com.creditharmony.dm.service.DmService;
import com.creditharmony.fortune.app.webservice.ocr.constant.OcrConstants;
import com.creditharmony.fortune.app.webservice.ocr.dao.OcrDao;
import com.creditharmony.fortune.app.webservice.ocr.entity.BankInfoBean;
import com.creditharmony.fortune.app.webservice.ocr.entity.CertInfoBean;
import com.creditharmony.fortune.app.webservice.ocr.entity.Dictionary;
import com.creditharmony.fortune.app.webservice.ocr.entity.LoanAccountBank;
import com.creditharmony.fortune.app.webservice.ocr.entity.OcrCustomerInfo;
import com.creditharmony.fortune.app.webservice.ocr.entity.OcrInvestmentProduct;
import com.creditharmony.fortune.app.webservice.ocr.entity.ResultBean;
import com.creditharmony.fortune.app.webservice.ocr.entity.UserBean;
import com.creditharmony.fortune.app.webservice.ocr.utils.DataTypeUtils;
import com.creditharmony.fortune.app.webservice.ocr.utils.ExceptionUtil;
import com.creditharmony.fortune.app.webservice.ocr.utils.Utils;
import com.creditharmony.fortune.common.CustomerCodeGenerator;
import com.creditharmony.fortune.common.util.BeanUtil;
import com.creditharmony.fortune.constants.FileConstants;
import com.creditharmony.fortune.customer.dao.AddressDao;
import com.creditharmony.fortune.customer.dao.AdvisoryDao;
import com.creditharmony.fortune.customer.dao.CommonDao;
import com.creditharmony.fortune.customer.dao.CustomerAccountDao;
import com.creditharmony.fortune.customer.dao.CustomerDao;
import com.creditharmony.fortune.customer.entity.Address;
import com.creditharmony.fortune.customer.entity.Advisory;
import com.creditharmony.fortune.customer.entity.Customer;
import com.creditharmony.fortune.customer.entity.CustomerAccount;
import com.creditharmony.fortune.customer.entity.FortuneOrg;
import com.creditharmony.fortune.customer.service.CustomerManager;
import com.creditharmony.fortune.customer.util.RelationShipUtil;
import com.creditharmony.fortune.triple.system.dao.TripleNewCustomerDao;
import com.creditharmony.fortune.triple.system.entity.IntPhone;
import com.creditharmony.fortune.triple.system.entity.ext.IntDeliveryEx;
import com.creditharmony.fortune.triple.system.service.TripleCheckInfoService;
import com.creditharmony.fortune.triple.system.service.TripleNewCustomerService;
import com.creditharmony.fortune.utils.OptionUtil;

/**
 * OCR一期财富接口实现类
 * @author 王俊杰
 * @date 2016-4-8
 */
@Transactional(readOnly = true,value="fortuneTransactionManager")
public class OcrServerImpl implements IOcrServer {
	
	private Logger logger = LoggerFactory.getLogger(OcrServerImpl.class);
	
	@Autowired
	private OcrDao ocrDao;
	@Autowired
	private UserManager userManager;
	
	@Autowired
	private CustomerManager customerService;
	
	
	// 调用公共方法生成客户编号
	@Autowired
	private CommonDao commonDao;

	// 地址表操作类
	@Autowired
	private AddressDao addressDao;
	
	// 咨询数据表操作类
	@Autowired
	private AdvisoryDao advisoryDao;
	
	@Autowired
	private CustomerDao customerDao;
	
	// 客户银行账户信息
	private CustomerAccountDao customerAccountDao;
	
	// 三网接口
	@Resource
	TripleCheckInfoService tripleCheckInfoService;
	
	// 三网注册
	@Autowired
	private TripleNewCustomerService tripleNewCustomerService;
	
	@Autowired
	private TripleNewCustomerDao tripleNewCustomerDao;
	
	@Transactional(readOnly = true,value="fortuneTransactionManager")
	@Override
	public String login(String jsonStr) {
		logger.info("OCR登录用户信息:\t" + jsonStr);
		
		UserBean userBean = JsonMapper.nonDefaultMapper().fromJson(jsonStr, UserBean.class);
		String json = "";
		ResultBean rb = null;
		
		if (StringUtils.isNotEmpty(userBean.getLoginName()) && StringUtils.isNotEmpty(userBean.getPassword())){
			if (PasswordUtil.validPassword(userBean.getLoginName(), userBean.getPassword())){
				//验证通过，判断是否为客户经理
				List<String> roleList = ocrDao.selectRoleIdList(userBean.getLoginName());
				for (String roleId : roleList){
					//如果是客户经理，登陆成功
					if (FortuneRole.FINANCING_MANAGER.id.equals(roleId)){
						Map<String,Object> userInfo = ocrDao.selectNameAndOrg(userBean.getLoginName());
						userInfo.put("userId", userBean.getLoginName());
						userInfo.put("role", FortuneRole.FINANCING_MANAGER.name);
						userInfo.put("userName", userInfo.get("username"));
						userInfo.remove("username");
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("result", true);
						map.put("userInfo", userInfo);
						json = JsonMapper.nonDefaultMapper().toJson(map);
						logger.info("OCR登陆成功，返回信息：" + json);
						return json;
					}
				}
				rb = new ResultBean(false, "登录用户不是客户经理，不允许登录");
				json = JsonMapper.nonDefaultMapper().toJson(rb);
				return json;
			}else{
				rb = new ResultBean(false, "用户名或密码不正确");
				json = JsonMapper.nonDefaultMapper().toJson(rb);
				return json;
			}
		} else {
			rb = new ResultBean(false, "用户名和密码不能为空");
			json = JsonMapper.nonDefaultMapper().toJson(rb);
			return json;
		}
	}
	
	@Transactional(readOnly = true,value="fortuneTransactionManager")
	@Override
	public String getAccountBankList() {
		logger.info("OCR获取开户行字典列表");
		List<LoanAccountBank> bankList = ocrDao.getAccountBankList("jk_open_bank");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", true);
		map.put("loanAccountBankList", bankList);
		String json = JsonMapper.nonDefaultMapper().toJson(map);
		logger.info(json);
		return json;
	}
	
	@Transactional(readOnly = true,value="fortuneTransactionManager")
	@Override
	public String checkMobilephone(String mobilephone) {
		logger.info("OCR手机号有效性验证信息:\t" + mobilephone);
		
		@SuppressWarnings("unchecked")
		Map<String, String> map = JsonMapper.nonDefaultMapper().fromJson(mobilephone, Map.class);
		
		mobilephone = map.get("mobilephone");
		if (StringUtils.isEmpty(mobilephone)){
			return JsonMapper.nonDefaultMapper().toJson(new ResultBean(false, "手机号为空"));
		}
		IntPhone intPhone = new IntPhone();
		intPhone.setPhone(mobilephone);
		List<IntDeliveryEx> list = tripleCheckInfoService.findAllInfoByPhoneList(intPhone);
		
		ResultBean rs = null;
		if (list.size() == 0){
			rs = new ResultBean(true, "验证通过");
		} else {
			rs = new ResultBean(false, "验证不通过");
		}
		String json = JsonMapper.nonDefaultMapper().toJson(rs);
		logger.info(json);
		return json;
	}
	
	@SuppressWarnings("unused")
	@Transactional(readOnly = false,value="fortuneTransactionManager")
	@Override
	public String saveCustomerInfoNew(String jsonStr) {
		logger.info("OCR保存客户信息:\t" + jsonStr);
		try {
			if (jsonStr == null) {
				return Utils.returnMessage(false, "传入参数不能为空");
			}
			OcrCustomerInfo ocrLender = JsonMapper.nonDefaultMapper().fromJson(
					jsonStr, OcrCustomerInfo.class);
			if (ocrLender == null) {
				return Utils.returnMessage(false, "客户信息类转换异常");
			}
			// 客户信息
			String userid = ocrLender.getUserId();
			String name = ocrLender.getName();
			String sex = ocrLender.getSex();
			String source = ocrLender.getSource();
			String mobilephone = ocrLender.getMobilephone();
			// 身份证信息
			String birthday = ocrLender.getBirthday();
			String certOrg = ocrLender.getCertOrg();
			String certNum = ocrLender.getCertNum();
			String idStartDate = ocrLender.getIdStartDate();
			String idEndDate = ocrLender.getIdEndDate();
			// 地址信息
			String addressProvince = ocrLender.getAddressProvince();
			String addressCity = ocrLender.getAddressCity();
			String addressDistrict = ocrLender.getAddressDistrict();
			String address = ocrLender.getAddress();
			// 咨询信息
			String content = ocrLender.getContent();
			BigDecimal contributiveMoney = ocrLender.getContributiveMoney();
			String intentionProduct = ocrLender.getIntentionProduct();
			String loanMonth = ocrLender.getLoanMonth();
			// 账户信息
			String accountBank = ocrLender.getAccountBank();
			String accountid = ocrLender.getAccountid();
			String branch = ocrLender.getBranch();
			String bankProvince = ocrLender.getBankProvince();
			String bankCity = ocrLender.getBankCity();
			String bankDistrict = ocrLender.getBankDistrict();
			// 切图地址
			String picNamePath = ocrLender.getPicNamePath();
			String picCertPath = ocrLender.getPicCertPath();
			String bankPath = ocrLender.getBankPicPath();

			if (mobilephone == null || "".equals(mobilephone.trim())) {
				return Utils.returnMessage(false, "客户手机号码不能为空");
			}
			List<Customer> cmlist = ocrDao.getCustomerListByMobile(mobilephone);
			if (cmlist != null && cmlist.size() > 0) {
				return Utils.returnMessage(false, "该客户手机号码已被占用");
			}

			if (userid == null || "".equals(userid.trim())) {
				return Utils.returnMessage(false, "userid不能为空");
			}

			if (certNum != null) {
				certNum = certNum.trim();
				cmlist = ocrDao.getCustomerListByCert(certNum);
				if (cmlist != null && cmlist.size() > 0) {
					return Utils.returnMessage(false, "身份证号已被使用");
				}
			}
			if (idEndDate != null && !"".equals(idEndDate)) {
				if ("长期".equals(idEndDate)) {
					idEndDate = null;
				}
			}
			if (name == null || "".equals(name.trim())) {
				return Utils.returnMessage(false, "姓名不能为空");
			}

			if (sex == null || "".equals(sex.trim())) {
				return Utils.returnMessage(false, "性别不能为空");
			}
			if (source == null || "".equals(source.trim())) {
				return Utils.returnMessage(false, "客户来源不能为空");
			}

			// 处理省市区数据 ***开始
			if (addressProvince != null && !"".equals(addressProvince.trim())) {
				addressProvince = getProvinceCodeByName(addressProvince.trim());
				if (addressProvince == null) {
					return Utils.returnMessage(false, "检索不到该所在地区省份代码："
							+ ocrLender.getAddressProvince());
				}
				if (addressCity != null && !"".equals(addressCity.trim())) {
					// 根据省code来查市
					addressCity = getCityCodeByName(addressCity.trim(),
							addressProvince);
					if (addressCity == null) {
						return Utils.returnMessage(false, "检索不到该所在地区城市代码："
								+ ocrLender.getAddressCity());
					}
					// 根据市code来查县区
					if (addressDistrict != null
							&& !"".equals(addressDistrict.trim())) {
						addressDistrict = getDistrictCodeByName(
								addressDistrict.trim(), addressCity);
						if (addressDistrict == null) {
							return Utils.returnMessage(false, "检索不到该所在地区区县代码："
									+ ocrLender.getAddressDistrict());
						}
					}
				} else {
					addressDistrict = null;
				}
			} else {
				addressCity = null;
				addressDistrict = null;
			}
			// 处理省市区数据 ***结束
			int bankInfo = Utils.hasNull(accountBank, accountid, branch,
					bankProvince, bankCity, bankDistrict);
			if (bankInfo == 2) {
				return Utils.returnMessage(false, "请填写完整的银行卡信息");
			}
			if (contributiveMoney != null
					&& contributiveMoney.doubleValue() >= 100000000) {
				return Utils.returnMessage(false, "最大投资金额需小于一亿元");
			}
			List<String> otherOrgList = ocrDao.getOtherOrgByPhone(mobilephone);
			if (otherOrgList != null && otherOrgList.size() > 0) {
				return Utils.returnMessage(false, "该手机号码已被注册，归属地为："
						+ otherOrgList.get(0));
			}

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String custCode = CustomerCodeGenerator.getNextCode();//commonDao.getNextCustCode(); // 生成客户编码
			
			// 地址信息
			Address addr = new Address();
			addr.setAddr(address);
			addr.setAddrProvince(addressProvince);
			addr.setAddrCity(addressCity);
			addr.setAddrDistrict(addressDistrict);
			addr.preInsert();
			addressDao.insert(addr);

			// 保存客户信息
			Customer customer = new Customer();
			customer.setCustName(name); // 客户姓名
			customer.setDictCustSex(sex); // 客户性别
			customer.setDictCustSource(source); // 客户来源
			customer.setDataTerminal(DataSource.YDD.value); // 数据终端
			customer.setCustMobilephone(mobilephone); // 手机号码
			customer.setCustCode(custCode);
			customer.setManagerCode(userid);
			customer.setApplyHostedStatus(TrustState.WKH.value);
			// 身份证信息
			customer.setCustCertOrg(certOrg); // 发证机关
			customer.setCustCertType(CertificateType.SFZ.getCode()); // 证件类型
			customer.setCustCertNum(certNum); // 证件号码
			customer.setCustCertIssuedate((Date) sdf.parse(idStartDate)); // 签发日期
			customer.setCustCertFailuredate((Date) sdf.parse(idEndDate)); // 失效日期
			customer.setAddress(addr);

			// 理财经理ID
			customer.setManagerCode(userid);
			// 所属营业部ID
			FortuneOrg org = RelationShipUtil.getStoresOrg(userid);
			if (null != org) {
				customer.setOrgCode(org.getId());
			}

			// 团队机构Id
			FortuneOrg teamOrg = RelationShipUtil.getTeamOrg(userid);
			if (null != teamOrg) {
				customer.setTeamOrgId(teamOrg.getId());
			}
			customer.preInsert();
			customerDao.insert(customer);
			OrcServiceHelper.saveFileId2Attachment("t_tz_customer",
					customer.getId(), picNamePath,FileConstants.FILE_TYPE_XM);
			OrcServiceHelper.saveFileId2Attachment("t_tz_customer",
					customer.getId(), picCertPath,FileConstants.FILE_TYPE_IDCARD);
			// chp客户注册时向三网插入信息
			TripleNewCustomerInBean paramBean = new TripleNewCustomerInBean();
			paramBean.setOperation("C");
			paramBean.setOsId(custCode);
			paramBean.setOsType("XH_CHP");
			paramBean.setLoginName(customer.getCustName());
			paramBean.setPhone(customer.getCustMobilephone());
			// 根据理财经理id查询对应编号
			IntPhone phone = new IntPhone();
			phone.setId(userid);
			IntPhone ip = tripleNewCustomerDao.findEmpCodeById(phone);
			if (ip != null) {
				paramBean.setEmpCode(ip.getEmpCode());
			}
			tripleNewCustomerService.registerCustomer(BeanUtil.conv2IntCustomerBean(paramBean,customer)); // 插入数据

			Advisory advisor = new Advisory();
			advisor.setCustCode(custCode);
			advisor.setManagerId(userid);
			advisor.setAskDes(content);
			advisor.setAskProduct(intentionProduct);
			advisor.setAskInputMoney(contributiveMoney);
			advisor.setAskDate(sdf.parse(loanMonth));
			advisor.preInsert();
			advisoryDao.insert(advisor);

			CustomerAccount customerAccount = new CustomerAccount();
			// 处理银行账户省市区数据 ***开始
			if (bankProvince != null && !"".equals(bankProvince.trim())) {
				bankProvince = getProvinceCodeByName(bankProvince.trim());
				if (bankProvince == null) {
					return Utils.returnMessage(false, "检索不到该所在地区省份代码："
							+ ocrLender.getBankProvince());
				}
				if (bankCity != null && !"".equals(bankCity.trim())) {
					// 根据省code来查市
					bankCity = getCityCodeByName(bankCity.trim(), bankCity);
					if (bankCity == null) {
						return Utils.returnMessage(false, "检索不到该所在地区城市代码："
								+ ocrLender.getBankCity());
					}
					// 根据市code来查县区
					if (bankDistrict != null && !"".equals(bankDistrict.trim())) {
						bankDistrict = getDistrictCodeByName(
								bankDistrict.trim(), bankDistrict);
						if (addressDistrict == null) {
							return Utils.returnMessage(false, "检索不到该所在地区区县代码："
									+ ocrLender.getBankDistrict());
						}
					}
				} else {
					bankDistrict = null;
				}
			} else {
				bankCity = null;
				bankDistrict = null;
			}
			// 处理银行账户省市区数据 ***结束
			customerAccount.setCustomerCode(customer.getCustCode());
			customerAccount.setAccountNo(accountid);
			customerAccount.setAccountBranch(branch);
			customerAccount.setAccountAddrProvince(bankProvince);
			customerAccount.setAccountAddrCity(bankCity);
			customerAccount.setAccountAddrDistrict(bankDistrict);
			customerAccount.setAccountCardOrBooklet("01");
			customerAccount.setAccountName("");
			customerAccount.setAccountBankId(accountBank);
			customerAccount.setLendCode("");

			customerAccount.preInsert();
			customerAccountDao.insert(customerAccount);
			OrcServiceHelper.saveFileId2Attachment("t_tz_customer_account",
					customerAccount.getId(), bankPath, FileConstants.FILE_TYPE_YHKH);
			return Utils.returnMessage(true, "咨询用户保存成功");

		} catch (Exception e) {
			logger.error("OCR--------saveCustomerInfo--------报错\n", e);
			return Utils.returnMessage(false, "服务器异常");
		}
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true,value="fortuneTransactionManager")
	@Override
	public String getLendDictionaryList(String jsonStr) {
		logger.info("OCR获取数据字典信息:\t" + jsonStr);
		List<Dictionary> list = null;
		try {
			if (jsonStr == null) {
				return Utils.returnMessage(false, "传入参数不能为空");
			}
			Map<String, String> map = JsonMapper.nonDefaultMapper().fromJson(
					jsonStr, Map.class);
			if (map == null) {
				return Utils.returnMessage(false, "传入参数格式错误");
			}
			String typeCode = (String) map.get("typeCode").trim();
			if ("104".equals(typeCode)) {
				typeCode = "tz_customer_src"; // 客户来源
			} else if ("504".equals(typeCode)) {
				typeCode = "tz_open_bank"; // 开户行
			} else {
				return Utils.returnMessage(false, "传入参数错误");
			}

			list = ocrDao.getDictionaryByCode(typeCode);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("OCR--------getLendDictionaryList--------报错\n"
					+ ExceptionUtil.getStackTrace(e));
		}
		logger.error("OCR==========getLendDictionaryList结果:"
				+ JsonMapper.nonDefaultMapper().toJson(list));
		return JsonMapper.nonDefaultMapper().toJson(list);
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true,value="fortuneTransactionManager")
	@Override
	public String getBankAddressList(String jsonStr) {
		logger.info("OCR银行卡省市区列表查询:\t" + jsonStr);
		try {
			if (jsonStr == null) {
				return Utils.returnMessage(false, "传入参数不能为空");
			}
			Map<String, String> map = JsonMapper.nonDefaultMapper().fromJson(
					jsonStr, Map.class);
			if (map == null) {
				return Utils.returnMessage(false, "传入参数格式错误");
			}
			String province = (String) map.get("province");
			String city = (String) map.get("city");
			HashMap<String, String> params = new HashMap<String, String>(2);
			if (city != null && !"".equals(city.trim())) {
				if (province == null || "".equals(province.trim())) {
					return Utils.returnMessage(false, "请传入城市所在的省份");
				}
				params.put("areaType", "3");
				params.put("areaName", city);
				params.put("parentName", province);
				List<String> list = ocrDao.getBankDistrictList(params);
				return Utils.returnMessage(true, "已查询到" + city + "下的所有县区",
						"bankAddressList", list);
			}
			if (province != null && !"".equals(province.trim())) {
				params.put("areaType", "2");
				params.put("areaName", province);
				List<String> list = ocrDao.getBankAddressList(params);
				return Utils.returnMessage(true, "已查询到" + province + "下的所有市",
						"bankAddressList", list);
			}
			params.put("areaType", "1");
			params.put("areaName", "中华人民共和国");
			List<String> list = ocrDao.getBankAddressList(params);
			return Utils.returnMessage(true, "已查询到所有省份", "bankAddressList",
					list);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("OCR--------getBankAddressList--------报错\n"
					+ ExceptionUtil.getStackTrace(e));
			return Utils.returnMessage(true, "服务器异常");
		}
	}
	
	@Transactional(readOnly = true,value="fortuneTransactionManager")
	@Override
	public String getCertInfo(String jsonStr) {
		//财富补录身份证信息查询
		logger.info("OCR财富补录身份证信息查询:\t" + jsonStr);
		
		try {
			if (jsonStr == null) {
				return Utils.returnMessage(false, "传入参数不能为空");
			}
			// 获取查询参数对象
			CertInfoBean certInfoBean = JsonMapper.nonDefaultMapper().fromJson(jsonStr, CertInfoBean.class);
			
			if (certInfoBean == null) {
				return Utils.returnMessage(false, "传入参数格式错误");
			}
			// 查询客户对象
			OcrCustomerInfo customer = this.getCustomerByMobile(certInfoBean);
			
			if (customer != null) {
				// 增加身份证长期的判断
				String idStartDate = customer.getIdStartDate();
				String idEndDate = customer.getIdEndDate();
				
				if (idStartDate != null && !"".equals(idStartDate.trim()) && (idEndDate == null || "".equals(idEndDate.trim()))) {
					customer.setIdEndDate("长期");
				}
				return Utils.returnMessage(true, "查询成功", "customerInfo", customer);
				
			} else {
				return Utils.returnMessage(false, "查询无结果");
			}
		} catch (Exception e) {
			logger.error("OCR--------getCertInfo--------报错\n", e);
			return Utils.returnMessage(false, "服务器异常");
		}
		
	}
	
	@Transactional(readOnly = false,value="fortuneTransactionManager")
	@Override
	public String saveCertInfo(String jsonStr) {
		//财富补录身份证信息保存
		try {
			if (jsonStr == null) {
				return Utils.returnMessage(false, "传入参数不能为空");
			}
			// 获取参数对象
			OcrCustomerInfo customer = JsonMapper.nonDefaultMapper().fromJson(jsonStr, OcrCustomerInfo.class);
			
			if (customer == null) {
				return Utils.returnMessage(false, "身份证信息类转化异常");
			}
			String customerId = customer.getCustomerId();
			String certNum = customer.getCertNum();
			String birthday = customer.getBirthday();
			String certOrg = customer.getCertOrg();
			String idStartDate = customer.getIdStartDate();
			String idEndDate = customer.getIdEndDate();
			if (customerId == null || "".equals(customerId)) {
				return Utils.returnMessage(false, "客户主键不能为空");
			}
			if (certNum == null || "".equals(certNum.trim())) {
				return Utils.returnMessage(false, "身份证号码不能为空");
			}
			if (birthday == null || "".equals(birthday.trim())) {
				return Utils.returnMessage(false, "出生日期不能为空");
			}
			if (certOrg == null || "".equals(certOrg.trim())) {
				return Utils.returnMessage(false, "签发机关不能为空");
			}
			if (idStartDate == null || "".equals(idStartDate.trim())) {
				return Utils.returnMessage(false, "有效期开始时间不能为空");
			}
			if (idEndDate == null || "".equals(idEndDate.trim())) {
				return Utils.returnMessage(false, "有效期结束时间不能为空");
			} else {
				if ("长期".equals(idEndDate)) {
					customer.setIdEndDate(null);
				}
			}
			
			// 校验身份证的有效性，防止身份证重复
			//TODO --校验财富中存在此身份证
			Customer customerParam = new Customer();
			customerParam.setCustCertType(OcrConstants.TZ_CERTIFICATE_TYPE_0);
			customerParam.setCustCertNum(certNum);
			customerParam.setCustCode(customerId);
			int certCnt = customerService.getCustomerCertNumCnt(customerParam);
			//已存在: 查出数据，并且传递客户编号不=查出的客户编号
			if (certCnt > 0) {  
				return Utils.returnMessage(false, "身份证号码已占用");
			}
			// TODO 目前手机端没有传入
			customer.setModifyBy(customer.getUserId());
			customer.setModifyTime(new Date());
			
			// 更新信息
			this.ocrDao.updateCustomerCert(customer);
			// 保存身份证图片
			String tableName = "t_tz_customer";
			String tableId = "customer_code";
			OrcServiceHelper.saveFileId2Attachment(tableName, tableId, customer.getPicNamePath(), FileConstants.FILE_TYPE_XM);
			OrcServiceHelper.saveFileId2Attachment(tableName, tableId, customer.getPicCertPath(), FileConstants.FILE_TYPE_IDCARD);
			
			return Utils.returnMessage(true, "提交成功");
			
		} catch (Exception e) {
			logger.error("OCR--------saveCertInfo--------报错\n", e);
			return Utils.returnMessage(false, "服务器异常");
		}
		
	}
	
	@Transactional(readOnly = true,value="fortuneTransactionManager")
	@Override
	public String getBankInfo(String jsonStr) {
		// 财富补录银行卡信息查询
		try {
			if (jsonStr == null) {
				return Utils.returnMessage(false, "传入参数不能为空");
			}
			// 获取参数对象
			BankInfoBean bankInfoBean = JsonMapper.nonDefaultMapper().fromJson(jsonStr, BankInfoBean.class);
			
			if (bankInfoBean == null) {
				return Utils.returnMessage(false, "传入参数格式错误");
			}
			
			OcrCustomerInfo customer = this.getCustomerByCert(bankInfoBean);
			if (customer != null) {
				return Utils.returnMessage(true, "查询成功", "customerInfo", customer);
			} else {
				return Utils.returnMessage(false, "查询无结果");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("OCR--------getBankInfo--------报错\n");
			return Utils.returnMessage(false, "服务器异常");
		}
				
	}
	
	@Transactional(readOnly = false,value="fortuneTransactionManager")
	@Override
	public String saveBankInfoNew(String jsonStr) {
		try {
			if (StringUtils.isBlank(jsonStr)) {
				return Utils.returnMessage(false, "传入参数不能为空");
			}
			OcrCustomerInfo customer = JsonMapper.nonDefaultMapper().fromJson(jsonStr, OcrCustomerInfo.class);
			if (customer == null) {
				return Utils.returnMessage(false, "客户信息类转化异常");
			}
			String bankId = customer.getBankId();
			String accountBank = customer.getAccountBank();
			String accountId = customer.getAccountid();
			String userId = customer.getUserId();
			String customerId = customer.getCustomerId();
			String branch = customer.getBranch();
			String bankProvince = customer.getBankProvince();
			String bankCity = customer.getBankCity();
			String bankDistrict = customer.getBankDistrict();
			if (bankId == null || "".equals(bankId.trim())) {
				return Utils.returnMessage(false, "银行卡主键不能为空");
			}
			int bankInfo = Utils.hasNull(accountBank, accountId, branch, bankProvince, bankCity, bankDistrict);
			if (bankInfo != 1) {
				return Utils.returnMessage(false, "请填写完整的银行卡信息");
			} else {
				bankProvince = bankProvince.trim();
				bankProvince = OptionUtil.getProvinceCodeByName(bankProvince);
				if (bankProvince == null) {
					return Utils.returnMessage(false, "银行卡所在省检索不到代码：" + customer.getBankProvince());
				}
				bankCity = bankCity.trim();
				bankCity = OptionUtil.getCityCodeByName(bankCity, bankProvince);
				if (bankCity == null) {
					return Utils.returnMessage(false, "银行卡所在市检索不到代码：" + customer.getBankCity());
				}
				bankDistrict = bankDistrict.trim();
				bankDistrict = OptionUtil.getDistrictCodeByName(bankDistrict, bankCity);
				if (bankDistrict == null) {
					return Utils.returnMessage(false, "银行卡所在区县检索不到代码：" + customer.getBankDistrict());
				}
				customer.setBankProvince(bankProvince);
				customer.setBankCity(bankCity);
				customer.setBankDistrict(bankDistrict);
			}
			// 数据库中没有银行卡数据 需要重新插入一条 则用户客户id和客户经理idbichu
			if ("00000".equals(bankId)) {
				if(StringUtils.isBlank(userId)){
					return Utils.returnMessage(false, "客户经理主键不能为空");
				}
				if(StringUtils.isBlank(customerId)){
					return Utils.returnMessage(false, "客户主键不能为空");
				}
			}
			OrcServiceHelper.updateCustomerAccount(customer);
			return Utils.returnMessage(true, "提交成功");
		} catch (Exception e) {
			logger.error("财富(OCR)补录银行卡信息保存报错\n", ExceptionUtil.getStackTrace(e));
			return Utils.returnMessage(false, "服务器异常",DataTypeUtils.ERROR_SERVER_ERROR);
		}
	}
	
	@Override
	public String getInvestmentProductList() {
		try {
			List<OcrInvestmentProduct> orcProductList= OrcServiceHelper.getFullProductList();
			return Utils.returnMessage(true, "查询返回","investmentProductList",orcProductList);
		}catch (Exception e) {
			logger.error("【财富(ORC)系统app端投资模式列表查询失败:】\n" +ExceptionUtil.getStackTrace(e));
			e.getStackTrace();
			return Utils.returnMessage(false, "服务器异常",DataTypeUtils.ERROR_SERVER_ERROR);
		}
	}
	
	@Override
	public String modifyPassword(String jsonStr) {
		String retJson = "";
		String oldPassword = "";
		String newPassword = "";
		String loginName = "";
		try {
			if (StringUtils.isBlank(jsonStr)) {
				 logger.info("【财富(ORC)-传入修改登录密码参数为空！】");
				 return Utils.returnMessage(false, "传入参数不能为空!",DataTypeUtils.ERROR_JSONSTR_BLANK);
			} else {
					JSONObject jsStr = JSONObject.parseObject(jsonStr);
					oldPassword = jsStr.getString("password");
					newPassword = jsStr.getString("newPassword");
					loginName = jsStr.getString("loginName");
					if (StringUtils.isBlank(oldPassword)
							|| StringUtils.isBlank(newPassword)
							|| StringUtils.isBlank(loginName)) {
						 logger.info("【财富(ORC)-用户名密码或新密码不能为空！】");
						 return Utils.returnMessage(false, "用户名密码或新密码不能为空",DataTypeUtils.ERROR_NECESSARY_PARAM_BLANK);
					}
					else if (StringUtils.isNotBlank(oldPassword)
							&& StringUtils.isNotBlank(newPassword)) {
						if (PasswordUtil.validPassword(loginName, oldPassword)) {
							userManager.updatePasswordById(loginName, loginName,
									newPassword);
							logger.info(retJson);
							return Utils.returnMessage(true, "密码修改成功");
						} else {
							logger.info(retJson);
							return Utils.returnMessage(false, "用户名或旧密码不正确");
						}
					}
			}
		}catch (Exception e) {
			logger.error("【财富(ORC)系统app端修改密码失败:】\n" +ExceptionUtil.getStackTrace(e));
			e.getStackTrace();
			return Utils.returnMessage(false, "服务器异常",DataTypeUtils.ERROR_SERVER_ERROR);
		}
		return null;
	}

	
	@Override
	public String getNewVersion() {
		return "";
	}
	
	/**
	 * OCR一期财富补录身份证信息查询
	 * @author 张虎
	 * @date 2016-4-11
	 */
	public OcrCustomerInfo getCustomerByMobile(CertInfoBean certInfoBean) {
		
		OcrCustomerInfo customer = ocrDao.getCustomerByMobile(certInfoBean);
		if (customer != null) {
			String state = customer.getState();
			// TODO 状态转换
			customer.setLender(false);
			if (CustomerState.YKH.value.equals(state)) {
				//"已开户"
				customer.setLender(true);
			}
			customer.setState(null);
			String bankId = customer.getBankId();
			if (bankId == null || "".equals(bankId)) {
				customer.setBankId("00000");
			}
		}
		return customer;
	}
	
	/**
	 * OCR一期财富补录银行卡信息查询
	 * @author 张虎
	 * @date 2016-4-11
	 */
	public OcrCustomerInfo getCustomerByCert(BankInfoBean bankInfoBean) {
		
		OcrCustomerInfo customer = ocrDao.getCustomerByCert(bankInfoBean);
		if (customer != null) {
			String state = customer.getState();
			// TODO 状态转换
			customer.setLender(false);
			if (CustomerState.YKH.value.equals(state)) {
				//"已开户"
				customer.setLender(true);
			}
			customer.setState(null);
			String bankId = customer.getBankId();
			if (bankId == null || "".equals(bankId)) {
				customer.setBankId("00000");
			}
		}
		return customer;
	}
	
	/**
	 * OCR上传图片到CE系统
	 * @author 王俊杰
	 * @date 2016-5-6
	 */
	@Override
	public String uploadFile2CE(DataHandler handler) {
		DmService dmService = DmService.getInstance();
		String userName = ConfigUtils.getAdminUser();
		String password = ConfigUtils.getAdminPassword();
		dmService.auth4nonWeb(userName, password);
		DocumentBean documentBean = null;
		try {
			documentBean = dmService.createDocument(new Date().getTime() + "", 
					handler.getInputStream(), DmService.BUSI_TYPE_FORTUNE, "app_file", "picture_000001", "");
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
		return documentBean.getDocId();
	}
	
	/**
	 * 查询省编码
	 * 2016年4月15日
	 * By 周怀富
	 * @param name
	 * @return
	 */
	public String getProvinceCodeByName(String name) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("area_type", "1");
		params.put("parent_id", "0");
		params.put("area_name", name);
		return ocrDao.getAreaCodeByName(params);
	}
	
	 /**
     * 查询市编码
     * 2016年4月15日
     * By 周怀富
     * @param name
     * @param pareant_id
     * @return
     */
	public String getCityCodeByName(String name, String pareant_id) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("area_type", "2");
		params.put("parent_id", pareant_id);
		params.put("area_name", name);
		return ocrDao.getAreaCodeByName(params);
	}

   /**
    * 查询区编码
    * 2016年4月15日
    * By 周怀富
    * @param name
    * @param pareant_id
    * @return
    */
	public String getDistrictCodeByName(String name, String pareant_id) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("area_type", "3");
		params.put("area_name", name);
		params.put("parent_id", pareant_id);
		return ocrDao.getAreaCodeByName(params);
	}
}
