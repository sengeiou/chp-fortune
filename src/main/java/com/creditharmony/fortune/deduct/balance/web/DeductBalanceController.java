package com.creditharmony.fortune.deduct.balance.web;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.creditharmony.common.util.DateUtils;
import com.creditharmony.core.excel.util.ImportExcel;
import com.creditharmony.core.fortune.type.DeductPlat;
import com.creditharmony.core.fortune.type.DeductState;
import com.creditharmony.core.fortune.type.FortuneLogLevel;
import com.creditharmony.core.fortune.type.FortuneLogNode;
import com.creditharmony.core.fortune.type.PayMent;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.fortune.common.FortuneExceptionUtil;
import com.creditharmony.fortune.customer.entity.LoanApply;
import com.creditharmony.fortune.customer.service.LoanApplyManager;
import com.creditharmony.fortune.deduct.balance.service.DeductBalanceManager;
import com.creditharmony.fortune.deduct.common.Constant;
import com.creditharmony.fortune.deduct.common.DeductUtils;
import com.creditharmony.fortune.deduct.common.DeductUtils.TheDayEnum;
import com.creditharmony.fortune.deduct.common.QueryFilter;
import com.creditharmony.fortune.deduct.entity.DeductBespoke;
import com.creditharmony.fortune.deduct.entity.DeductPool;
import com.creditharmony.fortune.deduct.entity.ext.DeductPoolEx;
import com.creditharmony.fortune.deduct.entity.ext.FyImportDeductResult;
import com.creditharmony.fortune.deduct.entity.ext.HylImportDeductResult;
import com.creditharmony.fortune.deduct.entity.ext.TonglianImportDeductResult;
import com.creditharmony.fortune.deduct.entity.ext.ZhjinImportDeductResult;
import com.creditharmony.fortune.deduct.facade.CommonFacade;
import com.creditharmony.fortune.deduct.facade.DeductBespokeFacade;
import com.creditharmony.fortune.deduct.facade.DeductManagerFacade;
import com.creditharmony.fortune.deduct.service.DeductBespokeManager;
import com.creditharmony.fortune.deduct.service.DeductManager;
import com.creditharmony.fortune.deduct.web.DeductCommon;
import com.creditharmony.fortune.triple.system.entity.ext.IntDeliveryEx;
import com.creditharmony.fortune.triple.system.facade.SendTripleInfoFacade;
import com.google.common.collect.Lists;

/**
 * 划扣结算控制层
 * @Class Name DeductBalanceController
 * @author 韩龙
 * @Create In 2015年12月1日
 */
@Controller
@RequestMapping(value = "${adminPath}/deductBalance")
public class DeductBalanceController extends DeductCommon{
	
	@Autowired
	private DeductBalanceManager deductBalanceManager;
	
	@Autowired
	private DeductManager deductManager;
	
	@Autowired
	private DeductBespokeManager deductBespokeManager;
	
	@Autowired
	private DeductManagerFacade deductManagerFacade;
	
	@Autowired
	private DeductBespokeFacade deductBespokeFacade;
	
	@Autowired
	private CommonFacade commonFacade;
	
	@Autowired
	private SendTripleInfoFacade sendTripleInfoFacade;
	
	@Autowired
	private LoanApplyManager loanApplyManager;
	
	/**
	 * 划扣结算-->划扣结算列表
	 * 2015年11月30日
	 * By 韩龙
	 * @param deductPoolEx
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/balanceList")
	public String balanceList(DeductPoolEx deductPoolEx, HttpServletRequest request,
				HttpServletResponse response,Model model) {
		List<String> dictDeductStatusList = Lists.newArrayList();
		// 设置计划划扣日期
//		deductPoolEx.setApplyDeductDateStart(new Date());
//		deductPoolEx.setApplyDeductDateEnd(new Date());
		// 分页查询 FAIL
		Page<DeductPoolEx> page = new Page<DeductPoolEx>(request, response);
		// 划扣待结算
		dictDeductStatusList.add(DeductState.DHKJS.value);
		// 划扣处理中
		dictDeductStatusList.add(DeductState.XSHK.value);
		// 预约划扣
		dictDeductStatusList.add(DeductState.YYHK.value);
		// 设置查询划扣状态
		deductPoolEx.setDeductStatusList(dictDeductStatusList);
		deductPoolEx.setGoldAccountFlag(PayMent.HK.value);
		// 单笔划扣标识
		deductPoolEx.setDayDeductFlag(TheDayEnum.DBHK.value);
		// 过滤掉老数据
		deductPoolEx.setFilterSql(Constant.filterSql);
		DeductUtils.assembleDeductPoolEx(deductPoolEx);
		page = deductManager.findPage(page, deductPoolEx);
		deductPoolEx.setApplyOrApproveFalg(Constant.APPLY_OR_APPROVE_FALG);
		// 获取字典项
		DeductUtils.getDictList(model);
		// 设置页面数据及查询条件回填
		model.addAttribute("page", page).addAttribute("deductPoolExt",
				deductPoolEx);
		// 当前条件下的出借金额与划扣金额
		Map<String,String> sumMoney = deductManager.getDeductSumMoney(deductPoolEx);
		model.addAttribute("sumMoney",sumMoney);
		return "deduct/deductBalanceList";
	}
	
	/**
	 * 划扣结算-->线上划扣弹框跳转
	 * 2015年12月2日
	 * By 韩龙
	 * @return
	 */
	@RequestMapping(value = "/onLineDeductShow")
	public String onLineDeductShow(){
		return "deduct/deductShowModalDialogOnLine";
	}
	
	/**
	 * 划扣结算-->线下划扣弹框跳转
	 * 2015年12月24日
	 * By 韩龙
	 * @param model
	 * @param lendCodes
	 * @return
	 */
	@RequestMapping(value = "/offLineDeductShow")
	public String offLineDeductShow(Model model,String lendCodes){
		model.addAttribute("lendCodes", lendCodes);
		// 获取字典项
		DeductUtils.getDictList(model);
		return "deduct/deductShowModalDialogOffLine";
	}
	
	/**
	 * 划扣结算-->线上划扣
	 * 2015年12月24日
	 * By 韩龙
	 * @param deductPoolExt
	 * @param request
	 * @param response
	 * @param model
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/onLineDeduct")
	@ResponseBody
	public String onLineDeduct(DeductPoolEx deductPoolExt, HttpServletRequest request,
			HttpServletResponse response,String ids,Model model){
		// 修改划扣状态并增加任务
		List<String> massge = deductManagerFacade.insertTaskThread(deductPoolExt,ids,DeductState.XSHK.value);
		if(massge.size()>0){
			StringBuffer buf = new StringBuffer();
			for (String string : massge) {
				if(string != null && !"".equals(string)){
					buf.append(string).append("<br/>");
				}
			}
//			addMessage(model, buf.toString());
			return jsonMapper.toJson(buf.toString());
		}else{
			addMessage(model, "已添加划扣任务");
			return jsonMapper.toJson(Constant.SUCCESS);
			
		}
//		return balanceList(deductPoolExt,request,response,model)/*"redirect:" + adminPath + "/deductBalance/balanceList"*/;
	}
	
	/**
	 * 划扣结算-->线下划扣导入数据
	 * 2016年1月22日
	 * By 韩龙
	 * @param file
	 * @param tp
	 * @param exportPtId
	 * @param exportType
	 * @param lendCodes
	 * @return
	 */
	@RequestMapping(value = "/importOffLineDeduct",method=RequestMethod.POST)
	public @ResponseBody String importOffLineDeduct(@RequestParam("file") MultipartFile file,@RequestParam("tp")String tp,
			@RequestParam("exportPtId")String exportPtId,@RequestParam("exportType")String exportType,
			@RequestParam("lendCodes")String lendCodes){
		StringBuffer message = new StringBuffer();
		try {
			Map<String,Object> filter = new HashMap<String,Object>();
			filter.put("tp", tp);
			filter.put("exportPtId", exportPtId);
			filter.put("exportType", exportType);
			filter.put("lendCodes", lendCodes);
			// 上传类型为excel
			Map<String,DeductPool> map = null;
			if(DeductPlat.FYPT.value.equals(exportPtId)){
				ImportExcel ei = new ImportExcel(file, 0, 0);
				List<FyImportDeductResult> list = ei.getDataList(FyImportDeductResult.class);
				if (list == null || list.size() == 0) {
					return jsonMapper.toJson("上传文件没有数据");
				}
				// 财富去重复
				map = DeductUtils.fuYouResultRepeat(list);
			}else if(DeductPlat.HYLPT.value.equals(exportPtId)){
				ImportExcel ei = new ImportExcel(file, 1, 1);
				List<HylImportDeductResult> list = ei.getDataList(HylImportDeductResult.class);
				if (list == null || list.size() == 0) {
					return jsonMapper.toJson("上传文件没有数据");
				}
				// 好易联去重复
				map = DeductUtils.hylResultRepeat(list);
			}else if(DeductPlat.ZJPT.value.equals(exportPtId)){
				ImportExcel ei = new ImportExcel(file, 1, 0);
				List<ZhjinImportDeductResult> list = ei.getDataList(ZhjinImportDeductResult.class);
				if (list == null || list.size() == 0) {
					return jsonMapper.toJson("上传文件没有数据");
				}
				// 中金去重复
				map = DeductUtils.zhJinResultRepeat(list);
			}else {
				ImportExcel ei = new ImportExcel(file, 0, 0);
				List<TonglianImportDeductResult> list = ei.getDataList(TonglianImportDeductResult.class);
				if (list == null || list.size() == 0) {
					return jsonMapper.toJson("上传文件没有数据");
				}
				// 通联去重复
				map = DeductUtils.tongLianResultRepeat(list);
			}
			deductManagerFacade.saveExcel(map,message);
		} catch (Exception e) {
			logger.error("导入用户失败！失败信息："+e.getMessage(),e);
			FortuneExceptionUtil.insertException(FortuneExceptionUtil.newException(e, null, FortuneLogNode.DEDUCT_BALANCE.getCode(),
					FortuneLogLevel.YELLOW.value, "导入用户失败！失败信息文件格式不正确"));
			return jsonMapper.toJson("导入用户失败！失败信息文件格式不正确");
		}
		return jsonMapper.toJson("导入划扣成功");
	}
	
	/**
	 * 划扣结算-->线下划扣导出数据
	 * 2015年12月24日
	 * By 韩龙
	 * @param request
	 * @param model
	 * @param response
	 */
	@RequestMapping(value = "/exportOffLineDeduct")
	public void exportOffLineDeduct(HttpServletRequest request,Model model,HttpServletResponse response){
		// 查询条件
		QueryFilter filter=new QueryFilter(request);
		// 查出要导出的信息
//		List<DeductExportExcel> temList = deductManager.getDeductExportExcel(filter.getFilters());
		// 导出excel或TXT
		download(null, filter.getFilters(), response);
	}
	
	/**
	 * 协议库对接
	 * 2016年3月12日
	 * By 韩龙
	 * @param ids
	 * @param deductPoolEx
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "protocolLibrary")
	@ResponseBody
	public String protocolLibrary(String ids,DeductPoolEx deductPoolEx, HttpServletRequest request,
			HttpServletResponse response,Model model){
//		String [] codes=null;
//		// 判断参数是否为空
//		if ( ids != null ){
//			codes = ids.split(",");
//		}
		List<String> massages = deductManager.protocolLibrary(DeductUtils.getCodes(ids));
		StringBuffer buf = new StringBuffer("协议库对接失败:");
		if(massages.size() > 0){
			for (String massage : massages) {
				buf.append(massage).append("<br>");
			}
//			addMessage(model, buf.toString());
			return jsonMapper.toJson(buf.toString());
		}else{
//			addMessage(model, "协议库对接成功");
			return jsonMapper.toJson(Constant.SUCCESS);
		}
		
		// 向客户端输出返回结果
//		return jsonMapper.toJson(result > 0 ? Constant.SUCCESS:Constant.FAIL);
//		return balanceList(deductPoolEx,request,response,model);
	}
	
	/**
	 * 划扣结算-->批量修改划扣状态
	 * 2015年12月24日
	 * By 韩龙
	 * @param deductPoolExt
	 * @param deductPlatFormID
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "/batchDeductStatus")
	@ResponseBody
	public String batchDeductStatus(DeductPoolEx deductPoolExt,String deductPlatFormID,
			RedirectAttributes redirectAttributes) {
		StringBuffer messge = new StringBuffer();
		String[] lendCodes = null;
		if (deductPlatFormID.isEmpty()) {
//			return "redirect:" + adminPath + "/deductBalance/balanceList";
			return jsonMapper.toJson("请选择要修改的记录...");
		}
		lendCodes = deductPlatFormID.split(",");
		// 批量修改状态
//		int result = deductManager.batchDeductApplyUpdate(lendCodes,DeductState.HKSB.value, null);
		for (String str : lendCodes) {
			// 出借编号
			String lendCode = str.split("_")[0];
			// 更新时间
			String verTime = str.split("_")[1];
			DeductPool dp = new DeductPool();
			dp.setApplyCode(lendCode);
			dp.setVerTime(verTime);
			dp = deductManager.get(dp);
			if(dp == null){
				messge.append("业务编号【"+lendCode+"】已被其它业务人员处理").append("</br>");
				logger.debug("业务编号【"+lendCode+"】已被其它业务人员处理");
				continue;
			}
			dp.setDictApplyDeductType(deductPoolExt.getDictApplyDeductType());
			dp.setFailReason(deductPoolExt.getConfirmOpinion());
			dp.setDictDeductStatus(deductPoolExt.getDictDeductStatus());
			dp.setDealTime(DateUtils.getDate());
			try {
				deductBalanceManager.batchDeductUpdate(dp);
				if(deductPoolExt.getDictDeductStatus().equals(DeductState.HKCG.value)){
					LoanApply loanApply = new LoanApply();
					loanApply.setApplyCode(lendCode);
					loanApply = loanApplyManager.get(loanApply);
					// 发送短信
					commonFacade.smsInfo(lendCode, dp.getDictDeductStatus(), loanApply);
					// 制作文件
					commonFacade.makeFileInfo(lendCode);
					// 三网首单校
					sendTripleInfoFacade.tripleInfo(loanApply.getCustCode(), loanApply.getApplyCode());
					
				}
			} catch (Exception e) {
				messge.append("业务编号【"+lendCode+"】已被其它业务人员处理").append("</br>");
				logger.debug("业务编号【"+lendCode+"】已被其它业务人员处理,失败信息为:"+e.getMessage(),e);
				FortuneExceptionUtil.newException(e, lendCode, FortuneLogNode.DEDUCT_BALANCE.getCode(),
						FortuneLogLevel.YELLOW.value, e.getMessage());
			}
//			deductBalanceManager.saveBackMoney(dp);
		}
//		Map<String,String> map = new HashMap<String, String>();
		return jsonMapper.toJson(messge.length() > 0 ? messge.toString():Constant.SUCCESS);
	}
	
	/**
	 * 划扣结算-->办理
	 * 2015年12月7日
	 * By 韩龙
	 * @param lendCode
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "balanceConduct")
	public String balanceConduct(String lendCode, Model model,RedirectAttributes redirectAttributes) {
		DeductPool pool = new DeductPool();
		String[] strArray = lendCode.split("_");
		pool.setApplyCode(strArray[0]);
		pool.setVerTime(strArray[1]);
		pool = deductBalanceManager.get(pool);
		if(pool == null){
			addMessage(redirectAttributes, "业务编号【"+strArray[0]+"】已被人处理过");
			return "redirect:" + adminPath + "/deductBalance/balanceList";
		}
		model.addAttribute("deductPool", pool);
		return "deduct/deductBalanceDetailed";
	}
	
	/**
	 * 划扣结算-->办理-->退回状态
	 * 2015年12月24日
	 * By 韩龙
	 * @param lendCode
	 * @param redirectAttributes
	 * @return
	 */
	/*@RequestMapping(value = "/goBack")
	public String goBack(String lendCode,RedirectAttributes redirectAttributes) {
		String[] code = lendCode.split(",");
		int result = deductBalanceManager.batchDeductApplyUpdate(code,DeductState.HKJSTH.value, null);
		if(result == 0){
			addMessage(redirectAttributes, "操作回退失败");
			return "redirect:" + adminPath + "/deductBalance/balanceList";
		}
		addMessage(redirectAttributes, "操作回退成功");
		return "redirect:" + adminPath + "/deductBalance/balanceList";
	}*/
	
	/**
	 * 划扣结算-->办理-->办理提交
	 * 2015年12月24日
	 * By 韩龙
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "/saveBalanceConduct")
	public String saveBalanceConduct(HttpServletRequest request,
			HttpServletResponse response, RedirectAttributes redirectAttributes) {
		// 初始化对象
		QueryFilter filter = new QueryFilter(request);
		// 获取deductPoolExt对象
		int result = deductBalanceManager.approve(filter.getFilters());
		if(filter.getFilters().get("dictDeductStatus").equals(DeductState.HKCG.value)){
			String lendCode = (String) filter.getFilters().get("applyCode");
			LoanApply loanApply = new LoanApply();
			loanApply.setApplyCode(lendCode);
			loanApply = loanApplyManager.get(loanApply);
			// 发送短信
			commonFacade.smsInfo(lendCode, DeductState.HKCG.value,loanApply);
			// 制作文件
			commonFacade.makeFileInfo(lendCode);
			// 三网首单校
			sendTripleInfoFacade.tripleInfo(loanApply.getCustCode(), loanApply.getApplyCode());
		}
		if (result > 0) {
			addMessage(redirectAttributes, "修改状态成功");
		} else {
			String message = "修改状态失败";
			if(result == -999){
				String lendCode = (String) filter.getFilters().get("applyCode");
				message = "业务编号【"+lendCode+"】已被其它业务人员处理";
			}
			addMessage(redirectAttributes, message);
		}
		return "redirect:" + adminPath + "/deductBalance/balanceList";
	}
	
	/**
	 * 调转批量修改状态页面
	 * 2016年1月27日
	 * By 韩龙
	 * @return
	 */
	@RequestMapping(value = "showBatchModel")
	public String showBatchModel(Model model){
		model.addAttribute("failOrBalanceFalg", "Balance");
		return "deduct/theDaydeductShowModal";
	}
	
	/**
	 * 预约划扣弹框
	 * 2016年2月18日
	 * By 韩龙
	 * @param id
	 * @param model
	 * @param applyDeductTypeName
	 * @return
	 */
	@RequestMapping(value = "/theDayDeduct")
	public String theDayDeduct(String id ,Model model,String applyDeductTypeName){
		/*String[] array = id.split(",");*/
		model.addAttribute("id", id);
	/*	if(id.length()==0){
			model.addAttribute("deductCount", 0);
		}else{
			model.addAttribute("deductCount", array.length);
		}
		//DeductPlat.initDeductPlat();
		for (String key : DeductPlat.deductPlatMap.keySet()) {
			if(applyDeductTypeName.equals(DeductPlat.deductPlatMap.get(key))){
			}
		}*/
		model.addAttribute("nowDate", new Date());
		return "deduct/deductShowModalDialogTheDay";
	}
	
	/**
	 * 保存预约划扣信息
	 * 2016年2月18日
	 * By 韩龙
	 * @param deductBespoke
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/saveDeductBespoke")
	@ResponseBody
	public String saveDeductBespoke(DeductBespoke deductBespoke,Model model){
		List<String> messages = deductBespokeFacade.saveDeductBespokeFacade(deductBespoke,DeductState.DHKJS.value);
		StringBuffer buf = new StringBuffer();
		if(messages.size() > 0){
			for (String message : messages) {
				if(message != null && !"".equals(message)){
					buf.append(message).append("</br>");
				}
			}
			return jsonMapper.toJson(buf.toString());
		}else{
			return jsonMapper.toJson(Constant.SUCCESS);
		}
	}
	
	/**
	 * 跳转到取消预约划扣列表弹出框
	 * 2016年2月19日
	 * By 韩龙
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("/goCancelDeductBespoke")
	public String goCancelDeductBespoke(DeductPoolEx deductPoolEx,HttpServletRequest request,HttpServletResponse response,Model model){
		List<String> list = Lists.newArrayList();
		list.add(DeductState.YYHK.value);
//		list.add(DeductState.ECYYHK.value);
		deductPoolEx.setDeductStatusList(list);
		deductPoolEx.setBespokeStatus(DeductState.YYY.value);
		Page<DeductPoolEx> page = new Page<DeductPoolEx>(request, response);
		page = deductManager.findPage(page, deductPoolEx);
		page.setFuncName("ajaxPage");
		model.addAttribute("pageUrl", "/deductBalance/goCancelDeductBespokePage");
		model.addAttribute("page", page);
		// 获取字典项
		DeductUtils.getDictList(model);
		return "deduct/cancelDeductBespoke";
	}
	
	/**
	 * 异步分页
	 * 2016年2月19日
	 * By 韩龙
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("/goCancelDeductBespokePage")
	public String goCancelDeductBespokePage(DeductPoolEx deductPoolEx,HttpServletRequest request,HttpServletResponse response,Model model){
		List<String> list = Lists.newArrayList();
		list.add(DeductState.YYHK.value);
//		list.add(DeductState.ECYYHK.value);
		deductPoolEx.setDeductStatusList(list);
		deductPoolEx.setBespokeStatus(DeductState.YYY.value);
		Page<DeductPoolEx> page = new Page<DeductPoolEx>(request, response);
		page = deductManager.findPage(page, deductPoolEx);
		page.setFuncName("ajaxPage");
		model.addAttribute("pageUrl", "/deductBalance/goCancelDeductBespokePage");
		model.addAttribute("page", page);
		// 获取字典项
		DeductUtils.getDictList(model);
		return "deduct/cancelDeductBespokeList";
	}

	/**
	 * 取消预约划扣
	 * 2016年2月19日
	 * By 韩龙
	 * @param id
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("/cancelDeductBespoke")
	@ResponseBody
	public String cancelDeductBespoke(String id,HttpServletRequest request,HttpServletResponse response,Model model){
		if(id == null || "".equals(id) ){
			return jsonMapper.toJson("取消预约划扣失败");
		}
		int result = deductBespokeManager.cancelDeductBespoke(id,DeductState.DHKJS.value);
		return jsonMapper.toJson(result > 0 ? Constant.SUCCESS:"取消预约划扣失败");
	}
	
	/**
	 * 划扣结算-->撤销功能
	 * 2016年2月17日
	 * By 韩龙
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/updateRevoke")
	@ResponseBody
	public String updateRevoke(HttpServletResponse response,HttpServletRequest request){
		QueryFilter filter = new QueryFilter(request);
		filter.addFilter("approve", "approve");
		String message = deductManagerFacade.batchUpdate(filter.getFilters());
		return jsonMapper.toJson(message.length() > 0 ? message:Constant.SUCCESS);
	}
	
}
