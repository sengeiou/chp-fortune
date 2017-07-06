package com.creditharmony.fortune.back.money.common.view;

import java.math.BigDecimal;
import java.util.Date;

import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.persistence.DataEntity;

/**
 * 回款详情页封装Bean
 * 
 * @Class Name BackMoneyItemView
 * @author 陈广鹏
 * @Create In 2015年12月2日
 */
public class ItemView extends DataEntity<ItemView> {

	/**
	 * long serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 客户姓名
	 */
	private String customerName;
	/**
	 * 客户编号
	 */
	private String customerCode;
	/**
	 * 证件号码
	 */
	private String customerCertNum;
	/**
	 * 开户行
	 */
	private String accountBank;
	/**
	 * 银行卡所在城市 省
	 */
	private String accountAddrprovince;
	/**
	 * 银行卡所在城市 市
	 */
	private String accountAddrcity;
	/**
	 * 银行卡所在城市 金账户省
	 */
	private String faccountAddrprovince;
	/**
	 * 银行卡所在城市 金账户市
	 */
	private String faccountAddrcity;
	/**
	 * 银行卡所在城市 区
	 */
	private String accountAddrdistrict;
	/**
	 * 银行卡所在城市 金账户区
	 */
	private String faccountAddrdistrict;
	/**
	 * 卡或折
	 */
	private String accountCardOrBooklet;
	/**
	 * 具体支行
	 */
	private String accountBranch;
	/**
	 * 开户姓名
	 */
	private String accountName;
	/**
	 * 回款账户
	 */
	private String accountNo;
	/**
	 * 出借编号
	 */
	private String lendCode;
	/**
	 * 出借申请日期
	 */
	private String applyDay;
	/**
	 * 计划划扣日期
	 */
	private String applyDeductDay;
	/**
	 * 计划出借日期
	 */
	private String applyLendDay;
	/**
	 * 付款方式
	 */
	private String applyPay;
	/**
	 * 销售折扣率（%）
	 */
	private String applySalesDiscount;
	/**
	 * 计划出借金额
	 */
	private Double applyLendMoney;
	/**
	 * 出借产品
	 */
	private String productName;
	/**
	 * 协议版本号
	 */
	private String applyAgreementEdition;
	/**
	 * 合同编号
	 */
	private String applyContractNo;
	/**
	 * 出借备注
	 */
	private String applyRemarks;
	/**
	 * 应回金额
	 */
	private Double backMoney;
	/**
	 * 实际回款金额
	 */
	private Double backActualbackMoney;
	/**
	 * 补息后回款金额
	 */
	private Double supplementedMoney;
	/**
	 * 到期日
	 */
	private String finalLinitDate;
	/**
	 * 回款日期
	 */
	private Date backMoneyDay;
	/**
	 * 债权转让日期
	 */
	private Date debtTransferDate;
	/**
	 * 备注
	 */
	private String backMoneyRemarks;
	/**
	 * 回款状态
	 */
	private String dictBackStatus;
	/**
	 * 回款ID
	 */
	private String backmId;
	/**
	 * 审批结果
	 */
	private String checkExaminetype;
	/**
	 * 审批意见
	 */
	private String checkExamine;
	/**
	 * 其它审批原因
	 */
	private String checkReason;
	/**
	 * 渠道
	 */
	private String dictFortunechannelflag;
	/**
	 * 在职状态
	 */
	private String workingState;
	/**
	 * 优先回款标识
	 */
	private String priorityBack;
	/**
	 * 实际回款金额 (BigDecimal类型)
	 * @return
	 */
	private BigDecimal backActualbackMoneyBig;
	
	
	public BigDecimal getBackActualbackMoneyBig() {
		return backActualbackMoneyBig;
	}

	public void setBackActualbackMoneyBig(BigDecimal backActualbackMoneyBig) {
		this.backActualbackMoneyBig = backActualbackMoneyBig;
	}

	public String getPriorityBack() {
		return priorityBack;
	}

	public void setPriorityBack(String priorityBack) {
		this.priorityBack = priorityBack;
	}

	public String getWorkingState() {
		return workingState;
	}

	public void setWorkingState(String workingState) {
		this.workingState = workingState;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	public String getCustomerCertNum() {
		return customerCertNum;
	}

	public void setCustomerCertNum(String customerCertNum) {
		this.customerCertNum = customerCertNum;
	}

	public String getAccountBank() {
		return accountBank;
	}

	public void setAccountBank(String accountBank) {
		this.accountBank = accountBank;
	}

	public String getAccountAddrprovince() {
		if (StringUtils.isEmpty(accountAddrprovince)) {
			accountAddrprovince = faccountAddrprovince;
		}
		return accountAddrprovince;
	}

	public void setAccountAddrprovince(String accountAddrprovince) {
		this.accountAddrprovince = accountAddrprovince;
	}

	public String getAccountAddrcity() {
		if (StringUtils.isEmpty(accountAddrcity)) {
			accountAddrcity = faccountAddrcity;
		}
		return accountAddrcity;
	}

	public void setAccountAddrcity(String accountAddrcity) {
		this.accountAddrcity = accountAddrcity;
	}

	public String getFaccountAddrprovince() {
		return faccountAddrprovince;
	}

	public void setFaccountAddrprovince(String faccountAddrprovince) {
		this.faccountAddrprovince = faccountAddrprovince;
	}

	public String getFaccountAddrcity() {
		return faccountAddrcity;
	}

	public void setFaccountAddrcity(String faccountAddrcity) {
		this.faccountAddrcity = faccountAddrcity;
	}

	public String getAccountAddrdistrict() {
		if (StringUtils.isEmpty(accountAddrdistrict)) {
			accountAddrdistrict = faccountAddrdistrict;
		}
		return accountAddrdistrict;
	}

	public void setAccountAddrdistrict(String accountAddrdistrict) {
		this.accountAddrdistrict = accountAddrdistrict;
	}

	public String getFaccountAddrdistrict() {
		return faccountAddrdistrict;
	}

	public void setFaccountAddrdistrict(String faccountAddrdistrict) {
		this.faccountAddrdistrict = faccountAddrdistrict;
	}

	public String getAccountCardOrBooklet() {
		return accountCardOrBooklet;
	}

	public void setAccountCardOrBooklet(String accountCardOrBooklet) {
		this.accountCardOrBooklet = accountCardOrBooklet;
	}

	public String getAccountBranch() {
		return accountBranch;
	}

	public void setAccountBranch(String accountBranch) {
		this.accountBranch = accountBranch;
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

	public String getLendCode() {
		return lendCode;
	}

	public void setLendCode(String lendCode) {
		this.lendCode = lendCode;
	}

	public String getApplyDay() {
		return applyDay;
	}

	public void setApplyDay(String applyDay) {
		this.applyDay = applyDay;
	}

	public String getApplyDeductDay() {
		return applyDeductDay;
	}

	public void setApplyDeductDay(String applyDeductDay) {
		this.applyDeductDay = applyDeductDay;
	}

	public String getApplyLendDay() {
		return applyLendDay;
	}

	public void setApplyLendDay(String applyLendDay) {
		this.applyLendDay = applyLendDay;
	}

	public String getApplyPay() {
		return applyPay;
	}

	public void setApplyPay(String applyPay) {
		this.applyPay = applyPay;
	}

	public String getApplySalesDiscount() {
		return applySalesDiscount;
	}

	public void setApplySalesDiscount(String applySalesDiscount) {
		this.applySalesDiscount = applySalesDiscount;
	}

	public Double getApplyLendMoney() {
		return applyLendMoney;
	}

	public void setApplyLendMoney(Double applyLendMoney) {
		this.applyLendMoney = applyLendMoney;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getApplyAgreementEdition() {
		return applyAgreementEdition;
	}

	public void setApplyAgreementEdition(String applyAgreementEdition) {
		this.applyAgreementEdition = applyAgreementEdition;
	}

	public String getApplyContractNo() {
		return applyContractNo;
	}

	public void setApplyContractNo(String applyContractNo) {
		this.applyContractNo = applyContractNo;
	}

	public String getApplyRemarks() {
		return applyRemarks;
	}

	public void setApplyRemarks(String applyRemarks) {
		this.applyRemarks = applyRemarks;
	}

	public Double getBackMoney() {
		return backMoney;
	}

	public void setBackMoney(Double backMoney) {
		this.backMoney = backMoney;
	}

	public Double getBackActualbackMoney() {
		return backActualbackMoney;
	}

	public void setBackActualbackMoney(Double backActualbackMoney) {
		this.backActualbackMoney = backActualbackMoney;
	}

	public String getFinalLinitDate() {
		return finalLinitDate;
	}

	public void setFinalLinitDate(String finalLinitDate) {
		this.finalLinitDate = finalLinitDate;
	}

	public Double getSupplementedMoney() {
		return supplementedMoney;
	}

	public void setSupplementedMoney(Double supplementedMoney) {
		this.supplementedMoney = supplementedMoney;
	}

	public Date getBackMoneyDay() {
		return backMoneyDay;
	}

	public void setBackMoneyDay(Date backMoneyDay) {
		this.backMoneyDay = backMoneyDay;
	}

	public Date getDebtTransferDate() {
		return debtTransferDate;
	}

	public void setDebtTransferDate(Date debtTransferDate) {
		this.debtTransferDate = debtTransferDate;
	}

	public String getBackMoneyRemarks() {
		return backMoneyRemarks;
	}

	public void setBackMoneyRemarks(String backMoneyRemarks) {
		this.backMoneyRemarks = backMoneyRemarks;
	}

	public String getDictBackStatus() {
		return dictBackStatus;
	}

	public void setDictBackStatus(String dictBackStatus) {
		this.dictBackStatus = dictBackStatus;
	}

	public String getBackmId() {
		return backmId;
	}

	public void setBackmId(String backmId) {
		this.backmId = backmId;
	}

	public String getCheckExaminetype() {
		return checkExaminetype;
	}

	public void setCheckExaminetype(String checkExaminetype) {
		this.checkExaminetype = checkExaminetype;
	}

	public String getCheckExamine() {
		return checkExamine;
	}

	public void setCheckExamine(String checkExamine) {
		this.checkExamine = checkExamine;
	}

	public String getCheckReason() {
		return checkReason;
	}

	public void setCheckReason(String checkReason) {
		this.checkReason = checkReason;
	}

	public String getDictFortunechannelflag() {
		return dictFortunechannelflag;
	}

	public void setDictFortunechannelflag(String dictFortunechannelflag) {
		this.dictFortunechannelflag = dictFortunechannelflag;
	}
}
