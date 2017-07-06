package com.creditharmony.fortune.look.approve.web;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.creditharmony.common.util.ArrayHelper;
import com.creditharmony.common.util.ObjectHelper;
import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.fortune.type.LendState;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.role.type.FortuneRole;
import com.creditharmony.core.system.util.DataScopeUtil;
import com.creditharmony.core.type.SystemFlag;
import com.creditharmony.core.web.BaseController;
import com.creditharmony.fortune.customer.CustomerConstants;
import com.creditharmony.fortune.customer.dao.LoanApplyDao;
import com.creditharmony.fortune.customer.entity.Customer;
import com.creditharmony.fortune.customer.entity.LoanApply;
import com.creditharmony.fortune.customer.workflow.dao.LendApplyLogDao;
import com.creditharmony.fortune.customer.workflow.dao.TransferRelationDao;
import com.creditharmony.fortune.customer.workflow.entity.LendApplyLog;
import com.creditharmony.fortune.customer.workflow.entity.TransferRelation;
import com.creditharmony.fortune.look.apply.entity.LendSearchObj;
import com.creditharmony.fortune.look.apply.manager.LendApplyLookManager;
import com.creditharmony.fortune.look.apply.util.ExporApprovalExcel;
import com.creditharmony.fortune.look.approve.constants.ApproveLendStateConstant;
import com.creditharmony.fortune.look.approve.manager.LendApplyApprovalLookManager;
import com.creditharmony.fortune.utils.FortuneDictUtil;

/**
 * 出借申请审批查看列表
 * 
 * @Class Name LendApplyApproval
 * @author 李志伟
 * @Create In 2015年12月22日
 */
@Controller
@RequestMapping(value = "${adminPath}/lendApplyApprovalLook")
public class LendApplyApprovalLookController extends BaseController {

	@Autowired
	private LendApplyApprovalLookManager lendApplyApprovalLookManager;
	@Autowired
	private LendApplyLookManager lendApplyLookManager;
	@Autowired
	private LendApplyLogDao lendApplyLogDao;
	@Autowired
	private TransferRelationDao transferRelationDao;
	@Autowired
	private LoanApplyDao loanApplyDao;


	/**
	 * 初始化出借申请审批查看列表 2016年1月21日 by 李志伟
	 * 
	 * @param lso
	 * @param request
	 * @param response
	 * @param m
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "loadLendApplyApprovalLookList")
	public String loadLendApplyApprovalLookList(LendSearchObj lso,
			HttpServletRequest request, HttpServletResponse response, Model m) {

		Page page = new Page<LoanApply>(request, response);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("lso", lso);

		// 出借状态
		if (lso.getLendStatus() != "" && lso.getLendStatus() != null) {
			String[] lendStatusParam = lso.getLendStatus().split(",");
			String[] lendStatus = null;
			for (String approveStatus : lendStatusParam) {
				lendStatus = (String[]) ArrayUtils.addAll(lendStatus, ApproveLendStateConstant.approveLendState.get(approveStatus));
			}
			map.put("lendStatus", lendStatus);
		}

		// 付款方式
		if (lso.getApplyPay() != "" && lso.getApplyPay() != null) {
			String[] applyPay = lso.getApplyPay().split(",");
			map.put("applyPay", applyPay);
		}

		// 出借产品
		if (lso.getProductCode() != "" && lso.getProductCode() != null) {
			String[] productCode = lso.getProductCode().split(",");
			map.put("productCode", productCode);
		}
		if (!ObjectHelper.isEmpty(lso.getDictApplyDeductType())) {
			map.put("dictApplyDeductType",
					lso.getDictApplyDeductType().split(","));
		}
		if (!ObjectHelper.isEmpty(lso.getAccountBank())) {
			map.put("accountBank", lso.getAccountBank().split(","));
		}
		// 营业部
		if (StringUtils.isNotBlank(lso.getOrgCode())) {
			map.put("orgCode", lso.getOrgCode().split(","));
		}
		// 银行
		if (!ObjectHelper.isEmpty(lso.getAccountBank())) {
			map.put("accountBank", lso.getAccountBank().split(","));
		}
		// 默认排序规则
		map.put("defaultOrderSql", CustomerConstants.WorkFlow.LEND_FLOW_TYPE);
		page = lendApplyApprovalLookManager.loadLendApplyApprovalLookList(page,
				map);
		m.addAttribute("page", page);
		m.addAttribute("lso", lso);
		String totalAmountChar = lendApplyApprovalLookManager
				.loadLendApplyApprovalLookListForMoney(map);
		if (null != totalAmountChar && !"".equals(totalAmountChar)) {
			double totalAmount = Double.parseDouble(totalAmountChar);
			m.addAttribute("totalAmount", totalAmount);
		}
		FortuneDictUtil.addDicts(m, new String[] { "tz_lend_state",
				"tz_deduct_plat", "tz_open_bank", "tz_pay_type" });
		return "lendApplyLook/lendApprovalListLook";
	}

	/**
	 * 去出借申请审批详情页 2016年1月21日 by 李志伟
	 * 
	 * @param code
	 * @param m
	 * @return
	 */
	@RequestMapping(value = "goLendApplyApprovalPage")
	public String goLendApplyApprovalPage(String code, String managerId, Model m) {

		Customer cs = lendApplyApprovalLookManager
				.goLendApplyApprovalPage(code);
		LoanApply la = lendApplyApprovalLookManager
				.loadLendApprovalLookPage(code);
		LendApplyLog lg = lendApplyApprovalLookManager
				.loadLendApprovalMesg(code);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", cs.getCustCode());

		if (la.getReceiveBankId().equals(la.getPaymentBankId())) {

			String[] id = { la.getPaymentBankId() };
			map.put("id", id);
			List<LoanApply> list = lendApplyApprovalLookManager
					.loadBankMesg(map);
			m.addAttribute("list", list);
		} else {

			String[] id = { la.getReceiveBankId(), la.getPaymentBankId() };
			map.put("id", id);
			List<LoanApply> list = lendApplyApprovalLookManager
					.loadBankMesg(map);
			m.addAttribute("list", list);
		}

//		map.put("lendStatus", LendState.SPTG.value);
//		List<LendLookListView> redeemLendlist = lendApplyApprovalLookManager
//				.findRedeemLendMesg(map);
		
		List<LoanApply> transferLendList = new ArrayList<LoanApply>();

		HashMap<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("childLendCode", code);
		List<TransferRelation> transferList = transferRelationDao
				.findList(paramMap);
		if (ArrayHelper.isNotEmpty(transferList)) {
			LoanApply a = null;
			for (TransferRelation item : transferList) {
				a = loanApplyDao.getLoanApplyByCode(item.getLendCodeParent());
				if (null != a) {
					a.setTransferMoney(item.getTransferMoney());
					transferLendList.add(a);
				}
			}
		}

		m.addAttribute("transferLendList", transferLendList);
		m.addAttribute("la", la);
		m.addAttribute("cs", cs);
		m.addAttribute("lg", lg);
		// 列表传值到办理页面的理财经理id
		m.addAttribute("managerId", managerId);
		FortuneDictUtil.addDicts(m, new String[] { "tz_contract_vesion", "sex",
				"tz_open_bank", "com_card_type", "tz_pay_type",
				"jk_deduct_plat", "tz_trust_state", "tz_lend_state",
				"tz_back_state", "tz_protocol_version" });

		LendApplyLog log = new LendApplyLog();
		log.setLendCode(code);

		List<LendApplyLog> list = lendApplyLogDao.findList(log);
		if (ArrayHelper.isNotEmpty(list)) {
			log = list.get(0);
			m.addAttribute("log", log);
		}

		return "lendApplyLook/lendApprovalLook";
	}

	/**
	 * 导出审核通过明细excel 2016年3月15日 By 肖长伟
	 * 
	 * @param lso
	 * @param request
	 * @param response
	 * @param m
	 */
	@RequestMapping("/exportExcel")
	public void exportExcel(LendSearchObj lso, HttpServletRequest request,
			HttpServletResponse response, Model m) {
		ExporApprovalExcel.exportData(getExportListParam(lso), response);
	}

	/**
	 * 查导出excel的list数据 2016年3月15日 By 肖长伟
	 * 
	 * @param lso
	 * @return
	 */
	private Map<String, Object> getExportListParam(LendSearchObj lso) {
		Map<String, Object> map = new HashMap<String, Object>();
		String dataRights = DataScopeUtil.getDataScope("a",
				SystemFlag.FORTUNE.value);
		if (StringUtils.isNotEmpty(dataRights)) {
			Map<String, String> sqlMap = new HashMap<String, String>();
			sqlMap.put("dataRights", dataRights);
			map.put("sqlMap", sqlMap);
		}
		map.put("defaultOrderSql", CustomerConstants.WorkFlow.LEND_FLOW_TYPE);
		map.put("OFFICE_STAFF", FortuneRole.OFFICE_STAFF.id);
		map.put("PASS", LendState.SPTG.value);
		map.put("NOTPASS", LendState.SPBTG.value);
		map.put("lso", lso);
		// 出借状态
		if (StringUtils.isNotEmpty(lso.getLendStatus())) {
			String[] lendStatus = lso.getLendStatus().split(",");
			map.put("lendStatus", lendStatus);
		}

		// 付款方式
		if (StringUtils.isNotEmpty(lso.getApplyPay())) {
			String[] applyPay = lso.getApplyPay().split(",");
			map.put("applyPay", applyPay);
		}
		// 出借产品
		if (StringUtils.isNotEmpty(lso.getProductCode())) {
			String[] productCode = lso.getProductCode().split(",");
			map.put("productCode", productCode);
		}
		if (!ObjectHelper.isEmpty(lso.getDictApplyDeductType())) {
			map.put("dictApplyDeductType",
					lso.getDictApplyDeductType().split(","));
		}
		if (!ObjectHelper.isEmpty(lso.getAccountBank())) {
			map.put("accountBank", lso.getAccountBank().split(","));
		}
		return map;
	}

	/**
	 * 查询统计 2016年3月18日 By 肖长伟
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("getApplyStatistics")
	public String getApplyStatistics(Model model) {

		String dateTime = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		Map<String, Object> approveInf = lendApplyLookManager
				.getApplyStatistics(dateTime);

		model.addAttribute("deductDate", dateTime);
		model.addAttribute("waitApprove", approveInf.get("waitapprove"));
		model.addAttribute("passApprove", approveInf.get("passapprove"));
		model.addAttribute("failApprove", approveInf.get("failapprove"));
		model.addAttribute("failDocument", approveInf.get("faildocument"));
		model.addAttribute("totalApprove", approveInf.get("totalapprove"));

		return "lendApplyLook/lendApprovalLookStastics";
	}

}