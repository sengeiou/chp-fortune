package com.creditharmony.fortune.customer.workflow.entity;

import com.creditharmony.core.persistence.DataEntity;

/**
 * 出借流程实体
 * 
 * @Class Name LendApplyFlow
 * @author 孙凯文
 * @Create In 2016年1月8日
 */
public class LendApplyFlow extends DataEntity<LendApplyFlow> {
	private static final long serialVersionUID = 1L;
	// 流程ID
	private String applyId;
	// 流程类型
	private String applyType;
	// 客户编号
	private String customerCode;
	// 出借编号
	private String lendCode;
	// 理财经理
	private String managerCode;
	// 团队经理
	private String teamManagerCode;
	// 城市经理
	private String cityManagerCode;
	// 门店经理
	private String storeManagerCode;
	// 门店
	private String storeCode;
	// 支公司经理
	private String branchManager;
	// 财富中心经理
	private String fortuneManager;
	// 机构ID
	private String orgId;
	// 状态
	private String dictStatus;

	public String getApplyId() {
		return applyId;
	}

	public void setApplyId(String applyId) {
		this.applyId = applyId == null ? null : applyId.trim();
	}

	public String getApplyType() {
		return applyType;
	}

	public void setApplyType(String applyType) {
		this.applyType = applyType == null ? null : applyType.trim();
	}

	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode == null ? null : customerCode.trim();
	}

	public String getLendCode() {
		return lendCode;
	}

	public void setLendCode(String lendCode) {
		this.lendCode = lendCode == null ? null : lendCode.trim();
	}

	public String getManagerCode() {
		return managerCode;
	}

	public void setManagerCode(String managerCode) {
		this.managerCode = managerCode == null ? null : managerCode.trim();
	}

	public String getTeamManagerCode() {
		return teamManagerCode;
	}

	public void setTeamManagerCode(String teamManagerCode) {
		this.teamManagerCode = teamManagerCode == null ? null : teamManagerCode
				.trim();
	}

	public String getCityManagerCode() {
		return cityManagerCode;
	}

	public void setCityManagerCode(String cityManagerCode) {
		this.cityManagerCode = cityManagerCode == null ? null : cityManagerCode
				.trim();
	}

	public String getStoreManagerCode() {
		return storeManagerCode;
	}

	public void setStoreManagerCode(String storeManagerCode) {
		this.storeManagerCode = storeManagerCode == null ? null
				: storeManagerCode.trim();
	}

	public String getStoreCode() {
		return storeCode;
	}

	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode == null ? null : storeCode.trim();
	}

	public String getBranchManager() {
		return branchManager;
	}

	public void setBranchManager(String branchManager) {
		this.branchManager = branchManager == null ? null : branchManager
				.trim();
	}

	public String getFortuneManager() {
		return fortuneManager;
	}

	public void setFortuneManager(String fortuneManager) {
		this.fortuneManager = fortuneManager == null ? null : fortuneManager
				.trim();
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId == null ? null : orgId.trim();
	}

	public String getDictStatus() {
		return dictStatus;
	}

	public void setDictStatus(String dictStatus) {
		this.dictStatus = dictStatus == null ? null : dictStatus.trim();
	}

}