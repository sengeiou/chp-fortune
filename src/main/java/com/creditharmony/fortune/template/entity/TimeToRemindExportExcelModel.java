package com.creditharmony.fortune.template.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.creditharmony.core.excel.annotation.ExcelField;

/**
 * 消息提醒导出excel model
 * @Class Name TimeToRemindExportExcelModel
 * @author 韩龙
 * @Create In 2015年11月27日
 */
public class TimeToRemindExportExcelModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 客户姓名
	@ExcelField(title = "客户姓名")
	private String customerName;

	// 出借编号
	@ExcelField(title = "出借编号")
	private BigDecimal loanCode;

	// 出借日期
	@ExcelField(title = "计划出借日期")
	private Date lendDay;

	// 出借金额
	@ExcelField(title = "计划出借金额")
	private BigDecimal lendMoney;

	// 出借方式
	@ExcelField(title = "出借方式")
	private String dictLendType;

	// 到期日期
	@ExcelField(title = "到期日期")
	private Date dueDay;

	// 手机号
	@ExcelField(title = "联系电话")
	private String tel;

	// 门店名称
	@ExcelField(title = "门店名称")
	private String menDianMingCheng;

	// 所在城市
	@ExcelField(title = "所在城市")
	private String souZaiCity;

	// 开户行
	@ExcelField(title = "回款开户行")
	private String accountBank;

	// 具体支行
	@ExcelField(title = "回款具体支行 ")
	private String accountBranch;

	// 银行账号
	@ExcelField(title = "账号")
	private String bankCode;

	// 付款方式
	@ExcelField(title = "付款方式")
	private String payType;

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public BigDecimal getLoanCode() {
		return loanCode;
	}

	public void setLoanCode(BigDecimal loanCode) {
		this.loanCode = loanCode;
	}

	public Date getLendDay() {
		return lendDay;
	}

	public void setLendDay(Date lendDay) {
		this.lendDay = lendDay;
	}

	public BigDecimal getLendMoney() {
		return lendMoney;
	}

	public void setLendMoney(BigDecimal lendMoney) {
		this.lendMoney = lendMoney;
	}

	public String getDictLendType() {
		return dictLendType;
	}

	public void setDictLendType(String dictLendType) {
		this.dictLendType = dictLendType;
	}

	public Date getDueDay() {
		return dueDay;
	}

	public void setDueDay(Date dueDay) {
		this.dueDay = dueDay;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getAccountBank() {
		return accountBank;
	}

	public void setAccountBank(String accountBank) {
		this.accountBank = accountBank;
	}

	public String getAccountBranch() {
		return accountBranch;
	}

	public void setAccountBranch(String accountBranch) {
		this.accountBranch = accountBranch;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getMenDianMingCheng() {
		return menDianMingCheng;
	}

	public void setMenDianMingCheng(String menDianMingCheng) {
		this.menDianMingCheng = menDianMingCheng;
	}

	public String getSouZaiCity() {
		return souZaiCity;
	}

	public void setSouZaiCity(String souZaiCity) {
		this.souZaiCity = souZaiCity;
	}

}