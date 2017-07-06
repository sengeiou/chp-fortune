package com.creditharmony.fortune.change.lend.reject.web;


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
import com.creditharmony.core.users.util.UserUtils;
import com.creditharmony.core.web.BaseController;
import com.creditharmony.fortune.change.lend.apply.entity.LendLaunchView;
import com.creditharmony.fortune.change.lend.apply.entity.LendLoanApply;
import com.creditharmony.fortune.change.lend.apply.entity.LendQueryView;
import com.creditharmony.fortune.change.lend.approve.entity.LendChangeTaskView;
import com.creditharmony.fortune.change.lend.reject.manager.LendFlowRejectManager;
import com.creditharmony.fortune.customer.entity.FortuneOrg;
import com.creditharmony.fortune.customer.util.RelationShipUtil;
import com.creditharmony.fortune.utils.FortuneDictUtil;



/**
 * 出借信息修改Controller层
 * @Class Name LendChangeController
 * @author 刘雄武
 * @Create In 2015年11月30日
 */
@Controller
@RequestMapping({ "${adminPath}/lendChangeReject" })
public class LendChangeRejectController extends BaseController {

	@Resource
	private LendFlowRejectManager flowManager;
	
    
	/**
	 * 获取退回的申请
	 * 2015年12月16日
	 * By 刘雄武
	 * @param model
	 * @param flag
	 * @param lendloanapply
	 * @param workItem
	 * @param response
	 * @return
	 */
	@RequestMapping(value = { "backList", "" })
	public String backList(Model model, boolean flag, LendQueryView queryView,
			WorkItemView workItem,HttpServletRequest request, HttpServletResponse response) {
		
		Page<LendChangeTaskView> page = new Page<LendChangeTaskView>(request, response);
		FlowPage flowPage = new FlowPage();
		List<LendChangeTaskView>  itemList= flowManager.backList(workItem,queryView,page,flowPage);
		FortuneOrg org = RelationShipUtil.getStoresOrg(UserUtils.getUserId());
		if(org!=null){
			queryView.setOrgCode(org.getId());
		}
		queryView.setManagerName(UserUtils.getUser(UserUtils.getUserId()).getName());
		FortuneDictUtil.addDicts(model, new String[]{"tz_trust_state","tz_pay_type","tz_lend_state","tz_customer_state","tz_customer_src"});
		model.addAttribute("page", flowPage);
		model.addAttribute("itemList", itemList).addAttribute("queryView",queryView);
		return "/lendChange/lendChangeBackList";
	}
	
	/**
	 * 重新发起流程
	 * 2015年12月16日
	 * By 刘雄武
	 * @param redirectAttributes
	 * @param workItem
	 * @param bv
	 * @param lendloanapply
	 * @param response
	 * @return
	 */
	@RequestMapping({ "newLaunchFlow" })
	public String newLaunchFlow(RedirectAttributes redirectAttributes, WorkItemView workItem, LendLaunchView bv, LendLoanApply lendloanapply, HttpServletResponse response) {

		flowManager.newLaunchFlow(workItem, bv);
		addMessage(redirectAttributes, new String[] { "客户：" + bv.getLendloanapply().getCustName() + "变更申请成功" });
		return "redirect:" + this.adminPath + "/lendChangeReject/backList";

	}
	
}
