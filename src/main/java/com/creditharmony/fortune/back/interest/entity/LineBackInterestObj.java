package com.creditharmony.fortune.back.interest.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class LineBackInterestObj implements Serializable{

	/**  */
	private static final long serialVersionUID = 1L;
	
	/** 回息ID(对应接口中的业务ID) */
	private String backiId;
	
	/** 账户名称 */
	private String accountName;
	
	/** 账号  */
	private String accountNo;
	
	/** 实际回息金额(传给接口的是BigDicmal类型的值) */
	private BigDecimal backRealMoney;
	
	/** 金账户账号 */
	private String applyPay;
	
	/** 回息成功金额 */
	private BigDecimal successMoney;
	
	/** 城市  */
	private String city;
	
	/** 回息开户行(给的是银行的ID，如102，103) */
	private String accountBank;
	
	/** 回款支行名称(支行名) */
	private String accountBranch;

	/** 省份(支行省) */
	private String province;
	
	/** 出借编号 */
	private String lendCode;
	
	/** 证件类型(编码) */
	private String dictCustomerCertType;
	
	/** 证件号码 */
	private String customerCertNum;
	
	/** 手机号码 */
	private String customerMobilephone;
	
	/** 账单类型 */
	private String accountcardOrbooklet;
	
	/** 账单日 */
	private Date currentBillday;
	
	/** 版本时间 */
	private String verTime;
	
	/** 回息平台 */
	private String platformId;
	
	// 以下字段是卡联线上回息使用
	/** 银行Code */
	private String bankCode;
	/** 支行名*/
	private String bankName;
	
	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getPlatformId() {
		return platformId;
	}

	public void setPlatformId(String platformId) {
		this.platformId = platformId;
	}
	public String getVerTime() {
		return verTime;
	}

	public void setVerTime(String verTime) {
		this.verTime = verTime;
	}

	public Date getCurrentBillday() {
		return currentBillday;
	}

	public void setCurrentBillday(Date currentBillday) {
		this.currentBillday = currentBillday;
	}

	public String getAccountcardOrbooklet() {
		return accountcardOrbooklet;
	}

	public void setAccountcardOrbooklet(String accountcardOrbooklet) {
		this.accountcardOrbooklet = accountcardOrbooklet;
	}

	public String getBackiId() {
		return backiId;
	}

	public void setBackiId(String backiId) {
		this.backiId = backiId;
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
	
	public BigDecimal getBackRealMoney() {
		return backRealMoney;
	}

	public void setBackRealMoney(BigDecimal backRealMoney) {
		this.backRealMoney = backRealMoney;
	}

	public String getApplyPay() {
		return applyPay;
	}

	public void setApplyPay(String applyPay) {
		this.applyPay = applyPay;
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


	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getLendCode() {
		return lendCode;
	}

	public void setLendCode(String lendCode) {
		this.lendCode = lendCode;
	}

	public BigDecimal getSuccessMoney() {
		return successMoney;
	}

	public void setSuccessMoney(BigDecimal successMoney) {
		this.successMoney = successMoney;
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

	public String getCustomerMobilephone() {
		return customerMobilephone;
	}

	public void setCustomerMobilephone(String customerMobilephone) {
		this.customerMobilephone = customerMobilephone;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}