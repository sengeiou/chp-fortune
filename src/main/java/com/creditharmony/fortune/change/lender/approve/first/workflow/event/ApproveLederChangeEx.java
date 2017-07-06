package com.creditharmony.fortune.change.lender.approve.first.workflow.event;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.creditharmony.bpm.frame.face.ExEvent;
import com.creditharmony.bpm.frame.view.WorkItemView;
import com.creditharmony.fortune.change.lender.apply.view.LenderInitView;
import com.creditharmony.fortune.change.lender.approve.first.service.LenderChangeApproveFirstManager;

/**
 * 初审审批回调事件保存审批信息
 * @Class Name ApproveTrusteeshipChangeEx
 * @author 郭才林
 * @Create In 2015年12月2日
 */
@Service("ex_cf_lenderChange_approve")
public class ApproveLederChangeEx implements ExEvent{
    
	@Resource
	private LenderChangeApproveFirstManager approveFirsService;
	@Override
	public String getBeanName() {
		
		return null;
	}

	@Override
	public void invoke(WorkItemView workItem) {
		
		LenderInitView view=(LenderInitView) workItem.getBv();
		approveFirsService.saveApproveInfo(view,workItem.getResponse());// 保存审批信息
		
	}

}
