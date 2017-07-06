package com.creditharmony.fortune.contract.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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

import com.creditharmony.common.util.DateUtils;
import com.creditharmony.common.util.ObjectHelper;
import com.creditharmony.core.common.type.BooleanType;
import com.creditharmony.core.common.type.SystemConfigConstant;
import com.creditharmony.core.excel.util.ExportExcel;
import com.creditharmony.core.excel.util.ImportExcel;
import com.creditharmony.core.exception.ServiceException;
import com.creditharmony.core.fortune.type.AppalyState;
import com.creditharmony.core.fortune.type.ContractChangeType;
import com.creditharmony.core.fortune.type.ContractOwner;
import com.creditharmony.core.fortune.type.ContractState;
import com.creditharmony.core.fortune.type.ContractVesion;
import com.creditharmony.core.fortune.type.PigeonholeState;
import com.creditharmony.core.fortune.type.SingnState;
import com.creditharmony.core.fortune.type.YoN;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.role.type.FortuneRole;
import com.creditharmony.core.users.dao.OrgDao;
import com.creditharmony.core.users.entity.Org;
import com.creditharmony.core.users.entity.Role;
import com.creditharmony.core.users.entity.User;
import com.creditharmony.core.users.type.BaseDeptOrgType;
import com.creditharmony.core.users.type.FortuneOrgType;
import com.creditharmony.core.users.util.UserUtils;
import com.creditharmony.core.web.BaseController;
import com.creditharmony.fortune.common.entity.Check;
import com.creditharmony.fortune.common.entity.UserRoleOrgEx;
import com.creditharmony.fortune.common.service.CheckManager;
import com.creditharmony.fortune.contract.constant.Constant;
import com.creditharmony.fortune.contract.constant.Result;
import com.creditharmony.fortune.contract.entity.Contract;
import com.creditharmony.fortune.contract.entity.ContractChange;
import com.creditharmony.fortune.contract.service.ContractChangeManager;
import com.creditharmony.fortune.contract.service.ContractManager;
import com.creditharmony.fortune.contract.util.ExporContractExcel;
import com.creditharmony.fortune.contract.util.StatisticsUtil;
import com.creditharmony.fortune.contract.view.ContractExcelView;
import com.creditharmony.fortune.contract.view.ContractOnfileExcelView;
import com.creditharmony.fortune.contract.view.ContractParamView;
import com.creditharmony.fortune.contract.view.ContractView;
import com.creditharmony.fortune.customer.entity.LoanApply;
import com.creditharmony.fortune.utils.FortuneDictUtil;
import com.creditharmony.fortune.utils.RoleOrgUtil;

/**
 * 状态变更
 * 
 * @Class Name ContractChangeApplyList
 * @author 李放
 * @Create In 2015年12月7日
 */
@Controller
@RequestMapping(value = "${adminPath}/contract")
public class ContractChangeApplyList extends BaseController {

	@Autowired
	private ContractChangeManager contractChangeManager;

	@Autowired
	private ContractManager contractManager;
	@Autowired
	private CheckManager checkManager;
	@Autowired
	private OrgDao orgDao;
	
	/**
	 * 合同列表页面 update By 王鹏飞 2015年12月30日 By 李放
	 * 
	 * @param contractInformation
	 * @param model
	 * @return 合同列表页面
	 */
	@RequestMapping(value = "/changeApplyList")
	public String changeApplyList(@ModelAttribute("contractInformation") ContractParamView contractInformation, 
									Model model,HttpServletRequest request,	HttpServletResponse response) {
		User user = (User) UserUtils.getSession().getAttribute(SystemConfigConstant.SESSION_USER_INFO);
		List<Role> roles = user.getRoleList();
		// 综合内勤
		for (int i = 0, k = roles.size(); i < k; i++) {
			if (roles.get(i).getId().equals(FortuneRole.OFFICE_STAFF.id)) {
				contractInformation.setNameId(user.getDepartment().getId());
				contractInformation.setName(user.getDepartment().getName());
				break;
			}
		}
		contractInformation.setContSignStatus(SingnState.YQS.value);
		contractInformation.setDictChangeStatus(AppalyState.DSH.value);
		Page<ContractView> pageview = contractManager.listContract(new Page<ContractView>(request, response), contractInformation);
		model.addAttribute("page", pageview).addAttribute(	"ContractInformation", contractInformation);
		// 自动化合同版本
		model.addAttribute("autoContVersion", Arrays.asList(new String[] { ContractVesion.D17BB.value,ContractVesion.D24BB.value}));
		FortuneDictUtil.addDicts(model, new String[]{"tz_contract_vesion", "tz_contract_state", "tz_filed_state", "tz_contract_owner"});
		return "contract/changeApplyList";
	}

	/**
	 * 变更申请详细页面 2015年12月16日 By 李放
	 * 
	 * @param contCode
	 * @param request
	 * @param response
	 * @param model
	 * @return 变更申请页面
	 */
	@RequestMapping(value = "/changeApply")
	public String changeApply(String contCode, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		User userInfo = (User) UserUtils.getSession().getAttribute(SystemConfigConstant.SESSION_USER_INFO);
		ContractView cf = contractChangeManager
				.getContractInformation(contCode);
		List<String> roleIds = new ArrayList<String>();
		roleIds.add(FortuneRole.FINANCING_MANAGER.id);
		if(null!=userInfo){
			cf.setChangeApply(userInfo.getName());
		}
		model.addAttribute("cf", cf);
		return "detailed/changeApply";
	}

	/**
	 * 带参数进入合同调拨详细页
	 * 
	 * @param contCode
	 *            合同编号
	 * @param request
	 * @param response
	 * @param model
	 * @return 合同调拨详细页
	 */
	@RequestMapping(value = "/transferContract")
	public String transferContract(String contCode, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		List<String> roleIds = new ArrayList<String>();
		roleIds.add(FortuneRole.FINANCING_MANAGER.id);
		List<UserRoleOrgEx> list2 = RoleOrgUtil.findOrgType(
				FortuneOrgType.TEAM.key, roleIds);
		model.addAttribute("list", list2);
		Contract contract = contractManager.getContract(contCode);
		User manger = UserUtils.get(contract.getContBelongEmpid());
		contract.setContTranferDay(new Date());
		model.addAttribute("contract", contract);
		model.addAttribute("contVersion", contract.getContVersion());
		model.addAttribute("userName", UserUtils.getUser(UserUtils.getUserId()).getName());
		model.addAttribute("mangerName", manger.getName());
		model.addAttribute("mangerId", contract.getContBelongEmpid());
		return "detailed/transfer";
	}

	/**
	 * 合同查询及状态变更导出 导出数据
	 * 
	 * @param contractInformation
	 *            查询条件
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return 文件流
	 */
	@RequestMapping(value = "/changeApplyList/exportOrder")
	public void exportOrder(@ModelAttribute("contractInformation") ContractParamView contractInformation,String ids,HttpServletRequest request, HttpServletResponse response) {
			User user = (User) UserUtils.getSession().getAttribute(SystemConfigConstant.SESSION_USER_INFO);
			List<Role> roles = user.getRoleList();
			// 综合内勤
			for (int i = 0, k = roles.size(); i < k; i++) {
				if (roles.get(i).getId()
						.equals(FortuneRole.OFFICE_STAFF.id)) {
					contractInformation.setContStoresId(user.getDepartment()
							.getId());
					contractInformation.setName(user.getDepartment().getName());
					break;
				}
			}
			contractInformation.setContSignStatus(SingnState.YQS.value);
			contractInformation.setDictChangeStatus(AppalyState.DSH.value);
			
			//权限控制
			User userInfo = (User) UserUtils.getSession().getAttribute(SystemConfigConstant.SESSION_USER_INFO);
			Org org = orgDao.get(userInfo.getDepartment().getId());
			if(!BaseDeptOrgType.LENDER_DEPT.key.equals(org.getType())){
				String nameIds = contractInformation.getNameId();
				if (StringUtils.isNotBlank(nameIds)) {
					contractInformation.setNameId(nameIds);
				} else {
					contractInformation.setNameId(userInfo.getDepartment().getId());
				}
			}
			
			Map<String, Object> map = new HashMap<String, Object>();
			if(null!=ids && !"".equals(ids)){
				map.put("contCodeids", ids.split(","));
			}
			if(null!=contractInformation.getNameId() && !"".equals(contractInformation.getNameId())){
				map.put("nameIds", contractInformation.getNameId().split(","));
			}
			map.put("contract", contractInformation);
			ExporContractExcel.exportData(map, response);
	}

	/**
	 * 根据选择导出 导出数据
	 * 
	 * @param ids
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return 文件流
	 */
	@RequestMapping(value = "/changeApplyList/checkoutOrder")
	public void checkoutOrder(String ids, HttpServletRequest request, HttpServletResponse response) {
		try {
			List<ContractExcelView> list = contractManager
					.listContractChangeCheckout(ids);
			ExportExcel exl = new ExportExcel(Constant.CONTRACT_CHANGE_PREFIX,
					ContractExcelView.class);
			exl.setDataList(list);
			
			response.reset();
	        response.setContentType("application/octet-stream; charset=UTF-8");
	        response.setCharacterEncoding("UTF-8");
	        String fileName =  StatisticsUtil	.getDownloadFileName(Constant.CONTRACT_CHANGE_PREFIX);
			response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ";filename*=UTF-8''" + fileName);
			
			exl.write(response, fileName);
			exl.dispose();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("导出excel失败！失败信息：" + e.getMessage());
		}
	}

	/**
	 * 理财经理列表
	 * 
	 * @param request
	 * @param response
	 * @return 理财经理集合
	 */
	@RequestMapping(value = "/changeApplyList/listManager")
	@ResponseBody
	public String listManager(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<String> roleIds = new ArrayList<String>();
		roleIds.add(FortuneRole.FINANCING_MANAGER.id);
		List<UserRoleOrgEx> list2 = RoleOrgUtil.findOrgType(
				FortuneOrgType.TEAM.key, roleIds);
		map.put("list", list2);
		String t = jsonMapper.toJson(map);
		return t;
	}

	/**
	 * 将合同分配给理财经理
	 * 
	 * @param ids
	 *            合同id
	 * @param userId
	 *            理财经理id
	 * @param request
	 * @param response
	 * @return 分配结果
	 */
	@RequestMapping(value = "/changeApplyList/distributeToManager")
	@ResponseBody
	public String distributeToManager(String ids, String userId,
			HttpServletRequest request, HttpServletResponse response) {
		User user = UserUtils.get(userId);
		if (user == null) {
			return BooleanType.FALSE;
		}
		contractManager.distrubuteToFinacialManager(ids, userId);
		return BooleanType.TRUE;
	}

	/**
	 * 合同调回
	 * 
	 * @param ids
	 *            合同id
	 * @param request
	 * @param response
	 * @return 调回结构
	 */
	@RequestMapping(value = "/changeApplyList/recallContract")
	@ResponseBody
	public String recallContract(String ids, HttpServletRequest request,
			HttpServletResponse response) {
		contractManager.recallContractFormFinacialManager(ids);
		return BooleanType.TRUE;
	}

	/**
	 * 获取合同信息
	 * 
	 * @param contCode
	 *            合同编号
	 * @param request
	 * @param response
	 * @param model
	 * @return 合同信息
	 */
	@RequestMapping(value = "/changeApplyList/getContract")
	@ResponseBody
	public String getContract(String contCode, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		Contract contract = contractManager.getContract(contCode);
		Map<String, Object> map = new HashMap<String, Object>();
		if (contract == null) {
			map.put("result", BooleanType.FALSE);
			map.put("v", Constant.PARAMETER_ERROR);
			String t = jsonMapper.toJson(map);
			return t;
		}
		if (!ContractOwner.LCJL.value.equals(contract.getDictContBelong())) {
			map.put("result", BooleanType.FALSE);
			map.put("v", Result.two.value);
			String t = jsonMapper.toJson(map);
			return t;
		}
		if (!ContractState.KC.value.equals(contract.getDictContStatus())) {
			map.put("result", BooleanType.FALSE);
			map.put("v", Result.zero.value);
			String t = jsonMapper.toJson(map);
			return t;
		}
		map.put("result", BooleanType.TRUE);
		User manger = UserUtils.get(contract.getContBelongEmpid());
		map.put("contVersion", contract.getContVersion());
		map.put("dictContStatus", contract.getDictContStatus());
		map.put("contIncomeDay", DateUtils.formatDate(
				contract.getContIncomeDay(), Constant.DATE_YYYY_MM_DD));
		map.put("contTranferDay",
				DateUtils.formatDate(new Date(), Constant.DATE_YYYY_MM_DD));
		map.put("contVersion", contract.getContVersion());
		map.put("userName", UserUtils.getUser(UserUtils.getUserId()).getName());
		map.put("mangerName", manger.getName());
		String t = jsonMapper.toJson(map);
		return t;
	}

	/**
	 * 合同调拨
	 * 
	 * @param contractInformation
	 * @return 合同调拨信息
	 */
	@RequestMapping(value = "/changeApplyList/transferToManager")
	@ResponseBody
	public String transferToManager(
			@ModelAttribute("contract") ContractParamView contractInformation) {
		Contract contract = contractManager.getContract(contractInformation
				.getContCode());
		Map<String, Object> map = new HashMap<String, Object>();
		if (contract == null) {
			map.put("result", BooleanType.FALSE);
			map.put("v", Constant.PARAMETER_ERROR);
			String t = jsonMapper.toJson(map);
			return t;
		}
		// 是否属于理财经理
		if (!ContractOwner.LCJL.value.equals(contract.getDictContBelong())) {
			map.put("result", BooleanType.FALSE);
			map.put("v", Result.two.value);
			String t = jsonMapper.toJson(map);
			return t;
		}
		// 是否已分配
		if (!ContractState.YFP.value.equals(contract.getDictContStatus())) {
			map.put("result", BooleanType.FALSE);
			map.put("v", Result.zero.value);
			String t = jsonMapper.toJson(map);
			return t;
		}
		// 是否属于同一理财经理
		if (contract.getContBelongEmpid().trim()
				.equals(contractInformation.getContBelongEmpid().trim())) {
			map.put("result", BooleanType.FALSE);
			map.put("v", Result.one.value);
			String t = jsonMapper.toJson(map);
			return t;
		}
		contractManager
				.transferToFinacialManager(contract, contractInformation);
		map.put("result", BooleanType.TRUE);
		String t = jsonMapper.toJson(map);
		return t;
	}

	/**
	 * 变更申请
	 * 
	 * @param contractChange
	 * @param request
	 * @param response
	 * @return 变更申请结果
	 */
	@RequestMapping(value = "/changeApplyList/applyChange")
	@ResponseBody
	public String applyChange(ContractChange contractChange,
			HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		// 查询合同变更申请是否存在未审核记录
		ContractChange contractChangeDb = contractChangeManager.getContractChange(contractChange.getContCode(), AppalyState.DSH.value);
		if (contractChangeDb != null) {
			map.put("result", BooleanType.FALSE);
			map.put("v", Result.zero.value);
			String t = jsonMapper.toJson(map);
			return t;
		}
		// 合同基本信息
		Contract contract = contractManager.getContract(contractChange.getContCode());
		if (contract == null) {
			map.put("result", BooleanType.FALSE);
			map.put("v", Constant.PARAMETER_ERROR);
			String t = jsonMapper.toJson(map);
			return t;
		}
		if(contract.getDictContStatus()!=null){
			// YSZF("1")遗失作废
			if (contract.getDictContStatus().equals(ContractState.YSZF.value)) {
				map.put("result", BooleanType.FALSE);
				map.put("v", Result.one.value);
				String t = jsonMapper.toJson(map);
				return t;
			}
			// QCZF("2") 签错作废
			if (contract.getDictContStatus().equals(ContractState.QCZF.value)) {
				map.put("result", BooleanType.FALSE);
				map.put("v", Result.two.value);
				String t = jsonMapper.toJson(map);
				return t;
			}
			// BBZF("4") 版本作废
			if (contract.getDictContStatus().equals(ContractState.BBZF.value)) {
				map.put("result", BooleanType.FALSE);
				map.put("v", Result.three.value);
				String t = jsonMapper.toJson(map);
				return t;
			}
		}
		

		/*// 转借-门店之间转借，合同状态为门店库存
		if (ContractChangeType.ZJ.value.equals(contractChange
				.getDictChangeType())) {
			if (!contract.getDictContStatus().equals(ContractState.KC.value)
					|| !contract.getDictContBelong().equals(
							ContractOwner.MDKC.value)) {
				map.put("result", BooleanType.FALSE);
				map.put("v", Result.four.value);
				String t = jsonMapper.toJson(map);
				return t;
			}
			// 转出营业部
			if (contractChange.getChangeInStoresId().indexOf(",") > 0) {
				String changeInStoresId = contractChange.getChangeInStoresId()
						.substring(
								0,
								contractChange.getChangeInStoresId().indexOf(
										","));
				contractChange.setChangeInStoresId(changeInStoresId);
			} else {
				contractChange.setChangeInStoresId(contractChange
						.getChangeInStoresId());
			}
			// 检测是否同一门店
			if (contract.getContStoresId().equals(
					contractChange.getChangeInStoresId())) {
				map.put("result", BooleanType.FALSE);
				map.put("v", Result.five.value);
				String t = jsonMapper.toJson(map);
				return t;
			}
		}*/
		
		//留痕
		LoanApply loanApply = new LoanApply();
		loanApply.setContractNumber(contractChange.getContCode());
		LoanApply apply=contractChangeManager.lendQueryContract(loanApply);
		if (apply != null && StringUtils.isNotBlank(apply.getApplyCode())) {
			checkManager.addCheck(apply.getApplyCode(), "发起变更申请", "合同变更");
		}
		
		//新增变更申请
		contractChange.setChangeOutStoresId(contract.getContStoresId());
		User user = UserUtils.getUser();
		contractChange.setDictChangeStatus(AppalyState.DSH.value);
		contractChange.setChangeDay(new Date());
		contractChange.setChangeApply(user.getId());
		contractChangeManager.insertContractChange(contractChange);
		
		
		//更新合同的状态
		Contract c=new Contract();
		c.setContCode(contract.getContCode());
		c.setDictContStatus(ContractState.ZFSPZ.value);
		contractManager.updateContractState(c);
		map.put("result", BooleanType.TRUE);
		String t = jsonMapper.toJson(map);
		return t;
	}

	/**
	 * 审核变更申请
	 * 
	 * @param contractChange
	 * @param request
	 * @param response
	 * @return 变更申请结果
	 */
	@RequestMapping(value = "/changeApplyList/updateApplyChange")
	@ResponseBody
	public String updateApplyChange(ContractChange contractChange,
			HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 变更申请信息
		ContractChange contractChangeDb = contractChangeManager
				.getApplyChange(contractChange.getId());
		if (contractChangeDb == null) {
			map.put("result", BooleanType.FALSE);
			map.put("v", Result.zero.value);
			String t = jsonMapper.toJson(map);
			return t;
		}
		// 判断是否审核
		if (!contractChangeDb.getDictChangeStatus().equals(AppalyState.DSH.value)) {
			map.put("result", BooleanType.FALSE);
			map.put("v", Result.one.value);
			String t = jsonMapper.toJson(map);
			return t;
		}
		User user = UserUtils.getUser();
		contractChange.setChangeCheckById(user.getId());
		contractChange.setChangeCheckDate(new Date());
		contractChange.preUpdate();
		// 审核不通过
		if (contractChange.getChangeCheckResult().equals(YoN.FOU.value)) {
			contractChange.setDictChangeStatus(AppalyState.SHBTG.value);
			//修改合同的状态为，退回审批
			Contract contract = contractManager.getContract(contractChangeDb.getContCode());
			contract.setDictContStatus(ContractState.KC.value);  //审批退回时，此状态变成申请前得状态
			contract.preUpdate();
			contractChangeManager.updateContract(contract, contractChange);
			
			//留痕
			LoanApply loanApply = new LoanApply();
			loanApply.setContractNumber(contractChange.getContCode());
			LoanApply apply=contractChangeManager.lendQueryContract(loanApply);
			if (apply != null && StringUtils.isNotBlank(apply.getApplyCode())) {
				checkManager.addCheck(apply.getApplyCode(), "审核不通过", "合同变更审核");
			}
			
			map.put("result", BooleanType.TRUE);
			String t = jsonMapper.toJson(map);
			return t;
		}
		// 审核通过
		Contract contract = contractManager.getContract(contractChangeDb
				.getContCode());
		if (contract == null) {
			map.put("result", BooleanType.FALSE);
			map.put("v", Result.two.value);
			String t = jsonMapper.toJson(map);
			return t;
		}
		contract.preUpdate();
		// 通过，修改合同信息
		if (contractChange.getChangeCheckResult().equals(YoN.SHI.value)) {
			contractChange.setDictChangeStatus(AppalyState.SHTG.value);
			contractChange.setChangeCheckDate(new Date());
			if (ContractChangeType.ZJ.value.equals(contractChangeDb
					.getDictChangeType())) {// 3 转借
				if (!contract.getDictContStatus()
						.equals(ContractState.KC.value)) {
					map.put("result", BooleanType.FALSE);
					map.put("v", Result.three.value);
					String t = jsonMapper.toJson(map);
					return t;
				}
				contract.setContStoresId(contractChangeDb.getChangeInStoresId());
				contract.setDictContSource(contractChangeDb
						.getChangeOutStoresId());
				contract.setDictContBelong(ContractOwner.MDKC.value);
				contract.setContBelongEmpid("");
				contract.setTransferDay(contractChange.getChangeCheckDate());
			} else if (ContractChangeType.YS.value.equals(contractChangeDb
					.getDictChangeType())) {// 1 遗失
				contract.setDictContStatus(ContractState.YSZF.value);
				contract.setInvalidDay(contractChange.getChangeCheckDate());
			} else if (ContractChangeType.ZF.value.equals(contractChangeDb
					.getDictChangeType())) {// 2 作废
				contract.setDictContStatus(ContractState.QCZF.value);
				contract.setInvalidDay(contractChange.getChangeCheckDate());
			}
			contractChangeManager.updateContract(contract, contractChange);
			
			//留痕
			LoanApply loanApply = new LoanApply();
			loanApply.setContractNumber(contractChange.getContCode());
			LoanApply apply=contractChangeManager.lendQueryContract(loanApply);
			if (apply != null && StringUtils.isNotBlank(apply.getApplyCode())) {
				checkManager.addCheck(apply.getApplyCode(), "审核通过", "合同变更审核");
			}
			
		}
		map.put("result", BooleanType.TRUE);
		String t = jsonMapper.toJson(map);
		return t;
	}

	/**
	 * 归档文件导入
	 * 
	 * @param file
	 * @param request
	 * @param response
	 * @param model
	 * @return 文件上传结果
	 */
	@RequestMapping(value = "/changeApplyList/importExcel")
	@ResponseBody
	public String importExcel(MultipartFile file, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		try {
			ImportExcel ei = new ImportExcel(file, 0, 0);
			List<ContractOnfileExcelView> list = ei
					.getDataList(ContractOnfileExcelView.class);
			if (list == null || list.size() == 0) {
		
				return jsonMapper.toJson("上传文件没有数据");
			}
			
			//权限控制
			User userInfo = (User) UserUtils.getSession().getAttribute(SystemConfigConstant.SESSION_USER_INFO);
			Org org = orgDao.get(userInfo.getDepartment().getId());
			String storeId = null;
			if(!BaseDeptOrgType.LENDER_DEPT.key.equals(org.getType())){
				storeId = userInfo.getDepartment().getId();
			}
			
			for (ContractOnfileExcelView contractOnfileExcelView : list) {
				Contract contract = contractManager.getContract(contractOnfileExcelView.getContCode());
				if (contract != null && !contract.getDictContFileStatus().equals(PigeonholeState.YGD.value)) {
					contract.setDictContFileStatus(PigeonholeState.YGD.value);
					contract.setContFileTime(new Date());
					contract.setOnfileDay(contract.getContFileTime());
					contract.preUpdate();
					if (StringUtils.isNotBlank(storeId)) {
						contract.setContStoresId(storeId);
					}
					contractManager.updateContractFileStatus(contract);
				}
			}

		} catch (Exception e) {
		
			return jsonMapper.toJson("导入失败！失败信息：" + e.getMessage());
		}
		return jsonMapper.toJson("文件上传成功！");
	}

	/**
	 * 合同明细
	 * 2016年4月21日
	 * By 郭才林
	 * @param loanApply
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/changeApplyList/lendQueryContract")
	public String lendQueryContract(LoanApply loanApply,HttpServletRequest request,HttpServletResponse response, Model model) {
		LoanApply apply=contractChangeManager.lendQueryContract(loanApply);
		String[] dictTypes = new String[]{"tz_lend_state"};
		FortuneDictUtil.addDicts(model, dictTypes);
		model.addAttribute("lend", apply);
		return "contract/queryDetail";
	}
	/**
	 * 全程留痕
	 * 2016年3月23日
	 * By 肖长伟
	 * @param request
	 * @param response
	 * @param model
	 * @param check
	 * @return
	 */
	@RequestMapping(value = "/changeApplyList/fullTrace")
	public String fullTrace(HttpServletRequest request, HttpServletResponse response,
			Model model,LoanApply loanApply){
		
		LoanApply apply=contractChangeManager.lendQueryContract(loanApply);
		if (!ObjectHelper.isEmpty(apply)) {
			Check check=new Check();
			check.setApplyCode(apply.getApplyCode());
			Page<Check> page = new Page<Check>(request, response);
			Page<Check> pageview = checkManager.findCheckList(page, check);
			page.setFuncName("subPage");
			model.addAttribute("page", pageview);
			model.addAttribute("check", check);
		}
		return "lendApplyLook/popFullTrace";
	}
}
