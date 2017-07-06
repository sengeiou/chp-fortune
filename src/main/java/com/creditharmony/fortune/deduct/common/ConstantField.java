package com.creditharmony.fortune.deduct.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.creditharmony.core.dict.entity.Dict;
import com.creditharmony.fortune.utils.FortuneDictUtil;

/**
 * 导出
 * @Class Name ConstantField
 * @author 韩龙
 * @Create In 2016年4月18日
 */
public class ConstantField {
	
	private static final String deductPool = "com.creditharmony.fortune.deduct.dao.DeductApplyDao."; 
	
	public static Map<String,List<Dict>> deductDictMap = new HashMap<String,List<Dict>>();

	/**
	 * 获取数据字典
	 * 2016年3月26日
	 * By 韩龙
	 * @param model
	 */
	static{
		deductDictMap = FortuneDictUtil.getDicts(new String[]{"tz_pay_type","jk_deduct_plat","tz_trust_state","tz_lend_state"
				,"tz_deduct_plat","tz_open_bank","com_email_state","tz_deduct_state",
				"com_deduct_type","jk_prof_type","sex","tz_bill_state","com_card_type","tz_protocol_version","tz_back_state","com_certificate_type","tz_billtaken_mode","tz_marital_state"});
	}
	
	
	/**审批导出excel导出表头信息*/
	public final static String approve_title = 
		"财富中心,"+
		"客户姓名,"+
		"出借编号,"+
		"划扣日期,"+
		"首次出借日期,"+
		"出借金额,"+
		"划扣金额,"+
		"出借产品,"+
		"理财经理,"+
		"门店,"+
		"划扣行别,"+
		"付款方式,"+
		"划扣平台,"+
		"反馈结果";
	/**审批导出excel导出查询字段*/
	public final static String[][] approve_field = new String[][]{
		{"fortuneCenter",null},
		{"cust_name",null},
		{"apply_code",null},
		{"apply_deduct_date",null},
		{"apply_lend_date",null},
		{"apply_lend_money","fm_#000.00"},
		{"apply_deduct_money","fm_#000.00"},
		{"productCode",null},
		{"managerName",null},
		{"checkNode",null},
		{"accountBank","tz_open_bank"},
		{"applyPay","tz_pay_type"},
		{"dictApplyDeductType","tz_deduct_plat"}
		};
	/**审批导出excel导出sql的ID*/
	public final static String approve_sql = deductPool + "getDeductPoolExportModel";

	/**划扣失败导出excel导出表头信息*/
	public final static String deductFail_title = 
			"序号,"
			+ "客户姓名,"
			+ "出借金额,"
			+ "出借产品,"
			+ "客户经理,"
			+ "营业部,"
			+ "回盘结果,"
			+ "失败原因,"
			+ "出借银行,"
			+ "出借卡号,"
			+ "划扣平台,"
			+ "划扣日期,"
			+ "出借编号,"
			+ "分天标识,"
			+ "划扣成功金额,"
			+ "划扣失败金额";
	
	/**划扣失败导出excel导出查询字段*/
	public final static String[][] deductFail_field = new String[][]{
		{"no",null},
		{"customer_name",null},
		{"apply_lend_money","fm_#000.00"},
		{"product_name",null},
		{"managerName",null},
		{"orgName",null},
		{"dictBackResult","tz_deduct_state"},
		{"dictBackFailResult",null},
		{"bankId","tz_open_bank"},
		{"account_no",null},
		{"dictDeductPlatformId","tz_deduct_plat"},
		{"deductDate",null},
		{"lend_code",null},
		{"day_deduct_flag",null},
		{"deductReallyMoney","fm_#000.00"},
		{"deductFailMoney","fm_#000.00"},
		};
	/**审批导出excel导出sql的ID*/
	public final static String deductFail_sql = deductPool + "getDeductFailExportModel";
	
	
	/**划扣失败导出协议库excel导出表头信息*/
	public final static String deductFailProtocol_title = 
			"业务类型,"
			+ "客户姓名,"
			+ "手机号码,"
			+ "证件类型,"
			+ "证件号码,"
			+ "账号,"
			+ "账户属性,"
			+ "账户行别,"
			+ "是否语音回拨,"
			+ "备注";
	
	/**划扣失败导出协议库excel导出查询字段*/
	public final static String[][] deductFailProtocol_field = new String[][]{
		{"business_type","label_AC01"},
		{"customer_name",null},
		{"customer_mobilephone",null},
		{"dict_customer_cert_type","com_certificate_type"},
		{"customer_cert_num",null},
		{"account_no",null},
		{"accounttype","label_个人"},
		{"account_card_or_booklet",null},
		{"voice_dail","label_否"},
		{"remarks","label_客户协议库"}
		};
	
	/**划扣失败导出协议库excel导出sql的ID*/
	public final static String deductFailProtocol_sql = deductPool + "getDeductFailProtocolExportModel";
	
	/**已出借导出excel导出表头信息*/
	public final static String deductSuccess_title = 
			"序号,"
			+ "客户姓名,"
			+ "出借金额,"
			+ "划扣金额,"
			+ "出借产品,"
			+ "客户经理,"
			+ "营业部,"
			+ "回盘结果,"
//			+ "失败原因,"
			+ "出借银行,"
			+ "出借卡号,"
			+ "划扣平台,"
			+ "划扣日期,"
			+ "出借编号";
	
	/**已出借导出excel导出查询字段*/
	public final static String[][] deductSuccess_field = new String[][]{
		{"no",null},
		{"customer_name",null},
		{"apply_lend_money","fm_#000.00"},
		{"deductmoney","fm_#000.00"},
		{"product_name",null},
		{"managerName",null},
		{"orgName",null},
		{"dictBackResult","tz_deduct_state"},
//		{"dictBackFailResult",null},
		{"bankId","tz_open_bank"},
		{"account_no",null},
		{"dictDeductPlatformId","tz_deduct_plat"},
		{"deal_time",null},
		{"lend_code",null},
//		{"customer_mobilephone",null},
//		{"deductDate",null},
		};
	
	/**已出借导出excel导出sql的ID*/
	public final static String deductSuccess_sql = deductPool + "getDeductSuccessExportModel";
	
	/**已出借金帐户导出excel导出表头信息*/
	public final static String deductGold_title = 
			"序号,"
			+ "客户姓名,"
			+ "金帐号,"
			+ "出借金额,"
			+ "划扣金额,"
			+ "出借产品,"
			+ "客户经理,"
			+ "营业部,"
			+ "回盘结果,"
			+ "失败原因,"
			+ "出借银行,"
			+ "出借卡号,"
			+ "划扣平台,"
			+ "划扣日期,"
			+ "出借编号";
	
	/**已出借金帐户excel导出查询字段*/
	public final static String[][] deductGold_field = new String[][]{
		{"no",null},
		{"customer_name",null},
		{"goldAccountName","label_***********"},
		{"apply_lend_money","fm_#000.00"},
		{"deductmoney","fm_#000.00"},
		{"product_name",null},
		{"managerName",null},
		{"orgName",null},
		{"dictBackResult","tz_deduct_state"},
		{"dictBackFailResult",null},
		{"bankId","tz_open_bank"},
		{"account_no",null},
		{"dictDeductPlatformId","tz_deduct_plat"},
		{"deal_time",null},
		{"lend_code",null},
//		{"customer_mobilephone",null},
//		{"deductDate",null},
		};
	
	/**已出借金帐户excel导出sql的ID*/
	public final static String deductGold_sql = deductPool + "getDeductSuccessExportGoldModel";
	
	/**已出借回访表导出excel导出表头信息*/
	public final static String deductReturnVisit_title = 
			"客户姓名,"
			+ "性别,"
			+ "身份证号码,"
			+ "电子邮箱,"
			+ "电话,"
			+ "出借方式,"
			+ "出借金额,"
			+ "划扣日期,"
			+ "出借日期,"
			+ "产品到期日期,"
			+ "理财经理,"
			+ "团队经理,"
			+ "门店";
	
	/**已出借回访表导出excel导出查询字段*/
	public final static String[][] deductReturnVisit_field = new String[][]{
		{"customer_name",null},
		{"dict_customer_sex","sex"},
		{"customer_cert_num",null},
		{"customer_email",null},
		{"customerTel",null},
		{"productName","tz_pay_type"},
		{"apply_lend_money","fm_#000.00"},
		{"apply_deduct_day",null},
		{"apply_lend_day",null},
		{"apply_expire_day",null},
		{"managerName",null},
		{"managerId","team"},
		{"orgName",null}
		};
	
	/**已出借回访表导出excel导出sql的ID*/
	public final static String deductReturnVisit_sql = deductPool + "getDeductSuccessPayExportModel";
	
	/**金账户线下划导出excel导出表头信息*/
	public final static String deductGoldOffLine_title = 
			"序号,"
			+ "付款方登录名,"
			+ "付款方中文名称,"
			+ "付款资金来自冻结,"
			+ "收款方登录名,"
			+ "收款方中文名称,"
			+ "收款后立即冻结,"
			+ "交易金额,"
			+ "备注信息,"
			+ "预授权合同号";
	
	/**金账户线下划导出excel导出查询字段*/
	public final static String[][] deductGoldOffLine_field = new String[][]{
		{"index",null},
		{"loginName",null},
		{"payChineseName",null},
		{"paymentFreezing","label_" + Constant.getProperties.get("payment_freezing")},
		{"payeeLoginName","label_" + Constant.getProperties.get("gold_login_name")},
		{"payeeChineseName","label_" + Constant.getProperties.get("gold_chinese_name")},
		{"frozenAfterPayment","label_" + Constant.getProperties.get("frozen_after_payment")},
		{"transactionMoney","fm_#000.00"},
		{"remarks","comb_财富划扣#remarks"},
		{"preContractNo","label_ "}
		};
	
	/**金账户线下划导出excel导出sql的ID*/
	public final static String deductGoldOffLine_sql = deductPool + "getGoldAccounExportModel";
	
	/**富有划扣线下导出excel导出表头信息*/
	public final static String deductFuyouOffLine_title = 
			"序号,"
			+ "开户行,"
			+ "扣款人银行账号,"
			+ "户名,"
			+ "金额(单位:元),"
			+ "企业流水账号,"
			+ "备注,"
			+ "手机号,"
			+ "证件类型,"
			+ "证件号";
	
	/**富有划扣线下导出excel导出查询字段*/
	public final static String[][] deductFuyouOffLine_field = new String[][]{
		{"index","index"},
		{"account_bank","tz_open_bank"},
		{"account_no",null},
		{"account_name",null},
		{"deductMoney","defaultMoney"},
		{"lendCode","comb_财富划扣#lendCode"},
		{"fuRemarks","label_代收"},
		{"customer_mobilephone",null},
		{"dict_customer_cert_type","com_certificate_type"},
		{"customer_cert_num",null}
		};
	
	/**好易联划扣线下导出excel导出第一表头信息*/
	public final static String deductHylOffLine_title1 = 
			"代收付类型,"
			+ "商户ID,"
			+ "提交日期,"
			+ "总记录数,"
			+ "总金额,"
			+ "业务类型";

	/**好易联划扣线下导出excel导出第二表头信息*/
	public final static String deductHylOffLine_title = 
			"序号,"
			+ "银联网络用户编号,"
			+ "银行代码,"
			+ "账号类型,"
			+ "账号,"
			+ "账户名,"
			+ "开户行所在省,"
			+ "开户行所在市,"
			+ "开户行名称,"
			+ "帐户类型,"
			+ "金额,"
			+ "货币类型,"
			+ "协议号,"
			+ "协议用户编号,"
			+ "开户证件类型,"
			+ "证件号,"
			+ "手机号,"
			+ "自定义用户名,"
			+ "备注1,"
			+ "备注2,"
			+ "备注,"
			+ "反馈码,"
			+ "原因";
	
	/**好易联划扣线下导出excel导出查询字段*/
	public final static String[][] deductHylOffLine_field = new String[][]{
		{"index","index"},
		{"unionNetworkCode","label_ "},
		{"account_bank",null},
		{"accountNumberType","label_ "},
		{"account_no",null},
		{"account_name",null},
		{"account_addrprovince","province"},
		{"account_addrcity","city"},
		{"account_branch",null},
		{"accountType","label_ "},
		{"deductMoney","defaultMoney"},
		{"typeOfCurrency","label_ "},
		{"protocolNumber","label_ "},
		{"protocolUserNumber","label_ "},
		{"dict_customer_cert_type","com_certificate_type"},
		{"customer_cert_num",null},
		{"customer_mobilephone",null},
		{"customUserNumber","label_ "},
		{"remarks1","label_ "},
		{"remarks2","label_ "},
		{"lendCode","comb_财富划扣#lendCode"},// 备注haoRemarks
		{"returnCode","label_ "},
		{"reason","label_ "}
		};
	
	/**中金划扣线下导出excel导出表头信息*/
	public final static String deductZjOffLine_title = 
			"明细流水号,"
			+ "金额(元),"
			+ "银行名称,"
			+ "账户类型,"
			+ "账户名称,"
			+ "账户号码,"
			+ "分支行,"
			+ "省份,"
			+ "城市,"
			+ "结算标识,"
			+ "备注,"
			+ "证件类型,"
			+ "证件号码,"
			+ "手机号,"
			+ "电子邮箱,"
			+ "协议用户编号";
	
	/**中金划扣线下导出excel导出查询字段*/
	public final static String[][] deductZjOffLine_field = new String[][]{
		{"index","index"},
		{"deductMoney","defaultMoney"},
		{"account_bank","tz_open_bank"},
		{"accountNumberType","label_个人"},
		{"account_name",null},
		{"account_no",null},
		{"account_branch",null},
		{"account_addrprovince","province"},
		{"account_addrcity","city"},
		{"settlementMark","label_0001"},
//		{"zhongRemarks",null},
		{"lendCode","comb_财富划扣#lendCode"},
		{"dict_customer_cert_type","com_certificate_type"},
		{"customer_cert_num",null},
		{"customer_mobilephone","label_ "},//中金导出不需要手机号
		{"emailAddress","label_ "},
		{"protocolUserNumber","label_ "}
		};
	
	/**中金划扣线下导出Txt导出查询字段*/
	public final static String[][] deductZjOffLine_field_txt = new String[][]{
		{"index","index"},
		{"deductMoney","defaultMoney"},
		{"account_bank",null},
		{"accountNumberType","label_11"},
		{"account_name",null},
		{"account_no",null},
		{"account_branch",null},
		{"account_addrprovince","province"},
		{"account_addrcity","city"},
//		{"zhongRemarks",null},
		{"lendCode","comb_财富划扣#lendCode"},
		{"dict_customer_cert_type",null},
		{"customer_cert_num",null},
		{"customer_mobilephone","label_ "},//中金导出不需要手机号
		{"emailAddress","label_ "},
		{"protocolUserNumber","label_ "},
		{"settlementMark","label_0001"},
		};
	
	
	/**通联划扣线下导出excel导出表头信息*/
	public final static String deductTlOffLine_title = 
			"序号,"
			+ "用户编号,"
			+ "银行代码,"
			+ "账号类型,"
			+ "账号,"
			+ "户名,"
			+ "省,"
			+ "市,"
			+ "开户行名称,"
			+ "账户类型 ,"
			+ "金额,"
			+ "货币类型,"
			+ "协议号,"
			+ "协议用户编号,"
			+ "开户证件类型,"
			+ "证件号,"
			+ "手机号/小灵通,"
			+ "自定义用户号,"
			+ "备注,"
			+ "反馈码,"
			+ "原因";
	
	/**通联划扣线下导出excel导出查询字段*/
	public final static String[][] deductTlOffLine_field = new String[][]{
		{"index","index"},
		{"userNumber","label_ "},
		{"account_bank",null},
		{"accountNumberType","label_ "},
		{"account_no",null},
		{"account_name",null},
		{"account_addrprovince","province"},
		{"account_addrcity","city"},
		{"account_branch",null},
		{"accountType","label_ "},
		{"deductMoney","defaultMoney"},
		{"typeOfCurrency","label_ "},
		{"protocolNumber","label_ "},
		{"protocolUserNumber","label_ "},
		{"dict_customer_cert_type","com_certificate_type"},
		{"customer_cert_num","label_ "},   // 通联导出不需要证件号
		{"customer_mobilephone","label_ "},// 通联导出不需要手机号
		{"customUserNumber","label_ "},
		/*{"tongRemarks","index"},*/{"lendCode","comb_财富划扣#lendCode"},
		{"feedbackCode","label_ "},
		{"reason","label_ "},
		};
	
	/**划扣线下导出excel导出sql的ID*/
	public final static String deductOffLine_sql = deductPool + "getDeductExportExcel";
}
