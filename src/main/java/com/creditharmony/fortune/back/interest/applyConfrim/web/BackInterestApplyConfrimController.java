package com.creditharmony.fortune.back.interest.applyConfrim.web;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.creditharmony.core.web.BaseController;
import com.creditharmony.fortune.back.interest.apply.service.BackInterestApplyManager;
import com.creditharmony.fortune.back.interest.applyConfrim.facade.BackInterestApplyConfrimForEachManager;
import com.creditharmony.fortune.back.interest.applyConfrim.service.BackInterestApplyConfrimManager;
import com.creditharmony.fortune.back.interest.common.service.BackInterestCommonService;
import com.creditharmony.fortune.back.interest.entity.BackInterestPool;
import com.creditharmony.fortune.back.interest.entity.DetailsPage;
import com.creditharmony.fortune.back.interest.entity.SearchObject;
import com.creditharmony.fortune.back.interest.view.BackInterestBeanView;
import com.creditharmony.fortune.utils.FortuneDictUtil;

/**
 * 回息申请确认
 * @Class Name BackInterestApplyCofrimController 
 * @author 李志伟
 * @Create In 2015年12月2日
 */
@Controller
@RequestMapping("${adminPath}/backInterestApplyConfrim/")
public class BackInterestApplyConfrimController extends BaseController {

	@Autowired
	private BackInterestApplyConfrimManager backInterestApplyConfrimManager;
	@Autowired
	private BackInterestApplyManager backInterestApplyManager;
	@Autowired
	private BackInterestCommonService backInterestCommonService;
	@Autowired
	private BackInterestApplyConfrimForEachManager backInterestApplyConfrimForEachManager;
	
	/**
	 * 回息申请确认列表搜索
	 * 2016年1月5日
	 * by 李志伟
	 * @param so
	 * @param request
	 * @param response
	 * @param m
	 * @return
	 */
	@RequestMapping(value = "loadBackInterestApplyConfrimList")
	public String loadBackInterestApplyConfrimList(SearchObject so, HttpServletRequest request,
			HttpServletResponse response, Model m) {
		
		BackInterestBeanView bibv = backInterestApplyConfrimManager.loadBackInterestApplyConfrimList(so, request, response);
		FortuneDictUtil.addDicts(m, new String[]{"tz_bill_day","tz_pay_type","com_card_type","tz_backsms_state",
		"tz_open_bank", "tz_contract_vesion"});
		
		// purl和surl用于批量操作的提交地址
		m.addAttribute("bibv", bibv);
		return "backInterest/applyConfrim/backInterestApplyConfrimList";
	}
	
	/**
	 * 待回息申请确认列表批量回息确认
	 * 2016年1月5日
	 * by 李志伟
	 * @param bip
	 * @param so
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "batchApplyConfirm")
	@ResponseBody
	public String batchApplyConfirm(BackInterestPool bip, SearchObject so) throws UnsupportedEncodingException{
		
		String errorMesg = backInterestApplyConfrimForEachManager.bacthApplyConfrim(bip, so);
        return jsonMapper.toJson(errorMesg);
	}
	
	/**
	 * 待回息申请确认列表导出
	 * 2016年1月5日
	 * by 李志伟
	 * @param resp
	 * @param req
	 * @param so
	 */
	@RequestMapping(value = "exportExl")
	public void exportExl(HttpServletResponse resp, HttpServletRequest req, SearchObject so){
		
		backInterestApplyConfrimManager.exportExl(resp, req, so);
	}
	
	/**
	 * 待回息申请确认列表待金账户回息明细
	 * 2016年1月11日
	 * by 李志伟
	 * @param resp
	 * @param req
	 * @param so
	 */
	@RequestMapping(value = "goldAccountExportExl")
	public void goldAccountExportExl(HttpServletResponse resp, HttpServletRequest req, SearchObject so){
		backInterestApplyConfrimManager.goldAccountExportExl(resp, req, so);
	}
	
	/**
	 * 弹出上传回息金额窗口
	 * 2016年1月20日
	 * by 李志伟
	 * @return
	 */
	@RequestMapping(value = "uploadMoneyWindow")
	public String uploadMoneyWindow(){
		return "backInterest/applyConfrim/uploadMoneyWindow";
	}
	
	/**
	 * 上传并修改回息金额
	 * 2016年1月20日
	 * by 李志伟
	 * @param files
	 * @param m
	 * @return
	 */
	@RequestMapping(value = "uploadMoney")
	@ResponseBody
	public String uploadMoney(MultipartHttpServletRequest files, Model m) {
		
		String message = backInterestApplyConfrimManager.uploadMoney(files);
		return message;
	}
	
	/**
	 * 去回息申请确认详情页
	 * 2015年12月2日
	 * by 李志伟
	 * @param code 回息Id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "goConfrim")
	public String goConfrimPage(String code, Model model) {

		DetailsPage dtp = backInterestCommonService.findMesgByCode(code);
		model.addAttribute("lendCode", dtp.getLendCode());
		model.addAttribute("dtp", dtp);
		return "backInterest/applyConfrim/backInterestApplyConfrim";
	}

	/**
	 * 提交回息申请确认
	 * 2015年12月2日
	 * by 李志伟
	 * @param de 回息信息对象
	 * @return
	 */
	@RequestMapping(value = "goSubmit")
	@ResponseBody
	public String goSubmit(BackInterestPool de){
		
		String mesg = backInterestApplyConfrimManager.goSubmit(de);
		return jsonMapper.toJson(mesg);
	}
}