package com.creditharmony.fortune.contract.entity;

import java.util.Date;

import com.creditharmony.core.persistence.DataEntity;


public class Contract extends DataEntity<Contract>{

	private static final long serialVersionUID = 8588605016678462375L;
	
	private String contCode; 				  		  //合同编号
	private String contStoresId;				  		//门店ID
	private String contVersion;				  		//合同版本
	private Date contUseDay;				  		//使用日期
	private Date contBestorageDay;				  		//分配日期
	private Date contTranferDay;				  		//调拨日期
	private Date contIncomeDay;				  		//入库日期
	private String contSignStatus;				  		//签收状态
	private String dictContStatus;				  		//合同状态
	private String dictContFileStatus;				  		// 归档状态
	private Date contFileTime;				  		// 归档日期
	private String dictContSource;				  		//来源
	private String dictContBelong;				  		//合同归属
	private String contBelongEmpid;				  		// 合同所归属理财经理ID
	private String contFilepath;				  		//文件路径
	private String applyContractId;          //申请id
	private String disContractId;     //派发id
	private Date onfileDay;    //归档日期
	private Date transferDay;  //转让日期
	private Date invalidDay;  //无效，作废日期
	private String lendCode;// 出借编号
	private Date applyDay;// 申请日期/生成日期
	private String contractType; //合同/协议标识 1合同 2协议
	public String getContCode() {
		return contCode;
	}
	public void setContCode(String contCode) {
		this.contCode = contCode;
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
	public Date getContUseDay() {
		return contUseDay;
	}
	public void setContUseDay(Date contUseDay) {
		this.contUseDay = contUseDay;
	}
	public Date getContBestorageDay() {
		return contBestorageDay;
	}
	public void setContBestorageDay(Date contBestorageDay) {
		this.contBestorageDay = contBestorageDay;
	}
	public Date getContTranferDay() {
		return contTranferDay;
	}
	public void setContTranferDay(Date contTranferDay) {
		this.contTranferDay = contTranferDay;
	}
	public Date getContIncomeDay() {
		return contIncomeDay;
	}
	public void setContIncomeDay(Date contIncomeDay) {
		this.contIncomeDay = contIncomeDay;
	}
	public String getContSignStatus() {
		return contSignStatus;
	}
	public void setContSignStatus(String contSignStatus) {
		this.contSignStatus = contSignStatus;
	}
	public String getDictContStatus() {
		return dictContStatus;
	}
	public void setDictContStatus(String dictContStatus) {
		this.dictContStatus = dictContStatus;
	}
	public String getDictContFileStatus() {
		return dictContFileStatus;
	}
	public void setDictContFileStatus(String dictContFileStatus) {
		this.dictContFileStatus = dictContFileStatus;
	}
	public Date getContFileTime() {
		return contFileTime;
	}
	public void setContFileTime(Date contFileTime) {
		this.contFileTime = contFileTime;
	}
	public String getDictContSource() {
		return dictContSource;
	}
	public void setDictContSource(String dictContSource) {
		this.dictContSource = dictContSource;
	}
	public String getDictContBelong() {
		return dictContBelong;
	}
	public void setDictContBelong(String dictContBelong) {
		this.dictContBelong = dictContBelong;
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
	public String getApplyContractId() {
		return applyContractId;
	}
	public void setApplyContractId(String applyContractId) {
		this.applyContractId = applyContractId;
	}
	public String getDisContractId() {
		return disContractId;
	}
	public void setDisContractId(String disContractId) {
		this.disContractId = disContractId;
	}
	public Date getInvalidDay() {
		return invalidDay;
	}
	public void setInvalidDay(Date invalidDay) {
		this.invalidDay = invalidDay;
	}
	public Date getOnfileDay() {
		return onfileDay;
	}
	public void setOnfileDay(Date onfileDay) {
		this.onfileDay = onfileDay;
	}
	public Date getTransferDay() {
		return transferDay;
	}
	public void setTransferDay(Date transferDay) {
		this.transferDay = transferDay;
	}
	public String getLendCode() {
		return lendCode;
	}
	public void setLendCode(String lendCode) {
		this.lendCode = lendCode;
	}
	public Date getApplyDay() {
		return applyDay;
	}
	public void setApplyDay(Date applyDay) {
		this.applyDay = applyDay;
	}
	public String getContractType() {
		return contractType;
	}
	public void setContractType(String contractType) {
		this.contractType = contractType;
	}

	
}