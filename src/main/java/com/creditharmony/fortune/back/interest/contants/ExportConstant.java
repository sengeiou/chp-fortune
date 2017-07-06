package com.creditharmony.fortune.back.interest.contants;

import java.io.Serializable;

/**
 * 列表导出Excel文件名
 * @Class Name ExportConstant 
 * @author 李志伟
 * @Create In 2016年1月5日
 */
public final class ExportConstant implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// 待回息申请列表导出EXCEL文件名
	public static final String APPLY_EXPORT = "待回息申请列表导出";
	public static final String APPLY_Jzh_EXPORT = "待回息申请列表待金账户回息明细";
	
	// 待回息申请确认列表导出EXCEL文件名
	public static final String APPLY_CONFRIM_EXPORT = "待回息申请确认列表导出";
	public static final String APPLY_CONFRIM_Jzh_EXPORT = "待回息申请确认列表待金账户回息明细";
	
	// 待回息审批列表导出EXCEL文件名
	public static final String APPROVAL_EXPORT = "待回息申请审批列表导出";
	
	// 执行回息列表导出EXCEL文件名
	public static final String EXCUTE_EXPORT = "执行回息列表导出";
	public static final String EXCUTE_JZH_EXPORT = "执行回息列表待金账户回息明细";
	
	// 待回息待确认列表导出EXCEL文件名
	public static final String CONFRIM_EXPORT = "待回息确认列表导出";
	
	//  已回息列表导出EXCEL文件名
	public static final String FINISH_EXPORT = "已回息列表导出";
	
	// 通联导出文件名
	public static final String TL_EXPORT = "200100000015107_F02";
	
	// 富友导出文件名
	public static final String FY_EXPORT = "AP01_";
	
	// 中金导出文件名 
	public static final String ZJ_EXPORT = "001572_F";
	
	
	/**
	 * 普通导出SQL查询语句id
	 */
	// 到期待回息申请导出Sql_id  gaoxu   2017-3-23 14:25:41
	public static final String return_apply_id = "com.creditharmony.fortune.back.interest.common.dao.ExportDao.returnApplyAndApplyConfrim"; 
	// 待回息申请导出Sql_id
	public static final String apply_id = "com.creditharmony.fortune.back.interest.common.dao.ExportDao.applyAndApplyConfrim"; 
	// 待回息申请确认导出Sql_id
	public static final String applyConfrim_id = "com.creditharmony.fortune.back.interest.common.dao.ExportDao.applyAndApplyConfrim"; 
	// 待回息审批导出Sql_id
	public static final String approval_id = "com.creditharmony.fortune.back.interest.common.dao.ExportDao.approvalExportExl"; 
	// 待回息导出Sql_id
	public static final String excute_id = "com.creditharmony.fortune.back.interest.common.dao.ExportDao.excuteExportExl"; 
	// 待回息确认导出Sql_id
	public static final String confrim_id = "com.creditharmony.fortune.back.interest.common.dao.ExportDao.confrimExportExcel"; 
	// 已回息导出Sql_id
	public static final String finish_id = "com.creditharmony.fortune.back.interest.common.dao.ExportDao.finishExportExl"; 
	
	//------------------------->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	/*
	 *  金账户导出
	 */
	// 到期待回息申请金账户导出Sql_id  gaoxu  2017-3-23 14:49:14
	public static final String return_acc_apply_id = "com.creditharmony.fortune.back.interest.common.dao.ExportDao.returnApplyGoldAccountExportExl"; 
	// 待回息申请金账户导出Sql_id
	public static final String acc_apply_id = "com.creditharmony.fortune.back.interest.common.dao.ExportDao.applyGoldAccountExportExl"; 
	// 待回息金账户导出Sql_id
	public static final String acc_excute_id = "com.creditharmony.fortune.back.interest.common.dao.ExportDao.excuteGoldAccountExportExl"; 
	
	//-------------------------->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	/*
	 * 线下回息导出
	 */
	// 通联导出
	public static final String tl_id = "com.creditharmony.fortune.back.interest.common.dao.ExportDao.communExport";
	// 中金导出
	public static final String zj_id = "com.creditharmony.fortune.back.interest.common.dao.ExportDao.chinaFinaceExport";
	// 富友导出
	public static final String fy_id = "com.creditharmony.fortune.back.interest.common.dao.ExportDao.fuyouExport";
	// 卡联支付导出
	public static final String CARD_ID = "com.creditharmony.fortune.back.interest.common.dao.ExportDao.cardPaymentExport";
	
	/**
	 * 程序标识
	 *		用来识别调用那个方法————导出
	 */
	public static final String applyFlag = "1";
	public static final String approvalFlag = "3";
	public static final String excuteFlag = "4";
	public static final String confrimFlag = "5";
	public static final String finishFlag = "6";
	
	//-----------------------------<<<<<<<<<
	public static final String zjFlag = "7";
	public static final String tlFlag = "8";
	public static final String fyFlag = "9";
	public static final String kalianFlag = "12";
	//-----------------------------<<<<<<<<<
	public static final String applyAccFlag = "10";
	public static final String excuteAccFlag = "11";
	
	// 到期待回息申请导出  gaoxu   2017-3-23 14:25:41
	public static final String returnApplyFlag = "13";
	// 到期待回息申请列表金账户导出明细  gaoxu   2017-3-23 14:25:41
	public static final String returnApplyAccFlag = "14";
	
	// 到期待回息申请及待回息申请确认列表导出标题 gaoxu 2017-3-23 14:28:18
	public static String RETURN_DHXSQ_TITLE = "出借编号,收款账户,收款户名,实际回息金额,银行编码,收款银行名称,收款银行支行,卡/折,收款省/直辖市,收款市/区,回息类型,序列号,备注,初始出借日期,初始出借金额,付款方式,营业部,到期日期,是否回息";
	// 待回息申请及待回息申请确认列表导出标题
	public static String DHXSQ_TITLE = "出借编号,收款账户,收款户名,实际回息金额,银行编码,收款银行名称,收款银行支行,卡/折,收款省/直辖市,收款市/区,回息类型,序列号,备注,初始出借日期,初始出借金额,付款方式,营业部";
	// 到期待回息申请及待回息申请确认列表导出待金账户回息明细标题  gaoxu 2017-3-23 14:28:18
	public static String RETURN_DHXSQ_jzh_TITLE = "金账户账号,收款户名,实际回息金额,备注,收款/直辖市,收款县,出借编号,初始出借金额,初始出借日期,营业部,账单日,卡/折,到期日期,是否回息";
	
	// 待回息申请及待回息申请确认列表导出待金账户回息明细标题
	public static String DHXSQ_jzh_TITLE = "金账户账号,收款户名,实际回息金额,备注,收款/直辖市,收款县,出借编号,初始出借金额,初始出借日期,营业部,账单日,卡/折";
	
	// 待回息审批导出标题
	public static String DHXSP_TITLE = "姓名,出借编号,首次出借日期,初始出借金额,产品类型,债权标识,产品到期日,理财经理,营业部,回息开户行,收款银行支行,账号,当期应还金额,合同版本号";
	// 执行回息网银导出标题
	public static String DHX_TITLE = "出借编号,收款账户,收款户名,实际回息金额,备注,收款银行名称,收款银行支行,卡/折,收款/直辖市,收款县,放款账户,开户行,银行账号,放款日期,账单日,合同版本号";
	// 执行回息待金账户回息明细
	public static String DHX_JZH_TITLE = "序号,付款方登录名,付款方中文名称,付款资金来自冻结,收款方登录名,收款方中文名称,收款后立即冻结,交易金额,备注信息,预授权合同号";
	// 待回息确认导出标题
	public static String DHXQR_TITLE = "出借编号,客户姓名,出借方式,客户回款开户行,回息平台,应回息日期,回息日期,实际回息金额,回息状态,操作";
	// 已回息导出标题
	public static String YHX_TITLE = "出借编号,收款账户,收款户名,实际回息金额,收款银行,收款银行支行,收款/直辖市,收款县,放款账户,开户行,银行账号,放款日期,金账户账号,已回息日期,本期账单日,是否回息";
	
	// 通联导出文件标题
	public static String TL_TITILE = "序号*,用户编号,银行代码*,账号类型,账号*,户名*,省,市,开户行名称,账户类型,金额*,货币类型,协议号,协议用户编号,开户证件类型,证件号,手机号/小灵通,自定义用户号,备注,反馈码,原因";
	// 中金导出文件标题
	public static String ZJ_TITLE = "明细流水号,金额(元),银行代码,帐户类型,账户名称,账户号码,分支行,省份,城市,备注,证件类型,证件号码,手机号,电子邮箱";
	// 富友导出文件标题
	public static String FY_TITLE = "序号,开户行,开户市/县,开户行支行全称,收款人银行账号,户名,金额(单位:元),企业流水账号,备注,手机号";
	// 卡折支付导出文件标题
	public static String KA_ZHE_TITLE = "业务对象类型,收款账户开户名,收款银行账号,收款开户银行,开户省份,开户城市,支行名称,支行代码,交易金额,备注,卡折标志";

}