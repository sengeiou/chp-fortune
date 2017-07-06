package com.creditharmony.fortune.change.lend.apply.entity;

import java.util.Date;

import com.creditharmony.core.persistence.DataEntity;

/**
 * 出借申请变更扩展类
 * @Class Name LendLoanApply
 * @author 刘雄武
 * @Create In 2015年11月30日
 */
public class LendLoanApplyEx extends DataEntity<LendLoanApplyEx> {

	/**
	 * long serialVersionUID 
	 */
	private static final long serialVersionUID = 1L;
	private String applyCode;// 出借编号
	private String custCode;// 客户编号
	private String applyHostedState;// 托管状态
	private String applyState;// 出借状态(对应码表出借状态)
	private String orgName;// 营业部
	private String managerId;// 理财经理
	private String teamEmp;// 团队经理
	private Date createTime;// 创建时间
	private String custMobilephone;// 联系电话（暂定为手机）
	private String addrProvince;// 通讯省
	private String addrCity;// 通讯市
	private String custName;// 客户名称
	private String custState;// 客户状态
	private String dictCustSource;// 客户来源
	private String addId;// 城市
	private String orgCode;// 营业部code
	private String dictDeductStatus;// 划扣申请状态
	private String dictApplyEndState;// 完结状态
	private String dictChangeState;// 变更状态
	private String managerName;// 理财经理名称
	private String teamManagerName;// 团队经理名称
	private String departmentName;// 营业部
	private String teamName;// 团队名称
	private String isGoldAccount;// 金账户标示
	private String trusteeshipNo;//金账户账号
 	private String changerTypeName;// 变更类型名称
 	private String changerTypeVal;// 变更类型值
 	private String applyPayName;// 付款方式（显示名称）
	private String backStatus;// 回款状态
 	
 	
	
	
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
	public String getDictChangeState() {
		return dictChangeState;
	}
	public void setDictChangeState(String dictChangeState) {
		this.dictChangeState = dictChangeState;
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
	public String getDictCustSource() {
		return dictCustSource;
	}
	public void setDictCustSource(String dictCustSource) {
		this.dictCustSource = dictCustSource;
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
	public String getCustMobilephone() {
		return custMobilephone;
	}
	public void setCustMobilephone(String custMobilephone) {
		this.custMobilephone = custMobilephone;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
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
	public String getIsGoldAccount() {
		return isGoldAccount;
	}
	public void setIsGoldAccount(String isGoldAccount) {
		this.isGoldAccount = isGoldAccount;
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
	public String getApplyPayName() {
		return applyPayName;
	}
	public void setApplyPayName(String applyPayName) {
		this.applyPayName = applyPayName;
	}
	public String getBackStatus() {
		return backStatus;
	}
	public void setBackStatus(String backStatus) {
		this.backStatus = backStatus;
	}
	
	
	
	
}
