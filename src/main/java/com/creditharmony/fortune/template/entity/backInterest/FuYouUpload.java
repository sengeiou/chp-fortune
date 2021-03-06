package com.creditharmony.fortune.template.entity.backInterest;

import java.io.Serializable;

import com.creditharmony.core.excel.annotation.ExcelField;

/**
 * 富友上传文件
 * @Class Name FuYouUpload 
 * @author 李志伟
 * @Create In 2016年1月15日
 */
public class FuYouUpload implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 交易提交时间
	 */
	@ExcelField(title = "交易提交时间")
	private String tradeTime;
	
	/**
	 * 交易流水
	 */
	@ExcelField(title = "交易流水")
	private String tradeFlow;
	
	/**
	 * 文件明细号
	 */
	@ExcelField(title = "文件明细号")
	private String fileDetailNo;
	
	/**
	 * 业务类型
	 */
	@ExcelField(title = "业务类型")
	private String businessType;
	
	/**
	 * 文件名
	 */
	@ExcelField(title = "文件名")
	private String fileName;
	
	/**
	 * 金额
	 */
	@ExcelField(title = "金额")
	private String amount;
	
	/**
	 * 开户行
	 */
	@ExcelField(title = "开户行")
	private String bank;
	
	/**
	 * 账户名称
	 */
	@ExcelField(title = "账户名称")
	private String accountName;
	
	/**
	 * 账户
	 */
	@ExcelField(title = "账户")
	private String accountNo;
	
	/**
	 * 交易状态
	 */
	@ExcelField(title = "交易状态")
	private String tradeStatus;
	
	/**
	 * 返回附言
	 */
	@ExcelField(title = "返回附言")
	private String backRemark;
	
	/**
	 * 企业流水号
	 */
	@ExcelField(title = "企业流水号")
	private String backiId;
	
	/**
	 * 备注
	 */
	@ExcelField(title = "备注")
	private String remark;
	
	/**
	 * 手机号码
	 */
	@ExcelField(title = "手机号码")
	private String cellNum;
	
	/**
	 * 是否退票
	 */
	@ExcelField(title = "是否退票")
	private String sendBack;

	public String getTradeTime() {
		return tradeTime;
	}

	public void setTradeTime(String tradeTime) {
		this.tradeTime = tradeTime;
	}

	public String getTradeFlow() {
		return tradeFlow;
	}

	public void setTradeFlow(String tradeFlow) {
		this.tradeFlow = tradeFlow;
	}

	public String getFileDetailNo() {
		return fileDetailNo;
	}

	public void setFileDetailNo(String fileDetailNo) {
		this.fileDetailNo = fileDetailNo;
	}

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
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

	public String getTradeStatus() {
		return tradeStatus;
	}

	public void setTradeStatus(String tradeStatus) {
		this.tradeStatus = tradeStatus;
	}

	public String getBackRemark() {
		return backRemark;
	}

	public void setBackRemark(String backRemark) {
		this.backRemark = backRemark;
	}

	public String getBackiId() {
		return backiId;
	}

	public void setBackiId(String backiId) {
		this.backiId = backiId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCellNum() {
		return cellNum;
	}

	public void setCellNum(String cellNum) {
		this.cellNum = cellNum;
	}

	public String getSendBack() {
		return sendBack;
	}

	public void setSendBack(String sendBack) {
		this.sendBack = sendBack;
	}
	
	
}