package com.creditharmony.fortune.creditor.matching.view;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 月满盈可用债权既有历史债权
 * @Class Name CreditorTradeMonthAbleView
 * @author 柳慧
 * @Create In 2015年12月30日
 */
public class CreditorTradeMonthAbleView {
	private String creditMonableId;

    private String creditMonId;

    private String loanCode;

    private String loanId;

    private String loanName;

    private String loanIdcard;

    private String loanJob;

    private String loanProduct;

    private String loanPurpose;

    private String dictLoanType;

    private Date loanOutmoneyDay;

    private Date loanBackmoneyFirday;

    private Date loanCreditDayUsable; // 债权可用日期

    private Integer loanDay;// 借款天数

    private Integer loanAvailableDays;// 可用天数

    private Date loanBackmoneyDay;

    private BigDecimal loanMonthRate;

    private BigDecimal loanCreditValue;

    private BigDecimal loanAvailabeValue;

    private BigDecimal loanValueYear;

    private String dictLoanFreeFlag;

    private Date loanModifiedDay;

    private Date loanFreezeDay;

    private String loanCarNumber;
    
    private String loanMiddleMan;
    
    private BigDecimal tradeBorrowdaysActual;  // 实际出借天数
    
    private BigDecimal tradeMateMoney; // 匹配金额
    
    private BigDecimal productRate; // 月满盈利率
    
    
	public BigDecimal getProductRate() {
		return productRate;
	}
	public void setProductRate(BigDecimal productRate) {
		this.productRate = productRate;
	}
	public BigDecimal getTradeMateMoney() {
		return tradeMateMoney;
	}
	public void setTradeMateMoney(BigDecimal tradeMateMoney) {
		this.tradeMateMoney = tradeMateMoney;
	}
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
	public String getLoanCode() {
		return loanCode;
	}
	public void setLoanCode(String loanCode) {
		this.loanCode = loanCode;
	}
	public String getLoanId() {
		return loanId;
	}
	public void setLoanId(String loanId) {
		this.loanId = loanId;
	}
	public String getLoanName() {
		return loanName;
	}
	public void setLoanName(String loanName) {
		this.loanName = loanName;
	}
	public String getLoanIdcard() {
		return loanIdcard;
	}
	public void setLoanIdcard(String loanIdcard) {
		this.loanIdcard = loanIdcard;
	}
	public String getLoanJob() {
		return loanJob;
	}
	public void setLoanJob(String loanJob) {
		this.loanJob = loanJob;
	}
	public String getLoanProduct() {
		return loanProduct;
	}
	public void setLoanProduct(String loanProduct) {
		this.loanProduct = loanProduct;
	}
	public String getLoanPurpose() {
		return loanPurpose;
	}
	public void setLoanPurpose(String loanPurpose) {
		this.loanPurpose = loanPurpose;
	}
	public String getDictLoanType() {
		return dictLoanType;
	}
	public void setDictLoanType(String dictLoanType) {
		this.dictLoanType = dictLoanType;
	}
	public Date getLoanOutmoneyDay() {
		return loanOutmoneyDay;
	}
	public void setLoanOutmoneyDay(Date loanOutmoneyDay) {
		this.loanOutmoneyDay = loanOutmoneyDay;
	}
	public Date getLoanBackmoneyFirday() {
		return loanBackmoneyFirday;
	}
	public void setLoanBackmoneyFirday(Date loanBackmoneyFirday) {
		this.loanBackmoneyFirday = loanBackmoneyFirday;
	}
	public Date getLoanCreditDayUsable() {
		return loanCreditDayUsable;
	}
	public void setLoanCreditDayUsable(Date loanCreditDayUsable) {
		this.loanCreditDayUsable = loanCreditDayUsable;
	}
	public Integer getLoanDay() {
		return loanDay;
	}
	public void setLoanDay(Integer loanDay) {
		this.loanDay = loanDay;
	}
	public Integer getLoanAvailableDays() {
		return loanAvailableDays;
	}
	public void setLoanAvailableDays(Integer loanAvailableDays) {
		this.loanAvailableDays = loanAvailableDays;
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
	public String getDictLoanFreeFlag() {
		return dictLoanFreeFlag;
	}
	public void setDictLoanFreeFlag(String dictLoanFreeFlag) {
		this.dictLoanFreeFlag = dictLoanFreeFlag;
	}
	public Date getLoanModifiedDay() {
		return loanModifiedDay;
	}
	public void setLoanModifiedDay(Date loanModifiedDay) {
		this.loanModifiedDay = loanModifiedDay;
	}
	public Date getLoanFreezeDay() {
		return loanFreezeDay;
	}
	public void setLoanFreezeDay(Date loanFreezeDay) {
		this.loanFreezeDay = loanFreezeDay;
	}
	public String getLoanCarNumber() {
		return loanCarNumber;
	}
	public void setLoanCarNumber(String loanCarNumber) {
		this.loanCarNumber = loanCarNumber;
	}
	public String getLoanMiddleMan() {
		return loanMiddleMan;
	}
	public void setLoanMiddleMan(String loanMiddleMan) {
		this.loanMiddleMan = loanMiddleMan;
	}
	public BigDecimal getTradeBorrowdaysActual() {
		return tradeBorrowdaysActual;
	}
	public void setTradeBorrowdaysActual(BigDecimal tradeBorrowdaysActual) {
		this.tradeBorrowdaysActual = tradeBorrowdaysActual;
	}
   
	
	
}