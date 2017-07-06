package com.creditharmony.fortune.contract.entity;

import java.util.Date;

import com.creditharmony.core.persistence.DataEntity;

/** 
 * 合同派发
 * @Class Name ContractDistribute
 * @Author lifang
 * @Create 2015年11月27日
 */
public class ContractDistribute extends DataEntity<ContractDistribute> {
	
	private static final long serialVersionUID = 3883177502291389561L;
	private String id;					//主键ID
	private String contractId;			//申请ID
	private String contStoresId;		//门店ID
	private String distStartNo;			//起始编号
	private String distExpressNo;		//物流编号
	private String contVersion;			//合同版本
	private String distEndNo;			//终止编号
	private Integer distBox;			//合同箱数
	private Integer distContractNo;		//派发套数
	private Date distDate;				//派发日期
	private String distStatus;			//派发状态（已派发--部分派发）
	private String signStatus;			//签收状态（已签收、未签收）
	private String orgName;				//机构名
	private Integer applyNo;				//申请数量
	private Date applyDay;				//申请日期
	private String assignedId;          //派发专员ID
	private String distType;          //主派发记录1，子派发记录 2 
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getContractId() {
		return contractId;
	}
	public void setContractId(String contractId) {
		this.contractId = contractId;
	}
	public String getContStoresId() {
		return contStoresId;
	}
	public void setContStoresId(String contStoresId) {
		this.contStoresId = contStoresId;
	}
	public String getDistStartNo() {
		return distStartNo;
	}
	public void setDistStartNo(String distStartNo) {
		this.distStartNo = distStartNo;
	}
	public String getDistExpressNo() {
		return distExpressNo;
	}
	public void setDistExpressNo(String distExpressNo) {
		this.distExpressNo = distExpressNo;
	}
	public String getContVersion() {
		return contVersion;
	}
	public void setContVersion(String contVersion) {
		this.contVersion = contVersion;
	}
	public String getDistEndNo() {
		return distEndNo;
	}
	public void setDistEndNo(String distEndNo) {
		this.distEndNo = distEndNo;
	}
	public Integer getDistBox() {
		return distBox;
	}
	public void setDistBox(Integer distBox) {
		this.distBox = distBox;
	}
	public Integer getDistContractNo() {
		return distContractNo;
	}
	public void setDistContractNo(Integer distContractNo) {
		this.distContractNo = distContractNo;
	}
	public Date getDistDate() {
		return distDate;
	}
	public void setDistDate(Date distDate) {
		this.distDate = distDate;
	}
	public String getDistStatus() {
		return distStatus;
	}
	public void setDistStatus(String distStatus) {
		this.distStatus = distStatus;
	}
	public String getSignStatus() {
		return signStatus;
	}
	public void setSignStatus(String signStatus) {
		this.signStatus = signStatus;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public Integer getApplyNo() {
		return applyNo;
	}
	public void setApplyNo(Integer applyNo) {
		this.applyNo = applyNo;
	}
	public Date getApplyDay() {
		return applyDay;
	}
	public void setApplyDay(Date applyDay) {
		this.applyDay = applyDay;
	}
	public String getAssignedId() {
		return assignedId;
	}
	public void setAssignedId(String assignedId) {
		this.assignedId = assignedId;
	}
	public String getDistType() {
		return distType;
	}
	public void setDistType(String distType) {
		this.distType = distType;
	}
	
	
	
}
