package com.creditharmony.fortune.template.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.creditharmony.core.excel.annotation.ExcelField;

/**
 * 消息提醒前30天封闭提醒导出exel model
 * @Class Name TimeTo30DayRemindExportExcelModel
 * @author 韩龙
 * @Create In 2015年11月27日
 */
public class TimeTo30DayRemindExportExcelModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 客户姓名
	@ExcelField(title = "客户姓名")
	private String customerName;

	// 手机号
	@ExcelField(title = "联系电话")
	private String tel;

	// 出借日期
	@ExcelField(title = "计划出借日期")
	private Date lendDay;

	// 出借金额
	@ExcelField(title = "计划出借金额")
	private BigDecimal lendMoney;

	// 出借方式
	@ExcelField(title = "出借方式")
	private String dictLendType;

	// 封闭期满日
	@ExcelField(title = "封闭期满日")
	private Date productClosedate;

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public Date getLendDay() {
		return lendDay;
	}

	public void setLendDay(Date lendDay) {
		this.lendDay = lendDay;
	}

	public BigDecimal getLendMoney() {
		return lendMoney;
	}

	public void setLendMoney(BigDecimal lendMoney) {
		this.lendMoney = lendMoney;
	}

	public String getDictLendType() {
		return dictLendType;
	}

	public void setDictLendType(String dictLendType) {
		this.dictLendType = dictLendType;
	}

	public Date getProductClosedate() {
		return productClosedate;
	}

	public void setProductClosedate(Date productClosedate) {
		this.productClosedate = productClosedate;
	}

}