package com.creditharmony.fortune.template.entity;

import java.io.Serializable;

import com.creditharmony.core.dict.util.DictUtils;
import com.creditharmony.core.excel.annotation.ExcelField;
import com.creditharmony.core.fortune.type.OpenBank;

/**
 * 线下划扣-->富有导出模板
 * @Class Name DeductFailExportModel
 * @author 韩龙
 * @Create In 2015年12月17日
 */
public class DeductFuyouExportModel implements Serializable {

	/**
	 * long serialVersionUID
	 */
	private static final long serialVersionUID = 8265211025163073774L;
	// 序号
	@ExcelField(title = "序号", align = 2)
	private String index;
	// 开户行
	@ExcelField(title = "开户行", align = 2)
	private String accountBank;
	// 扣款人银行账号
	@ExcelField(title = "扣款人银行账号", align = 2)
	private String accountNo;
	// 户名
	@ExcelField(title = "户名", align = 2)
	private String accountName;
	// 金额(单位:元)
	@ExcelField(title = "金额(单位:元)", align = 2)
	private String deductMoney;
	// 企业流水账号
	@ExcelField(title = "企业流水账号", align = 2)
	private String lendCode;
	// 备注
	@ExcelField(title = "备注", align = 2)
	private String fuRemarks;
	// 手机号
	@ExcelField(title = "手机号", align = 2)
	private String customerMobilephone;
	// 证件类型
	@ExcelField(title = "证件类型", align = 2)
	private String dictCustomerCertType;
	// 证件号
	@ExcelField(title = "证件号", align = 2)
	private String customerCertNum;
	
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	public String getAccountBank() {
		if(accountBank != null){
			accountBank = OpenBank.getOpenBank(accountBank);
		}
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
	public String getDeductMoney() {
		return deductMoney;
	}
	public void setDeductMoney(String deductMoney) {
		this.deductMoney = deductMoney;
	}
	public String getLendCode() {
		return lendCode;
	}
	public void setLendCode(String lendCode) {
		this.lendCode = lendCode;
	}
	public String getFuRemarks() {
		return fuRemarks;
	}
	public void setFuRemarks(String fuRemarks) {
		this.fuRemarks = fuRemarks;
	}
	public String getCustomerMobilephone() {
		return customerMobilephone;
	}
	public void setCustomerMobilephone(String customerMobilephone) {
		this.customerMobilephone = customerMobilephone;
	}
	public String getDictCustomerCertType() {
		if(dictCustomerCertType != null){
			dictCustomerCertType = DictUtils.getDictLabel(dictCustomerCertType, "tz_certificate_type", "");
		}
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
	
	@Override
	public String toString() {
		return "index=" + index + ", accountBank="
				+ accountBank + ", accountNo=" + accountNo + ", accountName="
				+ accountName + ", deductMoney=" + deductMoney + ", lendCode="
				+ lendCode + ", fuRemarks=" + fuRemarks
				+ ", customerMobilephone=" + customerMobilephone
				+ ", dictCustomerCertType=" + dictCustomerCertType
				+ ", customerCertNum=" + customerCertNum;
	}
}
