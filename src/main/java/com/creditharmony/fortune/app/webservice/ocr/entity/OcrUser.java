package com.creditharmony.fortune.app.webservice.ocr.entity;

import com.creditharmony.core.persistence.DataEntity;

/**
 * @Class Name OcrUser
 * @author 张虎
 * @Create In 2016年4月7日
 */
public class OcrUser extends DataEntity<OcrUser> {
	
	 /**
	 * long serialVersionUID 
	 */
	private static final long serialVersionUID = 1L;

	// 客户信息
	private String userId;
	private String userName;
	private String orgId;
	private String role;
	private String store;
	private String loginName;
	private String password;

	public String getPassword() {
		return password;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	@Override
	public String toString() {
		return null;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getStore() {
		return store;
	}

	public void setStore(String store) {
		this.store = store;
	}
}
