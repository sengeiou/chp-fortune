package com.creditharmony.fortune.delivery.web;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.creditharmony.common.util.DateUtils;
import com.creditharmony.core.excel.util.ExportExcel;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.web.BaseController;
import com.creditharmony.fortune.delivery.service.CustomerDeliveryHistoryManager;
import com.creditharmony.fortune.delivery.constant.Constant;
import com.creditharmony.fortune.delivery.entity.ext.DeliveryEx;
import com.creditharmony.fortune.template.entity.CustomerDeliveryHistoryExt;

/**
 * 客户交割历史查询
 * @Class Name CustomerDeliveryHistoryController
 * @author hutiyong
 * @Create In 2015年12月1日
 */
@Controller
@RequestMapping(value = "${adminPath}/delivery/customer/history")
public class CustomerDeliveryHistoryController extends BaseController{
	
	@Autowired
	private CustomerDeliveryHistoryManager customerDeliveryHistoryManager;
    
	/**
	 * 查询列表
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
			HttpServletResponse response, Model model) {
	    // 分页查询
		Page<DeliveryEx> page = new Page<DeliveryEx>(request,response);
		page = customerDeliveryHistoryManager.findPage(page, deliveryEx);
		// 设置页面列表显示
		model.addAttribute("page", page);
		model.addAttribute("deliveryEx",deliveryEx);
		
		return "/delivery/customerDeliveryHistoryList";
	}
	
	/**
	 * 导出客户交割历史查询列表
	 * 2016年1月8日
	 * By 胡体勇
	 * @param delivery
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	@RequestMapping(value="outExcel")
	public void outExcel(DeliveryEx deliveryEx,HttpServletRequest request,
			HttpServletResponse response){
		try {
			List<CustomerDeliveryHistoryExt> list = customerDeliveryHistoryManager.outExcel(deliveryEx);
			String fileName = Constant.OUT_EXCEL_TITLE_CUSTOMER_DELIVERY_HISTORY+"_"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
			ExportExcel exportExcel = new ExportExcel(Constant.OUT_EXCEL_TITLE_CUSTOMER_DELIVERY_HISTORY,CustomerDeliveryHistoryExt.class);
			exportExcel.setDataList(list);
			exportExcel.write(response, fileName);
			exportExcel.dispose();
		} catch (IOException e) {
			this.logger.error(Constant.OUT_EXCEL_TITLE_CUSTOMER_DELIVERY_HISTORY_ERROR, e);
		}
	}
}
