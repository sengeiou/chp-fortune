package com.creditharmony.fortune.template.entity;

import java.io.Serializable;

import com.creditharmony.core.common.type.CertificateType;
import com.creditharmony.core.excel.annotation.ExcelField;


/**
 * 协议库导出模板
 * @Class Name DeductFailProtocolExportModel
 * @author 韩龙
 * @Create In 2015年12月17日
 */
public class DeductFailProtocolExportModel implements Serializable{

	/**
	 * long serialVersionUID 
	 */
	private static final long serialVersionUID = 5827035542764585714L;
	
	// 业务类型
	@ExcelField(title = "业务类型" , align=2)
	private String businessType;
	// 客户姓名
	@ExcelField(title = "客户姓名" , align=2)
	private String customerName;
	// 手机号码
	@ExcelField(title = "手机号码" , align=2)
	private String customerMobilephone;
	// 证件类型
	@ExcelField(title = "证件类型" , align=2)
	private String dictCustomerCertType;
	// 证件号码
	@ExcelField(title = "证件号码" , align=2)
	private String customerCertNum;
	// 账号
	@ExcelField(title = "账号" , align=2)
	private String accountNo;
	// 账户属性
	@ExcelField(title = "账户属性" , align=2)
	private String accountCardOrBooklet;
	// 账户行别
	@ExcelField(title = "账户行别" , align=2)
	private String accountBank;
	// 是否语音回拨
	@ExcelField(title = "是否语音回拨" , align=2)
	private String voiceDail;
	// 备注
	@ExcelField(title = "备注" , align=2)
	private String remarks;
	public String getBusinessType() {
		return businessType;
	}
	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerMobilephone() {
		return customerMobilephone;
	}
	public void setCustomerMobilephone(String customerMobilephone) {
		this.customerMobilephone = customerMobilephone;
	}
	public String getDictCustomerCertType() {
		if(dictCustomerCertType != null){
			dictCustomerCertType = CertificateType.parseByCode(dictCustomerCertType).getName();
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
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public String getAccountCardOrBooklet() {
		return accountCardOrBooklet;
	}
	public void setAccountCardOrBooklet(String accountCardOrBooklet) {
		this.accountCardOrBooklet = accountCardOrBooklet;
	}
	public String getVoiceDail() {
		return voiceDail;
	}
	public void setVoiceDail(String voiceDail) {
		this.voiceDail = voiceDail;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getAccountBank() {
		return accountBank;
	}
	public void setAccountBank(String accountBank) {
		this.accountBank = accountBank;
	}
}
