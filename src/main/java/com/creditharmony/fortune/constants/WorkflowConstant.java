package com.creditharmony.fortune.constants;


/**
 * 财富Constant常量
 * @Class Name Constant
 * @author 韩龙
 * @Create In 2015年11月27日
 */
public interface WorkflowConstant {
	
	/**流程路由to_reAudit*/
	public final static String FLOW_TO_REAUDIT="to_reAudit";
	
	/**流程路由to_approve*/
	public final static String FLOW_TO_APPROVE="to_approve";
	
	/**流程路由to_end*/
	public final static String FLOW_TO_END="to_end";
	
	/**流程路由to_third_approval*/
	public final static String FLOW_TO_THIRD_APPROVAL="to_third_approval";
	
	/**流程路由to_second_approval*/
	public final static String FLOW_TO_SECOND_APPROVAL="to_second_approval";
	
	/**流程路由to_applyUser*/
	public final static String FLOW_TO_APPLYUSER="to_applyUser";
	
	/**流程队列名出借人信息变更初审*/
	public final static String FLOW_CF_LENDER_INFO_CHANGE="CF_LENDER_INFO_CHANGE";
	
	/**流程队列名出借人信息变更复审*/
	public final static String FLOW_CF_LENDER_INFO_CHANGE_APPROVE="CF_LENDER_INFO_CHANGE_APPROVE";
	
	/**流程队列名出借人变更申请*/
	public final static String FLOW_CF_LENDER_APPLY="CF_LENDER_APPLY";
	
	/**流程队列名出借信息变更申请*/
	public final static String FLOW_CF_LEND_APPLY="CF_LEND_APPLY";
	
	/**流程队列名出借申请信息变更初审*/
	public final static String FLOW_CF_LEND_APPLY_CHANGE="CF_LEND_APPLY_CHANGE";
	
	/**流队列名出借申请信息变更复审*/
	public final static String FLOW_CF_LEND_APPLY_CHANGE_APPROVE="CF_LEND_APPLY_CHANGE_APPROVE";
	
	/**流程队列名金账户审核 数据管理部*/
	public final static String FLOW_CF_TRUSTEESHIP_CHANGE_APPROVE="CF_TRUSTEESHIP_CHANGE_APPROVE";
	
	/**流程队列名抢单流程初审*/
	public final static String FLOW_CF_GRAP_SINGLE="CF_GRAP_SINGLE";
	
	/**流程队列名抢单流程复审*/
	public final static String FLOW_CF_GRAP_APPROVE="CF_GRAP_APPROVE";
	
	
	
}
