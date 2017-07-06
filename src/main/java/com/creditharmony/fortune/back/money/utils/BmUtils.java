package com.creditharmony.fortune.back.money.utils;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.creditharmony.common.util.DateUtils;
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
import com.creditharmony.fortune.customer.workflow.util.BillDateUtil;
import com.creditharmony.fortune.redemption.utils.RedeemUtils;

public class BmUtils {
	
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
	 * 初始化回款渠道分配列表查询条件
	 * 2017年5月12日11:11:28
	 * By 郭强
	 * @param view
	 */
	/*public static void initAllocationSearch(ListItemView view) {
		view.setStatusList(BmConstant.ALLOACTION_STATUS_LIST);
		
		if (ObjectHelper.isEmpty(view.getIsJZH())) {
			view.setIsJZH(YoN.FOU.value);
		}
	}*/
	
	/**
	 * 组装导表搜索条件
	 * 2016年4月15日
	 * By 陈广鹏
	 * @param view
	 * @return
	 */
	public static ListItemView assembleExportCondition(ListItemView view){
		if (view == null) {
			view = new ListItemView();
		}
		String city = view.getCity();
		if (null != city && city.length()>0) {
			String c = "%" + city.replace(",", "%|%") +"%";
			view.setCity(c);
		}
		
		// 检索出列表
		String ids = view.getBackmId();
		if (null != ids && !"".equals(ids)) {
			// 有勾选
			String[] applys = ids.split(",");
			List<String> codes = null;
			if (applys.length > 0) {
				codes = Arrays.asList(applys);
			}
			ListItemView itemView = new ListItemView();
			itemView.setBackmIds(codes);
			itemView.setIsJZH(view.getIsJZH());
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
	
	/**
	 * 获取两个日期相差天数
	 */
	public static int getSubtractDay(Date start, Date end){
		String startStr = DateUtils.formatDate(start, "yyyy-MM-dd");
		String endStr = DateUtils.formatDate(end, "yyyy-MM-dd");
		start = DateUtils.parseDate(startStr);
		end = DateUtils.parseDate(endStr);
		
		if (start.after(end)) {
			return 0;
		}
		long t = end.getTime()-start.getTime();
		return (int)(t/(1000*3600*24));
	}
	
	/**
	 * 根据出借日期与账单日获取下一个账单日
	 * @param date
	 * @param billDay
	 * @return
	 */
	public static Date  recentlyDay(Date date, int billDay){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		Calendar cycleEnd = Calendar.getInstance();
		int nextMax = 0;
		if (cal.get(Calendar.DATE) < billDay &&	cal.get(Calendar.MONTH) != 1) {
			Calendar temp = Calendar.getInstance();
			temp.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, 1);// 下月1日
			temp.add(Calendar.DAY_OF_MONTH, -1);// 本月最后一天
			nextMax = billDay > temp.get(Calendar.DAY_OF_MONTH) ? temp
					.get(Calendar.DAY_OF_MONTH) : billDay;
			cycleEnd.set(temp.get(Calendar.YEAR), temp.get(Calendar.MONTH),
					nextMax);
		} else if (cal.get(Calendar.MONTH) == 1) {
			if(cal.get(Calendar.DATE) > billDay){
				Calendar temp = Calendar.getInstance();
				temp.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 2, 1);// 下下月1日
				temp.add(Calendar.DAY_OF_MONTH, -1);// 下月最后一天
				nextMax = billDay > temp.get(Calendar.DAY_OF_MONTH) ? temp
						.get(Calendar.DAY_OF_MONTH) : billDay;
				cycleEnd.set(temp.get(Calendar.YEAR), temp.get(Calendar.MONTH),
								nextMax);
			}else if(cal.get(Calendar.DATE) == billDay){
				Calendar temp = Calendar.getInstance();
				temp.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 2, 1);// 下下月1日
				temp.add(Calendar.DAY_OF_MONTH, -1);// 下月最后一天
				nextMax = billDay > temp.get(Calendar.DAY_OF_MONTH) ? temp
						.get(Calendar.DAY_OF_MONTH) : billDay;
				cycleEnd.set(temp.get(Calendar.YEAR), temp.get(Calendar.MONTH),
								nextMax);
			}else{
				Calendar temp = Calendar.getInstance();
				temp.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, 1);// 下下月1日
				temp.add(Calendar.DAY_OF_MONTH, -1);// 下月最后一天
				nextMax = billDay > temp.get(Calendar.DAY_OF_MONTH) ? temp
						.get(Calendar.DAY_OF_MONTH) : billDay;
				cycleEnd.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),
						nextMax);
			}
		} else {
			Calendar temp = Calendar.getInstance();
			temp.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 2, 1);// 下下月1日
			temp.add(Calendar.DAY_OF_MONTH, -1);// 下月最后一天
			nextMax = billDay > temp.get(Calendar.DAY_OF_MONTH) ? temp
					.get(Calendar.DAY_OF_MONTH) : billDay;
			cycleEnd.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1,
					nextMax);
		}

		return cycleEnd.getTime();
	}
	
	/**
	 * 根据出借日期 与账单日 获取前一账单日
	 * date  出借日期
	 * billDay	账单日
	 */
	public static Date lastDay(Date date, int billDay){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		Calendar cycleStart = Calendar.getInstance();
		// 前一账单日
		int lastMin = 0;
		if (cal.get(Calendar.DATE) > billDay) {
			cycleStart.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),
					billDay);
		} else {
			Calendar temp = Calendar.getInstance();
			temp.setTime(date);
			temp.set(temp.get(Calendar.YEAR), temp.get(Calendar.MONTH), 1);// 本月1日
			temp.add(Calendar.DATE, -1); // 上月最后一天
			lastMin = billDay > temp.get(Calendar.DATE) ? temp
					.get(Calendar.DATE) : billDay; // 取上月天数和账单日的最小值
			cycleStart.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) - 1,
					lastMin);
		}
		return cycleStart.getTime();
	}
	
	
	/**
	 * 计算出借日期
	 */
	public static int  lendDay(Date date, int n){
		Date ndate  = recentlyDay(date,n);
		int days = getSubtractDay(date, ndate)+1;
		return days;
	}
	
	/**
	 * 获取指定日期所在账单周期的天数
	 * @param date 到期日
	 * @param billDay 账单日
	 * @return
	 */
	public static int getCycleDays(Date date, int billDay){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		Calendar cycleStart = Calendar.getInstance();
		Calendar cycleEnd = Calendar.getInstance();
		// 前一账单日
		int lastMin = 0;
		if (cal.get(Calendar.DATE) > billDay) {
			cycleStart.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),
					billDay);
		} else {
			Calendar temp = Calendar.getInstance();
			temp.setTime(date);
			temp.set(temp.get(Calendar.YEAR), temp.get(Calendar.MONTH), 1);// 本月1日
			temp.add(Calendar.DATE, -1); // 上月最后一天
			lastMin = billDay > temp.get(Calendar.DATE) ? temp
					.get(Calendar.DATE) : billDay; // 取上月天数和账单日的最小值
			cycleStart.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) - 1,
					lastMin);
		}
		// 后一账单日
		int nextMax = 0;
		if (cal.get(Calendar.DATE) < billDay) {
			Calendar temp = Calendar.getInstance();
			temp.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, 1);// 下月1日
			temp.add(Calendar.DAY_OF_MONTH, -1);// 本月最后一天
			nextMax = billDay > temp.get(Calendar.DAY_OF_MONTH) ? temp
					.get(Calendar.DAY_OF_MONTH) : billDay;
			cycleEnd.set(temp.get(Calendar.YEAR), temp.get(Calendar.MONTH),
					nextMax);
		} else if (cal.get(Calendar.DATE) == billDay) {
			cycleEnd.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),
					cal.get(Calendar.DATE));
		} else {
			Calendar temp = Calendar.getInstance();
			temp.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 2, 1);// 下下月1日
			temp.add(Calendar.DAY_OF_MONTH, -1);// 下月最后一天
			nextMax = billDay > temp.get(Calendar.DAY_OF_MONTH) ? temp
					.get(Calendar.DAY_OF_MONTH) : billDay;
			cycleEnd.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1,
					nextMax);
		}

		long a = cycleEnd.getTimeInMillis() - cycleStart.getTimeInMillis();
		int b = (int) (a / 1000 / 3600 / 24);

		return b;
	}
	
	/**
	 * 债转日,到期日,账单日 来推算应回息期数
	 * transferDay	债转日
	 * finalLinitDate	到期日
	 * applyBillday	账单日
	 */
	public static int interestNum(Date transferDay, Date finalLinitDate, int applyBillday){
		int  num = 1;
		try {
			transferDay = new SimpleDateFormat("yyyy-MM-dd").parse(new SimpleDateFormat("yyyy-MM-dd").format(transferDay));
		} catch (Exception e) {
			System.err.println("BmUtils interestNum 债转日,到期日,账单日 来推算应回息期数");
			e.printStackTrace();
		}
		//根据到期日与账单日获取到了下一账单日
		Date finallyDate= recentlyDay(finalLinitDate,applyBillday);
		try {
			finallyDate = new SimpleDateFormat("yyyy-MM-dd").parse(new SimpleDateFormat("yyyy-MM-dd").format(finallyDate));
		} catch (Exception e) {
			System.err.println("BmUtils interestNum 债转日,到期日,账单日 来推算应回息期数");
			e.printStackTrace();
		}
		if(finallyDate.after(transferDay)){
			return num = 0;
		}
		while(transferDay.after(finallyDate)){
			finallyDate =  recentlyDay(finallyDate,applyBillday);
			try {
				finallyDate = new SimpleDateFormat("yyyy-MM-dd").parse(new SimpleDateFormat("yyyy-MM-dd").format(finallyDate));
			} catch (Exception e) {
				System.err.println("BmUtils interestNum 债转日,到期日,账单日 来推算应回息期数");
				e.printStackTrace();
			}
			if(transferDay.after(finallyDate)){
				num++;
			}else if(isSameDate(transferDay,finallyDate)){
				num++;
			}
		}
		return num;
	}
	
	
	/**
	 * 债转日,到期日,账单日 来推算最后一个账单日
	 * transferDay	债转日
	 * finalLinitDate	到期日
	 * applyBillday	账单日
	 */
	public static Date finallyDate(Date transferDay, Date finalLinitDate, int applyBillday){
		try {
			transferDay = new SimpleDateFormat("yyyy-MM-dd").parse(new SimpleDateFormat("yyyy-MM-dd").format(transferDay));
			finalLinitDate = new SimpleDateFormat("yyyy-MM-dd").parse(new SimpleDateFormat("yyyy-MM-dd").format(finalLinitDate));
		} catch (Exception e) {
			System.err.println("BmUtils finallyDate 债转日,到期日,账单日 来推算最后一个账单日");
			e.printStackTrace();
		}
		//根据到期日与账单日获取到了下一账单日
		Date finallyDate= recentlyDay(finalLinitDate,applyBillday);
		try {
			finallyDate = new SimpleDateFormat("yyyy-MM-dd").parse(new SimpleDateFormat("yyyy-MM-dd").format(finallyDate));
		} catch (Exception e) {
			System.err.println("BmUtils finallyDate 债转日,到期日,账单日 来推算最后一个账单日");
			e.printStackTrace();
		}
		if(finallyDate.after(transferDay)){
			finallyDate=lastDay(finalLinitDate,applyBillday);
			return finallyDate;
		}
		Date day =finallyDate;
		while(transferDay.after(finallyDate)){
			finallyDate =  recentlyDay(finallyDate,applyBillday);
			try {
				finallyDate = new SimpleDateFormat("yyyy-MM-dd").parse(new SimpleDateFormat("yyyy-MM-dd").format(finallyDate));
			} catch (Exception e) {
				System.err.println("BmUtils finallyDate 债转日,到期日,账单日 来推算最后一个账单日");
				e.printStackTrace();
			}
			if(transferDay.compareTo(finallyDate)>=0){
				day=finallyDate;
			}
		}
		return day;
	}
	
	/**
	 * 根据获取到的本期账单日,到期日,账单日 判断是不是首期
	 * currentDay	本期账单日
	 * finalLinitDate	到期日
	 * applyBillday	账单日
	 */
	public static boolean ifFirst(Date currentDay, Date finalLinitDate, int applyBillday){
		boolean  flg = false;
		Date finallyDate= recentlyDay(finalLinitDate,applyBillday);
		String format = new SimpleDateFormat("yyyy-MM-dd").format(currentDay);
		String format1 = new SimpleDateFormat("yyyy-MM-dd").format(finallyDate);
		if(format.equals(format1)){
			flg = true;
		}
		return flg;
	}
	
	/**
	 * 判断两个时间是否是同一天的方法
	 * date
	 * date1
	 * 2017年5月26日13:04:22
	 */
	public static boolean isSameDate (Date date,Date date1){
	   Calendar cal1 = Calendar.getInstance();
       cal1.setTime(date);

       Calendar cal2 = Calendar.getInstance();
       cal2.setTime(date1);

       boolean isSameYear = cal1.get(Calendar.YEAR) == cal2
               .get(Calendar.YEAR);
       boolean isSameMonth = isSameYear
               && cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH);
       boolean isSameDate = isSameMonth
               && cal1.get(Calendar.DAY_OF_MONTH) == cal2
                       .get(Calendar.DAY_OF_MONTH);

       return isSameDate;
		
	}
}
