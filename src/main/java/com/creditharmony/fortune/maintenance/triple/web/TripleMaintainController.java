package com.creditharmony.fortune.maintenance.triple.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.web.BaseController;
import com.creditharmony.fortune.maintenance.triple.service.TripleMaintainManager;
import com.creditharmony.fortune.triple.system.entity.ext.IntDeliveryEx;
import com.creditharmony.fortune.utils.FortuneDictUtil;

/**
 * 三网维护交割维护
 * @Class Name TripleMaintainController
 * @author 周俊
 * @Create In 2016年5月19日
 */
@Controller
@RequestMapping("${adminPath}/maintenance/tripleMaintain")
public class TripleMaintainController extends BaseController{

	@Autowired
	private TripleMaintainManager tripleMaintainManager;
	/**
	 * 三网维护列表
	 * 2016年5月19日
	 * By 周俊
	 * @return
	 */
	@RequestMapping("/list")
	public String list(IntDeliveryEx intDeliveryEx,HttpServletRequest request,HttpServletResponse response,Model model){
		Page<IntDeliveryEx> page = tripleMaintainManager.findList(new Page<IntDeliveryEx>(request, response), intDeliveryEx);
		model.addAttribute("page",page);
		model.addAttribute("intDeliveryEx", intDeliveryEx);
		String[] types ={"tz_os_type"};
		FortuneDictUtil.addDicts(model, types);
		return "maintenance/triple/triplemaintainList";
	}
	
	/**
	 * 交割履历
	 * 2016年5月19日
	 * By 周俊
	 * @param id
	 * @return
	 */
	@RequestMapping("/deliveryRecord")
	public String deliveryRecord(String phone,HttpServletRequest request,HttpServletResponse response,Model model){
		Page<IntDeliveryEx> page = tripleMaintainManager.findDeliveryRecord(new Page<IntDeliveryEx>(request, response), phone);
		page.setFuncName("subPage");
		model.addAttribute("page",page);
		model.addAttribute("phone",phone);
		model.addAttribute("html","deliveryRecord");
		return "maintenance/triple/recordMain";
	}
	
	/**
	 * 发送履历
	 * 2016年5月19日
	 * By 周俊
	 * @param id
	 * @return
	 */
	@RequestMapping("/sendRecord")
	public String sendRecord(String phone,HttpServletRequest request,HttpServletResponse response,Model model){
		Page<IntDeliveryEx> page = tripleMaintainManager.findSendRecord(new Page<IntDeliveryEx>(request, response), phone);
		page.setFuncName("subPage");
		model.addAttribute("page",page);
		model.addAttribute("phone",phone);
		model.addAttribute("html","sendRecord");
		return "maintenance/triple/recordMain";
	}
	
	/**
	 * 操作履历
	 * 2016年5月19日
	 * By 周俊
	 * @param id
	 * @return
	 */
	@RequestMapping("/operateRecord")
	public String operateRecord(String id){
		System.out.println(id);
		return "maintenance/triple/triplemaintainList";
	}
	
	@RequestMapping(value="appSyncDate",method=RequestMethod.POST)
	@ResponseBody
	public String syncDate(){
		int a = tripleMaintainManager.syncDate();
		return jsonMapper.toJson(a);
	}
	
	@RequestMapping(value="updateUnm",method=RequestMethod.POST)
	@ResponseBody
	public String updateUnm(String uniqueNum,String osId,String sendStatus,HttpServletRequest request,HttpServletResponse response,Model model){
		int a = tripleMaintainManager.updateUnm(osId, uniqueNum, sendStatus);
		return jsonMapper.toJson(a);
	}
}
