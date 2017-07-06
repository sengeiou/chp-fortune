package com.creditharmony.fortune.trusteeship.view;

import com.creditharmony.bpm.frame.view.BaseBusinessView;
import com.creditharmony.fortune.change.lender.apply.entity.LenderChangeLog;

/**
 * 
 * @Class Name TrusteeshipChangeLoadView
 * @author 郭才林
 * @Create In 2015年12月7日
 */
public class TrusteeshipChangeLoadView  extends  BaseBusinessView {
	
	// 变更类型
	private String changerType;
	// 审批信息
	private LenderChangeLog log;

	public String getChangerType() {
		return changerType;
	}

	public void setChangerType(String changerType) {
		this.changerType = changerType;
	}

	public LenderChangeLog getLog() {
		return log;
	}

	public void setLog(LenderChangeLog log) {
		this.log = log;
	}

	
	 
	
}