package com.creditharmony.fortune.lend.apply.workflow;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditharmony.bpm.frame.face.ExEvent;
import com.creditharmony.bpm.frame.view.WorkItemView;
import com.creditharmony.fortune.lend.apply.manager.LendApplyManager;
import com.creditharmony.fortune.lend.apply.view.LendApplyView;

/**
 * 提交出借申请事件
 * 
 * @Class Name LendApplyApprovalBeforeEventEx
 * @author 孙凯文
 * @Create In 2015年12月22日
 */
@Service("ex_cf_lendApply_launch")
public class LendApplyBeforeEventEx implements ExEvent {
	@Autowired
	private LendApplyManager lendApplyManager;

	/**
	 * 2015年12月22日
	 * 
	 * @author 孙凯文
	 * @return String
	 */
	public String getBeanName() {
		return null;
	}

	/**
	 * 2015年12月22日
	 * 
	 * @author 孙凯文
	 * @param workItem
	 * @return none
	 */
	public void invoke(WorkItemView workItem) {
		LendApplyView view = (LendApplyView) workItem.getBv();
		lendApplyManager.updateMakeFileStatus(view);
		
	}

}
