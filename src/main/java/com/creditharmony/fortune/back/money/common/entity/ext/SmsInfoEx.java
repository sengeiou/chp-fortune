package com.creditharmony.fortune.back.money.common.entity.ext;

import java.io.Serializable;
import java.util.Date;

import com.creditharmony.common.util.DateUtils;
import com.creditharmony.common.util.StringUtils;
import com.creditharmony.fortune.redemption.constant.RedeemConstant;

/**
 * 回款发送短信所需信息
 * 
 * @Class Name SmsInfo
 * @author 陈广鹏
 * @Create In 2016年1月20日
 */
public class SmsInfoEx implements Serializable {

	/**
	 * long serialVersionUID
	 */
	private static final long serialVersionUID = 8235642567106378450L;
	/**
	 * 借款编码
	 */
	private String lendCode;
	/**
	 * 客户编号
	 */
	private String customerCode;
	/**
	 * 客户姓名
	 */
	private String customerName;
	/**
	 * 移动电话
	 */
	private String customerMobilephone;
	/**
	 * 出借日期
	 */
	private Date applyLendDay;
	/**
	 * 出借金额
	 */
	private Double applyLendMoney;
	/**
	 * 出借模式
	 */
	private String productName;
	/**
	 * 产品Code
	 */
	private String productCode;
	/**
	 * 付款方式
	 */
	private String applyPay;
	/**
	 * 回款类型
	 */
	private String backMoneyType;
	/**
	 * 回款日期
	 */
	private Date backMoneyDay;
	private String bankId; // 开户行ID
	private String bankName; // 开户行
	private String bankNo; // 银行账号

	public String getLendCode() {
		return lendCode;
	}

	public void setLendCode(String lendCode) {
		this.lendCode = lendCode;
	}

	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerMobilephone() {
		return customerMobilephone;
	}

	public void setCustomerMobilephone(String customerMobilephone) {
		this.customerMobilephone = customerMobilephone;
	}

	public String getApplyLendDay() {
		return DateUtils.formatDate(applyLendDay, "yyyy-MM-dd");
	}

	public void setApplyLendDay(Date applyLendDay) {
		this.applyLendDay = applyLendDay;
	}

	public String getApplyLendMoney() {
		return StringUtils.doNumFormat(applyLendMoney, RedeemConstant.MONEY_FORMAT);
	}

	public void setApplyLendMoney(Double applyLendMoney) {
		this.applyLendMoney = applyLendMoney;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getBackMoneyType() {
		return backMoneyType;
	}

	public void setBackMoneyType(String backMoneyType) {
		this.backMoneyType = backMoneyType;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getApplyPay() {
		return applyPay;
	}

	public void setApplyPay(String applyPay) {
		this.applyPay = applyPay;
	}

	public Date getBackMoneyDay() {
		return backMoneyDay;
	}

	public void setBackMoneyDay(Date backMoneyDay) {
		this.backMoneyDay = backMoneyDay;
	}

	public String getBankId() {
		return bankId;
	}

	public void setBankId(String bankId) {
		this.bankId = bankId;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankNo() {
		return bankNo;
	}

	public void setBankNo(String bankNo) {
		this.bankNo = bankNo;
	}

}
