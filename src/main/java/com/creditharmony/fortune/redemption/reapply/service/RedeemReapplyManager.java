package com.creditharmony.fortune.redemption.reapply.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditharmony.bpm.frame.service.FlowService;
import com.creditharmony.bpm.frame.view.FlowPage;
import com.creditharmony.bpm.frame.view.TaskBean;
import com.creditharmony.fortune.redemption.common.view.RedemptionApplyEntity;
import com.creditharmony.fortune.redemption.common.view.RedemptionFlowTaskItemView;
import com.creditharmony.fortune.redemption.constant.RedeemConstant;
import com.creditharmony.fortune.redemption.utils.RedeemUtils;
import com.query.ProcessQueryBuilder;

/**
 * 审批退回Service
 * @Class Name ReapplyManager
 * @author 陈广鹏
 * @Create In 2016年4月14日
 */
@Service
public class RedeemReapplyManager {
	
	@Autowired
	private FlowService flowService;
	
	/**
	 * 获取审批退回列表，不带分页
	 * 2015年12月21日
	 * By 陈广鹏
	 * @param entity
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<RedemptionFlowTaskItemView> fetchBackList(RedemptionApplyEntity entity) {
		String queueName = RedeemConstant.CF_ACCOUNT_APPROVE;
		
		ProcessQueryBuilder queryBuilder = RedeemUtils.getQueryBuilder(entity);
		
		TaskBean taskBean = flowService.fetchTaskItems(queueName,
				queryBuilder, RedemptionFlowTaskItemView.class);

		List<RedemptionFlowTaskItemView> itemList = new ArrayList<RedemptionFlowTaskItemView>();
		if (taskBean.getItemList() != null) {
			itemList = (List<RedemptionFlowTaskItemView>) taskBean.getItemList();
		}
		return itemList;
	}
	
	/**
	 * 获取审批退回列表，带分页
	 * 2015年12月21日
	 * By 陈广鹏
	 * @param entity
	 * @return
	 */
	public void fetchBackList(FlowPage page, RedemptionApplyEntity entity) {
		String queueName = RedeemConstant.CF_ACCOUNT_APPROVE;
		ProcessQueryBuilder queryBuilder = RedeemUtils.getQueryBuilder(entity);
		
		flowService.fetchTaskItems(queueName, queryBuilder, page, null, RedemptionFlowTaskItemView.class);
	}


}
