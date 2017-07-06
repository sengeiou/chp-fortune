package com.creditharmony.fortune.back.money.common.view;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.creditharmony.common.util.ObjectHelper;
import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.fortune.type.PayMent;
import com.creditharmony.core.persistence.DataEntity;

/**
 * 回款列表封装Bean
 * 
 * @Class Name ListItemView
 * @author 陈广鹏
 * @Create In 2016年1月25日
 */
public class ListItemView extends DataEntity<ListItemView> {

	/**
	 * long serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	private String customerName; 		// 客户姓名
	private List<String> customerNameList; 		// 客户姓名
	private String customerCode; 		// 客户编号
	private List<String> customerCodeList; // 客户编号
	private String lendCode; 			// 出借编号
	private List<String> lendCodeList; 	// 客户编号
	private Date applyLendDay; 			// 计划出借日期
	private Date applyLendDayTo;
	private Date debtTransferDate; 		// 债转日
	private Date finalLinitDate; 		// 到期日期
	private Date finalLinitDateTo;
	private Date backMoneyDay; 			// 回款日期
	private Date backDay; 				// 回款日期（批量回款确认时使用）
	private Date backMoneyDayTo;
	private Date applyDeductDay; 		// 计划划扣日期
	private Date applyDeductDayTo;
	private Double applyLendMoney; 		// 计划出借金额
	private Double applyLendMoneyTo;
	private String productCode; 		// 出借产品Code
	private List<String> productList; 	// 出借产品列表
	private String productName; 		// 出借产品
	private String applyPay; 			// 付款方式
	private List<String> applyPayList;
	private String accountAddrprovince; // 省份
	private String accountAddrcity; 	// 城市
	private String faccountAddrprovince; // 金账户省份
	private String faccountAddrcity; 	// 金账户城市
	private String orgName; 			// 营业部
	private String orgID; 				// 营业部ID
	private List<String> orgList;
	private String manager; 			// 理财经理
	private String userCode; 			// 员工编号
	private Double backMoney; 			// 应回款金额
	private Double backActualbackMoney; // 实际回款金额
	private Double supplementedMoney; 	// 补息后回款金额
	private String dictBackStatus; 		// 回款状态
	private List<String> statusList;
	private List<String> statusAcList;	// 用于搜索条件的状态
	private String backMoneyType; 		// 回款类型
	private List<String> backMoneyTypeList;
	private String dictBackMoneyError; 	// 回款失败原因
	private String accountCardOrBooklet;// 卡或折
	private List<String> cobList;		// 卡或折
	private String applyAgreementEdition;	// 合同版本号
	private List<String> editionList;	// 合同版本号
	private String bank; 				// 放款银行
	private String userId; 				// 登录用户ID
	private String backmId; 			// 回款ID
	private List<String> backmIds; 		// 回款ID列表
	private String city; 				// 营业部所在城市
	private List<String> cityList; 		// 营业部所在城市
	private String checkExaminetype; 	// 审批结果
	private String isSupplemented; 	// 是否补息   
	private String checkExamine; 		// 退回原因
	private String checkReason; 		// 其它退回原因
	private String backResult; 			// 回盘结果
	private List<String> backResultList;
	private String backReason; 			// 失败原因
	private String backBank; 			// 放款银行
	private List<String> bankList;
	private Date backResultDay; 		// 回盘日期
	private String rebackFlag; 			// 退回重放标志
	private Double successMoney; 		// 成功金额
	private Double failMoney; 			// 失败金额
	private String platformId; 			// 回款平台
	private String platformIds; 		// 回款平台(回款确认列表查询条件)
	private String exPlatform; 			// 客户上次回款平台（筛选条件和列表数据）
	private List<String> platformList;	
	private List<String> exPlatformList;// 
	private String isJZH; 				// 是否为金账户数据(仅执行回款列表使用)
	private String exclude;				// 不包括的数据
	private String exportType;			// 导出文件类型
	private String interfaceType;		// 接口类型：0实时，1批量
	private String dictFortunechannelflag; 			// 渠道标识
	private List<String> channelList;
	private String listFlag; 			// 渠道标识
	private String findFlag; 		// 支行行号匹配标识
	private List<String> findFlagList;
	private Date approveDate;// 转投审批时间
	private String interestDay;// 补息天数
	private Date approveDateStart;
	private Date approveDateEnd;
	private String workingState;	//在职状态
	private List<String> workingStateAcList;	
	private String xinhebaoType; 	// 信和宝类型
	private int supplementedDays; 	// 回款补息天数
	private String isSplit; 		// 网银导出大于5万是否拆分
	/** 自转审批时间-起始 */
	private Date zzApproveDateStart;
	/** 自转审批时间-结束 */
	private Date zzApproveDateEnd;
	/** 自转审批时间 */
	private Date zzApproveDate;
	private String priorityBack;	//优先回款
	private int applyBillday;		//账单日
	private int productPeriods;		//产品期数
	
	
	public int getProductPeriods() {
		return productPeriods;
	}

	public void setProductPeriods(int productPeriods) {
		this.productPeriods = productPeriods;
	}

	public int getApplyBillday() {
		return applyBillday;
	}

	public void setApplyBillday(int applyBillday) {
		this.applyBillday = applyBillday;
	}

	public String getPriorityBack() {
		return priorityBack;
	}

	public void setPriorityBack(String priorityBack) {
		this.priorityBack = priorityBack;
	}

	public List<String> getworkingStateAcList() {
		if (!ObjectHelper.isEmpty(workingState)) {
			workingStateAcList = Arrays.asList(workingState.split(","));
		}
		return workingStateAcList;
	}

	public void setworkingStateAcList(List<String> workingStateAcList) {
		this.workingStateAcList = workingStateAcList;
	}

	public String getWorkingState() {
		return workingState;
	}

	public void setWorkingState(String workingState) {
		this.workingState = workingState;
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

	public Date getDebtTransferDate() {
		return debtTransferDate;
	}

	public void setDebtTransferDate(Date debtTransferDate) {
		this.debtTransferDate = debtTransferDate;
	}

	public Date getFinalLinitDate() {
		return finalLinitDate;
	}

	public void setFinalLinitDate(Date finalLinitDate) {
		this.finalLinitDate = finalLinitDate;
	}

	public Date getFinalLinitDateTo() {
		return finalLinitDateTo;
	}

	public void setFinalLinitDateTo(Date finalLinitDateTo) {
		this.finalLinitDateTo = finalLinitDateTo;
	}

	public Date getBackMoneyDay() {
		return backMoneyDay;
	}

	public void setBackMoneyDay(Date backMoneyDay) {
		this.backMoneyDay = backMoneyDay;
	}

	public Date getBackDay() {
		return backDay;
	}

	public void setBackDay(Date backDay) {
		this.backDay = backDay;
	}

	public Date getBackMoneyDayTo() {
		return backMoneyDayTo;
	}

	public void setBackMoneyDayTo(Date backMoneyDayTo) {
		this.backMoneyDayTo = backMoneyDayTo;
	}

	public Date getApplyDeductDay() {
		return applyDeductDay;
	}

	public void setApplyDeductDay(Date applyDeductDay) {
		this.applyDeductDay = applyDeductDay;
	}

	public Date getApplyDeductDayTo() {
		return applyDeductDayTo;
	}

	public void setApplyDeductDayTo(Date applyDeductDayTo) {
		this.applyDeductDayTo = applyDeductDayTo;
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

	public String getAccountAddrprovince() {
		if (StringUtils.isEmpty(accountAddrprovince)) {
			accountAddrprovince = faccountAddrprovince;
		}
		return accountAddrprovince;
	}

	public void setAccountAddrprovince(String accountAddrprovince) {
		this.accountAddrprovince = accountAddrprovince;
	}

	public String getAccountAddrcity() {
		if (StringUtils.isEmpty(accountAddrcity)) {
			accountAddrcity = faccountAddrcity;
		}
		return accountAddrcity;
	}

	public void setAccountAddrcity(String accountAddrcity) {
		this.accountAddrcity = accountAddrcity;
	}

	public String getFaccountAddrprovince() {
		return faccountAddrprovince;
	}

	public void setFaccountAddrprovince(String faccountAddrprovince) {
		this.faccountAddrprovince = faccountAddrprovince;
	}

	public String getFaccountAddrcity() {
		return faccountAddrcity;
	}

	public void setFaccountAddrcity(String faccountAddrcity) {
		this.faccountAddrcity = faccountAddrcity;
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

	public Double getBackMoney() {
		return backMoney;
	}

	public void setBackMoney(Double backMoney) {
		this.backMoney = backMoney;
	}

	public Double getBackActualbackMoney() {
		return backActualbackMoney;
	}

	public void setBackActualbackMoney(Double backActualbackMoney) {
		this.backActualbackMoney = backActualbackMoney;
	}

	public Double getSupplementedMoney() {
		return supplementedMoney;
	}

	public void setSupplementedMoney(Double supplementedMoney) {
		this.supplementedMoney = supplementedMoney;
	}

	public String getDictBackStatus() {
		return dictBackStatus;
	}

	public void setDictBackStatus(String dictBackStatus) {
		this.dictBackStatus = dictBackStatus;
	}

	public List<String> getStatusList() {
		return statusList;
	}

	public void setStatusList(List<String> statusList) {
		this.statusList = statusList;
	}

	public List<String> getStatusAcList() {
		if (!ObjectHelper.isEmpty(dictBackStatus)) {
			statusAcList = Arrays.asList(dictBackStatus.split(","));
		}
		return statusAcList;
	}

	public void setStatusAcList(List<String> statusAcList) {
		this.statusAcList = statusAcList;
	}

	public String getBackMoneyType() {
		return backMoneyType;
	}

	public void setBackMoneyType(String backMoneyType) {
		this.backMoneyType = backMoneyType;
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

	public String getDictBackMoneyError() {
		return dictBackMoneyError;
	}

	public void setDictBackMoneyError(String dictBackMoneyError) {
		this.dictBackMoneyError = dictBackMoneyError;
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

	public String getApplyAgreementEdition() {
		return applyAgreementEdition;
	}

	public void setApplyAgreementEdition(String applyAgreementEdition) {
		this.applyAgreementEdition = applyAgreementEdition;
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

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getBackmId() {
		return backmId;
	}

	public void setBackmId(String backmId) {
		this.backmId = backmId;
	}

	public List<String> getBackmIds() {
		return backmIds;
	}

	public void setBackmIds(List<String> backmIds) {
		this.backmIds = backmIds;
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

	public String getCheckExaminetype() {
		return checkExaminetype;
	}

	public void setCheckExaminetype(String checkExaminetype) {
		this.checkExaminetype = checkExaminetype;
	}

	public String getIsSupplemented() {
		return isSupplemented;
	}

	public void setIsSupplemented(String isSupplemented) {
		this.isSupplemented = isSupplemented;
	}

	public String getCheckExamine() {
		return checkExamine;
	}

	public void setCheckExamine(String checkExamine) {
		this.checkExamine = checkExamine;
	}

	public String getCheckReason() {
		return checkReason;
	}

	public void setCheckReason(String checkReason) {
		this.checkReason = checkReason;
	}

	public String getBackResult() {
		return backResult;
	}

	public void setBackResult(String backResult) {
		this.backResult = backResult;
	}

	public List<String> getBackResultList() {
		if (ObjectHelper.isEmpty(backResult)) {
			return null;
		}
		backResultList = Arrays.asList(backResult.split(","));
		return backResultList;
	}

	public void setBackResultList(List<String> backResultList) {
		this.backResultList = backResultList;
	}

	public String getBackReason() {
		return backReason;
	}

	public void setBackReason(String backReason) {
		this.backReason = backReason;
	}

	public String getBackBank() {
		return backBank;
	}

	public void setBackBank(String backBank) {
		this.backBank = backBank;
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

	public Date getBackResultDay() {
		return backResultDay;
	}

	public void setBackResultDay(Date backResultDay) {
		this.backResultDay = backResultDay;
	}

	public String getRebackFlag() {
		return rebackFlag;
	}

	public void setRebackFlag(String rebackFlag) {
		this.rebackFlag = rebackFlag;
	}

	public Double getSuccessMoney() {
		return successMoney;
	}

	public void setSuccessMoney(Double successMoney) {
		this.successMoney = successMoney;
	}

	public Double getFailMoney() {
		return failMoney;
	}

	public void setFailMoney(Double failMoney) {
		this.failMoney = failMoney;
	}

	public String getPlatformId() {
		return platformId;
	}

	public void setPlatformId(String platformId) {
		this.platformId = platformId;
	}

	public String getPlatformIds() {
		return platformIds;
	}

	public void setPlatformIds(String platformIds) {
		this.platformIds = platformIds;
	}

	public String getExPlatform() {
		return exPlatform;
	}

	public void setExPlatform(String exPlatform) {
		this.exPlatform = exPlatform;
	}

	public List<String> getPlatformList() {
		if (ObjectHelper.isEmpty(platformIds)) {
			return null;
		}
		platformList = Arrays.asList(platformIds.split(","));
		return platformList;
	}

	public void setPlatformList(List<String> platformList) {
		this.platformList = platformList;
	}

	public List<String> getExPlatformList() {
		if (ObjectHelper.isEmpty(exPlatform)) {
			return null;
		}
		exPlatformList = Arrays.asList(exPlatform.split(","));
		return exPlatformList;
	}

	public void setExPlatformList(List<String> exPlatformList) {
		this.exPlatformList = exPlatformList;
	}

	public String getIsJZH() {
		return isJZH;
	}

	public void setIsJZH(String isJZH) {
		this.isJZH = isJZH;
	}

	public String getExclude() {
		if (StringUtils.isEmpty(exclude)) {
			exclude = PayMent.ZJTG.value;
		}
		return exclude;
	}

	public void setExclude(String exclude) {
		this.exclude = exclude;
	}

	public String getExportType() {
		return exportType;
	}

	public void setExportType(String exportType) {
		this.exportType = exportType;
	}

	public String getInterfaceType() {
		return interfaceType;
	}

	public void setInterfaceType(String interfaceType) {
		this.interfaceType = interfaceType;
	}

	public String getDictFortunechannelflag() {
		return dictFortunechannelflag;
	}

	public void setDictFortunechannelflag(String dictFortunechannelflag) {
		this.dictFortunechannelflag = dictFortunechannelflag;
	}

	public List<String> getChannelList() {
		if (ObjectHelper.isEmpty(dictFortunechannelflag)) {
			return null;
		}
		channelList = Arrays.asList(dictFortunechannelflag.split(","));
		return channelList;
	}

	public void setChannelList(List<String> channelList) {
		this.channelList = channelList;
	}

	public String getListFlag() {
		return listFlag;
	}

	public void setListFlag(String listFlag) {
		this.listFlag = listFlag;
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
	public Date getApproveDate() {
		return approveDate;
	}

	public void setApproveDate(Date approveDate) {
		this.approveDate = approveDate;
	}

	public String getInterestDay() {
		return interestDay;
	}

	public void setInterestDay(String interestDay) {
		this.interestDay = interestDay;
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

	public String getXinhebaoType() {
		return xinhebaoType;
	}

	public void setXinhebaoType(String xinhebaoType) {
		this.xinhebaoType = xinhebaoType;
	}

	public int getSupplementedDays() {
		return supplementedDays;
	}

	public void setSupplementedDays(int supplementedDays) {
		this.supplementedDays = supplementedDays;
	}

	public String getIsSplit() {
		return isSplit;
	}

	public void setIsSplit(String isSplit) {
		this.isSplit = isSplit;
	}

	public Date getZzApproveDateStart() {
		return zzApproveDateStart;
	}

	public void setZzApproveDateStart(Date zzApproveDateStart) {
		this.zzApproveDateStart = zzApproveDateStart;
	}

	public Date getZzApproveDateEnd() {
		return zzApproveDateEnd;
	}

	public void setZzApproveDateEnd(Date zzApproveDateEnd) {
		this.zzApproveDateEnd = zzApproveDateEnd;
	}

	public Date getZzApproveDate() {
		return zzApproveDate;
	}

	public void setZzApproveDate(Date zzApproveDate) {
		this.zzApproveDate = zzApproveDate;
	}

}
