package com.creditharmony.fortune.back.interest.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 金账户线上回息对象
 * @Class Name LineGoldBackInterestObj 
 * @author 李志伟
 * @Create In 2016年3月5日
 */
public class LineGoldBackInterestObj implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// 出借编号
	private String lendCode;
	// 商户代码(可不用给值)
	private String MerchantCode;
	
	// 流水号(回息ID)
	private String backiId;
	
	// 付款方登陆账户
	private String payAccount;
	
	// 收款方登陆账户
	private String posAccount;
	
	// 划拨金额 
	private BigDecimal backRealMoney;
	
	// 预授权合同号
	private String contractNo;
	
	// 产品名称
	private String productName;
	
	// 账单日期
	private Date currentBillday;
	
	// 备注
	private String memo;
	
	// 排他字段
	private String verTime;
	
	// 排他状态
	private String verState;

	public String getVerState() {
		return verState;
	}

	public void setVerState(String verState) {
		this.verState = verState;
	}
	
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Date getCurrentBillday() {
		return currentBillday;
	}

	public void setCurrentBillday(Date currentBillday) {
		this.currentBillday = currentBillday;
	}

	public String getVerTime() {
		return verTime;
	}

	public void setVerTime(String verTime) {
		this.verTime = verTime;
	}

	public String getLendCode() {
		return lendCode;
	}

	public void setLendCode(String lendCode) {
		this.lendCode = lendCode;
	}

	public String getMerchantCode() {
		return MerchantCode;
	}

	public void setMerchantCode(String merchantCode) {
		MerchantCode = merchantCode;
	}

	public String getBackiId() {
		return backiId;
	}

	public void setBackiId(String backiId) {
		this.backiId = backiId;
	}

	public String getPayAccount() {
		return payAccount;
	}

	public void setPayAccount(String payAccount) {
		this.payAccount = payAccount;
	}

	public String getPosAccount() {
		return posAccount;
	}

	public void setPosAccount(String posAccount) {
		this.posAccount = posAccount;
	}

	public BigDecimal getBackRealMoney() {
		return backRealMoney;
	}

	public void setBackRealMoney(BigDecimal backRealMoney) {
		this.backRealMoney = backRealMoney;
	}

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
}