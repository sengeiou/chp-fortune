package com.creditharmony.fortune.borrow.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.creditharmony.core.persistence.DataEntity;

public class BorrowMonth extends DataEntity<BorrowMonth>{
    /**
	 * long serialVersionUID 
	 */
	private static final long serialVersionUID = 1L;

	private String creditMonId;

    private String creditValueId;

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

    private Date loanCreditDayUsable;

    private Integer loanDay;

    private Integer loanAvailableDays;

    private Integer loanBackmoneyDay;
   
    private Date loanEndmoneyDay;
    
    private BigDecimal loanMonthRate;

    private BigDecimal loanCreditValue;

    private BigDecimal loanAvailabeValue;

    private BigDecimal loanValueYear;

    private Integer loanMonths;

    private Integer loanMonthsSurplus;

    private String dictLoanFreeFlag;

    private String loanTrusteeFlag;

    private Date loanModifiedDay;

    private Date loanFreezeDay;

    private String loanCarNumber;
    
    private String loanMiddleMan;
    
    private String dicLoanDistinguish; // 债权区分

	private String createBy;

    private Date createTime;

    private String modifyBy;

    private Date modifyTime;

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

	public Integer getLoanMonths() {
		return loanMonths;
	}

	public void setLoanMonths(Integer loanMonths) {
		this.loanMonths = loanMonths;
	}

	public Integer getLoanMonthsSurplus() {
		return loanMonthsSurplus;
	}

	public void setLoanMonthsSurplus(Integer loanMonthsSurplus) {
		this.loanMonthsSurplus = loanMonthsSurplus;
	}

	public String getDictLoanFreeFlag() {
		return dictLoanFreeFlag;
	}

	public void setDictLoanFreeFlag(String dictLoanFreeFlag) {
		this.dictLoanFreeFlag = dictLoanFreeFlag;
	}

	public String getLoanTrusteeFlag() {
		return loanTrusteeFlag;
	}

	public void setLoanTrusteeFlag(String loanTrusteeFlag) {
		this.loanTrusteeFlag = loanTrusteeFlag;
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

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getModifyBy() {
		return modifyBy;
	}

	public void setModifyBy(String modifyBy) {
		this.modifyBy = modifyBy;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public Date getLoanEndmoneyDay() {
		return loanEndmoneyDay;
	}

	public void setLoanEndmoneyDay(Date loanEndmoneyDay) {
		this.loanEndmoneyDay = loanEndmoneyDay;
	}

	public Integer getLoanBackmoneyDay() {
		return loanBackmoneyDay;
	}

	public void setLoanBackmoneyDay(Integer loanBackmoneyDay) {
		this.loanBackmoneyDay = loanBackmoneyDay;
	}

	public String getDicLoanDistinguish() {
		return dicLoanDistinguish;
	}

	public void setDicLoanDistinguish(String dicLoanDistinguish) {
		this.dicLoanDistinguish = dicLoanDistinguish;
	}

	public String getLoanMiddleMan() {
		return loanMiddleMan;
	}

	public void setLoanMiddleMan(String loanMiddleMan) {
		this.loanMiddleMan = loanMiddleMan;
	}
    

	

   
}