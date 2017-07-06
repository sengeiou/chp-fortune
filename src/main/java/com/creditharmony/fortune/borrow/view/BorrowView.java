package com.creditharmony.fortune.borrow.view;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.creditharmony.core.persistence.DataEntity;

/**
 * 封装可用债权列表的bean
 * @Class Name BorrowView
 * @author 周俊
 * @Create In 2015年11月27日
 */
public class BorrowView extends DataEntity<BorrowView>{

	    /**
	 * long serialVersionUID 
	 */
	private static final long serialVersionUID = 1L;
		private List<String> creditValueIdList; // id集合
		private String borrowerName;// 借款人姓名
	   
	    private BigDecimal borrowCreditValueFrom;// 可用剩余价值
	    
	    private BigDecimal borrowCreditValueTo;// 可用剩余价值1
	    
	    private Integer borrowMonthsSurplusFrom;// 剩余借款期数
	    
	    private Integer borrowMonthsSurplusTo;// 剩余借款期数
	    
	    private String borrowBakcmoneyDay;// 还款日
	    
	    private BigDecimal borrowMonthRate;// 月利率
	    
	    private Date borrowBackmoneyFirdayFrom;//首次还款日
	    
	    private Date borrowBackmoneyFirdayTo;//首次还款日
	    
	    private String borrowerJob;//借款人职业
	    
	    private String borrowType;//借款类型
	    
	    private String borrowTrusteeFlag; // 债权标识
	    
	    private Date loanBackmoneyDay;// 最后一期还款日
		
	    private String dicLoanDistinguish; // 债权区分
	    
	    private Date loanFreezeDayFrom;  //冻结时间开始
	    
	    private Date loanFreezeDayTo;  //冻结时间结束
	    
	    public Date getLoanFreezeDayFrom() {
			return loanFreezeDayFrom;
		}

		public void setLoanFreezeDayFrom(Date loanFreezeDayFrom) {
			this.loanFreezeDayFrom = loanFreezeDayFrom;
		}

		public Date getLoanFreezeDayTo() {
			return loanFreezeDayTo;
		}

		public void setLoanFreezeDayTo(Date loanFreezeDayTo) {
			this.loanFreezeDayTo = loanFreezeDayTo;
		}


		private Date pushBorrowTimeFrom;//推送日期
	    
	    private Date pushBorrowTimeTo;//推送日期
	    
	    private String pushBorrowStatus;//推送状态针对大金融成功标识
	    
	    private String pushHiddenMoney;//页面金额  后四舍五入
	    
	    private String pushHiddenMoneyOneByOne;//页面金额  先四舍五入
	    
	    private String pushHiddenCount;//页面总条数
	    
	    
	    
	    private Date loanBackmoneyLastdayFrom;//最后还款日
	    
	    private Date loanBackmoneyLastdayTo;//最后还款日
	    
	    /** 债权情况 */
		private String zqState;
	    
	    public String getBorrowerJob() {
			return borrowerJob;
		}

		public void setBorrowerJob(String borrowerJob) {
			this.borrowerJob = borrowerJob;
		}

		public String getBorrowType() {
			return borrowType;
		}

		public void setBorrowType(String borrowType) {
			this.borrowType = borrowType;
		}

		public String getBorrowTrusteeFlag() {
			return borrowTrusteeFlag;
		}

		public void setBorrowTrusteeFlag(String borrowTrusteeFlag) {
			this.borrowTrusteeFlag = borrowTrusteeFlag;
		}

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


		public Date getLoanBackmoneyDay() {
			return loanBackmoneyDay;
		}

		public void setLoanBackmoneyDay(Date loanBackmoneyDay) {
			this.loanBackmoneyDay = loanBackmoneyDay;
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

		public Date getBorrowBackmoneyFirdayFrom() {
			return borrowBackmoneyFirdayFrom;
		}

		public void setBorrowBackmoneyFirdayFrom(
				Date borrowBackmoneyFirdayFrom) {
			this.borrowBackmoneyFirdayFrom = borrowBackmoneyFirdayFrom;
		}

		public Date getBorrowBackmoneyFirdayTo() {
			return borrowBackmoneyFirdayTo;
		}

		public void setBorrowBackmoneyFirdayTo(Date borrowBackmoneyFirdayTo) {
			this.borrowBackmoneyFirdayTo = borrowBackmoneyFirdayTo;
		}

		public String getBorrowBakcmoneyDay() {
			return borrowBakcmoneyDay;
		}

		public void setBorrowBakcmoneyDay(String borrowBakcmoneyDay) {
			this.borrowBakcmoneyDay = borrowBakcmoneyDay;
		}

		public Integer getBorrowMonthsSurplusFrom() {
			return borrowMonthsSurplusFrom;
		}

		public void setBorrowMonthsSurplusFrom(Integer borrowMonthsSurplusFrom) {
			this.borrowMonthsSurplusFrom = borrowMonthsSurplusFrom;
		}

		public Integer getBorrowMonthsSurplusTo() {
			return borrowMonthsSurplusTo;
		}

		public void setBorrowMonthsSurplusTo(Integer borrowMonthsSurplusTo) {
			this.borrowMonthsSurplusTo = borrowMonthsSurplusTo;
		}

		public List<String> getCreditValueIdList() {
			return creditValueIdList;
		}

		public void setCreditValueIdList(List<String> creditValueIdList) {
			this.creditValueIdList = creditValueIdList;
		}

		public String getDicLoanDistinguish() {
			return dicLoanDistinguish;
		}

		public void setDicLoanDistinguish(String dicLoanDistinguish) {
			this.dicLoanDistinguish = dicLoanDistinguish;
		}

		public Date getPushBorrowTimeFrom() {
			return pushBorrowTimeFrom;
		}

		public void setPushBorrowTimeFrom(Date pushBorrowTimeFrom) {
			this.pushBorrowTimeFrom = pushBorrowTimeFrom;
		}

		public Date getPushBorrowTimeTo() {
			return pushBorrowTimeTo;
		}

		public void setPushBorrowTimeTo(Date pushBorrowTimeTo) {
			this.pushBorrowTimeTo = pushBorrowTimeTo;
		}

		public String getPushBorrowStatus() {
			return pushBorrowStatus;
		}

		public void setPushBorrowStatus(String pushBorrowStatus) {
			this.pushBorrowStatus = pushBorrowStatus;
		}

		public String getPushHiddenMoney() {
			return pushHiddenMoney;
		}

		public void setPushHiddenMoney(String pushHiddenMoney) {
			this.pushHiddenMoney = pushHiddenMoney;
		}

		public String getPushHiddenMoneyOneByOne() {
			return pushHiddenMoneyOneByOne;
		}

		public void setPushHiddenMoneyOneByOne(String pushHiddenMoneyOneByOne) {
			this.pushHiddenMoneyOneByOne = pushHiddenMoneyOneByOne;
		}

		public String getPushHiddenCount() {
			return pushHiddenCount;
		}

		public void setPushHiddenCount(String pushHiddenCount) {
			this.pushHiddenCount = pushHiddenCount;
		}

		public Date getLoanBackmoneyLastdayFrom() {
			return loanBackmoneyLastdayFrom;
		}

		public void setLoanBackmoneyLastdayFrom(Date loanBackmoneyLastdayFrom) {
			this.loanBackmoneyLastdayFrom = loanBackmoneyLastdayFrom;
		}

		public Date getLoanBackmoneyLastdayTo() {
			return loanBackmoneyLastdayTo;
		}

		public void setLoanBackmoneyLastdayTo(Date loanBackmoneyLastdayTo) {
			this.loanBackmoneyLastdayTo = loanBackmoneyLastdayTo;
		}

		public String getZqState() {
			return zqState;
		}

		public void setZqState(String zqState) {
			this.zqState = zqState;
		}

		

}
