package com.creditharmony.fortune.redemption.apply.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.creditharmony.bpm.frame.view.WorkItemView;
import com.creditharmony.core.fortune.type.ProductCode;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.web.BaseController;
import com.creditharmony.fortune.redemption.apply.service.RedeemApplyManager;
import com.creditharmony.fortune.redemption.common.entity.ext.Redemptionex;
import com.creditharmony.fortune.redemption.common.view.RedemptionApplyEntity;
import com.creditharmony.fortune.utils.FortuneDictUtil;

/**
 * 赎回申请Controller
 * @Class Name ApplyController
 * @author 陈广鹏
 * @Create In 2016年4月14日
 */
@Controller
@RequestMapping("${adminPath}/myApply/redemption/")
public class RedeemApplyController extends BaseController {
	
	@Autowired
	private RedeemApplyManager applyManager;
	
	/**
	 * 获取可提前赎回申请列表 
	 * 2015年12月7日
	 * By 陈广鹏
	 * @param redemptionex
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("applyList")
	public String applyList(Redemptionex redemptionex, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		List<String> list = new ArrayList<String>();
		list.add(ProductCode.YMY.value);
		list.add(ProductCode.YYY.value);
		redemptionex.setExProductList(list);
		
//		redemptionex.setUserId(UserUtils.getUser().getId());

		Page<Redemptionex> page = new Page<Redemptionex>(request, response);
		Page<Redemptionex> pageview = applyManager.findItemList(page, redemptionex);


		model.addAttribute("page", pageview);
		model.addAttribute("rdm", redemptionex);
		String[] types = {"tz_bill_day","tz_pay_type"};
		FortuneDictUtil.addDicts(model,types);

		return "redemption/apply/applyList";
	}

	/**
	 * 发起赎回申请流程
	 * 2015年12月7日
	 * By 陈广鹏
	 * @param redemptionApplyEntity
	 * @param workItemView
	 * @param model
	 * @return
	 */
	@RequestMapping("launchFlow")
	public String launchFlow(RedemptionApplyEntity redemptionApplyEntity, WorkItemView workItemView,
			Model model) {

		model.addAttribute("flowCode", workItemView.getFlowCode());
		model.addAttribute("flowName", workItemView.getFlowName());
		model.addAttribute("stepName", workItemView.getStepName());

		applyManager.launchFlow(redemptionApplyEntity,workItemView);

		return "redirect:" + this.adminPath + "/myApply/redemption/applyList";
	}
	
	/**
	 * 下载提前赎回申请表
	 * 2016年3月9日
	 * By 陈广鹏
	 * @param entity
	 * @param response
	 * @return
	 */
	@RequestMapping({ "downloadApplyForm" })
	public String downloadApplyForm(RedemptionApplyEntity entity, HttpServletResponse response) {

		applyManager.downloadTemplate(response,entity);
		
		return null;
	}
	
	/**
	 * 提交申请时验证剩余金额
	 * 2016年3月10日
	 * By 陈广鹏
	 * @param entity
	 * @return
	 */
	@RequestMapping({ "applyCheck" })
	@ResponseBody
	public String applyCheck(RedemptionApplyEntity entity) {
		
		String str = applyManager.applyCheck(entity);
		
		return jsonMapper.toJson(str);
	}

}
