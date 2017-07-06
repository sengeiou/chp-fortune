package com.creditharmony.fortune.change.lend.review.web;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.creditharmony.bpm.frame.view.FlowPage;
import com.creditharmony.bpm.frame.view.WorkItemView;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.web.BaseController;
import com.creditharmony.fortune.change.lend.apply.entity.LendChangeLog;
import com.creditharmony.fortune.change.lend.apply.entity.LendLoanApply;
import com.creditharmony.fortune.change.lend.apply.entity.LendQueryView;
import com.creditharmony.fortune.change.lend.approve.entity.LendChangeTaskView;
import com.creditharmony.fortune.change.lend.review.manager.LendFlowReviewManager;
import com.creditharmony.fortune.utils.FortuneDictUtil;



/**
 * 出借信息修改Controller层
 * @Class Name LendChangeController
 * @author 刘雄武
 * @Create In 2015年11月30日
 */
@Controller
@RequestMapping({ "${adminPath}/lendChangeReview" })
public class LendChangeFinalApproveController extends BaseController {

	@Resource
	private LendFlowReviewManager flowManager;
	
    
	/**
	 * 获取复审审任务
	 * 2015年12月25日
	 * By 刘雄武
	 * @param model
	 * @param flag
	 * @param lendloanapply
	 * @param workItem
	 * @param response
	 * @return
	 */
	@RequestMapping(value = { "fetchTaskItemsReview", "" })
	public String fetchTaskItemsReview(Model model, boolean flag, LendQueryView queryView,
			WorkItemView workItem,HttpServletRequest request, HttpServletResponse response) {
			
		Page<LendChangeTaskView> page = new Page<LendChangeTaskView>(request, response);
		FlowPage flowPage = new FlowPage();
		List<LendChangeTaskView> itemList=flowManager.fetchTaskItemsReview(workItem,queryView,page,flowPage);
		FortuneDictUtil.addDicts(model, new String[]{"tz_trust_state","tz_pay_type","tz_lend_state","tz_customer_state","tz_customer_src"});
		model.addAttribute("page", flowPage);
		model.addAttribute("itemList", itemList).addAttribute("queryView",queryView);
		return "/lendChange/fetchTaskItemsReview";
	}
	
	/**
	 * 处理流程->复审
	 * 2015年12月8日
	 * By 刘雄武
	 * @param workItem
	 * @param redirectAttributes
	 * @param response
	 * @param changelog
	 * @return
	 */
	@RequestMapping({ "review" })
	public String review(WorkItemView workItem,
			RedirectAttributes redirectAttributes, HttpServletResponse response,LendChangeLog changelog,LendLoanApply lendLoanApply) {
		
		flowManager.review(workItem,changelog,lendLoanApply);
		
		addMessage(redirectAttributes, new String[] { "已成功处理一条信息" });
		return "redirect:" + this.adminPath + "/lendChangeReview/fetchTaskItemsReview";
	}
	
}
