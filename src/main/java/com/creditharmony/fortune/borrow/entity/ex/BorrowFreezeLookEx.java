package com.creditharmony.fortune.borrow.entity.ex;

import java.math.BigDecimal;

/**
 * 封装冻结债权的查看字段
 * @Class Name BorrowFreezeLookEx
 * @author 周俊
 * @Create In 2015年12月10日
 */
public class BorrowFreezeLookEx {
    // 出借人
	private String name;
	// 匹配金额
	private BigDecimal tradeMoney;
	// 还款期数
	private Integer backMoneyNum;
	// 剩余还款金额
	private BigDecimal surplusMoney;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getTradeMoney() {
		return tradeMoney;
	}
	public void setTradeMoney(BigDecimal tradeMoney) {
		this.tradeMoney = tradeMoney;
	}
	public Integer getBackMoneyNum() {
		return backMoneyNum;
	}
	public void setBackMoneyNum(Integer backMoneyNum) {
		this.backMoneyNum = backMoneyNum;
	}
	public BigDecimal getSurplusMoney() {
		return surplusMoney;
	}
	public void setSurplusMoney(BigDecimal surplusMoney) {
		this.surplusMoney = surplusMoney;
	}
	
}
