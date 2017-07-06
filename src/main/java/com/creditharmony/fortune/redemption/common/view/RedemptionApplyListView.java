package com.creditharmony.fortune.redemption.common.view;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 赎回相关视图封装Bean
 * @Class Name RedemptionApplyListView
 * @author 陈广鹏
 * @Create In 2015年12月2日
 */
public class RedemptionApplyListView {

	private String customerName;		// 客户姓名
	private String customerCode;		// 客户编号

	private String lendCode;			// 出借编号
	private Date applyLendDay;			// 计划出借日期
	private Date applyLendDayTo;
	private Date applyDeductDay;		// 计划划扣日期
	private Date applyDeductDayTo;
	private Date applyExpireDay;		// 到期日
	private Date applyExpireDayTo;		// 到期日

	private String applyBillday;		// 账单日
	private BigDecimal applyLendMoney;		// 计划出借金额
	private BigDecimal applyLendMoneyTo;	// 计划出借金额
	private String applyPay;			// 付款方式
	private String dictApplyDeductType;	// 划扣平台

	private String productName;			// 出借产品名称
	private String productCode;			// 出借产品ID(多)

	private String userName;			// 理财经理姓名
	private String managerCode;			// 理财经理ID

	private String addrProvince;		// 通讯地址省
	private String addrCity;			// 通讯地址市

	private String orgName;				// 机构名
	private String orgID;				// 营业部(多)

	private String redemptionStatus;	// 赎回状态

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	public String getLendCode() {
		return lendCode;
	}

	public void setLendCode(String lendCode) {
		this.lendCode = lendCode;
	}

	public Date getApplyLendDay() {
		return applyLendDay;
	}

	public void setApplyLendDay(Date applyLendDay) {
		this.applyLendDay = applyLendDay;
	}

	public Date getApplyLendDayTo() {
		return applyLendDayTo;
	}

	public void setApplyLendDayTo(Date applyLendDayTo) {
		this.applyLendDayTo = applyLendDayTo;
	}

	public Date getApplyDeductDay() {
		return applyDeductDay;
	}

	public void setApplyDeductDay(Date applyDeductDay) {
		this.applyDeductDay = applyDeductDay;
	}

	public Date getApplyDeductDayTo() {
		return applyDeductDayTo;
	}

	public void setApplyDeductDayTo(Date applyDeductDayTo) {
		this.applyDeductDayTo = applyDeductDayTo;
	}

	public Date getApplyExpireDay() {
		return applyExpireDay;
	}

	public void setApplyExpireDay(Date applyExpireDay) {
		this.applyExpireDay = applyExpireDay;
	}

	public Date getApplyExpireDayTo() {
		return applyExpireDayTo;
	}

	public void setApplyExpireDayTo(Date applyExpireDayTo) {
		this.applyExpireDayTo = applyExpireDayTo;
	}

	public String getApplyBillday() {
		return applyBillday;
	}

	public void setApplyBillday(String applyBillday) {
		this.applyBillday = applyBillday;
	}

	public BigDecimal getApplyLendMoney() {
		return applyLendMoney;
	}

	public void setApplyLendMoney(BigDecimal applyLendMoney) {
		this.applyLendMoney = applyLendMoney;
	}

	public BigDecimal getApplyLendMoneyTo() {
		return applyLendMoneyTo;
	}

	public void setApplyLendMoneyTo(BigDecimal applyLendMoneyTo) {
		this.applyLendMoneyTo = applyLendMoneyTo;
	}

	public String getApplyPay() {
		return applyPay;
	}

	public void setApplyPay(String applyPay) {
		this.applyPay = applyPay;
	}

	public String getDictApplyDeductType() {
		return dictApplyDeductType;
	}

	public void setDictApplyDeductType(String dictApplyDeductType) {
		this.dictApplyDeductType = dictApplyDeductType;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getManagerCode() {
		return managerCode;
	}

	public void setManagerCode(String managerCode) {
		this.managerCode = managerCode;
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

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getOrgID() {
		return orgID;
	}

	public void setOrgID(String orgID) {
		this.orgID = orgID;
	}

	public String getRedemptionStatus() {
		return redemptionStatus;
	}

	public void setRedemptionStatus(String redemptionStatus) {
		this.redemptionStatus = redemptionStatus;
	}

}
