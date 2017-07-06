package com.creditharmony.fortune.template.entity.backInterest;

import java.io.Serializable;
import java.util.Date;

import com.creditharmony.core.excel.annotation.ExcelField;

/**
 * 通联代付模板导入(1)
 * @Class Name CommunicationsExt 
 * @author 李志伟
 * @Create In 2016年2月3日
 */
public class CommunicationsUploadFirst implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/*
	 * 文件序号
	 */
	@ExcelField(title = "文件序号")
	private String fileNo;
	
	/*
	 * 文件名
	 */
	@ExcelField(title = "文件名")
	private String fileName;
	
	/*
	 * 记录序号
	 */
	@ExcelField(title = "记录序号")
	private String recordNo;
	
	/*
	 *  交易类型
	 */
	@ExcelField(title = "交易类型")
	private String transactionType;
	
	/*
	 * 处理状态
	 */
	@ExcelField(title = "处理状态")
	private String processingState;
	
	/*
	 * 商户
	 */
	@ExcelField(title = "商户")
	private String merchant;
	
	/*
	 * 业务名称
	 */
	@ExcelField(title = "业务名称")
	private String businessName;
	
	/*
	 * 开户银行
	 */
	@ExcelField(title = "开户银行")
	private String bankName;
	
	/*
	 * 账号
	 */
	@ExcelField(title = "账号")
	private String accountNo;
	
	/*
	 * 姓名
	 */
	@ExcelField(title = "姓名")
	private String customerName;
	
	/*
	 * 交易金额
	 */
	@ExcelField(title = "交易金额")
	private String backRealMoney;
	
	/*
	 * 手续费
	 */
	@ExcelField(title = "手续费")
	private String counterFee;
	
	/*
	 * 开户省
	 */
	@ExcelField(title = "开户省")
	private String province;
	
	/*
	 * 开户市
	 */
	@ExcelField(title = "开户市")
	private String city;
	
	/*
	 * 支付行号
	 */
	@ExcelField(title = "支付行号")
	private String payNum;
	
	/*
	 * 终端号
	 */
	@ExcelField(title = "终端号")
	private String terminalNo;
	
	/*
	 * 提交时间
	 */
	@ExcelField(title = "提交时间")
	private Date commitTime; 
	
	/*
	 * 商户审核时间
	 */
	@ExcelField(title = "商户审核时间")
	private Date merchantAudit;
	
	/*
	 * 完成时间
	 */
	@ExcelField(title = "完成时间")
	private String finishTime;
	
	/*
	 * 原因
	 */
	@ExcelField(title = "原因")
	private String backReason;
	
	/*
	 * 保留字段
	 */
	@ExcelField(title = "保留字段")
	private String reserved;
	
	/*
	 * 备注(账单日_回息_出借编号_唯一标识)
	 */
	@ExcelField(title = "备注")
	private String backiId;
	
	
	public String getBackiId() {
		return backiId;
	}
	
	// 账单日_回息_出借编号_唯一标识
	public void setBackiId(String backiId) {
		if(backiId != null && !backiId.equals("")){
			this.backiId = backiId;
		}
	}
	public String getFileNo() {
		return fileNo;
	}
	public void setFileNo(String fileNo) {
		if(fileNo != null && !fileNo.equals("")){
			this.fileNo = fileNo;
		}
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		if(fileName != null && !fileName.equals("")){
			this.fileName = fileName;
		}
	}
	public String getRecordNo() {
		return recordNo;
	}
	public void setRecordNo(String recordNo) {
		if(recordNo != null && !recordNo.equals("")){
			this.recordNo = recordNo;
		}
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		if(transactionType != null && !transactionType.equals("")){
			this.transactionType = transactionType;
		}
	}
	public String getProcessingState() {
		return processingState;
	}
	public void setProcessingState(String processingState) {
		if(processingState != null && !processingState.equals("")){
			this.processingState = processingState;
		}
	}
	public String getMerchant() {
		return merchant;
	}
	public void setMerchant(String merchant) {
		if(merchant != null && !merchant.equals("")){
			this.merchant = merchant;
		}
	}
	public String getBusinessName() {
		return businessName;
	}
	public void setBusinessName(String businessName) {
		if(businessName != null && !businessName.equals("")){
			this.businessName = businessName;
		}
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		if(bankName != null && !bankName.equals("")){
			this.bankName = bankName;
		}
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		if(accountNo != null && !accountNo.equals("")){
			this.accountNo = accountNo;
		}
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {// 此处报空empty
		if(customerName != null && !customerName.equals("")){
			this.customerName = customerName;
		}
	}

	public String getBackRealMoney() {
		return backRealMoney;
	}

	public void setBackRealMoney(String backRealMoney) {
		this.backRealMoney = backRealMoney;
	}

	public String getCounterFee() {
		return counterFee;
	}

	public void setCounterFee(String counterFee) {
		if(counterFee !=null && !counterFee.equals("")){
			this.counterFee = counterFee;
		}
	}

	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		if(province != null && !province.equals("")){
			this.province = province;
		}
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		if(city != null && !city.equals("")){
			this.city = city;
		}
	}
	public String getPayNum() {
		return payNum;
	}
	public void setPayNum(String payNum) {
		if(payNum != null && !payNum.equals("")){
			this.payNum = payNum;
		}
	}
	public String getTerminalNo() {
		return terminalNo;
	}
	public void setTerminalNo(String terminalNo) {// sTRING不能转换类型DOUBLE
		if(terminalNo != null && !terminalNo.equals("")){
			this.terminalNo = terminalNo;
		}
	}
	public Date getCommitTime() {
		return commitTime;
	}
	public void setCommitTime(Date commitTime) {// String not cast double
		this.commitTime = commitTime;
	}
	public Date getMerchantAudit() {
		return merchantAudit;
	}
	public void setMerchantAudit(Date merchantAudit) {//String not cast Double
		this.merchantAudit = merchantAudit;
	}
	public String getFinishTime() {
		return finishTime;
	}
	public void setFinishTime(String finishTime) {
		this.finishTime = finishTime;
	}
	public String getBackReason() {
		return backReason;
	}
	public void setBackReason(String backReason) {
		if(backReason != null && !backReason.equals("")){
			this.backReason = backReason;
		}
	}
	public String getReserved() {
		return reserved;
	}
	public void setReserved(String reserved) {
		if(reserved != null && !reserved.equals("")){
			this.reserved = reserved;
		}
	}
	
}