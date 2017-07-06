package com.creditharmony.fortune.remind.view;

import java.io.Serializable;
import java.util.Date;

/**
 * 客户提醒view
 * @Class Name RemindView
 * @author 韩龙
 * @Create In 2015年12月1日
 */
public class RemindView implements Serializable{

	private static final long serialVersionUID = 3576637684989728990L;

	// 出借code
	private String applyCode;
	
	// 客户code
	private String custCode;

	// 客户姓名
	private String custName;

	// 申请日期
	private Date applyDate;
	
	// 计划出借日期
	private String applyDeductDate;

	// 计划出借金额
	private String applyLendMoney;

	// 出借方式
	private String applyPay;

	//付款所在城市
	private String applyPayAddress;

	// 营业部
	private String orgName;

	// 到期日期
	private String applyExpireDate;

	// 创建时间
	private String createTime;
	
	// 理财经理
	private String custIndustry;

	public String getApplyCode() {
		return applyCode;
	}

	public void setApplyCode(String applyCode) {
		this.applyCode = applyCode;
	}

	public String getCustCode() {
		return custCode;
	}

	public void setCustCode(String custCode) {
		this.custCode = custCode;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public Date getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}

	public String getApplyDeductDate() {
		return applyDeductDate;
	}

	public void setApplyDeductDate(String applyDeductDate) {
		this.applyDeductDate = applyDeductDate;
	}

	public String getApplyLendMoney() {
		return applyLendMoney;
	}

	public void setApplyLendMoney(String applyLendMoney) {
		this.applyLendMoney = applyLendMoney;
	}

	public String getApplyPay() {
		return applyPay;
	}

	public void setApplyPay(String applyPay) {
		this.applyPay = applyPay;
	}

	public String getApplyPayAddress() {
		return applyPayAddress;
	}

	public void setApplyPayAddress(String applyPayAddress) {
		this.applyPayAddress = applyPayAddress;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getApplyExpireDate() {
		return applyExpireDate;
	}

	public void setApplyExpireDate(String applyExpireDate) {
		this.applyExpireDate = applyExpireDate;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getCustIndustry() {
		return custIndustry;
	}

	public void setCustIndustry(String custIndustry) {
		this.custIndustry = custIndustry;
	}
}
