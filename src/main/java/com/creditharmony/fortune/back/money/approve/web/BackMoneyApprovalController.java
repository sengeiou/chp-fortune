package com.creditharmony.fortune.back.money.approve.web;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.creditharmony.common.util.DateUtils;
import com.creditharmony.core.fortune.type.BackState;
import com.creditharmony.core.fortune.type.PayMent;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.web.BaseController;
import com.creditharmony.fortune.back.money.approve.facade.BmApprovalForeachManager;
import com.creditharmony.fortune.back.money.approve.service.BmApprovalExportor;
import com.creditharmony.fortune.back.money.approve.service.BmApprovalJzhExportor;
import com.creditharmony.fortune.back.money.common.service.BmManager;
import com.creditharmony.fortune.back.money.common.view.ItemView;
import com.creditharmony.fortune.back.money.common.view.ListItemView;
import com.creditharmony.fortune.back.money.common.view.ResultView;
import com.creditharmony.fortune.back.money.constant.BmConstant;
import com.creditharmony.fortune.common.entity.ReturnMsg;
import com.creditharmony.fortune.redemption.constant.RedeemConstant;
import com.creditharmony.fortune.utils.FortuneDictUtil;

/**
 * 回款审批相关Controller
 * @Class Name BackMoneyApprovalController
 * @author 陈广鹏
 * @Create In 2015年12月3日
 */
@Controller
@RequestMapping("${adminPath}/myTodo/backMoney/")
public class BackMoneyApprovalController extends BaseController {
	
	@Autowired
	private BmManager bmManager;
	@Autowired
	private BmApprovalForeachManager approvalManager;
	
	/**
	 * 回款审批列表
	 * 2015年12月3日
	 * By 陈广鹏
	 * @param request
	 * @param response
	 * @param view
	 * @param model
	 * @return
	 */
	@RequestMapping("approvalList")
	public String approvalList(HttpServletRequest request,
			HttpServletResponse response, ListItemView view, Model model){
		List<String> status = new ArrayList<String>();
		status.add(BackState.DHKSP.value);
		view.setStatusList(status);
		
		Page<ListItemView> page = new Page<ListItemView>(request, response);
		Page<ListItemView> pageview = bmManager.findItemList(page, view);
		BigDecimal totalAcutalbackmoney = bmManager.getTotalAcutalbackmoney(view);

		model.addAttribute("page", pageview)
			.addAttribute("view", view)
			.addAttribute("totalABM", totalAcutalbackmoney)
			.addAttribute("eUrl", "exportApproval")
			.addAttribute("ejUrl", "exportJzhApproval");
		String[] types = {"tz_back_type","tz_pay_type","tz_back_state",
				"tz_contract_vesion","tz_channel_flag","tz_working_state"};
		FortuneDictUtil.addDicts(model,types);
		return "backMoney/approval/approvalList";
	}
	
	/**
	 * 跳转至回款审批页面
	 * 2015年12月3日
	 * By 陈广鹏
	 * @param backmId
	 * @param model
	 * @return
	 */
	@RequestMapping("approvalDetail")
	public String approvalDetail(String backmId, Model model){
		ItemView view = new ItemView();
		view = bmManager.getDetail(backmId);
		
		model.addAttribute("view", view);
		String[] types = {"tz_back_reason","com_card_type","tz_open_bank","tz_pay_type","tz_contract_vesion",
						  "tz_working_state"};
		FortuneDictUtil.addDicts(model,types);
		
		return "backMoney/approval/approval";
	}
	
	/**
	 * 提交审批结果
	 * 2015年12月3日
	 * By 陈广鹏
	 * @param view
	 * @return
	 */
	@RequestMapping("approval")
	@ResponseBody
	public String approval(ResultView view){
		String message = approvalManager.applyApproval(view);
		return jsonMapper.toJson(new ReturnMsg(message));
	}
	
	/**
	 * 批量回款审批
	 * 2015年12月16日
	 * By 陈广鹏
	 * @param view
	 * @return
	 */
	@RequestMapping("toMmultiApproval")
	public String toMmultiApproval(Model model){
		String[] types = {"tz_back_reason"};
		FortuneDictUtil.addDicts(model,types);
		return "backMoney/common/popMultOperate";
	}
	
	/**
	 * 批量回款审批
	 * 2015年12月16日
	 * By 陈广鹏
	 * @param view
	 * @return
	 */
	@RequestMapping("multiApproval")
	@ResponseBody
	public String multiApproval(ListItemView view){
		List<String> status = new ArrayList<String>();
		status.add(BackState.DHKSP.value);
		view.setStatusList(status);
		
		String rtn = approvalManager.multiApproval(view);
		return jsonMapper.toJson(new ReturnMsg(rtn));
	}
	
	/**
	 * 导出待回款明细
	 * 2015年12月25日
	 * By 陈广鹏
	 * @param response
	 * @param listItemView
	 */
	@RequestMapping("exportApproval")
	public void exportApproval(HttpServletResponse response, ListItemView listItemView) {
		List<String> status = new ArrayList<String>();
		status.add(BackState.DHKSP.value);
		listItemView.setStatusList(status);
		BmApprovalExportor exportor = new BmApprovalExportor(
				BmConstant.TO_APPROVAL + DateUtils.getDate(RedeemConstant.YYYY_MM_DD));
		exportor.exportData(listItemView, response);
	}
	
	/**
	 * 获取导出待回款明细数据量
	 * 2016年4月16日
	 * By 陈广鹏
	 * @param listItemView
	 */
	@RequestMapping("exportApprovalCheck")
	@ResponseBody
	public String exportApprovalCheck(ListItemView listItemView) {
		List<String> status = new ArrayList<String>();
		status.add(BackState.DHKSP.value);
		listItemView.setStatusList(status);
		int count = bmManager.countExport(listItemView);
		return jsonMapper.toJson(count);
	}
	
	/**
	 * 导出待金账户回款明细
	 * 2016年1月6日
	 * By 陈广鹏
	 * @param response
	 * @param listItemView
	 */
	@RequestMapping("exportJzhApproval")
	public void exportJzhApproval(HttpServletResponse response, ListItemView listItemView) {
		List<String> status = new ArrayList<String>();
		status.add(BackState.DHKSP.value);
		listItemView.setStatusList(status);
		listItemView.setApplyPay(PayMent.ZJTG.value);
		BmApprovalJzhExportor exportor = new BmApprovalJzhExportor(
				BmConstant.TO_JZH_APPROVAL + DateUtils.getDate(RedeemConstant.YYYY_MM_DD));
		exportor.exportData(listItemView, response);
	}

}
