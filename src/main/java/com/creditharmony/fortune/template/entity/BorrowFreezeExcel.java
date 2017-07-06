package com.creditharmony.fortune.template.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.creditharmony.core.excel.annotation.ExcelField;
import com.creditharmony.core.fortune.type.CreditSrc;
import com.creditharmony.core.fortune.type.Prof;
import com.creditharmony.core.fortune.type.RepayDay;
import com.creditharmony.core.fortune.type.ZjtrMark;
import com.creditharmony.fortune.borrow.utils.FormatUtils;

/**
 * 冻结列表导出Excel
 * @Class Name BorrowFreezeExcel
 * @author 周俊
 * @Create In 2015年12月24日
 */
public class BorrowFreezeExcel implements Serializable{

	/**
	 * long serialVersionUID 
	 */
	private static final long serialVersionUID = 1L;

	@ExcelField(title="借款人",type=0,align=2)
    private String loanName; // 借款人
	
	@ExcelField(title="借款人身份证号",type=0,align=2)
    private String loanIdcard;
	
	@ExcelField(title="借款类型",type=0,align=2)
    private String dictLoanType;// 借款类型
	
	@ExcelField(title="借款标识",type=0,align=2)
    private String loanTrusteeFlag;// 借款标识
	
	@ExcelField(title="借款产品",type=0,align=2)
    private String loanProduct;// 借款产品
	
	@ExcelField(title="借款人职业",type=0,align=2)
    private String loanJob;// 借款人职业
	
	@ExcelField(title="首次还款日",type=0,align=2)
    private Date loanBackmoneyFirday;// 首次还款日
	
	@ExcelField(title="还款日",type=0,align=2)
    private String loanBakcmoneyDay;// 还款日
	
	@ExcelField(title="借款期数",type=0,align=2)
    private String loanMonths;// 借款期数(天数)
	
	@ExcelField(title="可用期数",type=0,align=2)
    private String loanMonthsSurplus;// 可用期数(天数)
	
	@ExcelField(title="截至还款日",type=0,align=2)
    private Date loanBackmoneyLastday;// 截至还款日
	
	@ExcelField(title="月利率",type=0,align=2)
    private BigDecimal loanMonthRate;// 月利率
	
	@ExcelField(title="原始债权价值",type=0,align=2)
    private BigDecimal loanQuota;// 原始可用债权
	
	@ExcelField(title="可用债权价值",type=0,align=2)
    private BigDecimal loanCreditValue;// 可用债权价值

	@ExcelField(title="债权持有比例",type=0,align=2)
	public String ratio;// 债权持有比例
	
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

	public String getDictLoanType() {
		//CreditSrc.initCreditSrc();
		return CreditSrc.creditSrcMap.get(dictLoanType);
	}

	public void setDictLoanType(String dictLoanType) {
		this.dictLoanType = dictLoanType;
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

	public String getRatio() {
		return ratio;
	}

	public void setRatio(String ratio) {
		this.ratio = ratio;
	}

	public BigDecimal getLoanValueYear() {
		return loanValueYear;
	}

	public void setLoanValueYear(BigDecimal loanValueYear) {
		this.loanValueYear = loanValueYear;
	}

	public String getLoanBakcmoneyDay() {
		//RepayDay.initRepayDay();
		return RepayDay.repayDayMap.get(loanBakcmoneyDay);
	}

	public void setLoanBakcmoneyDay(String loanBakcmoneyDay) {
		this.loanBakcmoneyDay = loanBakcmoneyDay;
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
