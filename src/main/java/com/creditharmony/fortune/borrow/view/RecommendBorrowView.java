package com.creditharmony.fortune.borrow.view;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 可用债权查看
 * @Class Name RecommendBorrow
 * @author 周俊
 * @Create In 2015年12月22日
 */
public class RecommendBorrowView implements Serializable{

	/**
	 * long serialVersionUID 
	 */
	private static final long serialVersionUID = 1L;
	
	private String loanName;// 借款人
	private Integer loanMonthsSurplus;// 可用期数
	private Integer loanMonthsSurplus1;// 可用期数
	private String loanType;//债权来源
	private BigDecimal loanMonthRate;// 月利率
	private BigDecimal loanAvailabeValue;// 可用债权价值
	private BigDecimal loanAvailabeValue1;// 可用债权价值
	private Date loanBackmoneyDay;// 最后一期还款日
	public String getLoanName() {
		return loanName;
	}
	public void setLoanName(String loanName) {
		this.loanName = loanName;
	}
	public Integer getLoanMonthsSurplus() {
		return loanMonthsSurplus;
	}
	public void setLoanMonthsSurplus(Integer loanMonthsSurplus) {
		this.loanMonthsSurplus = loanMonthsSurplus;
	}
	public Integer getLoanMonthsSurplus1() {
		return loanMonthsSurplus1;
	}
	public void setLoanMonthsSurplus1(Integer loanMonthsSurplus1) {
		this.loanMonthsSurplus1 = loanMonthsSurplus1;
	}
	public String getLoanType() {
		return loanType;
	}
	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}
	public BigDecimal getLoanMonthRate() {
		return loanMonthRate;
	}
	public void setLoanMonthRate(BigDecimal loanMonthRate) {
		this.loanMonthRate = loanMonthRate;
	}
	public BigDecimal getLoanAvailabeValue() {
		return loanAvailabeValue;
	}
	public void setLoanAvailabeValue(BigDecimal loanAvailabeValue) {
		this.loanAvailabeValue = loanAvailabeValue;
	}
	public BigDecimal getLoanAvailabeValue1() {
		return loanAvailabeValue1;
	}
	public void setLoanAvailabeValue1(BigDecimal loanAvailabeValue1) {
		this.loanAvailabeValue1 = loanAvailabeValue1;
	}
	public Date getLoanBackmoneyDay() {
		return loanBackmoneyDay;
	}
	public void setLoanBackmoneyDay(Date loanBackmoneyDay) {
		this.loanBackmoneyDay = loanBackmoneyDay;
	}
	
	
	
}
