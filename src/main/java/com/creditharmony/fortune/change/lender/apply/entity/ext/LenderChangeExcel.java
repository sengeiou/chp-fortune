package com.creditharmony.fortune.change.lender.apply.entity.ext;

import java.util.Date;

import com.creditharmony.core.persistence.DataEntity;

public class LenderChangeExcel extends DataEntity<LenderChangeExcel>{
	
	/**
	 * long serialVersionUID 
	 */
	private static final long serialVersionUID = 1L;
	//申请ID
	private String applyId;
	// 审批日期
	private Date dictApprovalStartDate;
	// 客户姓名
	private String custName;
	//客户编号
	private String custCode;
	// 变更后信息
	private String lenderAfter;
	// 变更前信息
	private String lenderBegin;
	// 理财经理
	private String managerName;
	// 营业部
	private String departmentName;
	
	
	public String getApplyId() {
		return applyId;
	}
	public void setApplyId(String applyId) {
		this.applyId = applyId;
	}
	public String getCustCode() {
		return custCode;
	}
	public void setCustCode(String custCode) {
		this.custCode = custCode;
	}
	public Date getDictApprovalStartDate() {
		return dictApprovalStartDate;
	}
	public void setDictApprovalStartDate(Date dictApprovalStartDate) {
		this.dictApprovalStartDate = dictApprovalStartDate;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getLenderAfter() {
		return lenderAfter;
	}
	public void setLenderAfter(String lenderAfter) {
		this.lenderAfter = lenderAfter;
	}
	public String getLenderBegin() {
		return lenderBegin;
	}
	public void setLenderBegin(String lenderBegin) {
		this.lenderBegin = lenderBegin;
	}
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	
	

}
