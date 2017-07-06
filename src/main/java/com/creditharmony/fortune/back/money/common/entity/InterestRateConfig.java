package com.creditharmony.fortune.back.money.common.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.creditharmony.core.persistence.DataEntity;

public class InterestRateConfig extends DataEntity<InterestRateConfig> {

	/**
	 * long serialVersionUID 
	 */
	private static final long serialVersionUID = 64456874406495342L;
	
	private String productCode; 	// 产品编号
	private Date applyLendDayFrom; 	// 出借日期From
	private Date applyLendDayTo; 	// 出借日期To
	private BigDecimal lowerLimit; 	// 出借金额，上限
	private BigDecimal upperLimit; 	// 出借金额，下限
	private BigDecimal profitRate; 	// 补息年利率
	private String useFlag; 		// 启用标识
	private String xinhebaoType; 	// 信和宝类型

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public Date getApplyLendDayFrom() {
		return applyLendDayFrom;
	}

	public void setApplyLendDayFrom(Date applyLendDayFrom) {
		this.applyLendDayFrom = applyLendDayFrom;
	}

	public Date getApplyLendDayTo() {
		return applyLendDayTo;
	}

	public void setApplyLendDayTo(Date applyLendDayTo) {
		this.applyLendDayTo = applyLendDayTo;
	}

	public BigDecimal getLowerLimit() {
		return lowerLimit;
	}

	public void setLowerLimit(BigDecimal lowerLimit) {
		this.lowerLimit = lowerLimit;
	}

	public BigDecimal getUpperLimit() {
		return upperLimit;
	}

	public void setUpperLimit(BigDecimal upperLimit) {
		this.upperLimit = upperLimit;
	}

	public BigDecimal getProfitRate() {
		return profitRate;
	}

	public void setProfitRate(BigDecimal profitRate) {
		this.profitRate = profitRate;
	}

	public String getUseFlag() {
		return useFlag;
	}

	public void setUseFlag(String useFlag) {
		this.useFlag = useFlag;
	}

	public String getXinhebaoType() {
		return xinhebaoType;
	}

	public void setXinhebaoType(String xinhebaoType) {
		this.xinhebaoType = xinhebaoType;
	}

}
