package com.creditharmony.fortune.back.money.apply.web;

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
import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.fortune.type.BackState;
import com.creditharmony.core.fortune.type.PayMent;
import com.creditharmony.core.fortune.type.YoN;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.web.BaseController;
import com.creditharmony.fortune.back.money.apply.facade.BmApplyForeachManager;
import com.creditharmony.fortune.back.money.apply.service.BmApplyExportor;
import com.creditharmony.fortune.back.money.apply.service.BmApplyJzhExportor;
import com.creditharmony.fortune.back.money.common.service.BmManager;
import com.creditharmony.fortune.back.money.common.view.ItemView;
import com.creditharmony.fortune.back.money.common.view.ListItemView;
import com.creditharmony.fortune.back.money.common.view.ResultView;
import com.creditharmony.fortune.back.money.constant.BmConstant;
import com.creditharmony.fortune.common.entity.ReturnMsg;
import com.creditharmony.fortune.redemption.constant.RedeemConstant;
import com.creditharmony.fortune.utils.FortuneDictUtil;

/**
 * 回款申请相关Controller
 * @Class Name BackMoneyApplyController
 * @author 陈广鹏 
 * @Create In 2015年12月16日
 */
@Controller
@RequestMapping("${adminPath}/myApply/backMoney/")
public class BackMoneyApplyController extends BaseController{
	
	@Autowired
	private BmApplyForeachManager applyManager;
	@Autowired
	private BmManager bmManager;
	
	/**
	 * 回款申请列表
	 * 2015年12月3日
	 * By 陈广鹏
	 * @param request
	 * @param response
	 * @param view
	 * @param model
	 * @return
	 */
	@RequestMapping("applyList")
	public String applyList(HttpServletRequest request,
			HttpServletResponse response, ListItemView view, Model model){
		view.setStatusList(BmConstant.APPLY_STATUS_LIST);
		view.setListFlag(BmConstant.APPLY_LIST);
		Page<ListItemView> page = new Page<ListItemView>(request, response);
		Page<ListItemView> pageview = bmManager.findItemList(page, view);
		BigDecimal totalLendMoney = bmManager.getTotalLendMoney(view);
		BigDecimal totalBackmoney = bmManager.getTotalBackmoney(view);
		
		String backStatusOption = BackState.DHKSQ.value + ","
				+ BackState.DHKSQQRTH.value;

		model.addAttribute("page", pageview)
			.addAttribute("view", view)
			.addAttribute("backStatusOption", backStatusOption)
			.addAttribute("totalLM", totalLendMoney)
			.addAttribute("totalBM", totalBackmoney)
			.addAttribute("eUrl", "exportApply")
			.addAttribute("ejUrl", "exportJzhApply");
		String[] types = {"tz_back_type","tz_pay_type","com_card_type",
				"tz_back_state","tz_back_reason","tz_contract_vesion","tz_channel_flag",
				"tz_working_state","tz_priority_back"};
		FortuneDictUtil.addDicts(model,types);
		return "backMoney/apply/applyList";
	}
	
	/**
	 * 跳转至回款申请详细页面
	 * 2015年12月3日
	 * By 陈广鹏
	 * @param backmId
	 * @param model
	 * @return
	 */
	@RequestMapping("applyDetail")
	public String applyDetail(String backmId, Model model){
		
		ItemView view = new ItemView();
		view = bmManager.getDetail(backmId);
		
		model.addAttribute("view", view);
		String[] types = {"tz_back_reason","com_card_type","tz_open_bank","tz_pay_type","tz_contract_vesion","tz_working_state","tz_priority_back"};
		FortuneDictUtil.addDicts(model,types);
		
		return "backMoney/apply/apply";
	}
	
	/**
	 * 发起回款申请
	 * 2015年12月3日
	 * By 陈广鹏
	 * @param view 封装审批结果
	 * @return 回款申请列表
	 */
	@RequestMapping("apply")
	@ResponseBody
	public String apply(ResultView view){
		
		String rtn = applyManager.apply(view);
		
		return jsonMapper.toJson(new ReturnMsg(rtn));
	}
	
	/**
	 * 批量回款申请
	 * 2015年12月16日
	 * By 陈广鹏
	 * @param view
	 * @return
	 */
	@RequestMapping("multiApply")
	@ResponseBody
	public String multiApply(ListItemView itemView){
		ListItemView view = new ListItemView();
		if (StringUtils.isNotEmpty(itemView.getBackmId())) {
			view.setBackmId(itemView.getBackmId());
		} else {
			return "请先选择需要操作的数据！";
		}
		view.setStatusList(BmConstant.APPLY_STATUS_LIST);
		view.setListFlag(BmConstant.APPLY_LIST);
		
		String rtn = applyManager.multiApply(view);
		return jsonMapper.toJson(new ReturnMsg(rtn));
	}
	
	
	/**
	 * 导出回款申请明细
	 * 2015年12月25日
	 * By 陈广鹏
	 * @param response
	 * @param listItemView
	 */
	@RequestMapping("exportApply")
	public void exportApply(HttpServletResponse response, ListItemView listItemView) {
		listItemView.setStatusList(BmConstant.APPLY_STATUS_LIST);
		listItemView.setListFlag(BmConstant.APPLY_LIST);
		listItemView.setIsJZH(YoN.FOU.value);
		
		BmApplyExportor export = new BmApplyExportor(BmConstant.TO_APPLY + DateUtils.getDate(RedeemConstant.YYYY_MM_DD));
		export.exportData(listItemView, response);
	}
	
	/**
	 * 获取导出回款申请明细数据量
	 * 2016年3月5日
	 * By 陈广鹏
	 * @param listItemView
	 * @return
	 */
	@RequestMapping("exportApplyCheck")
	@ResponseBody
	public String exportApplyCheck(ListItemView listItemView) {
		listItemView.setStatusList(BmConstant.APPLY_STATUS_LIST);
		listItemView.setListFlag(BmConstant.APPLY_LIST);
		listItemView.setIsJZH(YoN.FOU.value);
		
		int count = bmManager.countExport(listItemView);
		return jsonMapper.toJson(count);
	}
	
	/**
	 * 导出金账户回款申请明细
	 * 2015年12月25日
	 * By 陈广鹏
	 * @param response
	 * @param listItemView
	 */
	@RequestMapping("exportJzhApply")
	public void exportJzhApply(HttpServletResponse response, ListItemView listItemView) {
		listItemView.setStatusList(BmConstant.APPLY_STATUS_LIST);
		listItemView.setListFlag(BmConstant.APPLY_LIST);
		listItemView.setApplyPay(PayMent.ZJTG.value);
		listItemView.setIsJZH(YoN.SHI.value);
		BmApplyJzhExportor exportor = new BmApplyJzhExportor(
				BmConstant.TO_JZH_APPLY + DateUtils.getDate(RedeemConstant.YYYY_MM_DD));
		exportor.exportData(listItemView, response);
	}
	
	/**
	 * 获取导出金账户回款申请明细数据条数
	 * 2015年12月25日
	 * By 陈广鹏
	 * @param response
	 * @param listItemView
	 */
	@RequestMapping("exportJzhApplyCheck")
	@ResponseBody
	public String exportJzhApplyCheck(ListItemView listItemView){
		listItemView.setStatusList(BmConstant.APPLY_STATUS_LIST);
		listItemView.setListFlag(BmConstant.APPLY_LIST);
		listItemView.setApplyPay(PayMent.ZJTG.value);
		listItemView.setIsJZH(YoN.SHI.value);
		
		int count = bmManager.countExport(listItemView);
		return jsonMapper.toJson(count);
	}
	
	/**
	 * 数据校验，验证是否可以提交回款申请
	 * 2016年3月4日
	 * By 陈广鹏
	 * @param listItemView
	 */
	@RequestMapping("dataExamine")
	@ResponseBody
	public String dataExamine(ListItemView view) {
		String str = applyManager.dataExamine(view);
		return jsonMapper.toJson(str);
	}
	
	/**
	 * 上传文件更新渠道标识
	 * 2016年6月20日
	 * By 陈广鹏
	 * @param file
	 * @param view
	 * @return
	 */
	@RequestMapping("updateChannel")
	@ResponseBody
	public String uploadConfirmResult(MultipartFile file,  ListItemView view) {
		String msg = applyManager.updateChannel(file,view);
		return msg;
	}
	
	/**
	 * 显示上传渠道标识弹窗
	 * 2017年1月11日
	 * By 郭强
	 * @return
	 */
	@RequestMapping("toUploadChannel")
	public String toUploadChannel(Model model) {
		String[] types = {"tz_channel_flag"};
		model.addAttribute("showPlatform", "2");
		FortuneDictUtil.addDicts(model,types);
		return "backMoney/common/fileUpload";
	}
	
}
