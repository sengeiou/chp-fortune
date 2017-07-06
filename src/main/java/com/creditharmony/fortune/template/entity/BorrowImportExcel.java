package com.creditharmony.fortune.template.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.creditharmony.core.excel.annotation.ExcelField;

public class BorrowImportExcel implements Serializable{

	/**
	 * long serialVersionUID 
	 */
	private static final long serialVersionUID = 1L;
	
	@ExcelField(title="序号",type=0,align=2)
	private String index;
	
	@ExcelField(title="借款人",type=0,align=2)
    private String loanName; // 借款人
	
	@ExcelField(title="借款人身份证号",type=0,align=2)
    private String loanIdcard;
	
	@ExcelField(title="职业情况",type=0,align=2)
	private String loanJob;// 借款人职业
	
	@ExcelField(title="借款产品",type=0,align=2)
	private String loanProduct;// 借款产品
	
	@ExcelField(title="借款用途",type=0,align=2)
    private String loanPurpose;// 借款用途
	
	@ExcelField(title="债权来源",type=0,align=2)
    private String dictLoanType;// 借款类型
	
	@ExcelField(title="首次还款日",type=0,align=2)
	private Date loanBackmoneyFirday;// 首次还款日
	
    @ExcelField(title="还款日",type=0,align=2)
    private Integer loanBackmoneyDay;// 还款日
	
    @ExcelField(title="最后一期还款日",type=0,align=2)
    private Date loanBackmoneyLastday;// 截至还款日
	
    @ExcelField(title="借款期数",type=0,align=2)
    private Integer loanMonths;// 借款期数

    @ExcelField(title="原始债权价值",type=0,align=2)
    private Double loanQuota;// 原始可用债权
    
    @ExcelField(title="可用债权价值",type=0,align=2)
    private Double loanCreditValue;// 可用债权价值
	
    @ExcelField(title="月利率",type=0,align=2)
    private Double loanMonthRate;// 月利率
 
    @ExcelField(title="中间人",type=0,align=2)
    private String loanMiddleMan;// 中间人
  
    @ExcelField(title="债权标识",type=0,align=2)
    private String loanTrusteeFlag;// 借款标识
    
    @ExcelField(title="债权区分",type=0,align=2)
    private String dicLoanDistinguish; // 债权区分

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
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

	public Date getLoanBackmoneyFirday() {
		return loanBackmoneyFirday;
	}

	public void setLoanBackmoneyFirday(Date loanBackmoneyFirday) {
		this.loanBackmoneyFirday = loanBackmoneyFirday;
	}

	public Integer getLoanBackmoneyDay() {
		return loanBackmoneyDay;
	}

	public void setLoanBackmoneyDay(Integer loanBackmoneyDay) {
		this.loanBackmoneyDay = loanBackmoneyDay;
	}

	public Date getLoanBackmoneyLastday() {
		return loanBackmoneyLastday;
	}

	public void setLoanBackmoneyLastday(Date loanBackmoneyLastday) {
		this.loanBackmoneyLastday = loanBackmoneyLastday;
	}

	public Integer getLoanMonths() {
		return loanMonths;
	}

	public void setLoanMonths(Integer loanMonths) {
		this.loanMonths = loanMonths;
	}

	public Double getLoanQuota() {
		return loanQuota;
	}

	public void setLoanQuota(Double loanQuota) {
		this.loanQuota = loanQuota;
	}

	public Double getLoanCreditValue() {
		return loanCreditValue;
	}

	public void setLoanCreditValue(Double loanCreditValue) {
		this.loanCreditValue = loanCreditValue;
	}

	public Double getLoanMonthRate() {
		return loanMonthRate;
	}

	public void setLoanMonthRate(Double loanMonthRate) {
		this.loanMonthRate = loanMonthRate;
	}

	public String getLoanMiddleMan() {
		return loanMiddleMan;
	}

	public void setLoanMiddleMan(String loanMiddleMan) {
		this.loanMiddleMan = loanMiddleMan;
	}

	public String getLoanTrusteeFlag() {
		return loanTrusteeFlag;
	}

	public void setLoanTrusteeFlag(String loanTrusteeFlag) {
		this.loanTrusteeFlag = loanTrusteeFlag;
	}

	public String getDicLoanDistinguish() {
		return dicLoanDistinguish;
	}

	public void setDicLoanDistinguish(String dicLoanDistinguish) {
		this.dicLoanDistinguish = dicLoanDistinguish;
	}
   
}
