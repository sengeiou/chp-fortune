package com.creditharmony.fortune.lend.approve.workflow;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditharmony.bpm.frame.face.BusinessLoadCallBack;
import com.creditharmony.bpm.frame.face.base.BaseService;
import com.creditharmony.bpm.frame.view.BaseBusinessView;
import com.creditharmony.fortune.lend.approve.manager.LendApproveManager;

/**
 * 出借申请审批加载页面事件
 * 
 * @Class Name LendApplyApprovalDataLoad
 * @author 孙凯文
 * @Create In 2015年12月25日
 */
@Service("load_cf_lendApplyApproval")
public class LendApplyApprovalDataLoad extends BaseService implements
		BusinessLoadCallBack {
	@Autowired
	private LendApproveManager lendApplyManager;

	/**
	 * 2015年12月25日
	 * 
	 * @author 孙凯文
	 * @param applyId
	 * @param stepName
	 * @return BaseBusinessView
	 */
	public BaseBusinessView load(String applyId, String stepName) {
		return lendApplyManager.loadLendApplyApprovalView(applyId);
	}

}
