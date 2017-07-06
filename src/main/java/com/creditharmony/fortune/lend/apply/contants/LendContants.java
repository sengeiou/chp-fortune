package com.creditharmony.fortune.lend.apply.contants;

import com.creditharmony.core.fortune.type.ProductCode;

/**
 * 出借申请常量
 * @Class Name LendContants
 * @author 肖长伟
 * @Create In 2016年4月27日
 */
public class LendContants {
	
	//最小出借金额
	public static String minApplyAmount = "50000";
	//合同标识---协议
	public static String PROTOCOL = "2";
	//合同标识---合同
	public static String CONTRACT = "1";
	
	public static String[] ENABLE_PRODUCT = new String[]{
		// 信和通
		ProductCode.XHT.value,
		// 月息通
		ProductCode.YXT.value,
		// 季度盈
		ProductCode.JDY.value,
		// 双季盈
		ProductCode.SJY.value,
		// 信和月增
		ProductCode.XHYZ.value,
		// 信和年聚
		ProductCode.XHNJ.value,
		// 年年金
		ProductCode.NNJ.value,
		// 月满盈
		ProductCode.YMY.value
	};
}
