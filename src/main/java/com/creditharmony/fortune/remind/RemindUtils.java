package com.creditharmony.fortune.remind;

import com.creditharmony.fortune.deduct.common.DeductUtils;
import com.creditharmony.fortune.remind.entity.ext.SmsSendListEx;

/**
 * 提醒工具类
 * @Class Name RemindUtils
 * @author 韩龙
 * @Create In 2016年4月7日
 */
public class RemindUtils {

	/**
	 * 提醒多选条件组装
	 * 2016年4月7日
	 * By 韩龙
	 * @param smsSendListEx
	 */
	public static void getContains(SmsSendListEx smsSendListEx){
		// 付款方式
		smsSendListEx.setApplyPayList(DeductUtils.assembleStr(smsSendListEx.getDictPayType()));
		// 营业部
		smsSendListEx.setOrgIdList(DeductUtils.assembleStr(smsSendListEx.getOrgId()));
		// 出借产品
		smsSendListEx.setProductCodeList(DeductUtils.assembleStr(smsSendListEx.getProductCode()));
		//回款状态
		smsSendListEx.setBackStatusList(DeductUtils.assembleStr(smsSendListEx.getBackStatus()));
		
	}
	// 截取城市
	public static void anreName(SmsSendListEx smsSendListEx){
		smsSendListEx.setAreaName(DeductUtils.anreNamedep(smsSendListEx.getAreaNames()));
	}
}
