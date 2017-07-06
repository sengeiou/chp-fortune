package com.creditharmony.fortune.template.entity.backInterest;

import java.io.Serializable;

import com.creditharmony.core.excel.annotation.ExcelField;

/**
 * 中金导入(.txt)模板
 * @Class Name ChinaFinanceUpload 
 * @author 李志伟
 * @Create In 2016年2月3日
 */
public class ChinaFinanceUpload implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/*
	 * 序号
	 */
	@ExcelField(title = "序号", type=2)
	private String no;
	
	/*
	 * 交易时间
	 */
	@ExcelField(title = "交易时间", type=2)
	private String tradingTime;
	
	/*
	 * 机构名称
	 */
	@ExcelField(title = "机构名称", type=2)
	private String orgName;
	
	/*
	 * 批次号
	 */
	@ExcelField(title = "批次号", type=2)
	private String batchNo;
	
	/*
	 * 明细号
	 */
	@ExcelField(title = "明细号", type=2)
	private String detailNo;
	
	/*
	 * 交易金额
	 */
	@ExcelField(title = "交易金额", type=2)
	private String backMoney;
	
	/*
	 * 结算金额
	 */
	@ExcelField(title = "结算金额", type=2)
	private String backRealMoney;
	
	/*
	 * 银行ID(银行代码-银行中文)
	 */
	@ExcelField(title = "银行ID", type=2)
	private String bankId;
	
	/*
	 * 账户类型
	 */
	@ExcelField(title = "账户类型", type=2)
	private String accountType;
	
	/*
	 * 账户号
	 */
	@ExcelField(title = "账户号", type=2)
	private String accountNo;
	
	/*
	 * 账户名
	 */
	@ExcelField(title = "账户名", type=2)
	private String accountName;
	
	/*
	 * 分支行名称(银行代码)
	 */
	@ExcelField(title = "分支行名称", type=2)
	private String accountBranch;
	
	/*
	 * 分支行省份
	 */
	@ExcelField(title = "分支行省份", type=2)
	private String province;
	
	/*
	 * 分支行城市
	 */
	@ExcelField(title = "分支行城市", type=2)
	private String city;
	
	/*
	 * 备注(唯一识别码[账单日_回息_出借编号_唯一识别码])
	 */
	@ExcelField(title = "备注", type=2)
	private String backiId;
	
	/*
	 * 手机号码
	 */
	@ExcelField(title = "手机号码", type=2)
	private String telphoneNo;
	
	/*
	 * 电子邮件
	 */
	@ExcelField(title = "电子邮件", type=2)
	private String email;
	
	/*
	 * 证件类型
	 */
	@ExcelField(title = "证件类型", type=2)
	private String documentType;
	
	/*
	 * 证件号码
	 */
	@ExcelField(title = "证件号码", type=2)
	private String documentNo;
	
	/*
	 * 银行代付的时间
	 */
	@ExcelField(title = "银行代付的时间", type=2)
	private String bankPayTime;
	
	/*
	 * 交易状态
	 */
	@ExcelField(title = "交易状态", type=2)
	private String tradingStatus;
	
	/*
	 * 银行响应代码
	 */
	@ExcelField(title = "银行响应代码", type=2)
	private String bankCode;
	
	/*
	 * 银行响应消息
	 */
	@ExcelField(title = "银行响应消息", type=2)
	private String bankMessage;
	
	/*
	 * 账单日
	 */
	private String applyBillday;
	
	/*
	 * 出借编号 
	 */
	private String lendCode;
	

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getTradingTime() {
		return tradingTime;
	}

	public void setTradingTime(String tradingTime) {
		this.tradingTime = tradingTime;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public String getDetailNo() {
		return detailNo;
	}

	public void setDetailNo(String detailNo) {
		this.detailNo = detailNo;
	}
	
	public String getBackMoney() {
		return backMoney;
	}

	public void setBackMoney(String backMoney) {
		this.backMoney = backMoney;
	}

	public String getBackRealMoney() {
		return backRealMoney;
	}

	public void setBackRealMoney(String backRealMoney) {
		this.backRealMoney = backRealMoney;
	}

	public String getBankId() {
		return bankId;
	}

	public void setBankId(String bankId) {
		this.bankId = bankId;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
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

	public String getAccountBranch() {
		return accountBranch;
	}

	public void setAccountBranch(String accountBranch) {
		this.accountBranch = accountBranch;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getTelphoneNo() {
		return telphoneNo;
	}

	public void setTelphoneNo(String telphoneNo) {
		this.telphoneNo = telphoneNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDocumentType() {
		return documentType;
	}

	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}

	public String getDocumentNo() {
		return documentNo;
	}

	public void setDocumentNo(String documentNo) {
		this.documentNo = documentNo;
	}

	public String getBankPayTime() {
		return bankPayTime;
	}

	public void setBankPayTime(String bankPayTime) {
		this.bankPayTime = bankPayTime;
	}

	public String getTradingStatus() {
		return tradingStatus;
	}

	public void setTradingStatus(String tradingStatus) {
		this.tradingStatus = tradingStatus;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getBankMessage() {
		return bankMessage;
	}

	public void setBankMessage(String bankMessage) {
		this.bankMessage = bankMessage;
	}

	public String getApplyBillday() {
		return applyBillday;
	}

	public void setApplyBillday(String applyBillday) {
		this.applyBillday = applyBillday;
	}

	public String getLendCode() {
		return lendCode;
	}

	public void setLendCode(String lendCode) {
		this.lendCode = lendCode;
	}

	public String getBackiId() {
		return backiId;
	}
	//备注信息(账单日_回息_出借编号_唯一识别码)
	public void setBackiId(String backiId) {
		this.backiId = backiId;
	}
}