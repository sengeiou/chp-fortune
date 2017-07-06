package com.creditharmony.fortune.remind.web;

import java.util.Calendar;
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
import com.creditharmony.fortune.remind.util.ExportRemindThreeMonthHelper;
import com.google.common.collect.Lists;

/**
 * 消息提醒--三个月内到期提醒Controller
 * @Class Name RemindBeforeThreeMonthController
 * @author liusl
 * @Create In 2016年10月12日
 */
@Controller
@RequestMapping(value = "${adminPath}/remindBeforeThreeMonth")
public class RemindBeforeThreeMonthController extends RemindCommon {

	@Autowired
	private RemindManager remindManager;
	
	/**
	 * 三个月内到期提醒列表
	 * 2016年10月12日
	 * By liusl
	 * @param model
	 * @param request
	 * @param response
	 * @param smsSendListEx
	 * @return
	 */
	@RequestMapping(value="/advanceThreeMonthToRemindList")
	public String advanceThreeMonthToRemindList(Model model,HttpServletRequest request,
			HttpServletResponse response,SmsSendListEx smsSendListEx){
		smsSendListEx.setLendCodes(null);
		// 设置参数 提醒类型
//		List<String> dictRemindType = Lists.newArrayList();
//		dictRemindType.add(SmsType.QX_DQTX.value);
//		smsSendListEx.setDictRemindTypeList(dictRemindType);
//		smsSendListEx.setNewDate(new Date());
		
		//默认进来查询三个月内数据，再次查询按输入条件查询
		if(null == smsSendListEx.getFirstShow() || "".equals(smsSendListEx.getFirstShow())){
			if("".equals(smsSendListEx.getDueDayStart()) || null == smsSendListEx.getDueDayStart()){
				smsSendListEx.setDueDayStart(formetDate());
			}
			if("".equals(smsSendListEx.getDueDayEnd()) || null == smsSendListEx.getDueDayEnd()){
				smsSendListEx.setDueDayEnd(new Date());
			}
		}
		
		RemindUtils.getContains(smsSendListEx);
		RemindUtils.anreName(smsSendListEx);
		// 分页查询
		Page<SmsSendListEx> page=remindManager.findThreeMonthPage(new Page<SmsSendListEx>(request,response),smsSendListEx);
		// 设置列表数据
		model.addAttribute("page", page).addAttribute("smsCfSendListExt", smsSendListEx);
		// 出借金额
		Map<String,String> sumMoney = remindManager.getTotalLendMoneyNew(smsSendListEx);
		model.addAttribute("sumMoney",sumMoney);
		// 获取字典项
		DeductUtils.getDictList(model);
		return "remind/advanceThreeMonthToRemindList";
	}
	/**
	 * 获取当前时间往前推三个月
	 * 2016年10月12日
	 * By liusl
	 * @return
	 */
	public Date formetDate(){
		Date dNow = new Date();   //当前时间
		Date dBefore = new Date();
		Calendar calendar = Calendar.getInstance(); //得到日历
		calendar.setTime(dNow);//把当前时间赋给日历
		calendar.add(Calendar.MONTH, -3);  //设置为前3月
		dBefore = calendar.getTime();   //得到前3月的时间
		return dBefore;
	}
	
	/**
	 * 三个月内到期提醒详细
	 * 2016年10月12日
	 * By liusl
	 * @param model
	 * @param loanCode
	 * @return
	 */
//	@RequestMapping(value="/advance10TimeToRemindDetail")
//	public String advance10TimeToRemindDetail(Model model,String loanCode){
//		// 根据出借编号检索出详细
//		SmsSendListEx smsCfSendListView=remindManager.getRemindDetail(loanCode,null);
//		model.addAttribute("smsCfSendListExt", smsCfSendListView);
//		// 获取字典项
//		DeductUtils.getDictList(model);
//		return "remind/advance10TimeToRemindDetail";
//	}
	
	/**
	 * 导出明细
	 * 2016年10月12日
	 * By liusl
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/outExcel")
	public void outExcel(HttpServletRequest request,HttpServletResponse response,SmsSendListEx smsSendListEx){
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
		ExportRemindThreeMonthHelper.exportData(map, response);	
	}
}