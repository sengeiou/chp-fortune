package com.creditharmony.fortune.back.money.common.entity.ext;

import java.math.BigDecimal;
import java.util.Date;

import com.creditharmony.core.persistence.DataEntity;

/**
 * 列表导出封装Bean
 * 
 * @Class Name ExportBean
 * @author 陈广鹏
 * @Create In 2015年12月25日
 */
public class ExportBean extends DataEntity<ExportBean> {

	/**
	 * long serialVersionUID 
	 */
	private static final long serialVersionUID = 3663389207164300429L;
	private String customerName;			// 客户姓名
	private String customerCode;			// 客户编号
	private String dictCustomerCertType;	// 证件类型
	private String customerCertNum;			// 证件号码
	private String customerMobilephone;		// 手机号码
	private String customerEmail;			// 电子邮箱
	private String lendCode;				// 出借编号
	private String applyLendDay;			// 计划出借日期
	private Double applyLendMoney;			// 计划出借金额
	private String apply_billday;			// 账单日
	private String productName;				// 产品类型
	private String trusteeshipNo;			// 金账户账号
	private String applyAgreementEdition;	// 协议版本号
	private Double applyTransferMoney;		// 内转金额
	private String fApplyCode;				// 内转出借编号
	private Date finalLinitDate;			// 到期日期
	private String manager;					// 理财经理
	private String orgName;					// 营业部
	private String city;					// 城市
	private String accountAddrprovince;		// 省份
	private String accountAddrcity;			// 城市
	private String accountBank;				// 回款开户行，银行代码，例：102，103
	private String accountBranch;			// 回款支行名称，汉字
	private String branchCode; 				// 支行代码
	private String accountNo;				// 账号
	private String accountName;				// 开户姓名
	private String backmId;					// 回款ID
	private Double backMoney;				// 应回款金额
	private BigDecimal backActualbackMoney;	// 实际回款金额
	private BigDecimal supplementedMoney;	// 补息后回款金额
	private String isSupplemented;			// 是否补息
	private String backMoneyType;			// 回款类型
	private String dictBackStatus;			// 回款状态
	private String backMoneyRemarks;		// 回款备注
	private Date backMoneyDay;				// 回款日期
	private String applyPay;				// 付款方式
	private String platformId;				// 回款平台ID
	private String accountCardOrBooklet;	// 卡或折
	private String redemptionReceType;		// 回款期限
	private BigDecimal suceessMoney;		// 回款成功金额
	private BigDecimal feedbackMoney;		// 客户回馈金额
	private String feedbackRemark;			// 回馈事项备注
	private String interfaceType;			// 接口类型
	private String workingState;			//在职状态
	private String zzApproveDate;			//自转审批日期
	private String srLendcode;				//受让人出借编号
	private String srrCustomerName;			//受让人姓名
	
	public String getWorkingState() {
		return workingState;
	}

	public void setWorkingState(String workingState) {
		this.workingState = workingState;
	}

	public String getIsSupplemented() {
		return isSupplemented;
	}

	public void setIsSupplemented(String isSupplemented) {
		this.isSupplemented = isSupplemented;
	}

	public String getInterfaceType() {
		return interfaceType;
	}

	public void setInterfaceType(String interfaceType) {
		this.interfaceType = interfaceType;
	}

	public BigDecimal getSuceessMoney() {
		return suceessMoney;
	}

	public void setSuceessMoney(BigDecimal suceessMoney) {
		this.suceessMoney = suceessMoney;
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

	public String getDictCustomerCertType() {
		return dictCustomerCertType;
	}

	public void setDictCustomerCertType(String dictCustomerCertType) {
		this.dictCustomerCertType = dictCustomerCertType;
	}

	public String getCustomerCertNum() {
		return customerCertNum;
	}

	public void setCustomerCertNum(String customerCertNum) {
		this.customerCertNum = customerCertNum;
	}

	public String getCustomerMobilephone() {
		return customerMobilephone;
	}

	public void setCustomerMobilephone(String customerMobilephone) {
		this.customerMobilephone = customerMobilephone;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public String getLendCode() {
		return lendCode;
	}

	public void setLendCode(String lendCode) {
		this.lendCode = lendCode;
	}

	public String getApplyLendDay() {
		return applyLendDay;
	}

	public void setApplyLendDay(String applyLendDay) {
		this.applyLendDay = applyLendDay;
	}

	public Double getApplyLendMoney() {
		return applyLendMoney;
	}

	public void setApplyLendMoney(Double applyLendMoney) {
		this.applyLendMoney = applyLendMoney;
	}

	public String getApply_billday() {
		return apply_billday;
	}

	public void setApply_billday(String apply_billday) {
		this.apply_billday = apply_billday;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getTrusteeshipNo() {
		return trusteeshipNo;
	}

	public void setTrusteeshipNo(String trusteeshipNo) {
		this.trusteeshipNo = trusteeshipNo;
	}

	public String getApplyAgreementEdition() {
		return applyAgreementEdition;
	}

	public void setApplyAgreementEdition(String applyAgreementEdition) {
		this.applyAgreementEdition = applyAgreementEdition;
	}

	public Double getApplyTransferMoney() {
		return applyTransferMoney;
	}

	public void setApplyTransferMoney(Double applyTransferMoney) {
		this.applyTransferMoney = applyTransferMoney;
	}

	public String getfApplyCode() {
		return fApplyCode;
	}

	public void setfApplyCode(String fApplyCode) {
		this.fApplyCode = fApplyCode;
	}

	public Date getFinalLinitDate() {
		return finalLinitDate;
	}

	public void setFinalLinitDate(Date finalLinitDate) {
		this.finalLinitDate = finalLinitDate;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
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

	public String getAccountBank() {
		return accountBank;
	}

	public void setAccountBank(String accountBank) {
		this.accountBank = accountBank;
	}

	public String getAccountBranch() {
		return accountBranch;
	}

	public void setAccountBranch(String accountBranch) {
		this.accountBranch = accountBranch;
	}

	public String getBranchCode() {
		return branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getBackmId() {
		return backmId;
	}

	public void setBackmId(String backmId) {
		this.backmId = backmId;
	}

	public Double getBackMoney() {
		return backMoney;
	}

	public void setBackMoney(Double backMoney) {
		this.backMoney = backMoney;
	}

	public BigDecimal getBackActualbackMoney() {
		return backActualbackMoney;
	}

	public void setBackActualbackMoney(BigDecimal backActualbackMoney) {
		this.backActualbackMoney = backActualbackMoney;
	}

	public String getBackMoneyType() {
		return backMoneyType;
	}

	public void setBackMoneyType(String backMoneyType) {
		this.backMoneyType = backMoneyType;
	}

	public String getDictBackStatus() {
		return dictBackStatus;
	}

	public void setDictBackStatus(String dictBackStatus) {
		this.dictBackStatus = dictBackStatus;
	}

	public String getBackMoneyRemarks() {
		return backMoneyRemarks;
	}

	public void setBackMoneyRemarks(String backMoneyRemarks) {
		this.backMoneyRemarks = backMoneyRemarks;
	}

	public Date getBackMoneyDay() {
		return backMoneyDay;
	}

	public void setBackMoneyDay(Date backMoneyDay) {
		this.backMoneyDay = backMoneyDay;
	}

	public String getApplyPay() {
		return applyPay;
	}

	public void setApplyPay(String applyPay) {
		this.applyPay = applyPay;
	}

	public String getPlatformId() {
		return platformId;
	}

	public void setPlatformId(String platformId) {
		this.platformId = platformId;
	}

	public String getAccountCardOrBooklet() {
		return accountCardOrBooklet;
	}

	public void setAccountCardOrBooklet(String accountCardOrBooklet) {
		this.accountCardOrBooklet = accountCardOrBooklet;
	}

	public String getRedemptionReceType() {
		return redemptionReceType;
	}

	public void setRedemptionReceType(String redemptionReceType) {
		this.redemptionReceType = redemptionReceType;
	}

	public BigDecimal getFeedbackMoney() {
		return feedbackMoney;
	}

	public void setFeedbackMoney(BigDecimal feedbackMoney) {
		this.feedbackMoney = feedbackMoney;
	}

	public String getFeedbackRemark() {
		return feedbackRemark;
	}

	public void setFeedbackRemark(String feedbackRemark) {
		this.feedbackRemark = feedbackRemark;
	}

	public BigDecimal getSupplementedMoney() {
		return supplementedMoney;
	}

	public void setSupplementedMoney(BigDecimal supplementedMoney) {
		this.supplementedMoney = supplementedMoney;
	}

	public ExportBean() {
		super();
	}

	public String getZzApproveDate() {
		return zzApproveDate;
	}

	public void setZzApproveDate(String zzApproveDate) {
		this.zzApproveDate = zzApproveDate;
	}

	public String getSrLendcode() {
		return srLendcode;
	}

	public void setSrLendcode(String srLendcode) {
		this.srLendcode = srLendcode;
	}

	public String getSrrCustomerName() {
		return srrCustomerName;
	}

	public void setSrrCustomerName(String srrCustomerName) {
		this.srrCustomerName = srrCustomerName;
	}

}
