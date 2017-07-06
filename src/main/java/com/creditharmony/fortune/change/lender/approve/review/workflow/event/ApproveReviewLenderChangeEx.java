package com.creditharmony.fortune.change.lender.approve.review.workflow.event;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.creditharmony.bpm.frame.face.ExEvent;
import com.creditharmony.bpm.frame.view.WorkItemView;
import com.creditharmony.fortune.change.lender.apply.view.LenderInitView;
import com.creditharmony.fortune.change.lender.approve.review.service.LenderChangeApproveReviewManager;

/**
 * 业务对接员复审回调类，保存审批信息
 * @Class Name ApproveReviewLendChangeEx
 * @author 刘雄武
 * @Create In 2015年12月8日
 */
@Service("ex_cf_lenderChange_approve_review")
public class ApproveReviewLenderChangeEx  implements ExEvent{
    
	@Resource
	private LenderChangeApproveReviewManager reviewManager ;
	@Override
	public String getBeanName() {
		
		return null;
	}

	@Override
	public void invoke(WorkItemView workItem) {
		LenderInitView view=(LenderInitView) workItem.getBv();
		reviewManager.saveApproveInfoReview(view,workItem.getResponse());// 保存变更信息
		
	}

}
