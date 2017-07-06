
package com.creditharmony.fortune.template.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.creditharmony.core.excel.annotation.ExcelField;

/**
 * 债权发送列表导出
 * @Class Name creditorSendExportModel
 * @author 胡体勇
 * @Create In 2015年12月25日
 */
public class CreditorSendExportModel implements Serializable {

	/**
	 * long serialVersionUID 
	 */
	private static final long serialVersionUID = 1L;
	
	@ExcelField(title = "出借编号" , align = 2)
	private String lendCode;// 出借编号
	@ExcelField(title = "客户姓名" , align = 2)
	private String customerName; // 客户姓名
	@ExcelField(title = "客户编号" , align = 2)
	private String customerCode;// 客户编号
	@ExcelField(title = "所在城市" , align = 2)
	private String addrCity;// 所在城市
	@ExcelField(title = "营业部" , align = 2)
	private String org;// 营业部
	@ExcelField(title = "本次出借日期" , align = 2)
	private Date applyLendDay;// 本次出借日期
	@ExcelField(title = "初始出借日期" , align = 2)
	private Date startApplyLendDay;// 初始出借日期
	@ExcelField(title = "初始出借金额" , align = 2)
	private BigDecimal startApplyLendMoney;// 初始出借金额
	@ExcelField(title = "出借产品" , align = 2)
	private String productName;// 产品类型
	@ExcelField(title = "付款方式" , align = 2, dictType="tz_pay_type")
	private String applyPay;// 付款方式
	@ExcelField(title = "出借完结状态" , align = 2, dictType="tz_finish_state")
	private String applyEndStatus;// 出借完结状态
	@ExcelField(title = "结算状态" , align = 2, dictType="tz_pay_status")
	private String matchingPayStatus;// 结算状态
	@ExcelField(title = "债权状态" , align = 2, dictType="tz_bill_state")
	private String matchingFirstdayFlag;// 债权状态
	@ExcelField(title = "已推荐金额" , align = 2)
	private BigDecimal matchingMatchMoney;// 已推荐金额
	@ExcelField(title = "账单发送状态" , align = 2, dictType="tz_creditsend_state")
	private String matchingFileSendStatus;// 账单发送状态
	@ExcelField(title = "账单收取方式" , align = 2, dictType="tz_taken_mode")
	private String loanBillRecv;// 账单收取方式
	@ExcelField(title = "制作状态" , align = 2 , dictType="tz_filecp_state")
	private String matchingFileStatus;// 制作状态
	@ExcelField(title = "邮箱地址" , align = 2)
	private String customerEmail;// 邮箱地址
	public String getLendCode() {
		return lendCode;
	}
	public void setLendCode(String lendCode) {
		this.lendCode = lendCode;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerCode() {
		return customerCode;
	}
	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}
	public String getAddrCity() {
		return addrCity;
	}
	public void setAddrCity(String addrCity) {
		this.addrCity = addrCity;
	}
	public String getOrg() {
		return org;
	}
	public void setOrg(String org) {
		this.org = org;
	}
	public Date getApplyLendDay() {
		return applyLendDay;
	}
	public void setApplyLendDay(Date applyLendDay) {
		this.applyLendDay = applyLendDay;
	}
	public Date getStartApplyLendDay() {
		return startApplyLendDay;
	}
	public void setStartApplyLendDay(Date startApplyLendDay) {
		this.startApplyLendDay = startApplyLendDay;
	}
	public BigDecimal getStartApplyLendMoney() {
		return startApplyLendMoney;
	}
	public void setStartApplyLendMoney(BigDecimal startApplyLendMoney) {
		this.startApplyLendMoney = startApplyLendMoney;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getApplyPay() {
		return applyPay;
	}
	public void setApplyPay(String applyPay) {
		this.applyPay = applyPay;
	}
	public String getApplyEndStatus() {
		return applyEndStatus;
	}
	public void setApplyEndStatus(String applyEndStatus) {
		this.applyEndStatus = applyEndStatus;
	}
	public String getMatchingPayStatus() {
		return matchingPayStatus;
	}
	public void setMatchingPayStatus(String matchingPayStatus) {
		this.matchingPayStatus = matchingPayStatus;
	}
	public String getMatchingFirstdayFlag() {
		return matchingFirstdayFlag;
	}
	public void setMatchingFirstdayFlag(String matchingFirstdayFlag) {
		this.matchingFirstdayFlag = matchingFirstdayFlag;
	}
	public BigDecimal getMatchingMatchMoney() {
		return matchingMatchMoney;
	}
	public void setMatchingMatchMoney(BigDecimal matchingMatchMoney) {
		this.matchingMatchMoney = matchingMatchMoney;
	}
	public String getMatchingFileSendStatus() {
		return matchingFileSendStatus;
	}
	public void setMatchingFileSendStatus(String matchingFileSendStatus) {
		this.matchingFileSendStatus = matchingFileSendStatus;
	}
	public String getLoanBillRecv() {
		return loanBillRecv;
	}
	public void setLoanBillRecv(String loanBillRecv) {
		this.loanBillRecv = loanBillRecv;
	}
	public String getMatchingFileStatus() {
		return matchingFileStatus;
	}
	public void setMatchingFileStatus(String matchingFileStatus) {
		this.matchingFileStatus = matchingFileStatus;
	}
	public String getCustomerEmail() {
		return customerEmail;
	}
	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}
}
