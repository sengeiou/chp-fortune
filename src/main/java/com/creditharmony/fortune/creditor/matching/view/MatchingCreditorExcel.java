package com.creditharmony.fortune.creditor.matching.view;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.creditharmony.core.excel.annotation.ExcelField;
import com.creditharmony.core.fortune.type.BillState;
import com.creditharmony.core.fortune.type.MatchingStatus;
/**
 * 
 * @Class 待债权推荐信息导出实体
 * @author 柳慧
 * @Create In 2015年12月11日
 */
public class MatchingCreditorExcel implements Serializable{

	/**
	 * long serialVersionUID 
	 */
	private static final long serialVersionUID = 1L;
	
	@ExcelField(title="出借编号",type=0,align=2)
	private String lendCode;// 出借编号
	 
	 @ExcelField(title="营业部",type=0,align=2)
	 private String orgName;// 客户所属机构(营业部)
	 
	 @ExcelField(title="客户名称",type=0,align=2)
	 private String customerName;// 客户名称
	 
	 @ExcelField(title="出借产品",type=0,align=2)
	 private String productName;// 产品名称
	 
	 @ExcelField(title="初始出借日期",type=0,align=2)
	 private Date startApplyLendDay;// 初始出借日期
	 
	 @ExcelField(title="初始出借金额",type=0,align=2)
	 private BigDecimal startApplyLendMoney;// 初始出借金额
	 
	 @ExcelField(title="本期推荐金额",type=0,align=2)
	 private BigDecimal matchingBorrowQuota;// 本期推荐金额
	 
	 @ExcelField(title="本期出借日期",type=0,align=2)
	 private Date matchingInterestStart;  // 本期出借日期
	 
	 @ExcelField(title="账单类型",type=0,align=2)
	 private String matchingFirstdayFlag;// 账单类型
	 
	 @ExcelField(title="到期日期",type=0,align=2)
	 private Date applyExpireDay;// 到期日期
	 
	 private String  matchingStatus;//债权状态
	 
	 @ExcelField(title="债权状态",type=0,align=2)
	 private String matchingStatusStr;//债权状态
	 
	 @ExcelField(title="匹配人",type=0,align=2)
	 private String matchingUserName;
	 
	 private String province;// 省份---
	 
	 private String city;// 城市---
	 
	 private String dictApplyDeductType;//划扣平台---
	 
	 private String accountBank;//出借银行---
	 
	 private String applyDeductDay;//计划划扣日期---
	 
	 private String applyPay;//付款方式---
	 

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

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Date getStartApplyLendDay() {
		return startApplyLendDay;
	}

	public void setStartApplyLendDay(Date startApplyLendDay) {
		this.startApplyLendDay = startApplyLendDay;
	}

	public BigDecimal getStartApplyLendMoney() {
		return startApplyLendMoney;
	}

	public void setStartApplyLendMoney(BigDecimal startApplyLendMoney) {
		this.startApplyLendMoney = startApplyLendMoney;
	}

	public BigDecimal getMatchingBorrowQuota() {
		return matchingBorrowQuota;
	}

	public void setMatchingBorrowQuota(BigDecimal matchingBorrowQuota) {
		this.matchingBorrowQuota = matchingBorrowQuota;
	}

	public Date getMatchingInterestStart() {
		return matchingInterestStart;
	}

	public void setMatchingInterestStart(Date matchingInterestStart) {
		this.matchingInterestStart = matchingInterestStart;
	}

	public String getMatchingFirstdayFlag() {
		//BillState.initBillState();
		return BillState.billStateMap.get(matchingFirstdayFlag);
	}

	public void setMatchingFirstdayFlag(String matchingFirstdayFlag) {
		this.matchingFirstdayFlag = matchingFirstdayFlag;
	}

	public Date getApplyExpireDay() {
		return applyExpireDay;
	}

	public void setApplyExpireDay(Date applyExpireDay) {
		this.applyExpireDay = applyExpireDay;
	}

	public String getMatchingStatus() {
		return matchingStatus;
	}

	public void setMatchingStatus(String matchingStatus) {
		this.matchingStatus = matchingStatus;
	}

	public String getMatchingStatusStr() {
		MatchingStatus.initMatchingStatus();
		return MatchingStatus.matchingStatusMap.get(matchingStatus.toString());
	}

	public void setMatchingStatusStr(String matchingStatusStr) {
		this.matchingStatusStr = matchingStatusStr;
	}

	public String getMatchingUserName() {
		return matchingUserName;
	}

	public void setMatchingUserName(String matchingUserName) {
		this.matchingUserName = matchingUserName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDictApplyDeductType() {
		return dictApplyDeductType;
	}

	public void setDictApplyDeductType(String dictApplyDeductType) {
		this.dictApplyDeductType = dictApplyDeductType;
	}

	public String getAccountBank() {
		return accountBank;
	}

	public void setAccountBank(String accountBank) {
		this.accountBank = accountBank;
	}



	public String getApplyDeductDay() {
		return applyDeductDay;
	}

	public void setApplyDeductDay(String applyDeductDay) {
		this.applyDeductDay = applyDeductDay;
	}

	public String getApplyPay() {
		return applyPay;
	}

	public void setApplyPay(String applyPay) {
		this.applyPay = applyPay;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}
	
	 

	 
}