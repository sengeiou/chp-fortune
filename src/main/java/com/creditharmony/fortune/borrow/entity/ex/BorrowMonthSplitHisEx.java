package com.creditharmony.fortune.borrow.entity.ex;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 封装月满盈拆分历史记录
 * @Class Name SplitHis
 * @author 周俊
 * @Create In 2015年11月30日
 */
public class BorrowMonthSplitHisEx {

	private String borrowerName;//借款人
	
	private BigDecimal borrowMonthRate;//月利率
	
	private BigDecimal splitRate;//拆分利率
	
	private BigDecimal beforMoney;
	 
	private BigDecimal operateMoney;// 操作金额
	
	private BigDecimal afterMoney;
	
	private String operator;
	
	private Date operateTime;

	public String getBorrowerName() {
		return borrowerName;
	}

	public void setBorrowerName(String borrowerName) {
		this.borrowerName = borrowerName;
	}

	public BigDecimal getBorrowMonthRate() {
		return borrowMonthRate;
	}

	public void setBorrowMonthRate(BigDecimal borrowMonthRate) {
		this.borrowMonthRate = borrowMonthRate;
	}

	public BigDecimal getSplitRate() {
		return splitRate;
	}

	public void setSplitRate(BigDecimal splitRate) {
		this.splitRate = splitRate;
	}

	public BigDecimal getBeforMoney() {
		return beforMoney;
	}

	public void setBeforMoney(BigDecimal beforMoney) {
		this.beforMoney = beforMoney;
	}

	public BigDecimal getAfterMoney() {
		return afterMoney;
	}

	public void setAfterMoney(BigDecimal afterMoney) {
		this.afterMoney = afterMoney;
	}


	public Date getOperateTime() {
		return operateTime;
	}

	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}

	public BigDecimal getOperateMoney() {
		return operateMoney;
	}

	public void setOperateMoney(BigDecimal operateMoney) {
		this.operateMoney = operateMoney;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}
	
	
}
