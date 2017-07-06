package com.creditharmony.fortune.creditor.config.rule.entity;

import java.math.BigDecimal;

import com.creditharmony.core.persistence.DataEntity;

public class CreditorperRuleLower extends DataEntity<CreditorperRuleLower	>{
    /**
	 * long serialVersionUID 
	 */
	private static final long serialVersionUID = 1L;

	private String proportionId;

    private BigDecimal lower;
    
    private Integer sort;

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getProportionId() {
		return proportionId;
	}

	public void setProportionId(String proportionId) {
		this.proportionId = proportionId;
	}

	public BigDecimal getLower() {
		return lower;
	}

	public void setLower(BigDecimal lower) {
		this.lower = lower;
	}

}