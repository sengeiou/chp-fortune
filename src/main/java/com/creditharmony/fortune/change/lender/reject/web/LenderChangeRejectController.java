package com.creditharmony.fortune.change.lender.reject.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.creditharmony.bpm.frame.view.FlowPage;
import com.creditharmony.bpm.frame.view.WorkItemView;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.web.BaseController;
import com.creditharmony.fortune.change.lender.apply.entity.ext.CustomerEx;
import com.creditharmony.fortune.change.lender.apply.service.LenderChangeApplyManager;
import com.creditharmony.fortune.change.lender.apply.view.LenderChangeTaskView;
import com.creditharmony.fortune.change.lender.apply.view.LenderInitView;
import com.creditharmony.fortune.change.lender.reject.workflow.service.LenderChangeRejectFlowManager;
import com.creditharmony.fortune.utils.FortuneDictUtil;

/**
 * 出借人信息变更重新发起控制器
 * @Class Name LenderChangeController
 * @author 郭才林
 * @Create In 2015年11月19日
 */
@Controller
@RequestMapping({ "${adminPath}/lenderChange/reject" })
public class LenderChangeRejectController extends BaseController {

	@Autowired
	private LenderChangeApplyManager applyService;

	@Autowired
	private LenderChangeRejectFlowManager flowManager;

	/**
	 * 重新发起流程
	 * 2015年12月11日
	 * By 郭才林
	 * @param redirectAttributes
	 * @param workItem
	 * @param bv
	 * @param cust
	 * @param response
	 * @return
	 */
	@RequestMapping({ "newLaunchFlow" })
	public String newLaunchFlow(RedirectAttributes redirectAttributes, WorkItemView workItem, LenderInitView bv, CustomerEx cust, HttpServletResponse response) {

		flowManager.newLaunchFlow(workItem, bv);
		addMessage(redirectAttributes, new String[] { "客户：" + bv.getCustomer().getCustName() + "变更申请成功" });
		return "redirect:" + this.adminPath + "/lenderChange/reject/backList";

	}

	/**
	 * 获取退回队列
	 * 2015年12月11日
	 * By 郭才林
	 * @param model
	 * @param customer
	 * @param workItem
	 * @param response
	 * @return
	 */
	@RequestMapping(value = { "backList", "" })
	public String backList(Model model, CustomerEx customer, WorkItemView workItem,HttpServletRequest request, HttpServletResponse response) {

		Page<LenderChangeTaskView> page = new Page<LenderChangeTaskView>(request, response);
		FlowPage flowPage = new FlowPage();
		List<LenderChangeTaskView> itemList = flowManager.backList(workItem, customer,page,flowPage);
		FortuneDictUtil.addDicts(model, new String[]{"tz_customer_src"});
		model.addAttribute("page", flowPage);
		model.addAttribute("itemList", itemList).addAttribute("customer", customer);
		return "/lenderChange/lenderChangeBackList";
	}


}