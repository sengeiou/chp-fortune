package com.creditharmony.fortune.back.priority.examine.web;

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

import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.web.BaseController;
import com.creditharmony.fortune.back.priority.common.service.PbmManager;
import com.creditharmony.fortune.back.priority.common.view.PriorityDetailItem;
import com.creditharmony.fortune.back.priority.common.view.PriorityListItemView;
import com.creditharmony.fortune.back.priority.constant.PbmConstant;
import com.creditharmony.fortune.back.priority.examine.service.PriorityExamine;
import com.creditharmony.fortune.common.entity.Attachment;
import com.creditharmony.fortune.common.entity.ReturnMsg;
import com.creditharmony.fortune.utils.FortuneDictUtil;

/**
 * 优先回款审批相关Controller
 * @Class Name PriorityBackExamineController
 * @author 郭强
 * @Create In 2017年3月29日
 */
@Controller
@RequestMapping("${adminPath}/myPriority/examine/")
public class PriorityExamineController extends BaseController{
	
	@Autowired
	private  PbmManager  pbmmanager;
	@Autowired
	private PriorityExamine pexamine;
	
	/**
	 * 优先回款审批列表
	 * 2017年3月30日
	 * By 郭强
	 * @param request
	 * @param response
	 * @param view
	 * @param model
	 * @return
	 */
	@RequestMapping("examineList")
	public String applyList(HttpServletRequest request,
			HttpServletResponse response, PriorityListItemView view, Model model){
		Page<PriorityListItemView> page = new Page<PriorityListItemView>(request, response);
		view.setPriorityBackStateAS(PbmConstant.PRIORITY_EXAMINE_STATUS_LIST);
		Page<PriorityListItemView> pageview = pexamine.findItemList(page, view);

		model.addAttribute("page", pageview)
			.addAttribute("view", view);
		
		String[] types = {"tz_back_type","tz_pay_type","com_card_type",
				"tz_back_state","tz_priority_state"};
		FortuneDictUtil.addDicts(model,types);
		return "priorityBack/examine/examineList";
	}
	
	/**
	 * 跳转至优先回款审批列表详细页面
	 * 2017年3月27日
	 * By 郭强
	 * @param model
	 * @param priorityId
	 * @return
	 */
	@RequestMapping("examineDetail")
	public String applyLookDetail(Model model, String priorityId) {

		PriorityDetailItem  view = new PriorityDetailItem();
		view = pbmmanager.getDetail(priorityId);
		
		model.addAttribute("view", view);
		String[] types = {"com_card_type","tz_priority_state","tz_pay_type","tz_open_bank","tz_contract_vesion"};
		FortuneDictUtil.addDicts(model,types);
		
		return "priorityBack/examine/examineDetail";
	}
	
	/**
	 *优先回款审批
	 * 2017年3月27日
	 * By 郭强
	 * @param view
	 * @return
	 */
	@RequestMapping("examineConfirm")
	@ResponseBody
	public String examineConfirm(PriorityDetailItem view) {
		String message = pexamine.examineConfirm(view);
		return jsonMapper.toJson(new ReturnMsg(message));
	}
	
	/**
	 * 获取文件
	 * @param code
	 * @param tableId
	 * @param tableName
	 * @return
	 */
	@RequestMapping(value="getAttachmentAjax", method = RequestMethod.POST)
	@ResponseBody
	public String getAttachmentList( @RequestParam("code") String code, @RequestParam("tableId") String tableId, @RequestParam("tableName") String tableName){
		List<Attachment> list = pbmmanager.findFileList(code,tableId,tableName);
		return jsonMapper.toJson(list);
	}
}
