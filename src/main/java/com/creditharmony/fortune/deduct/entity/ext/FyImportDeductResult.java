package com.creditharmony.fortune.deduct.entity.ext;

import java.io.Serializable;
import java.util.Date;

import com.creditharmony.core.excel.annotation.ExcelField;

/**
 * 线下划扣富有结果导入
 * @Class Name ImportDeductResult
 * @author 韩龙
 * @Create In 2015年12月19日
 */
public class FyImportDeductResult implements Serializable {
	/**
	 * long serialVersionUID 
	 */
	private static final long serialVersionUID = 977187771938022014L;
	
	/** 
	 * 交易提交时间
	 */
	@ExcelField(title = "交易提交时间" ,type=2)
	private Date transactionCommitTime;
	
	/**
	 * 交易流水
	 */
	@ExcelField(title = "交易流水" ,type=2)
	private String transactionFlow;
	
	/**
	 * 文件明细号
	 */
	@ExcelField(title = "文件明细号" ,type=2)
	private String fileDetails;
	
	/**
	 *  业务类型
	 */
	@ExcelField(title = "业务类型" ,type=2)
	private String typeOfService;
	
	/**
	 * 文件名
	 */
	@ExcelField(title = "文件名" ,type=2)
	private String fileName;
	
	/**
	 * 金额
	 */
	@ExcelField(title = "金额" ,type=2)
	private String deductMoney;
	
	/**
	 * 开户行
	 */
	@ExcelField(title = "开户行" ,type=2)
	private String accountBank;
	
	/**
	 * 账户名称
	 */
	@ExcelField(title = "账户名称" ,type=2)
	private String accountName;
	
	/**
	 * 账户
	 */
	@ExcelField(title = "账户" ,type=2)
	private String accountNo;
	
	/**
	 * 交易状态
	 */
	@ExcelField(title = "交易状态" ,type=2)
	private String tradingStatus;
	
	/**
	 * 返回附言
	 */
	@ExcelField(title = "返回附言" ,type=2)
	private String returnPostscript;
	
	/**
	 * 企业流水号
	 */
	@ExcelField(title = "企业流水号" ,type=2)
	private String numberEnterprises;
	
	/**
	 * 备注
	 */
	@ExcelField(title = "备注" ,type=2)
	private String remark;
	
	/**
	 * 手机号码
	 */
	@ExcelField(title = "手机号码" ,type=2)
	private String phone;
	
	/**
	 * 请求流水
	 */
	@ExcelField(title = "请求流水" ,type=2)
	private String requestFlow;
	
	public Date getTransactionCommitTime() {
		return transactionCommitTime;
	}
	public void setTransactionCommitTime(Date transactionCommitTime) {
		this.transactionCommitTime = transactionCommitTime;
	}
	public String getTransactionFlow() {
		return transactionFlow;
	}
	public void setTransactionFlow(String transactionFlow) {
		this.transactionFlow = transactionFlow;
	}
	public String getFileDetails() {
		return fileDetails;
	}
	public void setFileDetails(String fileDetails) {
		this.fileDetails = fileDetails;
	}
	public String getTypeOfService() {
		return typeOfService;
	}
	public void setTypeOfService(String typeOfService) {
		this.typeOfService = typeOfService;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getDeductMoney() {
		return deductMoney;
	}
	public void setDeductMoney(String deductMoney) {
		this.deductMoney = deductMoney;
	}
	public String getAccountBank() {
		return accountBank;
	}
	public void setAccountBank(String accountBank) {
		this.accountBank = accountBank;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public String getTradingStatus() {
		return tradingStatus;
	}
	public void setTradingStatus(String tradingStatus) {
		this.tradingStatus = tradingStatus;
	}
	public String getReturnPostscript() {
		return returnPostscript;
	}
	public void setReturnPostscript(String returnPostscript) {
		this.returnPostscript = returnPostscript;
	}
	public String getNumberEnterprises() {
		return numberEnterprises;
	}
	public void setNumberEnterprises(String numberEnterprises) {
		this.numberEnterprises = numberEnterprises;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getRequestFlow() {
		return requestFlow;
	}
	public void setRequestFlow(String requestFlow) {
		this.requestFlow = requestFlow;
	}

}
