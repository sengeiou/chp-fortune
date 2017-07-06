package com.creditharmony.fortune.borrow.view;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 封装债权撤销查询
 * @Class Name BorrowCancelView
 * @author 周俊
 * @Create In 2015年12月8日
 */
public class BorrowCancelView implements Serializable{
	  
	
		/**
	 * 
	 */
	private static final long serialVersionUID = 3723476239675202915L;
		// 出借编号
		private String lendCode;
		// 带推荐Id
		private String matchingId;
		// 客户编号
		private String custCode;
		// 客户姓名
		private String customerName;
		// 计划出借日期
		private Date lendDateFrom;
		
		private Date applyLendDay;
		
		private Date lendDateTo;
		// 产品ID
		private String procuctId;
		// 产品名称
		private String procuctName;
		// 计划出借金额
		private BigDecimal applyLendMoney;
		// 营业部名称
		private String orgName;
		// 营业部编号
		private String orgCode;
		// 城市
		private String addrCity;
		// 账单类型
		private String matchingFirstdayFlag;
		// 账单日
		private String applyBillday;
		// 替换状态
		private String replaceStatus;
		// 替换状态
		private String toPageReplaceStatus;
		// 交易状态
		private String tradeCreditStatus;
		// 本期推荐金额
		private BigDecimal creditLinesMoney;
		// 本期出借日
		private Date matchingInterestStart;
		// 出借到期日
		private Date applyExpireDay;
		// 统计总数
		private int totalCount;
		// 计算总金额
		private BigDecimal sumMoney;
		// 本期待替换金额
		private BigDecimal currentCreditLinesMoneyFrom;
		
		private BigDecimal currentCreditLinesMoney;
		
		private BigDecimal currentCreditLinesMoneyTo;
		// 本期待替换总金额
		private BigDecimal sumCurrentCreditLinesMoney;
		
		// 替换日期
		private Date replaceDay;
		
		private Date replaceDayFrom;
		
		private Date replaceDayTo;
		
		private List<String> filtermatchingIds;
		
		private List<String> listMatchingId;
		
		private List<String> listLendCode;
		
		public List<String> getListLendCode() {
			return listLendCode;
		}
		public void setListLendCode(List<String> listLendCode) {
			this.listLendCode = listLendCode;
		}
		public List<String> getListMatchingId() {
			return listMatchingId;
		}
		public void setListMatchingId(List<String> listMatchingId) {
			this.listMatchingId = listMatchingId;
		}
		public List<String> getFiltermatchingIds() {
			return filtermatchingIds;
		}
		public void setFiltermatchingIds(List<String> filtermatchingIds) {
			this.filtermatchingIds = filtermatchingIds;
		}
		public Date getReplaceDayFrom() {
			return replaceDayFrom;
		}
		public void setReplaceDayFrom(Date replaceDayFrom) {
			this.replaceDayFrom = replaceDayFrom;
		}
		public Date getReplaceDayTo() {
			return replaceDayTo;
		}
		public void setReplaceDayTo(Date replaceDayTo) {
			this.replaceDayTo = replaceDayTo;
		}
		public Date getReplaceDay() {
			return replaceDay;
		}
		public void setReplaceDay(Date replaceDay) {
			this.replaceDay = replaceDay;
		}
		private String otherParam;
		
		public String getOtherParam() {
			return otherParam;
		}
		public void setOtherParam(String otherParam) {
			this.otherParam = otherParam;
		}
		public BigDecimal getSumCurrentCreditLinesMoney() {
			return sumCurrentCreditLinesMoney;
		}
		public void setSumCurrentCreditLinesMoney(BigDecimal sumCurrentCreditLinesMoney) {
			this.sumCurrentCreditLinesMoney = sumCurrentCreditLinesMoney;
		}
		public BigDecimal getCurrentCreditLinesMoneyFrom() {
			return currentCreditLinesMoneyFrom;
		}
		public void setCurrentCreditLinesMoneyFrom(
				BigDecimal currentCreditLinesMoneyFrom) {
			this.currentCreditLinesMoneyFrom = currentCreditLinesMoneyFrom;
		}
		public BigDecimal getCurrentCreditLinesMoneyTo() {
			return currentCreditLinesMoneyTo;
		}
		public void setCurrentCreditLinesMoneyTo(BigDecimal currentCreditLinesMoneyTo) {
			this.currentCreditLinesMoneyTo = currentCreditLinesMoneyTo;
		}
		public BigDecimal getCurrentCreditLinesMoney() {
			return currentCreditLinesMoney;
		}
		public void setCurrentCreditLinesMoney(BigDecimal currentCreditLinesMoney) {
			this.currentCreditLinesMoney = currentCreditLinesMoney;
		}
		public String getLendCode() {
			return lendCode;
		}
		public void setLendCode(String lendCode) {
			this.lendCode = lendCode;
		}
		public String getCustomerName() {
			return customerName;
		}
		public void setCustomerName(String customerName) {
			this.customerName = customerName;
		}
		public Date getApplyLendDay() {
			return applyLendDay;
		}
		public void setApplyLendDay(Date applyLendDay) {
			this.applyLendDay = applyLendDay;
		}
		public String getMatchingFirstdayFlag() {
			return matchingFirstdayFlag;
		}
		public void setMatchingFirstdayFlag(String matchingFirstdayFlag) {
			this.matchingFirstdayFlag = matchingFirstdayFlag;
		}
		public String getCustCode() {
			return custCode;
		}
		public void setCustCode(String custCode) {
			this.custCode = custCode;
		}
		
		public String getOrgName() {
			return orgName;
		}
		public void setOrgName(String orgName) {
			this.orgName = orgName;
		}
		public String getOrgCode() {
			return orgCode;
		}
		public void setOrgCode(String orgCode) {
			this.orgCode = orgCode;
		}
		public BigDecimal getCreditLinesMoney() {
			return creditLinesMoney;
		}
		public void setCreditLinesMoney(BigDecimal creditLinesMoney) {
			this.creditLinesMoney = creditLinesMoney;
		}
		public String getToPageReplaceStatus() {
			return toPageReplaceStatus;
		}
		public void setToPageReplaceStatus(String toPageReplaceStatus) {
			this.toPageReplaceStatus = toPageReplaceStatus;
		}
		public String getProcuctName() {
			return procuctName;
		}
		public void setProcuctName(String procuctName) {
			this.procuctName = procuctName;
		}
		public String getProcuctId() {
			return procuctId;
		}
		public void setProcuctId(String procuctId) {
			this.procuctId = procuctId;
		}
		
		public String getReplaceStatus() {
			return replaceStatus;
		}
		public void setReplaceStatus(String replaceStatus) {
			this.replaceStatus = replaceStatus;
		}
		public String getAddrCity() {
			return addrCity;
		}
		public void setAddrCity(String addrCity) {
			this.addrCity = addrCity;
		}
		public String getApplyBillday() {
			return applyBillday;
		}
		public void setApplyBillday(String applyBillday) {
			this.applyBillday = applyBillday;
		}
		public Date getLendDateFrom() {
			return lendDateFrom;
		}
		public void setLendDateFrom(Date lendDateFrom) {
			this.lendDateFrom = lendDateFrom;
		}
		public Date getLendDateTo() {
			return lendDateTo;
		}
		public void setLendDateTo(Date lendDateTo) {
			this.lendDateTo = lendDateTo;
		}
		
		public Date getMatchingInterestStart() {
			return matchingInterestStart;
		}
		public void setMatchingInterestStart(Date matchingInterestStart) {
			this.matchingInterestStart = matchingInterestStart;
		}
		public Date getApplyExpireDay() {
			return applyExpireDay;
		}
		public void setApplyExpireDay(Date applyExpireDay) {
			this.applyExpireDay = applyExpireDay;
		}
		public String getTradeCreditStatus() {
			return tradeCreditStatus;
		}
		public void setTradeCreditStatus(String tradeCreditStatus) {
			this.tradeCreditStatus = tradeCreditStatus;
		}
		public int getTotalCount() {
			return totalCount;
		}
		public void setTotalCount(int totalCount) {
			this.totalCount = totalCount;
		}
		public BigDecimal getApplyLendMoney() {
			return applyLendMoney;
		}
		public void setApplyLendMoney(BigDecimal applyLendMoney) {
			this.applyLendMoney = applyLendMoney;
		}
		public BigDecimal getSumMoney() {
			return sumMoney;
		}
		public void setSumMoney(BigDecimal sumMoney) {
			this.sumMoney = sumMoney;
		}
		public String getMatchingId() {
			return matchingId;
		}
		public void setMatchingId(String matchingId) {
			this.matchingId = matchingId;
		}
}
