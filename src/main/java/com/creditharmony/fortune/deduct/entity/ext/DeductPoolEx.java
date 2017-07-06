package com.creditharmony.fortune.deduct.entity.ext;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.creditharmony.core.claim.util.CreditorUtils;
import com.creditharmony.core.persistence.DataEntity;

/**
 * 划扣申请扩展类model
 * @Class Name DeductPoolEx
 * @author 韩龙
 * @Create In 2015年11月27日
 */
public class DeductPoolEx extends DataEntity<DeductPoolEx> {

	private static final long serialVersionUID = 6389744963857576135L;
	// 划扣ID
	private String deductApplyId;
	// 客户ID
	private String custCode;
	// 模块标识
	private String applyOrApproveFalg;
	// 客户姓名
	private String custName;
	// 发送状态
	private String dictMatchingFilesendStatus;
	// 计划出借日开始
	private Date applyLendDateStart;
	// 计划出借日结束
	private Date applyLendDateEnd;
	// 计划出借日
	private Date applyLendDate;
	// 计划划扣日期开始
	private Date applyDeductDateStart;
	// 计划划扣日期结束
	private Date applyDeductDateEnd;
	// 计划划扣日期
	private Date applyDeductDate;
	// 营业部id
	private String checkNode;
	// 营业部名字
	private String orgName;
	// 计划出借金额开始
	private String applyLendMoneyStart;
	// 计划出借金额结束
	private String applyLendMoneyEnd;
	// 计划出借金额
	private String applyLendMoney;
	// 计划划扣金额开始
	private String applyDeductMoneyStart;
	// 计划划扣金额开始
	private String applyDeductMoneyEnd;
	// 计划划扣金额
	private String applyDeductMoney;
	// 支付方式
	private String applyPay;
	// 出借产品
	private String productCode;
	// 出借产品名字
	private String productName;
	// 划扣平台
	private String dictApplyDeductType;
	// 出借银行
	private String accountBank;
	// 出借银行卡号
	private String accountNo;
	// 出借编号
	private String applyCode;
	// 出借编号集合(用于前台全选或部分选存放出借编号)
	private List<String> applyCodes;
	// 划扣状态
	private String dictDeductStatus;
	// 划扣状态集合
	private List<String> deductStatusList;
	// 划扣状态
	private String dictDeductStatusOr;
	// 实际划扣金额或分天划扣金额
	private String actualDeductMoney;
	// 到期日期结束
	private Date applyExpireDateEnd;
	// 到期日期开始
	private Date applyExpireDateStart;
	// 到期日期
	private Date applyExpireDate;
	// 债权首期非首期
	private String matchingFirstdayFlag;
	// 账单日
	private String matchingBillDate;
	// 下个报告日
	private Date nextBillDate;
	// 下一个报告期借款人应还
	private String nextBorrowMoney;
	// 预计下一个报告日您的资产总额
	private String nextSumMoney;
	// 失败原因
	private String confirmOpinion;
	// 线上或线下划扣规则平台顺序
	private List<String> deductInterfaceType;
	// 划扣平台id集合
	private List<String> deductPlatFormID;
	// 债权推荐列表
	private List<CreditorTradeEx> creditorTradeExtList;
	// 审批结果
	private String confirmResult;
	// 分天划扣标识
	private String dayDeductFlag;
	// 出借银行查询集合
	private List<String> accountBankList;
	// 出借产品集合
	private List<String> productCodeList;
	// 划扣平台集合applyPay
	private List<String> dictApplyDeductTypeList;
	// 付款方式集合 productCode
	private List<String> applyPayList;
	// 营业部集合
	private List<String> checkNodeList;
	// 失败金额
	private String failMoney;
	// 未划扣金额
    private String noDeductMoney;
    // 金帐户名
    private String goldAccounName;
    // 资金状态
    private String goldAccountFlag;
    // 实际划扣时间
    private Date dealTime;
    // 预约时间
    private Date bespokeDate;
    // 预约划扣状态
    private String bespokeStatus;
    // 分天划扣成功金额
    private String deductSucceedMoney;
    // 分天划扣日期
    private Date deductValidityDate;
    // 当日是否跳转，是或否
    private String thedayJumpFlag;
    // 跳转金额
    private BigDecimal jumpAmount;
    // 累计失败金额
    private BigDecimal allDeductFailMoney;
    // 旧数据标识
    private String dataFlag;
    // 过滤sql
    private String filterSql;
    // 收款确认书邮件发送状态
    private String sendEmailStatus;
    // 收款确认书合成状态
    private String makeFlieStatus;
	public Date getDealTime() {
		return dealTime;
	}

	public void setDealTime(Date dealTime) {
		this.dealTime = dealTime;
	}

	public String getDeductApplyId() {
		return deductApplyId;
	}

	public void setDeductApplyId(String deductApplyId) {
		this.deductApplyId = deductApplyId;
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

	public String getDictMatchingFilesendStatus() {
		return dictMatchingFilesendStatus;
	}

	public void setDictMatchingFilesendStatus(String dictMatchingFilesendStatus) {
		this.dictMatchingFilesendStatus = dictMatchingFilesendStatus;
	}

	public Date getApplyLendDateStart() {
		return applyLendDateStart;
	}

	public void setApplyLendDateStart(Date applyLendDateStart) {
		this.applyLendDateStart = applyLendDateStart;
	}

	public Date getApplyLendDateEnd() {
		return applyLendDateEnd;
	}

	public void setApplyLendDateEnd(Date applyLendDateEnd) {
		this.applyLendDateEnd = applyLendDateEnd;
	}

	public Date getApplyLendDate() {
		return applyLendDate;
	}

	public void setApplyLendDate(Date applyLendDate) {
		this.applyLendDate = applyLendDate;
	}

	public Date getApplyDeductDateStart() {
		return applyDeductDateStart;
	}

	public void setApplyDeductDateStart(Date applyDeductDateStart) {
		this.applyDeductDateStart = applyDeductDateStart;
	}

	public Date getApplyDeductDateEnd() {
		return applyDeductDateEnd;
	}

	public void setApplyDeductDateEnd(Date applyDeductDateEnd) {
		this.applyDeductDateEnd = applyDeductDateEnd;
	}

	public Date getApplyDeductDate() {
		return applyDeductDate;
	}

	public void setApplyDeductDate(Date applyDeductDate) {
		this.applyDeductDate = applyDeductDate;
	}

	public String getCheckNode() {
		return checkNode;
	}

	public void setCheckNode(String checkNode) {
		this.checkNode = checkNode;
	}

	public String getApplyLendMoneyStart() {
		return applyLendMoneyStart;
	}

	public void setApplyLendMoneyStart(String applyLendMoneyStart) {
		this.applyLendMoneyStart = applyLendMoneyStart;
	}

	public String getApplyLendMoneyEnd() {
		return applyLendMoneyEnd;
	}

	public void setApplyLendMoneyEnd(String applyLendMoneyEnd) {
		this.applyLendMoneyEnd = applyLendMoneyEnd;
	}

	public String getApplyLendMoney() {
		return applyLendMoney;
	}

	public void setApplyLendMoney(String applyLendMoney) {
		this.applyLendMoney = applyLendMoney;
	}

	public String getApplyDeductMoneyStart() {
		return applyDeductMoneyStart;
	}

	public void setApplyDeductMoneyStart(String applyDeductMoneyStart) {
		this.applyDeductMoneyStart = applyDeductMoneyStart;
	}

	public String getApplyDeductMoneyEnd() {
		return applyDeductMoneyEnd;
	}

	public void setApplyDeductMoneyEnd(String applyDeductMoneyEnd) {
		this.applyDeductMoneyEnd = applyDeductMoneyEnd;
	}

	public String getApplyDeductMoney() {
		return applyDeductMoney;
	}

	public void setApplyDeductMoney(String applyDeductMoney) {
		this.applyDeductMoney = applyDeductMoney;
	}

	public String getApplyPay() {
		return applyPay;
	}

	public void setApplyPay(String applyPay) {
		this.applyPay = applyPay;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
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

	public String getApplyCode() {
		return applyCode;
	}

	public void setApplyCode(String applyCode) {
		this.applyCode = applyCode;
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

	public Date getApplyExpireDate() {
		return applyExpireDate;
	}

	public void setApplyExpireDate(Date applyExpireDate) {
		this.applyExpireDate = applyExpireDate;
	}

	public String getMatchingFirstdayFlag() {
		return matchingFirstdayFlag;
	}

	public void setMatchingFirstdayFlag(String matchingFirstdayFlag) {
		this.matchingFirstdayFlag = matchingFirstdayFlag;
	}

	public String getMatchingBillDate() {
		return matchingBillDate;
	}

	public void setMatchingBillDate(String matchingBillDate) {
		this.matchingBillDate = matchingBillDate;
	}

	public List<CreditorTradeEx> getCreditorTradeExtList() {
		return creditorTradeExtList;
	}

	public void setCreditorTradeExtList(List<CreditorTradeEx> creditorTradeExtList) {
		this.creditorTradeExtList = creditorTradeExtList;
	}

	public String getApplyOrApproveFalg() {
		return applyOrApproveFalg;
	}

	public void setApplyOrApproveFalg(String applyOrApproveFalg) {
		this.applyOrApproveFalg = applyOrApproveFalg;
	}

	public Date getApplyExpireDateEnd() {
		return applyExpireDateEnd;
	}

	public void setApplyExpireDateEnd(Date applyExpireDateEnd) {
		this.applyExpireDateEnd = applyExpireDateEnd;
	}

	public Date getApplyExpireDateStart() {
		return applyExpireDateStart;
	}

	public void setApplyExpireDateStart(Date applyExpireDateStart) {
		this.applyExpireDateStart = applyExpireDateStart;
	}

	public List<String> getDeductInterfaceType() {
		return deductInterfaceType;
	}

	public void setDeductInterfaceType(List<String> deductInterfaceType) {
		this.deductInterfaceType = deductInterfaceType;
	}

	public List<String> getDeductPlatFormID() {
		return deductPlatFormID;
	}

	public void setDeductPlatFormID(List<String> deductPlatFormID) {
		this.deductPlatFormID = deductPlatFormID;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getConfirmOpinion() {
		return confirmOpinion;
	}

	public void setConfirmOpinion(String confirmOpinion) {
		this.confirmOpinion = confirmOpinion;
	}

	public String getOrgName() {
		return orgName;
	}

	public List<String> getApplyCodes() {
		return applyCodes;
	}

	public void setApplyCodes(List<String> applyCodes) {
		this.applyCodes = applyCodes;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getDictDeductStatusOr() {
		return dictDeductStatusOr;
	}

	public void setDictDeductStatusOr(String dictDeductStatusOr) {
		this.dictDeductStatusOr = dictDeductStatusOr;
	}

	public Date getNextBillDate() {
		if(applyLendDate != null){
			applyLendDate = CreditorUtils.getCreditDaybyLendday(applyLendDate);
		}
		return applyLendDate;
	}

	public void setNextBillDate(Date nextBillDate) {
		this.nextBillDate = nextBillDate;
	}

	public String getNextBorrowMoney() {
		return nextBorrowMoney;
	}

	public void setNextBorrowMoney(String nextBorrowMoney) {
		this.nextBorrowMoney = nextBorrowMoney;
	}

	public String getNextSumMoney() {
		return nextSumMoney;
	}

	public void setNextSumMoney(String nextSumMoney) {
		this.nextSumMoney = nextSumMoney;
	}

	public String getConfirmResult() {
		return confirmResult;
	}

	public void setConfirmResult(String confirmResult) {
		this.confirmResult = confirmResult;
	}

	public String getDayDeductFlag() {
		return dayDeductFlag;
	}

	public void setDayDeductFlag(String dayDeductFlag) {
		this.dayDeductFlag = dayDeductFlag;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public List<String> getDeductStatusList() {
		return deductStatusList;
	}

	public void setDeductStatusList(List<String> deductStatusList) {
		this.deductStatusList = deductStatusList;
	}

	public List<String> getAccountBankList() {
		return accountBankList;
	}

	public void setAccountBankList(List<String> accountBankList) {
		this.accountBankList = accountBankList;
	}

	public List<String> getProductCodeList() {
		return productCodeList;
	}

	public void setProductCodeList(List<String> productCodeList) {
		this.productCodeList = productCodeList;
	}

	public List<String> getDictApplyDeductTypeList() {
		return dictApplyDeductTypeList;
	}

	public void setDictApplyDeductTypeList(List<String> dictApplyDeductTypeList) {
		this.dictApplyDeductTypeList = dictApplyDeductTypeList;
	}

	public List<String> getApplyPayList() {
		return applyPayList;
	}

	public void setApplyPayList(List<String> applyPayList) {
		this.applyPayList = applyPayList;
	}

	public List<String> getCheckNodeList() {
		return checkNodeList;
	}

	public void setCheckNodeList(List<String> checkNodeList) {
		this.checkNodeList = checkNodeList;
	}

	public String getFailMoney() {
		return failMoney;
	}

	public void setFailMoney(String failMoney) {
		this.failMoney = failMoney;
	}

	public String getNoDeductMoney() {
		return noDeductMoney;
	}

	public void setNoDeductMoney(String noDeductMoney) {
		this.noDeductMoney = noDeductMoney;
	}

	public String getGoldAccounName() {
		return goldAccounName;
	}

	public void setGoldAccounName(String goldAccounName) {
		this.goldAccounName = goldAccounName;
	}

	public String getGoldAccountFlag() {
		return goldAccountFlag;
	}

	public void setGoldAccountFlag(String goldAccountFlag) {
		this.goldAccountFlag = goldAccountFlag;
	}

	public Date getBespokeDate() {
		return bespokeDate;
	}

	public void setBespokeDate(Date bespokeDate) {
		this.bespokeDate = bespokeDate;
	}

	public String getDeductSucceedMoney() {
		return deductSucceedMoney;
	}

	public void setDeductSucceedMoney(String deductSucceedMoney) {
		this.deductSucceedMoney = deductSucceedMoney;
	}

	public String getBespokeStatus() {
		return bespokeStatus;
	}

	public void setBespokeStatus(String bespokeStatus) {
		this.bespokeStatus = bespokeStatus;
	}

	public Date getDeductValidityDate() {
		return deductValidityDate;
	}

	public void setDeductValidityDate(Date deductValidityDate) {
		this.deductValidityDate = deductValidityDate;
	}

	public String getThedayJumpFlag() {
		return thedayJumpFlag;
	}

	public void setThedayJumpFlag(String thedayJumpFlag) {
		this.thedayJumpFlag = thedayJumpFlag;
	}

	public BigDecimal getJumpAmount() {
		return jumpAmount;
	}

	public void setJumpAmount(BigDecimal jumpAmount) {
		this.jumpAmount = jumpAmount;
	}

	public BigDecimal getAllDeductFailMoney() {
		return allDeductFailMoney;
	}

	public void setAllDeductFailMoney(BigDecimal allDeductFailMoney) {
		this.allDeductFailMoney = allDeductFailMoney;
	}

	public String getDataFlag() {
		return dataFlag;
	}

	public void setDataFlag(String dataFlag) {
		this.dataFlag = dataFlag;
	}

	public String getFilterSql() {
		return filterSql;
	}

	public void setFilterSql(String filterSql) {
		this.filterSql = filterSql;
	}

	public String getSendEmailStatus() {
		return sendEmailStatus;
	}

	public void setSendEmailStatus(String sendEmailStatus) {
		this.sendEmailStatus = sendEmailStatus;
	}

	public String getMakeFlieStatus() {
		return makeFlieStatus;
	}

	public void setMakeFlieStatus(String makeFlieStatus) {
		this.makeFlieStatus = makeFlieStatus;
	}
}