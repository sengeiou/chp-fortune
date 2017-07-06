/**
 * @Probject Name: chp-fortune
 * @Path: com.creditharmony.fortune.creditor.matching.entity.extCreditorTradeEx.java
 * @Create By 胡体勇
 * @Create In 2015年12月9日 下午4:04:27
 */
package com.creditharmony.fortune.creditor.matching.entity.ext;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.creditharmony.core.persistence.DataEntity;

/**
 * @Class Name CreditorTradeEx
 * @author 胡体勇
 * @Create In 2015年12月9日
 */
public class CreditorTradeEx extends DataEntity<CreditorTradeEx>{

	/**
	 * long serialVersionUID 
	 */
	private static final long serialVersionUID = 1L;
	private String matchingId;//推荐Id
	private String lendCode;//出借编号
    private String customerName;// 客户名称
    private String customerCode;// 客户编号
    private String org;// 客户所属机构(营业部)
    private List<String> orgCode;// 营业部编号
    private String orgName;// 营业部名称
    private String addrCity;// 客户所在城市
    private Date applyLendDay;// 出借日期
    private Date startApplyLendDay;// 初始出借日期
    private BigDecimal startApplyLendMoney;// 初始出借金额
    private String productName;// 产品名称
    private String product;// 产品
    private List<String> productCode;// 产品名称code
    private String productCodeStr;
    private String applyPay;// 付款方式
    private String applyEndStatus;// 出借完结状态
    private String matchingPayStatus;// 结算状态
    private String  matchingStatus;// 债权状态
    private BigDecimal matchingMatchMoney;//匹配金额
    private String matchingFileSendStatus;// 账单发送状态
    private String matchingFileStatus;// 合成状态
    private String loanBillRecv;// 账单收取方式
    private String customerEmail;// 客户邮箱
    private Integer matchingBillDay;// 账单日
    private Date applyDeductDay;// 划扣日期
    private String matchingFirstdayFlag;// 账单类型
    private Date applyExpireDay;// 到期日期
    private BigDecimal littleMoney;// 金额范围
    private BigDecimal bigMoney;// 金额范围
    private String loanCode;// 借款Code
    private String operateType;// 操作类型
    private Integer matchingOrderStatus;// 债权订购标识
    private String tradeCreditStatus;//债权交易状态
    private BigDecimal matchingBorrowQuota;// 已推荐金额
    private List<String> listMatchingId;
    private String city;
    private String totalMoney;// 页面统计总金额
    private String matchingBillDayStr;// 账单日
    private List<Integer> matchingBillDays;                 //   账单日
    private String startApplyLendDayStart;
    private String startApplyLendDayEnd;
    private Date matchingInterestStart;// 本期出借日期
	private String matchingInterestStartStart;// 本期出借日期  开始
	private String matchingInterestStartEnd; // 本期出借日期 结束
	private List<String> lendStatusLst; // 出借状态
	private String matchingFlag;// 匹配标识（列表回显）
	private String dictMatchingFlagType; // 匹配标识（搜索词）
	private List<String> dictMatchingFlagTypes; // 匹配标识（sql使用）
	 
    private String cityId;
    
    private String  beforMatchingStatus;// 债权修改前状态
    
    private String  selStatus;	//选择状态
    private String  cxStatus;	//撤销状态
    private String  ncxStatus;	//其他
    
    private Date matchingOperateStart;// 操作日期
	private String matchingOperateStartStart;// 操作日期  开始
	private String matchingOperateStartEnd; // 操作日期 结束
	
	private String replaceIdentify; //替换标识
	
	private String chaStatus; //选择状态
	private String thStatus;  //替换状态
	private String othStatus; //其他状态
    
	
	public String getReplaceIdentify() {
		return replaceIdentify;
	}
	public void setReplaceIdentify(String replaceIdentify) {
		this.replaceIdentify = replaceIdentify;
	}
	public String getChaStatus() {
		return chaStatus;
	}
	public void setChaStatus(String chaStatus) {
		this.chaStatus = chaStatus;
	}
	public String getThStatus() {
		return thStatus;
	}
	public void setThStatus(String thStatus) {
		this.thStatus = thStatus;
	}
	public String getOthStatus() {
		return othStatus;
	}
	public void setOthStatus(String othStatus) {
		this.othStatus = othStatus;
	}
	public Date getMatchingOperateStart() {
		return matchingOperateStart;
	}
	public void setMatchingOperateStart(Date matchingOperateStart) {
		this.matchingOperateStart = matchingOperateStart;
	}
	public String getMatchingOperateStartStart() {
		return matchingOperateStartStart;
	}
	public void setMatchingOperateStartStart(String matchingOperateStartStart) {
		this.matchingOperateStartStart = matchingOperateStartStart;
	}
	public String getMatchingOperateStartEnd() {
		return matchingOperateStartEnd;
	}
	public void setMatchingOperateStartEnd(String matchingOperateStartEnd) {
		this.matchingOperateStartEnd = matchingOperateStartEnd;
	}
	public String getSelStatus() {
		return selStatus;
	}
	public void setSelStatus(String selStatus) {
		this.selStatus = selStatus;
	}
	public String getCxStatus() {
		return cxStatus;
	}
	public void setCxStatus(String cxStatus) {
		this.cxStatus = cxStatus;
	}
	public String getNcxStatus() {
		return ncxStatus;
	}
	public void setNcxStatus(String ncxStatus) {
		this.ncxStatus = ncxStatus;
	}
	public String getBeforMatchingStatus() {
		return beforMatchingStatus;
	}
	public void setBeforMatchingStatus(String beforMatchingStatus) {
		this.beforMatchingStatus = beforMatchingStatus;
	}
	public Date getMatchingInterestStart() {
		return matchingInterestStart;
	}
	public void setMatchingInterestStart(Date matchingInterestStart) {
		this.matchingInterestStart = matchingInterestStart;
	}
	public String getMatchingId() {
		return matchingId;
	}
	public void setMatchingId(String matchingId) {
		this.matchingId = matchingId;
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
	public String getCustomerCode() {
		return customerCode;
	}
	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}
	public String getOrg() {
		return org;
	}
	public void setOrg(String org) {
		this.org = org;
	}
	public String getAddrCity() {
		return addrCity;
	}
	public void setAddrCity(String addrCity) {
		this.addrCity = addrCity;
	}
	public Date getApplyLendDay() {
		return applyLendDay;
	}
	public void setApplyLendDay(Date applyLendDay) {
		this.applyLendDay = applyLendDay;
	}
	public Date getStartApplyLendDay() {
		return startApplyLendDay;
	}
	public void setStartApplyLendDay(Date startApplyLendDay) {
		this.startApplyLendDay = startApplyLendDay;
	}
	public BigDecimal getStartApplyLendMoney() {
		return startApplyLendMoney;
	}
	public void setStartApplyLendMoney(BigDecimal startApplyLendMoney) {
		this.startApplyLendMoney = startApplyLendMoney;
	}
	public String getApplyPay() {
		return applyPay;
	}
	public void setApplyPay(String applyPay) {
		this.applyPay = applyPay;
	}
	public String getApplyEndStatus() {
		return applyEndStatus;
	}
	public void setApplyEndStatus(String applyEndStatus) {
		this.applyEndStatus = applyEndStatus;
	}
	public String getMatchingPayStatus() {
		return matchingPayStatus;
	}
	public void setMatchingPayStatus(String matchingPayStatus) {
		this.matchingPayStatus = matchingPayStatus;
	}
	
	public String getMatchingStatus() {
		return matchingStatus;
	}
	public void setMatchingStatus(String matchingStatus) {
		this.matchingStatus = matchingStatus;
	}
	public BigDecimal getMatchingMatchMoney() {
		return matchingMatchMoney;
	}
	public void setMatchingMatchMoney(BigDecimal matchingMatchMoney) {
		this.matchingMatchMoney = matchingMatchMoney;
	}
	public String getMatchingFileSendStatus() {
		return matchingFileSendStatus;
	}
	public void setMatchingFileSendStatus(String matchingFileSendStatus) {
		this.matchingFileSendStatus = matchingFileSendStatus;
	}
	public String getMatchingFileStatus() {
		return matchingFileStatus;
	}
	public void setMatchingFileStatus(String matchingFileStatus) {
		this.matchingFileStatus = matchingFileStatus;
	}
	public String getLoanBillRecv() {
		return loanBillRecv;
	}
	public void setLoanBillRecv(String loanBillRecv) {
		this.loanBillRecv = loanBillRecv;
	}
	public String getCustomerEmail() {
		return customerEmail;
	}
	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}
	public Integer getMatchingBillDay() {
		return matchingBillDay;
	}
	public void setMatchingBillDay(Integer matchingBillDay) {
		this.matchingBillDay = matchingBillDay;
	}
	public Date getApplyDeductDay() {
		return applyDeductDay;
	}
	public void setApplyDeductDay(Date applyDeductDay) {
		this.applyDeductDay = applyDeductDay;
	}
	public String getMatchingFirstdayFlag() {
		return matchingFirstdayFlag;
	}
	public void setMatchingFirstdayFlag(String matchingFirstdayFlag) {
		this.matchingFirstdayFlag = matchingFirstdayFlag;
	}
	public Date getApplyExpireDay() {
		return applyExpireDay;
	}
	public void setApplyExpireDay(Date applyExpireDay) {
		this.applyExpireDay = applyExpireDay;
	}
	public BigDecimal getLittleMoney() {
		return littleMoney;
	}
	public void setLittleMoney(BigDecimal littleMoney) {
		this.littleMoney = littleMoney;
	}
	public BigDecimal getBigMoney() {
		return bigMoney;
	}
	public void setBigMoney(BigDecimal bigMoney) {
		this.bigMoney = bigMoney;
	}
	public String getLoanCode() {
		return loanCode;
	}
	public void setLoanCode(String loanCode) {
		this.loanCode = loanCode;
	}
	public String getOperateType() {
		return operateType;
	}
	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}
	public Integer getMatchingOrderStatus() {
		return matchingOrderStatus;
	}
	public void setMatchingOrderStatus(Integer matchingOrderStatus) {
		this.matchingOrderStatus = matchingOrderStatus;
	}
	public String getTradeCreditStatus() {
		return tradeCreditStatus;
	}
	public void setTradeCreditStatus(String tradeCreditStatus) {
		this.tradeCreditStatus = tradeCreditStatus;
	}
	public BigDecimal getMatchingBorrowQuota() {
		return matchingBorrowQuota;
	}
	public void setMatchingBorrowQuota(BigDecimal matchingBorrowQuota) {
		this.matchingBorrowQuota = matchingBorrowQuota;
	}
	public List<String> getListMatchingId() {
		return listMatchingId;
	}
	public void setListMatchingId(List<String> listMatchingId) {
		this.listMatchingId = listMatchingId;
	}
	
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getTotalMoney() {
		return totalMoney;
	}
	public void setTotalMoney(String totalMoney) {
		this.totalMoney = totalMoney;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public List<String> getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(List<String> orgCode) {
		this.orgCode = orgCode;
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	
	public String getMatchingBillDayStr() {
		return matchingBillDayStr;
	}
	public void setMatchingBillDayStr(String matchingBillDayStr) {
		this.matchingBillDayStr = matchingBillDayStr;
	}
	public List<Integer> getMatchingBillDays() {
		return matchingBillDays;
	}
	public void setMatchingBillDays(List<Integer> matchingBillDays) {
		this.matchingBillDays = matchingBillDays;
	}
	public List<String> getProductCode() {
		return productCode;
	}
	public void setProductCode(List<String> productCode) {
		this.productCode = productCode;
	}
	public String getProductCodeStr() {
		return productCodeStr;
	}
	public void setProductCodeStr(String productCodeStr) {
		this.productCodeStr = productCodeStr;
	}
	public String getStartApplyLendDayStart() {
		return startApplyLendDayStart;
	}
	public void setStartApplyLendDayStart(String startApplyLendDayStart) {
		this.startApplyLendDayStart = startApplyLendDayStart;
	}
	public String getStartApplyLendDayEnd() {
		return startApplyLendDayEnd;
	}
	public void setStartApplyLendDayEnd(String startApplyLendDayEnd) {
		this.startApplyLendDayEnd = startApplyLendDayEnd;
	}
	public String getCityId() {
		return cityId;
	}
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	public String getMatchingInterestStartStart() {
		return matchingInterestStartStart;
	}
	public void setMatchingInterestStartStart(String matchingInterestStartStart) {
		this.matchingInterestStartStart = matchingInterestStartStart;
	}
	public String getMatchingInterestStartEnd() {
		return matchingInterestStartEnd;
	}
	public void setMatchingInterestStartEnd(String matchingInterestStartEnd) {
		this.matchingInterestStartEnd = matchingInterestStartEnd;
	}
	public List<String> getLendStatusLst() {
		return lendStatusLst;
	}
	public void setLendStatusLst(List<String> lendStatusLst) {
		this.lendStatusLst = lendStatusLst;
	}
	public String getMatchingFlag() {
		return matchingFlag;
	}
	public void setMatchingFlag(String matchingFlag) {
		this.matchingFlag = matchingFlag;
	}
	public String getDictMatchingFlagType() {
		return dictMatchingFlagType;
	}
	public void setDictMatchingFlagType(String dictMatchingFlagType) {
		this.dictMatchingFlagType = dictMatchingFlagType;
	}
	public List<String> getDictMatchingFlagTypes() {
		return dictMatchingFlagTypes;
	}
	public void setDictMatchingFlagTypes(List<String> dictMatchingFlagTypes) {
		this.dictMatchingFlagTypes = dictMatchingFlagTypes;
	}
	

	
}
