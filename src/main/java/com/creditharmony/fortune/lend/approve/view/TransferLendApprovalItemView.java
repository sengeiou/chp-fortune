package com.creditharmony.fortune.lend.approve.view;

import java.util.Date;

import com.creditharmony.bpm.frame.view.BaseTaskItemView;

/**
 * @Class Name TransferLendCheckItemView
 * @author 孙凯文
 * @Create In 2015年12月16日
 */
public class TransferLendApprovalItemView extends BaseTaskItemView {
	// 开户编号
	private String customerCode;
	// 开户姓名
	private String customerName;
	// 客户来源
	private String dictCustomerStatus;
	// 省份
	private String province;
	// 城市
	private String city;
	// 营业部
	private String department;
	// 团队经理
	private String teamManager;
	// 团队名称
	private String team;
	// 创建日期
	private String financing;
	// 创建时间
	private Date createDate;

	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getDictCustomerStatus() {
		return dictCustomerStatus;
	}

	public void setDictCustomerStatus(String dictCustomerStatus) {
		this.dictCustomerStatus = dictCustomerStatus;
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

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getFinancing() {
		return financing;
	}

	public void setFinancing(String financing) {
		this.financing = financing;
	}

}
