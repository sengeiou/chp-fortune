package com.creditharmony.fortune.back.interest.apply.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.creditharmony.core.web.BaseController;
import com.creditharmony.fortune.back.interest.apply.facade.BackInterestApplyForEachManager;
import com.creditharmony.fortune.back.interest.apply.service.BackInterestApplyManager;
import com.creditharmony.fortune.back.interest.common.service.BackInterestCommonService;
import com.creditharmony.fortune.back.interest.entity.BackInterestPool;
import com.creditharmony.fortune.back.interest.entity.DetailsPage;
import com.creditharmony.fortune.back.interest.entity.SearchObject;
import com.creditharmony.fortune.back.interest.view.BackInterestBeanView;
import com.creditharmony.fortune.utils.FortuneDictUtil;

/**
 * 回息申请
 * @Class Name BackInterestApplyController 
 * @author 李志伟
 * @Create In 2015年12月2日
 */
@Controller
@RequestMapping("${adminPath}/backInterestApply/")
public class BackInterestApplyController extends BaseController{
	
	@Autowired
	private BackInterestApplyManager backInterestApplyManager;
	@Autowired
	private BackInterestCommonService backInterestCommonService;
	@Autowired
	private BackInterestApplyForEachManager backInterestApplyForEachManager;
	
	/**
	 * 回息申请列表初期表示
	 * 2016年1月5日
	 * by 李志伟
	 * @param so
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "loadBackInterestApplylist")
	public String loadBackInterestApplylist(SearchObject so, HttpServletRequest request,
			HttpServletResponse response, Model model){
		
		BackInterestBeanView bibv = backInterestApplyManager.loadBackInterestApplylist(so, request, response);
		FortuneDictUtil.addDicts(model, new String[]{"tz_bill_day","tz_pay_type","com_card_type","tz_backsms_state",
			"tz_open_bank","tz_contract_vesion"});
		
		model.addAttribute("bibv", bibv);
		return "backInterest/apply/backInterestApplyList";
	}
	
	/**
	 * 批量回息申请
	 * 2016年1月5日
	 * by 李志伟
	 * @param inst
	 * @param so
	 * @return
	 */
	@RequestMapping(value = "batchApply")
	@ResponseBody
	public String assemblyCondition(BackInterestPool bip, SearchObject so){
		
		String mesg = backInterestApplyForEachManager.assemblyCondition(bip, so);
		return jsonMapper.toJson(mesg);
	}
	
	/**
	 * 进入回息申请详情页
	 * 2016年1月5日
	 * by 李志伟
	 * @param backiId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "toApply")
	public String toApply(String backiId, Model model){
		
		DetailsPage dtp = backInterestCommonService.findMesgByCode(backiId);
		model.addAttribute("lendCode", dtp.getLendCode());
		model.addAttribute("dtp", dtp);
		return "backInterest/apply/backInterestApply";
	}
	
	/**
	 * 提交回息申请
	 * 2016年1月5日
	 * by 李志伟
	 * @param dp
	 * @return
	 */
	@RequestMapping(value = "toSubmit")
	@ResponseBody
	public String toSubmit(DetailsPage dp){
		
		String s = backInterestCommonService.getPlatFlag(dp.getBackiId());
		dp.setPlatFlag(s);
		String mesg = backInterestApplyManager.toSubmit(dp);
		return jsonMapper.toJson(mesg);
	}
	
	/**
	 * 待回息申请列表导出
	 * 2016年1月5日
	 * by 李志伟
	 * @param resp
	 * @param req
	 * @param so
	 */
	@RequestMapping(value = "exportExl")
	public void exportExl(HttpServletResponse resp, HttpServletRequest req, SearchObject so){
		
		backInterestApplyManager.exportExl(resp, req, so);
	}
	
	/**
	 * 待回息申请列表待金账户回息明细
	 * 2016年1月11日
	 * by 李志伟
	 * @param resp
	 * @param req
	 * @param so
	 */
	@RequestMapping(value = "goldAccountExportExl")
	public void goldAccountExportExl(HttpServletResponse resp, HttpServletRequest req, SearchObject so){
		
		backInterestApplyManager.goldAccountExportExl(resp, req, so);
	}
}