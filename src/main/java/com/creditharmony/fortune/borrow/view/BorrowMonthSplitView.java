package com.creditharmony.fortune.borrow.view;

import java.math.BigDecimal;

import com.creditharmony.core.persistence.DataEntity;

/**
 * 封装拆分属性
 * 
 * @Class Name BorrowMonthSplitView
 * @author 周俊
 * @Create In 2015年11月29日
 */
@SuppressWarnings("serial")
public class BorrowMonthSplitView extends DataEntity<BorrowMonthSplitView>{
	private String creditMonId;// 月满盈债权id
	
	private BigDecimal splitMoney;// 拆分金额
	
	private BigDecimal splitRate;// 拆分利率
	
	private BigDecimal initSplitMoney;// 被拆分金额
	
	private BigDecimal surplusSplitMoney;// 拆分后可用金额
	
	private BigDecimal loanAvailabeValue;// 可用金额
	
	public BigDecimal getSplitRate() {
		return splitRate;
	}
	public void setSplitRate(BigDecimal splitRate) {
		this.splitRate = splitRate;
	}
	
	public BigDecimal getSplitMoney() {
		return splitMoney;
	}
	public void setSplitMoney(BigDecimal splitMoney) {
		this.splitMoney = splitMoney;
	}
	public String getCreditMonId() {
		return creditMonId;
	}
	public void setCreditMonId(String creditMonId) {
		this.creditMonId = creditMonId;
	}
	public BigDecimal getInitSplitMoney() {
		return initSplitMoney;
	}
	public void setInitSplitMoney(BigDecimal initSplitMoney) {
		this.initSplitMoney = initSplitMoney;
	}
	public BigDecimal getSurplusSplitMoney() {
		return surplusSplitMoney;
	}
	public void setSurplusSplitMoney(BigDecimal surplusSplitMoney) {
		this.surplusSplitMoney = surplusSplitMoney;
	}
	public BigDecimal getLoanAvailabeValue() {
		return loanAvailabeValue;
	}
	public void setLoanAvailabeValue(BigDecimal loanAvailabeValue) {
		this.loanAvailabeValue = loanAvailabeValue;
	}
	
}
