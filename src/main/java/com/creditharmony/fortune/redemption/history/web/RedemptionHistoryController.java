package com.creditharmony.fortune.redemption.history.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.creditharmony.common.util.DateUtils;
import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.system.util.DataScopeUtil;
import com.creditharmony.core.type.SystemFlag;
import com.creditharmony.core.web.BaseController;
import com.creditharmony.fortune.redemption.common.entity.ext.Redemptionex;
import com.creditharmony.fortune.redemption.common.view.RedemptionApplyEntity;
import com.creditharmony.fortune.redemption.constant.RedeemConstant;
import com.creditharmony.fortune.redemption.history.service.RedeemApplyExportor;
import com.creditharmony.fortune.redemption.history.service.RedeemApprovalExportor;
import com.creditharmony.fortune.redemption.history.service.RedemptionHistoryManager;
import com.creditharmony.fortune.utils.FortuneDictUtil;

/**
 * 描述：赎回已办Controller
 * @Class Name RedemptionHistoryController
 * @author 陈广鹏
 * @Create In 2015年12月1日
 */
@Controller
@RequestMapping("${adminPath}/myDone/redemption/")
public class RedemptionHistoryController extends BaseController{
	
	@Autowired
	private RedemptionHistoryManager historyManager;
	
	/**
	 * 提前赎回已审批列表
	 * 2015年12月1日
	 * By 陈广鹏
	 * @param model
	 * @param request
	 * @param response
	 * @param redemptionex
	 * @return
	 */
	@RequestMapping("approvalList")
	public String approvalList(Model model, HttpServletRequest request,
			HttpServletResponse response, Redemptionex redemptionex){
		
		Page<Redemptionex> page = new Page<Redemptionex>(request, response);
		Page<Redemptionex> pageview = historyManager.findApprovalList(page, redemptionex);

		model.addAttribute("page", pageview)
			.addAttribute("rdm", redemptionex)
			.addAttribute("url", "approvalList")
			.addAttribute("allOrg", "showAllOrg");
		String[] types = {"tz_redeem_type","tz_pay_type","tz_yes_no","tz_redemption_status"};
		FortuneDictUtil.addDicts(model,types);
			

		return "redemption/history/historyList";
	}
	
	/**
	 * 提前赎回已申请列表
	 * 2015年12月22日
	 * By 陈广鹏
	 * @param model
	 * @param request
	 * @param response
	 * @param redemptionex
	 * @return
	 */
	@RequestMapping("applyList")
	public String applyList(Model model, HttpServletRequest request,
			HttpServletResponse response, Redemptionex redemptionex){
		
		Page<Redemptionex> page = new Page<Redemptionex>(request, response);
		Page<Redemptionex> pageview = historyManager.findApplyList(page, redemptionex);
		
		model.addAttribute("page", pageview)
			.addAttribute("rdm", redemptionex)
			.addAttribute("url", "applyList");
		String[] types = {"tz_redeem_type","tz_pay_type","tz_yes_no","tz_redemption_status"};
		FortuneDictUtil.addDicts(model,types);
		
		return "redemption/history/historyList";
	}
	
	/**
	 * 赎回申请查看列表
	 * 2015年12月22日
	 * By 陈广鹏
	 * @param model
	 * @param request
	 * @param response
	 * @param redemptionex
	 * @return
	 */
	@RequestMapping("applyCheck")
	public String applyCheck(Model model, HttpServletRequest request,
			HttpServletResponse response, Redemptionex redemptionex){
		
		Page<Redemptionex> page = new Page<Redemptionex>(request, response);
		Page<Redemptionex> pageview = historyManager.findApplyCheck(page, redemptionex);
		
		model.addAttribute("page", pageview)
			.addAttribute("rdm", redemptionex)
			.addAttribute("url", "applyCheck")
			.addAttribute("eUrl", "applyExport")
			.addAttribute("allOrg", "showAllOrg");
		String[] types = {"tz_redeem_type","tz_pay_type","tz_yes_no","tz_redemption_status"};
		FortuneDictUtil.addDicts(model,types);
		
		return "redemption/history/historyList";
	}
	
	/**
	 * 赎回申请查看列表，导出Excel
	 * 2015年12月23日
	 * By 陈广鹏
	 * @param response
	 * @param redemptionex
	 */
	@RequestMapping("applyExport")
	public void applyExport(HttpServletResponse response, Redemptionex redemptionex){
		// 配置数据权限
		String dataRights = DataScopeUtil.getDataScope("loan", SystemFlag.FORTUNE.value);
		if(StringUtils.isNotEmpty(dataRights)){
			Map<String, String> sqlMap = new HashMap<String, String>();
			sqlMap.put("dataRights", dataRights);
			redemptionex.setSqlMap(sqlMap);
		}
		
		String filename = RedeemConstant.FILENAME_REDEMPTION_APPLY + DateUtils.getDate(RedeemConstant.YYYY_MM_DD);
		RedeemApplyExportor exportor = new RedeemApplyExportor(filename);
		exportor.exportData(redemptionex, response);
	}
	
	/**
	 * 提前赎回审批查看
	 * 2015年12月22日
	 * By 陈广鹏
	 * @param model
	 * @param request
	 * @param response
	 * @param redemptionex
	 * @return
	 */
	@RequestMapping("approvalCheck")
	public String approvalCheck(Model model, HttpServletRequest request,
			HttpServletResponse response, Redemptionex redemptionex){
		
		Page<Redemptionex> page = new Page<Redemptionex>(request, response);
		Page<Redemptionex> pageview = historyManager.findApprovalCheck(page, redemptionex);
		
		model.addAttribute("page", pageview)
			.addAttribute("rdm", redemptionex)
			.addAttribute("eUrl", "approvalExport")
			.addAttribute("url", "approvalCheck")
			.addAttribute("allOrg", "showAllOrg");
		String[] types = {"tz_redeem_type","tz_pay_type","tz_yes_no","tz_redemption_status"};
		FortuneDictUtil.addDicts(model,types);
		
		return "redemption/history/historyList";
	}
	

	/**
	 * 赎回申请查看列表，导出Excel
	 * 2015年12月23日
	 * By 陈广鹏
	 * @param response
	 * @param redemptionex
	 */
	@RequestMapping("approvalExport")
	public void approvalExport(HttpServletResponse response, Redemptionex redemptionex){
		// 配置数据权限
		String dataRights = DataScopeUtil.getDataScope("loan", SystemFlag.FORTUNE.value);
		if(StringUtils.isNotEmpty(dataRights)){
			Map<String, String> sqlMap = new HashMap<String, String>();
			sqlMap.put("dataRights", dataRights);
			redemptionex.setSqlMap(sqlMap);
		}
		
		String filename = RedeemConstant.FILENAME_REDEMPTION_APPROVAL + DateUtils.getDate(RedeemConstant.YYYY_MM_DD);
		RedeemApprovalExportor exportor = new RedeemApprovalExportor(filename);
		exportor.exportData(redemptionex, response);
	}
	
	/**
	 * 跳转至详情页
	 * 2015年12月2日
	 * By 陈广鹏
	 * @param lendCode
	 * @param model
	 * @return
	 */
	@RequestMapping("historyDetail")
	public String historyDetail(String lendCode, Model model){
		
		RedemptionApplyEntity entity = historyManager.getRedemptionByLendCode(lendCode);
		
		model.addAttribute("entity", entity);
		String[] types = {"tz_open_bank","tz_pay_type","com_card_type","tz_contract_vesion","tz_redeem_type"};
		FortuneDictUtil.addDicts(model,types);
		return "redemption/history/history";
	}
	

}
