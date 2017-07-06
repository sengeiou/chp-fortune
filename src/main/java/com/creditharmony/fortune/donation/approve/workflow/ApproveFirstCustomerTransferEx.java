package com.creditharmony.fortune.donation.approve.workflow;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.creditharmony.bpm.frame.face.ExEvent;
import com.creditharmony.bpm.frame.view.WorkItemView;
import com.creditharmony.fortune.donation.apply.entity.CustomertransferLaunchView;
import com.creditharmony.fortune.donation.approve.manager.DonationApproveManager;
/**
 * 营业部经理初审
 * @Class Name ApproveFirstCustomerTransferEx
 * @author 刘雄武
 * @Create In 2016年3月7日
 */
@Service("ex_cf_CustomerTransfer_approve_first")
public class ApproveFirstCustomerTransferEx  implements ExEvent{
    
	@Resource
	private DonationApproveManager ctService;
	@Override
	public String getBeanName() {
		
		return null;
	}

	@Override
	public void invoke(WorkItemView workItem) {
			
		CustomertransferLaunchView view  = (CustomertransferLaunchView) workItem.getBv();
		ctService.saveApproveInfofirst(view, workItem.getResponse());
	}

}
