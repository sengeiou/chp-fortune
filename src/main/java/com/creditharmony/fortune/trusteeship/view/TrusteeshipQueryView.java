package com.creditharmony.fortune.trusteeship.view;

import com.creditharmony.core.persistence.DataEntity;

/**
 * 金账户查询
 * @Class Name TrusteeshipQueryView
 * @author 郭才林
 * @Create In 2016年2月13日
 */
public class TrusteeshipQueryView extends DataEntity<TrusteeshipQueryView>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// 客户姓名
	private String customerName;
	// 客户编号
	private String customerCode;
	//开户行
	private String bankId;
	//变更类型
	private String changeType ;
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
	public String getBankId() {
		return bankId;
	}
	public void setBankId(String bankId) {
		this.bankId = bankId;
	}
	public String getChangeType() {
		return changeType;
	}
	public void setChangeType(String changeType) {
		this.changeType = changeType;
	}


	
	
}