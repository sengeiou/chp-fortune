package com.creditharmony.fortune.back.priority.constant;

import java.util.ArrayList;
import java.util.List;

import com.creditharmony.core.fortune.type.PriorityBackState;



/**
 * 优先回款相关常量
 * 
 * @Class Name PbmConstant
 * @author 郭强
 * @Create In 2017年3月27日
 */
public class PbmConstant {

	/**
	 * 页面列表排序规则
	 * ——退回重放，升序
	 * ——修改日期，降序
	 */
	public static final String BACK_MONEY_DAY_ASC = "rebackFlag.asc,checkTime.desc";
	
	/**
	 * 待审批排序规则
	 */
	public static final String PCT = "create_time.desc";
	
	/**
	 * 被退回排序规则
	 */
	public static final String THCF = "reback_flag.desc,create_time.desc,";
	
	/**
	 * 列表标识
	 */
	public static final String APPLY_LIST = "apply";
	/**
	 * 优先回款审批列表所包含的状态
	 */
	public static List<String> PRIORITY_EXAMINE_STATUS_LIST = new ArrayList<String>();
	/**
	 * 优先回款审批(被退回)列表所包含的状态
	 */
	public static List<String> PRIORITY_BACK_STATUS_LIST = new ArrayList<String>();
	
	static{
		// 回款申请确认列表所包含的状态
		PRIORITY_EXAMINE_STATUS_LIST.add(PriorityBackState.DSP.value);
		
		// 优先回款审批(被退回)列表所包含的状态
		PRIORITY_BACK_STATUS_LIST.add(PriorityBackState.SPWTG.value);
	}
}
