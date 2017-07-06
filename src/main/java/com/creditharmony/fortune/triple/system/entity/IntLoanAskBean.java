package com.creditharmony.fortune.triple.system.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.creditharmony.core.persistence.DataEntity;

public class IntLoanAskBean extends DataEntity<IntRoleBean> {
	private static final long serialVersionUID = -7350862653514995086L;

	private String id;

    private String askId;

    private String customerCode;

    private Date askDay;

    private Date askBeginDay;

    private Date askEndDay;

    private String dictAskType;

    private String askProduct;

    private BigDecimal askInputMoney;

    private Date askInputDay;

    private String askDes;

    private Date askNextDay;

    private String askNextType;
    private String createBy;

    private Date createTime;
    private String modifyBy;

    private Date modifyTime;

    private String managerId;
    private String managerCode;
    private String managerName;

    private String sendStatus;

    private String sendType;

    private Date sendTime;
    private String operation;

    private String uniqueNum;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getAskId() {
        return askId;
    }

    public void setAskId(String askId) {
        this.askId = askId == null ? null : askId.trim();
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode == null ? null : customerCode.trim();
    }

    public Date getAskDay() {
        return askDay;
    }

    public String getManagerCode() {
		return managerCode;
	}

	public void setManagerCode(String managerCode) {
		this.managerCode = managerCode;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setAskDay(Date askDay) {
        this.askDay = askDay;
    }

    public Date getAskBeginDay() {
        return askBeginDay;
    }

    public void setAskBeginDay(Date askBeginDay) {
        this.askBeginDay = askBeginDay;
    }

    public Date getAskEndDay() {
        return askEndDay;
    }

    public void setAskEndDay(Date askEndDay) {
        this.askEndDay = askEndDay;
    }

    public String getDictAskType() {
        return dictAskType;
    }

    public void setDictAskType(String dictAskType) {
        this.dictAskType = dictAskType == null ? null : dictAskType.trim();
    }

    public String getAskProduct() {
        return askProduct;
    }

    public void setAskProduct(String askProduct) {
        this.askProduct = askProduct == null ? null : askProduct.trim();
    }

    public BigDecimal getAskInputMoney() {
        return askInputMoney;
    }

    public void setAskInputMoney(BigDecimal askInputMoney) {
        this.askInputMoney = askInputMoney;
    }

    public Date getAskInputDay() {
        return askInputDay;
    }

    public void setAskInputDay(Date askInputDay) {
        this.askInputDay = askInputDay;
    }

    public String getAskDes() {
        return askDes;
    }

    public void setAskDes(String askDes) {
        this.askDes = askDes == null ? null : askDes.trim();
    }

    public Date getAskNextDay() {
        return askNextDay;
    }

    public void setAskNextDay(Date askNextDay) {
        this.askNextDay = askNextDay;
    }

    public String getAskNextType() {
        return askNextType;
    }

    public void setAskNextType(String askNextType) {
        this.askNextType = askNextType == null ? null : askNextType.trim();
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

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId == null ? null : managerId.trim();
    }

    public String getSendStatus() {
        return sendStatus;
    }

    public void setSendStatus(String sendStatus) {
        this.sendStatus = sendStatus == null ? null : sendStatus.trim();
    }

    public String getSendType() {
        return sendType;
    }

    public void setSendType(String sendType) {
        this.sendType = sendType == null ? null : sendType.trim();
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation == null ? null : operation.trim();
    }

    public String getUniqueNum() {
        return uniqueNum;
    }

    public void setUniqueNum(String uniqueNum) {
        this.uniqueNum = uniqueNum == null ? null : uniqueNum.trim();
    }
}