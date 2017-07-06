package com.creditharmony.fortune.donation.apply.entity;

import java.util.List;

import com.creditharmony.core.persistence.DataEntity;

public class Customertransfe extends DataEntity<Customertransfe>{

	/**
	 * long serialVersionUID 
	 */
	private static final long serialVersionUID = 4590301972864641330L;

	// 客户姓名
	private String custName;
	// 客户编号
	private String custCode;
	// 省份输入名字
	private String provinceValue;
	// 城市输入名字
	private String cityValue;
	// 理财经理姓名
	private String managerName;
	// 理财经理手机号码
	private String managerMoblie;
	// 营业部
	private String departmentName;
	// 客户手机号码
	private String custMobilephone;
	// 出借编号
	private String lendCode;
	// 客户编号集合
	private List<String> custCodes;
	// 理财经理工号
	private String managerCode;
	// 理财经理ID
	private String managerId;
	// 团队经理姓名
	private String teamManagerName;
	// 团队经理工号
	private String teamManagerCode;
	// 角色ID
	private String roleId;
	
	
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
	public String getManagerId() {
		return managerId;
	}
	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}
	public String getManagerCode() {
		return managerCode;
	}
	public void setManagerCode(String managerCode) {
		this.managerCode = managerCode;
	}
	public List<String> getCustCodes() {
		return custCodes;
	}
	public void setCustCodes(List<String> custCodes) {
		this.custCodes = custCodes;
	}
	public String getLendCode() {
		return lendCode;
	}
	public void setLendCode(String lendCode) {
		this.lendCode = lendCode;
	}
	public String getCustMobilephone() {
		return custMobilephone;
	}
	public void setCustMobilephone(String custMobilephone) {
		this.custMobilephone = custMobilephone;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getCustCode() {
		return custCode;
	}
	public void setCustCode(String custCode) {
		this.custCode = custCode;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getProvinceValue() {
		return provinceValue;
	}
	public void setProvinceValue(String provinceValue) {
		this.provinceValue = provinceValue;
	}
	public String getCityValue() {
		return cityValue;
	}
	public void setCityValue(String cityValue) {
		this.cityValue = cityValue;
	}
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	public String getManagerMoblie() {
		return managerMoblie;
	}
	public void setManagerMoblie(String managerMoblie) {
		this.managerMoblie = managerMoblie;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	
}
