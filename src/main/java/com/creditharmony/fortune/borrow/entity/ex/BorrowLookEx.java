package com.creditharmony.fortune.borrow.entity.ex;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 查看可用债权封装
 * @Class Name BorrowLookView
 * @author 周俊
 * @Create In 2015年12月2日
 */
public class BorrowLookEx {
	private String lendCode;// 出借编号
	
	private String loanCode;// 借款code
	
	private String customerName;// 出借人姓名
	
	private String customerTrue;// 出借人编号
	
	private Date initLendDate;// 初始出借日期
	
	private Date matchingInterestStart; //匹配时出借日
	
	private BigDecimal tradeMateMoney;// 匹配金额
	
	private BigDecimal phasePrincipalSurplus;// 剩余资金
	
	private BigDecimal monthCapitalPayactual;// 月还本金 
	
	private BigDecimal monthInterest;// 月还利息
	
	private BigDecimal firstMonthInterest;// 首期月还利息
	
	private Integer initMonths;// 初始期数
	
	private Integer surplusMonths;// 剩余期数
	
	private Integer phaseNumber;// 当前期数
	
	private BigDecimal productRate;// 产品利率
	
	private BigDecimal phaseBackPrincipal;// 已还本金


	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerTrue() {
		return customerTrue;
	}

	public void setCustomerTrue(String customerTrue) {
		this.customerTrue = customerTrue;
	}

	public Date getInitLendDate() {
		return initLendDate;
	}

	public void setInitLendDate(Date initLendDate) {
		this.initLendDate = initLendDate;
	}

	public BigDecimal getTradeMateMoney() {
		return tradeMateMoney;
	}

	public void setTradeMateMoney(BigDecimal tradeMateMoney) {
		this.tradeMateMoney = tradeMateMoney;
	}


	public BigDecimal getMonthCapitalPayactual() {
		return monthCapitalPayactual;
	}

	public void setMonthCapitalPayactual(BigDecimal monthCapitalPayactual) {
		this.monthCapitalPayactual = monthCapitalPayactual;
	}

	public BigDecimal getMonthInterest() {
		return monthInterest;
	}

	public void setMonthInterest(BigDecimal monthInterest) {
		this.monthInterest = monthInterest;
	}

	public BigDecimal getFirstMonthInterest() {
		return firstMonthInterest;
	}

	public void setFirstMonthInterest(BigDecimal firstMonthInterest) {
		this.firstMonthInterest = firstMonthInterest;
	}

	public Integer getInitMonths() {
		return initMonths;
	}

	public void setInitMonths(Integer initMonths) {
		this.initMonths = initMonths;
	}

	public Integer getSurplusMonths() {
		return surplusMonths;
	}

	public void setSurplusMonths(Integer surplusMonths) {
		this.surplusMonths = surplusMonths;
	}

	public BigDecimal getProductRate() {
		return productRate;
	}

	public void setProductRate(BigDecimal productRate) {
		this.productRate = productRate;
	}

	public Integer getPhaseNumber() {
		return phaseNumber;
	}

	public void setPhaseNumber(Integer phaseNumber) {
		this.phaseNumber = phaseNumber;
	}

	public BigDecimal getPhaseBackPrincipal() {
		return phaseBackPrincipal;
	}

	public void setPhaseBackPrincipal(BigDecimal phaseBackPrincipal) {
		this.phaseBackPrincipal = phaseBackPrincipal;
	}

	public String getLendCode() {
		return lendCode;
	}

	public void setLendCode(String lendCode) {
		this.lendCode = lendCode;
	}

	public String getLoanCode() {
		return loanCode;
	}

	public void setLoanCode(String loanCode) {
		this.loanCode = loanCode;
	}

	public BigDecimal getPhasePrincipalSurplus() {
		return phasePrincipalSurplus;
	}

	public void setPhasePrincipalSurplus(BigDecimal phasePrincipalSurplus) {
		this.phasePrincipalSurplus = phasePrincipalSurplus;
	}

	public Date getMatchingInterestStart() {
		return matchingInterestStart;
	}

	public void setMatchingInterestStart(Date matchingInterestStart) {
		this.matchingInterestStart = matchingInterestStart;
	}
	
}
