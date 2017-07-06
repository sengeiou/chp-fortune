package com.creditharmony.fortune.lend.approve.view;

import java.util.List;

import com.creditharmony.fortune.customer.entity.LoanApply;
import com.creditharmony.fortune.customer.workflow.entity.LendApplyLog;
import com.creditharmony.fortune.lend.apply.view.LendApplyView;

/**
 * 出借申请审批视图类
 * 
 * @Class Name LendApplyApprovalView
 * @author 孙凯文
 * @Create In 2015年12月24日
 */
public class LendApplyApprovalView extends LendApplyView {
	// 审批记录
	private LendApplyLog lendApplyLog;
	// 内转出借列表
	private List<LoanApply> transferLoanApplyList;
	// 当前出借产品对应的协议版本列表
	private String[] protocolList;
	private String canceled;

	/**
	 * 获取审批记录 2016年1月25日
	 * 
	 * @author 孙凯文
	 * @return
	 */
	public LendApplyLog getLendApplyLog() {
		return lendApplyLog;
	}

	/**
	 * 设置审批记录 2016年1月25日
	 * 
	 * @author 孙凯文
	 * @param lendApplyLog
	 */
	public void setLendApplyLog(LendApplyLog lendApplyLog) {
		this.lendApplyLog = lendApplyLog;
	}

	public List<LoanApply> getTransferLoanApplyList() {
		return transferLoanApplyList;
	}

	public void setTransferLoanApplyList(List<LoanApply> transferLoanApplyList) {
		this.transferLoanApplyList = transferLoanApplyList;
	}

	public String[] getProtocolList() {
		return protocolList;
	}

	public void setProtocolList(String[] protocolList) {
		this.protocolList = protocolList;
	}

	public String getCanceled() {
		return canceled;
	}

	public void setCanceled(String canceled) {
		this.canceled = canceled;
	}


}
