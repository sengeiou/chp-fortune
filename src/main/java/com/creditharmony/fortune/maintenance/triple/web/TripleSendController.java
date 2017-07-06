package com.creditharmony.fortune.maintenance.triple.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.web.BaseController;
import com.creditharmony.fortune.maintenance.triple.service.TripleSendManager;
import com.creditharmony.fortune.maintenance.triple.view.TripleView;
import com.creditharmony.fortune.triple.system.entity.IntCustomerBean;
import com.creditharmony.fortune.triple.system.entity.IntEmployeeBean;
import com.creditharmony.fortune.triple.system.entity.IntOrgBean;
import com.creditharmony.fortune.utils.FortuneDictUtil;

/**
 * 三网维护发送历史维护
 * @Class Name TripleMaintainController
 * @author 周俊
 * @Create In 2016年5月19日
 */
@Controller
@RequestMapping("${adminPath}/maintenance/tripleSend")
public class TripleSendController extends BaseController{
	
	@Autowired
	private TripleSendManager tripleSendManager;
	
	/**
	 * 发送历史____客户理财经理变更
	 * 2016年5月20日
	 * By 周俊
	 * @param model
	 * @param request
	 * @param response
	 * @param tripleView
	 * @return
	 */
	@RequestMapping("/managerChange")
	public String managerChange(Model model,HttpServletRequest request,HttpServletResponse response,TripleView tripleView){
		Page<IntCustomerBean> page = tripleSendManager.findManagerChange(new Page<IntCustomerBean>(request, response), tripleView);
		model.addAttribute("page",page);
		return baseMethod(model, tripleView,"managerChange");
	}
	
	/**
	 * 发送历史____组织机构
	 * 2016年5月20日
	 * By 周俊
	 * @param model
	 * @param request
	 * @param response
	 * @param tripleView
	 * @return
	 */
	@RequestMapping(value = "/orgChange")
	public String orgChange(Model model,HttpServletRequest request,HttpServletResponse response,TripleView tripleView){
		Page<IntOrgBean> page = tripleSendManager.findOrgChange(new Page<IntOrgBean>(request, response),tripleView);
		model.addAttribute("page",page);
	    return baseMethod(model, tripleView,"orgChange");
	}
	
	/**
	 * 发送历史____用户同步
	 * 2016年5月20日
	 * By 周俊
	 * @param model
	 * @param request
	 * @param response
	 * @param tripleView
	 * @return
	 */
	@RequestMapping("/userSynchro")
	public String userSynchro(Model model,HttpServletRequest request,HttpServletResponse response,TripleView tripleView){
		Page<IntEmployeeBean> page = tripleSendManager.findUserSynchro(new Page<IntEmployeeBean>(request, response), tripleView);
		model.addAttribute("page",page);
		return baseMethod(model, tripleView,"userSynchro");
	}
	
	/**
	 * 基础的返回结果
	 * 2016年5月20日
	 * By 周俊
	 */
	public String baseMethod(Model model,TripleView tripleView,String htmlToken){
		model.addAttribute("tripleView", tripleView);
		model.addAttribute("html",htmlToken);
		String[] types ={"sex","tz_certificate_type"};
		FortuneDictUtil.addDicts(model, types);
		return "maintenance/triple/sendHistory";
	}
	
}
