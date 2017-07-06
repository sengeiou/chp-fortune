package com.creditharmony.fortune.deduct.entity.ext;

import java.io.Serializable;

/**
 * 线下导出扩展类
 * 
 * @Class Name TemplateExportModelEx
 * @author 韩龙
 * @Create In 2015年12月18日
 */
public class TemplateExportModelEx implements Serializable {

	/**
	 * long serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	// 序号
	private String no;
	// 开户行
	private String accountBank;
	// 扣款人银行账号
	private String accountNo;
	// 户名
	private String accountName;
	// 金额(单位:元)
	private String applyLendMoney;
	// 企业流水账号
	private String lendCode;
	// 备注
	private String remarks;
	// 手机号
	private String customerMobilephone;
	// 证件类型
	private String dictCustomerCertType;
	// 证件号
	private String customerCertNum;

	/** -------------以上是富有--------------- */
	/**--------------以下好易联--------------- */
	// 银联网络用户编号
	private String unionNetworkCode;
	// 账号类型
	private String accountNumberType;
	// 开户行所在省
	private String accountAddrprovince;
	// 开户行所在市
	private String accountAddrcity;
	// 开户行名称
	private String accountBranch;
	// 帐户类型
	private String accountType;
	// 货币类型
	private String currencyType;
	// 协议号
	private String protocolNumber;
	// 协议用户编号
	private String protocolUserNumber;
	// 开户证件类型
	private String typeOfAccount;
	// 自定义用户名
	private String customUserName;
	// 备注1
	private String remarks1;
	// 备注2
	private String remarks2;

	public String getIndex() {
		return no;
	}

	public void setIndex(String index) {
		this.no = index;
	}

	public String getAccountBank() {
		return accountBank;
	}

	public void setAccountBank(String accountBank) {
		this.accountBank = accountBank;
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

	public String getApplyLendMoney() {
		return applyLendMoney;
	}

	public void setApplyLendMoney(String applyLendMoney) {
		this.applyLendMoney = applyLendMoney;
	}

	public String getLendCode() {
		return lendCode;
	}

	public void setLendCode(String lendCode) {
		this.lendCode = lendCode;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getCustomerMobilephone() {
		return customerMobilephone;
	}

	public void setCustomerMobilephone(String customerMobilephone) {
		this.customerMobilephone = customerMobilephone;
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

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getUnionNetworkCode() {
		return unionNetworkCode;
	}

	public void setUnionNetworkCode(String unionNetworkCode) {
		this.unionNetworkCode = unionNetworkCode;
	}

	public String getAccountNumberType() {
		return accountNumberType;
	}

	public void setAccountNumberType(String accountNumberType) {
		this.accountNumberType = accountNumberType;
	}

	public String getAccountAddrprovince() {
		return accountAddrprovince;
	}

	public void setAccountAddrprovince(String accountAddrprovince) {
		this.accountAddrprovince = accountAddrprovince;
	}

	public String getAccountAddrcity() {
		return accountAddrcity;
	}

	public void setAccountAddrcity(String accountAddrcity) {
		this.accountAddrcity = accountAddrcity;
	}

	public String getAccountBranch() {
		return accountBranch;
	}

	public void setAccountBranch(String accountBranch) {
		this.accountBranch = accountBranch;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getCurrencyType() {
		return currencyType;
	}

	public String getProtocolNumber() {
		return protocolNumber;
	}

	public void setProtocolNumber(String protocolNumber) {
		this.protocolNumber = protocolNumber;
	}

	public String getProtocolUserNumber() {
		return protocolUserNumber;
	}

	public void setProtocolUserNumber(String protocolUserNumber) {
		this.protocolUserNumber = protocolUserNumber;
	}

	public String getTypeOfAccount() {
		return typeOfAccount;
	}

	public String getCustomUserName() {
		return customUserName;
	}

	public String getRemarks1() {
		return remarks1;
	}

	public void setRemarks1(String remarks1) {
		this.remarks1 = remarks1;
	}

	public String getRemarks2() {
		return remarks2;
	}

	public void setRemarks2(String remarks2) {
		this.remarks2 = remarks2;
	}

	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}

	public void setTypeOfAccount(String typeOfAccount) {
		this.typeOfAccount = typeOfAccount;
	}

	public void setCustomUserName(String customUserName) {
		this.customUserName = customUserName;
	}
}
