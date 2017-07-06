package com.creditharmony.fortune.change.lender.finish.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.web.BaseController;
import com.creditharmony.fortune.change.lender.apply.entity.ext.CustomerEx;
import com.creditharmony.fortune.change.lender.apply.view.LenderQueryView;
import com.creditharmony.fortune.change.lender.finish.service.LenderChangeFinishManager;
import com.creditharmony.fortune.utils.FortuneDictUtil;

/**
 * 出借人信息变更已办控制类
 * 
 * @Class Name LenderChangeFinishController
 * @author 郭才林
 * @Create In 2016年4月13日
 */
@Controller
@RequestMapping("${adminPath}/lenderChange/finish")
public class LenderChangeFinishController extends BaseController {

	@Autowired
	private LenderChangeFinishManager finishManager;

	/**
	 * 获取已办 2015年12月2日 By 郭才林
	 * 
	 * @param query
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping({ "list" })
	public String list(LenderQueryView query, Model model,
			HttpServletRequest request, HttpServletResponse response) {

		Page<CustomerEx> page = finishManager.getLenderChangeFinish(
				new Page<CustomerEx>(request, response), query);
		FortuneDictUtil.addDicts(model, new String[] { "tz_customer_src" });
		model.addAttribute("page", page).addAttribute("query", query);
		return "/lenderChange/lenderChangFinish";
	}

}
