package com.creditharmony.fortune.customer.view;

import java.io.Serializable;
import java.util.Date;

import com.creditharmony.fortune.customer.util.CustomerJsonObject;

/**
 * @Class Name ChangeTracesView
 * @author 孙凯文
 * @Create In 2015年12月10日
 */
public class ChangeTracesView implements Serializable {

	private static final long serialVersionUID = 1L;
	// 客户信息修改id
	private String id;
	// 创建时间
	private Date createTime;
	// 变更之前的信息
	private CustomerJsonObject changeBeforeCustomer;
	// 变更之后的信息
	private CustomerJsonObject changeAfterCustomer;

	public String getId() {
		return id;
	}

	public CustomerJsonObject getChangeBeforeCustomer() {
		return changeBeforeCustomer;
	}

	public void setChangeBeforeCustomer(CustomerJsonObject changeBeforeCustomer) {
		this.changeBeforeCustomer = changeBeforeCustomer;
	}

	public CustomerJsonObject getChangeAfterCustomer() {
		return changeAfterCustomer;
	}

	public void setChangeAfterCustomer(CustomerJsonObject changeAfterCustomer) {
		this.changeAfterCustomer = changeAfterCustomer;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
