package com.creditharmony.fortune.look.apply.view;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.creditharmony.core.persistence.DataEntity;

/**
 * 出借申请查看和出借申请审批查看列表对象
 * @Class Name LendApplyLookListView 
 * @author Mr
 * @Create In 2016年6月16日
 */
public class LendApplyLookListView extends DataEntity<LendApplyLookListView> implements Serializable{

	/**
	 * 31
	 */
	private static final long serialVersionUID = 1L;
	
	// 客户姓名
	private String customerName; 
	// 客户编号
	private String customerCode;
	// 托管状态
	private String applyHostedStatus; 
	// 出借编号
	private String lendCode;
	// 计划出借日期
	private Date applyLendDay;
	// 账单日
	private Integer applyBillday;
	// 到期日期
	private Date applyExpireDay;
	// 计划出借金额
	private String applyLendMoney;
	// 出借状态
	private String lendStatus;
	// 状态
	private String status;
	// 完结状态
	private String dictApplyEndState;
	// 产品类型名称
	private String productTypeName;
	// 团队经理
	private String teamName;
	// 理财经理
	private String managerName;
	// 营业部
	private String orgName;
	// (非金账户)省份
	private String provinceName;
	// (非金账户)城市
	private String cityName;
	// (金账户)省份
	private String jzhProvince;
	// (金账户)城市
	private String jzhCity;
	// 开发团队
	private String developTeam;
	// 出借产品
	private String productName;
	// 付款方式
	private String applyPay;
	// 划扣平台
	private String dictApplyDeductType;
	// 银行
	private String accountBank;
	// 实际划扣金额
	private String actualDeductMoney;
	// 失败金额
	private String failMoney;
	// 失败原因
	private String failReason;
	// 金账户返回内容
	private String trusteeshipRetMsg;
	// 用户表编号
	private String id;
	// 信和宝类型
	private String xinhebaoType;
	// 回款状态
	private String dictBackStatus;
	// 回款日期
	private Date backMoneyDay;
	// 出借天数
	private Integer applyLendDayNum;
	//渠道标识
	private String dictFortunechannelflag;
	// 转投审批状态
	private String switchApproveStatus;
	private String DjrSwitchFlag;
	private String backMoneyType;
	//在职状态
	private String  workingState;
	/** 自转审核日期 */
	private Date zzApproveDate;
	//优先回款
	private  String priorityBack;
	//是否优先回款状态 
	private String priorityState;
	//优先回款状态
	private String priorityBackState;
	//控制优先回款按钮的显示
	private String switchPriorityState;
	private String userManagerId;
	//优先回款id
	private String priorityId;
	
	
	public String getUserManagerId() {
		return userManagerId;
	}
	public void setUserManagerId(String userManagerId) {
		this.userManagerId = userManagerId;
	}
	public String getPriorityId() {
		return priorityId;
	}
	public void setPriorityId(String priorityId) {
		this.priorityId = priorityId;
	}
	public String getPriorityBackState() {
		return priorityBackState;
	}
	public void setPriorityBackState(String priorityBackState) {
		this.priorityBackState = priorityBackState;
	}
	public String getPriorityState() {
		return priorityState;
	}
	public void setPriorityState(String priorityState) {
		this.priorityState = priorityState;
	}
	public String getSwitchPriorityState() {
		return switchPriorityState;
	}
	public void setSwitchPriorityState(String switchPriorityState) {
		this.switchPriorityState = switchPriorityState;
	}
	public String getPriorityBack() {
		return priorityBack;
	}
	public void setPriorityBack(String priorityBack) {
		this.priorityBack = priorityBack;
	}
	public String getWorkingState() {
		return workingState;
	}
	public void setWorkingState(String workingState) {
		this.workingState = workingState;
	}
	public Integer getApplyLendDayNum() {
		return applyLendDayNum;
	}
	public void setApplyLendDayNum(Integer applyLendDayNum) {
		this.applyLendDayNum = applyLendDayNum;
	}
	public String getDictFortunechannelflag() {
		return dictFortunechannelflag;
	}
	public void setDictFortunechannelflag(String dictFortunechannelflag) {
		this.dictFortunechannelflag = dictFortunechannelflag;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerCode() {
		return customerCode;
	}
	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}
	public String getApplyHostedStatus() {
		return applyHostedStatus;
	}
	public void setApplyHostedStatus(String applyHostedStatus) {
		this.applyHostedStatus = applyHostedStatus;
	}
	public String getLendCode() {
		return lendCode;
	}
	public void setLendCode(String lendCode) {
		this.lendCode = lendCode;
	}
	public String getApplyLendMoney() {
		return applyLendMoney;
	}
	public void setApplyLendMoney(String applyLendMoney) {
		this.applyLendMoney = applyLendMoney;
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
	public String getProductTypeName() {
		return productTypeName;
	}
	public void setProductTypeName(String productTypeName) {
		this.productTypeName = productTypeName;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getProvinceName() {
		if(StringUtils.isNotBlank(provinceName)){
			return provinceName;
		}
		return jzhProvince;
	}
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	public String getCityName() {
		if(StringUtils.isNotBlank(cityName)){
			return cityName;
		}
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getDevelopTeam() {
		return developTeam;
	}
	public void setDevelopTeam(String developTeam) {
		this.developTeam = developTeam;
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
	public String getDictApplyDeductType() {
		return dictApplyDeductType;
	}
	public void setDictApplyDeductType(String dictApplyDeductType) {
		this.dictApplyDeductType = dictApplyDeductType;
	}
	public String getAccountBank() {
		return accountBank;
	}
	public String getJzhProvince() {
		return jzhProvince;
	}
	public void setJzhProvince(String jzhProvince) {
		this.jzhProvince = jzhProvince;
	}
	public String getJzhCity() {
		return jzhCity;
	}
	public void setJzhCity(String jzhCity) {
		this.jzhCity = jzhCity;
	}
	public void setAccountBank(String accountBank) {
		this.accountBank = accountBank;
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
	public String getTrusteeshipRetMsg() {
		return trusteeshipRetMsg;
	}
	public void setTrusteeshipRetMsg(String trusteeshipRetMsg) {
		this.trusteeshipRetMsg = trusteeshipRetMsg;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getXinhebaoType() {
		return xinhebaoType;
	}
	public void setXinhebaoType(String xinhebaoType) {
		this.xinhebaoType = xinhebaoType;
	}
	public String getDictBackStatus() {
		return dictBackStatus;
	}
	public void setDictBackStatus(String dictBackStatus) {
		this.dictBackStatus = dictBackStatus;
	}
	public Date getApplyLendDay() {
		return applyLendDay;
	}
	public void setApplyLendDay(Date applyLendDay) {
		this.applyLendDay = applyLendDay;
	}
	public Integer getApplyBillday() {
		return applyBillday;
	}
	public void setApplyBillday(Integer applyBillday) {
		this.applyBillday = applyBillday;
	}
	public Date getApplyExpireDay() {
		return applyExpireDay;
	}
	public void setApplyExpireDay(Date applyExpireDay) {
		this.applyExpireDay = applyExpireDay;
	}
	public Date getBackMoneyDay() {
		return backMoneyDay;
	}
	public void setBackMoneyDay(Date backMoneyDay) {
		this.backMoneyDay = backMoneyDay;
	}
	public String getSwitchApproveStatus() {
		return switchApproveStatus;
	}
	public void setSwitchApproveStatus(String switchApproveStatus) {
		this.switchApproveStatus = switchApproveStatus;
	}
	public String getDjrSwitchFlag() {
		return DjrSwitchFlag;
	}
	public void setDjrSwitchFlag(String djrSwitchFlag) {
		DjrSwitchFlag = djrSwitchFlag;
	}

	public String getBackMoneyType() {
		return backMoneyType;
	}

	public void setBackMoneyType(String backMoneyType) {
		this.backMoneyType = backMoneyType;
	}
	public Date getZzApproveDate() {
		return zzApproveDate;
	}
	public void setZzApproveDate(Date zzApproveDate) {
		this.zzApproveDate = zzApproveDate;
	}
	
}
