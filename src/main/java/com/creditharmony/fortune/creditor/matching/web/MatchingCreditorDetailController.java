
package com.creditharmony.fortune.creditor.matching.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.creditharmony.common.util.CookieUtils;
import com.creditharmony.core.config.Global;
import com.creditharmony.core.fortune.type.ProductCode;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.web.BaseController;
import com.creditharmony.fortune.common.entity.Check;
import com.creditharmony.fortune.common.service.CheckManager;
import com.creditharmony.fortune.creditor.matching.entity.ext.BorrowEx;
import com.creditharmony.fortune.creditor.matching.entity.ext.CreditorTradeEx;
import com.creditharmony.fortune.creditor.matching.service.MatchingCreditorDetailManager;
import com.creditharmony.fortune.creditor.matching.service.MatchingCreditorHistoryManager;
import com.creditharmony.fortune.utils.FortuneDictUtil;

/**
 * 已推荐债权查看列表页面点击办理按钮跳转页面及显示对应方法
 * @Class Name MatchingCreditorDetailController
 * @author 胡体勇
 * @Create In 2015年12月11日
 */
@Controller
@RequestMapping(value = "${adminPath}/creditor/matchingCreditorDetail")
public class MatchingCreditorDetailController extends BaseController {
	
	@Autowired
	private MatchingCreditorDetailManager matchingCreditorDetailManager;
	@Autowired
	private MatchingCreditorHistoryManager matchingCreditorHistoryManager;
	
	@Autowired
	private CheckManager checkManager;
	
	/**
	 * 已推荐债权查看
	 * 2016年1月15日
	 * By 胡体勇
	 * @param creditorTradeEx
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = {"list",""})
	public String findList(CreditorTradeEx creditorTradeEx, HttpServletRequest request,
			HttpServletResponse response, Model model){
		// 根据推荐id查询对应申请信息
		CreditorTradeEx creditor = matchingCreditorDetailManager.findloanInfoByMatchingId(creditorTradeEx);
		// 根据不同的节点去匹配不同的表，查询对应的数据
		List<BorrowEx> list = matchingCreditorDetailManager.joinTable(creditorTradeEx);
		// 设置页面显示信息borrow 0
		model.addAttribute("creditor",creditor);
		model.addAttribute("list",list);
		// 获取页面字典项值
		String[] types = {"jk_prof_type","tz_loan_distinguish"};
		FortuneDictUtil.addDicts(model, types);
		if(ProductCode.YMY.value.equals(creditor.getProductCodeStr())){
			return "/creditor/matchingCreditorBorrowableDetailList";
		}else{
			return "/creditor/matchingCreditorBorrowDetailList";
		}
	}
	
	/**
	 * 返回到主列表
	 * 2016年4月18日
	 * By 周俊
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("/backList")
	public String backList(HttpServletRequest request,HttpServletResponse response,Model model){
		CreditorTradeEx creditorTradeEx = new CreditorTradeEx();
		int pageNo = 0;
		int pageSize = 0 ; 
		 try {
			 pageNo = Integer.valueOf(CookieUtils.getCookie(request,"pageNo"));
		} catch (NumberFormatException e) {
			pageNo = 1;
		}
		try {
			pageSize = Integer.valueOf(CookieUtils.getCookie(request,"pageSize"));
		} catch (NumberFormatException e) {
			pageSize = Integer.valueOf(Global.getConfig("page.pageSize"));
		}
		Page<CreditorTradeEx> page = new Page<CreditorTradeEx>(pageNo, pageSize);
		page = matchingCreditorHistoryManager.findPage(page, creditorTradeEx);
		// 设置页面列表显示
		model.addAttribute("page", page);
		model.addAttribute("creditorTradeEx", creditorTradeEx);
		// 获取页面字典项值
		String[] types = {"tz_pay_type","tz_bill_state","tz_matching_status","tz_bill_day"};
		FortuneDictUtil.addDicts(model, types);
		return "/creditor/matchingCreditorHistoryList";
	}
	/**
	 * 撤销历史
	 * 2016年1月15日
	 * By 胡体勇
	 * @param creditorTradeEx
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "cancelHistory")
	public String cancelHistory(CreditorTradeEx creditorTradeEx,Model model){
		/*List<BorrowEx> list = matchingCreditorDetailManager.findPage(creditorTradeEx);*/
		Check check = new Check();
		check.setApplyCode(creditorTradeEx.getLendCode());
		check.setOperateAffiliated(creditorTradeEx.getMatchingId());
		List<Check> list = checkManager.findList(check);
		model.addAttribute("list",list);
		return "/creditor/cancelHistoryShowModalDialog";
	}
       
}
