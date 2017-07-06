package com.creditharmony.fortune.obligatory.view;

import java.io.Serializable;
import java.math.BigDecimal;

import com.creditharmony.core.persistence.Page;
import com.creditharmony.fortune.obligatory.entity.CreditOkListObj;

/**
 * 列表显示对象
 * @Class Name ObligatoryListView 
 * @author 李志伟
 * @Create In 2016年5月10日
 */
public class ObligatoryListView implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// 分页对象
	private Page<CreditOkListObj> page;
	// 可用债权配置对象
	private CreditOkListObj coo;
	// 
	private String loanBackmoneyDay;

	// 
	private BigDecimal loanMonthRate;

	public Page<CreditOkListObj> getPage() {
		return page;
	}

	public void setPage(Page<CreditOkListObj> page) {
		this.page = page;
	}

	public CreditOkListObj getCoo() {
		return coo;
	}

	public void setCoo(CreditOkListObj coo) {
		this.coo = coo;
	}

	public String getLoanBackmoneyDay() {
		return loanBackmoneyDay;
	}

	public void setLoanBackmoneyDay(String loanBackmoneyDay) {
		this.loanBackmoneyDay = loanBackmoneyDay;
	}

	public BigDecimal getLoanMonthRate() {
		return loanMonthRate;
	}

	public void setLoanMonthRate(BigDecimal loanMonthRate) {
		this.loanMonthRate = loanMonthRate;
	}
}