package com.creditharmony.fortune.back.money.constant;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.creditharmony.core.fortune.type.BackMoneyPlat;
import com.creditharmony.core.fortune.type.BackState;
import com.creditharmony.core.fortune.type.WorkingState;

/**
 * 回款相关常量
 * 
 * @Class Name Common
 * @author 陈广鹏
 * @Create In 2015年12月10日
 */
public class BmConstant {

	
	/**
	 * 付款资金来自冻结
	 */
	public static final String OUT_FREEZE = "否";
	/**
	 * 月息通、信和月增在 2016-08-01 前后，计算公式不同
	 */
	public static final String YXT_DATE = "2016-08-01";
	/**
	 * 列表标识
	 */
	public static final String APPLY_LIST = "apply";
	/**
	 * 收款后立即冻结
	 */
	public static final String IN_FREEZE = "否";
	/**
	 * 富友平台回款回执文件中，回款成功识别字符串
	 */
	public static final String FY_SUCCESS = "否";
	/**
	 * 金账户回款回执文件中，回款成功识别字符串
	 */
	public static final String JZH_SUCCESS = "0000";
	/**
	 * 2017年1月1日（包括1月1日当天）之后到期的客户，目前【月利率】固定是0.5%
	 */
	public static final String DATE_20170101 = "2017-01-01";
	/**
	 * 2017年1月1日（包括1月1日当天）之后到期的客户，目前【月利率】固定是0.5%
	 */
	public static final BigDecimal SUPLEMENT_RATE = new BigDecimal("0.5");
	
	/**
	 * 中金回款回执文件中，回款识别字符串
	 */
	public static final String ZJ_DEALING_2 = "10-New"; // 处理中
	public static final String ZJ_DEALING = "20-正在处理"; // 处理中
	public static final String ZJ_SUCCESS = "30-代付成功"; // 成功
	public static final String ZJ_FAIL = "40-代付失败"; // 失败
	
	/**
	 * 通联回款回执文件中，回款识别字符串
	 */
	public static final String TL_SUCCESS = "处理成功"; // 成功
	public static final String TL_FAIL = "处理失败"; // 失败
	public static final String TL_DEALING = "处理中"; // 处理中
	
	/**
	 * 卡联回款回执文件中，回款识别字符串
	 */
	public static final String KL_SUCCESS = "处理成功"; // 成功
	public static final String KL_FAIL = "处理失败"; // 失败
	public static final String KL_DEALING = "处理中"; // 处理中

	/**
	 * 页面列表排序规则
	 * ——退回重放，升序
	 * ——回款日期，升序
	 */
	public static final String BACK_MONEY_DAY_ASC = "reback_flag.asc,back_money_day.asc";
	/**
	 * 全程留痕列表排序规则——操作时间，降序
	 */
	public static final String OPERATE_TIME_DESC = "operate_time.desc";
	/**
	 * 待回款申请明细，导出文件名
	 */
	public static final String TO_APPLY = "债权到期回款明细";
	/**
	 * 待金账户回款申请明细，导出文件名
	 */
	public static final String TO_JZH_APPLY = "金账户回款明细";
	/**
	 * 待回款申请确认明细，导出文件名
	 */
	public static final String TO_APPLYCONFIRM = "债权到期回款明细";
	/**
	 * 待金账户回款申请确认明细，导出文件名
	 */
	public static final String TO_JZH_APPLYCONFIRM = "金账户回款明细";
	/**
	 * 已回款，导出文件名
	 */
	public static final String HISTORY = "已回款列表";
	/**
	 * 待回款审批导出，导出文件名
	 */
	public static final String TO_APPROVAL = "待回款列表";
	/**
	 * 待回款审批金账户导出，导出文件名
	 */
	public static final String TO_JZH_APPROVAL = "待金账户回款列表";
	/**
	 * 执行回款富友导出，导出文件名
	 */
	public static final String FYDC = "AP01";
	/**
	 * 执行回款中金导出，导出文件名
	 */
	public static final String ZJDC = "001572_F";
	/**
	 * 执行回款金账户导出，导出文件名
	 */
	public static final String JZHDC = "PW03";
	/**
	 * 执行回款网银导出，导出文件名
	 */
	public static final String WYDC = "网银回款列表";
	/**
	 * 执行回款卡联导出，导出文件名
	 */
	public static final String KLDC = "批量代付提交";
	/**
	 * 执行回款通联导出，导出文件名
	 */
	public static final String TLDC = "通联回款列表";
	/**
	 * 待回款确认列表，导出文件名
	 */
	public static final String TO_CONFIRM = "待回款确认列表";
	/**
	 * 导出文件笔数
	 */
	public static Map<String,Integer> splitNum = new HashMap<String,Integer>();
	
	/**
	 * 回款申请列表所包含的状态
	 */
	public static List<String> APPLY_STATUS_LIST = new ArrayList<String>();
	/**
	 * 回款申请确认列表所包含的状态
	 */
	public static List<String> APPLY_CONFIRM_STATUS_LIST = new ArrayList<String>();
	/**
	 * 回款补息列表所包含的状态
	 */
	public static List<String> SUPPLEMENT_STATUS_LIST = new ArrayList<String>();
	/**
	 * 待回款列表所包含的状态
	 */
	public static List<String> EXECUTE_STATUS_LIST = new ArrayList<String>();
	/**
	 * 回款申请列表所包含的状态
	 */
	public static List<String> WORKING_STATUS_LIST = new ArrayList<String>();
	/**
	 * 读取配置文件
	 */
	static{
		splitNum.put(BackMoneyPlat.FYPT.value, 499);
		splitNum.put(BackMoneyPlat.ZJPT.value, 499);
		splitNum.put(BackMoneyPlat.TL.value, 499);
		splitNum.put(BackMoneyPlat.JZH.value, 499);
		splitNum.put(BackMoneyPlat.WY.value, 499);
		splitNum.put(BackMoneyPlat.KL.value, 999);
		
		// 回款申请确认列表所包含的状态
		APPLY_CONFIRM_STATUS_LIST.add(BackState.DHKSQQR.value);
		APPLY_CONFIRM_STATUS_LIST.add(BackState.DHKSPTH.value);
		APPLY_CONFIRM_STATUS_LIST.add(BackState.DHKTH.value);
		APPLY_CONFIRM_STATUS_LIST.add(BackState.DHKQRTH.value);
		APPLY_CONFIRM_STATUS_LIST.add(BackState.YHKTH.value);
		APPLY_CONFIRM_STATUS_LIST.add(BackState.HKBXTH.value);
		
		// 回款补息列表所包含的状态
		SUPPLEMENT_STATUS_LIST.add(BackState.HKBX.value);
		
		// 回款申请列表所包含的状态
		APPLY_STATUS_LIST.add(BackState.DHKSQ.value);
		APPLY_STATUS_LIST.add(BackState.DHKSQQRTH.value);
		
		// 待回款列表所包含的状态
		EXECUTE_STATUS_LIST.add(BackState.DHK.value);
		EXECUTE_STATUS_LIST.add(BackState.BFHKCG.value);
		
		//在职状态
		WORKING_STATUS_LIST.add(WorkingState.LZ.value);
		WORKING_STATUS_LIST.add(WorkingState.ZZ.value);
	}

}
