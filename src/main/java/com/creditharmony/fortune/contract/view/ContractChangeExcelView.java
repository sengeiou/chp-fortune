package com.creditharmony.fortune.contract.view;

import java.io.Serializable;
import java.util.Date;

import com.creditharmony.core.excel.annotation.ExcelField;
import com.creditharmony.core.fortune.type.ContractChangeType;
import com.creditharmony.core.fortune.type.ContractVesion;

public class ContractChangeExcelView implements Serializable   {
	
	private static final long serialVersionUID = -7145210259276892803L;
	
	@ExcelField(title = "合同编号",type=0, align = 2)
	private String contCode;				//合同编号
	@ExcelField(title = "门店",type=0, align = 2)
	private String name;  
	@ExcelField(title = "合同版本",type=0, align = 2)
	private String contVersion;				//合同版本
	@ExcelField(title = "变更类型",type=0, align = 2)
	private String dictChangeType;          //变更类型
	@ExcelField(title = "变更通过时间",type=0, align = 2)
	private Date changeCheckDate;           //变更通过时间
	@ExcelField(title = "申请说明",type=0, align = 2)
	private String changeExplain;			//申请说明
	
	
	public String getContCode() {
		return contCode;
	}
	public void setContCode(String contCode) {
		this.contCode = contCode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContVersion() {
		return contVersion;
	}
	public void setContVersion(String contVersion) {
		//ContractVesion.initContractVesion(); 
		if(null!=contVersion){
			this.contVersion = ContractVesion.getContractVesion(contVersion);
		}
	}
	public String getDictChangeType() {
		return dictChangeType;
	}
	public void setDictChangeType(String dictChangeType) {
		//ContractChangeType.initContractChangeType();
		this.dictChangeType = ContractChangeType.getContractChangeType(dictChangeType);
	}
	public Date getChangeCheckDate() {
		return changeCheckDate;
	}
	public void setChangeCheckDate(Date changeCheckDate) {
		this.changeCheckDate = changeCheckDate;
	}
	public String getChangeExplain() {
		return changeExplain;
	}
	public void setChangeExplain(String changeExplain) {
		this.changeExplain = changeExplain;
	}
	 
    
    
    
}
