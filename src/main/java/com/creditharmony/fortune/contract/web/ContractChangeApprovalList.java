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
import com.creditharmony.core.fortune.type.ContractChangeType;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.role.type.FortuneRole;
import com.creditharmony.core.users.entity.Role;
import com.creditharmony.core.users.entity.User;
import com.creditharmony.core.users.util.UserUtils;
import com.creditharmony.core.web.BaseController;
import com.creditharmony.fortune.contract.constant.Constant;
import com.creditharmony.fortune.contract.service.ContractChangeManager;
import com.creditharmony.fortune.contract.util.StatisticsUtil;
import com.creditharmony.fortune.contract.view.ContractChangeExcelView;
import com.creditharmony.fortune.contract.view.ContractChangeParamView;
import com.creditharmony.fortune.contract.view.ContractChangeView;
import com.creditharmony.fortune.utils.FortuneDictUtil;

/**
 * 合同变更申请审核
 * @Class Name ContractChangeApprovalList
 * @author 王鹏飞
 *
 */
@Controller
@RequestMapping(value="${adminPath}/contract")
public class ContractChangeApprovalList extends BaseController {
	
	@Autowired
	private ContractChangeManager contractChangeManager;
	
	/**
	 * 审核列表页面
	 * @author 王鹏飞
	 * @param contractChange 变更列表查询条件
	 * @param page 分页参数
	 * @param model 模版
	 * @return 列表页
	 */
	@RequestMapping(value="/changeApprovalList")
	public String changeApprovalList(
			@ModelAttribute("contractChange") ContractChangeParamView contractChange,HttpServletRequest request, HttpServletResponse response
			, Model model) {
		User user = (User) UserUtils.getSession().getAttribute(SystemConfigConstant.SESSION_USER_INFO);
		List<Role> roles = user.getRoleList();
		boolean flag = false;
		// 综合内勤
		for (int i = 0, k = roles.size(); i < k; i++) {
			if (roles.get(i).getId().equals(FortuneRole.OFFICE_STAFF.id)) {
				contractChange.setChangeInStoresId(user.getDepartment().getId());
				contractChange.setName(user.getDepartment().getName());
				contractChange.setStaff(1);
				//contractChange.setDictChangeType(ContractChangeType.ZJ.value);
				flag = true;
				break;
			}
		}
		if(!flag){
			contractChange.setDictChangeType2(ContractChangeType.ZJ.value);
			contractChange.setDictChangeTypeValue(ContractChangeType.ZJ.value);
		}
		// 待审核状态
		
		Page<ContractChangeView> pageview = contractChangeManager.listContractChange(new Page<ContractChangeView> ( request,  response), contractChange);
		model.addAttribute("page", pageview).addAttribute("ContractChange", contractChange);
		FortuneDictUtil.addDicts(model, new String[]{"tz_contract_vesion", "tz_change_type", "tz_contract_state", "tz_appaly_state"});
		return "contract/changeApprovalList";
	}
	
	/**
	 * 审核详细页面
	 * 2015年12月16日
	 * By 李放
	 * @param id 申请id
	 * @param request 
	 * @param response
	 * @param model 数据模版
	 * @return 详情页面
	 */
	@RequestMapping(value = "/changeApproval")
	public String changeApproval(String id, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		ContractChangeView ch = contractChangeManager.getContractChangeById(id);
		model.addAttribute("ch", ch);
		return "detailed/changeApproval";
	}
	
	/**
	 * 导出合同变更记录
	 * By 王鹏飞
	 * @param contractChange 查询条件参数
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return 下载数据流
	 */
	@RequestMapping(value = "/changeApprovalList/exportOrders")
	public String exportOrders(
			@ModelAttribute("contractChange") ContractChangeParamView contractChange,
			HttpServletRequest request, HttpServletResponse response,
			RedirectAttributes redirectAttributes) {
		try {
			User user = (User) UserUtils.getSession().getAttribute(SystemConfigConstant.SESSION_USER_INFO);
			List<Role> roles = user.getRoleList();
			boolean flag = false;
			// 综合内勤
			for (int i = 0, k = roles.size(); i < k; i++) {
				if (roles.get(i).getId().equals(FortuneRole.OFFICE_STAFF.id)) {
					contractChange.setChangeInStoresId(user.getDepartment().getId());
					contractChange.setName(user.getDepartment().getName());
					contractChange.setStaff(1);
					contractChange.setDictChangeType(ContractChangeType.ZJ.value);
					flag = true;
					break;
				}
			}
			if(!flag){
				contractChange.setDictChangeType2(ContractChangeType.ZJ.value);
				contractChange.setDictChangeTypeValue(ContractChangeType.ZJ.value);
			}
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
	 * 根据ID导出合同变更记录 
	 * By 王鹏飞
	 * @param ids 申请id集合 ，多个用 “,”分割 
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return 下载数据流
	 */
	@RequestMapping(value = "/changeApprovalList/checkoutOrders")
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
