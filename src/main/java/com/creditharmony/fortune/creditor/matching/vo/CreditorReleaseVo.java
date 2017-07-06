package com.creditharmony.fortune.creditor.matching.vo;

import java.math.BigDecimal;
import java.util.Date;

import com.creditharmony.fortune.creditor.matching.entity.CreditorRelease;

/**
 * 债权到期释放Vo
 * @author xurongsheng
 * @date 2016年11月23日 下午3:01:16
 *
 */
public class CreditorReleaseVo extends CreditorRelease{

	private static final long serialVersionUID = 5136642221184070282L;

	/** 出借人 */
	private String customerName;
	
	/** 出借人身份证号 */
	private String customerCertNum;
	
	/** 出借编号 */
	private String lendCode;
	
	/** 出借到期释放金额 */
	private BigDecimal lendReleaseCreditValue;
	
	/** 出借到期日 */
	private Date applyExpireDay;
	
	/** 借款ID */
	private String loanCode;
	
	/** 借款人 */
	private String loanName;
	
	/** 借款人身份证号 */
	private String loanIdcard;
	
	/** 债权来源 */
	private String dictLoanType;
	
	/** 债权标识 */
	private String loanTrusteeFlag;
	
	/** 债权区分 */
	private String dicLoanDistinguish;
	
	/** 借款产品 */
	private String loanProduct;
	
	/** 借款用途 */
	private String loanPurpose;
	
	/** 借款期数 */
	private String loanMonths;
	
	/** 剩余期数 */
	private String loanMonthsSurplus;
	
	/** 首次还款日 */
	private Date loanBackmoneyFirday;
	
	/** 原始债权价值 */
	private String loanQuota;
	
	/** 还款日 */
	private String loanBackmoneyDay;
	
	/** 月利率 */
	private String loanMonthRate;
	
	/** 可用债权价值 */
	private BigDecimal loanCreditValue;
	
	/** 提前结清标识 */
	private String tjFlag;
	
	/** 提前结清日期 */
	private Date loanFreezeDay;
	
	/** 债权情况 */
	private String zqState;

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerCertNum() {
		return customerCertNum;
	}

	public void setCustomerCertNum(String customerCertNum) {
		this.customerCertNum = customerCertNum;
	}

	public String getLendCode() {
		return lendCode;
	}

	public void setLendCode(String lendCode) {
		this.lendCode = lendCode;
	}

	public BigDecimal getLendReleaseCreditValue() {
		return lendReleaseCreditValue;
	}

	public void setLendReleaseCreditValue(BigDecimal lendReleaseCreditValue) {
		this.lendReleaseCreditValue = lendReleaseCreditValue;
	}

	public Date getApplyExpireDay() {
		return applyExpireDay;
	}

	public void setApplyExpireDay(Date applyExpireDay) {
		this.applyExpireDay = applyExpireDay;
	}

	public String getLoanCode() {
		return loanCode;
	}

	public void setLoanCode(String loanCode) {
		this.loanCode = loanCode;
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

	public String getLoanTrusteeFlag() {
		return loanTrusteeFlag;
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

	public String getDicLoanDistinguish() {
		return dicLoanDistinguish;
	}

	public void setDicLoanDistinguish(String dicLoanDistinguish) {
		this.dicLoanDistinguish = dicLoanDistinguish;
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

	public Date getLoanBackmoneyFirday() {
		return loanBackmoneyFirday;
	}

	public void setLoanBackmoneyFirday(Date loanBackmoneyFirday) {
		this.loanBackmoneyFirday = loanBackmoneyFirday;
	}

	public String getLoanQuota() {
		return loanQuota;
	}

	public void setLoanQuota(String loanQuota) {
		this.loanQuota = loanQuota;
	}

	public String getLoanBackmoneyDay() {
		return loanBackmoneyDay;
	}

	public void setLoanBackmoneyDay(String loanBackmoneyDay) {
		this.loanBackmoneyDay = loanBackmoneyDay;
	}

	public String getLoanMonthRate() {
		return loanMonthRate;
	}

	public void setLoanMonthRate(String loanMonthRate) {
		this.loanMonthRate = loanMonthRate;
	}

	public BigDecimal getLoanCreditValue() {
		return loanCreditValue;
	}

	public void setLoanCreditValue(BigDecimal loanCreditValue) {
		this.loanCreditValue = loanCreditValue;
	}

	public String getTjFlag() {
		return tjFlag;
	}

	public void setTjFlag(String tjFlag) {
		this.tjFlag = tjFlag;
	}

	public Date getLoanFreezeDay() {
		return loanFreezeDay;
	}

	public void setLoanFreezeDay(Date loanFreezeDay) {
		this.loanFreezeDay = loanFreezeDay;
	}

	public String getZqState() {
		return zqState;
	}

	public void setZqState(String zqState) {
		this.zqState = zqState;
	}

	
	
}
