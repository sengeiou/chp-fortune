package com.creditharmony.fortune.deduct.entity.ext;

import java.io.Serializable;
import java.util.Date;

import com.creditharmony.core.excel.annotation.ExcelField;

/**
 * 线下划扣好易联结果导入
 * @Class Name ImportDeductResult
 * @author 韩龙
 * @Create In 2015年12月19日
 */
public class HylImportDeductResult implements Serializable {
	/**
	 * long serialVersionUID 
	 */
	private static final long serialVersionUID = 977187771938022014L;
	
	/**
	 * 商户批次号	
	 */
	@ExcelField(title = "商户批次号",type = 2)
	private String merchantBatchNumber;
	
	/**
	 * 文件名
	 */	
	@ExcelField(title = "文件名",type = 2)
	private String fileName;
	
	/**
	 * 提交时间
	 */
	@ExcelField(title = "提交时间",type = 2)
	private Date submissionTime;
	
	/**
	 * 清算日期	
	 */
	@ExcelField(title = "清算日期",type = 2)
	private String financeTime;
	
	/**
	 * 记录序号
	 */
	@ExcelField(title = "记录序号",type = 2)
	private String recordNumber;
	
	/**
	 * 开户省	
	 */
	@ExcelField(title = "开户省",type = 2)
	private String accountAddrProvince;
	
	/**
	 * 开户市	
	 */
	@ExcelField(title = "开户市",type = 2)
	private String accountAddrCity;
	
	/**
	 * 行别
	 */
	@ExcelField(title = "行别",type = 2)
	private String accountBranch;
	
	/**
	 * 银行账号	
	 */
	@ExcelField(title = "银行账号",type = 2)
	private String accountNo;
	
	/**
	 * 银行户名
	 */
	@ExcelField(title = "银行户名",type = 2)
	private String accountName;
	
	/**
	 * 业务商户
	 */
	@ExcelField(title = "业务商户",type = 2)
	private String businessMerchant;
	
	/**
	 * 业务名称	
	 */
	@ExcelField(title = "业务名称",type = 2)
	private String businessName;
	
	/**
	 * 清分账户
	 */
	@ExcelField(title = "清分账户",type = 2)
	private String clearingAccount;
	
	/**
	 * 交易金额(元)	
	 */
	@ExcelField(title = "交易金额(元)",type = 2)
	private String deductMoney;
	
	/**
	 * 处理状态
	 */
	@ExcelField(title = "处理状态",type = 2)
	private String tradingStatus;
	
	/**
	 * 交易结果
	 */
	@ExcelField(title = "交易结果",type = 2)
	private String returnResult;
	
	/**
	 * 原因	
	 */
	@ExcelField(title = "原因",type = 2)
	private String reason;
	
	/**
	 * 备注	
	 */
	@ExcelField(title = "备注",type = 2)
	private String remark;
	
	/**
	 * 备注1
	 */
	@ExcelField(title = "备注1",type = 2)
	private String remark1;
	
	/**
	 * 备注2
	 */
	@ExcelField(title = "备注2",type = 2)
	private String remark2;

	public String getMerchantBatchNumber() {
		return merchantBatchNumber;
	}

	public void setMerchantBatchNumber(String merchantBatchNumber) {
		this.merchantBatchNumber = merchantBatchNumber;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Date getSubmissionTime() {
		return submissionTime;
	}

	public void setSubmissionTime(Date submissionTime) {
		this.submissionTime = submissionTime;
	}

	public String getFinanceTime() {
		return financeTime;
	}

	public void setFinanceTime(String financeTime) {
		this.financeTime = financeTime;
	}

	public String getRecordNumber() {
		return recordNumber;
	}

	public void setRecordNumber(String recordNumber) {
		this.recordNumber = recordNumber;
	}

	public String getAccountAddrProvince() {
		return accountAddrProvince;
	}

	public void setAccountAddrProvince(String accountAddrProvince) {
		this.accountAddrProvince = accountAddrProvince;
	}

	public String getAccountAddrCity() {
		return accountAddrCity;
	}

	public void setAccountAddrCity(String accountAddrCity) {
		this.accountAddrCity = accountAddrCity;
	}

	public String getAccountBranch() {
		return accountBranch;
	}

	public void setAccountBranch(String accountBranch) {
		this.accountBranch = accountBranch;
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

	public String getBusinessMerchant() {
		return businessMerchant;
	}

	public void setBusinessMerchant(String businessMerchant) {
		this.businessMerchant = businessMerchant;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public String getClearingAccount() {
		return clearingAccount;
	}

	public void setClearingAccount(String clearingAccount) {
		this.clearingAccount = clearingAccount;
	}

	public String getDeductMoney() {
		return deductMoney;
	}

	public void setDeductMoney(String deductMoney) {
		this.deductMoney = deductMoney;
	}

	public String getTradingStatus() {
		return tradingStatus;
	}

	public void setTradingStatus(String tradingStatus) {
		this.tradingStatus = tradingStatus;
	}

	public String getReturnResult() {
		return returnResult;
	}

	public void setReturnResult(String returnResult) {
		this.returnResult = returnResult;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRemark1() {
		return remark1;
	}

	public void setRemark1(String remark1) {
		this.remark1 = remark1;
	}

	public String getRemark2() {
		return remark2;
	}

	public void setRemark2(String remark2) {
		this.remark2 = remark2;
	}
	
}

