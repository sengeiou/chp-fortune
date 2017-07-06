package com.creditharmony.fortune.back.money.applyconfirm.web;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.creditharmony.common.util.DateUtils;
import com.creditharmony.core.fortune.type.BackState;
import com.creditharmony.core.fortune.type.YoN;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.web.BaseController;
import com.creditharmony.fortune.back.money.applyconfirm.facade.BmApplyConfirmForeachManager;
import com.creditharmony.fortune.back.money.applyconfirm.service.ApplyConfirmManager;
import com.creditharmony.fortune.back.money.applyconfirm.service.BmApplyConfirmExportor;
import com.creditharmony.fortune.back.money.applyconfirm.service.BmApplyConfirmJzhExportor;
import com.creditharmony.fortune.back.money.common.service.BmManager;
import com.creditharmony.fortune.back.money.common.view.ItemView;
import com.creditharmony.fortune.back.money.common.view.ListItemView;
import com.creditharmony.fortune.back.money.common.view.ResultView;
import com.creditharmony.fortune.back.money.constant.BmConstant;
import com.creditharmony.fortune.common.entity.ReturnMsg;
import com.creditharmony.fortune.redemption.constant.RedeemConstant;
import com.creditharmony.fortune.utils.FortuneDictUtil;

/**
 * 回款申请确认相关Controller
 * @Class Name BackMoneyApplyConfirmController
 * @author 陈广鹏
 * @Create In 2015年12月2日
 */
@Controller
@RequestMapping("${adminPath}/myTodo/backMoney/")
public class BackMoneyApplyConfirmController extends BaseController {

	@Autowired
	private BmManager bmManager;
	@Autowired
	private BmApplyConfirmForeachManager applyConfirmForeachManager;
	@Autowired
	private ApplyConfirmManager applyConfirmManager;
	
	/**
	 * 回款申请确认列表
	 * 2015年12月3日
	 * By 陈广鹏
	 * @param request
	 * @param response
	 * @param view
	 * @param model
	 * @return
	 */
	@RequestMapping("applyConfirmList")
	public String applyConfirmList(HttpServletRequest request,
			HttpServletResponse response, ListItemView view, Model model){
		view.setStatusList(BmConstant.APPLY_CONFIRM_STATUS_LIST);
		
		Page<ListItemView> page = new Page<ListItemView>(request, response);
		Page<ListItemView> pageview = bmManager.findItemList(page, view);
		BigDecimal totalAcutalbackmoney = bmManager.getTotalAcutalbackmoney(view);
		BigDecimal totalSupplementedMoney = bmManager.getTotalSupplementedMoney(view);
		
		String backStatusOption = BackState.DHKSQQR.value + ","
				+ BackState.DHKSPTH.value + ","
				+ BackState.DHKTH.value + ","
				+ BackState.DHKQRTH.value + ","
				+ BackState.YHKTH.value + ","
				+ BackState.HKBXTH.value;
		
		model.addAttribute("page", pageview)
			.addAttribute("view", view)
			.addAttribute("backStatusOption", backStatusOption)
			.addAttribute("totalABM", totalAcutalbackmoney)
			.addAttribute("totalBXHM", totalSupplementedMoney)
			.addAttribute("eUrl", "exportApplyConfirm")
			.addAttribute("ejUrl", "exportJzhApplyConfirm");
		String[] types = {"tz_back_type","tz_pay_type","com_card_type",
				"tz_back_state","tz_back_reason","tz_contract_vesion","tz_channel_flag",
				"tz_working_state"};
		FortuneDictUtil.addDicts(model,types);
		return "backMoney/applyConfirm/applyConfirmList";
	}

	/**
	 * 跳转至回款申请确认详细页面
	 * 2015年12月3日
	 * By 陈广鹏
	 * @param model
	 * @param backmId
	 * @return
	 */
	@RequestMapping("applyConfirmDetail")
	public String applyConfirmDetail(Model model, String backmId) {

		ItemView view = new ItemView();
		view = bmManager.getDetail(backmId);
		
		model.addAttribute("view", view);
		String[] types = {"tz_back_reason","com_card_type","tz_open_bank","tz_pay_type","tz_contract_vesion",
						  "tz_working_state"};
		FortuneDictUtil.addDicts(model,types);
		
		return "backMoney/applyConfirm/applyConfirm";
	}
	
	/**
	 * 提交回款申请确认结果
	 * 2015年12月3日
	 * By 陈广鹏
	 * @param view 封装审批结果
	 * @return
	 */
	@RequestMapping("applyConfirm")
	@ResponseBody
	public String applyConfirm(ResultView view) {
		
		String str = applyConfirmForeachManager.applyConfirm(view);
		
		return jsonMapper.toJson(new ReturnMsg(str));
	}
	
	/**
	 * 显示批量回款申请确认弹窗
	 * 2016年6月17日
	 * By 陈广鹏
	 * @param model
	 * @return
	 */
	@RequestMapping("toMultiApplyConfirm")
	public String toMultiApplyConfirm(Model model){
		model.addAttribute("showFlag", "5");
		model.addAttribute("showBX", "1");
		String[] types = {"tz_back_reason"};
		FortuneDictUtil.addDicts(model,types);
		return "backMoney/common/popMultOperate";
	}

	/**
	 * 批量回款申请确认
	 * 2015年12月16日
	 * By 陈广鹏
	 * @param view
	 * @return
	 */
	@RequestMapping("multiApplyConfirm")
	@ResponseBody
	public String multiApplyConfirm(ListItemView view){
		view.setStatusList(BmConstant.APPLY_CONFIRM_STATUS_LIST);

		String rtnMsg = applyConfirmForeachManager.multiApplyConfirm(view);
		return jsonMapper.toJson(new ReturnMsg(rtnMsg));
	}

	/**
	 * 导出回款明细
	 * 2015年12月25日
	 * By 陈广鹏
	 * @param response
	 * @param listItemView
	 */
	@RequestMapping("exportApplyConfirm")
	public void exportApplyConfirm(HttpServletResponse response, ListItemView listItemView) {
		
		listItemView.setStatusList(BmConstant.APPLY_CONFIRM_STATUS_LIST);
		listItemView.setIsJZH(YoN.FOU.value);
		
		BmApplyConfirmExportor exportor = new BmApplyConfirmExportor(
				BmConstant.TO_APPLYCONFIRM + DateUtils.getDate(RedeemConstant.YYYY_MM_DD));
		exportor.exportData(listItemView, response);
	}
	
	/**
	 * 获取回款明细要导出的数据量
	 * 2016年3月5日
	 * By 陈广鹏
	 * @param listItemView
	 * @return
	 */
	@RequestMapping("exportApplyConfirmCheck")
	@ResponseBody
	public String exportApplyConfirmCheck(ListItemView listItemView){
		listItemView.setStatusList(BmConstant.APPLY_CONFIRM_STATUS_LIST);
		listItemView.setIsJZH(YoN.FOU.value);
		
		int count = bmManager.countExport(listItemView);
		return jsonMapper.toJson(count);
	}

	/**
	 * 导出金账户回款明细
	 * 2016年1月6日
	 * By 陈广鹏
	 * @param response
	 * @param listItemView
	 */
	@RequestMapping("exportJzhApplyConfirm")
	public void exportJzhApplyConfirm(HttpServletResponse response, ListItemView listItemView) {
		listItemView.setStatusList(BmConstant.APPLY_CONFIRM_STATUS_LIST);
		listItemView.setIsJZH(YoN.SHI.value);
		
		BmApplyConfirmJzhExportor exportor = new BmApplyConfirmJzhExportor(
				BmConstant.TO_JZH_APPLYCONFIRM + DateUtils.getDate(RedeemConstant.YYYY_MM_DD));
		exportor.exportData(listItemView, response);
	}
	
	/**
	 * 获取金账户回款明细要导出的数据量
	 * 2016年3月5日
	 * By 陈广鹏
	 * @param listItemView
	 * @return
	 */
	@RequestMapping("exportJzhApplyConfirmCheck")
	@ResponseBody
	public String exportJzhApplyConfirmCheck(ListItemView listItemView){
		
		listItemView.setStatusList(BmConstant.APPLY_CONFIRM_STATUS_LIST);
		listItemView.setIsJZH(YoN.SHI.value);
		
		int count = bmManager.countExport(listItemView);
		return jsonMapper.toJson(count);
	}

	/**
	 * 显示上传回款金额弹窗
	 * 2016年1月6日
	 * By 陈广鹏
	 * @return
	 */
	@RequestMapping("toUpload")
	public String toUpload(Model model) {
		return "backMoney/common/fileUpload";
	}
	
	/**
	 * 上传回款金额
	 * 2016年3月14日
	 * By 陈广鹏
	 * @param file
	 * @return
	 */
	@RequestMapping("uploadBackMoney")
	@ResponseBody
	public String uploadBackMoney(MultipartFile file) {

		String rtn = applyConfirmForeachManager.upLoadBackMoneyList(file);
		
		return rtn;
	}

}