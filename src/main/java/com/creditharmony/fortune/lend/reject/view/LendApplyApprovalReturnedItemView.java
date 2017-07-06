package com.creditharmony.fortune.lend.reject.view;

import java.util.Date;

import com.creditharmony.bpm.frame.view.BaseTaskItemView;

/**
 * 出借申请退回待办列表视图类
 * 
 * @Class Name LendApplyApprovalReturnedItemView
 * @author 孙凯文
 * @Create In 2015年12月31日
 */
public class LendApplyApprovalReturnedItemView extends BaseTaskItemView {
	// 客户编号
	private String customerCode;
	// 客户名称
	private String customerName;
	// 出借编号
	private String applyCode;
	// 出借产品
	private String lendProduct;
	// 门店
	private String department;
	// 出借日期
	private Date lendDate;
	// 划扣平台
	private String deductType;
	// 到期日期
	private Date expireDate;
	// 出借金额
	private Double lendMoney;
	// 付款方式
	private String payState;
	// 合同编号
	private String contractNo;
	// 省份
	private String province;
	// 城市
	private String city;
	// 理财经理
	private String managerCode;
	// 团队经理
	private String teammanagerCode;
	// 团队
	private String openTeam;
	// 出借状态
	private String lendState;
	// 申请状态
	private String forApplyStatus;
	// 完结状态
	private String endState;

	public String getDeductType() {
		return deductType;
	}

	public void setDeductType(String deductType) {
		this.deductType = deductType;
	}

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

	public String getApplyCode() {
		return applyCode;
	}

	public void setApplyCode(String applyCode) {
		this.applyCode = applyCode;
	}

	public String getLendProduct() {
		return lendProduct;
	}

	public void setLendProduct(String lendProduct) {
		this.lendProduct = lendProduct;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public Date getLendDate() {
		return lendDate;
	}

	public void setLendDate(Date lendDate) {
		this.lendDate = lendDate;
	}

	public Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

	public Double getLendMoney() {
		return lendMoney;
	}

	public void setLendMoney(Double lendMoney) {
		this.lendMoney = lendMoney;
	}

	public String getPayState() {
		return payState;
	}

	public void setPayState(String payState) {
		this.payState = payState;
	}

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
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

	public String getManagerCode() {
		return managerCode;
	}

	public void setManagerCode(String managerCode) {
		this.managerCode = managerCode;
	}

	public String getTeammanagerCode() {
		return teammanagerCode;
	}

	public void setTeammanagerCode(String teammanagerCode) {
		this.teammanagerCode = teammanagerCode;
	}

	public String getOpenTeam() {
		return openTeam;
	}

	public void setOpenTeam(String openTeam) {
		this.openTeam = openTeam;
	}

	public String getLendState() {
		return lendState;
	}

	public void setLendState(String lendState) {
		this.lendState = lendState;
	}

	public String getForApplyStatus() {
		return forApplyStatus;
	}

	public void setForApplyStatus(String forApplyStatus) {
		this.forApplyStatus = forApplyStatus;
	}

	public String getEndState() {
		return endState;
	}

	public void setEndState(String endState) {
		this.endState = endState;
	}
}
