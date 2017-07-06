package com.creditharmony.fortune.app.webservice.ocr.entity;

import com.creditharmony.core.persistence.DataEntity;

/**
 * @Class Name CertInfoBean 财富补录银行卡信息查询
 * @author 张虎
 * @Create In 2016年4月7日
 */
public class BankInfoBean extends DataEntity<OcrUser> {
	
	 /**
	 * long serialVersionUID 
	 */
	private static final long serialVersionUID = 1L;
	
	private String userId;//登录用户id
	private String name;//姓名
	private String certNum;//身份证号
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCertNum() {
		return certNum;
	}
	public void setCertNum(String certNum) {
		this.certNum = certNum;
	}
	
	
	
}
