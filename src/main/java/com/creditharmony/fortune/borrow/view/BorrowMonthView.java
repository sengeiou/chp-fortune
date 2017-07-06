package com.creditharmony.fortune.borrow.view;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 封装月满盈债权多条件查询
 * @Class Name BorrowMonthView
 * @author 周俊
 * @Create In 2015年11月30日
 */

public class BorrowMonthView implements Serializable{

	/**
	 * long serialVersionUID 
	 */
	private static final long serialVersionUID = 1L;

	private String borrowerName;// 借款人姓名
	
	private BigDecimal borrowAvailabeValueFrom;// 可用金额
	
	private BigDecimal borrowAvailabeValueTo;// 可用金额
	
	private Integer borrowDaysSurplusFrom;// 可用期数
	
	private Integer borrowDaysSurplusTo;// 可用期数
	
	private BigDecimal borrowMonthRate;// 月利率
	
	private Date borrowBackmoneyFirdayFrom;// 首次还款日
	
	private Date borrowBackmoneyFirdayTo;// 首次还款日
	
	private String borrowerJob;// 职业
	
	private String borrowTrusteeFlag;// 债权标识
	
	private String dicLoanDistinguish; // 债权区分

	public String getBorrowerName() {
		return borrowerName;
	}

	public void setBorrowerName(String borrowerName) {
		this.borrowerName = borrowerName;
	}

	public BigDecimal getBorrowAvailabeValueFrom() {
		return borrowAvailabeValueFrom;
	}

	public void setBorrowAvailabeValueFrom(BigDecimal borrowAvailabeValueFrom) {
		this.borrowAvailabeValueFrom = borrowAvailabeValueFrom;
	}

	public BigDecimal getBorrowAvailabeValueTo() {
		return borrowAvailabeValueTo;
	}

	public void setBorrowAvailabeValueTo(BigDecimal borrowAvailabeValueTo) {
		this.borrowAvailabeValueTo = borrowAvailabeValueTo;
	}

	public Integer getBorrowDaysSurplusFrom() {
		return borrowDaysSurplusFrom;
	}

	public void setBorrowDaysSurplusFrom(Integer borrowDaysSurplusFrom) {
		this.borrowDaysSurplusFrom = borrowDaysSurplusFrom;
	}

	public Integer getBorrowDaysSurplusTo() {
		return borrowDaysSurplusTo;
	}

	public void setBorrowDaysSurplusTo(Integer borrowDaysSurplusTo) {
		this.borrowDaysSurplusTo = borrowDaysSurplusTo;
	}

	public BigDecimal getBorrowMonthRate() {
		return borrowMonthRate;
	}

	public void setBorrowMonthRate(BigDecimal borrowMonthRate) {
		this.borrowMonthRate = borrowMonthRate;
	}

	public Date getBorrowBackmoneyFirdayFrom() {
		return borrowBackmoneyFirdayFrom;
	}

	public void setBorrowBackmoneyFirdayFrom(Date borrowBackmoneyFirdayFrom) {
		this.borrowBackmoneyFirdayFrom = borrowBackmoneyFirdayFrom;
	}

	public Date getBorrowBackmoneyFirdayTo() {
		return borrowBackmoneyFirdayTo;
	}

	public void setBorrowBackmoneyFirdayTo(Date borrowBackmoneyFirdayTo) {
		this.borrowBackmoneyFirdayTo = borrowBackmoneyFirdayTo;
	}

	public String getBorrowerJob() {
		return borrowerJob;
	}

	public void setBorrowerJob(String borrowerJob) {
		this.borrowerJob = borrowerJob;
	}

	public String getBorrowTrusteeFlag() {
		return borrowTrusteeFlag;
	}

	public void setBorrowTrusteeFlag(String borrowTrusteeFlag) {
		this.borrowTrusteeFlag = borrowTrusteeFlag;
	}

	public String getDicLoanDistinguish() {
		return dicLoanDistinguish;
	}

	public void setDicLoanDistinguish(String dicLoanDistinguish) {
		this.dicLoanDistinguish = dicLoanDistinguish;
	}

	
	
}
