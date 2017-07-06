package com.creditharmony.fortune.deduct.goldfail.web;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.creditharmony.common.util.DateUtils;
import com.creditharmony.core.excel.util.ImportExcel;
import com.creditharmony.core.fortune.type.BillState;
import com.creditharmony.core.fortune.type.DeductState;
import com.creditharmony.core.fortune.type.FortuneLogLevel;
import com.creditharmony.core.fortune.type.FortuneLogNode;
import com.creditharmony.core.fortune.type.LendState;
import com.creditharmony.core.fortune.type.PayMent;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.dm.filenet.CEContextHelper;
import com.creditharmony.dm.filenet.CERequestContext;
import com.creditharmony.fortune.common.FortuneExceptionUtil;
import com.creditharmony.fortune.common.service.CheckManager;
import com.creditharmony.fortune.customer.entity.LoanApply;
import com.creditharmony.fortune.customer.service.LoanApplyManager;
import com.creditharmony.fortune.deduct.common.Constant;
import com.creditharmony.fortune.deduct.common.ConstantField;
import com.creditharmony.fortune.deduct.common.DeductUtils;
import com.creditharmony.fortune.deduct.common.ExportDeductHelper;
import com.creditharmony.fortune.deduct.common.QueryFilter;
import com.creditharmony.fortune.deduct.entity.DeductPool;
import com.creditharmony.fortune.deduct.entity.ext.DeductPoolEx;
import com.creditharmony.fortune.deduct.facade.CommonFacade;
import com.creditharmony.fortune.deduct.facade.DeductManagerFacade;
import com.creditharmony.fortune.deduct.gold.facade.DeductGoldFacade;
import com.creditharmony.fortune.deduct.gold.service.DeductGoldManager;
import com.creditharmony.fortune.deduct.service.DeductManager;
import com.creditharmony.fortune.deduct.web.DeductCommon;
import com.creditharmony.fortune.template.entity.GoldAccountResultModel;
import com.creditharmony.fortune.triple.system.entity.ext.IntDeliveryEx;
import com.creditharmony.fortune.triple.system.facade.SendTripleInfoFacade;
import com.google.common.collect.Lists;

/**
 * 金帐户划扣失败控制层
 * @Class Name GoldAccountDeductFaliController
 * @author 韩龙
 * @Create In 2016年2月22日
 */
@Controller
@RequestMapping(value = "${adminPath}/goldAccounFali")
public class GoldAccountDeductFaliController extends DeductCommon {
	
	@Autowired
	private DeductGoldManager deductGoldManager;
	
	@Autowired
	private CheckManager checkManager;
	
	@Autowired
	private DeductManager deductManager;
	
	@Autowired
	private DeductGoldFacade deductGoldFacade;
	
	@Autowired
	private DeductManagerFacade deductManagerFacade;
	
	@Autowired
	private SendTripleInfoFacade sendTripleInfoFacade;
	
	@Autowired
	private CommonFacade commonFacade;
	
	@Autowired
	private LoanApplyManager loanApplyManager;
	
	/**
	 * 金帐户划扣失败-->金帐户列表
	 * 2016年2月22日
	 * By 韩龙
	 * @return
	 */
	@RequestMapping(value = "goldAccounFailList")
	public String goldAccounList(DeductPoolEx deductPoolEx, HttpServletRequest request,
			HttpServletResponse response,Model model){
		List<String> dictDeductStatusList = Lists.newArrayList();
		// 设置资金托管状态
		deductPoolEx.setApplyPay(PayMent.ZJTG.value);
		// 待划扣结算
		dictDeductStatusList.add(DeductState.HKSB.value);
		// 释放状态
		dictDeductStatusList.add(DeductState.CX.value);
		// 设置查询划扣状态
		deductPoolEx.setDeductStatusList(dictDeductStatusList);
		DeductUtils.assembleDeductPoolEx(deductPoolEx);
		// 资金托管标识
		deductPoolEx.setGoldAccountFlag(PayMent.ZJTG.value);
		// 分页查询
		Page<DeductPoolEx> page = new Page<DeductPoolEx>(request, response);
		page = deductManager.findPage(page, deductPoolEx);
		// 设置页面数据及查询条件回填
		model.addAttribute("page", page).addAttribute("deductPoolExt",deductPoolEx);
		// 当前条件下的出借金额与划扣金额
		Map<String,String> sumMoney = deductManager.getDeductSumMoney(deductPoolEx);
		model.addAttribute("sumMoney",sumMoney);
		// 获取字典项
		DeductUtils.getDictList(model);
		return "deduct/goldAccounFailList";
	}
	
	/**
	 * 金帐户划扣失败-->导出金帐户
	 * 2016年2月22日
	 * By 韩龙
	 * @param request
	 * @param response
	 * @param deductPoolExt
	 * @param applyCodes
	 */
	@RequestMapping(value = "outExcel")
	public void outExcel(HttpServletRequest request,HttpServletResponse response,
			DeductPoolEx deductPoolExt,String applyCodes) {
//		List<GoldAccounExportModel> list = Lists.newArrayList();
		// 选中条数导出记录
		if (applyCodes != null && !"".equals(applyCodes)) {
			String[] applys = DeductUtils.getCodes(applyCodes);
			List<String> codes = null;
			if (applys.length > 0) {
				codes = Arrays.asList(applys);
			}
			DeductPoolEx ext = new DeductPoolEx();
			ext.setMatchingFirstdayFlag(BillState.SQ.value);
			ext.setApplyCodes(codes);
			// 新版excel导出方法
			ExportDeductHelper edh = new ExportDeductHelper();
			edh.exportData(ext, response, ConstantField.deductGoldOffLine_title, 
					ConstantField.deductGoldOffLine_field, ConstantField.deductGoldOffLine_sql,
					"PW03_"+ DateUtils.getDate("yyyyMMdd") +"_0001");
			// 按选中出借编号导出
//			list = deductManager.getGoldAccounExportModel(ext);
		} else {
			// 按条件导出excel
			List<String> dictDeductStatusList = Lists.newArrayList();
			List<String> dictApplyPayList = Lists.newArrayList();
			// 设置资金托管状态
			dictApplyPayList.add(PayMent.ZJTG.value);
			deductPoolExt.setApplyPayList(dictApplyPayList);
			// 待划扣结算
			dictDeductStatusList.add(DeductState.HKSB.value);
			// 设置查询划扣状态
			deductPoolExt.setDeductStatusList(dictDeductStatusList);
			deductPoolExt.setMatchingFirstdayFlag(BillState.SQ.value);
			ExportDeductHelper edh = new ExportDeductHelper();
			edh.exportData(deductPoolExt, response, ConstantField.deductGoldOffLine_title, 
					ConstantField.deductGoldOffLine_field, ConstantField.deductGoldOffLine_sql,
					"PW03_"+ DateUtils.getDate("yyyyMMdd") +"_0001");
//			list = deductManager.getGoldAccounExportModel(deductPoolExt);
		}
		// 导出excel
//		outExcelCommon(list, GoldAccounExportModel.class,
//				FileType.JZHHBLB.getName(), response);

	}
	
	/**
	 * 金帐户划扣失败-->导入金帐户弹窗
	 * 2016年2月29日
	 * By 韩龙
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "importExcelPop")
	public String importExcelPop(Model model){
		return "deduct/goldUploadFilePop";
	}
	
	/**
	 * 金帐户划扣失败-->导入金帐户
	 * 2016年2月22日
	 * By 韩龙
	 * @param request
	 * @param response
	 * @param deductPoolExt
	 * @param applyCodes
	 */
	@RequestMapping(value = "importExcel")
	@ResponseBody
	public String importExcel(@RequestParam("file") MultipartFile file) {
		int result = 0;
		List<GoldAccountResultModel> goldAccountResultModelList = Lists.newArrayList();
		try {
			ImportExcel ei = new ImportExcel(file, 1, 1);
			goldAccountResultModelList = ei.getDataList(GoldAccountResultModel.class);
			result = deductGoldManager.updateGoldAccounExportModel(goldAccountResultModelList);
		} catch (Exception e) {
			logger.debug("导入失败信息:"+e.getMessage());
			FortuneExceptionUtil.insertException(FortuneExceptionUtil.newException(e, null, FortuneLogNode.GOLD_ACCOUNT_DEDUCT_FALI.getCode(),
					FortuneLogLevel.YELLOW.value, "导入信息失败,文件格式不正确"));
			return jsonMapper.toJson("导入信息失败,文件格式不正确");
		}
		return jsonMapper.toJson(result > 0 ? "导入成功":"导入失败");
	}
	
	/**
	 * 金帐户划扣失败-->划拨
	 * 2016年2月23日
	 * By 韩龙
	 * @return
	 */
	@RequestMapping(value = "transfer")
	@ResponseBody
	public String transfer(String applyCodes,DeductPoolEx deductPoolExt,HttpServletRequest request){
		if(applyCodes != null && !"".equals(applyCodes)){
			String [] codes = applyCodes.split(",");
			deductPoolExt.setApplyCodes(Arrays.asList(codes));
		}
		CERequestContext ce = (CERequestContext) CEContextHelper.getCEContext(request);
		String message = deductGoldFacade.transferFacade(deductPoolExt,ce);
		return jsonMapper.toJson(message.length() > 0 ? message : Constant.SUCCESS);
	}
	
	/**
	 * 金帐户划扣失败-->办理
	 * 2016年2月23日
	 * By 韩龙
	 * @param model
	 * @param applyCode
	 * @return
	 */
	@RequestMapping(value = "conduct")
	public String conduct(Model model,String applyCode,RedirectAttributes redirectAttributes){
		DeductPool dp = new DeductPool();
		String[] strArray = applyCode.split("_");
		String lendCode = strArray[0];
		String verTime = strArray[1];
		dp.setApplyCode(lendCode);
		dp.setVerTime(verTime);
		dp = deductGoldManager.get(dp);
		if(dp == null){
			addMessage(redirectAttributes, "业务编号【"+lendCode+"】已被其它业务人员处理");
			return "redirect:" + super.adminPath + "/goldAccoun/goldAccounList";
		}
		model.addAttribute("deductPool", dp);
		return "deduct/goldAccounDetailed";
	}
	
	/**
	 * 金帐户划扣失败-->办理-->提交
	 * 2016年2月23日
	 * By 韩龙
	 * @return
	 */
	@RequestMapping(value = "conductSubmit")
	public String conductSubmit(HttpServletRequest request,
			HttpServletResponse response, RedirectAttributes redirectAttributes){
		// 初始化对象
		QueryFilter filter = new QueryFilter(request);
/*		int result = deductGoldManager.conductSubmit(filter.getFilters());
		// 判断是否修改成功
		return "redirect:" + super.adminPath + "/goldAccounFali/goldAccounFailList";*/
		
		String lendCode = DeductUtils.isNull((String) filter.getFilters().get("applyCode"));
		int result = deductGoldManager.conductSubmit(filter.getFilters());
		if(result == 999){
			LoanApply loanApply = new LoanApply();
			loanApply.setApplyCode(lendCode);
			loanApply = loanApplyManager.get(loanApply);
			/*// 发送短信   资金托管不发短信
			commonFacade.smsInfo(lendCode, DeductState.HKCG.value,loanApply);*/
			// 制作文件
			commonFacade.makeFileInfo(lendCode);
			// 三网首单校
			sendTripleInfoFacade.tripleInfo(loanApply.getCustCode(),loanApply.getApplyCode());
		}
		if(result>0){
			addMessage(redirectAttributes, "金帐户划扣结算修改状态成功");
		}else{
			String message = "金帐户划扣结算修改状态失败";
			if(result==-999){
				message = "业务编号【"+lendCode+"】已被其它业务人员处理过";
			}
			addMessage(redirectAttributes, message);
		}
		return "redirect:" + super.adminPath + "/goldAccounFali/goldAccounFailList";
	}
	
	/**
	 * 划扣失败-->债权释放
	 * 2016年2月17日
	 * By 韩龙
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/batchReleaseCreditor")
	@ResponseBody
	public String batchReleaseCreditor(HttpServletResponse response,HttpServletRequest request){
		QueryFilter filter = new QueryFilter(request);
		filter.addFilter(LendState.HKSB.value, LendState.HKSB.value);
		String message = deductManagerFacade.batchUpdate(filter.getFilters());
		return jsonMapper.toJson(message.length() > 0 ? message : Constant.SUCCESS);
	}
}
