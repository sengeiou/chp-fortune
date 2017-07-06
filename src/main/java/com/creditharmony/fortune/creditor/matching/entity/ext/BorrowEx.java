/**
 * @Probject Name: chp-fortune
 * @Path: com.creditharmony.fortune.creditor.matching.entity.extBorrowEx.java
 * @Create By 胡体勇
 * @Create In 2015年12月15日 上午10:22:49
 */
package com.creditharmony.fortune.creditor.matching.entity.ext;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import org.springframework.util.StringUtils;

import com.creditharmony.core.persistence.DataEntity;

/**
 * @Class Name BorrowEx
 * @author 胡体勇
 * @Create In 2015年12月15日
 */
public class BorrowEx extends DataEntity<BorrowEx> {

	/**
	 * long serialVersionUID 
	 */
	private static final long serialVersionUID = 1L;

    private String loanName;//借款人
    private String dictLoanType;//债权来源
    private String loanProduct;//借款类型
    private String loanJob;//职业
    private String loanMiddleMan;//中间人
    private String loanPurpose;//借款用途
    private Date loanBackmoneyFirday;//起始还款日
    private Integer loanBackmoneyDay;//还款日
    private Integer phaseMateNumber;//匹配总期数
    private Integer phaseNumberSurplus;//剩余还款期数
    private BigDecimal loanQuota;//借款金额
    private BigDecimal loanCreditValue;//原始债权价值
    private String loanMonthRate;//月利率
    private BigDecimal tradeMateMoney;//推荐额度
    private BigDecimal loanAvailabeValue;//可用债权价值
    private String marchingId;//推荐id
    private BigDecimal phasePrincipalCur;//本期应还本金 
    private String tradeId;// 交易id
    private String creditNode;// 债权节点
    private String creditCode;// 债权id
    private String tradeCreditStatus;// 交易状态
    private Date loanBackmoneyLastDay;// 截止还款日期
    private Timestamp loanModifyTime;// 最后编辑时间
    private Timestamp tradePassDate;// 匹配时间
    private String phaseDiscardStatus;// 废弃状态
    private Date modifyTime;// 修改时间
    private String modifyBy;// 修改人
    private String tradeMateMoneyPercent;
    private BigDecimal hkqs; // 还款期数
    private Integer loanMonths;
    private BigDecimal syzqjz; // 剩余债权价值
    private String dicLoanDistinguish; // 债权区分
    
	public String getLoanName() {
		return loanName;
	}
	public void setLoanName(String loanName) {
		this.loanName = loanName;
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
	public String getLoanJob() {
		return loanJob;
	}
	public void setLoanJob(String loanJob) {
		this.loanJob = loanJob;
	}
	public String getLoanMiddleMan() {
		return loanMiddleMan;
	}
	public void setLoanMiddleMan(String loanMiddleMan) {
		this.loanMiddleMan = loanMiddleMan;
	}
	public String getLoanPurpose() {
		return loanPurpose;
	}
	public void setLoanPurpose(String loanPurpose) {
		this.loanPurpose = loanPurpose;
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
	public String getLoanMonthRate() {
		return loanMonthRate;
	}
	public void setLoanMonthRate(String loanMonthRate) {
		this.loanMonthRate = loanMonthRate;
	}
	public BigDecimal getTradeMateMoney() {
		return tradeMateMoney;
	}
	public void setTradeMateMoney(BigDecimal tradeMateMoney) {
		this.tradeMateMoney = tradeMateMoney;
	}
	public BigDecimal getLoanAvailabeValue() {
		return loanAvailabeValue;
	}
	public void setLoanAvailabeValue(BigDecimal loanAvailabeValue) {
		this.loanAvailabeValue = loanAvailabeValue;
	}
	public String getMarchingId() {
		return marchingId;
	}
	public void setMarchingId(String marchingId) {
		this.marchingId = marchingId;
	}
	public BigDecimal getPhasePrincipalCur() {
		return phasePrincipalCur;
	}
	public void setPhasePrincipalCur(BigDecimal phasePrincipalCur) {
		this.phasePrincipalCur = phasePrincipalCur;
	}
	public String getTradeId() {
		return tradeId;
	}
	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}
	public String getCreditNode() {
		return creditNode;
	}
	public void setCreditNode(String creditNode) {
		this.creditNode = creditNode;
	}
	public String getCreditCode() {
		return creditCode;
	}
	public void setCreditCode(String creditCode) {
		this.creditCode = creditCode;
	}
	public Date getLoanBackmoneyLastDay() {
		return loanBackmoneyLastDay;
	}
	public void setLoanBackmoneyLastDay(Date loanBackmoneyLastDay) {
		this.loanBackmoneyLastDay = loanBackmoneyLastDay;
	}
	public String getTradeCreditStatus() {
		return tradeCreditStatus;
	}
	public void setTradeCreditStatus(String tradeCreditStatus) {
		this.tradeCreditStatus = tradeCreditStatus;
	}
	public Timestamp getLoanModifyTime() {
		return loanModifyTime;
	}
	public void setLoanModifyTime(Timestamp loanModifyTime) {
		this.loanModifyTime = loanModifyTime;
	}
	public Timestamp getTradePassDate() {
		return tradePassDate;
	}
	public void setTradePassDate(Timestamp tradePassDate) {
		this.tradePassDate = tradePassDate;
	}
	public String getPhaseDiscardStatus() {
		return phaseDiscardStatus;
	}
	public void setPhaseDiscardStatus(String phaseDiscardStatus) {
		this.phaseDiscardStatus = phaseDiscardStatus;
	}
	public Date getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	public String getModifyBy() {
		return modifyBy;
	}
	
	public void setModifyBy(String modifyBy) {
		this.modifyBy = modifyBy;
	}
	public String getTradeMateMoneyPercent() {
		return tradeMateMoneyPercent;
	}
	public void setTradeMateMoneyPercent(String tradeMateMoneyPercent) {
		this.tradeMateMoneyPercent = tradeMateMoneyPercent;
	}
	public int getHkqs() {
		int i = Integer.parseInt(this.phaseMateNumber.toString());
		int b = Integer.parseInt(this.phaseNumberSurplus.toString());
		return i-b;
	}
	public void setHkqs(BigDecimal hkqs) {
		this.hkqs = hkqs;
	}
	public Integer getLoanMonths() {
		return loanMonths;
	}
	public void setLoanMonths(Integer loanMonths) {
		this.loanMonths = loanMonths;
	}
	public BigDecimal getSyzqjz() {
		if(StringUtils.isEmpty(getTradeMateMoneyPercent())){
			return getLoanAvailabeValue();
		}else{
			BigDecimal tradeMateMoneyPercent=new BigDecimal (getTradeMateMoneyPercent());
			if(tradeMateMoneyPercent.compareTo(BigDecimal.ZERO)==0){
				return getLoanAvailabeValue();
			}else{
				return this.getTradeMateMoney().divide(tradeMateMoneyPercent,BigDecimal.ROUND_HALF_UP,5).subtract(getTradeMateMoney());
			}
		}
	}
	public void setSyzqjz(BigDecimal syzqjz) {
		this.syzqjz = syzqjz;
	}
	public String getDicLoanDistinguish() {
		return dicLoanDistinguish;
	}
	public void setDicLoanDistinguish(String dicLoanDistinguish) {
		this.dicLoanDistinguish = dicLoanDistinguish;
	}
	 
}
