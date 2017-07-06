package com.creditharmony.fortune.change.lender.apply.view;

import java.util.Date;

import com.creditharmony.bpm.frame.view.BaseTaskItemView;

/**
 * 待办任务视图
 * @Class Name TrusteeshipChangeTaskView
 * @author 郭才林
 * @Create In 2015年12月2日
 */
public class LenderChangeTaskView extends BaseTaskItemView {

	private String  customerName; // 客户姓名
    private String  customerCode; // 客户编码
    private String  financing;// 理财经理
    private String  teamManager;// 团队经理
    private String  province;// 省
    private String  city;// 城市
    private String  department;// 营业部
    private Date    applyDate;// 申请日期
    private String  team;// 团队
    private String  customerSource;// 客户来源
    private String  trusteeshipNo;// 金账户
    private String  managerId;// 理财经理ID
    
    
	public String getManagerId() {
		return managerId;
	}
	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}
	public String getCustomerSource() {
		return customerSource;
	}
	public void setCustomerSource(String customerSource) {
		this.customerSource = customerSource;
	}
	public String getTeam() {
		return team;
	}
	public void setTeam(String team) {
		this.team = team;
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
	public String getFinancing() {
		return financing;
	}
	public void setFinancing(String financing) {
		this.financing = financing;
	}
	public String getTeamManager() {
		return teamManager;
	}
	public void setTeamManager(String teamManager) {
		this.teamManager = teamManager;
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

	
	
	
	
	
}
