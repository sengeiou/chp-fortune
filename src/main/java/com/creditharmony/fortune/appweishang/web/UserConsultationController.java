package com.creditharmony.fortune.appweishang.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.system.util.DataScopeUtil;
import com.creditharmony.core.type.SystemFlag;
import com.creditharmony.core.web.BaseController;
import com.creditharmony.fortune.appweishang.bean.UserConsultation;
import com.creditharmony.fortune.appweishang.server.EmpUserServer;
import com.creditharmony.fortune.appweishang.server.UserConsultationServer;
import com.creditharmony.fortune.triple.system.facade.SendTripleInfoFacade;

/**
 * 我的微服咨询接口controller
 * @author colin
 *
 */
@Controller
@RequestMapping("${adminPath}/consultationUser")
public class UserConsultationController extends BaseController {
	
	@Autowired
	private EmpUserServer empUserServer;
	
	@Autowired
	private UserConsultationServer userConsultationServer;
	
	@Autowired
	private SendTripleInfoFacade sendTripleInfoFacade;
	
	@RequestMapping("/consultationInfoList")
	public String  getConsultationUserList(UserConsultation userConsultation, 
			HttpServletRequest request, HttpServletResponse response, Model model){
		Page<UserConsultation> page = new Page<UserConsultation>(request, response);
		//page.setOrderBy(CustomerConstants.DataViewConfig.DefaultOrderSql);
		String dataRights = DataScopeUtil.getDataScope("ci",SystemFlag.FORTUNE.value);
		String managerId = dataRights.split("=")[1].replaceAll("\'", "");
		if(managerId.equals("a001")){
			managerId = null;
		}
		userConsultation.setManagerId(managerId);
		page = userConsultationServer.findConsultationList(page, userConsultation);
		model.addAttribute("userConsultation", userConsultation);
		model.addAttribute("page", page);
		return "appweishang/consultationList";
	}

}
