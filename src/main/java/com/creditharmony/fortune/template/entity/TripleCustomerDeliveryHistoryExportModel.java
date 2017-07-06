
package com.creditharmony.fortune.template.entity;

import java.io.Serializable;
import java.util.Date;

import com.creditharmony.core.excel.annotation.ExcelField;

/**
 * 三网客户交割历史查询表导出模板
 * @Class Name tripleCustomerDeliveryHistoryExportModel
 * @author 胡体勇
 * @Create In 2016年2月27日
 */
public class TripleCustomerDeliveryHistoryExportModel implements Serializable{

	/**
	 * long serialVersionUID 
	 */
	private static final long serialVersionUID = 1L;
	@ExcelField(title = "客户姓名" , align = 2)
	private String loginName;// 客户姓名
	@ExcelField(title = "营业部" , align = 2)
	private String newOrgName;// 新营业部
	@ExcelField(title = "团队经理" , align = 2)
	private String newTeamManagerName;// 新团队经理
	@ExcelField(title = "理财经理" , align = 2)
	private String newEmpName;// 新理财经理
	@ExcelField(title = "原营业部" , align = 2)
	private String orgName;// 营业部
	@ExcelField(title = "原团队经理" , align = 2)
	private String teamManagerName;// 团队经理
	@ExcelField(title = "原理财经理" , align = 2)
	private String empName;// 理财经理
	@ExcelField(title = "交割时间" , align = 2)
	private Date delDate;// 交割时间
	@ExcelField(title = "操作人" , align = 2)
	private String operator;//操作人
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getNewOrgName() {
		return newOrgName;
	}
	public void setNewOrgName(String newOrgName) {
		this.newOrgName = newOrgName;
	}
	public String getNewTeamManagerName() {
		return newTeamManagerName;
	}
	public void setNewTeamManagerName(String newTeamManagerName) {
		this.newTeamManagerName = newTeamManagerName;
	}
	public String getNewEmpName() {
		return newEmpName;
	}
	public void setNewEmpName(String newEmpName) {
		this.newEmpName = newEmpName;
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
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public Date getDelDate() {
		return delDate;
	}
	public void setDelDate(Date delDate) {
		this.delDate = delDate;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
}
