package com.creditharmony.fortune.customer.workflow.entity;

import java.util.Date;

import com.creditharmony.core.persistence.DataEntity;

/**
 * @Class Name TransferLendLog
 * @author 孙凯文
 * @Create In 2015年12月11日
 */
public class TransferLendLog extends DataEntity<TransferLendLog> {

	private static final long serialVersionUID = 1L;
	// 业务ID
	private String applyId;
	// 客户编号
	private String customerCode;
	// 客户状态
	private String customerStatus;
	// 审批意见
	private String checkExamineContent;
	// 审批结果
	private String checkExamineResult;
	// 审核人
	private String checkBy;
	// 审核时间
	private Date checkTime;
	// 申请人
	private String applyBy;
	// 申请时间
	private Date applyTime;
	// 日志类型
	private String logType;

	public String getApplyId() {
		return applyId;
	}

	public void setApplyId(String applyId) {
		this.applyId = applyId;
	}

	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	public String getCustomerStatus() {
		return customerStatus;
	}

	public void setCustomerStatus(String customerStatus) {
		this.customerStatus = customerStatus;
	}

	public String getCheckExamineContent() {
		return checkExamineContent;
	}

	public void setCheckExamineContent(String checkExamineContent) {
		this.checkExamineContent = checkExamineContent;
	}

	public String getCheckExamineResult() {
		return checkExamineResult;
	}

	public void setCheckExamineResult(String checkExamineResult) {
		this.checkExamineResult = checkExamineResult;
	}

	public String getCheckBy() {
		return checkBy;
	}

	public void setCheckBy(String checkBy) {
		this.checkBy = checkBy;
	}

	public Date getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
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

	public String getLogType() {
		return logType;
	}

	public void setLogType(String logType) {
		this.logType = logType;
	}

}
