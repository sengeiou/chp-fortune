package com.creditharmony.fortune.deduct.entity.ext;

import java.io.Serializable;

/**
 * 线下划扣基类
 * @Class Name BaseExportModel
 * @author 韩龙
 * @Create In 2016年2月17日
 */
public class BaseExportModel implements Serializable {
	
	/**
	 * long serialVersionUID 
	 */
	private static final long serialVersionUID = 8184472299488923465L;
	
	// 客户编号
	private String customerCode;
	// 开户行
	private String accountBank;
	// 具体支行
	private String accountBranch;
	// 银行所在省份
	private String accountAddrProvince;
	// 银行所在城市
	private String accountAddrCity;
	// 银行所在区
	private String accountAddrDistrict;
	// 卡或折
	private String accountCardOrBooklet;
	// 账户名称
	private String accountName;
	// 账号
	private String accountNo;
	// 划扣金额
	private String deductMoney;
	// 证件类型
	private String dictCustomerCertType;
	// 证件号码
	private String customerCertNum;
	// 手机号
	private String customerMobilephone;
	// 出借编号
	private String lendCode;
	
	// 好易联、通联账号类型
	private String accountNumberType;
	// 中金、好易联、通联账户类型(个人与企业)
	private String accountType;
	
	public String getCustomerCode() {
		return customerCode;
	}
	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}
	public String getAccountBank() {
		return accountBank;
	}
	public void setAccountBank(String accountBank) {
		this.accountBank = accountBank;
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
	public String getAccountAddrDistrict() {
		return accountAddrDistrict;
	}
	public void setAccountAddrDistrict(String accountAddrDistrict) {
		this.accountAddrDistrict = accountAddrDistrict;
	}
	public String getAccountCardOrBooklet() {
		return accountCardOrBooklet;
	}
	public void setAccountCardOrBooklet(String accountCardOrBooklet) {
		this.accountCardOrBooklet = accountCardOrBooklet;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public String getDeductMoney() {
		return deductMoney;
	}
	public void setDeductMoney(String deductMoney) {
		this.deductMoney = deductMoney;
	}
	public String getDictCustomerCertType() {
		/*if(dictCustomerCertType != null){
			dictCustomerCertType = Constant.credtTpMap.get(dictCustomerCertType);
		}*/
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
	public String getAccountNumberType() {
		return accountNumberType;
	}
	public void setAccountNumberType(String accountNumberType) {
		this.accountNumberType = accountNumberType;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public String getCustomerMobilephone() {
		return customerMobilephone;
	}
	public void setCustomerMobilephone(String customerMobilephone) {
		this.customerMobilephone = customerMobilephone;
	}
	public String getLendCode() {
		return lendCode;
	}
	public void setLendCode(String lendCode) {
		this.lendCode = lendCode;
	}
}