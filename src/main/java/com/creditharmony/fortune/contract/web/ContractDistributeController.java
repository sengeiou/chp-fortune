package com.creditharmony.fortune.contract.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.creditharmony.core.common.type.SystemConfigConstant;
import com.creditharmony.core.excel.util.ExportExcel;
import com.creditharmony.core.excel.util.ImportExcel;
import com.creditharmony.core.fortune.type.DistributeState;
import com.creditharmony.core.fortune.type.SingnState;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.role.type.FortuneRole;
import com.creditharmony.core.users.entity.Role;
import com.creditharmony.core.users.entity.User;
import com.creditharmony.core.users.util.UserUtils;
import com.creditharmony.core.web.BaseController;
import com.creditharmony.fortune.contract.constant.Constant;
import com.creditharmony.fortune.contract.constant.Result;
import com.creditharmony.fortune.contract.entity.ContractDistribute;
import com.creditharmony.fortune.contract.service.ContractDistributeManager;
import com.creditharmony.fortune.contract.service.ContractManager;
import com.creditharmony.fortune.contract.util.ContractNUMUtil;
import com.creditharmony.fortune.contract.util.StatisticsUtil;
import com.creditharmony.fortune.contract.view.ContractDistributeExcelView;
import com.creditharmony.fortune.contract.view.ContractDistributeParamView;
import com.creditharmony.fortune.contract.view.ContractDistributeView;
import com.creditharmony.fortune.utils.FortuneDictUtil;
/**
 * 合同派发
 * @Class Name ContractDistributeController
 * @author 李放
 * @Create In 2015年12月5日
 */
@Controller
@RequestMapping(value="${adminPath}/contract")
public class ContractDistributeController extends BaseController {
	
	@Autowired
	private ContractDistributeManager contractDistributeManager;
	
	@Autowired
	private ContractManager contractManager;
	
	/**
	 * 合同派发列表页面
	 * 2015年12月5日
	 * By 李放
	 * Update By 王鹏飞
	 * @param contractDistribute
	 * @param page
	 * @param m
	 * @return
	 */
	@RequestMapping(value="/distributeList")
	public String distributeList(
			@ModelAttribute("contractDistribute") ContractDistributeParamView contractDistribute,HttpServletRequest request, HttpServletResponse response
			, Model m) {
	
		User user = (User) UserUtils.getSession().getAttribute(SystemConfigConstant.SESSION_USER_INFO);
		List<Role> roles = user.getRoleList();
		// 综合内勤
		for (int i = 0, k = roles.size(); i < k; i++) {
			if (roles.get(i).getId().equals(FortuneRole.OFFICE_STAFF.id)) {
				contractDistribute.setContStoresId(user.getDepartment().getId());
				contractDistribute.setOrgName(user.getDepartment().getName());
				contractDistribute.setStaff(1);
				break;
			}
		}
		contractDistribute.setDistType(Constant.DISTRIBUT_MAIN_ORDER);
		Page<ContractDistributeView> pageview = contractDistributeManager
				.listContractDistribute(new Page<ContractDistributeView> (request,response), contractDistribute);
		m.addAttribute("page", pageview).addAttribute("ContractDistribute",
				contractDistribute);
		FortuneDictUtil.addDicts(m, new String[]{"tz_contract_vesion", "tz_dispath_status", "tz_singn_state"});
		return "contract/distributeList";
	}
	
	/**
	 * 合同派发详细页面
	 * 2015年12月29日
	 * update by 王鹏飞
	 * By 李放
	 * @param id
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/distributeContract")
	public String distributeContract(String id, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		ContractDistributeView contractDistributeView = contractDistributeManager
				.getContractDistributeView(id);
		model.addAttribute("cd", contractDistributeView);
		return "detailed/distribute";
	}
	
	/**
	 * 导出合同派发记录 
	 * 2015年12月28日
	 * By 王鹏飞
	 * @param contractDistribute
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "/distributeList/exportExcel")
	public String exportExcel(
			@ModelAttribute("contractDistribute") ContractDistribute contractDistribute,
			HttpServletRequest request, HttpServletResponse response,
			RedirectAttributes redirectAttributes) {
		try {
			User user = (User) UserUtils.getSession().getAttribute(SystemConfigConstant.SESSION_USER_INFO);
			List<Role> roles = user.getRoleList();
			// 综合内勤
			for (int i = 0, k = roles.size(); i < k; i++) {
				if (roles.get(i).getId().equals(FortuneRole.OFFICE_STAFF.id)) {
					contractDistribute.setContStoresId(user.getDepartment().getId());
					contractDistribute.setOrgName(user.getDepartment().getName());
					break;
				}
			}
			// 住派发记录，并且为派发为完成的
			contractDistribute.setDistType(Constant.DISTRIBUT_MAIN_ORDER);
			contractDistribute.setDistStatus(DistributeState.YPF.value);
			List<ContractDistributeExcelView> list = contractDistributeManager
					.listDistribute(contractDistribute);
			ExportExcel exl = new ExportExcel(
					Constant.CONTRACT_DISTRIBUTE_EXCEL_PREFIX,
					ContractDistributeExcelView.class);
			exl.setDataList(list);
			exl.write(
					response,
					StatisticsUtil
							.getDownloadFileName(Constant.CONTRACT_DISTRIBUTE_EXCEL_PREFIX));
			exl.dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出excel失败！失败信息：" + e.getMessage());
		}
		return null;
	}
	
	/**
	 * 根据ID导出合同派发记录 
	 * 2015年12月28日
	 * By 王鹏飞
	 * @param ids
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "/distributeList/checkoutExcel")
	public String checkoutExcel(String ids, HttpServletRequest request,
			HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
			List<ContractDistributeExcelView> list = contractDistributeManager
					.listContractDistributeCheckout(ids);
			ExportExcel exl = new ExportExcel(
					Constant.CONTRACT_DISTRIBUTE_EXCEL_PREFIX,
					ContractDistributeExcelView.class);
			exl.setDataList(list);
			exl.write(
					response,
					StatisticsUtil
							.getDownloadFileName(Constant.CONTRACT_DISTRIBUTE_EXCEL_PREFIX));
			exl.dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出excel失败！失败信息：" + e.getMessage());
		}
		return null;
	}

	/**
	 * 根据ID派发合同 
	 * 2015年12月28日
	 * By 王鹏飞
	 * @param request
	 * @param response
	 * @param ids
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "/distributeList/distributeContract")
	@ResponseBody
	public String distributeContract(String ids, HttpServletRequest request,
			HttpServletResponse response, RedirectAttributes redirectAttributes) {
		User user = UserUtils.getUser();
		String[] list = ids.split(",");
		return contractDistributeManager.distributeContract(user, list);
	}

	/**
	 * 派发合同签收
	 * @param id
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/distribute/signContract")
	@ResponseBody
	public String signContract(String id, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		ContractDistribute contractDistribute = contractDistributeManager
				.getContractDistribute(id);
		if (contractDistribute == null) {
			return Constant.PARAMETER_ERROR;
		}
		// 未派发
		if (DistributeState.WPF.value
				.equals(contractDistribute.getDistStatus())) {
			return String.valueOf(Result.one.value);
		}
		// 已签收
		if (SingnState.YQS.value.equals(contractDistribute.getSignStatus())) {
			return String.valueOf(Result.two.value);
		}
		// 未签收
		if (SingnState.WQS.value.equals(contractDistribute.getSignStatus())) {
			contractDistributeManager
					.signContractDistribute(contractDistribute);
		}
		return String.valueOf(Result.zero.value);
	}
	
	/**
	 * 派发文件导入
	 * @param file
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/distribute/importExcel")
	public String importExcel(MultipartFile file, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		try {
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<ContractDistributeExcelView> list = ei
					.getDataList(ContractDistributeExcelView.class);
			if (list == null || list.size() == 0) {
				addMessage(model, "上传文件没有数据");
				return "contract/fail";
			}
			for (ContractDistributeExcelView contractDistributeEX : list) {
				if (contractDistributeEX.getContractId() == null
						|| "".equals(contractDistributeEX.getContractId())) {
					continue;
				}
				if (StringUtils.isEmpty(contractDistributeEX
						.getDistContractNo())) {
					addMessage(model, "合同派发数量(套)不可为空");
					return "contract/fail";
				}

				if (StringUtils
						.isEmpty(contractDistributeEX.getDistExpressNo())) {
					addMessage(model, "物流编号不可为空");
					return "contract/fail";
				}

				if (StringUtils.isEmpty(contractDistributeEX.getDistStartNo())) {
					addMessage(model, "合同起始编号不可为空");
					return "contract/fail";
				}

				if (StringUtils.isEmpty(contractDistributeEX.getDistEndNo())) {
					addMessage(model, "合同终止编号不可为空");
					return "contract/fail";
				}

				if (StringUtils.isEmpty(contractDistributeEX.getDistBox())) {
					addMessage(model, "合同数量(箱)不可为空");
					return "contract/fail";
				}

				if (contractDistributeEX.getDistDate() == null) {
					addMessage(model, "合同派发日期不可为空");
					return "contract/fail";
				}

				List<String> range = ContractNUMUtil.range(
						contractDistributeEX.getDistStartNo(),
						contractDistributeEX.getDistEndNo());
				if (range == null) {
					addMessage(model, "无法生成区间的合同编号，合同起始编号："
							+ contractDistributeEX.getDistStartNo()
							+ "至合同终止编号：" + contractDistributeEX.getDistEndNo());
					return "contract/fail";
				}

				Integer distContractNo = Integer.valueOf(contractDistributeEX
						.getDistContractNo().trim());
				if (distContractNo.compareTo(range.size()) != 0) {
					addMessage(model, "合同数量与文档中的派发数量不一致");
					return "contract/fail";
				}

				boolean isExist = contractManager.isExistContract(range);
				if (isExist) {
					addMessage(model, "合同编号已经被使用");
					return "contract/fail";
				}

				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", contractDistributeEX.getContractId());
				map.put("distType", Constant.DISTRIBUT_MAIN_ORDER);
				ContractDistribute disDb = contractDistributeManager
						.getContractDistributeByContractId(map);
				if (disDb == null) {
					continue;
				}
				int i = Integer.valueOf(contractDistributeEX
						.getDistContractNo());
				int j = disDb.getDistContractNo() == null ? i : disDb
						.getDistContractNo() + i;
				if (j > disDb.getApplyNo()) {
					addMessage(model, contractDistributeEX.getName()
							+ "的合同派发数量大于申请数量");
					return "contract/fail";
				}
				
			}
			contractDistributeManager.addContracts(list);
		} catch (Exception e) {
			e.printStackTrace();
			addMessage(model, "导入合同失败！失败信息：" + e.getMessage());
			return "contract/fail";
		}
		return "contract/success";
	}
	
	/**
	 * 根据申请id查询派发记录
	 * @param applyId 合同申请id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/distributeSubSet")
	public String distributeSubSet(String applyId, Model model) {
		List<ContractDistributeView> list = contractDistributeManager.listDistributeSubSet(applyId, Constant.DISTRIBUT_SUBSET_ORDER);
		model.addAttribute("ContractDistribute", list);
		return "contract/distributeSubSet";
	}
	
}
