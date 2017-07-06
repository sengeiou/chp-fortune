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

import com.creditharmony.core.excel.util.ExportExcel;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.web.BaseController;
import com.creditharmony.fortune.contract.constant.Constant;
import com.creditharmony.fortune.contract.service.ContractChangeManager;
import com.creditharmony.fortune.contract.util.StatisticsUtil;
import com.creditharmony.fortune.contract.view.ContractChangeExcelView;
import com.creditharmony.fortune.contract.view.ContractChangeParamView;
import com.creditharmony.fortune.contract.view.ContractChangeView;
import com.creditharmony.fortune.utils.FortuneDictUtil;
/**
 * 合同状态变更已办
 * @Class Name ContractChangeAlreadylList
 * @author 李放
 * @Create In 2015年12月16日
 */
@Controller
@RequestMapping(value="${adminPath}/contract")
public class ContractChangeAlreadylList extends BaseController {
	
	@Autowired
	private ContractChangeManager contractChangeManager;
	
	/**
	 * 变更已办列表
	 * @param contractChange
	 * @param page
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/changeAlreadyList")
	public String changeAlreadyList(
			@ModelAttribute("contractChange") ContractChangeParamView contractChange,HttpServletRequest request, HttpServletResponse response
			, Model model) {
	
		// 非待审核记录
		Page<ContractChangeView> pageview = contractChangeManager
				.listChangeApplyDone(new Page<ContractChangeView> (request,response), contractChange);
		model.addAttribute("page", pageview).addAttribute("ContractChange",
				contractChange);
		FortuneDictUtil.addDicts(model, new String[]{"tz_contract_vesion", "tz_appaly_state", "tz_change_type"});
		return "contract/changeAlreadyList";
	}
	
	/**
	 * 变更已办详细
	 * 2015年12月7日
	 * By 李放
	 * @param id
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/changeAlready")
	public String changeAlready(String id, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		ContractChangeView cb = contractChangeManager.getContractChangeById(id);
		model.addAttribute("cb", cb);
		return "detailed/changeAlready";
	}
	
	/**
	 * 导出合同以办变更记录
	 * By 王鹏飞
	 * @param contractChange
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "/changeAlreadyList/exportOrders")
	public String exportOrders(
			@ModelAttribute("contractChange") ContractChangeParamView contractChange,
			HttpServletRequest request, HttpServletResponse response,
			RedirectAttributes redirectAttributes) {
		try {
			// 非待审核记录
			List<ContractChangeExcelView> list = contractChangeManager
					.listContractChangeExcel(contractChange);
			ExportExcel exl = new ExportExcel(
					Constant.CONTRACT_CHANGE_APPLY_PREFIX,
					ContractChangeExcelView.class);
			exl.setDataList(list);
			exl.write(response, StatisticsUtil
					.getDownloadFileName(Constant.CONTRACT_CHANGE_APPLY_PREFIX));
			exl.dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出excel失败！失败信息：" + e.getMessage());
		}
		return null;
	}
	
	/**
	 * 根据ID导出合同已办变更记录 
	 * By 王鹏飞
	 * @param ids
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "/changeAlreadyList/checkoutOrders")
	public String exportOrders(String ids, HttpServletRequest request,
			HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
			List<ContractChangeExcelView> list = contractChangeManager
					.listContractChangeExcelCheckout(ids);
			ExportExcel exl = new ExportExcel(
					Constant.CONTRACT_CHANGE_APPLY_PREFIX,
					ContractChangeExcelView.class);
			exl.setDataList(list);
			exl.write(response, StatisticsUtil
					.getDownloadFileName(Constant.CONTRACT_CHANGE_APPLY_PREFIX));
			exl.dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出excel失败！失败信息：" + e.getMessage());
		}
		return null;
	}
	
}
