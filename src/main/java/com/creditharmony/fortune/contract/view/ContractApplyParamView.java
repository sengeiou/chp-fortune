package com.creditharmony.fortune.contract.view;

import java.io.Serializable;
import java.util.Date;

/**
 * 合同申请审查询参数Bean
 * 2015年12月22日
 * @author 王鹏飞
 */
public class ContractApplyParamView implements Serializable  {

	private static final long serialVersionUID = 1L;

	private String contractId;						//申请ID
	private String contStoresId;					//门店ID
    private String contVersion;						//合同版本
    private String applyStatus;						//审核状态
    private Integer applyAlreadyuse;				//上月使用
    private Integer applyInventory;					//现有库存
    private Integer applyNo;						//申请数量
    private Date applyDay;							//申请日期
    private Integer distContractNo;                 //派发数量
    private String orgName;                         //门店名称
    private String dictApplyRefuseDemo;              //批量驳回理由
  	private Date endDate;   //申请日期
    private Date startDate;  
    private Date startCheckDate; //审核日期
    private Date endCheckDate;
    private Date startDistDate;  //派发日期
    private Date endDistDate;
    private String distType;          //主派发记录1，子派发记录 2 
	private int staff; // 1 为综合内勤
    
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
	public String getContVersion() {
		return contVersion;
	}
	public void setContVersion(String contVersion) {
		this.contVersion = contVersion;
	}
	public String getApplyStatus() {
		return applyStatus;
	}
	public void setApplyStatus(String applyStatus) {
		this.applyStatus = applyStatus;
	}
	public Integer getApplyAlreadyuse() {
		return applyAlreadyuse;
	}
	public void setApplyAlreadyuse(Integer applyAlreadyuse) {
		this.applyAlreadyuse = applyAlreadyuse;
	}
	public Integer getApplyInventory() {
		return applyInventory;
	}
	public void setApplyInventory(Integer applyInventory) {
		this.applyInventory = applyInventory;
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
	public Integer getDistContractNo() {
		return distContractNo;
	}
	public void setDistContractNo(Integer distContractNo) {
		this.distContractNo = distContractNo;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public String getDictApplyRefuseDemo() {
		return dictApplyRefuseDemo;
	}
	public void setDictApplyRefuseDemo(String dictApplyRefuseDemo) {
		this.dictApplyRefuseDemo = dictApplyRefuseDemo;
	}
	public Date getStartCheckDate() {
		return startCheckDate;
	}
	public void setStartCheckDate(Date startCheckDate) {
		this.startCheckDate = startCheckDate;
	}
	public Date getEndCheckDate() {
		return endCheckDate;
	}
	public void setEndCheckDate(Date endCheckDate) {
		this.endCheckDate = endCheckDate;
	}
	public Date getStartDistDate() {
		return startDistDate;
	}
	public void setStartDistDate(Date startDistDate) {
		this.startDistDate = startDistDate;
	}
	public Date getEndDistDate() {
		return endDistDate;
	}
	public void setEndDistDate(Date endDistDate) {
		this.endDistDate = endDistDate;
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
