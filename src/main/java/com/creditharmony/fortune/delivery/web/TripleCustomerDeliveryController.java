
package com.creditharmony.fortune.delivery.web;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.SerializationUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.creditharmony.common.util.ArrayHelper;
import com.creditharmony.common.util.DateUtils;
import com.creditharmony.core.common.type.SystemConfigConstant;
import com.creditharmony.core.excel.util.ImportExcel;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.users.entity.User;
import com.creditharmony.core.users.util.UserUtils;
import com.creditharmony.core.web.BaseController;
import com.creditharmony.fortune.common.service.IntKeyLogService;
import com.creditharmony.fortune.constants.ThreadPoolConstant;
import com.creditharmony.fortune.delivery.constant.Constant;
import com.creditharmony.fortune.delivery.facade.TripleCustomerDeliveryFacade;
import com.creditharmony.fortune.delivery.service.TripleCustomerDeliveryManager;
import com.creditharmony.fortune.delivery.utils.TripleDeliveryExportExcelUtils;
import com.creditharmony.fortune.donation.approve.manager.DonationApproveFlowManager;
import com.creditharmony.fortune.template.entity.TripleCustomerDeliveryExportModel;
import com.creditharmony.fortune.triple.system.entity.ext.IntDeliveryEx;
import com.creditharmony.fortune.utils.FortuneDictUtil;

/**
 * 三网客户交割
 * @Class Name TripleCustomerDeliveryController
 * @author 胡体勇
 * @Create In 2016年2月16日
 */
@Controller
@RequestMapping(value = "${adminPath}/delivery/tripleCustomer")
public class TripleCustomerDeliveryController extends BaseController {
	
	@Autowired
	private TripleCustomerDeliveryManager tripleCustomerDeliveryManager;
	@Autowired
	private TripleCustomerDeliveryFacade facade;
	@Autowired
	private IntKeyLogService intKeyLogService;
	@Autowired
	private DonationApproveFlowManager donationApproveFlowManager;
	
	private final ExecutorService executor = Executors.newFixedThreadPool(ThreadPoolConstant.FORTUNE_POOL_NUM);
	
	/**
	 * 三网客户交割列表
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
		Page<IntDeliveryEx> page  = new Page<IntDeliveryEx>(request,response);
		IntDeliveryEx  ex = (IntDeliveryEx) SerializationUtils.clone(intDeliveryEx);
		model.addAttribute("intDeliveryEx",ex);
		page = tripleCustomerDeliveryManager.findPage(page, intDeliveryEx);
		model.addAttribute("page", page);
		String[] types = {"tz_os_type","tz_order_status"};
		FortuneDictUtil.addDicts(model, types);
		return "/delivery/tripleCustomerDeliveryList";
	}
	
	/**
	 * 根据理财经理编号查询团队经理及营业部
	 * 2015年12月1日
	 * By hutiyong
	 * @param code
	 * @return
	 */
	@RequestMapping(value="findInfo",method=RequestMethod.POST)
	@ResponseBody
	public String findEmpInfoByCode(String code){
		List<IntDeliveryEx> result = tripleCustomerDeliveryManager.findEmpInfoByCode(code);
		return jsonMapper.toJson(result);
	}
	
	/**
	 * 根据理财经理编号查询团队经理及营业部(包含离职)
	 * 2015年12月1日
	 * By hutiyong
	 * @param code
	 * @return
	 */
	@RequestMapping(value="findStopInfo/{code}",method=RequestMethod.GET)
	@ResponseBody
	public String findStopEmpInfoByCode(@PathVariable("code") String code){
		List<IntDeliveryEx> result = tripleCustomerDeliveryManager.findStopEmpInfoByCode(code);
		return jsonMapper.toJson(result);
	}
	
	/**
	 * 三网客户批量交割
	 * 2016年2月23日
	 * By 胡体勇
	 * @return
	 */
	@RequestMapping(value="batchTripleCustomerDelivery",method=RequestMethod.POST)
	@ResponseBody
	public String batchTripleCustomerDelivery(String code){
		User user = (User) UserUtils.getSession().getAttribute(SystemConfigConstant.SESSION_USER_INFO);
		Map<String, Object> result = facade.batchTripleCustomerDelivery(code, "", user.getName(), null);
		return jsonMapper.toJson(result);
	}
	
	/**
	 * 三网理财经理客户交割
	 * 2016年2月25日
	 * By 胡体勇
	 * @param intDeliveryEx
	 * @return
	 */
	@RequestMapping(value="tripleManagerCustomerDelivery",method=RequestMethod.POST)
	@ResponseBody
	public String tripleManagerCustomerDelivery(IntDeliveryEx intDeliveryEx){
		String code = "";
		int result = facade.tripleManagerCustomerDelivery(intDeliveryEx);
		if(result == 0){
			code = Constant.NG;
		}else if(result == -1){
			code = Constant.NU;
		}else if(result > 0){
			code = Constant.OK;
		}
		return jsonMapper.toJson(code);
	}
	
	/**
	 * 三网理财经理业绩交割
	 * 2016年2月25日
	 * By 胡体勇
	 * @param intDeliveryEx
	 * @return
	 */
	@RequestMapping(value="tripleManagerAchievementDelivery",method=RequestMethod.POST)
	@ResponseBody
	public String tripleManagerAchievementDelivery(IntDeliveryEx intDeliveryEx){
		String code = "";
		int result = facade.tripleManagerAchievementDelivery(intDeliveryEx);
		if(result == 0){
			code = Constant.NG;
		}else if(result == -1){
			code = Constant.NU;
		}else if(result > 0){
			code = Constant.OK;
		}
		return jsonMapper.toJson(code);
	}
	
	/**
	 * 导表
	 * 2016年2月27日
	 * By 胡体勇
	 * @param intDeliveryEx
	 * @return
	 */
	@RequestMapping(value="outExcel")
	public void outExcel(IntDeliveryEx intDeliveryEx,HttpServletRequest request,
			HttpServletResponse response){
		try {
			IntDeliveryEx ex = tripleCustomerDeliveryManager.outExcel(intDeliveryEx);
			String fileName = Constant.OUT_EXCEL_TITLE_TRIPLE_CUSTOMER_DELIVERY+"_"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
			String namespace = "com.creditharmony.fortune.delivery.dao.TripleCustomerDeliveryDao.findList";
			TripleDeliveryExportExcelUtils.exportData(ex, response, namespace, fileName, "0", "excel");
		} catch (Exception e) {
			this.logger.error(Constant.OUT_TRIPLE_CUSTOMER_DELIVERY_EXCEL_ERROR, e);
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
		return "/delivery/tripleUploadShowDialog";
	}
	
	/**
	 * 上传Excel 交割
	 * 2016年1月11日
	 * By 胡体勇
	 * @return
	 */
	@RequestMapping(value="upload")
	@ResponseBody
	public String upload(@RequestParam("file") MultipartFile file){
		try {
			ImportExcel ie = new ImportExcel(file,0,0);
			List<TripleCustomerDeliveryExportModel> list = ie.getDataList(TripleCustomerDeliveryExportModel.class);
			final String num = DateUtils.getDate("yyyyMMddHHmmss");
			
			List<IntDeliveryEx> inList = new ArrayList<IntDeliveryEx>();
			for(int i = 0;i<list.size();i++){
				if(list.get(i) != null){
					String a = "";
					if(list.get(i).getEmpCode() != null){
						a = list.get(i).getEmpCode().toString();
					}
					String b = "";
					if(list.get(i).getNewEmpCode() != null){
						b = list.get(i).getNewEmpCode().toString();
					}
					if (StringUtils.isEmpty(list.get(i).getCustomerCode())
							&& StringUtils.isEmpty(a)
							&& StringUtils.isEmpty(list.get(i).getEmpName())
							&& StringUtils.isEmpty(list.get(i).getLoginName())
							&& StringUtils.isEmpty(b)
							&& StringUtils.isEmpty(list.get(i).getNewEmpName())
							&& StringUtils.isEmpty(list.get(i).getOrgName())
							&& StringUtils.isEmpty(list.get(i).getOsType())){
						continue;
					} else{
						IntDeliveryEx in = new IntDeliveryEx();
						in.setCustomerCode(list.get(i).getCustomerCode());
						in.setEmpCode(a);
						in.setEmpName(list.get(i).getEmpName());
						in.setLoginName(list.get(i).getLoginName());
						in.setNewEmpCode(b);
						in.setNewEmpName(list.get(i).getNewEmpName());
						in.setOrgName(list.get(i).getOrgName());
						in.setOsType(list.get(i).getOsType());
						inList.add(in);
					}
				}
			}
			if(inList.size() > 0){
				User user = (User) UserUtils.getSession().getAttribute(SystemConfigConstant.SESSION_USER_INFO);
				CompletionService<List<String>> completionService = new ExecutorCompletionService<List<String>>(executor);
				BlockingQueue<Future<List<String>>> queue = new LinkedBlockingQueue<Future<List<String>>>();
				final String userName = user.getName();
				for(final IntDeliveryEx ex : inList){
					try {
						if(ex != null){
							Future<List<String>> future = completionService.submit(new Callable<List<String>>() {
								public List<String> call() {
									// 校验导入交割数据
									IntDeliveryEx delivery = facade.checkExcelInfo(ex, num);
									String info = "";
									String infoCode = "";
									try {
										if(delivery != null){
											if(delivery.getRemark().equals("OK")){
												List<String> endCustomerCodeList = new ArrayList<String>();
												String phone = delivery.getPhone();
												String custName = delivery.getLoginName();
												String osType = delivery.getOsType();
												String empCode = delivery.getEmpCode();
												String newEmpCode = delivery.getNewEmpCode();
												String orderStatus = "";
												String cardId = "";
												String cardType = "";
												String custCode = delivery.getCustomerCode();
												String number = "";
												info = orderStatus+","+phone+","+empCode+","+newEmpCode+","+custName+","+osType+","+cardType+","+cardId+","+custCode+","+number;
												infoCode = infoCode + info + ";";
												facade.batchTripleCustomerDelivery(infoCode, num, userName, endCustomerCodeList);
												return endCustomerCodeList;
											}
										}
									} catch (Exception e) {
										intKeyLogService.log(e, "上传交割", "手机号为：" + delivery.getPhone() + "的客户上传交割异常");
									}
									return null;
								}
							});
							queue.add(future);
						}
					} catch (Exception e) {
						intKeyLogService.log(e, "上传交割", "客户姓名为" + ex.getLoginName() + "上传交割异常！");
					}
				}
				for (Future<List<String>> future : queue) {
					List<String> endCustomerCodeList = future.get();
					if (ArrayHelper.isNotEmpty(endCustomerCodeList)) {
						for (String customerCode : endCustomerCodeList) {
							donationApproveFlowManager.endAudit(customerCode);
						}
					}
				}
			}
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("num", num);
			map.put("size", list.size());
			return jsonMapper.toJson(map);
		} catch (Exception e) {
			this.logger.error("导入交割信息错误!",e);
			intKeyLogService.log(e, "上传交割", "导入交割信息错误!");
		}
		return null;
	}
}
