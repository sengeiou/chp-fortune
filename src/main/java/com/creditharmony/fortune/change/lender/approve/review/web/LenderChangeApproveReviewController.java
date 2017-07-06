package com.creditharmony.fortune.change.lender.approve.review.web;

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
import com.creditharmony.fortune.change.lender.apply.entity.LenderChangeLog;
import com.creditharmony.fortune.change.lender.apply.entity.ext.CustomerEx;
import com.creditharmony.fortune.change.lender.apply.view.LenderChangeTaskView;
import com.creditharmony.fortune.change.lender.approve.review.service.LenderChangeApproveReviewManager;
import com.creditharmony.fortune.change.lender.approve.review.workflow.service.LenderApproveReviewFlowManager;

/**
 * 出借人信息变更复审控制器
 * @Class Name LenderChangeController
 * @author 郭才林
 * @Create In 2015年11月19日
 */
@Controller
@RequestMapping({ "${adminPath}/lenderChangeApprove/review" })
public class LenderChangeApproveReviewController extends BaseController {

	@Autowired
	private LenderChangeApproveReviewManager reviewManager;

	@Autowired
	private LenderApproveReviewFlowManager flowManager;

	/**
	 * 获取复审审任务 
	 * 2015年12月2日
	 * By 郭才林
	 * @param model
	 * @param customer
	 * @param workItem
	 * @param response
	 * @return
	 */
	@RequestMapping(value = { "fetchTaskItemsReview", "" })
	public String fetchTaskItemsReview(Model model, CustomerEx customer, WorkItemView workItem,HttpServletRequest request, HttpServletResponse response) {

		Page<LenderChangeTaskView> page = new Page<LenderChangeTaskView>(request, response);
		FlowPage flowPage = new FlowPage();
		@SuppressWarnings("unchecked")
		List<LenderChangeTaskView> itemList = flowManager.fetchTaskItemsReview(workItem, customer,page,flowPage);
		model.addAttribute("page", flowPage);
		model.addAttribute("itemList", itemList).addAttribute("customer", customer);
		return "/lenderChange/fetchTaskItemsReview";
	}
	
	/**
	 * 处理流程，复审
	 * 2015年12月2日
	 * By 郭才林
	 * @param workItem
	 * @param redirectAttributes
	 * @param response
	 * @param changelog
	 * @return
	 */
	@RequestMapping({ "review" })
	public String review(WorkItemView workItem, RedirectAttributes redirectAttributes, HttpServletResponse response, LenderChangeLog changelog,CustomerEx cust) {
		
		flowManager.review(workItem, changelog, cust);

		addMessage(redirectAttributes, new String[] { "已成功处理一条信息" });
		return "redirect:" + this.adminPath + "/lenderChangeApprove/review/fetchTaskItemsReview";
	}

}