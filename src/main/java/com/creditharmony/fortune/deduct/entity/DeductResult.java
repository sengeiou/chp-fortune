package com.creditharmony.fortune.deduct.entity;

import java.util.Date;

import com.creditharmony.core.persistence.DataEntity;

public class DeductResult extends DataEntity<DeductResult>{
	
    /**
	 * long serialVersionUID 
	 */
	private static final long serialVersionUID = -3318275952199740775L;

	private String id;

    private String requestId;
    
    private String lendCode;

    private String thedayId;

    private String deductSucceedMoney;

    private String deductFailMoney;

    private String deductTime;

    private String confirmOpinion;

    private String deductResultCode;

    private String deductSysIdType;

    private String plateformId;

    private String undeductMoney;

    private Integer times;

    private String failFlag;

    private String remark;

    private String remark1;

    private String remark2;

    private String remark3;

    private String createBy;

    private Date createTime;

    private String modifyBy;

    private Date modifyTime;

    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public String getLendCode() {
        return lendCode;
    }

    public void setLendCode(String lendCode) {
        this.lendCode = lendCode == null ? null : lendCode.trim();
    }

    public String getThedayId() {
        return thedayId;
    }

    public void setThedayId(String thedayId) {
        this.thedayId = thedayId == null ? null : thedayId.trim();
    }

    public String getDeductSucceedMoney() {
        return deductSucceedMoney;
    }

    public void setDeductSucceedMoney(String deductSucceedMoney) {
        this.deductSucceedMoney = deductSucceedMoney;
    }

    public String getDeductFailMoney() {
        return deductFailMoney;
    }

    public void setDeductFailMoney(String deductFailMoney) {
        this.deductFailMoney = deductFailMoney;
    }

    public String getDeductTime() {
        return deductTime;
    }

    public void setDeductTime(String deductTime) {
        this.deductTime = deductTime;
    }

    public String getConfirmOpinion() {
        return confirmOpinion;
    }

    public void setConfirmOpinion(String confirmOpinion) {
        this.confirmOpinion = confirmOpinion == null ? null : confirmOpinion.trim();
    }

    public String getDeductResultCode() {
        return deductResultCode;
    }

    public void setDeductResultCode(String deductResultCode) {
        this.deductResultCode = deductResultCode == null ? null : deductResultCode.trim();
    }

    public String getDeductSysIdType() {
        return deductSysIdType;
    }

    public void setDeductSysIdType(String deductSysIdType) {
        this.deductSysIdType = deductSysIdType == null ? null : deductSysIdType.trim();
    }

    public String getPlateformId() {
        return plateformId;
    }

    public void setPlateformId(String plateformId) {
        this.plateformId = plateformId == null ? null : plateformId.trim();
    }

    public String getUndeductMoney() {
        return undeductMoney;
    }

    public void setUndeductMoney(String undeductMoney) {
        this.undeductMoney = undeductMoney;
    }

    public Integer getTimes() {
        return times;
    }

    public void setTimes(Integer times) {
        this.times = times;
    }

    public String getFailFlag() {
        return failFlag;
    }

    public void setFailFlag(String failFlag) {
        this.failFlag = failFlag == null ? null : failFlag.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getRemark1() {
        return remark1;
    }

    public void setRemark1(String remark1) {
        this.remark1 = remark1 == null ? null : remark1.trim();
    }

    public String getRemark2() {
        return remark2;
    }

    public void setRemark2(String remark2) {
        this.remark2 = remark2 == null ? null : remark2.trim();
    }

    public String getRemark3() {
        return remark3;
    }

    public void setRemark3(String remark3) {
        this.remark3 = remark3 == null ? null : remark3.trim();
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
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
        this.modifyBy = modifyBy == null ? null : modifyBy.trim();
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

	@Override
	public String toString() {
		return "DeductResult [id=" + id + ", requestId=" + requestId
				+ ", lendCode=" + lendCode + ", thedayId=" + thedayId
				+ ", deductSucceedMoney=" + deductSucceedMoney
				+ ", deductFailMoney=" + deductFailMoney + ", deductTime="
				+ deductTime + ", confirmOpinion=" + confirmOpinion
				+ ", deductResultCode=" + deductResultCode
				+ ", deductSysIdType=" + deductSysIdType + ", plateformId="
				+ plateformId + ", undeductMoney=" + undeductMoney + ", times="
				+ times + ", failFlag=" + failFlag + ", remark=" + remark
				+ ", remark1=" + remark1 + ", remark2=" + remark2
				+ ", remark3=" + remark3 + ", createBy=" + createBy
				+ ", createTime=" + createTime + ", modifyBy=" + modifyBy
				+ ", modifyTime=" + modifyTime + "]";
	}
    
}