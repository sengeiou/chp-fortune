package com.creditharmony.fortune.look.apply.entity;

import java.util.Date;

import com.creditharmony.fortune.look.apply.util.ExcelField4BD;


/**
 * 导出划扣
 * @Class Name LendApplyLookExportDecutExcelEx
 * @author 肖长伟
 * @Create In 2016年3月16日
 */
public class LendApplyLookExportDecutExcelEx {

	// 财富中心
	@ExcelField4BD(title = "财富中心")
	private String fortunCenter;
	// 客户姓名
	@ExcelField4BD(title = "客户姓名")
	private String custName;
	// 出借编号
	@ExcelField4BD(title = "出借编号")
	private String lendCode;
	// 划扣日期
	@ExcelField4BD(title = "划扣日期")
	private Date huakou;
	// 首次出借日期
	@ExcelField4BD(title = "首次出借日期 ")
	private Date applyLendDay;
	// 出借金额
	@ExcelField4BD(title = "出借金额", dataType=Double.class, format="0.00")
	private String applyLendMoney;
	// 划扣金额
	@ExcelField4BD(title = "划扣金额", dataType=Double.class, format="0.00")
	private String applyDeductMoney;
	// 出借产品
	@ExcelField4BD(title = "出借产品")
	private String productName;
	// 理财经理
	@ExcelField4BD(title = "理财经理")
	private String managerCode;
	// 门店
	@ExcelField4BD(title = "门店 ")
	private String storeName;
	// 划扣行别
	@ExcelField4BD(title = "划扣行别")
	private String openBank;
	// 付款方式
	@ExcelField4BD(title = "付款方式")
	private String payType; // apply_pay
	// 反馈结果
	@ExcelField4BD(title="反馈结果")
	private String feedBack;
	// 划扣平台
	@ExcelField4BD(title="划扣平台")
	private String dictApplyDeductType;
	
	
	public String getFortunCenter() {
		return fortunCenter;
	}
	public void setFortunCenter(String fortunCenter) {
		this.fortunCenter = fortunCenter;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getLendCode() {
		return lendCode;
	}
	public void setLendCode(String lendCode) {
		this.lendCode = lendCode;
	}
	public Date getHuakou() {
		return huakou;
	}
	public void setHuakou(Date huakou) {
		this.huakou = huakou;
	}
	
	public Date getApplyLendDay() {
		return applyLendDay;
	}
	public void setApplyLendDay(Date applyLendDay) {
		this.applyLendDay = applyLendDay;
	}
	public String getApplyLendMoney() {
		return applyLendMoney;
	}
	public void setApplyLendMoney(String applyLendMoney) {
		this.applyLendMoney = applyLendMoney;
	}
	public String getApplyDeductMoney() {
		return applyDeductMoney;
	}
	public void setApplyDeductMoney(String applyDeductMoney) {
		this.applyDeductMoney = applyDeductMoney;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getManagerCode() {
		return managerCode;
	}
	public void setManagerCode(String managerCode) {
		this.managerCode = managerCode;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getOpenBank() {
		return openBank;
	}
	public void setOpenBank(String openBank) {
		this.openBank = openBank;
	}
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	public String getFeedBack() {
		return feedBack;
	}
	public void setFeedBack(String feedBack) {
		this.feedBack = feedBack;
	}
	public String getDictApplyDeductType() {
		return dictApplyDeductType;
	}
	public void setDictApplyDeductType(String dictApplyDeductType) {
		this.dictApplyDeductType = dictApplyDeductType;
	}
	
	
	

}
