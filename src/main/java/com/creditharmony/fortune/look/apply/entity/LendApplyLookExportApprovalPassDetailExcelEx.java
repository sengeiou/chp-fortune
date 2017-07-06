package com.creditharmony.fortune.look.apply.entity;

import java.util.Date;

import com.creditharmony.fortune.look.apply.util.ExcelField4BD;

/**
 *  出借审批查看列表 导出审批通过的明细
 * @Class Name LendApplyLookExportApprovalPassDetailExcelEx
 * @author 肖长伟
 * @Create In 2016年3月24日
 */
public class LendApplyLookExportApprovalPassDetailExcelEx {
		//营业部
		@ExcelField4BD(title="营业部")
		private String storeName;
		//客户姓名
		@ExcelField4BD(title="客户姓名")
		private String custName;
		//出借编号
		@ExcelField4BD(title="出借编号")
		private String lendCode;
		//首次出借日期 
		@ExcelField4BD(title="计划出借日期 ")
		private Date applyLendDay;
		//计划划扣日期
		@ExcelField4BD(title="计划划扣日期")
		private Date huakou;
		//计划出借金额
		@ExcelField4BD(title="计划出借金额")
		private String firstAmount;
		//出借方式
		@ExcelField4BD(title="出借方式")
		private String productName;
		//付款方式
		@ExcelField4BD(title="付款方式")
		private String payType; //apply_pay
		//付款银行
		@ExcelField4BD(title="付款银行")
		private String openBank;
		//出借状态
		@ExcelField4BD(title="申请状态")
		private String lendStatus;  //审批完成。。。。
		//审核通过人员
		@ExcelField4BD(title="审核通过人员")
		private String checkUserName;
		
		
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
		public String getLendCode() {
			return lendCode;
		}
		public void setLendCode(String lendCode) {
			this.lendCode = lendCode;
		}
		public Date getApplyLendDay() {
			return applyLendDay;
		}
		public void setApplyLendDay(Date applyLendDay) {
			this.applyLendDay = applyLendDay;
		}
		public Date getHuakou() {
			return huakou;
		}
		public void setHuakou(Date huakou) {
			this.huakou = huakou;
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
		public String getPayType() {
			return payType;
		}
		public void setPayType(String payType) {
			this.payType = payType;
		}
		public String getOpenBank() {
			return openBank;
		}
		public void setOpenBank(String openBank) {
			this.openBank = openBank;
		}
		
		public String getLendStatus() {
			return lendStatus;
		}
		public void setLendStatus(String lendStatus) {
			this.lendStatus = lendStatus;
		}
		public String getCheckUserName() {
			return checkUserName;
		}
		public void setCheckUserName(String checkUserName) {
			this.checkUserName = checkUserName;
		}
		
		
}
