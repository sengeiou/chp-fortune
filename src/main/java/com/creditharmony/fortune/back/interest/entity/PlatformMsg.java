package com.creditharmony.fortune.back.interest.entity;

import java.io.Serializable;
/**
 * 中间人信息
 * @Class Name PlatformMsg 
 * @author 李志伟
 * @Create In 2015年12月10日
 */
public class PlatformMsg implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String id;// ID
	private String Name;// 平台名称或者中间人
	private String platformId;// 平台ID
	private String certNo;// 证件号码
	private String bank;// 开户行
	private String bankCode;// 银行账号
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPlatformId() {
		return platformId;
	}
	public void setPlatformId(String platformId) {
		this.platformId = platformId;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getCertNo() {
		return certNo;
	}
	public void setCertNo(String certNo) {
		this.certNo = certNo;
	}
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	@Override
	public String toString() {
		return "PlatformMsg [platformId=" + platformId + ", Name=" + Name
				+ ", certNo=" + certNo + ", bank=" + bank + ", bankCode="
				+ bankCode + "]";
	}
}