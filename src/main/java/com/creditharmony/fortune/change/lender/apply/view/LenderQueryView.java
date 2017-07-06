package com.creditharmony.fortune.change.lender.apply.view;

import java.util.Date;
import java.util.List;

import com.creditharmony.core.persistence.DataEntity;


/**
 * 检索条件view
 * @Class Name LenderQueryView
 * @author 郭才林
 * @Create In 2015年12月10日
 */
public class LenderQueryView extends DataEntity<LenderQueryView>{


	private static final long serialVersionUID = 1L;
	// 客户编号
	private String custCode;
	// 客户名称
	private String custName;
	// 变更类型
	private String dictChangeType;
	// 变更编码
	private String changeCode;
	// 城市
	private String addrCity;
	// 变更状态
	private String dictChangeState;
	// 客户当前状态
	private String dictCustomerState;
	// 理财经理
	private String managerId;
	// 审核通过查询初始日期
	private Date dictApprovalStartDate;
	// 审核通过查询结束日期
	private Date dictApprovalEndDate;
	// 申请ID数组
	private List<String> applyIds;
	// 变更类型(手机号)
	private String changeTypePhone;
	// 变更类型(销户)
	private String changeTypeAccOff;
	// 托管状态
	private String applyHostedStatus;
	// 营业部数组
	private List<String> orgIds;
	// 营业部
	private String orgCode;
	// 营业部名称
	private String orgName;
	// 处理各种角色登录办理按钮显示问题
	private String roleFlag;
	
	
	public String getRoleFlag() {
		return roleFlag;
	}
	public void setRoleFlag(String roleFlag) {
		this.roleFlag = roleFlag;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public List<String> getOrgIds() {
		return orgIds;
	}
	public void setOrgIds(List<String> orgIds) {
		this.orgIds = orgIds;
	}
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public String getApplyHostedStatus() {
		return applyHostedStatus;
	}
	public void setApplyHostedStatus(String applyHostedStatus) {
		this.applyHostedStatus = applyHostedStatus;
	}
	public List<String> getApplyIds() {
		return applyIds;
	}
	public void setApplyIds(List<String> applyIds) {
		this.applyIds = applyIds;
	}
	public Date getDictApprovalStartDate() {
		return dictApprovalStartDate;
	}
	public void setDictApprovalStartDate(Date dictApprovalStartDate) {
		this.dictApprovalStartDate = dictApprovalStartDate;
	}
	public Date getDictApprovalEndDate() {
		return dictApprovalEndDate;
	}
	public void setDictApprovalEndDate(Date dictApprovalEndDate) {
		this.dictApprovalEndDate = dictApprovalEndDate;
	}
	public String getManagerId() {
		return managerId;
	}
	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}
	public String getDictCustomerState() {
		return dictCustomerState;
	}
	public void setDictCustomerState(String dictCustomerState) {
		this.dictCustomerState = dictCustomerState;
	}
	public String getCustCode() {
		return custCode;
	}
	public void setCustCode(String custCode) {
		this.custCode = custCode;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getDictChangeType() {
		return dictChangeType;
	}
	public void setDictChangeType(String dictChangeType) {
		this.dictChangeType = dictChangeType;
	}
	public String getChangeCode() {
		return changeCode;
	}
	public void setChangeCode(String changeCode) {
		this.changeCode = changeCode;
	}
	public String getAddrCity() {
		return addrCity;
	}
	public void setAddrCity(String addrCity) {
		this.addrCity = addrCity;
	}
	public String getDictChangeState() {
		return dictChangeState;
	}
	public void setDictChangeState(String dictChangeState) {
		this.dictChangeState = dictChangeState;
	}
	public String getChangeTypePhone() {
		return changeTypePhone;
	}
	public void setChangeTypePhone(String changeTypePhone) {
		this.changeTypePhone = changeTypePhone;
	}
	public String getChangeTypeAccOff() {
		return changeTypeAccOff;
	}
	public void setChangeTypeAccOff(String changeTypeAccOff) {
		this.changeTypeAccOff = changeTypeAccOff;
	}
	
	


}