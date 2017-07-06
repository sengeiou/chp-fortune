package com.creditharmony.fortune.maintenance.missrelease.view;

public class MissReleaseView {
	
	// 出借编号
	private String lendCode;
	
	// 开始期数
	private Integer startPeriods;
	
	// 结束期数
	private Integer endPeriods;

	public String getLendCode() {
		return lendCode;
	}

	public void setLendCode(String lendCode) {
		this.lendCode = lendCode;
	}

	public Integer getStartPeriods() {
		return startPeriods;
	}

	public void setStartPeriods(Integer startPeriods) {
		this.startPeriods = startPeriods;
	}

	public Integer getEndPeriods() {
		return endPeriods;
	}

	public void setEndPeriods(Integer endPeriods) {
		this.endPeriods = endPeriods;
	}
}
