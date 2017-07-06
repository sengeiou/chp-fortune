package com.creditharmony.fortune.back.interest.approval.web;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.web.BaseController;
import com.creditharmony.fortune.back.interest.approval.facade.BackInterestApprovalForEachManager;
import com.creditharmony.fortune.back.interest.approval.service.BackInterestApprovalManager;
import com.creditharmony.fortune.back.interest.common.service.BackInterestCommonService;
import com.creditharmony.fortune.back.interest.confrim.facade.BackInterestConfrimForEachManager;
import com.creditharmony.fortune.back.interest.entity.BackInterestPool;
import com.creditharmony.fortune.back.interest.entity.DetailsPage;
import com.creditharmony.fortune.back.interest.entity.SearchObject;
import com.creditharmony.fortune.back.interest.util.StaticMethodUtil;
import com.creditharmony.fortune.back.interest.view.BackInterestBeanView;
import com.creditharmony.fortune.common.entity.ReturnMsg;
import com.creditharmony.fortune.utils.FortuneDictUtil;

/**
 * 回息审批
 * @Class Name BackInterestApplyCofrimController 
 * @author 李志伟
 * @Create In 2015年12月2日
 */
@Controller
@RequestMapping("${adminPath}/backInterestApproval/")
public class BackInterestApprovalController extends BaseController {

	@Autowired
	private BackInterestApprovalManager backInterestApprovalManager;
	@Autowired
	private BackInterestCommonService backInterestCommonService;
	@Autowired
	private BackInterestApprovalForEachManager backInterestApprovalForEach;
	@Autowired
	private BackInterestConfrimForEachManager backInterestConfrimForEachManager;
	
	/**
	 * 回息审批列表初始化
	 * 2016年1月4日
	 * by 李志伟
	 * @param model
	 * @param so
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = { "loadBackInterestApprovalList", ""})
	public String loadBackInterestApprovalList(Model model, SearchObject so, HttpServletRequest request, HttpServletResponse response) {
		BackInterestBeanView bibv = backInterestApprovalManager.loadBackInterestApplylist(so, request, response);
		FortuneDictUtil.addDicts(model, new String[]{"tz_bill_day","tz_pay_type","com_card_type","tz_backsms_state",
		"tz_open_bank", "tz_contract_vesion"});
		
		model.addAttribute("bibv", bibv);
		return "backInterest/approval/backInterestApprovalList";
	}
	
	/**
	 * 待回息审批列表批量回息审批
	 * 2016年1月21日
	 * by 李志伟
	 * @param state
	 * @param bil
	 * @return
	 */
	@RequestMapping(value="batchApproval")
	@ResponseBody
	public String batchApproval(BackInterestPool bip, SearchObject so){
	
		String errorMesg = backInterestApprovalForEach.bacthApproval(bip, so);
		return jsonMapper.toJson(errorMesg);
	}
	
	/**
	 * 去回息审批详情页
	 * 2015年12月2日
	 * by 李志伟
	 * @param code 回息ID
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "goConfirm")
	public String goConfirmPage(String code, Model model) {
 
		DetailsPage dtp = backInterestCommonService.findMesgByCode(code);
		model.addAttribute("lendCode", dtp.getLendCode());
		model.addAttribute("dtp", dtp);
		return "backInterest/approval/backInterestApproval";
	}

	/**
	 * 提交回息审批
	 * 2015年12月2日
	 * by 李志伟
	 * @param de 回息信息对象
	 * @return
	 */
	@RequestMapping(value = "goSubmit")
	@ResponseBody
	public String goSubmit(BackInterestPool de) {
		String mesg = backInterestApprovalManager.goSubmit(de);
		return jsonMapper.toJson(mesg);
	}
	
	/**
	 * 待回息审批列表导出
	 * 2016年1月8日
	 * by 李志伟
	 * @param resp
	 * @param req
	 */
	@RequestMapping(value = "exportExl")
	public void exportExl(HttpServletResponse resp, HttpServletRequest req, SearchObject so){
		
		backInterestApprovalManager.exportExl(resp, req, so);
	}
	
	/**
	 * 查询存在提前赎回，且已回款完成的数据的出借编号
	 * 2017年2月27日
	 * @param so
	 * @param resp
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "searchAheadBackid")
	@ResponseBody
	public String  searchAheadBackid(SearchObject so,HttpServletResponse resp, HttpServletRequest req){
		Map<String,Object> map = new  HashMap<String,Object>();
		map.put("so", so);
		map.put("status", StaticMethodUtil.getBackInterestApprovalStatus());
		map.put("codes", Arrays.asList(so.getBackiId().split(",")));
		String message=  backInterestConfrimForEachManager.searchAheadBackids(map);
		return jsonMapper.toJson(new ReturnMsg(message));
	}
	
	/**
	 * 查询存在提前赎回，且已回款完成的数据的出借编号
	 * 2017年3月1日
	 * @param so
	 * @param resp
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "searchAheadBackidsFrom")
	@ResponseBody
	public String  searchAheadBackidsFrom(SearchObject so,HttpServletResponse resp, HttpServletRequest req){
		Map<String,Object> map = new  HashMap<String,Object>();
		StaticMethodUtil.getCondition(so, map, StaticMethodUtil.getBackInterestApprovalStatus());
		String city = so.getAddrCity();
		if (StringUtils.isNotEmpty(city)) {
			String c = "%" + city.replace(",", "%|%") +"%";
			so.setAddrCity(c);
		}
		map.put("so",so);
		String message=  backInterestConfrimForEachManager.searchAheadBackidsFrom(map);
		return jsonMapper.toJson(new ReturnMsg(message));
	}
}