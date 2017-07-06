package com.creditharmony.fortune.back.interest.apply.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.creditharmony.core.web.BaseController;
import com.creditharmony.fortune.back.interest.apply.facade.BackInterestApplyForEachManager;
import com.creditharmony.fortune.back.interest.apply.facade.BackReturnInterestApplyForEachManager;
import com.creditharmony.fortune.back.interest.apply.service.BackInterestApplyManager;
import com.creditharmony.fortune.back.interest.apply.service.BackReturnInterestApplyManager;
import com.creditharmony.fortune.back.interest.common.service.BackInterestCommonService;
import com.creditharmony.fortune.back.interest.common.service.BackReturnInterestCommonService;
import com.creditharmony.fortune.back.interest.entity.BackInterestPool;
import com.creditharmony.fortune.back.interest.entity.DetailsPage;
import com.creditharmony.fortune.back.interest.entity.SearchObject;
import com.creditharmony.fortune.back.interest.view.BackInterestBeanView;
import com.creditharmony.fortune.utils.FortuneDictUtil;

/**
 * 回息申请
 * @Class Name BackInterestApplyController 
 * @author 高旭
 * @Create In 2017-3-23 10:30:34
 */
@Controller
@RequestMapping("${adminPath}/backReturnInterestApply/")
public class BackReturnInterestApplyController extends BaseController{
	
	@Autowired
	private BackReturnInterestApplyManager backReturnInterestApplyManager;
	@Autowired
	private BackReturnInterestCommonService bckReturnInterestCommonService;
	@Autowired
	private BackReturnInterestApplyForEachManager backReturnInterestApplyForEachManager;
	
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
	@RequestMapping(value = "loadBackReturnInterestApplylist")
	public String loadBackInterestApplylist(SearchObject so, HttpServletRequest request,
			HttpServletResponse response, Model model){
		
		BackInterestBeanView bibv = backReturnInterestApplyManager.loadBackInterestApplylist(so, request, response);
		FortuneDictUtil.addDicts(model, new String[]{"tz_bill_day","tz_pay_type","com_card_type","tz_backsms_state",
			"tz_open_bank","tz_contract_vesion","tz_back_interest_return"});
		
		model.addAttribute("bibv", bibv);
		return "backInterest/apply/backReturnInterestApplyList";
	}
	
	/**
	 * 批量回息申请
	 * 2016年1月5日
	 * by 李志伟
	 * @param inst
	 * @param so
	 * @return
	 */
	@RequestMapping(value = "batchReturnApply")
	@ResponseBody
	public String assemblyCondition(BackInterestPool bip, SearchObject so){
		
		String mesg = backReturnInterestApplyForEachManager.assemblyCondition(bip, so);
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
	@RequestMapping(value = "toReturnApply")
	public String toApply(String backiId, Model model){
		
		DetailsPage dtp = bckReturnInterestCommonService.findMesgByCode(backiId);
		model.addAttribute("lendCode", dtp.getLendCode());
		model.addAttribute("dtp", dtp);
		return "backInterest/apply/backReturnInterestApply";
	}
	
	/**
	 * 提交回息申请
	 * 2016年1月5日
	 * by 李志伟
	 * @param dp
	 * @return
	 */
	@RequestMapping(value = "toReturnSubmit")
	@ResponseBody
	public String toSubmit(DetailsPage dp){
		
		String s = bckReturnInterestCommonService.getPlatFlag(dp.getBackiId());
		dp.setPlatFlag(s);
		String mesg = backReturnInterestApplyManager.toSubmit(dp);
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
	@RequestMapping(value = "exportReturnExl")
	public void exportExl(HttpServletResponse resp, HttpServletRequest req, SearchObject so){
		
		backReturnInterestApplyManager.exportExl(resp, req, so);
	}
	
	/**
	 * 待回息申请列表待金账户回息明细
	 * 2016年1月11日
	 * by 李志伟
	 * @param resp
	 * @param req
	 * @param so
	 */
	@RequestMapping(value = "goldAccountExportReturnExl")
	public void goldAccountExportExl(HttpServletResponse resp, HttpServletRequest req, SearchObject so){
		
		backReturnInterestApplyManager.goldAccountExportExl(resp, req, so);
	}
	
	
	/**
	 * 弹出上传是否回息窗口
	 * 高旭
	 * by 2017-3-24 10:22:22
	 * 	 * @return
	 */
	@RequestMapping(value = "uploadIsInterestReturnWindow")
	public String uploadMoneyWindow(){
		return "backInterest/apply/uploadIsInterestReturnWindow";
	}
	
	/**
	 * 上传并修改是否回息
	 * 2017-3-24 10:26:18
	 * by 高旭
	 * @param files
	 * @param m
	 * @return
	 */
	@RequestMapping(value = "uploadIsInterestReturn")
	@ResponseBody
	public String uploadIsInterestReturn(MultipartHttpServletRequest files, Model m) {
		
		String message = backReturnInterestApplyManager.uploadIsInterestReturn(files);
		return message;
	}
}