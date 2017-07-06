package com.creditharmony.fortune.redemption.constant;

/**
 * 赎回状态
 * 
 * @Class Name RedeemState
 * @author 陈广鹏
 * @Create In 2016年2月25日
 */
public enum RedeemState {

	DSP("1", "待审批"), 
	YTG("2", "已通过"), 
	WTG("3", "未通过"),
	DTS("4", "待推送");

	public String value;
	public String name;

	private RedeemState(String value, String name) {
		this.value = value;
		this.name = name;
	}
}
