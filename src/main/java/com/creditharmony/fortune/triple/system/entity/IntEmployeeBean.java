
package com.creditharmony.fortune.triple.system.entity;

import java.util.Date;

import com.creditharmony.core.persistence.DataEntity;

/**
 * 理财经理信息
 * @Class Name employee
 * @author 胡体勇
 * @Create In 2016年2月25日
 */
public class IntEmployeeBean  extends DataEntity<IntEmployeeBean>{
	
	/**
	 * long serialVersionUID 
	 */
	private static final long serialVersionUID = 1L;
	private String id;// 主键
	private String empCode;// 员工编码
	private String empName;// 员工姓名
	private String teamId; //团队ID
	private String businessId;// 营业部ID
	private String status;//状态（0在职，1离职）
	private String ustatus;//状态（0在职，1离职） 该状态针对同一个usercode多个用户的情况，只要有一个用户在职 就是在职
	private String sex;// 性别（0女，1男）
	private String mobilePhone;//手机
	private Date createTime ;//创建时间
	private String mail; //邮箱
	private String empPosition; //职务
	private String parentId;// 上级id
	private Date lastModifyTime; // 最后修改时间
	private String sendStatus;// 发送状态
	private String sendType;// 发送类型
	private Date sendTime;// 发送时间
	private String operation;// 操作类型
	private String uniqueNum;// 发送消息唯一编号
	
	private String userId;			//CX 用户ID
	private String loginName;		//CX 登录名称
	private String password;		//CX 用户密码
	private String leaderId;		//CX 领导ID
	private String departmentId;	//CX 主组织机构ID
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEmpCode() {
		return empCode;
	}
	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getTeamId() {
		return teamId;
	}
	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}
	public String getBusinessId() {
		return businessId;
	}
	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}
	public String getStatus() {
		return status;
	}
	public String getUstatus() {
		return ustatus;
	}
	public void setUstatus(String ustatus) {
		this.ustatus = ustatus;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getMobilePhone() {
		return mobilePhone;
	}
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getEmpPosition() {
		return empPosition;
	}
	public void setEmpPosition(String empPosition) {
		this.empPosition = empPosition;
	}
	public Date getLastModifyTime() {
		return lastModifyTime;
	}
	public void setLastModifyTime(Date lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}
	public String getSendStatus() {
		return sendStatus;
	}
	public void setSendStatus(String sendStatus) {
		this.sendStatus = sendStatus;
	}
	public String getSendType() {
		return sendType;
	}
	public void setSendType(String sendType) {
		this.sendType = sendType;
	}
	public Date getSendTime() {
		return sendTime;
	}
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public String getUniqueNum() {
		return uniqueNum;
	}
	public void setUniqueNum(String uniqueNum) {
		this.uniqueNum = uniqueNum;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getUserId() {
		return userId;
	}
	public String getLoginName() {
		return loginName;
	}
	public String getPassword() {
		return password;
	}
	public String getLeaderId() {
		return leaderId;
	}
	public String getDepartmentId() {
		return departmentId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setLeaderId(String leaderId) {
		this.leaderId = leaderId;
	}
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}
}
