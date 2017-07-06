package com.creditharmony.fortune.borrow.view;

import java.math.BigDecimal;

import com.creditharmony.core.persistence.DataEntity;

/**
 * 月满盈可用债权回池
 * @Class Name BorrowMonthableBackToolView
 * @author 周俊
 * @Create In 2015年12月7日
 */
@SuppressWarnings("serial")
public class BorrowMonthableBackToolView extends DataEntity<BorrowMonthableBackToolView>{
    private String creditMonableId;
    
    private String creditMonId;
	
    private BigDecimal beforeReleaseBorrow;
	
	private BigDecimal beforeReleaseBorrowRate;
	
	private BigDecimal afterReleaseBorrow;
	
	private BigDecimal afterReleaseBorrowRate;
	
	private String borrowMonthableVerTime;
	
	private String borrowMonthVerTime;
	
	public String getCreditMonableId() {
		return creditMonableId;
	}

	public void setCreditMonableId(String creditMonableId) {
		this.creditMonableId = creditMonableId;
	}

	public String getCreditMonId() {
		return creditMonId;
	}

	public void setCreditMonId(String creditMonId) {
		this.creditMonId = creditMonId;
	}

	public BigDecimal getBeforeReleaseBorrow() {
		return beforeReleaseBorrow;
	}

	public void setBeforeReleaseBorrow(BigDecimal beforeReleaseBorrow) {
		this.beforeReleaseBorrow = beforeReleaseBorrow;
	}

	public BigDecimal getBeforeReleaseBorrowRate() {
		return beforeReleaseBorrowRate;
	}

	public void setBeforeReleaseBorrowRate(BigDecimal beforeReleaseBorrowRate) {
		this.beforeReleaseBorrowRate = beforeReleaseBorrowRate;
	}

	public BigDecimal getAfterReleaseBorrow() {
		return afterReleaseBorrow;
	}

	public void setAfterReleaseBorrow(BigDecimal afterReleaseBorrow) {
		this.afterReleaseBorrow = afterReleaseBorrow;
	}

	public BigDecimal getAfterReleaseBorrowRate() {
		return afterReleaseBorrowRate;
	}

	public void setAfterReleaseBorrowRate(BigDecimal afterReleaseBorrowRate) {
		this.afterReleaseBorrowRate = afterReleaseBorrowRate;
	}

	public String getBorrowMonthableVerTime() {
		return borrowMonthableVerTime;
	}

	public void setBorrowMonthableVerTime(String borrowMonthableVerTime) {
		this.borrowMonthableVerTime = borrowMonthableVerTime;
	}

	public String getBorrowMonthVerTime() {
		return borrowMonthVerTime;
	}

	public void setBorrowMonthVerTime(String borrowMonthVerTime) {
		this.borrowMonthVerTime = borrowMonthVerTime;
	}
	

}
