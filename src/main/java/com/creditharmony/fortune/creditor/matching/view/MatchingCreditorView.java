package com.creditharmony.fortune.creditor.matching.view;

import java.math.BigDecimal;
import java.util.Date;

import com.creditharmony.core.persistence.DataEntity;
/**
 * 
 * @Class 待债权推荐信息展示
 * @author 柳慧
 * @Create In 2015年12月11日
 */
public class MatchingCreditorView extends DataEntity<MatchingCreditorView>{

	/**
	 * long serialVersionUID 
	 */
	private static final long serialVersionUID = 1L;
	private String matchingId;
	private String lendCode;// 出借编号
	private String customerName;// 客户名称
	private String customerCode;// 客户编号
	private String orgName;// 客户所属机构(营业部)
	private String productName;// 产品名称
	private String productCode;//产品code 
	private String applyPay;// 付款方式
	private Date startApplyLendDay;// 初始出借日期
	private BigDecimal startApplyLendMoney;// 初始出借金额
	private Date applyLendDay;// 出借日期
	private BigDecimal tradeMateMoney;// 匹配金额
	private String matchingFirstdayFlag;// 账单类型
	private Date applyExpireDay;// 到期日期
	private String tradeCreditStatus;// 债权交易状态(0:暂存，1:开始款款，2:出借正常到期关闭，3:借款正常到期关闭，4:出借提前到期关闭，5:借款提前到期关闭，6:未开始被关闭)
	private int matchingBillDay;// 账单日
	private BigDecimal matchingBorrowQuota;//本期推荐金额
	private Date matchingInterestStart;  // 本期出借日期
	private String matchingStatus;//债权状态
	private BigDecimal productRate;//出借人对应的产品利率
	private String storeOrgId; // 该笔出借的营业部
	private String cityOrgId; // 该笔出借城市
	private String cityId;  // 城市编号
	private  String backType;
	private Integer matchingTotal;  // 共有几期
	private String mobilephone; // 客户移动电话
	private String customerEmail;
	private String matchingUserName; // 匹配人姓名
	private String provinceId; // 省份编号
	private String accountBank; //客户付款银行
	private String  dictApplyDeductType; // 划扣平台
	private Date applyDeductDay; // 计划划扣日期
	private String loanType; // 债权类型
	private Date matchingExpireDay; // 本期到期日期
	private Integer matchingNow;//当前期
	private String managerCode; // 理财经理编号
	private String matchingFlag;// 匹配标识
	public Date getMatchingInterestStart() {
		return matchingInterestStart;
	}
	public void setMatchingInterestStart(Date matchingInterestStart) {
		this.matchingInterestStart = matchingInterestStart;
	}
	public BigDecimal getProductRate() {
		return productRate;
	}
	public void setProductRate(BigDecimal productRate) {
		this.productRate = productRate;
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
	
	public String getMatchingStatus() {
		return matchingStatus;
	}
	public void setMatchingStatus(String matchingStatus) {
		this.matchingStatus = matchingStatus;
	}
	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	
	public String getApplyPay() {
		return applyPay;
	}
	public void setApplyPay(String applyPay) {
		this.applyPay = applyPay;
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
	public Date getApplyLendDay() {
		return applyLendDay;
	}
	public void setApplyLendDay(Date applyLendDay) {
		this.applyLendDay = applyLendDay;
	}
	public BigDecimal getTradeMateMoney() {
		return tradeMateMoney;
	}
	public void setTradeMateMoney(BigDecimal tradeMateMoney) {
		this.tradeMateMoney = tradeMateMoney;
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
	public String getTradeCreditStatus() {
		return tradeCreditStatus;
	}
	public void setTradeCreditStatus(String tradeCreditStatus) {
		this.tradeCreditStatus = tradeCreditStatus;
	}
	public int getMatchingBillDay() {
		return matchingBillDay;
	}
	public void setMatchingBillDay(int matchingBillDay) {
		this.matchingBillDay = matchingBillDay;
	}
	
	public BigDecimal getMatchingBorrowQuota() {
		return matchingBorrowQuota;
	}
	public void setMatchingBorrowQuota(BigDecimal matchingBorrowQuota) {
		this.matchingBorrowQuota = matchingBorrowQuota;
	}
	public String getMatchingId() {
		return matchingId;
	}
	public void setMatchingId(String matchingId) {
		this.matchingId = matchingId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getStoreOrgId() {
		return storeOrgId;
	}
	public void setStoreOrgId(String storeOrgId) {
		this.storeOrgId = storeOrgId;
	}
	public String getCityOrgId() {
		return cityOrgId;
	}
	public void setCityOrgId(String cityOrgId) {
		this.cityOrgId = cityOrgId;
	}
	public String getBackType() {
		return backType;
	}
	public void setBackType(String backType) {
		this.backType = backType;
	}
	public Integer getMatchingTotal() {
		return matchingTotal;
	}
	public void setMatchingTotal(Integer matchingTotal) {
		this.matchingTotal = matchingTotal;
	}
	public String getMobilephone() {
		return mobilephone;
	}
	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
	}
	public String getCustomerEmail() {
		return customerEmail;
	}
	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}
	public String getCityId() {
		return cityId;
	}
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	public String getMatchingUserName() {
		return matchingUserName;
	}
	public void setMatchingUserName(String matchingUserName) {
		this.matchingUserName = matchingUserName;
	}
	public String getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}
	public String getAccountBank() {
		return accountBank;
	}
	public void setAccountBank(String accountBank) {
		this.accountBank = accountBank;
	}
	public String getDictApplyDeductType() {
		return dictApplyDeductType;
	}
	public void setDictApplyDeductType(String dictApplyDeductType) {
		this.dictApplyDeductType = dictApplyDeductType;
	}
	public Date getApplyDeductDay() {
		return applyDeductDay;
	}
	public void setApplyDeductDay(Date applyDeductDay) {
		this.applyDeductDay = applyDeductDay;
	}
	public String getLoanType() {
		return loanType;
	}
	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}
	public Date getMatchingExpireDay() {
		return matchingExpireDay;
	}
	public void setMatchingExpireDay(Date matchingExpireDay) {
		this.matchingExpireDay = matchingExpireDay;
	}
	public Integer getMatchingNow() {
		return matchingNow;
	}
	public void setMatchingNow(Integer matchingNow) {
		this.matchingNow = matchingNow;
	}
	public String getManagerCode() {
		return managerCode;
	}
	public void setManagerCode(String managerCode) {
		this.managerCode = managerCode;
	}
	public String getMatchingFlag() {
		return matchingFlag;
	}
	public void setMatchingFlag(String matchingFlag) {
		this.matchingFlag = matchingFlag;
	}
	
	 
}