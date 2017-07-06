package com.creditharmony.fortune.customer.entity;

import com.creditharmony.core.persistence.DataEntity;

/**
 * @Class Name Address
 * @author 孙凯文
 * @Create In 2015年12月2日
 */
public class Address extends DataEntity<Address> {

	private static final long serialVersionUID = 5938530341304359584L;
	// 地址省份ID
	private String addrProvince;
	// 地址省份值
	private String addrProvinceValue;
	// 城市ID
	private String addrCity;
	// 城市值
	private String addrCityValue;
	// 区ID
	private String addrDistrict;
	// 区值
	private String addrDistrictValue;
	// 详细地址
	private String addr;
	// 邮政编码
	private String addrPostalCode;

	public String getAddrProvince() {
		return addrProvince;
	}

	public void setAddrProvince(String addrProvince) {
		this.addrProvince = addrProvince == null ? null : addrProvince.trim();
	}

	public String getAddrCity() {
		return addrCity;
	}

	public void setAddrCity(String addrCity) {
		this.addrCity = addrCity == null ? null : addrCity.trim();
	}

	public String getAddrDistrict() {
		return addrDistrict;
	}

	public void setAddrDistrict(String addrDistrict) {
		this.addrDistrict = addrDistrict == null ? null : addrDistrict.trim();
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr == null ? null : addr.trim();
	}

	public String getAddrPostalCode() {
		return addrPostalCode;
	}

	public void setAddrPostalCode(String addrPostalCode) {
		this.addrPostalCode = addrPostalCode == null ? null : addrPostalCode
				.trim();
	}

	public String getAddrProvinceValue() {
		return addrProvinceValue;
	}

	public void setAddrProvinceValue(String addrProvinceValue) {
		this.addrProvinceValue = addrProvinceValue;
	}

	public String getAddrCityValue() {
		return addrCityValue;
	}

	public void setAddrCityValue(String addrCityValue) {
		this.addrCityValue = addrCityValue;
	}

	public String getAddrDistrictValue() {
		return addrDistrictValue;
	}

	public void setAddrDistrictValue(String addrDistrictValue) {
		this.addrDistrictValue = addrDistrictValue;
	}

}
