package com.creditharmony.fortune.exituserorg.entity;

import com.creditharmony.core.persistence.DataEntity;

public class UserOrgInfo extends DataEntity<UserOrgInfo> {

	private static final long serialVersionUID = 1L;
	
	private String id;
	private String orgId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
}