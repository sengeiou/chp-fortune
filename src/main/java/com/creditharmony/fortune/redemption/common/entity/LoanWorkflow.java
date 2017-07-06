package com.creditharmony.fortune.redemption.common.entity;

import com.creditharmony.core.persistence.DataEntity;

/**
 * 描述：出借流程状态信息表Bean
 * @Class Name LoanWorkflow
 * @author 陈广鹏
 * @Create In 2015年11月30日
 */
public class LoanWorkflow extends DataEntity<LoanWorkflow> {

	/**
	 * long serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	private String applyId;			// 流程ID
	private String applyType;		// 流程类型
	private String customerCode;	// 客户编号
	private String lendCode;		// 出借编号
	private String managerCode;		// 理财经理
	private String teamManagerCode;	// 团队经理
	private String cityManagerCode;	// 城市经理
	private String storeManagerCode;// 门店经理
	private String storeCode;		// 门店ID
	private String branchManager;	// 分公司经理
	private String fortuneManager;	// 财富中心经理
	private String orgId;			// 机构ID
	private String dictStatus;		// 状态

	public String getApplyId() {
		return applyId;
	}

	public void setApplyId(String applyId) {
		this.applyId = applyId;
	}

	public String getApplyType() {
		return applyType;
	}

	public void setApplyType(String applyType) {
		this.applyType = applyType;
	}

	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

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

	public String getStoreCode() {
		return storeCode;
	}

	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}

	public String getBranchManager() {
		return branchManager;
	}

	public void setBranchManager(String branchManager) {
		this.branchManager = branchManager;
	}

	public String getFortuneManager() {
		return fortuneManager;
	}

	public void setFortuneManager(String fortuneManager) {
		this.fortuneManager = fortuneManager;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getDictStatus() {
		return dictStatus;
	}

	public void setDictStatus(String dictStatus) {
		this.dictStatus = dictStatus;
	}

}
