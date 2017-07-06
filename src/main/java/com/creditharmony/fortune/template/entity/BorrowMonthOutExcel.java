package com.creditharmony.fortune.template.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.creditharmony.core.excel.annotation.ExcelField;
import com.creditharmony.core.fortune.type.Prof;
import com.creditharmony.core.fortune.type.ZjtrMark;
import com.creditharmony.fortune.borrow.utils.FormatUtils;

public class BorrowMonthOutExcel implements Serializable{

	/**
	 * long serialVersionUID 
	 */
	private static final long serialVersionUID = 1L;
	 @ExcelField(title="借款人",type=0,align=2)
    private String loanName; // 借款人

	 @ExcelField(title="借款人身份证号",type=0,align=2)
    private String loanIdcard;// 借款人身份证号
   
	 @ExcelField(title="借款产品",type=0,align=2)
    private String loanProduct;// 借款产品
    
	 @ExcelField(title="标识",type=0,align=2)
    private String loanTrusteeFlag;// 借款标识
    
	@ExcelField(title="借款用途",type=0,align=2)
    private String loanPurpose;// 借款用途

	 @ExcelField(title="职业情况",type=0,align=2)
    private String loanJob;// 借款人职业

	 @ExcelField(title="首次还款日",type=0,align=2)
    private Date loanBackmoneyFirday;// 首次还款日
    
	 @ExcelField(title="还款日",type=0,align=2)
    private String loanBackmoneyDay;// 还款日

    @ExcelField(title="借款期数",type=0,align=2)
    private String loanMonths;// 借款期数
    
    @ExcelField(title="可用期数",type=0,align=2)
    private String loanMonthsSurplus;// 可用期数

    @ExcelField(title="截止还款日期",type=0,align=2)
    private Date loanEndmoneyDay;// 截至还款日

    @ExcelField(title="月利率",type=0,align=2)
    private BigDecimal loanMonthRate;// 月利率

    @ExcelField(title="分配金额",type=0,align=2)
    private BigDecimal loanCreditValue;// 分配金额

    @ExcelField(title="可用拆分金额",type=0,align=2)
    private BigDecimal loanAvailabeValue;// 可用拆分金额

    @ExcelField(title="年预计债权收益",type=0,align=2)
    private BigDecimal loanValueYear;// 年预计债权收益

   
    public String getLoanBackmoneyDay() {
		return loanBackmoneyDay;
	}

	public void setLoanBackmoneyDay(String loanBackmoneyDay) {
		this.loanBackmoneyDay = loanBackmoneyDay;
	}

	public Date getLoanEndmoneyDay() {
		return loanEndmoneyDay;
	}

	public void setLoanEndmoneyDay(Date loanEndmoneyDay) {
		this.loanEndmoneyDay = loanEndmoneyDay;
	}

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
	
	public String getLoanTrusteeFlag() {
		//ZjtrMark.initZjtrMark();
		return ZjtrMark.zjtrMarkMap.get(loanTrusteeFlag);
	}

	public void setLoanTrusteeFlag(String loanTrusteeFlag) {
		this.loanTrusteeFlag = loanTrusteeFlag;
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
	
	public String getLoanMonths() {
		return loanMonths;
	}

	public void setLoanMonths(String loanMonths) {
		this.loanMonths = loanMonths;
	}

	public String getLoanMonthsSurplus() {
		return loanMonthsSurplus;
	}

	public void setLoanMonthsSurplus(String loanMonthsSurplus) {
		this.loanMonthsSurplus = loanMonthsSurplus;
	}

	

}
