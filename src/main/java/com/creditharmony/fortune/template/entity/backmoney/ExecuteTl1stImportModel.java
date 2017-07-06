package com.creditharmony.fortune.template.entity.backmoney;

import java.io.Serializable;
import java.util.Date;

import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.excel.annotation.ExcelField;

/**
 * 通联导入模板1：交易明细
 * 
 * @Class Name ExecuteTl1stImportModel
 * @author 陈广鹏
 * @Create In 2016年2月3日
 */
public class ExecuteTl1stImportModel implements Serializable {

	/**
	 * long serialVersionUID
	 */
	private static final long serialVersionUID = 8627359937632781806L;
	/**
	 * 文件序号
	 */
	@ExcelField(title = "文件序号")
	private String fileNum;
	/**
	 * 文件名
	 */
	@ExcelField(title = "文件名")
	private String fileName;
	/**
	 * 记录序号
	 */
	@ExcelField(title = "记录序号")
	private String recordNum;
	/**
	 * 交易类型
	 */
	@ExcelField(title = "交易类型")
	private String tradeType;
	/**
	 * 处理状态
	 */
	@ExcelField(title = "处理状态")
	private String dealStatus;
	/**
	 * 商户
	 */
	@ExcelField(title = "商户")
	private String market;
	/**
	 * 业务名称
	 */
	@ExcelField(title = "业务名称")
	private String tradeName;
	/**
	 * 开户银行
	 */
	@ExcelField(title = "开户银行")
	private String accountBank;
	/**
	 * 账号
	 */
	@ExcelField(title = "账号")
	private String accountNo;
	/**
	 * 开户姓名
	 */
	@ExcelField(title = "姓名")
	private String accountName;
	/**
	 * 交易金额
	 */
	@ExcelField(title = "交易金额")
	private String strMoney;
	private Double money;
	/**
	 * 手续费
	 */
	@ExcelField(title = "手续费")
	private Double fee;
	/**
	 * 开户省
	 */
	@ExcelField(title = "开户省")
	private String accountAddrprovince;
	/**
	 * 城市
	 */
	@ExcelField(title = "开户市")
	private String accountAddrcity;
	/**
	 * 支付行号
	 */
	@ExcelField(title = "支付行号")
	private String bankCode;
	/**
	 * 终端号
	 */
	@ExcelField(title = "终端号")
	private String terminalNum;
	/**
	 * 提交时间
	 */
	@ExcelField(title = "提交时间")
	private Date submitDay;
	/**
	 * 商户审核时间
	 */
	@ExcelField(title = "商户审核时间")
	private Date checkDay;
	/**
	 * 完成时间
	 */
	@ExcelField(title = "完成时间")
	private Date finishDay;
	/**
	 * 原因
	 */
	@ExcelField(title = "原因")
	private String reason;
	/**
	 * 保留字段
	 */
	@ExcelField(title = "保留字段")
	private String savedField;
	/**
	 * 回款ID
	 */
	private String backmId;
	/**
	 * 回款类型
	 */
	private String backMoneyType;
	/**
	 * 备注
	 */
	@ExcelField(title = "备注")
	private String remark;
	/**
	 * 出借编号
	 */
	private String lendCode;
	/**
	 * 一笔出借的最终回款结果，如果有拆分，全部拆分的回款成功，则该出借回款成功
	 */
	private String finalResult;

	public String getFileNum() {
		return fileNum;
	}

	public void setFileNum(String fileNum) {
		this.fileNum = fileNum;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getRecordNum() {
		return recordNum;
	}

	public void setRecordNum(String recordNum) {
		this.recordNum = recordNum;
	}

	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

	public String getDealStatus() {
		return dealStatus;
	}

	public void setDealStatus(String dealStatus) {
		this.dealStatus = dealStatus;
	}

	public String getMarket() {
		return market;
	}

	public void setMarket(String market) {
		this.market = market;
	}

	public String getTradeName() {
		return tradeName;
	}

	public void setTradeName(String tradeName) {
		this.tradeName = tradeName;
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

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getStrMoney() {
		return strMoney;
	}

	public void setStrMoney(String strMoney) {
		this.strMoney = strMoney;
	}

	public Double getMoney() {
		if (StringUtils.isNotBlank(strMoney)) {
			strMoney = strMoney.replace(",", "");
			money = Double.valueOf(strMoney);
		}
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public Double getFee() {
		return fee;
	}

	public void setFee(Double fee) {
		this.fee = fee;
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

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getTerminalNum() {
		return terminalNum;
	}

	public void setTerminalNum(String terminalNum) {
		this.terminalNum = terminalNum;
	}

	public Date getSubmitDay() {
		return submitDay;
	}

	public void setSubmitDay(Date submitDay) {
		this.submitDay = submitDay;
	}

	public Date getCheckDay() {
		return checkDay;
	}

	public void setCheckDay(Date checkDay) {
		this.checkDay = checkDay;
	}

	public Date getFinishDay() {
		return finishDay;
	}

	public void setFinishDay(Date finishDay) {
		this.finishDay = finishDay;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getSavedField() {
		return savedField;
	}

	public void setSavedField(String savedField) {
		this.savedField = savedField;
	}

	public String getBackmId() {
		return backmId;
	}

	public void setBackmId(String backmId) {
		this.backmId = backmId;
	}

	public String getBackMoneyType() {
		return backMoneyType;
	}

	public void setBackMoneyType(String backMoneyType) {
		this.backMoneyType = backMoneyType;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getLendCode() {
		return lendCode;
	}

	public void setLendCode(String lendCode) {
		this.lendCode = lendCode;
	}

	public String getFinalResult() {
		return finalResult;
	}

	public void setFinalResult(String finalResult) {
		this.finalResult = finalResult;
	}

}
