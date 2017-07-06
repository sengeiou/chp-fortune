package com.creditharmony.fortune.creditor.matching.utils;

/**
 * 债权到期释放 转出状态
 * @author xurongsheng
 * @date 2016年11月29日 下午3:05:30
 *
 */
public enum CreditorReleaseState {
	/** 未操作 */
	WCZ("0"),
	/** 操作中 */
	CZZ("1"),
	/** 已操作 */
	YCZ("2"),
	/** 操作失败 */
	CZSB("3"),
	/** 已结清 */
	YJQ("4");
	
	public final String value;

	private CreditorReleaseState(String value) {
		this.value = value;
	}
}
