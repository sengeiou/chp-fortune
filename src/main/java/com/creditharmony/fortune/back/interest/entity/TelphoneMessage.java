package com.creditharmony.fortune.back.interest.entity;

import java.io.Serializable;
import java.util.Date;

public class TelphoneMessage implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// 回息ID
	private String backiId;
	
	// 客户姓名
	private String customerName;
	
	// 产品名称
	private String productCode;
	
	// 出借日期
	private Date applyLendDay;
	
	// 实际回息金额
	private String backRealMoney;
	
	// 客户移动电话
	private String customerMobilephone;
	
	// 开户行
	private String openBank;
	
	// 开户行ID
	private String bankCode;
	
	public Date getApplyLendDay() {
		return applyLendDay;
	}

	public void setApplyLendDay(Date applyLendDay) {
		this.applyLendDay = applyLendDay;
	}

	public String getBackiId() {
		return backiId;
	}
	
	public String getOpenBank() {
		return openBank;
	}

	public void setOpenBank(String openBank) {
		this.openBank = openBank;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public void setBackiId(String backiId) {
		this.backiId = backiId;
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

	public String getBackRealMoney() {
		return backRealMoney;
	}

	public void setBackRealMoney(String backRealMoney) {
		this.backRealMoney = backRealMoney;
	}

	public String getCustomerMobilephone() {
		return customerMobilephone;
	}

	public void setCustomerMobilephone(String customerMobilephone) {
		this.customerMobilephone = customerMobilephone;
	}
}