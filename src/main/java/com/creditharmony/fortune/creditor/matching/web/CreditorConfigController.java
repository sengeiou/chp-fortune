
package com.creditharmony.fortune.creditor.matching.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.web.BaseController;
import com.creditharmony.fortune.creditor.matching.constant.Constant;
import com.creditharmony.fortune.creditor.matching.entity.CreditorConfig;
import com.creditharmony.fortune.creditor.matching.service.CreditorConfigManager;
import com.creditharmony.fortune.utils.FortuneDictUtil;

/**
 * 错期匹配
 * @Class Name CreditorConfigController
 * @author 胡体勇
 * @Create In 2015年12月21日
 */
@Controller
@RequestMapping(value = "${adminPath}/creditor/creditorConfig")
public class CreditorConfigController extends BaseController {
	
	@Autowired
	private CreditorConfigManager creditorConfigManager;
	
	/**
	 * 错期匹配分页列表
	 * 2015年12月21日
	 * By 胡体勇
	 * @param creditorConfig
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = {"list",""})
	public String findList(CreditorConfig creditorConfig, HttpServletRequest request,
			HttpServletResponse response, Model model){
		// 分页查询
		Page<CreditorConfig> page = new Page<CreditorConfig>(request,response);
		page = creditorConfigManager.findPage(page, creditorConfig);
		// 设置页面列表显示
		model.addAttribute("page", page);
		model.addAttribute("creditorConfig",creditorConfig);
		String[] types = {"tz_bill_day","tz_repay_day","tz_bill_state","com_use_flag"};
		FortuneDictUtil.addDicts(model, types);
		return "/creditor/creditorConfigList";
	}
	
	/**
	 * 跳转新增错期匹配页面
	 * 2015年12月21日
	 * By 胡体勇
	 * @param creditorConfig
	 * @return
	 */
	@RequestMapping(value = "turnPage",method = RequestMethod.GET)
	public String turnPage(CreditorConfig creditorConfig, Model model){
		String[] types = {"tz_bill_day","tz_repay_day","tz_bill_state","com_use_flag"};
		FortuneDictUtil.addDicts(model, types);
		return "/creditor/creditorConfigAdd";
	}
	
	/**
	 * 新增错期匹配
	 * 2015年12月21日
	 * By 胡体勇
	 * @param creditorConfig
	 * @return
	 */
	@RequestMapping(value = "add",method = RequestMethod.POST)
	@ResponseBody
	public String add(CreditorConfig creditorConfig,HttpServletRequest request,
			HttpServletResponse response,Model model){
		int result = creditorConfigManager.add(creditorConfig);
		if(result == Constant.REPEAT_NUMBER){
			return jsonMapper.toJson(Constant.REPEAT);
		}else{
			return jsonMapper.toJson(result > 0 ? Constant.OK : Constant.NG);
		}
	}
	
	/**
	 * 修改错期匹配状态
	 * 2015年12月21日
	 * By 胡体勇
	 * @param creditorConfig
	 * @return
	 */
	@RequestMapping(value = "updateStatus",method = RequestMethod.POST)
	@ResponseBody
    public String updateStatus(CreditorConfig creditorConfig,HttpServletRequest request,
			HttpServletResponse response){
		int result = creditorConfigManager.updateStatus(creditorConfig);
		return jsonMapper.toJson(result > 0 ? Constant.OK : Constant.NG);
		
    }
	
	/**
	 * 批量启用
	 * 2016年09月22日
	 * By 陈晓强
	 * @param updateIds 批量启用的ids
	 */
	@RequestMapping("toEnable")
	@ResponseBody
	public String toEnable(@RequestParam("id") String[] updateIds) {
		return creditorConfigManager.updateBatchStatusByIds(updateIds, "1");
	}

	/**
	 * 批量停用
	 * 2016年09月22日
	 * By 陈晓强
	 * @param updateIds 批量停用的ids
	 */
	@RequestMapping("toDisable")
	@ResponseBody
	public String toDisable(@RequestParam("id") String[] updateIds) {
		return creditorConfigManager.updateBatchStatusByIds(updateIds, "0");
	}
}
