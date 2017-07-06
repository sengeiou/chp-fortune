package com.creditharmony.fortune.customer.workflow.entity;

import com.creditharmony.core.persistence.DataEntity;

/**
 * @Class Name TransferLendFlow
 * @author 孙凯文
 * @Create In 2015年12月11日
 */
public class TransferLendFlow extends DataEntity<TransferLendFlow> {

	private static final long serialVersionUID = 1L;
	
	private String applyId;
	private String flowType;
	private String custCode;
	private String status;
	private String managerCode;
	private String teamManagerCode;
	private String cityManagerCode;
	private String storeManagerCode;
	private String storeCode;
	private String branchManagerCode;
	private String fortuneManagerCode;

	public String getApplyId() {
		return applyId;
	}

	public void setApplyId(String applyId) {
		this.applyId = applyId;
	}

	public String getFlowType() {
		return flowType;
	}

	public void setFlowType(String flowType) {
		this.flowType = flowType;
	}

	public String getCustCode() {
		return custCode;
	}

	public void setCustCode(String custCode) {
		this.custCode = custCode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public String getStoreCode() {
		return storeCode;
	}

	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}

	public String getBranchManagerCode() {
		return branchManagerCode;
	}

	public void setBranchManagerCode(String branchManagerCode) {
		this.branchManagerCode = branchManagerCode;
	}

	public String getFortuneManagerCode() {
		return fortuneManagerCode;
	}

	public void setFortuneManagerCode(String fortuneManagerCode) {
		this.fortuneManagerCode = fortuneManagerCode;
	}

}
