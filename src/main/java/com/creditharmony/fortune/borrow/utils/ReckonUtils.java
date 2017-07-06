package com.creditharmony.fortune.borrow.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;

import com.creditharmony.common.util.BigDecimalTools;
import com.creditharmony.common.util.DateUtils;
import com.creditharmony.common.util.ObjectHelper;
import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.exception.ServiceException;
import com.creditharmony.core.fortune.type.RepayDay;
import com.creditharmony.fortune.borrow.entity.Borrow;
import com.creditharmony.fortune.borrow.entity.BorrowMonth;
import com.creditharmony.fortune.borrow.entity.BorrowMonthable;
import com.creditharmony.fortune.borrow.entity.CreditorTrade;
import com.creditharmony.fortune.borrow.entity.Loanphase;
import com.creditharmony.fortune.borrow.entity.ex.BorrowMonthSplitHisEx;
import com.creditharmony.fortune.borrow.entity.ex.BorrowReplaceEx;
import com.creditharmony.fortune.borrow.view.BorrowMonthSplitView;
import com.creditharmony.fortune.borrow.view.BorrowMonthableBackToolView;
import com.creditharmony.fortune.constants.BorrowConstant;
import com.creditharmony.fortune.creditor.matching.entity.ext.MatchingCreditorEx;


/**
 * 债权模块的计算
 * @Class Name ReckonUtils
 * @author 周俊
 * @Create In 2015年12月4日
 */
public class ReckonUtils {
	
	/**
	 * 获取认拆分信息
	 * 2015年12月5日
	 * By 周俊
	 * @param borrowMonth1
	 * @return
	 */
	public static BorrowMonthSplitView splitBorrowMonthReckon(BorrowMonth borrowMonth ,BigDecimal splitRateParam,BorrowMonthSplitView borrowMonthSplitViewParam){
		BorrowMonthSplitView borrowMonthSplitView = new BorrowMonthSplitView();
		// 获取月利率
		BigDecimal monthRate = borrowMonth.getLoanMonthRate();
		// 可用金额
		BigDecimal borrowAvailabeValue = borrowMonth.getLoanAvailabeValue();
		// 初始拆分数据
		if (ObjectHelper.isEmpty(borrowMonthSplitViewParam)) {
			// 拆分金额  拆分金额 * 拆分利率 = 可用金额 * 月利率
			BigDecimal splitMoney = borrowAvailabeValue.multiply(monthRate).divide(splitRateParam, 5, RoundingMode.HALF_DOWN);
			borrowMonthSplitView.setSplitMoney(splitMoney);
			borrowMonthSplitView.setCreditMonId(borrowMonth.getCreditMonId());
			borrowMonthSplitView.setSplitRate(splitRateParam);
			borrowMonthSplitView.setInitSplitMoney(borrowAvailabeValue);
			borrowMonthSplitView.setSurplusSplitMoney(borrowAvailabeValue .subtract(borrowAvailabeValue));
		}else {
			BigDecimal splitRate = borrowMonthSplitViewParam.getSplitRate();//拆分利率
			BigDecimal surplusSplitMoney = borrowMonthSplitViewParam.getSurplusSplitMoney();// 拆分后可用金额
			BigDecimal splitMoney = borrowMonthSplitViewParam.getSplitMoney();// 拆分金额
			BigDecimal initSplitMoney = borrowMonthSplitViewParam.getInitSplitMoney();// 原拆分金额
			// 不存在拆分金额,存在拆分后可用金额和拆分利率,计算
			if (ObjectHelper.isEmpty(splitMoney)) {
				// 判断拆分利率是否大于月利率
				if (splitRate.compareTo(monthRate) == BorrowConstant.FIRST) {
					splitRate = monthRate;
				}
				// 原拆分金额
				initSplitMoney = borrowAvailabeValue.subtract(surplusSplitMoney);
				// 拆分金额 = 原拆分金额 *月利率/拆分利率
				splitMoney = initSplitMoney.multiply(monthRate).divide(splitRate, 5, RoundingMode.HALF_DOWN);
				borrowMonthSplitView.setSplitRate(splitRate);
				borrowMonthSplitView.setSurplusSplitMoney(surplusSplitMoney);
				borrowMonthSplitView.setSplitMoney(splitMoney);
				borrowMonthSplitView.setInitSplitMoney(initSplitMoney);
			}
			// 不存在拆分利率,存在拆分后可用金额,拆分金额
			else if (ObjectHelper.isEmpty(splitRate)) {
				//拆分后可用金额 = 可用金额 – 原拆分金额-------原拆分金额
				initSplitMoney = borrowAvailabeValue.subtract(surplusSplitMoney);
				// 拆分金额 * 拆分利率 = 原拆分金额 * 月利率
				splitRate = initSplitMoney.multiply(monthRate).divide(splitMoney, 5, RoundingMode.HALF_DOWN);
				if (splitRate.compareTo(monthRate) == BorrowConstant.FIRST) {
					throw new ServiceException("拆分利率不能大于月利率");
				}
				borrowMonthSplitView.setSplitRate(splitRate);
				borrowMonthSplitView.setSurplusSplitMoney(surplusSplitMoney);
				borrowMonthSplitView.setSplitMoney(splitMoney);
				borrowMonthSplitView.setInitSplitMoney(initSplitMoney);
			}
			// 不存在拆分后可用金额,存在拆分利率和拆分金额
			else if (ObjectHelper.isEmpty(surplusSplitMoney)) {
				if (splitMoney.compareTo(BigDecimal.ZERO) == 0) {
					// 原拆分金额 为0
					initSplitMoney = new BigDecimal(0);
				}else {
					// 原拆分金额 = 拆分金额 * 拆分利率 / 月利率
					initSplitMoney = splitMoney.multiply(splitRate).divide(monthRate,5,RoundingMode.HALF_DOWN);
				}
				surplusSplitMoney = borrowAvailabeValue.subtract(initSplitMoney);
				if (surplusSplitMoney.compareTo(BigDecimal.ZERO) == -1) {
					surplusSplitMoney = new BigDecimal(0);
					// 计算拆分金额
					splitMoney = (borrowAvailabeValue.subtract(new BigDecimal(0))).multiply(monthRate).divide(splitRate, 5, RoundingMode.HALF_DOWN);
				}
				borrowMonthSplitView.setSplitRate(splitRate);
				borrowMonthSplitView.setSurplusSplitMoney(surplusSplitMoney);
				borrowMonthSplitView.setSplitMoney(splitMoney);
				borrowMonthSplitView.setInitSplitMoney(initSplitMoney);
			}else {
				// 原拆分金额*月利率  = 拆分金额*拆分利率
				if (initSplitMoney.multiply(monthRate).compareTo(splitMoney.multiply(splitRate)) != (Integer)BorrowConstant.ZERO) {
					throw new ServiceException("你的操做有误");
				}
			}
			
		}
		borrowMonthSplitView.setLoanAvailabeValue(borrowAvailabeValue);
		return borrowMonthSplitView;
	}
	
	/**
	 * 获取拆分利率
	 * 2015年12月7日
	 * By 周俊
	 * @param splitHis
	 * @return
	 */
	public static BigDecimal getSplitRate(BorrowMonthSplitHisEx splitHis){
		if(ObjectHelper.isEmpty(splitHis.getOperateMoney())){
			return null;
		}
		// 原拆分金额 = 拆分金额 * 拆分利率 / 月利率
		// 拆分后可用金额 = 可用金额 – 原拆分金额
		// 原拆分金额
		BigDecimal initSplitMoney = splitHis.getBeforMoney().subtract(splitHis.getAfterMoney());
		
		BigDecimal splitRate = initSplitMoney.multiply(splitHis.getBorrowMonthRate()).divide(splitHis.getOperateMoney(), 5, RoundingMode.HALF_UP);
		
		return splitRate;
	}
	
	/**
	 * 计算释放后可用金额
	 * 2015年12月7日
	 * By 周俊
	 * @return
	 */
	public static BigDecimal getAfterReleaseBorrow(BorrowMonthableBackToolView toolView,BorrowMonth borrowMonth,BorrowMonthable borrowMonthable){
		// 释放前金额 ＊ 释放前利率 = 释放后金额 * 释放后利率
		// 获得释放前金额 
		   BigDecimal beforeReleaseBorrow = null;
		// 获得释放前利率
		   BigDecimal beforeReleaseBorrowRate = null;
		// 释放后利率
		   BigDecimal afterReleaseBorrowRate = null;
		
		if (ObjectHelper.isEmpty(toolView) && ObjectHelper.isEmpty(borrowMonthable) && !ObjectHelper.isEmpty(borrowMonth)) {
			return null;
		}
		if (!ObjectHelper.isEmpty(toolView)) {
			// 获得释放前金额 
			beforeReleaseBorrow = toolView.getBeforeReleaseBorrow();
			// 获得释放前利率
			beforeReleaseBorrowRate = toolView.getBeforeReleaseBorrowRate();
			// 获得释放后利率
			afterReleaseBorrowRate = toolView.getAfterReleaseBorrowRate();
		}else if (!ObjectHelper.isEmpty(borrowMonthable) && !ObjectHelper.isEmpty(borrowMonth)) {
			// 获得释放前金额
			beforeReleaseBorrow = borrowMonthable.getLoanAvailabeValue();
			// 获得释放前利率
			beforeReleaseBorrowRate = borrowMonthable.getLoanMonthRate();
			// 获得释放后利率
			afterReleaseBorrowRate = borrowMonth.getLoanMonthRate();
		}
		// 获得释放后金额
		BigDecimal afterReleaseBorrow = beforeReleaseBorrow.multiply(beforeReleaseBorrowRate).divide(afterReleaseBorrowRate,5,RoundingMode.HALF_DOWN);
		return afterReleaseBorrow;
	}
	
	/**
	 * 百分比
	 * 2015年12月24日
	 * By 周俊
	 * @param money1 小
	 * @param money2 大
	 * @return
	 */
	public static String percentage(BigDecimal money1,BigDecimal money2){
		if (money1.compareTo(new BigDecimal(0))==0||money2.compareTo(new BigDecimal(0))==0) {
			return BorrowConstant.PERCENTAGE;
		}
		BigDecimal percentage = money1.divide(money2, 15, RoundingMode.HALF_UP);
		return String.valueOf(percentage);
	}
	
	/**
	 * 获得本期需推荐金额
	 * 2015年12月15日
	 * By 周俊
	 * @param borrowReplaceEx
	 * @return
	 */
	public static Integer flagBoolean(BorrowReplaceEx borrowReplaceEx,Date matchingExpireDay){
		// 冻结时间
		Date loanFreezeDay = borrowReplaceEx.getLoanFreezeDay();
		// 本期账单开始时间
		MatchingCreditorEx matchingCreditorEx = new MatchingCreditorEx();
		matchingCreditorEx.setMatchingExpireDay(matchingExpireDay);
		Date loanDate = getLoanDate(matchingCreditorEx);
		// 本期匹配日期
		Date matchingDate = getDateAfter(loanDate, 15);
		// 上期账单结算日
		Date upMatchingExpireDay = getDateAfter(loanDate, -1);
		Date upCloseDate = getDateAfter(upMatchingExpireDay, -15);
		// 本期账单结算日期
		Date closeDate = getDateAfter(matchingExpireDay, -15);
		
		if (DateUtils.dateBefore(loanFreezeDay, loanDate) && borrowReplaceEx.getPhaseNumber() == BorrowConstant.FIRST) {
			return BorrowConstant.FIRST;
		}
		if (DateUtils.dateAfter(loanFreezeDay, loanDate) && DateUtils.dateBefore(loanFreezeDay,closeDate)) {
			return BorrowConstant.NEGATIVE_ONE;
		}if (DateUtils.dateBefore(loanFreezeDay, loanDate) && DateUtils.dateAfter(loanFreezeDay,matchingDate) && borrowReplaceEx.getPhaseNumber() != BorrowConstant.FIRST) {
			return (Integer) BorrowConstant.ZERO;
		}
		return null;
	}
	
	/**
	 * 根据天数和时间转换为时间
	 * 2015年12月25日
	 * By 周俊
	 * @param date
	 * @param num
	 * @return
	 */

	public static Date getDateAfter(Date date,int day){  
		   Calendar now =Calendar.getInstance();  
		   now.setTime(date);  
		   now.set(Calendar.DATE,now.get(Calendar.DATE)+day);  
		   return now.getTime();  
		}  

	/**
	 * 计算首期月还利息,月还本金,月还本息,当前期数
	 * 2015年12月18日
	 * By 周俊
	 * @param creditLines
	 * @param borrow
	 * @return
	 */
	public	static Loanphase getLoanphaseToFirst(Borrow borrow,MatchingCreditorEx matchingCreditorEx,CreditorTrade creditorTrade,Loanphase loanphase){
		// 获得本期到期日期
		Date matchingExpireDay = matchingCreditorEx.getMatchingExpireDay();
		// 本期开始时间
		Date matchingInterestStart = matchingCreditorEx.getMatchingInterestStart();
		// 本期实际出借天数
		double realityDays = DateUtils.getDistanceOfTwoDate(matchingInterestStart,matchingExpireDay);
		// 获得出借日所在月的天数
		String month = DateUtils.formatDate(matchingInterestStart, "MM");
		String year = DateUtils.formatDate(matchingInterestStart, "yyyy");
		double monthDay = getDaysByYearMonth(year, month);
		// 匹配金额
		BigDecimal tradeMateMoney = creditorTrade.getTradeMateMoney();
		// 计算首期月还利息
		// 首期月还利息
	    BigDecimal phaseInterestCur = ((tradeMateMoney.multiply(borrow.getLoanMonthRate().divide(new BigDecimal(100),5, RoundingMode.HALF_DOWN))).
			  					    divide(new BigDecimal(monthDay), 5, RoundingMode.HALF_DOWN)).
			  					    multiply(new BigDecimal(realityDays));
	   loanphase.setPhaseInterestCur(phaseInterestCur);
		
		// 月还本金
		BigDecimal phasePrincipalCur = tradeMateMoney.divide(new BigDecimal(loanphase.getPhaseNumberSurplus()+1), 5, RoundingMode.HALF_DOWN);
		loanphase.setPhasePrincipalCur(phasePrincipalCur);
		// 本期还款结束后，剩余未还本金
		BigDecimal phasePrincipalSurplus = tradeMateMoney.subtract(phasePrincipalCur);
		// 月还本息
		BigDecimal phaseAmount = BigDecimalTools.add(phasePrincipalCur, phaseInterestCur);
		loanphase.setPhaseAmount(phaseAmount);
		loanphase.setPhasePrincipalSurplus(phasePrincipalSurplus);
		// 截止到本期已还本息 
		loanphase.setPhaseBackCount(phaseAmount);
		// 截止到本期已还本金 
		loanphase.setPhaseBackPrincipal(phasePrincipalCur);
		// 截止到本期已还利息 
		loanphase.setPhaseBackInterest(phaseInterestCur);	
		return loanphase;
	}
	
	/**
	 * 计算非首期月还利息,月还本金,月还本息,期数
	 * 2015年12月18日
	 * By 周俊
	 * @param creditLines
	 * @param borrow
	 * @return
	 */
	public static Loanphase getLoanphaseNotFirst(MatchingCreditorEx matchingCreditorEx,Borrow borrow,CreditorTrade creditorTrade, Loanphase loanphase){
				// 匹配金额
				BigDecimal tradeMateMoney = creditorTrade.getTradeMateMoney();
				// 月还本金
				BigDecimal phasePrincipalCur = tradeMateMoney.divide(new BigDecimal(loanphase.getPhaseNumberSurplus()+1), 5, RoundingMode.HALF_DOWN);
				loanphase.setPhasePrincipalCur(phasePrincipalCur);
				// 月还利息
				//BigDecimal phaseInterestCur = BigDecimalTools.div((tradeMateMoney.add(phasePrincipalCur)).multiply(borrow.getLoanMonthRate()),BigDecimal.valueOf(100),5);
				BigDecimal phaseInterestCur = BigDecimalTools.div(tradeMateMoney.multiply(borrow.getLoanMonthRate()),BigDecimal.valueOf(100),5);
				loanphase.setPhaseInterestCur(phaseInterestCur);
				// 月还本息
				BigDecimal phaseAmount = BigDecimalTools.add(phasePrincipalCur, phaseInterestCur);
				loanphase.setPhaseAmount(phaseAmount);
				// 本期还款结束后，剩余未还本金
				BigDecimal phasePrincipalSurplus = tradeMateMoney.subtract(phasePrincipalCur);
				loanphase.setPhasePrincipalSurplus(phasePrincipalSurplus);
				// 截止到本期已还本息 
				loanphase.setPhaseBackCount(phaseAmount);
				// 截止到本期已还本金 
				loanphase.setPhaseBackPrincipal(phasePrincipalCur);
				// 截止到本期已还利息 
				loanphase.setPhaseBackInterest(phaseInterestCur);			
				return loanphase;
	}
	
	/**
	 * 计算出借日期
	 * 2015年12月23日
	 * By 周俊
	 * @param matchingCreditor
	 * @return
	 */
	public static Date getLoanDate(MatchingCreditorEx matchingCreditor){
		Date matchingExpireDay = matchingCreditor.getMatchingExpireDay();
		// 下期出借日期
		Date nextLoanDate = new Date(matchingExpireDay.getTime()+(24*3600*1000));
		// 本期出借日期
		String month = DateUtils.formatDate(nextLoanDate,"MM");
		String year = DateUtils.formatDate(nextLoanDate,"yyyy");
		String date = DateUtils.formatDate(nextLoanDate,"dd");
		// 本期出借日期
		Date loanDate = DateUtils.parseDate(year+"-"+(Long.valueOf(month)-1)+"-"+date);
		return loanDate;
	}
	
	/**
	 * 计算债权可用日期
	 * 2015年12月2日
	 * By 周俊
	 * @param borrowBackmoneyFirday
	 * @return
	 */
	public	static Date getBorrowCreditDateUsable(Date borrowBackmoneyFirday){
		if (borrowBackmoneyFirday==null) {
			return new Date();
		}
		// 债权可用日期 = 首次还款日期 – 1个月 + 1
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(borrowBackmoneyFirday);
		calendar.add(Calendar.MONTH, BorrowConstant.NEGATIVE_ONE);
		calendar.add(Calendar.DAY_OF_MONTH,BorrowConstant.FIRST);
		Date date = calendar.getTime();
		return date;
	}
	
	/**
	 * 根据年 月 获取对应的月份 天数 
	 * 2016年1月12日
	 * By 周俊
	 * @param year
	 * @param month
	 * @return
	 */
    public static int getDaysByYearMonth(String year, String month) {  
        Calendar a = Calendar.getInstance();  
        a.set(Calendar.YEAR, StringUtils.toInteger(year));  
        a.set(Calendar.MONTH, StringUtils.toInteger(month) - 1);  
        a.set(Calendar.DATE, 1);  
        a.roll(Calendar.DATE, -1);  
        int maxDate = a.get(Calendar.DATE);  
        return maxDate;  
    }  
    
    /**
	 * 字符串转BigDecimal计算
	 * 2016年4月13日
	 * By 周俊
	 * @param main
	 * @param sign
	 * @param sub
	 * @return
	 */
	public static String reckonByBigDecimal(String main,String sign,String sub){
		BigDecimal valueMain = BigDecimal.valueOf(Double.valueOf(main));
		BigDecimal valueSub = BigDecimal.valueOf(Double.valueOf(sub));
		BigDecimal result = null;
		if (sign.equals("ADD")) {
			result = valueMain.add(valueSub);
		}else if (sign.equals("SUBTRACT")) {
			result = valueMain.subtract(valueSub);
		}else if (sign.equals("PERCENTAGE")) {
			BigDecimal percentage = BigDecimal.valueOf(BigDecimalTools.div(sub, main,15));
			return com.creditharmony.fortune.utils.FormatUtils.getFormatNumber(percentage,BorrowConstant.PERCENTAGE);
		}
		
		return FormatUtils.formatNumber(String.valueOf(result));
	}
	
	/**
	 * 计算债权可用期数
	 * 2016年4月19日
	 * By 周俊
	 * @param loanBackmoneyFirday
	 * @param loanMonths
	 * @return
	 */
	public static Integer reckonLoanMonthsSurplus(Date loanBackmoneyFirday,Integer loanMonths,String loanBackmoneyDay){
		if(DateUtils.dateBefore(new Date(),loanBackmoneyFirday)){ // 当前日期小于首次还款日
			return loanMonths-1;
		}
		// 当前日期大于首次还款日 
		// 获取首次结算日
		// 计算首次还款日距离当前日的期数
		Calendar c1=Calendar.getInstance();
		Calendar c2=Calendar.getInstance();
		c1.setTime(loanBackmoneyFirday);
		c2.setTime(new Date());
		int year =c2.get(Calendar.YEAR)-c1.get(Calendar.YEAR);
		int month = 0;
		if(c2.get(Calendar.DATE)>=c1.get(Calendar.DATE)){
			month = year*12+c2.get(Calendar.MONTH)-c1.get(Calendar.MONTH)+1;
		}else{
			month = year*12+c2.get(Calendar.MONTH)-c1.get(Calendar.MONTH);
		}
		// 当前天数
		Integer day = Integer.valueOf(DateUtils.getDay(new Date()));
		
		if(loanBackmoneyDay.equals(RepayDay.THREE.value)){ // 还款日为3 结算日为19
			// 当前月还款日对应的结算日
			Date closeAccountsDate = getCloseAccountsDate(-1, "19");
			// 当前月的还款日
			Date loanBackmoneyDate = getloanBackmoneyDate(0, RepayDay.THREE.value);
			// 当前月结算日
			Date nowCloseAccountsDate = getCloseAccountsDate(0, "19");
			if(DateUtils.dateAfter(new Date(), loanBackmoneyDate)&&DateUtils.dateAfter(new Date(), nowCloseAccountsDate)){
				month = month + 1;
			}
			
		}else if (loanBackmoneyDay.equals(RepayDay.SEVEN.value)) { // 还款日为7 结算日为23
			// 当前月还款日对应的结算日
			Date closeAccountsDate = getCloseAccountsDate(-1, "23");
			// 当前月的还款日
			Date loanBackmoneyDate = getloanBackmoneyDate(0, RepayDay.SEVEN.value);
			// 当前月结算日
			Date nowCloseAccountsDate = getCloseAccountsDate(0, "23");
			if(DateUtils.dateAfter(new Date(), loanBackmoneyDate)&&DateUtils.dateAfter(new Date(), nowCloseAccountsDate)){
				month = month + 1;
			}
		}else if (loanBackmoneyDay.equals(RepayDay.TEEN.value)) { // 还款日为10 结算日为26
			// 当前月还款日对应的结算日
			Date closeAccountsDate = getCloseAccountsDate(-1, "26");
			// 当前月的还款日
			Date loanBackmoneyDate = getloanBackmoneyDate(0, RepayDay.TEEN.value);
			// 当前月结算日
			Date nowCloseAccountsDate = getCloseAccountsDate(0, "26");
			if(DateUtils.dateAfter(new Date(), loanBackmoneyDate)&&DateUtils.dateAfter(new Date(), nowCloseAccountsDate)){
				month = month + 1;
			}
		}else if (loanBackmoneyDay.equals(RepayDay.FIFTEEN.value)) { // 还款日为15 结算日为30
			// 当前月还款日对应的结算日
			Date closeAccountsDate = getCloseAccountsDate(-1, "30");
			// 当前月的还款日
			Date loanBackmoneyDate = getloanBackmoneyDate(0, RepayDay.FIFTEEN.value);
			// 当前月结算日
			Date nowCloseAccountsDate = getCloseAccountsDate(0, "30");
			if(DateUtils.dateAfter(new Date(), loanBackmoneyDate)&&DateUtils.dateAfter(new Date(), nowCloseAccountsDate)){
				month = month + 1;
			}
		}else if (loanBackmoneyDay.equals(RepayDay.EIGHTEEN.value)) { // 还款日为18 结算日为4
			// 当前月还款日对应的结算日
			Date closeAccountsDate = getCloseAccountsDate(0, "4");
			// 当前月的还款日
			Date loanBackmoneyDate = getloanBackmoneyDate(0, RepayDay.EIGHTEEN.value);
			/*// 当前月结算日
			Date nowCloseAccountsDate = getCloseAccountsDate(0, "4");*/
			if(DateUtils.dateAfter(loanBackmoneyDate, new Date())&&DateUtils.dateAfter(new Date(), closeAccountsDate)){
				month = month + 1;
			}
		}else if (loanBackmoneyDay.equals(RepayDay.TWENTYTHREE.value)) { // 还款日为23 结算日为7
			// 当前月还款日对应的结算日
			Date closeAccountsDate = getCloseAccountsDate(0, "7");
			// 当前月的还款日
			Date loanBackmoneyDate = getloanBackmoneyDate(0, RepayDay.TWENTYTHREE.value);
			/*// 当前月结算日
			Date nowCloseAccountsDate = getCloseAccountsDate(0, "7");*/
			if(DateUtils.dateAfter(loanBackmoneyDate, new Date())&&DateUtils.dateAfter(new Date(), closeAccountsDate)){
				month = month + 1;
			}
		}else if (loanBackmoneyDay.equals(RepayDay.TWENTYFIVE.value)) { // 还款日为25 结算日为11
			// 当前月还款日对应的结算日
			Date closeAccountsDate = getCloseAccountsDate(0, "11");
			// 当前月的还款日
			Date loanBackmoneyDate = getloanBackmoneyDate(0, RepayDay.TWENTYFIVE.value);
			/*// 当前月结算日
			Date nowCloseAccountsDate = getCloseAccountsDate(0, "11");*/
			if(DateUtils.dateAfter(loanBackmoneyDate, new Date())&&DateUtils.dateAfter(new Date(), closeAccountsDate)){
				month = month + 1;
			}
		}else/* if (loanBackmoneyDay.equals(RepayDay.THIRTY.value)) */{ // 还款日为28,29,30 结算日为15
			// 当前月还款日对应的结算日
			Date closeAccountsDate = getCloseAccountsDate(0, "15");
			// 当前月的还款日
			Date loanBackmoneyDate = getloanBackmoneyDate(0, loanBackmoneyDay);
			/*// 当前月结算日
			Date nowCloseAccountsDate = getCloseAccountsDate(0, "15");*/
			if(DateUtils.dateAfter(loanBackmoneyDate, new Date())&&DateUtils.dateAfter(new Date(), closeAccountsDate)){
				month = month + 1;
			}
		}
		return loanMonths - month;
		
	}
	
	/**
	 * 获取当前月还款日对应的结算日
	 * 2016年4月19日
	 * By 周俊
	 * @param token
	 * @param closeAccountsDay
	 * @return
	 */
	public static Date  getCloseAccountsDate(int token,String closeAccountsDay){
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());  
		c.add(Calendar.MONTH, token);
		return DateUtils.parseDate(DateUtils.formatDate(c.getTime(),"yyyy-MM")+"-"+closeAccountsDay);  
	}
	
	/**
	 * 获取当前月的还款日
	 * 2016年4月19日
	 * By 周俊
	 * @param token
	 * @param closeAccountsDay
	 * @return
	 */
	public static Date getloanBackmoneyDate(int token,String loanBackmoneyDay){
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());  
		c.add(Calendar.MONTH, token);
		return DateUtils.parseDate(DateUtils.formatDate(c.getTime(),"yyyy-MM")+"-"+loanBackmoneyDay);  
	}
}
