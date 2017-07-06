package com.creditharmony.fortune.creditor.config.rule.entity;

import java.math.BigDecimal;
import java.util.List;

import com.creditharmony.core.persistence.DataEntity;

public class CreditorperRuleConfig extends DataEntity<CreditorperRuleConfig	>{
    /**
	 * long serialVersionUID 
	 */
	private static final long serialVersionUID = 1L;

	private String ruleName;

    private List<CreditorperRuleProporti> proporti;

    private String remark;

    private String useFlag;
    
    private String billType;
    
    private String defaultFlag;
    
    private BigDecimal upperLimit;
    
    private BigDecimal lowerLimit;

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName == null ? null : ruleName.trim();
    }    

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

	public List<CreditorperRuleProporti> getProporti() {
		return proporti;
	}

	public void setProporti(List<CreditorperRuleProporti> proporti) {
		this.proporti = proporti;
	}

	public String getUseFlag() {
		return useFlag;
	}

	public void setUseFlag(String useFlag) {
		this.useFlag = useFlag;
	}

	public String getDefaultFlag() {
		return defaultFlag;
	}

	public void setDefaultFlag(String defaultFlag) {
		this.defaultFlag = defaultFlag;
	}

	public String getBillType() {
		return billType;
	}

	public void setBillType(String billType) {
		this.billType = billType;
	}

	public BigDecimal getUpperLimit() {
		return upperLimit;
	}

	public void setUpperLimit(BigDecimal upperLimit) {
		this.upperLimit = upperLimit;
	}

	public BigDecimal getLowerLimit() {
		return lowerLimit;
	}

	public void setLowerLimit(BigDecimal lowerLimit) {
		this.lowerLimit = lowerLimit;
	}

}