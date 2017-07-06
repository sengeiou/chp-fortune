package com.creditharmony.fortune.creditor.matching.entity;

import java.math.BigDecimal;
import java.util.Date;

public class TempAutoMatchingCreditor {
    private String matchingId;

    private String lendCode;

    private String productCode;

    private String matchingFirstdayFlag;

    private Date matchingInterestStart;

    private Integer matchingBillDay;

    private BigDecimal matchingBorrowQuota;

    private String matchingStatus;

    private Date matchingEndday;

    private Integer matchingTotal;

    private Integer matchingNow;

    private Date matchingExpireDay;

    private Date matchingCossTime;

    private Date matchingMakeDay;

    private Date matchingHkDay;

    private Date matchingFirstbillDay;

    private String matchingPayStatus;

    private String createBy;

    private Date createTime;

    private String modifyBy;

    private Date modifyTime;

    private String customerCode;

    private String xhbBackrestTerm;

    private String filterLoanidCards;

    private BigDecimal matchingRateLower;

    private BigDecimal matchingRateUpper;

    private String loanBackmoneyDays;

    private Date matchingDate;
     
    private String autoId;

    public String getMatchingId() {
        return matchingId;
    }

    public void setMatchingId(String matchingId) {
        this.matchingId = matchingId == null ? null : matchingId.trim();
    }

    public String getLendCode() {
        return lendCode;
    }

    public void setLendCode(String lendCode) {
        this.lendCode = lendCode == null ? null : lendCode.trim();
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode == null ? null : productCode.trim();
    }

    public String getMatchingFirstdayFlag() {
        return matchingFirstdayFlag;
    }

    public void setMatchingFirstdayFlag(String matchingFirstdayFlag) {
        this.matchingFirstdayFlag = matchingFirstdayFlag == null ? null : matchingFirstdayFlag.trim();
    }

    public Date getMatchingInterestStart() {
        return matchingInterestStart;
    }

    public void setMatchingInterestStart(Date matchingInterestStart) {
        this.matchingInterestStart = matchingInterestStart;
    }

    public Integer getMatchingBillDay() {
        return matchingBillDay;
    }

    public void setMatchingBillDay(Integer matchingBillDay) {
        this.matchingBillDay = matchingBillDay;
    }

    public BigDecimal getMatchingBorrowQuota() {
        return matchingBorrowQuota;
    }

    public void setMatchingBorrowQuota(BigDecimal matchingBorrowQuota) {
        this.matchingBorrowQuota = matchingBorrowQuota;
    }

    public String getMatchingStatus() {
        return matchingStatus;
    }

    public void setMatchingStatus(String matchingStatus) {
        this.matchingStatus = matchingStatus == null ? null : matchingStatus.trim();
    }

    public Date getMatchingEndday() {
        return matchingEndday;
    }

    public void setMatchingEndday(Date matchingEndday) {
        this.matchingEndday = matchingEndday;
    }

    public Integer getMatchingTotal() {
        return matchingTotal;
    }

    public void setMatchingTotal(Integer matchingTotal) {
        this.matchingTotal = matchingTotal;
    }

    public Integer getMatchingNow() {
        return matchingNow;
    }

    public void setMatchingNow(Integer matchingNow) {
        this.matchingNow = matchingNow;
    }

    public Date getMatchingExpireDay() {
        return matchingExpireDay;
    }

    public void setMatchingExpireDay(Date matchingExpireDay) {
        this.matchingExpireDay = matchingExpireDay;
    }

    public Date getMatchingCossTime() {
        return matchingCossTime;
    }

    public void setMatchingCossTime(Date matchingCossTime) {
        this.matchingCossTime = matchingCossTime;
    }

    public Date getMatchingMakeDay() {
        return matchingMakeDay;
    }

    public void setMatchingMakeDay(Date matchingMakeDay) {
        this.matchingMakeDay = matchingMakeDay;
    }

    public Date getMatchingHkDay() {
        return matchingHkDay;
    }

    public void setMatchingHkDay(Date matchingHkDay) {
        this.matchingHkDay = matchingHkDay;
    }

    public Date getMatchingFirstbillDay() {
        return matchingFirstbillDay;
    }

    public void setMatchingFirstbillDay(Date matchingFirstbillDay) {
        this.matchingFirstbillDay = matchingFirstbillDay;
    }

    public String getMatchingPayStatus() {
        return matchingPayStatus;
    }

    public void setMatchingPayStatus(String matchingPayStatus) {
        this.matchingPayStatus = matchingPayStatus == null ? null : matchingPayStatus.trim();
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy == null ? null : modifyBy.trim();
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode == null ? null : customerCode.trim();
    }

    public String getXhbBackrestTerm() {
        return xhbBackrestTerm;
    }

    public void setXhbBackrestTerm(String xhbBackrestTerm) {
        this.xhbBackrestTerm = xhbBackrestTerm == null ? null : xhbBackrestTerm.trim();
    }

    public String getFilterLoanidCards() {
        return filterLoanidCards;
    }

    public void setFilterLoanidCards(String filterLoanidCards) {
        this.filterLoanidCards = filterLoanidCards == null ? null : filterLoanidCards.trim();
    }

    public String getLoanBackmoneyDays() {
        return loanBackmoneyDays;
    }

    public void setLoanBackmoneyDays(String loanBackmoneyDays) {
        this.loanBackmoneyDays = loanBackmoneyDays == null ? null : loanBackmoneyDays.trim();
    }

    public Date getMatchingDate() {
        return matchingDate;
    }

    public void setMatchingDate(Date matchingDate) {
        this.matchingDate = matchingDate;
    }

	public BigDecimal getMatchingRateLower() {
		return matchingRateLower;
	}

	public void setMatchingRateLower(BigDecimal matchingRateLower) {
		this.matchingRateLower = matchingRateLower;
	}

	public BigDecimal getMatchingRateUpper() {
		return matchingRateUpper;
	}

	public void setMatchingRateUpper(BigDecimal matchingRateUpper) {
		this.matchingRateUpper = matchingRateUpper;
	}

	public String getAutoId() {
		return autoId;
	}

	public void setAutoId(String autoId) {
		this.autoId = autoId;
	}
    
}