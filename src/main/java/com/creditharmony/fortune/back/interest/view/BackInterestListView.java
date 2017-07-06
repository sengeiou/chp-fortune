package com.creditharmony.fortune.back.interest.view;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

/**
 * 回息列表封装对象
 * @Class Name BackInterestListView 
 * @author 李志伟
 * @Create In 2016年2月2日
 */
public class BackInterestListView  implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// 回息ID
	private String backiId;
	// 出借编号
	private String lendCode;
	// 客户编号
	private String customerCode;
	// 客户姓名
	private String customerName;
	// 营业部
	private String orgName;
	// 出借金额
	private String applyLendMoney;
	// 付款方式
	private String applyPay;
	// 出借方式
	private String productCode;
	// 计划出借日
	private Date applyLendDay;
	// 回款银行
	private String accountBank;
	// 回款账号
	private String accountNo;
	// 应回息金额(应回收益)
	private BigDecimal backMoney;
	// 实际回息金额(应回收益)
	private BigDecimal backRealMoney;
	// 本期到期日
	private Date matchingExpireDay;
	// 账单日
	private Date currentBillday;
	// 回息平台
	private String platformId;
	// 回息日期
	private Date backMoneyDay;
	// 退回原因
	private String returnReason;
	// 回息状态
	private String backMoneyStatus;
	// 卡、折
	private String accountCardOrBooklet;
	// 所在省
	private String province;
	// 所在市
	private String city;
	// 金账户所在省
	private String jProvince;
	// 金账户所在市
	private String jCity;
	// 回盘结果
	private String backResult;
	// 回盘时间
	private Date backDay;
	// 退回重放标志
	private String rebackFlag;
	// 平台标识
	private String platFlag;
	// 成功金额
	private Double successMoney;
	// 失败金额
	private Double failMoney;
	// 失败原因
	private String backReason;
	// 合同版本号
	private String applyAgreementEdition;
	// 到期回息标识 1为到期回息数据 空的为正常回息数据   gaoxu 2017-3-23 10:49:33
	private String interestReturn;
	// 到期回息是否回息标识0是1否 gaoxu 2017-3-23 10:49:33
	private String isInterest;
	// 出借到期日  gaoxu 2017-3-23 10:49:33
	private Date applyExpireDay;
	
	

	
	public String getjProvince() {
		return jProvince;
	}
	public void setjProvince(String jProvince) {
		this.jProvince = jProvince;
	}
	public String getjCity() {
		return jCity;
	}
	public void setjCity(String jCity) {
		this.jCity = jCity;
	}
	public String getApplyAgreementEdition() {
		return applyAgreementEdition;
	}
	public void setApplyAgreementEdition(String applyAgreementEdition) {
		this.applyAgreementEdition = applyAgreementEdition;
	}
	public Double getSuccessMoney() {
		return successMoney;
	}
	public void setSuccessMoney(Double successMoney) {
		this.successMoney = successMoney;
	}
	public Double getFailMoney() {
		return failMoney;
	}
	public void setFailMoney(Double failMoney) {
		this.failMoney = failMoney;
	}
	public String getPlatFlag() {
		return platFlag;
	}
	public void setPlatFlag(String platFlag) {
		this.platFlag = platFlag;
	}
	public String getBackiId() {
		return backiId;
	}
	public void setBackiId(String backiId) {
		this.backiId = backiId;
	}
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
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getApplyLendMoney() {
		return applyLendMoney;
	}
	public void setApplyLendMoney(String applyLendMoney) {
		this.applyLendMoney = applyLendMoney;
	}
	public Date getApplyLendDay() {
		return applyLendDay;
	}
	public void setApplyLendDay(Date applyLendDay) {
		this.applyLendDay = applyLendDay;
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
	public BigDecimal getBackMoney() {
		return backMoney;
	}
	public BigDecimal getBackRealMoney() {
		return backRealMoney;
	}
	public void setBackRealMoney(BigDecimal backRealMoney) {
		this.backRealMoney = backRealMoney;
	}
	public void setBackMoney(BigDecimal backMoney) {
		this.backMoney = backMoney;
	}
	public Date getMatchingExpireDay() {
		return matchingExpireDay;
	}
	public void setMatchingExpireDay(Date matchingExpireDay) {
		this.matchingExpireDay = matchingExpireDay;
	}
	
	public Date getCurrentBillday() {
		return currentBillday;
	}
	public void setCurrentBillday(Date currentBillday) {
		this.currentBillday = currentBillday;
	}
	public String getPlatformId() {
		return platformId;
	}
	public void setPlatformId(String platformId) {
		this.platformId = platformId;
	}
	public Date getBackMoneyDay() {
		return backMoneyDay;
	}
	public void setBackMoneyDay(Date backMoneyDay) {
		this.backMoneyDay = backMoneyDay;
	}
	public String getBackMoneyStatus() {
		return backMoneyStatus;
	}
	public void setBackMoneyStatus(String backMoneyStatus) {
		this.backMoneyStatus = backMoneyStatus;
	}
	public String getReturnReason() {
		return returnReason;
	}
	public void setReturnReason(String returnReason) {
		this.returnReason = returnReason;
	}
	
	public String getAccountCardOrBooklet() {
		return accountCardOrBooklet;
	}
	public void setAccountCardOrBooklet(String accountCardOrBooklet) {
		this.accountCardOrBooklet = accountCardOrBooklet;
	}
	public String getProvince() {
		if(StringUtils.isNotEmpty(province)){
			return province;
		}
		return jProvince;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		if(StringUtils.isNotEmpty(city)){
			return city;
		}
		return jCity;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getBackResult() {
		return backResult;
	}
	public void setBackResult(String backResult) {
		this.backResult = backResult;
	}
	public Date getBackDay() {
		return backDay;
	}
	public void setBackDay(Date backDay) {
		this.backDay = backDay;
	}
	public String getRebackFlag() {
		return rebackFlag;
	}
	public void setRebackFlag(String rebackFlag) {
		this.rebackFlag = rebackFlag;
	}
	public String getBackReason() {
		return backReason;
	}
	public void setBackReason(String backReason) {
		this.backReason = backReason;
	}
	public String getApplyPay() {
		return applyPay;
	}
	public void setApplyPay(String applyPay) {
		this.applyPay = applyPay;
	}
	public String getInterestReturn() {
		return interestReturn;
	}
	public void setInterestReturn(String interestReturn) {
		this.interestReturn = interestReturn;
	}
	public String getIsInterest() {
		return isInterest;
	}
	public void setIsInterest(String isInterest) {
		this.isInterest = isInterest;
	}
	public Date getApplyExpireDay() {
		return applyExpireDay;
	}
	public void setApplyExpireDay(Date applyExpireDay) {
		this.applyExpireDay = applyExpireDay;
	}
	
	
	
}