package com.creditharmony.fortune.customer.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.creditharmony.core.persistence.DataEntity;

/**
 * @Class Name Advisory
 * @author 孙凯文
 * @Create In 2015年12月2日
 */
public class Advisory extends DataEntity<Advisory> {

	private static final long serialVersionUID = 8518466429122173582L;
	// 客戶编号
	private String custCode;
	// 沟通日期
	private Date askDate;
	// 沟通开始时间
	private Date askBeginDate;
	// 沟通結束时间
	private Date askEndDate;
	// 沟通方式
	private String askType;
	// 意向模式
	private String askProduct;
	// 出资金额
	private BigDecimal askInputMoney;
	// 出资日期
	private Date askInputDate;
	// 內容描述
	private String askDes;
	// 下次沟通日期
	private Date askNextDate;
	// 下次沟通方式
	private String askNextType;
	// 理财经理ID
	private String managerId;

	public Date getAskDate() {
		return askDate;
	}

	public void setAskDate(Date askDate) {
		this.askDate = askDate;
	}

	public Date getAskBeginDate() {
		return askBeginDate;
	}

	public void setAskBeginDate(Date askBeginDate) {
		this.askBeginDate = askBeginDate;
	}

	public Date getAskEndDate() {
		return askEndDate;
	}

	public void setAskEndDate(Date askEndDate) {
		this.askEndDate = askEndDate;
	}

	public String getAskType() {
		return askType;
	}

	public void setAskType(String askType) {
		this.askType = askType == null ? null : askType.trim();
	}

	public String getAskProduct() {
		return askProduct;
	}

	public void setAskProduct(String askProduct) {
		this.askProduct = askProduct == null ? null : askProduct.trim();
	}

	public BigDecimal getAskInputMoney() {
		return askInputMoney;
	}

	public void setAskInputMoney(BigDecimal askInputMoney) {
		this.askInputMoney = askInputMoney;
	}

	public Date getAskInputDate() {
		return askInputDate;
	}

	public void setAskInputDate(Date askInputDate) {
		this.askInputDate = askInputDate;
	}

	public String getAskDes() {
		return askDes;
	}

	public void setAskDes(String askDes) {
		this.askDes = askDes == null ? null : askDes.trim();
	}

	public Date getAskNextDate() {
		return askNextDate;
	}

	public void setAskNextDate(Date askNextDate) {
		this.askNextDate = askNextDate;
	}

	public String getAskNextType() {
		return askNextType;
	}

	public void setAskNextType(String askNextType) {
		this.askNextType = askNextType == null ? null : askNextType.trim();
	}

	public String getCustCode() {
		return custCode;
	}

	public void setCustCode(String custCode) {
		this.custCode = custCode;
	}

	public String getManagerId() {
		return managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}
}