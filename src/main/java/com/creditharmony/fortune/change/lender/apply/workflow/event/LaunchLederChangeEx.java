package com.creditharmony.fortune.change.lender.apply.workflow.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditharmony.bpm.frame.face.ExEvent;
import com.creditharmony.bpm.frame.view.WorkItemView;
import com.creditharmony.fortune.change.lender.apply.service.LenderChangeApplyManager;
import com.creditharmony.fortune.change.lender.apply.view.LenderInitView;

/**
 * 发起流程回调事件，保存申请信息
 * @Class Name LaunchLederChangeEx
 * @author 郭才林
 * @Create In 2015年12月2日
 */
@Service("ex_cf_lenderChange_launch")
public class LaunchLederChangeEx implements ExEvent{
    
	@Autowired
	private LenderChangeApplyManager applyService;
	@Override
	public String getBeanName() {
		
		return null;
	}

	@Override
	public void invoke(WorkItemView workItem) {
		
		LenderInitView view=(LenderInitView) workItem.getBv();
		applyService.saveLenderChangeInfo(view);// 保存变更信息
		
	}

}
