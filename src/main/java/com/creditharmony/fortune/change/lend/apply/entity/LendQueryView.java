package com.creditharmony.fortune.change.lend.apply.entity;

import java.util.Date;
import java.util.List;

import com.creditharmony.core.persistence.DataEntity;
/**
 * 查询条件视图
 * @Class Name LendQueryView
 * @author 刘雄武
 * @Create In 2015年12月14日
 */
public class LendQueryView extends DataEntity<LendQueryView>{

	/**
	 * long serialVersionUID 
	 */
	private static final long serialVersionUID = 1L;
	private String custCode;// 客户编号
	private String custName;// 客户姓名
	private String custState;// 客户状态
	private String custMobilephone;// 客户联系电话（暂定为手机）
	private String managerId;// 理财经理
	private String orgCode;// 营业部
	private String addId;// 城市
	private String applyHostedState;// 托管状态
	private String dictChangeState;// 变更状态 
	private String dictChangeType;// 变更类型
	private String city;// 市
	private String dictDeductStatus;// 划扣申请状态
	private String dictApplyEndState;// 完结状态
	private String applyState;// 出借状态(对应码表出借状态)
	private String managerName;// 理财经理名称
	private String orgName;// 营业部名称
	private Date dictApprovalStartDate; // 审核通过查询初始日期
 	private Date dictApprovalEndDate;// 审核通过查询结束日期
	private List<String> applyIds;// 申请ID数组
	private String accountType;// 标示是金账户类型
	private List<String> orgIds;// 营业部数组
	private String productCode;// 产品编号
	private String applyPay;// 付款方式
	private String lendCode;// 出借编号
	private String roleFlag;// 处理各种角色登录办理按钮显示问题
	
	
	
	public String getRoleFlag() {
		return roleFlag;
	}
	public void setRoleFlag(String roleFlag) {
		this.roleFlag = roleFlag;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getApplyPay() {
		return applyPay;
	}
	public void setApplyPay(String applyPay) {
		this.applyPay = applyPay;
	}
	public String getLendCode() {
		return lendCode;
	}
	public void setLendCode(String lendCode) {
		this.lendCode = lendCode;
	}
	public List<String> getOrgIds() {
		return orgIds;
	}
	public void setOrgIds(List<String> orgIds) {
		this.orgIds = orgIds;
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
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	public String getDictDeductStatus() {
		return dictDeductStatus;
	}
	public void setDictDeductStatus(String dictDeductStatus) {
		this.dictDeductStatus = dictDeductStatus;
	}
	public String getDictApplyEndState() {
		return dictApplyEndState;
	}
	public void setDictApplyEndState(String dictApplyEndState) {
		this.dictApplyEndState = dictApplyEndState;
	}
	public String getApplyState() {
		return applyState;
	}
	public void setApplyState(String applyState) {
		this.applyState = applyState;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
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
	public String getCustState() {
		return custState;
	}
	public void setCustState(String custState) {
		this.custState = custState;
	}
	public String getCustMobilephone() {
		return custMobilephone;
	}
	public void setCustMobilephone(String custMobilephone) {
		this.custMobilephone = custMobilephone;
	}
	public String getManagerId() {
		return managerId;
	}
	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}
	
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public String getAddId() {
			return addId;
	}
	public void setAddId(String addId) {
		this.addId = addId;
	}
	public String getApplyHostedState() {
		return applyHostedState;
	}
	public void setApplyHostedState(String applyHostedState) {
		this.applyHostedState = applyHostedState;
	}
	public String getDictChangeState() {
		return dictChangeState;
	}
	public void setDictChangeState(String dictChangeState) {
		this.dictChangeState = dictChangeState;
	}
	public String getDictChangeType() {
		return dictChangeType;
	}
	public void setDictChangeType(String dictChangeType) {
		this.dictChangeType = dictChangeType;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	
	
}
