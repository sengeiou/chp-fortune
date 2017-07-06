package com.creditharmony.fortune.change.lend.finish.web;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.creditharmony.core.fortune.type.LendchgType;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.web.BaseController;
import com.creditharmony.fortune.change.lend.apply.entity.LendLoanApply;
import com.creditharmony.fortune.change.lend.apply.entity.LendQueryView;
import com.creditharmony.fortune.change.lend.finish.manager.LendChangeFinishManager;
import com.creditharmony.fortune.utils.FortuneDictUtil;

/**
 * 出借信息修改Controller层
 * 
 * @Class Name LendChangeController
 * @author 刘雄武
 * @Create In 2015年11月30日
 */
@Controller
@RequestMapping({ "${adminPath}/lendChangeFinish" })
public class LendChangeFinishController extends BaseController {

	@Resource
	private LendChangeFinishManager lcmService;

	/**
	 * 获取出借信息变更查询数据 2015年12月15日 By 刘雄武
	 * 
	 * @param queryView
	 * @param flag
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "queryList", "" })
	public String queryList(LendQueryView queryView, boolean flag,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		queryView.setDictChangeType(LendchgType.LEND_CHG.value);
		queryView.setAccountType(LendchgType.TRUSTESSHIP_CARD_CHA.value);
		if (queryView.getOrgCode() != null
				&& !"".equals(queryView.getOrgCode())) {
			String[] applys = queryView.getOrgCode().split(",");
			List<String> codes = null;
			if (applys.length > 0) {
				codes = Arrays.asList(applys);
			}
			queryView.setOrgIds(codes);
		}
		Page<LendLoanApply> page = lcmService.queryList(
				new Page<LendLoanApply>(request, response), queryView);

		FortuneDictUtil.addDicts(model, new String[] { "tz_trust_state",
				"tz_lenderchg_state", "tz_lend_state", "tz_customer_state",
				"tz_customer_src" });
		model.addAttribute("page", page).addAttribute("queryView", queryView);
		return "/lendChange/lendChangeQueryList";
	}

	/**
	 * 获取已办 2015年12月9日 By 刘雄武
	 * 
	 * @param customer
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping({ "finish" })
	public String finish(LendQueryView queryView, Model model,
			HttpServletRequest request, HttpServletResponse response) {

		if (queryView.getOrgCode() != null
				&& !"".equals(queryView.getOrgCode())) {
			String[] applys = queryView.getOrgCode().split(",");
			List<String> codes = null;
			if (applys.length > 0) {
				codes = Arrays.asList(applys);
			}
			queryView.setOrgIds(codes);
		}
		Page<LendLoanApply> page = lcmService.getLendChangeFinish(
				new Page<LendLoanApply>(request, response), queryView);

		FortuneDictUtil.addDicts(model, new String[] { "tz_trust_state",
				"tz_lend_state", "tz_customer_state", "tz_customer_src" });
		model.addAttribute("page", page).addAttribute("queryView", queryView);
		return "/lendChange/lendChangFinish";
	}

}
