package com.creditharmony.fortune.contract.view;

import java.io.Serializable;

import com.creditharmony.core.excel.annotation.ExcelField;
import com.creditharmony.fortune.contract.util.StatisticsUtil;


public class ContractStatisticsExcelView  implements Serializable  {
	
	
	private static final long serialVersionUID = -4620345238708953967L;
	
	//申请ID
	@ExcelField(title = "序号",type=0, align = 2)
	private String no;					  //序号
	@ExcelField(title = "门店名称",type=0, align = 2)
	private String orgName;					  //机构名
	@ExcelField(title = "合同生成量",type=0, align = 2)
	private String createCount;					  //机构名
	@ExcelField(title = "有效使用量",type=0, align = 2)
	private int validNum;		      //有效使用合同（已出借的为为有效合同）
	@ExcelField(title = "作废量",type=0, align = 2)
	private int uselessNum;            //作废量
	@ExcelField(title = "合同版本",type=0, align = 2)
	private String conVersion; //合同版本
	@ExcelField(title = "起始时间",type=0, align = 2)
	private String start;
	@ExcelField(title = "终止时间",type=0, align = 2)
	private String end;
	
	
	
	public String getConVersion() {
		return conVersion;
	}
	public void setConVersion(String conVersion) {
		this.conVersion = conVersion;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public int getValidNum() {
		return validNum;
	}
	public void setValidNum(int validNum) {
		this.validNum = validNum;
	}
	
	public String getStart() {
		 return StatisticsUtil.getDate(start);
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getEnd() {
		 return StatisticsUtil.getDate(end);
	}
	public void setEnd(String end) {
		this.end = end;
	}
	public int getUselessNum() {
		return uselessNum;
	}
	public void setUselessNum(int uselessNum) {
		this.uselessNum = uselessNum;
	}
	public String getCreateCount() {
		return createCount;
	}
	public void setCreateCount(String createCount) {
		this.createCount = createCount;
	}
	

	
}
