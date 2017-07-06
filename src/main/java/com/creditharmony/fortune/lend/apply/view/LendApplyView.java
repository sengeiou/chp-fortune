package com.creditharmony.fortune.lend.apply.view;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.creditharmony.bpm.frame.view.BaseBusinessView;
import com.creditharmony.core.users.entity.Org;
import com.creditharmony.core.users.entity.ProvinceCity;
import com.creditharmony.fortune.customer.entity.Customer;
import com.creditharmony.fortune.customer.entity.CustomerAccount;
import com.creditharmony.fortune.customer.entity.LoanApply;

/**
 * 出借申请视图类
 * 
 * @Class Name LendApplyView
 * @author 孙凯文
 * @Create In 2015年12月22日
 */
public class LendApplyView extends BaseBusinessView {
	// 出借申请
	private LoanApply lendApply;
	// 客户信息
	private Customer customer;
	// 银行列表
	private List<CustomerAccount> banks;
	// 付款银行ID
	private String paymentBankId;
	// 回款银行ID
	private String receiveBankId;
	// 机构信息
	private Org org;
	// 门店名称
	private String storeName;
	// 团队经理名称
	private String teamManagerName;
	// 理财经理名称
	private String financialManager;
	// 团队名称
	private String teamName;
	// 省份
	private String province;
	// 城市
	private String city;
	// 创建日期
	private Date createDate;
	// 出借金额
	private Double lendMoney;
	// 划扣金额
	private Double deductMoney;
	// 内转金额
	private Double transferMoney;
	// 省份列表
	private List<ProvinceCity> provinceList;
	// 城市列表
	private List<List<ProvinceCity>> cityList;
	// 区县列表
	private List<List<ProvinceCity>> districtList;
	// 附件id集合
	private List<String> addAttachmentId;
	// 金账户
	private List<CustomerAccount> goldAccount;
	// 金账户省列表
	private List<ProvinceCity> goldAccountProvinceList;
	// 金账户城市列表
	private List<List<ProvinceCity>> goldAccountCityList;
	// 协议版本
	private String protocals;
	// 是否自动开通金账户
	private String autoOpenAccount;
	// 划扣银行
	private String deductBank;
	//最小出借金额
	private String minApplyAmount;
	// 删除附件集合
	private List<String> deleteAttachmentId;
	// 可用产品
	private List<String> enableProduct;
	//审批人
	private String auditor;
	
	

	public String getDeductBank() {
		return deductBank;
	}

	public void setDeductBank(String deductBank) {
		this.deductBank = deductBank;
	}

	public LendApplyView() {
		CustomerAccount bank = new CustomerAccount();
		goldAccount = new ArrayList<CustomerAccount>();
		goldAccount.add(bank);
		goldAccountProvinceList = new ArrayList<ProvinceCity>();
		goldAccountCityList = new ArrayList<List<ProvinceCity>>();
	}

	public String getMinApplyAmount() {
		return minApplyAmount;
	}

	public void setMinApplyAmount(String minApplyAmount) {
		this.minApplyAmount = minApplyAmount;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Double getLendMoney() {
		return lendMoney;
	}

	public void setLendMoney(Double lendMoney) {
		this.lendMoney = lendMoney;
	}

	public Double getDeductMoney() {
		return deductMoney;
	}

	public void setDeductMoney(Double deductMoney) {
		this.deductMoney = deductMoney;
	}

	public Double getTransferMoney() {
		return transferMoney;
	}

	public void setTransferMoney(Double transferMoney) {
		this.transferMoney = transferMoney;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public LoanApply getLendApply() {
		return lendApply;
	}

	public void setLendApply(LoanApply lendApply) {
		this.lendApply = lendApply;
	}

	public Org getOrg() {
		return org;
	}

	public void setOrg(Org org) {
		this.org = org;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getTeamManagerName() {
		return teamManagerName;
	}

	public void setTeamManagerName(String teamManagerName) {
		this.teamManagerName = teamManagerName;
	}

	public String getFinancialManager() {
		return financialManager;
	}

	public void setFinancialManager(String financialManager) {
		this.financialManager = financialManager;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public List<CustomerAccount> getBanks() {
		return banks;
	}

	public void setBanks(List<CustomerAccount> banks) {
		this.banks = banks;
	}

	public String getReceiveBankId() {
		return receiveBankId;
	}

	public void setReceiveBankId(String receiveBankId) {
		this.receiveBankId = receiveBankId;
	}

	public String getPaymentBankId() {
		return paymentBankId;
	}

	public void setPaymentBankId(String paymentBankId) {
		this.paymentBankId = paymentBankId;
	}

	public List<ProvinceCity> getProvinceList() {
		return provinceList;
	}

	public void setProvinceList(List<ProvinceCity> provinceList) {
		this.provinceList = provinceList;
	}

	public List<List<ProvinceCity>> getCityList() {
		return cityList;
	}

	public void setCityList(List<List<ProvinceCity>> cityList) {
		this.cityList = cityList;
	}

	public List<List<ProvinceCity>> getDistrictList() {
		return districtList;
	}

	public void setDistrictList(List<List<ProvinceCity>> districtList) {
		this.districtList = districtList;
	}

	public List<String> getAddAttachmentId() {
		return addAttachmentId;
	}

	public void setAddAttachmentId(List<String> addAttachmentId) {
		this.addAttachmentId = addAttachmentId;
	}

	public List<CustomerAccount> getGoldAccount() {
		return goldAccount;
	}

	public void setGoldAccount(List<CustomerAccount> goldAccount) {
		this.goldAccount = goldAccount;
	}

	public List<String> getDeleteAttachmentId() {
		return deleteAttachmentId;
	}

	public void setDeleteAttachmentId(List<String> deleteAttachmentId) {
		this.deleteAttachmentId = deleteAttachmentId;
	}

	public List<ProvinceCity> getGoldAccountProvinceList() {
		return goldAccountProvinceList;
	}

	public void setGoldAccountProvinceList(
			List<ProvinceCity> goldAccountProvinceList) {
		this.goldAccountProvinceList = goldAccountProvinceList;
	}

	public List<List<ProvinceCity>> getGoldAccountCityList() {
		return goldAccountCityList;
	}

	public void setGoldAccountCityList(
			List<List<ProvinceCity>> goldAccountCityList) {
		this.goldAccountCityList = goldAccountCityList;
	}

	public String getProtocals() {
		return protocals;
	}

	public void setProtocals(String protocals) {
		this.protocals = protocals;
	}

	public String getAutoOpenAccount() {
		return autoOpenAccount;
	}

	public void setAutoOpenAccount(String autoOpenAccount) {
		this.autoOpenAccount = autoOpenAccount;
	}

	public List<String> getEnableProduct() {
		return enableProduct;
	}

	public void setEnableProduct(List<String> enableProduct) {
		this.enableProduct = enableProduct;
	}

	public String getAuditor() {
		return auditor;
	}

	public void setAuditor(String auditor) {
		this.auditor = auditor;
	}

	
	
	
}
