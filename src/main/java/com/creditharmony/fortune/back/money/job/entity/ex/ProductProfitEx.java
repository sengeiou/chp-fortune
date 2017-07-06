package com.creditharmony.fortune.back.money.job.entity.ex;

import java.math.BigDecimal;
import java.util.Date;

import com.creditharmony.core.persistence.DataEntity;

/**
 * 收益调整扩展类
 * @Class Name ProductProfit
 * @author 陈广鹏
 * @Create In 2016年12月22日
 */
public class ProductProfitEx extends DataEntity<ProductProfitEx> {

	/**
	 * long serialVersionUID 
	 */
	private static final long serialVersionUID = 5217224190837295144L;
	private String productCode;				// 产品编号
	private Date startDay;					// 启动日期
	private Date closeDay;					// 结束日期
	private BigDecimal productMoneyUpper;	// 金额上限
	private BigDecimal productMoneyLowe;	// 金额下限
	private BigDecimal productRate;			// 产品利率
	private BigDecimal productYearRate;		// 月息通、信和月增年利率
	private String protocolVersion;			// 协议版本
	private String status;					// 状态

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public Date getStartDay() {
		return startDay;
	}

	public void setStartDay(Date startDay) {
		this.startDay = startDay;
	}

	public Date getCloseDay() {
		return closeDay;
	}

	public void setCloseDay(Date closeDay) {
		this.closeDay = closeDay;
	}

	public BigDecimal getProductMoneyUpper() {
		return productMoneyUpper;
	}

	public void setProductMoneyUpper(BigDecimal productMoneyUpper) {
		this.productMoneyUpper = productMoneyUpper;
	}

	public BigDecimal getProductMoneyLowe() {
		return productMoneyLowe;
	}

	public void setProductMoneyLowe(BigDecimal productMoneyLowe) {
		this.productMoneyLowe = productMoneyLowe;
	}

	public BigDecimal getProductRate() {
		return productRate;
	}

	public void setProductRate(BigDecimal productRate) {
		this.productRate = productRate;
	}

	public BigDecimal getProductYearRate() {
		return productYearRate;
	}

	public void setProductYearRate(BigDecimal productYearRate) {
		this.productYearRate = productYearRate;
	}

	public String getProtocolVersion() {
		return protocolVersion;
	}

	public void setProtocolVersion(String protocolVersion) {
		this.protocolVersion = protocolVersion;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
