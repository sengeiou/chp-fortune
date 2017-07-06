package com.creditharmony.fortune.maintenance.settles.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.creditharmony.core.web.BaseController;
import com.creditharmony.fortune.deduct.common.DeductUtils;
import com.creditharmony.fortune.maintenance.settles.facade.SettlesManager;
import com.creditharmony.fortune.maintenance.settles.view.SettlesView;

/**
 * 维护管理
 * @Class Name SettlesController
 * @author 韩龙
 * @Create In 2016年5月15日
 */
@Controller
@RequestMapping(value = "${adminPath}/maintenance/settle")
public class SettlesController extends BaseController{

	@Autowired
	private SettlesManager settlesManager;
	
	/**
	 * 初始化结算画面
	 * 2016年5月15日
	 * By 韩龙
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "init")
	public String init(SettlesView settlesView, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		model.addAttribute("SettlesView", settlesView);
		DeductUtils.getDictList(model);
		return "maintenance/settles/init";
	}
	
	
	/**
	 * 结算画面
	 * 2016年5月15日
	 * By 韩龙
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "settlesHeader")
	public String settlesHeader(SettlesView settlesView,HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String meesage = settlesManager.reSettle(settlesView.getBillday(), settlesView.getBillDate(), settlesView.getLendCode());
		model.addAttribute("SettlesView", settlesView);
		DeductUtils.getDictList(model);
		if(meesage.length()==0){
			addMessage(model, "结算成功");
		}else{
			addMessage(model, meesage);
		}
		
		return "maintenance/settles/init";
	}
	
	
}
