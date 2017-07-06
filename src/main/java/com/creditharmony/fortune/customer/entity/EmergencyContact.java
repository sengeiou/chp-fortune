package com.creditharmony.fortune.customer.entity;

import java.util.Date;

import com.creditharmony.core.persistence.DataEntity;

/**
 * @Class Name EmergencyContact
 * @author 孙凯文
 * @Create In 2015年12月2日
 */
public class EmergencyContact extends DataEntity<EmergencyContact> {

	private static final long serialVersionUID = 1L;
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
	// 地址信息
	private Address address;

	public String getCustCode() {
		return custCode;
	}

	public void setCustCode(String custCode) {
		this.custCode = custCode == null ? null : custCode.trim();
	}

	public String getEmerName() {
		return emerName;
	}

	public void setEmerName(String emerName) {
		this.emerName = emerName == null ? null : emerName.trim();
	}

	public String getEmerEname() {
		return emerEname;
	}

	public void setEmerEname(String emerEname) {
		this.emerEname = emerEname == null ? null : emerEname.trim();
	}

	public String getEmerSex() {
		return emerSex;
	}

	public void setEmerSex(String emerSex) {
		this.emerSex = emerSex == null ? null : emerSex.trim();
	}

	public String getEmerType() {
		return emerType;
	}

	public void setEmerType(String emerType) {
		this.emerType = emerType == null ? null : emerType.trim();
	}

	public String getEmerCertNum() {
		return emerCertNum;
	}

	public void setEmerCertNum(String emerCertNum) {
		this.emerCertNum = emerCertNum == null ? null : emerCertNum.trim();
	}

	public String getEmerMobilephone() {
		return emerMobilephone;
	}

	public void setEmerMobilephone(String emerMobilephone) {
		this.emerMobilephone = emerMobilephone == null ? null : emerMobilephone
				.trim();
	}

	public Date getEmerBirthday() {
		return emerBirthday;
	}

	public void setEmerBirthday(Date emerBirthday) {
		this.emerBirthday = emerBirthday;
	}

	public String getEmerRelationship() {
		return emerRelationship;
	}

	public void setEmerRelationship(String emerRelationship) {
		this.emerRelationship = emerRelationship == null ? null
				: emerRelationship.trim();
	}

	public String getEmerPhone() {
		return emerPhone;
	}

	public void setEmerPhone(String emerPhone) {
		this.emerPhone = emerPhone == null ? null : emerPhone.trim();
	}

	public String getEmerEmail() {
		return emerEmail;
	}

	public void setEmerEmail(String emerEmail) {
		this.emerEmail = emerEmail == null ? null : emerEmail.trim();
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

}