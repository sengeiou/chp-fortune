package com.creditharmony.fortune.obligatory.entity;

import java.math.BigDecimal;

import com.creditharmony.core.persistence.DataEntity;

/**
 * 可用债权配置对象
 * @Class Name CreditOkListObj 
 * @author 李志伟
 * @Create In 2016年1月21日
 */
public class CreditOkListObj  extends DataEntity<CreditOkListObj>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// 配置ID
	private String id;
	
	// 债权价值ID
	private String creditValueId;
	
	// 借款人id
	private String configLoanCode;
	
	// 借款ID
	private String loanCode;
	
	// 借款人姓名
	private String configLoanName;
	
	// 借款人证件类型
	private String dictConfigStatus;
	
	// 借款人证件号
	private String configZdr;
	
	// 省份ID
	private String configProvince;
	
	// 城市ID
	private String configCity;
	
	// 营业部ID
	private String configYy;
	
	// 隶属城市ID
	private String cityId;
	
	// 账单类型(首期/非首期)
	private String configType;
	
	// 账单类型
	private String dictDescription;
	
	// 可用债权价值
	private BigDecimal loanCreditValue;
	
	// 删除标志
	private String delFlag;
	
	public String getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
	
	public String getCreditValueId() {
		return creditValueId;
	}
	public void setCreditValueId(String creditValueId) {
		this.creditValueId = creditValueId;
	}
	public String getLoanCode() {
		return loanCode;
	}
	public void setLoanCode(String loanCode) {
		this.loanCode = loanCode;
	}
	public String getDictDescription() {
		return dictDescription;
	}
	public void setDictDescription(String dictDescription) {
		this.dictDescription = dictDescription;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getConfigLoanCode() {
		return configLoanCode;
	}
	public void setConfigLoanCode(String configLoanCode) {
		this.configLoanCode = configLoanCode;
	}
	public String getConfigLoanName() {
		return configLoanName;
	}
	public void setConfigLoanName(String configLoanName) {
		this.configLoanName = configLoanName;
	}
	public String getConfigZdr() {
		return configZdr;
	}
	public void setConfigZdr(String configZdr) {
		this.configZdr = configZdr;
	}
	public String getConfigCity() {
		return configCity;
	}
	public void setConfigCity(String configCity) {
		this.configCity = configCity;
	}
	public String getConfigType() {
		return configType;
	}
	public void setConfigType(String configType) {
		this.configType = configType;
	}
	public String getConfigYy() {
		return configYy;
	}
	public void setConfigYy(String configYy) {
		this.configYy = configYy;
	}
	public BigDecimal getLoanCreditValue() {
		return loanCreditValue;
	}
	public void setLoanCreditValue(BigDecimal loanCreditValue) {
		this.loanCreditValue = loanCreditValue;
	}
	public String getDictConfigStatus() {
		return dictConfigStatus;
	}
	public void setDictConfigStatus(String dictConfigStatus) {
		this.dictConfigStatus = dictConfigStatus;
	}
	
	public String getConfigProvince() {
		return configProvince;
	}
	public void setConfigProvince(String configProvince) {
		this.configProvince = configProvince;
	}
	public String getCityId() {
		return cityId;
	}
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	
}