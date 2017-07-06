
package com.creditharmony.fortune.template.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import com.creditharmony.core.excel.annotation.ExcelField;

/**
 * @Class Name CustomerDeliveryHistoryExt
 * @author 胡体勇
 * @Create In 2016年1月8日
 */
public class CustomerDeliveryHistoryExt implements Serializable{

	/**
	 * long serialVersionUID 
	 */
	private static final long serialVersionUID = 1L;
	@ExcelField(title = "客户姓名" , align = 2)
	private String custName;// 客户姓名
	@ExcelField(title = "客户编号" , align = 2)
	private String custCode;// 客户编号
	@ExcelField(title = "新营业部" , align = 2)
	private String nOrgName;// 新营业部
	@ExcelField(title = "新团队经理" , align = 2)
	private String nTeamManagerName;// 新团队经理
	@ExcelField(title = "新理财经理" , align = 2)
	private String nfManagerName;// 新理财经理
	@ExcelField(title = "营业部" , align = 2)
	private String orgName;// 营业部
	@ExcelField(title = "团队经理" , align = 2)
	private String teamManagerName;// 团队经理
	@ExcelField(title = "理财经理" , align = 2)
	private String fManagerName;// 理财经理
	@ExcelField(title = "交割时间" , align = 2)
	private Timestamp delDate;// 交割时间
	@ExcelField(title = "操作人" , align = 2)
	private String modifyByName;// 操作人
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getCustCode() {
		return custCode;
	}
	public void setCustCode(String custCode) {
		this.custCode = custCode;
	}
	public String getNOrgName() {
		return nOrgName;
	}
	public void setNOrgName(String nOrgName) {
		this.nOrgName = nOrgName;
	}
	public String getNTeamManagerName() {
		return nTeamManagerName;
	}
	public void setNTeamManagerName(String nTeamManagerName) {
		this.nTeamManagerName = nTeamManagerName;
	}
	public String getNfManagerName() {
		return nfManagerName;
	}
	public void setNfManagerName(String nfManagerName) {
		this.nfManagerName = nfManagerName;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getTeamManagerName() {
		return teamManagerName;
	}
	public void setTeamManagerName(String teamManagerName) {
		this.teamManagerName = teamManagerName;
	}
	public String getFManagerName() {
		return fManagerName;
	}
	public void setFManagerName(String fManagerName) {
		this.fManagerName = fManagerName;
	}
	public Timestamp getDelDate() {
		return delDate;
	}
	public void setDelDate(Timestamp delDate) {
		this.delDate = delDate;
	}
	public String getModifyByName() {
		return modifyByName;
	}
	public void setModifyByName(String modifyByName) {
		this.modifyByName = modifyByName;
	}
}
