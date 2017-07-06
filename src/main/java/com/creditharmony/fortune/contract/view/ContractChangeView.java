package com.creditharmony.fortune.contract.view;

import java.io.Serializable;
import java.util.Date;

public class ContractChangeView implements Serializable  {

	private static final long serialVersionUID = 8543510691713111509L;
	
	private String id;						//ID
	private	String contCode;				//合同编号
	private Date changeDay;					//变更日期
	private String dictChangeType;			//变更类型（作废/转借/遗失）
	private String changeExplain;			//申请说明
	private String changeApply;				//申请人
	private Date changeCheckDate;			//审核日期
	private String dictChangeStatus;		//审核状态
	private String changeCheckDesc;			//审核意见
	private String changeCheckResult;		//审核结果 0 通过 1 拒绝
	private String changeCheckById;			//审核人ID
	private String changeInStoresId;		//转入门店ID
    private String changeInName;
	private String changeOutStoresId;		//装出门店ID
	private String changeOutName;
	private String contStoresId;			//门店ID
	private String name;					//机构名
	private String contVersion;				//合同版本
	private String dictContStatus;			//合同状态
	private Date contUseDay;				//使用日期
	private String dictContBelong;			//合同归属
	private String dictContFileStatus;		//归档状态
	private Date contIncomeDay;				//入库日期
	private String contBelongEmpid;			//合同所归属理财经理ID
	private String contFilepath;			//文件路径
	private Date distDate;					//派发日期
	private Date applyDay;					//合同申请日期
	private Integer applyNo;				//申请数量
	private String applyTel;				//联系电话
	private String applyBy;					//申请人
	private String applyContacts;			//联系人
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getContCode() {
		return contCode;
	}
	public void setContCode(String contCode) {
		this.contCode = contCode;
	}
	public Date getChangeDay() {
		return changeDay;
	}
	public void setChangeDay(Date changeDay) {
		this.changeDay = changeDay;
	}
	public String getDictChangeType() {
		return dictChangeType;
	}
	public void setDictChangeType(String dictChangeType) {
		this.dictChangeType = dictChangeType;
	}
	public String getChangeExplain() {
		return changeExplain;
	}
	public void setChangeExplain(String changeExplain) {
		this.changeExplain = changeExplain;
	}
	public String getChangeApply() {
		return changeApply;
	}
	public void setChangeApply(String changeApply) {
		this.changeApply = changeApply;
	}
	public Date getChangeCheckDate() {
		return changeCheckDate;
	}
	public void setChangeCheckDate(Date changeCheckDate) {
		this.changeCheckDate = changeCheckDate;
	}
	public String getDictChangeStatus() {
		return dictChangeStatus;
	}
	public void setDictChangeStatus(String dictChangeStatus) {
		this.dictChangeStatus = dictChangeStatus;
	}
	public String getChangeCheckDesc() {
		return changeCheckDesc;
	}
	public void setChangeCheckDesc(String changeCheckDesc) {
		this.changeCheckDesc = changeCheckDesc;
	}
	public String getChangeCheckResult() {
		return changeCheckResult;
	}
	public void setChangeCheckResult(String changeCheckResult) {
		this.changeCheckResult = changeCheckResult;
	}
	public String getChangeCheckById() {
		return changeCheckById;
	}
	public void setChangeCheckById(String changeCheckById) {
		this.changeCheckById = changeCheckById;
	}
	public String getChangeInStoresId() {
		return changeInStoresId;
	}
	public void setChangeInStoresId(String changeInStoresId) {
		this.changeInStoresId = changeInStoresId;
	}
	public String getChangeOutStoresId() {
		return changeOutStoresId;
	}
	public void setChangeOutStoresId(String changeOutStoresId) {
		this.changeOutStoresId = changeOutStoresId;
	}
	public String getContStoresId() {
		return contStoresId;
	}
	public void setContStoresId(String contStoresId) {
		this.contStoresId = contStoresId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContVersion() {
		return contVersion;
	}
	public void setContVersion(String contVersion) {
		this.contVersion = contVersion;
	}
	public String getDictContStatus() {
		return dictContStatus;
	}
	public void setDictContStatus(String dictContStatus) {
		this.dictContStatus = dictContStatus;
	}
	public Date getContUseDay() {
		return contUseDay;
	}
	public void setContUseDay(Date contUseDay) {
		this.contUseDay = contUseDay;
	}
	public String getDictContBelong() {
		return dictContBelong;
	}
	public void setDictContBelong(String dictContBelong) {
		this.dictContBelong = dictContBelong;
	}
	public String getDictContFileStatus() {
		return dictContFileStatus;
	}
	public void setDictContFileStatus(String dictContFileStatus) {
		this.dictContFileStatus = dictContFileStatus;
	}
	public Date getContIncomeDay() {
		return contIncomeDay;
	}
	public void setContIncomeDay(Date contIncomeDay) {
		this.contIncomeDay = contIncomeDay;
	}
	public String getContBelongEmpid() {
		return contBelongEmpid;
	}
	public void setContBelongEmpid(String contBelongEmpid) {
		this.contBelongEmpid = contBelongEmpid;
	}
	public String getContFilepath() {
		return contFilepath;
	}
	public void setContFilepath(String contFilepath) {
		this.contFilepath = contFilepath;
	}
	public Date getDistDate() {
		return distDate;
	}
	public void setDistDate(Date distDate) {
		this.distDate = distDate;
	}
	public Date getApplyDay() {
		return applyDay;
	}
	public void setApplyDay(Date applyDay) {
		this.applyDay = applyDay;
	}
	public Integer getApplyNo() {
		return applyNo;
	}
	public void setApplyNo(Integer applyNo) {
		this.applyNo = applyNo;
	}
	public String getApplyTel() {
		return applyTel;
	}
	public void setApplyTel(String applyTel) {
		this.applyTel = applyTel;
	}
	public String getApplyBy() {
		return applyBy;
	}
	public void setApplyBy(String applyBy) {
		this.applyBy = applyBy;
	}
	public String getApplyContacts() {
		return applyContacts;
	}
	public void setApplyContacts(String applyContacts) {
		this.applyContacts = applyContacts;
	}
	public String getChangeInName() {
		return changeInName;
	}
	public void setChangeInName(String changeInName) {
		this.changeInName = changeInName;
	}
	public String getChangeOutName() {
		return changeOutName;
	}
	public void setChangeOutName(String changeOutName) {
		this.changeOutName = changeOutName;
	}
	

}
