package com.creditharmony.fortune.redemption.common.view;

import java.util.Date;

import com.creditharmony.bpm.frame.view.BaseTaskItemView;

/**
 * 描述：待办列表对应Bean
 * @Class Name RedemptionFlowTaskItemView
 * @author 陈广鹏
 * @Create In 2015年11月30日
 */
public class RedemptionFlowTaskItemView extends BaseTaskItemView {

	private String customerName;	// 客户姓名
	private String customerCode;	// 客户编号

	private String applyCode;		// 出借编号
	private Date applyLendDate;		// 计划出借日期
	private String applyBillday;	// 账单日
	private Double applyLendMoney;	// 计划出借金额

	private String productName;		// 出借产品
	private String applyDeductType;	// 划扣平台
	private String applyPay;		// 付款方式
	private String department;		// 营业部
	private String province;		// 省
	private String city;			// 市
	private Date backMoneyDay;  //回款日期
	
	public Date getBackMoneyDay() {
		return backMoneyDay;
	}

	public void setBackMoneyDay(Date backMoneyDay) {
		this.backMoneyDay = backMoneyDay;
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

	public String getApplyCode() {
		return applyCode;
	}

	public void setApplyCode(String applyCode) {
		this.applyCode = applyCode;
	}

	public Date getApplyLendDate() {
		return applyLendDate;
	}

	public void setApplyLendDate(Date applyLendDate) {
		this.applyLendDate = applyLendDate;
	}

	public String getApplyBillday() {
		return applyBillday;
	}

	public void setApplyBillday(String applyBillday) {
		this.applyBillday = applyBillday;
	}

	public Double getApplyLendMoney() {
		return applyLendMoney;
	}

	public void setApplyLendMoney(Double applyLendMoney) {
		this.applyLendMoney = applyLendMoney;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getApplyDeductType() {
		return applyDeductType;
	}

	public void setApplyDeductType(String applyDeductType) {
		this.applyDeductType = applyDeductType;
	}

	public String getApplyPay() {
		return applyPay;
	}

	public void setApplyPay(String applyPay) {
		this.applyPay = applyPay;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

}
