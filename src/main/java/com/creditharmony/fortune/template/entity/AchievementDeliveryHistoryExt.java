/**
 * @Probject Name: chp-fortune
 * @Path: com.creditharmony.fortune.template.entityAchievementDeliveryHistoryExt.java
 * @Create By 胡体勇
 * @Create In 2016年1月8日 下午2:52:55
 */
package com.creditharmony.fortune.template.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import com.creditharmony.core.excel.annotation.ExcelField;

/**
 * @Class Name AchievementDeliveryHistoryExt
 * @author 胡体勇
 * @Create In 2016年1月8日
 */
public class AchievementDeliveryHistoryExt implements Serializable{

	/**
	 * long serialVersionUID 
	 */
	private static final long serialVersionUID = 1L;
	@ExcelField(title = "客户姓名" , align = 2)
	private String custName;// 客户姓名
	@ExcelField(title = "客户编号" , align = 2)
	private String custCode;// 客户编号
	@ExcelField(title = "出借编号" , align = 2)
	private String lendCode;// 出借编号
	@ExcelField(title = "出借产品" , align = 2)
	private String productName;// 出借产品
	@ExcelField(title = "出借金额" , align = 2)
	private BigDecimal applyLendMoney;// 出借金额
	@ExcelField(title = "出借时间" , align = 2)
	private Date applyLendDay;// 出借时间
	@ExcelField(title = "付款方式" , align = 2 ,dictType = "tz_pay_type")
	private String applyPay;// 付款方式
	@ExcelField(title = "到期日期" , align = 2)
	private Date applyExpireDay;// 到期日期
	@ExcelField(title = "出借状态" , align = 2 , dictType = "tz_lend_state")
	private String lendStatus;// 出借状态
	@ExcelField(title = " 完结状态" , align = 2 , dictType = "tz_finish_state")
	private String applyEndStatus;// 完结状态
	@ExcelField(title = "新理财经理工号" , align = 2)
	private String nfManagerCode;// 新理财经理工号
	@ExcelField(title = "新理财经理" , align = 2)
	private String nfManagerName;// 新理财经理
	@ExcelField(title = "新团队经理编号" , align = 2)
	private String nTeamManagerCode;// 新团队经理编号
	@ExcelField(title = "新团队经理" , align = 2)
	private String nTeamManagerName;// 新团队经理
	@ExcelField(title = "新营业部" , align = 2)
	private String nOrgName;// 新营业部
	@ExcelField(title = "理财经理工号" , align = 2)
	private String fManagerCode;// 理财经理工号
	@ExcelField(title = "理财经理" , align = 2)
	private String fManagerName;// 理财经理
	@ExcelField(title = "团队经理工号" , align = 2)
	private String teamManagerCode;// 团队经理工号
	@ExcelField(title = "团队经理" , align = 2)
	private String teamManagerName;// 团队经理
	@ExcelField(title = "营业部" , align = 2)
	private String orgName;// 营业部
	@ExcelField(title = "交割时间" , align = 2)
	private Timestamp delDate;// 交割时间
	@ExcelField(title = "操作人" , align = 2)
	private String modifyByName;// 操作人
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
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public BigDecimal getApplyLendMoney() {
		return applyLendMoney;
	}
	public void setApplyLendMoney(BigDecimal applyLendMoney) {
		this.applyLendMoney = applyLendMoney;
	}
	public Date getApplyLendDay() {
		return applyLendDay;
	}
	public void setApplyLendDay(Date applyLendDay) {
		this.applyLendDay = applyLendDay;
	}
	public String getApplyPay() {
		return applyPay;
	}
	public void setApplyPay(String applyPay) {
		this.applyPay = applyPay;
	}
	public Date getApplyExpireDay() {
		return applyExpireDay;
	}
	public void setApplyExpireDay(Date applyExpireDay) {
		this.applyExpireDay = applyExpireDay;
	}
	public String getLendStatus() {
		return lendStatus;
	}
	public void setLendStatus(String lendStatus) {
		this.lendStatus = lendStatus;
	}
	public String getApplyEndStatus() {
		return applyEndStatus;
	}
	public void setApplyEndStatus(String applyEndStatus) {
		this.applyEndStatus = applyEndStatus;
	}
	public String getNfManagerCode() {
		return nfManagerCode;
	}
	public void setNfManagerCode(String nfManagerCode) {
		this.nfManagerCode = nfManagerCode;
	}
	public String getNfManagerName() {
		return nfManagerName;
	}
	public void setNfManagerName(String nfManagerName) {
		this.nfManagerName = nfManagerName;
	}
	public String getNTeamManagerCode() {
		return nTeamManagerCode;
	}
	public void setNTeamManagerCode(String nTeamManagerCode) {
		this.nTeamManagerCode = nTeamManagerCode;
	}
	public String getNTeamManagerName() {
		return nTeamManagerName;
	}
	public void setNTeamManagerName(String nTeamManagerName) {
		this.nTeamManagerName = nTeamManagerName;
	}
	public String getNOrgName() {
		return nOrgName;
	}
	public void setNOrgName(String nOrgName) {
		this.nOrgName = nOrgName;
	}
	public String getFManagerCode() {
		return fManagerCode;
	}
	public void setFManagerCode(String fManagerCode) {
		this.fManagerCode = fManagerCode;
	}
	public String getFManagerName() {
		return fManagerName;
	}
	public void setFManagerName(String fManagerName) {
		this.fManagerName = fManagerName;
	}
	public String getTeamManagerCode() {
		return teamManagerCode;
	}
	public void setTeamManagerCode(String teamManagerCode) {
		this.teamManagerCode = teamManagerCode;
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
	public Timestamp getDelDate() {
		return delDate;
	}
	public void setDelDate(Timestamp delDate) {
		this.delDate = delDate;
	}
	public String getModifyByName() {
		return modifyByName;
	}
	public void setModifyByName(String modifyByName) {
		this.modifyByName = modifyByName;
	}
	
}
