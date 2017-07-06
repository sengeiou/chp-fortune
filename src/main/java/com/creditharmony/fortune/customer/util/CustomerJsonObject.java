package com.creditharmony.fortune.customer.util;

import java.io.Serializable;

/**
 * @Class Name CustomerJsonObject
 * @author 孙凯文
 * @Create In 2015年12月29日
 */
public class CustomerJsonObject implements Serializable {
	
	private static final long serialVersionUID = 3024952842773774260L;
	private String id;
	private String custCode;
	private String custName;
	private String dictCustSex;
	private String dictCustSource;
	private String custMobilephone;
	private String custPhone;
	private String custEmail;
	private String custUnitname;
	private String custIndustry;
	private String custPost;
	private String custFax;

	private String addrId;
	private String addrProvince;
	private String addrCity;
	private String addrDistrict;
	private String addr;
	private String addrPostalCode;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCustCode() {
		return custCode;
	}

	public void setCustCode(String custCode) {
		this.custCode = custCode;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getDictCustSex() {
		return dictCustSex;
	}

	public void setDictCustSex(String dictCustSex) {
		this.dictCustSex = dictCustSex;
	}

	public String getDictCustSource() {
		return dictCustSource;
	}

	public void setDictCustSource(String dictCustSource) {
		this.dictCustSource = dictCustSource;
	}

	public String getCustMobilephone() {
		return custMobilephone;
	}

	public void setCustMobilephone(String custMobilephone) {
		this.custMobilephone = custMobilephone;
	}

	public String getCustPhone() {
		return custPhone;
	}

	public void setCustPhone(String custPhone) {
		this.custPhone = custPhone;
	}

	public String getCustEmail() {
		return custEmail;
	}

	public void setCustEmail(String custEmail) {
		this.custEmail = custEmail;
	}

	public String getCustUnitname() {
		return custUnitname;
	}

	public void setCustUnitname(String custUnitname) {
		this.custUnitname = custUnitname;
	}

	public String getCustIndustry() {
		return custIndustry;
	}

	public void setCustIndustry(String custIndustry) {
		this.custIndustry = custIndustry;
	}

	public String getCustPost() {
		return custPost;
	}

	public void setCustPost(String custPost) {
		this.custPost = custPost;
	}

	public String getCustFax() {
		return custFax;
	}

	public void setCustFax(String custFax) {
		this.custFax = custFax;
	}

	public String getAddrId() {
		return addrId;
	}

	public void setAddrId(String addrId) {
		this.addrId = addrId;
	}

	public String getAddrProvince() {
		return addrProvince;
	}

	public void setAddrProvince(String addrProvince) {
		this.addrProvince = addrProvince;
	}

	public String getAddrCity() {
		return addrCity;
	}

	public void setAddrCity(String addrCity) {
		this.addrCity = addrCity;
	}

	public String getAddrDistrict() {
		return addrDistrict;
	}

	public void setAddrDistrict(String addrDistrict) {
		this.addrDistrict = addrDistrict;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getAddrPostalCode() {
		return addrPostalCode;
	}

	public void setAddrPostalCode(String addrPostalCode) {
		this.addrPostalCode = addrPostalCode;
	}

	
}
