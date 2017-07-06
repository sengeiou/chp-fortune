package com.creditharmony.fortune.redemption.approval.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.creditharmony.bpm.frame.service.FlowService;
import com.creditharmony.bpm.frame.view.FlowPage;
import com.creditharmony.bpm.frame.view.WorkItemView;
import com.creditharmony.core.fortune.type.YoN;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.web.BaseController;
import com.creditharmony.fortune.redemption.approval.service.RedeemApprovalManager;
import com.creditharmony.fortune.redemption.common.view.RedemptionApplyEntity;
import com.creditharmony.fortune.redemption.common.view.RedemptionFlowTaskItemView;
import com.creditharmony.fortune.utils.FortuneDictUtil;

/**
 * 赎回审批Controller
 * @Class Name ApprovalController
 * @author 陈广鹏
 * @Create In 2016年4月14日
 */
@Controller
@RequestMapping("${adminPath}/myApply/redemption/")
public class RedeemApprovalController extends BaseController {
	
	@Autowired
	private RedeemApprovalManager approvalManager;
	@Autowired
	private FlowService flowService;
	
	/**
	 * 描述：获取待审批列表
	 * 2015年11月30日
	 * By 陈广鹏
	 * @param model
	 * @param entity 搜索Bean
	 * @return
	 */
	@RequestMapping("fetchTaskItems")
	public String fetchTaskItems(Model model, RedemptionApplyEntity entity,
			HttpServletRequest request, HttpServletResponse response) {
		FlowPage page = new FlowPage();
		Page<RedemptionFlowTaskItemView> p = new Page<RedemptionFlowTaskItemView>(request, response);
		page.setPageNo(p.getPageNo());
		page.setPageSize(p.getPageSize());
//		List<RedemptionFlowTaskItemView> itemList = approvalManager.fetchTaskItems(entity);
		approvalManager.fetchTaskItems(page, entity);

//		model.addAttribute("itemList", itemList);
		model.addAttribute("entity", entity);
		model.addAttribute("page", page);
		String[] types = {"tz_bill_day","tz_pay_type","tz_deduct_plat"};
		FortuneDictUtil.addDicts(model,types);

		return "redemption/approval/approvalList";
	}
	
	/**
	 * 提交审批结果
	 * 2015年12月7日
	 * By 陈广鹏
	 * @param model
	 * @param entity
	 * @param workItemView
	 * @return
	 */
	@RequestMapping("dispatchFlow")
	public String dispatchFlow(Model model, RedemptionApplyEntity entity, WorkItemView workItemView) {
		
		workItemView.setBv(entity);
		if (YoN.FOU.value.equals(entity.getCheckExaminetype())) {
			workItemView.setResponse("to_applyUser");
		}else {
			workItemView.setResponse("to_end");
		}
		
		// approvalManager.dispatch(entity);
		
		flowService.dispatch(workItemView);
		
		return "redirect:" + this.adminPath + "/myApply/redemption/fetchTaskItems";
	}
	
	/**
	 * 提交审批结果前数据校验
	 * 2016年3月10日
	 * By 陈广鹏
	 * @param entity
	 * @return
	 */
	@RequestMapping("dispatchFlowCheck")
	@ResponseBody
	public String dispatchFlowCheck(RedemptionApplyEntity entity) {
		
		String str = approvalManager.approvalCheck(entity);
		
		return jsonMapper.toJson(str);
	}

}
