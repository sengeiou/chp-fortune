package com.creditharmony.fortune.change.lend.apply.entity;

import com.creditharmony.core.persistence.DataEntity;

public class BranchBankCode extends DataEntity<BranchBankCode> {

	private static final long serialVersionUID = -8886873255542195264L;

	private String flag1;
	private String bankCode;
	private String code2;
	private String bankName;
	private String code3;
	private String flag2;

	public String getFlag1() {
		return flag1;
	}

	public void setFlag1(String flag1) {
		this.flag1 = flag1;
	}

	public String getCode2() {
		return code2;
	}

	public void setCode2(String code2) {
		this.code2 = code2;
	}

	public String getCode3() {
		return code3;
	}

	public void setCode3(String code3) {
		this.code3 = code3;
	}

	public String getFlag2() {
		return flag2;
	}

	public void setFlag2(String flag2) {
		this.flag2 = flag2;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

}
