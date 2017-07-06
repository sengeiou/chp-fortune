package com.creditharmony.fortune.change.lend.apply.entity;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.creditharmony.bpm.frame.view.BaseBusinessView;
import com.creditharmony.core.dict.entity.Dict;
import com.creditharmony.core.users.entity.ProvinceCity;
import com.creditharmony.fortune.customer.entity.CustomerAccount;

/**
 * 变更通用view
 * @Class Name LendLaunchView
 * @author 刘雄武
 * @Create In 2015年12月1日
 */

public class LendLaunchView extends BaseBusinessView{
	// 出借申请扩展类
    private LendLoanApplyEx lendloanapplyEx;
    // 出借申请类
    private LendLoanApply lendloanapply;
    // 出借人账户信息List    
    private ArrayList<CustomerAccount> accountlist;
    // 出借变更类
	private LendChangeInfo lendchangeinfo;
	// 出借变更审批记录
	private LendChangeLog changeLog;
	// 出借人账户信息
	private CustomerAccount customerAccount;
	// 出借人变更后账户信息存储处
	private CustomerAccount countAfter;
	// 城市级联
	private List<ProvinceCity> provinceList;
	//出借付款银行信息
	private CustomerAccount payAccount;
	//出借回款银行信息
	private CustomerAccount reyAccount;
	// 附件id
	private List<String> addAttachmentId;
	//删除附件集合	
	private List<String> deleteAttachmentId;	
	private Map<String, List<Dict>> dicts;
	public LendLoanApplyEx getLendloanapplyEx() {
		return lendloanapplyEx;
	}
	public void setLendloanapplyEx(LendLoanApplyEx lendloanapplyEx) {
		this.lendloanapplyEx = lendloanapplyEx;
	}
	public LendLoanApply getLendloanapply() {
		return lendloanapply;
	}
	public void setLendloanapply(LendLoanApply lendloanapply) {
		this.lendloanapply = lendloanapply;
	}
	public ArrayList<CustomerAccount> getAccountlist() {
		return accountlist;
	}
	public void setAccountlist(ArrayList<CustomerAccount> accountlist) {
		this.accountlist = accountlist;
	}
	public LendChangeInfo getLendchangeinfo() {
		return lendchangeinfo;
	}
	public void setLendchangeinfo(LendChangeInfo lendchangeinfo) {
		this.lendchangeinfo = lendchangeinfo;
	}
	public LendChangeLog getChangeLog() {
		return changeLog;
	}
	public void setChangeLog(LendChangeLog changeLog) {
		this.changeLog = changeLog;
	}
	public CustomerAccount getCustomerAccount() {
		return customerAccount;
	}
	public void setCustomerAccount(CustomerAccount customerAccount) {
		this.customerAccount = customerAccount;
	}
	public CustomerAccount getCountAfter() {
		return countAfter;
	}
	public void setCountAfter(CustomerAccount countAfter) {
		this.countAfter = countAfter;
	}
	public List<ProvinceCity> getProvinceList() {
		return provinceList;
	}
	public void setProvinceList(List<ProvinceCity> provinceList) {
		this.provinceList = provinceList;
	}
	public CustomerAccount getPayAccount() {
		return payAccount;
	}
	public void setPayAccount(CustomerAccount payAccount) {
		this.payAccount = payAccount;
	}
	public CustomerAccount getReyAccount() {
		return reyAccount;
	}
	public void setReyAccount(CustomerAccount reyAccount) {
		this.reyAccount = reyAccount;
	}
	public List<String> getAddAttachmentId() {
		return addAttachmentId;
	}
	public void setAddAttachmentId(List<String> addAttachmentId) {
		this.addAttachmentId = addAttachmentId;
	}
	public List<String> getDeleteAttachmentId() {
		return deleteAttachmentId;
	}
	public void setDeleteAttachmentId(List<String> deleteAttachmentId) {
		this.deleteAttachmentId = deleteAttachmentId;
	}
	public Map<String, List<Dict>> getDicts() {
		return dicts;
	}
	public void setDicts(Map<String, List<Dict>> dicts) {
		this.dicts = dicts;
	}

	
    
}
