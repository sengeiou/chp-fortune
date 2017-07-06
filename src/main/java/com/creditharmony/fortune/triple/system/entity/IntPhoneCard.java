
package com.creditharmony.fortune.triple.system.entity;

import java.util.Date;
import java.util.List;

import com.creditharmony.core.persistence.DataEntity;

/**
 * 手机号证件号关联表
 * @Class Name IntPhoneCard
 * @author 胡体勇
 * @Create In 2016年2月19日
 */
public class IntPhoneCard  extends DataEntity<IntPhone>{

	/**
	 * long serialVersionUID 
	 */
	private static final long serialVersionUID = 1L;
    private String id;
    private String phone;
    private String cardId;
    private String empCode;
    private Date createTime;
    private List<String> phoneList;
    private List<String> cardList;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCardId() {
		return cardId;
	}
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getEmpCode() {
		return empCode;
	}
	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}
	public List<String> getPhoneList() {
		return phoneList;
	}
	public void setPhoneList(List<String> phoneList) {
		this.phoneList = phoneList;
	}
	public List<String> getCardList() {
		return cardList;
	}
	public void setCardList(List<String> cardList) {
		this.cardList = cardList;
	}
}
