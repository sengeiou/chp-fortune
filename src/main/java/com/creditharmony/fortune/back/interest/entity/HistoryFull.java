package com.creditharmony.fortune.back.interest.entity;

import java.io.Serializable;
import java.util.Date;

import com.creditharmony.core.persistence.DataEntity;

/**
 * 历史留痕
 * @Class Name HistoryFull 
 * @author 李志伟
 * @Create In 2016年1月21日
 */
public class HistoryFull extends DataEntity<HistoryFull> implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// 出借编号
	private String applyCode;
	
	// 操作人
    private String operator;
            
    // 操作内容
    private String operateInfo;
    
    // 操作时间
    private Date operateTime;
    
    // 操作类型
    private String operateType;
    
    // 创建人
    private String createBy;
    
    // 创建时间
    private Date createTime;
    
    // 修改人
    private String modifyBy;
    
    // 修改时间
    private Date modifyTime;

	public String getApplyCode() {
		return applyCode;
	}

	public void setApplyCode(String applyCode) {
		this.applyCode = applyCode;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
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
}