package com.creditharmony.fortune.contract.view;

import java.io.Serializable;

import com.creditharmony.core.excel.annotation.ExcelField;

/**
 * 合同归档
 * @author 王鹏飞
 *
 */
public class ContractOnfileExcelView implements Serializable {

	private static final long serialVersionUID = 937039466503619453L;

	//合同编号
	@ExcelField(title = "合同编号",type=0, align = 2)
	private String contCode;

	public String getContCode() {
		return contCode;
	}

	public void setContCode(String contCode) {
		this.contCode = contCode;
	}
	
	

}
