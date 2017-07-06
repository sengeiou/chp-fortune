
package com.creditharmony.fortune.creditor.config.rule.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.creditharmony.core.common.type.UseFlag;
import com.creditharmony.core.fortune.type.YoN;
import com.creditharmony.core.web.BaseController;
import com.creditharmony.fortune.creditor.config.rule.dao.MatchingRuleDao;
import com.creditharmony.fortune.creditor.config.rule.entity.CreditorperRuleConfig;
import com.creditharmony.fortune.creditor.config.rule.service.MatchingRuleManager;
import com.creditharmony.fortune.creditor.config.rule.view.CreditorperRuleConfigView;
import com.creditharmony.fortune.utils.FortuneDictUtil;

/**
 * 匹配规则管理
 * 
 * @Class Name MatchingRuleController
 * @author 朱杰
 * @Create In 2015年12月22日
 */
@Controller
@RequestMapping(value = "${adminPath}/creditor/config/rule")
public class MatchingRuleController extends BaseController {
	
	@Autowired
	private MatchingRuleManager matchingRuleManager;
	
	@Autowired 
	private MatchingRuleDao matchingRuleDao; // 债权匹配规则管理Dao
	
	/**
	 * 匹配规则一览
	 * 
	 * 2015年12月22日
	 * By 朱杰
	 * @param creditorTradeEx 页面检索条件
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = {"ruleList",""})
	public String getRuleList(CreditorperRuleConfigView search, HttpServletRequest request,
			HttpServletResponse response, Model model){
		// 分页查询
		List<CreditorperRuleConfig> list= matchingRuleManager.getRuleList(search);
		String[] types = {"com_use_flag","tz_bill_state","tz_yes_no"};
		FortuneDictUtil.addDicts(model,types);
		model.addAttribute("list", list);
		model.addAttribute("search", search);
		return "/creditor/config/rule/ruleMain";
	}

	/**
	 * 新增匹配规则页面跳转
	 * 
	 * 2015年12月23日
	 * By 朱杰
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("ruleToAdd")
	public String ruleToAdd(HttpServletRequest request,HttpServletResponse response, Model model){
		model.addAttribute("entity", new CreditorperRuleConfig());
		return "/creditor/config/rule/detail";
	}
	
	/**
	 * 匹配规则删除
	 * 
	 * 2016年1月15日
	 * By 朱杰
	 * @param deleteIds 删除的规则ids
	 * @param search
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("ruleToDelete")
	@ResponseBody
	public String ruleToDelete(@RequestParam("select[]") String[] deleteIds,CreditorperRuleConfigView search,HttpServletRequest request,HttpServletResponse response, Model model){
		int deleteNum = matchingRuleManager.deleteByIds(deleteIds);
		/*// 分页查询
		List<CreditorperRuleConfig> list = matchingRuleManager.getRuleList(search);
		model.addAttribute("list", list);
		
		return "/creditor/config/rule/ruleList";*/
		return jsonMapper.toJson(deleteNum);
	}
	
	/**
	 * 保存前数据检查
	 * 2016年10月18日
	 * By 陈广鹏
	 * @return
	 */
	@RequestMapping("saveCheck")
	@ResponseBody
	public String saveCheck(CreditorperRuleConfig rule){
		String checkResult = matchingRuleManager.saveCheck(rule);
		return jsonMapper.toJson(checkResult);
	}
	
	/**
	 * 保存
	 * 
	 * 2015年12月23日
	 * By 朱杰
	 * @param cr 保存的数据
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("save")
	public String save(@ModelAttribute CreditorperRuleConfig cr, HttpServletRequest request,HttpServletResponse response, Model model){
		//保存处理
		cr.setRemark(StringEscapeUtils.unescapeHtml(cr.getRemark()));
		matchingRuleManager.saveData(cr);
		return this.getRuleList(new CreditorperRuleConfigView(), request, response, model);
	}
	
	/**
	 * 返回
	 * 
	 * 2015年12月23日
	 * By 朱杰
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("back")
	public String back(HttpServletRequest request,HttpServletResponse response, Model model){
		return this.getRuleList(new CreditorperRuleConfigView(), request, response, model);
	}
	
	/**
	 * 状态更新
	 * 2016年1月15日
	 * By 朱杰
	 * @param updateIds 状态更新的ids
	 * @param search
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("edit")
	@ResponseBody
	public String save(@RequestParam("select[]") String[] updateIds,CreditorperRuleConfigView search, HttpServletRequest request,HttpServletResponse response, Model model){
		//更新处理
		int updateNum = matchingRuleManager.updateStatusByIds(updateIds);
		/*// 分页查询
		List<CreditorperRuleConfig> list  = matchingRuleManager.getRuleList(search);
		model.addAttribute("list", list);
		
		return "/creditor/config/rule/ruleList";*/
		return jsonMapper.toJson(updateNum);
	}
	
	/**
	 * 切换启用状态
	 * 2016年11月4日
	 * By 陈广鹏
	 * @param updateIds
	 * @return
	 */
	@RequestMapping("changeStatus")
	@ResponseBody
	public String changeStatus(String id){
		//更新处理
		String rtn = matchingRuleManager.updateStatusById(id);
		return jsonMapper.toJson(rtn);
	}
	
	/**
	 * 选择默认页面
	 * 2016年1月18日
	 * By 柳慧
	 * @param matchingFirstdayFlag
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	
	@RequestMapping("ruleByMatchingFirstdayFlag/{matchingFirstdayFlag}")
	public String ruleByMatchingFirstdayFlag(@PathVariable("matchingFirstdayFlag") String matchingFirstdayFlag,HttpServletRequest request,HttpServletResponse response, Model model){
		// 分页查询
		CreditorperRuleConfigView search = new CreditorperRuleConfigView();
		search.setBillType(matchingFirstdayFlag);
		List<CreditorperRuleConfig> list = matchingRuleManager.getRuleList(search);
		model.addAttribute("list", list);
		
		return "/creditor/config/rule/ruleListDefault";
	}
	/**
	 * 通过主键 修改信息
	 * 2016年1月18日
	 * By 柳慧
	 * @param ruleID
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("updateByruleID/{ruleID}")
	public void updateByruleID(@PathVariable("ruleID") String ruleID,HttpServletRequest request,HttpServletResponse response, Model model){
		Map<String,Object> ruleConfigMap = new HashMap<String, Object>();
		ruleConfigMap.put("ruleId", ruleID);
		List<CreditorperRuleConfig> ruleConfigLs = matchingRuleDao.find(ruleConfigMap);// 查询匹配匹配规则
		
		if(ruleConfigLs!=null && ruleConfigLs.size()>0){
			CreditorperRuleConfig ruleConfig1 = ruleConfigLs.get(0);
			ruleConfig1.setDefaultFlag(YoN.SHI.value);
			matchingRuleManager.resetRuleDefaultFlag(ruleConfig1);
		}
	}
	
	/**
	 * 批量开启
	 * 2016年11月3日
	 * By 陈广鹏
	 * @param updateIds
	 * @return 
	 */
	@RequestMapping("toEnable")
	@ResponseBody
	public String toEnable(@RequestParam("select[]") String[] updateIds) {
		String rtn = "SUCCESS";
		try {
			rtn = matchingRuleManager.enableConfig(updateIds);
		} catch (Exception e) {
			e.printStackTrace();
			rtn = e.getMessage();
		}
		return rtn;
	}
	
	/**
	 * 批量停用
	 * 2016年09月21日
	 * By 陈晓强
	 * @param updateIds 批量停用的ids
	 */
	@RequestMapping("toDisable")
	@ResponseBody
	public String toDisable(@RequestParam("select[]") String[] updateIds) {
		return matchingRuleManager.updateBatchStatusByIds(updateIds, UseFlag.TY.value);
	}
}