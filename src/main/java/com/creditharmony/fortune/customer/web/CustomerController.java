package com.creditharmony.fortune.customer.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSON;
import com.creditharmony.adapter.bean.in.security.Scy_SimpleInBean;
import com.creditharmony.adapter.bean.out.security.Scy_SimpleOutBean;
import com.creditharmony.adapter.constant.ReturnConstant;
import com.creditharmony.adapter.constant.SecurityScoreType;
import com.creditharmony.adapter.constant.ServiceType;
import com.creditharmony.adapter.core.client.ClientPoxy;
import com.creditharmony.common.util.ArrayHelper;
import com.creditharmony.common.util.DateUtils;
import com.creditharmony.core.common.type.BooleanType;
import com.creditharmony.core.common.type.SystemConfigConstant;
import com.creditharmony.core.fortune.type.BackState;
import com.creditharmony.core.fortune.type.CustomerState;
import com.creditharmony.core.fortune.type.LendchgType;
import com.creditharmony.core.fortune.type.VerifyPinType;
import com.creditharmony.core.fortune.type.WorkingState;
import com.creditharmony.core.ocr.dto.RecognizeResult;
import com.creditharmony.core.ocr.util.RecognizeHelper;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.role.type.FortuneRole;
import com.creditharmony.core.users.entity.ProvinceCity;
import com.creditharmony.core.users.entity.ResourceAuth;
import com.creditharmony.core.users.entity.User;
import com.creditharmony.core.users.service.MenuManager;
import com.creditharmony.core.users.type.FortuneOrgType;
import com.creditharmony.core.users.type.UserStatus;
import com.creditharmony.core.users.util.UserUtils;
import com.creditharmony.core.web.BaseController;
import com.creditharmony.fortune.back.money.common.service.BmManager;
import com.creditharmony.fortune.back.money.common.view.ListItemView;
import com.creditharmony.fortune.common.entity.ReturnMsg;
import com.creditharmony.fortune.customer.CustomerConstants;
import com.creditharmony.fortune.customer.entity.Advisory;
import com.creditharmony.fortune.customer.entity.ChangeTraces;
import com.creditharmony.fortune.customer.entity.CustMode;
import com.creditharmony.fortune.customer.entity.Customer;
import com.creditharmony.fortune.customer.entity.EmergencyContact;
import com.creditharmony.fortune.customer.entity.FortuneUser;
import com.creditharmony.fortune.customer.entity.LoanApply;
import com.creditharmony.fortune.customer.entity.LoanConfiguration;
import com.creditharmony.fortune.customer.service.AdvisoryManager;
import com.creditharmony.fortune.customer.service.ChangeTracesManager;
import com.creditharmony.fortune.customer.service.CustomerExportor;
import com.creditharmony.fortune.customer.service.CustomerManager;
import com.creditharmony.fortune.customer.service.LoanApplyManager;
import com.creditharmony.fortune.customer.util.CustomerJsonObject;
import com.creditharmony.fortune.customer.util.ExportCustomerElectricHelper;
import com.creditharmony.fortune.customer.util.RelationShipUtil;
import com.creditharmony.fortune.customer.view.ChangeTracesView;
import com.creditharmony.fortune.customer.view.CustomerView;
import com.creditharmony.fortune.customer.view.OpenAccountView;
import com.creditharmony.fortune.redemption.constant.RedeemConstant;
import com.creditharmony.fortune.sync.data.proxy.FortuneSyncDataEndAudit;
import com.creditharmony.fortune.triple.system.entity.IntCard;
import com.creditharmony.fortune.utils.FortuneDictUtil;
import com.creditharmony.fortune.utils.OptionUtil;
import com.creditharmony.fortune.verify.manager.CustomerVerifyManager;

/**
 * 客户管理类
 * 
 * @Class Name CustomerController
 * @author 孙凯文
 * @Create In 2015年12月2日
 */
@Controller
@RequestMapping("${adminPath}/customer/investment")
public class CustomerController extends BaseController {

	@Autowired
	private CustomerManager customerService;
	@Autowired
	private AdvisoryManager advisorService;
	@Autowired
	private LoanApplyManager loanApplyManager;
	@Autowired
	private ChangeTracesManager changeTracesManager;
	@Autowired
	private CustomerVerifyManager customerVerifyManager;
	@Autowired
	private FortuneSyncDataEndAudit fortuneSyncDataEndAudit;
	@Autowired
	private MenuManager menuManager;
	@Autowired
	private BmManager bmManager;
	/**
	 * 客户导出明细，导出文件名
	 */
	public static final String TO_CUSTOMER_DETAIL = "我的客户导出明细";

	/**
	 * 客户一览 2015年12月2日 By 孙凯文
	 * 
	 * @param model
	 * @param customer
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = { "list", "" })
	public String list(Model model, @ModelAttribute Customer customer, HttpServletRequest request, HttpServletResponse response) {
		fortuneSyncDataEndAudit.endAuditApproach();
		Page<Customer> page = new Page<Customer>(request, response);
		page.setOrderBy(CustomerConstants.DataViewConfig.DefaultOrderSql);
		customer.setManagerCode(UserUtils.getUserId());
		page = customerService.findInvestList(page, customer);

		model.addAttribute("customer", customer);
		model.addAttribute("page", page);
		String[] dictTypes = new String[] {"tz_customer_src","tz_customer_state", "tz_invest_state", "tz_data_source","tz_transfer_state"};
		FortuneDictUtil.addDicts(model, dictTypes);

		User user = (User) UserUtils.getSession().getAttribute(SystemConfigConstant.SESSION_USER_INFO);
		List<ResourceAuth> resultList = menuManager.findResourceAuth(user.getId(), user.getDepartment().getId(), "e32dd0d5977444ff8b2886c9becd5c5f", "cf:customer:transfer");
		if (ArrayHelper.isNotEmpty(resultList)) {
			model.addAttribute("isShow", true);
		} else {
			model.addAttribute("isShow", false);
		}
		return "customer/customerList";
	}

	/**
	 * 新增客户 2015年12月2日 By 孙凯文
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("goAdd")
	public String goAdd(Model model) {
		List<ProvinceCity> provinceList = OptionUtil.getProvinceList();
		model.addAttribute("customerView", new CustomerView());
		model.addAttribute("provinceList", provinceList);
		// 获取团队经理，营业部经理，营业部副经理，综合内勤
		List<FortuneUser> storelist = new ArrayList<FortuneUser>();
		List<FortuneUser> teamlist = new ArrayList<FortuneUser>();
		
		if(FortuneOrgType.TEAM.key.equals(RelationShipUtil.getCurrentOrg().getType())){
			String storeId = RelationShipUtil.getCurrentOrg().getParentId();
			storelist = RelationShipUtil.getOrgMember(
					storeId,
					Arrays.asList(new String[] { 
							FortuneRole.STORE_MANAGER.id,
							FortuneRole.STORE_ASSISTANT_MANAGER.id,
							FortuneRole.OFFICE_STAFF.id
					}),UserStatus.ON);
			if(storelist!=null){
				for (FortuneUser fortuneUser : storelist) {
					String roleName=FortuneRole.getNameMap().get(fortuneUser.getRoleId());					
					fortuneUser.setRoleName(roleName.split("-")[roleName.split("-").length-1]);
				}
			}
			teamlist = RelationShipUtil.getOrgMember(
					RelationShipUtil.getCurrentOrg().getId(),
					Arrays.asList(new String[] { 
							FortuneRole.TEAM_MANAGER.id
					}),UserStatus.ON);
			if(teamlist!=null){
				for (FortuneUser fortuneUser : teamlist) {
					String roleName=FortuneRole.getNameMap().get(fortuneUser.getRoleId());					
					fortuneUser.setRoleName(roleName.split("-")[roleName.split("-").length-1]);
				}
			}
		}
		List<FortuneUser> rtnlist = null;
		
		String type = RelationShipUtil.getCurrentOrg().getName();
		if("信和总部职能人员".equals(type)){
			// 职能部门理财经理
			rtnlist = new ArrayList<FortuneUser>();
			
			FortuneUser fortuneUser = new FortuneUser();
			User user = UserUtils.getUser();
			fortuneUser.setId(UserUtils.getUserId());
			fortuneUser.setRoleName("理财经理");
			fortuneUser.setName(user.getName());
			rtnlist.add(fortuneUser);
			
			model.addAttribute("teamManagerList", rtnlist);
		} else {
			rtnlist = new ArrayList<FortuneUser>();
			if(ArrayHelper.isNotEmpty(storelist)){
				rtnlist.addAll(storelist);
			}
			if(ArrayHelper.isNotEmpty(teamlist)){
				rtnlist.addAll(teamlist);
			}
			
			if(rtnlist.isEmpty()){
				// 如果没有任何的上级人员，则显示自己
				FortuneUser fortuneUser = new FortuneUser();
				User user = UserUtils.getUser();
				fortuneUser.setId(UserUtils.getUserId());
				fortuneUser.setRoleName("理财经理");
				fortuneUser.setName(user.getName());
				rtnlist.add(fortuneUser);
			}
			
			model.addAttribute("teamManagerList", rtnlist);
		}
		
		/*if(rtnlist.size()==0){
			// 不存在团队经理，营业部经理，营业部副经理，综合内勤，则显示自己
			FortuneUser fortuneUser = new FortuneUser();
			User user = UserUtils.getUser();
			fortuneUser.setId(UserUtils.getUserId());
			fortuneUser.setRoleName("理财经理");
			fortuneUser.setName(user.getName());
			rtnlist.add(fortuneUser);
		}
		
		model.addAttribute("teamManagerList", rtnlist);*/
		FortuneDictUtil.addDicts(model, new String[] { "sex",
				"tz_customer_src", "tz_communication_mode" });
		return "customer/customerAdd";
	}

	/**
	 * 添加客户 2015年12月25日 By 刘雄武
	 * 
	 * @param recaddView
	 * @param result
	 * @return
	 */
	@ResponseBody
	@RequestMapping("add")
	public String add(@ModelAttribute CustomerView recaddView) {
		Customer customer = new Customer();
		customer.setCustMobilephone(recaddView.getCustomer().getCustMobilephone());
		customer = customerService.getCustomer(customer);
		if(null != customer){
			return jsonMapper.toJson(new ReturnMsg("phone_exsit"));
		}else{
			String results = customerService.saveCustomer(recaddView);
			// return "redirect:" + adminPath + "/customer/investment";
			return jsonMapper.toJson(new ReturnMsg(results));
		}
	}

	/**
	 * 修改客户信息 2015年12月2日 By 孙凯文
	 * 
	 * @param customer
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public String update(@ModelAttribute Customer customer,
			HttpServletRequest request) {
		Map<String, String> param = new HashMap<String, String>();
		try{
			customerService.update(customer);
			param.put("result", "true");
			param.put("msg", "更新成功");
		}catch(Exception e){
			logger.error("更新客户信息异常：" + customer.getCustCode(), e);
			param.put("result", "false");
			param.put("msg", "更新异常" + e.getMessage());
		}
		return jsonMapper.toJson(param);
	}

	/**
	 * 跳转新增咨询页面 2015年12月2日 By 孙凯文
	 * 
	 * @param advisory
	 * @param model
	 * @return
	 */
	@RequestMapping("goAddAdvisory")
	public String goAddAdvisory(String customerCode, Model model) {
		Advisory advisory = new Advisory();
		advisory.setCustCode(customerCode);
		model.addAttribute("advisory", advisory);
		FortuneDictUtil.addDicts(model,
				new String[] { "tz_communication_mode" });
		return "customer/customerAdvisoryAdd";
	}

	/**
	 * 新增咨询 2015年12月3日 By 孙凯文
	 * 
	 * @param advisory
	 * @param model
	 * @return
	 */
	@RequestMapping("addAdvisory")
	@ResponseBody
	public String addAdvisory(@ModelAttribute Advisory advisory, Model model) {
		advisory.setManagerId(UserUtils.getUserId());
		//advisorService.save(advisory);
		advisorService.saveAdvisory(advisory);
		model.addAttribute("advisory", advisory);
		Map<String, String> map = new HashMap<String, String>();
		map.put("result", BooleanType.TRUE);
		return jsonMapper.toJson(map);
		// return "redirect:" + adminPath + "/customer/investment";
	}

	/**
	 * 查看咨询详细信息 2015年12月9日 By 孙凯文
	 * 
	 * @param advisoryId
	 * @param model
	 * @return
	 */
	@RequestMapping("advisoryDetail")
	public String goAdvisoryDetail(String advisoryId, Model model) {
		Advisory advisory = new Advisory();
		advisory.setId(advisoryId);
		advisory = advisorService.get(advisory);
		model.addAttribute("advisory", advisory);
		FortuneDictUtil.addDicts(model,
				new String[] { "tz_communication_mode" });
		return "customer/customerAdvisoryDetail";
	}

	/**
	 * 修改历史详细 2015年12月9日 By 孙凯文
	 * 
	 * @param changeTracesId
	 * @param model
	 * @return
	 */
	@RequestMapping("changeTracesDetail")
	public String goChangeTracesDetail(String changeTracesId, Model model) {
		ChangeTraces changeTraces = new ChangeTraces();
		changeTraces.setId(changeTracesId);
		changeTraces = changeTracesManager.get(changeTraces);

		String changeBeforeJson = changeTraces.getChangeBegin();
		String changeAfterJson = changeTraces.getChangeAfter();

		CustomerJsonObject changeBeforeObject = JSON.parseObject(
				changeBeforeJson, CustomerJsonObject.class);
		CustomerJsonObject changeAfterObject = JSON.parseObject(
				changeAfterJson, CustomerJsonObject.class);

		model.addAttribute("before", changeBeforeObject);
		model.addAttribute("after", changeAfterObject);

		FortuneDictUtil.addDicts(model,
				new String[] { "sex", "tz_customer_src" });

		return "customer/customerChangeTracesDetail";
	}

	/**
	 * 我的客户-办理
	 * 
	 * 2015年12月10日 By 孙凯文
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("handle")
	public ModelAndView showTab(HttpServletRequest request,
			HttpServletResponse response, Model model,
			@ModelAttribute Customer customer) {
		String pageName = request.getParameter("pageName");
		String custCode = request.getParameter("custCode");
		model.addAttribute("custCode", custCode);
		model.addAttribute("pageName", pageName);

		Page<Customer> pageSearch = new Page<Customer>(request, response);
		model.addAttribute("pageSearch", pageSearch);
		model.addAttribute("customerSearch", customer);
		customer.setCustCode(null);

		Customer temp = new Customer();
		temp.setCustCode(custCode);// 客户编号
		temp = customerService.getCustomer(temp);

		boolean belongToCurrentUser = temp.getManagerCode().equals(
				UserUtils.getUserId());
		model.addAttribute("belongToCurrentUser", belongToCurrentUser);
		// 首次加载
		if (null == pageName || "".equals(pageName)) {
			if (belongToCurrentUser) {
				// 客户基本信息
				return this.showCustomerTab(request, response, custCode, model);
			} else {
				ModelAndView mv = this.showAdvisoryTab(request, response,
						custCode, model);
				mv.setViewName("customer/customerHandle");
				return mv;
			}
		}
		if ("advisory".equals(pageName)) {
			// 咨询信息
			return this.showAdvisoryTab(request, response, custCode, model);
		} else if ("investment".equals(pageName)) {
			// 投资信息
			return this.showInvestmentTab(request, response, custCode, model);
		} else if ("modify".equals(pageName)) {
			if (belongToCurrentUser) {
				// 修改历史
				return this.showChangeTracesTab(request, response, custCode,
						model);
			}
		}

		return null;
	}

	/**
	 * 客户基本信息获取 2015年12月31日 By 孙凯文
	 * 
	 * @param custCode
	 * @return
	 */
	private ModelAndView showCustomerTab(HttpServletRequest request,
			HttpServletResponse response, String custCode, Model model) {
		Customer customer = new Customer();
		customer.setCustCode(custCode);// 客户编号
		customer = customerService.getCustomer(customer);

		ModelAndView m = new ModelAndView();
		m.setViewName("customer/customerHandle");
		m.addObject("customer", customer);
		if (CustomerState.DKHSP.value.equals(customer.getCustState())
				|| CustomerState.YKH.value.equals(customer.getCustState())
				|| CustomerState.KHJJ.value.equals(customer.getCustState())) {
			// 如果客户状态为：待开户审批、已开户、开户拒绝
			EmergencyContact emergencyContact = customerService
					.getEmergencyContact(customer.getCustCode());
			LoanConfiguration loanConfiguration = customerService
					.getLoanConfiguration(customer.getCustCode());
			m.addObject("emergencyContact", emergencyContact);
			m.addObject("loanConfiguration", loanConfiguration);
		}
		String provinceId = customer.getAddress().getAddrProvince();
		String cityId = customer.getAddress().getAddrCity();
		String districtId = customer.getAddress().getAddrDistrict();
		List<ProvinceCity> provinceList = OptionUtil.getProvinceList();
		List<ProvinceCity> cityList = OptionUtil
				.getCityByProvinceId(provinceId);
		List<ProvinceCity> districtList = OptionUtil
				.getDistrictByCityId(cityId);
		m.addObject("provinceList", provinceList);
		m.addObject("cityList", cityList);
		m.addObject("districtList", districtList);
		m.addObject("provinceId", provinceId);
		m.addObject("cityId", cityId);
		m.addObject("districtId", districtId);
		FortuneDictUtil.addDicts(model, new String[] { "tz_prof",
				"tz_unit_size", "tz_customer_src", "sex",
				"com_certificate_type", "tz_marital_state",
				"tz_billtaken_mode", "tz_yes_no", "tz_protocol_type",
				"tz_protocol_version" });
		return m;
	}

	/**
	 * 咨询信息获取 2015年12月31日 By 孙凯文
	 * 
	 * @param custCode
	 * @param page
	 * @return
	 */
	@RequestMapping("advisoryList")
	private ModelAndView showAdvisoryTab(HttpServletRequest request,
			HttpServletResponse response, String custCode, Model model) {
		Page<Advisory> page = new Page<Advisory>(request, response);
		page.setOrderBy(CustomerConstants.DataViewConfig.DefaultOrderSql);

		Customer customer = new Customer();
		customer.setCustCode(custCode);
		customer = customerService.getCustomer(customer);

		Advisory advisory = new Advisory();
		advisory.setCustCode(custCode);
		advisory.setManagerId(customer.getManagerCode());
		page.setFuncName("ajaxAdvisoryPage");
		page.setCountBy("ask_id");
		advisorService.findPage(page, advisory);

		ModelAndView m = new ModelAndView();
		m.addObject("page", page);
		m.setViewName("customer/customerAdvisoryList");

		FortuneDictUtil.addDicts(model,
				new String[] { "tz_communication_mode" });
		return m;
	}

	/**
	 * 投资信息获取 2015年12月31日 By 孙凯文
	 * 
	 * @param custCode
	 * @param page
	 * @return
	 */
	private ModelAndView showInvestmentTab(HttpServletRequest request,
			HttpServletResponse response, String custCode, Model model) {
		Page<LoanApply> page = new Page<LoanApply>(request, response);
		page.setOrderBy(CustomerConstants.DataViewConfig.DefaultOrderSql);

		LoanApply loanApply = new LoanApply();
		loanApply.setCustCode(custCode);
		page.setFuncName("ajaxInvestmentPage");
		loanApplyManager.findPage(page, loanApply);
		ModelAndView m = new ModelAndView();

		m.addObject("page", page);
		m.setViewName("customer/customerInvestmentList");
		FortuneDictUtil.addDicts(model, new String[] { "tz_pay_type",
				"tz_lend_state", "tz_finish_state" });
		return m;
	}

	/**
	 * 变更信息获取
	 * 
	 * 2015年12月31日 By 孙凯文
	 * 
	 * @param custCode
	 * @param page
	 * @return
	 */
	private ModelAndView showChangeTracesTab(HttpServletRequest request,
			HttpServletResponse response, String custCode, Model model) {

		Page<ChangeTraces> page = new Page<ChangeTraces>(request, response);
		page.setOrderBy(CustomerConstants.DataViewConfig.DefaultOrderSql);

		ChangeTraces modifyHis = new ChangeTraces();
		modifyHis.setAssociateId(custCode);
		modifyHis.setApplyId(CustomerConstants.WorkFlow.DefaultFlowApplyId);
		modifyHis.setChangeType(LendchgType.CUSTOMER_CHG.value);
		page.setFuncName("ajaxChangeTracesPage");
		page.setCountBy("change_id");
		changeTracesManager.findPage(page, modifyHis);

		List<ChangeTraces> changeList = page.getList();
		List<ChangeTracesView> customerViewList = new ArrayList<ChangeTracesView>();
		CustomerJsonObject jsonObject = null;
		ChangeTracesView changeView = null;
		if (null != changeList && changeList.size() > 0) {
			for (ChangeTraces item : changeList) {
				jsonObject = JSON.parseObject(item.getChangeAfter(),
						CustomerJsonObject.class);

				changeView = new ChangeTracesView();
				changeView.setId(item.getId());
				changeView.setCreateTime(item.getCreateTime());
				changeView.setChangeAfterCustomer(jsonObject);

				customerViewList.add(changeView);
			}
		}

		ModelAndView m = new ModelAndView();
		m.addObject("list", customerViewList);
		m.addObject("page", page);
		m.setViewName("customer/customerChangeTracesList");
		FortuneDictUtil.addDicts(model, new String[] { "tz_customer_src" });
		return m;
	}

	/**
	 * 开户时打开表单加载数据 2015年12月15日
	 * 
	 * @author 孙凯文
	 * @param param
	 * @return
	 */
	@RequestMapping({ "openTransferLend" })
	public String openTransferLend(Customer param, Model model) {

		Customer customer = customerService.getCustomer(param);
		EmergencyContact emergencyContact = customerService
				.getEmergencyContact(customer.getCustCode());
		model.addAttribute("customer", customer);
		model.addAttribute("emergencyContact", emergencyContact);
		model.addAttribute("provinceList", OptionUtil.getProvinceList());
		if (emergencyContact != null && emergencyContact.getAddress() != null) {
			model.addAttribute("cityList", OptionUtil
					.getCityByProvinceId(emergencyContact.getAddress()
							.getAddrProvince()));
			model.addAttribute("districtList", OptionUtil
					.getDistrictByCityId(emergencyContact.getAddress()
							.getAddrCity()));
		}
		model.addAttribute("loanConfiguration",
				customerService.getLoanConfiguration(customer.getCustCode()));
		return "/customer/transferLend";
	}

	/**
	 * 开户 2016年3月29日
	 * 
	 * @author 孙凯文
	 * @param redirectAttributes
	 * @param model
	 * @param view
	 * @return
	 */
	@RequestMapping({ "transferLend" })
	@ResponseBody
	public String transferLend(RedirectAttributes redirectAttributes,
			Model model, OpenAccountView view, HttpServletRequest request) {
		Customer customer = view.getCustomer();
		LoanConfiguration configuration = view.getLoanConfiguration();
		EmergencyContact emergencyContact = view.getEmergencyContact();
		try {

			customerService.transferLend(customer, configuration,
					emergencyContact, request);

			// 重新生成短信验证码
			String smsPin = customerVerifyManager.makePin();
			customerVerifyManager.updateVerifyInfo(VerifyPinType.SMS.value,
					customer.getCustCode(), smsPin);
			// 重新生成邮件验证码
			String emailPin = customerVerifyManager.makePin();
			customerVerifyManager.updateVerifyInfo(VerifyPinType.EMAIL.value,
					customer.getCustCode(), emailPin);
		} catch (Exception e) {
			String msg = e.getMessage();
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("content",
					"客户【"
							+ customer.getCustName()
							+ "】开户失败"
							+ ((null != msg && !"".equals(msg)) ? ":" + msg
									: ""));
			logger.error("客户【" + customer.getCustCode() + "】开户失败", e);
			return jsonMapper.toJson(new ReturnMsg("false"));
		}
		// 不需要生成开户申请书
		// try{
		// //开户生成开户申请书
		// Customer customerDB = customerService.getCustomer(customer);
		// customerService.makingFile(customerDB);
		// }catch(Exception e){
		// e.printStackTrace();
		// redirectAttributes.addFlashAttribute("content", "客户【"
		// + customer.getCustName() + "】开户申请书生成失败，请到我的客户合成开户申请书：" +
		// e.getMessage());
		// return "redirect:" + this.adminPath + "/customer/investment/list";
		// }
		return jsonMapper.toJson(new ReturnMsg("true"));
	}

	@RequestMapping({ "isExsitOfIdCard" })
	@ResponseBody
	public String isExsitOfIdCard(OpenAccountView view) {
		Map<String, String> msg = new HashMap<String, String>();
		Customer customer = view.getCustomer();
		// 验证chp内是否存在证件类型以及证件号
		customer.setCustCertType(view.getCustomer().getCustCertType());
		customer.setCustCertNum(view.getCustomer().getCustCertNum());
		if (customerService.getCustomerCertNumCnt(customer) > 0) {
			// chp内存在，不予开户
			msg.put("chp_exsit", "true");
			return jsonMapper.toJson(msg);
		}
		// 验证三网中该证件类型，证件号是否存在
		IntCard card = new IntCard();
		card.setCardType(view.getCustomer().getCustCertType());
		card.setCardId(view.getCustomer().getCustCertNum());
		if (customerService.getTripleCustomerCertNumCnt(card) > 0) {
			// 三网内存在，给予提示
			msg.put("exsit", "true");
		} else {
			msg.put("exsit", "false");

		}
		return jsonMapper.toJson(msg);

	}

	/**
	 * OCR识别 2016年1月20日 By 周俊
	 * 
	 * @return
	 */
	@RequestMapping(value = "/uploadOcr", method = RequestMethod.POST)
	@ResponseBody
	public RecognizeResult getOcrResult(
			@RequestParam(value = "file") MultipartFile file,
			OpenAccountView openAccountView) {
		RecognizeResult result = new RecognizeResult();
		try {
			result = RecognizeHelper.recognize(UserUtils.getUserId(), file);
			// 文件保存
			customerService.saveUploadOcr(file, openAccountView);
		} catch (Exception e) {
			result.setErrorCode(e.getMessage());
		}
		return result;
	}

	/**
	 * 公安系统对接
	 * 
	 * 2016年5月3日 By 朱杰
	 * 
	 * @param attaTableId
	 *            表Id
	 * @param custRealname
	 *            客户姓名
	 * @param custCertNum
	 *            客户身份证号
	 * @param sex
	 *            客户性别
	 * @return
	 */
	@RequestMapping(value = "/policeVerify", method = RequestMethod.POST)
	@ResponseBody
	public String policeVerify(String attaTableId, String custRealname,
			String custCertNum, String sex) {
		// OCR验证获取身份证号，姓名，性别
		// 与上传的参数对比身份证号，姓名，性别
		/*
		 * try { boolean boo = customerService.uploadOcrEqParam(attaTableId,
		 * custRealname, custCertNum, sex); if (!boo) { return jsonMapper
		 * .toJson(new ReturnMsg("身份证号,姓名,性别与身份证上有误,请核对")); } } catch
		 * (IOException e1) { return jsonMapper.toJson(new
		 * ReturnMsg("身份证号,姓名,性别与身份证上有误,请核对")); }
		 */
		// 调用公安系统对接接口
		try {
			ClientPoxy service = new ClientPoxy(ServiceType.Type.SCY_SIMPLE);
			Scy_SimpleInBean inParam = new Scy_SimpleInBean();
			inParam.setCustomerName(custRealname);
			inParam.setCustomerCretNum(custCertNum);
			Scy_SimpleOutBean outParam = (Scy_SimpleOutBean) service
					.callService(inParam);

			if (ReturnConstant.SUCCESS.equals(outParam.getRetCode())
					&& SecurityScoreType.SAME == outParam.getResult()) {
				// 认证成功,继续开户提交
				return jsonMapper.toJson(new ReturnMsg(""));
			} else {
				// 认证失败(三方认证失败,请求失败)
				return jsonMapper.toJson(new ReturnMsg("公安系统对接失败:"
						+ outParam.getRetMsg()));
			}
		} catch (Exception e) {
			return jsonMapper
					.toJson(new ReturnMsg("公安系统对接失败:" + e.getMessage()));
		}
	}
	
	
	/**
	 * 导出明细
	 * 2016年10月10日
	 * By liusl
	 * @param response
	 * @param Customer
	 */
	@RequestMapping("exportCustomerDetail")
	public void exportCustomerDetail(HttpServletResponse response,@ModelAttribute Customer customerView) {
		CustomerExportor exportor = new CustomerExportor(TO_CUSTOMER_DETAIL + DateUtils.getDate(RedeemConstant.YYYY_MM_DD));
		exportor.exportData(customerView, response);
	}
	
	
	

	
	/**
	 * 我的客户列表（电销部）
	 * 高旭
	 * @param model
	 * @param customer
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("listCustomerElectric")
	public String listCustomerElectric(Model model, @ModelAttribute Customer customer,
			HttpServletRequest request, HttpServletResponse response) {
		Page<Customer> page = new Page<Customer>(request, response);
		//customer.setManagerCode(UserUtils.getUserId());
		page = customerService.findInvestListElectric(page, customer);

		model.addAttribute("customer", customer);
		model.addAttribute("page", page);
		String[] dictTypes = new String[] {"tz_customer_src","tz_customer_state", "tz_invest_state", "tz_data_source","tz_transfer_state"};
		FortuneDictUtil.addDicts(model, dictTypes);
		return "customer/customerElectricList";
	}


	/**
	 * 导出明细  应对电销  高旭 2016年11月15日12:41:53
	 * @param response
	 * @param Customer
	 */
	@RequestMapping("exportCustomerElectric")
	public void exportCustomerElectric(HttpServletResponse response,@ModelAttribute Customer customerView) {
		try {
			String filename = DateUtils.formatDate(new Date(), "yyyy年MM月dd日") + "_导出明细";
			ExportCustomerElectricHelper exportor = new ExportCustomerElectricHelper(filename);
			exportor.exportData(customerView, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 修改在职状态
	 * 2016年12月22日
	 * 郭强
	 * @param request
	 * @param response
	 * @param list
	 * @return
	 */
	@RequestMapping("updateWorkingState")
	@ResponseBody
	public  String  updateWorkingState(HttpServletRequest request,
			HttpServletResponse response,ListItemView  list){
		String  dictBackStatus=BackState.DHKSQ.value+","
							  +BackState.DHKSQQR.value+","
							  +BackState.DHKSQQRTH.value+","
							  +BackState.HKBX.value+","
							  +BackState.HKBXTH.value;
		list.setDictBackStatus(dictBackStatus);
		list.setWorkingState(WorkingState.ZZ.value);
		String  str = bmManager.updateWorkingState(list);
		return  str;
	}

	/**
	 * 弹出上传excel弹窗
	 * 2016年12月23日
	 * By 郭强
	 * @param model
	 * @return
	 */
	@RequestMapping("uploadinvoke")
	public String uploadinvoke() {
		return "customer/customerUpadInvok";
	}
	
	/**
	 * 上传excel
	 * 2016年12月23日
	 * 郭强
	 * @param request
	 * @param response
	 * @param list
	 * @return
	 */
	@RequestMapping("uploadexcel")
	@ResponseBody
	public CustMode uploadexcel(MultipartFile file,ListItemView  list) {
		CustMode  cust = customerService.uploadexcel(file);
		String  dictBackStatus=BackState.DHKSQ.value+","
				  +BackState.DHKSQQR.value+","
				  +BackState.DHKSQQRTH.value+","
				  +BackState.HKBX.value+","
				  +BackState.HKBXTH.value;
		list.setDictBackStatus(dictBackStatus);
		list.setWorkingState(WorkingState.LZ.value);
		if(null!=cust.getList()&&0!=cust.getList().size()){
			list.setLendCodeList(cust.getList());
			bmManager.updateWorkingState(list);
		}
		
		return cust;
	}

}
