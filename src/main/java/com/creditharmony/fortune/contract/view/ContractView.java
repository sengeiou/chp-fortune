package com.creditharmony.fortune.contract.view;

import java.io.Serializable;
import java.util.Date;

public class ContractView implements Serializable{
	
	private static final long serialVersionUID = -7145210259276892803L;
	
	private String contCode;				//合同编号
	private String contStoresId;			//门店ID
	private String contVersion;				//合同版本
	private Date contUseDay;				//使用日期
	private Date applyDay;                 //申请日期
	private Date contBestorageDay;			//分配日期
	private Date contTranferDay;			//调拨日期
	private Date distDate;                  //派发日期
	private Date contIncomeDay;				//入库(统计)日期
	private String contSignStatus;			//签收状态(0 未签收  1 已签收)
	private String dictContStatus;			//合同状态（出借/作废）
	private String dictContFileStatus;		//归档状态（已归档/未归档）
	private String contFileTime;			//归档时间
	private String dictContSource;			//来源（门店转借-门店ID/总部下发）
	private String dictContBelong;			//合同归属（门店库存 1 理财经理 2）
	private String contBelongEmpid;			//合同所归属理财经理ID
	private String name;					//机构名
	private String changeApply;				//变更是申请人
	private String dictChangeType;			//变更类型
	private String changeInStoresId;		//转入门店ID
	private String changeExplain;			//申请说明
	private String createBy;				  //创建人
	private Date createTime;				  //创建时间
	private String modifyBy;				  //最后修改人
	private Date modify_time;				  //最后修改时间
	private String contFilepath;			//文件路径
	private String userName;               //用户名称	
	
	private String contractType;          //合同/协议标识 1合同 2协议
	
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
	public Date getApplyDay() {
		return applyDay;
	}
	public void setApplyDay(Date applyDay) {
		this.applyDay = applyDay;
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
	public Date getDistDate() {
		return distDate;
	}
	public void setDistDate(Date distDate) {
		this.distDate = distDate;
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
	public String getContFileTime() {
		return contFileTime;
	}
	public void setContFileTime(String contFileTime) {
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getChangeApply() {
		return changeApply;
	}
	public void setChangeApply(String changeApply) {
		this.changeApply = changeApply;
	}
	public String getDictChangeType() {
		return dictChangeType;
	}
	public void setDictChangeType(String dictChangeType) {
		this.dictChangeType = dictChangeType;
	}
	public String getChangeInStoresId() {
		return changeInStoresId;
	}
	public void setChangeInStoresId(String changeInStoresId) {
		this.changeInStoresId = changeInStoresId;
	}
	public String getChangeExplain() {
		return changeExplain;
	}
	public void setChangeExplain(String changeExplain) {
		this.changeExplain = changeExplain;
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
	public String getContFilepath() {
		return contFilepath;
	}
	public void setContFilepath(String contFilepath) {
		this.contFilepath = contFilepath;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getContractType() {
		return contractType;
	}
	public void setContractType(String contractType) {
		this.contractType = contractType;
	}
	
   
}
