package com.creditharmony.fortune.teleSale.entity.ex;

/**
 * 电销中心分配理财经理、电销专员 Bean
 * @Class Name DistributeManagerBeanEx
 * @author 肖长伟
 * @Create In 2016年2月2日
 */
public class DistributeManagerBeanEx {
	private String managerId;   //理财经理ID
	private String managerName;  //理财经理姓名
	private String managerCode;  //理财经理code
	private String managerOrgName; //理财经理所属营业部
	
	
	public String getManagerId() {
		return managerId;
	}
	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	public String getManagerCode() {
		return managerCode;
	}
	public void setManagerCode(String managerCode) {
		this.managerCode = managerCode;
	}
	public String getManagerOrgName() {
		return managerOrgName;
	}
	public void setManagerOrgName(String managerOrgName) {
		this.managerOrgName = managerOrgName;
	}
	
	
	
}
