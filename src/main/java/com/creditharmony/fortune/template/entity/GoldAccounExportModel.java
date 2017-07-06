package com.creditharmony.fortune.template.entity;

import com.creditharmony.core.excel.annotation.ExcelField;
import com.creditharmony.fortune.deduct.common.Constant;

/**
 * 金帐户划扣导出
 * @Class Name GoldAccounExportModel
 * @author 韩龙
 * @Create In 2016年2月22日
 */
public class GoldAccounExportModel {
	
	// 序号
	@ExcelField(title = "序号",align = 2)
	private String index;
	// 付款方登录名
	@ExcelField(title = "付款方登录名",align = 2)
	private String loginName;
	// 付款方中文名称
	@ExcelField(title = "付款方中文名称",align = 2)
	private String payChineseName;
	// 付款资金来自冻结
	@ExcelField(title = "付款资金来自冻结",align = 2)
	private String paymentFreezing;
	// 收款方登录名
	@ExcelField(title = "收款方登录名",align = 2)
	private String payeeLoginName;
	// 收款方中文名称
	@ExcelField(title = "收款方中文名称",align = 2)
	private String payeeChineseName;
	// 收款后立即冻结
	@ExcelField(title = "收款后立即冻结",align = 2)
	private String frozenAfterPayment;
	// 交易金额
	@ExcelField(title = "交易金额",align = 2)
	private String transactionMoney;
	// 备注信息
	@ExcelField(title = "备注信息",align = 2)
	private String remarks;
	// 预授权合同号
	@ExcelField(title = "预授权合同号",align = 2)
	private String preContractNo;
	
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getPayChineseName() {
		return payChineseName;
	}
	public void setPayChineseName(String payChineseName) {
		this.payChineseName = payChineseName;
	}
	public String getPaymentFreezing() {
		paymentFreezing = Constant.getProperties.get("payment_freezing");
		if(paymentFreezing == null){
			paymentFreezing = "email-conf.xml.properties少配置payment_freezing=否";
		}
		return paymentFreezing;
	}
	public void setPaymentFreezing(String paymentFreezing) {
		this.paymentFreezing = paymentFreezing;
	}
	public String getPayeeLoginName() {
		payeeLoginName = Constant.getProperties.get("gold_login_name");
		if(payeeLoginName == null){
			payeeLoginName = "email-conf.xml.properties少配置gold_login_name=18311053891";
		}
		return payeeLoginName;
	}
	public void setPayeeLoginName(String payeeLoginName) {
		this.payeeLoginName = payeeLoginName;
	}
	public String getPayeeChineseName() {
		payeeChineseName = Constant.getProperties.get("gold_chinese_name");
		if(payeeChineseName == null){
			payeeChineseName = "email-conf.xml.properties少配置gold_chinese_name=夏靖";
		}
		return payeeChineseName;
	}
	public void setPayeeChineseName(String payeeChineseName) {
		this.payeeChineseName = payeeChineseName;
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
	public String getPreContractNo() {
		return preContractNo;
	}
	public void setPreContractNo(String preContractNo) {
		this.preContractNo = preContractNo;
	}
	public String getFrozenAfterPayment() {
		frozenAfterPayment = Constant.getProperties.get("frozen_after_payment");
		if(frozenAfterPayment == null){
			frozenAfterPayment = "email-conf.xml.properties少配置frozen_after_payment=否";
		}
		return frozenAfterPayment;
	}
	public void setFrozenAfterPayment(String frozenAfterPayment) {
		this.frozenAfterPayment = frozenAfterPayment;
	}
}
