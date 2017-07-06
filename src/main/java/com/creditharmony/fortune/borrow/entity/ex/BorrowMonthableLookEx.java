package com.creditharmony.fortune.borrow.entity.ex;

import java.math.BigDecimal;
import java.util.Date;

import com.creditharmony.fortune.borrow.utils.ReckonUtils;

/**
 * 封装查看月满盈可用债权信息
 * @Class Name BorrowMonthLookView
 * @author 周俊
 * @Create In 2015年12月3日
 */
public class BorrowMonthableLookEx {

	private String creditMonableId;//月满盈可用债权id
	
	private String applyCode;//出借编号
	
	private String customerName;//出借人姓名
	
	private String customerTrue;//出借人编号
	
	private BigDecimal applyLendMoney; // 出借金额
	
	private BigDecimal tradeMateMoney;//匹配金额
	
	private Date loanDate;//出借日期
	
	private String loanProduct;//出借产品
	
	private Integer applyBillday;//账单日
	
	private Date applyLendDate;//计划出借日期,即月满盈可用债权的出借日期
	
	private String borrowPercent;//债权百分比
	
	private Integer loanMonths;//匹配期数
	
	public String getApplyCode() {
		return applyCode;
	}
	public void setApplyCode(String applyCode) {
		this.applyCode = applyCode;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerTrue() {
		return customerTrue;
	}
	public void setCustomerTrue(String customerTrue) {
		this.customerTrue = customerTrue;
	}
	
	public Date getLoanDate() {
		return loanDate;
	}
	public void setLoanDate(Date loanDate) {
		this.loanDate = loanDate;
	}
	public String getLoanProduct() {
		return loanProduct;
	}
	public void setLoanProduct(String loanProduct) {
		this.loanProduct = loanProduct;
	}
	public Date getApplyLendDate() {
		return applyLendDate;
	}
	public void setApplyLendDate(Date applyLendDate) {
		this.applyLendDate = applyLendDate;
	}
	public String getBorrowPercent() {
		return ReckonUtils.percentage(tradeMateMoney, applyLendMoney);
	}
	public void setBorrowPercent(String borrowPercent) {
		this.borrowPercent = borrowPercent;
	}
	public Integer getLoanMonths() {
		return loanMonths;
	}
	public void setLoanMonths(Integer loanMonths) {
		this.loanMonths = loanMonths;
	}
	public Integer getApplyBillday() {
		return applyBillday;
	}
	public void setApplyBillday(Integer applyBillday) {
		this.applyBillday = applyBillday;
	}
	public BigDecimal getTradeMateMoney() {
		return tradeMateMoney;
	}
	public void setTradeMateMoney(BigDecimal tradeMateMoney) {
		this.tradeMateMoney = tradeMateMoney;
	}
	public String getCreditMonableId() {
		return creditMonableId;
	}
	public void setCreditMonableId(String creditMonableId) {
		this.creditMonableId = creditMonableId;
	}
	public BigDecimal getApplyLendMoney() {
		return applyLendMoney;
	}
	public void setApplyLendMoney(BigDecimal applyLendMoney) {
		this.applyLendMoney = applyLendMoney;
	}
	
	
	
	
}
