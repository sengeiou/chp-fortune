package com.creditharmony.fortune.deduct.entity;

import java.math.BigDecimal;

import com.creditharmony.core.persistence.DataEntity;

public class FortuneDeductReq extends DataEntity<FortuneDeductReq>{
	
    /**
	 * long serialVersionUID 
	 */
	private static final long serialVersionUID = 7580630895785550437L;

	private String sysId;

    private String requestId;
    
    private String businessId;

    private String batId;

    private String refId;

    private String deductFlag;

    private String rule;

    private String bankId;

    private String bankProv;

    private String bankCity;

    private String bankName;

    private String accountNo;

    private String accountName;

    private String idType;

    private String idNo;

    private String mobile;

    private BigDecimal amount;

    private String status;

    private Integer failedTimes;

    private String accountType;

    public String getSysId() {
		return sysId;
	}

	public void setSysId(String sysId) {
		this.sysId = sysId;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId == null ? null : businessId.trim();
    }

    public String getBatId() {
        return batId;
    }

    public void setBatId(String batId) {
        this.batId = batId == null ? null : batId.trim();
    }

    public String getRefId() {
        return refId;
    }

    public void setRefId(String refId) {
        this.refId = refId == null ? null : refId.trim();
    }

    public String getDeductFlag() {
        return deductFlag;
    }

    public void setDeductFlag(String deductFlag) {
        this.deductFlag = deductFlag == null ? null : deductFlag.trim();
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule == null ? null : rule.trim();
    }

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId == null ? null : bankId.trim();
    }

    public String getBankProv() {
        return bankProv;
    }

    public void setBankProv(String bankProv) {
        this.bankProv = bankProv == null ? null : bankProv.trim();
    }

    public String getBankCity() {
        return bankCity;
    }

    public void setBankCity(String bankCity) {
        this.bankCity = bankCity == null ? null : bankCity.trim();
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName == null ? null : bankName.trim();
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo == null ? null : accountNo.trim();
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName == null ? null : accountName.trim();
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType == null ? null : idType.trim();
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo == null ? null : idNo.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Integer getFailedTimes() {
        return failedTimes;
    }

    public void setFailedTimes(Integer failedTimes) {
        this.failedTimes = failedTimes;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType == null ? null : accountType.trim();
    }

	@Override
	public String toString() {
		return "FortuneDeductReq [sysId=" + sysId + ", requestId=" + requestId
				+ ", businessId=" + businessId + ", batId=" + batId
				+ ", refId=" + refId + ", deductFlag=" + deductFlag + ", rule="
				+ rule + ", bankId=" + bankId + ", bankProv=" + bankProv
				+ ", bankCity=" + bankCity + ", bankName=" + bankName
				+ ", accountNo=" + accountNo + ", accountName=" + accountName
				+ ", idType=" + idType + ", idNo=" + idNo + ", mobile="
				+ mobile + ", amount=" + amount + ", status=" + status
				+ ", failedTimes=" + failedTimes + ", accountType="
				+ accountType + "]";
	}
    
}