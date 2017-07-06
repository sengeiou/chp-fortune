package com.creditharmony.fortune.creditor.matching.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.creditharmony.common.util.Global;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.role.type.BaseRole;
import com.creditharmony.core.web.BaseController;
import com.creditharmony.fortune.contract.constant.Result;
import com.creditharmony.fortune.creditor.matching.constant.Constant;
import com.creditharmony.fortune.creditor.matching.entity.SendCountConfig;
import com.creditharmony.fortune.creditor.matching.service.MatchingCreditorManager;
import com.creditharmony.fortune.creditor.matching.service.DistributeRecommendationManager;
import com.creditharmony.fortune.creditor.matching.view.SendCountConfigView;

/**
 * 文件制作人员配置表
 * 
 * @Class Name DistributeConfigController
 * @author 王鹏飞
 * @Create In 2016年02月04日
 */
@Controller
@RequestMapping("${adminPath}/matchingcreditor")
public class DistributeConfigController extends BaseController {
	
	@Autowired
	private DistributeRecommendationManager sendCountConfigManager;

	@Autowired
	private MatchingCreditorManager matchingCreditorManager;
	
	/**
	 * 文件制作人员配置列表
	 * @param request
	 * @param response
	 * @param m
	 * @param page
	 * @param sendCountConfigView 查询参数
	 * @return 文件制作人员配置表页面
	 */
	@RequestMapping(value = "/distributeConfigList")
	public String distributeConfigList(HttpServletRequest request,
			HttpServletResponse response, Model model,
			SendCountConfigView sendCountConfigView) {

		Map<String, Object> map = new HashMap<String, Object>();

		// 直接跳转到列表页面
		map.put("name", sendCountConfigView.getName());
		map.put("status", sendCountConfigView.getStatus());

		Page<SendCountConfigView> pageview = sendCountConfigManager
				.listDistributeConfig(new Page<SendCountConfigView>(request, response), map);

		model.addAttribute("page", pageview);
		return "creditor/matching/distributeConfigList";
	}
	
	/**
	 * 合同制作员工信息
	 * @param request 
	 * @param response
	 * @param model 
	 * @return 合同制作专员集合
	 */
	@RequestMapping(value = "/listMakeContractStaff")
	@ResponseBody
	public String listMakeContractStaff(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		Map<String, Object> map = new HashMap<String, Object>();
		//文件制作专员,文件制作组长,文件制作副组长,文件制作主管
		String[] roleids = {
				BaseRole.FILE_MAKE_MAKER.id,
				BaseRole.FILE_MAKE_TEAM_LEADER.id,
				BaseRole.FILE_MAKE_DEPUTY_LEADER.id,
				BaseRole.FILE_MAKE_LEADER.id
				};
		map.put("roleId", roleids);
		map.put("status", Global.YES);
		List<SendCountConfigView> list2 = sendCountConfigManager
				.listMakeContractStaff(map);
		map.put("list", list2);
		String t = jsonMapper.toJson(map);
		return t;
	}
	
	
	/**
	 * 添加合同员工配置
	 * @param request 
	 * @param response
	 * @param model
	 * @param userId 
	 * @return 合同制作专员集合
	 */
	@RequestMapping(value = "/addContractStaff")
	@ResponseBody
	public String addContractStaff(HttpServletRequest request,
			HttpServletResponse response, Model model,SendCountConfig add) {
		if(StringUtils.isEmpty(add.getUserId())){
			//未选择员工
			return String.valueOf(Result.one.value);
		}
		SendCountConfig sendCountConfig = sendCountConfigManager.getSendCountConfig(add.getUserId());
		if(sendCountConfig != null){
			//重复选择员工
			return String.valueOf(Result.two.value);
		}
		//默认数量
		add.setUserSendCount(Constant.DEF_COUNT * Constant.DEF_AGAIN_COUNT_PERCENT / 100);
		add.setLeaderSendCount(Constant.DEF_COUNT * Constant.DEF_AGAIN_COUNT_PERCENT / 100);
		add.preInsert();
		sendCountConfigManager.insertSendCountConfig(add);
		return String.valueOf(Result.zero.value);
	}
	
	/**
	 * 删除合同制作专员配置
	 * @param request
	 * @param response
	 * @param model
	 * @param ids 主键id集合
	 * @return 处理结果
	 */
	@RequestMapping(value = "/delContractStaff")
	@ResponseBody
	public String delContractStaff(HttpServletRequest request,
			HttpServletResponse response, Model model,String ids) {
		if(StringUtils.isEmpty(ids)){
			return String.valueOf(Result.one.value);
		}
		String[] id = ids.split(",");
		sendCountConfigManager.delSendCountConfig(id);
		return String.valueOf(Result.zero.value);
	}
	
	/**
	 * 修改合同制作专员派发数量
	 * @param request
	 * @param response
	 * @param model
	 * @param sendCount 派发数量
	 * @param id 主键id
	 * @param status 在岗状态 0 离岗 
	 * @return 处理结果
	 */
	@RequestMapping(value = "/updateSendCount")
	@ResponseBody
	public String updateSendCount(HttpServletRequest request,
			HttpServletResponse response, Model model, int sendCount, String id,int status) {
		SendCountConfig sendCountConfig = sendCountConfigManager.selectByPrimaryKey(id);
		if (sendCountConfig == null) {
			return String.valueOf(Result.one.value);
		}
		if (sendCountConfig != null) {
			sendCountConfig.setLeaderSendCount(sendCount);
			sendCountConfig.setStatus(status);
			sendCountConfig.preUpdate();
			sendCountConfigManager.updateSendCountConfig(sendCountConfig);
		}
		return String.valueOf(Result.zero.value);
	}
	
	/**
	 * 批量在岗/离岗
	 * 2016年3月18日
	 * By 朱杰
	 * @param selectIds
	 * @return
	 */
	@RequestMapping(value = "/updateStatus")
	@ResponseBody
	public String updateStatus(@RequestParam("selectIds[]") String[] selectIds,int status) {
		sendCountConfigManager.updateStatus(selectIds,status);
		return String.valueOf(Result.zero.value);
	}
}