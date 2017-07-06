package com.creditharmony.fortune.back.priority.common.view;

import java.util.Date;

import com.creditharmony.core.persistence.DataEntity;

public class PriorityDetailItem extends DataEntity<PriorityDetailItem> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * priorityId
	 */
	private  String priorityId;
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
	 * 银行卡所在城市 区
	 */
	private String accountAddrdistrict;
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
	 * 计划划扣日期
	 */
	private String applyDeductDay;
	/**
	 * 计划出借日期
	 */
	private String applyLendDay;
	/**
	 * 合同编号
	 */
	private String applyContractNo;
	/**
	 * 付款方式
	 */
	private String applyPay;
	/**
	 * 出借产品
	 */
	private String productName;
	/**
	 * 协议版本号
	 */
	private String applyAgreementEdition;
	/**
	 * 审批日期
	 */
	private Date checkTime;
	/**
	 * 状态
	 */
	private String priorityBackState;
	/**
	 * 计划出借金额
	 */
	private Double applyLendMoney;
	/**
	 * 通过不通过
	 * @return
	 */
	private String checkExaminetype;
	/**
	 * 审批意见
	 * @return
	 */
	private String checkExamine;
	//申请人
	private String applyBy;
	//申请时间
	private Date applyTime;
	//创建人
	private String createBy;
	//创建时间
	private Date createTime;
	//备注
	private String backPriorityRemarks;
	//理财经理
	private String managerCode;
	//退回重放
	private String rebackFlag;
	//出借申请的ID
	private String id;
	//回款状态
	private String dictBackStatus;
	// 附件ID
	private String addAttachmentId;
	private String deleteAttachmentId;

	public String getDeleteAttachmentId() {
		return deleteAttachmentId;
	}

	public void setDeleteAttachmentId(String deleteAttachmentId) {
		this.deleteAttachmentId = deleteAttachmentId;
	}

	public String getAddAttachmentId() {
		return addAttachmentId;
	}

	public void setAddAttachmentId(String addAttachmentId) {
		this.addAttachmentId = addAttachmentId;
	}
	public String getDictBackStatus() {
		return dictBackStatus;
	}
	public void setDictBackStatus(String dictBackStatus) {
		this.dictBackStatus = dictBackStatus;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRebackFlag() {
		return rebackFlag;
	}
	public void setRebackFlag(String rebackFlag) {
		this.rebackFlag = rebackFlag;
	}
	public String getManagerCode() {
		return managerCode;
	}
	public void setManagerCode(String managerCode) {
		this.managerCode = managerCode;
	}
	public String getBackPriorityRemarks() {
		return backPriorityRemarks;
	}
	public void setBackPriorityRemarks(String backPriorityRemarks) {
		//backPriorityRemarks=backPriorityRemarks.trim();
		this.backPriorityRemarks = backPriorityRemarks;
	}
	public String getApplyBy() {
		return applyBy;
	}
	public void setApplyBy(String applyBy) {
		this.applyBy = applyBy;
	}
	public Date getApplyTime() {
		return applyTime;
	}
	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getCheckExamine() {
		return checkExamine;
	}
	public void setCheckExamine(String checkExamine) {
		//checkExamine=checkExamine.trim();
		this.checkExamine = checkExamine;
	}
	public Double getApplyLendMoney() {
		return applyLendMoney;
	}
	public void setApplyLendMoney(Double applyLendMoney) {
		this.applyLendMoney = applyLendMoney;
	}
	public String getCheckExaminetype() {
		return checkExaminetype;
	}
	public void setCheckExaminetype(String checkExaminetype) {
		this.checkExaminetype = checkExaminetype;
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
	public String getAccountAddrdistrict() {
		return accountAddrdistrict;
	}
	public void setAccountAddrdistrict(String accountAddrdistrict) {
		this.accountAddrdistrict = accountAddrdistrict;
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
	public String getApplyContractNo() {
		return applyContractNo;
	}
	public void setApplyContractNo(String applyContractNo) {
		this.applyContractNo = applyContractNo;
	}
	public String getApplyPay() {
		return applyPay;
	}
	public void setApplyPay(String applyPay) {
		this.applyPay = applyPay;
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
	public String getPriorityBackState() {
		return priorityBackState;
	}
	public void setPriorityBackState(String priorityBackState) {
		this.priorityBackState = priorityBackState;
	}
	public Date getCheckTime() {
		return checkTime;
	}
	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}
	public String getPriorityId() {
		return priorityId;
	}
	public void setPriorityId(String priorityId) {
		this.priorityId = priorityId;
	}
	
}
