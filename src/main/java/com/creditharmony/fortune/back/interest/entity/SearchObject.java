package com.creditharmony.fortune.back.interest.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.creditharmony.core.fortune.type.YoN;

/**
 * @Class Name SearchObject 
 * @author 李志伟
 * @Create In 2015年12月2日
 */

public class SearchObject implements Serializable{

	private static final long serialVersionUID = 8237369338453555429L;
	
	private String backiId;// 回息ID
	private String customerCode;// 客户编号
	private String customerName;// 客户姓名
	private String lendCode;// 出借编号
	private String orgId;// 营业部
	private String orgName;// 营业部
	
	private String applyBillday;// 账单日
	private Date currentBillday;// 当期账单日期
	private String addrProvince;// 省份
	private String addrCity;// 城市
	private Date applyLendDayFrom;// 计划出借日
	
	private Date applyLendDayTo;// 计划出借日
	private Date matchingExpireDayFrom;// 本期到期日
	private Date matchingExpireDayTo;// 本期到期日
	private Date applyExpireDayFrom;// 到期日(区别于本期到期日，指的是购买的产品到期日)
	
	private Date applyExpireDayTo;// 到期日(区别于本期到期日，指的是购买的产品到期日)
	private Date applyDeductDayFrom;// 计划划扣日
	private Date applyDeductDayTo;// 计划划扣日
	
	private BigDecimal applyLendMoneyFrom;// 出借金额
	private BigDecimal applyLendMoneyTo;// 出借金额

	private Date backMoneyDayFrom;// 应回息日期
	private Date backMoneyDayTo;// 应回息日期
	
	private String applyDeductMoneyFrom;// 计划划扣金额
	private String applyDeductMoneyTo;// 计划划扣金额
	
	private String applyPay;// 付款方式
	private String productCode;// 出借产品
	private String dictValue;// 卡/折
	
	private String backMoneyStatus;// 回息状态
	private String platformCode;// 回息平台(检索列表中回息表示）
	private String name;// 平台名称
	private String trusteeshipFlag;// 金账户、非金账户标识
	
	private String accountBank;// 回息开户行
	private Date backDayFrom;// 回盘日期
	private Date backDayTo;// 回盘日期
	private String platFlag;// 平台标识
	private String applyAgreementEdition;// 合同版本号
	
	private String backResult;// 回盘结果
	private String matchingFlag;// 匹配标识
	/*
	 * 导出所用属性
	 */
	private String exportType;// 导出格式
	private String exportPlat;// 导出平台
	
	private String isInterest;//  是否回息
	
	public String getMatchingFlag() {
		return matchingFlag;
	}
	public void setMatchingFlag(String matchingFlag) {
		this.matchingFlag = matchingFlag;
	}
	public String getExportType() {
		return exportType;
	}
	public void setExportType(String exportType) {
		this.exportType = exportType;
	}
	public String getExportPlat() {
		return exportPlat;
	}
	public void setExportPlat(String exportPlat) {
		this.exportPlat = exportPlat;
	}
	public String getBackResult() {
		return backResult;
	}
	public void setBackResult(String backResult) {
		this.backResult = backResult;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getLendCode() {
		return lendCode;
	}
	public void setLendCode(String lendCode) {
		this.lendCode = lendCode;
	}
	
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getApplyBillday() {
		return applyBillday;
	}
	public void setApplyBillday(String applyBillday) {
		this.applyBillday = applyBillday;
	}
	public String getAddrProvince() {
		return addrProvince;
	}
	public void setAddrProvince(String addrProvince) {
		this.addrProvince = addrProvince;
	}
	public String getAddrCity() {
		return addrCity;
	}
	public void setAddrCity(String addrCity) {
		this.addrCity = addrCity;
	}
	public Date getApplyLendDayFrom() {
		return applyLendDayFrom;
	}
	public void setApplyLendDayFrom(Date applyLendDayFrom) {
		this.applyLendDayFrom = applyLendDayFrom;
	}
	public Date getApplyLendDayTo() {
		return applyLendDayTo;
	}
	public void setApplyLendDayTo(Date applyLendDayTo) {
		this.applyLendDayTo = applyLendDayTo;
	}
	public Date getMatchingExpireDayFrom() {
		return matchingExpireDayFrom;
	}
	public void setMatchingExpireDayFrom(Date matchingExpireDayFrom) {
		this.matchingExpireDayFrom = matchingExpireDayFrom;
	}
	public Date getMatchingExpireDayTo() {
		return matchingExpireDayTo;
	}
	public void setMatchingExpireDayTo(Date matchingExpireDayTo) {
		this.matchingExpireDayTo = matchingExpireDayTo;
	}
	public Date getApplyExpireDayFrom() {
		return applyExpireDayFrom;
	}
	public void setApplyExpireDayFrom(Date applyExpireDayFrom) {
		this.applyExpireDayFrom = applyExpireDayFrom;
	}
	public Date getApplyExpireDayTo() {
		return applyExpireDayTo;
	}
	public void setApplyExpireDayTo(Date applyExpireDayTo) {
		this.applyExpireDayTo = applyExpireDayTo;
	}
	public Date getApplyDeductDayFrom() {
		return applyDeductDayFrom;
	}
	public void setApplyDeductDayFrom(Date applyDeductDayFrom) {
		this.applyDeductDayFrom = applyDeductDayFrom;
	}
	public Date getApplyDeductDayTo() {
		return applyDeductDayTo;
	}
	public void setApplyDeductDayTo(Date applyDeductDayTo) {
		this.applyDeductDayTo = applyDeductDayTo;
	}
	public Date getBackMoneyDayFrom() {
		return backMoneyDayFrom;
	}
	public void setBackMoneyDayFrom(Date backMoneyDayFrom) {
		this.backMoneyDayFrom = backMoneyDayFrom;
	}
	public Date getBackMoneyDayTo() {
		return backMoneyDayTo;
	}
	public void setBackMoneyDayTo(Date backMoneyDayTo) {
		this.backMoneyDayTo = backMoneyDayTo;
	}
	public String getApplyPay() {
		return applyPay;
	}
	public void setApplyPay(String applyPay) {
		this.applyPay = applyPay;
	}
	
	public String getDictValue() {
		return dictValue;
	}
	public void setDictValue(String dictValue) {
		this.dictValue = dictValue;
	}
	public String getBackMoneyStatus() {
		return backMoneyStatus;
	}
	public void setBackMoneyStatus(String backMoneyStatus) {
		this.backMoneyStatus = backMoneyStatus;
	}
	public String getPlatformCode() {
		return platformCode;
	}
	public void setPlatformCode(String platformCode) {
		this.platformCode = platformCode;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getTrusteeshipFlag() {
		if(trusteeshipFlag != null && !trusteeshipFlag.equals("")){
			return trusteeshipFlag;
		}else{
			trusteeshipFlag = YoN.FOU.value;
			return trusteeshipFlag;
		}
	}
	public void setTrusteeshipFlag(String trusteeshipFlag) {
		this.trusteeshipFlag = trusteeshipFlag;
	}
	public String getAccountBank() {
		return accountBank;
	}
	public void setAccountBank(String accountBank) {
		this.accountBank = accountBank;
	}
	public Date getBackDayFrom() {
		return backDayFrom;
	}
	public void setBackDayFrom(Date backDayFrom) {
		this.backDayFrom = backDayFrom;
	}
	public Date getBackDayTo() {
		return backDayTo;
	}
	public void setBackDayTo(Date backDayTo) {
		this.backDayTo = backDayTo;
	}
	public String getApplyAgreementEdition() {
		return applyAgreementEdition;
	}
	public void setApplyAgreementEdition(String applyAgreementEdition) {
		this.applyAgreementEdition = applyAgreementEdition;
	}
	public String getApplyDeductMoneyFrom() {
		return applyDeductMoneyFrom;
	}
	public void setApplyDeductMoneyFrom(String applyDeductMoneyFrom) {
		this.applyDeductMoneyFrom = applyDeductMoneyFrom;
	}
	public String getApplyDeductMoneyTo() {
		return applyDeductMoneyTo;
	}
	public void setApplyDeductMoneyTo(String applyDeductMoneyTo) {
		this.applyDeductMoneyTo = applyDeductMoneyTo;
	}
	
	public BigDecimal getApplyLendMoneyFrom() {
		return applyLendMoneyFrom;
	}
	public void setApplyLendMoneyFrom(BigDecimal applyLendMoneyFrom) {
		this.applyLendMoneyFrom = applyLendMoneyFrom;
	}
	public BigDecimal getApplyLendMoneyTo() {
		return applyLendMoneyTo;
	}
	public void setApplyLendMoneyTo(BigDecimal applyLendMoneyTo) {
		this.applyLendMoneyTo = applyLendMoneyTo;
	}
	public Double getHkMoneyFrom() {
		return Double.valueOf(applyDeductMoneyFrom);
	}
	public Double getHkMoneyTo() {
		return Double.valueOf(applyDeductMoneyTo);
	}
	public Date getCurrentBillday() {
		return currentBillday;
	}
	public void setCurrentBillday(Date currentBillday) {
		this.currentBillday = currentBillday;
	}
	public String getIsInterest() {
		return isInterest;
	}
	public void setIsInterest(String isInterest) {
		this.isInterest = isInterest;
	}
	
	
}