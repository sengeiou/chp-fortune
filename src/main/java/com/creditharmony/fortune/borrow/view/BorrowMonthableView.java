package com.creditharmony.fortune.borrow.view;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.creditharmony.core.persistence.DataEntity;

/**
 * 封装条件查询
 * @Class Name BorrowMonthableView
 * @author 周俊
 * @Create In 2015年12月2日
 */
public class BorrowMonthableView extends DataEntity<BorrowMonthableView>{

	/**
	 * long serialVersionUID 
	 */
	private static final long serialVersionUID = 1L;
	
	private String creditMonableId; // 月满盈可用债权ID
	
	private List<String> creditMonableIdList;// 月满盈可用债权Id集合
	
	private String borrowerName;// 借款人姓名
	
	private BigDecimal borrowCreditValueFrom;// 可用金额
	
	private BigDecimal borrowCreditValueTo;// 可用金额
	
	private Integer borrowDaysSurplusFrom;// 可用天数
	
	private Integer borrowDaysSurplusTo;// 可用天数
	
	private Date borrowCreditDateUsableFrom;//债权可用日期
	
	private Date borrowCreditDateUsableTo;//债权可用日期
	
	private BigDecimal borrowMonthRate;// 月利率
	
	private Date borrowBackmoneyFirdayFrom;// 首次还款日
	
	private Date  borrowBackmoneyFirdayTo;// 首次还款日
	
	private String  borrowBackmoneyFirday;
	
	private String borrowerJob;// 职业
	
	private String borrowType;//债权类型
	
	private String  dictLoanFreeFlag; // 是否可用，1可用，0不可用，2冻结
	
	private Map<String,BigDecimal> ppxy;   //匹配限额

	private String creditMonableIds;
	
    private String trusteeFlag; // 资金托管标识
    private String trusteeFlagOrderby;
    
    private List<String> LoanIds;
    
    private List<String> creditMonableIdLs;
    
    private List<String> filterLoanIdCrards;//待过滤借款人code 
    
    private List<String> oldLoadIdCard;
    
    private Date loanOutmoneyDay;
    private List<String> borrowerJobls;
    
    private String dicLoanDistinguish; // 债权区分
	
	public Map<String, BigDecimal> getPpxy() {
		return ppxy;
	}

	public void setPpxy(Map<String, BigDecimal> ppxy) {
		this.ppxy = ppxy;
	}

	public String getDictLoanFreeFlag() {
		return dictLoanFreeFlag;
	}

	public void setDictLoanFreeFlag(String dictLoanFreeFlag) {
		this.dictLoanFreeFlag = dictLoanFreeFlag;
	}

	public String getBorrowerName() {
		return borrowerName;
	}

	public void setBorrowerName(String borrowerName) {
		this.borrowerName = borrowerName;
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

	public Integer getBorrowDaysSurplusFrom() {
		return borrowDaysSurplusFrom;
	}

	public void setBorrowDaysSurplusFrom(Integer borrowDaysSurplusFrom) {
		this.borrowDaysSurplusFrom = borrowDaysSurplusFrom;
	}

	public Integer getBorrowDaysSurplusTo() {
		return borrowDaysSurplusTo;
	}

	public void setBorrowDaysSurplusTo(Integer borrowDaysSurplusTo) {
		this.borrowDaysSurplusTo = borrowDaysSurplusTo;
	}

	

	public BigDecimal getBorrowMonthRate() {
		return borrowMonthRate;
	}

	public void setBorrowMonthRate(BigDecimal borrowMonthRate) {
		this.borrowMonthRate = borrowMonthRate;
	}

	
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

	

	public String getBorrowBackmoneyFirday() {
		return borrowBackmoneyFirday;
	}

	public void setBorrowBackmoneyFirday(String borrowBackmoneyFirday) {
		this.borrowBackmoneyFirday = borrowBackmoneyFirday;
	}

	public Date getBorrowCreditDateUsableFrom() {
		return borrowCreditDateUsableFrom;
	}

	public void setBorrowCreditDateUsableFrom(Date borrowCreditDateUsableFrom) {
		this.borrowCreditDateUsableFrom = borrowCreditDateUsableFrom;
	}

	public Date getBorrowCreditDateUsableTo() {
		return borrowCreditDateUsableTo;
	}

	public void setBorrowCreditDateUsableTo(Date borrowCreditDateUsableTo) {
		this.borrowCreditDateUsableTo = borrowCreditDateUsableTo;
	}

	public Date getBorrowBackmoneyFirdayFrom() {
		return borrowBackmoneyFirdayFrom;
	}

	public void setBorrowBackmoneyFirdayFrom(Date borrowBackmoneyFirdayFrom) {
		this.borrowBackmoneyFirdayFrom = borrowBackmoneyFirdayFrom;
	}

	public Date getBorrowBackmoneyFirdayTo() {
		return borrowBackmoneyFirdayTo;
	}

	public void setBorrowBackmoneyFirdayTo(Date borrowBackmoneyFirdayTo) {
		this.borrowBackmoneyFirdayTo = borrowBackmoneyFirdayTo;
	}

	public String getCreditMonableIds() {
		return creditMonableIds;
	}

	public void setCreditMonableIds(String creditMonableIds) {
		this.creditMonableIds = creditMonableIds;
	}

	public String getTrusteeFlag() {
		return trusteeFlag;
	}

	public void setTrusteeFlag(String trusteeFlag) {
		this.trusteeFlag = trusteeFlag;
	}

	public List<String> getLoanIds() {
		return LoanIds;
	}

	public void setLoanIds(List<String> loanIds) {
		LoanIds = loanIds;
	}

	public List<String> getCreditMonableIdLs() {
		return creditMonableIdLs;
	}

	public void setCreditMonableIdLs(List<String> creditMonableIdLs) {
		this.creditMonableIdLs = creditMonableIdLs;
	}


	public String getCreditMonableId() {
		return creditMonableId;
	}

	public void setCreditMonableId(String creditMonableId) {
		this.creditMonableId = creditMonableId;
	}

	public List<String> getCreditMonableIdList() {
		return creditMonableIdList;
	}

	public void setCreditMonableIdList(List<String> creditMonableIdList) {
		this.creditMonableIdList = creditMonableIdList;
	}

	public List<String> getOldLoadIdCard() {
		return oldLoadIdCard;
	}

	public void setOldLoadIdCard(List<String> oldLoadIdCard) {
		this.oldLoadIdCard = oldLoadIdCard;
	}

	public List<String> getFilterLoanIdCrards() {
		return filterLoanIdCrards;
	}

	public void setFilterLoanIdCrards(List<String> filterLoanIdCrards) {
		this.filterLoanIdCrards = filterLoanIdCrards;
	}

	public Date getLoanOutmoneyDay() {
		return loanOutmoneyDay;
	}

	public void setLoanOutmoneyDay(Date loanOutmoneyDay) {
		this.loanOutmoneyDay = loanOutmoneyDay;
	}

	public String getTrusteeFlagOrderby() {
		return trusteeFlagOrderby;
	}

	public void setTrusteeFlagOrderby(String trusteeFlagOrderby) {
		this.trusteeFlagOrderby = trusteeFlagOrderby;
	}

	public String getDicLoanDistinguish() {
		return dicLoanDistinguish;
	}

	public void setDicLoanDistinguish(String dicLoanDistinguish) {
		this.dicLoanDistinguish = dicLoanDistinguish;
	}

	public List<String> getBorrowerJobls() {
		return borrowerJobls;
	}

	public void setBorrowerJobls(List<String> borrowerJobls) {
		this.borrowerJobls = borrowerJobls;
	}
	
	
}
