package com.creditharmony.fortune.back.priority.apply.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.creditharmony.common.util.DateUtils;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.web.BaseController;
import com.creditharmony.fortune.back.priority.apply.facade.MultiManager;
import com.creditharmony.fortune.back.priority.apply.service.PriorityApplyManager;
import com.creditharmony.fortune.back.priority.apply.service.PriorityExportor;
import com.creditharmony.fortune.back.priority.common.service.PbmManager;
import com.creditharmony.fortune.back.priority.common.view.PriorityDetailItem;
import com.creditharmony.fortune.back.priority.common.view.PriorityListItemView;
import com.creditharmony.fortune.common.entity.Attachment;
import com.creditharmony.fortune.common.entity.ReturnMsg;
import com.creditharmony.fortune.redemption.constant.RedeemConstant;
import com.creditharmony.fortune.utils.FortuneDictUtil;

/**
 * 优先回款申请相关Controller
 * @Class Name PriorityBackMoneyApplyController
 * @author 郭强
 * @Create In 2017年3月27日
 */
@Controller
@RequestMapping("${adminPath}/myPriority/backMoney/")
public class PriorityBackMoneyApplyController extends BaseController{
	
	@Autowired
	private  PbmManager  pbmmanager;
	@Autowired
	private  PriorityApplyManager  paManager;
	@Autowired
	private  MultiManager  mumanager;
	
	/**
	 * 优先回款申请列表
	 * 2017年3月27日
	 * By 郭强
	 * @param request
	 * @param response
	 * @param view
	 * @param model
	 * @return
	 */
	@RequestMapping("applyList")
	public String applyList(HttpServletRequest request,
			HttpServletResponse response, PriorityListItemView view, Model model){
		Page<PriorityListItemView> page = new Page<PriorityListItemView>(request, response);
		Page<PriorityListItemView> pageview = pbmmanager.findItemList(page, view);
		
		model.addAttribute("page", pageview)
			.addAttribute("view", view);
		
		String[] types = {"tz_back_type","tz_pay_type","com_card_type",
				"tz_back_state","tz_priority_state"};
		FortuneDictUtil.addDicts(model,types);
		return "priorityBack/apply/applyList";
	}
	
	/**
	 * 跳转至优先回款申请查看详细页面
	 * 2017年3月27日
	 * By 郭强
	 * @param model
	 * @param priorityId
	 * @return
	 */
	@RequestMapping("applyLookDetail")
	public String applyLookDetail(Model model, String priorityId) {

		PriorityDetailItem  view = new PriorityDetailItem();
		view = pbmmanager.getDetail(priorityId);
		
		model.addAttribute("view", view);
		String[] types = {"com_card_type","tz_priority_state","tz_pay_type","tz_open_bank"};
		FortuneDictUtil.addDicts(model,types);
		
		return "priorityBack/apply/applyDetail";
	}
	
	/**
	 * 撤销申请
	 * 2017年3月31日
	 * 郭强
	 */
	@RequestMapping("revocationApply")
	@ResponseBody
	public String revocationApply(PriorityListItemView view) {
		
		String message = paManager.revocationApply(view);
		return jsonMapper.toJson(new ReturnMsg(message));
	}
	
	/**
	 * 批量撤销申请
	 * 2017年3月31日
	 * 郭强
	 */
	@RequestMapping("multiRevocation")
	@ResponseBody
	public String multiRevocation(PriorityListItemView view) {
		String message = mumanager.multiRevocation(view);
		return jsonMapper.toJson(new ReturnMsg(message));
	}
	
	/**
	 * 对数据的状态进行校验
	 * 2017年4月1日
	 * 郭强
	 */
	@RequestMapping("checkoutState")
	@ResponseBody
	public String checkoutState(PriorityListItemView view) {
		String message = mumanager.checkoutState(view);
		return jsonMapper.toJson(new ReturnMsg(message));
	}
	
	/**
	 * 导出excel
	 * 2017年4月1日
	 * 郭强
	 */
	@RequestMapping("exportExcel")
	public  void  exportExcel(HttpServletResponse response, PriorityListItemView view) {
		
		PriorityExportor exportor = new PriorityExportor(
				"优先回款申请excel" + DateUtils.getDate(RedeemConstant.YYYY_MM_DD));
		exportor.exportData(view, response);
	}
	
	
	/**
	 * 获取文件
	 * @param code
	 * @param tableId
	 * @param tableName
	 * @return
	 */
	@RequestMapping(value="/getAttachmentAjax", method = RequestMethod.POST)
	@ResponseBody
	public String getAttachmentList( @RequestParam("code") String code, @RequestParam("tableId") String tableId, @RequestParam("tableName") String tableName){
		List<Attachment> list = pbmmanager.findFileList(code,tableId,tableName);
		return jsonMapper.toJson(list);
	}
	
}
