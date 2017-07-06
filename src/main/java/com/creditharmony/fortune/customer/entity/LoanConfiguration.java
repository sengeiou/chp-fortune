package com.creditharmony.fortune.customer.entity;

import java.util.Date;

import com.creditharmony.core.persistence.DataEntity;

/**
 * @Class Name LoanConfiguration
 * @author 孙凯文
 * @Create In 2015年11月27日
 */
public class LoanConfiguration extends DataEntity<LoanConfiguration> {

	private static final long serialVersionUID = 1L;
	// 客户编号
	private String custCode;
	// 账单收取方式
	private String loanBillRecv;
	// 是否委托协议
	private String loanSign;
	// 委托协议种类
	private String loanAgreementType;
	// 委托协议版本
	private String loanAgreementEdition;
	// 签订日期
	private Date agreemrntSignDate;

	public String getCustCode() {
		return custCode;
	}

	public void setCustCode(String custCode) {
		this.custCode = custCode;
	}

	public String getLoanBillRecv() {
		return loanBillRecv;
	}

	public void setLoanBillRecv(String loanBillRecv) {
		this.loanBillRecv = loanBillRecv;
	}

	public String getLoanSign() {
		return loanSign;
	}

	public void setLoanSign(String loanSign) {
		this.loanSign = loanSign;
	}

	public String getLoanAgreementType() {
		return loanAgreementType;
	}

	public void setLoanAgreementType(String loanAgreementType) {
		this.loanAgreementType = loanAgreementType;
	}

	public String getLoanAgreementEdition() {
		return loanAgreementEdition;
	}

	public void setLoanAgreementEdition(String loanAgreementEdition) {
		this.loanAgreementEdition = loanAgreementEdition;
	}

	public Date getAgreemrntSignDate() {
		return agreemrntSignDate;
	}

	public void setAgreemrntSignDate(Date agreemrntSignDate) {
		this.agreemrntSignDate = agreemrntSignDate;
	}

}
