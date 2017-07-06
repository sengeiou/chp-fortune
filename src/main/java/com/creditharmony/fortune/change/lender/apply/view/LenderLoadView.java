package com.creditharmony.fortune.change.lender.apply.view;

import java.util.List;
import java.util.Map;

import com.creditharmony.bpm.frame.view.BaseBusinessView;
import com.creditharmony.core.dict.entity.Dict;
import com.creditharmony.fortune.change.lender.apply.entity.LenderChangeInfo;
import com.creditharmony.fortune.change.lender.apply.entity.LenderChangeLog;

/**
 * 
 * @Class Name TrusteeshipChangeLoadView
 * @author 郭才林
 * @Create In 2015年12月7日
 */
public class LenderLoadView  extends  BaseBusinessView {
	
	private LenderInitView lenderBegin;// 变更前view
	private LenderInitView lenderAfter;// 变更后view
	private LenderChangeLog changeLog;// 初审
	private LenderChangeInfo changInfo;// 变更信息
	private LenderChangeLog changInfoRiew;// 复审
	private LenderChangeLog changInfoRes;// 退回意见
	//判断手机号与变更前是否相同
	private boolean meginAndAfterMob;
	public boolean isMeginAndAfterMob() {
		return meginAndAfterMob;
	}
	public void setMeginAndAfterMob(boolean meginAndAfterMob) {
		this.meginAndAfterMob = meginAndAfterMob;
	}
	private Map<String, List<Dict>> dicts;
	
	
	public Map<String, List<Dict>> getDicts() {
		return dicts;
	}
	public void setDicts(Map<String, List<Dict>> dicts) {
		this.dicts = dicts;
	}
	public LenderInitView getLenderBegin() {
		return lenderBegin;
	}
	public void setLenderBegin(LenderInitView lenderBegin) {
		this.lenderBegin = lenderBegin;
	}
	public LenderInitView getLenderAfter() {
		return lenderAfter;
	}
	public void setLenderAfter(LenderInitView lenderAfter) {
		this.lenderAfter = lenderAfter;
	}
	public LenderChangeLog getChangeLog() {
		return changeLog;
	}
	public void setChangeLog(LenderChangeLog changeLog) {
		this.changeLog = changeLog;
	}
	public LenderChangeInfo getChangInfo() {
		return changInfo;
	}
	public void setChangInfo(LenderChangeInfo changInfo) {
		this.changInfo = changInfo;
	}
	public LenderChangeLog getChangInfoRiew() {
		return changInfoRiew;
	}
	public void setChangInfoRiew(LenderChangeLog changInfoRiew) {
		this.changInfoRiew = changInfoRiew;
	}
	public LenderChangeLog getChangInfoRes() {
		return changInfoRes;
	}
	public void setChangInfoRes(LenderChangeLog changInfoRes) {
		this.changInfoRes = changInfoRes;
	}
	
	
	
	
	
}