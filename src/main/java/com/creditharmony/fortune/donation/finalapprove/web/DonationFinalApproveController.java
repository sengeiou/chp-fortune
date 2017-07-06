package com.creditharmony.fortune.donation.finalapprove.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.creditharmony.bpm.frame.view.FlowPage;
import com.creditharmony.bpm.frame.view.WorkItemView;
import com.creditharmony.core.fortune.type.DeliveryType;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.web.BaseController;
import com.creditharmony.fortune.common.service.IntKeyLogService;
import com.creditharmony.fortune.donation.apply.entity.CustomerManagerinfo;
import com.creditharmony.fortune.donation.apply.entity.CustomerQueryView;
import com.creditharmony.fortune.donation.approve.entity.CustomertransferTaskView;
import com.creditharmony.fortune.donation.finalapprove.manager.DonationFinalApproveFlowManager;
import com.creditharmony.fortune.utils.FortuneDictUtil;

/**
 * 转赠业务Controller
 * @Class Name DonationFinalApproveController
 * @author 刘雄武
 * @Create In 2016年3月3日
 */
@Controller
@RequestMapping("${adminPath}/donationFinalApprove")
public class DonationFinalApproveController extends BaseController{

	@Resource
	private DonationFinalApproveFlowManager flowManager;
	@Autowired
	private IntKeyLogService intKeyLogService;
	
	/**
	 * 获取复审待办列表
	 * 2016年3月7日
	 * By 刘雄武
	 * @param model
	 * @param query
	 * @param workItem
	 * @param response
	 * @return
	 */
	@RequestMapping(value = { "fetchTaskItemsReview", "" })
	public String fetchTaskItemsReview(Model model, CustomerQueryView query, WorkItemView workItem,HttpServletRequest request, HttpServletResponse response) {

		Page<CustomertransferTaskView> page = new Page<CustomertransferTaskView>(request, response);
		FlowPage flowPage = new FlowPage();
		List<CustomertransferTaskView> itemList = flowManager.fetchTaskItemsReview(workItem, query,page,flowPage);
		FortuneDictUtil.addDicts(model, new String[]{"tz_transfer_state"});
		model.addAttribute("page", flowPage);
		model.addAttribute("itemList", itemList).addAttribute("query", query);
		return "/donation/fetchTaskItemsReview";
	}
	
	/**
	 * 支公司经理复审
	 * 2016年3月7日
	 * By 刘雄武
	 * @param workItem
	 * @param redirectAttributes
	 * @param response
	 * @param cminfo
	 * @return
	 */
	@RequestMapping({ "review" })
	public String review(WorkItemView workItem, RedirectAttributes redirectAttributes, HttpServletResponse response, CustomerManagerinfo cminfo) {
		try {
			flowManager.review(workItem, cminfo);
			addMessage(redirectAttributes, new String[] { "已成功处理一条信息" });
		} catch (Exception e) {
			intKeyLogService.log(e, DeliveryType.ZZ.value, "支分公司复审客户" + cminfo.getCustCode() + "异常!");
			addMessage(redirectAttributes, new String[] { "复审异常，请联系相关人员进行处理！" });
		}
		return "redirect:" + this.adminPath + "/donationFinalApprove/fetchTaskItemsReview";
	}
	
}
