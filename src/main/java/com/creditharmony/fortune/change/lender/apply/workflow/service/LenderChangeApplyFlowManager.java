package com.creditharmony.fortune.change.lender.apply.workflow.service;

import java.util.Date;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.creditharmony.bpm.frame.service.ApplyIdService;
import com.creditharmony.bpm.frame.service.FlowService;
import com.creditharmony.bpm.frame.view.WorkItemView;
import com.creditharmony.fortune.change.lender.apply.view.LenderInitView;

/**
 * 出借人信息变更申请流程管理类
 * @Class Name LenderChangeApplyFlowManager
 * @author 郭才林
 * @Create In 2016年4月13日
 */
@Service
public class LenderChangeApplyFlowManager {

	@Resource(name = "appFrame_flowServiceImpl")
	private FlowService fs;
	@Resource(name = "ApplyIdServiceImpl")
	private ApplyIdService apid;

	/**
	 * 发起流程
	 * 2015年12月2日
	 * By 郭才林
	 * @param workItem
	 * @param bv
	 */
	public void launchFlow(WorkItemView workItem, LenderInitView bv) {
		bv.setApplyDate(new Date());
		String applyId = apid.builderApplyId(workItem.getFlowType());
		bv.setApplyId(applyId);
		workItem.setBv(bv);
		this.fs.launchFlow(workItem);
	}

}
