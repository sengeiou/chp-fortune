package com.creditharmony.fortune.trusteeship.entity;

import com.creditharmony.core.persistence.DataEntity;

/**
 * 富有地区码
 * @Class Name FyArea
 * @author 朱杰
 * @Create In 2016年2月29日
 */
public class FyCode extends DataEntity<FyCode>{

	/**
	 * long serialVersionUID 
	 */
	private static final long serialVersionUID = 1L;

	private String name;
	
	private String fyCode;
	
	private String sysCode;

	public String getFyCode() {
		return fyCode;
	}

	public void setFyCode(String fyCode) {
		this.fyCode = fyCode;
	}

	public String getSysCode() {
		return sysCode;
	}

	public void setSysCode(String sysCode) {
		this.sysCode = sysCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}