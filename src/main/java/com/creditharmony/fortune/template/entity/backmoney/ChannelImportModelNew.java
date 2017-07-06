package com.creditharmony.fortune.template.entity.backmoney;

import java.io.Serializable;
import java.util.Date;

import com.creditharmony.common.util.DateUtils;
import com.creditharmony.core.excel.annotation.ExcelField;

/**
 * 修改渠道标识导入model
 * 
 * @Class Name ChannelImportModelNew
 * @author 郭强
 * @Create In 2017年1月4日
 */
public class ChannelImportModelNew implements Serializable {
	
	private static final long serialVersionUID = -3139507880169485527L;
	@ExcelField(title = "姓名")
	private String customerName;
	@ExcelField(title = "出借编号")
	private String lendCode;
	@ExcelField(title = "出借日期")
	private String applyLendDayStr;
	private Date applyLendDay;
	@ExcelField(title = "产品类型")
	private String  productType;
	@ExcelField(title = "到期日期")
	private String applyExpireDayStr;
	private Date applyExpireDay;

	public String getLendCode() {
		return lendCode;
	}

	public void setLendCode(String lendCode) {
		this.lendCode = lendCode;
	}

	public String getCustomerName() {
		return customerName;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getApplyLendDayStr() {
		return applyLendDayStr;
	}

	public void setApplyLendDayStr(String applyLendDayStr) {
		this.applyLendDayStr = applyLendDayStr;
	}

	public Date getApplyLendDay() {
		applyLendDay = DateUtils.parseDate(applyLendDayStr);
		return applyLendDay;
	}

	public void setApplyLendDay(Date applyLendDay) {
		this.applyLendDay = applyLendDay;
	}

	public String getApplyExpireDayStr() {
		return applyExpireDayStr;
	}

	public void setApplyExpireDayStr(String applyExpireDayStr) {
		this.applyExpireDayStr = applyExpireDayStr;
	}

	public Date getApplyExpireDay() {
		applyExpireDay = DateUtils.parseDate(applyExpireDayStr);
		return applyExpireDay;
	}

	public void setApplyExpireDay(Date applyExpireDay) {
		this.applyExpireDay = applyExpireDay;
	}

}
