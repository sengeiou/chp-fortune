package com.creditharmony.fortune.contract.entity;

import java.util.Date;
import java.util.List;

import com.creditharmony.core.persistence.DataEntity;


public class ContractChange extends DataEntity<ContractChange> {
	private static final long serialVersionUID = -3388728794937324830L;
	private String Id;						//ID
	private	String contCode;				//合同编号
	private Date changeDay;					//变更日期
	private String dictChangeType;			//变更类型（作废/转借/遗失）
	private String changeExplain;			//申请说明
	private String changeApply;				//申请人
	private Date changeCheckDate;			//审核日期
	private String dictChangeStatus;		//审核状态
	private String changeCheckDesc;			//审核意见
	private String changeCheckResult;		//审核结果 0 通过 1 拒绝
	private String changeCheckById;			//审核人ID
	private String changeInStoresId;		//转入门店ID
	private String changeOutStoresId;		//装出门店ID
	private List<String> addAttachmentId;  //附件id
	
	
	
	public List<String> getAddAttachmentId() {
		return addAttachmentId;
	}
	public void setAddAttachmentId(List<String> addAttachmentId) {
		this.addAttachmentId = addAttachmentId;
	}
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getContCode() {
		return contCode;
	}
	public void setContCode(String contCode) {
		this.contCode = contCode;
	}
	public Date getChangeDay() {
		return changeDay;
	}
	public void setChangeDay(Date changeDay) {
		this.changeDay = changeDay;
	}
	public String getDictChangeType() {
		return dictChangeType;
	}
	public void setDictChangeType(String dictChangeType) {
		this.dictChangeType = dictChangeType;
	}
	public String getChangeExplain() {
		return changeExplain;
	}
	public void setChangeExplain(String changeExplain) {
		this.changeExplain = changeExplain;
	}
	public String getChangeApply() {
		return changeApply;
	}
	public void setChangeApply(String changeApply) {
		this.changeApply = changeApply;
	}
	public Date getChangeCheckDate() {
		return changeCheckDate;
	}
	public void setChangeCheckDate(Date changeCheckDate) {
		this.changeCheckDate = changeCheckDate;
	}
	public String getDictChangeStatus() {
		return dictChangeStatus;
	}
	public void setDictChangeStatus(String dictChangeStatus) {
		this.dictChangeStatus = dictChangeStatus;
	}
	public String getChangeCheckDesc() {
		return changeCheckDesc;
	}
	public void setChangeCheckDesc(String changeCheckDesc) {
		this.changeCheckDesc = changeCheckDesc;
	}
	public String getChangeCheckResult() {
		return changeCheckResult;
	}
	public void setChangeCheckResult(String changeCheckResult) {
		this.changeCheckResult = changeCheckResult;
	}
	public String getChangeCheckById() {
		return changeCheckById;
	}
	public void setChangeCheckById(String changeCheckById) {
		this.changeCheckById = changeCheckById;
	}
	public String getChangeInStoresId() {
		return changeInStoresId;
	}
	public void setChangeInStoresId(String changeInStoresId) {
		this.changeInStoresId = changeInStoresId;
	}
	public String getChangeOutStoresId() {
		return changeOutStoresId;
	}
	public void setChangeOutStoresId(String changeOutStoresId) {
		this.changeOutStoresId = changeOutStoresId;
	}
	
	
	
}
