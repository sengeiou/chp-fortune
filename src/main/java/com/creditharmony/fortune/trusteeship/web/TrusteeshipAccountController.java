package com.creditharmony.fortune.trusteeship.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.creditharmony.adapter.bean.BaseOutInfo;
import com.creditharmony.adapter.constant.ReturnConstant;
import com.creditharmony.common.util.ArrayHelper;
import com.creditharmony.common.util.DateUtils;
import com.creditharmony.common.util.PropertyUtil;
import com.creditharmony.core.common.type.BooleanType;
import com.creditharmony.core.dict.util.DictUtils;
import com.creditharmony.core.excel.util.ImportExcel;
import com.creditharmony.core.fortune.type.FuYouAccountBackState;
import com.creditharmony.core.fortune.type.PayMent;
import com.creditharmony.core.fortune.type.SmsType;
import com.creditharmony.core.fortune.type.TrustState;
import com.creditharmony.core.fortune.type.YoN;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.web.BaseController;
import com.creditharmony.fortune.common.entity.Check;
import com.creditharmony.fortune.common.service.CheckManager;
import com.creditharmony.fortune.constants.ThreadPoolConstant;
import com.creditharmony.fortune.customer.dao.CustomerDao;
import com.creditharmony.fortune.customer.entity.Customer;
import com.creditharmony.fortune.sms.entity.SmsTemplate;
import com.creditharmony.fortune.sms.manager.SmsManager;
import com.creditharmony.fortune.trusteeship.dao.TrusteeshipAccountDao;
import com.creditharmony.fortune.trusteeship.entity.ProtocalExportExcel;
import com.creditharmony.fortune.trusteeship.entity.TrusteeshipAccount;
import com.creditharmony.fortune.trusteeship.entity.TrusteeshipImportExcel;
import com.creditharmony.fortune.trusteeship.manager.TrusteeshipAccountManager;
import com.creditharmony.fortune.utils.FileUtil;
import com.creditharmony.fortune.utils.FortuneDictUtil;
import com.creditharmony.fortune.utils.OptionUtil;
import com.creditharmony.fortune.utils.SmsUtil;

/**
 * 金账户开户
 * 
 * @Class Name TrusteeshipAccountController
 * @author 朱杰
 * @Create In 2016年2月12日
 */
@Controller
@RequestMapping(value = "${adminPath}/trusteeship/account")
public class TrusteeshipAccountController extends BaseController {

	@Autowired
	private TrusteeshipAccountManager trusteeshipAccountManager;
	@Autowired
	private CheckManager checkManager;
	@Autowired
	private SmsManager smsManager;
	@Autowired
	private TrusteeshipAccountDao trusteeshipAccountDao;
	@Autowired
	private CustomerDao customerDao;
	
	private final ExecutorService executor = Executors.newFixedThreadPool(ThreadPoolConstant.FORTUNE_POOL_NUM);

	/**
	 * 金账户开户列表 2016年2月13日 By 朱杰
	 * 
	 * @param search
	 * @param request
	 * @param response
	 * @param m
	 * @return
	 */
	@RequestMapping(value = { "list", "" })
	public String accountList(TrusteeshipAccount search,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		Properties properties = PropertyUtil
				.getProperties("trusteeship_config.properties");
		String autoFlag = properties.getProperty("auto_docking", "0").trim();
		String message = null;
		if ("0".equals(autoFlag) || "false".equals(autoFlag)) {
			message = "手动开户";
		} else {
			message = "自动开户";
		}

		Page<TrusteeshipAccount> page = new Page<TrusteeshipAccount>(request,
				response);
		page = trusteeshipAccountManager.getList(page, search);
		if(null != page){
			List<TrusteeshipAccount> dataList = page.getList();
			if(ArrayHelper.isNotEmpty(dataList)){
				for(TrusteeshipAccount item : dataList){
					String payType = item.getPayType();
					if(PayMent.ZJTG.value.equals(payType)){
						item.setAddrprovince(OptionUtil.getLable(item.getAddrprovince()));
						item.setAddrcity(OptionUtil.getLable(item.getAddrcity()));
					}
				}
			}
		}
		model.addAttribute("page", page);
		model.addAttribute("search", search);
		model.addAttribute("autoFlag", message);
		
		FortuneDictUtil.addDicts(model, new String[]{"tz_trust_state", "tz_open_bank"});
		
		return "trusteeship/account/main";
	}

	/**
	 * 导出excel 2016年2月27日
	 * 
	 * @author 孙凯文
	 * @param search
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("exportExcel")
	public void exportExcel(TrusteeshipAccount search,
			HttpServletRequest request, HttpServletResponse response,String[] customerCodes,
			Model model) {
		List<TrusteeshipAccount> dataList = trusteeshipAccountManager.getAllSelective(search,customerCodes);
		if (ArrayHelper.isNotEmpty(dataList)) {
			TrusteeshipAccount account = null;
			for (int i = 0; i < dataList.size(); i++) {
				account = dataList.get(i);
				account.setIndex((i + 1) + "");
				String bankName = DictUtils.getDictLabel(account.getBankId(),
						"tz_open_bank", "");
				account.setBankName(bankName);
				
				// 屏蔽客户姓名/手机号/身份证号
				account.setCustomerName("***");
				account.setCustomerCertNum("***");
				account.setMobilephone("***");
			}
		}
		FileUtil.exportExcel(dataList, TrusteeshipAccount.class,
				"PW10_" + DateUtils.getDate("yyyyMMdd") + "_0001", response);
	}

	/**
	 * 导入页面跳转
	 * 2016年2月29日
	 * 
	 * @author 孙凯文
	 * @param model
	 */
	@RequestMapping("toImportPage")
	public String goImportPage(Model model) {
		return "trusteeship/account/importExcel";
	}

	/**
	 * 导入excel 2016年2月27日
	 * 
	 * @author 孙凯文
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("importExcel")
	@ResponseBody
	public String importExcel(
			@RequestParam("file") CommonsMultipartFile[] files,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		Map<String, String> message = new HashMap<String, String>();
		int updateCnt=0;
		try {
			if (null != files && files.length > 0) {
				for (int i = 0; i < files.length; i++) {
					if (!files[i].isEmpty()) {
						ImportExcel excel = new ImportExcel(files[i], 0, 0);
						List<TrusteeshipImportExcel> list = excel.getDataList(
								TrusteeshipImportExcel.class, 1);
						if (ArrayHelper.isNotEmpty(list)) {
							updateCnt = trusteeshipAccountManager.importExcel(list);
							message.put("result", BooleanType.TRUE);
							message.put("message", "导入成功,更新"+updateCnt+"条数据");
						} else {
							message.put("result", BooleanType.FALSE);
							message.put("message", "文档有效数据为空");
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			message.put("result", BooleanType.FALSE);
			message.put("message", e.getMessage());
		}

		return message.get("message");
	}

	/**
	 * 导出协议库 2016年2月29日
	 * 
	 * @author 孙凯文
	 * @param search
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping("exportProtocolExcel")
	public void exportProtocolExcel(TrusteeshipAccount search, String[] customerCodes,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		
		List<ProtocalExportExcel> dataList = trusteeshipAccountManager.getProtocalList(search, customerCodes);

		if (ArrayHelper.isNotEmpty(dataList)) {
			ProtocalExportExcel excel = null;
			for (int i = 0; i < dataList.size(); i++) {
				excel = dataList.get(i);
				String bankName = DictUtils.getDictLabel(excel.getBankId(),
						"tz_open_bank", "");
				excel.setBankName(bankName);
				String certName = DictUtils.getDictLabel(
						excel.getCredentialsType(), "com_certificate_type", "");
				excel.setCredentialsName(certName);
				excel.setBusinessName("AC01(代收)");
				excel.setAccountProperty("借记卡");
				excel.setCallBack("否");
				
				// 屏蔽客户姓名/手机号/身份证号
				excel.setCustomerName("***");
				excel.setMobilePhone("***");
				excel.setCredentialsNum("***");
			}
		}

		FileUtil.exportExcel(dataList, ProtocalExportExcel.class,
				"金账户导出协议库" + DateUtils.getDate("yyyy-MM-dd"), response);
	}

	/**
	 * 金账户开户 2016年2月23日 By 朱杰
	 * 
	 * @param customerLendCodes
	 *            客户编号,出借编号;
	 * @return
	 */
	@RequestMapping(value = { "openAccount" })
	@ResponseBody
	public String openAccount(TrusteeshipAccount search, String[] customerCodes) {
		//获取金账户开户的信息
		List<TrusteeshipAccount> list = trusteeshipAccountManager.getAllSelective(search, customerCodes);
		String rtnMsg = "";
		if(ArrayHelper.isNotEmpty(list)){
			CompletionService<String> completionService = new ExecutorCompletionService<String>(executor);
			int callCount = list.size();
			for(final TrusteeshipAccount trusteeshipAccount : list){
				try {
					completionService.submit(new Callable<String>() {
						public String call() {
							// 每条处理的返回结果
							String rtnMsgForeach="";
							try{
								rtnMsgForeach = trusteeshipAccountManager.openAccount(trusteeshipAccount, false);
							}catch(Exception e){
								return trusteeshipAccount.getMobilephone()+ "开户失败:"+e.getLocalizedMessage()+"<br>";
							}
							if(StringUtils.isBlank(rtnMsgForeach)){
								try{
									//开户成功，发送短信
									SmsTemplate template = smsManager.getSmsTemplate(SmsType.JZH_KH.value);
									String content = template.getTemplateContent();
									content=content.replace("{#Name#}", trusteeshipAccount.getCustomerName())
											.replace("{#mobilephone#}", trusteeshipAccount.getMobilephone());
									BaseOutInfo result = SmsUtil.sendSms(trusteeshipAccount.getMobilephone(), content);
									if(null != result && !ReturnConstant.SUCCESS.equals(result.getRetCode())){
										return trusteeshipAccount.getMobilephone()+"开户成功，但是短信发送失败<br>";
									}else{
										// 如果发送成功保存发送履历
										trusteeshipAccountManager.insertSmsHis(trusteeshipAccount,content);
									}
									return "";
								}catch(Exception ex){
									logger.error("开户成功，但是短信发送失败",ex);
									return trusteeshipAccount.getMobilephone()+"开户成功，但是短信发送失败<br>";
								}
								
							}else{
								//开户失败,返回添加消息
								return rtnMsgForeach;
							}
						}
					});
				} catch (Exception e) {
					logger.error(trusteeshipAccount.getMobilephone()+"开户失败",e);
					//此处提交线程发生异常，提交线程数减去1
					return trusteeshipAccount.getMobilephone()+"开户失败<br>";
				}
			}
			// 组装返回结果
			for(int i =0; i< callCount; i++){
				try{
					Future<String> future = completionService.take();
					rtnMsg += future.get();
				}catch(Exception e){
					logger.error("completionService.take()失败",e);
				}				
			}
		}
		
		return jsonMapper.toJson(rtnMsg);
	}

	/**
	 * 协议库对接 2016年2月29日 By 朱杰
	 * 
	 * @param customerLendCodes
	 */
	@RequestMapping("excuteProtocol")
	@ResponseBody
	public String exportProtocol(TrusteeshipAccount search, String[] customerCodes) {
		
		String rtnMsg = trusteeshipAccountManager.exportProtocol(search, customerCodes);
		return jsonMapper.toJson(rtnMsg);
	}

	/**
	 * 自动开户配置 2016年3月1日 By 孙凯文
	 * 
	 * @param status
	 * @param request
	 * @return
	 */
	@RequestMapping("setAutoOpenAccount")
	@ResponseBody
	public String setAutoOpenAccount(String status, HttpServletRequest request) {
		Properties properties = PropertyUtil
				.getProperties("trusteeship_config.properties");
		String autoFlag = properties.getProperty("auto_docking", "0").trim();

		Map<String, String> map = new HashMap<String, String>();
		map.put("result", "true");
		if ("0".equals(autoFlag) || "true".equals(autoFlag)) {
			// 没有相应配置信息
			autoFlag = "false";
			map.put("message", "设置为手动开户");
		} else if ("false".equals(autoFlag)) {
			// 自动开户
			autoFlag = "true";
			map.put("message", "设置为自动开户");
		}

		PropertyUtil.updateProperties("trusteeship_config.properties", "auto_docking", autoFlag);
		map.put("autoFlag", autoFlag);
		
		return jsonMapper.toJson(map);
	}

	/**
	 * 弹出修改窗体 2016年3月1日 By 朱杰
	 * 
	 * @param customerLendCodes
	 * @return
	 */
	@RequestMapping(value = { "toChangeStatusPage" })
	public String toChangeStatusPage(
			@RequestParam(value = "lendCustomerCode") String lendCustomerCode,
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		if (StringUtils.isEmpty(lendCustomerCode)
				|| lendCustomerCode.split(";").length == 1) {
			return null;
		}
		model.addAttribute("customerCode", lendCustomerCode.split(";")[0]);
		model.addAttribute("lendCode", lendCustomerCode.split(";")[1]);
		model.addAttribute("verTime", lendCustomerCode.split(";")[2]);
		return "trusteeship/account/approval";
	}

	/**
	 * 修改状态
	 * 
	 * @param customerLendCodes
	 */
	@RequestMapping("changeStatus")
	@ResponseBody
	public String changeStatus(TrusteeshipAccount trusteeshipAccount) {
		// 设置手机号
		Customer cus = new Customer(trusteeshipAccount.getCustomerCode());
		cus = customerDao.getCustomerbyCode(cus);
		trusteeshipAccount.setMobilephone(cus.getCustMobilephone());
		if (YoN.SHI.value.equals(trusteeshipAccount.getChgStatus())) {
			// 成功
			try {
			trusteeshipAccountManager
					.updateTrusteeship(trusteeshipAccount,
							TrustState.KHCG.value,
							FuYouAccountBackState.JYCG.value, "", true);
			} catch (Exception e) {
				return jsonMapper.toJson(e.getMessage());
			}
			
			Customer customer = new Customer();
			customer.setCustCode(trusteeshipAccount.getCustomerCode());
			customer = customerDao.getCustomer(customer);
			//开户成功，发送短信
			SmsTemplate template = smsManager.getSmsTemplate(SmsType.JZH_KH.value);
			if(null != template){
				String content = template.getTemplateContent();
				content=content.replace("{#Name#}", customer.getCustName())
						.replace("{#mobilephone#}", customer.getCustMobilephone());
				SmsUtil.sendSms(customer.getCustMobilephone(), content);
				//保存发送履历
				
					trusteeshipAccountManager.insertSmsHis(trusteeshipAccount,content);

			}
		} else {
			// 退回
			try {
				trusteeshipAccountManager.updateTrusteeship(trusteeshipAccount,
						TrustState.KHSB.value, "",
						trusteeshipAccount.getTrusteeshipRetMsg(), true);
			} catch (Exception e) {
				return jsonMapper.toJson(e.getMessage());
			}
			
		}

		return jsonMapper.toJson("修改成功");
	}

	/**
	 * 出借留痕 2016年2月26日 By 朱杰
	 * 
	 * @param search
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping({ "fullMark" })
	public String failList(Check check, HttpServletRequest request,
			HttpServletResponse response, Model model) {

		Page<Check> page = checkManager.findCheckList(new Page<Check>(request,
				response), check);
		model.addAttribute("page", page);

		return "fullMark/fullMark";
	}
}