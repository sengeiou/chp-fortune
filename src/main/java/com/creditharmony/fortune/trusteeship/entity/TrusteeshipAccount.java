package com.creditharmony.fortune.trusteeship.entity;

import com.creditharmony.core.excel.annotation.ExcelField;
import com.creditharmony.core.persistence.DataEntity;

/**
 * 金账户开户
 * @Class Name TrusteeshipQueryView
 * @author 朱杰
 * @Create In 2016年2月13日
 */
public class TrusteeshipAccount extends DataEntity<TrusteeshipAccount>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@ExcelField(title = "序号")
	private String index;
	// 客户姓名
	@ExcelField(title = "客户姓名")
	private String customerName;
	
	//证件号码
	@ExcelField(title = "身份证号码")
	private String customerCertNum;
	
	//手机号码
	@ExcelField(title = "手机号码")
	private String mobilephone;
	
	//邮箱
	@ExcelField(title = "邮箱地址")
	private String email;	
	
	private String districtName;
	
	//开户行省
	@ExcelField(title = "开户行省市")
	private String addrprovince;
	//开户行市
	@ExcelField(title = "开户行区县")
	private String addrcity;
	
	@ExcelField(title = "开户行行别")
	private String bankName;
	
	// 客户编号
	private String customerCode;
	// 出借编号
	private String lendCode;
	//证件类型
	private String customerCertType;
	//开户行
	private String bankId;
	
	private String addrcityId;
	//开户行区
	private String addrdistrict;
	//支行名
	@ExcelField(title = "开户行支行名称")
	private String branch;
	//户名
	@ExcelField(title = "户名")
	private String accountName;
	//银行卡号
	@ExcelField(title = "帐号")
	private String accountNo;
	//托管状态
	private String applyHostedStatus;
	//失败原因
	private String trusteeshipRetMsg;
	//返回码
	private String trusteeshipRetCode;
	@ExcelField(title = "初始密码")
	private String password;
	@ExcelField(title = "备注")
	private String mark;
	//修改状态
	private String chgStatus;
	// 付款方式
	private String payType;
	
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
	public String getLendCode() {
		return lendCode;
	}
	public void setLendCode(String lendCode) {
		this.lendCode = lendCode;
	}
	public String getCustomerCertNum() {
		return customerCertNum;
	}
	public void setCustomerCertNum(String customerCertNum) {
		this.customerCertNum = customerCertNum;
	}
	public String getMobilephone() {
		return mobilephone;
	}
	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getBankId() {
		return bankId;
	}
	public void setBankId(String bankId) {
		this.bankId = bankId;
	}
	public String getAddrprovince() {
		return addrprovince;
	}
	public void setAddrprovince(String addrprovince) {
		this.addrprovince = addrprovince;
	}
	public String getAddrcity() {
		return addrcity;
	}
	public void setAddrcity(String addrcity) {
		this.addrcity = addrcity;
	}
	public String getAddrdistrict() {
		return addrdistrict;
	}
	public void setAddrdistrict(String addrdistrict) {
		this.addrdistrict = addrdistrict;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
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
	public String getCustomerCertType() {
		return customerCertType;
	}
	public void setCustomerCertType(String customerCertType) {
		this.customerCertType = customerCertType;
	}
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMark() {
		return mark;
	}
	public void setMark(String mark) {
		this.mark = mark;
	}
	public String getApplyHostedStatus() {
		return applyHostedStatus;
	}
	public void setApplyHostedStatus(String applyHostedStatus) {
		this.applyHostedStatus = applyHostedStatus;
	}
	public String getTrusteeshipRetMsg() {
		return trusteeshipRetMsg;
	}
	public void setTrusteeshipRetMsg(String trusteeshipRetMsg) {
		this.trusteeshipRetMsg = trusteeshipRetMsg;
	}
	public String getTrusteeshipRetCode() {
		return trusteeshipRetCode;
	}
	public void setTrusteeshipRetCode(String trusteeshipRetCode) {
		this.trusteeshipRetCode = trusteeshipRetCode;
	}
	public String getChgStatus() {
		return chgStatus;
	}
	public void setChgStatus(String chgStatus) {
		this.chgStatus = chgStatus;
	}
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	public String getAddrcityId() {
		return addrcityId;
	}
	public void setAddrcityId(String addrcityId) {
		this.addrcityId = addrcityId;
	}
	
}