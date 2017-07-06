
package com.creditharmony.fortune.delivery.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.creditharmony.core.web.BaseController;
import com.creditharmony.fortune.delivery.entity.ext.DeliveryEx;

/**
 * 三网理财经理交割
 * @Class Name TripleManagerDeliveryController
 * @author 胡体勇
 * @Create In 2016年2月16日
 */
@Controller
@RequestMapping(value = "${adminPath}/delivery/tripleManager")
public class TripleManagerDeliveryController extends BaseController {
	
    /**
     * 三网理财经理交割页面
     * 2016年2月16日
     * By 胡体勇
     * @param deliveryEx
     * @param request
     * @param response
     * @param model
     * @return
     */
	@RequestMapping(value = {"list",""})
	public String findList(DeliveryEx deliveryEx, HttpServletRequest request,
			HttpServletResponse response, Model model){
		return "/delivery/tripleManagerDelivery";
	}

}
