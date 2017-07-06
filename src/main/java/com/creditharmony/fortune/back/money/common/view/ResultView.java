package com.creditharmony.fortune.back.money.common.view;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 封装审批详情页面提交的参数
 * 
 * @Class Name ResultView
 * @author 陈广鹏
 * @Create In 2015年12月3日
 */
public class ResultView implements Serializable {

	/**
	 * long serialVersionUID
	 */
	private static final long serialVersionUID = -708152068074991245L;
	/**
	 * 回款ID
	 */
	private String backmId;
	/**
	 * 出借编号
	 */
	private String lendCode;
	/**
	 * 回款备注
	 */
	private String backMoneyRemarks;
	/**
	 * 实际回款金额
	 */
	private BigDecimal backActualbackMoney;
	/**
	 * 补息后回款金额
	 */
	private BigDecimal supplementedMoney;
	/**
	 * 补息天数
	 */
	private int supplementDays;
	/**
	 * 回款日期（批量确认时使用）
	 */
	private Date backDay;
	/**
	 * 债权转让日期
	 */
	private Date debtTransferDate;
	/**
	 * 审批意见
	 */
	private String checkReason;
	/**
	 * 审批意见
	 */
	private String checkExamine;
	/**
	 * 审批结果
	 */
	private String checkExaminetype;
	/**
	 * 是否补息
	 */
	private String isSupplemented;
	/**
	 * 出借产品Code
	 */
	private String productCode; 
	/**
	 * 出借的日期
	 */
	private Date applyLendDay; 	
	/**
	 * 回款平台ID
	 */
	private String platformId;
	/**
	 * 回款状态
	 */
	private String dictBackStatus;
	private String verTime;     // 取modifyTime
	private String dictFortunechannelflag;
	/**
	 * 历史回息金额
	 * @return
	 */
	private BigDecimal sunInterestMoney;

	
	public BigDecimal getSunInterestMoney() {
		return sunInterestMoney;
	}

	public void setSunInterestMoney(BigDecimal sunInterestMoney) {
		this.sunInterestMoney = sunInterestMoney;
	}

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

	public String getBackMoneyRemarks() {
		return backMoneyRemarks;
	}

	public void setBackMoneyRemarks(String backMoneyRemarks) {
		this.backMoneyRemarks = backMoneyRemarks;
	}

	public BigDecimal getBackActualbackMoney() {
		return backActualbackMoney;
	}

	public void setBackActualbackMoney(BigDecimal backActualbackMoney) {
		this.backActualbackMoney = backActualbackMoney;
	}

	public Date getBackDay() {
		return backDay;
	}

	public void setBackDay(Date backDay) {
		this.backDay = backDay;
	}

	public String getCheckReason() {
		return checkReason;
	}

	public void setCheckReason(String checkReason) {
		this.checkReason = checkReason;
	}

	public String getCheckExamine() {
		return checkExamine;
	}

	public void setCheckExamine(String checkExamine) {
		this.checkExamine = checkExamine;
	}

	public String getCheckExaminetype() {
		return checkExaminetype;
	}

	public void setCheckExaminetype(String checkExaminetype) {
		this.checkExaminetype = checkExaminetype;
	}

	public String getPlatformId() {
		return platformId;
	}

	public void setPlatformId(String platformId) {
		this.platformId = platformId;
	}

	public String getDictBackStatus() {
		return dictBackStatus;
	}

	public void setDictBackStatus(String dictBackStatus) {
		this.dictBackStatus = dictBackStatus;
	}

	public String getVerTime() {
		return verTime;
	}

	public void setVerTime(String verTime) {
		this.verTime = verTime;
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

	public int getSupplementDays() {
		return supplementDays;
	}

	public void setSupplementDays(int supplementDays) {
		this.supplementDays = supplementDays;
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

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public Date getApplyLendDay() {
		return applyLendDay;
	}

	public void setApplyLendDay(Date applyLendDay) {
		this.applyLendDay = applyLendDay;
	}

}
