package com.creditharmony.fortune.template.entity;

import java.io.Serializable;

import com.creditharmony.core.common.type.CertificateType;
import com.creditharmony.core.dict.util.DictUtils;
import com.creditharmony.core.excel.annotation.ExcelField;
import com.creditharmony.fortune.deduct.common.DeductUtils;
import com.creditharmony.fortune.utils.OptionUtil;

/**
 * 线下划扣-->好易联导出模板
 * @Class Name DeductHaoylExportModel1
 * @author 韩龙
 * @Create In 2015年12月17日
 */
public class DeductHaoylExportModel implements Serializable {

	/**
	 * long serialVersionUID
	 */
	private static final long serialVersionUID = 8265211025163073774L;

	// 序号
	@ExcelField(title = "序号", align = 2)
	private String index;
	// 银联网络用户编号
	@ExcelField(title = "银联网络用户编号", align = 2)
	private String unionNetworkCode;
	// 银行代码
	@ExcelField(title = "银行代码", align = 2)
	private String accountBank;
	// 账号类型
	@ExcelField(title = "账号类型", align = 2)
	private String accountNumberType;
	// 账号
	@ExcelField(title = "账号", align = 2)
	private String accountNo;
	// 账户名
	@ExcelField(title = "账户名	", align = 2)
	private String accountName;
	// 开户行所在省
	@ExcelField(title = "开户行所在省", align = 2)
	private String accountAddrProvince;
	// 开户行所在市
	@ExcelField(title = "开户行所在市", align = 2)
	private String accountAddrCity;
	// 开户行名称
	@ExcelField(title = "开户行名称", align = 2)
	private String accountBranch;
	// 帐户类型
	@ExcelField(title = "帐户类型", align = 2)
	private String accountType;
	// 金额
	@ExcelField(title = "金额", align = 2)
	private String deductMoney;
	// 货币类型
	@ExcelField(title = "货币类型", align = 2)
	private String typeOfCurrency;
	// 协议号
	@ExcelField(title = "协议号", align = 2)
	private String protocolNumber;
	// 协议用户编号
	@ExcelField(title = "协议用户编号", align = 2)
	private String protocolUserNumber;
	// 开户证件类型
	@ExcelField(title = "开户证件类型", align = 2)
	private String dictCustomerCertType;
	// 证件号
	@ExcelField(title = "证件号", align = 2)
	private String customerCertNum;
	// 手机号
	@ExcelField(title = "手机号", align = 2)
	private String customerMobilephone;
	// 自定义用户名
	@ExcelField(title = "自定义用户名", align = 2)
	private String customUserNumber;
	// 备注1
	@ExcelField(title = "备注1", align = 2)
	private String remarks1;
	// 备注2
	@ExcelField(title = "备注2", align = 2)
	private String remarks2;
	// 备注（出借编号）
	@ExcelField(title = "备注", align = 2)
	private String haoRemarks;
	// 反馈码
	@ExcelField(title ="反馈码", align = 2)
	private String returnCode;
	// 原因
	@ExcelField(title ="原因", align = 2)
	private String reason;
	
	public String getReturnCode() {
		return returnCode;
	}
	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	public String getUnionNetworkCode() {
		return unionNetworkCode;
	}
	public void setUnionNetworkCode(String unionNetworkCode) {
		this.unionNetworkCode = unionNetworkCode;
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
	public String getRemarks1() {
		return remarks1;
	}
	public void setRemarks1(String remarks1) {
		this.remarks1 = remarks1;
	}
	public String getRemarks2() {
		return remarks2;
	}
	public void setRemarks2(String remarks2) {
		this.remarks2 = remarks2;
	}
	public String getHaoRemarks() {
		return haoRemarks;
	}
	public void setHaoRemarks(String haoRemarks) {
		this.haoRemarks = haoRemarks;
	}
	
	@Override
	public String toString() {
		return DeductUtils.isNull(index)
				+ "," + DeductUtils.isNull(unionNetworkCode) 
				+ "," + DeductUtils.isNull(accountBank) 
				+ "," + DeductUtils.isNull(accountNumberType)
				+ "," + DeductUtils.isNull(accountNo) 
				+ "," + DeductUtils.isNull(accountName)
				+ "," + DeductUtils.isNull(OptionUtil.getProvinceLabel(accountAddrProvince))
				+ "," + DeductUtils.isNull(OptionUtil.getCityLabel(accountAddrCity)) 
				+ "," + DeductUtils.isNull(accountBranch) 
				+ "," + DeductUtils.isNull(accountType)
				+ "," + DeductUtils.isNull(deductMoney) 
				+ "," + DeductUtils.isNull(typeOfCurrency) 
				+ "," + DeductUtils.isNull(protocolNumber)
				+ "," + DeductUtils.isNull(protocolUserNumber)
				+ "," + DeductUtils.isNull(CertificateType.parseByCode(dictCustomerCertType).getName())
				+ "," + DeductUtils.isNull(customerCertNum)
				+ "," + DeductUtils.isNull(customerMobilephone)
				+ "," + DeductUtils.isNull(customUserNumber) 
				+ "," + DeductUtils.isNull(remarks1) 
				+ "," + DeductUtils.isNull(remarks2) 
				+ "," + DeductUtils.isNull(haoRemarks)
				+ "," + DeductUtils.isNull(returnCode)
				+ "," + DeductUtils.isNull(reason)
				+"\r\n";
	}
}
