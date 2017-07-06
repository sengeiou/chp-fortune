package com.creditharmony.fortune.lend.reject.workflow;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditharmony.bpm.frame.face.ExEvent;
import com.creditharmony.bpm.frame.view.WorkItemView;
import com.creditharmony.fortune.lend.apply.manager.LendApplyManager;
import com.creditharmony.fortune.lend.apply.view.LendApplyView;

/**
 * @Class Name LendApplyApprovalBeforeEventEx
 * @author 孙凯文
 * @Create In 2015年12月25日
 */
@Service("ex_cf_lendApply_ReLaunch")
public class LendApplyReReLaunchBeforeEventEx implements ExEvent {
	@Autowired
	private LendApplyManager lendApplyManager;

	/**
	 * 2015年12月25日
	 * 
	 * @author 孙凯文
	 * @return
	 */
	public String getBeanName() {
		return null;
	}

	/**
	 * 出借申请退回再提交 2015年12月25日
	 * 
	 * @author 郭才林
	 * @param workItem
	 */
	public void invoke(WorkItemView workItem) {
		LendApplyView view = (LendApplyView) workItem.getBv();
		lendApplyManager.updateMakeFileStatus(view);
	}

}
