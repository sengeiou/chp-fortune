package com.creditharmony.fortune.back.priority.common.view;

import java.util.Date;

import com.creditharmony.core.persistence.DataEntity;

/**
 * 优先回款审批记录表对应Bean
 * 
 * @Class Name PriorityBackLog
 */
public class PriorityBackLog extends DataEntity<PriorityBackLog> {

	/**
	 * long serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	private String id;
	/**
	 * 优先回款ID
	 */
	private String priorityId;
	/**
	 * 状态
	 */
	private String priorityBackState;
	/**
	 * 审批意见
	 */
	private String checkExamine;
	/**
	 * 审批结果
	 */
	private String checkExaminetype;
	/**
	 * 审核人ID
	 */
	private String checkById;
	/**
	 * 审核时间
	 */
	private Date checkTime;
	/**
	 * 申请人
	 */
	private String applyBy;
	/**
	 * 申请时间
	 */
	private Date applyTime;
	//创建人
	private String  createBy;
	//创建时间
	private Date createTime;
	//最后修改人
	private String modifyBy;
	//最后修改时间
	private Date modifyTime;
	//其他退回原因
	private String checkReason;
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPriorityId() {
		return priorityId;
	}

	public void setPriorityId(String priorityId) {
		this.priorityId = priorityId;
	}

	public String getPriorityBackState() {
		return priorityBackState;
	}

	public void setPriorityBackState(String priorityBackState) {
		this.priorityBackState = priorityBackState;
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

	public String getModifyBy() {
		return modifyBy;
	}

	public void setModifyBy(String modifyBy) {
		this.modifyBy = modifyBy;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getCheckReason() {
		return checkReason;
	}

	public void setCheckReason(String checkReason) {
		this.checkReason = checkReason;
	}

	public String getCheckExamine() {
		return checkExamine;
	}

	public void setCheckExamine(String checkExamine) {
		this.checkExamine = checkExamine;
	}

	public String getCheckExaminetype() {
		return checkExaminetype;
	}

	public void setCheckExaminetype(String checkExaminetype) {
		this.checkExaminetype = checkExaminetype;
	}

	public String getCheckById() {
		return checkById;
	}

	public void setCheckById(String checkById) {
		this.checkById = checkById;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
