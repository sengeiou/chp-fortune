package com.creditharmony.fortune.deduct.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.creditharmony.core.persistence.DataEntity;

/**
 * 预约划扣列表
 * @Class Name DeductBespoke
 * @author 韩龙
 * @Create In 2016年2月1日
 */
public class DeductBespoke extends DataEntity<DeductBespoke>{
    /**
	 * long serialVersionUID 
	 */
	private static final long serialVersionUID = 2935379448467619267L;
	// 主健ID
	private String id;
	// 划扣平台ID
    private String dictDeductPlatformId;
    // 划扣规则
    private String dictDeductRule;
    // 划扣状态
    private String dictDeductStatus;
    // 预约日期
    private Date bespokeDate;
    // 执行时间段
    private String executionTimeSegment;
    // 出借编号
    private String lendCode;
    // 分天划扣ID
    private String dayDeductId;
    // 划扣银行ID
    private String bankId;
    // 银行代码
    private String bankNo;
    // 账户号码
    private String accountNo;
    // 账户名称
    private String accountName;
    // 代收代付（默认代收）
    private String collectionAndPayment;
    // 业务代码
    private String operationNo;
    // 账户类型(银行卡|存折|信用卡)
    private String accountType;
    // 预约划扣金额
    private BigDecimal bespokeDeductMoney;
    // 账号属性(私人|公司)
    private String accountProperty;
    // 证件类型
    private String dictCustomerCertType;
    // 证件号码
    private String customerCertNum;
    // 银行卡所在城市省
    private String accountAddrprovince;
    // 银行卡所在城市市
    private String accountAddrcity;
    // 具体支行
    private String accountBranch;
    // 客户邮箱
    private String customerEmail;
    // 移动电话
    private String mobilephone;

    private String createBy;

    private Date createTime;

    private String modifyBy;
    // 
    private Date modifyTime;
    // 外键ID(分天划扣id或单天划扣ID)
    private String foreginId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getDictDeductPlatformId() {
        return dictDeductPlatformId;
    }

    public void setDictDeductPlatformId(String dictDeductPlatformId) {
        this.dictDeductPlatformId = dictDeductPlatformId == null ? null : dictDeductPlatformId.trim();
    }

    public String getDictDeductRule() {
        return dictDeductRule;
    }

    public void setDictDeductRule(String dictDeductRule) {
        this.dictDeductRule = dictDeductRule == null ? null : dictDeductRule.trim();
    }

    public String getDictDeductStatus() {
        return dictDeductStatus;
    }

    public void setDictDeductStatus(String dictDeductStatus) {
        this.dictDeductStatus = dictDeductStatus == null ? null : dictDeductStatus.trim();
    }

    public Date getBespokeDate() {
        return bespokeDate;
    }

    public void setBespokeDate(Date bespokeDate) {
        this.bespokeDate = bespokeDate;
    }

    public String getExecutionTimeSegment() {
        return executionTimeSegment;
    }

    public void setExecutionTimeSegment(String executionTimeSegment) {
        this.executionTimeSegment = executionTimeSegment == null ? null : executionTimeSegment.trim();
    }

    public String getLendCode() {
        return lendCode;
    }

    public void setLendCode(String lendCode) {
        this.lendCode = lendCode == null ? null : lendCode.trim();
    }

    public String getDayDeductId() {
        return dayDeductId;
    }

    public void setDayDeductId(String dayDeductId) {
        this.dayDeductId = dayDeductId == null ? null : dayDeductId.trim();
    }

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    public String getBankNo() {
        return bankNo;
    }

    public void setBankNo(String bankNo) {
        this.bankNo = bankNo == null ? null : bankNo.trim();
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

    public String getCollectionAndPayment() {
        return collectionAndPayment;
    }

    public void setCollectionAndPayment(String collectionAndPayment) {
        this.collectionAndPayment = collectionAndPayment == null ? null : collectionAndPayment.trim();
    }

    public String getOperationNo() {
        return operationNo;
    }

    public void setOperationNo(String operationNo) {
        this.operationNo = operationNo == null ? null : operationNo.trim();
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType == null ? null : accountType.trim();
    }

    public BigDecimal getBespokeDeductMoney() {
        return bespokeDeductMoney;
    }

    public void setBespokeDeductMoney(BigDecimal bespokeDeductMoney) {
        this.bespokeDeductMoney = bespokeDeductMoney;
    }

    public String getAccountProperty() {
        return accountProperty;
    }

    public void setAccountProperty(String accountProperty) {
        this.accountProperty = accountProperty == null ? null : accountProperty.trim();
    }

    public String getDictCustomerCertType() {
        return dictCustomerCertType;
    }

    public void setDictCustomerCertType(String dictCustomerCertType) {
        this.dictCustomerCertType = dictCustomerCertType == null ? null : dictCustomerCertType.trim();
    }

    public String getCustomerCertNum() {
        return customerCertNum;
    }

    public void setCustomerCertNum(String customerCertNum) {
        this.customerCertNum = customerCertNum == null ? null : customerCertNum.trim();
    }

    public String getAccountAddrprovince() {
        return accountAddrprovince;
    }

    public void setAccountAddrprovince(String accountAddrprovince) {
        this.accountAddrprovince = accountAddrprovince == null ? null : accountAddrprovince.trim();
    }

    public String getAccountAddrcity() {
        return accountAddrcity;
    }

    public void setAccountAddrcity(String accountAddrcity) {
        this.accountAddrcity = accountAddrcity == null ? null : accountAddrcity.trim();
    }

    public String getAccountBranch() {
        return accountBranch;
    }

    public void setAccountBranch(String accountBranch) {
        this.accountBranch = accountBranch == null ? null : accountBranch.trim();
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail == null ? null : customerEmail.trim();
    }

    public String getMobilephone() {
        return mobilephone;
    }

    public void setMobilephone(String mobilephone) {
        this.mobilephone = mobilephone == null ? null : mobilephone.trim();
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

	public String getForeginId() {
		return foreginId;
	}

	public void setForeginId(String foreginId) {
		this.foreginId = foreginId;
	}

	@Override
	public String toString() {
		return "DeductBespoke [id=" + id + ", dictDeductPlatformId="
				+ dictDeductPlatformId + ", dictDeductRule=" + dictDeductRule
				+ ", dictDeductStatus=" + dictDeductStatus + ", bespokeDate="
				+ bespokeDate + ", executionTimeSegment="
				+ executionTimeSegment + ", lendCode=" + lendCode
				+ ", dayDeductId=" + dayDeductId + ", bankId=" + bankId
				+ ", bankNo=" + bankNo + ", accountNo=" + accountNo
				+ ", accountName=" + accountName + ", collectionAndPayment="
				+ collectionAndPayment + ", operationNo=" + operationNo
				+ ", accountType=" + accountType + ", bespokeDeductMoney="
				+ bespokeDeductMoney + ", accountProperty=" + accountProperty
				+ ", dictCustomerCertType=" + dictCustomerCertType
				+ ", customerCertNum=" + customerCertNum
				+ ", accountAddrprovince=" + accountAddrprovince
				+ ", accountAddrcity=" + accountAddrcity + ", accountBranch="
				+ accountBranch + ", customerEmail=" + customerEmail
				+ ", mobilephone=" + mobilephone + ", createBy=" + createBy
				+ ", createTime=" + createTime + ", modifyBy=" + modifyBy
				+ ", modifyTime=" + modifyTime + ", foreginId=" + foreginId
				+ "]";
	}
	
	
}