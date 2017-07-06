package com.creditharmony.fortune.donation.apply.manager;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.creditharmony.bpm.frame.service.ApplyIdService;
import com.creditharmony.bpm.frame.service.FlowService;
import com.creditharmony.bpm.frame.view.WorkItemView;
import com.creditharmony.fortune.donation.apply.entity.CustomertransferLaunchView;


/**
 * 转赠流程Manager
 * @Class Name CustomerTransferFlowManager
 * @author 刘雄武
 * @Create In 2016年3月7日
 */
@Service
public class CustomerTransferFlowManager {

	@Resource(name = "appFrame_flowServiceImpl")
	private FlowService fs;
	
	@Resource(name = "ApplyIdServiceImpl")
	private ApplyIdService apid;
	
	/**
	 * 发起流程
	 * 2016年3月7日
	 * By 刘雄武
	 * @param workItem
	 * @param bv
	 */
	public void launchFlow(WorkItemView workItem, CustomertransferLaunchView bv) {
		String applyId = apid.builderApplyId(workItem.getFlowType());
		bv.setApplyDate(new Date());
		bv.setApplyId(applyId);
		bv.getCTManagerinfo().setTransferState("1");
		workItem.setBv(bv);
		this.fs.launchFlow(workItem);
	}
}
