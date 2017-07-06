package com.creditharmony.fortune.contract.view;

import java.io.Serializable;
import java.util.Date;

import com.creditharmony.core.excel.annotation.ExcelField;
import com.creditharmony.core.fortune.type.ContractState;

public class ContractExcelView implements Serializable   {
	
	private static final long serialVersionUID = -7145210259276892803L;
	
	@ExcelField(title = "门店",type=0, align = 2)
	private String name; 
	@ExcelField(title = "合同编号",type=0, align = 2)
	private String contCode;				//合同编号
	@ExcelField(title = "合同版本",type=0, align = 2)
	private String contVersion;				//合同版本
	@ExcelField(title = "生成日期",type=0, align = 2)
	private Date applyDay;                  //申请日期
	@ExcelField(title = "使用日期",type=0, align = 2)
	private Date useDay;
	@ExcelField(title = "合同归属",type=0, align = 2)
	private String contBelong;				
	@ExcelField(title = "合同状态",type=0, align = 2)
	private String dictContStatus;
	
	
	public Date getUseDay() {
		return useDay;
	}
	public void setUseDay(Date useDay) {
		this.useDay = useDay;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContCode() {
		return contCode;
	}
	public void setContCode(String contCode) {
		this.contCode = contCode;
	}
	public String getContVersion() {
		return contVersion;
	}
	public void setContVersion(String contVersion) {
		this.contVersion = contVersion;
	}
	public Date getApplyDay() {
		return applyDay;
	}
	public void setApplyDay(Date applyDay) {
		this.applyDay = applyDay;
	}

	public String getContBelong() {
		return contBelong;
	}
	public void setContBelong(String contBelong) {
		this.contBelong = contBelong;
	}
	public String getDictContStatus() {
		return dictContStatus;
	}
	public void setDictContStatus(String dictContStatus) {
		//ContractState.initContractState();
		this.dictContStatus = ContractState.getContractState(dictContStatus);
	}		
 
    
}
