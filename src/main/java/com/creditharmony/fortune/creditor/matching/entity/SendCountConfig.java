package com.creditharmony.fortune.creditor.matching.entity;

import com.creditharmony.core.persistence.DataEntity;

public class SendCountConfig extends DataEntity<SendCountConfig>{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 4458562959504759886L;

    private String userId;  //派单用户

    private Integer userSendCount;//用户添加派单数量

    private Integer leaderSendCount;//主管添加派单数量

    private Integer status;  //用户在岗状态 0 离岗 ，1 在岗
    
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getUserSendCount() {
		return userSendCount;
	}

	public void setUserSendCount(Integer userSendCount) {
		this.userSendCount = userSendCount;
	}

	public Integer getLeaderSendCount() {
		return leaderSendCount == null ? 0 : leaderSendCount;
	}

	public void setLeaderSendCount(Integer leaderSendCount) {
		this.leaderSendCount = leaderSendCount;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

   
}