package com.creditharmony.fortune.common.entity;

import com.creditharmony.core.users.entity.UserRoleOrg;

/**
 * 员工机构关联扩展
 * 
 * @Class Name UserRoleOrgEx
 * @author 朱杰
 * @Create In 2016年2月2日
 */ 
public class UserRoleOrgEx extends UserRoleOrg {
	
	private static final long serialVersionUID = 1L;
	
    /** 机构父ID */
    private String orgParentId;
    /** 用户手机号 */
    private String mobile;
    /** 用户编号 */
    private String userCode;
    /** 团队经理用户编号 */
    private String teamUserCode;
    /** 团队经理用户名 */
    private String teamUserName;
    /** 用户停用 */
    private String delFlagTemp;
    
	public String getDelFlagTemp() {
		return delFlagTemp;
	}
	public void setDelFlagTemp(String delFlagTemp) {
		this.delFlagTemp = delFlagTemp;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getTeamUserCode() {
		return teamUserCode;
	}
	public void setTeamUserCode(String teamUserCode) {
		this.teamUserCode = teamUserCode;
	}
	public String getTeamUserName() {
		return teamUserName;
	}
	public void setTeamUserName(String teamUserName) {
		this.teamUserName = teamUserName; 
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getOrgParentId() {
		return orgParentId;
	}
	public void setOrgParentId(String orgParentId) {
		this.orgParentId = orgParentId;
	}
	
}