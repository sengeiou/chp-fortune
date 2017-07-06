package com.creditharmony.fortune.template.entity;

import java.io.Serializable;

import com.creditharmony.core.dict.util.DictUtils;
import com.creditharmony.core.excel.annotation.ExcelField;
import com.creditharmony.fortune.utils.OptionUtil;

/**
 * 线下划扣-->通联导出模板
 * @Class Name DeductTongLianExportModel
 * @author 韩龙
 * @Create In 2016年3月2日
 */
public class DeductTongLianExportModel implements Serializable {

	/**
	 * long serialVersionUID 
	 */
	private static final long serialVersionUID = 1066693773765933903L;
	
	// 序号*
	@ExcelField(title = "序号")
	private String index;
	// 用户编号
	@ExcelField(title = "用户编号")
	private String userNumber;
	// 银行代码*
	@ExcelField(title = "银行代码")
	private String accountBank;
	// 账号类型
	@ExcelField(title = "账号类型")
	private String accountNumberType;
	// 账号*
	@ExcelField(title = "账号")
	private String accountNo;
	// 户名*
	@ExcelField(title = "户名")
	private String accountName;
	// 省
	@ExcelField(title = "省")
	private String accountAddrProvince;
	// 市
	@ExcelField(title = "市")
	private String accountAddrCity;
	// 开户行名称
	@ExcelField(title = "开户行名称")
	private String accountBranch;
	// 账户类型 
	@ExcelField(title = "账户类型 ")
	private String accountType;
	// 金额* 
	@ExcelField(title = "金额")
	private String deductMoney;
	// 货币类型
	@ExcelField(title = "货币类型")
	private String typeOfCurrency;
	// 协议号
	@ExcelField(title = "协议号")
	private String protocolNumber;
	// 协议用户编号
	@ExcelField(title = "协议用户编号")
	private String protocolUserNumber;
	// 开户证件类型
	@ExcelField(title = "开户证件类型")
	private String dictCustomerCertType;
	// 证件号
	@ExcelField(title = "证件号")
	private String customerCertNum;
	// 手机号/小灵通
	@ExcelField(title = "手机号/小灵通")
	private String customerMobilephone;
	// 自定义用户号
	@ExcelField(title = "自定义用户号")
	private String customUserNumber;
	// 备注
	@ExcelField(title = "备注")
	private String tongRemarks;
	// 反馈码
	@ExcelField(title = "反馈码")
	private String feedbackCode;
	// 原因
	@ExcelField(title = "原因")
	private String reason;
	
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	public String getUserNumber() {
		return userNumber;
	}
	public void setUserNumber(String userNumber) {
		this.userNumber = userNumber;
	}
	public String getAccountBank() {
		return accountBank;
	}
	public void setAccountBank(String accountBank) {
		this.accountBank = accountBank;
	}
	public String getAccountNumberType() {
		return accountNumberType;
	}
	public void setAccountNumberType(String accountNumberType) {
		this.accountNumberType = accountNumberType;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getAccountAddrProvince() {
		if(accountAddrProvince!=null){
			accountAddrProvince= OptionUtil.getProvinceLabel(accountAddrProvince);
		}
		return accountAddrProvince;
	}
	public void setAccountAddrProvince(String accountAddrProvince) {
		this.accountAddrProvince = accountAddrProvince;
	}
	public String getAccountAddrCity() {
		if(accountAddrCity!=null){
			accountAddrCity =  OptionUtil.getCityLabel(accountAddrCity);
		}
		return accountAddrCity;
	}
	public void setAccountAddrCity(String accountAddrCity) {
		this.accountAddrCity = accountAddrCity;
	}
	public String getAccountBranch() {
		return accountBranch;
	}
	public void setAccountBranch(String accountBranch) {
		this.accountBranch = accountBranch;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public String getDeductMoney() {
		return deductMoney;
	}
	public void setDeductMoney(String deductMoney) {
		this.deductMoney = deductMoney;
	}
	public String getTypeOfCurrency() {
		return typeOfCurrency;
	}
	public void setTypeOfCurrency(String typeOfCurrency) {
		this.typeOfCurrency = typeOfCurrency;
	}
	public String getProtocolNumber() {
		return protocolNumber;
	}
	public void setProtocolNumber(String protocolNumber) {
		this.protocolNumber = protocolNumber;
	}
	public String getProtocolUserNumber() {
		return protocolUserNumber;
	}
	public void setProtocolUserNumber(String protocolUserNumber) {
		this.protocolUserNumber = protocolUserNumber;
	}
	public String getDictCustomerCertType() {
		if(dictCustomerCertType != null){
			dictCustomerCertType = DictUtils.getDictLabel(dictCustomerCertType, "tz_certificate_type", "");
		}
		return dictCustomerCertType;
	}
	public void setDictCustomerCertType(String dictCustomerCertType) {
		this.dictCustomerCertType = dictCustomerCertType;
	}
	public String getCustomerCertNum() {
		return customerCertNum;
	}
	public void setCustomerCertNum(String customerCertNum) {
		this.customerCertNum = customerCertNum;
	}
	public String getCustomerMobilephone() {
		return customerMobilephone;
	}
	public void setCustomerMobilephone(String customerMobilephone) {
		this.customerMobilephone = customerMobilephone;
	}
	public String getCustomUserNumber() {
		return customUserNumber;
	}
	public void setCustomUserNumber(String customUserNumber) {
		this.customUserNumber = customUserNumber;
	}
	public String getTongRemarks() {
		return tongRemarks;
	}
	public void setTongRemarks(String tongRemarks) {
		this.tongRemarks = tongRemarks;
	}
	public String getFeedbackCode() {
		return feedbackCode;
	}
	public void setFeedbackCode(String feedbackCode) {
		this.feedbackCode = feedbackCode;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	
	@Override
	public String toString() {
		return "index=" + index + ", userNumber="
				+ userNumber + ", accountBank=" + accountBank
				+ ", accountNumberType=" + accountNumberType + ", accountNo="
				+ accountNo + ", accountName=" + accountName
				+ ", accountAddrProvince=" + accountAddrProvince
				+ ", accountAddrCity=" + accountAddrCity + ", accountBranch="
				+ accountBranch + ", accountType=" + accountType
				+ ", deductMoney=" + deductMoney + ", typeOfCurrency="
				+ typeOfCurrency + ", protocolNumber=" + protocolNumber
				+ ", protocolUserNumber=" + protocolUserNumber
				+ ", dictCustomerCertType=" + dictCustomerCertType
				+ ", customerCertNum=" + customerCertNum
				+ ", customerMobilephone=" + customerMobilephone
				+ ", customUserNumber=" + customUserNumber + ", tongRemarks="
				+ tongRemarks + ", feedbackCode=" + feedbackCode + ", reason="
				+ reason;
	}
	
}
