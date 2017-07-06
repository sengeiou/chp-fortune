package com.creditharmony.fortune.template.entity.backInterest;

import java.io.Serializable;
import java.util.Date;

import com.creditharmony.core.excel.annotation.ExcelField;
import com.creditharmony.core.fortune.type.PayMent;

/**
 * 待回息申请确认列表上传回息金额
 * @Class Name BackInterestApplyExt 
 * @author 李志伟
 * @Create In 2016年1月5日
 */
public class UploadReturnBackMoney implements Serializable{

	/**
	 * long serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 客户姓名
	 */
	@ExcelField(title = "客户姓名")
	private String customerName;
	
	/**
	 * 出借编号
	 */
	@ExcelField(title = "出借编号")
	private String lendCode;
	
	/**
	 * 初始出借日期
	 */
	@ExcelField(title = "首次出借日期")
	private Date applyLendDay;
	
	/**
	 * 初始出借金额
	 */
	@ExcelField(title = "初始出借金额")
	private Double applyLendMoney;
	
	/**
	 * 出借模式
	 */
	@ExcelField(title = "出借模式")
	private String applyProduct;
	
	/**
	 * 到期日期
	 */
	@ExcelField(title = "到期日期")
	private Double applyExpireDay;

	/**
	 * 序列号
	 */
	@ExcelField(title = "序列号")
	private String backiId;
	
	
	/**
	 * 实际回息金额
	 */
	@ExcelField(title = "实际回息金额")
	private Double backRealMoney;

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getLendCode() {
		return lendCode;
	}

	public void setLendCode(String lendCode) {
		this.lendCode = lendCode;
	}

	public Date getApplyLendDay() {
		return applyLendDay;
	}

	public void setApplyLendDay(Date applyLendDay) {
		this.applyLendDay = applyLendDay;
	}

	public Double getApplyLendMoney() {
		return applyLendMoney;
	}

	public void setApplyLendMoney(Double applyLendMoney) {
		this.applyLendMoney = applyLendMoney;
	}

	public String getApplyProduct() {
		return applyProduct;
	}

	public void setApplyProduct(String applyProduct) {
		this.applyProduct = applyProduct;
	}

	public Double getApplyExpireDay() {
		return applyExpireDay;
	}

	public void setApplyExpireDay(Double applyExpireDay) {
		this.applyExpireDay = applyExpireDay;
	}


	public String getBackiId() {
		return backiId;
	}

	public void setBackiId(String backiId) {
		this.backiId = backiId;
	}

	public Double getBackRealMoney() {
		return backRealMoney;
	}

	public void setBackRealMoney(Double backRealMoney) {
		this.backRealMoney = backRealMoney;
	}
	


}