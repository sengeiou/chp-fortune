package com.creditharmony.fortune.look.apply.view;

import java.io.Serializable;
import java.util.Date;

import com.creditharmony.core.fortune.type.ForApplyStatus;

/**
 * 出借申请查看
 * @Class Name LendLookListView 
 * @author 李志伟
 * @Create In 2016年3月1日
 */
public class LendLookListView implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 出借编号
	private String lendCode;
	
	// 出借模式
	private String productName;
	
	// 出借金额
	private Double applyLendMoney;
	
	// 出借日期
	private Date applyLendDay;
	
	// 到期日期
	private Date applyExpireDay;
	
	// 付款方式
	private String applyPay;
	
	// 出借状态
	private String lendStatus;
	
	// 回款状态
	private String dictBackStatus;
	
	// 协议版本号
	private String applyAgreementEdition;
	
	// 账单日
	private Integer applyBillday;
	
	// 状态
	private String status;

	public String getStatus() {
		if(new Date().compareTo(this.applyExpireDay) >= 0 && (null ==status || status.equals(""))){
			return ForApplyStatus.YDQ.value;
		}
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLendCode() {
		return lendCode;
	}

	public void setLendCode(String lendCode) {
		this.lendCode = lendCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Double getApplyLendMoney() {
		return applyLendMoney;
	}

	public void setApplyLendMoney(Double applyLendMoney) {
		this.applyLendMoney = applyLendMoney;
	}

	public Date getApplyLendDay() {
		return applyLendDay;
	}

	public void setApplyLendDay(Date applyLendDay) {
		this.applyLendDay = applyLendDay;
	}

	public Date getApplyExpireDay() {
		return applyExpireDay;
	}

	public void setApplyExpireDay(Date applyExpireDay) {
		this.applyExpireDay = applyExpireDay;
	}

	public String getApplyPay() {
		return applyPay;
	}

	public void setApplyPay(String applyPay) {
		this.applyPay = applyPay;
	}

	public String getLendStatus() {
		return lendStatus;
	}

	public void setLendStatus(String lendStatus) {
		this.lendStatus = lendStatus;
	}

	public String getDictBackStatus() {
		return dictBackStatus;
	}

	public void setDictBackStatus(String dictBackStatus) {
		this.dictBackStatus = dictBackStatus;
	}

	public String getApplyAgreementEdition() {
		return applyAgreementEdition;
	}

	public void setApplyAgreementEdition(String applyAgreementEdition) {
		this.applyAgreementEdition = applyAgreementEdition;
	}

	public Integer getApplyBillday() {
		return applyBillday;
	}

	public void setApplyBillday(Integer applyBillday) {
		this.applyBillday = applyBillday;
	}
}