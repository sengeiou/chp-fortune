package com.creditharmony.fortune.contract.view;

import java.io.Serializable;
import java.util.Date;

import com.creditharmony.core.excel.annotation.ExcelField;
import com.creditharmony.core.fortune.type.AppalyState;
import com.creditharmony.core.fortune.type.ContractVesion;

/**
 * 合同申请导出Excel Bean
 * 2015年12月24日
 * @author 王鹏飞
 */
public class ContractApplyExcelView implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@ExcelField(title="门店",type=0,align=2)
	private String orgName;                         //门店名称
	
	@ExcelField(title="上月使用",type=0,align=2)
	private String applyAlreadyuse;				//上月使用
	
	@ExcelField(title="现有库存",type=0,align=2)
	private String applyInventory;					//现有库存
	
	@ExcelField(title="合同申请数量(套)",type=0,align=2)
	private String applyNo;						//申请数量
	
	@ExcelField(title="派发数量",type=0,align=2)
	private String distContractNo;                 //派发数量
	
	@ExcelField(title="合同版本",type=0,align=2)
	private String contVersion;						//合同版本
	
	@ExcelField(title="申请日期",type=0,align=2)
	private Date applyDay;							//申请日期
	
	@ExcelField(title="发货日期",type=0,align=2)
	private Date distDate ;						    //发货日期
	
	@ExcelField(title="申请状态",type=0,align=2)
	private String applyStatus ;	                //申请状态

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getApplyAlreadyuse() {
		return applyAlreadyuse;
	}

	public void setApplyAlreadyuse(String applyAlreadyuse) {
		this.applyAlreadyuse = applyAlreadyuse;
	}

	public String getApplyInventory() {
		return applyInventory;
	}

	public void setApplyInventory(String applyInventory) {
		this.applyInventory = applyInventory;
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

	public String getApplyStatus() {
		return applyStatus;
	}

	public void setApplyStatus(String applyStatus) {
		//AppalyState.initAppalyState();
		this.applyStatus = AppalyState.getAppalyState(applyStatus);
	}
   
}
