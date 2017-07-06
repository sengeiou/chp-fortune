package com.creditharmony.fortune.template.entity;

import java.io.Serializable;

import com.creditharmony.core.excel.annotation.ExcelField;
import com.creditharmony.core.fortune.type.DeductPlat;
import com.creditharmony.core.fortune.type.DeductState;
import com.creditharmony.core.fortune.type.OpenBank;

/**
 * 划扣失败列表导出
 * 
 * @Class Name DeductFailExportModel
 * @author 韩龙
 * @Create In 2015年12月17日
 */
public class DeductFailExportModel implements Serializable {

	/**
	 * long serialVersionUID
	 */
	private static final long serialVersionUID = 8265211025163073774L;
	// 序号
	@ExcelField(title = "序号", align = 2)
	private String no;
	// 客户姓名
	@ExcelField(title = "客户姓名", align = 2)
	private String customerName;
	// 出借金额
	@ExcelField(title = "出借金额", align = 2)
	private String applyLendMoney;
	// 出借产品
	@ExcelField(title = "出借产品", align = 2)
	private String productName;
	// 客户经理
	@ExcelField(title = "客户经理", align = 2)
	private String managerName;
	// 营业部
	@ExcelField(title = "营业部", align = 2)
	private String orgName;
	// 回盘结果
	@ExcelField(title = "回盘结果", align = 2)
	private String dictBackResult;
	// 失败原因
	@ExcelField(title = "失败原因", align = 2)
	private String dictBackFailResult;
	// 出借银行
	@ExcelField(title = "出借银行", align = 2)
	private String bankId;
	// 出借卡号
	@ExcelField(title = "出借卡号", align = 2)
	private String accountNo;
	// 划扣平台
	@ExcelField(title = "划扣平台", align = 2)
	private String dictDeductPlatformId;
	// 划扣日期
	@ExcelField(title = "划扣日期", align = 2)
	private String deductDate;
	// 出借编号
	@ExcelField(title = "出借编号", align = 2)
	private String lendCode;
	// 手机号码
	@ExcelField(title = "手机号码", align = 2)
	private String customerMobilephone;
	// 划扣成功金额
	@ExcelField(title = "划扣成功金额", align = 2)
	private String deductReallyMoney;
	// 划扣失败金额
	@ExcelField(title = "划扣失败金额", align = 2)
	private String deductFailMoney;

	public String getCustomerMobilephone() {
		return customerMobilephone;
	}

	public void setCustomerMobilephone(String customerMobilephone) {
		this.customerMobilephone = customerMobilephone;
	}

	public String getDeductReallyMoney() {
		return deductReallyMoney;
	}

	public void setDeductReallyMoney(String deductReallyMoney) {
		this.deductReallyMoney = deductReallyMoney;
	}

	public String getDeductFailMoney() {
		return deductFailMoney;
	}

	public void setDeductFailMoney(String deductFailMoney) {
		this.deductFailMoney = deductFailMoney;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getApplyLendMoney() {
		return applyLendMoney;
	}

	public void setApplyLendMoney(String applyLendMoney) {
		this.applyLendMoney = applyLendMoney;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getDictBackResult() {
		return DeductState.getDeductState(dictBackResult);
	}

	public void setDictBackResult(String dictBackResult) {
		this.dictBackResult = dictBackResult;
	}

	public String getDictBackFailResult() {
		return dictBackFailResult;
	}

	public void setDictBackFailResult(String dictBackFailResult) {
		this.dictBackFailResult = dictBackFailResult;
	}

	public String getBankId() {
		//OpenBank.initOpenBank();
		return OpenBank.getOpenBank(bankId);
	}

	public void setBankId(String bankId) {
		this.bankId = bankId;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getDictDeductPlatformId() {
		//DeductPlat.initDeductPlat();
		return DeductPlat.getDeductPlat(dictDeductPlatformId);
	}

	public void setDictDeductPlatformId(String dictDeductPlatformId) {
		this.dictDeductPlatformId = dictDeductPlatformId;
	}

	public String getDeductDate() {
		return deductDate;
	}

	public void setDeductDate(String deductDate) {
		this.deductDate = deductDate;
	}

	public String getLendCode() {
		return lendCode;
	}

	public void setLendCode(String lendCode) {
		this.lendCode = lendCode;
	}
}
