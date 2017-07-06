
package com.creditharmony.fortune.delivery.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.SerializationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.creditharmony.common.util.ArrayHelper;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.web.BaseController;
import com.creditharmony.fortune.delivery.facade.TripleAchievementDeliveryFacade;
import com.creditharmony.fortune.delivery.service.TripleAchievementDeliveryManager;
import com.creditharmony.fortune.delivery.service.TripleCustomerDeliveryManager;
import com.creditharmony.fortune.triple.system.entity.ext.IntDeliveryEx;
import com.creditharmony.fortune.utils.FortuneDictUtil;

/**
 * 三网业绩交割
 * @Class Name TripleAchievementDeliveryController
 * @author 胡体勇
 * @Create In 2016年2月23日
 */
@Controller
@RequestMapping(value = "${adminPath}/delivery/tripleAchievement")
public class TripleAchievementDeliveryController extends BaseController {
	
	@Autowired
	private TripleAchievementDeliveryManager tripleAchievementDeliveryManager;
	@Autowired
	private TripleCustomerDeliveryManager tripleCustomerDeliveryManager;
	@Autowired
	private TripleAchievementDeliveryFacade tFacade;
	
	/**
	 * 业绩交割列表页
	 * 2016年2月23日
	 * By 胡体勇
	 * @param intDeliveryEx
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = {"list",""})
	public String findList(IntDeliveryEx intDeliveryEx, HttpServletRequest request,
			HttpServletResponse response, Model model){
		        // 搜索条件复制
		        IntDeliveryEx search = (IntDeliveryEx) SerializationUtils.clone(intDeliveryEx);
		        // 分页
		        Page<IntDeliveryEx> page = new Page<IntDeliveryEx>(request,response);
		        page = tripleAchievementDeliveryManager.findPage(page, search);
		        model.addAttribute("page",page);
		        model.addAttribute("intDeliveryEx",intDeliveryEx);
		        List<IntDeliveryEx> list = tripleCustomerDeliveryManager.findEmpInfoByCode(intDeliveryEx.getEmpCode());
		        if(ArrayHelper.isNotEmpty(list)){
		        	IntDeliveryEx ide = new IntDeliveryEx();
		        	ide.setEmpName(list.get(0).getNewEmpName());
		        	ide.setTeamManagerName(list.get(0).getNewTeamManagerName());
		        	ide.setTeamName(list.get(0).getNewTeamName());
		        	ide.setOrgName(list.get(0).getNewOrgName());
		        	model.addAttribute("ide",ide);
		        }
		        IntDeliveryEx totalMoney = tripleAchievementDeliveryManager.countMoney(intDeliveryEx);
		    	if(totalMoney == null){
					model.addAttribute("totalMoney","");
				}else{
					model.addAttribute("totalMoney",totalMoney.getTotalMoney());
				}
		    	// 获取页面需要的字典项
		    	String[] types = {"tz_lend_state","tz_finish_state","tz_pay_type"};
		    	FortuneDictUtil.addDicts(model, types);
				return "/delivery/tripleAchievementDelivery";
	}
	
	/**
	 * 批量业绩交割
	 * 2015年12月8日
	 * By 胡体勇
	 * @param code
	 * @return
	 */
	@RequestMapping(value="batchTripleAchievementDelivery",method=RequestMethod.POST)
	@ResponseBody
    public String batchTripleAchievementDelivery(String code){
		int result = tFacade.batchTripleAchievementDelivery(code);
		return jsonMapper.toJson(result);
    }

}
