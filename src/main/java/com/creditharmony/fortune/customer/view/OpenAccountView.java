package com.creditharmony.fortune.customer.view;

import com.creditharmony.fortune.customer.entity.Customer;
import com.creditharmony.fortune.customer.entity.EmergencyContact;
import com.creditharmony.fortune.customer.entity.LoanConfiguration;

/**
 * 开户用的view
 * @Class Name OpenAccountView
 * @author 肖长伟
 * @Create In 2016年4月11日
 */
public class OpenAccountView {
	// 客户
	private Customer customer;
	// 紧急联系人
	private EmergencyContact emergencyContact;
	// 出借信息
	private LoanConfiguration loanConfiguration;
	
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public EmergencyContact getEmergencyContact() {
		return emergencyContact;
	}
	public void setEmergencyContact(EmergencyContact emergencyContact) {
		this.emergencyContact = emergencyContact;
	}
	public LoanConfiguration getLoanConfiguration() {
		return loanConfiguration;
	}
	public void setLoanConfiguration(LoanConfiguration loanConfiguration) {
		this.loanConfiguration = loanConfiguration;
	}
	
	
	
}
