package com.creditharmony.fortune.look.apply.view;

import java.util.Date;

import org.apache.poi.ss.formula.functions.T;

import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.persistence.DataEntity;

public class PriorityResultView extends 	DataEntity<T>{

	private static final long serialVersionUID = 1L;
	
	//主键id
	private String priorityId;
	// 出借编号
	private String lendCode;
	// 优先回款状态
	private String priorityBackState;
	//申请人ID
	private	String applyBy;
	//申请时间
	private Date applyTime;
	//创建人ID
	private String createBy;
	//创建时间
	private Date createTime;
	//退回重放标识
	private String reback_flag;
	//备注
	private String backPriorityRemarks;
	
	private String managerCode;
	
	//附件ID
	private String addAttachmentId;
	
	
	public String getAddAttachmentId() {
		return addAttachmentId;
	}

	public void setAddAttachmentId(String addAttachmentId) {
		this.addAttachmentId = addAttachmentId;
	}

	public String getManagerCode() {
		if(StringUtils.isNotEmpty(createBy)){
			managerCode =  createBy;
		}
		return managerCode;
	}

	public void setManagerCode(String managerCode) {
		this.managerCode = managerCode;
	}

	public String getLendCode() {
		return lendCode;
	}

	public void setLendCode(String lendCode) {
		this.lendCode = lendCode;
	}

	public String getPriorityBackState() {
		return priorityBackState;
	}

	public void setPriorityBackState(String priorityBackState) {
		this.priorityBackState = priorityBackState;
	}

	public String getPriorityId() {
		return priorityId;
	}

	public void setPriorityId(String priorityId) {
		this.priorityId = priorityId;
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

	public String getReback_flag() {
		return reback_flag;
	}

	public void setReback_flag(String reback_flag) {
		this.reback_flag = reback_flag;
	}

	public String getBackPriorityRemarks() {
		return backPriorityRemarks;
	}

	public void setBackPriorityRemarks(String backPriorityRemarks) {
		this.backPriorityRemarks = backPriorityRemarks;
	}
	
	
}
