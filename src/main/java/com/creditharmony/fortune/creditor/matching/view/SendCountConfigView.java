package com.creditharmony.fortune.creditor.matching.view;

import java.io.Serializable;

import com.creditharmony.core.persistence.DataEntity;

public class SendCountConfigView extends DataEntity<SendCountConfigView> implements Serializable {
	
	private static final long serialVersionUID = 7338434534598188213L;
	
	private String id;  //主键
	
	private String userId; //用户id
	
    private String userCode; //用户工号
    
    private String name; //名称
    
    private String userSendCount;//用户配置派发数量
    
    private String leaderSendCount;//主管配置合同数量

    private Integer status;//用户状态
    
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserSendCount() {
		return userSendCount;
	}

	public void setUserSendCount(String userSendCount) {
		this.userSendCount = userSendCount;
	}

	public String getLeaderSendCount() {
		return leaderSendCount;
	}

	public void setLeaderSendCount(String leaderSendCount) {
		this.leaderSendCount = leaderSendCount;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}





}
