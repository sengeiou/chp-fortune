
package com.creditharmony.fortune.delivery.web;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.creditharmony.common.util.DateUtils;
import com.creditharmony.core.excel.util.ExportExcel;
import com.creditharmony.core.excel.util.ImportExcel;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.web.BaseController;
import com.creditharmony.fortune.delivery.constant.Constant;
import com.creditharmony.fortune.delivery.entity.ext.DeliveryEx;
import com.creditharmony.fortune.delivery.service.CustomerDeliveryManager;
import com.creditharmony.fortune.template.entity.CustomerDeliveryExt;

/**
 *客户交割
 * @Class Name CustomerDeliveryController
 * @author hutiyong
 * @Create In 2015年12月1日
 */
@Controller
@RequestMapping(value = "${adminPath}/delivery/customer")
public class CustomerDeliveryController extends BaseController{
	
	@Autowired
	private CustomerDeliveryManager  customerDeliveryManager;
	
	/**
	 * 查询列表页面
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
		// 分页查询
		Page<DeliveryEx> page = new Page<DeliveryEx>(request,response);
		page = customerDeliveryManager.findPage(page, deliveryEx);
		// 设置页面列表显示
		model.addAttribute("page", page);
		model.addAttribute("deliveryEx",deliveryEx);
		return "/delivery/customerDeliveryList";
	}
	
	/**
	 * 交割提醒列表显示
	 * 2016年1月20日
	 * By 胡体勇
	 * @param deliveryEx
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "deliveryRemindList")
	public String deliveryRemindList(DeliveryEx deliveryEx, HttpServletRequest request,
			HttpServletResponse response, Model model){
		 // 分页查询
		Page<DeliveryEx> page = new Page<DeliveryEx>(request,response);
		page = customerDeliveryManager.deliveryRemindList(page, deliveryEx);
		// 设置页面列表显示
		model.addAttribute("page", page);
		model.addAttribute("deliveryEx",deliveryEx);
		return "/delivery/deliveryRemindList";
	}
	
	/**
	 * 根据理财经理编号查询团队经理及营业部
	 * 2015年12月1日
	 * By hutiyong
	 * @param fmanagerCode
	 * @return
	 */
	@RequestMapping(value="findInfo",method=RequestMethod.POST)
	@ResponseBody
	public String findInfoByCode(String code){
		List<DeliveryEx> result = customerDeliveryManager.findInfoByCode(code);
		return jsonMapper.toJson(result);
	}
	
	/**
	 * 客户交割
	 * 2015年12月8日
	 * By 胡体勇
	 * @param custCode
	 * @param fManagerCode
	 * @param nfManagerCode
	 * @param delDate
	 * @param toDelDate
	 * @return
	 */
	@RequestMapping(value="custDelivery",method=RequestMethod.POST)
	@ResponseBody
	public String custDelivery(String custCode,String fManagerCode,String nfManagerCode,
			String delDate,String toDelDate,String delId){
		Map<String,String> map = new HashMap<String,String>(); 
		map.put("custCode", custCode);
		map.put("fManagerCode", fManagerCode);
		map.put("nfManagerCode", nfManagerCode);
		map.put("delDate", delDate);
		map.put("toDelDate", toDelDate);
		map.put("delId", delId);
		int result = customerDeliveryManager.custDelivery(map);
		return jsonMapper.toJson(result > 0 ? Constant.OK : Constant.NG);
	}
	
	/**
	 * 批量客户交割
	 * 2015年12月4日
	 * By 胡体勇
	 * @param code
	 * @return
	 */
	@RequestMapping(value="batchCustomerDelivery",method=RequestMethod.POST)
	@ResponseBody
    public String batchCustomerDelivery(String code){
		int result = customerDeliveryManager.batchCustomerDelivery(code);
		return jsonMapper.toJson(result > 0 ? Constant.OK : Constant.NG);
    }
    
	/**
	 * 导出客户交割列表
	 * 2016年1月8日
	 * By 胡体勇
	 * @param delivery
	 * @param request
	 * @param response
	 * @param code
	 * @throws IOException 
	 */
	@RequestMapping(value="outExcel")
	public void outExcel(DeliveryEx deliveryEx,HttpServletRequest request,
			HttpServletResponse response){
		try {
			List<CustomerDeliveryExt> list = customerDeliveryManager.outExcel(deliveryEx);
			String fileName = Constant.OUT_EXCEL_TITLE_CUSTOMER_DELIVERY+"_"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
			ExportExcel exportExcel = new ExportExcel(Constant.OUT_EXCEL_TITLE_CUSTOMER_DELIVERY,CustomerDeliveryExt.class);
			exportExcel.setDataList(list);
			exportExcel.write(response, fileName);
			exportExcel.dispose();
		} catch (IOException e) {
			this.logger.error(Constant.OUT_CUSTOMER_DELIVERY_EXCEL_ERROR, e);
		}
	}
	
	/**
	 * 导出交割提醒列表
	 * 2016年1月20日
	 * By 胡体勇
	 * @param deliveryEx
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="outRemindExcel")
	public void outRemindExcel(DeliveryEx deliveryEx,HttpServletRequest request,
			HttpServletResponse response){
		try {
			List<CustomerDeliveryExt> list = customerDeliveryManager.outRemindExcel(deliveryEx);
			String fileName = Constant.OUT_EXCEL_TITLE_CUSTOMER_DELIVERY+"_"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
			ExportExcel exportExcel = new ExportExcel(Constant.OUT_EXCEL_TITLE_CUSTOMER_DELIVERY,CustomerDeliveryExt.class);
			exportExcel.setDataList(list);
			exportExcel.write(response, fileName);
			exportExcel.dispose();
		} catch (IOException e) {
			this.logger.error(Constant.OUT_CUSTOMER_DELIVERY_EXCEL_ERROR, e);
		}
	}
	
	/**
	 * 页面点击上传按钮弹出选择框
	 * 2016年1月8日
	 * By 胡体勇
	 * @return
	 */
	@RequestMapping(value="showDialog")
	public String uploadDialogShow(){
		return "/delivery/uploadShowDialog";
	}
	
	/**
	 * 客户交割上传
	 * 2016年1月11日
	 * By 胡体勇
	 * @return
	 */
	@RequestMapping(value="upload")
	@ResponseBody
	public String upload(@RequestParam("file") MultipartFile file){
		int result = 0;
		try {
			ImportExcel ie = new ImportExcel(file,1,0);
			List<CustomerDeliveryExt> list = ie.getDataList(CustomerDeliveryExt.class);
			if (list == null || list.size() == 0) {
				return jsonMapper.toJson(Constant.NG);
			}else{
				result = customerDeliveryManager.importExcel(list);
			}
		} catch (Exception e) {
			this.logger.error("导入交割信息错误!",e);
		}
		return jsonMapper.toJson(result);
	}
	
	/**
	 * 页面批量带出理财经理信息
	 * 2016年1月12日
	 * By 胡体勇
	 * @param ids
	 * @return
	 */
	@RequestMapping(value="loadManagerInfo")
	@ResponseBody
	public String loadManagerInfo(String ids){
		List<DeliveryEx> result = customerDeliveryManager.loadManagerInfo(ids);
		return jsonMapper.toJson(result);
	}
}
