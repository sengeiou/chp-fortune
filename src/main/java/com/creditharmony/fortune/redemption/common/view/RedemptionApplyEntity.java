package com.creditharmony.fortune.redemption.common.view;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.creditharmony.bpm.frame.view.BaseBusinessView;
import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.fortune.type.ProductCode;
import com.creditharmony.fortune.redemption.constant.RedeemConstant;

/**
 * 描述：赎回申请审批页面实体类
 * 
 * @Class Name RedemptionApplyEntity
 * @author 陈广鹏
 * @Create In 2015年11月30日
 */
public class RedemptionApplyEntity extends BaseBusinessView {

	/**
	 * 客户姓名
	 */
	private String customerName; 
	/**
	 * 客户编号
	 */
	private String customerCode; 
	/**
	 * 证件号码
	 */
	private String customerCertNum; 
	/**
	 * 开户行
	 */
	private String accountBank; 
	/**
	 * 银行卡所在城市 省
	 */
	private String accountAddrprovince; 
	/**
	 * 银行卡所在城市 市
	 */
	private String accountAddrcity; 
	/**
	 * 银行卡所在城市 区
	 */
	private String accountAddrdistrict; 
	/**
	 * 金账户 银行卡所在城市 省
	 */
	private String faccountAddrprovince; 
	/**
	 * 金账户 银行卡所在城市 市
	 */
	private String faccountAddrcity; 
	/**
	 * 金账户 银行卡所在城市 区
	 */
	private String faccountAddrdistrict; 
	/**
	 * 卡或折
	 */
	private String accountCardOrBooklet; 
	/**
	 * 具体支行
	 */
	private String accountBranch; 
	/**
	 * 开户姓名
	 */
	private String accountName; 
	/**
	 * 回款账号
	 */
	private String accountNo; 
	/**
	 * 出借编号
	 */
	private String lendCode; 
	/**
	 * 计划划扣日期
	 */
	private Date applyDeductDay; 
	/**
	 * 计划出借日期
	 */
	private Date applyLendDay; 
	/**
	 * 出借到期日期
	 */
	private Date applyExpireDay; 
	/**
	 * 合同编号
	 */
	private String applyContractNo; 
	/**
	 * 付款方式
	 */
	private String applyPay; 
	/**
	 * 计划出借金额
	 */
	private BigDecimal applyLendMoneyd; 
	/**
	 * 计划出借金额
	 */
	private Double applyLendMoney; 
	private Double applyLendMoneyTo; 
	/**
	 * 协议版本号
	 */
	private String applyAgreementEdition; 
	/**
	 * 划扣平台
	 */
	private String dictApplyDeductType; 
	/**
	 * 账单日
	 */
	private String applyBillday; 
	/**
	 * 营业部 名(以后存入工作流)
	 */
	private String department; 
	/**
	 * 营业部ID
	 */
	private String orgID; 
	/**
	 * 营业部 省(以后存入工作流)
	 */
	private String province; 
	/**
	 * 营业部 市(以后存入工作流)
	 */
	private String city; 
	/**
	 * 出借产品
	 */
	private String productName; 
	/**
	 * 出借产品ID
	 */
	private String productCode; 
	/**
	 * 产品类型
	 */
	private String producType; 
	/**
	 * 赎回申请ID
	 */
	private String redemptionId; 
	/**
	 * 赎回申请日期
	 */
	private Date redemptionTime; 
	/**
	 * 赎回类型
	 */
	private String redemptionType; 
	/**
	 * 是否允许部分赎回的标志
	 * 允许为1，不允许为0，默认1
	 */
	private String redeemPartFlag; 
	/**
	 * 是否为1.6.1的合同版本
	 * 【是】为1，【否】为2，默认2
	 */
	private String is161Contract; 
	/**
	 * 赎回金额
	 */
	private BigDecimal redemptionMoney; 
	/**
	 * 剩余金额
	 */
	private BigDecimal residualAmount; 
	/**
	 * 应回金额
	 */
	private BigDecimal redemptionBMoney; 
	/**
	 * 应扣金额
	 */
	private BigDecimal redemptionDeMoney; 
	/**
	 * 预期年化收益
	 */
	private String expectProfit; 
	/**
	 * 选中的回款期限ID
	 */
	private String redemptionReceType; 
	/**
	 * 到期日期
	 */
	private Date linitDay; 
	/**
	 * 回款日期
	 */
	private Date backMoneyDay; 
	/**
	 * 审批结果
	 */
	private String checkExaminetype; 
	/**
	 * 审批意见
	 */
	private String checkExamine; 
	/**
	 * 是否特批
	 */
	private String checkSp; 
	/**
	 * 特批金额
	 */
	private BigDecimal checkSpmoney; 
	/**
	 * 特批备注
	 */
	private String checkSpremarks; 
	/**
	 * 是否客户回馈
	 */
	private String feedback;
	/**
	 * 客户回馈金额
	 */
	private BigDecimal feedbackMoney;
	/**
	 * 回馈事项备注
	 */
	private String feedbackRemark;
	
	private Date backMoneyDayOfLend;  //出借申请回款池的 回款日期
	
	
	/**
	 * 用于区别不同赎回费率的字符串
	 */
	private String typeStr; 
	private List<String> addAttachmentId; // 更新的附件ids
	private List<String> deleteAttachmentId; // 删除的附件ids 

	
	
	
	public Date getBackMoneyDayOfLend() {
		return backMoneyDayOfLend;
	}

	public void setBackMoneyDayOfLend(Date backMoneyDayOfLend) {
		this.backMoneyDayOfLend = backMoneyDayOfLend;
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

	public String getCustomerCertNum() {
		return customerCertNum;
	}

	public void setCustomerCertNum(String customerCertNum) {
		this.customerCertNum = customerCertNum;
	}

	public String getAccountBank() {
		return accountBank;
	}

	public void setAccountBank(String accountBank) {
		this.accountBank = accountBank;
	}

	public String getOrgID() {
		return orgID;
	}

	public void setOrgID(String orgID) {
		this.orgID = orgID;
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

	public String getAccountAddrdistrict() {
		if (StringUtils.isEmpty(accountAddrdistrict)) {
			accountAddrdistrict = faccountAddrdistrict;
		}
		return accountAddrdistrict;
	}

	public void setAccountAddrdistrict(String accountAddrdistrict) {
		this.accountAddrdistrict = accountAddrdistrict;
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

	public String getFaccountAddrdistrict() {
		return faccountAddrdistrict;
	}

	public void setFaccountAddrdistrict(String faccountAddrdistrict) {
		this.faccountAddrdistrict = faccountAddrdistrict;
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

	public String getLendCode() {
		return lendCode;
	}

	public void setLendCode(String lendCode) {
		this.lendCode = lendCode;
	}

	public Date getApplyDeductDay() {
		return applyDeductDay;
	}

	public void setApplyDeductDay(Date applyDeductDay) {
		this.applyDeductDay = applyDeductDay;
	}

	public Date getApplyLendDay() {
		return applyLendDay;
	}

	public void setApplyLendDay(Date applyLendDay) {
		this.applyLendDay = applyLendDay;
	}

	public Date getApplyExpireDay() {
		return applyExpireDay;
	}

	public void setApplyExpireDay(Date applyExpireDay) {
		this.applyExpireDay = applyExpireDay;
	}

	public String getApplyContractNo() {
		return applyContractNo;
	}

	public void setApplyContractNo(String applyContractNo) {
		this.applyContractNo = applyContractNo;
	}

	public String getApplyPay() {
		return applyPay;
	}

	public void setApplyPay(String applyPay) {
		this.applyPay = applyPay;
	}

	public BigDecimal getApplyLendMoneyd() {
		return applyLendMoneyd;
	}

	public void setApplyLendMoneyd(BigDecimal applyLendMoneyd) {
		this.applyLendMoneyd = applyLendMoneyd;
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

	public String getApplyAgreementEdition() {
		return applyAgreementEdition;
	}

	public void setApplyAgreementEdition(String applyAgreementEdition) {
		this.applyAgreementEdition = applyAgreementEdition;
	}

	public String getDictApplyDeductType() {
		return dictApplyDeductType;
	}

	public void setDictApplyDeductType(String dictApplyDeductType) {
		this.dictApplyDeductType = dictApplyDeductType;
	}

	public String getApplyBillday() {
		return applyBillday;
	}

	public void setApplyBillday(String applyBillday) {
		this.applyBillday = applyBillday;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
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

	public String getProducType() {
		return producType;
	}

	public void setProducType(String producType) {
		this.producType = producType;
	}

	public String getRedemptionId() {
		return redemptionId;
	}

	public void setRedemptionId(String redemptionId) {
		this.redemptionId = redemptionId;
	}

	public Date getRedemptionTime() {
		return redemptionTime;
	}

	public void setRedemptionTime(Date redemptionTime) {
		this.redemptionTime = redemptionTime;
	}

	public String getRedemptionType() {
		return redemptionType;
	}

	public void setRedemptionType(String redemptionType) {
		this.redemptionType = redemptionType;
	}

	public String getRedeemPartFlag() {
		return redeemPartFlag;
	}

	public void setRedeemPartFlag(String redeemPartFlag) {
		this.redeemPartFlag = redeemPartFlag;
	}

	public String getIs161Contract() {
		is161Contract = "2";
		if (RedeemConstant.SPECIAL_FLOW_EDITION.contains(applyAgreementEdition)) {
			is161Contract = "1";
		}
		return is161Contract;
	}

	public void setIs161Contract(String is161Contract) {
		this.is161Contract = is161Contract;
	}

	public BigDecimal getRedemptionMoney() {
		return redemptionMoney;
	}

	public void setRedemptionMoney(BigDecimal redemptionMoney) {
		this.redemptionMoney = redemptionMoney;
	}

	public BigDecimal getResidualAmount() {
		return residualAmount;
	}

	public void setResidualAmount(BigDecimal residualAmount) {
		this.residualAmount = residualAmount;
	}

	public BigDecimal getRedemptionBMoney() {
		return redemptionBMoney;
	}

	public void setRedemptionBMoney(BigDecimal redemptionBMoney) {
		this.redemptionBMoney = redemptionBMoney;
	}

	public BigDecimal getRedemptionDeMoney() {
		return redemptionDeMoney;
	}

	public void setRedemptionDeMoney(BigDecimal redemptionDeMoney) {
		this.redemptionDeMoney = redemptionDeMoney;
	}

	public String getExpectProfit() {
		return expectProfit;
	}

	public void setExpectProfit(String expectProfit) {
		this.expectProfit = expectProfit;
	}

	public String getRedemptionReceType() {
		return redemptionReceType;
	}

	public void setRedemptionReceType(String redemptionReceType) {
		this.redemptionReceType = redemptionReceType;
	}

	public Date getLinitDay() {
		return linitDay;
	}

	public void setLinitDay(Date linitDay) {
		this.linitDay = linitDay;
	}

	public Date getBackMoneyDay() {
		return backMoneyDay;
	}

	public void setBackMoneyDay(Date backMoneyDay) {
		this.backMoneyDay = backMoneyDay;
	}

	public String getCheckExaminetype() {
		return checkExaminetype;
	}

	public void setCheckExaminetype(String checkExaminetype) {
		this.checkExaminetype = checkExaminetype;
	}

	public String getCheckExamine() {
		return checkExamine;
	}

	public void setCheckExamine(String checkExamine) {
		this.checkExamine = checkExamine;
	}

	public String getCheckSp() {
		return checkSp;
	}

	public void setCheckSp(String checkSp) {
		this.checkSp = checkSp;
	}

	public BigDecimal getCheckSpmoney() {
		return checkSpmoney;
	}

	public void setCheckSpmoney(BigDecimal checkSpmoney) {
		this.checkSpmoney = checkSpmoney;
	}

	public String getCheckSpremarks() {
		return checkSpremarks;
	}

	public void setCheckSpremarks(String checkSpremarks) {
		this.checkSpremarks = checkSpremarks;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
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

	public String getTypeStr() {
		typeStr = RedeemConstant.FQXL_STR;
		if (RedeemConstant.QXL_NEW_LIST.contains(applyAgreementEdition)) {
			typeStr = RedeemConstant.QXL_STR_NEW;
		}
		if (RedeemConstant.QXL_LIST.contains(applyAgreementEdition)) {
			typeStr = RedeemConstant.QXL_STR;
		}
		if (ProductCode.YXT.value.equals(productCode)
				|| ProductCode.XHT.value.equals(productCode)) {
			typeStr = RedeemConstant.FQXL_STR;
		}
		if (RedeemConstant.SPECIAL_FLOW_EDITION.contains(applyAgreementEdition)) {
			typeStr = RedeemConstant.WITHOUT_LIMIT_STR;
		}
		return typeStr;
	}

	public void setTypeStr(String typeStr) {
		this.typeStr = typeStr;
	}

	public List<String> getAddAttachmentId() {
		return addAttachmentId;
	}

	public void setAddAttachmentId(List<String> addAttachmentId) {
		this.addAttachmentId = addAttachmentId;
	}

	public List<String> getDeleteAttachmentId() {
		return deleteAttachmentId;
	}

	public void setDeleteAttachmentId(List<String> deleteAttachmentId) {
		this.deleteAttachmentId = deleteAttachmentId;
	}

}
