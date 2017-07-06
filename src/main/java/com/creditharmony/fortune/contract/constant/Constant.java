package com.creditharmony.fortune.contract.constant;

import java.util.ArrayList;
import java.util.List;

public interface Constant {
	
	public static final String PARAMETER_ERROR = "-1";//错误参数
	
	public static final Integer PAGE_SIZE = 10;// 分页大小
	
	public static final String CONT_SIGN_STATUS_HAS = "0";//未签收
	
	public static final String CONT_SIGN_STATUS_NO = "1";//已签收
	
	public static final String DICT_CONT_FILE_STATUS_YES = "已归档";//归档状态
	
	public static final String DICT_CONT_FILE_STATUS_NO = "未归档";//归档状态
	
	public static final String DICT_CONT_BELONG_YES = "1";//门店库存
	
	public static final String DICT_CONT_BELONG_NO = "2";//理财经理
	
	public static final String CONTRACT_APPLY_ORDER_RULE = "apply_day.desc";	// 已办列表排序规则
	public static final String CONTRACT_DISTRIBUTE_ORDER_RULE = "apply_day.desc";  // 派发列表排序规则
	public static final String CONTRACT_ORDER_RULE = "create_time.desc";  // 合同列表排序规则
	public static final String CONTRACT_CHANAGE_ORDER_RULE = "change_day.desc";  // 合同变更申请列表排序规则
	
	
	public static final String DATE_YYYYMMDDMMSS = "yyyyMMddHHmmss";//日期格式化
	public static final String DATE_YYYY_MM_DD = "yyyy-MM-dd";//日期格式化
	public static final String DATE_YYYYMMDD = "yyyyMMdd";//日期格式化
	
	
	public static final String CONTRACT_EXCEL_PREFIX = "合同申请 ";//合同申请文件前缀
	
	public static final String CONTRACT_EXCEL_SUFFIX = ".xlsx";//合同文件申请后缀
	
	public static final String CONTRACT_DISTRIBUTE_EXCEL_PREFIX = "合同派发 ";//合同派发文件前缀
	
	public static final String CONTRACT_CHANGE_PREFIX = "合同查询及状态变更导出 ";//合同申请文件前缀
	
	public static final String CONTRACT_CHANGE_APPLY_PREFIX = "合同状态变更导出 ";//合同状态变更导出前缀
	
	public static final String CONTRACT_CHANGE_STATISTICS_PREFIX = "合同统计导出 ";//合同统计导出
	
	public static final String DICT_CONT_SOURCE="0"; //总部下发
	
	public static final int length = 12;//合同长度
	
	public static final String prefix = "88";//合同编号前缀
	
	public static final String suffix = "88";//合同编号后缀
	
	public static final int increment_index = 8; //自增区间起始下标
	
	public static final int increment_last_index = 10; //自增区间结束下标
	
	public static final String placeholder = "0"; //自增区间占位符
	
	public static final List<String> exceptionVersionList = new ArrayList<String>(){//异常合同版本集合
		private static final long serialVersionUID = -3751184373782801334L;
	{
		//add(ContractVesion.D14BB.value);
	}};
	
    public static final int NUM_ZERO = 0;//数字零值
	
	public static final int NUM_ONE = 1;//数字一
	
	public static final float USELESS_LOWEST_POINT = (float)0.05;//作废率标准点
	public static final float USELESS_PRICE = 20;//作废单价
	public static final float LOSS_PRICE = 200;//遗失单价
	
	public static final String DISTRIBUT_MAIN_ORDER = "1";//主派发记录1
	public static final String DISTRIBUT_SUBSET_ORDER = "2";//子派发记录 2 
	
	public static final String CONTRACT_MODULE = "CONTRACT";//权限模块
}
