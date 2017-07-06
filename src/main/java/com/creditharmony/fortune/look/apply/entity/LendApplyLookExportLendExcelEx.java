package com.creditharmony.fortune.look.apply.entity;

import java.util.Date;

import com.creditharmony.fortune.look.apply.util.ExcelField4BD;


public class LendApplyLookExportLendExcelEx {
		//备注  不需要赋值
		@ExcelField4BD(title="备注")
		private String remark;  
		//财富中心
		@ExcelField4BD(title="财富中心")
		private String fortunCenter;
		//门店 
		@ExcelField4BD(title="门店 ")
		private String storeName;
		//客户姓名
		@ExcelField4BD(title="客户姓名")
		private String custName;
		//客户编号
		@ExcelField4BD(title="客户编号")
		private String custCode;
		//出借编号
		@ExcelField4BD(title="出借编号")
		private String lendCode;
		//审核日期
		@ExcelField4BD(title="审核日期")
		private Date shenPi;
		//划扣日期
		@ExcelField4BD(title="划扣日期")
		private Date huakou;
		//首次出借日期 
		@ExcelField4BD(title="首次出借日期 ")
		private Date applyLendDay;
		//初始出借金额
		@ExcelField4BD(title="初始出借金额", format="0.00", dataType = Double.class)
		private String firstAmount;
		//出借模式
		@ExcelField4BD(title="出借模式")
		private String productName;
		//产品到期日
		@ExcelField4BD(title="产品到期日")
		private Date applyExpireDay;
		//付款方式
		@ExcelField4BD(title="付款方式")
		private String payType; //apply_pay
		//协议版本 
		@ExcelField4BD(title="协议版本 ")
		private String applyAgreementEdition;
		//账单日
		@ExcelField4BD(title="账单日")
		private String applyBillday;
		//第一个账单日
		@ExcelField4BD(title="第一个账单日")
		private Date firstBillDate;
		//划扣行别
		@ExcelField4BD(title="划扣行别")
		private String openBank;
		//划扣开户行
		@ExcelField4BD(title="划扣开户行")
		private String branch;
		//划扣账号
		@ExcelField4BD(title="划扣账号")
		private String cardNo;
		//回款行别
		@ExcelField4BD(title="回款行别")
		private String backOpenBank;
		//回款开户行 
		@ExcelField4BD(title="回款开户行 ")
		private String backBranch;
		//回款账号
		@ExcelField4BD(title="回款账号")
		private String backCardNo;
		//账号类型
		@ExcelField4BD(title="账号类型")
		private String accountCardOrBooklet;
		//所在城市
		@ExcelField4BD(title="所在城市")
		private String accountAddrcity;
		//回款日期
		@ExcelField4BD(title="回款日期")
		private Date backDate;
		//回款金额
		@ExcelField4BD(title="回款金额", format="0.00", dataType = Double.class)
		private String backMoney;
		//状态
		@ExcelField4BD(title="状态")
		private String status;  //出借中。。。。
		//完结状态
		@ExcelField4BD(title="完结状态")
		private String dictApplyEndState;
		//合同编号 
		@ExcelField4BD(title="合同编号")
		private String applyContractNo;
		//信和宝类型 
		@ExcelField4BD(title="信和宝类型 ")
		private String xinhebaoType;
		//年化收益率
		@ExcelField4BD(title="年化收益率", dataType = Double.class ,format="0.00%")
		private String yearRate;
		//理财经理
		@ExcelField4BD(title="理财经理")
		private String managerCode;
		//账单收取方式
		@ExcelField4BD(title="账单收取方式")
		private String loanBillRecv;
		
		//划扣平台
		private String dictApplyDeductType;
		//出借金额
		private String applyLendMoney;
		// 划扣金额
		private String applyDeductMoney;
		
		public String getDictApplyDeductType() {
			return dictApplyDeductType;
		}
		public void setDictApplyDeductType(String dictApplyDeductType) {
			this.dictApplyDeductType = dictApplyDeductType;
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
		public String getRemark() {
			return remark;
		}
		public void setRemark(String remark) {
			this.remark = remark;
		}
		public String getFortunCenter() {
			return fortunCenter;
		}
		public void setFortunCenter(String fortunCenter) {
			this.fortunCenter = fortunCenter;
		}
		public String getStoreName() {
			return storeName;
		}
		public void setStoreName(String storeName) {
			this.storeName = storeName;
		}
		public String getCustName() {
			return custName;
		}
		public void setCustName(String custName) {
			this.custName = custName;
		}
		public String getCustCode() {
			return custCode;
		}
		public void setCustCode(String custCode) {
			this.custCode = custCode;
		}
		public String getLendCode() {
			return lendCode;
		}
		public void setLendCode(String lendCode) {
			this.lendCode = lendCode;
		}
		public Date getShenPi() {
			return shenPi;
		}
		public void setShenPi(Date shenPi) {
			this.shenPi = shenPi;
		}
		public Date getHuakou() {
			return huakou;
		}
		public void setHuakou(Date huakou) {
			this.huakou = huakou;
		}
		
		public Date getApplyLendDay() {
			return applyLendDay;
		}
		public void setApplyLendDay(Date applyLendDay) {
			this.applyLendDay = applyLendDay;
		}
		public String getFirstAmount() {
			return firstAmount;
		}
		public void setFirstAmount(String firstAmount) {
			this.firstAmount = firstAmount;
		}
		public String getProductName() {
			return productName;
		}
		public void setProductName(String productName) {
			this.productName = productName;
		}
		public Date getApplyExpireDay() {
			return applyExpireDay;
		}
		public void setApplyExpireDay(Date applyExpireDay) {
			this.applyExpireDay = applyExpireDay;
		}
		public String getPayType() {
			return payType;
		}
		public void setPayType(String payType) {
			this.payType = payType;
		}
		public String getApplyAgreementEdition() {
			return applyAgreementEdition;
		}
		public void setApplyAgreementEdition(String applyAgreementEdition) {
			this.applyAgreementEdition = applyAgreementEdition;
		}
		public String getApplyBillday() {
			return applyBillday;
		}
		public void setApplyBillday(String applyBillday) {
			this.applyBillday = applyBillday;
		}
		public Date getFirstBillDate() {
			return firstBillDate;
		}
		public void setFirstBillDate(Date firstBillDate) {
			this.firstBillDate = firstBillDate;
		}
		public String getOpenBank() {
			return openBank;
		}
		public void setOpenBank(String openBank) {
			this.openBank = openBank;
		}
		public String getBranch() {
			return branch;
		}
		public void setBranch(String branch) {
			this.branch = branch;
		}
		public String getCardNo() {
			return cardNo;
		}
		public void setCardNo(String cardNo) {
			this.cardNo = cardNo;
		}
		public String getBackOpenBank() {
			return backOpenBank;
		}
		public void setBackOpenBank(String backOpenBank) {
			this.backOpenBank = backOpenBank;
		}
		public String getBackBranch() {
			return backBranch;
		}
		public void setBackBranch(String backBranch) {
			this.backBranch = backBranch;
		}
		public String getBackCardNo() {
			return backCardNo;
		}
		public void setBackCardNo(String backCardNo) {
			this.backCardNo = backCardNo;
		}
		public String getAccountCardOrBooklet() {
			return accountCardOrBooklet;
		}
		public void setAccountCardOrBooklet(String accountCardOrBooklet) {
			this.accountCardOrBooklet = accountCardOrBooklet;
		}
		public String getAccountAddrcity() {
			return accountAddrcity;
		}
		public void setAccountAddrcity(String accountAddrcity) {
			this.accountAddrcity = accountAddrcity;
		}
		public Date getBackDate() {
			return backDate;
		}
		public void setBackDate(Date backDate) {
			this.backDate = backDate;
		}
		
		public String getBackMoney() {
			return backMoney;
		}
		public void setBackMoney(String backMoney) {
			this.backMoney = backMoney;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public String getDictApplyEndState() {
			return dictApplyEndState;
		}
		public void setDictApplyEndState(String dictApplyEndState) {
			this.dictApplyEndState = dictApplyEndState;
		}
		public String getApplyContractNo() {
			return applyContractNo;
		}
		public void setApplyContractNo(String applyContractNo) {
			this.applyContractNo = applyContractNo;
		}
		public String getXinhebaoType() {
			return xinhebaoType;
		}
		public void setXinhebaoType(String xinhebaoType) {
			this.xinhebaoType = xinhebaoType;
		}
		public String getYearRate() {
			return yearRate;
		}
		public void setYearRate(String yearRate) {
			this.yearRate = yearRate;
		}
		public String getManagerCode() {
			return managerCode;
		}
		public void setManagerCode(String managerCode) {
			this.managerCode = managerCode;
		}
		public String getLoanBillRecv() {
			return loanBillRecv;
		}
		public void setLoanBillRecv(String loanBillRecv) {
			this.loanBillRecv = loanBillRecv;
		}
		
		
}
