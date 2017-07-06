package com.creditharmony.fortune.lend.apply.entity;

import com.creditharmony.core.persistence.DataEntity;

/**
 * @Class Name LendPerformance
 * @Create In 2016年7月1日
 */
public class LendPerformance extends DataEntity<LendPerformance> {

	private static final long serialVersionUID = 1L;
	private String lendCode;
	private String managerCode;
	private String teamManagerCode;
	private String cityManagerCode;
	private String storeManagerCode;
	private String teamOrgId;
	private String storeOrgId;
	private String cityOrgId;

	public String getLendCode() {
		return lendCode;
	}

	public void setLendCode(String lendCode) {
		this.lendCode = lendCode;
	}

	public String getManagerCode() {
		return managerCode;
	}

	public void setManagerCode(String managerCode) {
		this.managerCode = managerCode;
	}

	public String getTeamManagerCode() {
		return teamManagerCode;
	}

	public void setTeamManagerCode(String teamManagerCode) {
		this.teamManagerCode = teamManagerCode;
	}

	public String getCityManagerCode() {
		return cityManagerCode;
	}

	public void setCityManagerCode(String cityManagerCode) {
		this.cityManagerCode = cityManagerCode;
	}

	public String getStoreManagerCode() {
		return storeManagerCode;
	}

	public void setStoreManagerCode(String storeManagerCode) {
		this.storeManagerCode = storeManagerCode;
	}

	public String getTeamOrgId() {
		return teamOrgId;
	}

	public void setTeamOrgId(String teamOrgId) {
		this.teamOrgId = teamOrgId;
	}

	public String getStoreOrgId() {
		return storeOrgId;
	}

	public void setStoreOrgId(String storeOrgId) {
		this.storeOrgId = storeOrgId;
	}

	public String getCityOrgId() {
		return cityOrgId;
	}

	public void setCityOrgId(String cityOrgId) {
		this.cityOrgId = cityOrgId;
	}

}
