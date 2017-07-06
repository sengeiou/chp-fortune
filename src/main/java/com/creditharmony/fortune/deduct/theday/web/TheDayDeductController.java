package com.creditharmony.fortune.deduct.theday.web;

import java.util.ArrayList;
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

import com.creditharmony.common.util.ArrayHelper;
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
import com.creditharmony.fortune.deduct.entity.DeductPool;
import com.creditharmony.fortune.deduct.entity.ThedayDeductPool;
import com.creditharmony.fortune.deduct.entity.ext.DeductPoolEx;
import com.creditharmony.fortune.deduct.entity.ext.FyImportDeductResult;
import com.creditharmony.fortune.deduct.entity.ext.HylImportDeductResult;
import com.creditharmony.fortune.deduct.entity.ext.TonglianImportDeductResult;
import com.creditharmony.fortune.deduct.entity.ext.ZhjinImportDeductResult;
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
 * 分天划扣
 * @Class Name TheDayDeductController
 * @author 韩龙
 * @Create In 2016年1月23日
 */
@Controller
@RequestMapping(value = "${adminPath}/theDayDeduct")
public class TheDayDeductController extends DeductCommon{
	
	@Autowired
	private DeductManager deductManager;
	
	@Autowired
	private TheDayDeductManager theDayDeductManager;
	
	@Autowired
	private DeductBespokeManager deductBespokeManager;
	
	@Autowired
	private TheDayDeductFacade theDayDeductFacade;
	
	@Autowired
	private DeductBespokeFacade deductBespokeFacade;
	
	@Autowired
	private SendTripleInfoFacade sendTripleInfoFacade;
	
	@Autowired
	private CommonFacade commonFacade;
	
	@Autowired
	private LoanApplyManager loanApplyManager;
	
	@Autowired
	private CheckManager checkManager;
	
	/**
	 * 
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
		List<String> deductStatusList = deductPoolEx.getDeductStatusList();
		if (!ArrayHelper.isNotEmpty(deductStatusList)) {
			deductStatusList = new ArrayList<String>();
		}
		// 待分天划扣状态
		deductStatusList.add(DeductState.DFTHK.value);
		// 预约划扣状态
		deductStatusList.add(DeductState.YYHK.value);
		// 线上划扣状态
		deductStatusList.add(DeductState.XSHK.value);
		// 划扣集合
		deductPoolEx.setDeductStatusList(deductStatusList);
		
		DeductUtils.assembleDeductPoolEx(deductPoolEx);
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
		return "deduct/theDaydeductList";
	}
	
	/**
	 * checkbox计算金额
	 * 2016年2月4日
	 * By 周俊
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/checkboxReckonMoney",method=RequestMethod.POST)
	@ResponseBody
	public DeductPoolEx checkboxReckonMoney(String id){
		DeductPoolEx deductPoolEx = theDayDeductManager.checkboxReckonMoney(id);
		return deductPoolEx;
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
		List<String> message = theDayDeductFacade.sendDeductThread(deductPoolExt,ids,DeductState.XSHK.value);
		if(message != null && message.size()>0){
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
//			ImportExcel ei = new ImportExcel(file, 1, 0);
//			int groups=0;
//			if(DeductPlat.FYPT.value.equals(exportPtId)){
//				groups = StringUtils.toInteger(DeductPlat.FYPT.value);
//			}else if(DeductPlat.ZJPT.value.equals(exportPtId)){
//				groups = StringUtils.toInteger(DeductPlat.ZJPT.value);
//			}else if(DeductPlat.TL.value.equals(exportPtId)){
//				groups = StringUtils.toInteger(DeductPlat.TL.value);
//			}else {
//				groups = StringUtils.toInteger(DeductPlat.HYLPT.value);
//			}
//			List<ImportDeductResult> list = ei.getDataList(ImportDeductResult.class,groups);
//			theDayDeductManager.saveExcel(list, filter);
			
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
			theDayDeductManager.saveExcel(map);
		} catch (Exception e) {
			logger.error("导入用户失败！失败信息："+e.getMessage(), e);
			FortuneExceptionUtil.insertException(FortuneExceptionUtil.newException(e, null,
					FortuneLogNode.DEDUCT_THEDAY.getCode(), FortuneLogLevel.YELLOW.value, 
					"导入用户失败！失败信息："+e.getMessage()));
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
		filter.addFilter("SUCCESS", Constant.SUCCESS);
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
				checkManager.addCheck(resultMap.get("lend_code"), "分天划扣批量修改状态", 
						DeductState.getDeductState(filter.getFilters().get("dictDeductStatus")+""),
						id,FortuneLogNode.DEDUCT_THEDAY);
			} catch (Exception e) {
				String info = "业务编号【"+id+"】已被其它业务人员处理过";
				logger.error(info+",失败信息："+e.getMessage(), e);
				message.append(info).append("</br>");
				FortuneExceptionUtil.insertException(FortuneExceptionUtil.newException(e, null,
						FortuneLogNode.DEDUCT_THEDAY.getCode(), FortuneLogLevel.YELLOW.value, 
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
	public String theDayDeduct(String id ,Model model/*,String applyDeductTypeName*/){
//		String[] array = id.split(",");
		//BigDecimal actualDeductMoneySum = theDayDeductManager.getActualDeductMoneySum(array);
	//	model.addAttribute("actualDeductMoneySum", actualDeductMoneySum);
		model.addAttribute("id", id);
		/*if(id.length()==0){
			model.addAttribute("deductCount", 0);
		}else{
			model.addAttribute("deductCount", array.length);
		}*/
		//DeductPlat.initDeductPlat();
		/*for (String key : DeductPlat.deductPlatMap.keySet()) {
			if(applyDeductTypeName.equals(DeductPlat.deductPlatMap.get(key))){
				model.addAttribute("dictApplyDeductType", key);
			}
		}*/
		model.addAttribute("nowDate", new Date());
		return "deduct/deductShowModalDialogTheDay";
		
	}
	
	/**
	 * 保存预约划扣信息
	 * 2016年2月16日
	 * By 韩龙
	 * @param id
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/saveDeductBespoke")
	@ResponseBody
	public String saveDeductBespoke(DeductBespoke deductBespoke){
		// 保存预约划扣
		List<String> messages = deductBespokeFacade.saveDeductBespokeTheDayFacade(deductBespoke,DeductState.YYHK.value);
		StringBuffer buf = new StringBuffer();
		if(messages.size() > 0){
			// 验证成功
			for (String message : messages) {
				if(message != null && !"".equals(message)){
					buf.append(message).append("</br>");
				}
			}
			return jsonMapper.toJson(buf.toString());
		}else{
			// 验证失败
			return jsonMapper.toJson(Constant.SUCCESS);
		}
	}
	
	/**
	 * 跳转到取消预约划扣列表弹出框
	 * 2016年2月18日
	 * By 韩龙
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
		page = theDayDeductManager.findPage(page, deductPoolEx);
		page.setFuncName("ajaxPage");
		model.addAttribute("pageUrl", "/theDayDeduct/goCancelDeductBespokePage");
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
		page = theDayDeductManager.findPage(page, deductPoolEx);
		page.setFuncName("ajaxPage");
		model.addAttribute("pageUrl", "/theDayDeduct/goCancelDeductBespokePage");
		model.addAttribute("page", page);
		// 获取字典项
		DeductUtils.getDictList(model);
		return "deduct/cancelDeductBespokeList";
	}
	
	/**
	 * 取消预约划扣
	 * 2016年2月18日
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
				deductBespokeManager.cancelDeductBespokeTheDay(tdp,DeductState.YYHK.value,message);
			} catch (Exception e) {
				String info = "分天划扣业务编号【"+id+"】已被其它业务人员处理";
				logger.error(info+",失败信息："+e.getMessage(),e);
				message.append(info).append("</br>");
				logger.error("保存分天划扣预约失败："+e.getMessage(), e);
				FortuneExceptionUtil.insertException(FortuneExceptionUtil.newException(e, thedayid,
						FortuneLogNode.DEDUCT_THEDAY.getCode(), FortuneLogLevel.RED.value, 
						info));
			}
		}
		
		return jsonMapper.toJson(message.length() > 0 ? message.toString():Constant.SUCCESS);
	}
}
