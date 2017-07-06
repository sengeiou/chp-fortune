package com.creditharmony.fortune.template.entity.backmoney;

import java.io.Serializable;
import java.util.Date;

import com.creditharmony.common.util.ObjectHelper;
import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.deduct.type.CardOrBookType;
import com.creditharmony.core.excel.annotation.ExcelField;
import com.creditharmony.core.fortune.type.OpenBank;

/**
 * 执行回款，网银导出/导入Model
 * 
 * @Class Name ExecuteWyModel
 * @author 陈广鹏
 * @Create In 2015年12月26日
 */
public class ExecuteWyModel implements Serializable {

	/**
	 * long serialVersionUID
	 */
	private static final long serialVersionUID = -5268458810445906251L;
	/**
	 * 出借编号
	 */
	@ExcelField(title = "出借编号")
	private String lendCode;
	/**
	 * 账号
	 */
	@ExcelField(title = "收款账户")
	private String accountNo;
	/**
	 * 回款账户名
	 */
	@ExcelField(title = "收款户名")
	private String accName;
	/**
	 * 实际回款金额
	 */
	@ExcelField(title = "实际回款金额")
	private Double backActualbackMoney;
	/**
	 * 回款成功金额
	 */
	private Double suceessMoney;
	/**
	 * 城市
	 */
//	private String city;
	/**
	 * 产品类型
	 */
//	private String productName;
	/**
	 * 回款类型
	 */
//	private String backMoneyType;
	/**
	 * 备注 = 城市+出借方式+回款类型
	 */
	@ExcelField(title = "备注")
	private String remark;
	/**
	 * 回款开户行
	 */
	@ExcelField(title = "收款开户行")
	private String accountBank;
	/**
	 * 回款支行名称
	 */
	@ExcelField(title = "收款银行支行")
	private String accountBranch;
	/**
	 * 卡或折
	 */
	@ExcelField(title = "卡或折")
	private String accountCardOrBooklet;
	/**
	 * 账户省份
	 */
	@ExcelField(title = "收款省/直辖市")
	private String accountAddrprovince;
	/**
	 * 账户城市
	 */
	@ExcelField(title = "收款县")
	private String accountAddrcity;
	/**
	 * 放款账户
	 */
	@ExcelField(title = "放款账户")
	private String fkAccount;
	/**
	 * 开户行
	 */
	@ExcelField(title = "开户行")
	private String fkBank;
	/**
	 * 银行账号
	 */
	@ExcelField(title = "银行账号")
	private String fkAccountNo;
	/**
	 * 放款日期
	 */
	@ExcelField(title = "放款日期")
	private Date fkDate;

	public String getAccName() {
		return accName;
	}

	public void setAccName(String accName) {
		this.accName = accName;
	}

//	public String getCity() {
//		return city;
//	}
//
//	public void setCity(String city) {
//		this.city = city;
//	}

	public Double getBackActualbackMoney() {
		return backActualbackMoney;
	}

	public void setBackActualbackMoney(Double backActualbackMoney) {
		this.backActualbackMoney = backActualbackMoney;
	}

	public Double getSuceessMoney() {
		return suceessMoney;
	}

	public void setSuceessMoney(Double suceessMoney) {
		this.suceessMoney = suceessMoney;
	}

	public String getAccountCardOrBooklet() {
		//CardOrPassbook.initCardOrPassbook();
		return CardOrBookType.parseByCode(accountCardOrBooklet).getName();
	}

	public void setAccountCardOrBooklet(String accountCardOrBooklet) {
		this.accountCardOrBooklet = accountCardOrBooklet;
	}

	public String getFkAccount() {
		return fkAccount;
	}

	public void setFkAccount(String fkAccount) {
		this.fkAccount = fkAccount;
	}

	public String getFkBank() {
		return fkBank;
	}

	public void setFkBank(String fkBank) {
		this.fkBank = fkBank;
	}

	public String getFkAccountNo() {
		return fkAccountNo;
	}

	public void setFkAccountNo(String fkAccountNo) {
		this.fkAccountNo = fkAccountNo;
	}

	public Date getFkDate() {
		return fkDate;
	}

	public void setFkDate(Date fkDate) {
		this.fkDate = fkDate;
	}

	public String getRemark() {
		if (null == remark) {
			remark = lendCode + "回款";
			remark = remark.replace("-", "");
		}
		
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getLendCode() {
		return lendCode;
	}

	public void setLendCode(String lendCode) {
		this.lendCode = lendCode;
	}

//	public String getProductName() {
//		return productName;
//	}
//
//	public void setProductName(String productName) {
//		this.productName = productName;
//	}

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

//	public String getBackMoneyType() {
//		BackType.initBackType();
//		return BackType.backTypeMap.get(backMoneyType);
//	}
//
//	public void setBackMoneyType(String backMoneyType) {
//		this.backMoneyType = backMoneyType;
//	}

}
