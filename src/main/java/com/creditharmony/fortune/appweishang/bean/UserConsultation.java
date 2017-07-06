package com.creditharmony.fortune.appweishang.bean;

import java.sql.Timestamp;
import java.util.Date;



public class UserConsultation {
	
	private String name;
	private String mobile;
	private String consultContent;
	private String managerId;
	private Date searchDate;//查询时间
	private Timestamp consultationDate;//咨询时间
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getConsultContent() {
		return consultContent;
	}
	public void setConsultContent(String consultContent) {
		this.consultContent = consultContent;
	}
	public String getManagerId() {
		return managerId;
	}
	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}
	public Date getSearchDate() {
		return searchDate;
	}
	public void setSearchDate(Date searchDate) {
		this.searchDate = searchDate;
	}
	public Timestamp getConsultationDate() {
		return consultationDate;
	}
	public void setConsultationDate(Timestamp consultationDate) {
		this.consultationDate = consultationDate;
	}
}
