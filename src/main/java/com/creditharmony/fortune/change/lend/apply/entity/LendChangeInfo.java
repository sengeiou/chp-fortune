package com.creditharmony.fortune.change.lend.apply.entity;

import com.creditharmony.core.persistence.DataEntity;

/**
 * 出借信息变更JSON层
 * @Class Name LendChangeInfo
 * @author 刘雄武
 * @Create In 2015年12月2日
 */
public class LendChangeInfo extends DataEntity<LendChangeInfo> {

	/**
	 * long serialVersionUID 
	 */
	private static final long serialVersionUID = 1L;
	private String changeBegin;// 变更前json
	private String changeAfter;// 变更后json	
	private String dictChangeType;// 变更类型
	private String changeCode;// 变更关联CODE
	private String changeId;// 变更ID
	private String applyId;// 申请ID
	private String dictChangeState;// 状态
	
	public String getDictChangeState() {
		return dictChangeState;
	}
	public void setDictChangeState(String dictChangeState) {
		this.dictChangeState = dictChangeState;
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
	public String getChangeId() {
		return changeId;
	}
	public void setChangeId(String changeId) {
		this.changeId = changeId;
	}
	public String getApplyId() {
		return applyId;
	}
	public void setApplyId(String applyId) {
		this.applyId = applyId;
	}
	
}
