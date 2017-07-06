package com.creditharmony.fortune.maintenance.settles.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.creditharmony.core.persistence.DataEntity;

public class LoanphaseInfo extends DataEntity<LoanphaseInfo> {

	/**
	 * long serialVersionUID 
	 */
	private static final long serialVersionUID = 1L;

	private String phaseId;

    private String matchingId;

    private String loanCode;

    private String lendCode;

    private Integer phaseNumber;// 期数

    private BigDecimal phaseAmount;// 本息

    private BigDecimal phaseInterestCur;// 利息

    private BigDecimal phasePrincipalCur;// 本金

    private BigDecimal phasePrincipalSurplus;// 剩余未还本金

    private String phaseRepaySign;// 

    private Date phaseRepaydateActual;

    private BigDecimal phaseBackCount;// 截至本期已还本息

    private BigDecimal phaseBackPrincipal;// 截至本期已还本金

    private BigDecimal phaseBackInterest;// 截至本期已还利息

    private Date phaseBegindayCur;

    private Date phaseEnddayCur;

    private String phaseMateId;

    private Integer phaseMateNumber;

    private Integer phaseNumberSurplus;

    private String phaseDiscardStatus;// 废弃

    private String phaseReleaseStatus;// 释放状态

    private String phaseFreezeNextstatus;

    private String createBy;

    private Date createTime;

    private String modifyBy;

    private Date modifyTime;
    
    private Date billDay;

    public String getPhaseId() {
        return phaseId;
    }
    public void setPhaseId(String phaseId) {
        this.phaseId = phaseId == null ? null : phaseId.trim();
    }

    public String getMatchingId() {
        return matchingId;
    }

    public void setMatchingId(String matchingId) {
        this.matchingId = matchingId == null ? null : matchingId.trim();
    }

    public String getLoanCode() {
        return loanCode;
    }

    public void setLoanCode(String loanCode) {
        this.loanCode = loanCode == null ? null : loanCode.trim();
    }

    public String getLendCode() {
        return lendCode;
    }

    public void setLendCode(String lendCode) {
        this.lendCode = lendCode == null ? null : lendCode.trim();
    }

    public Integer getPhaseNumber() {
        return phaseNumber;
    }

    public void setPhaseNumber(Integer phaseNumber) {
        this.phaseNumber = phaseNumber;
    }

    public BigDecimal getPhaseAmount() {
        return phaseAmount;
    }

    public void setPhaseAmount(BigDecimal phaseAmount) {
        this.phaseAmount = phaseAmount;
    }

    public BigDecimal getPhaseInterestCur() {
        return phaseInterestCur;
    }

    public void setPhaseInterestCur(BigDecimal phaseInterestCur) {
        this.phaseInterestCur = phaseInterestCur;
    }

    public BigDecimal getPhasePrincipalCur() {
        return phasePrincipalCur;
    }

    public void setPhasePrincipalCur(BigDecimal phasePrincipalCur) {
        this.phasePrincipalCur = phasePrincipalCur;
    }

    public BigDecimal getPhasePrincipalSurplus() {
        return phasePrincipalSurplus;
    }

    public void setPhasePrincipalSurplus(BigDecimal phasePrincipalSurplus) {
        this.phasePrincipalSurplus = phasePrincipalSurplus;
    }

    public String getPhaseRepaySign() {
        return phaseRepaySign;
    }

    public void setPhaseRepaySign(String phaseRepaySign) {
        this.phaseRepaySign = phaseRepaySign == null ? null : phaseRepaySign.trim();
    }

    public Date getPhaseRepaydateActual() {
        return phaseRepaydateActual;
    }

    public void setPhaseRepaydateActual(Date phaseRepaydateActual) {
        this.phaseRepaydateActual = phaseRepaydateActual;
    }

    public BigDecimal getPhaseBackCount() {
        return phaseBackCount;
    }

    public void setPhaseBackCount(BigDecimal phaseBackCount) {
        this.phaseBackCount = phaseBackCount;
    }

    public BigDecimal getPhaseBackPrincipal() {
        return phaseBackPrincipal;
    }

    public void setPhaseBackPrincipal(BigDecimal phaseBackPrincipal) {
        this.phaseBackPrincipal = phaseBackPrincipal;
    }

    public BigDecimal getPhaseBackInterest() {
        return phaseBackInterest;
    }

    public void setPhaseBackInterest(BigDecimal phaseBackInterest) {
        this.phaseBackInterest = phaseBackInterest;
    }

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

    public String getPhaseMateId() {
        return phaseMateId;
    }

    public void setPhaseMateId(String phaseMateId) {
        this.phaseMateId = phaseMateId == null ? null : phaseMateId.trim();
    }

    public Integer getPhaseMateNumber() {
        return phaseMateNumber;
    }

    public void setPhaseMateNumber(Integer phaseMateNumber) {
        this.phaseMateNumber = phaseMateNumber;
    }

    public Integer getPhaseNumberSurplus() {
        return phaseNumberSurplus;
    }

    public void setPhaseNumberSurplus(Integer phaseNumberSurplus) {
        this.phaseNumberSurplus = phaseNumberSurplus;
    }

    public String getPhaseDiscardStatus() {
        return phaseDiscardStatus;
    }

    public void setPhaseDiscardStatus(String phaseDiscardStatus) {
        this.phaseDiscardStatus = phaseDiscardStatus == null ? null : phaseDiscardStatus.trim();
    }

    public String getPhaseReleaseStatus() {
        return phaseReleaseStatus;
    }

    public void setPhaseReleaseStatus(String phaseReleaseStatus) {
        this.phaseReleaseStatus = phaseReleaseStatus == null ? null : phaseReleaseStatus.trim();
    }

    public String getPhaseFreezeNextstatus() {
        return phaseFreezeNextstatus;
    }

    public void setPhaseFreezeNextstatus(String phaseFreezeNextstatus) {
        this.phaseFreezeNextstatus = phaseFreezeNextstatus == null ? null : phaseFreezeNextstatus.trim();
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
	public Date getBillDay() {
		return billDay;
	}
	public void setBillDay(Date billDay) {
		this.billDay = billDay;
	}
    
}