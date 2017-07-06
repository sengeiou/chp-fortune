package com.creditharmony.fortune.creditor.matching.vo;

import java.util.Date;

/**
 * 债权到期释放View
 * @author xurongsheng
 * @date 2016年11月24日 下午1:41:14
 *
 */
public class CreditorReleaseView {

	/** 借款人 */
	private String loanName;
	
	/** 债权释放金额-起始 */
	private String releaseCreditValueFrom;
	
	/** 债权释放金额-结束 */
	private String releaseCreditValueTo;
	
	/** 剩余期数 */
	private String loanMonthsSurplus;
	
	/** 剩余期数-起始 */
	private String loanMonthsSurplusFrom;
	
	/** 剩余期数-结束 */
	private String loanMonthsSurplusTo;
	
	/** 还款日 */
	private String loanBackmoneyDay;
	
	/** 月利率 */
	private String loanMonthRate;
	
	/** 首次还款日-起始 */
	private Date loanBackmoneyFirdayFrom;
	
	/** 首次还款日-结束 */
	private Date loanBackmoneyFirdayTo;
	
	/** 债权区分 */
	private String dicLoanDistinguish;

	/** 债权来源 */
	private String dictLoanType;
	
	/** 债权标识 */
	private String loanTrusteeFlag;
	
	/** 出借到期日-起始 */
	private Date applyExpireDayFrom;
	
	/** 出借到期日-结束 */
	private Date applyExpireDayTo;
	
	/** 转出状态 */
	private String releaseState;
	
	/** 转出时间-起始 */
	private Date releaseTimeFrom;
	
	/** 转出时间-结束 */
	private Date releaseTimeTo;
	
	/** 转出标识 */
	private String releaseFlag;
	
	/** 借款ID */
	private String creditValueIdFlag;
	
	/** 证件类型 */
	private String customerCertType;
	
	/** 是否搜索标识 */
	private String isSearch;
	
	/** 选中项ID */
	private String ids;
	
	/** 转出平台 **/
	private String targetPlat;
	
	/** 提前结清标识 */
	private String tjFlag;
	
	/** 提前结清日期-起始 */
	private Date loanFreezeDayFrom;
	
	/** 提前结清日期-结束 */
	private Date loanFreezeDayTo;
	
	/** 债权情况 */
	private String zqState;

	public String getLoanName() {
		return loanName;
	}

	public void setLoanName(String loanName) {
		this.loanName = loanName;
	}

	public String getReleaseCreditValueFrom() {
		return releaseCreditValueFrom;
	}

	public void setReleaseCreditValueFrom(String releaseCreditValueFrom) {
		this.releaseCreditValueFrom = releaseCreditValueFrom;
	}

	public String getReleaseCreditValueTo() {
		return releaseCreditValueTo;
	}

	public void setReleaseCreditValueTo(String releaseCreditValueTo) {
		this.releaseCreditValueTo = releaseCreditValueTo;
	}

	public String getLoanMonthsSurplus() {
		return loanMonthsSurplus;
	}

	public void setLoanMonthsSurplus(String loanMonthsSurplus) {
		this.loanMonthsSurplus = loanMonthsSurplus;
	}

	public String getLoanMonthsSurplusFrom() {
		return loanMonthsSurplusFrom;
	}

	public void setLoanMonthsSurplusFrom(String loanMonthsSurplusFrom) {
		this.loanMonthsSurplusFrom = loanMonthsSurplusFrom;
	}

	public String getLoanMonthsSurplusTo() {
		return loanMonthsSurplusTo;
	}

	public void setLoanMonthsSurplusTo(String loanMonthsSurplusTo) {
		this.loanMonthsSurplusTo = loanMonthsSurplusTo;
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

	public Date getLoanBackmoneyFirdayFrom() {
		return loanBackmoneyFirdayFrom;
	}

	public void setLoanBackmoneyFirdayFrom(Date loanBackmoneyFirdayFrom) {
		this.loanBackmoneyFirdayFrom = loanBackmoneyFirdayFrom;
	}

	public Date getLoanBackmoneyFirdayTo() {
		return loanBackmoneyFirdayTo;
	}

	public void setLoanBackmoneyFirdayTo(Date loanBackmoneyFirdayTo) {
		this.loanBackmoneyFirdayTo = loanBackmoneyFirdayTo;
	}

	public String getDicLoanDistinguish() {
		return dicLoanDistinguish;
	}

	public void setDicLoanDistinguish(String dicLoanDistinguish) {
		this.dicLoanDistinguish = dicLoanDistinguish;
	}

	public String getDictLoanType() {
		return dictLoanType;
	}

	public void setDictLoanType(String dictLoanType) {
		this.dictLoanType = dictLoanType;
	}

	public String getLoanTrusteeFlag() {
		return loanTrusteeFlag;
	}

	public void setLoanTrusteeFlag(String loanTrusteeFlag) {
		this.loanTrusteeFlag = loanTrusteeFlag;
	}

	public Date getApplyExpireDayFrom() {
		return applyExpireDayFrom;
	}

	public void setApplyExpireDayFrom(Date applyExpireDayFrom) {
		this.applyExpireDayFrom = applyExpireDayFrom;
	}

	public Date getApplyExpireDayTo() {
		return applyExpireDayTo;
	}

	public void setApplyExpireDayTo(Date applyExpireDayTo) {
		this.applyExpireDayTo = applyExpireDayTo;
	}

	public String getReleaseState() {
		return releaseState;
	}

	public void setReleaseState(String releaseState) {
		this.releaseState = releaseState;
	}

	public Date getReleaseTimeFrom() {
		return releaseTimeFrom;
	}

	public void setReleaseTimeFrom(Date releaseTimeFrom) {
		this.releaseTimeFrom = releaseTimeFrom;
	}

	public Date getReleaseTimeTo() {
		return releaseTimeTo;
	}

	public void setReleaseTimeTo(Date releaseTimeTo) {
		this.releaseTimeTo = releaseTimeTo;
	}

	public String getReleaseFlag() {
		return releaseFlag;
	}

	public void setReleaseFlag(String releaseFlag) {
		this.releaseFlag = releaseFlag;
	}

	public String getCreditValueIdFlag() {
		return creditValueIdFlag;
	}

	public void setCreditValueIdFlag(String creditValueIdFlag) {
		this.creditValueIdFlag = creditValueIdFlag;
	}

	public String getCustomerCertType() {
		return customerCertType;
	}

	public void setCustomerCertType(String customerCertType) {
		this.customerCertType = customerCertType;
	}

	public String getIsSearch() {
		return isSearch;
	}

	public void setIsSearch(String isSearch) {
		this.isSearch = isSearch;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getTargetPlat() {
		return targetPlat;
	}

	public void setTargetPlat(String targetPlat) {
		this.targetPlat = targetPlat;
	}

	public String getTjFlag() {
		return tjFlag;
	}

	public void setTjFlag(String tjFlag) {
		this.tjFlag = tjFlag;
	}

	public Date getLoanFreezeDayFrom() {
		return loanFreezeDayFrom;
	}

	public void setLoanFreezeDayFrom(Date loanFreezeDayFrom) {
		this.loanFreezeDayFrom = loanFreezeDayFrom;
	}

	public Date getLoanFreezeDayTo() {
		return loanFreezeDayTo;
	}

	public void setLoanFreezeDayTo(Date loanFreezeDayTo) {
		this.loanFreezeDayTo = loanFreezeDayTo;
	}

	public String getZqState() {
		return zqState;
	}

	public void setZqState(String zqState) {
		this.zqState = zqState;
	}
	
	
}
