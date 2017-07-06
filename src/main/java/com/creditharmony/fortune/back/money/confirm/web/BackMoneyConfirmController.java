package com.creditharmony.fortune.back.money.confirm.web;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.creditharmony.common.util.DateUtils;
import com.creditharmony.core.fortune.type.BackMoneyPlat;
import com.creditharmony.core.fortune.type.BackState;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.web.BaseController;
import com.creditharmony.fortune.back.money.common.service.BmManager;
import com.creditharmony.fortune.back.money.common.view.ItemView;
import com.creditharmony.fortune.back.money.common.view.ListItemView;
import com.creditharmony.fortune.back.money.common.view.ResultView;
import com.creditharmony.fortune.back.money.confirm.facade.BmConfirmForeachManager;
import com.creditharmony.fortune.back.money.confirm.service.BmConfirmExportor;
import com.creditharmony.fortune.back.money.confirm.service.ConfirmManager;
import com.creditharmony.fortune.back.money.constant.BmConstant;
import com.creditharmony.fortune.common.entity.ReturnMsg;
import com.creditharmony.fortune.redemption.constant.RedeemConstant;
import com.creditharmony.fortune.utils.FortuneDictUtil;

/**
 * 回款确认相关Controller
 * @Class Name BackMoneyConfirmController
 * @author 陈广鹏
 * @Create In 2015年12月16日
 */
@Controller
@RequestMapping("${adminPath}/myTodo/backMoney/")
public class BackMoneyConfirmController extends BaseController {
	
	@Autowired
	private BmManager bmManager;
	@Autowired
	private ConfirmManager confirmManager;
	@Autowired
	private BmConfirmForeachManager confirmForeachManager;
	
	/**
	 * 回款确认列表
	 * 2015年12月3日
	 * By 陈广鹏
	 * @param request
	 * @param response
	 * @param view
	 * @param model
	 * @return
	 */
	@RequestMapping("confirmList")
	public String confirmList(HttpServletRequest request,
			HttpServletResponse response, ListItemView view, Model model){
		List<String> status = new ArrayList<String>();
		status.add(BackState.DHKQR.value);
		view.setStatusList(status);
		Page<ListItemView> page = new Page<ListItemView>(request, response);
		Page<ListItemView> pageview = bmManager.findItemList(page, view);
		BigDecimal totalAcutalbackmoney = bmManager.getTotalAcutalbackmoney(view);

		model.addAttribute("page", pageview)
			.addAttribute("view", view)
			.addAttribute("totalABM", totalAcutalbackmoney)
			.addAttribute("eUrl", "exportConfirm")
			.addAttribute("ejUrl", "exportJzhConfirm");
		String[] types = {"tz_back_type","tz_pay_type","tz_backMoney_plat",
				"com_card_type","tz_back_state","tz_back_result",
				"tz_contract_vesion","tz_channel_flag","tz_working_state"};
		FortuneDictUtil.addDicts(model,types);
		return "backMoney/confirm/confirmList";
	}
	
	/**
	 * 跳转至回款确认详细页面
	 * 2015年12月15日
	 * By 陈广鹏
	 * @param backmId
	 * @param model
	 * @return
	 */
	@RequestMapping("confirmDetail")
	public String confirmDetail(String backmId, Model model){
		ItemView view = new ItemView();
		view = bmManager.getDetail(backmId);
		
		model.addAttribute("view", view);
		String[] types = {"tz_back_reason","com_card_type","tz_open_bank","tz_pay_type","tz_contract_vesion",
						  "tz_working_state"};
		FortuneDictUtil.addDicts(model,types);
		return "backMoney/confirm/confirm";
	}
	
	/**
	 * 提交回款确认结果
	 * 2015年12月15日
	 * By 陈广鹏
	 * @param view
	 * @return
	 */
	@RequestMapping("confirm")
	@ResponseBody
	public String confirm(ResultView view){
		String message = confirmForeachManager.confirm(view);
		return jsonMapper.toJson(new ReturnMsg(message));
	}
	
	/**
	 * 显示批量回款确认弹窗
	 * 2017年3月1日
	 * By 陈广鹏
	 * @param view
	 * @param model
	 * @return
	 */
	@RequestMapping("toMultiConfirm")
	public String toMultiConfirm(ListItemView view, Model model){
		model.addAttribute("showFlag", "5");
		String[] types = {"tz_back_reason"};
		FortuneDictUtil.addDicts(model,types);
		
		List<String> status = new ArrayList<String>();
		status.add(BackState.DHKQR.value);
		view.setStatusList(status);
		
		// 弹窗中显示所选数据的最大回款日期
		Date hMaxDate = bmManager.getMaxBackMoneyDay(view);
		model.addAttribute("hMaxDate", hMaxDate);
		
		// 获取数据不同回款日期的天数
		int days = bmManager.getDiffBackMoneyDays(view);
		model.addAttribute("diffDays", days);
		
		return "backMoney/common/popMultOperate";
	}
	
	/**
	 * 批量回款确认
	 * 2015年12月16日
	 * By 陈广鹏
	 * @param view
	 * @return
	 */
	@RequestMapping("multiConfirm")
	@ResponseBody
	public String multiConfirm(ListItemView view){
		List<String> status = new ArrayList<String>();
		status.add(BackState.DHKQR.value);
		view.setStatusList(status);
		
		String message = confirmForeachManager.multiConfirm(view);
		
		return jsonMapper.toJson(new ReturnMsg(message));
	}
	
	/**
	 * 导出Excel
	 * 2015年12月26日
	 * By 陈广鹏
	 * @param response
	 * @param listItemView
	 */
	@RequestMapping("exportConfirm")
	public void exportConfirm(HttpServletResponse response, ListItemView listItemView) {
		List<String> status = new ArrayList<String>();
		status.add(BackState.DHKQR.value);
		listItemView.setStatusList(status);
		BmConfirmExportor exportor = new BmConfirmExportor(
				BmConstant.TO_CONFIRM + DateUtils.getDate(RedeemConstant.YYYY_MM_DD));
		exportor.exportData(listItemView, response);
		
	}
	
	/**
	 * 获取要导出的数据量
	 * 2016年4月16日
	 * By 陈广鹏
	 * @param listItemView
	 * @return
	 */
	@RequestMapping("exportConfirmCheck")
	@ResponseBody
	public String exportConfirmCheck(ListItemView listItemView) {
		List<String> status = new ArrayList<String>();
		status.add(BackState.DHKQR.value);
		listItemView.setStatusList(status);
		int count = bmManager.countExport(listItemView);
		return jsonMapper.toJson(count);
	}
	
	/**
	 * 显示上传回盘结果页面
	 * 2016年2月2日
	 * By 陈广鹏
	 * @param model
	 * @return
	 */
	@RequestMapping("toConfirmUpload")
	public String toConfirmUpload(Model model){
		String platformOption = BackMoneyPlat.FYPT.value + ","
				+ BackMoneyPlat.ZJPT.value + ","
				+ BackMoneyPlat.TL.value;
		model.addAttribute("showPlatform", "1")
		.addAttribute("platformOption", platformOption);
		String[] types = {"tz_backMoney_plat"};
		FortuneDictUtil.addDicts(model,types);
		return "backMoney/common/fileUpload";
	}
	
	/**
	 * 上传回盘结果
	 * 2016年2月4日
	 * By 陈广鹏
	 * @param file
	 * @param view
	 * @return
	 */
	@RequestMapping("uploadConfirmResult")
	@ResponseBody
	public String uploadConfirmResult(MultipartFile file, ListItemView view) {
		String msg = confirmForeachManager.confirmUpload(file, view);
		return msg;
	}
	
	/**
	 * 退回到待回款列表
	 * 2016年2月17日
	 * By 陈广鹏
	 * @param view
	 * @return
	 */
	@RequestMapping("backtoExecute")
	@ResponseBody
	public String backtoExecute(ListItemView view){
		String message = confirmForeachManager.backtoExecute(view);
		return jsonMapper.toJson(new ReturnMsg(message));
	}
	
	/**
	 * 退回到待回款列表
	 * 2016年3月4日
	 * By 陈广鹏
	 * @param view
	 * @return
	 */
	@RequestMapping("multiBacktoExecute")
	@ResponseBody
	public String multiBacktoExecute(ListItemView view){
		String message = confirmForeachManager.multiBacktoExecute(view);
		return jsonMapper.toJson(new ReturnMsg(message));
	}
	
	/**
	 * 回款确认列表数据校验，回盘结果为【处理中】的数据不允许操作
	 * 2016年3月14日
	 * By 陈广鹏
	 * @param listItemView
	 * @return
	 */
	@RequestMapping("confirmListCheck")
	@ResponseBody
	public String confirmListCheck(ListItemView listItemView) {
		List<String> status = new ArrayList<String>();
		status.add(BackState.DHKQR.value);
		listItemView.setStatusList(status);
		
		String result = confirmManager.confirmListCheck(listItemView);

		return jsonMapper.toJson(new ReturnMsg(result));
	}
	
}
