package com.creditharmony.fortune.redemption.common.entity;

import java.util.Date;

import com.creditharmony.core.persistence.DataEntity;

/**
 * 描述：赎回审批记录Bean
 * @Class Name RedemptionLog
 * @author 陈广鹏
 * @Create In 2015年11月30日
 */
public class RedemptionLog extends DataEntity<RedemptionLog> {

	/**
	 * long serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	private String applyId;				// 业务编号
	private String redemptionId;		// 赎回申请ID
	private String dictRedemptionStatus;// 审批状态
	private String checkExamine;		// 审批意见
	private String checkExaminetype;	// 审批结果
	private String checkById;			// 审核人ID
	private Date checkTime;				// 审批时间
	private String applyBy;				// 申请人
	private Date applyTime;				// 申请时间

	public String getApplyId() {
		return applyId;
	}

	public void setApplyId(String applyId) {
		this.applyId = applyId;
	}

	public String getRedemptionId() {
		return redemptionId;
	}

	public void setRedemptionId(String redemptionId) {
		this.redemptionId = redemptionId;
	}

	public String getDictRedemptionStatus() {
		return dictRedemptionStatus;
	}

	public void setDictRedemptionStatus(String dictRedemptionStatus) {
		this.dictRedemptionStatus = dictRedemptionStatus;
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

}
