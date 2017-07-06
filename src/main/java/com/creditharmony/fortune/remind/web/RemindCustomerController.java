package com.creditharmony.fortune.remind.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.system.util.DataScopeUtil;
import com.creditharmony.core.type.SystemFlag;
import com.creditharmony.fortune.customer.service.CustomerManager;
import com.creditharmony.fortune.deduct.common.DeductUtils;
import com.creditharmony.fortune.remind.entity.ext.CustomerBirthdayEx;

/**
 * 消息提醒--客户生日提醒Controller
 * @Class Name RemindCustomerController
 * @author 韩龙
 * @Create In 2015年11月27日
 */
@Controller
@RequestMapping(value = "${adminPath}/customerRemind")
public class RemindCustomerController extends RemindCommon {

	@Autowired
	private CustomerManager customerManager;
	
	/**
	 * 客户生日提醒
	 * 2015年11月30日
	 * By 韩龙
	 * @param customerBirthdayEx
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/customerRemindList")
	public String customerRemind(CustomerBirthdayEx customerBirthdayEx, HttpServletRequest request, HttpServletResponse response, Model model){
		//获取数据权限
		String dataRights = DataScopeUtil.getDataScope("t", SystemFlag.FORTUNE.value);
		if(StringUtils.isNotEmpty(dataRights)){
			Map<String, String> sqlMap = new HashMap<String, String>();
			sqlMap.put("dataRights", dataRights);
			customerBirthdayEx.setSqlMap(sqlMap);
		}
		Page<CustomerBirthdayEx> page = customerManager.findPage(
				new Page<CustomerBirthdayEx>(request, response), customerBirthdayEx); 
        // 设置页面数据及查询条件回填
		model.addAttribute("page", page).addAttribute("customer", customerBirthdayEx);
		// 获取字典项
		DeductUtils.getDictList(model);
        return "remind/customerRemindList";
	}

}
