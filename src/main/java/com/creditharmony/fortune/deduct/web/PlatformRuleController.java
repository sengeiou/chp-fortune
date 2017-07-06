package com.creditharmony.fortune.deduct.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.creditharmony.core.fortune.type.PlateformRuleState;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.web.BaseController;
import com.creditharmony.fortune.deduct.service.PlatformRuleManager;
import com.creditharmony.fortune.deduct.entity.PlatformRule;

/**
 * 划扣限额配置Controller
 * 
 * @Class Name PlatformRuleController
 * @author 陈广鹏
 * @Create In 2016年2月15日
 */
@Controller
@RequestMapping("${adminPath}/deduct/platform/")
public class PlatformRuleController extends BaseController {
	
	@Autowired
	private PlatformRuleManager manager;

	/**
	 * 查询列表
	 * 2016年2月15日
	 * By 陈广鹏
	 * @param request
	 * @param response
	 * @param rule
	 * @param model
	 * @return
	 */
	@RequestMapping("listRules")
	public String listRules(HttpServletRequest request,
			HttpServletResponse response, PlatformRule rule, Model model) {
		
		Page<PlatformRule> page = new Page<PlatformRule>(request, response);
		page = manager.findPlatformRuleList(page, rule);
		
		model.addAttribute(page);
		model.addAttribute(rule);

		return "deduct/ruleList";
	}
	
	/**
	 * 显示新增弹窗
	 * 2016年2月16日
	 * By 陈广鹏
	 * @return
	 */
	@RequestMapping("toAddRule")
	public String toAddRule(){
		return "deduct/popAddRule";
	}
	
	/**
	 * 新增规则
	 * 2016年2月16日
	 * By 陈广鹏
	 * @return
	 */
	@RequestMapping("addRule")
	public String addRule(PlatformRule rule){
		rule.setStatus(PlateformRuleState.DSH.value);
		manager.save(rule);
		return "redirect:listRules";
	}
	
	/**
	 * 显示编辑弹窗
	 * 2016年2月16日
	 * By 陈广鹏
	 * @return
	 */
	@RequestMapping("toEditRule")
	public String toEditRule(String id, Model model){
		PlatformRule rule = manager.get(id);
		model.addAttribute("rule", rule);
		return "deduct/popEditRule";
	}
	
	/**
	 * 编辑规则
	 * 2016年2月16日
	 * By 陈广鹏
	 * @return
	 */
	@RequestMapping("editRule")
	public String editRule(PlatformRule rule, Model model){
		rule.setStatus(PlateformRuleState.DSH.value);
		manager.save(rule);
		return "redirect:listRules";
	}
	
	/**
	 * 启用规则
	 * 2016年2月16日
	 * By 陈广鹏
	 * @return
	 */
	@RequestMapping("startUse")
	public String startUse(PlatformRule rule, Model model){
		manager.startUse(rule);
		return "forward:listRules";
	}
	
	/**
	 * 停用规则
	 * 2016年2月16日
	 * By 陈广鹏
	 * @return
	 */
	@RequestMapping("stopUse")
	public String stopUse(PlatformRule rule, Model model){
		manager.stopUse(rule);
		return "forward:listRules";
	}
	
	/**
	 * 显示审核规则弹窗
	 * 2016年2月16日
	 * By 陈广鹏
	 * @return
	 */
	@RequestMapping("toExamine")
	public String toExamine(){
		return "deduct/popExamineRule";
	}
	
	/**
	 * 审核规则
	 * 2016年2月16日
	 * By 陈广鹏
	 * @return
	 */
	@RequestMapping("ruleExamine")
	public String ruleExamine(){
		return "forward:listRules";
	}
	
}
