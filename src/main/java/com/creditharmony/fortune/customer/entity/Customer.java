package com.creditharmony.fortune.customer.entity;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.creditharmony.common.util.ObjectHelper;
import com.creditharmony.core.excel.annotation.ExcelField;
import com.creditharmony.core.persistence.DataEntity;

/**
 * @Class Name Customer
 * @author 孙凯文
 * @Create In 2015年12月2日
 */
public class Customer extends DataEntity<Customer> {

	private static final long serialVersionUID = 5543975725691249838L;
	
	public Customer(){};
	
	public Customer(String customerCode){
		this.custCode = customerCode;
	}
	
	// 客户编号
	@ExcelField(title="客户编号")
	private String custCode;
	// 客户名称
	@ExcelField(title="客户名称")
	private String custName;
	// 客户真实姓名
	@ExcelField(title="客户真实姓名")
	private String custRealname;
	// 客户性别字典码
	@ExcelField(title="客户性别字典码")
	private String dictCustSex;
	// 客户来源
	@ExcelField(title="客户来源")
	private String dictCustSource;
	// 客户移动号码
	@ExcelField(title="客户移动号码")
	private String custMobilephone;
	// 客户固定电话
	@ExcelField(title="客户固定电话")
	private String custPhone;
	// 邮箱
	@ExcelField(title="邮箱")
	private String custEmail;
	// 单位名称
	@ExcelField(title="单位名称")
	private String custUnitname;
	// 行业
	@ExcelField(title="行业")
	private String custIndustry;
	// 职务
	@ExcelField(title="职务")
	private String custPost;
	// 传真
	@ExcelField(title="传真")
	private String custFax;
	// 证件类型
	@ExcelField(title="证件类型")
	private String custCertType;
	// 证件号码
	@ExcelField(title="证件号码")
	private String custCertNum;
	// 发证机关
	@ExcelField(title="发证机关")
	private String custCertOrg;
	// 签发日期
	@ExcelField(title="签发日期")
	private Date custCertIssuedate;
	// 失效日期
	@ExcelField(title="失效日期")
	private Date custCertFailuredate;
	// 长期有效
	@ExcelField(title="长期有效")
	private String custCertPermanent;
	// 英文名称
	@ExcelField(title="英文名称")
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
	// 归属人(理财经理)
	private String managerCode;
	// 公共池标识
	private String custMark;
	// 电销客服ID
	private String teleManagerId;
	//电销客服姓名
	private String teleManagerName;
	// 电销时间
	private Date teleTime;
	// 数据来源终端
	private String dataTerminal;
	// 来源版本
	private String sourceEdition;
	// 是否做过交割
	private String isDelivery;
	// 机构ID
	private String orgCode;
	// 流程ID
	private String applyId;
	// 客户咨询列表
	private List<Advisory> advisoryList;
	// 客户地址
	private Address address;
	// 理财经理名称
	private String managerName;
	// 团队经理名称
	private String teamManagerName;
	// 营业部
	private String storesName;
	// 团队名称
	private String teamName;
	// 营业部省份
	private String provinceId;
	// 营业部城市
	private String cityId;
	// 团队经理电话
	private String teamMobilePhone;
	//金账户账号
	private String trusteeshipNo;
	//预计进入公共池时间
	private Date preIntoPoolTime;
	// 托管状态
	private String applyHostedStatus;
	//公共池状态，对应pool表的 data_Type
	private String poolStatus; 
	//金账户返回码
	private String trusteeshipRetCode;
	//金账户返回内容
	private String trusteeshipRetMsg;
	// 金账户银行ID
	private String trusteeshipAccountId;
	// 转赠状态
	private String transferState;
	// 地址ID
	private String addId;
	// 团队机构ID
	private String teamOrgId;
	//营业部ID
	private String storesId;
	// 旧客户编号
	private String oldCustomerCode;
	// 团队经理
	private String teamManagerCode;
	
	// 城市
	private String addrCity;
	
	private String provinceName;
	private String cityName;
	private String customerTerminalName;
	private String dictCustomerSourceName;
	private String customerLendingName;
	
	
	/** 应对电销     start   2016-11-15 15:30:56**/
	/**原机构IDList**/
	private List<String> customerOldOrgIdList;
	/**原机构ID**/
	private String customerOldOrgId;
	/**原机构**/
	private String customerOldOrgName;
	/**原省**/
	private String customerOldProvinceId;
	/**原省中文**/
	private String customerOldProvinceName;
	/**原市**/
	private String customerOldCityid;
	/**原市中文**/
	private String customerOldCityName;
	/** 应对电销     end   2016-11-15 15:30:56**/
	

	
	public String getAddrCity() {
		return addrCity;
	}

	public void setAddrCity(String addrCity) {
		this.addrCity = addrCity;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getCustomerTerminalName() {
		return customerTerminalName;
	}

	public void setCustomerTerminalName(String customerTerminalName) {
		this.customerTerminalName = customerTerminalName;
	}

	public String getDictCustomerSourceName() {
		return dictCustomerSourceName;
	}

	public void setDictCustomerSourceName(String dictCustomerSourceName) {
		this.dictCustomerSourceName = dictCustomerSourceName;
	}

	public String getCustomerLendingName() {
		return customerLendingName;
	}

	public void setCustomerLendingName(String customerLendingName) {
		this.customerLendingName = customerLendingName;
	}

	public String getStoresId() {
		return storesId;
	}

	public void setStoresId(String storesId) {
		this.storesId = storesId;
	}

	public String getAddId() {
		return addId;
	}

	public void setAddId(String addId) {
		this.addId = addId;
	}

	public String getTransferState() {
		return transferState;
	}

	public void setTransferState(String transferState) {
		this.transferState = transferState;
	}

	public String getPoolStatus() {
		return poolStatus;
	}

	public void setPoolStatus(String poolStatus) {
		this.poolStatus = poolStatus;
	}

	public Date getPreIntoPoolTime() {
		return preIntoPoolTime;
	}

	public void setPreIntoPoolTime(Date preIntoPoolTime) {
		this.preIntoPoolTime = preIntoPoolTime;
	}

	public String getTeleManagerName() {
		return teleManagerName;
	}

	public void setTeleManagerName(String teleManagerName) {
		this.teleManagerName = teleManagerName;
	}

	public String getTeamMobilePhone() {
		return teamMobilePhone;
	}

	public void setTeamMobilePhone(String teamMobilePhone) {
		this.teamMobilePhone = teamMobilePhone;
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

	public Date getCustCertIssuedate() {
		return custCertIssuedate;
	}

	public void setCustCertIssuedate(Date custCertIssuedate) {
		this.custCertIssuedate = custCertIssuedate;
	}

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

	public String getManagerCode() {
		return managerCode;
	}

	public void setManagerCode(String managerCode) {
		this.managerCode = managerCode;
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

	public Date getTeleTime() {
		return teleTime;
	}

	public void setTeleTime(Date teleTime) {
		this.teleTime = teleTime;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<Advisory> getAdvisoryList() {
		return advisoryList;
	}

	public void setAdvisoryList(List<Advisory> advisoryList) {
		this.advisoryList = advisoryList;
	}

	public String getDataTerminal() {
		return dataTerminal;
	}

	public void setDataTerminal(String dataFrom) {
		this.dataTerminal = dataFrom;
	}

	public String getSourceEdition() {
		return sourceEdition;
	}

	public void setSourceEdition(String sourceEdition) {
		this.sourceEdition = sourceEdition;
	}

	public String getIsDelivery() {
		return isDelivery;
	}

	public void setIsDelivery(String isDelivery) {
		this.isDelivery = isDelivery;
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

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public String getStoresName() {
		return storesName;
	}

	public void setStoresName(String storesName) {
		this.storesName = storesName;
	}

	public String getTrusteeshipNo() {
		return trusteeshipNo;
	}

	public void setTrusteeshipNo(String trusteeshipNo) {
		this.trusteeshipNo = trusteeshipNo;
	}

	public String getApplyHostedStatus() {
		return applyHostedStatus;
	}

	public void setApplyHostedStatus(String applyHostedStatus) {
		this.applyHostedStatus = applyHostedStatus;
	}

	public String getTrusteeshipRetCode() {
		return trusteeshipRetCode;
	}

	public void setTrusteeshipRetCode(String trusteeshipRetCode) {
		this.trusteeshipRetCode = trusteeshipRetCode;
	}

	public String getTrusteeshipRetMsg() {
		return trusteeshipRetMsg;
	}

	public void setTrusteeshipRetMsg(String trusteeshipRetMsg) {
		this.trusteeshipRetMsg = trusteeshipRetMsg;
	}

	public String getTrusteeshipAccountId() {
		return trusteeshipAccountId;
	}

	public void setTrusteeshipAccountId(String trusteeshipAccountId) {
		this.trusteeshipAccountId = trusteeshipAccountId;
	}

	public String getTeamOrgId() {
		return teamOrgId;
	}

	public void setTeamOrgId(String teamOrgId) {
		this.teamOrgId = teamOrgId;
	}

	public String getOldCustomerCode() {
		return oldCustomerCode;
	}

	public void setOldCustomerCode(String oldCustomerCode) {
		this.oldCustomerCode = oldCustomerCode;
	}

	public String getTeamManagerCode() {
		return teamManagerCode;
	}

	public void setTeamManagerCode(String teamManagerCode) {
		this.teamManagerCode = teamManagerCode;
	}

	public String getCustomerOldOrgName() {
		return customerOldOrgName;
	}

	public void setCustomerOldOrgName(String customerOldOrgName) {
		this.customerOldOrgName = customerOldOrgName;
	}

	public String getCustomerOldProvinceId() {
		return customerOldProvinceId;
	}

	public void setCustomerOldProvinceId(String customerOldProvinceId) {
		this.customerOldProvinceId = customerOldProvinceId;
	}

	public String getCustomerOldProvinceName() {
		return customerOldProvinceName;
	}

	public void setCustomerOldProvinceName(String customerOldProvinceName) {
		this.customerOldProvinceName = customerOldProvinceName;
	}

	public String getCustomerOldCityid() {
		return customerOldCityid;
	}

	public void setCustomerOldCityid(String customerOldCityid) {
		this.customerOldCityid = customerOldCityid;
	}

	public String getCustomerOldCityName() {
		return customerOldCityName;
	}

	public void setCustomerOldCityName(String customerOldCityName) {
		this.customerOldCityName = customerOldCityName;
	}

	public String getCustomerOldOrgId() {
		return customerOldOrgId;
	}

	public void setCustomerOldOrgId(String customerOldOrgId) {
		this.customerOldOrgId = customerOldOrgId;
	}

	public List<String> getCustomerOldOrgIdList() {
		if (ObjectHelper.isEmpty(customerOldOrgId)) {
			return null;
		}
		customerOldOrgIdList = Arrays.asList(customerOldOrgId.split(","));
		return customerOldOrgIdList;
	}

	public void setCustomerOldOrgIdList(List<String> customerOldOrgIdList) {
		this.customerOldOrgIdList = customerOldOrgIdList;
	}
	
	
	

}