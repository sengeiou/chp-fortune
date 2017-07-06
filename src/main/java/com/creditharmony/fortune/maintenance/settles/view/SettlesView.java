package com.creditharmony.fortune.maintenance.settles.view;

import java.util.Date;

/**
 * 结算维护view
 * @Class Name SettlesView
 * @author 韩龙
 * @Create In 2016年5月15日
 */
public class SettlesView {

	// 账单日
	private Integer billday;

	// 报告日期
	private Date billDate;
	
	// 出借编号
	private  String lendCode;

	public Integer getBillday() {
		return billday;
	}

	public void setBillday(Integer billday) {
		this.billday = billday;
	}

	public Date getBillDate() {
		return billDate;
	}

	public void setBillDate(Date billDate) {
		this.billDate = billDate;
	}

	public String getLendCode() {
		return lendCode;
	}

	public void setLendCode(String lendCode) {
		this.lendCode = lendCode;
	}
}
