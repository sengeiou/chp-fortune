
package com.creditharmony.fortune.delivery.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.creditharmony.core.web.BaseController;
import com.creditharmony.fortune.delivery.constant.Constant;
import com.creditharmony.fortune.delivery.entity.ext.DeliveryEx;
import com.creditharmony.fortune.delivery.service.ManagerDeliveryManager;

/**
 * 理财经理交割
 * @Class Name ManagerDeliveryController
 * @author hutiyong
 * @Create In 2015年12月2日
 */
@Controller
@RequestMapping(value = "${adminPath}/delivery/manager")
public class ManagerDeliveryController extends BaseController {
	
	@Autowired
	private ManagerDeliveryManager managerDeliveryManager;
	
	/**
	 * 页面初始化
	 * 2015年12月2日
	 * By hutiyong
	 * @param deliveryEx
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = {"list",""})
	public String findList(DeliveryEx deliveryEx, HttpServletRequest request,
			HttpServletResponse response, Model model){
		return "/delivery/managerDelivery";
	}
	
	/**
	 * 理财经理客户交割
	 * 2015年12月8日
	 * By 胡体勇
	 * @param fManagerCode
	 * @param nfManagerCode
	 * @return
	 */
	@RequestMapping(value="managerDelivery",method=RequestMethod.POST)
	@ResponseBody
	public String managerDelivery(String fManagerCode,String nfManagerCode){
		Map<String,String> map = new HashMap<String,String>();
		map.put("fManagerCode", fManagerCode);
		map.put("nfManagerCode", nfManagerCode);
		int result = managerDeliveryManager.managerDelivery(map);
		return jsonMapper.toJson(result > 0 ? Constant.OK : Constant.NG);
	}
	
	/**
	 * 理财经理业绩交割
	 * 2016年1月19日
	 * By 胡体勇
	 * @param deliveryEx
	 * @return
	 */
	@RequestMapping(value="managerAchievementDelivery",method=RequestMethod.POST)
	@ResponseBody
    public String managerAchievementDelivery(DeliveryEx deliveryEx){
		int result = 0;
		result = managerDeliveryManager.mangerAchievementDelivery(deliveryEx);
		return jsonMapper.toJson(result > 0 ? Constant.OK : Constant.NG);
    }
}
