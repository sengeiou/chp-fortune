
package com.creditharmony.fortune.triple.system.entity;

import java.util.Date;

import com.creditharmony.core.persistence.DataEntity;

/**
 * 三网组织机构
 * @Class Name IntOrg
 * @author 胡体勇
 * @Create In 2016年2月25日
 */
public class IntOrgBean extends DataEntity<IntOrgBean>{

	/**
	 * long serialVersionUID 
	 */
	private static final long serialVersionUID = 1L;
	private String id;// 主键
	private String parentId;//父ID
	private String orgId;// 机构id
	private String orgName;//机构名
	private String status;//状态（0可用，1不可用）
	private String provinceId;//省份ID
	private String provinceName;// 省份名称
	private String cityId;//城市ID
	private String cityName;// 城市名称
	private String description;//描述
	private String orgType;//组织机构类型
	private String orgCode;//组织机构编号
	private String orgAddr;// 门店地址
	private String contracts;//联系人
	private String contractNum;//联系电话
	private Date lastModifyTime;// 最后修改时间
	private String sendType;// 发送类型
	private String sendStatus; // 发送状态（0  发送成功 1待发送）
	private Date sendTime;// 发送时间
	private String operation;// 操作类型
	private String uniqueNum;// 发送消息唯一编号
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}
	public String getProvinceName() {
		return provinceName;
	}
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	public String getCityId() {
		return cityId;
	}
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getOrgType() {
		return orgType;
	}
	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public String getOrgAddr() {
		return orgAddr;
	}
	public void setOrgAddr(String orgAddr) {
		this.orgAddr = orgAddr;
	}
	public String getContracts() {
		return contracts;
	}
	public void setContracts(String contracts) {
		this.contracts = contracts;
	}
	public String getContractNum() {
		return contractNum;
	}
	public void setContractNum(String contractNum) {
		this.contractNum = contractNum;
	}
	public Date getLastModifyTime() {
		return lastModifyTime;
	}
	public void setLastModifyTime(Date lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}
	public String getSendStatus() {
		return sendStatus;
	}
	public void setSendStatus(String sendStatus) {
		this.sendStatus = sendStatus;
	}
	public String getSendType() {
		return sendType;
	}
	public void setSendType(String sendType) {
		this.sendType = sendType;
	}
	public Date getSendTime() {
		return sendTime;
	}
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public String getUniqueNum() {
		return uniqueNum;
	}
	public void setUniqueNum(String uniqueNum) {
		this.uniqueNum = uniqueNum;
	}
}
