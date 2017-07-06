package com.creditharmony.fortune.back.interest.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.creditharmony.core.persistence.DataEntity;

/**
 * @Class Name DetailsPage 
 * @author 李志伟
 * @Create In 2015年12月2日
 */
public class DetailsPage extends DataEntity<DetailsPage> implements Serializable{
	
	private static final long serialVersionUID = 5993949296921187453L;
	private String backiId;// 回息ID
	private String customerName;// 客户姓名
	private String customerCode;// 客户编号
	private Date currentBillday;// 当期账单日
	
	private String orgName;// 营业部
	private Date applyLendDay;// 初始出借日期
	private BigDecimal applyLendMoney;// 初始出借金额
	private BigDecimal backMoney;// 当期应回金额
	
	private Date backMoneyDay;// 应回息日期
	
	private Date matchingExpireDay;// 本期到期日期
	private BigDecimal backRealMoney;// 实际回息金额
	private String lendCode;// 出借编号
	private String backMoneyStatus;// 回息状态
	
	private String checkExaminetype;// 审批结果
	private String checkExamine;// 审批意见
    private String textAre;// 其他原因
    private String returnReason;// 退回原因
    private String backResult;// 回盘结果
    private String platFlag;// 平台标识
    private String smsStatus;// 短信状态 
    private String verState;// 排他状态
    private String applyPay;// 付款方式
    
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
	public Date getMatchingExpireDay() {
		return matchingExpireDay;
	}
	public void setMatchingExpireDay(Date matchingExpireDay) {
		this.matchingExpireDay = matchingExpireDay;
	}
	public String getVerState() {
		return verState;
	}
	public void setVerState(String verState) {
		this.verState = verState;
	}
	public String getPlatFlag() {
		return platFlag;
	}
	public void setPlatFlag(String platFlag) {
		this.platFlag = platFlag;
	}
	public String getBackResult() {
		return backResult;
	}
	public void setBackResult(String backResult) {
		this.backResult = backResult;
	}
	public String getReturnReason() {
		return returnReason;
	}
	public void setReturnReason(String returnReason) {
		this.returnReason = returnReason;
	}
	public String getCheckExaminetype() {
		return checkExaminetype;
	}
	public void setCheckExaminetype(String checkExaminetype) {
		this.checkExaminetype = checkExaminetype;
	}
	public String getCheckExamine() {
		if(textAre != null && !("").equals(textAre)){
			checkExamine = textAre;
		}
		return checkExamine;
	}
	public void setCheckExamine(String checkExamine) {
		this.checkExamine = checkExamine;
	}
	public String getTextAre() {
		return textAre;
	}
	public void setTextAre(String textAre) {
		this.textAre = textAre;
	}
	public String getBackiId() {
		return backiId;
	}
	public void setBackiId(String backiId) {
		this.backiId = backiId;
	}
	public String getBackMoneyStatus() {
		return backMoneyStatus;
	}
	public void setBackMoneyStatus(String backMoneyStatus) {
		this.backMoneyStatus = backMoneyStatus;
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
	public Date getApplyLendDay() {
		return applyLendDay;
	}
	public void setApplyLendDay(Date applyLendDay) {
		this.applyLendDay = applyLendDay;
	}
	public String getLendCode() {
		return lendCode;
	}
	public void setLendCode(String lendCode) {
		this.lendCode = lendCode;
	}
	public BigDecimal getApplyLendMoney() {
		return applyLendMoney;
	}
	public void setApplyLendMoney(BigDecimal applyLendMoney) {
		this.applyLendMoney = applyLendMoney;
	}
	public void setBackMoney(BigDecimal backMoney) {
		this.backMoney = backMoney;
	}
	
	public void setBackRealMoney(BigDecimal backRealMoney) {
		this.backRealMoney = backRealMoney;
	}

	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public BigDecimal getBackMoney() {
		return backMoney;
	}
	public BigDecimal getBackRealMoney() {
		return backRealMoney;
	}
	
	public Date getBackMoneyDay() {
		return backMoneyDay;
	}
	public void setBackMoneyDay(Date backMoneyDay) {
		this.backMoneyDay = backMoneyDay;
	}
	public Date getCurrentBillday() {
		return currentBillday;
	}
	public void setCurrentBillday(Date currentBillday) {
		this.currentBillday = currentBillday;
	}
	
}