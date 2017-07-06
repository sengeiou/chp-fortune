package com.creditharmony.fortune.template.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.creditharmony.common.util.DateUtils;
import com.creditharmony.core.excel.annotation.ExcelField;
import com.creditharmony.core.fortune.type.CreditSrc;
import com.creditharmony.core.fortune.type.ZjtrMark;
import com.creditharmony.core.loan.type.ProfType;
import com.creditharmony.fortune.borrow.utils.FormatUtils;
import com.creditharmony.fortune.borrow.utils.ReckonUtils;
import com.creditharmony.fortune.constants.BorrowConstant;

public class BorrowOutExcel implements Serializable{

	/**
	 * long serialVersionUID 
	 */
	private static final long serialVersionUID = 1L;
	@ExcelField(title="借款人",type=0,align=2)
    private String loanName; // 借款人
	
	@ExcelField(title="借款人身份证号",type=0,align=2)
    private String loanIdcard;
	
	@ExcelField(title="债权来源",type=0,align=2)
    private String dictLoanType;// 借款类型
	
	@ExcelField(title="债权标识",type=0,align=2)
    private String loanTrusteeFlag;// 借款标识
	
	@ExcelField(title="借款产品",type=0,align=2)
    private String loanProduct;// 借款产品
	
	@ExcelField(title="职业情况",type=0,align=2)
    private String loanJob;// 借款人职业
	
    private Date loanBackmoneyFirday;// 首次还款日
	
    @ExcelField(title="首次还款日",type=0,align=2)
	@SuppressWarnings("unused")
	private String formatLoanBackmoneyFirday;
	
	@ExcelField(title="还款日",type=0,align=2)
    private Integer loanBackmoneyDay;// 还款日
	
	@ExcelField(title="借款期数",type=0,align=2)
    private Integer loanMonths;// 借款期数

	@ExcelField(title="可用期数",type=0,align=2)
    private Integer loanMonthsSurplus;// 可用期数
	
    private Date loanBackmoneyLastday;// 截至还款日
	
    @ExcelField(title="截至还款日",type=0,align=2)
	@SuppressWarnings("unused")
	private String formatLoanBackmoneyLastday; 
	
	@ExcelField(title="月利率",type=0,align=2)
    private BigDecimal loanMonthRate;// 月利率
	
	@ExcelField(title="原始债权价值",type=0,align=2)
    private BigDecimal loanQuota;// 原始可用债权
	
	@ExcelField(title="可用债权价值",type=0,align=2)
    private BigDecimal loanCreditValue;// 可用债权价值
	
	@ExcelField(title="占用债权价值",type=0,align=2)
	private BigDecimal occupyLoanCreditValue;
	
	@ExcelField(title="年预计债权收益",type=0,align=2)
    private BigDecimal loanValueYear;// 年预计债权收益
	
	@ExcelField(title="债权持有比例",type=0,align=2)
	private String borrowRatio;
	
	@ExcelField(title="债权转让比例",type=0,align=2)
	private String attornBorrowRatio;
	
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
		return ZjtrMark.zjtrMarkMap.get(loanTrusteeFlag);
	}

	public void setLoanTrusteeFlag(String loanTrusteeFlag) {
		this.loanTrusteeFlag = loanTrusteeFlag;
	}
	
	public BigDecimal getOccupyLoanCreditValue() {
		return loanQuota.subtract(loanCreditValue);
	}

	public void setOccupyLoanCreditValue(BigDecimal occupyLoanCreditValue) {
		this.occupyLoanCreditValue = occupyLoanCreditValue;
	}

	public String getDictLoanType() {
		return CreditSrc.creditSrcMap.get(dictLoanType);
	}
	
	public void setDictLoanType(String dictLoanType) {
		this.dictLoanType = dictLoanType;
	}
	
	public String getLoanProduct() {
		return loanProduct;
	}

	public void setLoanProduct(String loanProduct) {
		this.loanProduct = loanProduct;
	}
	public String getLoanJob() {
		return ProfType.parseByCode(loanJob).getName();
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
	public Date getLoanBackmoneyLastday() {
		return loanBackmoneyLastday;
	}

	public void setLoanBackmoneyLastday(Date loanBackmoneyLastday) {
		this.loanBackmoneyLastday = loanBackmoneyLastday;
	}
	public BigDecimal getLoanMonthRate() {
		return loanMonthRate;
	}
	
	public void setLoanMonthRate(BigDecimal loanMonthRate) {
		this.loanMonthRate = loanMonthRate;
	}
	public BigDecimal getLoanQuota() {
		return loanQuota;
	}

	public void setLoanQuota(BigDecimal loanQuota) {
		this.loanQuota = loanQuota;
	}
	public BigDecimal getLoanCreditValue() {
		return loanCreditValue;
	}

	public void setLoanCreditValue(BigDecimal loanCreditValue) {
		this.loanCreditValue = loanCreditValue;
	}
	public BigDecimal getLoanValueYear() {
		
		return loanValueYear;
	}

	public void setLoanValueYear(BigDecimal loanValueYear) {
		this.loanValueYear = loanValueYear;
	}

	public String getBorrowRatio() {
		return ReckonUtils.percentage(loanCreditValue, loanQuota);
	}

	public void setBorrowRatio(String borrowRatio) {
		this.borrowRatio = borrowRatio;
	}

	public String getAttornBorrowRatio() {
		if (getOccupyLoanCreditValue().compareTo(new BigDecimal(0))==0) {
			return BorrowConstant.PERCENTAGE;
		}
		return ReckonUtils.percentage(getOccupyLoanCreditValue(), loanQuota);
	}

	public void setAttornBorrowRatio(String attornBorrowRatio) {
		this.attornBorrowRatio = attornBorrowRatio;
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
	public Integer getLoanBackmoneyDay() {
		return loanBackmoneyDay;
	}

	public void setLoanBackmoneyDay(Integer loanBackmoneyDay) {
		this.loanBackmoneyDay = loanBackmoneyDay;
	}

	public String getFormatLoanBackmoneyFirday() {
		return DateUtils.formatDate(loanBackmoneyFirday,"yyyy-MM-dd");
	}

	public void setFormatLoanBackmoneyFirday(String formatLoanBackmoneyFirday) {
		this.formatLoanBackmoneyFirday = formatLoanBackmoneyFirday;
	}

	public String getFormatLoanBackmoneyLastday() {
		return DateUtils.formatDate(loanBackmoneyLastday, "yyyy-MM-dd");
	}

	public void setFormatLoanBackmoneyLastday(String formatLoanBackmoneyLastday) {
		this.formatLoanBackmoneyLastday = formatLoanBackmoneyLastday;
	}

}
