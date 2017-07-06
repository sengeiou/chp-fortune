package com.creditharmony.fortune.borrow.entity.ex;

import com.creditharmony.fortune.customer.entity.LoanApply;

public class LoanApplyEx extends LoanApply{

	/**
	 * long serialVersionUID 
	 */
	private static final long serialVersionUID = 1L;
	// 营业部名称
	private String orgName;
	// 产品名称
	private String procuctName;
	// 客户姓名
	private String custName;
	
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getProcuctName() {
		return procuctName;
	}
	public void setProcuctName(String procuctName) {
		this.procuctName = procuctName;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	
}
