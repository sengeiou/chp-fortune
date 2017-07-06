package com.creditharmony.fortune.common.entity;

import java.util.Date;

import com.creditharmony.core.persistence.DataEntity;

public class Check extends DataEntity<Check>{
    /**
	 * long serialVersionUID 
	 */
	private static final long serialVersionUID = 2033762841544196219L;

	private String applyCode;
	
    private String operator;
                   
    private String operateInfo;

    private Date operateTime;

    private String operateType;

    private String createBy;

    private Date createTime;

    private String modifyBy;

    private Date modifyTime;
    
    private String operateAffiliated;
    
    private String operateNode;

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }

    public String getOperateInfo() {
		return operateInfo;
	}

	public void setOperateInfo(String operateInfo) {
		this.operateInfo = operateInfo;
	}

	public Date getOperateTime() {
		return operateTime;
	}

	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}

	public String getOperateType() {
		return operateType;
	}

	public void setOperateType(String operateType) {
		this.operateType = operateType;
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

	public String getApplyCode() {
		return applyCode;
	}

	public void setApplyCode(String applyCode) {
		this.applyCode = applyCode;
	}

	public String getOperateAffiliated() {
		return operateAffiliated;
	}

	public void setOperateAffiliated(String operateAffiliated) {
		this.operateAffiliated = operateAffiliated;
	}

	public String getOperateNode() {
		return operateNode;
	}

	public void setOperateNode(String operateNode) {
		this.operateNode = operateNode;
	}
	
}