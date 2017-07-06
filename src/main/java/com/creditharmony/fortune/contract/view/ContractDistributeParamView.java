package com.creditharmony.fortune.contract.view;

import java.io.Serializable;
import java.util.Date;

/** 
 * 合同派发参数视图
 * @Class Name ContractDistributeView
 * @Author 王鹏飞
 * @Create 2015年12月31日
 */
public class ContractDistributeParamView implements Serializable   {
	private static final long serialVersionUID = -3238919031408700016L;
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
	private String applyNo;				//申请数量
	private Date applyDay;				//申请日期
	private String createBy;			//创建人
	private Date createTime;		    //创建时间
	private String modifyBy;			//最后修改人
	private Date modify_time;			//最后修改时间
	private String assignedId;          //派发专员ID
	private String distType;          //主派发记录1，子派发记录 2 
	private int staff; // 1 为综合内勤
	
	
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
	public String getApplyNo() {
		return applyNo;
	}
	public void setApplyNo(String applyNo) {
		this.applyNo = applyNo;
	}
	public Date getApplyDay() {
		return applyDay;
	}
	public void setApplyDay(Date applyDay) {
		this.applyDay = applyDay;
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
	public Date getModify_time() {
		return modify_time;
	}
	public void setModify_time(Date modify_time) {
		this.modify_time = modify_time;
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
	public int getStaff() {
		return staff;
	}
	public void setStaff(int staff) {
		this.staff = staff;
	}
	
}
