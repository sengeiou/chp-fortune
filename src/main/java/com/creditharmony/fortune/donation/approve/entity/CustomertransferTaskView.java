package com.creditharmony.fortune.donation.approve.entity;

import java.util.Date;

import com.creditharmony.bpm.frame.view.BaseTaskItemView;

/**
 * 转赠待办视图
 * @Class Name CustomertransferTaskView
 * @author 刘雄武
 * @Create In 2016年3月7日
 */
public class CustomertransferTaskView extends BaseTaskItemView {

	private String  customerName; // 客户姓名
    private String  customerCode; // 客户编码
    private String  financing;// 新理财经理ID
    private String  managerCode;// 理财经理工号
    private String  managerCodeNew;// 理财经理工号（新）
    private String  managerName;// 理财经理姓名
    private String  managerNameNew;// 理财经理姓名（新）
    private String  departmentNew;// 营业部（新）
    private String  department;// 营业部
    private String  transferState;// 转赠状态
    private Date  createDate;// 转赠日期
    
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerCode() {
		return customerCode;
	}
	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}
	public String getFinancing() {
		return financing;
	}
	public void setFinancing(String financing) {
		this.financing = financing;
	}
	public String getManagerCode() {
		return managerCode;
	}
	public void setManagerCode(String managerCode) {
		this.managerCode = managerCode;
	}
	public String getManagerCodeNew() {
		return managerCodeNew;
	}
	public void setManagerCodeNew(String managerCodeNew) {
		this.managerCodeNew = managerCodeNew;
	}
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	public String getManagerNameNew() {
		return managerNameNew;
	}
	public void setManagerNameNew(String managerNameNew) {
		this.managerNameNew = managerNameNew;
	}
	public String getDepartmentNew() {
		return departmentNew;
	}
	public void setDepartmentNew(String departmentNew) {
		this.departmentNew = departmentNew;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	
	public String getTransferState() {
		return transferState;
	}
	public void setTransferState(String transferState) {
		this.transferState = transferState;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
    
   	
}
