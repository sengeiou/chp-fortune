package com.creditharmony.fortune.template.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.creditharmony.core.excel.annotation.ExcelField;
import com.creditharmony.core.fortune.type.CreditSrc;
import com.creditharmony.core.fortune.type.Prof;
import com.creditharmony.fortune.borrow.utils.FormatUtils;

public class BorrowMonthableOutExcel implements Serializable{

	/**
	 * long serialVersionUID 
	 */
	private static final long serialVersionUID = 1L;

	@ExcelField(title="借款人",type=0,align=2)
    private String loanName; // 借款人

	@ExcelField(title="借款人身份证号",type=0,align=2)
    private String loanIdcard;// 借款人身份证号
   
	@ExcelField(title="债权来源",type=0,align=2)
    private String dictLoanType;// 借款来源
   
    @ExcelField(title="借款产品",type=0,align=2)
    private String loanProduct;// 借款产品
    
    @ExcelField(title="借款用途",type=0,align=2)
    private String loanPurpose;// 借款用途

    @ExcelField(title="职业情况",type=0,align=2)
    private String loanJob;// 借款人职业

    @ExcelField(title="首次还款日",type=0,align=2)
    private Date loanBackmoneyFirday;// 首次还款日
    
    @ExcelField(title="债权可用日期",type=0,align=2)
    private Date loanCreditDayUsable; // 债权可用日期
    
    @ExcelField(title="借款天数",type=0,align=2)
    private String loanDay;// 借款天数

    @ExcelField(title="可用天数",type=0,align=2)
    private String loanAvailableDays;// 可用天数
    
    @ExcelField(title="截至还款日期",type=0,align=2)
    private Date loanBackmoneyDay;// 截至还款日

    @ExcelField(title="月利率",type=0,align=2)
    private BigDecimal loanMonthRate;// 月利率

    @ExcelField(title="原始债权价值",type=0,align=2)
    private BigDecimal loanCreditValue;// 原始债权价值

    @ExcelField(title="可用债权价值",type=0,align=2)
    private BigDecimal loanAvailabeValue;// 可用债权价值

    @ExcelField(title="年预计债权收益",type=0,align=2)
    private BigDecimal loanValueYear;// 年预计债权收益

    public String getLoanName() {
		return loanName;
	}

	public void setLoanName(String loanName) {
		this.loanName = loanName;
	}
	public String getLoanIdcard() {
		return FormatUtils.formatLoanIdcard(loanIdcard);
	}

	public void setLoanIdcard(String loanIdcard) {
		this.loanIdcard = loanIdcard;
	}
	
	public String getLoanProduct() {
		return loanProduct;
	}

	public void setLoanProduct(String loanProduct) {
		this.loanProduct = loanProduct;
	}
	public String getLoanJob() {
		return Prof.getProf(loanJob);
	}

	public void setLoanJob(String loanJob) {
		this.loanJob = loanJob;
	}
	public Date getLoanBackmoneyFirday() {
		return loanBackmoneyFirday;
	}

	public void setLoanBackmoneyFirday(Date loanBackmoneyFirday) {
		this.loanBackmoneyFirday = loanBackmoneyFirday;
	}
	
	public Date getLoanBackmoneyDay() {
		return loanBackmoneyDay;
	}

	public void setLoanBackmoneyDay(Date loanBackmoneyDay) {
		this.loanBackmoneyDay = loanBackmoneyDay;
	}
	public BigDecimal getLoanMonthRate() {
		return loanMonthRate;
	}

	public void setLoanMonthRate(BigDecimal loanMonthRate) {
		this.loanMonthRate = loanMonthRate;
	}
	public BigDecimal getLoanCreditValue() {
		return loanCreditValue;
	}

	public void setLoanCreditValue(BigDecimal loanCreditValue) {
		this.loanCreditValue = loanCreditValue;
	}
	public BigDecimal getLoanAvailabeValue() {
		return loanAvailabeValue;
	}

	public void setLoanAvailabeValue(BigDecimal loanAvailabeValue) {
		this.loanAvailabeValue = loanAvailabeValue;
	}
	public BigDecimal getLoanValueYear() {
		return loanValueYear;
	}

	public void setLoanValueYear(BigDecimal loanValueYear) {
		this.loanValueYear = loanValueYear;
	}
	public String getLoanPurpose() {
		return loanPurpose;
	}

	public void setLoanPurpose(String loanPurpose) {
		this.loanPurpose = loanPurpose;
	}
	public String getDictLoanType() {
		//CreditSrc.initCreditSrc();
		return CreditSrc.creditSrcMap.get(dictLoanType);
	}

	public void setDictLoanType(String dictLoanType) {
		this.dictLoanType = dictLoanType;
	}
	public Date getLoanCreditDayUsable() {
		return loanCreditDayUsable;
	}

	public void setLoanCreditDayUsable(Date loanCreditDayUsable) {
		this.loanCreditDayUsable = loanCreditDayUsable;
	}

	public String getLoanDay() {
		return loanDay;
	}

	public void setLoanDay(String loanDay) {
		this.loanDay = loanDay;
	}

	public String getLoanAvailableDays() {
		return loanAvailableDays;
	}

	public void setLoanAvailableDays(String loanAvailableDays) {
		this.loanAvailableDays = loanAvailableDays;
	}

	

}
