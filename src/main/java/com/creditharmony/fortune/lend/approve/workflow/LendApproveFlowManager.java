package com.creditharmony.fortune.lend.approve.workflow;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditharmony.bpm.frame.service.FlowService;
import com.creditharmony.bpm.frame.view.WorkItemView;
import com.creditharmony.core.fortune.type.LendState;
import com.creditharmony.core.fortune.type.YoN;
import com.creditharmony.core.users.util.UserUtils;
import com.creditharmony.fortune.customer.CustomerConstants;
import com.creditharmony.fortune.customer.dao.LoanApplyDao;
import com.creditharmony.fortune.customer.entity.LoanApply;
import com.creditharmony.fortune.customer.workflow.entity.LendApplyLog;
import com.creditharmony.fortune.lend.approve.view.LendApplyApprovalView;

@Service
public class LendApproveFlowManager {

	@Resource(name = "appFrame_flowServiceImpl")
	private FlowService fs;
	@Autowired
	private LoanApplyDao dao;

	
	/**
	 * 出借审批控制流程走向 2015年12月25日
	 * 
	 * @author 孙凯文
	 * @param workItem
	 * @param log
	 * @return none
	 */
	public void dispatchFlow(WorkItemView workItem, LendApplyLog log) {
		LendApplyApprovalView view = new LendApplyApprovalView();
		view.setLendApplyLog(log);

		LoanApply apply = new LoanApply();
		apply.setApplyCode(log.getLendCode());
		apply = dao.get(apply);
		if(null != apply && !LendState.DCJSP.value.equals(apply.getLendStatus())){
			throw new RuntimeException("此条数据已经审批过，不能再次审批");
		}
		view.setLendApply(apply);
		// 审批人
		view.setAuditor(UserUtils.getUser().getName());
		workItem.setBv(view);

		// 根据审批结果设置对应的response值控制路由走向
		if (YoN.SHI.value.equals(log.getCheckExaminetype())) {
			workItem.setResponse(CustomerConstants.WorkFlow.LENDAPPLY_FLOW_YES);
		} else if (YoN.FOU.value.equals(log.getCheckExaminetype())) {
			workItem.setResponse(CustomerConstants.WorkFlow.LENDAPPLY_FLOW_NO);
		}

		this.fs.dispatch(workItem);

	}

}
