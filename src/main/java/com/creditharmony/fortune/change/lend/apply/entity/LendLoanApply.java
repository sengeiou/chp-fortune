package com.creditharmony.fortune.change.lend.apply.entity;

import java.util.Date;

import com.creditharmony.core.persistence.DataEntity;

/**
 * 出借申请实体
 * @Class Name LendLoanApply
 * @author 刘雄武
 * @Create In 2015年11月30日
 */
public class LendLoanApply extends DataEntity<LendLoanApply> {

	/**
	 * long serialVersionUID 
	 */
	private static final long serialVersionUID = 1L;
	private String applyCode;// 出借编号
	private String custCode;// 客户编号
	private String applyHostedState;// 托管状态
	private String applyState;// 出借状态(对应码表出借状态)
	private String applyDate;// 申请日期
	private String applyDeductDate;// 计划划扣日期
	private String applyLendDate;// 计划出借日期
	private String applyPay;// 付款方式
	private String applyAgreemenEdition;// 协议版本
	private String applyLendMoney;// 计划出借金额
	private String productCode;// 产品编号(暂定为出借模式查询出处)
	private String applyContractNo;// 合同编号
	private String custName;// 客户名称
	private String productName;// 产品名称
	private String applySalesDiscount;// 销售折扣率（%）
	private String managerId;// 理财经理
	private String teamEmp;// 团队经理
	private String addrProvince;// 通讯省
	private String addrCity;// 通讯市
	private String orgName;// 营业部
	private String custState;// 客户状态
	private String dictCustSource;// 客户来源
	private String custMobilephone;// 联系电话（暂定为手机）
	private Date createTime;// 创建时间
	private String applyId;// 申请ID
	private String repayId;// 借款银行ID
	private String receivingId;// 收款银行ID
	private String orgCode;// 营业部code
	private String dictChangeState;// 变更状态
	private String changeType;// 变更类型
	private String managerName;// 理财经理名称
	private String teamManagerName;// 团队经理名称
	private String departmentName;// 营业部
	private String teamName;// 团队名称
	private String trusteeshipNo;// 金账户账号
	private String changerTypeName;// 变更类型名称
 	private String changerTypeVal;// 变更类型值
 	private String custCertNum;// 身份证号
 	private String changerState;// 变更状态
 	private Date dictApprovalStartDate; // 审核通过查询初始日期
 	private Date dictApprovalEndDate;// 审核通过查询结束日期
 	private String applyPayName;// 付款方式(显示名称)
 	private String dictApplyEndState;// 完结状态
 	private String accountType;//
 	private String custCertType;
 	private Integer billDay;// 账单日
 	private String receivingIdAfter;// 记录之前的回款银行
 	private String orgIds;// 营业部数组
 	private String applyPayCode;// 付款方式编号
 	private Date flowStartTime;
 	
	public String getApplyPayCode() {
		return applyPayCode;
	}

	public void setApplyPayCode(String applyPayCode) {
		this.applyPayCode = applyPayCode;
	}

	public String getOrgIds() {
		return orgIds;
	}

	public void setOrgIds(String orgIds) {
		this.orgIds = orgIds;
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

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getTeamManagerName() {
		return teamManagerName;
	}

	public void setTeamManagerName(String teamManagerName) {
		this.teamManagerName = teamManagerName;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getChangeType() {
		return changeType;
	}

	public void setChangeType(String changeType) {
		this.changeType = changeType;
	}

	public String getDictChangeState() {
		return dictChangeState;
	}

	public void setDictChangeState(String dictChangeState) {
		this.dictChangeState = dictChangeState;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getApplyId() {
		return applyId;
	}

	public void setApplyId(String applyId) {
		this.applyId = applyId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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

	public String getTeamEmp() {
		return teamEmp;
	}

	public void setTeamEmp(String teamEmp) {
		this.teamEmp = teamEmp;
	}

	public String getAddrProvince() {
		return addrProvince;
	}

	public void setAddrProvince(String addrProvince) {
		this.addrProvince = addrProvince;
	}

	public String getAddrCity() {
		return addrCity;
	}

	public void setAddrCity(String addrCity) {
		this.addrCity = addrCity;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getCustState() {
		return custState;
	}

	public void setCustState(String custState) {
		this.custState = custState;
	}

	public String getDictCustSource() {
		return dictCustSource;
	}

	public void setDictCustSource(String dictCustSource) {
		this.dictCustSource = dictCustSource;
	}

	public String getApplySalesDiscount() {
		return applySalesDiscount;
	}

	public void setApplySalesDiscount(String applySalesDiscount) {
		this.applySalesDiscount = applySalesDiscount;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(String applyDate) {
		this.applyDate = applyDate;
	}

	public String getApplyDeductDate() {
		return applyDeductDate;
	}

	public void setApplyDeductDate(String applyDeductDate) {
		this.applyDeductDate = applyDeductDate;
	}

	public String getApplyLendDate() {
		return applyLendDate;
	}

	public void setApplyLendDate(String applyLendDate) {
		this.applyLendDate = applyLendDate;
	}

	public String getApplyPay() {
		return applyPay;
	}

	public void setApplyPay(String applyPay) {
		this.applyPay = applyPay;
	}

	public String getApplyAgreemenEdition() {
		return applyAgreemenEdition;
	}

	public void setApplyAgreemenEdition(String applyAgreemenEdition) {
		this.applyAgreemenEdition = applyAgreemenEdition;
	}

	public String getApplyLendMoney() {
		return applyLendMoney;
	}

	public void setApplyLendMoney(String applyLendMoney) {
		this.applyLendMoney = applyLendMoney;
	}

	public String getApplyContractNo() {
		return applyContractNo;
	}

	public void setApplyContractNo(String applyContractNo) {
		this.applyContractNo = applyContractNo;
	}

	public String getApplyCode() {
		return applyCode;
	}

	public void setApplyCode(String applyCode) {
		this.applyCode = applyCode;
	}

	public String getCustCode() {
		return custCode;
	}

	public void setCustCode(String custCode) {
		this.custCode = custCode;
	}

	public String getApplyHostedState() {
		return applyHostedState;
	}

	public void setApplyHostedState(String applyHostedState) {
		this.applyHostedState = applyHostedState;
	}

	public String getApplyState() {
		return applyState;
	}

	public void setApplyState(String applyState) {
		this.applyState = applyState;
	}

	public String getRepayId() {
		return repayId;
	}

	public void setRepayId(String repayId) {
		this.repayId = repayId;
	}

	public String getReceivingId() {
		return receivingId;
	}

	public void setReceivingId(String receivingId) {
		this.receivingId = receivingId;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getTrusteeshipNo() {
		return trusteeshipNo;
	}

	public void setTrusteeshipNo(String trusteeshipNo) {
		this.trusteeshipNo = trusteeshipNo;
	}

	public String getChangerTypeName() {
		return changerTypeName;
	}

	public void setChangerTypeName(String changerTypeName) {
		this.changerTypeName = changerTypeName;
	}

	public String getChangerTypeVal() {
		return changerTypeVal;
	}

	public void setChangerTypeVal(String changerTypeVal) {
		this.changerTypeVal = changerTypeVal;
	}

	public String getCustCertNum() {
		return custCertNum;
	}

	public void setCustCertNum(String custCertNum) {
		this.custCertNum = custCertNum;
	}

	public String getChangerState() {
		return changerState;
	}

	public void setChangerState(String changerState) {
		this.changerState = changerState;
	}

	public String getApplyPayName() {
		return applyPayName;
	}

	public void setApplyPayName(String applyPayName) {
		this.applyPayName = applyPayName;
	}

	public String getDictApplyEndState() {
		return dictApplyEndState;
	}

	public void setDictApplyEndState(String dictApplyEndState) {
		this.dictApplyEndState = dictApplyEndState;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getCustCertType() {
		return custCertType;
	}

	public void setCustCertType(String custCertType) {
		this.custCertType = custCertType;
	}

	public Integer getBillDay() {
		return billDay;
	}

	public void setBillDay(Integer billDay) {
		this.billDay = billDay;
	}

	public String getReceivingIdAfter() {
		return receivingIdAfter;
	}

	public void setReceivingIdAfter(String receivingIdAfter) {
		this.receivingIdAfter = receivingIdAfter;
	}

	public Date getFlowStartTime() {
		return flowStartTime;
	}

	public void setFlowStartTime(Date flowStartTime) {
		this.flowStartTime = flowStartTime;
	}

	
	

}
