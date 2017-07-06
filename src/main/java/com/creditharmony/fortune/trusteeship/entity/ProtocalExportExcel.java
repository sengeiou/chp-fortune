package com.creditharmony.fortune.trusteeship.entity;

import com.creditharmony.core.excel.annotation.ExcelField;
import com.creditharmony.core.persistence.DataEntity;

/**
 * 导出协议库实体类
 * 
 * @Class Name TrusteeshipProtocalExportExcel
 * @Create In 2016年2月29日
 */
public class ProtocalExportExcel extends DataEntity<TrusteeshipAccount>{
	private static final long serialVersionUID = 8976307387324416095L;
	@ExcelField(title = "业务类型")
	private String businessName;
	@ExcelField(title = "客户姓名")
	private String customerName;
	@ExcelField(title = "手机号码")
	private String mobilePhone;
	private String credentialsType;
	@ExcelField(title = "证件类型")
	private String credentialsName;
	@ExcelField(title = "证件号码")
	private String credentialsNum;
	@ExcelField(title = "账号")
	private String account;
	@ExcelField(title = "账号属性")
	private String accountProperty;
	private String bankId;
	@ExcelField(title = "账号行别")
	private String bankName;
	@ExcelField(title = "是否需要语音回拨")
	private String callBack;
	@ExcelField(title = "备注")
	private String mark;
	
	public String getBusinessName() {
		return businessName;
	}
	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getMobilePhone() {
		return mobilePhone;
	}
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	public String getCredentialsType() {
		return credentialsType;
	}
	public void setCredentialsType(String credentialsType) {
		this.credentialsType = credentialsType;
	}
	public String getCredentialsNum() {
		return credentialsNum;
	}
	public void setCredentialsNum(String credentialsNum) {
		this.credentialsNum = credentialsNum;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getAccountProperty() {
		return accountProperty;
	}
	public void setAccountProperty(String accountProperty) {
		this.accountProperty = accountProperty;
	}
	public String getCallBack() {
		return callBack;
	}
	public void setCallBack(String callBack) {
		this.callBack = callBack;
	}
	public String getMark() {
		return mark;
	}
	public void setMark(String mark) {
		this.mark = mark;
	}
	public String getBankId() {
		return bankId;
	}
	public void setBankId(String bankId) {
		this.bankId = bankId;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getCredentialsName() {
		return credentialsName;
	}
	public void setCredentialsName(String credentialsName) {
		this.credentialsName = credentialsName;
	}
	
}
