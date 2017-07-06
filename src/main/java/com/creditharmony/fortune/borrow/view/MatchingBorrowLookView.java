package com.creditharmony.fortune.borrow.view;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 封装带推荐债权列表查询条件
 * @Class Name MatchingBorrowLookView
 * @author 周俊
 * @Create In 2016年1月5日
 */
public class MatchingBorrowLookView implements Serializable{

	/**
	 * long serialVersionUID 
	 */
	   private static final long serialVersionUID = 1L;
	
	    private String creditValueIds; // checkbox选中的集合
		
	    private String matchingId; // 债权推荐id
		
        private BigDecimal creditLines; // 待匹配金额
        
        private String lendCode; // 出借编号
       
        private Date lendDate; // 计划出借日期
        
        private String procuctName;  // 产品类型      
        
        private String borrowerName; // 借款人
        
        private Integer borrowDaysSurplusFrom; // 可用期数
        
        private Integer borrowDaysSurplusTo; // 可用期数
        
        private String borrowType; // 债权来源
        
        private BigDecimal borrowMonthRate; // 月利率
        
        private BigDecimal borrowCreditValueFrom; // 可用债权价值
        
        private BigDecimal borrowCreditValueTo; // 可用债权价值
        
        private Date loanBackmoneyDay; // 还款末期日期
        
        private String dicLoanDistinguish; // 债权区分
        
        private BigDecimal productMatchingRateUpper; // 债权匹配利率上限
        
        private BigDecimal productMatchingRateLower; // 债权匹配利率下限

		public String getMatchingId() {
			return matchingId;
		}

		public void setMatchingId(String matchingId) {
			this.matchingId = matchingId;
		}

		public BigDecimal getCreditLines() {
			return creditLines;
		}

		public void setCreditLines(BigDecimal creditLines) {
			this.creditLines = creditLines;
		}

		public Date getLendDate() {
			return lendDate;
		}

		public void setLendDate(Date lendDate) {
			this.lendDate = lendDate;
		}

		public String getProcuctName() {
			return procuctName;
		}

		public void setProcuctName(String procuctName) {
			this.procuctName = procuctName;
		}

		public String getBorrowerName() {
			return borrowerName;
		}

		public void setBorrowerName(String borrowerName) {
			this.borrowerName = borrowerName;
		}


		public String getBorrowType() {
			return borrowType;
		}

		public void setBorrowType(String borrowType) {
			this.borrowType = borrowType;
		}

		public BigDecimal getBorrowMonthRate() {
			return borrowMonthRate;
		}

		public void setBorrowMonthRate(BigDecimal borrowMonthRate) {
			this.borrowMonthRate = borrowMonthRate;
		}

		public BigDecimal getBorrowCreditValueFrom() {
			return borrowCreditValueFrom;
		}

		public void setBorrowCreditValueFrom(BigDecimal borrowCreditValueFrom) {
			this.borrowCreditValueFrom = borrowCreditValueFrom;
		}

		public BigDecimal getBorrowCreditValueTo() {
			return borrowCreditValueTo;
		}

		public void setBorrowCreditValueTo(BigDecimal borrowCreditValueTo) {
			this.borrowCreditValueTo = borrowCreditValueTo;
		}

		public Date getLoanBackmoneyDay() {
			return loanBackmoneyDay;
		}

		public void setLoanBackmoneyDay(Date loanBackmoneyDay) {
			this.loanBackmoneyDay = loanBackmoneyDay;
		}

		public String getLendCode() {
			return lendCode;
		}

		public void setLendCode(String lendCode) {
			this.lendCode = lendCode;
		}

		public String getCreditValueIds() {
			return creditValueIds;
		}

		public void setCreditValueIds(String creditValueIds) {
			this.creditValueIds = creditValueIds;
		}

		public Integer getBorrowDaysSurplusTo() {
			return borrowDaysSurplusTo;
		}

		public void setBorrowDaysSurplusTo(Integer borrowDaysSurplusTo) {
			this.borrowDaysSurplusTo = borrowDaysSurplusTo;
		}

		public Integer getBorrowDaysSurplusFrom() {
			return borrowDaysSurplusFrom;
		}

		public void setBorrowDaysSurplusFrom(Integer borrowDaysSurplusFrom) {
			this.borrowDaysSurplusFrom = borrowDaysSurplusFrom;
		}

		public String getDicLoanDistinguish() {
			return dicLoanDistinguish;
		}

		public void setDicLoanDistinguish(String dicLoanDistinguish) {
			this.dicLoanDistinguish = dicLoanDistinguish;
		}

		public BigDecimal getProductMatchingRateUpper() {
			return productMatchingRateUpper;
		}

		public void setProductMatchingRateUpper(BigDecimal productMatchingRateUpper) {
			this.productMatchingRateUpper = productMatchingRateUpper;
		}

		public BigDecimal getProductMatchingRateLower() {
			return productMatchingRateLower;
		}

		public void setProductMatchingRateLower(BigDecimal productMatchingRateLower) {
			this.productMatchingRateLower = productMatchingRateLower;
		}
	
        
	
}
