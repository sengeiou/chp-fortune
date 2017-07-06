package com.creditharmony.fortune.change.lend.approve.web;


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
import com.creditharmony.core.role.type.FortuneRole;
import com.creditharmony.core.users.type.FortuneOrgType;
import com.creditharmony.core.users.util.UserUtils;
import com.creditharmony.core.web.BaseController;
import com.creditharmony.fortune.change.lend.apply.entity.LendChangeLog;
import com.creditharmony.fortune.change.lend.apply.entity.LendQueryView;
import com.creditharmony.fortune.change.lend.approve.entity.LendChangeTaskView;
import com.creditharmony.fortune.change.lend.approve.manager.LendFlowApproveManager;
import com.creditharmony.fortune.common.entity.UserRoleOrgEx;
import com.creditharmony.fortune.utils.FortuneDictUtil;
import com.creditharmony.fortune.utils.RoleOrgUtil;



/**
 * 出借信息修改初审Controller层
 * @Class Name LendChangeController
 * @author 刘雄武
 * @Create In 2015年11月30日
 */
@Controller
@RequestMapping({ "${adminPath}/lendChangeApprove" })
public class LendChangeApproveController extends BaseController {

	@Resource
	private LendFlowApproveManager flowManager;
	
    
	/**
	 * 获取初审任务
	 * 2015年12月3日
	 * By 刘雄武
	 * @param model
	 * @param flag
	 * @param lendloanapply
	 * @param workItem
	 * @param response
	 * @return
	 */
	@RequestMapping(value = { "fetchTaskItemsFirst", "" })
	public String fetchTaskItemsFirst(Model model, boolean flag, LendQueryView queryView,
			WorkItemView workItem,HttpServletRequest request, HttpServletResponse response) {
		
		Page<LendChangeTaskView> page = new Page<LendChangeTaskView>(request, response);
		FlowPage flowPage = new FlowPage();
		List<UserRoleOrgEx> list = RoleOrgUtil.findOrgByUserAndRole(
				UserUtils.getUserId(), FortuneRole.STORE_MANAGER.id, FortuneOrgType.STORE.key, FortuneOrgType.STORE.key, null);
		if(list.size() > 0){
			queryView.setOrgCode(list.get(0).getOrgId());
		}else{
			// 初审 营业部为必须字段
			queryView.setOrgCode("-1");
		}
		List<LendChangeTaskView>  itemList= flowManager.fetchTaskItemsFirst(workItem,queryView,page,flowPage);
		
		FortuneDictUtil.addDicts(model, new String[]{"tz_trust_state","tz_pay_type","tz_lend_state","tz_customer_state","tz_customer_src"});
		model.addAttribute("page", flowPage);
		model.addAttribute("itemList", itemList).addAttribute("queryView",queryView);
		return "/lendChange/fetchTaskItemsFirst";
	}
	
	/**
	 * 处理流程->初审
	 * 2015年12月8日
	 * By 刘雄武
	 * @param workItem
	 * @param redirectAttributes
	 * @param response
	 * @param changelog
	 * @return
	 */
	@RequestMapping({ "dispatch" })
	public String dispatch(WorkItemView workItem,
			RedirectAttributes redirectAttributes, HttpServletResponse response,LendChangeLog changelog) {
		
		flowManager.dispatch(workItem,changelog);
		addMessage(redirectAttributes, new String[] { "已成功处理一条信息" });
		return "redirect:" + this.adminPath + "/lendChangeApprove/fetchTaskItemsFirst";
	}
	
}
