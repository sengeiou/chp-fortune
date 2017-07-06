package com.creditharmony.fortune.borrow.view;

import java.io.Serializable;
import java.math.BigDecimal;

public class ReplaceBorrowView implements Serializable{

	/**
	 * long serialVersionUID 
	 */
	private static final long serialVersionUID = 1L;
	private String creditValueId;
	private BigDecimal creditLines;//匹配金额
	public String getCreditValueId() {
		return creditValueId;
	}
	public void setCreditValueId(String creditValueId) {
		this.creditValueId = creditValueId;
	}
	public BigDecimal getCreditLines() {
		return creditLines;
	}
	public void setCreditLines(BigDecimal creditLines) {
		this.creditLines = creditLines;
	}
}
