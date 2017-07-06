package com.creditharmony.fortune.deduct.success.web;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.creditharmony.core.common.type.EffectiveFlag;
import com.creditharmony.core.fortune.type.BillState;
import com.creditharmony.core.fortune.type.DeductState;
import com.creditharmony.core.fortune.type.FileType;
import com.creditharmony.core.fortune.type.FortuneLogLevel;
import com.creditharmony.core.fortune.type.FortuneLogNode;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.dm.filenet.CEContextHelper;
import com.creditharmony.dm.filenet.CERequestContext;
import com.creditharmony.fortune.common.FortuneExceptionUtil;
import com.creditharmony.fortune.common.entity.Attachment;
import com.creditharmony.fortune.common.entity.Check;
import com.creditharmony.fortune.common.service.CheckManager;
import com.creditharmony.fortune.constants.FileConstants;
import com.creditharmony.fortune.deduct.common.Constant;
import com.creditharmony.fortune.deduct.common.ConstantField;
import com.creditharmony.fortune.deduct.common.DeductUtils;
import com.creditharmony.fortune.deduct.common.ExportDeductHelper;
import com.creditharmony.fortune.deduct.common.QueryFilter;
import com.creditharmony.fortune.deduct.entity.ext.DeductPoolEx;
import com.creditharmony.fortune.deduct.service.DeductManager;
import com.creditharmony.fortune.deduct.success.service.DeductSuccessManager;
import com.creditharmony.fortune.deduct.web.DeductCommon;
import com.creditharmony.fortune.utils.FileUtil;
import com.google.common.collect.Lists;

/**
 * 已划扣控制层
 * @Class Name DeductSuccessController
 * @author 韩龙
 * @Create In 2015年12月1日
 */
@Controller
@RequestMapping(value = "${adminPath}/deductSuccess")
public class DeductSuccessController extends DeductCommon{
	
	@Autowired
	private DeductSuccessManager deductSuccessManager;
	
	@Autowired
	private CheckManager checkManager;
	
	@Autowired
	private DeductManager deductManager;
	
	/**
	 * 已划扣-->划扣列表
	 * 2015年11月30日
	 * By 韩龙
	 * @param deductPoolEx
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/deductSuccessList")
	public String deductSuccessList(DeductPoolEx deductPoolEx, HttpServletRequest request,
			HttpServletResponse response,Model model) {
		// 分页查询 FAIL
		Page<DeductPoolEx> page = new Page<DeductPoolEx>(request, response);
		initCondition(deductPoolEx);
		// 除去旧数据的划扣成功数据
		deductPoolEx.setDataFlag("0");
		page = deductManager.findPage(page, deductPoolEx);
		// 设置页面数据及查询条件回填
		model.addAttribute("page", page).addAttribute("deductPoolExt",
				deductPoolEx);
		// 当前条件下的出借金额与划扣金额
		Map<String,String> sumMoney = deductManager.getDeductSumMoney(deductPoolEx);
		model.addAttribute("sumMoney",sumMoney);
		// 获取字典项
		DeductUtils.getDictList(model);
		return "deduct/deductDrawSuccessList";
	}

	/**
	 * 条件初始化
	 * 2016年4月20日
	 * By 韩龙
	 * @param deductPoolEx
	 */
	private void initCondition(DeductPoolEx deductPoolEx) {
		List<String> dictDeductStatusList = Lists.newArrayList();
		// 设置审批状态查询条件
		dictDeductStatusList.add(DeductState.HKCG.value);
		deductPoolEx.setDataFlag("0");
		deductPoolEx.setMatchingFirstdayFlag(BillState.SQ.value);
		// 设置查询划扣状态
		deductPoolEx.setDeductStatusList(dictDeductStatusList);
		DeductUtils.assembleDeductPoolEx(deductPoolEx);
	}
	
	/**
	 * 已划扣-->批量下载收款确认书pdf或word类型
	 * 2015年12月9日
	 * By 韩龙
	 * @param request
	 * @param response
	 * @param model
	 */
	@RequestMapping(value = "batchFileDownload")
	public void batchFileDownload(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		// 获取页面参数
		QueryFilter filter = new QueryFilter(request);
		String ids = (String) filter.getFilters().get("ids");
		// 获取批量下载的类型
		String type = (String) filter.getFilters().get("type");
		if (ids != null) {
			String []loanCodes = ids.split(",");
			filter.addFilter("loanCode", loanCodes);
			filter.addFilter("attaFlag", FileConstants.FILE_TYPE_SKQR);
			filter.addFilter("attaFileType", type);
			filter.addFilter("isDiscard", EffectiveFlag.yx.value);
			// 获取附件文件列表
			List<Attachment> listAttachment = deductSuccessManager.getAttachment(filter
					.getFilters());
			// 压缩文件并下载
			FileUtil.zipFileDownload(FileType.SKQRS_HK.getName(), response, listAttachment);
		}

	}
	
	/**
	 * 已划扣-->导出excel 
	 * 2015年12月10日
	 * By 韩龙
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/exportExcel")
	public void exportExcel(DeductPoolEx deductPoolEx,HttpServletRequest request,HttpServletResponse response) {
		// 参数过滤器
		QueryFilter filter = new QueryFilter(request);
		// 参数是否为空
		if (filter.getFilters().get("ids") != null) {
			String ids = filter.getFilters().get("ids").toString();
			// 设置参数集合
//			filter.addFilter("lendCodes", ids.split(","));
			deductPoolEx.setApplyCodes(Arrays.asList(ids.split(",")));
			deductPoolEx.setMatchingFirstdayFlag(BillState.SQ.value);
		}else{
			initCondition(deductPoolEx);
		}
		ExportDeductHelper edh = new ExportDeductHelper();
		edh.exportData(deductPoolEx, response, ConstantField.deductSuccess_title,
				ConstantField.deductSuccess_field, ConstantField.deductSuccess_sql,
				FileType.YHKLB.getName());
//		// 循环copy转换
//		List<DeductSuccessExportModel> dateList = deductManager.getDeductSuccessExportModel(filter.getFilters());
//		// 导出excel
//		outExcelCommon(dateList, DeductSuccessExportModel.class,
//				FileType.YHKLB.getName(), response);
	}
	
	/**
	 * 已划扣-->导出金帐户 
	 * 2015年12月10日
	 * By 韩龙
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/exporExcelGold")
	public void exporExcelGold(DeductPoolEx deductPoolEx,HttpServletRequest request,HttpServletResponse response) {
		// 参数过滤器
		QueryFilter filter = new QueryFilter(request);
		// 参数是否为空
		if (filter.getFilters().get("ids") != null) {
			String ids = filter.getFilters().get("ids").toString();
			// 设置参数集合
//			filter.addFilter("lendCodes", ids.split(","));
			deductPoolEx.setApplyCodes(Arrays.asList(ids.split(",")));
			deductPoolEx.setMatchingFirstdayFlag(BillState.SQ.value);
		}else{
			initCondition(deductPoolEx);
		}
		ExportDeductHelper edh = new ExportDeductHelper();
		edh.exportData(deductPoolEx, response, ConstantField.deductGold_title,
				ConstantField.deductGold_field, ConstantField.deductGold_sql,
				FileType.DCJZHLB.getName());
//		// 循环copy转换
//		List<DeductSuccessExportGoldModel> dateList = deductManager.getDeductSuccessExportGoldModel(filter.getFilters());
//		// 导出excel
//		outExcelCommon(dateList, DeductSuccessExportGoldModel.class,
//				FileType.DCJZHLB.getName(), response);
	}
	
	/**
	 * 已划扣-->导出回访表excel 
	 * 2015年12月10日
	 * By 韩龙
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/exportTableExcel")
	public void exportTableExcel(DeductPoolEx deductPoolEx,HttpServletRequest request,
			HttpServletResponse response){
		// 参数过滤器
		QueryFilter filter = new QueryFilter(request);
		// 参数是否为空
		if (filter.getFilters().get("ids") != null) {
			String ids = filter.getFilters().get("ids").toString();
			// 设置参数集合
//			filter.addFilter("lendCodes", ids.split(","));
			deductPoolEx.setApplyCodes(Arrays.asList(ids.split(",")));
			deductPoolEx.setMatchingFirstdayFlag(BillState.SQ.value);
		}else{
			initCondition(deductPoolEx);
		}
		ExportDeductHelper edh = new ExportDeductHelper();
		edh.exportData(deductPoolEx, response, ConstantField.deductReturnVisit_title,
				ConstantField.deductReturnVisit_field, ConstantField.deductReturnVisit_sql,
				FileType.YCJZQHFLB.getName());
//		List<DeductSuccessPayExportModel> dateList = deductManager.getDeductSuccessPayExportModel(filter.getFilters());
//		outExcelCommon(dateList, DeductSuccessPayExportModel.class, 
//				FileType.YCJZQHFLB.getName(), response);
	}
	
	/**
	 * 已划扣-->文件合成
	 * 2015年12月10日
	 * By 韩龙
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/filSynthesis")
	@ResponseBody
	public String FilSynthesis(HttpServletRequest request,
			HttpServletResponse response){
		// 获取参数 
		CERequestContext ce = (CERequestContext) CEContextHelper.getCEContext(request);
		QueryFilter filter = new QueryFilter(request);
		filter.addFilter(CERequestContext.DM_FILENET_CONTEXT, ce);
		int result = deductSuccessManager.compositeFile(filter.getFilters());
		return jsonMapper.toJson(result > 0 ? Constant.SUCCESS:Constant.FAIL);
	}
	
	/**
	 * 已划扣-->办理
	 * 2015年12月24日
	 * By 韩龙
	 * @param model
	 * @param applyCode
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/conductSuccess")
	public String conductSuccess(Model model, String applyCode,
			HttpServletRequest request) {
		// 获取deductPoolExt对象
		QueryFilter filter = new QueryFilter(request);
		DeductPoolEx deductPoolExt = deductSuccessManager.conduct(filter.getFilters());
		model.addAttribute("deductPoolExt", deductPoolExt);
		DeductUtils.getDictList(model);
		return "deduct/deductSuccessDetailed";
	}
	
	/**
	 * 已划扣-->办理-->全程流痕
	 * 2015年12月28日
	 * By 韩龙
	 * @param model
	 * @param check
	 * @return
	 */
	@RequestMapping(value = "/fullFlowMark")
	public String fullFlowMark(Model model, Check check,HttpServletRequest request,
			HttpServletResponse response) {
		Page<Check> page = new Page<Check>(request, response);
//		page = checkManager.findPage(page, check);
		page = checkManager.findCheckList(page, check);
		page.setFuncName("pageAjax");
		// 设置页面数据及查询条件回填
		model.addAttribute("page", page).addAttribute("check", check);
		return "deduct/fullFlowMark";
	}
	
	/**
	 * 已划扣-->办理-->下载收款确认书Word版
	 * 2015年12月10日
	 * By 韩龙
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/fileDownload")
	public void fileDownload(HttpServletRequest request,
			HttpServletResponse response) {
//		DmService dmService = DmService.getInstance();
//		dmService.queryDocuments(DmService.BUSI_TYPE_FORTUNE, arg1, arg2, arg3)
		// 获取页面参数
		QueryFilter filter = new QueryFilter(request);
		String[] ids = filter.getFilters().get("applyCode").toString().split(",");
		// 下载类型
		String type = filter.getFilters().get("type").toString();
		if (ids != null) {
			filter.addFilter("loanCode", ids);
			filter.addFilter("attaFlag", FileConstants.FILE_TYPE_SKQR);
			filter.addFilter("attaFileType", type);
			filter.addFilter("isDiscard", EffectiveFlag.yx.value);
			// 获取附件文件列表
			List<Attachment> list = deductSuccessManager.getAttachment(filter
					.getFilters());
			// 压缩文件并下载
			FileUtil.zipFileDownload(FileType.SKQRS_HK.getName(), response, list);
		}
	}
	
//	/**
//	 * 已划扣-->办理-->下载收款确认书Word版
//	 * 2015年12月10日
//	 * By 韩龙
//	 * @param request
//	 * @param response
//	 */
//	@RequestMapping(value = "/bacthSendFile")
//	public void bacthSendFile(HttpServletRequest request,
//			HttpServletResponse response) {
//		// 获取页面参数
//		QueryFilter filter = new QueryFilter(request);
//		String[] ids = filter.getFilters().get("applyCode").toString().split(",");
//	}
	
	/**
	 * 发送收款确认文件
	 * 2016年3月29日
	 * By 韩龙
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/sendEmailFile")
	@ResponseBody
	public String sendEmailFile(HttpServletRequest request,
			HttpServletResponse response){
		StringBuffer messages = new StringBuffer();
		QueryFilter filter = new QueryFilter(request);
		String lendCode =  filter.getFilters().get("lendCode") + "";
		String type =  filter.getFilters().get("type") + "";
		String [] lendCodes = lendCode.split(",");
		for (String code : lendCodes) {
			try{
				// 插入邮件待发送列表
				// String message = deductSuccessManager.sendEmailFile(code,type);
				// 实时发送邮件
				String message = deductSuccessManager.sendEmailFileConstantly(code,type);
				if(message != null){
					messages.append(message).append("<br>");
				}
			}catch(Exception e){
				messages.append("出借编号【"+code+"】发送收款确认书出错").append("<br>");
				FortuneExceptionUtil.insertException(FortuneExceptionUtil.newException(e, code,
						FortuneLogNode.DEDUCT_SUCCESS.getCode(), FortuneLogLevel.YELLOW.value, 
						"出借编号【"+code+"】发送收款确认书出错"));
			}
		}
		if(messages.length()>0){
			return jsonMapper.toJson(messages.toString());
		}
		return jsonMapper.toJson(Constant.SUCCESS);
	}
	
}
