package com.creditharmony.fortune.template.entity.redemption;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.excel.annotation.ExcelField;
import com.creditharmony.core.fortune.type.ContractVesion;
import com.creditharmony.core.fortune.type.PayMent;
import com.creditharmony.core.fortune.type.RedeemBackDeadline;
import com.creditharmony.core.fortune.type.RedeemCostType;
import com.creditharmony.core.fortune.type.RedeemType;

/**
 * 赎回审批查看列表导出扩展类
 * 
 * @Class Name RedemptionApprovalExportModel
 * @author 陈广鹏
 * @Create In 2015年12月24日
 */
public class RedemptionApprovalExportModel implements Serializable {

	/**
	 * long serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 审核通过日期
	 */
	@ExcelField(title = "审核通过日期")
	private Date checkTime;
	/**
	 * 客户姓名
	 */
	@ExcelField(title = "客户姓名")
	private String customerName;
	/**
	 * 客户编号
	 */
	@ExcelField(title = "客户编号")
	private String customerCode;
	/**
	 * 出借编号
	 */
	@ExcelField(title = "出借编号")
	private String lendCode;
	/**
	 * 计划出借日期
	 */
	@ExcelField(title = "计划出借日期")
	private Date applyLendDay;
	/**
	 * 计划出借金额
	 */
	@ExcelField(title = "计划出借金额")
	private BigDecimal applyLendMoney;
	/**
	 * 赎回金额
	 */
	@ExcelField(title = "赎回金额")
	private BigDecimal redemptionMoney;
	/**
	 * 出借方式
	 */
	@ExcelField(title = "出借方式")
	private String productName;
	/**
	 * 付款方式
	 */
	@ExcelField(title = "付款方式")
	private String applyPay;
	/**
	 * 省份
	 */
	@ExcelField(title = "省份")
	private String addrProvince;
	/**
	 * 城市
	 */
	@ExcelField(title = "城市")
	private String addrCity;
	/**
	 * 营业部
	 */
	@ExcelField(title = "营业部")
	private String orgName;
	/**
	 * 团队经理
	 */
	@ExcelField(title = "团队经理")
	private String teamManager;
	/**
	 * 理财经理
	 */
	@ExcelField(title = "理财经理")
	private String userName;
	/**
	 * 账单日
	 */
	@ExcelField(title = "账单日")
	private String applyBillday;
	/**
	 * 到期日期
	 */
	@ExcelField(title = "到期日期")
	private Date linitDay;
	/**
	 * 回款日期
	 */
	@ExcelField(title = "回款日期")
	private Date backMoneyDay;
	/**
	 * 协议版本
	 */
	@ExcelField(title = "协议版本")
	private String applyAgreementEdition;
	/**
	 * 回款期限
	 */
	private String redemptionReceType;
	/**
	 * 扣费标准
	 */
	@ExcelField(title = "扣费标准")
	private BigDecimal redeemCost;
	/**
	 * 回款期限
	 */
	@ExcelField(title = "回款期限")
	private String redeemBackDeadline;
	/**
	 * 回款金额
	 */
	@ExcelField(title = "回款金额")
	private BigDecimal redemptionBmoney;
	/**
	 * 扣费金额
	 */
	@ExcelField(title = "扣费金额")
	private BigDecimal redemptionDemoney;
	/**
	 * 赎回类型
	 */
	@ExcelField(title = "赎回类型")
	private String redemptionType;

	public Date getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}

	public Date getBackMoneyDay() {
		return backMoneyDay;
	}

	public void setBackMoneyDay(Date backMoneyDay) {
		this.backMoneyDay = backMoneyDay;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
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

	public String getApplyLendMoney() {
		return StringUtils.doNumFormat(applyLendMoney, "0,000.00");
	}

	public void setApplyLendMoney(BigDecimal applyLendMoney) {
		this.applyLendMoney = applyLendMoney;
	}

	public String getRedemptionMoney() {
		return StringUtils.doNumFormat(redemptionMoney, "0,000.00");
	}

	public void setRedemptionMoney(BigDecimal redemptionMoney) {
		this.redemptionMoney = redemptionMoney;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getApplyPay() {
		//PayMent.initPayMent();
		
		return PayMent.getPayMent(applyPay);
	}

	public void setApplyPay(String applyPay) {
		this.applyPay = applyPay;
	}

	public String getAddrProvince() {
		return addrProvince;
	}

	public void setAddrProvince(String addrProvince) {
		this.addrProvince = addrProvince;
	}

	public String getAddrCity() {
		return addrCity;
	}

	public void setAddrCity(String addrCity) {
		this.addrCity = addrCity;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getTeamManager() {
		return teamManager;
	}

	public void setTeamManager(String teamManager) {
		this.teamManager = teamManager;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getApplyBillday() {
		return applyBillday;
	}

	public void setApplyBillday(String applyBillday) {
		this.applyBillday = applyBillday;
	}

	public Date getLinitDay() {
		return linitDay;
	}

	public void setLinitDay(Date linitDay) {
		this.linitDay = linitDay;
	}

	public String getApplyAgreementEdition() {
		
		return ContractVesion.contractVesionMap.get(applyAgreementEdition);
		// return applyAgreementEdition;
	}

	public void setApplyAgreementEdition(String applyAgreementEdition) {
		this.applyAgreementEdition = applyAgreementEdition;
	}

	public String getRedemptionReceType() {
		return redemptionReceType;
	}

	public void setRedemptionReceType(String redemptionReceType) {
		this.redemptionReceType = redemptionReceType;
	}

	public String getRedeemCost() {
		redeemCost = null;
		
		if (null != redemptionReceType) {
			redeemCost = RedeemCostType.parseByCode(redemptionReceType).getValue();
		}
		return StringUtils.doNumFormat(redeemCost,"0.00%");
	}

	public void setRedeemCost(BigDecimal redeemCost) {
		this.redeemCost = redeemCost;
	}

	public String getRedeemBackDeadline() {
		redeemBackDeadline = null;
		if (null != redemptionReceType) {
			redeemBackDeadline = RedeemBackDeadline.parseByCode(redemptionReceType).getValue();
		}
		return redeemBackDeadline;
	}

	public void setRedeemBackDeadline(String redeemBackDeadline) {
		this.redeemBackDeadline = redeemBackDeadline;
	}

	public String getRedemptionType() {
		if (null == redemptionType) {
			return null;
		}
		//RedeemType.initRedeemType();
		return RedeemType.getRedeemType(redemptionType);
	}

	public String getRedemptionBmoney() {
		return StringUtils.doNumFormat(redemptionBmoney, "0,000.00");
	}

	public void setRedemptionBmoney(BigDecimal redemptionBmoney) {
		this.redemptionBmoney = redemptionBmoney;
	}

	public String getRedemptionDemoney() {
		return StringUtils.doNumFormat(redemptionDemoney, "0,000.00");
	}

	public void setRedemptionDemoney(BigDecimal redemptionDemoney) {
		this.redemptionDemoney = redemptionDemoney;
	}

	public void setRedemptionType(String redemptionType) {
		this.redemptionType = redemptionType;
	}

}
