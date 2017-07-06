package com.creditharmony.fortune.creditor.config.rule.entity;

import java.math.BigDecimal;
import java.util.List;

import com.creditharmony.core.persistence.DataEntity;

public class CreditorperRuleProporti extends DataEntity<CreditorperRuleProporti	>{
    /**
	 * long serialVersionUID 
	 */
	private static final long serialVersionUID = 1L;

	private String ruleId;

    private BigDecimal proportion;
    
    private Integer sort;

    private String useFlag;
    
    private List<CreditorperRuleLower> lower;

	public String getRuleId() {
		return ruleId;
	}

	public void setRuleId(String ruleId) {
		this.ruleId = ruleId;
	}

	public BigDecimal getProportion() {
		return proportion;
	}

	public void setProportion(BigDecimal proportion) {
		this.proportion = proportion;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getUseFlag() {
		return useFlag;
	}

	public void setUseFlag(String useFlag) {
		this.useFlag = useFlag;
	}

	public List<CreditorperRuleLower> getLower() {
		return lower;
	}

	public void setLower(List<CreditorperRuleLower> lower) {
		this.lower = lower;
	}

}