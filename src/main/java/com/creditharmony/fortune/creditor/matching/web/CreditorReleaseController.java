package com.creditharmony.fortune.creditor.matching.web;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.web.BaseController;
import com.creditharmony.fortune.creditor.matching.service.CreditorReleaseManager;
import com.creditharmony.fortune.creditor.matching.utils.CreditorReleaseState;
import com.creditharmony.fortune.creditor.matching.vo.CreditorReleaseView;
import com.creditharmony.fortune.creditor.matching.vo.CreditorReleaseVo;
import com.creditharmony.fortune.utils.FortuneDictUtil;

/**
 * 债权到期释放
 * @author xurongsheng
 * @date 2016年11月23日 下午1:49:54
 *
 */
@Controller
@RequestMapping(value = "${adminPath}/creditor/creditorRelease")
public class CreditorReleaseController extends BaseController{

	@Resource
	private CreditorReleaseManager creditorReleaseManager;
	
	/**
	 * 分页查询 债权到期释放列表
	 * @author xurongsheng
	 * @date 2016年11月24日 下午1:38:56
	 * @param creditorReleaseVo
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("list")
	public String findList(CreditorReleaseView creditorReleaseView, HttpServletRequest request,
			HttpServletResponse response, Model model){
		//默认查询【转出状态】为"未操作"的数据
		if(creditorReleaseView == null || creditorReleaseView.getIsSearch() == null){
			creditorReleaseView.setReleaseState(CreditorReleaseState.WCZ.value);
		}
		// 分页查询
		Page<CreditorReleaseVo> page = new Page<CreditorReleaseVo>(request,response);
		page = creditorReleaseManager.findPage(page, creditorReleaseView);
		// 查询总金额数
		BigDecimal allMoney = creditorReleaseManager.findAllMoney(creditorReleaseView);
		// 查询总金额数[先四舍五入后求和]
		BigDecimal allMoney2 = creditorReleaseManager.findAllMoney2(creditorReleaseView);
		// 设置页面列表显示
		model.addAttribute("page", page);
		model.addAttribute("creditorReleaseView",creditorReleaseView);
		model.addAttribute("allMoney",allMoney);
		model.addAttribute("allMoney2",allMoney2);
		String[] types = {"tz_credit_src","tz_zjtr_mark","jk_prof_type","tz_repay_day","tz_loan_distinguish","tz_creditor_release_state","tz_creditor_release_flag","tz_creditor_release_loan_flag","tz_certificate_type"};
		FortuneDictUtil.addDicts(model,types);
		return "/creditor/matching/creditorReleaseList";
	}
	
	/**
	 * 查看 选择平台窗口
	 * @author xurongsheng
	 * @date 2016年11月28日 下午5:38:56
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "showSelectPlatWindow")
	public String showSelectPlatWindow(Model model){
		return "/creditor/matching/selectPlatWindow";
	}

	/**
	 * 检测能否释放
	 * @author xurongsheng
	 * @date 2016年12月27日 下午2:10:28
	 * @param request
	 * @param response
	 * @param model
	 * @param creditorReleaseView
	 * @return
	 */
	@RequestMapping(value = "checkReleaseCreditor")
	@ResponseBody
	public String checkReleaseCreditor(HttpServletRequest request,
			HttpServletResponse response, Model model, CreditorReleaseView creditorReleaseView){
		Map<String, Object> resultMap = creditorReleaseManager.checkReleaseCreditor(creditorReleaseView);
		resultMap.put("result", "success");
		return jsonMapper.toJson(resultMap); 
	}
	
	/**
	 * 释放
	 * @author xurongsheng
	 * @date 2016年11月28日 下午5:46:18
	 * @param request
	 * @param response
	 * @param model
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "releaseCreditor")
	@ResponseBody
	public String releaseCreditor(HttpServletRequest request,
			HttpServletResponse response, Model model,CreditorReleaseView creditorReleaseView){
		String result = "success";
		
		Date startTime = new Date(); //释放开始时间
		
		//释放
		List<String> errorList = creditorReleaseManager.releaseCreditor(creditorReleaseView);
		if(errorList != null && errorList.size() > 0){
			result = "fail";
		}
		Date endTime = new Date(); //释放结束时间
		
		//获取总释放金额
		BigDecimal totalMoney = creditorReleaseManager.getTotalReleaseMoney(startTime,endTime); //总释放金额
		
		//新增 债权释放统计
		creditorReleaseManager.insertReleaseStat(startTime,endTime,totalMoney);
				
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("result", result);
		resultMap.put("message", errorList.toString());
		return jsonMapper.toJson(resultMap); 
	}
	
	/**
	 * 检测能否转出
	 * @author xurongsheng
	 * @date 2016年12月28日 下午3:04:28
	 * @param request
	 * @param response
	 * @param model
	 * @param creditorReleaseView
	 * @return
	 */
	@RequestMapping(value = "checkTransferOut")
	@ResponseBody
	public String checkTransferOut(HttpServletRequest request,
			HttpServletResponse response, Model model, CreditorReleaseView creditorReleaseView){
		Map<String, Object> resultMap = creditorReleaseManager.checkTransferOut(creditorReleaseView);
		resultMap.put("result", "success");
		return jsonMapper.toJson(resultMap); 
	}
	
	
	/**
	 * 转出
	 * @author xurongsheng
	 * @date 2016年11月28日 下午7:47:29
	 * @param request
	 * @param response
	 * @param model
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "transferOut")
	@ResponseBody
	public String transferOut(HttpServletRequest request,
			HttpServletResponse response, Model model, CreditorReleaseView creditorReleaseView){
		String result = creditorReleaseManager.transferOut(creditorReleaseView);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("result", result);
		return jsonMapper.toJson(resultMap); 
	}
	
	/**
	 * 检测能否确认转出
	 * @author xurongsheng
	 * @date 2016年12月28日 下午3:26:38
	 * @param request
	 * @param response
	 * @param model
	 * @param creditorReleaseView
	 * @return
	 */
	@RequestMapping(value = "checkConfirmTransferOut")
	@ResponseBody
	public String checkConfirmTransferOut(HttpServletRequest request,
			HttpServletResponse response, Model model, CreditorReleaseView creditorReleaseView){
		Map<String, Object> resultMap = creditorReleaseManager.checkConfirmTransferOut(creditorReleaseView);
		resultMap.put("result", "success");
		return jsonMapper.toJson(resultMap); 
	}

	/**
	 * 确认转出
	 * @author xurongsheng
	 * @date 2016年11月28日 下午7:46:52
	 * @param request
	 * @param response
	 * @param model
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "confirmTransferOut")
	@ResponseBody
	public String confirmTransferOut(HttpServletRequest request,
			HttpServletResponse response, Model model, CreditorReleaseView creditorReleaseView){
		String result = creditorReleaseManager.confirmTransferOut(creditorReleaseView);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("result", result);
		return jsonMapper.toJson(resultMap); 
	}
	
	/**
	 * 检测能否 结清
	 * @author xurongsheng
	 * @date 2017年3月22日 上午11:35:08
	 * @param request
	 * @param response
	 * @param model
	 * @param creditorReleaseView
	 * @return
	 */
	@RequestMapping(value = "checkEarlySettlement")
	@ResponseBody
	public String checkEarlySettlement(HttpServletRequest request,
			HttpServletResponse response, Model model, CreditorReleaseView creditorReleaseView){
		Map<String, Object> resultMap = creditorReleaseManager.checkEarlySettlement(creditorReleaseView);
		resultMap.put("result", "success");
		return jsonMapper.toJson(resultMap); 
	}
	
	/**
	 * 结清
	 * @author xurongsheng
	 * @date 2017年3月22日 上午11:35:38
	 * @param request
	 * @param response
	 * @param model
	 * @param creditorReleaseView
	 * @return
	 */
	@RequestMapping(value = "earlySettlement")
	@ResponseBody
	public String earlySettlement(HttpServletRequest request,
			HttpServletResponse response, Model model, CreditorReleaseView creditorReleaseView){
		String result = creditorReleaseManager.earlySettlement(creditorReleaseView);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("result", result);
		return jsonMapper.toJson(resultMap); 
	}
	
}
