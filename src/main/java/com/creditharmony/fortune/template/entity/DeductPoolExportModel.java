package com.creditharmony.fortune.template.entity;

import java.io.Serializable;
import java.util.Date;

import com.creditharmony.core.excel.annotation.ExcelField;
import com.creditharmony.core.fortune.type.DeductPlat;
import com.creditharmony.core.fortune.type.OpenBank;
import com.creditharmony.core.fortune.type.PayMent;

/**
 * 划扣审批导出excel模板类model
 * @Class Name DeductPoolExportModel
 * @author 韩龙
 * @Create In 2015年11月27日
 */
public class DeductPoolExportModel implements Serializable {

	private static final long serialVersionUID = 6389744963857576135L;
	// 财富中心
	@ExcelField(title = "财富中心")
	private String fortuneCenter;
	// 客户姓名
	@ExcelField(title = "客户姓名")
	private String custName;
	// 出借编号
	@ExcelField(title = "出借编号")
	private String applyCode;
	// 划扣日期
	@ExcelField(title = "划扣日期")
	private Date applyDeductDate;
	// 首次出借日期
	@ExcelField(title = "首次出借日期")
	private Date applyLendDate;
	// 出借金额
	@ExcelField(title = "出借金额")
	private String applyLendMoney;
	// 划扣金额
	@ExcelField(title = "划扣金额")
	private String applyDeductMoney;
	// 出借产品
	@ExcelField(title = "出借产品")
	private String productCode;
	// 理财经理
	@ExcelField(title = "理财经理")
	private String managerName;
	// 门店
	@ExcelField(title = "门店")
	private String checkNode;
	// 划扣行别
	@ExcelField(title = "划扣行别")
	private String accountBank;
	// 付款方式
	@ExcelField(title = "付款方式")
	private String applyPay;
	// 反馈结果
	@ExcelField(title = "反馈结果")
	private String result;
	// 划扣平台
	@ExcelField(title = "划扣平台")
	private String dictApplyDeductType;
	public String getFortuneCenter() {
		return fortuneCenter;
	}
	public void setFortuneCenter(String fortuneCenter) {
		this.fortuneCenter = fortuneCenter;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getApplyCode() {
		return applyCode;
	}
	public void setApplyCode(String applyCode) {
		this.applyCode = applyCode;
	}
	public Date getApplyDeductDate() {
		return applyDeductDate;
	}
	public void setApplyDeductDate(Date applyDeductDate) {
		this.applyDeductDate = applyDeductDate;
	}
	public Date getApplyLendDate() {
		return applyLendDate;
	}
	public void setApplyLendDate(Date applyLendDate) {
		this.applyLendDate = applyLendDate;
	}
	public String getApplyLendMoney() {
		return applyLendMoney;
	}
	public void setApplyLendMoney(String applyLendMoney) {
		this.applyLendMoney = applyLendMoney;
	}
	public String getApplyDeductMoney() {
		return applyDeductMoney;
	}
	public void setApplyDeductMoney(String applyDeductMoney) {
		this.applyDeductMoney = applyDeductMoney;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	public String getCheckNode() {
		return checkNode;
	}
	public void setCheckNode(String checkNode) {
		this.checkNode = checkNode;
	}
	public String getAccountBank() {
		//OpenBank.initOpenBank();
		return OpenBank.getOpenBank(accountBank);
	}
	public void setAccountBank(String accountBank) {
		this.accountBank = accountBank;
	}
	public String getApplyPay() {
		//PayMent.initPayMent();
		return PayMent.getPayMent(applyPay);
	}
	public void setApplyPay(String applyPay) {
		this.applyPay = applyPay;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getDictApplyDeductType() {
		//DeductPlat.initDeductPlat();
		return DeductPlat.getDeductPlat(dictApplyDeductType);
	}
	public void setDictApplyDeductType(String dictApplyDeductType) {
		this.dictApplyDeductType = dictApplyDeductType;
	}

}