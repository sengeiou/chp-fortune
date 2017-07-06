package com.creditharmony.fortune.lend.finish.web;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.creditharmony.adapter.bean.in.dirswitch.DjrSwitchSendFileBean;
import com.creditharmony.adapter.bean.in.dirswitch.DjrSwitchSendUserInBean;
import com.creditharmony.adapter.bean.out.dirswitch.DjrSwitchSendUserOutBean;
import com.creditharmony.adapter.constant.ReturnConstant;
import com.creditharmony.adapter.constant.ServiceType;
import com.creditharmony.adapter.core.client.ClientPoxy;
import com.creditharmony.bpm.frame.view.WorkItemView;
import com.creditharmony.core.config.Global;
import com.creditharmony.core.fortune.type.FortuneSwitchApproveStatus;
import com.creditharmony.core.fortune.type.PayMent;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.users.type.FortuneOrgType;
import com.creditharmony.core.users.util.UserUtils;
import com.creditharmony.core.web.BaseController;
import com.creditharmony.fortune.common.service.AttachmentManager;
import com.creditharmony.fortune.common.service.CheckManager;
import com.creditharmony.fortune.customer.CustomerConstants;
import com.creditharmony.fortune.customer.entity.Customer;
import com.creditharmony.fortune.customer.entity.CustomerAccount;
import com.creditharmony.fortune.customer.entity.LoanApply;
import com.creditharmony.fortune.customer.service.AccountManager;
import com.creditharmony.fortune.customer.service.CustomerManager;
import com.creditharmony.fortune.customer.workflow.entity.LendApplyLog;
import com.creditharmony.fortune.lend.apply.view.LendApplyQueryView;
import com.creditharmony.fortune.lend.apply.view.LendApplyView;
import com.creditharmony.fortune.lend.approve.manager.LendApproveManager;
import com.creditharmony.fortune.lend.finish.manager.LendFinishFlowManager;
import com.creditharmony.fortune.lend.finish.manager.LendFinishManager;
import com.creditharmony.fortune.look.apply.util.ExportDJRExcel;
import com.creditharmony.fortune.utils.FortuneDictUtil;

/**
 * 已办（出借申请，审批）处理类
 * 
 * @Class Name LendFinishController
 * @author 朱杰
 * @Create In 2016年4月10日
 */
@Controller
@RequestMapping("${adminPath}/lend/finish")
@SuppressWarnings({ "unused", "resource" })
public class LendFinishController extends BaseController {
	@Autowired
	private LendFinishManager lendFinishManager;
	@Autowired
	private LendFinishFlowManager lendFinishFlowManager;
	@Autowired
	private CustomerManager customerManager;
	@Autowired
	private AccountManager accountManager;
	@Autowired
	private LendApproveManager lendApproveManager;
	@Autowired
	private AttachmentManager attachmentManager;
	@Autowired
	private CheckManager checkManager;

	/**
	 * 出借申请（我的已办）列表查询 2016年1月25日
	 * 
	 * @param apply
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping({ "fetchFinishItems" })
	public String fetchFinishItems(LendApplyQueryView apply, Model model, HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = queryParam(apply);
		map.put("list_name", "lend_apply");
		Page<LoanApply> page = new Page<LoanApply>(request, response);
		page.setOrderBy(CustomerConstants.DataViewConfig.DefaultOrderSql);
		page = lendFinishManager.findLendApply(page, map);
		model.addAttribute("lendApply", apply);
		model.addAttribute("page", page);
		model.addAttribute("SHNZ", PayMent.SHNZ.value);
		String sumLend = lendFinishManager.findLendApplyTotalMoney(map);
		if (null != sumLend && !"".equals(sumLend)) {
			double totalAmount = Double.parseDouble(sumLend);
			model.addAttribute("totalAmount", totalAmount);
		}
		FortuneDictUtil.addDicts(model, new String[] { "tz_lend_state", "tz_deduct_plat", "tz_open_bank", "tz_pay_type", "tz_channel_flag" });

		return "/lend/finish/lendApply_finish_list";
	}

	private Map<String, Object> queryParam(LendApplyQueryView apply) {
		apply.setBackMoneyDayBegin(getTimeOfDate(apply.getBackMoneyDayBegin(), false));
		apply.setBackMoneyDayEnd(getTimeOfDate(apply.getBackMoneyDayEnd(), true));

		Map<String, Object> map = new HashMap<String, Object>();
		// map.put("lendStatus", new String[] { LendState.DCJSP.value,
		// LendState.SPTG.value, LendState.SPBTG.value, LendState.CX.value });
		map.put("query", apply);
		// 划扣平台
		if (StringUtils.isNotBlank(apply.getDictApplyDeductType())) {
			map.put("dictApplyDeductType", apply.getDictApplyDeductType().split(","));
		}
		// 银行
		if (StringUtils.isNotBlank(apply.getAccountBank())) {
			map.put("accountBank", apply.getAccountBank().split(","));
		}
		if (StringUtils.isNotBlank(apply.getChannelMarking())) {
			map.put("channelMarking", apply.getChannelMarking().split(","));
		}
		if (StringUtils.isNotBlank(apply.getSwitchApproveStatus())) {
			map.put("switchApproveStatus", apply.getSwitchApproveStatus().split(","));
		}
		if (null != apply && apply.getStoreId() != null && !"".equals(apply.getStoreId())) {
			map.put("storeIds", apply.getStoreId().split(","));
		}
		if (StringUtils.isNotBlank(apply.getProductCode())) {
			map.put("productCode", apply.getProductCode().split(","));
		}
		if (StringUtils.isNotBlank(apply.getApplyPay())) {
			map.put("applyPay", apply.getApplyPay().split(","));
		}
		if (StringUtils.isNotBlank(apply.getLendStatus())) {
			map.put("lendStatus", apply.getLendStatus().split(","));
		}
		map.put("authFilter", true);
		return map;
	}

	/**
	 * 获取日期的最大、最小时间 2016年3月18日 By 肖长伟
	 * 
	 * @param date
	 * @param isMax
	 *            一天中的最大true; 一天中最小 false
	 * @return
	 */
	private Date getTimeOfDate(Date date, boolean isMax) {
		if (date != null) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			if (isMax) {
				calendar.set(Calendar.HOUR, 23);
				calendar.set(Calendar.MINUTE, 59);
				calendar.set(Calendar.SECOND, 59);
			} else {
				calendar.set(Calendar.HOUR, 0);
				calendar.set(Calendar.MINUTE, 0);
				calendar.set(Calendar.SECOND, 0);
			}
			return calendar.getTime();
		} else {
			return null;
		}
	}

	/**
	 * 获取审批已办列表 2016年1月25日
	 * 
	 * @param apply
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping({ "fetchApprovalFinishItems" })
	public String fetchApprovalFinishItems(LendApplyQueryView apply,
			Model model, HttpServletRequest request,
			HttpServletResponse response) {

		Page<LoanApply> page = new Page<LoanApply>(request, response);
		page.setOrderBy(CustomerConstants.DataViewConfig.DefaultOrderSql);

		Map<String, Object> map = new HashMap<String, Object>();
		// map.put("lendStatus", new String[] { LendState.SPTG.value,
		// LendState.SPBTG.value, LendState.DHK.value,
		// LendState.HKCLZ.value, LendState.HKCG.value,
		// LendState.HKSB.value, LendState.CX.value });
		map.put("query", apply);
		// 划扣平台
		if (StringUtils.isNotBlank(apply.getDictApplyDeductType())) {
			map.put("dictApplyDeductType", apply.getDictApplyDeductType()
					.split(","));
		}
		// 银行
		if (StringUtils.isNotBlank(apply.getAccountBank())) {
			map.put("accountBank", apply.getAccountBank().split(","));
		}

		if (null != apply && apply.getStoreId() != null
				&& !"".equals(apply.getStoreId())) {
			map.put("storeIds", apply.getStoreId().split(","));
		}
		// 出借状态
		if (StringUtils.isNotBlank(apply.getLendStatus())) {
			map.put("lendStatus", apply.getLendStatus().split(","));
		}
		map.put("authFilter", true);
		map.put("list_name", "lend_approval");
		page = lendFinishManager.findLendApply(page, map);

		model.addAttribute("lendApply", apply);
		model.addAttribute("page", page);
		String sumLend = lendFinishManager.findLendApplyTotalMoney(map);
		if (null != sumLend && !"".equals(sumLend)) {
			double totalAmount = Double.parseDouble(sumLend);
			model.addAttribute("totalAmount", totalAmount);
		}
		FortuneDictUtil.addDicts(model, new String[] { "tz_lend_state",
				"tz_deduct_plat", "tz_open_bank", "tz_pay_type" });

		return "/lend/finish/lendApply_approval_finish_list";
	}

	/**
	 * 金账户开户失败修改(托管状态变成申请中) 2016年4月19日 By 郭才林
	 * 
	 * @param apply
	 * @return
	 */
	@RequestMapping("/update")
	public String update(LoanApply apply, RedirectAttributes redirectAttributes) {

		int r = lendFinishManager.update(apply);
		if (r > 0) {
			redirectAttributes.addFlashAttribute("content",
					"出借编号【" + apply.getApplyCode() + "】已提交修改！");
		} else {
			redirectAttributes.addFlashAttribute("content",
					"出借编号【" + apply.getApplyCode() + "】信息未能修改或之前数据已被修改！请重试！");
		}

		return "redirect:" + this.adminPath + "/lend/finish/fetchFinishItems";
	}

	/**
	 * 金账户待附件上传，上传附件，数据提交至审批 2016年4月19日 By 郭才林
	 * 
	 * @param apply
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping("/trusteeshipSumbit")
	public String trusteeshipSumbit(WorkItemView workItem, LendApplyView view,
			LoanApply lendApply, RedirectAttributes redirectAttributes) {

		lendFinishFlowManager.launchFlow(workItem, lendApply, view);
		return "redirect:" + this.adminPath + "/lend/finish/fetchFinishItems";
	}

	/**
	 * 赎回内转 2016年1月25日
	 * 
	 * @param pageFlag
	 * @param lendCode
	 * @param customerCode
	 * @param model
	 * @return
	 */
	@RequestMapping("goDetail")
	public String goLendApplyDetail(String pageFlag, String lendCode,
			String customerCode, Model model) {
		LoanApply apply = new LoanApply();
		apply.setApplyCode(lendCode);
		apply = lendFinishManager.get(apply);

		Customer customer = new Customer();
		customer.setCustCode(customerCode);
		customer = customerManager.getCustomer(customer);

		CustomerAccount payBank = accountManager.get(apply.getPaymentBankId());
		CustomerAccount receive = accountManager.get(apply.getReceiveBankId());
		List<CustomerAccount> banks = new ArrayList<CustomerAccount>();
		banks.add(payBank);
		banks.add(receive);

		if ("finishApprovalDetail".equals(pageFlag)) {
			// 出借审批记录
			LendApplyLog log = new LendApplyLog();
			log.setLendCode(lendCode);
			List<LendApplyLog> list = lendApproveManager.findLendApplyLog(log);
			model.addAttribute("lendApplyLog",
					(null != list && list.size() > 0) ? list.get(0) : null);
		}

		List<LoanApply> transferLoanApplyList = lendFinishManager
				.findTransfers(lendCode);

		if (StringUtils.isNotBlank(apply.getOptions())) {
			String[] options = apply.getOptions().split("_");
			apply.setCheck1(options[0]);
			apply.setCheck2(options[1]);
		}

		model.addAttribute("pageFlag", pageFlag);
		model.addAttribute("apply", apply);
		model.addAttribute("customer", customer);
		model.addAttribute("banks", banks);
		model.addAttribute("transferLoanApplyList", transferLoanApplyList);

		String types[] = { "com_card_type", "tz_deduct_plat", "tz_open_bank",
				"tz_pay_type", "tz_contract_vesion", "tz_trust_state",
				"tz_lend_state", "tz_back_state", "tz_bill_day" };
		FortuneDictUtil.addDicts(model, types);

		return "/lend/finish/lendApply_detail_shnz";

	}
	
	/**
	 * 转投大金融
	 * @param lendCode
	 * @param model
	 * @return
	 */
	@RequestMapping("todjr")
	public ModelAndView todjr(String lendCode, Model model) {
		model.addAttribute("lendCode", lendCode);
		attachmentManager.deleteAttachmentDJR(lendCode);
		return new ModelAndView("/lend/finish/lendtodjr");
	}
	

	@RequestMapping(value="downloaddjr")
	public void downloaddjr(String type,HttpServletResponse response, Model model) throws IOException {
		System.out.println("进到下载方法了----type="+type);
		String root,strPathCZ,strFileCZName,strPathKH,strFileKHName,strPathDJB,strFileDJBName;
		root=Global.getConfig("uploadFilePath")+ "/userfiles/todjr/";
		strFileCZName="充值委托授权书.docx";
		strPathCZ=root+strFileCZName;
		response.setContentType("application/vnd.ms-word");
		strFileKHName="开户委托授权书.docx";
		strPathKH=root+strFileKHName;
//		response.setContentType("application/vnd.ms-word");
		strFileDJBName="签署授权书客户信息登记表.xlsx";
		strPathDJB=root+strFileDJBName;
//		response.setContentType("application/vnd.ms-excel");
		
		System.out.println("=====>"+strPathCZ);
//		File fileWord=new File(root+strFileCZName);
		/**
		 * 下载1    充值委托授权书  word
		 */
		if("1".equals(type) || type=="1"){
	    	File fileWord=new File("D:\\room\\eclipse2\\chp-fortune1\\src\\main\\webapp\\userfiles\\todjr\\"+strFileCZName);
	//    	File fileWord=new File(strPathCZ);  //上线的时候放开
			response.addHeader("Content-Disposition","attachment;filename="+new String( strFileCZName.getBytes("gb2312"), "ISO8859-1" ));
	        //写明要下载的文件的大小
	        response.setContentLength((int)fileWord.length());
	        //读出文件到i/o流
	        FileInputStream fis=new FileInputStream(fileWord);
	        BufferedInputStream buff=new BufferedInputStream(fis);
	 
	        byte [] b=new byte[1024];//相当于我们的缓存
	        long k=0;//该值用于计算当前实际下载了多少字节
	        //从response对象中得到输出流,准备下载
	        OutputStream myout=response.getOutputStream();
	        //开始循环下载
	        while(k<fileWord.length()){
	            int j=buff.read(b,0,1024);
	            k+=j;
	            //将b中的数据写到客户端的内存
	            myout.write(b,0,j);
	        }
	        //将写入到客户端的内存的数据,刷新到磁盘
	        myout.flush();
	        myout.close();
		}
        /**
		 *  下载2    签署授权书客户信息登记表   excel
		 */
        if("2".equals(type) || type=="2"){
	        response.setContentType("application/vnd.ms-excel");
	        File fileWord2=new File("D:\\room\\eclipse2\\chp-fortune1\\src\\main\\webapp\\userfiles\\todjr\\"+strFileDJBName);
	//    	File fileWord2=new File(strPathDJB);  //上线的时候放开
	        response.addHeader("Content-Disposition","attachment;filename="+new String( strFileDJBName.getBytes("gb2312"), "ISO8859-1" ));
	        //写明要下载的文件的大小
	        response.setContentLength((int)fileWord2.length());
	        //读出文件到i/o流
	        FileInputStream fis2=new FileInputStream(fileWord2);
	        BufferedInputStream buff2=new BufferedInputStream(fis2);
	 
	        byte [] b2=new byte[1024];//相当于我们的缓存
	        long k2=0;//该值用于计算当前实际下载了多少字节
	        //从response对象中得到输出流,准备下载
	        OutputStream myout2=response.getOutputStream();
	        //开始循环下载
	        while(k2<fileWord2.length()){
	            int j=buff2.read(b2,0,1024);
	            k2+=j;
	            //将b中的数据写到客户端的内存
	            myout2.write(b2,0,j);
	        }
	        //将写入到客户端的内存的数据,刷新到磁盘
	        myout2.flush();
	        myout2.close();
        }
        /**
		 * 下载3    开户委托授权书   word
		 */
        if("3".equals(type) || type=="3"){
	        response.setContentType("application/vnd.ms-word");
	        File fileWord3=new File("D:\\room\\eclipse2\\chp-fortune1\\src\\main\\webapp\\userfiles\\todjr\\"+strFileKHName);
	//    	File fileWord3=new File(strPathKH);  //上线的时候放开
	        response.addHeader("Content-Disposition","attachment;filename="+new String( strFileKHName.getBytes("gb2312"), "ISO8859-1" ));
	        //写明要下载的文件的大小
	        response.setContentLength((int)fileWord3.length());
	        //读出文件到i/o流
	        FileInputStream fis3=new FileInputStream(fileWord3);
	        BufferedInputStream buff3=new BufferedInputStream(fis3);
	 
	        byte [] b3=new byte[1024];//相当于我们的缓存
	        long k3=0;//该值用于计算当前实际下载了多少字节
	        //从response对象中得到输出流,准备下载
	        OutputStream myout3=response.getOutputStream();
	        //开始循环下载
	        while(k3<fileWord3.length()){
	            int j=buff3.read(b3,0,1024);
	            k3+=j;
	            //将b中的数据写到客户端的内存
	            myout3.write(b3,0,j);
	        }
	        //将写入到客户端的内存的数据,刷新到磁盘
	        myout3.flush();
	        myout3.close();
        }
	}
	/**
	 * 转投大金融提交
	 * 
	 * @param lendCode
	 * @param model
	 * @return
	 */
	@RequestMapping("todjrsubmit")
	@ResponseBody
	public String todjrsubmit(String lendCode, Model model) {
		synchronized (this) {
			Map<String, Object> resultMap = new HashMap<String, Object>();
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMddhhmmssSSS");
			String sn = df.format(new Date()).toString() + randomNumStr(3);
			String switchApproveStatus = lendFinishManager.getLoanApplySwitchApproveStatus(lendCode);
			if (FortuneSwitchApproveStatus.DSP.value.equals(switchApproveStatus) || FortuneSwitchApproveStatus.SPCG.value.equals(switchApproveStatus) || FortuneSwitchApproveStatus.ZTCG.value.equals(switchApproveStatus)) {
				DjrSwitchSendUserOutBean outParam = new DjrSwitchSendUserOutBean();
				outParam.setRetMsg("已有人对此进行转投，请勿重复操作！");
				return jsonMapper.toJson(outParam);
			}
			// 转投大金融所需数据
			DjrSwitchSendUserInBean loanApply = lendFinishManager.getLoanApplyDJR(lendCode);
			if (loanApply != null) {
				loanApply.setSn(sn);
				// 转投大金融所需附件
				List<DjrSwitchSendFileBean> fileList = attachmentManager.getAttachmentDJR(lendCode);
				if (fileList != null) {
					loanApply.setFileList(fileList);
				}
				ClientPoxy service = new ClientPoxy(ServiceType.Type.DJRSWITCH_SEND_USER_SERVICE);
				DjrSwitchSendUserOutBean outParam = (DjrSwitchSendUserOutBean) service.callService(loanApply);
				String djrResult = "成功!";
				if (ReturnConstant.SUCCESS.equals(outParam.getRetCode())) {
					LoanApply la = new LoanApply();
					la.setApplyCode(lendCode);
					la.setSwitchApproveStatus(FortuneSwitchApproveStatus.DSP.value);
					lendFinishManager.updateSwitchApproveStatus(la);
				}else{
					djrResult = "失败! 详情为：" + outParam.getRetMsg();
				}
				checkManager.addCheckDjr(lendCode, "提交转投大金融" + djrResult, "提交", "201612161601_djr", UserUtils.getUser(UserUtils.getUserId()).getName());
				return jsonMapper.toJson(outParam);
			}
			return null;
		}
	}

	/**
	 * 随机产生指定长度的数据字符串
	 * @param length指定长度
	 * @return 结果
	 */
	public static final String randomNumStr(int length) {
		if (length < 1) {
			return null;
		}
		Random numGen = new Random();
		char[] numbers = ("0123456789").toCharArray();
		char[] randBuffer = new char[length];
		for (int i = 0; i < randBuffer.length; i++) {
			randBuffer[i] = numbers[numGen.nextInt(10)];
		}
		return new String(randBuffer);
	}
	
	
	/**
	 * 转投大金融查看列表
	 * 
	 * @param lendCode
	 * @param model
	 * @return
	 */
	@RequestMapping({ "todjrcklist" })
	public String todjrcklist(LendApplyQueryView apply, Model model, HttpServletRequest request, HttpServletResponse response) {
		Page<LoanApply> page = new Page<LoanApply>(request, response);
		page.setOrderBy(CustomerConstants.DataViewConfig.DefaultOrderSql);

		Map<String, Object> map = queryParam(apply);
		map.put("viewType", "1");

		page = lendFinishManager.findLendApplyToDJRChk(page, map);
		model.addAttribute("lendApply", apply);
		model.addAttribute("page", page);
		model.addAttribute("SHNZ", PayMent.SHNZ.value);
		FortuneDictUtil.addDicts(model, new String[] { "tz_lend_state", "tz_deduct_plat", "tz_open_bank", "tz_pay_type", "tz_switch_approve_status", "tz_channel_flag", "tz_finish_state" });
		return "/lend/finish/lendtodjrCheckList";
	}

	/**
	 * 赎回内转 2016年1月25日
	 * 
	 * @param pageFlag
	 * @param lendCode
	 * @param customerCode
	 * @param model
	 * @return
	 */
	@RequestMapping("goDetailDJR")
	public String goDetailDJR(String pageFlag, String lendCode, String customerCode, Model model) {
		LoanApply apply = new LoanApply();
		apply.setApplyCode(lendCode);
		apply = lendFinishManager.get(apply);

		Customer customer = new Customer();
		customer.setCustCode(customerCode);
		customer = customerManager.getCustomer(customer);

		CustomerAccount payBank = accountManager.get(apply.getPaymentBankId());
		CustomerAccount receive = accountManager.get(apply.getReceiveBankId());
		List<CustomerAccount> banks = new ArrayList<CustomerAccount>();
		banks.add(payBank);
		banks.add(receive);

		if ("finishApprovalDetail".equals(pageFlag)) {
			// 出借审批记录
			LendApplyLog log = new LendApplyLog();
			log.setLendCode(lendCode);
			List<LendApplyLog> list = lendApproveManager.findLendApplyLog(log);
			model.addAttribute("lendApplyLog", (null != list && list.size() > 0) ? list.get(0) : null);
		}

		if (StringUtils.isNotBlank(apply.getOptions())) {
			String[] options = apply.getOptions().split("_");
			apply.setCheck1(options[0]);
			apply.setCheck2(options[1]);
		}

		model.addAttribute("pageFlag", pageFlag);
		model.addAttribute("apply", apply);
		model.addAttribute("customer", customer);
		model.addAttribute("banks", banks);

		String types[] = { "com_card_type", "tz_deduct_plat", "tz_open_bank", "tz_pay_type", "tz_contract_vesion", "tz_trust_state", "tz_lend_state", "tz_back_state", "tz_bill_day" };
		FortuneDictUtil.addDicts(model, types);

		return "/lend/finish/lendApply_detail_djr";
	}

	/**
	 * 
	 * @param lso
	 * @param request
	 * @param response
	 * @param m
	 */
	@RequestMapping("/exportExcel")
	public void exportExcel(LendApplyQueryView apply, HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> queryParam = queryParam(apply);
		queryParam.put("viewType", "1");
		queryParam.put("orgType", FortuneOrgType.CENTER.key);
		if (StringUtils.isNotEmpty(apply.getApplyIds())) {
			queryParam.put("applyIds", apply.getApplyIds().split(","));
		}
		ExportDJRExcel.exportData(queryParam, response);
	}
}
