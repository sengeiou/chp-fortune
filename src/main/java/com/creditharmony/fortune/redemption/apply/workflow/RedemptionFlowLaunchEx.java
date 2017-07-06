package com.creditharmony.fortune.redemption.apply.workflow;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditharmony.bpm.frame.face.ExEvent;
import com.creditharmony.bpm.frame.face.base.BaseService;
import com.creditharmony.bpm.frame.view.WorkItemView;
import com.creditharmony.fortune.redemption.apply.service.RedeemApplyManager;
import com.creditharmony.fortune.redemption.common.view.RedemptionApplyEntity;

/**
 * 描述：流程发起时保存业务数据
 * @Class Name RedemptionFlowLaunchEx
 * @author 陈广鹏
 * @Create In 2015年11月27日
 */
@Service("ex_cf_redemptionFlow_launch")
public class RedemptionFlowLaunchEx extends BaseService implements ExEvent {

	@Autowired
	private RedeemApplyManager applyManager;

	/**
	 * 描述：发起提前赎回流程 
	 * 2015年12月10日 
	 * By 陈广鹏
	 * @param itemView
	 */
	public void invoke(WorkItemView itemView) {

		RedemptionApplyEntity entity = (RedemptionApplyEntity) itemView.getBv();

		applyManager.dataSave(entity);
	}
}
