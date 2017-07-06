package com.creditharmony.fortune.template.entity;

import java.io.Serializable;

import com.creditharmony.core.excel.annotation.ExcelField;
import com.creditharmony.core.fortune.type.PayMent;
import com.creditharmony.core.fortune.type.Sex;

/**
 * 已划扣回访列表导出
 * 
 * @Class Name DeductSuccessPayExportModel
 * @author 韩龙
 * @Create In 2015年12月17日
 */
public class DeductSuccessPayExportModel implements Serializable {

	/**
	 * long serialVersionUID
	 */
	private static final long serialVersionUID = 8265211025163073774L;

	// 客户姓名
	@ExcelField(title = "客户姓名" , align = 2)
	private String customerName;
	// 性别
	@ExcelField(title = "性别" , align = 2)
	private String dictCustomerSex;
	// 身份证号码
	@ExcelField(title = "身份证号码" , align = 2)
	private String customerCertNum;
	// 电子邮箱
	@ExcelField(title = "电子邮箱" , align = 2)
	private String customerEmail;
	// 电话
	@ExcelField(title = "电话" , align = 2)
	private String customerTel;
	// 出借方式
	@ExcelField(title = "出借方式" , align = 2)
	private String productName;
	// 出借金额
	@ExcelField(title = "出借金额" , align = 2)
	private String applyLendMoney;
	// 划扣日期
	@ExcelField(title = "划扣日期" , align = 2)
	private String applyDeductDay;
	// 出借日期
	@ExcelField(title = "出借日期" , align = 2)
	private String applyLendDay;
	// 产品到期日期
	@ExcelField(title = "产品到期日期" , align = 2)
	private String applyExpireDay;
	// 理财经理
	@ExcelField(title = "理财经理" , align = 2)
	private String managerName;
	// 团队经理
	@ExcelField(title = "团队经理" , align = 2)
	private String teamManagerName;
	// 门店
	@ExcelField(title = "门店" , align = 2)
	private String orgName;
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getDictCustomerSex() {
		return Sex.getSex(dictCustomerSex);
	}
	public void setDictCustomerSex(String dictCustomerSex) {
		this.dictCustomerSex = dictCustomerSex;
	}
	public String getCustomerCertNum() {
		return customerCertNum;
	}
	public void setCustomerCertNum(String customerCertNum) {
		this.customerCertNum = customerCertNum;
	}
	public String getCustomerEmail() {
		return customerEmail;
	}
	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}
	public String getCustomerTel() {
		return customerTel;
	}
	public void setCustomerTel(String customerTel) {
		this.customerTel = customerTel;
	}
	public String getProductName() {
		return PayMent.getPayMent(productName);
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getApplyLendMoney() {
		return applyLendMoney;
	}
	public void setApplyLendMoney(String applyLendMoney) {
		this.applyLendMoney = applyLendMoney;
	}
	public String getApplyDeductDay() {
		return applyDeductDay;
	}
	public void setApplyDeductDay(String applyDeductDay) {
		this.applyDeductDay = applyDeductDay;
	}
	public String getApplyLendDay() {
		return applyLendDay;
	}
	public void setApplyLendDay(String applyLendDay) {
		this.applyLendDay = applyLendDay;
	}
	public String getApplyExpireDay() {
		return applyExpireDay;
	}
	public void setApplyExpireDay(String applyExpireDay) {
		this.applyExpireDay = applyExpireDay;
	}
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	public String getTeamManagerName() {
		return teamManagerName;
	}
	public void setTeamManagerName(String teamManagerName) {
		this.teamManagerName = teamManagerName;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

}
