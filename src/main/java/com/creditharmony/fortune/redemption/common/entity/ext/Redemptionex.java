package com.creditharmony.fortune.redemption.common.entity.ext;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.creditharmony.common.util.ObjectHelper;
import com.creditharmony.core.fortune.type.OrgTypeRange;
import com.creditharmony.core.fortune.type.RedeemBackDeadline;
import com.creditharmony.core.fortune.type.RedeemCostType;
import com.creditharmony.core.persistence.DataEntity;

/**
 * 描述：赎回列表页扩展Bean
 * 
 * @Class Name Redemptionex
 * @author 陈广鹏
 * @Create In 2015年11月30日
 */
public class Redemptionex extends DataEntity<Redemptionex> {

	/**
	 * long serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	private String customerName; 		// 客户姓名
	private List<String> customerNameList; 
	private String customerCode; 		// 客户编号
	private List<String> customerCodeList;
	private String lendCode; 			// 出借编号
	private List<String> lendCodeList; 	
	private Date applyLendDay;			// 计划出借日期
	private Date applyLendDayTo;
	private Date applyDeductDay; 			// 计划划扣日期
	private Date applyDeductDayTo;
	private Date applyExpireDay;			// 到期日
	private Date applyExpireDayTo; 			// 到期日
	private String applyBillday; 			// 账单日
	private List<String> billdayList; 		// 账单日List
	private BigDecimal applyLendMoney; 		// 计划出借金额
	private BigDecimal applyLendMoneyTo; 
	private String applyPay; 				// 付款方式
	private List<String> applyPayList; 		// 付款方式
	private String dictApplyDeductType; 	// 划扣平台
	private String applyAgreementEdition; 	// 协议版本
	private String productName; 			// 出借产品名称
	private String productCode; 			// 出借产品ID(多)
	private List<String> productCodeList; 	// 出借产品
	private List<String> exProductList; 	// 不允许提前赎回的产品
	private String userName; 				// 理财经理姓名
	private String managerCode; 			// 理财经理ID
	private String teamManager; 			// 团队经理姓名
	private String teamManagerCode; 		// 团队经理ID
	private String addrProvince; 			// 通讯地址省
	private String addrCity; 				// 通讯地址市
	private List<String> cityList; 				// 通讯地址市
	private String orgName; 				// 机构名
	private String orgID; 					// 营业部(多)
	private List<String> orgList; 			// 营业部(多)
	private String redemptionStatus; 		// 赎回状态
	private List<String> redemptionStatusList;
	private String redemptionId; 			// 赎回申请ID
	private List<String> redemtptionIds;	// 赎回申请ID列表，
	private String userId; 					// 登录用户ID
	private Date checkTime; 				// 审批日期
	private Date checkTimeTo;
	private String redemptionType; 			// 赎回类型
	private Date redemptionTime; 			// 赎回时间
	private BigDecimal redemptionMoney; 	// 赎回金额
	private BigDecimal redemptionBmoney;	// 应回金额
	private BigDecimal redemptionDemoney;	// 应扣金额
	private Date linitDay;					// 赎回到期日期
	private String redemptionReceType;		// 回款期限
	private Date backMoneyDay;				// 回款日期
	private Date backMoneyDayTo;			// 回款日期
	private String checkSp; 				// 是否特批
	private String serviceFee; 				// 服务费率
	private String applyId; 				// 工作流ID
	private String lendStatus; 				// 出借状态，用于筛选列表
	private String status; 					// 出借申请表.状态，出借中、提前赎回、已到期
	private String dictApplyEndState; 		// 完结状态，用于筛选列表
	private BigDecimal redeemCost; 			// 扣费标准
	private String redeemBackDeadline; 		// 扣费标准
	private String limitApplyPay; 			// 特殊限制的付款方式
	private int limitDay; 					// 特殊限制的天数
	private String orgType; 				// 机构类型

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

	public String getApplyBillday() {
		return applyBillday;
	}

	public void setApplyBillday(String applyBillday) {
		this.applyBillday = applyBillday;
	}

	public List<String> getBilldayList() {
		if (ObjectHelper.isEmpty(applyBillday)) {
			return null;
		}
		billdayList = Arrays.asList(applyBillday.split(","));
		return billdayList;
	}

	public void setBilldayList(List<String> billdayList) {
		this.billdayList = billdayList;
	}

	public BigDecimal getApplyLendMoney() {
		return applyLendMoney;
	}

	public void setApplyLendMoney(BigDecimal applyLendMoney) {
		this.applyLendMoney = applyLendMoney;
	}

	public BigDecimal getApplyLendMoneyTo() {
		return applyLendMoneyTo;
	}

	public void setApplyLendMoneyTo(BigDecimal applyLendMoneyTo) {
		this.applyLendMoneyTo = applyLendMoneyTo;
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

	public String getDictApplyDeductType() {
		return dictApplyDeductType;
	}

	public void setDictApplyDeductType(String dictApplyDeductType) {
		this.dictApplyDeductType = dictApplyDeductType;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public List<String> getProductCodeList() {
		if (ObjectHelper.isEmpty(productCode)) {
			return null;
		}
		productCodeList = Arrays.asList(productCode.split(","));
		return productCodeList;
	}

	public void setProductCodeList(List<String> productCodeList) {
		this.productCodeList = productCodeList;
	}

	public List<String> getExProductList() {
		return exProductList;
	}

	public void setExProductList(List<String> exProductList) {
		this.exProductList = exProductList;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getManagerCode() {
		return managerCode;
	}

	public void setManagerCode(String managerCode) {
		this.managerCode = managerCode;
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

	public List<String> getCityList() {
		if (ObjectHelper.isEmpty(addrCity)) {
			return null;
		}
		cityList = Arrays.asList(addrCity.split(","));
		return cityList;
	}

	public void setCityList(List<String> cityList) {
		this.cityList = cityList;
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

	public String getRedemptionStatus() {
		return redemptionStatus;
	}

	public void setRedemptionStatus(String redemptionStatus) {
		this.redemptionStatus = redemptionStatus;
	}

	public List<String> getRedemptionStatusList() {
		if (!ObjectHelper.isEmpty(redemptionStatus)) {
			redemptionStatusList = Arrays.asList(redemptionStatus.split(","));
		}
		return redemptionStatusList;
	}

	public void setRedemptionStatusList(List<String> redemptionStatusList) {
		this.redemptionStatusList = redemptionStatusList;
	}

	public String getRedemptionId() {
		return redemptionId;
	}

	public void setRedemptionId(String redemptionId) {
		this.redemptionId = redemptionId;
	}

	public List<String> getRedemtptionIds() {
		return redemtptionIds;
	}

	public void setRedemtptionIds(List<String> redemtptionIds) {
		this.redemtptionIds = redemtptionIds;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public String getRedemptionType() {
		return redemptionType;
	}

	public void setRedemptionType(String redemptionType) {
		this.redemptionType = redemptionType;
	}

	public String getRedemptionReceType() {
		return redemptionReceType;
	}

	public void setRedemptionReceType(String redemptionReceType) {
		this.redemptionReceType = redemptionReceType;
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

	public String getCheckSp() {
		return checkSp;
	}

	public void setCheckSp(String checkSp) {
		this.checkSp = checkSp;
	}

	public String getServiceFee() {
		return serviceFee;
	}

	public void setServiceFee(String serviceFee) {
		this.serviceFee = serviceFee;
	}

	public String getApplyId() {
		return applyId;
	}

	public void setApplyId(String applyId) {
		this.applyId = applyId;
	}

	public String getApplyAgreementEdition() {
		return applyAgreementEdition;
	}

	public void setApplyAgreementEdition(String applyAgreementEdition) {
		this.applyAgreementEdition = applyAgreementEdition;
	}

	public String getTeamManager() {
		return teamManager;
	}

	public void setTeamManager(String teamManager) {
		this.teamManager = teamManager;
	}

	public String getTeamManagerCode() {
		return teamManagerCode;
	}

	public void setTeamManagerCode(String teamManagerCode) {
		this.teamManagerCode = teamManagerCode;
	}

	public Date getRedemptionTime() {
		return redemptionTime;
	}

	public void setRedemptionTime(Date redemptionTime) {
		this.redemptionTime = redemptionTime;
	}

	public BigDecimal getRedemptionMoney() {
		return redemptionMoney;
	}

	public void setRedemptionMoney(BigDecimal redemptionMoney) {
		this.redemptionMoney = redemptionMoney;
	}

	public BigDecimal getRedemptionBmoney() {
		return redemptionBmoney;
	}

	public void setRedemptionBmoney(BigDecimal redemptionBmoney) {
		this.redemptionBmoney = redemptionBmoney;
	}

	public BigDecimal getRedemptionDemoney() {
		return redemptionDemoney;
	}

	public void setRedemptionDemoney(BigDecimal redemptionDemoney) {
		this.redemptionDemoney = redemptionDemoney;
	}

	public Date getLinitDay() {
		return linitDay;
	}

	public void setLinitDay(Date linitDay) {
		this.linitDay = linitDay;
	}

	public String getLendStatus() {
		return lendStatus;
	}

	public void setLendStatus(String lendStatus) {
		this.lendStatus = lendStatus;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDictApplyEndState() {
		return dictApplyEndState;
	}

	public void setDictApplyEndState(String dictApplyEndState) {
		this.dictApplyEndState = dictApplyEndState;
	}

	public BigDecimal getRedeemCost() {
		redeemCost = null;
		
		if (null != redemptionReceType) {
			redeemCost = RedeemCostType.parseByCode(redemptionReceType).getValue();
		}
		return redeemCost;
	}

	public void setRedeemCost(BigDecimal redeemCost) {
		this.redeemCost = redeemCost;
	}

	public String getRedeemBackDeadline() {
		redeemBackDeadline = null;
		if (null != redemptionReceType) {
			redeemBackDeadline = RedeemBackDeadline.parseByCode(redemptionReceType).getValue();
		}
		
		return redeemBackDeadline;
	}

	public void setRedeemBackDeadline(String redeemBackDeadline) {
		this.redeemBackDeadline = redeemBackDeadline;
	}

	public String getLimitApplyPay() {
		return limitApplyPay;
	}

	public void setLimitApplyPay(String limitApplyPay) {
		this.limitApplyPay = limitApplyPay;
	}

	public int getLimitDay() {
		return limitDay;
	}

	public void setLimitDay(int limitDay) {
		this.limitDay = limitDay;
	}

	public String getOrgType() {
		orgType = OrgTypeRange.CENTER.getCode();
		return orgType;
	}

	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}

}
