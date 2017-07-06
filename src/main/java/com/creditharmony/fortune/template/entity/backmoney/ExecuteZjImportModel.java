package com.creditharmony.fortune.template.entity.backmoney;

import java.io.Serializable;
import java.util.Date;

import com.creditharmony.common.util.DateUtils;
import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.excel.annotation.ExcelField;

/**
 * 中金导入对应Bean
 * 
 * @Class Name ExecuteZjImportModel
 * @author 陈广鹏
 * @Create In 2016年1月27日
 */
public class ExecuteZjImportModel implements Serializable {

	/**
	 * long serialVersionUID
	 */
	private static final long serialVersionUID = -1278952118372494783L;
	/**
	 * 序号
	 */
	@ExcelField(title = "序号")
	private String serialNum;
	/**
	 * 交易时间
	 */
	@ExcelField(title = "交易时间")
	private Date tradeTime;
	/**
	 * 机构名称
	 */
	@ExcelField(title = "机构名称")
	private String orgName;
	/**
	 * 批次号
	 */
	@ExcelField(title = "批次号")
	private String batchNo;
	/**
	 * 明细号
	 */
	@ExcelField(title = "明细号")
	private String detailNo;
	/**
	 * 交易金额
	 */
	@ExcelField(title = "交易金额")
	private Double tradeAmount;
	/**
	 * 结算金额
	 */
	@ExcelField(title = "结算金额")
	private Double balanceAmount;
	/**
	 * 银行ID
	 */
	@ExcelField(title = "银行ID")
	private String bankId;
	/**
	 * 账户类型
	 */
	@ExcelField(title = "账户类型")
	private String acountType;
	/**
	 * 账户号
	 */
	@ExcelField(title = "账户号")
	private String acountNo;
	/**
	 * 账户名
	 */
	@ExcelField(title = "账户名")
	private String acountName;
	/**
	 * 分支行名称
	 */
	@ExcelField(title = "分支行名称")
	private String accountBranch;
	/**
	 * 分支行省份
	 */
	@ExcelField(title = "分支行省份")
	private String accountProvince;
	/**
	 * 分支行城市
	 */
	@ExcelField(title = "分支行城市")
	private String accountCity;
	/**
	 * 备注信息
	 */
	@ExcelField(title = "备注信息")
	private String remark;
	/**
	 * 手机号码
	 */
	@ExcelField(title = "手机号码")
	private String customerMobilephone;
	/**
	 * 电子邮件
	 */
	@ExcelField(title = "电子邮件")
	private String customerEmail;
	/**
	 * 证件类型
	 */
	@ExcelField(title = "证件类型")
	private String dictCustomerCertType;
	/**
	 * 证件号码
	 */
	@ExcelField(title = "证件号码")
	private String customerCertNum;
	/**
	 * 银行代付的时间
	 */
	@ExcelField(title = "银行代付的时间")
	private String strBankPayTime;
	private Date bankPayTime;
	/**
	 * 交易状态
	 */
	@ExcelField(title = "交易状态")
	private String tradeStatus;
	/**
	 * 银行响应代码
	 */
	@ExcelField(title = "银行响应代码")
	private String bankResponseCode;
	/**
	 * 银行响应消息
	 */
	@ExcelField(title = "银行响应消息")
	private String bankResponseMsg;

	public String getSerialNum() {
		return serialNum;
	}

	public void setSerialNum(String serialNum) {
		this.serialNum = serialNum;
	}

	public Date getTradeTime() {
		return tradeTime;
	}

	public void setTradeTime(Date tradeTime) {
		this.tradeTime = tradeTime;
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

	public Double getTradeAmount() {
		return tradeAmount;
	}

	public void setTradeAmount(Double tradeAmount) {
		this.tradeAmount = tradeAmount;
	}

	public Double getBalanceAmount() {
		return balanceAmount;
	}

	public void setBalanceAmount(Double balanceAmount) {
		this.balanceAmount = balanceAmount;
	}

	public String getBankId() {
		return bankId;
	}

	public void setBankId(String bankId) {
		this.bankId = bankId;
	}

	public String getAcountType() {
		return acountType;
	}

	public void setAcountType(String acountType) {
		this.acountType = acountType;
	}

	public String getAcountNo() {
		return acountNo;
	}

	public void setAcountNo(String acountNo) {
		this.acountNo = acountNo;
	}

	public String getAcountName() {
		return acountName;
	}

	public void setAcountName(String acountName) {
		this.acountName = acountName;
	}

	public String getAccountBranch() {
		return accountBranch;
	}

	public void setAccountBranch(String accountBranch) {
		this.accountBranch = accountBranch;
	}

	public String getAccountProvince() {
		return accountProvince;
	}

	public void setAccountProvince(String accountProvince) {
		this.accountProvince = accountProvince;
	}

	public String getAccountCity() {
		return accountCity;
	}

	public void setAccountCity(String accountCity) {
		this.accountCity = accountCity;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCustomerMobilephone() {
		return customerMobilephone;
	}

	public void setCustomerMobilephone(String customerMobilephone) {
		this.customerMobilephone = customerMobilephone;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public String getDictCustomerCertType() {
		return dictCustomerCertType;
	}

	public void setDictCustomerCertType(String dictCustomerCertType) {
		this.dictCustomerCertType = dictCustomerCertType;
	}

	public String getCustomerCertNum() {
		return customerCertNum;
	}

	public void setCustomerCertNum(String customerCertNum) {
		this.customerCertNum = customerCertNum;
	}

	public String getStrBankPayTime() {
		return strBankPayTime;
	}

	public void setStrBankPayTime(String strBankPayTime) {
		this.strBankPayTime = strBankPayTime;
	}

	public Date getBankPayTime() {
		if (StringUtils.isNotEmpty(strBankPayTime)) {
			bankPayTime = DateUtils.parseDate(strBankPayTime);
		}
		return bankPayTime;
	}

	public void setBankPayTime(Date bankPayTime) {
		this.bankPayTime = bankPayTime;
	}

	public String getTradeStatus() {
		return tradeStatus;
	}

	public void setTradeStatus(String tradeStatus) {
		this.tradeStatus = tradeStatus;
	}

	public String getBankResponseCode() {
		return bankResponseCode;
	}

	public void setBankResponseCode(String bankResponseCode) {
		this.bankResponseCode = bankResponseCode;
	}

	public String getBankResponseMsg() {
		return bankResponseMsg;
	}

	public void setBankResponseMsg(String bankResponseMsg) {
		this.bankResponseMsg = bankResponseMsg;
	}

}
