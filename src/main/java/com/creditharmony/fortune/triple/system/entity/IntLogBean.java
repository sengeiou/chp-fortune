package com.creditharmony.fortune.triple.system.entity;

import java.util.Date;

import com.creditharmony.core.persistence.DataEntity;

/**
 * @Class Name IntLogBean
 * @author 胡体勇
 * @Create In 2016年3月3日
 */
public class IntLogBean  extends DataEntity<IntLogBean>{

	/**
	 * long serialVersionUID 
	 */
	private static final long serialVersionUID = 1L;
    private String id;
    private String typeId;
    private String content;
    private String opType;
    private Date createTime;
    private String uniqueNum;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTypeId() {
		return typeId;
	}
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getOpType() {
		return opType;
	}
	public void setOpType(String opType) {
		this.opType = opType;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getUniqueNum() {
		return uniqueNum;
	}
	public void setUniqueNum(String uniqueNum) {
		this.uniqueNum = uniqueNum;
	}
}
