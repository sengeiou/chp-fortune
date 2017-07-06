package com.creditharmony.fortune.contract.web;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
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

import com.creditharmony.common.util.IdGen;
import com.creditharmony.core.common.type.SystemConfigConstant;
import com.creditharmony.core.fortune.type.AppalyState;
import com.creditharmony.core.fortune.type.ContractState;
import com.creditharmony.core.fortune.type.ContractVesion;
import com.creditharmony.core.fortune.type.DistributeState;
import com.creditharmony.core.fortune.type.SingnState;
import com.creditharmony.core.fortune.type.YoN;
import com.creditharmony.core.users.entity.User;
import com.creditharmony.core.users.util.UserUtils;
import com.creditharmony.core.web.BaseController;
import com.creditharmony.fortune.contract.constant.Constant;
import com.creditharmony.fortune.contract.constant.Result;
import com.creditharmony.fortune.contract.entity.ContractApply;
import com.creditharmony.fortune.contract.service.ContractApplyManager;

/** 
 * 合同申请
 * @Class Name ContractApplyController
 * @author 李放
 * @Create In 2015年12月8日
 */
@Controller
@RequestMapping(value="${adminPath}/contract")
public class ContractApplyController extends BaseController{
	
	@Autowired
	private ContractApplyManager contractApplyManager;

	
	
	/**
	 * 初始化合同申请
	 * 2015年12月21日
	 * By 王鹏飞
	 * 
	 * @param contractApply
	 * @param request
	 * @param response
	 * @param m
	 * @return 合同申请页面
	 */
	@RequestMapping(value ="/applyContract")
	public String applyContract(
			@ModelAttribute("contractApply") ContractApply contractApply,
			HttpServletRequest request, HttpServletResponse response, Model m) {
		ContractApply ct = new ContractApply();
		User user = (User) UserUtils.getSession().getAttribute(SystemConfigConstant.SESSION_USER_INFO);;
		ct.setOrgName(user.getDepartment().getName());
		ct.setApplyBy(user.getName());
		m.addAttribute("contractApply", ct);
		return "contract/applyContract";
	}
	
	/**
	 * 提交合同申请
	 * 2015年12月21日
	 * By 王鹏飞
	 * 
	 * @param contractApply
	 * @param request
	 * @param response
	 * @param m
	 * @return 合同申请结果
	 */
	@RequestMapping(value ="/contractApply/addContractApply")
	@ResponseBody
	public String addContractApply(
			@ModelAttribute("contractApply") ContractApply contractApply,
			HttpServletRequest request, HttpServletResponse response, Model m) {
		// 获取用户信息
		User user = (User) UserUtils.getSession().getAttribute(SystemConfigConstant.SESSION_USER_INFO);;
		// 检测用户营业务部信息,合同版本信息
		if (StringUtils.isEmpty(user.getDepartment().getId())) {
			return Constant.PARAMETER_ERROR;
		}
		if (StringUtils.isEmpty(contractApply.getContVersion())) {
			return Constant.PARAMETER_ERROR;
		}
		contractApply.setContStoresId(user.getDepartment().getId());

		// 同一版本的合同每月只可申请一次
		Calendar end_date = Calendar.getInstance();
		end_date.set(Calendar.DAY_OF_MONTH, 1);
		end_date.set(Calendar.HOUR_OF_DAY, 0);
		end_date.set(Calendar.MINUTE, 0);
		end_date.set(Calendar.SECOND, 0);
		Date end = end_date.getTime();
		String result = contractApplyManager.getCountOfMonthlyApply(
				contractApply.getContStoresId(),
				contractApply.getContVersion(), end);
		if (!StringUtils.isEmpty(result) && Integer.valueOf(result) > 0) {
			return String.valueOf(Result.zero.value);
		}

		contractApply.setContractId(IdGen.uuid());
		contractApply.setApplyBy(user.getId());
		contractApply.setApplyDay(new Date());
		contractApply.setApplyStatus(AppalyState.DSH.value);
		contractApplyManager.addContractApply(contractApply);
		return String.valueOf(Result.one.value);
	}
	
	/**
	 * 批量合同申请审核通过
	 * 2015年12月23日
	 * @author 王鹏飞
	 * 
	 * @param ids 合同申请id 多个id使用 ‘,’分割开 如： 1,2,3
	 * @param request
	 * @param response
	 * @param m
	 * @return 申请审核通过结果
	 */
	@RequestMapping(value = "/contractApply/passApplication")
	@ResponseBody
	public String passApplication(String ids, HttpServletRequest request,
			HttpServletResponse response, Model m) {
		int i = contractApplyManager.getYSHContractApply(ids);
		if(i > 0){
			return String.valueOf(Result.one.value);
		}
		contractApplyManager.updateContractApplyStatus(ids,
				AppalyState.SHTG.value, YoN.SHI.value.toString(),
				DistributeState.WPF.value, SingnState.WQS.value);
		return String.valueOf(Result.zero.value);
	}
	
	/**
	 * 批量合同申请审核不通过
	 * 2015年12月23日
	 * @author 王鹏飞
	 * 
	 * @param ids 合同申请id 多个id使用 ‘,’分割开 如： 1,2,3
	 * @param dictApplyRefuseDemo 拒绝内容
	 * @param request
	 * @param response
	 * @param m
	 * @return 申请审核不通过结果
	 */
	@RequestMapping(value = "/contractApply/rejectApplication")
	@ResponseBody
	public String rejectApplication(String ids, String dictApplyRefuseDemo,
			HttpServletRequest request, HttpServletResponse response, Model m) {
		int i = contractApplyManager.getYSHContractApply(ids);
		if(i > 0){
			return String.valueOf(Result.one.value);
		}
		contractApplyManager.updateContractApplyStatus(ids,
				AppalyState.SHBTG.value, YoN.FOU.value.toString(),
				dictApplyRefuseDemo);
		return String.valueOf(Result.zero.value);
	}
	
	/**
	 * 请求到制定页面
	 * @param select
	 * @return 定制页面
	 */
	@RequestMapping(value = "/goPage")
	public String goPage(String goAheadPage) {
		return "contract/"+goAheadPage;
	}
	
	/**
	 * 根据合同版本的使用情况计算合同推荐使用数量
	 * 普通合同推荐数量：全部使用合同（没有版本区别）* 2 - 上月申请版本库存数量
	 * 年年金的合同推荐数量 = 年年金上月使用量 * 2 - 先申请版本库存数量
	 * @param contVersion 版本号
	 * @param request 
	 * @param response
	 * @param m
	 * @return 合同推荐信息
	 */
	@RequestMapping(value ="/contractApply/changVersion")
	@ResponseBody
	public String changVersion(
			@ModelAttribute("contractApply") String contVersion,
			HttpServletRequest request, HttpServletResponse response, Model m) {
		Map<String, Integer> ct = new HashMap<String, Integer>();
		User loginUser = (User) UserUtils.getSession().getAttribute(SystemConfigConstant.SESSION_USER_INFO);
		// 前一个月的使用数量
		Calendar lastMonth = Calendar.getInstance();
		lastMonth.add(Calendar.MONTH, -1);
		lastMonth.set(Calendar.DAY_OF_MONTH, 1);
		lastMonth.set(Calendar.HOUR_OF_DAY, 0);
		lastMonth.set(Calendar.MINUTE, 0);
		lastMonth.set(Calendar.SECOND, 0);
		// 当前月份的使用数量
		Calendar currentMonth = Calendar.getInstance();
		currentMonth.set(Calendar.DAY_OF_MONTH, 1);
		currentMonth.set(Calendar.HOUR_OF_DAY, 0);
		currentMonth.set(Calendar.MINUTE, 0);
		currentMonth.set(Calendar.SECOND, 0);
		String lastMonthCount = null;
		// 需要特殊处理的合同版本
		if (Constant.exceptionVersionList.contains(contVersion)) {
			lastMonthCount = contractApplyManager.getCountOfUsedContract(
					loginUser.getDepartment().getId(), ContractState.YCJ.value,
					lastMonth.getTime(), currentMonth.getTime(), contVersion);
		} else {
			lastMonthCount = contractApplyManager.getCountOfUsedContract(
					loginUser.getDepartment().getId(), ContractState.YCJ.value,
					lastMonth.getTime(), currentMonth.getTime(), null);
		}
		// 现有库存
		String storeCount = contractApplyManager.getCountOfStoredContract(
				loginUser.getDepartment().getId(), ContractState.KC.value,
				contVersion);
		// 1.6.1版本库存
		String store161Count = contractApplyManager.getCountOfStoredContract(
				loginUser.getDepartment().getId(), ContractState.KC.value,
				ContractVesion.D161BB.value);
		// 1.7版本库存
		String store17Count = contractApplyManager.getCountOfStoredContract(
				loginUser.getDepartment().getId(), ContractState.KC.value,
				ContractVesion.D17BB.value);
		ct.put("applyAlreadyuse", Integer.valueOf(lastMonthCount));
		ct.put("applyInventory", Integer.valueOf(storeCount));
		// int recommendCount = Integer.valueOf(lastMonthCount) * 2 - Integer.valueOf(storeCount);
		// 推荐数量=上月有效使用量（1.5+1.6+1.6.1+2.2+2.3上月总有效使用量）*2-1.6.1版本库存-1.7版本库存
		int recommendCount = Integer.valueOf(lastMonthCount) * 2 
				- Integer.valueOf(store161Count) 
				- Integer.valueOf(store17Count);
		int distNo = recommendCount < 0 ? 0 : recommendCount;
		ct.put("applyDistNo", Integer.valueOf(distNo));
		String t = jsonMapper.toJson(ct);
		return t;
	}
	
	
	
}
