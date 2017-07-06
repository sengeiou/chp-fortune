package com.creditharmony.fortune.back.money.common.entity;

import com.creditharmony.core.persistence.DataEntity;

/**
 * 第三方平台对应Bean
 * @Class Name Platform
 * @author 陈广鹏
 * @Create In 2015年12月3日
 */
public class ThirdPlatform extends DataEntity<ThirdPlatform> {

	/**
	 * long serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	private String platformId;	// 平台Code
	private String name;		// 平台名或者中间人姓名
	private String certNo;		// 证件号码
	private String bank;		// 开户行
	private String bankcode;	// 银行账号

	public String getPlatformId() {
		return platformId;
	}

	public void setPlatformId(String platformId) {
		this.platformId = platformId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getBankcode() {
		return bankcode;
	}

	public void setBankcode(String bankcode) {
		this.bankcode = bankcode;
	}

}
