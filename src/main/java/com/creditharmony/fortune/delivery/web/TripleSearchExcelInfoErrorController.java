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
import com.creditharmony.fortune.delivery.service.TripleSearchExcelInfoErrorManager;
import com.creditharmony.fortune.delivery.utils.TripleDeliveryExportExcelUtils;
import com.creditharmony.fortune.triple.system.entity.ext.IntDeliveryEx;
import com.creditharmony.fortune.utils.FortuneDictUtil;

/**
 * @Class Name TripleSearchExcelInfoErrorController
 * @author 胡体勇
 * @Create In 2016年3月2日
 */
@Controller
@RequestMapping(value = "${adminPath}/delivery/tripleCustomer/errorList")
public class TripleSearchExcelInfoErrorController extends BaseController{
	
	@Autowired
	private TripleSearchExcelInfoErrorManager tripleSearchExcelInfoErrorManager;
 
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
		page = tripleSearchExcelInfoErrorManager.findPage(page, intDeliveryEx);
		// 设置页面列表显示
		model.addAttribute("page", page);
		model.addAttribute("intDeliveryEx",intDeliveryEx);
		String[] types = {"tz_os_type"};
		FortuneDictUtil.addDicts(model, types);
		return "/delivery/tripleSearchExcelInfoErrorList";
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
	public void outExcel(IntDeliveryEx intDeliveryEx, HttpServletRequest request,
			HttpServletResponse response){
		IntDeliveryEx ex = tripleSearchExcelInfoErrorManager.outExcel(intDeliveryEx);
		String fileName = Constant.OUT_EXCEL_TITLE_TRIPLE_CUSTOMER_DELIVERY_ERROR_HISTORY+"_"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
		String namespace = "com.creditharmony.fortune.delivery.dao.TripleSearchExcelInfoErrorDao.findList";
		TripleDeliveryExportExcelUtils.exportData(ex, response, namespace, fileName, "2", "excel");
	} 
}
