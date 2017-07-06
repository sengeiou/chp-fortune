package com.creditharmony.fortune.change.lender.excel;

/**
 * 导出表头类
 * @Class Name ExportField
 * @author 刘雄武
 * @Create In 2016年4月26日
 */
public class ExportField {

	/**出借人信息变更导出excel导出表头信息*/
	public final static String lenderChange_title = 
			"审核日期,"
			+ "客户姓名,"
			+ "客户编号,"
			+ "变更前的信息,"
			+ "变更后的信息,"
			+ "理财经理,"
			+ "营业部";
	
	/**出借人信息变更导出excel导出sql*/
	public final static String lenderChange_sql = "com.creditharmony.fortune.change.lender.apply.dao.LenderChangeDao.queryExport";
	
	/**出借信息变更导出excel导出表头信息*/
	public final static String lendChange_title = 
			"审核日期,"
			+ "客户姓名,"
			+ "出借编号,"
			+ "首次出借日期,"
			+ "初始出借金额,"
			+ "出借模式,"
			+ "账单日,"
			+ "变更前行别,"
			+ "变更前开户行,"
			+ "变更前账号,"
			+ "变更后行别,"
			+ "变更后开户行,"
			+ "变更后账号,"
			+ "理财经理,"
			+ "营业部";
	
	/**出借信息变更导出excel导出sql*/
	public final static String lendChange_sql = "com.creditharmony.fortune.change.lend.apply.dao.LendChangeDao.queryExport";
}
