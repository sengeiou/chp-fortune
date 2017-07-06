package com.creditharmony.fortune.back.interest.finish.web;

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
import com.creditharmony.fortune.back.interest.common.service.BackInterestCommonService;
import com.creditharmony.fortune.back.interest.confrim.facade.BackInterestConfrimForEachManager;
import com.creditharmony.fortune.back.interest.entity.BackInterestPool;
import com.creditharmony.fortune.back.interest.entity.DetailsPage;
import com.creditharmony.fortune.back.interest.entity.SearchObject;
import com.creditharmony.fortune.back.interest.finish.facade.FinishBackInterestForEachManager;
import com.creditharmony.fortune.back.interest.finish.service.FinishBackInterestManager;
import com.creditharmony.fortune.back.interest.util.StaticMethodUtil;
import com.creditharmony.fortune.back.interest.view.BackInterestBeanView;
import com.creditharmony.fortune.common.entity.ReturnMsg;
import com.creditharmony.fortune.utils.FortuneDictUtil;

/**
 * 已回息
 * @Class Name FinishBackInterestController 
 * @author 李志伟
 * @Create In 2015年12月11日
 */
@Controller
@RequestMapping("${adminPath}/finishBackInterest/")
public class FinishBackInterestController extends BaseController {

	@Autowired
	private FinishBackInterestManager finishBackInterestManager;
	@Autowired
	private BackInterestCommonService backInterestCommonService;
	@Autowired
	private FinishBackInterestForEachManager finishBackInterestForEachManager;
	@Autowired
	private BackInterestConfrimForEachManager backInterestConfrimForEachManager;
	
	/**
	 * 已回息列表初始化
	 * 2016年1月5日
	 * by 李志伟
	 * @param model
	 * @param so
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = { "loadFinishBackInterestList", ""})
	public String loadFinishBackInterestList(Model model, SearchObject so, HttpServletRequest request, HttpServletResponse response) {
		
		BackInterestBeanView bibv = finishBackInterestManager.loadFinishBackInterestList(so, request, response);
		
		FortuneDictUtil.addDicts(model, new String[]{"tz_bill_day","tz_pay_type","com_card_type","tz_backsms_state",
				"tz_open_bank","plat_flag","tz_back_result","tz_backInterest_plat", "tz_contract_vesion","tz_back_interest_return"});
		
		model.addAttribute("bibv", bibv);
		return "backInterest/finish/finishBackInterestList";
	}

	/**
	 * 已回息列表导出回息明细
	 * 2016年1月7日
	 * by 李志伟
	 * @param resp
	 * @param req
	 * @param so
	 */
	@RequestMapping(value = "exportExl")
	public void exportExl(HttpServletResponse resp, HttpServletRequest req, SearchObject so){
		
		finishBackInterestManager.exportExl(resp, req, so);
	}
	
	/**
	 * 去已回息详情页
	 * 2015年12月2日
	 * by 李志伟
	 * @param backiId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "goFinishBackInterestPage")
	public String goFinishBackInterestPage(Model model, String backiId) {
		
		DetailsPage dtp = backInterestCommonService.findMesgByCode(backiId);
		model.addAttribute("dtp", dtp).addAttribute("lendCode", dtp.getLendCode());
		return "backInterest/finish/finishBackInterest";
	}
	
	/**
	 * 已回息批量退回
	 * 2016年1月25日
	 * by 李志伟
	 * @param bil
	 * @param so
	 * @return
	 */
	@RequestMapping(value = "branchReturn")
	@ResponseBody
	public String branchReturn(BackInterestPool bip, SearchObject so){
		
		String errorMesg = finishBackInterestForEachManager.batchReturn(bip, so);
		return jsonMapper.toJson(errorMesg);
	}
	
	/**
	 * 已回息退回
	 * 2016年1月25日
	 * by 李志伟
	 * @param bip
	 * @return
	 */
	@RequestMapping(value = "return")
	@ResponseBody
	public String back(BackInterestPool bip){
			
		String mesg = finishBackInterestManager.goBack(bip);
		return jsonMapper.toJson(mesg);
	}
	
	/**
	 * 已回息批量退回弹窗
	 * 2016年1月25日
	 * by 李志伟
	 * @param req
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "returnWindow")
	public String returnWindow(Model model){

		return "backInterest/finish/finishBackInterestReturnWindow";
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
		map.put("status", StaticMethodUtil.getFinishBackInterestStatus());
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
		StaticMethodUtil.getCondition(so, map, StaticMethodUtil.getFinishBackInterestStatus());
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