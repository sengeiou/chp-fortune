package com.creditharmony.fortune.contract.view;

import java.io.Serializable;
import java.util.Date;

public class ContractChangeParamView implements Serializable{

	private static final long serialVersionUID = 8598345515909614836L;
	
	private String id;		
	private	String contCode;				//合同编号
	private Date changeDay;					//变更日期
	private String dictChangeType;			//变更类型（作废/转借/遗失）
	private String dictContStatus;			//合同状态
	private String dictChangeStatus;		//审核状态
	private String contVersion;				//合同版本
	private String contStoresId;			//门店ID
	private String name;					//机构名
	private String changeInStoresId;		//转入门店ID
	private String changeOutStoresId;		//装出门店ID
  	private Date endDate;   //申请日期
    private Date startDate;
	private int staff; // 1 为综合内勤
	private String dictChangeType2;			//变更类型（作废/转借/遗失）
	private String dictChangeTypeValue;			//变更类型（作废/转借/遗失）
    private String dictChangeStatus2;       // 审核状态2
    
	
	public String getDictChangeStatus2() {
		return dictChangeStatus2;
	}
	public void setDictChangeStatus2(String dictChangeStatus2) {
		this.dictChangeStatus2 = dictChangeStatus2;
	}
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
	public String getDictContStatus() {
		return dictContStatus;
	}
	public void setDictContStatus(String dictContStatus) {
		this.dictContStatus = dictContStatus;
	}
	public String getDictChangeStatus() {
		return dictChangeStatus;
	}
	public void setDictChangeStatus(String dictChangeStatus) {
		this.dictChangeStatus = dictChangeStatus;
	}
	public String getContVersion() {
		return contVersion;
	}
	public void setContVersion(String contVersion) {
		this.contVersion = contVersion;
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
	public int getStaff() {
		return staff;
	}
	public void setStaff(int staff) {
		this.staff = staff;
	}
	public String getDictChangeTypeValue() {
		return dictChangeTypeValue;
	}
	public void setDictChangeTypeValue(String dictChangeTypeValue) {
		this.dictChangeTypeValue = dictChangeTypeValue;
	}
	public String getDictChangeType2() {
		return dictChangeType2;
	}
	public void setDictChangeType2(String dictChangeType2) {
		this.dictChangeType2 = dictChangeType2;
	}
	
    
    
}
