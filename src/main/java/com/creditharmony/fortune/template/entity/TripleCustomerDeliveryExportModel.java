
package com.creditharmony.fortune.template.entity;

import java.io.Serializable;

import com.creditharmony.core.excel.annotation.ExcelField;

/**
 * @Class Name TripleCustomerDeliveryExportModel
 * @author 胡体勇
 * @Create In 2016年2月27日
 */
public class TripleCustomerDeliveryExportModel implements Serializable{

	/**
	 * long serialVersionUID 
	 */
	private static final long serialVersionUID = 1L;

	@ExcelField(title = "客户编号" , align = 2)
	private String customerCode;// 客户编号
	@ExcelField(title = "客户姓名" , align = 2)
	private String loginName;// 客户姓名
	@ExcelField(title = "理财经理工号" , align = 2)
	private Integer empCode;// 理财经理工号
	@ExcelField(title = "理财经理" , align = 2)
	private String empName;// 理财经理号
	@ExcelField(title = "营业部" , align = 2)
	private String orgName;// 营业部
	@ExcelField(title = "系统类型" , align = 2)
	private String osType;// 系统类型
	@ExcelField(title = "理财经理工号（新）" , align = 2)
	private Integer newEmpCode;// 新理财经理工号
	@ExcelField(title = "理财经理（新）" , align = 2)
	private String newEmpName;// 新理财经理
	
	public String getCustomerCode() {
		return customerCode;
	}
	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public Integer getEmpCode() {
		return empCode;
	}
	public void setEmpCode(Integer empCode) {
		this.empCode = empCode;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getOsType() {
		return osType;
	}
	public void setOsType(String osType) {
		this.osType = osType;
	}
	public Integer getNewEmpCode() {
		return newEmpCode;
	}
	public void setNewEmpCode(Integer newEmpCode) {
		this.newEmpCode = newEmpCode;
	}
	public String getNewEmpName() {
		return newEmpName;
	}
	public void setNewEmpName(String newEmpName) {
		this.newEmpName = newEmpName;
	}
	
}
