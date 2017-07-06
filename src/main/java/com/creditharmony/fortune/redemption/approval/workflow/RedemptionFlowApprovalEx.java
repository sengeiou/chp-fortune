package com.creditharmony.fortune.redemption.approval.workflow;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditharmony.bpm.frame.face.ExEvent;
import com.creditharmony.bpm.frame.face.base.BaseService;
import com.creditharmony.bpm.frame.view.WorkItemView;
import com.creditharmony.fortune.redemption.approval.service.RedeemApprovalManager;
import com.creditharmony.fortune.redemption.common.view.RedemptionApplyEntity;

/**
 * 提前赎回审批处理数据
 * @Class Name RedemptionFlowApprovalEx
 * @author 陈广鹏
 * @Create In 2016年4月14日
 */
@Service("ex_cf_redemptionFlow_approval")
public class RedemptionFlowApprovalEx  extends BaseService implements ExEvent {
	
	@Autowired
	private RedeemApprovalManager approvalManager;

	@Override
	public void invoke(WorkItemView workItemView) {
		
		RedemptionApplyEntity entity = (RedemptionApplyEntity) workItemView.getBv();
		
		approvalManager.dispatch(entity);
		
	}

}
