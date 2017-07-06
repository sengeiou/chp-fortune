package com.creditharmony.fortune.lend.reject.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.creditharmony.core.fortune.type.FortuneLogLevel;
import com.creditharmony.core.fortune.type.FortuneLogNode;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.web.BaseController;
import com.creditharmony.fortune.common.entity.FortuneException;
import com.creditharmony.fortune.common.entity.ReturnMsg;
import com.creditharmony.fortune.common.service.FortuneExceptionService;
import com.creditharmony.fortune.lend.apply.view.LendApplyQueryView;
import com.creditharmony.fortune.lend.apply.view.LendApplyView;
import com.creditharmony.fortune.lend.reject.manager.LendRejectManager;
import com.creditharmony.fortune.lend.reject.view.LendApplyApprovalReturnedItemView;
import com.creditharmony.fortune.lend.reject.workflow.LendRejectFlowManager;
import com.creditharmony.fortune.utils.FortuneDictUtil;

/**
 * 出借退回处理类
 * 
 * @Class Name LendRejectController
 * @author 朱杰
 * @Create In 2016年4月10日
 */
@Controller
@RequestMapping("${adminPath}/lend/reject")
public class LendRejectController extends BaseController {
	@Autowired
	private LendRejectManager lendRejectManager;
	@Resource
	private LendRejectFlowManager lendRejectFlowManager;
	@Autowired
	private FortuneExceptionService fortuneExceptionService;

	/**
	 * 获取出借申请退回列表 2015年12月31日
	 * 
	 * @param view
	 * @param model
	 * @param workItem
	 * @return
	 */
	@RequestMapping({ "fetchReturnedTaskItems" })
	public String fetchReturnedItems(LendApplyQueryView view, Model model, WorkItemView workItem, HttpServletRequest request, HttpServletResponse response) {
		
		Page<LendApplyApprovalReturnedItemView> page = new Page<LendApplyApprovalReturnedItemView>(request, response);
		FlowPage flowPage = new FlowPage();
		
		List<LendApplyApprovalReturnedItemView> list = lendRejectManager.fetchReturnedTaskItems(workItem, view, page, flowPage);
		model.addAttribute("lendApply", view);   
		model.addAttribute("items", list);
		model.addAttribute("page", flowPage);
		FortuneDictUtil.addDicts(model, new String[]{"tz_deduct_plat","tz_pay_type"});
		return "/lend/reject/lendApply_returned_list";
	}


	/**
	 * 重新发起流程 2015年12月31日
	 * 
	 * @param redirectAttributes
	 * @param workItem
	 * @param bv
	 * @return
	 */
	@RequestMapping("reLaunchFlow")
	@ResponseBody
	public String reLaunchFlow(RedirectAttributes redirectAttributes, WorkItemView workItem, LendApplyView bv) {
		String msg = "";
		try{
			msg = lendRejectFlowManager.reLaunchFlow(workItem, bv);
		}catch(Exception e){
			logger.error("重新发起流程",e);
			msg = e.getMessage();
			FortuneException log = new FortuneException(
					bv.getLendApply().getApplyCode(), e, FortuneLogNode.LEND_REJECT.getCode(), 
					FortuneLogLevel.YELLOW.value, null, "退回提交："+msg);
			fortuneExceptionService.save(log);
		}
		return jsonMapper.toJson(new ReturnMsg(msg));
	}
	
	@RequestMapping("canCelLendApply")
	@ResponseBody
	public String canCelLendApply(String lendCode, String wobNum, String token){
		Map<String, String> msg = new HashMap<String, String>();
		try{
			lendRejectFlowManager.canCelLendApply(lendCode, wobNum, token);
			msg.put("result", "true");
			msg.put("msg", "撤销成功");
			return jsonMapper.toJson(msg);
		}catch(Exception e){
			logger.info("出借申请退回异常：" + lendCode, e);
			msg.put("result", "false");
			msg.put("msg", "撤销失败" + e.getMessage());
			return jsonMapper.toJson(msg);
		}
		
		
	}

}
