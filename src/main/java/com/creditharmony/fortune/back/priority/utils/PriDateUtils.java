package com.creditharmony.fortune.back.priority.utils;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.creditharmony.common.util.DateUtils;
import com.creditharmony.common.util.ObjectHelper;
import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.fortune.type.PayMent;
import com.creditharmony.core.fortune.type.ProductCode;
import com.creditharmony.fortune.redemption.common.entity.ext.Redemptionex;
import com.creditharmony.fortune.redemption.constant.RedeemConstant;

/**
 * 赎回工具类
 * @Class Name RedeemUtils
 * @author 陈广鹏
 * @Create In 2015年12月30日
 */
public class PriDateUtils {

	/**
	 * 根据日期获取当年天数
	 * 2015年12月30日
	 * By 陈广鹏
	 * @param date
	 * @return
	 */
	public static int getYearDays(Date date){
		GregorianCalendar gc = (GregorianCalendar) GregorianCalendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dStr = sdf.format(date);
		
		int year = Integer.parseInt(dStr.substring(0, 4));
		return gc.isLeapYear(year)?366:365;
	}
	
	/**
	 * 根据日期获取当月天数
	 * 2016年11月9日
	 * By 陈广鹏
	 * @param date
	 * @return
	 */
	public static int getMonthDays(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.getActualMaximum(Calendar.DATE);
	}
	
	/**
	 * 获取指定日期所在账单周期的天数
	 * 2016年1月18日
	 * By 陈广鹏
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
	 * 获取两个日期相差天数
	 * 2015年12月30日
	 * By 陈广鹏
	 * @param start
	 * @param end
	 * @return
	 */
	public static int getDays(Date start, Date end){
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
	 * 计算到期日前最近的到期日，如果到期日期与账单日相同，得到的到期日等于账单日
	 * 2016年1月18日
	 * By 陈广鹏
	 * @param date 到期日期
	 * @param billDay 账单日
	 * @return
	 * @throws ParseException 
	 */
	public static Date getLastBillDay(Date date, int billDay) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		Calendar lastBillDay = Calendar.getInstance();
		// 前一账单日
		int lastMin = 0;
		if (cal.get(Calendar.DATE) >= billDay) {
			// 取本月账单日
			lastBillDay.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),
					billDay);
		} else {
			Calendar temp = Calendar.getInstance();
			temp.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), 1);// 本月1日
			temp.add(Calendar.DAY_OF_MONTH, -1);// 上月最后一天
			lastMin = billDay > temp.get(Calendar.DAY_OF_MONTH) ? temp
					.get(Calendar.DAY_OF_MONTH) : billDay; // 取上月天数和账单日的最小值
			lastBillDay.set(temp.get(Calendar.YEAR),
					temp.get(Calendar.MONTH), lastMin);
		}
		Date time = lastBillDay.getTime();
		String timeStr = DateUtils.formatDate(time, "yyyy-MM-dd");
		return DateUtils.parseDate(timeStr);

	}

	/**
	 * 计算两个日期间的月数
	 * 2016年1月19日
	 * By 陈广鹏
	 * @param start
	 * @param end
	 * @return
	 */
	public static int getMonths(Date start, Date end) {
		if (start.after(end)) {
			return 0;
		}
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.setTime(start);
		c2.setTime(end);
		int years = c2.get(Calendar.YEAR)-c1.get(Calendar.YEAR);
		int months = c2.get(Calendar.MONTH)-c1.get(Calendar.MONTH);
		months = years *12 + months ;
		if (c2.get(Calendar.DATE)<c1.get(Calendar.DATE)) {
			months -= 1;
		}
		return months;
	}
	
	/**
	 * 获取已回息周期后的下一周期起始日期
	 * @author xurongsheng
	 * @date 2016年10月17日 下午2:47:15
	 * @param lendDate	出借日期
	 * @param successCount	已回息次数
	 * @param cycleMonths	每周期月数
	 * @return
	 */
	public static Date getNextCycleDay(Date lendDate,int successCount,int cycleMonths){
		Calendar cal = Calendar.getInstance();
		cal.setTime(lendDate);
		cal.add(Calendar.MONTH, successCount*cycleMonths);
		return cal.getTime();
	}
		
	/**
	 * 计算两个日期间的月数</br>
	 * 不满一月按一月算
	 * @author xurongsheng
	 * @date 2016年8月30日 下午3:10:12
	 * @param start
	 * @param end
	 * @return
	 */
	public static int getMonths4RealLend(Date start, Date end) {
		if (start.after(end)) {
			return 0;
		}
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.setTime(start);
		c2.setTime(end);
		int years = c2.get(Calendar.YEAR)-c1.get(Calendar.YEAR);
		int months = c2.get(Calendar.MONTH)-c1.get(Calendar.MONTH);
		months = years *12 + months ;
		if (c2.get(Calendar.DATE)>c1.get(Calendar.DATE)) {
			months += 1;
		}
		return months;
	}
	
	/**
	 * 获取前一个工作日
	 * 2016年3月2日
	 * By 陈广鹏
	 * @param date
	 * @return
	 */
	public static Date getPreviousWorkday(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int week = c.get(Calendar.DAY_OF_WEEK);
		if(week== Calendar.MONDAY){
			// 周一
			c.add(Calendar.DAY_OF_YEAR, -3);
		} else if(week == Calendar.SUNDAY){
			// 周日
			c.add(Calendar.DAY_OF_YEAR, -2);
		} else {
			// 周六
			c.add(Calendar.DAY_OF_YEAR, -1);
		}
		return c.getTime();
	}
	
	/**
	 * 获取前推n个工作日的日期
	 * 2016年11月30日
	 * By 陈广鹏
	 * @param date
	 * @param n 应为正数
	 * @return
	 */
	public static Date getPreviousWorkday(Date date, int n) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);

		int week = 0;
		for (int i = 0; i < n; i++) {
			c.add(Calendar.DAY_OF_YEAR, -1);
			week = c.get(Calendar.DAY_OF_WEEK);
			while (week == Calendar.SATURDAY || week == Calendar.SUNDAY) {
				// 周六周日向前推到周五
				c.add(Calendar.DAY_OF_YEAR, -1);
				week = c.get(Calendar.DAY_OF_WEEK);
			}
		}

		return c.getTime();
	}
	
	/**
	 * 判断一个日期是否为工作日
	 * 2016年11月30日
	 * By 陈广鹏
	 * @param date
	 * @return
	 */
	public static boolean isWorkday(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int week = c.get(Calendar.DAY_OF_WEEK);
		if (week == Calendar.SATURDAY || week == Calendar.SUNDAY) {
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * 到期日-最后一个账单日的天数
	 */
	public static int  datenum(Date date, int n){
		Date ndate  = PriDateUtils.getLastBillDay(date , n);
		int days = PriDateUtils.getDays(ndate, date);
		return days;
	}
	
	
	/**
	 * 组装导出文件时的搜索条件
	 * 2016年4月18日
	 * By 陈广鹏
	 * @param redemptionex
	 * @return
	 */
	public static Redemptionex assembleExportCondition(Redemptionex redemptionex){
		String redemtptionIds = redemptionex.getRedemptionId();
		String city = redemptionex.getAddrCity();
		if (!ObjectHelper.isEmpty(city)) {
			String c = "%" + city.replace(",", "%|%") +"%";
			redemptionex.setAddrCity(c);
		}
		// 选中条数导出记录
		if (StringUtils.isNotEmpty(redemtptionIds)) {
			// 有勾选
			String[] applys = redemtptionIds.split(",");
			List<String> codes = null;
			if (applys.length > 0) {
				codes = Arrays.asList(applys);
			}
			Redemptionex ext = new Redemptionex();
			ext.setRedemtptionIds(codes);
			return ext;
		} else {
			// 无勾选
			return redemptionex;
		}
	}
	
	/**
	 * 判断是否支持部分赎回
	 * 2016年5月16日
	 * By 陈广鹏
	 * @param applyLendMoney 出借金额
	 * @param applyLendDay 出借日期
	 * @param applyPay 付款方式
	 * @param productCode 产品编号
	 * @param contractVersion 合同版本
	 * @return true允许，false不允许
	 */
	public static boolean allowRedeemPart(
			BigDecimal applyLendMoney, 
			Date applyLendDay, 
			String applyPay, 
			String productCode, 
			String contractVersion){
		boolean result = true;
		if (!RedeemConstant.CONTRACT_EDT_LIST.contains(contractVersion)) {
			// 有部分赎回限制的合同版本
			if (new BigDecimal(RedeemConstant.LOW_LIMIT).compareTo(applyLendMoney) != -1) {
				// 低于最低出借金额
				if (RedeemConstant.PRODUCT_CODE_LIST.contains(productCode)) {
					// 产品为：信和通、月息通、季度盈、双季盈、信和宝、信和宝ABC
					result = false;
				}
			} else {
				result = true;
			}
			// 在2016年1月5日之前出借的年年盈、年年金赎回再出借金额可低于5万
			Date sd = DateUtils.parseDate(RedeemConstant.DEVIDE_DATE);
			if (sd.after(applyLendDay)) {
				if (ProductCode.NNY.value.equals(productCode)) {
					result = true;
				}
				if (ProductCode.NNJ.value.equals(productCode)) {
					result = true;
				}
			}
			/**
			 * 年年金：
			 * 出借日期是2016-01-01以后（包括2016-01-01）：
			 * 		若投资金额大于5万，允许部分赎回；
			 * 		若投资金额小于等于5万，不允许部分赎回；
			 * 出借日期是2016-01-01以前：
			 * 		若投资金额大于1万，允许部分赎回；
			 * 		若投资金额小于等于1万，不允许部分赎回；
			 */
			if (ProductCode.NNJ.value.equals(productCode)) {
				Date NNJsd = DateUtils.parseDate("2016-01-01");
				if (NNJsd.after(applyLendDay)) {
					// 出借日期是2016-01-01以前
					if (new BigDecimal("10000").compareTo(applyLendMoney) != -1) {
						// 出借金额 <= 1W
						result = false;
					} else {
						result = true;
					}
				} else {
					// 出借日期是2016-01-01以后（包括2016-01-01）
					if (new BigDecimal(RedeemConstant.LOW_LIMIT).compareTo(applyLendMoney) != -1) {
						// 出借金额 <= 5W
						result = false;
					} else {
						result = true;
					}
				}
			}
		} else {
			result = true;
		}
		// 资金托管的出借，只能全部赎回，不能部分赎回
		if (PayMent.ZJTG.value.equals(applyPay)) {
			result = false;
		}
		return result;
	}
}
