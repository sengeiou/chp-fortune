package com.creditharmony.fortune.contract.web;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.creditharmony.common.util.IdGen;
import com.creditharmony.core.common.type.BooleanType;
import com.creditharmony.core.excel.util.ExportExcel;
import com.creditharmony.core.fortune.type.AppalyState;
import com.creditharmony.core.fortune.type.DistributeState;
import com.creditharmony.core.fortune.type.SingnState;
import com.creditharmony.core.fortune.type.YoN;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.users.entity.User;
import com.creditharmony.core.users.util.UserUtils;
import com.creditharmony.core.web.BaseController;
import com.creditharmony.fortune.contract.constant.Constant;
import com.creditharmony.fortune.contract.entity.ContractApply;
import com.creditharmony.fortune.contract.entity.ContractDistribute;
import com.creditharmony.fortune.contract.service.ContractApplyManager;
import com.creditharmony.fortune.contract.util.StatisticsUtil;
import com.creditharmony.fortune.contract.view.ContractApplyDetailView;
import com.creditharmony.fortune.contract.view.ContractApplyExcelView;
import com.creditharmony.fortune.contract.view.ContractApplyParamView;
import com.creditharmony.fortune.utils.FortuneDictUtil;

/** 
 * 合同申请审核
 * update by 王鹏飞
 * 
 * @Class Name ContractApplyApprovalList
 * @author 李放
 * @Create In 2015年12月3日
 */
@Controller
@RequestMapping("${adminPath}/contract")
public class ContractApplyApprovalList extends BaseController {
	
	@Autowired
	private ContractApplyManager contractApplyManager;
	
	/**
	 * 合同申请审核分页列表页
	 *  2015年12月22日
	 * @param contractApplyView
	 * @param page
	 * @param model
	 * @author 王鹏飞
	 * @return 合同申请审核分页列表页地址
	 */
	@RequestMapping(value = "/applyApprovalList")
	public String applyApprovalList(
			@ModelAttribute ContractApplyParamView contractApplyView,HttpServletRequest request, HttpServletResponse response, Model model) {

		// 关联派发记录主订单类型
		contractApplyView.setDistType(Constant.DISTRIBUT_MAIN_ORDER);
		Page<ContractApplyDetailView> pageview = contractApplyManager
				.listContractApprovalPage(new Page<ContractApplyDetailView>(request,response), contractApplyView);
		model.addAttribute("page", pageview).addAttribute("contractApplyView",
				contractApplyView);
		FortuneDictUtil.addDicts(model, new String[]{"tz_contract_vesion", "tz_appaly_state"});
		return "contract/applyApprovalList";
	}
	
	/**
	 * 获取合同审核详细页 
	 * 2015年12月23日
	 * By 王鹏飞
	 * @param contractId
	 * @param request
	 * @param response
	 * @param model
	 * @return 合同审核详细页 
	 */
	@RequestMapping(value = "/contractApply/getInfo")
	public String getInfo(String contractId, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		// 获取合同申请详细信息
		ContractApply ca = contractApplyManager.getContractApply(contractId);
		User user = UserUtils.get(ca.getApplyBy());
		if (user != null) {
			ca.setApplyBy(user.getName());
		}
		model.addAttribute("ca", ca);
		return "detailed/applyApproval";
	}
	
	/**
	 * 保存合同审核详信息 
	 * 2015年12月23日
	 * By 王鹏飞
	 * @param ca
	 * @param request
	 * @param response
	 * @param model
	 * @return 同审核详信息 
	 */
	@RequestMapping(value = "/applyApproval")
	@ResponseBody
	public String applyApproval(@ModelAttribute ContractApply ca,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		// 审核用户
		ca.setApplyCheckById(UserUtils.getUserId());
		// 审核日期
		ca.setApplyCheckDate(new Date());

		// 审核通过
		if (YoN.SHI.value.equals(ca.getApplyCheckResult()))
			ca.setApplyStatus(AppalyState.SHTG.value);
		// 审核不通过
		if (YoN.FOU.value.equals(ca.getApplyCheckResult()))
			ca.setApplyStatus(AppalyState.SHBTG.value);

		// 审核通过，添加派发记录。审核不通过只需要更新合同申请信息
		if (YoN.SHI.value.equals(ca.getApplyCheckResult())) {
			ContractDistribute dis = new ContractDistribute();
			dis.setId(IdGen.uuid());
			dis.setContractId(ca.getContractId());
			dis.setContStoresId(ca.getContStoresId());
			dis.setContVersion(ca.getContVersion());
			// 派发状态-未派发
			dis.setDistStatus(DistributeState.WPF.value);
			// 签收状态-未签收
			dis.setSignStatus(SingnState.WQS.value);
			// 派发订单类型-主派发记录
			dis.setDistType(Constant.DISTRIBUT_MAIN_ORDER);
			contractApplyManager.insertContractDistribute(dis, ca);
		} else {
			contractApplyManager.updateContractApply(ca);
		}
		return BooleanType.TRUE;
	}
		
	/**
	 * 导出合同申请记录 
	 * 2015年12月24日
	 * By 王鹏飞
	 * @param contractApplyView
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return 文件流
	 */
	@RequestMapping(value = "/applyApprovalList/exportOrders")
	public String exportOrders(
			@ModelAttribute ContractApplyParamView contractApplyView,
			HttpServletRequest request, HttpServletResponse response,
			RedirectAttributes redirectAttributes) {
		try {
			// 待审核状态
			contractApplyView.setApplyStatus(AppalyState.DSH.value);
			// 住派发记录
			contractApplyView.setDistType(Constant.DISTRIBUT_MAIN_ORDER);
			List<ContractApplyExcelView> list = contractApplyManager
					.listContractApproval(contractApplyView);
			ExportExcel exl = new ExportExcel(Constant.CONTRACT_EXCEL_PREFIX,
					ContractApplyExcelView.class);
			exl.setDataList(list);
			exl.write(response, StatisticsUtil
					.getDownloadFileName(Constant.CONTRACT_EXCEL_PREFIX));
			exl.dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出excel失败！失败信息：" + e.getMessage());
		}
		return null;
	}
	
	/**
	 * 根据ID导出合同申请记录 
	 * 2015年12月24日
	 * By 王鹏飞
	 * @param ids
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return 文件流
	 */
	@RequestMapping(value = "/applyApprovalList/checkoutOrders")
	public String checkoutOrders(String ids, HttpServletRequest request,
			HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
			List<ContractApplyExcelView> list = contractApplyManager
					.listContractApprovalCheckout(ids);
			ExportExcel exl = new ExportExcel(Constant.CONTRACT_EXCEL_PREFIX,
					ContractApplyExcelView.class);
			exl.setDataList(list);
			exl.write(response, StatisticsUtil
					.getDownloadFileName(Constant.CONTRACT_EXCEL_PREFIX));
			exl.dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出excel失败！失败信息：" + e.getMessage());
		}
		return null;
	}
		
}
