package com.creditharmony.fortune.back.money.common.view;

import java.util.Date;

import com.creditharmony.core.persistence.DataEntity;

/**
 * 页面搜索封装Bean
 * @Class Name SearchObject
 * @author 陈广鹏
 * @Create In 2015年12月2日
 */
public class SearchView extends DataEntity<SearchView> {

	/**
	 * long serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	private String custName; // 客户姓名
	private String custCode; // 客户编号
	private String applyCode; // 出借编号
	private String department; // 营业部
	private String city; // 城市
	private String backMoneyType; // 回款类型
	private String applyLendDateFrom; // 计划出借日期
	private String applyLendDateTo;
	private Date linitTimeFrom; // 到期日期
	private Date linitTimeTo;
	private String productName; // 出借产品
	private Date applyDeductDateFrom; // 计划划扣日期
	private Date applyDeductDateTo;
	private Double applyLendMoneyFrom; // 计划出借金额
	private Double applyLendMoneyTo;
	private String applyPay; // 付款方式
	private String backMoneyDateFrom; // 回款日期
	private String backMoneyDateTo;
	private String accountCardOrBooklet;// 卡或折

	private String userId; // 登录用户ID

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getCustCode() {
		return custCode;
	}

	public void setCustCode(String custCode) {
		this.custCode = custCode;
	}

	public String getApplyCode() {
		return applyCode;
	}

	public void setApplyCode(String applyCode) {
		this.applyCode = applyCode;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getBackMoneyType() {
		return backMoneyType;
	}

	public void setBackMoneyType(String backMoneyType) {
		this.backMoneyType = backMoneyType;
	}

	public String getApplyLendDateFrom() {
		return applyLendDateFrom;
	}

	public void setApplyLendDateFrom(String applyLendDateFrom) {
		this.applyLendDateFrom = applyLendDateFrom;
	}

	public String getApplyLendDateTo() {
		return applyLendDateTo;
	}

	public void setApplyLendDateTo(String applyLendDateTo) {
		this.applyLendDateTo = applyLendDateTo;
	}

	public Date getLinitTimeFrom() {
		return linitTimeFrom;
	}

	public void setLinitTimeFrom(Date linitTimeFrom) {
		this.linitTimeFrom = linitTimeFrom;
	}

	public Date getLinitTimeTo() {
		return linitTimeTo;
	}

	public void setLinitTimeTo(Date linitTimeTo) {
		this.linitTimeTo = linitTimeTo;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Date getApplyDeductDateFrom() {
		return applyDeductDateFrom;
	}

	public void setApplyDeductDateFrom(Date applyDeductDateFrom) {
		this.applyDeductDateFrom = applyDeductDateFrom;
	}

	public Date getApplyDeductDateTo() {
		return applyDeductDateTo;
	}

	public void setApplyDeductDateTo(Date applyDeductDateTo) {
		this.applyDeductDateTo = applyDeductDateTo;
	}

	public Double getApplyLendMoneyFrom() {
		return applyLendMoneyFrom;
	}

	public void setApplyLendMoneyFrom(Double applyLendMoneyFrom) {
		this.applyLendMoneyFrom = applyLendMoneyFrom;
	}

	public Double getApplyLendMoneyTo() {
		return applyLendMoneyTo;
	}

	public void setApplyLendMoneyTo(Double applyLendMoneyTo) {
		this.applyLendMoneyTo = applyLendMoneyTo;
	}

	public String getApplyPay() {
		return applyPay;
	}

	public void setApplyPay(String applyPay) {
		this.applyPay = applyPay;
	}

	public String getBackMoneyDateFrom() {
		return backMoneyDateFrom;
	}

	public void setBackMoneyDateFrom(String backMoneyDateFrom) {
		this.backMoneyDateFrom = backMoneyDateFrom;
	}

	public String getBackMoneyDateTo() {
		return backMoneyDateTo;
	}

	public void setBackMoneyDateTo(String backMoneyDateTo) {
		this.backMoneyDateTo = backMoneyDateTo;
	}

	public String getAccountCardOrBooklet() {
		return accountCardOrBooklet;
	}

	public void setAccountCardOrBooklet(String accountCardOrBooklet) {
		this.accountCardOrBooklet = accountCardOrBooklet;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
