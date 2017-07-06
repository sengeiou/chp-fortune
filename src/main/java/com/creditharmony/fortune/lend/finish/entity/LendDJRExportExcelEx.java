package com.creditharmony.fortune.lend.finish.entity;

import java.util.Date;

import com.creditharmony.fortune.look.apply.util.ExcelField4BD;

/**
 *  出借审批查看列表，导出excel bean
 * @Class Name LendApprovalLookExportExcelEx
 * @author 肖长伟
 * @Create In 2016年3月24日
 */
public class LendDJRExportExcelEx {
		//审核通过日期
//		@ExcelField4BD(title = "审核通过日期")
//		private Date passDate;
		//客户姓名
		@ExcelField4BD(title="客户姓名")
		private String custName;
		//客户编号
		@ExcelField4BD(title="客户编号")
		private String custCode;
		//出借编号
		@ExcelField4BD(title="出借编号")
		private String applyCode;
		//计划出借日期
		@ExcelField4BD(title="计划出借日期")
		private Date lendDate;
		//计划出借金额
		@ExcelField4BD(title="计划出借金额", dataType=Double.class, format="0.00")
		private String lendMoney;
		//出借模式
		@ExcelField4BD(title="出借方式")
		private String productName;
		//出借模式
		@ExcelField4BD(title="出借方式")
		private String productCode;
		//到期日期
		@ExcelField4BD(title="到期日期")
		private Date expireDate;
		//付款方式
		@ExcelField4BD(title="付款方式")
		private String applyPay;
		//理财经理
		@ExcelField4BD(title="理财经理")
		private String managerName;
		//营业部
		@ExcelField4BD(title="营业部")
		private String storeName;
		//财富中心
		@ExcelField4BD(title="财富中心")
		private String caifu;
		//渠道标识
		@ExcelField4BD(title="渠道标识")
		private String channelMarking;
		//实际回款金额
		@ExcelField4BD(title="实际回款金额", dataType=Double.class, format="0.00")
		private String actualback_money;
				
			
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
		public String getApplyCode() {
			return applyCode;
		}
		public void setApplyCode(String applyCode) {
			this.applyCode = applyCode;
		}
		public Date getLendDate() {
			return lendDate;
		}
		public void setLendDate(Date lendDate) {
			this.lendDate = lendDate;
		}
		public String getLendMoney() {
			return lendMoney;
		}
		public void setLendMoney(String lendMoney) {
			this.lendMoney = lendMoney;
		}
		public String getProductName() {
			return productName;
		}
		public void setProductName(String productName) {
			this.productName = productName;
		}
		public Date getExpireDate() {
			return expireDate;
		}
		public void setExpireDate(Date expireDate) {
			this.expireDate = expireDate;
		}
		public String getApplyPay() {
			return applyPay;
		}
		public void setApplyPay(String applyPay) {
			this.applyPay = applyPay;
		}
		public String getManagerName() {
			return managerName;
		}
		public void setManagerName(String managerName) {
			this.managerName = managerName;
		}
		public String getStoreName() {
			return storeName;
		}
		public void setStoreName(String storeName) {
			this.storeName = storeName;
		}
		public String getCaifu() {
			return caifu;
		}
		public void setCaifu(String caifu) {
			this.caifu = caifu;
		}
		public String getChannelMarking() {
			return channelMarking;
		}
		public void setChannelMarking(String channelMarking) {
			this.channelMarking = channelMarking;
		}
		public String getActualback_money() {
			return actualback_money;
		}
		public void setActualback_money(String actualback_money) {
			this.actualback_money = actualback_money;
		}
		public String getProductCode() {
			return productCode;
		}
		public void setProductCode(String productCode) {
			this.productCode = productCode;
		}
		
}
