package com.creditharmony.fortune.lend.apply.view;

import java.util.Date;

/**
 * 出借申请查询类
 * 
 * @Class Name LendApplyQueryView
 * @author 孙凯文
 * @Create In 2015年12月24日
 */
public class LendApplyQueryView {
	
	//客户的申请id，用于导出数据
	private String applyIds;
	
	// 客户姓名
	private String name;
	// 客户编号
	private String code;
	// 出借编号
	private String lendCode;
	// 门店名称
	private String storeId;
	// 出借模式
	private String productCode;
	// 出借日期
	private Date lendDate;
	// 划扣开始时间
	private Date deductStart;
	// 划扣结束时间
	private Date deductEnd;
	// 出借开始时间
	private Date lendStart;
	// 出借结束时间
	private Date lendEnd;
	// 申请日期
	private Date applyDate;
	private String orgName;
	private String lendStatus;
	private Date expireDate;
	private Date finalLinitDate; // 到期日期
	private Date finalLinitDateTo;

	// 划扣平台ID
    private String dictApplyDeductType;
    
    // 出借银行
 	private String accountBank;
 	
 	private Date backMoneyDayBegin; 	//回款开始时间
	private Date backMoneyDayEnd;  		// 回款截止时间
	// 付款方式
	private String payType;
	// 付款方式2
	private String applyPay;
	private String custCertNum;
	//审核人
	private String auditor;
	private String channelMarking;// 渠道标识
	private String switchApproveStatus;// 转投审批状态
	private String approveReason;// 审核信息
	private String cancelReason;// 撤销转投原因
	private Date approveDateStart;// 转投审批时间
	private Date approveDateEnd;// 转投审批时间
	private String interestDay;// 补息天数
	// 完结状态
	private String dictApplyEndStatus;
	
 	public String getApplyPay() {
		return applyPay;
	}

	public void setApplyPay(String applyPay) {
		this.applyPay = applyPay;
	}

	public Date getBackMoneyDayBegin() {
		return backMoneyDayBegin;
	}

	public void setBackMoneyDayBegin(Date backMoneyDayBegin) {
		this.backMoneyDayBegin = backMoneyDayBegin;
	}

	public Date getBackMoneyDayEnd() {
		return backMoneyDayEnd;
	}

	public void setBackMoneyDayEnd(Date backMoneyDayEnd) {
		this.backMoneyDayEnd = backMoneyDayEnd;
	}

	public String getApplyIds() {
		return applyIds;
	}

	public void setApplyIds(String applyIds) {
		this.applyIds = applyIds;
	}

	public String getLendStatus() {
		return lendStatus;
	}

	public void setLendStatus(String lendStatus) {
		this.lendStatus = lendStatus;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLendCode() {
		return lendCode;
	}

	public void setLendCode(String lendCode) {
		this.lendCode = lendCode;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public Date getLendDate() {
		return lendDate;
	}

	public void setLendDate(Date lendDate) {
		this.lendDate = lendDate;
	}

	public Date getDeductStart() {
		return deductStart;
	}

	public void setDeductStart(Date deductStart) {
		this.deductStart = deductStart;
	}

	public Date getDeductEnd() {
		return deductEnd;
	}

	public void setDeductEnd(Date deductEnd) {
		this.deductEnd = deductEnd;
	}

	public Date getLendStart() {
		return lendStart;
	}

	public void setLendStart(Date lendStart) {
		this.lendStart = lendStart;
	}

	public Date getLendEnd() {
		return lendEnd;
	}

	public void setLendEnd(Date lendEnd) {
		this.lendEnd = lendEnd;
	}

	public Date getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getDictApplyDeductType() {
		return dictApplyDeductType;
	}

	public void setDictApplyDeductType(String dictApplyDeductType) {
		this.dictApplyDeductType = dictApplyDeductType;
	}

	public String getAccountBank() {
		return accountBank;
	}

	public void setAccountBank(String accountBank) {
		this.accountBank = accountBank;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getAuditor() {
		return auditor;
	}

	public void setAuditor(String auditor) {
		this.auditor = auditor;
	}

	public Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

	public Date getFinalLinitDate() {
		return finalLinitDate;
	}

	public void setFinalLinitDate(Date finalLinitDate) {
		this.finalLinitDate = finalLinitDate;
	}

	public Date getFinalLinitDateTo() {
		return finalLinitDateTo;
	}

	public void setFinalLinitDateTo(Date finalLinitDateTo) {
		this.finalLinitDateTo = finalLinitDateTo;
	}

	public String getCustCertNum() {
		return custCertNum;
	}

	public void setCustCertNum(String custCertNum) {
		this.custCertNum = custCertNum;
	}

	public String getChannelMarking() {
		return channelMarking;
	}

	public void setChannelMarking(String channelMarking) {
		this.channelMarking = channelMarking;
	}

	public String getSwitchApproveStatus() {
		return switchApproveStatus;
	}

	public void setSwitchApproveStatus(String switchApproveStatus) {
		this.switchApproveStatus = switchApproveStatus;
	}

	public String getApproveReason() {
		return approveReason;
	}

	public void setApproveReason(String approveReason) {
		this.approveReason = approveReason;
	}

	public String getCancelReason() {
		return cancelReason;
	}

	public void setCancelReason(String cancelReason) {
		this.cancelReason = cancelReason;
	}

	public Date getApproveDateStart() {
		return approveDateStart;
	}

	public void setApproveDateStart(Date approveDateStart) {
		this.approveDateStart = approveDateStart;
	}

	public Date getApproveDateEnd() {
		return approveDateEnd;
	}

	public void setApproveDateEnd(Date approveDateEnd) {
		this.approveDateEnd = approveDateEnd;
	}

	public String getInterestDay() {
		return interestDay;
	}

	public void setInterestDay(String interestDay) {
		this.interestDay = interestDay;
	}

	public String getDictApplyEndStatus() {
		return dictApplyEndStatus;
	}

	public void setDictApplyEndStatus(String dictApplyEndStatus) {
		this.dictApplyEndStatus = dictApplyEndStatus;
	}
}
