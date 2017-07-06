package com.creditharmony.fortune.remind.web;

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

import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.fortune.type.SmsType;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.system.util.DataScopeUtil;
import com.creditharmony.core.type.SystemFlag;
import com.creditharmony.fortune.deduct.common.DeductUtils;
import com.creditharmony.fortune.remind.RemindUtils;
import com.creditharmony.fortune.remind.entity.ext.SmsSendListEx;
import com.creditharmony.fortune.remind.service.RemindManager;
import com.creditharmony.fortune.remind.util.ExportRemind10Helper;
import com.google.common.collect.Lists;

/**
 * 消息提醒--前10天到期Controller
 * @Class Name RemindBefore10DaysController
 * @author 韩龙
 * @Create In 2015年11月27日
 */
@Controller
@RequestMapping(value = "${adminPath}/remindBefore10Days")
public class RemindBefore10DaysController extends RemindCommon {

	@Autowired
	private RemindManager remindManager;
	
	/**
	 * 前10天到期列表
	 * 2015年11月30日
	 * By 韩龙
	 * @param model
	 * @param request
	 * @param response
	 * @param smsSendListEx
	 * @return
	 */
	@RequestMapping(value="/advance10TimeToRemindList")
	public String advance10TimeToRemindList(Model model,HttpServletRequest request,
			HttpServletResponse response,SmsSendListEx smsSendListEx){
		smsSendListEx.setLendCodes(null);
		// 设置参数 提醒类型
//		smsSendListEx.setDictRemindType(SmsType.QX_DQTX.value);
		List<String> dictRemindType = Lists.newArrayList();
		dictRemindType.add(SmsType.QX_DQTX.value);
//		dictRemindType.add(SmsType.FQX_DQTX.value);
		smsSendListEx.setDictRemindTypeList(dictRemindType);
		smsSendListEx.setNewDate(new Date());
		RemindUtils.getContains(smsSendListEx);
		RemindUtils.anreName(smsSendListEx);
		// 分页查询
		Page<SmsSendListEx> page=remindManager.
				findPage(new Page<SmsSendListEx>(request,response),smsSendListEx);
		// 设置列表数据
		model.addAttribute("page", page)
		.addAttribute("smsCfSendListExt", smsSendListEx);
		// 出借金额
		Map<String,String> sumMoney = remindManager.getTotalLendMoney(smsSendListEx);
		model.addAttribute("sumMoney",sumMoney);
		// 获取字典项
		DeductUtils.getDictList(model);
		model.addAttribute("pageTokenId", 10);
		return "remind/advance10TimeToRemindList";
	}
	
	/**
	 * 前10天到期详细
	 * 2015年11月30日
	 * By 韩龙
	 * @param model
	 * @param loanCode
	 * @return
	 */
	@RequestMapping(value="/advance10TimeToRemindDetail")
	public String advance10TimeToRemindDetail(Model model,String loanCode){
		// 根据出借编号检索出详细
		SmsSendListEx smsCfSendListView=remindManager.getRemindDetail(loanCode,null);
		model.addAttribute("smsCfSendListExt", smsCfSendListView);
		// 获取字典项
		DeductUtils.getDictList(model);
		return "remind/advance10TimeToRemindDetail";
	}
	
	/**
	 * 导出明细
	 * 2015年11月30日
	 * By 韩龙
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/outExcel")
	public void outExcel(HttpServletRequest request,
			HttpServletResponse response,SmsSendListEx smsSendListEx){
		smsSendListEx.setDictRemindType(SmsType.QX_DQTX.value);
		List<String> remindType = Lists.newArrayList();
		remindType.add(SmsType.QX_DQTX.value);
		smsSendListEx.setDictRemindTypeList(remindType);
		RemindUtils.getContains(smsSendListEx);
		//表示导出已勾选的数据
		if (null != smsSendListEx.getLendCodes()&& smsSendListEx.getLendCodes().size()>0 ) {
			smsSendListEx.setLoanCode(null);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sms", smsSendListEx);
		String dataRights = DataScopeUtil.getDataScope("a", SystemFlag.FORTUNE.value);
		if(StringUtils.isNotEmpty(dataRights)){
			Map<String, String> sqlMap = new HashMap<String, String>();
			sqlMap.put("dataRights", dataRights);
			map.put("sqlMap", sqlMap);
		}
		ExportRemind10Helper.exportData(map, response);	
	}
}
