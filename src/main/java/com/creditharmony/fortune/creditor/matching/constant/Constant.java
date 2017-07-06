
package com.creditharmony.fortune.creditor.matching.constant;

import java.io.IOException;
import java.util.Properties;



/**
 * @Class Name Constant
 * @author 胡体勇
 * @Create In 2015年12月9日
 */
public class Constant {
	public final static String OK = "OK";// 修改或插入数据返回成功标志
	public final static String NG = "NG";// 修改或插入数据返回失败标志
	public final static String REPEAT = "REPEAT";// 重复时常量
	public final static Integer REPEAT_NUMBER = 2;// 查询重复时返回值
	public final static Integer SIZE = 2;// 设置数组长度
    /**配置文件名*/
	private final static String FILE_NAME="/email-conf.xml.properties";
	/**邮件发送模板ID_KEY*/
	private final static String EMAIL_TEMPEMPLT_PROTOCOL_ID_KEY="creditorProtocolId";
	/**邮件发送模板ID_VALUE*/
	public static String EMAIL_TEMPEMPLT_PROTOCOL_ID_VALUE;
	/**发送邮件的主题_KEY*/
	private final static String SEND_MAIL_SUBJECT_PROTOCOL_KEY="creditorTheme";
	/**发送邮件的主题_VALUE*/
	public static String SEND_MAIL_SUBJECT_PROTOCOL_VALUE;
	/**设置匹配金额为0*/
	public final static int MATCHING_CREDITOR_MATCHING_MONEY = 0;
	/**债权交易状态 0:暂存**/
	public final static String TRADE_CREDIT_STATUS_ZC = "0"; 
	
	/** **/
	public final static String PHASE_REPAY_SIGN = "0"; 
	
	/** 邮件发送标识 不发送**/
	public final static String SEND_FLAG_NO = "0";
	
	/** 邮件发送标识 发送**/
	public final static String SEND_FLAG_YES = "1";
	
	/** 文件合成标志 **/
	public final static String FILE_MAKE_FROM_HC ="1";
	/**
	 * 导出的列表标题
	 */
	public final static String CREDITOR_SEND_TITLE = "债权发送列表";
	public final static String CREDITOR_SEND_ERROR = "债权发送列表导出失败!";
	
	/**
	 * BigDecimal 对象初始化默认值4
	 */
	public static final Double BIGDECIMAL_OBJECT_DEFAULT = 0.0;
	
	/**
	 * 月满盈可用债权池 是否可用，1可用，0不可用，2冻结 
	 */
	public  static final String DICT_LOAN_FREE_FLAG_YES ="1";
	/**
	 * 匹配类型  非月满盈首期第一次匹配
	 */
	public  static final String PP_TYPE_FYMY_FRISRT_ONE ="1";
	/**
	 * 匹配类型  非月满盈首期第二次匹配
	 */
	public  static final String PP_TYPE_FYMY_FRISRT_TWO ="2";
	
	/**
	 * 匹配类型  非月满盈次期第一次匹配
	 */
	public  static final String PP_TYPE_FYMY_CQ_ONE ="11";
	
	/**
	 * 匹配类型  非月满盈次期第二次匹配
	 */
	public  static final String PP_TYPE_FYMY_CQ_TWO ="12";
	
	/**
	 * 匹配类型  非月满盈次期第二次匹配
	 */
	public  static final String PP_TYPE_FYMY_CQ_THROW ="13";
	
	/**
	 * 可用标识
	 */
	public static final String CREDITORPER_UPPER_KY = "1";
	
	/**
	 * 不可用标识
	 */
	public static final String CREDITORPER_UPPER_FKY = "0";
	
	// 操作类型
	public static final String COMIT = "提交";
	// 全程留痕插入值
	public static final String INSERT_ADD = "匹配成功待推荐编号为:";
	/**
	 * 读取配置文件
	 */
	static{
		Properties prop = new Properties();
		try {
			prop.load(Constant.class.getResourceAsStream(FILE_NAME));
			EMAIL_TEMPEMPLT_PROTOCOL_ID_VALUE=prop.getProperty(EMAIL_TEMPEMPLT_PROTOCOL_ID_KEY);
			SEND_MAIL_SUBJECT_PROTOCOL_VALUE=prop.getProperty(SEND_MAIL_SUBJECT_PROTOCOL_KEY);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static final Integer DEF_COUNT = 800;// 默认分配量
	public static final Integer DEF_AGAIN_COUNT_PERCENT = 10;// 默认再次分配数量相对于总量的百分比
	
	public static final String CREDIT_DONE = "1";// 完成单
	public static final String CREDIT_DROP = "2";// 弃单
	public static final String CREDIT_CHANGE = "3";// 换单
	public static final String CREDIT_DISTRIBUTED = "4";// 派单
	
	public static final String SHOW_HIS_FLG_YES = "0"; // 显示历史标识
	public static final String SHOW_HIS_FLG_NO = "1"; // 不显示历史标识
	
	/**
	 * 月满盈默认天数
	 */
	public static final Integer BORROWDAYSSURPLU_DEFALUT =15;
}

