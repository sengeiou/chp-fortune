package com.creditharmony.fortune.back.interest.contants;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 全局常量
 * @Class Name GlobalConstant 
 * @author 李志伟
 * @Create In 2015年12月3日
 */
public final class GlobalConstant implements Serializable{
	
	private static final long serialVersionUID = 2093926610946665346L;
	
	/**
	 * 日期格式
	 */
	public static final String YYYY_MM_DD = "yyyy-MM-dd";
	public static final String yyyy_mm_dd = "yyyy-MM-dd HH:mm:ss";
	public static final String yyyyMMdd = "yyyyMMddHHmmss";
	
	// 上传文件成功识别码、线上回息金账户/非金账户回盘结果成功返回码
	public static final String RETURN_NO = "0000";
	
	/**
	 * 全程留痕插入使用值
	 */
	// 线上回息标识
	public static final String JZH = "金账户";
	public static final String TL = "通联";
	public static final String ZJ = "中金";
	//----------操作详情--------------------
	public static final String UPDATE_ST = "回息状态变更为:";
	public static final String UPDATE_MN = "回息金额变更为:";
	public static final String BACK_RESULT = "回盘结果变更为:";
	public static final String SUCCESS_MONEY = "回息成功金额变更为:";
	public static final String FAIL_MONEY = "回息失败金额变更为:";
	public static final String BACK_MONEY = "应回息金额为:";
	public static final String UPDATE_MONEY = "上传回息金额";
	public static final String SEND_MONEY = "发送金额:";
	public static final String FRIST = "单次";
	public static final String BRANCH = "批量";
	public static final String BACK = "退回";
	public static final String MQ_GIVE = "MQ推送";
	public static final String RESULT = "回盘结果";
	//gaoxu  2017-3-24 13:26:54   上传是否回息字段更新
	public static final String RETURN_UPDATE_ISINTEREST = "上传是否回息";
	public static final String RETURN_UPDATE_IS = "是否回息变更为:";
	//----------操作类型--------------------
	public static final String LINE = "线下回息";
	public static final String ONLINE = "线上回息";
	public static final String COMMIT = "提交";
	public static final String RETURN = "退回";
	public static final String UPD = "上传";
	
	/*
	 *  比较大小返回值
	 */
	// 大于
	public static final int GR = 1;
	// 等于
	public static final int EQ = 0;
	// 小于
	public static final int LE = -1;
	
	// 回息列表默认金额
	public static final String MONEY = "0.00";
	
	/*
	 * 中金交易状态__回盘结果
	 */
	// 正在处理
	public static final String PROCESSING = "20-正在处理";
	public static final String CHULIZHONG = "10-New";
	// 代付成功
	public static final String PAYSUCCESS = "30-代付成功";
	// 代付失败
	public static final String PAYFAIL = "40-代付失败";
	
	// 是否回息__初始化产品下拉列表使用
	//public static final String BACKIN = "1";// 回息
	
	// 富友回盘结果__是否退票
	public static final String REFUND_YES = "是";
	public static final String REFUND_NO = "否";
	
	// 通联回盘结果
	public static final String ChuLiChengGong = "处理成功";
	public static final String ChuLiShiBai = "处理失败";
	public static final String ChuLiZhong = "处理中";
	// 通联平台限额
	public static final BigDecimal TL_Amount = new BigDecimal("3000000.00");
	
	// 历史留痕
	public static final String ZJ_DHX_RESULT = "待回息列表上传中金回盘结果";
	public static final String TL_DHX_RESULT = "待回息列表上传通联回盘结果";
	public static final String FY_DHX_RESULT = "待回息列表上传富友回盘结果";
	public static final String WY_DHX_RESULT = "待回息列表上传网银回盘结果";
	public static final String JZH_DHX_RESULT= "待回息列表上传金账户回盘结果";
	//-----------------------------------------------------------------------
	
	public static final String ZJ_DHXQR_RESULT = "待回息确认列表上传中金回盘结果";
	public static final String TL_DHXQR_RESULT = "待回息确认列表上传通联回盘结果";
	public static final String FY_DHXQR_RESULT = "待回息确认列表上传富友回盘结果";
	public static final String WY_DHXQR_RESULT = "待回息确认列表上传网银回盘结果";
	public static final String JZH_DHXQR_RESULT= "待回息确认列表上传金账户回盘结果";
	
	//------------------------------------------------------------------------
	public static final String DHXSQ_LIST = "待回息申请列表";
	public static final String DHXSQQR_LIST = "待回息申请确认列表";
	public static final String DHXSP_LIST = "待息审批列表";
	public static final String DHX_LIST = "待回息列表";
	public static final String DHXQR_LIST = "待回息确认列表";
	public static final String YHX_LIST = "已回息列表";
	public static final String RETURN_DHXSQ_LIST = "到期待回息申请列表";
	public static final String RETURN_DHXSQQR_LIST = "到期待回息申请确认列表";
	//=================================================================
	public static final String DHXSQ_PAGE = "待回息申请详情页";
	public static final String DHXSQQR_PAGE = "待回息申请确认详情页";
	public static final String DHXSP_PAGE = "待回息审批详情页";
	public static final String DHX_PAGE = "待回息详情页";
	public static final String DHXQR_PAGE = "待回息确认详情页";
	public static final String YHX_PAGE = "已回息详情页";
	public static final String DAO = "到";
	public static final String LEND_STATE_UPDATE = "完结状态变更为未完结";
	public static final String RETURN_DHXSQ_PAGE = "到期待回息申请详情页";
	public static final String RETURN_DHXSQQR_PAGE = "到期待回息申请确认详情页";
	// 通联单日限额
	public static BigDecimal daysMaxMoney = new BigDecimal(3000000.00);
 	// 通联单笔限额
	public static BigDecimal limitMaxMoney = new BigDecimal(200000.00);
	// 通联导出每个文件笔数
	public static int fileNo = 500;
	// 一条数据拆分最大数量
	public static int maxSplitNum = 15;
	
	// 富友单文件限额
	public static BigDecimal FY_FILE_MAX_MONEY = new BigDecimal(10000000.00);
	// 富友单笔限额
	public static BigDecimal FY_LIMIT_MAX_MONEY = new BigDecimal(5000000.00);
	// 100倍
	public static BigDecimal ONE = new BigDecimal(100);
	// 导出文件占位符
	public static String ERROR = "回息金额为0";
	public static String OUT_OF_LIMIT = "回息金额超出单日限额";
	
	// 卡联支付导出每个文件限制笔数
	public static int KALIAN_FILE_LIMIT = 1000; 
	// 卡联业务类型
	public static final String FOR_PRIVATE = "00";
	// 卡联单卡/单笔/单日限额
	public static BigDecimal KA_LIAN_LIMIT = new BigDecimal("200000.00");
} 