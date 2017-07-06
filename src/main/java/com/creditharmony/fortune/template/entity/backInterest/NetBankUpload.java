package com.creditharmony.fortune.template.entity.backInterest;

import java.io.Serializable;
import java.util.Date;

import com.creditharmony.core.excel.annotation.ExcelField;

/**
 * 网银上传模板
 * @Class Name NetBankUpload 
 * @author 李志伟
 * @Create In 2016年5月20日
 */
public class NetBankUpload implements Serializable {

	private static final long serialVersionUID = 1L;

	@ExcelField(title = "出借编号")
	private String lendCode;
	
	@ExcelField(title = "收款账户")
	private String getMoneyAccount;
	
	@ExcelField(title = "收款户名")
	private String getMoneyName;
	
	@ExcelField(title = "实际回息金额")
	private String backRealMoney;
	
	@ExcelField(title = "备注")
	private String backiId;
	
	@ExcelField(title = "收款银行")
	private String getMoneyBank;
	
	@ExcelField(title = "收款银行支行")
	private String getMoneyBankBranch;
	
	@ExcelField(title = "卡或折")
	private String cardOrBooklet;
	
	@ExcelField(title = "收款/直辖市")
	private String accountAddrprovince;
	
	@ExcelField(title = "收款县")
	private String accountAddrcity;
	
	@ExcelField(title = "放款账户")
	private String loanAccount;
	
	@ExcelField(title = "开户行")
	private String accountBank;
	
	@ExcelField(title = "银行账号")
	private String accountNo;
	
	/*@ExcelField(title = "放款日期")
	private String loanDate;*/
	
	@ExcelField(title = "放款日期")
	private Date loanDate;
	
	@ExcelField(title = "账单日")
	private String applyBillday;
	
	public String getBackiId() {
		return backiId;
	}

	public void setBackiId(String backiId) {
		this.backiId = backiId;
	}

	public String getLendCode() {
		return lendCode;
	}

	public void setLendCode(String lendCode) {
		this.lendCode = lendCode;
	}

	public String getGetMoneyAccount() {
		return getMoneyAccount;
	}

	public void setGetMoneyAccount(String getMoneyAccount) {
		this.getMoneyAccount = getMoneyAccount;
	}

	public String getGetMoneyName() {
		return getMoneyName;
	}

	public void setGetMoneyName(String getMoneyName) {
		this.getMoneyName = getMoneyName;
	}

	public String getBackRealMoney() {
		return backRealMoney;
	}

	public void setBackRealMoney(String backRealMoney) {
		this.backRealMoney = backRealMoney;
	}

	public String getGetMoneyBank() {
		return getMoneyBank;
	}

	public void setGetMoneyBank(String getMoneyBank) {
		this.getMoneyBank = getMoneyBank;
	}

	public String getGetMoneyBankBranch() {
		return getMoneyBankBranch;
	}

	public void setGetMoneyBankBranch(String getMoneyBankBranch) {
		this.getMoneyBankBranch = getMoneyBankBranch;
	}

	public String getCardOrBooklet() {
		return cardOrBooklet;
	}

	public void setCardOrBooklet(String cardOrBooklet) {
		this.cardOrBooklet = cardOrBooklet;
	}

	public String getAccountAddrprovince() {
		return accountAddrprovince;
	}

	public void setAccountAddrprovince(String accountAddrprovince) {
		this.accountAddrprovince = accountAddrprovince;
	}

	public String getAccountAddrcity() {
		return accountAddrcity;
	}

	public void setAccountAddrcity(String accountAddrcity) {
		this.accountAddrcity = accountAddrcity;
	}

	public String getLoanAccount() {
		return loanAccount;
	}

	public void setLoanAccount(String loanAccount) {
		this.loanAccount = loanAccount;
	}

	public String getAccountBank() {
		return accountBank;
	}

	public void setAccountBank(String accountBank) {
		this.accountBank = accountBank;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	/*public String getLoanDate() {
		return loanDate;
	}

	public void setLoanDate(String loanDate) {
		this.loanDate = loanDate;
	}*/
	public Date getLoanDate() {
		return loanDate;
	}

	public void setLoanDate(Date loanDate) {
		this.loanDate = loanDate;
	}

	public String getApplyBillday() {
		return applyBillday;
	}

	public void setApplyBillday(String applyBillday) {
		this.applyBillday = applyBillday;
	}
	
}