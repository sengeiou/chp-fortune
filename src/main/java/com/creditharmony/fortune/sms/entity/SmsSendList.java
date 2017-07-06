package com.creditharmony.fortune.sms.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.creditharmony.core.persistence.DataEntity;

/**
 * 短信提醒实体类
 * @Class Name SmsSendList
 * @author 韩龙
 * @Create In 2015年12月4日
 */
public class SmsSendList extends DataEntity<SmsSendList> {
	
	/**
	 * long serialVersionUID 
	 */
	private static final long serialVersionUID = 5552095544966083433L;

	private String id;

    private String lendCode;

    private String customerName;
    
    private String productCode;
    
    private String productName;
    
    private String customerCode;

    private String customerPhone;

    private String managerCode;

    private Date deductDate;

    private Integer billDay;

    private BigDecimal lendMoney;

    private Date lendDay;

    private Date dueDay;

    private String dictLendType;

    private Date backMoneyDay;

    private String dictRepayType;

    private String dictDeductStatus;

    private String bankNo;

    private String bankName;

    private String smsId;

    private String smsMsg;

    private String sendStatus;

    private Date sendDay;

    private Date pushDate;

    private String areaName;

    private String orgName;

    private String dictBackStatus;

    private Integer productCloseTerm;

    private String dictRemindType;

    private Date applyDay;
    
    private String onlyFlag;

    private String createBy;

    private Date createTime;

    private String modifyBy;

    private Date modifyTime;
    
    private String accountBank;

    private BigDecimal deductSucceedMoney;
    
    private BigDecimal deductFailMoney;
    
    private String failReason;
    
    private String platformId;
    
    private String bankId;
    
    private String managerName;
    
    private String orgCode;
    
	public String getAccountBank() {
		return accountBank;
	}

	public void setAccountBank(String accountBank) {
		this.accountBank = accountBank;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode == null ? null : customerCode.trim();
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone == null ? null : customerPhone.trim();
    }

    public String getManagerCode() {
        return managerCode;
    }

    public void setManagerCode(String managerCode) {
        this.managerCode = managerCode == null ? null : managerCode.trim();
    }

    public Date getDeductDate() {
        return deductDate;
    }

    public void setDeductDate(Date deductDate) {
        this.deductDate = deductDate;
    }

    public Integer getBillDay() {
        return billDay;
    }

    public void setBillDay(Integer billDay) {
        this.billDay = billDay;
    }

    public BigDecimal getLendMoney() {
        return lendMoney;
    }

    public void setLendMoney(BigDecimal lendMoney) {
        this.lendMoney = lendMoney;
    }

    public Date getLendDay() {
        return lendDay;
    }

    public void setLendDay(Date lendDay) {
        this.lendDay = lendDay;
    }

    public Date getDueDay() {
        return dueDay;
    }

    public void setDueDay(Date dueDay) {
        this.dueDay = dueDay;
    }

    public String getDictLendType() {
        return dictLendType;
    }

    public void setDictLendType(String dictLendType) {
        this.dictLendType = dictLendType == null ? null : dictLendType.trim();
    }

    public Date getBackMoneyDay() {
        return backMoneyDay;
    }

    public void setBackMoneyDay(Date backMoneyDay) {
        this.backMoneyDay = backMoneyDay;
    }

    public String getDictRepayType() {
        return dictRepayType;
    }

    public void setDictRepayType(String dictRepayType) {
        this.dictRepayType = dictRepayType == null ? null : dictRepayType.trim();
    }

    public String getDictDeductStatus() {
        return dictDeductStatus;
    }

    public void setDictDeductStatus(String dictDeductStatus) {
        this.dictDeductStatus = dictDeductStatus == null ? null : dictDeductStatus.trim();
    }

    public String getBankNo() {
        return bankNo;
    }

    public void setBankNo(String bankNo) {
        this.bankNo = bankNo == null ? null : bankNo.trim();
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName == null ? null : bankName.trim();
    }

    public String getSmsId() {
        return smsId;
    }

    public void setSmsId(String smsId) {
        this.smsId = smsId == null ? null : smsId.trim();
    }

    public String getSmsMsg() {
        return smsMsg;
    }

    public void setSmsMsg(String smsMsg) {
        this.smsMsg = smsMsg == null ? null : smsMsg.trim();
    }

    public String getSendStatus() {
        return sendStatus;
    }

    public void setSendStatus(String sendStatus) {
        this.sendStatus = sendStatus == null ? null : sendStatus.trim();
    }

    public Date getSendDay() {
        return sendDay;
    }

    public void setSendDay(Date sendDay) {
        this.sendDay = sendDay;
    }

    public Date getPushDate() {
        return pushDate;
    }

    public void setPushDate(Date pushDate) {
        this.pushDate = pushDate;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName == null ? null : areaName.trim();
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName == null ? null : orgName.trim();
    }

    public String getDictBackStatus() {
        return dictBackStatus;
    }

    public void setDictBackStatus(String dictBackStatus) {
        this.dictBackStatus = dictBackStatus == null ? null : dictBackStatus.trim();
    }

    public Integer getProductCloseTerm() {
        return productCloseTerm;
    }

    public void setProductCloseTerm(Integer productCloseTerm) {
        this.productCloseTerm = productCloseTerm;
    }

    public String getDictRemindType() {
        return dictRemindType;
    }

    public void setDictRemindType(String dictRemindType) {
        this.dictRemindType = dictRemindType == null ? null : dictRemindType.trim();
    }

    public Date getApplyDay() {
        return applyDay;
    }

    public void setApplyDay(Date applyDay) {
        this.applyDay = applyDay;
    }

    public String getOnlyFlag() {
		return onlyFlag;
	}

	public void setOnlyFlag(String onlyFlag) {
		this.onlyFlag = onlyFlag;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLendCode() {
		return lendCode;
	}

	public void setLendCode(String lendCode) {
		this.lendCode = lendCode;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public BigDecimal getDeductSucceedMoney() {
		return deductSucceedMoney;
	}

	public void setDeductSucceedMoney(BigDecimal deductSucceedMoney) {
		this.deductSucceedMoney = deductSucceedMoney;
	}

	public BigDecimal getDeductFailMoney() {
		return deductFailMoney;
	}

	public void setDeductFailMoney(BigDecimal deductFailMoney) {
		this.deductFailMoney = deductFailMoney;
	}

	public String getFailReason() {
		return failReason;
	}

	public void setFailReason(String failReason) {
		this.failReason = failReason;
	}

	public String getPlatformId() {
		return platformId;
	}

	public void setPlatformId(String platformId) {
		this.platformId = platformId;
	}

	public String getBankId() {
		return bankId;
	}

	public void setBankId(String bankId) {
		this.bankId = bankId;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
}