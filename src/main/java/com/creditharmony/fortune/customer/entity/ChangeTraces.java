package com.creditharmony.fortune.customer.entity;

import com.creditharmony.core.persistence.DataEntity;

/**
 * @Class Name CustomerModify
 * @author kevin
 * @Create In 2015年11月30日
 */
public class ChangeTraces extends DataEntity<ChangeTraces> {

	private static final long serialVersionUID = 1L;
	// 申请id
	private String applyId;
	// 变更类型
	private String changeType;
	// 关联id
	private String associateId;
	// 变更前
	private String changeBegin;
	// 变更后
	private String changeAfter;
	// 变更状态
	private String changeState;

	public String getApplyId() {
		return applyId;
	}

	public void setApplyId(String applyId) {
		this.applyId = applyId;
	}

	public String getChangeType() {
		return changeType;
	}

	public void setChangeType(String changeType) {
		this.changeType = changeType;
	}

	public String getAssociateId() {
		return associateId;
	}

	public void setAssociateId(String associateId) {
		this.associateId = associateId;
	}

	public String getChangeBegin() {
		return changeBegin;
	}

	public void setChangeBegin(String changeBegin) {
		this.changeBegin = changeBegin;
	}

	public String getChangeState() {
		return changeState;
	}

	public void setChangeState(String changeState) {
		this.changeState = changeState;
	}

	public String getChangeAfter() {
		return changeAfter;
	}

	public void setChangeAfter(String changeAfter) {
		this.changeAfter = changeAfter;
	}

}
