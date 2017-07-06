package com.creditharmony.fortune.borrow.view;

import java.math.BigDecimal;

import com.creditharmony.core.persistence.DataEntity;

/**
 * 债权分配的封装
 * @author zhoujunAllot
 *
 */
@SuppressWarnings("serial")
public class BorrowAllotView extends DataEntity<BorrowAllotView>{
	private String creditValueId;
	
	private BigDecimal borrowCreditValue;
	
	private BigDecimal allotMoney;
	
	private BigDecimal surplusMoney;

	private String creditMonId;
	
	private String borrowVerTime;
	
	private String borrowMonthVerTime;
	
	public BigDecimal getAllotMoney() {
		return allotMoney;
	}
	public void setAllotMoney(BigDecimal allotMoney) {
		this.allotMoney = allotMoney;
	}
	public BigDecimal getBorrowCreditValue() {
		return borrowCreditValue;
	}
	public void setBorrowCreditValue(BigDecimal borrowCreditValue) {
		this.borrowCreditValue = borrowCreditValue;
	}
	public String getCreditValueId() {
		return creditValueId;
	}
	public void setCreditValueId(String creditValueId) {
		this.creditValueId = creditValueId;
	}
	public BigDecimal getSurplusMoney() {
		return surplusMoney;
	}
	public void setSurplusMoney(BigDecimal surplusMoney) {
		this.surplusMoney = surplusMoney;
	}
	public String getBorrowMonthVerTime() {
		return borrowMonthVerTime;
	}
	public void setBorrowMonthVerTime(String borrowMonthVerTime) {
		this.borrowMonthVerTime = borrowMonthVerTime;
	}
	public String getBorrowVerTime() {
		return borrowVerTime;
	}
	public void setBorrowVerTime(String borrowVerTime) {
		this.borrowVerTime = borrowVerTime;
	}
	public String getCreditMonId() {
		return creditMonId;
	}
	public void setCreditMonId(String creditMonId) {
		this.creditMonId = creditMonId;
	}
	
}
