package com.creditharmony.fortune.customer.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.creditharmony.core.persistence.DataEntity;

/**
 * @Class Name LoanApply
 * @author 孙凯文
 * @Create In 2015年11月27日
 */
public class LoanApply extends DataEntity<LoanApply> {

	private static final long serialVersionUID = 1L;
	// 申请编号
	private String applyCode;
	// 客户编号
	private String custCode;
	// 客户姓名
	private String custName;
	// 申请日期
	private Date applyDate;
	// 划扣日期
	private Date deductDate;
	// 出借日期
	private Date lendDate;
	// 产品Code
	private String productCode;
	// 付款方式
	private String payType;
	// 划扣方式
	private String deductTypeId;
	// 状态
	private String status;
	// 出借金额
	private BigDecimal lendMoney;
	// 划扣金额
	private BigDecimal deductMoney;
	// 内部转账金额
	private BigDecimal transferMoney;
	// 内转出借编号
	private String transferLendId;
	// 协议版本
	private String protocoEdition;
	// 合同编号
	private String contractNumber;
	// 销售折扣率
	private String salesDiscount;
	// 账单日
	private Integer billDay;
	// 到期日
	private Date expireDate;
	// 出借状态
	private String lendStatus;
	// 来源版本
	private String sourceEdition;
	// 备注
	private String remark;
	// 完结状态
	private String applyEndStatus;
	// 交割标识
	private String isDelivery;
	// 理财经理
	private String managerCode;
	// 团队经理
	private String teamManagerCode;
	// 城市经理
	private String cityManagerCode;
	// 门店经理
	private String storeManagerCode;
	// 产品利率
	private BigDecimal productRate;
	// 回款银行ID
	private String receiveBankId;
	// 付款银行ID
	private String paymentBankId;
	// 流程ID
	private String applyId;
	// 客户信息实体
	private Customer customer;
	// 团队机构编号
	private String teamOrgId;
	// 营业部机构编号
	private String storeOrgId;
	// 城市机构编号
	private String cityOrgId;
	// 信和宝类型
	private String xinhebaoType;
	//	出借编号
	private String lendCode;
	// 旧出借编号
	private String lendCodeOld;
	// 页面显示：营业部名称
	private String storesName;
	// 页面显示:团队经理
	private String teamManagerName;
	// 页面显示：理财经理
	private String managerName;
	// 页面显示：团队名称
	private String teamName;
	// 页面显示：省份
	private String provinceId;
	// 页面显示：城市
	private String cityId;
	// 实际回款金额
	private BigDecimal actualBackMoney;
	// 回款状态
	private String backMoneyStatus;
	// 回款状态
	private String backMoneyType;
	// 回息状态
	private String matchingBackinterestStatus;
	// 本期回息日期
	private Date matchingBackinterestDay;
	// 托管状态
	private String tuoguanStatus;
	// 出借状态名
	private String lendStatusName;
	// 划扣天数
	private Integer applyDeductDays;
	// 补充协议版本
	private String applyProtocolVersion;
	// 补充协议版本号
	private String applyProtocolNo;
	// 金账户开户失败原因
	private String trusteeshipRetMsg;
	private Integer lendDay;
	private String applyPay; // 付款方式
	private String dictApplyDeductType; // 划扣平台
	private String accountBank; // 银行
	private String dictDeductStatus; // 划扣状态
	private String actualDeductMoney; // 划扣成功金额
	private String failMoney; // 划扣失败金额
	private String failReason; // 划扣失败原因
	private Date realDeductTime; // 实际划扣时间
	private String options; // 合同选项
	private String orderBy;
	private String check1; // 对应于出借申请页面的合同选项第5条
	private String check2; // 对应于出借申请页面的合同选项第9条
	private String validateMoney; // 有效可内转金额
	private BigDecimal backMoney;
	private BigDecimal deductFailMoney;
	private String matchingFlag;// 匹配标识
	private String channelMarking;// 渠道标识
	private String switchApproveStatus;// 转投审批状态
	private String DjrSwitchFlag;
	private String approveReason;// 审核信息
	private String cancelReason;// 撤销转投原因
	private String fortuneCentre;// 财富中心
	private Date approveDate;// 转投审批时间
	private String interestDay;// 补息天数
	
	public String getValidateMoney() {
		return validateMoney;
	}

	public void setValidateMoney(String validateMoney) {
		this.validateMoney = validateMoney;
	}

	public String getOptions() {
		return options;
	}

	public void setOptions(String options) {
		this.options = options;
	}

	public String getApplyProtocolVersion() {
		return applyProtocolVersion;
	}

	public void setApplyProtocolVersion(String applyProtocolVersion) {
		this.applyProtocolVersion = applyProtocolVersion;
	}

	public String getApplyProtocolNo() {
		return applyProtocolNo;
	}

	public void setApplyProtocolNo(String applyProtocolNo) {
		this.applyProtocolNo = applyProtocolNo;
	}

	public String getApplyPay() {
		return applyPay;
	}

	public void setApplyPay(String applyPay) {
		this.applyPay = applyPay;
	}

	public String getDictApplyDeductType() {
		return dictApplyDeductType;
	}

	public void setDictApplyDeductType(String dictApplyDeductType) {
		this.dictApplyDeductType = dictApplyDeductType;
	}

	public String getAccountBank() {
		return accountBank;
	}

	public void setAccountBank(String accountBank) {
		this.accountBank = accountBank;
	}

	public String getDictDeductStatus() {
		return dictDeductStatus;
	}

	public void setDictDeductStatus(String dictDeductStatus) {
		this.dictDeductStatus = dictDeductStatus;
	}

	public String getActualDeductMoney() {
		return actualDeductMoney;
	}

	public void setActualDeductMoney(String actualDeductMoney) {
		this.actualDeductMoney = actualDeductMoney;
	}

	public String getFailMoney() {
		return failMoney;
	}

	public void setFailMoney(String failMoney) {
		this.failMoney = failMoney;
	}

	public String getFailReason() {
		return failReason;
	}

	public void setFailReason(String failReason) {
		this.failReason = failReason;
	}

	public String getLendStatusName() {
		return lendStatusName;
	}

	public void setLendStatusName(String lendStatusName) {
		this.lendStatusName = lendStatusName;
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

	public Date getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}

	public Date getDeductDate() {
		return deductDate;
	}

	public void setDeductDate(Date deductDate) {
		this.deductDate = deductDate;
	}

	public Date getLendDate() {
		return lendDate;
	}

	public void setLendDate(Date lendDate) {
		this.lendDate = lendDate;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getDeductTypeId() {
		return deductTypeId;
	}

	public void setDeductTypeId(String deductTypeId) {
		this.deductTypeId = deductTypeId;
	}

	public BigDecimal getLendMoney() {
		return lendMoney;
	}

	public void setLendMoney(BigDecimal lendMoney) {
		this.lendMoney = lendMoney;
	}

	public BigDecimal getDeductMoney() {
		return deductMoney;
	}

	public void setDeductMoney(BigDecimal deductMoney) {
		this.deductMoney = deductMoney;
	}

	public BigDecimal getTransferMoney() {
		return transferMoney;
	}

	public void setTransferMoney(BigDecimal transferMoney) {
		this.transferMoney = transferMoney;
	}

	public String getProtocoEdition() {
		return protocoEdition;
	}

	public void setProtocoEdition(String protocoEdition) {
		this.protocoEdition = protocoEdition;
	}

	public String getContractNumber() {
		return contractNumber;
	}

	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
	}

	public String getSalesDiscount() {
		return salesDiscount;
	}

	public void setSalesDiscount(String salesDiscount) {
		this.salesDiscount = salesDiscount;
	}

	public Integer getBillDay() {
		return billDay;
	}

	public void setBillDay(Integer billDay) {
		this.billDay = billDay;
	}

	public Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

	public String getLendStatus() {
		return lendStatus;
	}

	public void setLendStatus(String lendStatus) {
		this.lendStatus = lendStatus;
	}

	public String getSourceEdition() {
		return sourceEdition;
	}

	public void setSourceEdition(String sourceEdition) {
		this.sourceEdition = sourceEdition;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getApplyEndStatus() {
		return applyEndStatus;
	}

	public void setApplyEndStatus(String applyEndStatus) {
		this.applyEndStatus = applyEndStatus;
	}

	public String getTransferLendId() {
		return transferLendId;
	}

	public void setTransferLendId(String transferLendId) {
		this.transferLendId = transferLendId;
	}

	public String getIsDelivery() {
		return isDelivery;
	}

	public void setIsDelivery(String isDelivery) {
		this.isDelivery = isDelivery;
	}

	public String getManagerCode() {
		return managerCode;
	}

	public void setManagerCode(String managerCode) {
		this.managerCode = managerCode;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getTeamManagerCode() {
		return teamManagerCode;
	}

	public void setTeamManagerCode(String teamManagerCode) {
		this.teamManagerCode = teamManagerCode;
	}

	public String getStoreManagerCode() {
		return storeManagerCode;
	}

	public void setStoreManagerCode(String storeManagerCode) {
		this.storeManagerCode = storeManagerCode;
	}

	public String getReceiveBankId() {
		return receiveBankId;
	}

	public void setReceiveBankId(String receiveBankId) {
		this.receiveBankId = receiveBankId;
	}

	public String getPaymentBankId() {
		return paymentBankId;
	}

	public void setPaymentBankId(String paymentBankId) {
		this.paymentBankId = paymentBankId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BigDecimal getProductRate() {
		return productRate;
	}

	public void setProductRate(BigDecimal productRate) {
		this.productRate = productRate;
	}

	public String getApplyId() {
		return applyId;
	}

	public void setApplyId(String applyId) {
		this.applyId = applyId;
	}

	public String getStoreOrgId() {
		return storeOrgId;
	}

	public String getTeamOrgId() {
		return teamOrgId;
	}

	public void setTeamOrgId(String teamOrgId) {
		this.teamOrgId = teamOrgId;
	}

	public void setStoreOrgId(String storeOrgId) {
		this.storeOrgId = storeOrgId;
	}

	public String getCityOrgId() {
		return cityOrgId;
	}

	public void setCityOrgId(String cityOrgId) {
		this.cityOrgId = cityOrgId;
	}

	public String getXinhebaoType() {
		return xinhebaoType;
	}

	public void setXinhebaoType(String xinhebaoType) {
		this.xinhebaoType = xinhebaoType;
	}

	public String getLendCodeOld() {
		return lendCodeOld;
	}

	public void setLendCodeOld(String lendCodeOld) {
		this.lendCodeOld = lendCodeOld;
	}

	public String getStoresName() {
		return storesName;
	}

	public void setStoresName(String storesName) {
		this.storesName = storesName;
	}

	public String getTeamManagerName() {
		return teamManagerName;
	}

	public void setTeamManagerName(String teamManagerName) {
		this.teamManagerName = teamManagerName;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getCityManagerCode() {
		return cityManagerCode;
	}

	public void setCityManagerCode(String cityManagerCode) {
		this.cityManagerCode = cityManagerCode;
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

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public BigDecimal getActualBackMoney() {
		return actualBackMoney;
	}

	public void setActualBackMoney(BigDecimal actualBackMoney) {
		this.actualBackMoney = actualBackMoney;
	}

	public String getBackMoneyStatus() {
		return backMoneyStatus;
	}

	public void setBackMoneyStatus(String backMoneyStatus) {
		this.backMoneyStatus = backMoneyStatus;
	}

	public String getBackMoneyType() {
		return backMoneyType;
	}

	public void setBackMoneyType(String backMoneyType) {
		this.backMoneyType = backMoneyType;
	}

	public String getMatchingBackinterestStatus() {
		return matchingBackinterestStatus;
	}

	public void setMatchingBackinterestStatus(String matchingBackinterestStatus) {
		this.matchingBackinterestStatus = matchingBackinterestStatus;
	}

	public Date getMatchingBackinterestDay() {
		return matchingBackinterestDay;
	}

	public void setMatchingBackinterestDay(Date matchingBackinterestDay) {
		this.matchingBackinterestDay = matchingBackinterestDay;
	}

	public String getTuoguanStatus() {
		return tuoguanStatus;
	}

	public void setTuoguanStatus(String tuoguanStatus) {
		this.tuoguanStatus = tuoguanStatus;
	}

	public Integer getApplyDeductDays() {
		return applyDeductDays;
	}

	public void setApplyDeductDays(Integer applyDeductDays) {
		this.applyDeductDays = applyDeductDays;
	}

	public String getTrusteeshipRetMsg() {
		return trusteeshipRetMsg;
	}

	public void setTrusteeshipRetMsg(String trusteeshipRetMsg) {
		this.trusteeshipRetMsg = trusteeshipRetMsg;
	}

	public Integer getLendDay() {
		return lendDay;
	}

	public void setLendDay(Integer lendDay) {
		this.lendDay = lendDay;
	}

	public Date getRealDeductTime() {
		return realDeductTime;
	}

	public void setRealDeductTime(Date realDeductTime) {
		this.realDeductTime = realDeductTime;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public String getCheck1() {
		return check1;
	}

	public void setCheck1(String check1) {
		this.check1 = check1;
	}

	public String getCheck2() {
		return check2;
	}

	public void setCheck2(String check2) {
		this.check2 = check2;
	}

	public BigDecimal getBackMoney() {
		return backMoney;
	}

	public void setBackMoney(BigDecimal backMoney) {
		this.backMoney = backMoney;
	}

	public BigDecimal getDeductFailMoney() {
		return deductFailMoney;
	}

	public void setDeductFailMoney(BigDecimal deductFailMoney) {
		this.deductFailMoney = deductFailMoney;
	}

	public String getMatchingFlag() {
		return matchingFlag;
	}

	public void setMatchingFlag(String matchingFlag) {
		this.matchingFlag = matchingFlag;
	}

	public String getChannelMarking() {
		return channelMarking;
	}

	public void setChannelMarking(String channelMarking) {
		this.channelMarking = channelMarking;
	}

	public String getSwitchApproveStatus() {
		return switchApproveStatus;
	}

	public void setSwitchApproveStatus(String switchApproveStatus) {
		this.switchApproveStatus = switchApproveStatus;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getDjrSwitchFlag() {
		return DjrSwitchFlag;
	}

	public void setDjrSwitchFlag(String djrSwitchFlag) {
		DjrSwitchFlag = djrSwitchFlag;
	}

	public String getApproveReason() {
		return approveReason;
	}

	public void setApproveReason(String approveReason) {
		this.approveReason = approveReason;
	}

	public String getCancelReason() {
		return cancelReason;
	}

	public void setCancelReason(String cancelReason) {
		this.cancelReason = cancelReason;
	}

	public String getFortuneCentre() {
		return fortuneCentre;
	}

	public void setFortuneCentre(String fortuneCentre) {
		this.fortuneCentre = fortuneCentre;
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

	public String getLendCode() {
		return lendCode;
	}

	public void setLendCode(String lendCode) {
		this.lendCode = lendCode;
	}

	
}
