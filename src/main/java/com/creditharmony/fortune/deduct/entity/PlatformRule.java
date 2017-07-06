package com.creditharmony.fortune.deduct.entity;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import com.creditharmony.common.util.ObjectHelper;
import com.creditharmony.core.persistence.DataEntity;

/**
 * 三方平台规则表
 * 
 * @Class Name PlatformRule
 * @author 韩龙
 * @Create In 2015年12月17日
 */
public class PlatformRule extends DataEntity<PlatformRule> {

	/**
	 * long serialVersionUID 
	 */
	private static final long serialVersionUID = 4337232277691685191L;
	/**
	 * 划扣平台id
	 */
	private String dictDeductPlatformId;
	private List<String> platformList;
	/**
	 * 银行id
	 */
	private String dictBankId;
	private List<String> bankList;
	/**
	 * 划扣接口方式（1实时 2批量）
	 */
	private String dictDeductInterfaceType;
	/**
	 * 单笔限额
	 */
	private BigDecimal singleLimitMoney;
	private BigDecimal singleLimitMoneyTo;
	/**
	 * 单日限额
	 */
	private BigDecimal dayLimitMoney;
	private BigDecimal dayLimitMoneyTo;
	/**
	 * 是否发送第一笔（1是，2否）
	 */
	private String isFirst;
	/**
	 * 审核状态
	 */
	private String status;
	/**
	 * 审核不通过原因
	 */
	private String backReason;
	/**
	 * 审核结果
	 */
	private String checkResult;

	public String getDictDeductPlatformId() {
		return dictDeductPlatformId;
	}

	public void setDictDeductPlatformId(String dictDeductPlatformId) {
		this.dictDeductPlatformId = dictDeductPlatformId == null ? null
				: dictDeductPlatformId.trim();
	}

	public String getDictBankId() {
		return dictBankId;
	}

	public void setDictBankId(String dictBankId) {
		this.dictBankId = dictBankId == null ? null : dictBankId.trim();
	}

	public List<String> getPlatformList() {
		if (ObjectHelper.isEmpty(dictDeductPlatformId)) {
			return null;
		}
		platformList = Arrays.asList(dictDeductPlatformId.split(","));
		return platformList;
	}

	public void setPlatformList(List<String> platformList) {
		this.platformList = platformList;
	}

	public List<String> getBankList() {
		if (ObjectHelper.isEmpty(dictBankId)) {
			return null;
		}
		bankList = Arrays.asList(dictBankId.split(","));
		return bankList;
	}

	public void setBankList(List<String> bankList) {
		this.bankList = bankList;
	}

	public String getDictDeductInterfaceType() {
		return dictDeductInterfaceType;
	}

	public void setDictDeductInterfaceType(String dictDeductInterfaceType) {
		this.dictDeductInterfaceType = dictDeductInterfaceType == null ? null
				: dictDeductInterfaceType.trim();
	}

	public BigDecimal getSingleLimitMoney() {
		return singleLimitMoney;
	}

	public void setSingleLimitMoney(BigDecimal singleLimitMoney) {
		this.singleLimitMoney = singleLimitMoney;
	}

	public BigDecimal getSingleLimitMoneyTo() {
		return singleLimitMoneyTo;
	}

	public void setSingleLimitMoneyTo(BigDecimal singleLimitMoneyTo) {
		this.singleLimitMoneyTo = singleLimitMoneyTo;
	}

	public BigDecimal getDayLimitMoney() {
		return dayLimitMoney;
	}

	public void setDayLimitMoney(BigDecimal dayLimitMoney) {
		this.dayLimitMoney = dayLimitMoney;
	}

	public BigDecimal getDayLimitMoneyTo() {
		return dayLimitMoneyTo;
	}

	public void setDayLimitMoneyTo(BigDecimal dayLimitMoneyTo) {
		this.dayLimitMoneyTo = dayLimitMoneyTo;
	}

	public String getIsFirst() {
		return isFirst;
	}

	public void setIsFirst(String isFirst) {
		this.isFirst = isFirst;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getBackReason() {
		return backReason;
	}

	public void setBackReason(String backReason) {
		this.backReason = backReason;
	}

	public String getCheckResult() {
		return checkResult;
	}

	public void setCheckResult(String checkResult) {
		this.checkResult = checkResult;
	}

}