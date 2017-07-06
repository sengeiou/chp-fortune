package com.creditharmony.fortune.customer.entity;

import java.math.BigDecimal;

import com.creditharmony.core.persistence.DataEntity;

/**
 * 产品实体类
 * 
 * @Class Name Product
 * @author 孙凯文
 * @Create In 2016年1月7日
 */
public class Product extends DataEntity<Product> {
	private static final long serialVersionUID = 6117383628290016466L;
	// id
	private String productId;
	// 名称
	private String productName;
	// 编号
	private String productCode;
	// 产品类型
	private String productTypeCode;
	// 产品状态（使用、非使用）
	private String productStatus;
	// 产品期数
	private Integer productPeriods;
	// 旧年化收益率
	private BigDecimal oldYearprofit;
	// 新年化收益率
	private BigDecimal newYearprofit;
	// 封闭期
	private Integer productClosedate;
	// 是否回息 1是 2否
	private String productIsBack;
	// 版本协议
	private String productTreaty;
	// 回款金额公式
	private String productFormula;
	// 起投金额
	private BigDecimal startInvestAmount;
	// 产品折扣率
	private BigDecimal productDiscountrate;

	public BigDecimal getNewYearprofit() {
		return newYearprofit;
	}

	public void setNewYearprofit(BigDecimal newYearprofit) {
		this.newYearprofit = newYearprofit;
	}
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName == null ? null : productName.trim();
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode == null ? null : productCode.trim();
	}

	public String getProductTypeCode() {
		return productTypeCode;
	}

	public void setProductTypeCode(String productTypeCode) {
		this.productTypeCode = productTypeCode == null ? null : productTypeCode
				.trim();
	}

	public String getProductStatus() {
		return productStatus;
	}

	public void setProductStatus(String productStatus) {
		this.productStatus = productStatus == null ? null : productStatus
				.trim();
	}

	public Integer getProductPeriods() {
		return productPeriods;
	}

	public void setProductPeriods(Integer productPeriods) {
		this.productPeriods = productPeriods;
	}

	public BigDecimal getOldYearprofit() {
		return oldYearprofit;
	}

	public void setOldYearprofit(BigDecimal oldYearprofit) {
		this.oldYearprofit = oldYearprofit;
	}

	public Integer getProductClosedate() {
		return productClosedate;
	}

	public void setProductClosedate(Integer productClosedate) {
		this.productClosedate = productClosedate;
	}

	public String getProductIsBack() {
		return productIsBack;
	}

	public void setProductIsBack(String productIsBack) {
		this.productIsBack = productIsBack == null ? null : productIsBack
				.trim();
	}

	public String getProductTreaty() {
		return productTreaty;
	}

	public void setProductTreaty(String productTreaty) {
		this.productTreaty = productTreaty == null ? null : productTreaty
				.trim();
	}

	public String getProductFormula() {
		return productFormula;
	}

	public void setProductFormula(String productFormula) {
		this.productFormula = productFormula == null ? null : productFormula
				.trim();
	}

	public BigDecimal getStartInvestAmount() {
		return startInvestAmount;
	}

	public void setStartInvestAmount(BigDecimal startInvestAmount) {
		this.startInvestAmount = startInvestAmount;
	}

	public BigDecimal getProductDiscountrate() {
		return productDiscountrate;
	}

	public void setProductDiscountrate(BigDecimal productDiscountrate) {
		this.productDiscountrate = productDiscountrate;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}
}