package com.creditharmony.fortune.borrow.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.creditharmony.common.util.DateUtils;
import com.creditharmony.core.loan.type.LoanType;
import com.creditharmony.core.loan.type.ProfType;
import com.creditharmony.core.persistence.DataEntity;

public class BorrowPush extends DataEntity<BorrowPush>{
	 /**
	 * long serialVersionUID 
	 */
	private static final long serialVersionUID = 1L;
		//id
		private String id;
		//Borrowid
		private String borrowId;
		//借款id
	    private String loanCode;
	    //借款人id
	    private String loanId;
	    //借款人姓名
	    private String loanName;
	    //债权来源
	    private String dictLoanType;
	    //债权标识
	    private String loanTrusteeFlag;
	    //职业情况
	    private String loanJob;
	    //借款产品
	    private String loanProduct;
	    //首次还款日
	    private Date loanBackmoneyFirday;
	    //还款日
	    private Integer loanBackmoneyDay;
	    //截止还款日期
	    private Date loanBackmoneyLastday;
	    //月利率
	    private BigDecimal loanMonthRate;
	    //借款期数
	    private Integer loanMonths;
	    //可用期数
	    private Integer loanMonthsSurplus;
	    //原始债权价值
	    private BigDecimal loanQuota;
	    //推送债权价值
	    private BigDecimal loanCreditValue;	    
	    //年预计债权收益
	    private BigDecimal loanValueYear;
	    //债权区分
	    private String dicLoanDistinguish;   
	    //推送系统 /JX,DJR
	    private String pushPlatForm;
	    //批次号
	    private String batchNo;
	    //推送时间
	    private Date pushTime;
	    //推动到大金融系统成功或者失败   1成功  2失败   新增为0
	    private String status;
	    //勾选还是批量  1勾选  0批量
	    private String oneOrBeath;
	    //是否更新到  1更新减金额  0未更新 2更新加金额  大金融推送失败
	    private String isUpdate;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getBorrowId() {
			return borrowId;
		}

		public void setBorrowId(String borrowId) {
			this.borrowId = borrowId;
		}

		public String getLoanCode() {
			return loanCode;
		}

		public void setLoanCode(String loanCode) {
			this.loanCode = loanCode;
		}

		public String getLoanId() {
			return loanId;
		}

		public void setLoanId(String loanId) {
			this.loanId = loanId;
		}

		public String getLoanName() {
			return loanName;
		}

		public void setLoanName(String loanName) {
			this.loanName = loanName;
		}

		public String getDictLoanType() {
			return dictLoanType;
		}

		public void setDictLoanType(String dictLoanType) {
			this.dictLoanType = dictLoanType;
		}

		public String getLoanTrusteeFlag() {
			return loanTrusteeFlag;
		}

		public void setLoanTrusteeFlag(String loanTrusteeFlag) {
			this.loanTrusteeFlag = loanTrusteeFlag;
		}

		public String getLoanJob() {
			return loanJob;
		}

		public void setLoanJob(String loanJob) {
			this.loanJob = loanJob;
		}

		public String getLoanProduct() {
			return loanProduct;
		}

		public void setLoanProduct(String loanProduct) {
			this.loanProduct = loanProduct;
		}

		public Date getLoanBackmoneyFirday() {
			return loanBackmoneyFirday;
		}

		public void setLoanBackmoneyFirday(Date loanBackmoneyFirday) {
			this.loanBackmoneyFirday = loanBackmoneyFirday;
		}

		public Integer getLoanBackmoneyDay() {
			return loanBackmoneyDay;
		}

		public void setLoanBackmoneyDay(Integer loanBackmoneyDay) {
			this.loanBackmoneyDay = loanBackmoneyDay;
		}

		public Date getLoanBackmoneyLastday() {
			return loanBackmoneyLastday;
		}

		public void setLoanBackmoneyLastday(Date loanBackmoneyLastday) {
			this.loanBackmoneyLastday = loanBackmoneyLastday;
		}

		public BigDecimal getLoanMonthRate() {
			return loanMonthRate;
		}

		public void setLoanMonthRate(BigDecimal loanMonthRate) {
			this.loanMonthRate = loanMonthRate;
		}

		public Integer getLoanMonths() {
			return loanMonths;
		}

		public void setLoanMonths(Integer loanMonths) {
			this.loanMonths = loanMonths;
		}

		public Integer getLoanMonthsSurplus() {
			return loanMonthsSurplus;
		}

		public void setLoanMonthsSurplus(Integer loanMonthsSurplus) {
			this.loanMonthsSurplus = loanMonthsSurplus;
		}

		public BigDecimal getLoanQuota() {
			return loanQuota;
		}

		public void setLoanQuota(BigDecimal loanQuota) {
			this.loanQuota = loanQuota;
		}

		public BigDecimal getLoanCreditValue() {
			return loanCreditValue;
		}

		public void setLoanCreditValue(BigDecimal loanCreditValue) {
			this.loanCreditValue = loanCreditValue;
		}

		public BigDecimal getLoanValueYear() {
			return loanValueYear;
		}

		public void setLoanValueYear(BigDecimal loanValueYear) {
			this.loanValueYear = loanValueYear;
		}

		public String getDicLoanDistinguish() {
			return dicLoanDistinguish;
		}

		public void setDicLoanDistinguish(String dicLoanDistinguish) {
			this.dicLoanDistinguish = dicLoanDistinguish;
		}

		public Date getCreateTime() {
			return createTime;
		}

		public void setCreateTime(Date createTime) {
			this.createTime = createTime;
		}

		public String getPushPlatForm() {
			return pushPlatForm;
		}

		public void setPushPlatForm(String pushPlatForm) {
			this.pushPlatForm = pushPlatForm;
		}

		public String getCreateBy() {
			return createBy;
		}

		public void setCreateBy(String createBy) {
			this.createBy = createBy;
		}

		public String getBatchNo() {
			return batchNo;
		}

		public void setBatchNo(String batchNo) {
			this.batchNo = batchNo;
		}

		public Date getPushTime() {
			return pushTime;
		}

		public void setPushTime(Date pushTime) {
			this.pushTime = pushTime;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public String getOneOrBeath() {
			return oneOrBeath;
		}

		public void setOneOrBeath(String oneOrBeath) {
			this.oneOrBeath = oneOrBeath;
		}

		public String getIsUpdate() {
			return isUpdate;
		}

		public void setIsUpdate(String isUpdate) {
			this.isUpdate = isUpdate;
		}
		
		
	    
	    
	    
	} 