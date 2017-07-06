package com.creditharmony.fortune.template.entity.backInterest;

import java.io.Serializable;
import java.util.Date;

import com.creditharmony.core.excel.annotation.ExcelField;
import com.creditharmony.core.fortune.type.PayMent;

/**
 * 待回息申请确认列表上传回息金额
 * @Class Name BackInterestApplyExt 
 * @author 李志伟
 * @Create In 2016年1月5日
 */
public class UploadMoney implements Serializable{

	/**
	 * long serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 出借编号
	 */
	@ExcelField(title = "出借编号")
	private String lendCode;
	
	/**
	 * 收款账户
	 */
	@ExcelField(title = "收款账户")
	private String accountNo;
	
	/**
	 * 收款户名
	 */
	@ExcelField(title = "收款户名")
	private String accountName;
	
	/**
	 * 当期应回金额
	 */
	@ExcelField(title = "当期应回金额")
	private Double backRealMoney;
	
	/**
	 *  (收款银行)银行编码
	 */
	@ExcelField(title = "银行编码")
	private String accountBank;
	
	/**
	 * 收款银行名称
	 */
	@ExcelField(title = "收款银行")
	private String bankName;
	
	/**
	 * 收款银行支行
	 */
	@ExcelField(title = "收款银行支行")
	private String accountBranch;
	
	/**
	 * 卡/折
	 */
	@ExcelField(title = "账户类型")
	private String accountCardOrBooklet;
	
	/**
	 * 收款省/直辖市
	 */
	@ExcelField(title = "收款/直辖市")
	private String accountAddrprovince;
	
	/**
	 * 收款市/区
	 */
	@ExcelField(title = "收款县")
	private String accountAddrdistrict;
	
	/**
	 * 回款类型(月息通回息、信和宝回息)
	 */
	@ExcelField(title = "回款类型")
	private String productName;
	
	/**
	 * 序列号
	 */
	@ExcelField(title = "序列号")
	private String backiId;
	
	/**
	 * 备注
	 */
	@ExcelField(title = "备注")
	private String memo;
	
	/**
	 * 初始出借日期
	 */
	@ExcelField(title = "初始出借日期")
	private Date applyLendDay;
	
	/**
	 * 初始出借金额
	 */
	@ExcelField(title = "初始出借金额")
	private Double applyLendMoney;
	
	/**
	 * 付款方式
	 */
	@ExcelField(title = "付款方式")
	private String applyPay;

	public String getLendCode() {
		return lendCode;
	}

	public void setLendCode(String lendCode) {
		this.lendCode = lendCode;
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

	public Double getBackRealMoney() {
		return backRealMoney;
	}

	public void setBackRealMoney(Double backRealMoney) {
		this.backRealMoney = backRealMoney;
	}

	public void setApplyLendMoney(Double applyLendMoney) {
		this.applyLendMoney = applyLendMoney;
	}
	
	public Double getApplyLendMoney() {
		return applyLendMoney;
	}

	public String getAccountBank() {
		return accountBank;
	}

	public void setAccountBank(String accountBank) {
		this.accountBank = accountBank;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getAccountBranch() {
		return accountBranch;
	}

	public void setAccountBranch(String accountBranch) {
		this.accountBranch = accountBranch;
	}

	public String getAccountCardOrBooklet() {
		return accountCardOrBooklet;
	}

	public void setAccountCardOrBooklet(String accountCardOrBooklet) {
		this.accountCardOrBooklet = accountCardOrBooklet;
	}

	public String getAccountAddrprovince() {
		return accountAddrprovince;
	}

	public void setAccountAddrprovince(String accountAddrprovince) {
		this.accountAddrprovince = accountAddrprovince;
	}

	public String getAccountAddrdistrict() {
		return accountAddrdistrict;
	}

	public void setAccountAddrdistrict(String accountAddrdistrict) {
		this.accountAddrdistrict = accountAddrdistrict;
	}
	
	public String getBackiId() {
		return backiId;
	}

	public void setBackiId(String backiId) {
		this.backiId = backiId;
	}

	public String getMemo() {
		return memo;
	}
	
	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Date getApplyLendDay() {
		return applyLendDay;
	}

	public void setApplyLendDay(Date applyLendDay) {
		this.applyLendDay = applyLendDay;
	}

	public String getApplyPay() {
		return PayMent.getPayMent(applyPay);
	}

	public void setApplyPay(String applyPay) {
		this.applyPay = applyPay;
	}
}