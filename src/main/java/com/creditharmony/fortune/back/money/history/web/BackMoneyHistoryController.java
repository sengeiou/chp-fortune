package com.creditharmony.fortune.back.money.history.web;

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
import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.fortune.type.BackState;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.web.BaseController;
import com.creditharmony.fortune.back.money.common.service.BmManager;
import com.creditharmony.fortune.back.money.common.view.ItemView;
import com.creditharmony.fortune.back.money.common.view.ListItemView;
import com.creditharmony.fortune.back.money.common.view.ResultView;
import com.creditharmony.fortune.back.money.constant.BmConstant;
import com.creditharmony.fortune.back.money.history.facade.BmHistoryForeachManager;
import com.creditharmony.fortune.back.money.history.service.BmHistoryExportor;
import com.creditharmony.fortune.back.money.history.service.HistoryManager;
import com.creditharmony.fortune.common.entity.Check;
import com.creditharmony.fortune.common.entity.ReturnMsg;
import com.creditharmony.fortune.common.service.CheckManager;
import com.creditharmony.fortune.redemption.constant.RedeemConstant;
import com.creditharmony.fortune.utils.FortuneDictUtil;

/**
 * 回款已办相关Controller
 * @Class Name BackMoneyHistoryController
 * @author 陈广鹏
 * @Create In 2015年12月16日
 */
@Controller
@RequestMapping("${adminPath}/myDone/backMoney/")
public class BackMoneyHistoryController extends BaseController {

	@Autowired
	private BmManager bmManager;
	@Autowired
	private HistoryManager historyManager;
	@Autowired
	private BmHistoryForeachManager historyForeachManager;
	@Autowired
	private CheckManager checkManager;
	
	/**
	 * 已回款列表
	 * 2015年12月4日
	 * By 陈广鹏
	 * @param request
	 * @param response
	 * @param view
	 * @param model
	 * @return
	 */
	@RequestMapping("historyList")
	public String historyList(HttpServletRequest request,
			HttpServletResponse response, ListItemView view, Model model){
		List<String> status = new ArrayList<String>();
		status.add(BackState.YHK.value);
		view.setStatusList(status);
//		view.setUserId(UserUtils.getUser().getId());
		Page<ListItemView> page = new Page<ListItemView>(request, response);
		Page<ListItemView> pageview = bmManager.findItemList(page, view);
		BigDecimal totalAcutalbackmoney = bmManager.getTotalAcutalbackmoney(view);

		model.addAttribute("page", pageview)
			.addAttribute("view", view)
			.addAttribute("totalABM", totalAcutalbackmoney)
			.addAttribute("eUrl", "exportHistory")
			.addAttribute("ejUrl", "exportJzhHistory");
		String[] types = {"tz_back_type","tz_pay_type","tz_back_state",
				"tz_backMoney_plat","tz_open_bank","tz_contract_vesion","tz_channel_flag",
				"tz_working_state"};
		FortuneDictUtil.addDicts(model,types);
		return "backMoney/history/historyList";
	}
	
	/**
	 * 跳转至已回款详细页面
	 * 2015年12月4日
	 * By 陈广鹏
	 * @param backmId
	 * @param model
	 * @return
	 */
	@RequestMapping("historyDetail")
	public String historyDetail(String backmId, Model model){
		ItemView view = new ItemView();
		view = bmManager.getDetail(backmId);
		
		model.addAttribute("view", view);
		String[] types = {"tz_back_reason","com_card_type","tz_open_bank","tz_pay_type","tz_contract_vesion",
						  "tz_working_state"};
		FortuneDictUtil.addDicts(model,types);
		
		return "backMoney/history/history";
	}
	
	/**
	 * 导出回款明细
	 * 2015年12月26日
	 * By 陈广鹏
	 * @param response
	 * @param listItemView
	 */
	@RequestMapping("exportHistory")
	public void exportHistory(HttpServletResponse response, ListItemView listItemView) {
		List<String> status = new ArrayList<String>();
		status.add(BackState.YHK.value);
		listItemView.setStatusList(status);
		BmHistoryExportor exportor = new BmHistoryExportor(
				BmConstant.HISTORY + DateUtils.getDate(RedeemConstant.YYYY_MM_DD));
		exportor.exportData(listItemView, response);
	}
	
	/**
	 * 获取要导出的数据量
	 * 2016年4月16日
	 * By 陈广鹏
	 * @param listItemView
	 * @return
	 */
	@RequestMapping("exportHistoryCheck")
	@ResponseBody
	public String exportHistoryCheck(ListItemView listItemView) {
		List<String> status = new ArrayList<String>();
		status.add(BackState.YHK.value);
		listItemView.setStatusList(status);
		int count = bmManager.countExport(listItemView);
		return jsonMapper.toJson(count);
	}
	
	/**
	 * 已回款退回
	 * 2016年1月11日
	 * By 陈广鹏
	 * @param view
	 * @return
	 */
	@RequestMapping("returnBackmoney")
	@ResponseBody
	public String returnBackmoney(ResultView view){
		String message = historyForeachManager.returnBackmoney(view);
		return jsonMapper.toJson(new ReturnMsg(message));
	}
	
	/**
	 * 显示批量退回弹窗
	 * 2016年1月8日
	 * By 陈广鹏
	 * @return
	 */
	@RequestMapping("toMultiReturn")
	public String toMultiReturn(Model model){
		model.addAttribute("showFlag", "history");
		String[] types = {"tz_back_reason"};
		FortuneDictUtil.addDicts(model,types);
		return "backMoney/common/popMultOperate";
	}
	
	/**
	 * 批量退回
	 * 2016年1月11日
	 * By 陈广鹏
	 * @param view
	 * @return
	 */
	@RequestMapping("multiReturn")
	@ResponseBody
	public String multiReturn(ListItemView itemView){
		ListItemView view = new ListItemView();
		if (StringUtils.isNotEmpty(itemView.getBackmId())) {
			view.setBackmId(itemView.getBackmId());
			view.setCheckExamine(itemView.getCheckExamine());
			view.setCheckReason(itemView.getCheckReason());
		} else {
			return "请先选择需要操作的数据！";
		}
		List<String> status = new ArrayList<String>();
		status.add(BackState.YHK.value);
		view.setStatusList(status);
		
		String rtnMsg = historyForeachManager.multiReturn(view);
		return jsonMapper.toJson(new ReturnMsg(rtnMsg));
	}
	
	/**
	 * 全程留痕
	 * 2016年1月21日
	 * By 陈广鹏
	 * @param request
	 * @param response
	 * @param model
	 * @param check
	 * @return
	 */
	@RequestMapping("fullTrace")
	public String fullTrace(HttpServletRequest request, HttpServletResponse response,
			Model model, Check check){
		Page<Check> page = new Page<Check>(request, response);
		Page<Check> pageview = checkManager.findCheckList(page, check);
		page.setFuncName("pageAjax");

		model.addAttribute("page", pageview)
			.addAttribute("check", check);
		return "backMoney/common/popFullTrace";
	}
}
