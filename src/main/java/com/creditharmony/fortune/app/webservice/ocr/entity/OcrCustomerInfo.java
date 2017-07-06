package com.creditharmony.fortune.app.webservice.ocr.entity;

import java.math.BigDecimal;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import com.creditharmony.core.persistence.DataEntity;

/**
 * @Class Name OcrCustomerInfo
 * @author 张虎
 * @Create In 2016年4月7日
 */
public class OcrCustomerInfo extends DataEntity<OcrCustomerInfo> {
	
	 /**
	 * long serialVersionUID 
	 */
	private static final long serialVersionUID = 1L;

	private String customerId;
	private String userId;
	private String source;
	private String bankId;
	private String name;
	private String sex;
	private String mobilephone;
	private String state;
	private boolean isLender;
	// 地址信息
	private String addressProvince;
	private String addressCity;
	private String addressDistrict;
	private String address;
	// 身份证信息
	private String birthday;
	private String certOrg;
	private String certNum;
	private String idStartDate;
	private String idEndDate;
	// 银行卡信息
	private String accountBank;
	private String branch;
	private String accountid;
	private String bankProvince;
	private String bankCity;
	private String bankDistrict;
	// 咨询信息
	private String content;
	private BigDecimal contributiveMoney;
	private String intentionProduct;
	private String loanMonth;
	// 呼叫中心接口添加字段
	private String phone;
	private String email;
	private String unitname;
	private String industry;
	private String post;
	private String postalCode;
	private String addrProvince;
	private String addrCity;
	private String addrDistrict;
	private String addr;
	private String picPath;// 身份证切图路径
	private String bankPicPath;// 银行卡切图路径
	
	private String picNamePath;// 姓名图片路径
	private String picCertPath;// 身份证号图片路径
	

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getPicPath() {
		return picPath;
	}

	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}

	public String getBankPicPath() {
		return bankPicPath;
	}

	public void setBankPicPath(String bankPicPath) {
		this.bankPicPath = bankPicPath;
	}

	public String getAddressProvince() {
		return addressProvince;
	}

	public void setAddressProvince(String addressProvince) {
		this.addressProvince = addressProvince;
	}

	public String getAddressCity() {
		return addressCity;
	}

	public void setAddressCity(String addressCity) {
		this.addressCity = addressCity;
	}

	public String getAddressDistrict() {
		return addressDistrict;
	}

	public void setAddressDistrict(String addressDistrict) {
		this.addressDistrict = addressDistrict;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public BigDecimal getContributiveMoney() {
		return contributiveMoney;
	}

	public void setContributiveMoney(BigDecimal contributiveMoney) {
		this.contributiveMoney = contributiveMoney;
	}

	public String getIntentionProduct() {
		return intentionProduct;
	}

	public void setIntentionProduct(String intentionProduct) {
		this.intentionProduct = intentionProduct;
	}

	public String getLoanMonth() {
		return loanMonth;
	}

	public void setLoanMonth(String loanMonth) {
		this.loanMonth = loanMonth;
	}

	public String getBankProvince() {
		return bankProvince;
	}

	public void setBankProvince(String bankProvince) {
		this.bankProvince = bankProvince;
	}

	public String getBankCity() {
		return bankCity;
	}

	public void setBankCity(String bankCity) {
		this.bankCity = bankCity;
	}

	public String getBankDistrict() {
		return bankDistrict;
	}

	public void setBankDistrict(String bankDistrict) {
		this.bankDistrict = bankDistrict;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getMobilephone() {
		return mobilephone;
	}

	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUnitname() {
		return unitname;
	}

	public void setUnitname(String unitname) {
		this.unitname = unitname;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getAddrProvince() {
		return addrProvince;
	}

	public void setAddrProvince(String addrProvince) {
		this.addrProvince = addrProvince;
	}

	public String getAddrCity() {
		return addrCity;
	}

	public void setAddrCity(String addrCity) {
		this.addrCity = addrCity;
	}

	public String getAddrDistrict() {
		return addrDistrict;
	}

	public void setAddrDistrict(String addrDistrict) {
		this.addrDistrict = addrDistrict;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public boolean isLender() {
		return isLender;
	}

	public void setLender(boolean isLender) {
		this.isLender = isLender;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBankId() {
		return bankId;
	}

	public void setBankId(String bankId) {
		this.bankId = bankId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCertNum() {
		return certNum;
	}

	public void setCertNum(String certNum) {
		this.certNum = certNum;
	}

	public String getCertOrg() {
		return certOrg;
	}

	public void setCertOrg(String certOrg) {
		this.certOrg = certOrg;
	}

	public String getAccountBank() {
		return accountBank;
	}

	public void setAccountBank(String accountBank) {
		this.accountBank = accountBank;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getAccountid() {
		return accountid;
	}

	public void setAccountid(String accountid) {
		this.accountid = accountid;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getIdStartDate() {
		return idStartDate;
	}

	public void setIdStartDate(String idStartDate) {
		this.idStartDate = idStartDate;
	}

	public String getIdEndDate() {
		return idEndDate;
	}

	public void setIdEndDate(String idEndDate) {
		this.idEndDate = idEndDate;
	}

	public String getPicNamePath() {
		return picNamePath;
	}

	public void setPicNamePath(String picNamePath) {
		this.picNamePath = picNamePath;
	}

	public String getPicCertPath() {
		return picCertPath;
	}

	public void setPicCertPath(String picCertPath) {
		this.picCertPath = picCertPath;
	}
}
