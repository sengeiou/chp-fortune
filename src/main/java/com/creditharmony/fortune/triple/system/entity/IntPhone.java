
package com.creditharmony.fortune.triple.system.entity;

import java.util.Date;

import com.creditharmony.core.persistence.DataEntity;

/**
 * 三网客户
 * @Class Name IntPhone
 * @author 胡体勇
 * @Create In 2016年2月18日
 */
public class IntPhone extends DataEntity<IntPhone> {

	/**
	 * long serialVersionUID 
	 */
	private static final long serialVersionUID = 1L;
    private String id;
    private String loginName; // 用户登录名称
    private String customerCode; // 客户编号
    private String phone; // 旧手机号
    private String osType; // 系统类型
    private String osId;// 各系统id
    private String empCode; // 理财经理工号
    private Date createTime; // 创建时间 
    private String newPhone; // 新手机号
    private String faileName;// 伪名
    private String cardId;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getOsType() {
		return osType;
	}
	public void setOsType(String osType) {
		this.osType = osType;
	}
	public String getOsId() {
		return osId;
	}
	public void setOsId(String osId) {
		this.osId = osId;
	}
	public String getEmpCode() {
		return empCode;
	}
	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getNewPhone() {
		return newPhone;
	}
	public void setNewPhone(String newPhone) {
		this.newPhone = newPhone;
	}
	public String getCustomerCode() {
		return customerCode;
	}
	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}
	public String getFaileName() {
		return faileName;
	}
	public void setFaileName(String faileName) {
		this.faileName = faileName;
	}
	public String getCardId() {
		return cardId;
	}
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
}
