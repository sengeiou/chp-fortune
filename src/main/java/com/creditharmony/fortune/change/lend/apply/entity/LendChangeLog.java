package com.creditharmony.fortune.change.lend.apply.entity;

import com.creditharmony.core.persistence.DataEntity;

/**
 * 审批记录实体表
 * @Class Name LenderChangeLog
 * @author 刘雄武
 * @Create In 2015年12月4日
 */
public class LendChangeLog extends DataEntity<LendChangeLog>{
   
	
	private static final long serialVersionUID = 1L;
	private String changeId;// 变更ID
	private String applyId;// 申请ID
	private String auditCheckExamine;// 审批意见
	private String auditResult;// 审批结果
	private Long   approveId;// 审批人ID
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
	public Long getApproveId() {
		return approveId;
	}
	public void setApproveId(Long approveId) {
		this.approveId = approveId;
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
