package com.creditharmony.fortune.donation.finalapprove.workflow;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.creditharmony.bpm.frame.face.ExEvent;
import com.creditharmony.bpm.frame.view.WorkItemView;
import com.creditharmony.fortune.donation.apply.entity.CustomertransferLaunchView;
import com.creditharmony.fortune.donation.finalapprove.manager.DonationFinalApproveManager;

/**
 * 支公司经理复审
 * @Class Name ApproveReviewCustomerTransferEx
 * @author 刘雄武
 * @Create In 2016年3月7日
 */
@Service("ex_cf_CustomerTransfer_approve_review")
public class ApproveReviewCustomerTransferEx  implements ExEvent{
    
	@Resource
	private DonationFinalApproveManager ctService;
	@Override
	public String getBeanName() {
		
		return null;
	}

	@Override
	public void invoke(WorkItemView workItem) {
		CustomertransferLaunchView view  = (CustomertransferLaunchView) workItem.getBv();
		ctService.saveApproveInfoReview(view, workItem.getResponse());
	}

}
