
package com.creditharmony.fortune.creditor.matching.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.web.BaseController;
import com.creditharmony.fortune.creditor.matching.entity.ext.CreditorTradeEx;
import com.creditharmony.fortune.creditor.matching.service.MatchingCreditorHistoryManager;
import com.creditharmony.fortune.utils.FortuneDictUtil;

/**
 * 已推荐债权查看
 * @Class Name CreditorSendHistoryController
 * @author 胡体勇
 * @Create In 2015年12月10日
 */
@Controller
@RequestMapping(value = "${adminPath}/creditor/matchingCreditorHistory")
public class MatchingCreditorHistoryController extends BaseController {
	
	@Autowired
	private MatchingCreditorHistoryManager matchingCreditorHistoryManager;
	
	/**
	 * 已推荐债权列表
	 * 2015年12月10日
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
		// 分页查询
		Page<CreditorTradeEx> page = new Page<CreditorTradeEx>(request,response);
		page = matchingCreditorHistoryManager.findPage(page, creditorTradeEx);
		// 设置页面列表显示
		model.addAttribute("page", page);
		model.addAttribute("creditorTradeEx",creditorTradeEx);
		// 获取页面字典项值
		String[] types = {"tz_pay_type","tz_bill_state","tz_matching_status","tz_bill_day"};
		FortuneDictUtil.addDicts(model, types);
		return "/creditor/matchingCreditorHistoryList";
	}

}
