package com.creditharmony.fortune.change.lend.apply.entity;

import java.util.List;
import java.util.Map;

import com.creditharmony.bpm.frame.view.BaseBusinessView;
import com.creditharmony.core.dict.entity.Dict;
/**
 * 变更前后的View
 * @Class Name LendLoadView
 * @author 刘雄武
 * @Create In 2015年12月7日
 */
public class LendLoadView extends BaseBusinessView {
    // 出借变更表变更前JSON存储
	private LendLaunchView changeBegin;
	// 出借变更表变更后JSON存储
	private LendLaunchView changeAfter;
	// 出借变更信息
	private LendChangeInfo changeInfo;
	// 出借变更审批历史
	private LendChangeLog  changeLog;
	// 出借变更复审
	private LendChangeLog changInfoRiew;
	// 出借变更数据管理部退回
	private LendChangeLog changInfoRes;
	private Map<String, List<Dict>> dicts;
	
	
	public Map<String, List<Dict>> getDicts() {
		return dicts;
	}

	public void setDicts(Map<String, List<Dict>> dicts) {
		this.dicts = dicts;
	}

	public LendChangeLog getChangInfoRiew() {
		return changInfoRiew;
	}

	public void setChangInfoRiew(LendChangeLog changInfoRiew) {
		this.changInfoRiew = changInfoRiew;
	}

	public LendChangeLog getChangeLog() {
		return changeLog;
	}

	public void setChangeLog(LendChangeLog changeLog) {
		this.changeLog = changeLog;
	}

	public LendChangeInfo getChangeInfo() {
		return changeInfo;
	}

	public void setChangeInfo(LendChangeInfo changeInfo) {
		this.changeInfo = changeInfo;
	}

	public LendLaunchView getChangeBegin() {
		return changeBegin;
	}

	public void setChangeBegin(LendLaunchView changeBegin) {
		this.changeBegin = changeBegin;
	}

	public LendLaunchView getChangeAfter() {
		return changeAfter;
	}

	public void setChangeAfter(LendLaunchView changeAfter) {
		this.changeAfter = changeAfter;
	}

	public LendChangeLog getChangInfoRes() {
		return changInfoRes;
	}

	public void setChangInfoRes(LendChangeLog changInfoRes) {
		this.changInfoRes = changInfoRes;
	}
	
	
}
