package com.creditharmony.fortune.utils;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import com.creditharmony.common.util.DateUtils;
import com.creditharmony.core.claim.util.CreditorUtils;
import com.creditharmony.core.fortune.type.ProductCode;
import com.creditharmony.core.fortune.type.XinhebaoType;
import com.creditharmony.fortune.back.money.constant.BmConstant;
import com.creditharmony.fortune.redemption.constant.RedeemConstant;
import com.creditharmony.fortune.redemption.utils.RedeemUtils;

/**
 * 回款金额计算
 * 
 * @Class Name BackMoneyUtil
 * @author 赵红江
 * @Create In 2016年2月15日
 */
public class BackMoneyUtil {
	/**
	 * 根据产品Code，出借日期，账单日期，出借金额，利率，计算获取回款金额
	 * 
	 * 2016年2月15日
	 * By 赵红江
	 * @param productCode 产品code
	 * @param lendDay 出借日期
	 * @param billDay 账单日
	 * @param lendMoney 出借金额
	 * @param maturityDay 到期日
	 * @param rate 利率（信和月增/月息通为月利率,其他为年利率,此参数可以直接调用getRate方法获取
	 * @param matchingRate 债权匹配基准利率（信和月增/月息通需要传递次参数(债权匹配基准利率),其他情况传空即可）,
	 * 20160801之后(含)的出借传入收益调整表中加息后年化收益率
	 * @param backType 回息类型（信和宝:12月回息/24月回息）
	 * @param leadDate 出借期数
	 * @return
	 */
	public static BigDecimal getBackMoney(String productCode, Date lendDay,
			int billDay, Date maturityDay, BigDecimal lendMoney,
			BigDecimal rate,BigDecimal matchingRate, String backType, String lendDate) {
		if (rate == null) {
			rate = BigDecimal.ZERO;
		}
		if(lendMoney==null){
			lendMoney = BigDecimal.ZERO;
		}else{
			lendMoney= lendMoney.setScale(5, BigDecimal.ROUND_HALF_UP);
		}
		// 出借日所在月的天数 lendDays
		Calendar cal = Calendar.getInstance();
		cal.setTime(lendDay);
		int month = cal.get(Calendar.MONTH);
		int year = cal.get(Calendar.YEAR);
		cal.set(year, month, 1);
		String lendDays = String.valueOf(cal.getActualMaximum(Calendar.DATE));
		// 计息天数=到期日-出借日期+1
		String interestDays = "0";
		if (maturityDay != null && lendDay != null) {
			interestDays = String.valueOf((maturityDay.getTime() - lendDay.getTime())
					/ (24 * 60 * 60 * 1000) + 1);
		}
		String days = getDaysByLastdayAndBackday(billDay, maturityDay);
		BigDecimal backMoney = BigDecimal.ZERO;
		// 出借日所在账单日周期天数
		String billweekDay = String.valueOf(CreditorUtils.getDateNumberBylendDay(lendDay));// 出借日所在账单日周期天数

		Date benchmarkDay = DateUtils.parseDate(RedeemConstant.DEVIDE_DATE);
		Date date20160801 = DateUtils.parseDate(BmConstant.YXT_DATE); 
		if (ProductCode.YYY.value.equals(productCode)) {// 月邮赢
			// 出借金额*月利率/出借日所在账单日周期天数*未回息天数+出借金额
			backMoney = ((((lendMoney.multiply(rate)).divide(
					new BigDecimal(billweekDay),15, BigDecimal.ROUND_HALF_UP)).multiply(
							new BigDecimal(days))).divide(new BigDecimal("100"),15, BigDecimal.ROUND_HALF_UP)).add(lendMoney);

		} else if (ProductCode.YMY.value.equals(productCode)) {// 月满盈
			if("0".equals(interestDays)){
				interestDays="30";
			}
			// 出借金额*年化收益率/12/出借日所在月的天数*计息天数（目前固定为匹配30天）+出借金额   (小数点后5位)
			backMoney = (((((lendMoney.multiply(rate)).divide(new BigDecimal(lendDays),15, BigDecimal.ROUND_HALF_UP)).multiply(
					new BigDecimal(interestDays))).divide(new BigDecimal("100"),15, BigDecimal.ROUND_HALF_UP))
					.divide(new BigDecimal("12"),15, BigDecimal.ROUND_HALF_UP)).add(lendMoney);
					//new BigDecimal(interestDays))).add(lendMoney);

		} else if (ProductCode.JDY.value.equals(productCode)) {// 季度盈
			// 出借金额*年化收益率/12*出借期限（3个月，保留百分比3位小数）+出借金额
			backMoney = ((((lendMoney.multiply(rate)).divide(new BigDecimal("100"),15, BigDecimal.ROUND_HALF_UP))
					.multiply(new BigDecimal(lendDate))).divide(new BigDecimal("12"),15, BigDecimal.ROUND_HALF_UP))
					.add(lendMoney);
		} else if (ProductCode.NNY.value.equals(productCode)) {// 年年盈
			// 出借金额*11%+出借金额
			backMoney = ((lendMoney.multiply(new BigDecimal("11"))).divide(
					new BigDecimal("100"),15, BigDecimal.ROUND_HALF_UP)).add(lendMoney);
		} else if (ProductCode.SJY.value.equals(productCode)) {// 双季盈
			// 出借金额*年化收益率/12*出借期限（6个月，保留百分比3位小数）+出借金额
			backMoney = ((((lendMoney.multiply(rate))
					.divide(new BigDecimal("100"),15, BigDecimal.ROUND_HALF_UP)).multiply(new BigDecimal(lendDate)))
					.divide(new BigDecimal("12"),15, BigDecimal.ROUND_HALF_UP)).add(lendMoney);
		} else if (ProductCode.YXT.value.equals(productCode)) {// 月息通
			if (benchmarkDay.after(lendDay)) {
				// 20160105前
				//首期账单日计息天数
				Date afertDate = CreditorUtils.getCreditDaybyLendday(lendDay);
				try {
					interestDays = String.valueOf(CreditorUtils.daysBetween(lendDay,afertDate)+1);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				// 首期账单日收益=出借金额*债权匹配月利率/出借日所在月天数*出借天数（计息天数）；
				//债权匹配月利率为基准债权匹配月利率
				BigDecimal firstBillDayProceeds = (((lendMoney
						.multiply(matchingRate))
						.divide(new BigDecimal(lendDays),15, BigDecimal.ROUND_HALF_UP))
						.divide(new BigDecimal("100"),15, BigDecimal.ROUND_HALF_UP))
						.multiply(new BigDecimal(interestDays));
				// 回款:回款金额=出借金额*月利率-首期账单日收益+出借金额
				backMoney = (((lendMoney.multiply(rate))
						.divide(new BigDecimal("100"),15, BigDecimal.ROUND_HALF_UP))
						.subtract(firstBillDayProceeds)).add(lendMoney);
			} else {
				if (date20160801.after(lendDay)) {
					// 20160801前
					// 回款金额=出借金额*月利率/出借日所在账单日周期天数*未回息天数+出借金额
					backMoney = ((((lendMoney.multiply(rate))
							.divide(new BigDecimal(billweekDay),15, BigDecimal.ROUND_HALF_UP))
							.divide(new BigDecimal("100"),15, BigDecimal.ROUND_HALF_UP)).multiply(
									new BigDecimal(days))).add(lendMoney);
				} else {
					// 20160801后
					// 12期回息金额=出借金额*月利率/出借日所在账单日周期天数*回息天数+11*出借金额*月利率
					interestDays = getDaysByFirstdayAndBackday(billDay, lendDay)+"";
					BigDecimal interestOf12 = lendMoney.multiply(rate)
							.divide(BigDecimal.valueOf(100),15, BigDecimal.ROUND_HALF_UP)
							.divide(new BigDecimal(billweekDay),15, BigDecimal.ROUND_HALF_UP)
							.multiply(new BigDecimal(interestDays))
							.add(lendMoney.multiply(rate)
									.divide(BigDecimal.valueOf(100),15, BigDecimal.ROUND_HALF_UP)
									.multiply(BigDecimal.valueOf(11)));
					
					// 回款金额=出借金额*加息后年化收益-12期回息金额+出借金额
					backMoney = lendMoney.multiply(matchingRate)
							.divide(BigDecimal.valueOf(100),15, BigDecimal.ROUND_HALF_UP)
							.subtract(interestOf12)
							.add(lendMoney);
				}
			}
		} else if (ProductCode.XHT.value.equals(productCode)) {// 信和通
			if (benchmarkDay.after(lendDay)) {
				// 回款金额=出借金额*12.68%+出借金额
				backMoney = ((lendMoney.multiply(new BigDecimal("12.68")))
						.divide(new BigDecimal("100"),15, BigDecimal.ROUND_HALF_UP))
						.add(lendMoney);
			} else {
				// 回款金额=出借金额*年化收益率+出借金额
				backMoney = ((lendMoney.multiply(rate))
						.divide(new BigDecimal("100"),15, BigDecimal.ROUND_HALF_UP)).add(lendMoney);
			}
		} else if (ProductCode.XHB.value.equals(productCode)) {// 信和宝
			if (XinhebaoType.XHB12.value.equals(backType)) {// 12个月返息
				// 信和宝（12个月返息）：返息=出借金额*年化收益率；回款金额=出借金额*年化收益率+出借金
				backMoney = ((lendMoney.multiply(new BigDecimal("14")))
						.divide(new BigDecimal("100"),15, BigDecimal.ROUND_HALF_UP)).add(lendMoney);
			} else if (XinhebaoType.XHB24.value.equals(backType)) {
				// 24个月返息 回款金额=出借金额*年化收益*出借期限（2年）+出借金额
				backMoney = ((lendMoney.multiply(new BigDecimal("29.78")))
						.divide(new BigDecimal("100"),15, BigDecimal.ROUND_HALF_UP)).add(lendMoney);
			}
		} else if (ProductCode.NNJ.value.equals(productCode)) {// 年年金
			if (benchmarkDay.after(lendDay)) {
				rate=new BigDecimal("8");
				// 出借金额*8%+出借金额
				backMoney = ((lendMoney.multiply(rate))
						.divide(new BigDecimal("100"),15, BigDecimal.ROUND_HALF_UP)).add(lendMoney);
			} else {
				// 出借金额*7%+出借金额
				rate = BigDecimal.valueOf(7);
				backMoney = ((lendMoney.multiply(rate))
						.divide(new BigDecimal("100"),15, BigDecimal.ROUND_HALF_UP)).add(lendMoney);
			}
		} else if (ProductCode.JXY.value.equals(productCode)) {// 金信盈
			rate=new BigDecimal("11");
			// 出借金额*11%+出借金额
			backMoney = ((lendMoney.multiply(rate))
					.divide(new BigDecimal("100"),15, BigDecimal.ROUND_HALF_UP)).add(lendMoney);
		} else if (ProductCode.JXB.value.equals(productCode)) {// 金信宝
			rate=new BigDecimal("28");
			// 出借金额*28%+出借金额
			backMoney = ((lendMoney.multiply(rate))
					.divide(new BigDecimal("100"),15, BigDecimal.ROUND_HALF_UP)).add(lendMoney);
		} else if (ProductCode.XHBA.value.equals(productCode)) {// 信和宝A
			// 回款金额=出借金额*14% + 出借金额
			rate=new BigDecimal("14");
			backMoney = ((lendMoney.multiply(rate))
					.divide(new BigDecimal("100"),15, BigDecimal.ROUND_HALF_UP)).add(lendMoney);
		} else if (ProductCode.XHBB.value.equals(productCode)) {// 信和宝B
			// 出借金额*29.78%
			rate=new BigDecimal("29.78");
			backMoney = (lendMoney.multiply(rate.divide(
					new BigDecimal("100"),15, BigDecimal.ROUND_HALF_UP))).add(lendMoney);
		} else if (ProductCode.XHBC.value.equals(productCode)) {// 信和宝C
			// 回款金额=出借金额*13.6%/2+出借金额
			rate= BigDecimal.valueOf(13.6);
			backMoney = ((lendMoney.multiply(rate))
					.divide(new BigDecimal("200"),15, BigDecimal.ROUND_HALF_UP)).add(lendMoney);
		} else if (ProductCode.XHYZ.value.equals(productCode)) {// 信和月增
			if (date20160801.after(lendDay)) {
				// 20160801前
				// 回款金额=出借金额*月利率/出借日所在账单日周期天数*未回息天数+出借金额
				backMoney = (((lendMoney.multiply(rate))
						.divide(new BigDecimal(billweekDay), 15, BigDecimal.ROUND_HALF_UP))
						.divide(new BigDecimal("100"), 15, BigDecimal.ROUND_HALF_UP))
						.multiply(new BigDecimal(days)).add(lendMoney);
			} else {
				// 20160801后
				// 24期回息金额=出借金额*月利率/出借日所在账单日周期天数*回息天数+23*出借金额*月利率
				interestDays = getDaysByFirstdayAndBackday(billDay, lendDay)+"";
				BigDecimal interestOf24 = lendMoney
						.multiply(rate)
						.divide(BigDecimal.valueOf(100),15, BigDecimal.ROUND_HALF_UP)
						.divide(new BigDecimal(billweekDay),15, BigDecimal.ROUND_HALF_UP)
						.multiply(new BigDecimal(interestDays))
						.add(lendMoney.multiply(BigDecimal.valueOf(23))
								.multiply(rate)
								.divide(BigDecimal.valueOf(100),15, BigDecimal.ROUND_HALF_UP));
				
				// 回款金额=出借金额*加息后年化收益率*2-24期回息金额+出借金额
				backMoney = lendMoney.multiply(matchingRate)
						.divide(BigDecimal.valueOf(100),15, BigDecimal.ROUND_HALF_UP)
						.multiply(BigDecimal.valueOf(2))
						.subtract(interestOf24).add(lendMoney);
			}
		} else if (ProductCode.XHNJ.value.equals(productCode)) {// 信和年聚
			// 出借金额*年化收益率*2+出借金额
			backMoney = (((lendMoney.multiply(rate))
					.multiply(new BigDecimal("2")))
					.divide(new BigDecimal("100"),15, BigDecimal.ROUND_HALF_UP)).add(lendMoney);
		}
		return backMoney;
	}

	/**
	 * 根据账单日、到期日获取未回息天数
	 * 2016年2月15日
	 * By 赵红江
	 * @return days
	 */
	public static String getDaysByLastdayAndBackday(int billDay,
			Date maturityDay) {
		// 到期日
		Date lastBillday =RedeemUtils.getLastBillDay(maturityDay, billDay);
		// 未回息天数=到期日-最后账单日期
		String days = "0";
		if (maturityDay != null && lastBillday != null) {
			days = RedeemUtils.getDays(lastBillday, maturityDay)+"";// 未回息天数
		}
		return days;
	}
	
	/**
	 * 根据账单日、到期日获取回息天数，回息天数为出借日至首个账单日之间的天数
	 * 2016年12月26日
	 * By 陈广鹏
	 * @param billDay 账单日
	 * @param applyLendDay 出借日期
	 * @return
	 */
	public static int getDaysByFirstdayAndBackday(int billDay, Date applyLendDay) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(applyLendDay);
		Calendar cycleEnd = Calendar.getInstance();
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
		
		long a = cycleEnd.getTimeInMillis() - cal.getTimeInMillis();
		return (int) (a / 1000 / 3600 / 24) + 1;
	}
	
	//根据本期出借日期和第一个还款日和借款期数获取出借剩余期数
	public static int getMonthSpace(Date curretbillDay, Date firstbackmoneyDay, int months)
			throws ParseException {
		int result = 0;
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.setTime(curretbillDay);
		c2.setTime(firstbackmoneyDay);
		int nowEndday = c1.get(Calendar.DATE);
		int lastBackday = c2.get(Calendar.DATE);
		if(nowEndday >= lastBackday){
			c1.add(Calendar.MONTH, 1);
		}
		result = (c1.get(Calendar.YEAR) - c2.get(Calendar.YEAR))*12 + c1.get(Calendar.MONTH) - c2.get(Calendar.MONTH);
		return  months-Math.abs(result);
	}
	
	/*public static void main(String[] args) throws ParseException {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//小写的mm表示的是分钟  
		Date lendDay=sdf.parse("2016-07-26"); 
		Date lastBackday=sdf.parse("2014-12-23"); 		
		int aa =getMonthSpace(lendDay,lastBackday,24);
		Date lendDay1=sdf.parse("2016-07-26"); 
		Date lastBackday1=sdf.parse("2015-01-23"); 		
		int aa1 =getMonthSpace(lendDay1,lastBackday1,24);
		Date lendDay2=sdf.parse("2016-07-26"); 
		Date lastBackday2=sdf.parse("2015-02-23"); 		
		int aa2 =getMonthSpace(lendDay2,lastBackday2,24);
		Date lendDay3=sdf.parse("2016-07-15"); 
		Date lastBackday3=sdf.parse("2015-07-11"); 		
		int aa3 =getMonthSpace(lendDay3,lastBackday3,24);
		
	}*/
}
