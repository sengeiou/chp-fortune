package com.creditharmony.fortune.back.priority.common.view;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.creditharmony.common.util.ObjectHelper;
import com.creditharmony.core.persistence.DataEntity;

/**
 * 优先回款列表封装Bean
 * @Class Name PriorityListItemView
 * @author 郭强
 * @Create In 2017年3月27日
 */
public class PriorityListItemView extends DataEntity<PriorityListItemView> {

	/**
	 * long serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	private String priorityId;		//主键Id
	private String prioIds;		
	private List<String> priorityIds;
	private String priorityBackState;		//优先回款状态
	private List<String>  priorityBackStateList;
	private	List<String>  priorityBackStateAS;  //用于搜索条件时使用
	private Date  createTime;		//创建时间    审批时间 搜索使用这个
	private Date  createTimeTo;		
	private String backPriorityRemarks;	//备注
	private String rebackFlag;			//退回重放标识
	
	private String dictBackState;		//回款状态
	private List<String> dictBackStateList;
	private Date applyExpireDay; 		// 到期日期
	private Date applyExpireDayTo;
	private Date backMoneyDay;			//回款日期
	private Date backMoneyDayTo;
	private String backMoneyType;		//回款类型
	private List<String> backMoneyTypeList;
	private String backBank;			//放款银行
	private List<String> bankList; 
	private String lendCode; 			// 出借编号
	private List<String> lendCodeList; 	// 出借编号
	private String applyAgreementEdition;	//合同版本号
	private List<String> editionList;	// 合同版本号
	private Date approveDate;
	private Date approveDateStart;
	private Date approveDateEnd;	
	private Date applyLendDay; 			// 出借日期
	private Date applyLendDayTo;
	private Double applyLendMoney; 		// 计划出借金额
	private Double applyLendMoneyTo;
	private String applyPay; 			// 付款方式
	private List<String> applyPayList;
	private String productCode; 		// 出借产品Code
	private List<String> productList; 	// 出借产品列表
	private String productName; 		// 出借产品
	private String orgName; 			// 营业部
	private String orgID; 				// 营业部ID
	private List<String> orgList;
	private String city; 				// 营业部所在城市
	private List<String> cityList; 		// 营业部所在城市
	private String manager; 			// 理财经理
	private String userCode; 			// 员工编号
	private String accountCardOrBooklet;// 卡或折
	private List<String> cobList;		// 卡或折
	private String accountAddrprovince; // 省份
	private String accountAddrcity; 	// 城市
    private String accountAddrdistrict;	//区
	private String findFlag; 		// 支行行号匹配标识
	private List<String> findFlagList;
	private String customerName; 		// 客户姓名
	private List<String> customerNameList; 		// 客户姓名
	private String customerCode; 		// 客户编号
	private List<String> customerCodeList; // 客户编号
	private String PriorityBack;		//是否优先回款
	private String listFlag;
	private Integer applyBillDay;		//账单日
	private Date checkTime;				//审核时间
	private Date checkTimeTo;
	private Date applyTime;				//申请时间
	private Date applyTimeTo;	
	private String returnReasons;		//备注
	private String userId;	//用户Id
	private String dataRights;	// 数据权限
	private String applyBy;  //applyBy
	private String createBy;
	private String fortuneCentre;
	
	
	public String getFortuneCentre() {
		return fortuneCentre;
	}

	public void setFortuneCentre(String fortuneCentre) {
		this.fortuneCentre = fortuneCentre;
	}

	public String getApplyBy() {
		return applyBy;
	}

	public void setApplyBy(String applyBy) {
		this.applyBy = applyBy;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getDataRights() {
		return dataRights;
	}

	public void setDataRights(String dataRights) {
		this.dataRights = dataRights;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPrioIds() {
		return prioIds;
	}

	public void setPrioIds(String prioIds) {
		this.prioIds = prioIds;
	}

	public Date getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}

	public Date getApplyTimeTo() {
		return applyTimeTo;
	}

	public void setApplyTimeTo(Date applyTimeTo) {
		this.applyTimeTo = applyTimeTo;
	}

	public Date getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}

	public Date getCheckTimeTo() {
		return checkTimeTo;
	}

	public void setCheckTimeTo(Date checkTimeTo) {
		this.checkTimeTo = checkTimeTo;
	}

	public Integer getApplyBillDay() {
		return applyBillDay;
	}

	public void setApplyBillDay(Integer applyBillDay) {
		this.applyBillDay = applyBillDay;
	}

	public List<String> getBankList() {
		if (ObjectHelper.isEmpty(backBank)) {
			return null;
		}
		bankList = Arrays.asList(backBank.split(","));
		return bankList;
	}

	public void setBankList(List<String> bankList) {
		this.bankList = bankList;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public List<String> getCustomerNameList() {
		if (!ObjectHelper.isEmpty(customerName)) {
			customerNameList = Arrays.asList(customerName.replace("，", ",").split(","));
		}
		return customerNameList;
	}

	public void setCustomerNameList(List<String> customerNameList) {
		this.customerNameList = customerNameList;
	}

	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	public List<String> getCustomerCodeList() {
		if (!ObjectHelper.isEmpty(customerCode)) {
			customerCodeList = Arrays.asList(customerCode.replace("，", ",").split(","));
		}
		 
		return customerCodeList;
	}

	public void setCustomerCodeList(List<String> customerCodeList) {
		this.customerCodeList = customerCodeList;
	}

	public String getLendCode() {
		return lendCode;
	}

	public void setLendCode(String lendCode) {
		this.lendCode = lendCode;
	}

	public List<String> getLendCodeList() {
		if (!ObjectHelper.isEmpty(lendCode)) {
			lendCodeList = Arrays.asList(lendCode.replace("，", ",").split(","));
		}
		return lendCodeList;
	}

	public void setLendCodeList(List<String> lendCodeList) {
		this.lendCodeList = lendCodeList;
	}

	public Date getApplyLendDay() {
		return applyLendDay;
	}

	public void setApplyLendDay(Date applyLendDay) {
		this.applyLendDay = applyLendDay;
	}

	public Date getApplyLendDayTo() {
		return applyLendDayTo;
	}

	public void setApplyLendDayTo(Date applyLendDayTo) {
		this.applyLendDayTo = applyLendDayTo;
	}

	public Date getApplyExpireDay() {
		return applyExpireDay;
	}

	public void setApplyExpireDay(Date applyExpireDay) {
		this.applyExpireDay = applyExpireDay;
	}

	public Date getApplyExpireDayTo() {
		return applyExpireDayTo;
	}

	public void setApplyExpireDayTo(Date applyExpireDayTo) {
		this.applyExpireDayTo = applyExpireDayTo;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public List<String> getProductList() {
		if (ObjectHelper.isEmpty(productCode)) {
			return null;
		}
		productList = Arrays.asList(productCode.split(","));
		return productList;
	}

	public void setProductList(List<String> productList) {
		this.productList = productList;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getApplyPay() {
		return applyPay;
	}

	public void setApplyPay(String applyPay) {
		this.applyPay = applyPay;
	}

	public List<String> getApplyPayList() {
		if (ObjectHelper.isEmpty(applyPay)) {
			return null;
		}
		applyPayList = Arrays.asList(applyPay.split(","));
		return applyPayList;
	}

	public void setApplyPayList(List<String> applyPayList) {
		this.applyPayList = applyPayList;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getOrgID() {
		return orgID;
	}

	public void setOrgID(String orgID) {
		this.orgID = orgID;
	}

	public List<String> getOrgList() {
		if (ObjectHelper.isEmpty(orgID)) {
			return null;
		}
		orgList = Arrays.asList(orgID.split(","));
		return orgList;
	}

	public void setOrgList(List<String> orgList) {
		this.orgList = orgList;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getPriorityId() {
		return priorityId;
	}

	public void setPriorityId(String priorityId) {
		this.priorityId = priorityId;
	}

	public Double getApplyLendMoney() {
		return applyLendMoney;
	}

	public void setApplyLendMoney(Double applyLendMoney) {
		this.applyLendMoney = applyLendMoney;
	}

	public Double getApplyLendMoneyTo() {
		return applyLendMoneyTo;
	}

	public void setApplyLendMoneyTo(Double applyLendMoneyTo) {
		this.applyLendMoneyTo = applyLendMoneyTo;
	}

	public String getAccountAddrprovince() {
		return accountAddrprovince;
	}

	public void setAccountAddrprovince(String accountAddrprovince) {
		this.accountAddrprovince = accountAddrprovince;
	}

	public String getAccountAddrcity() {
		return accountAddrcity;
	}

	public void setAccountAddrcity(String accountAddrcity) {
		this.accountAddrcity = accountAddrcity;
	}

	public String getPriorityBackState() {
		return priorityBackState;
	}

	public void setPriorityBackState(String priorityBackState) {
		this.priorityBackState = priorityBackState;
	}

	public String getPriorityBack() {
		return PriorityBack;
	}

	public void setPriorityBack(String priorityBack) {
		PriorityBack = priorityBack;
	}


	public String getModifyBy() {
		return modifyBy;
	}

	public void setModifyBy(String modifyBy) {
		this.modifyBy = modifyBy;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getBackPriorityRemarks() {
		return backPriorityRemarks;
	}

	public void setBackPriorityRemarks(String backPriorityRemarks) {
		this.backPriorityRemarks = backPriorityRemarks;
	}

	public String getReturnReasons() {
		return returnReasons;
	}

	public void setReturnReasons(String returnReasons) {
		this.returnReasons = returnReasons;
	}

	public String getRebackFlag() {
		return rebackFlag;
	}

	public void setRebackFlag(String rebackFlag) {
		this.rebackFlag = rebackFlag;
	}

	public List<String> getPriorityBackStateAS() {
		if (!ObjectHelper.isEmpty(priorityBackState)) {
			priorityBackStateAS = Arrays.asList(priorityBackState.split(","));
		}
		return priorityBackStateAS;
	}

	public void setPriorityBackStateAS(List<String> priorityBackStateAS) {
		this.priorityBackStateAS = priorityBackStateAS;
	}

	public String getDictBackState() {
		return dictBackState;
	}

	public void setDictBackState(String dictBackState) {
		this.dictBackState = dictBackState;
	}

	public List<String> getDictBackStateList() {
		if (!ObjectHelper.isEmpty(dictBackState)) {
			dictBackStateList = Arrays.asList(dictBackState.split(","));
		}
		return dictBackStateList;
	}

	public void setDictBackStateList(List<String> dictBackStateList) {
		this.dictBackStateList = dictBackStateList;
	}

	public Date getBackMoneyDay() {
		return backMoneyDay;
	}

	public void setBackMoneyDay(Date backMoneyDay) {
		this.backMoneyDay = backMoneyDay;
	}

	public Date getBackMoneyDayTo() {
		return backMoneyDayTo;
	}

	public void setBackMoneyDayTo(Date backMoneyDayTo) {
		this.backMoneyDayTo = backMoneyDayTo;
	}

	public String getBackMoneyType() {
		return backMoneyType;
	}

	public void setBackMoneyType(String backMoneyType) {
		this.backMoneyType = backMoneyType;
	}

	public String getBackBank() {
		return backBank;
	}

	public void setBackBank(String backBank) {
		this.backBank = backBank;
	}

	public String getApplyAgreementEdition() {
		return applyAgreementEdition;
	}

	public void setApplyAgreementEdition(String applyAgreementEdition) {
		this.applyAgreementEdition = applyAgreementEdition;
	}

	public Date getApproveDate() {
		return approveDate;
	}

	public void setApproveDate(Date approveDate) {
		this.approveDate = approveDate;
	}

	public List<String> getPriorityIds() {
		if (!ObjectHelper.isEmpty(prioIds)) {
			priorityIds = Arrays.asList(prioIds.split(","));
		}
		
		return priorityIds;
	}

	public void setPriorityIds(List<String> priorityIds) {
		this.priorityIds = priorityIds;
	}

	public Date getApproveDateStart() {
		return approveDateStart;
	}

	public void setApproveDateStart(Date approveDateStart) {
		this.approveDateStart = approveDateStart;
	}

	public Date getApproveDateEnd() {
		return approveDateEnd;
	}

	public void setApproveDateEnd(Date approveDateEnd) {
		this.approveDateEnd = approveDateEnd;
	}
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public List<String> getCityList() {
		if (ObjectHelper.isEmpty(city)) {
			return null;
		}
		cityList = Arrays.asList(city.split(","));
		return cityList;
	}

	public void setCityList(List<String> cityList) {
		this.cityList = cityList;
	}
	
	public String getAccountCardOrBooklet() {
		return accountCardOrBooklet;
	}

	public void setAccountCardOrBooklet(String accountCardOrBooklet) {
		this.accountCardOrBooklet = accountCardOrBooklet;
	}

	public List<String> getCobList() {
		if (ObjectHelper.isEmpty(accountCardOrBooklet)) {
			return null;
		}
		cobList = Arrays.asList(accountCardOrBooklet.split(","));
		return cobList;
	}

	public void setCobList(List<String> cobList) {
		this.cobList = cobList;
	}
	
	public List<String> getEditionList() {
		if (ObjectHelper.isEmpty(applyAgreementEdition)) {
			return null;
		}
		editionList = Arrays.asList(applyAgreementEdition.split(","));
		return editionList;
	}

	public void setEditionList(List<String> editionList) {
		this.editionList = editionList;
	}
	
	public List<String> getBackMoneyTypeList() {
		if (ObjectHelper.isEmpty(backMoneyType)) {
			return null;
		}
		backMoneyTypeList = Arrays.asList(backMoneyType.split(","));
		return backMoneyTypeList;
	}

	public void setBackMoneyTypeList(List<String> backMoneyTypeList) {
		this.backMoneyTypeList = backMoneyTypeList;
	}
	
	public String getFindFlag() {
		return findFlag;
	}

	public void setFindFlag(String findFlag) {
		this.findFlag = findFlag;
	}

	public List<String> getFindFlagList() {
		if (ObjectHelper.isEmpty(findFlag)) {
			return null;
		}
		findFlagList = Arrays.asList(findFlag.split(","));
		return findFlagList;
	}

	public void setFindFlagList(List<String> findFlagList) {
		this.findFlagList = findFlagList;
	}
	public String getListFlag() {
		return listFlag;
	}

	public void setListFlag(String listFlag) {
		this.listFlag = listFlag;
	}

	public String getAccountAddrdistrict() {
		return accountAddrdistrict;
	}

	public void setAccountAddrdistrict(String accountAddrdistrict) {
		this.accountAddrdistrict = accountAddrdistrict;
	}

	public List<String> getPriorityBackStateList() {
		return priorityBackStateList;
	}

	public void setPriorityBackStateList(List<String> priorityBackStateList) {
		this.priorityBackStateList = priorityBackStateList;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getCreateTimeTo() {
		return createTimeTo;
	}

	public void setCreateTimeTo(Date createTimeTo) {
		this.createTimeTo = createTimeTo;
	}
	
}
