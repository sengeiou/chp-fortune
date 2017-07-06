package com.creditharmony.fortune.template.entity.backmoney;

import java.io.Serializable;
import java.util.Date;

import com.creditharmony.common.util.DateUtils;
import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.excel.annotation.ExcelField;

/**
 * 修改渠道标识导入model
 * 
 * @Class Name ChannelImportModel
 * @author 陈广鹏
 * @Create In 2016年6月20日
 */
public class ChannelImportModel implements Serializable {

	/**
	 * long serialVersionUID 
	 */
	private static final long serialVersionUID = -4293883895483969738L;
	
	@ExcelField(title = "出借编号")
	private String lendCode;
	@ExcelField(title = "客户姓名")
	private String customerName;
	@ExcelField(title = "出借金额")
	private String applyLendMoneyStr;
	private Double applyLendMoney;
	@ExcelField(title = "出借模式")
	private String productCode;
	@ExcelField(title = "出借日期")
	private String applyLendDayStr;
	private Date applyLendDay;
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

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getApplyLendMoneyStr() {
		return applyLendMoneyStr;
	}

	public void setApplyLendMoneyStr(String applyLendMoneyStr) {
		this.applyLendMoneyStr = applyLendMoneyStr;
	}

	public Double getApplyLendMoney() {
		try {
			if (StringUtils.isNotEmpty(applyLendMoneyStr)) {
				applyLendMoneyStr = applyLendMoneyStr.replace(",", "");
			}
			applyLendMoney = Double.valueOf(applyLendMoneyStr);
		} catch (NumberFormatException e) {
			applyLendMoney = null;
		}
		return applyLendMoney;
	}

	public void setApplyLendMoney(Double applyLendMoney) {
		this.applyLendMoney = applyLendMoney;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
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
