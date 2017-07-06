package com.creditharmony.fortune.teleSale.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.creditharmony.core.cache.UserCache;
import com.creditharmony.core.common.type.BooleanType;
import com.creditharmony.core.fortune.type.CustomerState;
import com.creditharmony.core.fortune.type.FileType;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.role.type.FortuneRole;
import com.creditharmony.core.users.entity.ProvinceCity;
import com.creditharmony.core.users.entity.User;
import com.creditharmony.core.users.util.UserUtils;
import com.creditharmony.core.web.BaseController;
import com.creditharmony.fortune.common.entity.UserRoleOrgEx;
import com.creditharmony.fortune.customer.CustomerConstants;
import com.creditharmony.fortune.customer.entity.Advisory;
import com.creditharmony.fortune.customer.entity.Customer;
import com.creditharmony.fortune.customer.entity.EmergencyContact;
import com.creditharmony.fortune.customer.entity.LoanConfiguration;
import com.creditharmony.fortune.customer.service.AdvisoryManager;
import com.creditharmony.fortune.customer.service.CustomerManager;
import com.creditharmony.fortune.look.apply.util.ExportExcel4BD;
import com.creditharmony.fortune.teleSale.entity.CustomerPool;
import com.creditharmony.fortune.teleSale.entity.ex.CustomerPoolEx;
import com.creditharmony.fortune.teleSale.service.CustomerPoolManager;
import com.creditharmony.fortune.utils.FortuneDictUtil;
import com.creditharmony.fortune.utils.OptionUtil;

/**
 * 电销公共池 controller
 * @Class Name TeleSaleController
 * @author 肖长伟
 * @Create In 2016年2月1日
 */
@Controller
@RequestMapping(value = "${adminPath}/customerPoolController")
public class CustomerPoolController extends BaseController {
	
	@Autowired
	private CustomerPoolManager customerPoolManager;
	@Autowired
	private AdvisoryManager advisoryManager;
	@Autowired
	private CustomerManager customerManager;
	
	
	/**
	 * 查询公共池中的客户信息，也用于条件查询
	 * 2016年2月1日
	 * By 肖长伟
	 * @param customerPool
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/listCustomerPool")
	public String listTeleSalePool(CustomerPool customerPool, HttpServletRequest request, HttpServletResponse response, Model model) {
		//查询数据
		Page<CustomerPool> page = customerPoolManager.getCustomerInPool(new Page<CustomerPool>(request, response), customerPool);
        // 设置页面数据及查询条件回填
		model.addAttribute("page", page).addAttribute("customerPool", customerPool);
		List<ProvinceCity> provinceList = OptionUtil.getProvinceList();
		model.addAttribute("provinceList", provinceList);
		return "customerPool/listCustomerPool";
	}
	
	
	/**
	 * 公共池列表，办理处理
	 * 2016年2月19日
	 * By 肖长伟
	 * @param request
	 * @param response
	 * @param model
	 * @param pageName
	 * @param custCode
	 * @param id
	 * @return
	 */
	@RequestMapping("/handle")
	public ModelAndView showTab(HttpServletRequest request, HttpServletResponse response, Model model, String pageName, String custCode, String id) {
		model.addAttribute("custCode", custCode);
		model.addAttribute("pageName", pageName);
		model.addAttribute("id", id);
		
		FortuneDictUtil.addDicts(model, new String[]{"tz_communication_mode"});
		
//		Role role = UserUtils.getUser().getRole();  //当前角色
//		String roleId = role.getId();
		String roleId = "6510000001";   //6510000002
		if (FortuneRole.TELE_FINANCING_MANAGER.id.equals(roleId)) { //是电销理财专员
			model.addAttribute("teleRoleType", "1");  //电销专员角色
		} else {
			model.addAttribute("teleRoleType", "0");  //非电销专员角色
		}
		if (StringUtils.isBlank(pageName) || "distribute".equals(pageName)) {   //客户分配
			return distributePage(id);
		} else if("advisory".equals(pageName)) {
			Page<Advisory> page = new Page<Advisory>(request, response);
			return advisoryPage(custCode, page);
		}
		return null;
	}
	
	/**
	 * 客户咨询Tab页面（公共池）
	 * 2016年2月19日
	 * By 肖长伟
	 * @param custCode
	 * @return
	 */
	private ModelAndView advisoryPage(String custCode, Page<Advisory> page) {
		ModelAndView modelView = new ModelAndView();
		modelView.setViewName("customerPool/customerPoolAdvisoryList");
		
		page.setOrderBy(CustomerConstants.DataViewConfig.DefaultOrderSql);
		page.setFuncName("ajaxAdvisoryPage");
		
		Customer customer = new Customer();
		customer.setCustCode(custCode);
		customer = customerManager.getCustomer(customer);
		Advisory advisory = new Advisory();
		advisory.setCustCode(custCode);
		page.setCountBy("ask_id");
		advisoryManager.findPage(page, advisory);
		
		modelView.addObject("page", page);
		modelView.addObject("custCode", custCode);
		
		return modelView;
	}

	/**
	 * 客户分配Tab页面 （公共池）
	 * 2016年2月19日
	 * By 肖长伟
	 * @param id
	 * @return
	 */
	private ModelAndView distributePage(String id) {
		ModelAndView modelView = new ModelAndView();
		modelView.setViewName("customerPool/customerPoolHandle");
		//查询公共池中客户数据
		CustomerPool customerPool = new CustomerPool();
		customerPool.setId(id);
		customerPool = customerPoolManager.getCustomerInPoolByPoolId(customerPool);
		modelView.addObject("customerPool", customerPool);
		//获取当前登录人的Id
		String userId = UserUtils.getUserId();
		String userName = UserUtils.getUser(UserUtils.getUserId()).getName();
		modelView.addObject("userId", userId);
		modelView.addObject("userName", userName);
		modelView.addObject("id", customerPool.getId());
		modelView.addObject("customerCode", customerPool.getCustomerCode());
		
		//获取原理财经理
		String oldManagerName = "";
		String oldManagerId = customerPool.getManagerId();
		if (oldManagerId != null) {
			User user = UserCache.getInstance().get(oldManagerId);
			if (user != null) {
				oldManagerName = user.getName();
			}
		}
		modelView.addObject("oldManagerName", oldManagerName);
		return modelView;
	}
	
	/**
	 * 电销客户咨询列表tab
	 * 2016年2月18日
	 * By 肖长伟
	 * @param request
	 * @param response
	 * @param model
	 * @param pageName
	 * @param custCode
	 * @param id
	 * @return
	 */
	@RequestMapping("/handleCustomerDistibute")
	public ModelAndView handleCustomerDistibute(HttpServletRequest request, HttpServletResponse response, Model model, String pageName, String custCode, String id) {
		String dataType = request.getParameter("dataType");
		String flag = request.getParameter("flag");
		model.addAttribute("custCode", custCode);
		model.addAttribute("pageName", pageName);
		model.addAttribute("id", id);
		model.addAttribute("dataType", dataType);
		
		FortuneDictUtil.addDicts(model, new String[]{"sex", "tz_customer_src", "tz_billtaken_mode", "tz_yes_no", "tz_protocol_type", 
				"tz_protocol_version", "tz_marital_state", "tz_certificate_type"});
		
		if (StringUtils.isBlank(pageName) || "information".equals(pageName)) {  //查看
			return getCustomerInfo(custCode, flag);
		} else if("advisory".equals(pageName)){  // 客户咨询
			Page<Advisory> page = new Page<Advisory>(request, response);
			return advisoryPage(custCode, page);
		} else if ("distribute".equals(pageName)) { //客户分配
			return distributePage4DistrPage(id);
		}
		return null;
	}

	/**
	 * 客户分配（电销客户咨询）
	 * 2016年2月19日
	 * By 肖长伟
	 * @param id
	 * @return
	 */
	private ModelAndView distributePage4DistrPage(String id) {
			ModelAndView modelView = new ModelAndView();
			modelView.setViewName("customerPool/distributeManager4List");
			//查询公共池中客户数据
			CustomerPool customerPool = new CustomerPool();
			customerPool.setId(id);
			CustomerPool customer = customerPoolManager.getCustomerInPoolByPoolId(customerPool);
			modelView.addObject("customerPool", customer);
			//获取当前登录人的Id
			String userId = UserUtils.getUserId();
			String userName = UserUtils.getUser(UserUtils.getUserId()).getName();
			modelView.addObject("userId", userId);
			modelView.addObject("userName", userName);
			modelView.addObject("id", customer.getId());
			modelView.addObject("customerCode", customer.getCustomerCode());
			
			return modelView;
		}
	
	/**
	 * 进入分配理财经理页面
	 * 2016年2月1日
	 * By 肖长伟
	 * @param poolId
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/distributePage")
	public String distributePage(CustomerPool customerPool, Model model) {
		try {
			//查询公共池中客户数据
			CustomerPool customer = customerPoolManager.getCustomerInPoolByPoolId(customerPool);
			model.addAttribute("customerPool", customer);
			//获取当前登录人的Id
			String userId = UserUtils.getUserId();
			String userName = UserUtils.getUser(UserUtils.getUserId()).getName();
			model.addAttribute("userId", userId);
			model.addAttribute("userName", userName);
			model.addAttribute("id", customer.getId());
			model.addAttribute("customerCode", customer.getCustomerCode());
			//获取原理财经理
			String oldManagerName = "";
			String oldManagerId = customer.getCustomerCode();
			if (oldManagerId != null) {
				User user = UserCache.getInstance().get(oldManagerId);
				oldManagerName = user.getName();
			}
			model.addAttribute("oldManagerName", oldManagerName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "customerPool/distributeManager";
	}
	
	/**
	 * 分配理财经理
	 * 2016年2月1日
	 * By 肖长伟
	 * @param customerPoolEx
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/distributeManager")
	public String distributeManger(CustomerPoolEx customerPoolEx, Model model, HttpServletRequest request, HttpServletResponse response) {
		//分配理财经理
		try {
			customerPoolManager.distributeManger(customerPoolEx);
		} catch (Exception e) {
			e.printStackTrace();
			//错误信息
		}
		if (StringUtils.isNotBlank(customerPoolEx.getIsListFlag()) && "1".equals(customerPoolEx.getIsListFlag())) {
			return listDistributeCustomer(new CustomerPoolEx(), request, response, model);
		} else {
			return listTeleSalePool(new CustomerPool(), request, response, model);
		}
	}
	
	/**
	 * 获取理财经理信息，用于电销分配理财经理
	 * 2016年2月2日
	 * By 肖长伟
	 * @return
	 */
	@RequestMapping("/getManagerList")
	public  String getManagerList(UserRoleOrgEx managerBeanEx, Model model, HttpServletRequest request, HttpServletResponse response) {
		//查询理财经理
		Page<UserRoleOrgEx> managerListPage = customerPoolManager.findManager(managerBeanEx, new Page<UserRoleOrgEx>(request, response));
		//FortuneRoleType
		model.addAttribute("page", managerListPage).addAttribute("customerPool", managerBeanEx);
		managerListPage.setFuncName("pageGetManagerList");
		return "customerPool/searchManager";
	}
	
	/**
	 * 获取电销专员信息，用于电销
	 * 2016年2月2日
	 * By 肖长伟
	 * @return
	 */
	@RequestMapping("/getTeleManagerList")
	public  String getTeleManagerList(UserRoleOrgEx managerBeanEx, Model model, HttpServletRequest request, HttpServletResponse response) {
		//查询理财经理
		managerBeanEx.setRoleId("");
		Page<UserRoleOrgEx> managerListPage = customerPoolManager.findTelManager(managerBeanEx, new Page<UserRoleOrgEx>(request, response));
		//FortuneRoleType
		model.addAttribute("page", managerListPage).addAttribute("customerPool", managerBeanEx);
		managerListPage.setFuncName("pageGetTeleManagerList");
		return "customerPool/searchTelManager";
	}
	
	
	/**
	 * 获取公共池客户的咨询信息
	 * 取当前登录人的咨询信息
	 * 2016年2月15日
	 * By 肖长伟
	 * @return
	 */
	@RequestMapping("/advisoryList")
	public ModelAndView advisoryList(String custCode, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		Page<Advisory> page = new Page<Advisory>(request, response);
		page.setOrderBy(CustomerConstants.DataViewConfig.DefaultOrderSql);
		
		Customer customer = new Customer();
		customer.setCustCode(custCode);
		customer = customerManager.getCustomer(customer);
		
		Advisory advisory = new Advisory();
		advisory.setCustCode(custCode);
		
		advisoryManager.findPage(page, advisory);
		
		ModelAndView m = new ModelAndView();
		m.addObject("page", page);
		m.addObject("custCode", custCode);
		FortuneDictUtil.addDicts(model, new String[]{"tz_communication_mode"});
		
		m.setViewName("customerPool/customerPoolAdvisoryList");

		return m;
	}
	
	/**
	 *  获取客户信息（用于电销）
	 * 2016年2月16日
	 * By 肖长伟
	 * @param custCode
	 * @return
	 */
	@RequestMapping("/getCustomerInfo")
	public  ModelAndView getCustomerInfo(String custCode, String flag) {
		Customer customer = new Customer();
		customer.setCustCode(custCode); // 客户编号
		customer = customerManager.getCustomer(customer);  //查询客户信息

		
		ModelAndView m = new ModelAndView();
		String url = "customerPool/customerDistributeHandInner";
		if ("1".equals(flag)) {
			url = "customerPool/customerDistributeHandle";
		}
		
		m.setViewName(url);
		m.addObject("customer", customer);
		if (CustomerState.DKHSP.value.equals(customer.getCustState())
				|| CustomerState.YKH.value.equals(customer.getCustState())
				|| CustomerState.KHJJ.value.equals(customer.getCustState())) {
			// 如果客户状态为：待开户审批、已开户、开户拒绝
			EmergencyContact emergencyContact = customerManager.getEmergencyContact(customer.getCustCode());
			LoanConfiguration loanConfiguration = customerManager.getLoanConfiguration(customer.getCustCode());
			m.addObject("emergencyContact", emergencyContact);
			m.addObject("loanConfiguration", loanConfiguration);

		} 
		String provinceId = customer.getAddress().getAddrProvince();
		String cityId = customer.getAddress().getAddrCity();
		String districtId = customer.getAddress().getAddrDistrict();
		List<ProvinceCity> provinceList = OptionUtil.getProvinceList();
		List<ProvinceCity> cityList = OptionUtil.getCityByProvinceId(provinceId);
		List<ProvinceCity> districtList = OptionUtil.getDistrictByCityId(cityId);
		m.addObject("provinceList", provinceList);
		m.addObject("cityList", cityList);
		m.addObject("districtList", districtList);
		m.addObject("provinceId", provinceId);
		m.addObject("cityId", cityId);
		m.addObject("districtId", districtId);

		return m;
	}
	
	/**
	 * 新增咨询
	 * 2016年2月15日
	 * By 肖长伟
	 * @param advisory
	 * @param model
	 * @return
	 */
	@RequestMapping("/addAdvisory")
	@ResponseBody
	public String addAdvisory(@ModelAttribute Advisory advisory, Model model, HttpServletRequest request, HttpServletResponse response) {
		advisory.setManagerId(UserUtils.getUserId());
		//advisoryManager.save(advisory);   			//保存
		advisoryManager.saveAdvisory(advisory);   			//保存并插入同步表  20160727 张新民
		// 修改 customer_pool 表中 lend_input_date字段
		customerPoolManager.updateLendInputDate(advisory);
		model.addAttribute("advisory", advisory);
		request.setAttribute("custCode", advisory.getCustCode());
		Map<String, String> map = new HashMap<String, String>();
		map.put("result", BooleanType.TRUE);
//		return "redirect:customerPoolController/advisoryList";
		return jsonMapper.toJson(map);
	}
	
	/**
	 * 获取 电销客户咨询列表
	 * 2016年2月15日
	 * By 肖长伟
	 * @param customerPoolEX
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("/listDistributeCustomer")
	public  String listDistributeCustomer(CustomerPoolEx customerPoolEX, HttpServletRequest request, HttpServletResponse response, Model model) {
		//查询数据
		Page<CustomerPoolEx> page = customerPoolManager.getDistributeCustomers(new Page<CustomerPoolEx>(request, response), customerPoolEX);
		// 设置页面数据及查询条件回填
		model.addAttribute("page", page);
		model.addAttribute("customerPoolEx", customerPoolEX);
		
		FortuneDictUtil.addDicts(model, new String[]{"tz_customer_pool_type"});
		
		return "customerPool/distributeCustomerList";
	}
	
	/**
	 * 退回电销中心
	 * 2016年2月16日
	 * By 肖长伟
	 * @return
	 */
	@RequestMapping("/sendBack2CustomerPool")
	@ResponseBody
	public String sendBack2CustomerPool(String custCode) {
		Map<String, String> map = new HashMap<String, String>();
		//退回电销
		customerPoolManager.sendBack2CustomerPool(custCode);
		map.put("result", BooleanType.TRUE);
		return jsonMapper.toJson(map);
	}
	
	/**
	 * 导出公共池数据，按条件或勾选的数据
	 * 2016年2月16日
	 * By 肖长伟
	 * @param request
	 * @param response
	 * @param customerPool
	 * @param checkedCodes
	 */
	@RequestMapping("/exportCustomerPool")
	public void exportCustomerPool(HttpServletRequest request,HttpServletResponse response, CustomerPool customerPool,String checkedCodes) {
		//1.取出数据
		 ExportExcel4BD exportExcel4BD = customerPoolManager.getCustomerPoolData4ExportExcel(customerPool, checkedCodes);
		//2.生成excel
		outExcelCommon( FileType.GGCLB.getName(), response, exportExcel4BD);
	}
	
	/**
	 * 导出excel
	 * 2016年4月27日
	 * By 肖长伟
	 * @param fileName
	 * @param response
	 * @param exportExcel4BD
	 */
	private void outExcelCommon(String fileName, HttpServletResponse response, ExportExcel4BD exportExcel4BD) {
		try {
			this.logger.info("写出文件到客户端" + fileName + ".xlsx");
			// 写出文件到客户端
			exportExcel4BD.write(response, fileName + ".xlsx");
			exportExcel4BD.dispose();
		} catch (IOException e) {
			this.logger.error("前10天到期提醒列表导出失败", e);
		}
	}
	
	
}
