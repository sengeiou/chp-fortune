package com.creditharmony.fortune.template.entity;

import java.io.Serializable;

import com.creditharmony.core.common.type.CertificateType;
import com.creditharmony.core.dict.util.DictUtils;
import com.creditharmony.core.excel.annotation.ExcelField;
import com.creditharmony.core.fortune.type.OpenBank;
import com.creditharmony.fortune.deduct.common.DeductUtils;
import com.creditharmony.fortune.utils.OptionUtil;

/**
 * 线下划扣-->中金导出模板
 * @Class Name DeductZhongJinExportModel
 * @author 韩龙
 * @Create In 2016年3月2日
 */
public class DeductZhongJinExportModel implements Serializable {

	/**
	 * long serialVersionUID
	 */
	private static final long serialVersionUID = -3562542271029306775L;

	// 明细流水号
	@ExcelField(title = "明细流水号")
	private String index;
	// 金额(元)
	@ExcelField(title = "金额(元)")
	private String deductMoney;
	// 银行名称
	@ExcelField(title = "银行名称")
	private String bankName;
	// 账户类型
	@ExcelField(title = "账户类型")
	private String accountType;
	// 账户名称
	@ExcelField(title = "账户名称")
	private String accountName;
	// 账户号码
	@ExcelField(title = "账户号码")
	private String accountNo;
	// 分支行
	@ExcelField(title = "分支行")
	private String accountBranch;
	// 省份
	@ExcelField(title = "省份")
	private String accountAddrProvince;
	// 城市
	@ExcelField(title = "城市")
	private String accountAddrCity;
	// 结算标识
	@ExcelField(title = "结算标识")
	private String settlementMark;
	// 备注
	@ExcelField(title = "备注")
	private String zhongRemarks;
	// 证件类型
	@ExcelField(title = "证件类型")
	private String dictCustomerCertType;
	// 证件号码
	@ExcelField(title = "证件号码")
	private String customerCertNum;
	// 手机号
	@ExcelField(title = "手机号")
	private String customerMobilephone;
	// 电子邮箱
	@ExcelField(title = "电子邮箱")
	private String emailAddress;
	// 协议用户编号
	@ExcelField(title = "协议用户编号")
	private String protocolUserNumber;
	
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	public String getDeductMoney() {
		return deductMoney;
	}
	public void setDeductMoney(String deductMoney) {
		this.deductMoney = deductMoney;
	}
	public String getBankName() {
		if(bankName != null){
			bankName = OpenBank.getOpenBank(bankName);
		}
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
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
	public String getAccountBranch() {
		return accountBranch;
	}
	public void setAccountBranch(String accountBranch) {
		this.accountBranch = accountBranch;
	}
	public String getAccountAddrProvince() {
		if(accountAddrProvince!=null){
			accountAddrProvince= OptionUtil.getProvinceLabel(accountAddrProvince);
		}
		return accountAddrProvince;
	}
	public void setAccountAddrProvince(String accountAddrProvince) {
		this.accountAddrProvince = accountAddrProvince;
	}
	public String getAccountAddrCity() {
		if(accountAddrCity!=null){
			accountAddrCity =  OptionUtil.getCityLabel(accountAddrCity);
		}
		return accountAddrCity;
	}
	public void setAccountAddrCity(String accountAddrCity) {
		this.accountAddrCity = accountAddrCity;
	}
	public String getSettlementMark() {
		return settlementMark;
	}
	public void setSettlementMark(String settlementMark) {
		this.settlementMark = settlementMark;
	}
	public String getZhongRemarks() {
		return zhongRemarks;
	}
	public void setZhongRemarks(String zhongRemarks) {
		this.zhongRemarks = zhongRemarks;
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
	public String getCustomerMobilephone() {
		return customerMobilephone;
	}
	public void setCustomerMobilephone(String customerMobilephone) {
		this.customerMobilephone = customerMobilephone;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getProtocolUserNumber() {
		return protocolUserNumber;
	}
	public void setProtocolUserNumber(String protocolUserNumber) {
		this.protocolUserNumber = protocolUserNumber;
	}
	
	@Override
	public String toString() {
		return DeductUtils.isNull(index) + ","
				+ DeductUtils.isNull(deductMoney) + "," + DeductUtils.isNull(OpenBank.getOpenBank(bankName)) + ","
				+ DeductUtils.isNull(accountType) + "," + DeductUtils.isNull(accountName) + ","
				+ DeductUtils.isNull(accountNo) + "," + DeductUtils.isNull(accountBranch)
				+ "," + DeductUtils.isNull( OptionUtil.getProvinceLabel(accountAddrProvince))
				+ "," + DeductUtils.isNull( OptionUtil.getCityLabel(accountAddrCity)) + ","
				+ DeductUtils.isNull(settlementMark) + "," + DeductUtils.isNull(zhongRemarks)
				+ "," + DeductUtils.isNull(CertificateType.parseByCode(dictCustomerCertType).getName())
				+ "," + DeductUtils.isNull(customerCertNum)
				+ "," + DeductUtils.isNull(customerMobilephone)
				+ "," + DeductUtils.isNull(emailAddress) + ","
				+ DeductUtils.isNull(protocolUserNumber) + "\r\n";
	}

}
