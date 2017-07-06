package com.creditharmony.fortune.creditor.config.auto.matching.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.web.BaseController;
import com.creditharmony.fortune.creditor.config.auto.matching.entity.CreditorperAutoConfig;
import com.creditharmony.fortune.creditor.config.auto.matching.service.AutoMatchingManager;
import com.creditharmony.fortune.creditor.config.auto.matching.view.CreditorperAutoConfigView;
import com.creditharmony.fortune.creditor.matching.job.AutoMatchingJob;
import com.creditharmony.fortune.utils.FortuneDictUtil;

/**
 * 自动匹配配置管理
 * @Class Name AutoMatchingController
 * @author 朱杰
 * @Create In 2015年12月24日
 */
@Controller
@RequestMapping(value = "${adminPath}/creditor/config/automatching")
public class AutoMatchingController extends BaseController {
	
	@Autowired
	private AutoMatchingManager autoMatchingManager;
	@Autowired
	private AutoMatchingJob autoMatchingJob;
	
	/**
	 * 自动一览
	 * 
	 * 2015年12月22日
	 * By 朱杰
	 * @param creditorTradeEx 页面检索条件
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = {"list",""})
	public String getAutoMatchingList(CreditorperAutoConfigView search, HttpServletRequest request,
			HttpServletResponse response, Model model){
		// 分页查询
		Page<CreditorperAutoConfig> page = new Page<CreditorperAutoConfig>(request,response);
		page = autoMatchingManager.getAutoMatchingList(page, search);
		String[] types = {"tz_bill_state","tz_bill_day","com_use_flag"};
		FortuneDictUtil.addDicts(model,types);
		model.addAttribute("page", page);
		model.addAttribute("search", search);
		return "/creditor/config/autoMatching/main";
	}

	/**
	 * 编辑跳转
	 * 2016年1月9日
	 * By 朱杰
	 * @param editId 编辑的自动匹配id
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = {"toEdit/{editId}"})
	public String toEdit(@PathVariable("editId") String editId,HttpServletRequest request,HttpServletResponse response, Model model){
		CreditorperAutoConfig entity = new CreditorperAutoConfig();
		if(StringUtils.isNotBlank(editId)){
			entity = autoMatchingManager.get(editId);			
		}
		model.addAttribute("entity", entity);
		return "/creditor/config/autoMatching/detail";
	}
	
	/**
	 * 新增跳转
	 * 2016年1月9日
	 * By 朱杰
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = {"toAdd"})
	public String toAdd(HttpServletRequest request,HttpServletResponse response, Model model){
		CreditorperAutoConfig entity = new CreditorperAutoConfig();
		model.addAttribute("entity", entity);
		return "/creditor/config/autoMatching/detail";
	}
	
	/**
	 * 保存
	 * 2016年3月18日
	 * By 周俊
	 * @param creditorperAutoConfig
	 * @return
	 */
	@RequestMapping("/save")
	@ResponseBody
	public String save(CreditorperAutoConfig creditorperAutoConfig){
		try {
			autoMatchingManager.save(creditorperAutoConfig);
			autoMatchingManager.updateAutoMatching();
		} catch (Exception e) {
			return	jsonMapper.toJson(e.getMessage());
		}
		return null;
	}
	
	/**
	 * 状态更新
	 * 
	 * 2015年12月30日
	 * By 朱杰
	 * @param selectId 更新的匹配ids
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("edit")
	@ResponseBody
	public String updateStatus(@RequestParam("select[]") String[] updateIds,CreditorperAutoConfigView search, HttpServletRequest request,HttpServletResponse response, Model model){
		//状态更新处理
		int updateNum = autoMatchingManager.updateStatusByIds(updateIds);
		if(updateNum >= 1){
			autoMatchingManager.updateAutoMatching();
		}
		return jsonMapper.toJson(updateNum);
	}
	
	/**
	 * 返回
	 * 2015年12月30日
	 * By 朱杰
	 * @param cr
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("back")
	public String back(HttpServletRequest request,HttpServletResponse response, Model model){
		return this.getAutoMatchingList(new CreditorperAutoConfigView(), request, response, model);
	}
	
	/**
	 * 删除
	 * 2015年12月30日
	 * By 朱杰
	 * @param cr
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("delete")
	@ResponseBody
	public String delete(@RequestParam("select[]") String[] deleteIds,CreditorperAutoConfigView search,HttpServletRequest request,HttpServletResponse response, Model model){
		//删除
		int deleteNum = autoMatchingManager.deleteByIds(deleteIds);
		if(deleteNum >= 1){
			autoMatchingManager.updateAutoMatching();
		}
		return jsonMapper.toJson(deleteNum);
	}
	
	/**
	 * 自动匹配圈数据（手动）
	 * 2016年6月25日
	 * By 朱杰
	 * @param deleteIds
	 * @param search
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("toAutoMatching")
	public String toAutoMatching(HttpServletRequest request,HttpServletResponse response, Model model){
		autoMatchingJob.start();
		return null;
	}
	
	/**
	 * 批量开启
	 * 2016年09月22日
	 * By 陈晓强
	 * @param updateIds 批量开启的ids
	 */
	@RequestMapping("toEnable")
	@ResponseBody
	public String toEnable(@RequestParam("select[]") String[] updateIds) {
		String result = autoMatchingManager.updateBatchStatusByIds(updateIds, "1");
		if ("SUCCESS".equals(result)) {
			autoMatchingManager.updateAutoMatching();
		}
		return result;
	}

	/**
	 * 批量停用
	 * 2016年09月22日
	 * By 陈晓强
	 * @param updateIds 批量停用的ids
	 */
	@RequestMapping("toDisable")
	@ResponseBody
	public String toDisable(@RequestParam("select[]") String[] updateIds) {
		String result = autoMatchingManager.updateBatchStatusByIds(updateIds, "0");
		if ("SUCCESS".equals(result)) {
			autoMatchingManager.updateAutoMatching();
		}
		return result;
	}
}
