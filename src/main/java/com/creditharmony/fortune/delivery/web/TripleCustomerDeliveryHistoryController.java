
package com.creditharmony.fortune.delivery.web;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.creditharmony.common.util.DateUtils;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.web.BaseController;
import com.creditharmony.fortune.delivery.constant.Constant;
import com.creditharmony.fortune.delivery.service.TripleCustomerDeliveryHistoryManager;
import com.creditharmony.fortune.delivery.utils.TripleDeliveryExportExcelUtils;
import com.creditharmony.fortune.triple.system.entity.ext.IntDeliveryEx;
import com.creditharmony.fortune.utils.FortuneDictUtil;

/**
 * 三网客户交割历史查询
 * @Class Name TripleCustomerDeliveryHistoryController
 * @author 胡体勇
 * @Create In 2016年2月16日
 */
@Controller
@RequestMapping(value = "${adminPath}/delivery/tripleCustomer/history")
public class TripleCustomerDeliveryHistoryController extends BaseController {
	
	@Autowired
	private TripleCustomerDeliveryHistoryManager tripleCustomerDeliveryHistoryManager;
	
	/**
	 * 三网客户交割历史查询列表
	 * 2016年2月16日
	 * By 胡体勇
	 * @param deliveryEx
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = {"list",""})
	public String findList(IntDeliveryEx intDeliveryEx, HttpServletRequest request,
			HttpServletResponse response, Model model){
		 // 分页查询
		Page<IntDeliveryEx> page = new Page<IntDeliveryEx>(request,response);
		page = tripleCustomerDeliveryHistoryManager.findPage(page, intDeliveryEx);
		// 设置页面列表显示
		model.addAttribute("page", page);
		model.addAttribute("intDeliveryEx",intDeliveryEx);
		String[] types = { "tz_os_type", "tz_delivery_type" };
		FortuneDictUtil.addDicts(model, types);
		return "/delivery/tripleCustomerDeliveryHistoryList";
	}
	
	/**
	 * 导表
	 * 2016年2月27日
	 * By 胡体勇
	 * @param intDeliveryEx
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="outExcel")
	public void outExcel(IntDeliveryEx intDeliveryEx, String difference, HttpServletRequest request, HttpServletResponse response) {
		IntDeliveryEx ex = tripleCustomerDeliveryHistoryManager.outExcel(intDeliveryEx);
		String fileName = Constant.OUT_EXCEL_TITLE_TRIPLE_CUSTOMER_DELIVERY_HISTORY+"_"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
		String namespace = "";
		if ("excel".equals(difference)) {
			namespace = "com.creditharmony.fortune.delivery.dao.TripleCustomerDeliveryHistoryDao.findList";
		} else if ("cardExcel".equals(difference)) {
			namespace = "com.creditharmony.fortune.delivery.dao.TripleCustomerDeliveryHistoryDao.findCardList";
		}
		TripleDeliveryExportExcelUtils.exportData(ex, response, namespace, fileName, "1", difference);
	} 

}
