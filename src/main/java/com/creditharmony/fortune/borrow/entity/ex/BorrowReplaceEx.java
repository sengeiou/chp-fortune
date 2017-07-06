package com.creditharmony.fortune.borrow.entity.ex;

import java.math.BigDecimal;
import java.util.Date;

import com.creditharmony.fortune.borrow.entity.Borrow;
import com.creditharmony.fortune.utils.FormatUtils;

public class BorrowReplaceEx extends Borrow{
	
	/**
	 * long serialVersionUID 
	 */
	private static final long serialVersionUID = 1L;
	
	private Date phaseBegindayCur; // 本期起始时间
	
	private Date phaseEnddayCur; // 本期结束时间
	
	private Integer phaseNumber; // 当前期数
	
	private String matchingFirstdayFlag; // 首期非首期标识  
	
	private BigDecimal phasePrincipalSurplus; // 本期还款结束后剩余未还本金

	private BigDecimal creditLines;// 推荐金额
	
	private String creditLinesString;
	
	private String loanBackmoneyFirdayString;
	
	private String loanBackmoneyLastdayString;
	
	private String loanQuotaString;
	
	private String loanCreditValueString;
	
	private String loanMonthRateString;

	private String matchingId;
	
	public Date getPhaseBegindayCur() {
		return phaseBegindayCur;
	}
	public void setPhaseBegindayCur(Date phaseBegindayCur) {
		this.phaseBegindayCur = phaseBegindayCur;
	}
	public Date getPhaseEnddayCur() {
		return phaseEnddayCur;
	}
	public void setPhaseEnddayCur(Date phaseEnddayCur) {
		this.phaseEnddayCur = phaseEnddayCur;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Integer getPhaseNumber() {
		return phaseNumber;
	}
	public void setPhaseNumber(Integer phaseNumber) {
		this.phaseNumber = phaseNumber;
	}
	public String getMatchingFirstdayFlag() {
		return matchingFirstdayFlag;
	}
	public void setMatchingFirstdayFlag(String matchingFirstdayFlag) {
		this.matchingFirstdayFlag = matchingFirstdayFlag;
	}
	public BigDecimal getPhasePrincipalSurplus() {
		return phasePrincipalSurplus;
	}
	public void setPhasePrincipalSurplus(BigDecimal phasePrincipalSurplus) {
		this.phasePrincipalSurplus = phasePrincipalSurplus;
	}
	public BigDecimal getCreditLines() {
		return creditLines; 
	}
	public void setCreditLines(BigDecimal creditLines) {
		this.creditLines = creditLines;
	}
	public String getLoanBackmoneyLastdayString() {
		return loanBackmoneyLastdayString;
	}
	public void setLoanBackmoneyLastdayString(String loanBackmoneyLastdayString) {
		this.loanBackmoneyLastdayString = loanBackmoneyLastdayString;
	}
	public String getLoanBackmoneyFirdayString() {
		return loanBackmoneyFirdayString;
	}
	public void setLoanBackmoneyFirdayString(String loanBackmoneyFirdayString) {
		this.loanBackmoneyFirdayString = loanBackmoneyFirdayString;
	}
	public String getMatchingId() {
		return matchingId;
	}
	public void setMatchingId(String matchingId) {
		this.matchingId = matchingId;
	}
	public String getLoanQuotaString() {
		return loanQuotaString;
	}
	public void setLoanQuotaString(String loanQuotaString) {
		this.loanQuotaString = loanQuotaString;
	}
	public String getLoanCreditValueString() {
		return loanCreditValueString;
	}
	public void setLoanCreditValueString(String loanCreditValueString) {
		this.loanCreditValueString = loanCreditValueString;
	}
	public String getLoanMonthRateString() {
		return loanMonthRateString;
	}
	public void setLoanMonthRateString(String loanMonthRateString) {
		this.loanMonthRateString = loanMonthRateString;
	}
	public String getCreditLinesString() {
		return creditLinesString;
		
	}
	public void setCreditLinesString(String creditLinesString) {
		this.creditLinesString = creditLinesString;
	}
	
}
