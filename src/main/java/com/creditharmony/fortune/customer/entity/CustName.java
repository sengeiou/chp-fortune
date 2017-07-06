package com.creditharmony.fortune.customer.entity;

import java.io.Serializable;

import com.creditharmony.core.excel.annotation.ExcelField;

public class CustName implements Serializable{

	private static final long serialVersionUID = 1L;
	/**
	 * 客户姓名
	 */
	@ExcelField(title = "客户姓名")
	private String customerName;
	// 证件号码
	@ExcelField(title="证件号码")
	private String custCertNum;
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustCertNum() {
		return custCertNum;
	}
	public void setCustCertNum(String custCertNum) {
		this.custCertNum = custCertNum;
	}
	
}
