package com.creditharmony.fortune.borrow.view;

import java.math.BigDecimal;
import java.util.Date;

public class BorrowCancelSearchView {

	private String customerName;
	private String applyBillday;
	private String addrCity;
	private Date lendDateFrom;
	private Date lendDateTo;
	private String matchingFirstdayFlag;
	private String procuctId;
	private String orgCode;
	private String lendCode;
	private String replaceStatus;
	private Date replaceDay;
	// 替换日期
	private Date replaceDayFrom;
	private Date replaceDayTo;
	public BorrowCancelSearchView(Date replaceDayFrom, Date replaceDayTo) {
		super();
		this.replaceDayFrom = replaceDayFrom;
		this.replaceDayTo = replaceDayTo;
	}

	public Date getReplaceDayFrom() {
		return replaceDayFrom;
	}

	public void setReplaceDayFrom(Date replaceDayFrom) {
		this.replaceDayFrom = replaceDayFrom;
	}

	public Date getReplaceDayTo() {
		return replaceDayTo;
	}

	public void setReplaceDayTo(Date replaceDayTo) {
		this.replaceDayTo = replaceDayTo;
	}

	public BorrowCancelSearchView(Date replaceDay) {
		super();
		this.replaceDay = replaceDay;
	}

	public Date getReplaceDay() {
		return replaceDay;
	}

	public void setReplaceDay(Date replaceDay) {
		this.replaceDay = replaceDay;
	}

	// 本期待替换金额
	private BigDecimal currentCreditLinesMoney;
			
	public BigDecimal getCurrentCreditLinesMoney() {
		return currentCreditLinesMoney;
	}

	public void setCurrentCreditLinesMoney(BigDecimal currentCreditLinesMoney) {
		this.currentCreditLinesMoney = currentCreditLinesMoney;
	}

	public BorrowCancelSearchView() {
		
	}

	public BorrowCancelSearchView(String customerName, String applyBillday,
			String addrCity, Date lendDateFrom, Date lendDateTo,
			String matchingFirstdayFlag, String procuctId, String orgCode,
			String lendCode, String replaceStatus) {
		super();
		this.customerName = customerName;
		this.applyBillday = applyBillday;
		this.addrCity = addrCity;
		this.lendDateFrom = lendDateFrom;
		this.lendDateTo = lendDateTo;
		this.matchingFirstdayFlag = matchingFirstdayFlag;
		this.procuctId = procuctId;
		this.orgCode = orgCode;
		this.lendCode = lendCode;
		this.replaceStatus = replaceStatus;
	}

	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getApplyBillday() {
		return applyBillday;
	}
	public void setApplyBillday(String applyBillday) {
		this.applyBillday = applyBillday;
	}
	public String getAddrCity() {
		return addrCity;
	}
	public void setAddrCity(String addrCity) {
		this.addrCity = addrCity;
	}

	public String getMatchingFirstdayFlag() {
		return matchingFirstdayFlag;
	}
	public void setMatchingFirstdayFlag(String matchingFirstdayFlag) {
		this.matchingFirstdayFlag = matchingFirstdayFlag;
	}
	public String getProcuctId() {
		return procuctId;
	}
	public void setProcuctId(String procuctId) {
		this.procuctId = procuctId;
	}
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public String getLendCode() {
		return lendCode;
	}
	public void setLendCode(String lendCode) {
		this.lendCode = lendCode;
	}
	public String getReplaceStatus() {
		return replaceStatus;
	}
	public void setReplaceStatus(String replaceStatus) {
		this.replaceStatus = replaceStatus;
	}

	public Date getLendDateFrom() {
		return lendDateFrom;
	}

	public void setLendDateFrom(Date lendDateFrom) {
		this.lendDateFrom = lendDateFrom;
	}

	public Date getLendDateTo() {
		return lendDateTo;
	}

	public void setLendDateTo(Date lendDateTo) {
		this.lendDateTo = lendDateTo;
	}

}
