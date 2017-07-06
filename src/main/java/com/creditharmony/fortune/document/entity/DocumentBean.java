package com.creditharmony.fortune.document.entity;

import java.util.Date;

/**
 * @Class Name DocumentBean
 * @author 胡体勇
 * @Create In 2016年3月14日
 */
public class DocumentBean {
	
	private String id; // 主键
    private String businessType; //业务类型1-财富 
    private String batchNo; // 客户编码
    private String subType; // 出借编码或者借款编码
    private String result; // 出借编码或者借款编码
    private String createBy; // 创建人
    private Date createTime; // 创建时间
    private String modifyBy; // 修改人
    private Date modifyTime; // 修改时间
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBusinessType() {
		return businessType;
	}
	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}
	public String getBatchNo() {
		return batchNo;
	}
	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
	public String getSubType() {
		return subType;
	}
	public void setSubType(String subType) {
		this.subType = subType;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
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
