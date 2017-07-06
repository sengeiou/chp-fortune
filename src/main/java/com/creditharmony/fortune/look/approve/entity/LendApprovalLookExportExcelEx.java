package com.creditharmony.fortune.look.approve.entity;

import java.util.Date;

import com.creditharmony.fortune.look.apply.util.ExcelField4BD;

/**
 *  出借审批查看列表，导出excel bean
 * @Class Name LendApprovalLookExportExcelEx
 * @author 肖长伟
 * @Create In 2016年3月24日
 */
public class LendApprovalLookExportExcelEx {
		//营业部
		@ExcelField4BD(title="营业部 ")
		private String storeName;
		//客户姓名
		@ExcelField4BD(title="客户姓名")
		private String custName;
		//划扣日期
		@ExcelField4BD(title = "计划划扣时间")
		private Date huakou;
		//首次出借日期 
		@ExcelField4BD(title="计划出借时间 ")
		private Date applyLendDay;
		//初始出借金额
		@ExcelField4BD(title="计划出借金额", dataType=Double.class, format="0.00")
		private String firstAmount;
		//出借模式
		@ExcelField4BD(title="出借方式")
		private String productName;
		//理财经理
		@ExcelField4BD(title="理财经理")
		private String managerCode;
		//团队经理
		@ExcelField4BD(title="团队经理")
		private String teamMangerCode;
		//综合内勤
		@ExcelField4BD(title="综合内勤")
		private String zongHeNeiQin;
		//审核人
		@ExcelField4BD(title="审核人")
		private String checkUserName;
		//审核不通过次数
		@ExcelField4BD(title="审核不通过次数")
		private String notPassTimes;
		//审批通过时间
		@ExcelField4BD(title="审批通过时间")
		private Date passTime;
		//审批意见
		@ExcelField4BD(title="审批意见")
		private String checkMark;
		//错误类型
		@ExcelField4BD(title="错误类型")
		private String errorType;
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
		public String getManagerCode() {
			return managerCode;
		}
		public void setManagerCode(String managerCode) {
			this.managerCode = managerCode;
		}
		public String getTeamMangerCode() {
			return teamMangerCode;
		}
		public void setTeamMangerCode(String teamMangerCode) {
			this.teamMangerCode = teamMangerCode;
		}
		public String getZongHeNeiQin() {
			return zongHeNeiQin;
		}
		public void setZongHeNeiQin(String zongHeNeiQin) {
			this.zongHeNeiQin = zongHeNeiQin;
		}
		public String getCheckUserName() {
			return checkUserName;
		}
		public void setCheckUserName(String checkUserName) {
			this.checkUserName = checkUserName;
		}
		public String getNotPassTimes() {
			return notPassTimes;
		}
		public void setNotPassTimes(String notPassTimes) {
			this.notPassTimes = notPassTimes;
		}
		public Date getPassTime() {
			return passTime;
		}
		public void setPassTime(Date passTime) {
			this.passTime = passTime;
		}
		public String getCheckMark() {
			return checkMark;
		}
		public void setCheckMark(String checkMark) {
			this.checkMark = checkMark;
		}
		public String getErrorType() {
			return errorType;
		}
		public void setErrorType(String errorType) {
			this.errorType = errorType;
		}
		
		
		
}
