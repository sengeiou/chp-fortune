package com.creditharmony.fortune.trusteeship.view;

import com.creditharmony.bpm.frame.view.BaseTaskItemView;

/**
 * 待办任务视图
 * @Class Name TrusteeshipChangeTaskView
 * @author 郭才林
 * @Create In 2015年12月2日
 */
public class TrusteeshipChangeTaskView extends BaseTaskItemView {
	
	// 客户姓名
	private String  customerName;
	// 客户编码
    private String  customerCode;
    // 证件号码
    private String  custCertNum;
    // 银行名称
 	private String accountBankId;
 	// 支行地址
 	private String accountBranch;
 	// 银行所在省份城市
 	private String accountAddrProvinceCity;
 	// 银行所在区
 	private String accountAddrDistrict;
 	// 账户名称
 	private String accountName;
 	// 账号
 	private String accountNo;
 	// 变更类型名称
 	private String changerTypeName;
 	// 变更类型值
 	private String changerTypeVal;
 	// 变更状态
 	private String changerState;
	// 银行所在省份
	private String accountAddrProvince;
	// 银行所在城市
	private String accountAddrCity;

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
	public String getCustCertNum() {
		return custCertNum;
	}
	public void setCustCertNum(String custCertNum) {
		this.custCertNum = custCertNum;
	}
	public String getAccountBankId() {
		return accountBankId;
	}
	public void setAccountBankId(String accountBankId) {
		this.accountBankId = accountBankId;
	}
	public String getAccountBranch() {
		return accountBranch;
	}
	public void setAccountBranch(String accountBranch) {
		this.accountBranch = accountBranch;
	}
	public String getAccountAddrProvinceCity() {
		return accountAddrProvinceCity;
	}
	public void setAccountAddrProvinceCity(String accountAddrProvinceCity) {
		this.accountAddrProvinceCity = accountAddrProvinceCity;
	}
	public String getAccountAddrDistrict() {
		return accountAddrDistrict;
	}
	public void setAccountAddrDistrict(String accountAddrDistrict) {
		this.accountAddrDistrict = accountAddrDistrict;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public String getChangerState() {
		return changerState;
	}
	public void setChangerState(String changerState) {
		this.changerState = changerState;
	}
	public String getChangerTypeName() {
		return changerTypeName;
	}
	public void setChangerTypeName(String changerTypeName) {
		this.changerTypeName = changerTypeName;
	}
	public String getChangerTypeVal() {
		return changerTypeVal;
	}
	public void setChangerTypeVal(String changerTypeVal) {
		this.changerTypeVal = changerTypeVal;
	}
	public String getAccountAddrProvince() {
		return accountAddrProvince;
	}
	public void setAccountAddrProvince(String accountAddrProvince) {
		this.accountAddrProvince = accountAddrProvince;
	}
	public String getAccountAddrCity() {
		return accountAddrCity;
	}
	public void setAccountAddrCity(String accountAddrCity) {
		this.accountAddrCity = accountAddrCity;
	}
	
	
	
}
