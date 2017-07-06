package com.creditharmony.fortune.change.lender.apply.entity;

import com.creditharmony.core.persistence.DataEntity;

/**
 * 审批记录表entity
 * @Class Name LenderChangeLog
 * @author 郭才林
 * @Create In 2015年12月1日
 */

public class LenderChangeLog extends DataEntity<LenderChangeLog>{
  
	private static final long serialVersionUID = 1L;
	private String   changeId;// 变更id
	private String applyId;// 申请id
	private String auditCheckExamine;// 审批意见
	private String auditResult;// 审批结果
	private String dictChangeState;// 审核状态
	private String changerTypeVal;// 变更类型
	
	
	public String getDictChangeState() {
		return dictChangeState;
	}
	public void setDictChangeState(String dictChangeState) {
		this.dictChangeState = dictChangeState;
	}
	public String getApplyId() {
		return applyId;
	}
	public void setApplyId(String applyId) {
		this.applyId = applyId;
	}
	public String getAuditCheckExamine() {
		return auditCheckExamine;
	}
	public void setAuditCheckExamine(String auditCheckExamine) {
		this.auditCheckExamine = auditCheckExamine;
	}
	public String getAuditResult() {
		return auditResult;
	}
	public void setAuditResult(String auditResult) {
		this.auditResult = auditResult;
	}

	public String getChangeId() {
		return changeId;
	}
	public void setChangeId(String changeId) {
		this.changeId = changeId;
	}
	public String getChangerTypeVal() {
		return changerTypeVal;
	}
	public void setChangerTypeVal(String changerTypeVal) {
		this.changerTypeVal = changerTypeVal;
	}
	
	
	
	
}
