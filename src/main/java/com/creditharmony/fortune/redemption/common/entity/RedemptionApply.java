package com.creditharmony.fortune.redemption.common.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.creditharmony.core.persistence.DataEntity;

/**
 * 描述：赎回申请Bean
 * @Class Name RedemptionApply
 * @author 陈广鹏
 * @Create In 2015年11月30日
 */
public class RedemptionApply extends DataEntity<RedemptionApply> {

	/**
	 * long serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	private String redemptionId;			// 赎回申请ID
	private String lendCode;				// 出借编号
	private String customerCode;			// 客户编号
	private Date redemptionTime;			// 赎回申请时间
	private String redemptionType;			// 赎回类型
	private BigDecimal redemptionMoney;		// 赎回金额
	private String redemptionReceType;		// 选择回款期限
	private BigDecimal redemptionBmoney;	// 应回款额
	private BigDecimal redemptionDemoney;	// 应扣款额
	private BigDecimal residualAmount;		// 剩余金额
	private Date linitDay;					// 到期日期
	private Date backMoneyDay;				// 回款日期
	private String redemptionStatus;		// 当前状态
	private String feedback;				// 是否客户回馈
	private BigDecimal feedbackMoney;		// 客户回馈金额
	private String feedbackRemark;			// 回馈事项备注
	private String checkSp;					// 是否特批
	private BigDecimal checkSpmoney;		// 特批金额
	private String checkSpremarks;			// 特批备注

	public String getRedemptionId() {
		return redemptionId;
	}

	public void setRedemptionId(String redemptionId) {
		this.redemptionId = redemptionId;
	}

	public String getLendCode() {
		return lendCode;
	}

	public void setLendCode(String lendCode) {
		this.lendCode = lendCode;
	}

	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
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

	public String getRedemptionReceType() {
		return redemptionReceType;
	}

	public void setRedemptionReceType(String redemptionReceType) {
		this.redemptionReceType = redemptionReceType;
	}

	public BigDecimal getRedemptionMoney() {
		return redemptionMoney;
	}

	public void setRedemptionMoney(BigDecimal redemptionMoney) {
		this.redemptionMoney = redemptionMoney;
	}

	public BigDecimal getRedemptionBmoney() {
		return redemptionBmoney;
	}

	public void setRedemptionBmoney(BigDecimal redemptionBmoney) {
		this.redemptionBmoney = redemptionBmoney;
	}

	public BigDecimal getRedemptionDemoney() {
		return redemptionDemoney;
	}

	public void setRedemptionDemoney(BigDecimal redemptionDemoney) {
		this.redemptionDemoney = redemptionDemoney;
	}

	public BigDecimal getResidualAmount() {
		return residualAmount;
	}

	public void setResidualAmount(BigDecimal residualAmount) {
		this.residualAmount = residualAmount;
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

	public String getRedemptionStatus() {
		return redemptionStatus;
	}

	public void setRedemptionStatus(String redemptionStatus) {
		this.redemptionStatus = redemptionStatus;
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

}
