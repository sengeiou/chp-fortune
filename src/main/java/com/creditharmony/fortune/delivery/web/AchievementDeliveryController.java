
package com.creditharmony.fortune.delivery.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.SerializationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.web.BaseController;
import com.creditharmony.fortune.delivery.constant.Constant;
import com.creditharmony.fortune.delivery.entity.ext.DeliveryEx;
import com.creditharmony.fortune.delivery.service.AchievementDeliveryManager;

/**业绩交割
 * @Class Name AchievementDeliveryController
 * @author hutiyong
 * @Create In 2015年12月1日
 */
@Controller
@RequestMapping(value = "${adminPath}/delivery/achievement")
public class AchievementDeliveryController extends BaseController {
	
	@Autowired
	private AchievementDeliveryManager achievementDeliveryManager;
	
	
	/**
	 * 业绩交割列表
	 * 2015年12月1日
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
		// 页面检索条件复制
		DeliveryEx search = (DeliveryEx) SerializationUtils.clone(deliveryEx);
		// 分页查询
		Page<DeliveryEx> page = new Page<DeliveryEx>(request,response);
		page = achievementDeliveryManager.findPage(page, search);
		// 设置页面列表显示
		model.addAttribute("page", page);
		model.addAttribute("deliveryEx",deliveryEx);
		// 根据页面传过来的理财经理Code查询理财经理信息
		DeliveryEx del = achievementDeliveryManager.findInfoById(search.getfManagerId());
		model.addAttribute("del",del);
		// 统计页面显示金额
		DeliveryEx totalMoney = achievementDeliveryManager.countMoney(deliveryEx);
		if(totalMoney == null){
			model.addAttribute("totalMoney","");
		}else{
			model.addAttribute("totalMoney",totalMoney.getTotalMoney());
		}
		return "/delivery/achievementDelivery";
	}
	
	/**
	 * 业绩交割
	 * 2015年12月8日
	 * By 胡体勇
	 * @param custCode
	 * @param lendCode
	 * @param fManagerCode
	 * @param nfManagerCode
	 * @return
	 */
	@RequestMapping(value="achievementDelivery",method=RequestMethod.POST)
	@ResponseBody
	public String achievementDelivery(String custCode,String lendCode,String fManagerId,String nfManagerId){
		Map<String,String> map = new HashMap<String,String>();
		map.put("custCode", custCode);
		map.put("lendCode", lendCode);
		map.put("fManagerId", fManagerId);
		map.put("nfManagerId", nfManagerId);	
		int result = achievementDeliveryManager.achievementDelivery(map);
		return jsonMapper.toJson(result > 0 ? Constant.OK : Constant.NG);
	}
	
	/**
	 * 批量业绩交割
	 * 2015年12月8日
	 * By 胡体勇
	 * @param code
	 * @return
	 */
	@RequestMapping(value="batchAchievementDelivery",method=RequestMethod.POST)
	@ResponseBody
    public String batchAchievementDelivery(String code){
		int result = achievementDeliveryManager.batchAchievementDelivery(code);
		return jsonMapper.toJson(result > 0 ? Constant.OK : Constant.NG);
    }

}
