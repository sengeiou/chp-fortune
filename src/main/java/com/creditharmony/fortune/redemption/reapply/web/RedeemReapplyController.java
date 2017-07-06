package com.creditharmony.fortune.redemption.reapply.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.creditharmony.bpm.frame.service.FlowService;
import com.creditharmony.bpm.frame.view.FlowPage;
import com.creditharmony.bpm.frame.view.WorkItemView;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.web.BaseController;
import com.creditharmony.fortune.redemption.common.view.RedemptionApplyEntity;
import com.creditharmony.fortune.redemption.common.view.RedemptionFlowTaskItemView;
import com.creditharmony.fortune.redemption.reapply.service.RedeemReapplyManager;
import com.creditharmony.fortune.utils.FortuneDictUtil;

/**
 * 赎回审批失败再申请Controller
 * @Class Name ReapplyController
 * @author 陈广鹏
 * @Create In 2016年4月14日
 */
@Controller
@RequestMapping("${adminPath}/myApply/redemption/")
public class RedeemReapplyController extends BaseController {
	
	@Autowired
	private FlowService flowService;
	@Autowired
	private RedeemReapplyManager reapplyManager;
	
	/**
	 * 获取审批退回列表
	 * 2015年12月21日
	 * By 陈广鹏
	 * @param model
	 * @param entity
	 * @return
	 */
	@RequestMapping("backList")
	public String fetchBackList(Model model, RedemptionApplyEntity entity,
			HttpServletRequest request, HttpServletResponse response) {
		FlowPage page = new FlowPage();
		Page<RedemptionFlowTaskItemView> p = new Page<RedemptionFlowTaskItemView>(request, response);
		page.setPageNo(p.getPageNo());
		page.setPageSize(p.getPageSize());

//		List<RedemptionFlowTaskItemView> itemList = reapplyManager.fetchBackList(entity);
		reapplyManager.fetchBackList(page, entity);

//		model.addAttribute("itemList", itemList);
		model.addAttribute("entity", entity);
		model.addAttribute("page", page);
		String[] types = {"tz_pay_type","tz_deduct_plat","tz_bill_day"};
		FortuneDictUtil.addDicts(model,types);

		return "redemption/reapply/backList";
	}
	
	/**
	 * 修改退回的申请后，再次提交申请
	 * 2015年12月21日
	 * By 陈广鹏
	 * @param model
	 * @param entity
	 * @param workItemView
	 * @return
	 */
	@RequestMapping("reApply")
	public String reApply(Model model, RedemptionApplyEntity entity, WorkItemView workItemView) {
		
		workItemView.setBv(entity);

		model.addAttribute("flowCode", workItemView.getFlowCode());
		model.addAttribute("flowName", workItemView.getFlowName());
		model.addAttribute("stepName", workItemView.getStepName());
		
		flowService.dispatch(workItemView);
		
		return "redirect:" + this.adminPath + "/myApply/redemption/backList";
	}

}
