package com.creditharmony.fortune.change.lender.apply.web;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.creditharmony.bpm.frame.view.WorkItemView;
import com.creditharmony.core.fortune.type.FinishState;
import com.creditharmony.core.fortune.type.ReportType;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.web.BaseController;
import com.creditharmony.fortune.change.lend.apply.entity.LendLoanApply;
import com.creditharmony.fortune.change.lender.apply.entity.LenderChangeInfo;
import com.creditharmony.fortune.change.lender.apply.entity.ext.CustomerEx;
import com.creditharmony.fortune.change.lender.apply.service.LenderChangeApplyManager;
import com.creditharmony.fortune.change.lender.apply.view.LenderInitView;
import com.creditharmony.fortune.change.lender.apply.view.LenderLoadView;
import com.creditharmony.fortune.change.lender.apply.view.LenderQueryView;
import com.creditharmony.fortune.change.lender.apply.workflow.service.LenderChangeApplyFlowManager;
import com.creditharmony.fortune.deduct.common.Constant;
import com.creditharmony.fortune.utils.FortuneDictUtil;

/**
 * 出借人信息变更申请控制器
 * @Class Name LenderChangeController
 * @author 郭才林
 * @Create In 2015年11月19日
 */
@Controller
@RequestMapping({ "${adminPath}/lenderChange/apply" })
public class LenderChangeApplyController extends BaseController {

	@Autowired
	private LenderChangeApplyManager applyService;

	@Autowired
	private LenderChangeApplyFlowManager flowManager;

	/**
	 * 查询可变更客户
	 * 2015年12月2日
	 * By 郭才林
	 * @param query
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "list", "" })
	public String list(LenderQueryView query, HttpServletRequest request, HttpServletResponse response, Model model) {

		Page<CustomerEx> page = applyService.findPage(new Page<CustomerEx>(request, response), query);
		FortuneDictUtil.addDicts(model, new String[]{"tz_customer_src"});
		model.addAttribute("page", page).addAttribute("query", query);
		return "/lenderChange/lenderChangeList";
	}
	
	/**
	 * 发起流程
	 * 2015年12月9日
	 * By 郭才林
	 * @param redirectAttributes
	 * @param workItem
	 * @param bv
	 * @param cust
	 * @param response
	 * @return
	 */
	@RequestMapping({ "launchFlow" })
	public String launchFlow(RedirectAttributes redirectAttributes, WorkItemView workItem, LenderInitView bv, CustomerEx cust, HttpServletResponse response) {

		flowManager.launchFlow(workItem, bv);
		addMessage(redirectAttributes, new String[] { "客户：" + bv.getCustomer().getCustName() + "变更申请成功" });
		return "redirect:" + this.adminPath + "/lenderChange/apply/list";

	}

	/**
	 * 跳转到出借人变更历史
	 * 2015年12月2日
	 * By 郭才林
	 * @param changeInfo
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping({ "goLenderChangeHistory" })
	public String goLenderChangeHistory(LenderChangeInfo changeInfo, Model model, HttpServletRequest request, HttpServletResponse response) {

		Page<CustomerEx> page = applyService.getLenderChangeHistory(new Page<CustomerEx>(request, response), changeInfo);
		
		FortuneDictUtil.addDicts(model, new String[]{"tz_customer_src"});
		model.addAttribute("page", page);
		return "/lenderChange/lenderChangeHistory";
	}

	/**
	 * 弹出变更历史详细页
	 * 2015年12月9日
	 * By 郭才林
	 * @param applyId
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping({ "openLenderChangeHistoryDetail" })
	public String openLenderChangeHistoryDetail(String applyId, Model model, HttpServletRequest request, HttpServletResponse response) {

		LenderLoadView bv = applyService.historyDetail(applyId);
		FortuneDictUtil.addDicts(model, new String[]{"tz_marital_state","tz_billtaken_mode"});
		model.addAttribute("bv", bv);
		return "/lenderChange/lenderChangeHistoryDetail";
	}
	
	/**
	 * 导出模板
	 * 2015年12月8日
	 * By 郭才林
	 * @param customerEx
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping({ "exportExcel" })
	public String exportExcel(String custCode, Model model, HttpServletRequest request, HttpServletResponse response) {

		String filename = "出借人信息变更申请书.pdf";
		Map<String, Object> filters = new HashMap<String, Object>();
		String cptName = Constant.getProperties.get(ReportType.CJR_BG.getCode());
		filters.put("templateName",cptName);
		applyService.downloadTemplate(response,filters,filename);
		return null;
	}
	
	/**
	 * 导出金账户销户模板
	 * 2015年12月8日
	 * By 郭才林
	 * @param customerEx
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping({ "exportGoldCancelExcel" })
	public String exportGoldCancelExcel(HttpServletRequest request, HttpServletResponse response) {

		String filename = "金账户用户注销申请.pdf";
		Map<String, Object> filters = new HashMap<String, Object>();
		String cptName = Constant.getProperties.get(ReportType.JZH_XH.getCode());
		filters.put("templateName",cptName);
		applyService.downloadTemplate(response,filters,filename);
		return null;
	}
	
	/**
	 * 判断是否可以金账户注销
	 * 2016年2月29日
	 * By 郭才林
	 * @param applyId
	 * @return
	 */
	@RequestMapping(value = { "isAccountOff", "" })
	public void isAccountOff(String custCode,HttpServletResponse response) {
		
		LendLoanApply apply=new LendLoanApply();
		apply.setCustCode(custCode);
		apply.setDictApplyEndState(FinishState.WWJ.value);
		try {
			response.getWriter().write(applyService.isAccountOff(apply));
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 申请书预览
	 * 2016年3月29日
	 * By 郭才林
	 * @param model
	 * @param type
	 * @param str
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping({ "sqsPreview"})
	public void sqsPreview(Model model, HttpServletResponse response,LenderInitView view) {
		
		OutputStream os;
		HttpURLConnection httpConn = null;  
		
		try {
			os = response.getOutputStream();
			String url= applyService.sqsPreview(view);
			URL uri = new URL(url);
			httpConn = (HttpURLConnection) uri.openConnection();  
			httpConn.setConnectTimeout(15 * 1000);// 联通最长时间
			httpConn.setReadTimeout( 60 * 1000); // 获取数据最长时间
			httpConn.setRequestMethod("POST");
			httpConn.setRequestProperty("connection", "close");
			httpConn.setRequestProperty("Charsert", "UTF-8");
			httpConn.setDoOutput(true);
			httpConn.setUseCaches(false);
			InputStream in = httpConn.getInputStream();  
			com.creditharmony.dm.file.util.FileUtil.writeFile(os, in);
			os.flush();  
			os.close();  
			os = null;  
			response.flushBuffer();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(httpConn!=null){
				httpConn.disconnect();
			}
		
		}
		
	}

}