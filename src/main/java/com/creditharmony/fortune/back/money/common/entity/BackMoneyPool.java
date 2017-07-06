package com.creditharmony.fortune.back.money.common.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.creditharmony.core.persistence.DataEntity;

/**
 * 回款池对应Bean
 * @Class Name BackMoneyPool
 * @author 陈广鹏
 * @Create In 2015年12月2日
 */
public class BackMoneyPool extends DataEntity<BackMoneyPool> {

	/**
	 * long serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	private String backmId;				// 回款ID
	private String lendCode;			// 出借编号
	private Date finalLinitDate;		// 到期日期
	private String backMoneyType;		// 回款类型
	private String dictBackStatus;		// 状态
	private BigDecimal backMoney;			// 应回款金额
	private BigDecimal backActualbackMoney;	// 实际回款金额
	private String backMoneyRemarks;	// 回款备注
	private Date backMoneyDay;			// 回款日期
	private String dictBackMoneyError;	// 回款错误原因
	private String platformId;			// 回款平台ID
	private String exPlatform;			// 最近使用的回款平台ID
	private String backResult;			// 回盘结果（1回款成功，2回款失败）
	private String backReason;			// （回盘）失败原因
	private Date backDay;				// 回盘日期
	private String backBank;			// 放款银行
	private String rebackFlag;			// 退回重放标志，1-退回重放
	private BigDecimal successMoney;	// 回款成功金额
	private BigDecimal failMoney;		// 回款失败金额
	private String dictFortunechannelflag; 	// 渠道标识
	private BigDecimal supplementedMoney;	// 补息后回款金额
	private Date debtTransferDate;		// 债权转让日期
	private String isSupplemented;		// 是否补息
	private String interestDay;// 补息天数
	private BigDecimal supplementedDays;	// 回款补息天数
	private String workingState;		// 在职状态
	private String switchApproveStatus;

	public String getBackmId() {
		return backmId;
	}

	public void setBackmId(String backmId) {
		this.backmId = backmId;
	}

	public String getLendCode() {
		return lendCode;
	}

	public void setLendCode(String lendCode) {
		this.lendCode = lendCode;
	}

	public Date getFinalLinitDate() {
		return finalLinitDate;
	}

	public void setFinalLinitDate(Date finalLinitDate) {
		this.finalLinitDate = finalLinitDate;
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

	public BigDecimal getBackMoney() {
		return backMoney;
	}

	public void setBackMoney(BigDecimal backMoney) {
		this.backMoney = backMoney;
	}

	public BigDecimal getBackActualbackMoney() {
		return backActualbackMoney;
	}

	public void setBackActualbackMoney(BigDecimal backActualbackMoney) {
		this.backActualbackMoney = backActualbackMoney;
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

	public String getDictBackMoneyError() {
		return dictBackMoneyError;
	}

	public void setDictBackMoneyError(String dictBackMoneyError) {
		this.dictBackMoneyError = dictBackMoneyError;
	}

	public String getPlatformId() {
		return platformId;
	}

	public void setPlatformId(String platformId) {
		this.platformId = platformId;
	}

	public String getExPlatform() {
		return exPlatform;
	}

	public void setExPlatform(String exPlatform) {
		this.exPlatform = exPlatform;
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

	public Date getBackDay() {
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

	public String getDictFortunechannelflag() {
		return dictFortunechannelflag;
	}

	public void setDictFortunechannelflag(String dictFortunechannelflag) {
		this.dictFortunechannelflag = dictFortunechannelflag;
	}

	public BigDecimal getSupplementedMoney() {
		return supplementedMoney;
	}

	public void setSupplementedMoney(BigDecimal supplementedMoney) {
		this.supplementedMoney = supplementedMoney;
	}

	public Date getDebtTransferDate() {
		return debtTransferDate;
	}

	public void setDebtTransferDate(Date debtTransferDate) {
		this.debtTransferDate = debtTransferDate;
	}

	public String getIsSupplemented() {
		return isSupplemented;
	}

	public void setIsSupplemented(String isSupplemented) {
		this.isSupplemented = isSupplemented;
	}

	public String getInterestDay() {
		return interestDay;
	}

	public void setInterestDay(String interestDay) {
		this.interestDay = interestDay;
	}

	public BigDecimal getSupplementedDays() {
		return supplementedDays;
	}

	public void setSupplementedDays(BigDecimal supplementedDays) {
		this.supplementedDays = supplementedDays;
	}

	public String getWorkingState() {
		return workingState;
	}

	public void setWorkingState(String workingState) {
		this.workingState = workingState;
	}

	public String getSwitchApproveStatus() {
		return switchApproveStatus;
	}

	public void setSwitchApproveStatus(String switchApproveStatus) {
		this.switchApproveStatus = switchApproveStatus;
	}
}