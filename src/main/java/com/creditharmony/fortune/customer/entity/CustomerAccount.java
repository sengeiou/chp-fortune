package com.creditharmony.fortune.customer.entity;

import com.creditharmony.core.persistence.DataEntity;

/**
 * @Class Name CustomerAccount
 * @author 孙凯文
 * @Create In 2015年12月2日
 */
public class CustomerAccount extends DataEntity<CustomerAccount> {

	private static final long serialVersionUID = 1L;
	// 客户编号
	private String customerCode;
	// 银行名称
	private String accountBankId;
	// 支行地址
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
	// 出借编号
	private String lendCode;
	//删除标记  0未删除、1删除
	private String isDel;
	//能否编辑，1不能编辑，0 可以编辑
	private String canModify;
	// 支行代码
	private String bankCode;
	// 支行信息匹配标识
	private String findFlag;

	public String getCanModify() {
		return canModify;
	}

	public void setCanModify(String canModify) {
		this.canModify = canModify;
	}

	public String getAccountBranch() {
		return accountBranch;
	}

	public void setAccountBranch(String accountBranch) {
		this.accountBranch = accountBranch == null ? null : accountBranch
				.trim();
	}

	public String getAccountCardOrBooklet() {
		return accountCardOrBooklet;
	}

	public void setAccountCardOrBooklet(String accountCardOrBooklet) {
		this.accountCardOrBooklet = accountCardOrBooklet == null ? null
				: accountCardOrBooklet.trim();
	}

	public String getIsDel() {
		return isDel;
	}

	public void setIsDel(String isDel) {
		this.isDel = isDel;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName == null ? null : accountName.trim();
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo == null ? null : accountNo.trim();
	}

	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
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

	public String getAccountBankId() {
		return accountBankId;
	}

	public void setAccountBankId(String accountBankId) {
		this.accountBankId = accountBankId;
	}

	public String getLendCode() {
		return lendCode;
	}

	public void setLendCode(String lendCode) {
		this.lendCode = lendCode;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getFindFlag() {
		return findFlag;
	}

	public void setFindFlag(String findFlag) {
		this.findFlag = findFlag;
	}

}