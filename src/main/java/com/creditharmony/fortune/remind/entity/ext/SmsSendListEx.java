package com.creditharmony.fortune.remind.entity.ext;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.creditharmony.core.persistence.DataEntity;

/**
 * 消息提醒扩展实体
 * @Class Name SmsCfSendListExt
 * @author 韩龙
 * @Create In 2015年11月27日
 */
public class SmsSendListEx extends DataEntity<SmsSendListEx>{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 出借编号
    private String loanCode;
    // 出借编号集合
    private List<String> lendCodes;
    private List<String> applyPayList;
    private List<String> productCodeList;
    private List<String> orgIdList;
    private List<String> backStatusList;

	

	// 客户姓名
	private String customerName;
	// 客户编号
	private String customerCode;
	// 证件号码
	private String custCertNum;
	// 手机号
    private String tel;
    // 理财经理
    private String wealthManager;
    // 理财经理code
    private String managerCode;
    // 划扣日期
    private Date deductsDay;
    // 账单日
    private String billDay;
    // 出借金额
    private BigDecimal lendMoney;
    // 出借日期
    private Date lendDay;
    // 到期日期开始
    private Date dueDayStart;
    // 到期日期结束
    private Date dueDayEnd;
    
    // 到期日期开始
    private String dueDayStartchar;
    // 到期日期结束
    private String dueDayEndchar;
    
    

	// 到期日期
    private Date dueDay;
    private String dueDayFlag;
    // 出借方式
    private String dictLendType;
    // 回款日期
    private Date backMoneyDay;
    // 付款方式
    private String dictPayType;
    // 划扣状态
    private String dictDeductsStatus;
    // 银行账号
    private String bankCode;
    // 开户行
    private String bankName;
    // 短信模板ID
    private String smsId;
    // 发送状态
    private String operStatus;
    // 发送日期
    private Date sendDay;
    // 推送日期
    private Date pushDay;
    // 所在城市
    private String areaName;
    //所在城市回显
    private String areaNames;
    

	// 营业部
    private String orgName;
    // 营业部
    private String orgId;
    // 提醒类型
    private String dictRemindType;
    // 申请日期
    private String applyDay;
    // 封闭期满日开始
    private Date productClosedateStart;
    // 封闭期满日结束
    private Date productClosedateEnd;
    // 封闭期满日
    private String productClosedate;
    
    // 封闭期满日开始
    private String productClosedateStartchar;
    // 封闭期满日结束
    private String productClosedateEndchar;
  
	private Date productClosedates;
	
	private List<String> dictRemindTypeList;
    
  

	// 回款状态
    private String backStatus;
    /*--------------详细页面属性------------------------*/
    // 开户行
    private String accountBank;
    // 银行卡所在城市省
    private String accountAddrprovince;
    // 银行卡所在城市市
    private String accountAddrcity;
    // 银行卡所在区
    private String accountAddrdistrict;
    // 卡或折
    private String accountCardOrBooklet;
    // 具体支行 
    private String accountBranch;
    // 开户名称
    private String accountName;
    // 银行账户
    private String accountNo;
    // 出借申请日期
    private Date applyDate;
    // 出借划扣日期
    private Date applyDuctDate;
    // 计划出借日期
    private Date applyLendDate;
    // 出借模式(出借产品)
    private String productCode;
    // 协议版本号
    private String applyAgreementEition;
    // 销售折扣率
    private String applySalesDiscount;
    // 合同编号
    private String applyContractNo;
    // 划扣平台
    private String dictApplyDeductType;
    // 备注
    private String applyRemarks;
    // 计划出借金额
    private String applyLendMoney;
    // 划扣金额
    private String applyDeductMoney;
    // 内部转账出借编号
    private String fApplyCode;
    // 内部转账总金额
    private String applyTransferMoney;
    // 出借状态
    private String lendStatus;
    // 审批意见
    private String checkEamine;
    // 审批结果
    private String checkExaminetype;
    private String createBy;
    private Date createTime;
    private String lastmodifyBy;
    private Date lastmodifyTime;
    // 时间标识
    private Date newDate;
    
    //是否是首次提交
    private String firstShow;
    
    public String getFirstShow() {
		return firstShow;
	}

	public void setFirstShow(String firstShow) {
		this.firstShow = firstShow;
	}

	// 内部转账对应出借列表
    private List<SmsSendListEx> smsSendListEx;
    //信和宝类型
    private String xinheBaoType;

	public String getXinhebao_type() {
		return xinheBaoType;
	}

	public void setXinhebao_type(String xinheBaoType) {
		this.xinheBaoType = xinheBaoType;
	}

	public String getLoanCode() {
		return loanCode;
	}

	public void setLoanCode(String loanCode) {
		this.loanCode = loanCode;
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

	public String getCustCertNum() {
		return custCertNum;
	}

	public void setCustCertNum(String custCertNum) {
		this.custCertNum = custCertNum;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getWealthManager() {
		return wealthManager;
	}

	public void setWealthManager(String wealthManager) {
		this.wealthManager = wealthManager;
	}

	public Date getDeductsDay() {
		return deductsDay;
	}

	public void setDeductsDay(Date deductsDay) {
		this.deductsDay = deductsDay;
	}

	public String getBillDay() {
		return billDay;
	}

	public void setBillDay(String billDay) {
		this.billDay = billDay;
	}

	public BigDecimal getLendMoney() {
		return lendMoney;
	}

	public void setLendMoney(BigDecimal lendMoney) {
		this.lendMoney = lendMoney;
	}

	public Date getLendDay() {
		return lendDay;
	}

	public void setLendDay(Date lendDay) {
		this.lendDay = lendDay;
	}

	public Date getDueDayStart() {
		return dueDayStart;
	}

	public void setDueDayStart(Date dueDayStart) {
		this.dueDayStart = dueDayStart;
	}

	public Date getDueDayEnd() {
		return dueDayEnd;
	}

	public void setDueDayEnd(Date dueDayEnd) {
		this.dueDayEnd = dueDayEnd;
	}

	public Date getDueDay() {
		return dueDay;
	}

	public void setDueDay(Date dueDay) {
		this.dueDay = dueDay;
	}

	public String getLendType() {
		return dictLendType;
	}

	public void setLendType(String lendType) {
		this.dictLendType = lendType;
	}

	public Date getBackMoneyDay() {
		return backMoneyDay;
	}

	public void setBackMoneyDay(Date backMoneyDay) {
		this.backMoneyDay = backMoneyDay;
	}

	public String getPayType() {
		return dictPayType;
	}

	public void setPayType(String payType) {
		this.dictPayType = payType;
	}

	public String getDeductsStatus() {
		return dictDeductsStatus;
	}

	public void setDeductsStatus(String deductsStatus) {
		this.dictDeductsStatus = deductsStatus;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getSmsId() {
		return smsId;
	}

	public void setSmsId(String smsId) {
		this.smsId = smsId;
	}

	public String getOperStatus() {
		return operStatus;
	}

	public void setOperStatus(String operStatus) {
		this.operStatus = operStatus;
	}

	public Date getSendDay() {
		return sendDay;
	}

	public void setSendDay(Date sendDay) {
		this.sendDay = sendDay;
	}

	public Date getPushDay() {
		return pushDay;
	}

	public void setPushDay(Date pushDay) {
		this.pushDay = pushDay;
	}

	public String getAccountBank() {
		return accountBank;
	}

	public void setAccountBank(String accountBank) {
		this.accountBank = accountBank;
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

	public String getAccountAddrdistrict() {
		return accountAddrdistrict;
	}

	public void setAccountAddrdistrict(String accountAddrdistrict) {
		this.accountAddrdistrict = accountAddrdistrict;
	}

	public String getAccountCardOrBooklet() {
		return accountCardOrBooklet;
	}

	public void setAccountCardOrBooklet(String accountCardOrBooklet) {
		this.accountCardOrBooklet = accountCardOrBooklet;
	}

	public String getAccountBranch() {
		return accountBranch;
	}

	public void setAccountBranch(String accountBranch) {
		this.accountBranch = accountBranch;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public Date getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}

	public Date getApplyDuctDate() {
		return applyDuctDate;
	}

	public void setApplyDuctDate(Date applyDuctDate) {
		this.applyDuctDate = applyDuctDate;
	}

	public Date getApplyLendDate() {
		return applyLendDate;
	}

	public void setApplyLendDate(Date applyLendDate) {
		this.applyLendDate = applyLendDate;
	}

	public String getApplyAgreementEition() {
		return applyAgreementEition;
	}

	public void setApplyAgreementEition(String applyAgreementEition) {
		this.applyAgreementEition = applyAgreementEition;
	}

	public String getApplySalesDiscount() {
		return applySalesDiscount;
	}

	public void setApplySalesDiscount(String applySalesDiscount) {
		this.applySalesDiscount = applySalesDiscount;
	}

	public String getApplyContractNo() {
		return applyContractNo;
	}

	public void setApplyContractNo(String applyContractNo) {
		this.applyContractNo = applyContractNo;
	}

	public String getApplyDeductType() {
		return dictApplyDeductType;
	}

	public void setApplyDeductType(String applyDeductType) {
		this.dictApplyDeductType = applyDeductType;
	}

	public String getApplyRemarks() {
		return applyRemarks;
	}

	public void setApplyRemarks(String applyRemarks) {
		this.applyRemarks = applyRemarks;
	}

	public String getApplyLendMoney() {
		return applyLendMoney;
	}

	public void setApplyLendMoney(String applyLendMoney) {
		this.applyLendMoney = applyLendMoney;
	}

	public String getApplyDeductMoney() {
		return applyDeductMoney;
	}

	public void setApplyDeductMoney(String applyDeductMoney) {
		this.applyDeductMoney = applyDeductMoney;
	}

	public String getApplyTransferMoney() {
		return applyTransferMoney;
	}

	public void setApplyTransferMoney(String applyTransferMoney) {
		this.applyTransferMoney = applyTransferMoney;
	}

	public String getCheckEamine() {
		return checkEamine;
	}

	public void setCheckEamine(String checkEamine) {
		this.checkEamine = checkEamine;
	}

	public String getCheckExaminetype() {
		return checkExaminetype;
	}

	public void setCheckExaminetype(String checkExaminetype) {
		this.checkExaminetype = checkExaminetype;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getLastmodifyBy() {
		return lastmodifyBy;
	}

	public void setLastmodifyBy(String lastmodifyBy) {
		this.lastmodifyBy = lastmodifyBy;
	}

	public Date getLastmodifyTime() {
		return lastmodifyTime;
	}

	public void setLastmodifyTime(Date lastmodifyTime) {
		this.lastmodifyTime = lastmodifyTime;
	}

	public String getProductClosedate() {
		return productClosedate;
	}

	public void setProductClosedate(String productClosedate) {
		this.productClosedate = productClosedate;
	}

	public Date getProductClosedateStart() {
		return productClosedateStart;
	}

	public void setProductClosedateStart(Date productClosedateStart) {
		this.productClosedateStart = productClosedateStart;
	}

	public Date getProductClosedateEnd() {
		return productClosedateEnd;
	}

	public void setProductClosedateEnd(Date productClosedateEnd) {
		this.productClosedateEnd = productClosedateEnd;
	}

	public String getBackStatus() {
		return backStatus;
	}

	public void setBackStatus(String backStatus) {
		this.backStatus = backStatus;
	}

	public String getDictLendType() {
		return dictLendType;
	}

	public void setDictLendType(String dictLendType) {
		this.dictLendType = dictLendType;
	}

	public String getDictPayType() {
		return dictPayType;
	}

	public void setDictPayType(String dictPayType) {
		this.dictPayType = dictPayType;
	}

	public String getDictDeductsStatus() {
		return dictDeductsStatus;
	}

	public void setDictDeductsStatus(String dictDeductsStatus) {
		this.dictDeductsStatus = dictDeductsStatus;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getDictRemindType() {
		return dictRemindType;
	}

	public void setDictRemindType(String dictRemindType) {
		this.dictRemindType = dictRemindType;
	}

	public String getApplyDay() {
		return applyDay;
	}

	public void setApplyDay(String applyDay) {
		this.applyDay = applyDay;
	}

	public String getDictApplyDeductType() {
		return dictApplyDeductType;
	}

	public void setDictApplyDeductType(String dictApplyDeductType) {
		this.dictApplyDeductType = dictApplyDeductType;
	}

	public String getfApplyCode() {
		return fApplyCode;
	}

	public void setfApplyCode(String fApplyCode) {
		this.fApplyCode = fApplyCode;
	}

	public String getLendStatus() {
		return lendStatus;
	}

	public void setLendStatus(String lendStatus) {
		this.lendStatus = lendStatus;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public List<String> getLendCodes() {
		return lendCodes;
	}

	public List<String> getApplyPayList() {
		return applyPayList;
	}

	public void setApplyPayList(List<String> applyPayList) {
		this.applyPayList = applyPayList;
	}

	public List<String> getProductCodeList() {
		return productCodeList;
	}

	public void setProductCodeList(List<String> productCodeList) {
		this.productCodeList = productCodeList;
	}

	public void setLendCodes(List<String> lendCodes) {
		this.lendCodes = lendCodes;
	}

	public List<SmsSendListEx> getSmsSendListEx() {
		return smsSendListEx;
	}

	public void setSmsSendListEx(List<SmsSendListEx> smsSendListEx) {
		this.smsSendListEx = smsSendListEx;
	}

	public List<String> getOrgIdList() {
		return orgIdList;
	}

	public void setOrgIdList(List<String> orgIdList) {
		this.orgIdList = orgIdList;
	}

	public String getDueDayFlag() {
		return dueDayFlag;
	}

	public void setDueDayFlag(String dueDayFlag) {
		this.dueDayFlag = dueDayFlag;
	}
	public List<String> getBackStatusList() {
		return backStatusList;
	}

	public void setBackStatusList(List<String> backStatusList) {
		this.backStatusList = backStatusList;
	}

	public Date getProductClosedates() {
		return productClosedates;
	}

	public void setProductClosedates(Date productClosedates) {
		this.productClosedates = productClosedates;
	}

	public String getProductClosedateStartchar() {
		return productClosedateStartchar;
	}

	public void setProductClosedateStartchar(String productClosedateStartchar) {
		this.productClosedateStartchar = productClosedateStartchar;
	}

	public String getProductClosedateEndchar() {
		return productClosedateEndchar;
	}

	public void setProductClosedateEndchar(String productClosedateEndchar) {
		this.productClosedateEndchar = productClosedateEndchar;
	}


    public String getDueDayStartchar() {
		return dueDayStartchar;
	}

	public void setDueDayStartchar(String dueDayStartchar) {
		this.dueDayStartchar = dueDayStartchar;
	}

	public String getDueDayEndchar() {
		return dueDayEndchar;
	}

	public void setDueDayEndchar(String dueDayEndchar) {
		this.dueDayEndchar = dueDayEndchar;
	}
	public String getAreaNames() {
		return areaNames;
	}

	public void setAreaNames(String areaNames) {
		this.areaNames = areaNames;
	}

	public List<String> getDictRemindTypeList() {
		return dictRemindTypeList;
	}

	public void setDictRemindTypeList(List<String> dictRemindTypeList) {
		this.dictRemindTypeList = dictRemindTypeList;
	}

	public String getManagerCode() {
		return managerCode;
	}

	public void setManagerCode(String managerCode) {
		this.managerCode = managerCode;
	}

	public Date getNewDate() {
		return newDate;
	}

	public void setNewDate(Date newDate) {
		this.newDate = newDate;
	}
}