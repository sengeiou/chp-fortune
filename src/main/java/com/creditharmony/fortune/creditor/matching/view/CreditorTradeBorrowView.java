package com.creditharmony.fortune.creditor.matching.view;

import java.math.BigDecimal;
import java.util.Date;

import com.creditharmony.core.fortune.type.ProductCode;
import com.creditharmony.fortune.creditor.matching.utils.BigDecimalUtil;

/**
 * 既有及历史债权列表
 * @Class Name CreditorTradeBorrowView
 * @author 柳慧
 * @Create In 2015年12月28日
 */
public class CreditorTradeBorrowView {
	private String phaseId;
    private String matchingId;
    private String loanCode;
    private String lendCode;
    private Integer phaseNumber;
    private BigDecimal phaseAmount;
    private BigDecimal phaseInterestCur;
    private BigDecimal phasePrincipalCur;
    private BigDecimal phasePrincipalSurplus;
    private String phaseRepaySign;
    private Date phaseRepaydateActual;
    private BigDecimal phaseBackCount;
    private BigDecimal phaseBackPrincipal;
    private BigDecimal phaseBackInterest;
    private Date phaseBegindayCur;
    private Date phaseEnddayCur;
    private String phaseMateId;
    private Integer phaseMateNumber;
    private Integer phaseNumberSurplus;
    private String phaseDiscardStatus;
    private String phaseReleaseStatus;
    private String phaseFreezeNextstatus;
    private String creditValueId;
    private String loanId;
    private String loanName;
    private String loanIdcard;
    private String loanJob;
    private String loanProduct;
    private String loanPurpose;
    private String dictLoanType;
    private Date loanOutmoneyDay;
    private Date loanBackmoneyFirday;
    private Integer loanBackmoneyDay;
    private Date loanBackmoneyLastday;
    private Integer loanMonths;
    private Integer loanMonthsSurplus;
    private BigDecimal loanQuota;
    private BigDecimal loanCreditValue;
    private BigDecimal loanMonthRate;
    private BigDecimal loanValueYear;
    private String dictLoanFreeFlag;
    private Date loanModifiedDay;
    private String loanPledge;
    private Integer loanDaySurplus;
    private String loanMiddleMan;
    private Date loanFreezeDay;
    private String loanMonthgainFlag;
    private String loanTrusteeFlag;
    private  Integer hkqs;//还款期数
    private BigDecimal productRate; //预计债权收益率
    private BigDecimal matchingBorrowQuota;// 初始受让债权价值   待推荐列表中出借金额
    private BigDecimal nyrcyzq;//年月日持有债权价值
    private BigDecimal tradeMateMoney;
    private  double yjzqsyl;   // 预计债权收益率
    private Integer matchingNow;
    private BigDecimal bqkgje;  //本期还款金额
    private String productCode; //产品Code   
    public Integer getHkqs() {
    	if(loanMonths!=null && loanMonthsSurplus!=null){
    		return loanMonths-loanMonthsSurplus;
    	}else{
    		return 0;
    	}
	}
    
	public BigDecimal getTradeMateMoney() {
		return tradeMateMoney;
	}

	public void setTradeMateMoney(BigDecimal tradeMateMoney) {
		this.tradeMateMoney = tradeMateMoney;
	}

	public BigDecimal getMatchingBorrowQuota() {
		return matchingBorrowQuota;
	}

	public void setMatchingBorrowQuota(BigDecimal matchingBorrowQuota) {
		this.matchingBorrowQuota = matchingBorrowQuota;
	}

	public BigDecimal getNyrcyzq() {
		return nyrcyzq;
	}

	public void setNyrcyzq(BigDecimal nyrcyzq) {
		this.nyrcyzq = nyrcyzq;
	}

	public BigDecimal getProductRate() {
		return productRate;
	}

	public void setProductRate(BigDecimal productRate) {
		this.productRate = productRate;
	}

	public String getPhaseId() {
		return phaseId;
	}
	public void setPhaseId(String phaseId) {
		this.phaseId = phaseId;
	}
	public String getMatchingId() {
		return matchingId;
	}
	public void setMatchingId(String matchingId) {
		this.matchingId = matchingId;
	}
	public String getLoanCode() {
		return loanCode;
	}
	public void setLoanCode(String loanCode) {
		this.loanCode = loanCode;
	}
	public String getLendCode() {
		return lendCode;
	}
	public void setLendCode(String lendCode) {
		this.lendCode = lendCode;
	}
	public Integer getPhaseNumber() {
		return phaseNumber;
	}
	public void setPhaseNumber(Integer phaseNumber) {
		this.phaseNumber = phaseNumber;
	}
	public BigDecimal getPhaseAmount() {
		return phaseAmount;
	}
	public void setPhaseAmount(BigDecimal phaseAmount) {
		this.phaseAmount = phaseAmount;
	}
	public BigDecimal getPhaseInterestCur() {
		return phaseInterestCur;
	}
	public void setPhaseInterestCur(BigDecimal phaseInterestCur) {
		this.phaseInterestCur = phaseInterestCur;
	}
	public BigDecimal getPhasePrincipalCur() {
		return phasePrincipalCur;
	}
	public void setPhasePrincipalCur(BigDecimal phasePrincipalCur) {
		this.phasePrincipalCur = phasePrincipalCur;
	}
	public BigDecimal getPhasePrincipalSurplus() {
		return phasePrincipalSurplus;
	}
	public void setPhasePrincipalSurplus(BigDecimal phasePrincipalSurplus) {
		this.phasePrincipalSurplus = phasePrincipalSurplus;
	}
	public String getPhaseRepaySign() {
		return phaseRepaySign;
	}
	public void setPhaseRepaySign(String phaseRepaySign) {
		this.phaseRepaySign = phaseRepaySign;
	}
	public Date getPhaseRepaydateActual() {
		return phaseRepaydateActual;
	}
	public void setPhaseRepaydateActual(Date phaseRepaydateActual) {
		this.phaseRepaydateActual = phaseRepaydateActual;
	}
	public BigDecimal getPhaseBackCount() {
		return phaseBackCount;
	}
	public void setPhaseBackCount(BigDecimal phaseBackCount) {
		this.phaseBackCount = phaseBackCount;
	}
	public BigDecimal getPhaseBackPrincipal() {
		return phaseBackPrincipal;
	}
	public void setPhaseBackPrincipal(BigDecimal phaseBackPrincipal) {
		this.phaseBackPrincipal = phaseBackPrincipal;
	}
	public BigDecimal getPhaseBackInterest() {
		return phaseBackInterest;
	}
	public void setPhaseBackInterest(BigDecimal phaseBackInterest) {
		this.phaseBackInterest = phaseBackInterest;
	}
	public Date getPhaseBegindayCur() {
		return phaseBegindayCur;
	}
	public void setPhaseBegindayCur(Date phaseBegindayCur) {
		this.phaseBegindayCur = phaseBegindayCur;
	}
	public Date getPhaseEnddayCur() {
		return phaseEnddayCur;
	}
	public void setPhaseEnddayCur(Date phaseEnddayCur) {
		this.phaseEnddayCur = phaseEnddayCur;
	}
	public String getPhaseMateId() {
		return phaseMateId;
	}
	public void setPhaseMateId(String phaseMateId) {
		this.phaseMateId = phaseMateId;
	}
	public Integer getPhaseMateNumber() {
		return phaseMateNumber;
	}
	public void setPhaseMateNumber(Integer phaseMateNumber) {
		this.phaseMateNumber = phaseMateNumber;
	}
	public Integer getPhaseNumberSurplus() {
		return phaseNumberSurplus;
	}
	public void setPhaseNumberSurplus(Integer phaseNumberSurplus) {
		this.phaseNumberSurplus = phaseNumberSurplus;
	}
	public String getPhaseDiscardStatus() {
		return phaseDiscardStatus;
	}
	public void setPhaseDiscardStatus(String phaseDiscardStatus) {
		this.phaseDiscardStatus = phaseDiscardStatus;
	}
	public String getPhaseReleaseStatus() {
		return phaseReleaseStatus;
	}
	public void setPhaseReleaseStatus(String phaseReleaseStatus) {
		this.phaseReleaseStatus = phaseReleaseStatus;
	}
	public String getPhaseFreezeNextstatus() {
		return phaseFreezeNextstatus;
	}
	public void setPhaseFreezeNextstatus(String phaseFreezeNextstatus) {
		this.phaseFreezeNextstatus = phaseFreezeNextstatus;
	}
	public String getCreditValueId() {
		return creditValueId;
	}
	public void setCreditValueId(String creditValueId) {
		this.creditValueId = creditValueId;
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
	public String getDictLoanType() {
		return dictLoanType;
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
	public String getLoanPurpose() {
		return loanPurpose;
	}
	public void setLoanPurpose(String loanPurpose) {
		this.loanPurpose = loanPurpose;
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
	public Integer getLoanMonthsSurplus() {
		if(loanMonthsSurplus==null || loanMonthsSurplus<0){
			return 0;
		}else{
			return loanMonthsSurplus;
		}
	}
	public void setLoanMonthsSurplus(Integer loanMonthsSurplus) {
		this.loanMonthsSurplus = loanMonthsSurplus;
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
	public BigDecimal getLoanMonthRate() {
		return loanMonthRate;
	}
	public void setLoanMonthRate(BigDecimal loanMonthRate) {
		this.loanMonthRate = loanMonthRate;
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
	public String getLoanPledge() {
		return loanPledge;
	}
	public void setLoanPledge(String loanPledge) {
		this.loanPledge = loanPledge;
	}
	public Integer getLoanDaySurplus() {
		return loanDaySurplus;
	}
	public void setLoanDaySurplus(Integer loanDaySurplus) {
		this.loanDaySurplus = loanDaySurplus;
	}
	public String getLoanMiddleMan() {
		return loanMiddleMan;
	}
	public void setLoanMiddleMan(String loanMiddleMan) {
		this.loanMiddleMan = loanMiddleMan;
	}
	public Date getLoanFreezeDay() {
		return loanFreezeDay;
	}
	public void setLoanFreezeDay(Date loanFreezeDay) {
		this.loanFreezeDay = loanFreezeDay;
	}
	public String getLoanMonthgainFlag() {
		return loanMonthgainFlag;
	}
	public void setLoanMonthgainFlag(String loanMonthgainFlag) {
		this.loanMonthgainFlag = loanMonthgainFlag;
	}
	public String getLoanTrusteeFlag() {
		return loanTrusteeFlag;
	}
	public void setLoanTrusteeFlag(String loanTrusteeFlag) {
		this.loanTrusteeFlag = loanTrusteeFlag;
	}

	public double getYjzqsyl() {
		// （1+债权月利率）的12次方-1
		BigDecimal v1 = loanMonthRate.divide(new BigDecimal("100"),2, BigDecimal.ROUND_HALF_UP).add(new BigDecimal("1"));
		double temp = BigDecimalUtil.getmulMoer(v1.doubleValue(), 12);
		BigDecimal v2 = new BigDecimal(temp); 
		v2= v2.multiply(new BigDecimal("1"));
		return BigDecimalUtil.round(v2.doubleValue(), 3);
	}

	public void setYjzqsyl(double yjzqsyl) {
		this.yjzqsyl = yjzqsyl;
	}

	public Integer getMatchingNow() {
		return matchingNow;
	}

	public void setMatchingNow(Integer matchingNow) {
		this.matchingNow = matchingNow;
	}

	public BigDecimal getBqkgje() {
		if(this.getProductCode().equals(ProductCode.YXT.value)|| this.getProductCode().equals(ProductCode.XHYZ.value)){
			if(this.getLoanMonthsSurplus()==0){
				return this.getMatchingNow()-this.getPhaseNumber()>1?BigDecimal.ZERO:this.bqkgje;
			}else{
				return  this.bqkgje;
			}
		}else{
			if(this.getLoanMonthsSurplus()==0){
				return this.getMatchingNow()-this.getPhaseNumber()>1?BigDecimal.ZERO:this.getPhaseAmount();
			}else{
				return this.getPhaseAmount();
			}
			
		}
	}

	public void setBqkgje(BigDecimal bqkgje) {
		this.bqkgje = bqkgje;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	
}