package com.creditharmony.fortune.trusteeship.entity;

import com.creditharmony.core.excel.annotation.ExcelField;

/**
 * 金账户导入Excel实体类
 * @Class Name TrusteeshipImportExcel
 * @Create In 2016年2月29日
 */
public class TrusteeshipImportExcel {
	@ExcelField(title = "序号", type = 2,groups=1)
	private String index;
	@ExcelField(title = "客户姓名", type = 2,groups=1)
	private String name;
	@ExcelField(title = "身份证号码", type = 2,groups=1)
	private String idCard;
	@ExcelField(title = "手机号码", type = 2,groups=1)
	private String mobilePhone;
	@ExcelField(title = "邮箱地址", type = 2,groups=1)
	private String email;
	@ExcelField(title = "开户行省市", type = 2,groups=1)
	private String bankProvinceCode;
	private String bankCityCode;
	@ExcelField(title = "开户行区县", type = 2,groups=1)
	private String bankDistrictCode;
	@ExcelField(title = "开户行行别", type = 2,groups=1)
	private String bankCode;
	@ExcelField(title = "开户行支行名称", type = 2,groups=1)
	private String bankBranchName;
	@ExcelField(title = "户名", type = 2,groups=1)
	private String bankAccountName;
	@ExcelField(title = "帐号", type = 2,groups=1)
	private String bankAccountNum;
	@ExcelField(title = "初始密码", type = 2,groups=1)
	private String password;
	@ExcelField(title = "备注", type = 2,groups=1)
	private String mark;
	@ExcelField(title = "返回码", type = 2,groups=1)
	private String returnCode;
	@ExcelField(title = "返回描述", type = 2,groups=1)
	private String returnMark;
	
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public String getMobilePhone() {
		return mobilePhone;
	}
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getBankProvinceCode() {
		return bankProvinceCode;
	}
	public void setBankProvinceCode(String bankProvinceCode) {
		this.bankProvinceCode = bankProvinceCode;
	}
	public String getBankCityCode() {
		return bankCityCode;
	}
	public void setBankCityCode(String bankCityCode) {
		this.bankCityCode = bankCityCode;
	}
	public String getBankDistrictCode() {
		return bankDistrictCode;
	}
	public void setBankDistrictCode(String bankDistrictCode) {
		this.bankDistrictCode = bankDistrictCode;
	}
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	public String getBankAccountName() {
		return bankAccountName;
	}
	public void setBankAccountName(String bankAccountName) {
		this.bankAccountName = bankAccountName;
	}
	public String getBankAccountNum() {
		return bankAccountNum;
	}
	public void setBankAccountNum(String bankAccountNum) {
		this.bankAccountNum = bankAccountNum;
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
	public String getReturnCode() {
		return returnCode;
	}
	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}
	public String getReturnMark() {
		return returnMark;
	}
	public void setReturnMark(String returnMark) {
		this.returnMark = returnMark;
	}
	public String getBankBranchName() {
		return bankBranchName;
	}
	public void setBankBranchName(String bankBranchName) {
		this.bankBranchName = bankBranchName;
	}
}
