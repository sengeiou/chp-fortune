
package com.creditharmony.fortune.creditor.config.rate.web;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.creditharmony.core.fortune.type.ProductCode;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.web.BaseController;
import com.creditharmony.fortune.creditor.config.rate.entity.ProductMatchingRate;
import com.creditharmony.fortune.creditor.config.rate.service.ProductMatchingRateManager;
import com.creditharmony.fortune.creditor.matching.utils.BigDecimalUtil;
import com.creditharmony.fortune.utils.FortuneDictUtil;

/**
 * 匹配规则管理
 * 
 * @Class Name MatchingRuleController
 * @author 朱杰
 * @Create In 2015年12月22日
 */
@Controller
@RequestMapping(value = "${adminPath}/creditor/config/rate")
public class ProductMatchingRateController extends BaseController {
	
	@Autowired
	private ProductMatchingRateManager productMatchingRateManager;
	
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
	@RequestMapping(value = {"list",""})
	public String getList(ProductMatchingRate search, HttpServletRequest request,
			HttpServletResponse response, Model model){
		// 分页查询
		Page<ProductMatchingRate> page = new Page<ProductMatchingRate>(request,response);
		page = productMatchingRateManager.getList(page, search);
		String[] types = {"tz_bill_state","com_use_flag","tz_bill_day"};
		FortuneDictUtil.addDicts(model,types);
		model.addAttribute("page", page);
		model.addAttribute("search", search);
		return "/creditor/config/rate/main";
	}

	/**
	 * 新增产品利率配置页面跳转
	 * 
	 * 2015年12月23日
	 * By 朱杰
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("toAdd")
	public String ruleToAdd(HttpServletRequest request,HttpServletResponse response, Model model){
		model.addAttribute("entity", new ProductMatchingRate());
		String[] types = {"tz_bill_state","com_use_flag","tz_bill_day"};
		FortuneDictUtil.addDicts(model,types);
		return "/creditor/config/rate/detail";
	}
	
	/**
	 * 产品利率配置删除
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
	@RequestMapping("delete")
	@ResponseBody
	public String delete(@RequestParam("select[]") String[] deleteIds,ProductMatchingRate search,HttpServletRequest request,HttpServletResponse response, Model model){
		int deleteNum = productMatchingRateManager.deleteByIds(deleteIds);
		return jsonMapper.toJson(deleteNum);
	}
	/**
	 * 删除检查是否有启用数据
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
	@RequestMapping("beforDelete")
	@ResponseBody
	public String beforDelete(@RequestParam("select[]") String[] deleteIds,ProductMatchingRate search,HttpServletRequest request,HttpServletResponse response, Model model){
		int deleteNum = productMatchingRateManager.beforDelete(deleteIds);
		return jsonMapper.toJson(deleteNum);
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
	public String save(@ModelAttribute ProductMatchingRate cr, HttpServletRequest request,HttpServletResponse response, Model model){
		productMatchingRateManager.saveData(cr);
		return this.getList(new ProductMatchingRate(), request, response, model);
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
		return this.getList(new ProductMatchingRate(), request, response, model);
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
	public String save(@RequestParam("select[]") String[] updateIds,ProductMatchingRate search, HttpServletRequest request,HttpServletResponse response, Model model){
		//更新处理
		int updateNum = productMatchingRateManager.updateStatusByIds(updateIds);
		return jsonMapper.toJson(updateNum);
	}
	/**
	 * *保存前看是否已经存在数据
	 * 2016年3月21日
	 * By 柳慧
	 * @param periodLower
	 * @param periodUpper
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("beforSaveCheck")
	@ResponseBody
	public String beforSaveCheck(String applyLenddayLower,String applyLenddayUpper,String productCode,String baseFlag,
								Integer applyLendMoneyLower,Integer applyLendMoneyUpper, String billType,
								String matchingInterestStart,Integer matchingBillDay, Model model,
			                     HttpServletRequest request,HttpServletResponse response){
		Map<String,Object> param = new HashMap<String, Object>();
		if(!StringUtils.isEmpty(applyLenddayLower)){
			param.put("applyLenddayLower", applyLenddayLower);
			param.put("applyLenddayUpper", applyLenddayUpper);
		}
		if(!StringUtils.isEmpty(applyLendMoneyLower)){
			param.put("applyLendMoneyLower", applyLendMoneyLower);
			param.put("applyLendMoneyUpper", applyLendMoneyUpper);
		}
		if(matchingBillDay!=null  && matchingBillDay!=0){
			param.put("matchingBillDay", matchingBillDay);
		}
		if(!StringUtils.isEmpty(matchingInterestStart)){
			param.put("matchingInterestStart", matchingInterestStart);
		}
		param.put("productCode", productCode);
		param.put("billType", billType);
		int saveNum = productMatchingRateManager.beforSaveCheck(param);
		return jsonMapper.toJson(saveNum);
	}
	
	/** 
	 *  通过 月利率算出 年化收益
	 * 2016年4月18日
	 * By 柳慧
	 * @param rate
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("getYearRate")
	@ResponseBody
	public String getYearRate(String rate,String productCode,HttpServletRequest request,HttpServletResponse response){
		 BigDecimal ret =  BigDecimal.ZERO;
		 if(ProductCode.YMY.value.equals(productCode)){
			 ret = new BigDecimal(rate); 
			 ret = ret.multiply(new BigDecimal(12));
		 }else{
			 ret = new BigDecimal(String.valueOf((Math.pow((Double.valueOf(rate) / 100 + 1),12) - 1) * 100));
		 }
		
		return jsonMapper.toJson( BigDecimalUtil.round(ret.doubleValue(), 2));
	}
	
	/**
	 * 批量开启
	 * 2016年09月21日
	 * By 陈晓强
	 * @param updateIds 批量开启的ids
	 */
	@RequestMapping("toEnable")
	@ResponseBody
	public String toEnable(@RequestParam("select[]") String[] updateIds) {
		return productMatchingRateManager.updateBatchStatusByIds(updateIds, "1");
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
		return productMatchingRateManager.updateBatchStatusByIds(updateIds, "0");
	}
}
