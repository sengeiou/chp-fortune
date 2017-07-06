package com.creditharmony.fortune.app.webservice.ocr.entity;

public class Achievement {

	private String userId ;		//用户ID
	private String userType;	//用户类型 
	private String role ;
	private String userName ;

	private String empCode ;
	private String monthString;	//月份
	private String orgId ;
	private String finishedAchievement;	//完成业绩
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmpCode() {
		return empCode;
	}
	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}
	public String getMonthString() {
		return monthString;
	}
	public void setMonthString(String monthString) {
		this.monthString = monthString;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getFinishedAchievement() {
		return finishedAchievement;
	}
	public void setFinishedAchievement(String finishedAchievement) {
		this.finishedAchievement = finishedAchievement;
	}
	
}
