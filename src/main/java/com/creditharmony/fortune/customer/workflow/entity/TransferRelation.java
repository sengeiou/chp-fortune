package com.creditharmony.fortune.customer.workflow.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.creditharmony.core.persistence.DataEntity;

/**
 * 内转实体类
 * 
 * @Class Name TransferRelation
 * @author 柳慧
 * @Create In 2016年2月23日
 */
public class TransferRelation extends DataEntity<TransferRelation> {
	private static final long serialVersionUID = -8568671031949443695L;

	private String lendCodeParent;

	private String lendCodeChilde;

	private BigDecimal backMoney;

	private BigDecimal transferMoney;

	private String backMoneyType;

	private String transferState;

	private String createBy;

	private Date createTime;

	private Date modifyTime;

	private String modifyBy;
	
	private String childLendStatus; //子申请单的出借状态

	
	
	public String getChildLendStatus() {
		return childLendStatus;
	}

	public void setChildLendStatus(String childLendStatus) {
		this.childLendStatus = childLendStatus;
	}

	public String getLendCodeParent() {
		return lendCodeParent;
	}

	public void setLendCodeParent(String lendCodeParent) {
		this.lendCodeParent = lendCodeParent == null ? null : lendCodeParent
				.trim();
	}

	public String getLendCodeChilde() {
		return lendCodeChilde;
	}

	public void setLendCodeChilde(String lendCodeChilde) {
		this.lendCodeChilde = lendCodeChilde == null ? null : lendCodeChilde
				.trim();
	}

	public BigDecimal getBackMoney() {
		return backMoney;
	}

	public void setBackMoney(BigDecimal backMoney) {
		this.backMoney = backMoney;
	}

	public BigDecimal getTransferMoney() {
		return transferMoney;
	}

	public void setTransferMoney(BigDecimal transferMoney) {
		this.transferMoney = transferMoney;
	}

	public String getBackMoneyType() {
		return backMoneyType;
	}

	public void setBackMoneyType(String backMoneyType) {
		this.backMoneyType = backMoneyType == null ? null : backMoneyType
				.trim();
	}

	public String getTransferState() {
		return transferState;
	}

	public void setTransferState(String transferState) {
		this.transferState = transferState == null ? null : transferState
				.trim();
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy == null ? null : createBy.trim();
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getModifyBy() {
		return modifyBy;
	}

	public void setModifyBy(String modifyBy) {
		this.modifyBy = modifyBy == null ? null : modifyBy.trim();
	}
}