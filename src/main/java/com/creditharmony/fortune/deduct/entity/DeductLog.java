package com.creditharmony.fortune.deduct.entity;

import java.util.Date;

import com.creditharmony.core.persistence.DataEntity;

public class DeductLog extends DataEntity<DeductLog> {
    /**
	 * long serialVersionUID 
	 */
	private static final long serialVersionUID = 7057168897956734093L;

    private String deductApplyId;

    private String dictDeductStatus;

    private String checkExaminetype;

    private String checkExamine;

    private String checkById;

    private Date checkTime;

    private String applyBy;

    private Date applyTime;

    private String createBy;

    private Date createTime;

    private String modifyBy;

    private Date modifyTime;

    public String getDeductApplyId() {
        return deductApplyId;
    }

    public void setDeductApplyId(String deductApplyId) {
        this.deductApplyId = deductApplyId == null ? null : deductApplyId.trim();
    }

    public String getDictDeductStatus() {
        return dictDeductStatus;
    }

    public void setDictDeductStatus(String dictDeductStatus) {
        this.dictDeductStatus = dictDeductStatus == null ? null : dictDeductStatus.trim();
    }

    public String getCheckExaminetype() {
        return checkExaminetype;
    }

    public void setCheckExaminetype(String checkExaminetype) {
        this.checkExaminetype = checkExaminetype == null ? null : checkExaminetype.trim();
    }

    public String getCheckExamine() {
        return checkExamine;
    }

    public void setCheckExamine(String checkExamine) {
        this.checkExamine = checkExamine == null ? null : checkExamine.trim();
    }

    public String getCheckById() {
        return checkById;
    }

    public void setCheckById(String checkById) {
        this.checkById = checkById == null ? null : checkById.trim();
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
        this.applyBy = applyBy == null ? null : applyBy.trim();
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
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
}