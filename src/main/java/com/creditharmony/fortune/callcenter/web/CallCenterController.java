package com.creditharmony.fortune.callcenter.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.users.entity.User;
import com.creditharmony.core.web.BaseController;
import com.creditharmony.fortune.callcenter.entity.CallCenterCustomer;
import com.creditharmony.fortune.callcenter.service.CallCenterManager;
import com.creditharmony.fortune.common.entity.Org;

/**
 * 呼叫中心controller
 * @Class Name CallCenterController
 * @author 肖长伟
 * @Create In 2016年3月1日
 */
@Controller
@RequestMapping("${adminPath}/callCenterController")
public class CallCenterController extends BaseController {
	
	@Autowired
	private CallCenterManager callCenterManager;
	
	
	/**
	 * s
	 * 2016年3月1日
	 * By 肖长伟
	 * @param customer
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("/getCallCenterCustomerList")
	public String  getCallCenterCustomerList(CallCenterCustomer customer, HttpServletRequest request, HttpServletResponse response, Model model) {
		//查询数据
		Page<CallCenterCustomer> page = callCenterManager.getCallCenterCustomerList(new Page<CallCenterCustomer>(request, response), customer);
        // 设置页面数据及查询条件回填
		model.addAttribute("page", page);
		model.addAttribute("customer", customer);
		return "callCenter/callCenterCustomerList";
	}
	
	/**
	 * 城市客户展示列表， 用户城市经理指定门店
	 * 2016年3月1日
	 * By 肖长伟
	 * @param customer
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("/getList4CityManager")
	public String getList4CityManager(CallCenterCustomer customer, HttpServletRequest request, HttpServletResponse response, Model model) {
		//查询数据
		Page<CallCenterCustomer> page = callCenterManager.getList4CityManager(new Page<CallCenterCustomer>(request, response), customer);
        // 设置页面数据及查询条件回填
		model.addAttribute("page", page);
		model.addAttribute("customer", customer);
		return "callCenter/callCenterList4City";
	}
	
	/**
	 * 门店客户展示，用于门店经理指定理财经理
	 * 2016年3月1日
	 * By 肖长伟
	 * @param customer
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("/getList4StoreManager")
	public String getList4StoreManager(CallCenterCustomer customer, HttpServletRequest request, HttpServletResponse response, Model model) {
		//查询数据
		Page<CallCenterCustomer> page = callCenterManager.getList4StoreManager(new Page<CallCenterCustomer>(request, response), customer);
        // 设置页面数据及查询条件回填
		model.addAttribute("page", page);
		model.addAttribute("customer", customer);
		return "callCenter/callCenterList4Store";
	}
	
	/**
	 * 返回城市经理分配门店页面
	 * 2016年3月1日
	 * By 肖长伟
	 * @return
	 */
	@RequestMapping("/getCityDistributePage")
	public String getCityDistributePage(CallCenterCustomer customer, Model model) {
		List<Org> orgList = callCenterManager.getChildrenOrg();
		model.addAttribute("customer", callCenterManager.getCallCenterCustomerById(customer));
		model.addAttribute("storeList", orgList);
		return "callCenter/callCenterCityDistribute";
	}
	
	/**
	 * 返回 门店经理分配理财经理页面
	 * 2016年3月1日
	 * By 肖长伟
	 * @return
	 */
	@RequestMapping("/getStoreDistributePage")
	public String getStoreDistributePage(CallCenterCustomer customer, Model model) {
		List<User> managerList = callCenterManager.getSubFinancialManagerList();
		model.addAttribute("customer", callCenterManager.getCallCenterCustomerById(customer));
		model.addAttribute("managerList", managerList);
		return "callCenter/callCenterStoreDistribute";
	}
	
	/**
	 * 分配门店
	 * 2016年3月1日
	 * By 肖长伟
	 * @param customer
	 * @return
	 */
	@RequestMapping("/distributeStore")
	@ResponseBody
	public String distributeStore(CallCenterCustomer customer) {
		callCenterManager.distributeStore(customer);
		Map<String, Object> rsMap = new HashMap<String, Object>();
		rsMap.put("result", "true");
		rsMap.put("msg", "设置门店成功");
		return jsonMapper.toJson(rsMap);
	}
	
	/**
	 * 分配理财经理
	 * 2016年3月1日
	 * By 肖长伟
	 * @param customer
	 * @return
	 */
	@RequestMapping("/distributeManager")
	@ResponseBody
	public String distributeManager(CallCenterCustomer customer) {
		String msg = callCenterManager.distributeManager(customer);
		Map<String, Object> rsMap = new HashMap<String, Object>();
		if ("分配成功".equals(msg)) {
			rsMap.put("result", "true");
		} else {
			rsMap.put("result", "false");
		}
		rsMap.put("msg", msg);
		return jsonMapper.toJson(rsMap);
	}
	
	
	
}
