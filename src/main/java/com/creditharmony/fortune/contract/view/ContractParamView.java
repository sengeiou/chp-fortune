package com.creditharmony.fortune.contract.view;

import java.io.Serializable;
import java.util.Date;

/**
 * 合同查询参数Bean
 * @author wangpengfei
 *
 */
public class ContractParamView implements Serializable  {
	
	private static final long serialVersionUID = -7675730231160529652L;
	
	private String contCode;				//合同编号
	private String contStoresId;			//门店ID
	private String contVersion;				//合同版本
	private Date contUseDay;				//使用日期
	private Date contBestorageDay;			//分配日期
	private Date contTranferDay;			//调拨日期
	private Date contIncomeDay;				//入库(统计)日期
	private String contSignStatus;			//签收状态(0 未签收  1 已签收)
	private String dictContStatus;			//合同状态（出借/作废）
	private String dictContFileStatus;		//归档状态（已归档/未归档）
	private String contFileTime;			//归档时间
	private String dictContSource;			//来源（门店转借-门店ID/总部下发）
	private String dictContBelong;			//合同归属（门店库存 1 理财经理 2）
	private String contBelongEmpid;			//合同所归属理财经理ID
	private String name;					//机构名
	private String nameId;					//机构id
	
	private String changeApply;				//变更是申请人
	private String dictChangeType;			//变更类型
	private String dictChangeStatus;		//变更审核状态
	private String changeInStoresId;		//转入门店ID
	private String changeExplain;			//申请说明
	private String createBy;				  //创建人
	private Date createTime;				  //创建时间
	private String modifyBy;				  //最后修改人
	private Date modify_time;				  //最后修改时间
	private String contFilepath;			//文件路径
	private int staff; // 1 为综合内勤
	private Date contUseDayEnd; 
	private String q;// 是否切换之前的查询条件
	
	private Date contApplyDayStart; 	//生成时间查询区间开始
	private Date contApplyDayEnd;	//生成时间查询区间截止
	private String contractType; // 合同类型
	
	
	public Date getContApplyDayStart() {
		return contApplyDayStart;
	}
	public void setContApplyDayStart(Date contApplyDayStart) {
		this.contApplyDayStart = contApplyDayStart;
	}
	public Date getContApplyDayEnd() {
		return contApplyDayEnd;
	}
	public void setContApplyDayEnd(Date contApplyDayEnd) {
		this.contApplyDayEnd = contApplyDayEnd;
	}
	public String getNameId() {
		return nameId;
	}
	public void setNameId(String nameId) {
		this.nameId = nameId;
	}
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
	public String getDictChangeStatus() {
		return dictChangeStatus;
	}
	public void setDictChangeStatus(String dictChangeStatus) {
		this.dictChangeStatus = dictChangeStatus;
	}
	public int getStaff() {
		return staff;
	}
	public void setStaff(int staff) {
		this.staff = staff;
	}
	public Date getContUseDayEnd() {
		return contUseDayEnd;
	}
	public void setContUseDayEnd(Date contUseDayEnd) {
		this.contUseDayEnd = contUseDayEnd;
	}
	public String getQ() {
		return q;
	}
	public void setQ(String q) {
		this.q = q;
	}
	public String getContractType() {
		return contractType;
	}
	public void setContractType(String contractType) {
		this.contractType = contractType;
	}

	
}
