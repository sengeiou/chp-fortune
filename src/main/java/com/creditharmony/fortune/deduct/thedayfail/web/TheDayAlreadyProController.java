package com.creditharmony.fortune.deduct.thedayfail.web;

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

import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.excel.util.ImportExcel;
import com.creditharmony.core.fortune.type.DeductPlat;
import com.creditharmony.core.fortune.type.DeductState;
import com.creditharmony.core.fortune.type.FortuneLogLevel;
import com.creditharmony.core.fortune.type.FortuneLogNode;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.fortune.common.FortuneExceptionUtil;
import com.creditharmony.fortune.common.service.CheckManager;
import com.creditharmony.fortune.customer.entity.LoanApply;
import com.creditharmony.fortune.customer.service.LoanApplyManager;
import com.creditharmony.fortune.deduct.common.Constant;
import com.creditharmony.fortune.deduct.common.DeductUtils;
import com.creditharmony.fortune.deduct.common.QueryFilter;
import com.creditharmony.fortune.deduct.entity.DeductBespoke;
import com.creditharmony.fortune.deduct.entity.ThedayDeductPool;
import com.creditharmony.fortune.deduct.entity.ext.DeductPoolEx;
import com.creditharmony.fortune.deduct.entity.ext.ImportDeductResult;
import com.creditharmony.fortune.deduct.facade.CommonFacade;
import com.creditharmony.fortune.deduct.facade.DeductBespokeFacade;
import com.creditharmony.fortune.deduct.facade.TheDayDeductFacade;
import com.creditharmony.fortune.deduct.service.DeductBespokeManager;
import com.creditharmony.fortune.deduct.service.DeductManager;
import com.creditharmony.fortune.deduct.service.TheDayDeductManager;
import com.creditharmony.fortune.deduct.web.DeductCommon;
import com.creditharmony.fortune.triple.system.entity.ext.IntDeliveryEx;
import com.creditharmony.fortune.triple.system.facade.SendTripleInfoFacade;
import com.google.common.collect.Lists;

/**
 * 分天划扣已处理列表
 * @Class Name TheDayDeductController
 * @author 韩龙
 * @Create In 2016年1月23日
 */
@Controller
@RequestMapping(value = "${adminPath}/theDayAlreadPor")
public class TheDayAlreadyProController extends DeductCommon{
	
	@Autowired
	private DeductManager deductManager;
	
	@Autowired
	private TheDayDeductManager theDayDeductManager;
	
	@Autowired
	private DeductBespokeManager deductBespokeManager;
	
	@Autowired
	private DeductBespokeFacade deductBespokeFacade;
	
	@Autowired
	private TheDayDeductFacade theDayDeductFacade;
	
	@Autowired
	private SendTripleInfoFacade sendTripleInfoFacade;
	
	@Autowired
	private CommonFacade commonFacade;
	
	@Autowired
	private LoanApplyManager loanApplyManager;
	
	@Autowired
	private CheckManager checkManager;
	
	/**
	 * 分页查询
	 * 2016年1月26日
	 * By 韩龙
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/theDayDeductList")
	public String theDayDeductList(DeductPoolEx deductPoolEx,HttpServletRequest request,
				HttpServletResponse response,Model model) {
		List<String> deductStatusList = Lists.newArrayList();
//		deductPoolEx.setDictDeductStatus(DeductState.DFTHK.value);
		// 分天划扣失败状态
		deductStatusList.add(DeductState.HKSB.value);
		deductStatusList.add(DeductState.HKCG.value);
		deductStatusList.add(DeductState.FTQRSB.value);
		// 二次预约划扣状态
		deductStatusList.add(DeductState.ECYYHK.value);
		// 二次线上划扣状态
		deductStatusList.add(DeductState.ECXSHK.value);
		// 分天划扣标识
		deductPoolEx.setDayDeductFlag("1");
		deductPoolEx.setDeductStatusList(deductStatusList);
		DeductUtils.assembleDeductPoolEx(deductPoolEx);
//		deductPoolEx.setDictDeductStatusOr(DeductState.HKSPTH.value);
		// 分页查询
		Page<DeductPoolEx> page = new Page<DeductPoolEx>(request, response);
		page = theDayDeductManager.findPage(page, deductPoolEx);
		// 设置页面数据及查询条件回填
		model.addAttribute("page", page).addAttribute("deductPoolExt",deductPoolEx);
		DeductPoolEx deductPoolExToMoney = theDayDeductManager.getApplyLendMoneyAndActualDeductMoney(deductPoolEx);
		model.addAttribute("actualDeductMoney", deductPoolExToMoney.getActualDeductMoney());
		model.addAttribute("applyLendMoney",deductPoolExToMoney.getApplyLendMoney());
		// 获取字典项
		DeductUtils.getDictList(model);
		return "deduct/theDayAlreadyProList";
	}
	
	/**
	 * 分天划扣-->线上划扣弹框
	 * 2016年1月26日
	 * By 韩龙
	 * @return
	 */
	@RequestMapping(value = "/onLineDeductShow")
	public String onLineDeductShow(){
		return "deduct/deductShowModalDialogOnLine";
	}
	
	/**
	 * 分天划扣-->线下划扣弹框跳转
	 * 2016年1月26日
	 * By 韩龙
	 * @param model
	 * @param lendCodes
	 * @return
	 */
	@RequestMapping(value = "/offLineDeductShow")
	public String offLineDeductShow(Model model,String lendCodes){
		model.addAttribute("lendCodes", lendCodes);
		return "deduct/theDayDeductShowModalOffLine";
	}
	
	/**
	 * 分天划扣-->线上划扣
	 * 2016年1月26日
	 * By 韩龙
	 * @param deductPoolExt
	 * @param request
	 * @param response
	 * @param model
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/onLineDeduct")
	public String onLineDeduct(DeductPoolEx deductPoolExt, HttpServletRequest request,
			HttpServletResponse response, Model model,String ids){
		// 修改划扣状态并增加任务
		List<String> message = theDayDeductFacade.sendDeductThread(deductPoolExt,ids,DeductState.ECXSHK.value);
		if(message != null && message.size() > 0){
			StringBuffer buf = new StringBuffer();
			for (String string : message) {
				if(string != null && !"".equals(string)){
					buf.append(string).append("<br/>");
				}
			}
			addMessage(model, buf.toString());
		}else{
			addMessage(model, "已添加划扣任务");
		}
		return theDayDeductList(deductPoolExt,request,response,model);
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
		try {
			Map<String,Object> filter = new HashMap<String,Object>();
			filter.put("tp", tp);
			filter.put("exportPtId", exportPtId);
			filter.put("exportType", exportType);
			filter.put("lendCodes", lendCodes);
			// 上传类型为excel
			ImportExcel ei = new ImportExcel(file, 1, 1);
			int groups=0;
			if(DeductPlat.FYPT.value.equals(exportPtId)){
				groups = StringUtils.toInteger(DeductPlat.FYPT.value);
			}else if(DeductPlat.ZJPT.value.equals(exportPtId)){
				groups = StringUtils.toInteger(DeductPlat.ZJPT.value);
			}else if(DeductPlat.TL.value.equals(exportPtId)){
				groups = StringUtils.toInteger(DeductPlat.TL.value);
			}else {
				groups = StringUtils.toInteger(DeductPlat.HYLPT.value);
			}
			List<ImportDeductResult> list = ei.getDataList(ImportDeductResult.class,groups);
			if (list == null || list.size() == 0) {
				return jsonMapper.toJson("上传文件没有数据");
			}
			// 本段代码可查看TheDayDeductController这个类，两个共用一个方法
//			theDayDeductManager.saveExcel(list, filter);  

		} catch (Exception e) {
			return jsonMapper.toJson("导入用户失败！失败信息："+e.getMessage());
		}
		return jsonMapper.toJson("导入用户成功");
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
		filter.addFilter("theDay", "theDay");
		// 查出要导出的信息
//		List<DeductExportExcel> temList= deductManager.getDeductExportExcel(filter.getFilters());
		// 导出excel或TXT
		download(null, filter.getFilters(), response);
	}
	
	/**
	 * 协议库对接
	 * 2015年12月24日
	 * By 韩龙
	 * @param ids
	 * @param response
	 */
	@RequestMapping(value = "protocolLibrary")
	@ResponseBody
	public String protocolLibrary(String ids,HttpServletResponse response){
		String [] codes=null;
		// 判断参数是否为空
		if ( ids != null ){
//			codes = ids.split(",");
			codes = DeductUtils.getCodes(ids);
		}
		List<String> massages = theDayDeductManager.protocolLibrary(codes);
		StringBuffer buf = new StringBuffer("协议库对接失败:");
		if(massages.size() > 0){
			for (String massage : massages) {
				buf.append(massage).append("<br>");
			}
//			addMessage(model, buf.toString());
			return jsonMapper.toJson(buf.toString());
		}else{
			return jsonMapper.toJson(Constant.SUCCESS);
		}
	}
	
	/**
	 * 调转批量修改状态页面
	 * 2016年1月27日
	 * By 韩龙
	 * @return
	 */
	@RequestMapping(value = "showBatchModel")
	public String showBatchModel(Model model){
		model.addAttribute("failOrBalanceFalg", "theDay");
		return "deduct/theDaydeductShowModal";
	}
	
	/**
	 * 提交批量划扣修改划扣状态
	 * 2016年1月27日
	 * By 韩龙
	 * @return
	 */
	@RequestMapping(value = "theayBatchStatus")
	@ResponseBody
	public String theDayBatchStatus(HttpServletRequest request){
		QueryFilter filter = new QueryFilter(request);
		StringBuffer message = new StringBuffer();
		String theDayId =(String) filter.getFilters().get("theDayId");
		String[] theDayIds = theDayId.split(",");
		for (String strCode : theDayIds) {
			// 分天划扣对象
			ThedayDeductPool tdp = new ThedayDeductPool();
			String[] strArray = strCode.split("_");
			// 分天划扣id
			String id = strArray[0];
			// 分天划扣更新时间(排它标识)
			String verTime = strArray[1];
			tdp.setId(id);
			tdp.setVerTime(verTime);
			try {
				theDayDeductManager.batchUpdateStatus(filter.getFilters(),tdp,message);
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("id", id);
				Map<String,String> resultMap = theDayDeductManager.getTheDaysById(map);
				LoanApply loanApply = new LoanApply();
				loanApply.setApplyCode(resultMap.get("lend_code"));
				loanApply = loanApplyManager.get(loanApply);
				if(filter.getFilters().get("dictDeductStatus").equals(DeductState.HKCG.value)){
					tdp.setVerTime(null);
					tdp = theDayDeductManager.get(tdp);
					// 
					if(theDayDeductManager.checkTheDayList(tdp.getDeductApplyId())){
						continue;
					}
					// 发送短信
					commonFacade.smsInfo(loanApply.getApplyCode(), DeductState.HKCG.value,loanApply);
					// 制作文件
					commonFacade.makeFileInfo(loanApply.getCustCode());
					// 三网首单校
					sendTripleInfoFacade.tripleInfo(loanApply.getCustCode(),loanApply.getApplyCode());
				}
				checkManager.addCheck(resultMap.get("lend_code"), 
						"分天划扣已处理批量修改状态", DeductState.getDeductState(filter.getFilters().get("dictDeductStatus")+""),
						id,FortuneLogNode.DEDUCT_THEDAY);
			} catch (Exception e) {
				String info = "业务编号【"+id+"】已被其它业务人员处理过";
				logger.error(info+",失败信息："+e.getMessage(), e);
				message.append(info).append("</br>");
				FortuneExceptionUtil.insertException(FortuneExceptionUtil.newException(e, null,
						FortuneLogNode.DEDUCT_THEDAYFAIL.getCode(), FortuneLogLevel.YELLOW.value, 
						info));
			}
		}
		return jsonMapper.toJson(message.length() > 0 ? message.toString():Constant.SUCCESS);
	}
	
	/**
	 * 预约划扣弹框
	 * 2016年1月27日
	 * By 韩龙
	 * @return
	 */
	@RequestMapping(value = "/theDayDeduct")
	public String theDayDeduct(String id ,Model model,String applyDeductTypeName){
/*		String[] array = id.split(",");
		BigDecimal actualDeductMoneySum = theDayDeductManager.getActualDeductMoneySum(array);
		model.addAttribute("actualDeductMoneySum", actualDeductMoneySum);*/
		model.addAttribute("id", id);
/*		if(id.length()==0){
			model.addAttribute("deductCount", 0);
		}else{
			model.addAttribute("deductCount", array.length);
		}*/
/*		//DeductPlat.initDeductPlat();
		for (String key : DeductPlat.deductPlatMap.keySet()) {
			if(applyDeductTypeName.equals(DeductPlat.deductPlatMap.get(key))){
			}
		}*/
		//model.addAttribute("dictApplyDeductType", "2");
		model.addAttribute("nowDate", new Date());
		return "deduct/deductShowModalDialogTheDay";
		
	}
	
	/**
	 * 保存预约划扣信息
	 * 2016年2月16日
	 * By 周俊
	 * @param id
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/saveDeductBespoke")
	@ResponseBody
	public String saveDeductBespoke(DeductBespoke deductBespoke){
		List<String> messages = deductBespokeFacade.saveDeductBespokeTheDayFacade(deductBespoke,DeductState.ECYYHK.value);
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
	 * 预约确认失败
	 * 2016年1月31日
	 * By 韩龙
	 * @return
	 */
	@RequestMapping(value = "confirmFail")
	@ResponseBody
	public String confirmFail(Model model,String ids){
		StringBuffer message = new StringBuffer();
		if(ids != null ){
			String [] codes = ids.split(",");
			for (String strPare : codes) {
				String[] strArray = strPare.split("_");
				// 划扣申请id
				String  deductApplyId = strArray[0];
				// 更新时间(排它标识)
				String verTime = strArray[1];
				ThedayDeductPool tdp = new ThedayDeductPool();
				tdp.setDeductApplyId(deductApplyId);
				tdp.setVerTime(verTime);
				try {
					theDayDeductManager.updateFail(tdp,message);
				} catch (Exception e) {
					String info = "此条记录已被其它业务人员处理过";
					logger.error(info+",失败信息："+e.getMessage(),e);
					message.append(info).append("</br>");
					FortuneExceptionUtil.insertException(FortuneExceptionUtil.newException(e, null,
							FortuneLogNode.DEDUCT_THEDAYFAIL.getCode(), FortuneLogLevel.YELLOW.value, 
							info));
				}
			}
		}
		return jsonMapper.toJson(message.length() > 0 ? message.toString():Constant.SUCCESS);
	}
	
	/**
	 * 跳转到取消预约划扣列表弹出框
	 * 2016年2月18日
	 * By 周俊
	 * @return
	 */
	@RequestMapping("/goCancelDeductBespoke")
	public String goCancelDeductBespoke(DeductPoolEx deductPoolEx,HttpServletRequest request,HttpServletResponse response,Model model){
		List<String> list = Lists.newArrayList();
//		list.add(DeductState.YYHK.value);
		list.add(DeductState.ECYYHK.value);
		deductPoolEx.setDeductStatusList(list);
		deductPoolEx.setBespokeStatus(DeductState.YYY.value);
		Page<DeductPoolEx> page = new Page<DeductPoolEx>(request, response);
		page = theDayDeductManager.findPage(page, deductPoolEx);
		page.setFuncName("ajaxPage");
		model.addAttribute("pageUrl", "/theDayAlreadPor/goCancelDeductBespokePage");
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
//		list.add(DeductState.YYHK.value);
		list.add(DeductState.ECYYHK.value);
		deductPoolEx.setDeductStatusList(list);
		deductPoolEx.setBespokeStatus(DeductState.YYY.value);
		Page<DeductPoolEx> page = new Page<DeductPoolEx>(request, response);
		page = theDayDeductManager.findPage(page, deductPoolEx);
		page.setFuncName("ajaxPage");
		model.addAttribute("pageUrl", "/theDayAlreadPor/goCancelDeductBespokePage");
		model.addAttribute("page", page);
		// 获取字典项
		DeductUtils.getDictList(model);
		return "deduct/cancelDeductBespokeList";
	}
	/**
	 * 取消预约划扣
	 * 2016年2月19日
	 * By 周俊
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
/*		int result = deductBespokeManager.cancelDeductBespokeTheDay(id,DeductState.HKSB.value);
		return jsonMapper.toJson(result > 0 ? Constant.SUCCESS:"取消预约划扣失败");*/
		// 返回message信息
		StringBuffer message = new StringBuffer();
		String[] arrayId = id.split(",");
		for (String idParam : arrayId) {
			// 组装分天划扣对象
			ThedayDeductPool tdp = new ThedayDeductPool();
			String[] strArray = idParam.split("_");
			// 分天划扣id
			String thedayid = strArray[0];
			// 修改时间（排它标识）
			String verTime = strArray[1];
			tdp.setId(thedayid);
			tdp.setVerTime(verTime);
			try {
				deductBespokeManager.cancelDeductBespokeTheDay(tdp,DeductState.HKSB.value,message);
			} catch (Exception e) {
				String info = "分天划扣业务编号【"+id+"】已被其它业务人员处理";
				logger.error(info+",失败信息："+e.getMessage(),e);
				message.append(info).append("</br>");
				FortuneExceptionUtil.insertException(FortuneExceptionUtil.newException(e, null,
						FortuneLogNode.DEDUCT_THEDAYFAIL.getCode(), FortuneLogLevel.YELLOW.value, 
						info));
			}
		}
		return jsonMapper.toJson(message.length() > 0 ? message.toString():Constant.SUCCESS);
	}
}
