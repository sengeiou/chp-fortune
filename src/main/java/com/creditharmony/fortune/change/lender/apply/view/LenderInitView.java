package com.creditharmony.fortune.change.lender.apply.view;

import java.util.List;
import java.util.Map;

import com.creditharmony.bpm.frame.view.BaseBusinessView;
import com.creditharmony.core.dict.entity.Dict;
import com.creditharmony.core.users.entity.ProvinceCity;
import com.creditharmony.fortune.change.lender.apply.entity.LenderChangeInfo;
import com.creditharmony.fortune.change.lender.apply.entity.LenderChangeLog;
import com.creditharmony.fortune.change.lender.apply.entity.ext.CustomerEx;
import com.creditharmony.fortune.change.lender.apply.entity.ext.EmergencyContactEx;
import com.creditharmony.fortune.customer.entity.CustomerAccount;
import com.creditharmony.fortune.customer.entity.LoanConfiguration;

/**
 * 表单视图
 * @Class Name LenderInitView
 * @author 郭才林
 * @Create In 2015年12月2日
 */
/**
 * @Class Name LenderInitView
 * @author 郭才林
 * @Create In 2015年12月7日
 */
public class LenderInitView  extends  BaseBusinessView {
	
	private CustomerEx customer;// 客户信息
	private LenderChangeInfo changInfo;// 变更记录
	private LenderChangeLog  changeLog ;// 审批记录
	private EmergencyContactEx emer;// 紧急联系联系人
	private LoanConfiguration loanInfo;// 出借信息
	private List<ProvinceCity> provinceList;// 获取城市级联
	private List<String> addAttachmentId;// 附件id集合
	private CustomerAccount account;// 账户信息付款
	private CustomerAccount rePay;// 账户信息回款
	private List<String> deleteAttachmentId;//删除附件集合
	private Map<String, List<Dict>> dicts;
	
	
	public Map<String, List<Dict>> getDicts() {
		return dicts;
	}

	public void setDicts(Map<String, List<Dict>> dicts) {
		this.dicts = dicts;
	}

	public List<ProvinceCity> getProvinceList() {
		return provinceList;
	}

	public void setProvinceList(List<ProvinceCity> provinceList) {
		this.provinceList = provinceList;
	}

	public LoanConfiguration getLoanInfo() {
		return loanInfo;
	}

	public void setLoanInfo(LoanConfiguration loanInfo) {
		this.loanInfo = loanInfo;
	}

	
	public LenderChangeInfo getChangInfo() {
		return changInfo;
	}

	public void setChangInfo(LenderChangeInfo changInfo) {
		this.changInfo = changInfo;
	}

	
	public LenderChangeLog getChangeLog() {
		return changeLog;
	}

	public void setChangeLog(LenderChangeLog changeLog) {
		this.changeLog = changeLog;
	}

	
	public CustomerEx getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerEx customer) {
		this.customer = customer;
	}

	public EmergencyContactEx getEmer() {
		return emer;
	}

	public void setEmer(EmergencyContactEx emer) {
		this.emer = emer;
	}

	public List<String> getAddAttachmentId() {
		return addAttachmentId;
	}

	public void setAddAttachmentId(List<String> addAttachmentId) {
		this.addAttachmentId = addAttachmentId;
	}

	public CustomerAccount getAccount() {
		return account;
	}

	public void setAccount(CustomerAccount account) {
		this.account = account;
	}

	public CustomerAccount getRePay() {
		return rePay;
	}

	public void setRePay(CustomerAccount rePay) {
		this.rePay = rePay;
	}

	public List<String> getDeleteAttachmentId() {
		return deleteAttachmentId;
	}

	public void setDeleteAttachmentId(List<String> deleteAttachmentId) {
		this.deleteAttachmentId = deleteAttachmentId;
	}

	
	

}