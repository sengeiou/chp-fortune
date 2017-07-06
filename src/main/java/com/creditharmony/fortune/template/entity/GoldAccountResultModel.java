package com.creditharmony.fortune.template.entity;

import java.io.Serializable;

import com.creditharmony.core.excel.annotation.ExcelField;

/**
 * 金帐户导入回执结果实体
 * @Class Name GoldAccountResultModel
 * @author 韩龙
 * @Create In 2016年2月29日
 */
public class GoldAccountResultModel implements Serializable{
	
	/**
	 * long serialVersionUID 
	 */
	private static final long serialVersionUID = 8104125602048043544L;
	// 序号
	@ExcelField(title = "序号" ,type = 2)
	private String index;
	// 付款方登录名
	@ExcelField(title = "付款方登录名" ,type = 2)
	private String paymentLoginName;
	// 付款方中文名称
	@ExcelField(title = "付款方中文名称" ,type = 2)
	private String paymentChineseName;
	// 付款资金来自冻结
	@ExcelField(title = "付款资金来自冻结" ,type = 2)
	private String paymentFromFreezing;
	// 收款方登录名
	@ExcelField(title = "收款方登录名" ,type = 2)
	private String loginNameBeneficiary;
	// 收款方中文名称
	@ExcelField(title = "收款方中文名称" ,type = 2)
	private String beneficiarysChineseName;
	// 收款后立即冻结
	@ExcelField(title = "收款后立即冻结" ,type = 2)
	private String immediatelyReceiptFreezing;
	// 交易金额
	@ExcelField(title = "交易金额" ,type = 2)
	private String transactionMoney;
	// 备注信息
	@ExcelField(title = "备注信息" ,type = 2)
	private String remarks;
	// 预授权合同号
	@ExcelField(title = "预授权合同号" ,type = 2)
	private String preContractNumber;
	// 返回码
	@ExcelField(title = "返回码" ,type = 2)
	private String returnCode;
	// 返回描述
	@ExcelField(title = "返回描述" ,type = 2)
	private String returnDescription;
	// 收款方证件号
	@ExcelField(title = "收款方证件号" ,type = 2)
	private String beneficiaryCertificateNo;
	// 付款方证件号
	@ExcelField(title = "付款方证件号" ,type = 2)
	private String paymentDocumentNo;
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	public String getPaymentLoginName() {
		return paymentLoginName;
	}
	public void setPaymentLoginName(String paymentLoginName) {
		this.paymentLoginName = paymentLoginName;
	}
	public String getPaymentChineseName() {
		return paymentChineseName;
	}
	public void setPaymentChineseName(String paymentChineseName) {
		this.paymentChineseName = paymentChineseName;
	}
	public String getPaymentFromFreezing() {
		return paymentFromFreezing;
	}
	public void setPaymentFromFreezing(String paymentFromFreezing) {
		this.paymentFromFreezing = paymentFromFreezing;
	}
	public String getLoginNameBeneficiary() {
		return loginNameBeneficiary;
	}
	public void setLoginNameBeneficiary(String loginNameBeneficiary) {
		this.loginNameBeneficiary = loginNameBeneficiary;
	}
	public String getBeneficiarysChineseName() {
		return beneficiarysChineseName;
	}
	public void setBeneficiarysChineseName(String beneficiarysChineseName) {
		this.beneficiarysChineseName = beneficiarysChineseName;
	}
	public String getImmediatelyReceiptFreezing() {
		return immediatelyReceiptFreezing;
	}
	public void setImmediatelyReceiptFreezing(String immediatelyReceiptFreezing) {
		this.immediatelyReceiptFreezing = immediatelyReceiptFreezing;
	}
	public String getTransactionMoney() {
		return transactionMoney;
	}
	public void setTransactionMoney(String transactionMoney) {
		this.transactionMoney = transactionMoney;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getPreContractNumber() {
		return preContractNumber;
	}
	public void setPreContractNumber(String preContractNumber) {
		this.preContractNumber = preContractNumber;
	}
	public String getReturnCode() {
		return returnCode;
	}
	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}
	public String getReturnDescription() {
		return returnDescription;
	}
	public void setReturnDescription(String returnDescription) {
		this.returnDescription = returnDescription;
	}
	public String getBeneficiaryCertificateNo() {
		return beneficiaryCertificateNo;
	}
	public void setBeneficiaryCertificateNo(String beneficiaryCertificateNo) {
		this.beneficiaryCertificateNo = beneficiaryCertificateNo;
	}
	public String getPaymentDocumentNo() {
		return paymentDocumentNo;
	}
	public void setPaymentDocumentNo(String paymentDocumentNo) {
		this.paymentDocumentNo = paymentDocumentNo;
	}
	
}
