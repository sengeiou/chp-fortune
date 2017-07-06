package com.creditharmony.fortune.back.priority.utils;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.creditharmony.common.util.ObjectHelper;
import com.creditharmony.common.util.SpringContextHolder;
import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.common.type.UseFlag;
import com.creditharmony.core.deduct.entity.PlatformBankEntity;
import com.creditharmony.core.deduct.service.PlatformBankService;
import com.creditharmony.core.deduct.type.DeductFlagType;
import com.creditharmony.core.exception.ServiceException;
import com.creditharmony.core.fortune.type.YoN;
import com.creditharmony.core.type.SystemFlag;
import com.creditharmony.fortune.back.money.common.view.ListItemView;
import com.creditharmony.fortune.back.money.constant.BmConstant;
import com.creditharmony.fortune.back.priority.common.view.PriorityListItemView;
import com.creditharmony.fortune.customer.workflow.util.BillDateUtil;
import com.creditharmony.fortune.redemption.utils.RedeemUtils;

public class PmUtils {
	
	private static PlatformBankService platformBankService = SpringContextHolder.getBean(PlatformBankService.class);
	
	/**
	 * 初始化执行回款列表查询条件
	 * 2016年2月19日
	 * By 陈广鹏
	 * @param view
	 */
	public static void initExecuteSearch(ListItemView view) {
		view.setStatusList(BmConstant.EXECUTE_STATUS_LIST);
		
		if (ObjectHelper.isEmpty(view.getIsJZH())) {
			view.setIsJZH(YoN.FOU.value);
		}
	}
	
	/**
	 * 组装导表搜索条件
	 * 2016年4月15日
	 * By 陈广鹏
	 * @param view
	 * @return
	 */
	public static PriorityListItemView assembleExportCondition(PriorityListItemView view){
		if (view == null) {
			view = new PriorityListItemView();
		}
		String city = view.getCity();
		if (null != city && city.length()>0) {
			String c = "%" + city.replace(",", "%|%") +"%";
			view.setCity(c);
		}
		
		// 检索出列表
		String ids = view.getPrioIds();
		if (null != ids && !"".equals(ids)) {
			// 有勾选
			String[] applys = ids.split(",");
			List<String> codes = null;
			if (applys.length > 0) {
				codes = Arrays.asList(applys);
			}
			PriorityListItemView itemView = new PriorityListItemView();
			itemView.setPriorityIds(codes);
			return itemView;
		}else{
			 // 无勾选
			return view;
		}
	}
	
	/**
	 * 抛出操作数据过期异常
	 * 2016年4月28日
	 * By 陈广鹏
	 */
	public static void throwDataOutOfDateException(){
		throw new ServiceException("页面数据已过期，请刷新页面后再次操作");
	}
	
	/**
	 * 取得第三方平台限额信息
	 * 2016年5月5日
	 * By 陈广鹏
	 * @param platformId
	 */
	public static PlatformBankEntity getLimitMoney(String platformId){
		PlatformBankEntity pfr = new PlatformBankEntity(); 
		pfr.setBankId("");
		pfr.setPlatformId(platformId);
		pfr.setDeductFlag(DeductFlagType.PAY.getCode());
		pfr.setSysId(String.valueOf(SystemFlag.FORTUNE.value));
		pfr.setStatus(UseFlag.QY.value);
		pfr = platformBankService.getPlatformBank(pfr);
		return pfr;
	}
	
	/**
	 * 去掉省市末尾的“省”、“市”字样<br/>
	 * 2016年6月28日<br/>
	 * By 陈广鹏
	 * @param string
	 * @return
	 */
	public static String deleteTail(String string){
		if (StringUtils.isEmpty(string)) {
			string = "";
		}
		return string.replace("省", "").replace("市", "");
	}
	
	/**
	 * 获取回款导出文件备注信息
	 * 2016年6月29日
	 * By 陈广鹏
	 * @param lendCode
	 * @return
	 */
	public static String getRemark(String lendCode){
		String remark = "";
		if (StringUtils.isNotEmpty(lendCode)) {
			remark = lendCode.replace("-", "");
			remark += "回款";
		}
		return remark;
	}
	
	/**
	 * 根据回款日期计算债权转让日期<br/>
	 * 回款日期（包括回款日期当天）前3个工作日的前一个自然日是债权转让日期<br/>
	 * 2016年11月29日<br/>
	 * By 陈广鹏<br/>
	 * @param backMoneyDay
	 * @return debtTransferDate
	 */
	public static Date getDebtTransferDate(Date backMoneyDay){
		Date debtTransferDate = backMoneyDay;
		// 前3个工作日
		if (RedeemUtils.isWorkday(backMoneyDay)) {
			// 回款日期为工作日，向前推2天
			debtTransferDate = RedeemUtils.getPreviousWorkday(debtTransferDate, 2);
		} else {
			// 回款日期不为工作日，向前推3天
			debtTransferDate = RedeemUtils.getPreviousWorkday(debtTransferDate, 3);
		}
		// 前一个自然日
		debtTransferDate = BillDateUtil.addDays(debtTransferDate, -1);
		return debtTransferDate;
	}

	/**
	 * 计算补息天数：到期日下一日到债权转让日期（包括债权转让日期当天）<br/>
	 * 2016年12月9日<br/>
	 * By 陈广鹏<br/>
	 * @param finalLinitDate 到期日
	 * @param debtTransferDate 债权转让日期
	 * @return
	 */
	public static int getDays(Date finalLinitDate, Date debtTransferDate) {
		int days = RedeemUtils.getDays(finalLinitDate, debtTransferDate);
		return days;
	}

	/**
	 * 判断回款日期是否在到期日的三个工作日内<br/>
	 * 2016年12月9日<br/>
	 * By 陈广鹏<br/>
	 * @param finalLinitDate 到期日
	 * @param backMoneyDay 回款日期
	 * @return
	 */
	public static boolean in3Workdays(Date finalLinitDate, Date backMoneyDay) {
		
		// 到期日后第三个工作日
		Calendar c = Calendar.getInstance();
		c.setTime(finalLinitDate);

		int week = 0;
		for (int i = 0; i < 3; i++) {
			c.add(Calendar.DAY_OF_YEAR, 1);
			week = c.get(Calendar.DAY_OF_WEEK);
			while (week == Calendar.SATURDAY || week == Calendar.SUNDAY) {
				// 周六周日向后推到周一
				c.add(Calendar.DAY_OF_YEAR, 1);
				week = c.get(Calendar.DAY_OF_WEEK);
			}
		}
		Date date = c.getTime();
		if (backMoneyDay.after(date)) {
			return false;
		} else {
			return true;
		}
	}
	
}
