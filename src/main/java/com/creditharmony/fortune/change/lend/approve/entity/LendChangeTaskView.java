package com.creditharmony.fortune.change.lend.approve.entity;

import java.util.Date;

import com.creditharmony.bpm.frame.view.BaseTaskItemView;

public class LendChangeTaskView extends BaseTaskItemView {

	private String customerName;// 客户姓名
	private String customerCode;// 客户编号
	private String province;// 省
	private String city;// 市
	private String department;// 部门
	private String teamManager;// 团队经理
	private String financing;// 理财经理
	private String applyHostedState;// 托管状态
	private String customerSource;// 客户来源
	private String dictCustomerStatus;// 客户当前状态
	private String lendCode;// 出借编号
	private String applyState;// 申请状态
	private Date createDate;// 创建时间
	private String  team;// 团队
	private String custMobilephone;// 客户联系电话
	private String  trusteeshipNo;// 金账户
	private Date applyDate;// 申请时间
	private String managerId;// 理财经理ID
	private String billDay;// 账单日
	private String productName;// 产品
	private String applyPay;// 付款方式
	
	
	public String getApplyPay() {
		return applyPay;
	}
	public void setApplyPay(String applyPay) {
		this.applyPay = applyPay;
	}
	public String getManagerId() {
		return managerId;
	}
	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}
	public Date getApplyDate() {
		return applyDate;
	}
	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}
	public String getTrusteeshipNo() {
		return trusteeshipNo;
	}
	public void setTrusteeshipNo(String trusteeshipNo) {
		this.trusteeshipNo = trusteeshipNo;
	}
	public String getCustMobilephone() {
		return custMobilephone;
	}
	public void setCustMobilephone(String custMobilephone) {
		this.custMobilephone = custMobilephone;
	}
	public String getTeam() {
		return team;
	}
	public void setTeam(String team) {
		this.team = team;
	}
	public String getLendCode() {
		return lendCode;
	}
	public void setLendCode(String lendCode) {
		this.lendCode = lendCode;
	}
	public String getApplyState() {
		return applyState;
	}
	public void setApplyState(String applyState) {
		this.applyState = applyState;
	}
	
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getCustomerSource() {
		return customerSource;
	}
	public void setCustomerSource(String customerSource) {
		this.customerSource = customerSource;
	}
	public String getDictCustomerStatus() {
		return dictCustomerStatus;
	}
	public void setDictCustomerStatus(String dictCustomerStatus) {
		this.dictCustomerStatus = dictCustomerStatus;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerCode() {
		return customerCode;
	}
	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getTeamManager() {
		return teamManager;
	}
	public void setTeamManager(String teamManager) {
		this.teamManager = teamManager;
	}
	public String getFinancing() {
		return financing;
	}
	public void setFinancing(String financing) {
		this.financing = financing;
	}
	public String getApplyHostedState() {
		return applyHostedState;
	}
	public void setApplyHostedState(String applyHostedState) {
		this.applyHostedState = applyHostedState;
	}
	public String getBillDay() {
		return billDay;
	}
	public void setBillDay(String billDay) {
		this.billDay = billDay;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	
}
