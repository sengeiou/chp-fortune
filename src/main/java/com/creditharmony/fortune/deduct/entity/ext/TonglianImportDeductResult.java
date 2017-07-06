package com.creditharmony.fortune.deduct.entity.ext;

import java.io.Serializable;
import java.util.Date;

import com.creditharmony.core.excel.annotation.ExcelField;

/**
 * 线下划扣通联结果导入
 * @Class Name ImportDeductResult
 * @author 韩龙
 * @Create In 2015年12月19日
 */
public class TonglianImportDeductResult implements Serializable {
	
	/**
	 * long serialVersionUID 
	 */
	private static final long serialVersionUID = 977187771938022014L;
	
	/**
	 * 文件序号
	 */
	@ExcelField(title="文件序号",type=2)
	public String index;
	
	/**
	 * 	文件名
	 */
	@ExcelField(title="文件名",type=2)
	public String fileName;
	
	/**
	 * 	记录序号
	 */
	@ExcelField(title="记录序号",type=2)
	public String recordNumber ;
	
	/**
	 * 	交易类型
	 */
	@ExcelField(title="交易类型",type=2)
	public String transactionType;
	
	/**
	 * 	处理状态
	 */
	@ExcelField(title="处理状态",type=2)
	public String tradingStatus;
	
	/**
	 * 	商户
	 */
	@ExcelField(title="商户",type=2)
	public String merchants;
	
	/**
	 * 	业务名称
	 */
	@ExcelField(title="业务名称",type=2)
	public String businessName;
	
	/**
	 * 	开户银行
	 */
	@ExcelField(title="开户银行",type=2)
	public String accountBank;
	
	/**
	 * 	账号
	 */
	@ExcelField(title="账号",type=2)
	public String accountNo;
	
	/**
	 * 	姓名
	 */
	@ExcelField(title="姓名",type=2)
	public String userName;
	
	/**
	 * 	交易金额
	 */
	@ExcelField(title="交易金额",type=2)
	public String deductMoney;
	
	/**
	 * 	手续费
	 */
	@ExcelField(title="手续费",type=2)
	public String  poundage;
	
	/**
	 * 	开户省
	 */
	@ExcelField(title="开户省",type=2)
	public String accountAddrProvince;
	
	/**
	 * 	开户市
	 */
	@ExcelField(title="开户市",type=2)
	public String accountAddrCity;
	
	/**
	 * 	支付行号
	 */
	@ExcelField(title="支付行号",type=2)
	public String accountBranch;
	
	/**
	 * 	终端号
	 */
	@ExcelField(title="终端号",type=2)
	public String terminalId;;
	
	/**
	 * 	提交时间
	 */
	@ExcelField(title="提交时间",type=2)
	public Date submitTime;
	
	/**
	 * 	商户审核时间
	 */
	@ExcelField(title="商户审核时间",type=2)
	public String merchantAuditTime;
	
	/**
	 * 	完成时间
	 */
	@ExcelField(title="完成时间",type=2)
	public String  finishTime;
	
	/**
	 * 	原因	
	 */
	@ExcelField(title="原因",type=2)
	public String reason;
	
	/**
	 * 保留字段
	 */
	@ExcelField(title="保留字段",type=2)
	public String reserved;
	
	/**
	 * 	备注
	 */
	@ExcelField(title="备注",type=2)
	public String remark;

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getRecordNumber() {
		return recordNumber;
	}

	public void setRecordNumber(String recordNumber) {
		this.recordNumber = recordNumber;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public String getTradingStatus() {
		return tradingStatus;
	}

	public void setTradingStatus(String tradingStatus) {
		this.tradingStatus = tradingStatus;
	}

	public String getMerchants() {
		return merchants;
	}

	public void setMerchants(String merchants) {
		this.merchants = merchants;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getDeductMoney() {
		return deductMoney;
	}

	public void setDeductMoney(String deductMoney) {
		this.deductMoney = deductMoney;
	}

	public String getPoundage() {
		return poundage;
	}

	public void setPoundage(String poundage) {
		this.poundage = poundage;
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

	public String getTerminalId() {
		return terminalId;
	}

	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}

	public Date getSubmitTime() {
		return submitTime;
	}

	public void setSubmitTime(Date submitTime) {
		this.submitTime = submitTime;
	}

	public String getMerchantAuditTime() {
		return merchantAuditTime;
	}

	public void setMerchantAuditTime(String merchantAuditTime) {
		this.merchantAuditTime = merchantAuditTime;
	}

	public String getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(String finishTime) {
		this.finishTime = finishTime;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getReserved() {
		return reserved;
	}

	public void setReserved(String reserved) {
		this.reserved = reserved;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}

