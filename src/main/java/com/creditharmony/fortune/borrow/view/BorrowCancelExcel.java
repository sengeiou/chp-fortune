package com.creditharmony.fortune.borrow.view;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.creditharmony.core.excel.annotation.ExcelField;

/**
 *
 * 撤销列表导出Excel实体
 * 2017年3月22日
 * By 常亚运
 *
 */
public class BorrowCancelExcel implements Serializable{

	/**
	 * long serialVersionUID 
	 */
	private static final long serialVersionUID = 1L;
	
	@ExcelField(title="出借编号",type=0,align=2)
	private String lendCode; // 出借编号
	
	@ExcelField(title="客户姓名",type=0,align=2)
	private String customerName; //客户姓名

	@ExcelField(title="营业部",type=0,align=2)
	private String orgName; //营业部

	@ExcelField(title="计划出借日期",type=0,align=2)
	private Date applyLendDay; //计划出借日期

	@ExcelField(title="计划出借金额",type=0,align=2)
	private BigDecimal applyLendMoney; //计划出借金额

	@ExcelField(title="本期出借日期",type=0,align=2)
	private Date matchingInterestStart; //本期出借日期

	@ExcelField(title="本期待替换金额",type=0,align=2)
	private BigDecimal currentCreditLinesMoney; //本期待替换金额

	@ExcelField(title="本期到期日期",type=0,align=2)
	private Date applyExpireDay; //本期到期日期

	@ExcelField(title="账单日",type=0,align=2)
	private String applyBillday; //账单日

	@ExcelField(title="出借产品",type=0,align=2)
	private String procuctName; //出借产品

	@ExcelField(title="替换日期",type=0,align=2)
	private Date replaceDay; //替换日期

	@ExcelField(title="账单类型",type=0,align=2)
	private String matchingFirstdayFlag; //账单类型

	@ExcelField(title="替换状态",type=0,align=2)
	private String replaceStatus; //替换状态

	public String getLendCode() {
		return lendCode;
	}

	public void setLendCode(String lendCode) {
		this.lendCode = lendCode;
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

	public Date getApplyLendDay() {
		return applyLendDay;
	}

	public void setApplyLendDay(Date applyLendDay) {
		this.applyLendDay = applyLendDay;
	}

	public BigDecimal getApplyLendMoney() {
		return applyLendMoney;
	}

	public void setApplyLendMoney(BigDecimal applyLendMoney) {
		this.applyLendMoney = applyLendMoney;
	}

	public Date getMatchingInterestStart() {
		return matchingInterestStart;
	}

	public void setMatchingInterestStart(Date matchingInterestStart) {
		this.matchingInterestStart = matchingInterestStart;
	}

	public BigDecimal getCurrentCreditLinesMoney() {
		return currentCreditLinesMoney;
	}

	public void setCurrentCreditLinesMoney(BigDecimal currentCreditLinesMoney) {
		this.currentCreditLinesMoney = currentCreditLinesMoney;
	}

	public Date getApplyExpireDay() {
		return applyExpireDay;
	}

	public void setApplyExpireDay(Date applyExpireDay) {
		this.applyExpireDay = applyExpireDay;
	}

	public String getApplyBillday() {
		return applyBillday;
	}

	public void setApplyBillday(String applyBillday) {
		this.applyBillday = applyBillday;
	}

	public String getProcuctName() {
		return procuctName;
	}

	public void setProcuctName(String procuctName) {
		this.procuctName = procuctName;
	}

	public Date getReplaceDay() {
		return replaceDay;
	}

	public void setReplaceDay(Date replaceDay) {
		this.replaceDay = replaceDay;
	}

	public String getMatchingFirstdayFlag() {
		return matchingFirstdayFlag;
	}

	public void setMatchingFirstdayFlag(String matchingFirstdayFlag) {
		this.matchingFirstdayFlag = matchingFirstdayFlag;
	}

	public String getReplaceStatus() {
		return replaceStatus;
	}

	public void setReplaceStatus(String replaceStatus) {
		this.replaceStatus = replaceStatus;
	}
	
	

	
	
	
	
}
