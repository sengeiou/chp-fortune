package com.creditharmony.fortune.back.priority.back.web;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.creditharmony.core.fortune.type.PriorityBackState;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.web.BaseController;
import com.creditharmony.fortune.back.priority.back.service.PriorityBackManager;
import com.creditharmony.fortune.back.priority.common.view.PriorityDetailItem;
import com.creditharmony.fortune.back.priority.common.view.PriorityListItemView;
import com.creditharmony.fortune.back.priority.constant.PbmConstant;
import com.creditharmony.fortune.common.entity.ReturnMsg;
import com.creditharmony.fortune.utils.FortuneDictUtil;

/**
 * 优先回款申请（被退回）相关Controller
 * @Class Name PriorityBackMoneyApplyController
 * @author 郭强
 * @Create In 2017年3月27日
 */
@Controller
@RequestMapping("${adminPath}/priorityBack/")
public class PriorityBackController extends BaseController{
	@Autowired
	private PriorityBackManager    pbm; 
	
	/**
	 * 优先回款申请（被退回）
	 * 2017年4月7日
	 * By 郭强
	 * @param request
	 * @param response
	 * @param view
	 * @param model
	 * @return
	 */
	@RequestMapping("backList")
	public String applyList(HttpServletRequest request,
			HttpServletResponse response, PriorityListItemView view, Model model){
		Page<PriorityListItemView> page = new Page<PriorityListItemView>(request, response);
		view.setPriorityBackStateAS(PbmConstant.PRIORITY_BACK_STATUS_LIST);
		Page<PriorityListItemView> pageview = pbm.findItemList(page, view);

		model.addAttribute("page", pageview)
			.addAttribute("view", view);
		
		String[] types = {"tz_back_type","tz_pay_type","com_card_type",
				"tz_back_state","tz_priority_state"};
		FortuneDictUtil.addDicts(model,types);
		return "priorityBack/back/backList";
	}
	
	/**
	 * 跳转至优先回款(被退回)详细页面
	 * 2017年4月7日
	 * By 郭强
	 * @param model
	 * @param priorityId
	 * @return
	 */
	@RequestMapping("backDetail")
	public String applyLookDetail(Model model, String priorityId) {

		PriorityDetailItem  view = new PriorityDetailItem();
		view = pbm.getDetail(priorityId);
		
		model.addAttribute("view", view);
		String[] types = {"com_card_type","tz_priority_state","tz_pay_type","tz_open_bank","tz_contract_vesion"};
		FortuneDictUtil.addDicts(model,types);
		
		return "priorityBack/back/backDetail";
	}
	
	/**
	 * 提交
	 * 2017年4月9日
	 * 郭强
	 */
	@RequestMapping("ApplyConfirm")
	@ResponseBody
	public String applyConfirm(PriorityDetailItem view){
		
		String message = pbm.applyConfirm(view);
		return jsonMapper.toJson(new ReturnMsg(message));
	}
	
	/**
	 * 撤销申请
	 * 2017年3月31日
	 * 郭强
	 */
	@RequestMapping("revocationApply")
	@ResponseBody
	public String revocationApply(PriorityListItemView view) {
		view.setPriorityBackState(PriorityBackState.YCX.value);
		String message = pbm.revocationApply(view);
		return jsonMapper.toJson(new ReturnMsg(message));
	}
	
}
