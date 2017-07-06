package com.creditharmony.fortune.customer.view;

import java.io.Serializable;

import com.creditharmony.fortune.customer.entity.Address;
import com.creditharmony.fortune.customer.entity.Advisory;
import com.creditharmony.fortune.customer.entity.Customer;
import com.creditharmony.fortune.customer.entity.EmergencyContact;
import com.creditharmony.fortune.customer.entity.LoanConfiguration;

/**
 * @Class Name CustomerAddView
 * @author 孙凯文
 * @Create In 2015年11月27日
 */
public class CustomerView implements Serializable {
	private static final long serialVersionUID = 1L;
	// 客户信息
	private Customer customer;
	// 咨询
	private Advisory advisory;
	// 地址
	private Address address;
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

	public Advisory getAdvisory() {
		return advisory;
	}

	public void setAdvisory(Advisory advisory) {
		this.advisory = advisory;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
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