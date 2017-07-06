package com.creditharmony.fortune.back.interest.excute.web;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.web.BaseController;
import com.creditharmony.fortune.back.interest.confrim.facade.BackInterestConfrimForEachManager;
import com.creditharmony.fortune.back.interest.entity.BackInterestPool;
import com.creditharmony.fortune.back.interest.entity.SearchObject;
import com.creditharmony.fortune.back.interest.excute.facade.ExcuteBackInterestForEachManager;
import com.creditharmony.fortune.back.interest.excute.service.ExcuteBackInterestManager;
import com.creditharmony.fortune.back.interest.util.StaticMethodUtil;
import com.creditharmony.fortune.back.interest.view.BackInterestBeanView;
import com.creditharmony.fortune.common.entity.ReturnMsg;
import com.creditharmony.fortune.utils.FortuneDictUtil;

/**
 * 执行回息
 * @Class Name ExcuteBackInterestController 
 * @author 李志伟
 * @Create In 2015年12月3日
 */
@Controller
@RequestMapping("${adminPath}/excuteBackInterest/")
public class ExcuteBackInterestController extends BaseController {

	@Autowired
	private ExcuteBackInterestManager excuteBackInterestManager;
	@Autowired
	private ExcuteBackInterestForEachManager excuteBackInterestForEachManager;
	@Autowired
	private BackInterestConfrimForEachManager backInterestConfrimForEachManager;
	
	/**
	 * 初始化执行回息列表
	 * 2016年1月5日
	 * by 李志伟
	 * @param so
	 * @param m
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "loadExcuteBackInterestList")
	public String loadExcuteBackInterestList(SearchObject so, Model m, HttpServletRequest request, HttpServletResponse response) {

		BackInterestBeanView bibv = excuteBackInterestManager.loadExcuteBackInterestList(so, request, response);
		FortuneDictUtil.addDicts(m, new String[]{"tz_bill_day","tz_pay_type","com_card_type","tz_backsms_state",
		"tz_open_bank","tz_backInterest_plat", "tz_contract_vesion"});
		
		/* 此处model主要是为了辨别所在页面所需要的数据(如执行回息列表中的批量执行回息，点击通过需要显示中间人
		 * 信息，但是所有列表通用一个页面，为了保证其他页面的显示，所以要页面model一个标记。用来分别是否需要显示
		 * 中间人信息
		 */
		m.addAttribute("sl", "sl").addAttribute("bibv", bibv);
		return "backInterest/excute/excuteBackInterestList";
	}
	
	/**
	 * 批量执行回息
	 * 2016年1月20日
	 * by 李志伟
	 * @param bip
	 * @param so
	 * @return
	 */
	@RequestMapping(value="batchExcute")
	@ResponseBody
	public String batchApproval(BackInterestPool bip, SearchObject so){
	
		String mesg = excuteBackInterestForEachManager.bacthExcete(bip, so);
		return jsonMapper.toJson(mesg);
	}

	/**
	 * 去执行回息审核页
	 * 2015年12月2日
	 * by 李志伟
	 * @param code 回息编号
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "goExecuteBackInterestPage")
	public String goExecuteBackInterestPage(String code, Model model) {

		BackInterestBeanView bibv = excuteBackInterestManager.goExcuteBackInterestPage(code);
		FortuneDictUtil.addDicts(model, new String[]{"tz_open_bank"});
		model.addAttribute("bibv", bibv).addAttribute("lendCode", bibv.getDetailsPage().getLendCode());
		return "backInterest/excute/excuteBackInterest";
	}

	/**
	 * 提交执行回息审批
	 * 2015年12月2日
	 * by 李志伟
	 * @param bip 回息池对象
	 * @return
	 */
	@RequestMapping(value = "excuteOperation")
	@ResponseBody
	public String excuteOperation(BackInterestPool bip) {
		
		String mesg = excuteBackInterestManager.goSubmit(bip);
		return jsonMapper.toJson(mesg);	
	}
	
	/**
	 * 线上回息弹出窗口
	 * 2016年1月13日
	 * by 李志伟
	 * @param req
	 * @param m
	 * @return
	 */
	@RequestMapping(value = "onLineBackInterestWinow")
	public String onLineBackInterestWinow(HttpServletRequest req, Model m){
		
		String flag = req.getParameter("flag");
		m.addAttribute("flag", flag);
		FortuneDictUtil.addDicts(m, new String[]{"tz_backInterest_plat"});
		return "backInterest/excute/onLineBackInterestWindow";
	}
	
	/**
	 * 线上回息提交
	 * 2016年1月21日
	 * by 李志伟
	 * @param so
	 * @param p
	 * @return
	 */
	@RequestMapping(value = "lineComit")
	@ResponseBody
	public String lineComit(SearchObject so, BackInterestPool p){
		
		String message = excuteBackInterestManager.getLineBackInterestData(so, p);
		return jsonMapper.toJson(message);
	}
	
	/**
	 * 线下回息弹出窗口
	 * 2016年1月13日
	 * by 李志伟
	 * @param m
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "lineBackInterestWinow")
	public String lineBackInterestWinow(Model m, HttpServletRequest req){
		String flag = req.getParameter("flag");
		FortuneDictUtil.addDicts(m, new String[]{"tz_backInterest_plat"});
		m.addAttribute("flag", flag);
		return "backInterest/excute/lineBackInterestWindow";
	}
	
	/**
	 * 上传文件
	 * 2016年1月21日
	 * by 李志伟
	 * @param files
	 * @param req
	 * @param rep
	 * @return
	 */
	@RequestMapping(value = "uploadFile")
	@ResponseBody
	public String uploadFile(MultipartHttpServletRequest file, HttpServletRequest req, HttpServletResponse rep){
		
		String message = excuteBackInterestManager.uploadFile(file, req, rep);
		return message;
	}
	
	/**
	 * 线下导出
	 * 2016年1月21日
	 * by 李志伟
	 * @param so
	 * @param m
	 * @param req
	 * @param resp
	 */
	@RequestMapping(value = "lineExport")
	public void lineExport(SearchObject so, HttpServletRequest req, HttpServletResponse resp){
		excuteBackInterestManager.export(so, req, resp);
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
		map.put("status", StaticMethodUtil.getExcuteBackInterestStatus());
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
		StaticMethodUtil.getCondition(so, map, StaticMethodUtil.getExcuteBackInterestStatus());
		List<String> payType = StaticMethodUtil.getPayType(so.getTrusteeshipFlag());
		map.put("payType", payType);
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