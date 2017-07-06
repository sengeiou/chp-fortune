package com.creditharmony.fortune.change.lender.apply.entity;

import com.creditharmony.core.persistence.DataEntity;

/**
 * 变更信息entity
 * @Class Name LenderChangeInfo
 * @author 郭才林
 * @Create In 2015年11月27日
 */
public class LenderChangeInfo  extends DataEntity<LenderChangeInfo>{
	

	private static final long serialVersionUID = 1L;
	// 变更前json
	private String changeBegin;
	// 变更后json
	private String changeAfter;
	// 变更类型
	private String dictChangeType;
	// 变更编码
	private String changeCode;
	// 申请id
	private String applyId;
	// 变更状态
	private String dictChangeState;
	// 变更id
	private String changeId;
	// 变更类型(手机号)
	private String changeTypePhone;
	// 变更类型(销户)
	private String changeTypeAccOff;
	
	public String getChangeId() {
		return changeId;
	}
	public void setChangeId(String changeId) {
		this.changeId = changeId;
	}
	public String getChangeBegin() {
		return changeBegin;
	}
	public void setChangeBegin(String changeBegin) {
		this.changeBegin = changeBegin;
	}
	public String getChangeAfter() {
		return changeAfter;
	}
	public void setChangeAfter(String changeAfter) {
		this.changeAfter = changeAfter;
	}
	
	public String getDictChangeType() {
		return dictChangeType;
	}
	public void setDictChangeType(String dictChangeType) {
		this.dictChangeType = dictChangeType;
	}
	public String getChangeCode() {
		return changeCode;
	}
	public void setChangeCode(String changeCode) {
		this.changeCode = changeCode;
	}
	public String getApplyId() {
		return applyId;
	}
	public void setApplyId(String applyId) {
		this.applyId = applyId;
	}
	public String getDictChangeState() {
		return dictChangeState;
	}
	public void setDictChangeState(String dictChangeState) {
		this.dictChangeState = dictChangeState;
	}
	public String getChangeTypePhone() {
		return changeTypePhone;
	}
	public void setChangeTypePhone(String changeTypePhone) {
		this.changeTypePhone = changeTypePhone;
	}
	public String getChangeTypeAccOff() {
		return changeTypeAccOff;
	}
	public void setChangeTypeAccOff(String changeTypeAccOff) {
		this.changeTypeAccOff = changeTypeAccOff;
	}
	


	

}
