package com.creditharmony.fortune.lend.approve.workflow;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditharmony.bpm.frame.face.ExEvent;
import com.creditharmony.bpm.frame.view.WorkItemView;
import com.creditharmony.fortune.common.service.FortuneExceptionService;
import com.creditharmony.fortune.creditor.config.auto.matching.service.AutoMatchingManager;
import com.creditharmony.fortune.lend.approve.manager.LendApproveManager;
import com.creditharmony.fortune.lend.approve.view.LendApplyApprovalView;

/**
 * 保存出借申请触发事件
 * 
 * @Class Name LendApplyApprovalBeforeEventEx
 * @author 孙凯文
 * @Create In 2015年12月25日
 */
@Service("ex_cf_lendApply_approve")
public class LendApplyApprovalBeforeEventEx implements ExEvent {
	
	protected Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private LendApproveManager lendApplyManager;
	@Autowired
	private AutoMatchingManager autoMatchingManager;
	@Autowired
	private FortuneExceptionService fortuneExceptionService;

	/**
	 * 2015年12月25日
	 * 
	 * @author 孙凯文
	 * @return String
	 */
	public String getBeanName() {
		return null;
	}

	/**
	 * 2015年12月25日
	 * 
	 * @author 孙凯文
	 * @param workItem
	 * @return none
	 */
	public void invoke(WorkItemView workItem) {
		LendApplyApprovalView view = (LendApplyApprovalView) workItem.getBv();
		lendApplyManager.saveApprovalForm(view, workItem.getResponse());
		// 更新自动匹配物化视图
		autoMatchingManager.updateAutoMatching();
	}

}
