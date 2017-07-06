package com.creditharmony.fortune.change.lender.query.web;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.web.BaseController;
import com.creditharmony.fortune.change.lender.apply.entity.ext.CustomerExSearch;
import com.creditharmony.fortune.change.lender.apply.view.LenderQueryView;
import com.creditharmony.fortune.change.lender.query.service.LenderChangeQueryManager;
import com.creditharmony.fortune.template.entity.LenderChangerOutExcel;
import com.creditharmony.fortune.utils.FortuneDictUtil;

/**
 * 出借人信息变更查询控制器
 * 
 * @Class Name LenderChangeController
 * @author 郭才林
 * @Create In 2015年11月19日
 */
@Controller
@RequestMapping({ "${adminPath}/lenderChange/query" })
public class LenderChangeQueryController extends BaseController {

	@Autowired
	private LenderChangeQueryManager queryManager;

	/**
	 * 出借人信息变更查询 2015年12月11日 By 郭才林
	 * 
	 * @param query
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "queryList", "" })
	public String queryList(LenderQueryView query, HttpServletRequest request,
			HttpServletResponse response, Model model) {

		if (query.getOrgCode() != null && !"".equals(query.getOrgCode())) {
			String[] applys = query.getOrgCode().split(",");
			List<String> codes = null;
			if (applys.length > 0) {
				codes = Arrays.asList(applys);
			}
			query.setOrgIds(codes);
		}
		Page<CustomerExSearch> page = queryManager.queryList(
				new Page<CustomerExSearch>(request, response), query);
		FortuneDictUtil.addDicts(model, new String[] { "tz_customer_src",
				"tz_lenderchg_state" });
		model.addAttribute("page", page).addAttribute("query", query);
		return "/lenderChange/lenderChangeQueryList";
	}

	/**
	 * 出借人查询导出 2016年4月15日 By 刘雄武
	 * 
	 * @param request
	 * @param response
	 * @param query
	 * @param applyIds
	 */
	@SuppressWarnings("unused")
	@RequestMapping(value = "/expExcel")
	public void expExcel(HttpServletRequest request,
			HttpServletResponse response, LenderQueryView query, String applyIds) {
		List<LenderChangerOutExcel> list = null;
		// 选中条数导出记录
		if (applyIds != null && !"".equals(applyIds)) {
			String[] applys = applyIds.split(",");
			List<String> codes = null;
			if (applys.length > 0) {
				codes = Arrays.asList(applys);
			}
			LenderQueryView ext = new LenderQueryView();
			ext.setApplyIds(codes);
			// 按选中出借编号导出
			list = queryManager.findListExportModel(ext, response);
		} else {
			if (query.getOrgCode() != null && !"".equals(query.getOrgCode())) {
				String[] applys = query.getOrgCode().split(",");
				List<String> codes = null;
				if (applys.length > 0) {
					codes = Arrays.asList(applys);
				}
				query.setOrgIds(codes);
			}
			// 按条件导出excel
			list = queryManager.findListExportModel(query, response);
		}
		// 导出excel
		// FileUtil.exportExcel(list, LenderChangerOutExcel.class,
		// "出借人信息变更", response);
		// ExportExcel ee = new ExportExcel();
		// ee.LenderExportData(response,
		// ExportField.lenderChange_title,"出借人信息变更", list);
	}

}