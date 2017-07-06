package com.creditharmony.fortune.donation.apply.entity;

import java.util.Date;

import com.creditharmony.core.persistence.DataEntity;

public class CustomerManagerinfo extends DataEntity<CustomerManagerinfo> {

	/**
	 * long serialVersionUID 
	 */
	private static final long serialVersionUID = -5839746545434663715L;

	    // 客户姓名
		private String custName;
		// 客户编号
		private String custCode;
		// 理财经理姓名
		private String managerName;
		// 理财经理工号
		private String managerCode;
		// 营业部
		private String departmentName;
		// 团队经理姓名
		private String teamManagerName;
		// 团队经理工号
		private String teamManagerCode;
		// 理财经理工号新
		private String managerCodeNew;
		// 理财经理姓名新
		private String managerNameNew;
		// 营业部新
		private String departmentNameNew;
		// 转赠日期
		private Date TransferDate;
		// 转赠状态
		private String TransferState;
		// 新理财经理ID
		private String managerId;
		// 营业部ID
		private String departmentId;
		// 交割类型
		private String cdictdeltype;
		// 旧理财经理ID
		private String oldmanagerId;
		// 旧营业部ID
		private String olddepartmentId;
		// 申请ID
		private String applyId;
		// 审批结果
		private String auditResult;
		// 客户手机号码
		private String custMobilephone;
		// 支公司ID
		private String branchCompanyId;
		// 团队编号（新）
		private String teamCodeNew;
		// 团队编号（旧）
		private String teamCodeOld;
		// 分公司ID
		private String districtCompanyId;
		
		
		public String getDistrictCompanyId() {
			return districtCompanyId;
		}
		public void setDistrictCompanyId(String districtCompanyId) {
			this.districtCompanyId = districtCompanyId;
		}
		public String getTeamCodeOld() {
			return teamCodeOld;
		}
		public void setTeamCodeOld(String teamCodeOld) {
			this.teamCodeOld = teamCodeOld;
		}
		public String getTeamCodeNew() {
			return teamCodeNew;
		}
		public void setTeamCodeNew(String teamCodeNew) {
			this.teamCodeNew = teamCodeNew;
		}
		public String getBranchCompanyId() {
			return branchCompanyId;
		}
		public void setBranchCompanyId(String branchCompanyId) {
			this.branchCompanyId = branchCompanyId;
		}
		public String getCustMobilephone() {
			return custMobilephone;
		}
		public void setCustMobilephone(String custMobilephone) {
			this.custMobilephone = custMobilephone;
		}
		public String getAuditResult() {
			return auditResult;
		}
		public void setAuditResult(String auditResult) {
			this.auditResult = auditResult;
		}
		public String getApplyId() {
			return applyId;
		}
		public void setApplyId(String applyId) {
			this.applyId = applyId;
		}
		public String getCdictdeltype() {
			return cdictdeltype;
		}
		public void setCdictdeltype(String cdictdeltype) {
			this.cdictdeltype = cdictdeltype;
		}
		public String getOlddepartmentId() {
			return olddepartmentId;
		}
		public void setOlddepartmentId(String olddepartmentId) {
			this.olddepartmentId = olddepartmentId;
		}
		public String getOldmanagerId() {
			return oldmanagerId;
		}
		public void setOldmanagerId(String oldmanagerId) {
			this.oldmanagerId = oldmanagerId;
		}
		public String getDepartmentId() {
			return departmentId;
		}
		public void setDepartmentId(String departmentId) {
			this.departmentId = departmentId;
		}
		public String getCustName() {
			return custName;
		}
		public void setCustName(String custName) {
			this.custName = custName;
		}
		public String getCustCode() {
			return custCode;
		}
		public void setCustCode(String custCode) {
			this.custCode = custCode;
		}
		public String getManagerName() {
			return managerName;
		}
		public void setManagerName(String managerName) {
			this.managerName = managerName;
		}
		public String getManagerCode() {
			return managerCode;
		}
		public void setManagerCode(String managerCode) {
			this.managerCode = managerCode;
		}
		public String getDepartmentName() {
			return departmentName;
		}
		public void setDepartmentName(String departmentName) {
			this.departmentName = departmentName;
		}
		public String getTeamManagerName() {
			return teamManagerName;
		}
		public void setTeamManagerName(String teamManagerName) {
			this.teamManagerName = teamManagerName;
		}
		public String getTeamManagerCode() {
			return teamManagerCode;
		}
		public void setTeamManagerCode(String teamManagerCode) {
			this.teamManagerCode = teamManagerCode;
		}
		public String getManagerCodeNew() {
			return managerCodeNew;
		}
		public void setManagerCodeNew(String managerCodeNew) {
			this.managerCodeNew = managerCodeNew;
		}
		public String getManagerNameNew() {
			return managerNameNew;
		}
		public void setManagerNameNew(String managerNameNew) {
			this.managerNameNew = managerNameNew;
		}
		public String getDepartmentNameNew() {
			return departmentNameNew;
		}
		public void setDepartmentNameNew(String departmentNameNew) {
			this.departmentNameNew = departmentNameNew;
		}
		public Date getTransferDate() {
			return TransferDate;
		}
		public void setTransferDate(Date transferDate) {
			TransferDate = transferDate;
		}
		public String getTransferState() {
			return TransferState;
		}
		public void setTransferState(String transferState) {
			TransferState = transferState;
		}
		public String getManagerId() {
			return managerId;
		}
		public void setManagerId(String managerId) {
			this.managerId = managerId;
		}
	
		
}
