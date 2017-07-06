package com.creditharmony.fortune.back.interest.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.creditharmony.core.persistence.DataEntity;

/**
 * @Class Name BackInterestPool 
 * @author 李志伟
 * @Create In 2015年12月2日
 */
public final class BackInterestPool extends DataEntity<BackInterestPool>{

	private static final long serialVersionUID = -8320089400201680588L;

	private String backiId;// 回息ID

    private String lendCode;// 出借编号

    private String backMoneyStatus;// 回息状态

    private BigDecimal backMoney;// 回息金额
    
    private BigDecimal backRealMoney;// 实际回息金额

    private Date backMoneyDay;// 到期回息日期
    
	private Date matchingExpireDay;// 本期到期日期
    
    private String platformId;// 回息平台(页面回息标识对应字段)
    
    private String returnReason;// 退回原因
    
    private String textAre;// 其他原因
    
    private String checkExaminetype;// 审批结果

    private String checkExamine;// 审批意见
    
    private String tp;// 操作类型(导出：1，上传：2)
    
    private String backResult;// 回盘结果
    
    private String backReason;// 失败原因
    
    private Date backDay;// 回盘时间
    
    private String backBank;// 放款银行
    
    private String rebackFlag;// 退回重放标志
    
    private BigDecimal successMoney;// 成功金额
    
    private BigDecimal failMoney;// 失败金额
    
    private String interfaceType;// 接口类型(实时、批量)
    
    private Date currentBillday;// 当期账单日期
    
    private String customerCode;// 客户编号
    
    private String platFlag;// 平台标识
    
    private String productCode;// 产品编号
    
    private Date applyExpireDay;// 到期日期
    
    private List<String> verState;// 排他状态
    
    private String smsStatus;// 短信状态(实际上是退回重放标志)
    
    private String applyPay;// 付款方式
    
	// 到期回息标识 1为到期回息数据 空的为正常回息数据   gaoxu 2017-3-23 10:49:33
	private String interestReturn;
	// 到期回息是否回息标识0是1否 gaoxu 2017-3-23 10:49:33
	private String isInterest;
    
	public String getApplyPay() {
		return applyPay;
	}

	public void setApplyPay(String applyPay) {
		this.applyPay = applyPay;
	}

	public String getSmsStatus() {
		return smsStatus;
	}

	public void setSmsStatus(String smsStatus) {
		this.smsStatus = smsStatus;
	}

	public List<String> getVerState() {
		return verState;
	}

	public void setVerState(List<String> verState) {
		this.verState = verState;
	}

	public Date getApplyExpireDay() {
		return applyExpireDay;
	}

	public void setApplyExpireDay(Date applyExpireDay) {
		this.applyExpireDay = applyExpireDay;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getPlatFlag() {
		return platFlag;
	}

	public void setPlatFlag(String platFlag) {
		this.platFlag = platFlag;
	}

	public Date getCurrentBillday() {
		return currentBillday;
	}

	public void setCurrentBillday(Date currentBillday) {
		this.currentBillday = currentBillday;
	}
	
	public BigDecimal getBackRealMoney() {
		return backRealMoney;
	}

	public void setBackRealMoney(BigDecimal backRealMoney) {
		this.backRealMoney = backRealMoney;
	}
	
	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	public String getTp() {
		return tp;
	}

	public void setTp(String tp) {
		this.tp = tp;
	}

	
    public Date getMatchingExpireDay() {
		return matchingExpireDay;
	}

	public void setMatchingExpireDay(Date matchingExpireDay) {
		this.matchingExpireDay = matchingExpireDay;
	}

	public String getPlatformId() {
		return platformId;
	}

	public void setPlatformId(String platformId) {
		this.platformId = platformId;
	}

	public String getBackiId() {
        return backiId;
    }

    public void setBackiId(String backiId) {
        this.backiId = backiId;
    }
 
    public String getLendCode() {
		return lendCode;
	}
    
	public String getTextAre() {
		return textAre;
	}

	public void setTextAre(String textAre) {
		this.textAre = textAre;
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

	public Date getBackDay() {
		return backDay;
	}

	public void setLendCode(String lendCode) {
		this.lendCode = lendCode;
	}

	public String getBackMoneyStatus() {
		return backMoneyStatus;
	}

	public void setBackMoneyStatus(String backMoneyStatus) {
		this.backMoneyStatus = backMoneyStatus;
	}

	public Date getBackMoneyDay() {
		return backMoneyDay;
	}

	public void setBackMoneyDay(Date backMoneyDay) {
		this.backMoneyDay = backMoneyDay;
	}
	
    public BigDecimal getBackMoney() {
		return backMoney;
	}

	public void setBackMoney(BigDecimal backMoney) {
		this.backMoney = backMoney;
	}

	public String getReturnReason() {
		return returnReason;
	}

	public void setReturnReason(String returnReason) {
		this.returnReason = returnReason;
	}

	public String getBackResult() {
		return backResult;
	}

	public void setBackResult(String backResult) {
		this.backResult = backResult;
	}

	public String getBackReason() {
		return backReason;
	}

	public void setBackReason(String backReason) {
		this.backReason = backReason;
	}

	public Date BackDay() {
		return backDay;
	}

	public void setBackDay(Date backDay) {
		this.backDay = backDay;
	}

	public String getBackBank() {
		return backBank;
	}

	public void setBackBank(String backBank) {
		this.backBank = backBank;
	}

	public String getRebackFlag() {
		return rebackFlag;
	}

	public void setRebackFlag(String rebackFlag) {
		this.rebackFlag = rebackFlag;
	}

	public BigDecimal getSuccessMoney() {
		return successMoney;
	}

	public void setSuccessMoney(BigDecimal successMoney) {
		this.successMoney = successMoney;
	}

	public BigDecimal getFailMoney() {
		return failMoney;
	}

	public void setFailMoney(BigDecimal failMoney) {
		this.failMoney = failMoney;
	}

	public String getInterfaceType() {
		return interfaceType;
	}

	public void setInterfaceType(String interfaceType) {
		this.interfaceType = interfaceType;
	}

	public String getInterestReturn() {
		return interestReturn;
	}

	public void setInterestReturn(String interestReturn) {
		this.interestReturn = interestReturn;
	}

	public String getIsInterest() {
		return isInterest;
	}

	public void setIsInterest(String isInterest) {
		this.isInterest = isInterest;
	}
	
}