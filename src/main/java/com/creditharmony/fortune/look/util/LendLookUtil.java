package com.creditharmony.fortune.look.util;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import com.creditharmony.common.util.DateUtils;
import com.creditharmony.common.util.ObjectHelper;
import com.creditharmony.common.util.StringUtils;
import com.creditharmony.fortune.look.apply.entity.LendSearchObj;

/**
 * 查询统计-出借相关功能工具类
 * @Class Name LendLookUtil 
 * @author Mr
 * @Create In 2016年6月16日
 */
public class LendLookUtil {
	
	/**
	 * 设置查询条件
	 * 2016年4月14日
	 * By 肖长伟
	 * @param map
	 * @param lso
	 */
	public static void getConditions(Map<String, Object> map, LendSearchObj lso) {
		lso.setBackMoneyDayBegin(getTimeOfDate(lso.getBackMoneyDayBegin(), false));
		lso.setBackMoneyDayEnd(getTimeOfDate(lso.getBackMoneyDayEnd(), true));
		
		//将多个值得字符串切割成数组
		// 托管状态
		if(!("").equals(lso.getTuoguanStatus()) && lso.getTuoguanStatus() != null){
			map.put("tuoguanStatus", lso.getTuoguanStatus());
		}
		// 出借状态
		if(!("").equals(lso.getLendStatus()) && lso.getLendStatus() != null){
			map.put("lendStatus", lso.getLendStatus().split(","));
		}
		// 账单日
		if(!("").equals(lso.getApplyBillday()) && lso.getApplyBillday() != null){
			map.put("applyBillday",  lso.getApplyBillday().split(","));
		}
		// 付款方式
		if(!("").equals(lso.getApplyPay()) && lso.getApplyPay() != null){
			map.put("applyPay", lso.getApplyPay().split(","));
		}
		// 出借产品
		if(!("").equals(lso.getProductCode()) && lso.getProductCode() != null){
			map.put("productCode",  lso.getProductCode().split(","));
		}
		//划扣平台
		if (!ObjectHelper.isEmpty(lso.getDictApplyDeductType())) {
			map.put("dictApplyDeductType",lso.getDictApplyDeductType().split(","));
		}
		//渠道标识
		if (!ObjectHelper.isEmpty(lso.getDictFortunechannelflag())) {
			map.put("dictFortunechannelflag",lso.getDictFortunechannelflag().split(","));
		}
		//银行
		if (!ObjectHelper.isEmpty(lso.getAccountBank())) {
			map.put("accountBank", lso.getAccountBank().split(","));
		}
		//客户来源
		if (!ObjectHelper.isEmpty(lso.getCustomerTerminal())) {
			map.put("customerTerminal", lso.getCustomerTerminal().split(","));
		}
		//营业部
		if (StringUtils.isNotBlank(lso.getOrgCode())) {
			map.put("orgCode", lso.getOrgCode().split(","));
		}
		//审核日期
		if (StringUtils.isNotBlank(lso.getDictApplyEndState())) {
			map.put("dictApplyEndState", lso.getDictApplyEndState());
		}
//		status
		if (StringUtils.isNotBlank(lso.getStatus())) {
			map.put("status", lso.getStatus());
		}
		//投资状态
		if (StringUtils.isNotBlank(lso.getCustomerLendingStatus())) {
			map.put("customerLendingStatus", lso.getCustomerLendingStatus().split(","));
		}
		if (StringUtils.isNotBlank(lso.getSwitchApproveStatus())) {
			map.put("switchApproveStatus", lso.getSwitchApproveStatus().split(","));
		}
		if("".equals(lso.getStatus())){
			lso.setStatus(null);
		}
		//在职状态
		if(StringUtils.isNotEmpty(lso.getWorkingState())) {
			map.put("workingState",lso.getWorkingState().split(","));
		}
		//自转审批日期-起始
		if (lso.getZzApproveDateStart() != null) {
			map.put("zzApproveDateStart", DateUtils.formatDate(lso.getZzApproveDateStart(),"yyyy-MM-dd"));
		}
		//自转审批日期-结束
		if (lso.getZzApproveDateEnd() != null) {
			map.put("zzApproveDateEnd", DateUtils.formatDate(lso.getZzApproveDateEnd(),"yyyy-MM-dd"));
		}
		map.put("lso", lso);
	}
	
	public static void main(String[] args) {
		Date now = new Date();
		System.out.println(DateUtils.formatDate(now, "yyyy-MM-dd"));
	}
	
	/**
	 * 转化日期的最大时间，最小时间
	 * 2016年4月14日
	 * By 肖长伟
	 * @param date
	 * @param isMax
	 * @return
	 */
	public static Date getTimeOfDate(Date date, boolean isMax) {
		if (date != null) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			if (isMax) {
				calendar.set(Calendar.HOUR, 23);
				calendar.set(Calendar.MINUTE, 59);
				calendar.set(Calendar.SECOND, 59);
			} else {
				calendar.set(Calendar.HOUR, 0);
				calendar.set(Calendar.MINUTE, 0);
				calendar.set(Calendar.SECOND, 0);
			}
				return calendar.getTime();
		} else {
			return null;
		}
	}
}
