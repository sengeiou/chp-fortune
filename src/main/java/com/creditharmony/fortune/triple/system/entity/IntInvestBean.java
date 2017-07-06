package com.creditharmony.fortune.triple.system.entity;

import java.util.Date;

public class IntInvestBean {

	private String operation;

    private String sendType;

    private String osId;

    private String invId;

    private String osType;

    private String invCode;

    private String proType;

    private String loanType;

    private String periodsNo;

    private String annualYield;

    private String invMoney;

    private String invProformMoney;

    private String expReturn;

    private String actReturn;

    private Date compDate;

    private Date expireDate;

    private String billDate;

    private String isAdvanced;

    private String isFirstOder;

    private String empCode;

    private String empName;

    private String sendStatus;

    private Date infoCreateTime;
    
    private Date createTime;

    private String uniqueNum;

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation == null ? null : operation.trim();
    }

    public String getSendType() {
        return sendType;
    }

    public void setSendType(String sendType) {
        this.sendType = sendType == null ? null : sendType.trim();
    }

    public String getOsId() {
        return osId;
    }

    public void setOsId(String osId) {
        this.osId = osId == null ? null : osId.trim();
    }

    public String getInvId() {
        return invId;
    }

    public void setInvId(String invId) {
        this.invId = invId == null ? null : invId.trim();
    }

    public String getOsType() {
        return osType;
    }

    public void setOsType(String osType) {
        this.osType = osType == null ? null : osType.trim();
    }

    public String getInvCode() {
        return invCode;
    }

    public void setInvCode(String invCode) {
        this.invCode = invCode == null ? null : invCode.trim();
    }

    public String getProType() {
        return proType;
    }

    public void setProType(String proType) {
        this.proType = proType == null ? null : proType.trim();
    }

    public String getLoanType() {
        return loanType;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType == null ? null : loanType.trim();
    }

    public String getPeriodsNo() {
        return periodsNo;
    }

    public void setPeriodsNo(String periodsNo) {
        this.periodsNo = periodsNo == null ? null : periodsNo.trim();
    }

    public String getAnnualYield() {
        return annualYield;
    }

    public void setAnnualYield(String annualYield) {
        this.annualYield = annualYield == null ? null : annualYield.trim();
    }

    public String getInvMoney() {
        return invMoney;
    }

    public void setInvMoney(String invMoney) {
        this.invMoney = invMoney == null ? null : invMoney.trim();
    }

    public String getInvProformMoney() {
        return invProformMoney;
    }

    public void setInvProformMoney(String invProformMoney) {
        this.invProformMoney = invProformMoney == null ? null : invProformMoney.trim();
    }

    public String getExpReturn() {
        return expReturn;
    }

    public void setExpReturn(String expReturn) {
        this.expReturn = expReturn == null ? null : expReturn.trim();
    }

    public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getActReturn() {
        return actReturn;
    }

    public void setActReturn(String actReturn) {
        this.actReturn = actReturn == null ? null : actReturn.trim();
    }

    public Date getCompDate() {
        return compDate;
    }

    public void setCompDate(Date compDate) {
        this.compDate = compDate;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public String getBillDate() {
        return billDate;
    }

    public void setBillDate(String billDate) {
        this.billDate = billDate == null ? null : billDate.trim();
    }

    public String getIsAdvanced() {
        return isAdvanced;
    }

    public void setIsAdvanced(String isAdvanced) {
        this.isAdvanced = isAdvanced == null ? null : isAdvanced.trim();
    }

    public String getIsFirstOder() {
        return isFirstOder;
    }

    public void setIsFirstOder(String isFirstOder) {
        this.isFirstOder = isFirstOder == null ? null : isFirstOder.trim();
    }

    public String getEmpCode() {
        return empCode;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode == null ? null : empCode.trim();
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName == null ? null : empName.trim();
    }

    public String getSendStatus() {
        return sendStatus;
    }

    public void setSendStatus(String sendStatus) {
        this.sendStatus = sendStatus == null ? null : sendStatus.trim();
    }

    public Date getInfoCreateTime() {
        return infoCreateTime;
    }

    public void setInfoCreateTime(Date infoCreateTime) {
        this.infoCreateTime = infoCreateTime;
    }

    public String getUniqueNum() {
        return uniqueNum;
    }

    public void setUniqueNum(String uniqueNum) {
        this.uniqueNum = uniqueNum == null ? null : uniqueNum.trim();
    }
}