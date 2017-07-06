package com.creditharmony.fortune.app.webservice.ocr.entity;

import java.io.Serializable;

/**
 * 投资模式信息表
 * @Class Name OcrInvestmentProduct
 * @Create In 2016年4月14日
 */
public class OcrInvestmentProduct implements Serializable{
	
	 /**
	 * long serialVersionUID 
	 */
	private static final long serialVersionUID = 1L;
	
	private String productId;//投资模式id
	private String productName;//投资模式名称
	private String investmentTerm;//投资期限
	private String annualisedReturn;//预期年化收益率
	private String introduction;//特点
	
	
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getInvestmentTerm() {
		return investmentTerm;
	}
	public void setInvestmentTerm(String investmentTerm) {
		this.investmentTerm = investmentTerm;
	}
	public String getAnnualisedReturn() {
		return annualisedReturn;
	}
	public void setAnnualisedReturn(String annualisedReturn) {
		this.annualisedReturn = annualisedReturn;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	
}
