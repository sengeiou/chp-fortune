package com.creditharmony.fortune.borrow.view;

import java.math.BigDecimal;

import com.creditharmony.core.persistence.DataEntity;

/**
 * 封装月满盈回池
 * @Class Name BorrowMonthBackToolView
 * @author 周俊
 * @Create In 2015年12月1日
 */
@SuppressWarnings("serial")
public class BorrowMonthBackToolView extends DataEntity<BorrowMonthBackToolView>{

	private String creditMonId;//月满盈债权id
	
	private String creditValueId;// 可用债权id 
	
	private BigDecimal loanAvailabeValue;//可用债权价值
	
	private BigDecimal backToolMoney;//回池金额
	
	private BigDecimal surplusBorrowCreditValue;//剩余可用债权价值
	
	private String borrowVerTime;
	
	private String borrowMonthVerTime;

	public String getCreditMonId() {
		return creditMonId;
	}

	public void setCreditMonId(String creditMonId) {
		this.creditMonId = creditMonId;
	}

	public String getCreditValueId() {
		return creditValueId;
	}

	public void setCreditValueId(String creditValueId) {
		this.creditValueId = creditValueId;
	}

	
	public BigDecimal getBackToolMoney() {
		return backToolMoney;
	}

	public void setBackToolMoney(BigDecimal backToolMoney) {
		this.backToolMoney = backToolMoney;
	}

	public BigDecimal getSurplusBorrowCreditValue() {
		return surplusBorrowCreditValue;
	}

	public void setSurplusBorrowCreditValue(BigDecimal surplusBorrowCreditValue) {
		this.surplusBorrowCreditValue = surplusBorrowCreditValue;
	}

	public BigDecimal getLoanAvailabeValue() {
		return loanAvailabeValue;
	}

	public void setLoanAvailabeValue(BigDecimal loanAvailabeValue) {
		this.loanAvailabeValue = loanAvailabeValue;
	}

	public String getBorrowVerTime() {
		return borrowVerTime;
	}

	public void setBorrowVerTime(String borrowVerTime) {
		this.borrowVerTime = borrowVerTime;
	}

	public String getBorrowMonthVerTime() {
		return borrowMonthVerTime;
	}

	public void setBorrowMonthVerTime(String borrowMonthVerTime) {
		this.borrowMonthVerTime = borrowMonthVerTime;
	}

	
}
