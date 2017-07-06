package com.creditharmony.fortune.contract.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.creditharmony.core.common.type.SystemConfigConstant;
import com.creditharmony.core.excel.util.ExportExcel;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.role.type.FortuneRole;
import com.creditharmony.core.users.entity.Role;
import com.creditharmony.core.users.entity.User;
import com.creditharmony.core.users.util.UserUtils;
import com.creditharmony.core.web.BaseController;
import com.creditharmony.fortune.contract.constant.Constant;
import com.creditharmony.fortune.contract.entity.ContractApply;
import com.creditharmony.fortune.contract.service.ContractApplyManager;
import com.creditharmony.fortune.contract.util.StatisticsUtil;
import com.creditharmony.fortune.contract.view.ContractApplyDetailView;
import com.creditharmony.fortune.contract.view.ContractApplyExcelView;
import com.creditharmony.fortune.contract.view.ContractApplyParamView;
import com.creditharmony.fortune.utils.FortuneDictUtil;

/**
 * 合同申请已办
 * @Class Name ContractApplyAlreadyList
 * @author 李放
 * @Create In 2015年12月16日
 */
@Controller
@RequestMapping(value="${adminPath}/contract")
public class ContractApplyAlreadyList extends BaseController {
	@Autowired
	private ContractApplyManager contractApplyManager;
	
	/**
	 * 已办理合同申请列表
	 * 2015年12月25日
	 * By  王鹏飞
	 * @param contractApplyView
	 * @param page
	 * @param model
	 * @return 合同申请列表页面地址
	 */
	@RequestMapping(value = "/applyAlreadyList")
	public String applyAlreadyList(
			@ModelAttribute ContractApplyParamView contractApplyView, Model model,HttpServletRequest request, HttpServletResponse response) {
		User user = (User) UserUtils.getSession().getAttribute(SystemConfigConstant.SESSION_USER_INFO);
		List<Role> roles = user.getRoleList();
		// 综合内勤
		for (int i = 0, k = roles.size(); i < k; i++) {
			if (roles.get(i).getId().equals(FortuneRole.OFFICE_STAFF.id)) {
				contractApplyView.setContStoresId(user.getDepartment().getId());
				contractApplyView.setOrgName(user.getDepartment().getName());
				contractApplyView.setStaff(1);
				break;
			}
		}
		// 派发主记录
		contractApplyView.setDistType(Constant.DISTRIBUT_MAIN_ORDER);
		Page<ContractApplyDetailView> pageview = contractApplyManager
				.listContractApprovalDonePage(	new Page<ContractApplyDetailView>(request,response), contractApplyView);
		model.addAttribute("page", pageview).addAttribute("contractApplyView",
				contractApplyView);
		
		FortuneDictUtil.addDicts(model, new String[]{"tz_contract_vesion", "tz_appaly_state"});
		  
		return "contract/applyAlreadyList";
	}
	
	/**
	 * 详细页面
	 * 2015年12月25日
	 * By 王鹏飞
	 * @param contractId
	 * @param request
	 * @param response
	 * @param model
	 * @return 详细页面地址
	 */
	@RequestMapping(value = "/applyAlready")
	public String applyAlready(String contractId, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		ContractApply ct = contractApplyManager.getContractApply(contractId);
		// 1.获取用户信息
		User user = (User) UserUtils.getSession().getAttribute(SystemConfigConstant.SESSION_USER_INFO);;
		// 2.根据用户查询到门店信息
		ct.setApplyBy(user.getName());
		ct.setOrgName(user.getDepartment().getName());
		model.addAttribute("ct", ct);
		return "detailed/applyAlready";
	}
	
	/**
	 * 导出合同申请审核记录 
	 * 2015年12月25日
	 * By 王鹏飞
	 * @param contractApplyView
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return 导出合同申请审核记录数据流
	 */
	@RequestMapping(value = "/applyAlreadyList/exportOrders")
	public String exportOrders(
			@ModelAttribute ContractApplyParamView contractApplyView,
			HttpServletRequest request, HttpServletResponse response,
			RedirectAttributes redirectAttributes) {
		try {
			User user = (User) UserUtils.getSession().getAttribute(SystemConfigConstant.SESSION_USER_INFO);
			List<Role> roles = user.getRoleList();
			// 综合内勤
			for (int i = 0, k = roles.size(); i < k; i++) {
				if (roles.get(i).getId().equals(FortuneRole.OFFICE_STAFF.id)) {
					contractApplyView.setContStoresId(user.getDepartment().getId());
					contractApplyView.setOrgName(user.getDepartment().getName());
					contractApplyView.setStaff(1);
					break;
				}
			}
			// 派发住记录
			contractApplyView.setDistType(Constant.DISTRIBUT_MAIN_ORDER);
			List<ContractApplyExcelView> list = contractApplyManager
					.listContractApprovalDone(contractApplyView);
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
	 * 根据ID导出合同申请审核记录 
	 * 2015年12月25日
	 * By 王鹏飞
	 * @param ids
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return 导出合同申请审核记录数据流
	 */
	@RequestMapping(value = "/applyAlreadyList/checkoutOrders")
	public String checkoutOrders(String ids, HttpServletRequest request,
			HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
			// 选择记录集合
			List<ContractApplyExcelView> list = contractApplyManager
					.listContractApprovalDoneCheckout(ids);
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
