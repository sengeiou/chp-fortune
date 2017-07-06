package com.creditharmony.fortune.redemption.constant;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.creditharmony.core.fortune.type.ContractVesion;
import com.creditharmony.core.fortune.type.ProductCode;
import com.creditharmony.fortune.redemption.utils.RedeemRate;

/**
 * 赎回相关常量
 * 
 * @Class Name constant
 * @author 陈广鹏
 * @Create In 2015年11月30日
 */
public class RedeemConstant {

	/**
	 * 提前赎回flow_type
	 */
	public static final String REDT_FLOW_TYPE = "CF0005";
	/**
	 * 出借申请flow_type
	 */
	public static final String APPLY_FLOW_TYPE = "CF0007";
	/**
	 * 审批查看列表排序规则
	 */
	public static final String APPROVAL_ORDER_RULE = "checkTime.desc";
	/**
	 * 已办列表排序规则
	 */
	public static final String HISTORY_ORDER_RULE = "check_time.desc";
	public static final String FILENAME_REDEMPTION_APPLY = "提前赎回列表";
	public static final String FILENAME_REDEMPTION_APPROVAL = "提前赎回审批通过列表";
	/**
	 * 降息时间为2016-01-05，信和通、月息通在这个时间前后的的计算公式不同
	 */
	public static final String DEVIDE_DATE = "2016-01-05";
	/**
	 * 出借日期在时间 2016-04-01 前后的出借，下载赎回申请表内容有区别
	 */
	public static final String DOWNLOAD_DEVIDE_DATE = "2016-04-01";
	/**
	 * 赎回审批待办工作队列
	 */
	public static final String CF_EARLY_REDEMPTION = "CF_EARLY_REDEMPTION";
	/**
	 * 赎回审批退回工作队列
	 */
	public static final String CF_ACCOUNT_APPROVE = "CF_EL_RDPT_REAPPLY";
	/**
	 * 日期格式
	 */
	public static final String YYYY_MM_DD = "yyyy-MM-dd";
	/**
	 * 金额格式
	 */
	public static final String MONEY_FORMAT = "0.00";
	/**
	 * 非期限类选择赎回费率对应字符串
	 */
	public static final String FQXL_STR = "tz_notermpro_paytime";
	/**
	 * 期限类选择赎回费率对应字符串
	 */
	public static final String QXL_STR = "tz_termpro_paytime";
	/**
	 * 1.6.1合同版本对应的回款期限字符串
	 */
	public static final String WITHOUT_LIMIT_STR = "tz_without_limit_paytime";
	/**
	 * 期限类选择赎回费率(新标准)对应字符串
	 */
	public static final String QXL_STR_NEW = "tz_termpro_paytime_new";
	/**
	 * 没有部分赎回限制的合同版本
	 */
	public static List<String> CONTRACT_EDT_LIST;
	/**
	 * 允许部分赎回的最低出借金额
	 */
	public static final String LOW_LIMIT = "50000";
	/**
	 * 出借日期为2016-01-05前，信和通月利率
	 */
	public static final String XHT_MONTH_RATE = "0.01";
	/**
	 * 合同版本为（1.3、1.3续投、2.1、3.2）期限类产品赎回时计算年利率按 5.8%
	 */
	public static final BigDecimal QXL_INTEREST_RATE = new BigDecimal("5.8");
	
	/**
	 * 降息标准时间
	 */
	public static final String STANDARD_DATE = "2016/04/01";

	
	/**
	 * 付款方式为赎回内转的出借在90天内，不允许提前赎回
	 */
	public static final int LIMIT_DAY = 90;

	/**
	 * 该列表中的合同版本， 赎回申请时，选择回款期限显示规则：期限类回款期限(新)（4%、3%、2%）
	 */
	public static List<String> QXL_NEW_LIST;
	/**
	 * 该列表中的合同版本， 赎回申请时，选择回款期限显示规则：期限类回款期限（2%、1%、0%）
	 */
	public static List<String> QXL_LIST;
	/**
	 * 有全部赎回限制的产品Code列表
	 */
	public static List<String> PRODUCT_CODE_LIST;
	/**
	 * 计算：第一类计算产品
	 */
	public static List<String> FIRST_KIND_PRODUCTS;
	/**
	 * 计算：第一类计算合同版本
	 */
	public static List<String> FIRST_KIND_EDITION;
	/**
	 * 计算：第二类计算产品
	 */
	public static List<String> SECOND_KIND_PRODUCTS;
	/**
	 * 计算：第二类计算合同版本
	 */
	public static List<String> SECOND_KIND_EDITION;
	/**
	 * 赎回时需要进行特殊流程的合同版本
	 */
	public static List<String> SPECIAL_FLOW_EDITION;
	
	
	/**
	 * 降息前利率
	 */
	public static List<String> PRE_RATES;  
	
	/**
	 * 降息后利率
	 */
	public static List<String> AFT_RATES;


	static {
		// 没有部分赎回限制的合同版本列表初始化
		CONTRACT_EDT_LIST = new ArrayList<String>();
		CONTRACT_EDT_LIST.add(ContractVesion.D12BB.value);
		CONTRACT_EDT_LIST.add(ContractVesion.D13BB.value);
		CONTRACT_EDT_LIST.add(ContractVesion.D13XTBB.value);
		CONTRACT_EDT_LIST.add(ContractVesion.D30BB.value);
		CONTRACT_EDT_LIST.add(ContractVesion.D31BB.value);
		CONTRACT_EDT_LIST.add(ContractVesion.D32BB.value);

		// 期限类回款期限(新)（4%、3%、2%）的合同版本列表初始化
		QXL_NEW_LIST = new ArrayList<String>();
		QXL_NEW_LIST.add(ContractVesion.D13BB.value);
		QXL_NEW_LIST.add(ContractVesion.D13XTBB.value);
		QXL_NEW_LIST.add(ContractVesion.D21BB.value);
		QXL_NEW_LIST.add(ContractVesion.D32BB.value);

		// 期限类回款期限（2%、1%、0%）的合同版本列表初始化
		QXL_LIST = new ArrayList<String>();
		QXL_LIST.add(ContractVesion.D12BB.value);
		QXL_LIST.add(ContractVesion.D20BB.value);
		QXL_LIST.add(ContractVesion.D30BB.value);
		QXL_LIST.add(ContractVesion.D31BB.value);

		// 初始化产品列表
		PRODUCT_CODE_LIST = new ArrayList<String>();
		PRODUCT_CODE_LIST.add(ProductCode.XHT.value);
		PRODUCT_CODE_LIST.add(ProductCode.YXT.value);
		PRODUCT_CODE_LIST.add(ProductCode.JDY.value);
		PRODUCT_CODE_LIST.add(ProductCode.SJY.value);
		PRODUCT_CODE_LIST.add(ProductCode.XHB.value);
		PRODUCT_CODE_LIST.add(ProductCode.XHBA.value);
		PRODUCT_CODE_LIST.add(ProductCode.XHBB.value);
		PRODUCT_CODE_LIST.add(ProductCode.XHBC.value);

		// 第一类计算产品列表:季度盈、双季盈、年年金、年年盈、信和宝、金信宝、金信盈
		FIRST_KIND_PRODUCTS = new ArrayList<String>();
		FIRST_KIND_PRODUCTS.add(ProductCode.JDY.value);
		FIRST_KIND_PRODUCTS.add(ProductCode.SJY.value);
		FIRST_KIND_PRODUCTS.add(ProductCode.NNJ.value);
		FIRST_KIND_PRODUCTS.add(ProductCode.NNY.value);
		FIRST_KIND_PRODUCTS.add(ProductCode.XHB.value);
		FIRST_KIND_PRODUCTS.add(ProductCode.JXB.value);
		FIRST_KIND_PRODUCTS.add(ProductCode.JXY.value);
		
		// 第一类计算合同版本：1.3；1.3续投；2.1；3.2
		FIRST_KIND_EDITION = new ArrayList<String>();
		FIRST_KIND_EDITION.add(ContractVesion.D13BB.value);
		FIRST_KIND_EDITION.add(ContractVesion.D13XTBB.value);
		FIRST_KIND_EDITION.add(ContractVesion.D21BB.value);
		FIRST_KIND_EDITION.add(ContractVesion.D32BB.value);

		// 第二类计算产品列表:季度盈、双季盈、年年金、年年盈、信和宝、信和宝abc、信和年聚、金信宝、金信盈
		SECOND_KIND_PRODUCTS = new ArrayList<String>();
		SECOND_KIND_PRODUCTS.add(ProductCode.JDY.value);
		SECOND_KIND_PRODUCTS.add(ProductCode.SJY.value);
		SECOND_KIND_PRODUCTS.add(ProductCode.NNJ.value);
		SECOND_KIND_PRODUCTS.add(ProductCode.NNY.value);
		SECOND_KIND_PRODUCTS.add(ProductCode.XHB.value);
		SECOND_KIND_PRODUCTS.add(ProductCode.XHBA.value);
		SECOND_KIND_PRODUCTS.add(ProductCode.XHBB.value);
		SECOND_KIND_PRODUCTS.add(ProductCode.XHBC.value);
		SECOND_KIND_PRODUCTS.add(ProductCode.XHNJ.value);
		SECOND_KIND_PRODUCTS.add(ProductCode.JXB.value);
		SECOND_KIND_PRODUCTS.add(ProductCode.JXY.value);
		
		// 第二类计算合同版本：1.2；1.4；1.4.1；1.5；1.6；2.0；2.2；2.3；3.0；3.1；
		SECOND_KIND_EDITION = new ArrayList<String>();
		SECOND_KIND_EDITION.add(ContractVesion.D12BB.value);
		SECOND_KIND_EDITION.add(ContractVesion.D14BB.value);
		SECOND_KIND_EDITION.add(ContractVesion.D141BB.value);
		SECOND_KIND_EDITION.add(ContractVesion.D15BB.value);
		SECOND_KIND_EDITION.add(ContractVesion.D16BB.value);
		SECOND_KIND_EDITION.add(ContractVesion.D161BB.value);
		SECOND_KIND_EDITION.add(ContractVesion.D17BB.value);
		SECOND_KIND_EDITION.add(ContractVesion.D20BB.value);
		SECOND_KIND_EDITION.add(ContractVesion.D22BB.value);
		SECOND_KIND_EDITION.add(ContractVesion.D23BB.value);
		SECOND_KIND_EDITION.add(ContractVesion.D24BB.value);
		SECOND_KIND_EDITION.add(ContractVesion.D30BB.value);
		SECOND_KIND_EDITION.add(ContractVesion.D31BB.value);
		
		// 赎回时需要进行特殊流程的合同版本
		SPECIAL_FLOW_EDITION = new ArrayList<String>();
		SPECIAL_FLOW_EDITION.add(ContractVesion.D161BB.value);
		SPECIAL_FLOW_EDITION.add(ContractVesion.D17BB.value);
		
		
		//降息前利率
		PRE_RATES = new ArrayList<String>();
		PRE_RATES.add(RedeemRate.PRE_0.getRate());
		PRE_RATES.add(RedeemRate.PRE_1.getRate());
		PRE_RATES.add(RedeemRate.PRE_2.getRate());
		PRE_RATES.add(RedeemRate.PRE_3.getRate());
		PRE_RATES.add(RedeemRate.PRE_4.getRate());
		PRE_RATES.add(RedeemRate.PRE_5.getRate());
		PRE_RATES.add(RedeemRate.PRE_6.getRate());
		PRE_RATES.add(RedeemRate.PRE_7.getRate());
		PRE_RATES.add(RedeemRate.PRE_8.getRate());
		PRE_RATES.add(RedeemRate.PRE_9.getRate());
		PRE_RATES.add(RedeemRate.PRE_10.getRate());
		PRE_RATES.add(RedeemRate.PRE_11.getRate());
		PRE_RATES.add(RedeemRate.PRE_12.getRate());
		PRE_RATES.add(RedeemRate.PRE_13.getRate());
		PRE_RATES.add(RedeemRate.PRE_14.getRate());
		PRE_RATES.add(RedeemRate.PRE_15.getRate());
		PRE_RATES.add(RedeemRate.PRE_16.getRate());
		PRE_RATES.add(RedeemRate.PRE_17.getRate());
		PRE_RATES.add(RedeemRate.PRE_18.getRate());
		PRE_RATES.add(RedeemRate.PRE_19.getRate());
		PRE_RATES.add(RedeemRate.PRE_20.getRate());
		PRE_RATES.add(RedeemRate.PRE_21.getRate());
		PRE_RATES.add(RedeemRate.PRE_22.getRate());
		PRE_RATES.add(RedeemRate.PRE_23.getRate());
		PRE_RATES.add(RedeemRate.PRE_24.getRate());
		//降息后利率
		AFT_RATES = new ArrayList<String>();
		AFT_RATES.add(RedeemRate.AFT_0.getRate());
		AFT_RATES.add(RedeemRate.AFT_1.getRate());
		AFT_RATES.add(RedeemRate.AFT_2.getRate());
		AFT_RATES.add(RedeemRate.AFT_3.getRate());
		AFT_RATES.add(RedeemRate.AFT_4.getRate());
		AFT_RATES.add(RedeemRate.AFT_5.getRate());
		AFT_RATES.add(RedeemRate.AFT_6.getRate());
		AFT_RATES.add(RedeemRate.AFT_7.getRate());
		AFT_RATES.add(RedeemRate.AFT_8.getRate());
		AFT_RATES.add(RedeemRate.AFT_9.getRate());
		AFT_RATES.add(RedeemRate.AFT_10.getRate());
		AFT_RATES.add(RedeemRate.AFT_11.getRate());
		AFT_RATES.add(RedeemRate.AFT_12.getRate());
		AFT_RATES.add(RedeemRate.AFT_13.getRate());
		AFT_RATES.add(RedeemRate.AFT_14.getRate());
		AFT_RATES.add(RedeemRate.AFT_15.getRate());
		AFT_RATES.add(RedeemRate.AFT_16.getRate());
		AFT_RATES.add(RedeemRate.AFT_17.getRate());
		AFT_RATES.add(RedeemRate.AFT_18.getRate());
		AFT_RATES.add(RedeemRate.AFT_19.getRate());
		AFT_RATES.add(RedeemRate.AFT_20.getRate());
		AFT_RATES.add(RedeemRate.AFT_21.getRate());
		AFT_RATES.add(RedeemRate.AFT_22.getRate());
		AFT_RATES.add(RedeemRate.AFT_23.getRate());
		AFT_RATES.add(RedeemRate.AFT_24.getRate());
	}

}
