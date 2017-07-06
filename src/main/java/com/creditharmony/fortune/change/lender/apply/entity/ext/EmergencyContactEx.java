package com.creditharmony.fortune.change.lender.apply.entity.ext;

import java.util.Date;

import com.creditharmony.core.persistence.DataEntity;
import com.creditharmony.fortune.customer.entity.Address;

/**
 * 紧急人联系entity
 * @Class Name EmergencyContactEx
 * @author 郭才林
 * @Create In 2015年11月27日
 */
@SuppressWarnings("serial")
public class EmergencyContactEx extends DataEntity<EmergencyContactEx> {

	// 客户编号
	private String custCode;
	// 紧急联系人名称
	private String emerName;
	// 紧急联系人英文名称
	private String emerEname;
	// 紧急联系人性别
	private String emerSex;
	// 紧急联系人证件类型
	private String emerType;
	// 紧急联系人证件号码
	private String emerCertNum;
	// 紧急联系人移动号码
	private String emerMobilephone;
	// 紧急联系人生日
	private Date emerBirthday;
	// 与紧急联系人关系
	private String emerRelationship;
	// 固定号码
	private String emerPhone;
	// 邮箱
	private String emerEmail;
	// 地址id
	private String addId;
	// 紧急联系人地址
	private Address address;

	public String getEmerName() {
		return emerName;
	}

	public void setEmerName(String emerName) {
		this.emerName = emerName;
	}

	public String getEmerEname() {
		return emerEname;
	}

	public void setEmerEname(String emerEname) {
		this.emerEname = emerEname;
	}

	public String getEmerMobilephone() {
		return emerMobilephone;
	}

	public void setEmerMobilephone(String emerMobilephone) {
		this.emerMobilephone = emerMobilephone;
	}

	public String getEmerPhone() {
		return emerPhone;
	}

	public void setEmerPhone(String emerPhone) {
		this.emerPhone = emerPhone;
	}

	public String getEmerEmail() {
		return emerEmail;
	}

	public void setEmerEmail(String emerEmail) {
		this.emerEmail = emerEmail;
	}

	public String getEmerRelationship() {
		return emerRelationship;
	}

	public void setEmerRelationship(String emerRelationship) {
		this.emerRelationship = emerRelationship;
	}

	public String getEmerSex() {
		return emerSex;
	}

	public void setEmerSex(String emerSex) {
		this.emerSex = emerSex;
	}

	public String getAddId() {
		return addId;
	}

	public void setAddId(String addId) {
		this.addId = addId;
	}

	public String getCustCode() {
		return custCode;
	}

	public void setCustCode(String custCode) {
		this.custCode = custCode;
	}

	public String getEmerType() {
		return emerType;
	}

	public void setEmerType(String emerType) {
		this.emerType = emerType;
	}

	public String getEmerCertNum() {
		return emerCertNum;
	}

	public void setEmerCertNum(String emerCertNum) {
		this.emerCertNum = emerCertNum;
	}

	public Date getEmerBirthday() {
		return emerBirthday;
	}

	public void setEmerBirthday(Date emerBirthday) {
		this.emerBirthday = emerBirthday;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	
	

}
