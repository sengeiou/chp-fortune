package com.creditharmony.fortune.deduct.entity;

import com.creditharmony.core.persistence.DataEntity;

/**
 * 划扣申请实体model
 * @Class Name DeductPool
 * @author 韩龙
 * @Create In 2015年11月27日
 */
public class DeductPool extends DataEntity<DeductPool>{
   
	private static final long serialVersionUID = 6389744963857576135L;
	// 划扣申请ID
	private String deductApplyId;
	// 客户编号
    private String custCode;
    // 出借编号
    private String applyCode;
    // 划扣申请状态
    private String dictDeductStatus;
    // 实际划扣金额
    private String actualDeductMoney;
    // 确认人ID
    private String confirmId;
    // 确认时间
    private String confirmDate;
    // 确认结果
    private String confirmResult;
    // 确认意见（失败时填写）
    private String confirmOpinion;
    //  批处理ID
    private String batchId;
    // 失败金额
    private String failMoney;
    // 划扣平台ID
    private String dictApplyDeductType;
    // 分天划扣标识
    private String dayDeductFlag;
    // 出借金额
    private String loanMoney;
    // 交易时间
    private String dealTime;
    // 失败原因
    private String failReason;
    // 未划扣金额
    private String noDeductMoney;
    // 收款确认书邮件发送状态
    private String sendEmailStatus;
    // 收款确认书合成状态
    private String makeFlieStatus;

	public String getDeductApplyId() {
		return deductApplyId;
	}

	public void setDeductApplyId(String deductApplyId) {
		this.deductApplyId = deductApplyId;
	}

	public String getCustCode() {
		return custCode;
	}

	public void setCustCode(String custCode) {
		this.custCode = custCode;
	}

	public String getApplyCode() {
		return applyCode;
	}

	public void setApplyCode(String applyCode) {
		this.applyCode = applyCode;
	}

	public String getDictDeductStatus() {
		return dictDeductStatus;
	}

	public void setDictDeductStatus(String dictDeductStatus) {
		this.dictDeductStatus = dictDeductStatus;
	}

	public String getActualDeductMoney() {
		return actualDeductMoney;
	}

	public void setActualDeductMoney(String actualDeductMoney) {
		this.actualDeductMoney = actualDeductMoney;
	}

	public String getConfirmId() {
		return confirmId;
	}

	public void setConfirmId(String confirmId) {
		this.confirmId = confirmId;
	}

	public String getConfirmDate() {
		return confirmDate;
	}

	public void setConfirmDate(String confirmDate) {
		this.confirmDate = confirmDate;
	}

	public String getConfirmResult() {
		return confirmResult;
	}

	public void setConfirmResult(String confirmResult) {
		this.confirmResult = confirmResult;
	}

	public String getConfirmOpinion() {
		return confirmOpinion;
	}

	public void setConfirmOpinion(String confirmOpinion) {
		this.confirmOpinion = confirmOpinion;
	}

	public String getBatchId() {
		return batchId;
	}

	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	public String getFailMoney() {
		return failMoney;
	}

	public void setFailMoney(String failMoney) {
		this.failMoney = failMoney;
	}

	public String getDictApplyDeductType() {
		return dictApplyDeductType;
	}

	public void setDictApplyDeductType(String dictApplyDeductType) {
		this.dictApplyDeductType = dictApplyDeductType;
	}

	public String getDayDeductFlag() {
		return dayDeductFlag;
	}

	public void setDayDeductFlag(String dayDeductFlag) {
		this.dayDeductFlag = dayDeductFlag;
	}

	public String getLoanMoney() {
		return loanMoney;
	}

	public void setLoanMoney(String loanMoney) {
		this.loanMoney = loanMoney;
	}

	public String getDealTime() {
		return dealTime;
	}

	public void setDealTime(String dealTime) {
		this.dealTime = dealTime;
	}

	public String getFailReason() {
		return failReason;
	}

	public void setFailReason(String failReason) {
		this.failReason = failReason;
	}

	public String getNoDeductMoney() {
		return noDeductMoney;
	}

	public void setNoDeductMoney(String noDeductMoney) {
		this.noDeductMoney = noDeductMoney;
	}

	public String getSendEmailStatus() {
		return sendEmailStatus;
	}

	public void setSendEmailStatus(String sendEmailStatus) {
		this.sendEmailStatus = sendEmailStatus;
	}

	public String getMakeFlieStatus() {
		return makeFlieStatus;
	}

	public void setMakeFlieStatus(String makeFlieStatus) {
		this.makeFlieStatus = makeFlieStatus;
	}

}