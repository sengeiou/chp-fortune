package com.creditharmony.fortune.trusteeship.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.creditharmony.bpm.frame.view.FlowPage;
import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.fortune.type.LendchgType;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.web.BaseController;
import com.creditharmony.fortune.change.lender.apply.entity.LenderChangeLog;
import com.creditharmony.fortune.common.entity.Attachment;
import com.creditharmony.fortune.common.entity.Check;
import com.creditharmony.fortune.common.entity.ReturnMsg;
import com.creditharmony.fortune.common.service.AttachmentManager;
import com.creditharmony.fortune.common.service.CheckManager;
import com.creditharmony.fortune.trusteeship.manager.TrusteeshipChangeManager;
import com.creditharmony.fortune.trusteeship.view.TrusteeshipChangeTaskView;
import com.creditharmony.fortune.trusteeship.view.TrusteeshipQueryView;
import com.creditharmony.fortune.utils.FileUtil;
import com.creditharmony.fortune.utils.FortuneDictUtil;

/**
 * 金账户信息变更
 * @Class Name TrusteeshipChangeController
 * @author 郭才林
 * @Create In 2016年2月14日
 */
@Controller
@RequestMapping(value="${adminPath}/trusteeship/change")
public class TrusteeshipChangeController extends BaseController{
	
	@Autowired
	private TrusteeshipChangeManager tsChangeManager;
	
	@Autowired
	private CheckManager checkManager;
	@Autowired
	protected AttachmentManager attachmentService;
	
	
	/**
	 * 金账户信息变更列表
	 * 2016年2月26日
	 * By 郭才林
	 * @param search
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = {"list",""})
	public String list(TrusteeshipQueryView search,HttpServletRequest request,
			HttpServletResponse response, Model model){
	
		Page<TrusteeshipQueryView> page = new Page<TrusteeshipQueryView>(request, response);
		FlowPage flowPage = new FlowPage();
		List<TrusteeshipChangeTaskView> list = tsChangeManager.getList(search,page,flowPage);
		model.addAttribute("itemList", list);
		model.addAttribute("search", search);
		model.addAttribute("page", flowPage);
		FortuneDictUtil.addDicts(model,new String[]{"tz_open_bank"});
		return "trusteeship/change/main";
	}
	
	/**
	 * 金账户信息变更留痕
	 * 2016年2月26日
	 * By 郭才林
	 * @param search
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping({"failList"})
	public String failList(Check check,HttpServletRequest request,
			HttpServletResponse response, Model model){
	
		Page<Check> page = checkManager.findCheckList(new Page<Check>(request, response), check);
		model.addAttribute("page", page);

		return "fullMark/fullMark";
	}
	
	
	/**
	 * 金账户审批
	 * 2016年2月25日
	 * By 郭才林
	 * @param workItem
	 * @param redirectAttributes
	 * @param response
	 * @param changelog
	 * @return
	 */
	@RequestMapping({"dispatch"})
	@ResponseBody
	public String dispatch(String flowInfo,LenderChangeLog log, String oper,HttpServletResponse response) {
	   
		try {
			tsChangeManager.dispatch(flowInfo,oper,log);
			return jsonMapper.toJson(new ReturnMsg("true"));
		} catch (Exception e) {
			logger.error("金账户信息变更",e);
			return jsonMapper.toJson(new ReturnMsg(e.getMessage()));
		}
		
	}
	
	/**
	 * 金账户审批
	 * 2016年2月25日
	 * By 郭才林
	 * @param workItem
	 * @param redirectAttributes
	 * @param response
	 * @param changelog
	 * @return
	 */
	@RequestMapping({"back"})
	public String back(HttpServletResponse response) {
	   
		return "trusteeship/change/back";
		
	}
	
	/**
	 * 变更附件下载
	 * 2016年3月2日
	 * By 郭才林
	 * @param response
	 * @return
	 */
	@RequestMapping({"chengeFileDown"})
	public void chengeFileDown(HttpServletResponse response,String applyIds,String downFileType,TrusteeshipQueryView search) {
	   
		String fileName="";
		if(downFileType.equals(LendchgType.TRUSTESSHIP_CANCELLATION.value)){
			fileName=LendchgType.getLendchgType(LendchgType.TRUSTESSHIP_CANCELLATION.value);
		}else if(downFileType.equals(LendchgType.TRUSTESSHIP_PHONE_CHA.value)){
			fileName=LendchgType.getLendchgType(LendchgType.TRUSTESSHIP_PHONE_CHA.value);
		}else if(downFileType.equals(LendchgType.TRUSTESSHIP_CARD_CHA.value)){
			fileName=LendchgType.getLendchgType(LendchgType.TRUSTESSHIP_CARD_CHA.value);
		}
		if(StringUtils.isEmpty(applyIds)){
			// 下载查询条件的所有附件
			StringBuilder sb = new StringBuilder();   
			List<TrusteeshipChangeTaskView> list = tsChangeManager.getList(search,null,null);
			if(list!=null){
				for (int i = 0; i < list.size(); i++) {
					if (i < list.size() - 1) {  
		                sb.append(list.get(i).getApplyId() + ",");  
		            } else {  
		                sb.append(list.get(i).getApplyId());  
		            } 
				}
			}
			applyIds=sb.toString();
		}
		List<Attachment> attaList=tsChangeManager.getAttachmentByApplyIds(applyIds,downFileType);
		FileUtil.zipFileDownload(fileName, response, attaList);
		
	}
	
	
	
}