package com.creditharmony.fortune.donation.apply.workflow;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.creditharmony.bpm.frame.face.ExEvent;
import com.creditharmony.bpm.frame.view.WorkItemView;
import com.creditharmony.fortune.donation.apply.entity.CustomertransferLaunchView;
import com.creditharmony.fortune.donation.apply.manager.CustomerTransferManager;


/**
 * 出借信息变更申请实现ExEven接口流程
 * @Class Name LaunchLendChangeEx
 * @author 刘雄武
 * @Create In 2015年12月2日
 */
@Service("ex_cf_customertransfer_launch")
public class LaunchCustomerTransferEx  implements ExEvent{

	@Resource
	private CustomerTransferManager ctService;
	@Override
	public String getBeanName() {
		return null;
	}

	@Override
	public void invoke(WorkItemView workItem) {
		CustomertransferLaunchView view = (CustomertransferLaunchView) workItem.getBv();
	
		ctService.savecustomertransferInfo(view);
	}

}
