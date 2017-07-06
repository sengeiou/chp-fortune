package com.creditharmony.fortune.look.apply.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.formula.functions.T;

import com.creditharmony.core.persistence.DataEntity;

/**
 * 出借检索
 * @Class Name LendSearchObj 
 * @author 李志伟
 * @Create In 2016年1月21日
 */
public class LendSearchObj extends 	DataEntity<T>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// 客户姓名
	private String customerName;
	
	// 客户编号
	private String customerCode;
	
	// 合同编号
	private String applyContractNo;
	
	// 出借编号
	private String lendCode;
	
	private List<String> lendCodeList;
	
	// 营业部名称
	private String orgCode;
	private String orgName;
	
	// 联系电话
	private String customerTel;
	
	// 理财经理
	private String managerCode;
	
	// 城市名称
	private String cityId;
	
	// 出借产品
	private String productCode;
	
	// 账单日
	private String applyBillday;
	
	// 状态
	private String status;
	
	// 完结状态
	private String dictApplyEndState;
	
	// 出借状态
	private String lendStatus;
	
	// 付款方式
	private String applyPay;
	
	// 计划出借日期
	private Date applyLendDayFrom;
	private Date applyLendDayTo;
	
	// 计划划扣日
	private Date applyDeductDayFrom;
	private Date applyDeductDayTo;
	
	// 到期日
	private Date applyExpireDayFrom;
	private Date applyExpireDayTo;
	
	// 出借金额
	private BigDecimal applyLendMoneyFrom;
	private BigDecimal applyLendMoneyTo;
	
	// 审核人
	private String checkByName;
	private String tuoguanStatus;
	
	// 划扣平台
	private String dictApplyDeductType;
	// 出借银行
	private String accountBank;
	
	private String teamManagerName;  	//团队经理
	private Date backMoneyDayBegin; 	//回款开始时间
	private Date backMoneyDayEnd;  		// 回款截止时间
	private String   backMoeyType;		//回款状态

	private String customerLendingStatus; //投资状态
	private String customerTerminal;  //客户来源
	//划扣状态
	private String dictApplyDeductPay;
	// 数据权限
	private String dataRights;
	// 数据权限
	private String defaultOrderSql;
	// 数据权限
	private String dictFortunechannelflag;
	// 转投审批状态
	private String switchApproveStatus;
	// 转投审批状态
	private String workingState;	//在职状态
	/** 自转审批时间-起始 */
	private Date zzApproveDateStart;
	/** 自转审批时间-结束 */
	private Date zzApproveDateEnd;
	/** 自转审批时间 */
	private Date zzApproveDate; 
	//优先回款
	private String priorityBack;	

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

	public void setDefaultOrderSql(String defaultOrderSql) {
		this.defaultOrderSql = defaultOrderSql;
	}
	public String getDataRights() {
		return dataRights;
	}
	public void setDataRights(String dataRights) {
		this.dataRights = dataRights;
	}
	public String getDictApplyDeductPay() {
		return dictApplyDeductPay;
	}
	public void setDictApplyDeductPay(String dictApplyDeductPay) {
		this.dictApplyDeductPay = dictApplyDeductPay;
	}
	public String getCustomerTerminal() {
		return customerTerminal;
	}
	public void setCustomerTerminal(String customerTerminal) {
		this.customerTerminal = customerTerminal;
	}
	public String getCustomerLendingStatus() {
		return customerLendingStatus;
	}
	public void setCustomerLendingStatus(String customerLendingStatus) {
		this.customerLendingStatus = customerLendingStatus;
	}
	public String getTeamManagerName() {
		return teamManagerName;
	}
	public void setTeamManagerName(String teamManagerName) {
		this.teamManagerName = teamManagerName;
	}
	
	public Date getBackMoneyDayBegin() {
		return backMoneyDayBegin;
	}
	public void setBackMoneyDayBegin(Date backMoneyDayBegin) {
		this.backMoneyDayBegin = backMoneyDayBegin;
	}
	public Date getBackMoneyDayEnd() {
		return backMoneyDayEnd;
	}
	public void setBackMoneyDayEnd(Date backMoneyDayEnd) {
		this.backMoneyDayEnd = backMoneyDayEnd;
	}
	public String getCheckByName() {
		return checkByName;
	}
	public void setCheckByName(String checkByName) {
		this.checkByName = checkByName;
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
	public String getLendCode() {
		return lendCode;
	}
	public void setLendCode(String lendCode) {
		this.lendCode = lendCode;
	}
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public String getBackMoeyType() {
		return backMoeyType;
	}
	public void setBackMoeyType(String backMoeyType) {
		this.backMoeyType = backMoeyType;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getCustomerTel() {
		return customerTel;
	}
	public void setCustomerTel(String customerTel) {
		this.customerTel = customerTel;
	}
	public String getManagerCode() {
		return managerCode;
	}
	public void setManagerCode(String managerCode) {
		this.managerCode = managerCode;
	}
	public String getCityId() {
		return cityId;
	}
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	public String getApplyBillday() {
		return applyBillday;
	}
	public void setApplyBillday(String applyBillday) {
		this.applyBillday = applyBillday;
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
	public String getLendStatus() {
		return lendStatus;
	}
	public void setLendStatus(String lendStatus) {
		this.lendStatus = lendStatus;
	}
	public String getApplyPay() {
		return applyPay;
	}
	public void setApplyPay(String applyPay) {
		this.applyPay = applyPay;
	}
	public Date getApplyLendDayFrom() {
		return applyLendDayFrom;
	}
	public void setApplyLendDayFrom(Date applyLendDayFrom) {
		this.applyLendDayFrom = applyLendDayFrom;
	}
	public Date getApplyLendDayTo() {
		return applyLendDayTo;
	}
	public void setApplyLendDayTo(Date applyLendDayTo) {
		this.applyLendDayTo = applyLendDayTo;
	}
	public Date getApplyDeductDayFrom() {
		return applyDeductDayFrom;
	}
	public void setApplyDeductDayFrom(Date applyDeductDayFrom) {
		this.applyDeductDayFrom = applyDeductDayFrom;
	}
	public Date getApplyDeductDayTo() {
		return applyDeductDayTo;
	}
	public void setApplyDeductDayTo(Date applyDeductDayTo) {
		this.applyDeductDayTo = applyDeductDayTo;
	}
	
	
	public Date getApplyExpireDayFrom() {
		return applyExpireDayFrom;
	}
	public void setApplyExpireDayFrom(Date applyExpireDayFrom) {
		this.applyExpireDayFrom = applyExpireDayFrom;
	}
	public Date getApplyExpireDayTo() {
		return applyExpireDayTo;
	}
	public void setApplyExpireDayTo(Date applyExpireDayTo) {
		this.applyExpireDayTo = applyExpireDayTo;
	}
	
	public BigDecimal getApplyLendMoneyFrom() {
		return applyLendMoneyFrom;
	}
	public void setApplyLendMoneyFrom(BigDecimal applyLendMoneyFrom) {
		this.applyLendMoneyFrom = applyLendMoneyFrom;
	}
	public BigDecimal getApplyLendMoneyTo() {
		return applyLendMoneyTo;
	}
	public void setApplyLendMoneyTo(BigDecimal applyLendMoneyTo) {
		this.applyLendMoneyTo = applyLendMoneyTo;
	}
	public String getApplyContractNo() {
		return applyContractNo;
	}
	public void setApplyContractNo(String applyContractNo) {
		this.applyContractNo = applyContractNo;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getTuoguanStatus() {
		return tuoguanStatus;
	}
	public void setTuoguanStatus(String tuoguanStatus) {
		this.tuoguanStatus = tuoguanStatus;
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
	public List<String> getLendCodeList() {
		return lendCodeList;
	}
	public void setLendCodeList(List<String> lendCodeList) {
		this.lendCodeList = lendCodeList;
	}
	public String getDictFortunechannelflag() {
		return dictFortunechannelflag;
	}
	public void setDictFortunechannelflag(String dictFortunechannelflag) {
		this.dictFortunechannelflag = dictFortunechannelflag;
	}

	public String getSwitchApproveStatus() {
		return switchApproveStatus;
	}

	public void setSwitchApproveStatus(String switchApproveStatus) {
		this.switchApproveStatus = switchApproveStatus;
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