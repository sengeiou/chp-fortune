
package com.creditharmony.fortune.triple.system.entity;

import java.util.Date;

import com.creditharmony.core.persistence.DataEntity;

/**
 * 三网首单
 * @Class Name IntCard
 * @author 胡体勇
 * @Create In 2016年2月18日
 */
public class IntCard extends DataEntity<IntCard> {

	/**
	 * long serialVersionUID 
	 */
	private static final long serialVersionUID = 1L;
    private String id;
    private String userName; // 客户姓名
    private String cardType; // 证件类型
    private String cardId; // 证件号码
    private Date birthday; // 生日
    private String sex; // 性别
    private String mail; // 邮箱
    private String empCode; // 理财经理工号
    private String orderStatus; // 成单状态
    private Date orderTime; // 成单时间
    private Date createTime; // 创建时间
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	public String getCardId() {
		return cardId;
	}
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getEmpCode() {
		return empCode;
	}
	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public Date getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
