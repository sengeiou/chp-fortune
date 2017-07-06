package com.creditharmony.fortune.template.entity.backmoney;

import java.io.Serializable;
import java.util.Date;

import com.creditharmony.common.util.ObjectHelper;
import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.deduct.type.CardOrBookType;
import com.creditharmony.core.excel.annotation.ExcelField;
import com.creditharmony.core.fortune.type.BackType;
import com.creditharmony.core.fortune.type.OpenBank;
import com.creditharmony.core.fortune.type.PayMent;
import com.creditharmony.fortune.redemption.constant.RedeemConstant;

/**
 * 回款申请确认导出Model
 * 
 * @Class Name ApplyConfirmExportModel
 * @author 陈广鹏
 * @Create In 2015年12月25日
 */
public class ApplyConfirmExportModel implements Serializable {

	/**
	 * long serialVersionUID
	 */
	private static final long serialVersionUID = 5528682932298393582L;
	/**
	 * 客户姓名
	 */
	@ExcelField(title = "姓名")
	private String customerName;
	/**
	 * 出借编号
	 */
	@ExcelField(title = "出借编号")
	private String lendCode;
	/**
	 * 计划出借日期
	 */
	@ExcelField(title = "首次出借日期")
	private String applyLendDay;
	/**
	 * 计划出借金额
	 */
	@ExcelField(title = "初始出借金额")
	private Double applyLendMoney;
	/**
	 * 产品类型
	 */
	@ExcelField(title = "产品类型")
	private String productName;
	/**
	 * 到期日期
	 */
	@ExcelField(title = "产品到期日")
	private Date finalLinitDate;
	/**
	 * 理财经理
	 */
	@ExcelField(title = "理财经理")
	private String manager;
	/**
	 * 营业部
	 */
	@ExcelField(title = "营业部")
	private String orgName;
	/**
	 * 应回款金额
	 */
	@ExcelField(title = "应回款金额")
	private Double backMoney;
	/**
	 * 实际回款金额
	 */
	@ExcelField(title = "实际回款金额")
	private Double backActualbackMoney;
	/**
	 * 补息后回款金额
	 */
	@ExcelField(title = "补息后回款金额")
	private Double supplementedMoney;
	/**
	 * 省份
	 */
	@ExcelField(title = "账户所在省")
	private String accountAddrprovince;
	/**
	 * 城市
	 */
	@ExcelField(title = "账户所在城市")
	private String accountAddrcity;
	/**
	 * 回款开户行
	 */
	@ExcelField(title = "回款开户行")
	private String accountBank;
	/**
	 * 回款支行名称
	 */
	@ExcelField(title = "回款支行名称")
	private String accountBranch;
	/**
	 * 账号
	 */
	@ExcelField(title = "账号")
	private String accountNo;
	/**
	 * 回款类型
	 */
	@ExcelField(title = "回款类型")
	private String backMoneyType;
	/**
	 * 付款方式
	 */
	@ExcelField(title = "付款方式")
	private String applyPay;
	/**
	 * 卡或折
	 */
	@ExcelField(title = "卡/折")
	private String accountCardOrBooklet;
	/**
	 * 客户回馈金额
	 */
	@ExcelField(title = "备注")
	private Double feedbackMoney;

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getLendCode() {
		return lendCode;
	}

	public void setLendCode(String lendCode) {
		this.lendCode = lendCode;
	}

	public String getApplyLendDay() {
		return applyLendDay;
	}

	public void setApplyLendDay(String applyLendDay) {
		this.applyLendDay = applyLendDay;
	}

	public String getApplyLendMoney() {
		return StringUtils.doNumFormat(applyLendMoney, RedeemConstant.MONEY_FORMAT);
	}

	public void setApplyLendMoney(Double applyLendMoney) {
		this.applyLendMoney = applyLendMoney;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Date getFinalLinitDate() {
		return finalLinitDate;
	}

	public void setFinalLinitDate(Date finalLinitDate) {
		this.finalLinitDate = finalLinitDate;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getAccountAddrprovince() {
		return accountAddrprovince;
	}

	public void setAccountAddrprovince(String accountAddrprovince) {
		this.accountAddrprovince = accountAddrprovince;
	}

	public String getAccountAddrcity() {
		return accountAddrcity;
	}

	public void setAccountAddrcity(String accountAddrcity) {
		this.accountAddrcity = accountAddrcity;
	}

	public String getAccountBank() {
		//OpenBank.initOpenBank();
		if (null != accountBank) {
			return OpenBank.openBankMap.get(accountBank);
		}
		return accountBank;
	}

	public void setAccountBank(String accountBank) {
		this.accountBank = accountBank;
	}

	public String getAccountBranch() {
		return accountBranch;
	}

	public void setAccountBranch(String accountBranch) {
		this.accountBranch = accountBranch;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getBackMoney() {
		return StringUtils.doNumFormat(backMoney, RedeemConstant.MONEY_FORMAT);

	}

	public void setBackMoney(Double backMoney) {
		this.backMoney = backMoney;
	}

	public String getBackActualbackMoney() {
		return StringUtils.doNumFormat(backActualbackMoney,
				RedeemConstant.MONEY_FORMAT);
	}

	public void setBackActualbackMoney(Double backActualbackMoney) {
		this.backActualbackMoney = backActualbackMoney;
	}

	public Double getSupplementedMoney() {
		return supplementedMoney;
	}

	public void setSupplementedMoney(Double supplementedMoney) {
		this.supplementedMoney = supplementedMoney;
	}

	public String getBackMoneyType() {
		//BackType.initBackType();
		return BackType.backTypeMap.get(backMoneyType);
	}

	public void setBackMoneyType(String backMoneyType) {
		this.backMoneyType = backMoneyType;
	}

	public String getApplyPay() {
		//PayMent.initPayMent();
		return PayMent.payMentMap.get(applyPay);
	}

	public void setApplyPay(String applyPay) {
		this.applyPay = applyPay;
	}

	public String getAccountCardOrBooklet() {
		//CardOrPassbook.initCardOrPassbook();
		return CardOrBookType.parseByCode(accountCardOrBooklet).getName();
	}

	public void setAccountCardOrBooklet(String accountCardOrBooklet) {
		this.accountCardOrBooklet = accountCardOrBooklet;
	}

	public String getFeedbackMoney() {
		String remark = "";
		if (!ObjectHelper.isEmpty(feedbackMoney)) {
			remark = StringUtils.doNumFormat(backActualbackMoney,"0,000.00")+"__客户享受馈赠礼品价值";
		}
		return remark;
	}

	public void setFeedbackMoney(Double feedbackMoney) {
		this.feedbackMoney = feedbackMoney;
	}

}
