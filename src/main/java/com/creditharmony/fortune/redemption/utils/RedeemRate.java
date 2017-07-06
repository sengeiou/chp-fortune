package com.creditharmony.fortune.redemption.utils;

/**
 * 提前赎回降息前后利率
 * @author xurongsheng
 * @date 2016年8月30日 下午3:23:31
 *
 */
public enum RedeemRate {
	PRE_0("0","0"),
	PRE_1("1","6.80"),
	PRE_2("2","7.40"),
	PRE_3("3","8.00"),
	PRE_4("4","8.33"),
	PRE_5("5","8.67"),
	PRE_6("6","9.00"),
	PRE_7("7","9.62"),
	PRE_8("8","10.23"),
	PRE_9("9","10.84"),
	PRE_10("10","11.46"),
	PRE_11("11","12.06"),
	PRE_12("12","12.68"),
	PRE_13("13","12.71"),
	PRE_14("14","12.73"),
	PRE_15("15","12.75"),
	PRE_16("16","12.79"),
	PRE_17("17","12.81"),
	PRE_18("18","12.84"),
	PRE_19("19","12.87"),
	PRE_20("20","12.89"),
	PRE_21("21","12.91"),
	PRE_22("22","12.95"),
	PRE_23("23","12.97"),
	PRE_24("24","13.00"),
	
	AFT_0("0","0"),
	AFT_1("1","5.80"),
	AFT_2("2","6.40"),
	AFT_3("3","7.00"),
	AFT_4("4","7.34"),
	AFT_5("5","7.67"),
	AFT_6("6","8.00"),
	AFT_7("7","8.33"),
	AFT_8("8","8.67"),
	AFT_9("9","8.99"),
	AFT_10("10","9.34"),
	AFT_11("11","9.67"),
	AFT_12("12","10.00"),
	AFT_13("13","10.17"),
	AFT_14("14","10.33"),
	AFT_15("15","10.51"),
	AFT_16("16","10.67"),
	AFT_17("17","10.83"),
	AFT_18("18","11.00"),
	AFT_19("19","11.17"),
	AFT_20("20","11.34"),
	AFT_21("21","11.50"),
	AFT_22("22","11.67"),
	AFT_23("23","11.84"),
	AFT_24("24","12.00");

	/** 月数 */
	private String monthCount;
	/** 利率 */
	private String rate;
	
	private RedeemRate(String monthCount, String rate) {
		this.monthCount = monthCount;
		this.rate = rate;
	}
	
	public String getMonthCount() {
		return monthCount;
	}
	public void setMonthCount(String monthCount) {
		this.monthCount = monthCount;
	}
	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}
	
}
