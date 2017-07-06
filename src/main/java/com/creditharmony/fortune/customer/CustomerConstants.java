package com.creditharmony.fortune.customer;

/**
 * @Class Name CustomerConstants
 * @author 孙凯文
 * @Create In 2015年12月8日
 */
public class CustomerConstants {
	public static final String TRANSFERLEND_FLOW_TYPENAME = "0";
	public static int LENDCODE_GROWTHRATE = 1;
	public static int FIRSTPHASE = 1;
	public static String XINHEBAO_12 = "1";
	public static String XINHEBAO_24 = "2";
	
	public final static class DataViewConfig {
		// 默认排序规则
		public final static String DefaultOrderSql = "create_time desc";
		// 出借编号尾号规则
		public final static String LENDCODE_SERIALPATTERN = "%03d";
	}

	public final static class WorkFlow {
		public final static String LEND_FLOW_TYPE = "cf_lend_apply";
		// 默认流程ID
		public final static String DefaultFlowApplyId = "0";
		// 开户队列名称
		public final static String QUENE_OF_TRANSFERLEND = "CF_OPEN_ACCOUNT";
		// 合同审批队列名称
		public final static String QUENE_OF_LENDAPPLY = "CF_FORTUNE_APPROVE";
		// 合同审批被退回，申请人队列
		public final static String QUENE_OF_LENDAPPLY_RETURNED = "CF_APPLY";
		// 开户审批结果（不通过）标识
		public final static String TRANSFERLEND_FLOW_NO = "to_applyUser";
		// 开户审批结果（通过）标识
		public final static String TRANSFERLEND_FLOW_YES = "to_end";
		// 出借审批结果（不通过）标识
		public final static String LENDAPPLY_FLOW_NO = "to_applyer";
		// 出借审批结果（通过）标识
		public final static String LENDAPPLY_FLOW_YES = "to_end";
		// 金账户开户流程
		public final static String GOLDACCOUNT_FLOW = "to_accountApprove";
		// 合同审批流程
		public final static String CONTRACT_FLOW = "to_approve";
	}

}
