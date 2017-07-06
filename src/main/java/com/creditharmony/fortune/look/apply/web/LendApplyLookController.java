package com.creditharmony.fortune.look.apply.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.creditharmony.common.util.ObjectHelper;
import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.fortune.type.BillState;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.system.util.DataScopeUtil;
import com.creditharmony.core.type.SystemFlag;
import com.creditharmony.core.users.type.FortuneOrgType;
import com.creditharmony.core.web.BaseController;
import com.creditharmony.fortune.common.entity.Check;
import com.creditharmony.fortune.common.entity.ReturnMsg;
import com.creditharmony.fortune.common.service.CheckManager;
import com.creditharmony.fortune.customer.CustomerConstants;
import com.creditharmony.fortune.look.apply.entity.LendSearchObj;
import com.creditharmony.fortune.look.apply.manager.LendApplyLookManager;
import com.creditharmony.fortune.look.apply.util.ExporLendExcel;
import com.creditharmony.fortune.look.apply.util.ExportDeductExcel;
import com.creditharmony.fortune.look.apply.view.LendLookPageView;
import com.creditharmony.fortune.look.apply.view.PriorityResultView;
import com.creditharmony.fortune.utils.FortuneDictUtil;

/**
 * 出借申请查看
 * 
 * @Class Name LendApplyLookController
 * @author 李志伟
 * @Create In 2016年1月21日
 */
@Controller
@RequestMapping("${adminPath}/lendApplyLook/")
public class LendApplyLookController extends BaseController {

	@Autowired
	private LendApplyLookManager lendApplyLookManager;
	@Autowired
	private CheckManager checkManager;

	/**
	 * 初始化出借申请查看列表 2016年1月21日 by 李志伟
	 * 
	 * @param lso
	 * @param request
	 * @param response
	 * @param m
	 * @return
	 */
	@RequestMapping(value = "loadLendApplyLookList")
	public String loadLendApplyLookList(LendSearchObj lso,
			HttpServletRequest req, HttpServletResponse resp, Model m) {

		LendLookPageView llov = lendApplyLookManager.loadLendApplyLookList(
				req, resp, lso);
		
		m.addAttribute("llov", llov);
		FortuneDictUtil.addDicts(m, new String[] { "tz_back_state",
				"tz_for_apply_status", "tz_finish_state", "tz_lend_state",
				"tz_bill_day", "tz_pay_type", "tz_trust_state",
				"tz_deduct_plat", "tz_open_bank", "tz_invest_state",
				"tz_data_source", "tz_for_apply_status", "tz_deduct_state", "tz_channel_flag",
				"tz_switch_approve_status","tz_working_state","tz_priority_back"});
		return "lendApplyLook/lendApplyLookList";
	}

	/**
	 * 导出划扣excel 2016年3月15日 By 肖长伟
	 * 
	 * @param lso
	 * @param request
	 * @param response
	 * @param m
	 */
	@RequestMapping("exportDeductExcel")
	public void exportDeductExcel(LendSearchObj lso,
			HttpServletRequest request, HttpServletResponse response, Model m) {

		// 1代表出借，2代表划扣
		ExportDeductExcel.exportData(getExportListParam(lso, "2"), response);
	}

	/**
	 * 导出出借excel 2016年3月15日 By 肖长伟
	 * 
	 * @param lso
	 * @param request
	 * @param response
	 * @param m
	 */
	@RequestMapping("exportLendExcel")
	public void exportLendExcel(LendSearchObj lso, HttpServletRequest request,
			HttpServletResponse response, Model m) {
		// 1代表出借，2代表划扣
		ExporLendExcel.exportData(getExportListParam(lso, "1"), response);
	}

	/**
	 * 查导出excel的list数据 2016年3月15日 By 肖长伟
	 * 
	 * @param lso
	 * @return
	 */
	private Map<String, Object> getExportListParam(LendSearchObj lso,
			String code) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		String dataRights = DataScopeUtil.getDataScope("app",
				SystemFlag.FORTUNE.value);
		if (StringUtils.isNotEmpty(dataRights)) {
			Map<String, String> sqlMap = new HashMap<String, String>();
			sqlMap.put("dataRights", dataRights);
			paramMap.put("sqlMap", sqlMap);
		}
		paramMap.put("defaultOrderSql",
				CustomerConstants.WorkFlow.LEND_FLOW_TYPE);
		paramMap.put("orgType", FortuneOrgType.CENTER.key);
		// 1代表出借，2代表划扣
		if (code.equals("1")) {
			// 第一账单日
			paramMap.put("firstBill", BillState.SQ.value);
		}
		paramMap.put("lso", lso);
		/*
		 * 将多个值得字符串切割成数组
		 */
		// 划扣状态
		if (!("").equals(lso.getDictApplyDeductPay())
				&& lso.getDictApplyDeductPay() != null) {
			paramMap.put("dictApplyDeductPay", lso.getDictApplyDeductPay());
		}
		// 状态
		if (!("").equals(lso.getStatus()) && lso.getStatus() != null) {
			paramMap.put("status", lso.getStatus());
		}

		if (!("").equals(lso.getTuoguanStatus())
				&& lso.getTuoguanStatus() != null) {
			paramMap.put("tuoguanStatus", lso.getTuoguanStatus());
		}

		// 完结状态
		if (!("").equals(lso.getDictApplyEndState())
				&& lso.getDictApplyEndState() != null) {
			paramMap.put("dictApplyEndState", lso.getDictApplyEndState());
		}

		// 出借状态
		if (!("").equals(lso.getLendStatus()) && lso.getLendStatus() != null) {

			String[] lendStatus = lso.getLendStatus().split(",");
			paramMap.put("lendStatus", lendStatus);
		}
		// 账单日
		if (!("").equals(lso.getApplyBillday())
				&& lso.getApplyBillday() != null) {
			String[] applyBillday = lso.getApplyBillday().split(",");
			paramMap.put("applyBillday", applyBillday);
		}

		// 付款方式
		if (!("").equals(lso.getApplyPay()) && lso.getApplyPay() != null) {
			String[] applyPay = lso.getApplyPay().split(",");
			paramMap.put("applyPay", applyPay);
		}

		// 出借产品
		if (!("").equals(lso.getProductCode()) && lso.getProductCode() != null) {
			String[] productCode = lso.getProductCode().split(",");
			paramMap.put("productCode", productCode);
		}
		if (!ObjectHelper.isEmpty(lso.getDictApplyDeductType())) {
			paramMap.put("dictApplyDeductType", lso.getDictApplyDeductType()
					.split(","));
		}
		if (!ObjectHelper.isEmpty(lso.getAccountBank())) {
			paramMap.put("accountBank", lso.getAccountBank().split(","));
		}
		//渠道标识
		if (!ObjectHelper.isEmpty(lso.getDictFortunechannelflag())) {
			paramMap.put("dictFortunechannelflag",lso.getDictFortunechannelflag().split(","));
		}
		//在职状态
		if (!ObjectHelper.isEmpty(lso.getWorkingState())) {
			paramMap.put("workingState",lso.getWorkingState().split(","));
		}
		return paramMap;
	}

	/**
	 * 初始化出借申请页数据 2015年12月22日 by 李志伟
	 * 
	 * @param code
	 * @param m
	 * @return
	 */
	@RequestMapping(value = "goLendApplyLookPage")
	public String goLendApplyPage(String code, String userManagerId, Model m) {
		
		LendLookPageView llpv = lendApplyLookManager.goLendApplyPage(code, userManagerId);
		m.addAttribute("llpv", llpv);
		
		FortuneDictUtil.addDicts(m, new String[] { "tz_open_bank",
				"com_card_type", "tz_pay_type", "tz_deduct_plat",
				"tz_contract_vesion", "tz_trust_state", "tz_lend_state",
				"tz_lend_state", "tz_back_state", "tz_protocol_version" });
		// DeductUtils.getDictList(m);
		return "lendApplyLook/lendApplyLook";
	}

	/**
	 * 全程留痕 2016年3月23日 By 肖长伟
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param check
	 * @return
	 */
	@RequestMapping("fullTrace")
	public String fullTrace(HttpServletRequest request,
			HttpServletResponse response, Model model, Check check) {
		Page<Check> page = new Page<Check>(request, response);
		Page<Check> pageview = checkManager.findCheckList(page, check);
		page.setFuncName("subPage");
		model.addAttribute("page", pageview);
		model.addAttribute("check", check);
		return "lendApplyLook/popFullTrace";
	}
	
	/**
	 * 跳转至优先回款详细页面
	 * 2017年3月24日
	 * By 郭强
	 * @param backmId
	 * @param model
	 * @return
	 */
	@RequestMapping("goPriorityBackPage")
	public String goPriorityBackPage(String code, String userManagerId,String priorityId, Model m) {
		
		LendLookPageView llpv = lendApplyLookManager.goPriorityBackPage(code, userManagerId,priorityId);
		m.addAttribute("llpv", llpv);
		
		FortuneDictUtil.addDicts(m, new String[] { "tz_open_bank",
				"com_card_type", "tz_pay_type", "tz_deduct_plat",
				"tz_contract_vesion", "tz_trust_state", "tz_lend_state",
				"tz_lend_state", "tz_back_state", "tz_protocol_version" });
		return "priorityBack/priorityBack";
	}
		
	/**
	 * 优先回款申请
	 * 2017年3月30日
	 * 郭强
	 * @param view
	 * @return
	 */
	@RequestMapping("applyPriorityBack")
	@ResponseBody
	public String applyPriorityBack(PriorityResultView view){
		
		String message = lendApplyLookManager.apply(view);
		
		return jsonMapper.toJson(new ReturnMsg(message));
	}
	
}