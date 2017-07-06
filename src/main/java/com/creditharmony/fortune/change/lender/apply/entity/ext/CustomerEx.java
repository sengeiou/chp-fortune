package com.creditharmony.fortune.change.lender.apply.entity.ext;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.creditharmony.core.persistence.DataEntity;
import com.creditharmony.fortune.customer.entity.Address;
import com.fasterxml.jackson.annotation.JsonFormat;
/**
 * 出借人信息entity
 * @Class Name CustomerEx
 * @author 郭才林
 * @Create In 2015年11月27日
 */
public class CustomerEx extends DataEntity<CustomerEx> {

	private static final long serialVersionUID = 5543975725691249838L;

		// 客户编号
		private String custCode;
		// 客户名称
		private String custName;
		// 客户真实姓名
		private String custRealname;
		// 客户性别字典码
		private String dictCustSex;
		// 客户来源
		private String dictCustSource;
		// 客户移动号码
		private String custMobilephone;
		// 客户固定电话
		private String custPhone;
		// 邮箱
		private String custEmail;
		// 单位名称
		private String custUnitname;
		// 行业
		private String custIndustry;
		// 职务
		private String custPost;
		// 传真
		private String custFax;
		// 证件类型
		private String custCertType;
		// 证件号码
		private String custCertNum;
		// 发证机关
		private String custCertOrg;
		// 签发日期
		private Date custCertIssuedate;
		// 失效日期
		private Date custCertFailuredate;
		// 长期有效
		private String custCertPermanent;
		// 英文名称
		private String custEname;
		// 学历
		private String custEducation;
		// 婚姻状况
		private String custMarriage;
		// 出生日期
		private Date custBirthday;
		// 母亲姓氏
		private String custMotherName;
		// 首次联系时间
		private Date custFirstContactdate;
		// 职业
		private String custOccupation;
		// 工作年限
		private String custWorkExperience;
		// 单位规模
		private String custUnitScale;
		// 客户状态
		private String custState;
		// 是否做过出借
		private String custLending;
		// 公共池标识
		private String custMark;
		// 电销客服ID
		private String teleManagerId;
		// 来源版本
		private String sourceEdition;
		// 客户地址
		private Address address;
		// 理财经理
		private String managerId;
		// 团队经理
		private String teamManagerId;
		// 申请id
		private String applyId;
		// 省
		private String addrProvince;
		// 营业部
		private String norg;
		// 城市
		private String addrCity;
		// 地址id
		private String addrId;
		// 团队经理
		private String teamEmp;
		// 客户年龄
		private String custAge;
		// 离下次过生日的天数
		private String lastBirthdayDay;
		// 变更状态
		private String dictChangeState;
		// 客户当前状态
		private String dictCustomerState;
		// 理财经理名称
		private String managerName;
		// 团队经理名称
		private String teamManagerName;
		// 营业部
		private String departmentName;
		// 团队名称
		private String teamName;
		// 金账户标示
		private String isGoldAccount;
		//金账户账号
		private String trusteeshipNo;
		// 变更前的手机号码
		private String custMobilephoneChanger;
		// 变更类型名称
	 	private String changerTypeName;
	 	// 变更类型值
	 	private String changerTypeVal;
	 	// 变更状态
	 	private String changerState;
		// 托管状态
		private String   applyHostedStatus;
		// 审核通过查询初始日期
		private Date dictApprovalStartDate;
		// 审核通过查询结束日期
		private Date dictApprovalEndDate;
		private String emailBegin;
		// 金账户银行ID
		private String trusteeshipAccountId;
		
		
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

		public String getCustRealname() {
			return custRealname;
		}

		public void setCustRealname(String custRealname) {
			this.custRealname = custRealname;
		}

		public String getDictCustSex() {
			return dictCustSex;
		}

		public void setDictCustSex(String dictCustSex) {
			this.dictCustSex = dictCustSex;
		}

		public String getDictCustSource() {
			return dictCustSource;
		}

		public void setDictCustSource(String dictCustSource) {
			this.dictCustSource = dictCustSource;
		}

		public String getCustMobilephone() {
			return custMobilephone;
		}

		public void setCustMobilephone(String custMobilephone) {
			this.custMobilephone = custMobilephone;
		}

		public String getCustPhone() {
			return custPhone;
		}

		public void setCustPhone(String custPhone) {
			this.custPhone = custPhone;
		}

		public String getCustEmail() {
			return custEmail;
		}

		public void setCustEmail(String custEmail) {
			this.custEmail = custEmail;
		}

		public String getCustUnitname() {
			return custUnitname;
		}

		public void setCustUnitname(String custUnitname) {
			this.custUnitname = custUnitname;
		}

		public String getCustIndustry() {
			return custIndustry;
		}

		public void setCustIndustry(String custIndustry) {
			this.custIndustry = custIndustry;
		}

		public String getCustPost() {
			return custPost;
		}

		public void setCustPost(String custPost) {
			this.custPost = custPost;
		}

		public String getCustFax() {
			return custFax;
		}

		public void setCustFax(String custFax) {
			this.custFax = custFax;
		}

		public String getCustCertType() {
			return custCertType;
		}

		public void setCustCertType(String custCertType) {
			this.custCertType = custCertType;
		}

		public String getCustCertNum() {
			return custCertNum;
		}

		public void setCustCertNum(String custCertNum) {
			this.custCertNum = custCertNum;
		}

		public String getCustCertOrg() {
			return custCertOrg;
		}

		public void setCustCertOrg(String custCertOrg) {
			this.custCertOrg = custCertOrg;
		}
		@DateTimeFormat(pattern="yyyy-MM-dd")  
		@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")  
		public Date getCustCertIssuedate() {
			return custCertIssuedate;
		}

		public void setCustCertIssuedate(Date custCertIssuedate) {
			this.custCertIssuedate = custCertIssuedate;
		}

		@DateTimeFormat(pattern="yyyy-MM-dd")  
		@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")  
		public Date getCustCertFailuredate() {
			return custCertFailuredate;
		}

		public void setCustCertFailuredate(Date custCertFailuredate) {
			this.custCertFailuredate = custCertFailuredate;
		}

		public String getCustCertPermanent() {
			return custCertPermanent;
		}

		public void setCustCertPermanent(String custCertPermanent) {
			this.custCertPermanent = custCertPermanent;
		}

		public String getCustEname() {
			return custEname;
		}

		public void setCustEname(String custEname) {
			this.custEname = custEname;
		}

		public String getCustEducation() {
			return custEducation;
		}

		public void setCustEducation(String custEducation) {
			this.custEducation = custEducation;
		}

		public String getCustMarriage() {
			return custMarriage;
		}

		public void setCustMarriage(String custMarriage) {
			this.custMarriage = custMarriage;
		}

		public Date getCustBirthday() {
			return custBirthday;
		}

		public void setCustBirthday(Date custBirthday) {
			this.custBirthday = custBirthday;
		}

		public String getCustMotherName() {
			return custMotherName;
		}

		public void setCustMotherName(String custMotherName) {
			this.custMotherName = custMotherName;
		}
		
		@DateTimeFormat(pattern="yyyy-MM-dd")  
		@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")  
		public Date getCustFirstContactdate() {
			return custFirstContactdate;
		}

		public void setCustFirstContactdate(Date custFirstContactdate) {
			this.custFirstContactdate = custFirstContactdate;
		}

		public String getCustOccupation() {
			return custOccupation;
		}

		public void setCustOccupation(String custOccupation) {
			this.custOccupation = custOccupation;
		}

		public String getCustWorkExperience() {
			return custWorkExperience;
		}

		public void setCustWorkExperience(String custWorkExperience) {
			this.custWorkExperience = custWorkExperience;
		}

		public String getCustUnitScale() {
			return custUnitScale;
		}

		public void setCustUnitScale(String custUnitScale) {
			this.custUnitScale = custUnitScale;
		}

		public String getCustState() {
			return custState;
		}

		public void setCustState(String custState) {
			this.custState = custState;
		}

		public String getCustLending() {
			return custLending;
		}

		public void setCustLending(String custLending) {
			this.custLending = custLending;
		}

		public String getManagerId() {
			return managerId;
		}

		public void setManagerId(String managerId) {
			this.managerId = managerId;
		}

		public String getCustMark() {
			return custMark;
		}

		public void setCustMark(String custMark) {
			this.custMark = custMark;
		}

		public String getTeleManagerId() {
			return teleManagerId;
		}

		public void setTeleManagerId(String teleManagerId) {
			this.teleManagerId = teleManagerId;
		}


		public Address getAddress() {
			return address;
		}

		public void setAddress(Address address) {
			this.address = address;
		}

		public String getCustAge() {
			return custAge;
		}

		public void setCustAge(String custAge) {
			this.custAge = custAge;
		}

		public void setLastBirthdayDay(String lastBirthdayDay) {
			this.lastBirthdayDay = lastBirthdayDay;
		}
		
		public String getSourceEdition() {
			return sourceEdition;
		}

		public void setSourceEdition(String sourceEdition) {
			this.sourceEdition = sourceEdition;
		}

		public String getAddrProvince() {
			return addrProvince;
		}

		public void setAddrProvince(String addrProvince) {
			this.addrProvince = addrProvince;
		}

		public String getNorg() {
			return norg;
		}

		public void setNorg(String norg) {
			this.norg = norg;
		}

		public String getAddrCity() {
			return addrCity;
		}

		public void setAddrCity(String addrCity) {
			this.addrCity = addrCity;
		}

		public String getLastBirthdayDay() {
			return lastBirthdayDay;
		}

		public String getAddrId() {
			return addrId;
		}

		public void setAddrId(String addrId) {
			this.addrId = addrId;
		}

		public String getTeamEmp() {
			return teamEmp;
		}

		public void setTeamEmp(String teamEmp) {
			this.teamEmp = teamEmp;
		}

		public String getApplyId() {
			return applyId;
		}

		public void setApplyId(String applyId) {
			this.applyId = applyId;
		}

		public String getDictChangeState() {
			return dictChangeState;
		}

		public void setDictChangeState(String dictChangeState) {
			this.dictChangeState = dictChangeState;
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

		public String getCustMobilephoneChanger() {
			return custMobilephoneChanger;
		}

		public void setCustMobilephoneChanger(String custMobilephoneChanger) {
			this.custMobilephoneChanger = custMobilephoneChanger;
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

		public String getChangerState() {
			return changerState;
		}

		public void setChangerState(String changerState) {
			this.changerState = changerState;
		}

		public String getApplyHostedStatus() {
			return applyHostedStatus;
		}

		public void setApplyHostedStatus(String applyHostedStatus) {
			this.applyHostedStatus = applyHostedStatus;
		}

		public String getEmailBegin() {
			return emailBegin;
		}

		public void setEmailBegin(String emailBegin) {
			this.emailBegin = emailBegin;
		}

		public String getTrusteeshipAccountId() {
			return trusteeshipAccountId;
		}

		public void setTrusteeshipAccountId(String trusteeshipAccountId) {
			this.trusteeshipAccountId = trusteeshipAccountId;
		}

		public String getTeamManagerId() {
			return teamManagerId;
		}

		public void setTeamManagerId(String teamManagerId) {
			this.teamManagerId = teamManagerId;
		}


}