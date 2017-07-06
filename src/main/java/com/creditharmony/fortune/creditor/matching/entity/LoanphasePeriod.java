package com.creditharmony.fortune.creditor.matching.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.creditharmony.core.persistence.DataEntity;

public class LoanphasePeriod extends DataEntity<LoanphasePeriod> {
    private String id;

    private String matchingId;

    private String lendCode;

    private BigDecimal phaseAmount;

    private BigDecimal phaseInterestCur;

    private BigDecimal lendMoney;

    private BigDecimal totalasset;

    private Date reportPeriodStart;

    private Date reportPeriodEnd;

    private String creditNode;

    private String creditIdAll;

    private String customerCode;

    private String matchingStatus;

    private String createBy;

    private Date createTime;

    private String modifyBy;

    private Date modifyTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

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

    public BigDecimal getLendMoney() {
        return lendMoney;
    }

    public void setLendMoney(BigDecimal lendMoney) {
        this.lendMoney = lendMoney;
    }

    public BigDecimal getTotalasset() {
        return totalasset;
    }

    public void setTotalasset(BigDecimal totalasset) {
        this.totalasset = totalasset;
    }

    public Date getReportPeriodStart() {
        return reportPeriodStart;
    }

    public void setReportPeriodStart(Date reportPeriodStart) {
        this.reportPeriodStart = reportPeriodStart;
    }

    public Date getReportPeriodEnd() {
        return reportPeriodEnd;
    }

    public void setReportPeriodEnd(Date reportPeriodEnd) {
        this.reportPeriodEnd = reportPeriodEnd;
    }

    public String getCreditNode() {
        return creditNode;
    }

    public void setCreditNode(String creditNode) {
        this.creditNode = creditNode == null ? null : creditNode.trim();
    }

    public String getCreditIdAll() {
        return creditIdAll;
    }

    public void setCreditIdAll(String creditIdAll) {
        this.creditIdAll = creditIdAll == null ? null : creditIdAll.trim();
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode == null ? null : customerCode.trim();
    }

    public String getMatchingStatus() {
        return matchingStatus;
    }

    public void setMatchingStatus(String matchingStatus) {
        this.matchingStatus = matchingStatus == null ? null : matchingStatus.trim();
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
}