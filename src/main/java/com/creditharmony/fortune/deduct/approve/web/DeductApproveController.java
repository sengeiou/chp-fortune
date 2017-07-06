package com.creditharmony.fortune.deduct.approve.web;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.creditharmony.core.fortune.type.BillState;
import com.creditharmony.core.fortune.type.DeductState;
import com.creditharmony.core.fortune.type.FileType;
import com.creditharmony.core.fortune.type.FortuneLogLevel;
import com.creditharmony.core.fortune.type.FortuneLogNode;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.fortune.common.FortuneExceptionUtil;
import com.creditharmony.fortune.deduct.approve.facade.DeductApproveFacade;
import com.creditharmony.fortune.deduct.approve.service.DeductApproveManager;
import com.creditharmony.fortune.deduct.common.Constant;
import com.creditharmony.fortune.deduct.common.ConstantField;
import com.creditharmony.fortune.deduct.common.DeductUtils;
import com.creditharmony.fortune.deduct.common.ExportDeductHelper;
import com.creditharmony.fortune.deduct.common.QueryFilter;
import com.creditharmony.fortune.deduct.entity.ext.DeductPoolEx;
import com.creditharmony.fortune.deduct.service.DeductManager;
import com.creditharmony.fortune.deduct.web.DeductCommon;
import com.google.common.collect.Lists;

/**
 * 划扣审批控制层
 * @Class Name DeductApproveController
 * @author 韩龙
 * @Create In 2015年12月1日
 */
@Controller
@RequestMapping(value = "${adminPath}/deductApprove")
public class DeductApproveController extends DeductCommon{
	
	@Autowired
	private DeductApproveManager deductApproveManager;
	
	@Autowired
	private DeductManager deductManager;
	
	@Autowired
	private DeductApproveFacade deductApproveFacade;
	
	/**
	 * 划扣审批-->划扣审批列表
	 * 2015年11月30日
	 * By 韩龙
	 * @param deductPoolEx
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/approveList")
	public String approveList(DeductPoolEx deductPoolEx, HttpServletRequest request,
			HttpServletResponse response, Model model){
		// 分页查询
		Page<DeductPoolEx> page = new Page<DeductPoolEx>(request, response);
		List<String> dictDeductStatusList = Lists.newArrayList();
		// 设置审批状态查询条件
		dictDeductStatusList.add(DeductState.DHKSP.value);
		// 设置查询划扣状态
		deductPoolEx.setDeductStatusList(dictDeductStatusList);
		DeductUtils.assembleDeductPoolEx(deductPoolEx);
		page = deductManager.findPage(page, deductPoolEx);
		deductPoolEx.setApplyOrApproveFalg(Constant.APPLY_OR_APPROVE_FALG);
		// 获取字典项
		DeductUtils.getDictList(model);
        // 设置页面数据及查询条件回填
		model.addAttribute("page", page).addAttribute("deductPoolExt", deductPoolEx);
		// 当前条件下的出借金额与划扣金额
		Map<String,String> sumMoney = deductManager.getDeductSumMoney(deductPoolEx);
		model.addAttribute("sumMoney",sumMoney);
		return "deduct/deductApplyList";
	}
	
	/**
	 * 划扣审批-->批量审批
	 * 2015年11月30日
	 * By 韩龙
	 * @param ids
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/batchDeductApprove")
	@ResponseBody
	public String batchDeductApprove(DeductPoolEx deductPoolEx,String ids,HttpServletRequest request,
			HttpServletResponse response){
		StringBuffer message = new StringBuffer();
		// 判断是否选中批量操作
		deductApproveFacade.batch(deductPoolEx, ids, message);
		// 判断是否成功
		return jsonMapper.toJson(message.length() > 0 ? message.toString():Constant.SUCCESS);
	}
	
	/**
	 * 划扣审批-->导出excel
	 * 2015年12月24日
	 * By 韩龙
	 * @param request
	 * @param response
	 * @param deductPoolExt
	 * @param applyCodes
	 */
	@RequestMapping(value = "/expExcel")
	public void expExcel(HttpServletRequest request,HttpServletResponse response,
			DeductPoolEx deductPoolExt,String applyCodes) {
//		List<DeductPoolExportModel> list = null;
		List<String> status = Lists.newArrayList();
		ExportDeductHelper edh = new ExportDeductHelper();
		// 选中条数导出记录
		if (applyCodes != null && !"".equals(applyCodes)) {	
//			String[] applys = applyCodes.split(",");
			String[] applys = DeductUtils.getCodes(applyCodes);;
			List<String> codes = null;
			if (applys.length > 0) {
				codes = Arrays.asList(applys);
			}
			DeductPoolEx ext = new DeductPoolEx();
			ext.setApplyCodes(codes);
			ext.setMatchingFirstdayFlag(BillState.SQ.value);
			// 按选中出借编号导出
//			list = deductManager.findListExportModel(ext);
			edh.exportData(ext, response, ConstantField.approve_title,
					ConstantField.approve_field, ConstantField.approve_sql,
					FileType.HQSPLB.getName());
		} else {
			DeductUtils.assembleDeductPoolEx(deductPoolExt);
			// 按条件导出excel
			status.add(DeductState.DHKSP.value);
			deductPoolExt.setDeductStatusList(status);
			deductPoolExt.setMatchingFirstdayFlag(BillState.SQ.value);
//			list = deductManager.findListExportModel(deductPoolExt);
			edh.exportData(deductPoolExt, response, ConstantField.approve_title,
					ConstantField.approve_field, ConstantField.approve_sql,
					FileType.HQSPLB.getName());
		}
		// 导出excel
//		outExcelCommon(list, DeductPoolExportModel.class,
//				FileType.HQSPLB.getName(), response);
	}
	
	/**
	 * 划扣审批-->办理
	 * 2015年11月30日
	 * By 韩龙
	 * @param model
	 * @param applyCode
	 * @return
	 */
	@RequestMapping(value = "/conductApprove")
	public String conductApprove(Model model, String applyCode,
			HttpServletRequest request) {
		// TODO 初始化下拉列表信息
		QueryFilter filter = new QueryFilter(request);
		// 获取deductPoolExt对象
		DeductPoolEx deductPoolExt = deductManager.conduct(filter.getFilters());
		deductPoolExt.setApplyOrApproveFalg(Constant.APPLY_OR_APPROVE_FALG);
		model.addAttribute("deductPoolExt", deductPoolExt);
		DeductUtils.getDictList(model);
		return "deduct/deductDetailed";
	}
	
	/**
	 * 划扣审批-->办理-->划扣审批提交
	 * 2015年12月24日
	 * By 韩龙
	 * @param model
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "/approve")
	public String approve(Model model,HttpServletRequest request,
			HttpServletResponse response, RedirectAttributes redirectAttributes) {
		// 初始化对象
		QueryFilter filter = new QueryFilter(request);
		// 获取deductPoolExt对象
		DeductPoolEx deductPoolExt = new DeductPoolEx();
		try {
			int result = deductApproveManager.approve(filter.getFilters());
			if (result > 0) {
				addMessage(redirectAttributes, "划扣审批成功");
			} else {
				addMessage(redirectAttributes, "划扣审批失败，该数据是否有人已操作，请详查！");
			}
		} catch (Exception e) {
			logger.error("划扣审批失败，该数据是否有人已操作，请详查！",e.getMessage(),e);
			addMessage(redirectAttributes, "划扣审批失败，该数据是否有人已操作，请详查！");
			FortuneExceptionUtil.insertException(FortuneExceptionUtil.newException(e, null, 
					FortuneLogNode.DEDUCT_APPROVE.getCode(),FortuneLogLevel.YELLOW.value, 
					"出借编号【】批量操作失败"));
		}
		deductPoolExt.setApplyOrApproveFalg(Constant.APPLY_OR_APPROVE_FALG);
		model.addAttribute("deductPoolExt", deductPoolExt);
		return "redirect:" + adminPath + "/deductApprove/approveList";
	}
}
