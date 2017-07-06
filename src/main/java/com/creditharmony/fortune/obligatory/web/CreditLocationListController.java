package com.creditharmony.fortune.obligatory.web;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.creditharmony.core.users.entity.ProvinceCity;
import com.creditharmony.core.web.BaseController;
import com.creditharmony.fortune.obligatory.entity.CreditOkListObj;
import com.creditharmony.fortune.obligatory.manager.CreditLocationListManager;
import com.creditharmony.fortune.obligatory.view.ObligatoryListView;
import com.creditharmony.fortune.utils.FortuneDictUtil;
import com.creditharmony.fortune.utils.OptionUtil;

/**
 * 可用债权配置
 * @Class Name creditLocationListController 
 * @author 李志伟
 * @Create In 2015年12月15日
 */
@Controller
@RequestMapping("${adminPath}/creditLocation/")
public class CreditLocationListController extends BaseController {

	@Autowired
	private CreditLocationListManager creditLocationListManager;
	
	/**
	 * 可用债权配置列表搜索
	 * 2016年1月11日
	 * by 李志伟
	 * @param coo
	 * @param m
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "loadCreditLocationList")
	public String loadCreditLocationList(CreditOkListObj coo, String loanBackmoneyDay,BigDecimal loanMonthRate,  Model m, HttpServletRequest request, HttpServletResponse response) {
		
		ObligatoryListView olv = creditLocationListManager.loadCreditLocationList(coo, loanBackmoneyDay, loanMonthRate, request, response);
		String[] types ={"tz_repay_day","tz_bill_state"};
		FortuneDictUtil.addDicts(m, types);
		m.addAttribute("olv", olv);
		return "obligatory/creditLocationList";
	}
	
	/**
	 * 去修改页面
	 * 2016年1月11日
	 * by 李志伟
	 * @param loanCode
	 * @param m
	 * @return
	 */
	@RequestMapping(value = "goEdit")
	public String goEdit(String loanCode, Model m){
		
		CreditOkListObj coo = creditLocationListManager.goEdit(loanCode);
		m.addAttribute("coo", coo);
		return "obligatory/creditLocationPage";
	}
	
	/**
	 * 更新可用债权配置信息
	 * 2015年12月21日
	 * by 李志伟
	 * @param coo
	 * @return
	 */
	/*@RequestMapping(value = "updateMesg")
	public String updateMesg(CreditOkListObj coo){
		
		coo.preUpdate();
		creditLocationListManager.updateMest(coo);
		
		return "redirect:" + adminPath + "/creditLocation/loadCreditLocationList";
	}*/
	
	/**
	 * 删除
	 * 2015年12月17日
	 * by 李志伟
	 * @param inst
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "creditBatchDel")
	@ResponseBody
	public String creditBatchDel(String inst) {
		
		creditLocationListManager.creditBatchDel(inst);
		return jsonMapper.toJson("true");
	}
	
	/**
	 * 去添加页面(此方法不可去,第一次进入此页面，页面不该存在数据)
	 * 2015年12月16日
	 * by 李志伟
	 * @return
	 */
	@RequestMapping(value = "goAdd")
	public String goAddPage() {
		return "obligatory/creditLocationAdd";
	}
	
	/**
	 * 可用债权配置列表添加页面检索
	 * 2016年1月19日
	 * by 李志伟
	 * @param coo
	 * @param m
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "addPageSearch")
	public String addPageSearch(CreditOkListObj coo, Model m, HttpServletRequest request, HttpServletResponse response){
		
		ObligatoryListView olv = creditLocationListManager.loadAddPage(coo, request, response);
		m.addAttribute("olv", olv);
		return "obligatory/creditLocationAdd";
	}
	
	/**
	 * 可用债权配置弹出窗口
	 * 2015年12月19日
	 * by 李志伟
	 * @param req
	 * @param m
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "openWindow")
	public String openWindow(HttpServletRequest req, CreditOkListObj coo, Model m) throws UnsupportedEncodingException{
		
		String par = req.getParameter("code");
		List<ProvinceCity> provinceList = OptionUtil.getProvinceList();
		m.addAttribute("par", par).addAttribute("provinceList", provinceList);
		return "obligatory/creditLocationWindow";
	}
	
	/**
	 * 添加新的可用债权配置
	 * 2015年12月17日
	 * by 李志伟
	 * @param coo
	 */
	@RequestMapping(value = "addCreditConfig")
	@ResponseBody
	public String addCreditConfig(CreditOkListObj coo){
		
		String mesg = creditLocationListManager.addCreditConfig(coo);
		return jsonMapper.toJson(mesg);
	}
}