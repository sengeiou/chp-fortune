package com.creditharmony.fortune.borrow.entity.ex;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 封装债权替换的实体类
 * @Class Name BorrowCanceEx
 * @author 周俊
 * @Create In 2015年12月9日
 */
public class BorrowCanceEx implements Serializable{
 
	/**
	 * long serialVersionUID 
	 */
	private static final long serialVersionUID = 159081675105113244L;
	private LoanApplyEx loanApply;
	private BigDecimal currentCreditLines; //本期待推荐金额 
	private BigDecimal primaryCreditLines; //本期推荐金额
	private String matchingId;// 记录每期出借情况
	private Date matchingExpireDay; // 本期结束日期
	private List<BorrowReplaceEx> list = new ArrayList<BorrowReplaceEx>();
	
	public List<BorrowReplaceEx> getList() {
		return list;
	}

	public void setList(List<BorrowReplaceEx> list) {
		this.list = list;
	}

	public String getMatchingId() {
		return matchingId;
	}

	public void setMatchingId(String matchingId) {
		this.matchingId = matchingId;
	}

	public Date getMatchingExpireDay() {
		return matchingExpireDay;
	}

	public void setMatchingExpireDay(Date matchingExpireDay) {
		this.matchingExpireDay = matchingExpireDay;
	}

	public LoanApplyEx getLoanApply() {
		return loanApply;
	}

	public void setLoanApply(LoanApplyEx loanApply) {
		this.loanApply = loanApply;
	}

	public BigDecimal getCurrentCreditLines() {
		return currentCreditLines;
	}

	public void setCurrentCreditLines(BigDecimal currentCreditLines) {
		this.currentCreditLines = currentCreditLines;
	}

	public BigDecimal getPrimaryCreditLines() {
		return primaryCreditLines;
	}

	public void setPrimaryCreditLines(BigDecimal primaryCreditLines) {
		this.primaryCreditLines = primaryCreditLines;
	}

	
}
