package com.creditharmony.fortune.change.lend.review.workflow;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.creditharmony.bpm.frame.face.ExEvent;
import com.creditharmony.bpm.frame.view.WorkItemView;
import com.creditharmony.fortune.change.lend.apply.entity.LendLaunchView;
import com.creditharmony.fortune.change.lend.review.manager.LendChangeReviewManager;

/**
 * 业务对接员复审
 * @Class Name ApproveReviewLendChangeEx
 * @author 刘雄武
 * @Create In 2015年12月8日
 */
@Service("ex_cf_lendChange_approve_review")
public class ApproveReviewLendChangeEx  implements ExEvent{
    
	@Resource
	private LendChangeReviewManager lcmService;
	@Override
	public String getBeanName() {
		
		return null;
	}

	@Override
	public void invoke(WorkItemView workItem) {
		LendLaunchView view=(LendLaunchView) workItem.getBv();
		lcmService.saveApproveInfoReview(view.getChangeLog(),workItem.getResponse());// 保存变更信息
		
	}

}
