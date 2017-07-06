package com.creditharmony.fortune.borrow.view;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;


public class ReplaceView implements Serializable{

	/**
	 * long serialVersionUID 
	 */
	private static final long serialVersionUID = 1L;

	private String lendCode;
	private String matchingId;// 带推荐id
	//private String phaseMateId; // 移除时的债权交易id
	private BigDecimal primaryCreditLines;// 本期出借金额
	private String oldCreditValueIds;
	private List<ReplaceBorrowView> data;
	public String getLendCode() {
		return lendCode;
	}
	public void setLendCode(String lendCode) {
		this.lendCode = lendCode;
	}
	public List<ReplaceBorrowView> getData() {
		return data;
	}
	public void setData(List<ReplaceBorrowView> data) {
		this.data = data;
	}
	public String getMatchingId() {
		return matchingId;
	}
	public void setMatchingId(String matchingId) {
		this.matchingId = matchingId;
	}
	public BigDecimal getPrimaryCreditLines() {
		return primaryCreditLines;
	}
	public void setPrimaryCreditLines(BigDecimal primaryCreditLines) {
		this.primaryCreditLines = primaryCreditLines;
	}
	/*public String getPhaseMateId() {
		return phaseMateId;
	}
	public void setPhaseMateId(String phaseMateId) {
		this.phaseMateId = phaseMateId;
	}*/
	public String getOldCreditValueIds() {
		return oldCreditValueIds;
	}
	public void setOldCreditValueIds(String oldCreditValueIds) {
		this.oldCreditValueIds = oldCreditValueIds;
	}
	
}
