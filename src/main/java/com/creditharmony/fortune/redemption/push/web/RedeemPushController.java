package com.creditharmony.fortune.redemption.push.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.web.BaseController;
import com.creditharmony.fortune.redemption.common.entity.ext.Redemptionex;
import com.creditharmony.fortune.redemption.common.view.RedemptionApplyEntity;
import com.creditharmony.fortune.redemption.push.service.RedeemPushManager;
import com.creditharmony.fortune.utils.FortuneDictUtil;

/**
 * 特殊提前赎回推送Controller
 * @Class Name PushController
 * @author 陈广鹏
 * @Create In 2016年4月14日
 */
@Controller
@RequestMapping("${adminPath}/myApply/redemption/")
public class RedeemPushController extends BaseController {
	
	@Autowired
	private RedeemPushManager pushManager;
	
	/**
	 * 特殊提前赎回管理
	 * 2016年3月1日
	 * By 陈广鹏
	 * @param redemptionex
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("pushList")
	public String pushList(Redemptionex redemptionex, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		Page<Redemptionex> page = new Page<Redemptionex>(request, response);
		Page<Redemptionex> pageview = pushManager.findPushList(page, redemptionex);

		model.addAttribute("page", pageview);
		model.addAttribute("rdm", redemptionex);
		String[] types = {"tz_redeem_type","tz_pay_type","tz_yes_no","tz_redemption_status"};
		FortuneDictUtil.addDicts(model,types);
		
		return "redemption/push/pushList";
	}
	
	/**
	 * 特殊提前赎回推送
	 * 2016年3月2日
	 * By 陈广鹏
	 * @param lendCode
	 * @param model
	 * @return
	 */
	@RequestMapping("pushDetail")
	public String pushDetail(String lendCode, Model model) {
		RedemptionApplyEntity entity = pushManager.loadPushDetail(lendCode);
		model.addAttribute("entity", entity);
		String[] types = {"tz_open_bank","tz_pay_type","com_card_type","tz_contract_vesion","tz_redeem_type"};
		FortuneDictUtil.addDicts(model,types);
		return "redemption/push/pushDetail";
	}
	
	/**
	 * 特殊提前赎回推送
	 * 2016年3月2日
	 * By 陈广鹏
	 * @param lendCode
	 * @param model
	 * @return
	 */
	@RequestMapping("pushRedeem")
	public String pushRedeem(RedemptionApplyEntity entity, Model model) {
		pushManager.pushRedeem(entity);
		return "redirect:" + this.adminPath + "/myApply/redemption/pushList";
	}
	
	/**
	 * 批量推送
	 * 2016年3月4日
	 * By 陈广鹏
	 * @param entity
	 * @return
	 */
	@RequestMapping("multiPush")
	public String multiPush(RedemptionApplyEntity entity) {
		pushManager.multiPush(entity.getRedemptionId());
		return "redirect:" + this.adminPath + "/myApply/redemption/pushList";
	}

}
