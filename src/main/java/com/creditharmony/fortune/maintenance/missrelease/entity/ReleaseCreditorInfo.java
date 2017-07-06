/**
 * @Probject Name: chp-fortune
 * @Path: com.creditharmony.fortune.creditor.matching.entityMatchingCreditor.java
 * @Create By 胡体勇
 * @Create In 2015年12月24日 下午3:51:59
 */
package com.creditharmony.fortune.maintenance.missrelease.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.creditharmony.core.persistence.DataEntity;

/**
 * @Class Name MatchingCreditorInfo
 * @author 
 * @Create In 2015年12月24日
 */
public class ReleaseCreditorInfo extends DataEntity<ReleaseCreditorInfo> {
	/**
	 * long serialVersionUID 
	 */
	private static final long serialVersionUID = 1L;
	private String matchingId;                       //   债权推荐ID自增ID记录出借每期情况
    private String lendCode;                         //   出借编号
    private String productCode;                      //   产品编号
    private String matchingStatus;                  //   债权状态
    private BigDecimal matchingMatchMoney;           //   已匹配金额
    private Integer matchingNow;                     //   当前期数
    private String createBy;                         //   创建人
    private Date createTime;                         //   创建时间
    private String modifyBy;                         //   修改人
    private Date modifyTime;                         //   修改时间
    private String lendStatus;                     //出借状态
    private String creditCode;                    //债权ID
    private BigDecimal tradeMateMoney;            //交易匹配金额
    private Integer startPeriods;                     //   开始期数
    private Integer endPeriods;                     //   截止期数
    private Integer matchingTotal;
    private String matchingPayStatus;              //结算状态
    
	public String getMatchingPayStatus() {
		return matchingPayStatus;
	}
	public void setMatchingPayStatus(String matchingPayStatus) {
		this.matchingPayStatus = matchingPayStatus;
	}
	public Integer getMatchingTotal() {
		return matchingTotal;
	}
	public void setMatchingTotal(Integer matchingTotal) {
		this.matchingTotal = matchingTotal;
	}
	public Integer getStartPeriods() {
		return startPeriods;
	}
	public void setStartPeriods(Integer startPeriods) {
		this.startPeriods = startPeriods;
	}
	public Integer getEndPeriods() {
		return endPeriods;
	}
	public void setEndPeriods(Integer endPeriods) {
		this.endPeriods = endPeriods;
	}
	public BigDecimal getTradeMateMoney() {
		return tradeMateMoney;
	}
	public void setTradeMateMoney(BigDecimal tradeMateMoney) {
		this.tradeMateMoney = tradeMateMoney;
	}
	public String getCreditCode() {
		return creditCode;
	}
	public void setCreditCode(String creditCode) {
		this.creditCode = creditCode;
	}
	
	public String getLendStatus() {
		return lendStatus;
	}
	public void setLendStatus(String lendStatus) {
		this.lendStatus = lendStatus;
	}

	public String getMatchingId() {
		return matchingId;
	}
	public void setMatchingId(String matchingId) {
		this.matchingId = matchingId;
	}
	public String getLendCode() {
		return lendCode;
	}
	public void setLendCode(String lendCode) {
		this.lendCode = lendCode;
	}
	
	public String getMatchingStatus() {
		return matchingStatus;
	}
	public void setMatchingStatus(String matchingStatus) {
		this.matchingStatus = matchingStatus;
	}
	
	public BigDecimal getMatchingMatchMoney() {
		return matchingMatchMoney;
	}
	public void setMatchingMatchMoney(BigDecimal matchingMatchMoney) {
		this.matchingMatchMoney = matchingMatchMoney;
	}
	
	public Integer getMatchingNow() {
		return matchingNow;
	}
	public void setMatchingNow(Integer matchingNow) {
		this.matchingNow = matchingNow;
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
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	
}
