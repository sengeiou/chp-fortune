package com.creditharmony.fortune.donation.apply.entity;

import com.creditharmony.core.persistence.DataEntity;
/**
 * 查询条件视图
 * @Class Name LendQueryView
 * @author 刘雄武
 * @Create In 2015年12月14日
 */
public class CustomerQueryView extends DataEntity<CustomerQueryView>{

	/**
	 * long serialVersionUID 
	 */
	private static final long serialVersionUID = 1L;
	
	// 理财经理姓名
	private String managerName;
	// 理财经理工号
	private String managerCode;
	// 营业部ID
	private String departmentId;
	// 营业部名称
	private String departmentName;
	// 客户姓名
	private String custName;
	// 客户编号
	private String custCode;
	// 支公司ID
	private String branchCompanyId;
	// 分公司ID
	private String districtCompanyId;
	
	
	public String getDistrictCompanyId() {
		return districtCompanyId;
	}
	public void setDistrictCompanyId(String districtCompanyId) {
		this.districtCompanyId = districtCompanyId;
	}
	public String getBranchCompanyId() {
		return branchCompanyId;
	}
	public void setBranchCompanyId(String branchCompanyId) {
		this.branchCompanyId = branchCompanyId;
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
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
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
	
	
}
