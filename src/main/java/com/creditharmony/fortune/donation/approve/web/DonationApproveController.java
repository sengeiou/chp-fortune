package com.creditharmony.fortune.donation.approve.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.creditharmony.bpm.frame.view.FlowPage;
import com.creditharmony.bpm.frame.view.WorkItemView;
import com.creditharmony.core.fortune.type.DeliveryType;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.role.type.FortuneRole;
import com.creditharmony.core.users.type.FortuneOrgType;
import com.creditharmony.core.users.util.UserUtils;
import com.creditharmony.core.web.BaseController;
import com.creditharmony.fortune.common.entity.UserRoleOrgEx;
import com.creditharmony.fortune.common.service.IntKeyLogService;
import com.creditharmony.fortune.donation.apply.entity.CustomerManagerinfo;
import com.creditharmony.fortune.donation.apply.entity.CustomerQueryView;
import com.creditharmony.fortune.donation.approve.entity.CustomertransferTaskView;
import com.creditharmony.fortune.donation.approve.manager.DonationApproveFlowManager;
import com.creditharmony.fortune.utils.FortuneDictUtil;
import com.creditharmony.fortune.utils.RoleOrgUtil;

/**
 * 转赠业务Controller
 * @Class Name DonationApproveController
 * @author 刘雄武
 * @Create In 2016年3月3日
 */
@Controller
@RequestMapping("${adminPath}/donationApprove")
public class DonationApproveController extends BaseController{

	@Resource
	private DonationApproveFlowManager flowManager;
	@Autowired
	private IntKeyLogService intKeyLogService;
	
	/**
	 * 获取初审待办列表
	 * 2016年3月7日
	 * By 刘雄武
	 * @param model
	 * @param workItem
	 * @param response
	 * @return
	 */
	@RequestMapping(value = { "fetchTaskItemsFirst", "" })
	public String fetchTaskItemsFirst(Model model,CustomerQueryView query, WorkItemView workItem,HttpServletRequest request, HttpServletResponse response) {

		Page<CustomertransferTaskView> page = new Page<CustomertransferTaskView>(request, response);
		FlowPage flowPage = new FlowPage();
		List<UserRoleOrgEx> list = RoleOrgUtil.findOrgByUserAndRole(UserUtils.getUserId(), FortuneRole.STORE_MANAGER.id, FortuneOrgType.STORE.key, FortuneOrgType.STORE.key, null);
		if(list.size() > 0){
			query.setDepartmentId(list.get(0).getOrgId());
		} else {
			list = RoleOrgUtil.findOrgByUserAndRole(UserUtils.getUserId(), FortuneRole.STORE_ASSISTANT_MANAGER.id, FortuneOrgType.STORE.key, FortuneOrgType.STORE.key, null);
			if (list.size() > 0) {
				query.setDepartmentId(list.get(0).getOrgId());
			}
		}
		List<CustomertransferTaskView> itemList = flowManager.fetchTaskItemsFirst(workItem, query,page,flowPage);
		FortuneDictUtil.addDicts(model, new String[]{"tz_transfer_state"});
		model.addAttribute("page", flowPage);
		model.addAttribute("itemList", itemList).addAttribute("query", query);
		return "/donation/fetchTaskItemsFirst";
	}
	
	
	/**
	 * 处理流程，初审
	 * 2016年3月7日
	 * By 刘雄武
	 * @param workItem
	 * @param redirectAttributes
	 * @param response
	 * @param cminfo
	 * @return
	 */
	@RequestMapping({ "dispatch" })
	public String dispatch(WorkItemView workItem, RedirectAttributes redirectAttributes, HttpServletResponse response, CustomerManagerinfo cminfo) {
		try {
			flowManager.dispatch(workItem, cminfo);
			addMessage(redirectAttributes, new String[] { "已成功处理一条信息" });
		} catch (Exception e) {
			intKeyLogService.log(e, DeliveryType.ZZ.value, "营业部初审客户" + cminfo.getCustCode() + "异常!");
			addMessage(redirectAttributes, new String[] { "初审异常，请联系相关人员进行处理！" });
		}
		return "redirect:" + this.adminPath + "/donationApprove/fetchTaskItemsFirst";
	}

	@ResponseBody
	@RequestMapping({ "checkHaveReview" })
	public String checkHaveReview(String applyId, String wobNum, String token, String custCode) {
		return flowManager.checkHaveReview(applyId, wobNum, token, custCode);
	}
}
