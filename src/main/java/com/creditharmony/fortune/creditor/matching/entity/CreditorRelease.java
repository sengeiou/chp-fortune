package com.creditharmony.fortune.creditor.matching.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.creditharmony.core.persistence.DataEntity;

/**
 * 债权到期释放
 * @author xurongsheng
 * @date 2016年11月23日 下午1:52:25
 *
 */
public class CreditorRelease extends DataEntity<CreditorRelease> {
	
	private static final long serialVersionUID = 1L;
	
	/** 债权到期释放ID */
	private String releaseId;
	
	/** 债权ID */
	private String creditValueId;
	
	/** 释放债权价值 */
	private BigDecimal releaseCreditValue;
	
	/** 出借编号 */
	private String lendCode;
	
	/** 债权匹配id */
	private String matchingId;
	
	/** 分期收益id */
	private String phaseId;
	
	/** 债权释放标识 */
	private String releaseFlag;
	
	/** 债权释放时间 */
	private Date releaseTime;
	
	/** 债权释放状态 */
	private String releaseState;
	
	/** 是否已释放 */
	private String isRelease;

	public String getReleaseId() {
		return releaseId;
	}

	public void setReleaseId(String releaseId) {
		this.releaseId = releaseId;
	}

	public String getCreditValueId() {
		return creditValueId;
	}

	public void setCreditValueId(String creditValueId) {
		this.creditValueId = creditValueId;
	}

	public BigDecimal getReleaseCreditValue() {
		return releaseCreditValue;
	}

	public void setReleaseCreditValue(BigDecimal releaseCreditValue) {
		this.releaseCreditValue = releaseCreditValue;
	}

	public String getLendCode() {
		return lendCode;
	}

	public void setLendCode(String lendCode) {
		this.lendCode = lendCode;
	}

	public String getMatchingId() {
		return matchingId;
	}

	public void setMatchingId(String matchingId) {
		this.matchingId = matchingId;
	}

	public String getPhaseId() {
		return phaseId;
	}

	public void setPhaseId(String phaseId) {
		this.phaseId = phaseId;
	}

	public String getReleaseFlag() {
		return releaseFlag;
	}

	public void setReleaseFlag(String releaseFlag) {
		this.releaseFlag = releaseFlag;
	}

	public Date getReleaseTime() {
		return releaseTime;
	}

	public void setReleaseTime(Date releaseTime) {
		this.releaseTime = releaseTime;
	}

	public String getReleaseState() {
		return releaseState;
	}

	public void setReleaseState(String releaseState) {
		this.releaseState = releaseState;
	}

	public String getIsRelease() {
		return isRelease;
	}

	public void setIsRelease(String isRelease) {
		this.isRelease = isRelease;
	}

	

}
