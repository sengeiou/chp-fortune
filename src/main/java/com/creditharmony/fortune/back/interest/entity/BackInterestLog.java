package com.creditharmony.fortune.back.interest.entity;

import java.util.Date;

import com.creditharmony.core.persistence.DataEntity;

/**
 * @Class Name BackInterestLog 
 * @author 李志伟
 * @Create In 2015年12月2日
 */
public class BackInterestLog extends DataEntity<BackInterestLog>{
    
	private static final long serialVersionUID = -5299868011856528236L;

    private String backiId;// 回息ID

    private String dictBackiStatus;// 状态

    private String textAre;// 其他原因
    
    private String checkExaminetype;// 审批结果

    private String checkExamine;// 审批意见

    private String checkById;// 审核人ID
    
    private String type;// 操作类型

    private Date checkTime;// 审批时间

    private String applyBy;// 申请人

    private Date applyTime;// 申请时间
    
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBackiId() {
		return backiId;
	}

	public void setBackiId(String backiId) {
		this.backiId = backiId;
	}

	public String getDictBackiStatus() {
		return dictBackiStatus;
	}

	public void setDictBackiStatus(String dictBackiStatus) {
		this.dictBackiStatus = dictBackiStatus;
	}

	public String getCheckExamine() {
		return checkExamine;
	}

	public void setCheckExamine(String checkExamine) {
		this.checkExamine = checkExamine;
	}

	public String getTextAre() {
		return textAre;
	}

	public void setTextAre(String textAre) {
		this.textAre = textAre;
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