package com.creditharmony.fortune.trusteeship.entity;

import java.io.Serializable;

import com.creditharmony.core.excel.annotation.ExcelField;

/**
 * 划扣列表导出基类
 * @Class Name DeductExportModel
 * @author 韩龙
 * @Create In 2015年12月17日
 */
public class TrusteeshipExportModel implements Serializable{

	/**
	 * long serialVersionUID 
	 */
	private static final long serialVersionUID = 8265211025163073774L;
	
	
	
	// 变更状态
	@ExcelField(title = "" ,align=2)
	private String changeState;
	// 银行所在省份
	@ExcelField(title = "所在省" ,align=2)
	private String accountAddrProvince;
	// 银行所在城市
	@ExcelField(title = "所在城市" ,align=2)
	private String accountAddrCity;
	// 金账户账号
	@ExcelField(title = "金账户账号" ,align=2)
	private String trusteeshipNo;
	// 手机号码
	@ExcelField(title = "接收验证手机号码" ,align=2)
	private String custMobilephone;
	// 开户行名称
	@ExcelField(title = "开户行支行名称" ,align=2)
	private String accountBankId;
	// 账户名称
	@ExcelField(title = "户名" ,align=2)
	private String accountName;
	// 账号
	@ExcelField(title = "卡号" ,align=2)
	private String accountNo;
	// 证件类型
	@ExcelField(title = "证件类型" ,align=2)
	private String custCertType;
	// 证件号码
	@ExcelField(title = "证件号码" ,align=2)
	private String custCertNum;
	
	public String getChangeState() {
		return changeState;
	}
	public void setChangeState(String changeState) {
		this.changeState = changeState;
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
	public String getTrusteeshipNo() {
		return trusteeshipNo;
	}
	public void setTrusteeshipNo(String trusteeshipNo) {
		this.trusteeshipNo = trusteeshipNo;
	}
	public String getCustMobilephone() {
		return custMobilephone;
	}
	public void setCustMobilephone(String custMobilephone) {
		this.custMobilephone = custMobilephone;
	}
	public String getAccountBankId() {
		return accountBankId;
	}
	public void setAccountBankId(String accountBankId) {
		this.accountBankId = accountBankId;
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
	public String getCustCertType() {
		return custCertType;
	}
	public void setCustCertType(String custCertType) {
		this.custCertType = custCertType;
	}
	public String getCustCertNum() {
		return custCertNum;
	}
	public void setCustCertNum(String custCertNum) {
		this.custCertNum = custCertNum;
	}
	
	
	
	
	
	
}
