package com.creditharmony.fortune.contract.view;

import java.io.Serializable;
import java.util.Date;

import com.creditharmony.core.excel.annotation.ExcelField;
import com.creditharmony.core.fortune.type.ContractVesion;

public class ContractDistributeExcelView implements Serializable {

	private static final long serialVersionUID = 937039466503619453L;

	//申请ID
	@ExcelField(title = "申请编号",type=0, align = 2)
	private String contractId;
	
	//门店ID
	@ExcelField(title = "门店",type=0, align = 2)
	private String name;
	
	//物流编号
	@ExcelField(title = "物流编号",type=0, align = 2)
	private String distExpressNo;
	
	//合同起始编号
	@ExcelField(title = "合同起始编号",type=0, align = 2)
	private String distStartNo;
	
	//合同终止编号
	@ExcelField(title = "合同终止编号",type=0, align = 2)
	private String distEndNo;
	
	//合同数量（箱）
	@ExcelField(title = "合同数量(箱)",type=0, align = 2)
	private String distBox;
	
	//合同申请数量（套）
	@ExcelField(title = "合同申请数量(套)",type=0, align = 2)
	private String applyNo;
	
	//合同派发数量(套)
	@ExcelField(title = "合同派发数量(套)",type=0, align = 2)
	private String distContractNo;
	
	//合同版本
	@ExcelField(title = "合同版本",type=0, align = 2)
	private String contVersion;
	
	//申请日期
	@ExcelField(title = "申请日期",type=0, align = 2)
	private Date applyDay;
	
	//派发日期
	@ExcelField(title = "发货日期",type=0, align = 2)
	private Date distDate;
	
	//收货地址
	@ExcelField(title = "收货地址",type=0, align = 2)
	private String applyShippingAddr;
	
	//联系人
	@ExcelField(title = "联系人",type=0, align = 2)
	private String applyContacts;
	
	//联系电话
	@ExcelField(title = "联系电话",type=0, align = 2)
	private String applyTel;
	
	//申请说明
	@ExcelField(title = "申请说明",type=0, align = 2)
	private String applyExplain;

	public String getContractId() {
		return contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDistExpressNo() {
		return distExpressNo;
	}

	public void setDistExpressNo(String distExpressNo) {
		this.distExpressNo = distExpressNo;
	}

	public String getDistStartNo() {
		return distStartNo;
	}

	public void setDistStartNo(String distStartNo) {
		this.distStartNo = distStartNo;
	}

	public String getDistEndNo() {
		return distEndNo;
	}

	public void setDistEndNo(String distEndNo) {
		this.distEndNo = distEndNo;
	}

	public String getDistBox() {
		return distBox;
	}

	public void setDistBox(String distBox) {
		this.distBox = distBox;
	}

	public String getApplyNo() {
		return applyNo;
	}

	public void setApplyNo(String applyNo) {
		this.applyNo = applyNo;
	}

	public String getDistContractNo() {
		return distContractNo;
	}

	public void setDistContractNo(String distContractNo) {
		this.distContractNo = distContractNo;
	}

	public String getContVersion() {
		return contVersion;
	}

	public void setContVersion(String contVersion) {
		//ContractVesion.initContractVesion(); 
		this.contVersion = ContractVesion.getContractVesion(contVersion);
	}

	public Date getApplyDay() {
		return applyDay;
	}

	public void setApplyDay(Date applyDay) {
		this.applyDay = applyDay;
	}

	public Date getDistDate() {
		return distDate;
	}

	public void setDistDate(Date distDate) {
		this.distDate = distDate;
	}

	public String getApplyShippingAddr() {
		return applyShippingAddr;
	}

	public void setApplyShippingAddr(String applyShippingAddr) {
		this.applyShippingAddr = applyShippingAddr;
	}

	public String getApplyContacts() {
		return applyContacts;
	}

	public void setApplyContacts(String applyContacts) {
		this.applyContacts = applyContacts;
	}

	public String getApplyTel() {
		return applyTel;
	}

	public void setApplyTel(String applyTel) {
		this.applyTel = applyTel;
	}

	public String getApplyExplain() {
		return applyExplain;
	}

	public void setApplyExplain(String applyExplain) {
		this.applyExplain = applyExplain;
	}

}
