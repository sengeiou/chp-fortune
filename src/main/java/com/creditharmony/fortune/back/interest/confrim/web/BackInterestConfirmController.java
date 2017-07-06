package com.creditharmony.fortune.back.interest.confrim.web;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.mapper.JsonMapper;
import com.creditharmony.core.web.BaseController;
import com.creditharmony.fortune.back.interest.applyConfrim.facade.BackInterestApplyConfrimForEachManager;
import com.creditharmony.fortune.back.interest.common.service.BackInterestCommonService;
import com.creditharmony.fortune.back.interest.confrim.facade.BackInterestConfrimForEachManager;
import com.creditharmony.fortune.back.interest.confrim.service.BackInterestConfrimManager;
import com.creditharmony.fortune.back.interest.entity.BackInterestPool;
import com.creditharmony.fortune.back.interest.entity.DetailsPage;
import com.creditharmony.fortune.back.interest.entity.PlatformMsg;
import com.creditharmony.fortune.back.interest.entity.SearchObject;
import com.creditharmony.fortune.back.interest.util.StaticMethodUtil;
import com.creditharmony.fortune.back.interest.view.BackInterestBeanView;
import com.creditharmony.fortune.common.entity.ReturnMsg;
import com.creditharmony.fortune.utils.FortuneDictUtil;

/**
 * 回息确认
 * @Class Name BackInterestConfrimController 
 * @author 李志伟
 * @Create In 2015年12月23日
 */
@Controller
@RequestMapping("${adminPath}/backInterestConfrim/")
public class BackInterestConfirmController extends BaseController {

	/**
	 * 日志对象
	 */
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private BackInterestConfrimManager backInterestConfrimManager;
	@Autowired
	private BackInterestConfrimForEachManager backInterestConfrimForEachManager;
	@Autowired
	private BackInterestCommonService backInterestCommonService;
	@Autowired
	private BackInterestApplyConfrimForEachManager backInterestApplyConfrimForEachManager;
	
	/**
	 * 回息确认列表搜索
	 * 2016年1月5日
	 * by 李志伟
	 * @param model
	 * @param so
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "loadBackInterestConfrimList")
	public String loadBackInterestConfrimList(Model model, SearchObject so, HttpServletRequest request, HttpServletResponse response) {
		
		BackInterestBeanView bibv =  backInterestConfrimManager.loadBackInterestConfrimList(so, request, response);
		FortuneDictUtil.addDicts(model, new String[]{"tz_bill_day","tz_pay_type","com_card_type","tz_backsms_state",
				"tz_open_bank","plat_flag","tz_back_result","tz_backInterest_plat", "tz_contract_vesion"});
		
		model.addAttribute("bibv", bibv);
		return "backInterest/confrim/backInterestConfrimList";
	}
	
	/**
	 * 回息确认列表批量回息确认
	 * 2016年1月5日
	 * by 李志伟
	 * @param bip 接收审批信息以及回息信息
	 * @param so 检索条件
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value="batchConfrim")
	@ResponseBody
	public String batchConfrim(BackInterestPool bip, SearchObject so) throws UnsupportedEncodingException{
	
		String errorMesg = backInterestConfrimForEachManager.bacthConfrim(bip, so);
		return jsonMapper.toJson(errorMesg);
	}
	
	/**
	 * 去回息确认详情页
	 * 2015年12月23日
	 * by 李志伟
	 * @param code
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "goConfrimPage")
	public String goConfrimPage(String code, Model model) {

		DetailsPage dtp = backInterestCommonService.findMesgByCode(code);
		model.addAttribute("dtp", dtp).addAttribute("lendCode", dtp.getLendCode());
		return "backInterest/confrim/backInterestConfrim";
	}

	/**
	 * 提交回息确认
	 * 2015年12月2日
	 * by 李志伟
	 * @param bip
	 * @return
	 */
	@RequestMapping(value = "goSubmit")
	@ResponseBody
	public String goSubmit(BackInterestPool bip) {
		
		String mesg = backInterestConfrimManager.goSubmit(bip);
		return jsonMapper.toJson(mesg);
	}
	
	/**
	 * 退回至待回息列表
	 * 2016年4月1日
	 * by 李志伟
	 * @param pool
	 */
	@RequestMapping(value = "backOne")
	@ResponseBody
	public String backOne(BackInterestPool pool){
		
		String mesg = backInterestConfrimManager.backOne(pool);
		return jsonMapper.toJson(mesg);
	}
	
	/**
	 * 待回息确认列表导出
	 * 2016年1月8日
	 * by 李志伟
	 * @param so
	 * @param resp
	 * @param req
	 */
	@RequestMapping(value = "exportExl")
	public void exportExl(SearchObject so, HttpServletResponse resp, HttpServletRequest req){
		
		backInterestConfrimManager.exportExl(so, resp, req);
	}
	
	/**
	 * 退回至执行回息列表
	 * 2016年2月18日
	 * by 李志伟
	 * @param so
	 * @param bip
	 * @return
	 */
	@RequestMapping(value = "returnExcuteInterest")
	@ResponseBody
	public String returnExcuteInterest(SearchObject so, BackInterestPool bip){
		
		String errorMesg = backInterestConfrimForEachManager.returnExcuteInterest(so, bip);
		return jsonMapper.toJson(errorMesg);
	}
	
	/**
	 * 待回息确认列表上传回盘结果
	 * 2016年2月25日 
	 * by 李志伟
	 * @param files
	 * @param m
	 * @return
	 */
	@RequestMapping(value = "uploadResult")
	@ResponseBody
	public String uploadResult(MultipartHttpServletRequest files, Model m){
		
		String rtnMsg = backInterestConfrimManager.uploadResult(files);
		return rtnMsg;
	}
	
	/**
	 * 查找回息确认列表中回盘结果为处理中的数据条数
	 * 2016年4月1日
	 * by 李志伟
	 * @param so
	 * @return
	 */
	@RequestMapping(value = "findBackResult")
	@ResponseBody
	public String findBackResult(BackInterestPool bip, SearchObject so){
		int count = backInterestCommonService.findBackResult(bip,so);
		return JsonMapper.toJsonString(count);
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
		map.put("status", StaticMethodUtil.getBackInterestConfrimStatus());
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
		StaticMethodUtil.getCondition(so, map, StaticMethodUtil.getBackInterestConfrimStatus());
		String city = so.getAddrCity();
		if (StringUtils.isNotEmpty(city)) {
			String c = "%" + city.replace(",", "%|%") +"%";
			so.setAddrCity(c);
		}
		map.put("so",so);
		String message=  backInterestConfrimForEachManager.searchAheadBackidsFrom(map);
		return jsonMapper.toJson(new ReturnMsg(message));
	}
	
	/**
	 * 弹出批量操作窗口
	 * 2017年3月6日
	 * @param m
	 * @return
	 */
	@RequestMapping(value = "batchWindow")
	public String batchWindow(Model m, SearchObject so){
		
		List<PlatformMsg> list = backInterestCommonService.searchThirdPlat();
		FortuneDictUtil.addDicts(m, new String[] { "tz_open_bank",
				"tz_backsms_reason" });
		m.addAttribute("list", list);
		// 弹窗中显示所选数据的最大回款日期
		Date hMaxDate = backInterestCommonService.getMaxBackMoneyDay(so);
		m.addAttribute("hMaxDate", hMaxDate);
		m.addAttribute("backMoneyDay", hMaxDate);
		// m.addAttribute("backMoneyDay", new Date());

		// 获取数据不同回款日期的天数
		int days = backInterestCommonService.getDiffBackMoneyDays(so);
		m.addAttribute("diffDays", days);

		return "backInterest/common/branchBackInterestWindow";
	}
}