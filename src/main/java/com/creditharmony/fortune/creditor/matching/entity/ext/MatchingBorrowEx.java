package com.creditharmony.fortune.creditor.matching.entity.ext;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 债权匹配查询条件实体
 * @Class Name MatchingBorrowEx
 * @author 柳慧
 * @Create In 2015年12月26日
 */
public class MatchingBorrowEx implements Serializable {
	private List<String> loanIds;//历史借款人编号集合
	private List<Integer> loanBackmoneyDays;//还款日集合
	private BigDecimal loanMonthRate;//月利率
	private Date loanBackmoneyFirday;// 首次还款日
	private Map<String,BigDecimal> ppxy; // 匹配限额
	private String ppType; // 匹配类型   1为 非月满盈首期第一次匹配，2为非月满盈首期第二次匹配
	private BigDecimal secondPPmoneyMin ; // 非月满盈首期第二次匹配 金额最小值
	private BigDecimal fristPPmoneyMin; //非月满盈首期第一次匹配 金额最小值
	private List<String> oldCreditValueId;// 已匹配的债权ID集合
	private BigDecimal ppxeMoney;// 匹配限额
	private String dictLoanFreeFlag; // 可用状态
	private List<String> filterLoanIdCards;//待过滤借款人省份证
	private String trusteeFlagOrderBy; // 资金托管排序方式
	private List<String> loanIdCards; // 历史记录人身份证号集合
	private String loanType; // 借款类型
	
	public BigDecimal getFristPPmoneyMin() {
		return fristPPmoneyMin;
	}
	public void setFristPPmoneyMin(BigDecimal fristPPmoneyMin) {
		this.fristPPmoneyMin = fristPPmoneyMin;
	}
	public String getDictLoanFreeFlag() {
		return dictLoanFreeFlag;
	}
	public void setDictLoanFreeFlag(String dictLoanFreeFlag) {
		this.dictLoanFreeFlag = dictLoanFreeFlag;
	}
	public BigDecimal getPpxeMoney() {
		return ppxeMoney;
	}
	public void setPpxeMoney(BigDecimal ppxeMoney) {
		this.ppxeMoney = ppxeMoney;
	}
	public List<String> getOldCreditValueId() {
		return oldCreditValueId;
	}
	public void setOldCreditValueId(List<String> oldCreditValueId) {
		this.oldCreditValueId = oldCreditValueId;
	}
	public BigDecimal getSecondPPmoneyMin() {
		return secondPPmoneyMin;
	}
	public void setSecondPPmoneyMin(BigDecimal secondPPmoneyMin) {
		this.secondPPmoneyMin = secondPPmoneyMin;
	}
	public String getPpType() {
		return ppType;
	}
	public void setPpType(String ppType) {
		this.ppType = ppType;
	}
	public Map<String, BigDecimal> getPpxy() {
		return ppxy;
	}
	public void setPpxy(Map<String, BigDecimal> ppxy) {
		this.ppxy = ppxy;
	}
	public Date getLoanBackmoneyFirday() {
		return loanBackmoneyFirday;
	}
	public void setLoanBackmoneyFirday(Date loanBackmoneyFirday) {
		this.loanBackmoneyFirday = loanBackmoneyFirday;
	}
	public List<String> getLoanIds() {
		return loanIds;
	}
	public void setLoanIds(List<String> loanIds) {
		this.loanIds = loanIds;
	}
	public List<Integer> getLoanBackmoneyDays() {
		return loanBackmoneyDays;
	}
	public void setLoanBackmoneyDays(List<Integer> loanBackmoneyDays) {
		this.loanBackmoneyDays = loanBackmoneyDays;
	}
	public BigDecimal getLoanMonthRate() {
		return loanMonthRate;
	}
	public void setLoanMonthRate(BigDecimal loanMonthRate) {
		this.loanMonthRate = loanMonthRate;
	}
	
	public List<String> getFilterLoanIdCards() {
		return filterLoanIdCards;
	}
	public void setFilterLoanIdCards(List<String> filterLoanIdCards) {
		this.filterLoanIdCards = filterLoanIdCards;
	}
	
	public String getTrusteeFlagOrderBy() {
		return trusteeFlagOrderBy;
	}
	public void setTrusteeFlagOrderBy(String trusteeFlagOrderBy) {
		this.trusteeFlagOrderBy = trusteeFlagOrderBy;
	}
	public List<String> getLoanIdCards() {
		return loanIdCards;
	}
	public void setLoanIdCards(List<String> loanIdCards) {
		this.loanIdCards = loanIdCards;
	}
	public String getLoanType() {
		return loanType;
	}
	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}

	

}
