package com.creditharmony.fortune.template.entity.backmoney;

import java.io.Serializable;

import com.creditharmony.common.util.ObjectHelper;
import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.common.type.CertificateType;
import com.creditharmony.core.excel.annotation.ExcelField;
import com.creditharmony.core.fortune.type.BackType;
import com.creditharmony.core.fortune.type.OpenBank;

/**
 * 通联导出
 * @Class Name ExecuteTlExportModel
 * @author 陈广鹏
 * @Create In 2016年2月17日
 */
public class ExecuteTlExportModel implements Serializable {

	/**
	 * long serialVersionUID 
	 */
	private static final long serialVersionUID = 6034892538509892362L;
	/**
	 * 序号*
	 */
	@ExcelField(title = "序号*")
	private String serialNum;
	/**
	 * 用户编号
	 */
	@ExcelField(title = "用户编号")
	private String customerCode;
	/**
	 * 银行代码*
	 */
	@ExcelField(title = "银行代码*")
	private String accountBank;
	/**
	 * 账号类型
	 */
	@ExcelField(title = "账号类型")
	private String accountType;
	/**
	 * 账号*
	 */
	@ExcelField(title = "账号*")
	private String accountNo;
	/**
	 * 户名*
	 */
	@ExcelField(title = "户名*")
	private String accountName;
	/**
	 * 省
	 */
	@ExcelField(title = "省")
	private String accountAddrprovince;
	/**
	 * 市
	 */
	@ExcelField(title = "市")
	private String accountAddrcity;
	/**
	 * 开户行名称
	 */
	@ExcelField(title = "开户行名称")
	private String accountBankName;
	/**
	 * 账户类型
	 */
	@ExcelField(title = "账户类型")
	private String accType;
	/**
	 * 实际回款金额
	 */
	@ExcelField(title = "金额*")
	private Double backActualbackMoney;
	/**
	 * 回款成功金额
	 */
	private Double suceessMoney;
	/**
	 * 货币类型
	 */
	@ExcelField(title = "货币类型")
	private String currencyType;
	/**
	 * 协议号
	 */
	@ExcelField(title = "协议号")
	private String agreementNo;
	/**
	 * 协议用户编号
	 */
	@ExcelField(title = "协议用户编号")
	private String agreeCustomerCode;
	/**
	 * 证件类型
	 */
	@ExcelField(title = "开户证件类型")
	private String dictCustomerCertType;
	/**
	 * 证件号
	 */
	@ExcelField(title = "证件号")
	private String customerCertNum;
	/**
	 * 手机号/小灵通
	 */
	@ExcelField(title = "手机号/小灵通")
	private String customerMobilephone;
	/**
	 * 自定义用户号
	 */
	@ExcelField(title = "自定义用户号")
	private String customerNo;
	/**
	 * 回款类型
	 */
	private String backMoneyType;
	/**
	 * 出借编号
	 */
	private String lendCode;
	/**
	 * 回款ID
	 */
	private String backmId;
	/**
	 * 备注
	 */
	@ExcelField(title = "备注")
	private String tlRemark;
	/**
	 * 反馈码
	 */
	@ExcelField(title = "反馈码")
	private String responseCode;
	/**
	 * 原因
	 */
	@ExcelField(title = "原因")
	private String reason;

	public String getSerialNum() {
		return serialNum;
	}

	public void setSerialNum(String serialNum) {
		this.serialNum = serialNum;
	}

	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	public String getAccountType() {
		if (null == accountType) {
			accountType = "个人";
		}
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

	public String getAccountBank() {
		return accountBank;
	}

	public void setAccountBank(String accountBank) {
		this.accountBank = accountBank;
	}

	public String getAccountBankName() {
		//OpenBank.initOpenBank();
		if (null != accountBank) {
			return OpenBank.openBankMap.get(accountBank);
		}
		return accountBankName;
	}

	public void setAccountBankName(String accountBankName) {
		this.accountBankName = accountBankName;
	}

	public String getAccType() {
		if (null == accType) {
			accType = "0";
		}
		return accType;
	}

	public void setAccType(String accType) {
		this.accType = accType;
	}

	public String getBackActualbackMoney() {
		if (!ObjectHelper.isEmpty(suceessMoney)) {
			backActualbackMoney = backActualbackMoney - suceessMoney;
		}
		Double money = backActualbackMoney*100;
		return StringUtils.doNumFormat(money, "#0");
	}

	public void setBackActualbackMoney(Double backActualbackMoney) {
		this.backActualbackMoney = backActualbackMoney;
	}

	public Double getSuceessMoney() {
		return suceessMoney;
	}

	public void setSuceessMoney(Double suceessMoney) {
		this.suceessMoney = suceessMoney;
	}

	public String getCurrencyType() {
		if (null == currencyType) {
			currencyType = "CNY";
		}
		return currencyType;
	}

	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}

	public String getAgreementNo() {
		return agreementNo;
	}

	public void setAgreementNo(String agreementNo) {
		this.agreementNo = agreementNo;
	}

	public String getAgreeCustomerCode() {
		return agreeCustomerCode;
	}

	public void setAgreeCustomerCode(String agreeCustomerCode) {
		this.agreeCustomerCode = agreeCustomerCode;
	}

	public String getDictCustomerCertType() {
		//CertificateType.initCertificateType();
		return CertificateType.parseByCode(dictCustomerCertType).getName();
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

	public String getCustomerMobilephone() {
		return customerMobilephone;
	}

	public void setCustomerMobilephone(String customerMobilephone) {
		this.customerMobilephone = customerMobilephone;
	}

	public String getCustomerNo() {
		return customerNo;
	}

	public void setCustomerNo(String customerNo) {
		this.customerNo = customerNo;
	}

	public String getBackMoneyType() {
		return backMoneyType;
	}

	public void setBackMoneyType(String backMoneyType) {
		this.backMoneyType = backMoneyType;
	}

	public String getLendCode() {
		return lendCode;
	}

	public void setLendCode(String lendCode) {
		this.lendCode = lendCode;
	}

	public String getBackmId() {
		return backmId;
	}

	public void setBackmId(String backmId) {
		this.backmId = backmId;
	}

	public String getTlRemark() {
		if (ObjectHelper.isEmpty(tlRemark)) {
			tlRemark = "";
			//BackType.initBackType();
			if (null != backMoneyType) {
				tlRemark = BackType.backTypeMap.get(backMoneyType);
			}
			tlRemark = tlRemark + "_";
			if (null != lendCode) {
				tlRemark = tlRemark + lendCode;
			}
			tlRemark = tlRemark + "_" + backmId;
		}
		return tlRemark;
	}

	public void setTlRemark(String tlRemark) {
		this.tlRemark = tlRemark;
	}

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

}
