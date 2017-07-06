package com.creditharmony.fortune.customer.workflow.entity;

import java.util.Date;

import com.creditharmony.core.persistence.DataEntity;

/**
 * 出借申请日志类
 * 
 * @Class Name LendApplyLog
 * @author 孙凯文
 * @Create In 2016年1月8日
 */
public class LendApplyLog extends DataEntity<LendApplyLog> {

	private static final long serialVersionUID = 8138153371645846686L;
	// 客户编号
	private String customerCode;
	// 工作流ID
	private String applyId;
	// 出借编号
	private String lendCode;
	// 出借状态
	private String dictApplyStatus;
	// 审批内容
	private String checkExamine;
	// 审批结果
	private String checkExaminetype;
	// 审批人
	private String checkById;
	// 审批时间
	private Date checkTime;
	// 申请人
	private String applyBy;
	// 申请时间
	private Date applyTime;
	// 错误类型
	private String errorTypeId;

	public String getErrorTypeId() {
		return errorTypeId;
	}

	public void setErrorTypeId(String errorTypeId) {
		this.errorTypeId = errorTypeId;
	}

	public String getApplyId() {
		return applyId;
	}

	public void setApplyId(String applyId) {
		this.applyId = applyId == null ? null : applyId.trim();
	}

	public String getLendCode() {
		return lendCode;
	}

	public void setLendCode(String lendCode) {
		this.lendCode = lendCode == null ? null : lendCode.trim();
	}

	public String getDictApplyStatus() {
		return dictApplyStatus;
	}

	public void setDictApplyStatus(String dictApplyStatus) {
		this.dictApplyStatus = dictApplyStatus == null ? null : dictApplyStatus
				.trim();
	}

	public String getCheckExamine() {
		return checkExamine;
	}

	public void setCheckExamine(String checkExamine) {
		this.checkExamine = checkExamine == null ? null : checkExamine.trim();
	}

	public String getCheckExaminetype() {
		return checkExaminetype;
	}

	public void setCheckExaminetype(String checkExaminetype) {
		this.checkExaminetype = checkExaminetype == null ? null
				: checkExaminetype.trim();
	}

	public String getCheckById() {
		return checkById;
	}

	public void setCheckById(String checkById) {
		this.checkById = checkById == null ? null : checkById.trim();
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
		this.applyBy = applyBy == null ? null : applyBy.trim();
	}

	public Date getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}

	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

}