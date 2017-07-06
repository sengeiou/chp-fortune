package com.creditharmony.fortune.lend.apply.entity;

import com.creditharmony.fortune.customer.entity.CustomerAccount;

/**
 * @Class Name AccountView
 * @author 孙凯文
 * @Create In 2015年12月23日
 */
public class AccountView {
	private CustomerAccount account;
	private String operating;
	// 金账户标识
	private String trusteeFlag;
	
	private String applyCode;
	
	

	public String getApplyCode() {
		return applyCode;
	}

	public void setApplyCode(String applyCode) {
		this.applyCode = applyCode;
	}

	public CustomerAccount getAccount() {
		return account;
	}

	public void setAccount(CustomerAccount account) {
		this.account = account;
	}

	public String getOperating() {
		return operating;
	}

	public void setOperating(String operating) {
		this.operating = operating;
	}

	public String getTrusteeFlag() {
		return trusteeFlag;
	}

	public void setTrusteeFlag(String trusteeFlag) {
		this.trusteeFlag = trusteeFlag;
	}
}
