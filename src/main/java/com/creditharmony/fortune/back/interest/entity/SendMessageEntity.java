package com.creditharmony.fortune.back.interest.entity;

import java.io.Serializable;
import java.util.Date;

import com.creditharmony.core.persistence.DataEntity;

/**
 * 短信类
 * @Class Name SendMessageEntity 
 * @author 李志伟
 * @Create In 2016年5月30日
 */
public class SendMessageEntity extends DataEntity<SendMessageEntity> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String lendCode;
	private String customerCode;
	private String customerName;
	private String productName;
	private String customerMobilephone;
	private String managerCode;// 理财经理
	private Date applyDeductDay;// 划扣日期
	private Integer applyBillday;
	private Double applyLendMoney;
	private Date applyLendDay;
	private Date applyExpireDay;
	private Date backMoneyDay;
	private String applyPay;
	private String dictDeductStatus;
	private String accountNo;
	private String accountBankName;
	private String smsId;
	private String smsMsg;
	private String sendStatus;
	private Date sendDay;// 发送日期
	private Date pushDay;// 推送日期
	private String fyCity;// (金账户)区域名称
	private String city;// (非金账户)区域名称
	private String orgName;// 机构名
	private String dictBackStatus;// 状态(未知字段)
	private Integer productClosedate;// 封闭期
	private String dictRemindType;// 提醒类型
	private Date applyDay;// 申请日期
	private String onlyFlag;// 唯一标识
	private String createBy;//创建人
	private Date createTime;//创建时间
	private String modifyBy;//修改人
	private Date modifyTime;//修改时间
	private String productCode;//产品ID
	private String managerName;//理财经理姓名
	private String orgCode;// 机构编号
	private String accountBank;// 开户行ID
	private String failReason;// 失败原因
	private Double deductSucceedMoney;// 划扣成功金额
	private Double deductFailMoney;//划扣失败金额
	private String platformId;// 划扣平台
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLendCode() {
		return lendCode;
	}
	public void setLendCode(String lendCode) {
		this.lendCode = lendCode;
	}
	public Integer getProductClosedate() {
		return productClosedate;
	}
	public void setProductClosedate(Integer productClosedate) {
		this.productClosedate = productClosedate;
	}
	public String getCustomerCode() {
		return customerCode;
	}
	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getCustomerMobilephone() {
		return customerMobilephone;
	}
	public void setCustomerMobilephone(String customerMobilephone) {
		this.customerMobilephone = customerMobilephone;
	}
	public String getManagerCode() {
		return managerCode;
	}
	public void setManagerCode(String managerCode) {
		this.managerCode = managerCode;
	}
	
	public Date getApplyDeductDay() {
		return applyDeductDay;
	}
	public void setApplyDeductDay(Date applyDeductDay) {
		this.applyDeductDay = applyDeductDay;
	}
	public Integer getApplyBillday() {
		return applyBillday;
	}
	public void setApplyBillday(Integer applyBillday) {
		this.applyBillday = applyBillday;
	}
	public Double getApplyLendMoney() {
		return applyLendMoney;
	}
	public void setApplyLendMoney(Double applyLendMoney) {
		this.applyLendMoney = applyLendMoney;
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
	public String getDictDeductStatus() {
		return dictDeductStatus;
	}
	public void setDictDeductStatus(String dictDeductStatus) {
		this.dictDeductStatus = dictDeductStatus;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public String getAccountBankName() {
		return accountBankName;
	}
	public void setAccountBankName(String accountBankName) {
		this.accountBankName = accountBankName;
	}
	public String getSmsId() {
		return smsId;
	}
	public void setSmsId(String smsId) {
		this.smsId = smsId;
	}
	public String getSmsMsg() {
		return smsMsg;
	}
	public void setSmsMsg(String smsMsg) {
		this.smsMsg = smsMsg;
	}
	public String getSendStatus() {
		return sendStatus;
	}
	public void setSendStatus(String sendStatus) {
		this.sendStatus = sendStatus;
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
	public String getFyCity() {
		return fyCity;
	}
	public void setFyCity(String fyCity) {
		this.fyCity = fyCity;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getDictBackStatus() {
		return dictBackStatus;
	}
	public void setDictBackStatus(String dictBackStatus) {
		this.dictBackStatus = dictBackStatus;
	}

	public String getDictRemindType() {
		return dictRemindType;
	}
	public void setDictRemindType(String dictRemindType) {
		this.dictRemindType = dictRemindType;
	}
	public Date getApplyDay() {
		return applyDay;
	}
	public void setApplyDay(Date applyDay) {
		this.applyDay = applyDay;
	}
	public String getOnlyFlag() {
		return onlyFlag;
	}
	public void setOnlyFlag(String onlyFlag) {
		this.onlyFlag = onlyFlag;
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
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public String getAccountBank() {
		return accountBank;
	}
	public void setAccountBank(String accountBank) {
		this.accountBank = accountBank;
	}
	public String getFailReason() {
		return failReason;
	}
	public void setFailReason(String failReason) {
		this.failReason = failReason;
	}
	public Double getDeductSucceedMoney() {
		return deductSucceedMoney;
	}
	public void setDeductSucceedMoney(Double deductSucceedMoney) {
		this.deductSucceedMoney = deductSucceedMoney;
	}
	public Double getDeductFailMoney() {
		return deductFailMoney;
	}
	public void setDeductFailMoney(Double deductFailMoney) {
		this.deductFailMoney = deductFailMoney;
	}
	public String getPlatformId() {
		return platformId;
	}
	public void setPlatformId(String platformId) {
		this.platformId = platformId;
	}
}