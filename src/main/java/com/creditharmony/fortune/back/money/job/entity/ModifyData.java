package com.creditharmony.fortune.back.money.job.entity;

import java.math.BigDecimal;
import java.util.Date;


/**
 * 封装需要更新的数据
 * @Class Name ModifyData
 * @author 陈广鹏
 * @Create In 2016年12月23日
 */
public class ModifyData {
	
	
	private String lendCode;			// 出借编号
	private Date applyLendDay;			// 出借日期
	private BigDecimal applyLendMoney;	// 出借金额
	private Date finalLinitDate;		// 到期日期
	private String xinhebaoType;		// 信和宝类型
	private String productCode;			// 产品编号
	private int applyBillday; 			// 账单日

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

	public BigDecimal getApplyLendMoney() {
		return applyLendMoney;
	}

	public void setApplyLendMoney(BigDecimal applyLendMoney) {
		this.applyLendMoney = applyLendMoney;
	}

	public Date getFinalLinitDate() {
		return finalLinitDate;
	}

	public void setFinalLinitDate(Date finalLinitDate) {
		this.finalLinitDate = finalLinitDate;
	}

	public String getXinhebaoType() {
		return xinhebaoType;
	}

	public void setXinhebaoType(String xinhebaoType) {
		this.xinhebaoType = xinhebaoType;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public int getApplyBillday() {
		return applyBillday;
	}

	public void setApplyBillday(int applyBillday) {
		this.applyBillday = applyBillday;
	}

}
