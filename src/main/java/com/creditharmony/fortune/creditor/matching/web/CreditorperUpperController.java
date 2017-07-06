
package com.creditharmony.fortune.creditor.matching.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.creditharmony.core.loan.type.LoanType;
import com.creditharmony.core.loan.type.ProfType;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.web.BaseController;
import com.creditharmony.fortune.creditor.config.rate.entity.ProductMatchingRate;
import com.creditharmony.fortune.creditor.matching.constant.Constant;
import com.creditharmony.fortune.creditor.matching.entity.CreditorperUpper;
import com.creditharmony.fortune.creditor.matching.service.CreditorperUpperManager;
import com.creditharmony.fortune.utils.FortuneDictUtil;

/**
 * 债权人借款上限 控制层
 * @Class Name CreditorperUpperController
 * @author 柳慧
 * @Create In 2016年1月27日
 */
@Controller
@RequestMapping(value = "${adminPath}/creditor/CreditorperUpper")
public class CreditorperUpperController extends BaseController {
	
	@Autowired
	private CreditorperUpperManager creditorperUpperManager;
	
	/**
	 * 债权人借款上限主页
	 * 2016年1月27日
	 * By 柳慧
	 * @param creditorperUpper
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = {"list",""})
	public String findList(CreditorperUpper creditorperUpper, HttpServletRequest request,
			HttpServletResponse response, Model model){
		ProfType[] allValues = ProfType.values();// 职业 枚举
		Map<String,String> proMap = new HashMap<String, String>();
		Map<String,String> loanTypeMap = new HashMap<String, String>();
		for (ProfType proty:allValues){
			proMap.put(proty.getName(), proty.getCode());
		}	
		LoanType [] loanTypes = LoanType.values();// 债权类型枚举
		for(LoanType loanType:loanTypes){
			loanTypeMap.put(loanType.getName(), loanType.getCode());
		}
		// 分页查询
		Page<CreditorperUpper> page = new Page<CreditorperUpper>(request,response);
		page = creditorperUpperManager.findPage(page, creditorperUpper);
		// 设置页面列表显示
		model.addAttribute("proMap", proMap);
		model.addAttribute("loanTypeMap", loanTypeMap);
		model.addAttribute("page", page);
		model.addAttribute("creditorperUpper",creditorperUpper);
		model.addAttribute("entity", new ProductMatchingRate());
		String[] types = {"com_use_flag"};
		FortuneDictUtil.addDicts(model,types);
		return "/creditor/matching/creditorperUpperMain";
	}
	
	/**
	 * 跳转到添加页面
	 * 2016年1月28日
	 * By 柳慧
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "goAdd")
	public String goAdd(Model model){
		ProfType[] allValues = ProfType.values();// 职业 枚举
		Map<String,String> proMap = new HashMap<String, String>();
		Map<String,String> loanTypeMap = new HashMap<String, String>();
		for (ProfType proty:allValues){
			proMap.put(proty.getName(), proty.getCode());
		}	
		LoanType [] loanTypes = LoanType.values();// 债权类型枚举
		for(LoanType loanType:loanTypes){
			loanTypeMap.put(loanType.getName(), loanType.getCode());
		}
		model.addAttribute("proMap", proMap);
		model.addAttribute("loanTypeMap", loanTypeMap);
		return "/creditor/matching/creditorperUpperAdd";
	}
	
	/**
	 * 添加方法
	 * 2016年1月28日
	 * By 柳慧
	 * @param creditorperUpper
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "add",method = RequestMethod.POST)
	@ResponseBody
	public String add(CreditorperUpper creditorperUpper,HttpServletRequest request,
			HttpServletResponse response,Model model){
			int result = creditorperUpperManager.add(creditorperUpper);
			return jsonMapper.toJson(result > 0 ? Constant.OK : Constant.NG);
	}
	
	/**
	 * 删除方法
	 * 2016年1月28日
	 * By 柳慧
	 * @param deleteIds
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("delete")
	@ResponseBody
	public String delete(@RequestParam("select[]") String[] deleteIds,HttpServletRequest request,HttpServletResponse response, Model model){
		int result = creditorperUpperManager.delete(deleteIds);
		return jsonMapper.toJson(result > 0 ? Constant.OK : Constant.NG);
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
	public String edit(@RequestParam("select[]") String[] updateIds,ProductMatchingRate search, HttpServletRequest request,HttpServletResponse response, Model model){
		//更新处理
		int updateNum = creditorperUpperManager.updateStatusByIds(updateIds);
		return jsonMapper.toJson(updateNum);
	}

	/**
	 * 批量启用
	 * 2016年09月21日
	 * By 陈晓强
	 * @param updateIds 批量启用的ids
	 */
	@RequestMapping("toEnable")
	@ResponseBody
	public String toEnable(@RequestParam("select[]") String[] updateIds) {
		return creditorperUpperManager.updateBatchStatusByIds(updateIds, "1");
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
		return creditorperUpperManager.updateBatchStatusByIds(updateIds, "0");
	}
}
