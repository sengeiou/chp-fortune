package com.creditharmony.fortune.look.apply.view;

import java.util.List;

import com.creditharmony.core.persistence.Page;
import com.creditharmony.fortune.customer.entity.Customer;
import com.creditharmony.fortune.customer.entity.CustomerAccount;
import com.creditharmony.fortune.customer.entity.LoanApply;
import com.creditharmony.fortune.look.apply.entity.LendSearchObj;

/**
 * 返回出借申请查看、出借申请审批查看对象
 * @Class Name LendLookObjectView 
 * @author Mr
 * @Create In 2016年6月16日
 */
public class LendLookPageView {
	
	/*
	 *	以下是出借申请查看列表页面的数据对象 
	 */
	// 分页对象
	private Page<?> page;
	// 出借总金额
	private String sum;
	// 检索对象
	private LendSearchObj lso;
	
	/*
	 *	以下是出借申请查看详情页数据对象
	 */
	// 出借信息对象
	private LoanApply la;
	// 客户信息对象
	private Customer ct;
	// 银行信息对象
	List<CustomerAccount> list;
	// 内转信息
	List<LoanApply> transferList; 
	// 合同选项
	private String check1;
	private String check2;
	private String userManagerId;
	// 优惠回款id
	private String priorityId;
	
	public String getUserManagerId() {
		return userManagerId;
	}

	public void setUserManagerId(String userManagerId) {
		this.userManagerId = userManagerId;
	}

	public String getPriorityId() {
		return priorityId;
	}

	public void setPriorityId(String priorityId) {
		this.priorityId = priorityId;
	}

	public String getCheck1() {
		return check1;
	}

	public void setCheck1(String check1) {
		this.check1 = check1;
	}

	public String getCheck2() {
		return check2;
	}

	public void setCheck2(String check2) {
		this.check2 = check2;
	}

	public List<LoanApply> getTransferList() {
		return transferList;
	}

	public void setTransferList(List<LoanApply> transferList) {
		this.transferList = transferList;
	}

	public LoanApply getLa() {
		return la;
	}

	public void setLa(LoanApply la) {
		this.la = la;
	}

	public Customer getCt() {
		return ct;
	}

	public void setCt(Customer ct) {
		this.ct = ct;
	}

	public List<CustomerAccount> getList() {
		return list;
	}

	public void setList(List<CustomerAccount> list) {
		this.list = list;
	}

	public LendSearchObj getLso() {
		return lso;
	}

	public void setLso(LendSearchObj lso) {
		this.lso = lso;
	}

	public Page<?> getPage() {
		return page;
	}

	public void setPage(Page<?> page) {
		this.page = page;
	}

	public String getSum() {
		return sum;
	}

	public void setSum(String sum) {
		this.sum = sum;
	}
	
	
}
