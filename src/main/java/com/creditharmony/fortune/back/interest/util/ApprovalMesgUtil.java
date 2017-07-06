package com.creditharmony.fortune.back.interest.util;

import java.util.Date;

import com.creditharmony.core.users.util.UserUtils;
import com.creditharmony.fortune.back.interest.entity.BackInterestLog;

public final class ApprovalMesgUtil{
	
	/**
	 * 生成审批记录
	 * 2015年12月30日
	 * by 李志伟
	 * @param st 回息状态
	 * @param state 审批状态
	 * @param check 审批不通过原因
	 * @return
	 */
	public static BackInterestLog generateMes(String st, String state, String check){
		
		BackInterestLog log = new BackInterestLog();
		log.setDictBackiStatus(st);
		log.setCheckExaminetype(state);
		log.setCheckExamine(check);
		log.setCheckById(UserUtils.getUserId());
		log.setCreateBy(UserUtils.getUser(UserUtils.getUserId()).getLoginName());
		log.setApplyBy(UserUtils.getUser(UserUtils.getUserId()).getLoginName());
		log.setApplyTime(new Date());
		log.setCheckTime(new Date());
		return log;
	}
}