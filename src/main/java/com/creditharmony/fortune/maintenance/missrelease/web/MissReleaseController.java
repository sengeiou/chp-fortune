package com.creditharmony.fortune.maintenance.missrelease.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.creditharmony.core.web.BaseController;
import com.creditharmony.fortune.maintenance.missrelease.service.MissReleaseManager;
import com.creditharmony.fortune.maintenance.missrelease.view.MissReleaseView;

/**
 * 维护管理-->撤销维护
 * 
 * @Class Name MissReleaseController
 * @author 韩龙
 * @Create In 2016年5月15日
 */
@Controller
@RequestMapping(value = "${adminPath}/maintenance/missRelease")
public class MissReleaseController extends BaseController {

	@Autowired
	private MissReleaseManager missReleaseManager;
	
	/**
	 * 初始化撤销功能 2016年5月15日 By 韩龙
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "init")
	public String init(MissReleaseView missReleaseView,HttpServletRequest request,
			HttpServletResponse response, Model model) {
		model.addAttribute("MissReleaseView", missReleaseView);
		return "maintenance/missrelease/init";
	}

	/**
	 * 撤消功能 2016年5月15日 By 韩龙
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "settlesHeader")
	public String settlesHeader(MissReleaseView missReleaseView,
			HttpServletRequest request,HttpServletResponse response, Model model) {
		String message = missReleaseManager.releaseCreditor(missReleaseView.getLendCode(), 
				missReleaseView.getStartPeriods(), missReleaseView.getEndPeriods());
		if(message.length() > 0){
			addMessage(model, message);
		}else{
			addMessage(model, "出借编号为【"+missReleaseView.getLendCode()+"】释放债权成功");
		}
		model.addAttribute("MissReleaseView", missReleaseView);
		return "maintenance/missrelease/init";
	}
}
