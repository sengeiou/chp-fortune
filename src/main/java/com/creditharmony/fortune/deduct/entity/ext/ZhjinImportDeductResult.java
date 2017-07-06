package com.creditharmony.fortune.deduct.entity.ext;

import java.io.Serializable;
import java.util.Date;

import com.creditharmony.core.excel.annotation.ExcelField;

/**
 * 线下划扣中金结果导入
 * @Class Name ImportDeductResult
 * @author 韩龙
 * @Create In 2015年12月19日
 */
public class ZhjinImportDeductResult implements Serializable {
	
	/**
	 * long serialVersionUID 
	 */
	private static final long serialVersionUID = 977187771938022014L;
	
	/**
	 * 序号
	 */
	@ExcelField(title="序号",type=2)
	private String index;
	
	/**
	 * 交易时间
	 */
	@ExcelField(title="交易时间",type=2)
	private Date transactionTime;
	
	/**
	 * 机构名称
	 */
	@ExcelField(title="机构名称",type=2)
	private String organization;
	
	/**
	 * 批次号
	 */
	@ExcelField(title="批次号",type=2)
	private String batchNumber;
	
	/**
	 * 明细号
	 */
	@ExcelField(title="明细号",type=2)
	private String detailNumber;
	
	/**
	 * 金额
	 */
	@ExcelField(title="金额",type=2)
	private String deductMoney;
	
	/**
	 * 银行ID
	 */
	@ExcelField(title="银行ID",type=2)
	private String bankId;
	
	/**
	 * 账户类型
	 */
	@ExcelField(title="",type=2)
	private String accountType;
	
	/**
	 * 账户号码
	 */
	@ExcelField(title="账户号码",type=2)
	private String accountNo;
	
	/**
	 * 账户名称
	 */
	@ExcelField(title="账户名称",type=2)
	private String accountName;
	
	/**
	 * 分支行名称
	 */
	@ExcelField(title="分支行名称",type=2)
	private String accountBranch;
	
	/**
	 * 分支行省份
	 */
	@ExcelField(title="分支行省份",type=2)
	private String accountAddrProvince;
	
	/**
	 * 分支行城市
	 */
	@ExcelField(title="分支行城市",type=2)
	private String accountAddrCity;
	
	/**
	 * 备注信息	
	 */
	@ExcelField(title="备注信息",type=2)
	private String remark;
	
	/**
	 * 协议用户编号
	 */
	@ExcelField(title="协议用户编号",type=2)
	private String protocolUserNumber;
	
	/**
	 * 协议号
	 */
	@ExcelField(title="协议号",type=2)
	private String protocolNumber;
	
	/**
	 * 手机号码
	 */
	@ExcelField(title="手机号码",type=2)
	private String phone;
	
	/**
	 * 电子邮件
	 */
	@ExcelField(title="电子邮件",type=2)
	private String email;
	
	/**
	 * 证件类型
	 */
	@ExcelField(title="证件类型",type=2)
	private String dictCustomerCertType;
	
	/**
	 * 证件号码
	 */
	@ExcelField(title="证件号码",type=2)
	private String customerCertNum;
	
	/**
	 * 银行代收的时间
	 */
	@ExcelField(title="银行代收的时间",type=2)
	private Date timeOfBankCollection;
	
	/**
	 * 交易状态
	 */
	@ExcelField(title="交易状态",type=2)
	private String tradingStatus;
	
	/**
	 * 银行响应代码
	 */
	@ExcelField(title="银行响应代码",type=2)
	private String bankResponseCode;
	
	/**
	 * 银行响应消息
	 */
	@ExcelField(title="银行响应消息",type=2)
	private String bankResponseMessage;

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public Date getTransactionTime() {
		return transactionTime;
	}

	public void setTransactionTime(Date transactionTime) {
		this.transactionTime = transactionTime;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public String getBatchNumber() {
		return batchNumber;
	}

	public void setBatchNumber(String batchNumber) {
		this.batchNumber = batchNumber;
	}

	public String getDetailNumber() {
		return detailNumber;
	}

	public void setDetailNumber(String detailNumber) {
		this.detailNumber = detailNumber;
	}

	public String getDeductMoney() {
		return deductMoney;
	}

	public void setDeductMoney(String deductMoney) {
		this.deductMoney = deductMoney;
	}

	public String getBankId() {
		return bankId;
	}

	public void setBankId(String bankId) {
		this.bankId = bankId;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAccountBranch() {
		return accountBranch;
	}

	public void setAccountBranch(String accountBranch) {
		this.accountBranch = accountBranch;
	}

	public String getAccountAddrProvince() {
		return accountAddrProvince;
	}

	public void setAccountAddrProvince(String accountAddrProvince) {
		this.accountAddrProvince = accountAddrProvince;
	}

	public String getAccountAddrCity() {
		return accountAddrCity;
	}

	public void setAccountAddrCity(String accountAddrCity) {
		this.accountAddrCity = accountAddrCity;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getProtocolUserNumber() {
		return protocolUserNumber;
	}

	public void setProtocolUserNumber(String protocolUserNumber) {
		this.protocolUserNumber = protocolUserNumber;
	}

	public String getProtocolNumber() {
		return protocolNumber;
	}

	public void setProtocolNumber(String protocolNumber) {
		this.protocolNumber = protocolNumber;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDictCustomerCertType() {
		return dictCustomerCertType;
	}

	public void setDictCustomerCertType(String dictCustomerCertType) {
		this.dictCustomerCertType = dictCustomerCertType;
	}

	public String getCustomerCertNum() {
		return customerCertNum;
	}

	public void setCustomerCertNum(String customerCertNum) {
		this.customerCertNum = customerCertNum;
	}

	public Date getTimeOfBankCollection() {
		return timeOfBankCollection;
	}

	public void setTimeOfBankCollection(Date timeOfBankCollection) {
		this.timeOfBankCollection = timeOfBankCollection;
	}

	public String getTradingStatus() {
		return tradingStatus;
	}

	public void setTradingStatus(String tradingStatus) {
		this.tradingStatus = tradingStatus;
	}

	public String getBankResponseCode() {
		return bankResponseCode;
	}

	public void setBankResponseCode(String bankResponseCode) {
		this.bankResponseCode = bankResponseCode;
	}

	public String getBankResponseMessage() {
		return bankResponseMessage;
	}

	public void setBankResponseMessage(String bankResponseMessage) {
		this.bankResponseMessage = bankResponseMessage;
	}
	
}

