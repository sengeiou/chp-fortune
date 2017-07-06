package com.creditharmony.fortune.deduct.view;

import java.io.Serializable;
import java.util.List;

import com.creditharmony.fortune.deduct.entity.ext.CreditorTradeEx;

/**
 * 划扣申请扩展类model
 * @Class Name DeductPoolView
 * @author 韩龙
 * @Create In 2015年11月27日
 */
public class DeductPoolView implements Serializable {

	private static final long serialVersionUID = 6389744963857576135L;

	// 划扣ID
	private Integer deductApplyId;

	// 客户ID
	private Integer custCode;

	// 客户姓名
	private String custName;

	// 发送状态
	private String matchingFilesendStatus;

	// 计划出借日开始
	private String applyLendDateStart;

	// 计划出借日结束
	private String applyLendDateEnd;

	// 计划出借日
	private String applyLendDate;

	// 计划划扣日期开始
	private String applyDeductDateStart;

	// 计划划扣日期结束
	private String applyDeductDateEnd;

	// 计划划扣日期
	private String applyDeductDate;

	// 营业部
	private String checkNode;

	// 计划出借金额开始
	private String applyLendMoneyStart;

	// 计划出借金额结束
	private String applyLendMoneyEnd;

	// 计划出借金额
	private String applyLendMoney;

	// 计划划扣金额开始
	private String applyDeductMoneyStart;

	// 计划划扣金额开始
	private String applyDeductMoneyEnd;

	// 计划划扣金额
	private String applyDeductMoney;
	
	// 支付方式
	private String applyPay;
	
	// 出借产品
	private String productCode;
	
	// 出借产品名字
	private String productName;
	
	// 划扣平台
	private String applyDeductType;
	
	// 出借银行
	private String accountBank;

	// 出借编号
	private String applyCode;

	// 划扣状态
	private Integer deductStatus;

	// 实际划扣金额
	private String actualDeductMoney;

	// 到期日期
	private String applyExpireDate;
	
	// 债权首期非首期
	private String matchingFirstdayFlag;
	
	// 账单日
	private String matchingBillDate;
	
	// 债权推荐列表
	private List<CreditorTradeEx> creditorTradeExtList;
	
	public Integer getDeductApplyId() {
		return deductApplyId;
	}

	public void setDeductApplyId(Integer deductApplyId) {
		this.deductApplyId = deductApplyId;
	}

	public Integer getCustCode() {
		return custCode;
	}

	public void setCustCode(Integer custCode) {
		this.custCode = custCode;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getMatchingFilesendStatus() {
		return matchingFilesendStatus;
	}

	public void setMatchingFilesendStatus(String matchingFilesendStatus) {
		this.matchingFilesendStatus = matchingFilesendStatus;
	}

	public String getApplyLendDateStart() {
		return applyLendDateStart;
	}

	public void setApplyLendDateStart(String applyLendDateStart) {
		this.applyLendDateStart = applyLendDateStart;
	}

	public String getApplyLendDateEnd() {
		return applyLendDateEnd;
	}

	public void setApplyLendDateEnd(String applyLendDateEnd) {
		this.applyLendDateEnd = applyLendDateEnd;
	}

	public String getApplyLendDate() {
		return applyLendDate;
	}

	public void setApplyLendDate(String applyLendDate) {
		this.applyLendDate = applyLendDate;
	}

	public String getApplyDeductDateStart() {
		return applyDeductDateStart;
	}

	public void setApplyDeductDateStart(String applyDeductDateStart) {
		this.applyDeductDateStart = applyDeductDateStart;
	}

	public String getApplyDeductDateEnd() {
		return applyDeductDateEnd;
	}

	public void setApplyDeductDateEnd(String applyDeductDateEnd) {
		this.applyDeductDateEnd = applyDeductDateEnd;
	}

	public String getApplyDeductDate() {
		return applyDeductDate;
	}

	public void setApplyDeductDate(String applyDeductDate) {
		this.applyDeductDate = applyDeductDate;
	}

	public String getCheckNode() {
		return checkNode;
	}

	public void setCheckNode(String checkNode) {
		this.checkNode = checkNode;
	}

	public String getApplyLendMoneyStart() {
		return applyLendMoneyStart;
	}

	public void setApplyLendMoneyStart(String applyLendMoneyStart) {
		this.applyLendMoneyStart = applyLendMoneyStart;
	}

	public String getApplyLendMoneyEnd() {
		return applyLendMoneyEnd;
	}

	public void setApplyLendMoneyEnd(String applyLendMoneyEnd) {
		this.applyLendMoneyEnd = applyLendMoneyEnd;
	}

	public String getApplyLendMoney() {
		return applyLendMoney;
	}

	public void setApplyLendMoney(String applyLendMoney) {
		this.applyLendMoney = applyLendMoney;
	}

	public String getApplyDeductMoneyStart() {
		return applyDeductMoneyStart;
	}

	public void setApplyDeductMoneyStart(String applyDeductMoneyStart) {
		this.applyDeductMoneyStart = applyDeductMoneyStart;
	}

	public String getApplyDeductMoneyEnd() {
		return applyDeductMoneyEnd;
	}

	public void setApplyDeductMoneyEnd(String applyDeductMoneyEnd) {
		this.applyDeductMoneyEnd = applyDeductMoneyEnd;
	}

	public String getApplyDeductMoney() {
		return applyDeductMoney;
	}

	public void setApplyDeductMoney(String applyDeductMoney) {
		this.applyDeductMoney = applyDeductMoney;
	}

	public String getApplyPay() {
		return applyPay;
	}

	public void setApplyPay(String applyPay) {
		this.applyPay = applyPay;
	}

	public String getApplyDeductType() {
		return applyDeductType;
	}

	public void setApplyDeductType(String applyDeductType) {
		this.applyDeductType = applyDeductType;
	}

	public String getAccountBank() {
		return accountBank;
	}

	public void setAccountBank(String accountBank) {
		this.accountBank = accountBank;
	}

	public String getApplyCode() {
		return applyCode;
	}

	public void setApplyCode(String applyCode) {
		this.applyCode = applyCode;
	}

	public Integer getDeductStatus() {
		return deductStatus;
	}

	public void setDeductStatus(Integer deductStatus) {
		this.deductStatus = deductStatus;
	}

	public String getActualDeductMoney() {
		return actualDeductMoney;
	}

	public void setActualDeductMoney(String actualDeductMoney) {
		this.actualDeductMoney = actualDeductMoney;
	}

	public String getApplyExpireDate() {
		return applyExpireDate;
	}

	public void setApplyExpireDate(String applyExpireDate) {
		this.applyExpireDate = applyExpireDate;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getMatchingFirstdayFlag() {
		return matchingFirstdayFlag;
	}

	public void setMatchingFirstdayFlag(String matchingFirstdayFlag) {
		this.matchingFirstdayFlag = matchingFirstdayFlag;
	}

	public String getMatchingBillDate() {
		return matchingBillDate;
	}

	public void setMatchingBillDate(String matchingBillDate) {
		this.matchingBillDate = matchingBillDate;
	}

	public List<CreditorTradeEx> getCreditorTradeExtList() {
		return creditorTradeExtList;
	}

	public void setCreditorTradeExtList(List<CreditorTradeEx> creditorTradeExtList) {
		this.creditorTradeExtList = creditorTradeExtList;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
}