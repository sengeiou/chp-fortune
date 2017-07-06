package com.creditharmony.fortune.lend.approve.view;

import java.util.Date;

import com.creditharmony.bpm.frame.view.BaseTaskItemView;
import com.creditharmony.core.excel.annotation.ExcelField;

/**
 * 出借申请审批待办列表视图类
 * 
 * @Class Name LendApplyApprovalItemView
 * @author 孙凯文
 * @Create In 2015年12月23日
 */
public class LendApplyApprovalItemView extends BaseTaskItemView {
	
	// 客户名称
	@ExcelField(title = "客户姓名")
	private String customerName;
	// 客户编号
	@ExcelField(title = "客户编号")
	private String customerCode;
	// 出借编号
	@ExcelField(title = "出借编号")
	private String applyCode;
	// 出借日期
	@ExcelField(title = "计划出借日期")
	private Date lendDate;
	// 出借金额
	@ExcelField(title = "计划出借金额")
	private Double lendMoney;
	// 门店
	private String department;
	// 出借模式
	private String lendProduct;
	// 出借模式名称
	@ExcelField(title = "出借方式")
	private String lendProductName;
	// 划扣平台
	private String deductType;
	
	// 划扣平台名称
	@ExcelField(title = "划扣平台")
	private String deductTypeName;
	// 付款方式
	private String payState;
	// 付款方式名称
	@ExcelField(title = "付款方式")
	private String payStateName;
	// 省份
	private String province;
	// 城市
	private String city;
	// 计划划扣日期
	private Date deductDate;
	// 省份名称
	@ExcelField(title = "省")
	private String provinceName;
	// 城市名称
	@ExcelField(title = "市")
	private String cityName;

	// 客户编号
	@ExcelField(title = "营业部")
	private String storesName;
	
	// 银行
	@ExcelField(title = "出借银行")
	private String deductBank;
	
	//审批人
	private String auditor;
	

	public String getDeductBank() {
		return deductBank;
	}

	public void setDeductBank(String deductBank) {
		this.deductBank = deductBank;
	}

	public String getStoresName() {
		return storesName;
	}

	public void setStoresName(String storesName) {
		this.storesName = storesName;
	}

	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
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

	public String getLendProduct() {
		return lendProduct;
	}

	public void setLendProduct(String lendProduct) {
		this.lendProduct = lendProduct;
	}

	public Date getLendDate() {
		return lendDate;
	}

	public void setLendDate(Date lendDate) {
		this.lendDate = lendDate;
	}

	public Double getLendMoney() {
		return lendMoney;
	}

	public void setLendMoney(Double lendMoney) {
		this.lendMoney = lendMoney;
	}

	public String getPayState() {
		return payState;
	}

	public void setPayState(String payState) {
		this.payState = payState;
	}

	public String getDeductType() {
		return deductType;
	}

	public void setDeductType(String deductType) {
		this.deductType = deductType;
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


	public Date getDeductDate() {
		return deductDate;
	}

	public void setDeductDate(Date deductDate) {
		this.deductDate = deductDate;
	}

	
	public String getLendProductName() {
		return lendProductName;
	}

	public void setLendProductName(String lendProductName) {
		this.lendProductName = lendProductName;
	}

	public String getDeductTypeName() {
		return deductTypeName;
	}

	public void setDeductTypeName(String deductTypeName) {
		this.deductTypeName = deductTypeName;
	}

	public String getPayStateName() {
		return payStateName;
	}

	public void setPayStateName(String payStateName) {
		this.payStateName = payStateName;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getAuditor() {
		return auditor;
	}

	public void setAuditor(String auditor) {
		this.auditor = auditor;
	}
	
	

}
