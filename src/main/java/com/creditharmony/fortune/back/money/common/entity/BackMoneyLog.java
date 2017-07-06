package com.creditharmony.fortune.back.money.common.entity;

import java.util.Date;

import com.creditharmony.core.persistence.DataEntity;

/**
 * 回款审批记录表对应Bean
 * 
 * @Class Name BackMoneyLog
 * @author 陈广鹏
 * @Create In 2015年12月2日
 */
public class BackMoneyLog extends DataEntity<BackMoneyLog> {

	/**
	 * long serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 回款ID
	 */
	private String backmId;
	/**
	 * 状态
	 */
	private String dictBackmStatus;
	/**
	 * 其它审批意见
	 */
	private String checkReason;
	/**
	 * 审批意见
	 */
	private String checkExamine;
	/**
	 * 审批结果
	 */
	private String checkExaminetype;//
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

	public String getBackmId() {
		return backmId;
	}

	public void setBackmId(String backmId) {
		this.backmId = backmId;
	}

	public String getDictBackmStatus() {
		return dictBackmStatus;
	}

	public void setDictBackmStatus(String dictBackmStatus) {
		this.dictBackmStatus = dictBackmStatus;
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
