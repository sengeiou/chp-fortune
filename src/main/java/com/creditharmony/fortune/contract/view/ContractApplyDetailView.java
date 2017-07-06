package com.creditharmony.fortune.contract.view;

import java.io.Serializable;
import java.util.Date;

/**
 * 合同申请详情页面 Bean
 * 2015年12月25日
 * @author 王鹏飞
 */
public class ContractApplyDetailView implements Serializable {

	private static final long serialVersionUID = 9157044434668565387L;
	
	private String contractId;						//申请ID
	private String contStoresId;					//门店ID
	private String orgName;                         //门店名称
	private int applyAlreadyuse;				//上月使用
	private int applyInventory;					//现有库存
	private int applyNo;						//申请数量
	private int distContractNo;                 //派发数量
	private String contVersion;						//合同版本
	private Date applyDay;							//申请日期
	private Date applyCheckDate;                   //审核日期
    private Date distDate;                         //派发日期
	private String applyStatus;						//审核状态
	
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
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public int getApplyAlreadyuse() {
		return applyAlreadyuse;
	}
	public void setApplyAlreadyuse(int applyAlreadyuse) {
		this.applyAlreadyuse = applyAlreadyuse;
	}
	public int getApplyInventory() {
		return applyInventory;
	}
	public void setApplyInventory(int applyInventory) {
		this.applyInventory = applyInventory;
	}
	public int getApplyNo() {
		return applyNo;
	}
	public void setApplyNo(int applyNo) {
		this.applyNo = applyNo;
	}
	public int getDistContractNo() {
		return distContractNo;
	}
	public void setDistContractNo(int distContractNo) {
		this.distContractNo = distContractNo;
	}
	public String getContVersion() {
		return contVersion;
	}
	public void setContVersion(String contVersion) {
		this.contVersion = contVersion;
	}
	public Date getApplyDay() {
		return applyDay;
	}
	public void setApplyDay(Date applyDay) {
		this.applyDay = applyDay;
	}
	public Date getApplyCheckDate() {
		return applyCheckDate;
	}
	public void setApplyCheckDate(Date applyCheckDate) {
		this.applyCheckDate = applyCheckDate;
	}
	public Date getDistDate() {
		return distDate;
	}
	public void setDistDate(Date distDate) {
		this.distDate = distDate;
	}
	public String getApplyStatus() {
		return applyStatus;
	}
	public void setApplyStatus(String applyStatus) {
		this.applyStatus = applyStatus;
	}

}
