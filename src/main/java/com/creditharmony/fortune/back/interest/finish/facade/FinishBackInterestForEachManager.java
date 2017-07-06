package com.creditharmony.fortune.back.interest.finish.facade;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditharmony.common.util.IdGen;
import com.creditharmony.core.fortune.type.BacksmsState;
import com.creditharmony.core.fortune.type.FinishState;
import com.creditharmony.core.fortune.type.FortuneLogNode;
import com.creditharmony.core.fortune.type.YoN;
import com.creditharmony.core.users.util.UserUtils;
import com.creditharmony.fortune.back.interest.common.dao.BackInterestCommonDao;
import com.creditharmony.fortune.back.interest.common.service.BackInterestCommonService;
import com.creditharmony.fortune.back.interest.contants.GlobalConstant;
import com.creditharmony.fortune.back.interest.entity.BackInterestLog;
import com.creditharmony.fortune.back.interest.entity.BackInterestPool;
import com.creditharmony.fortune.back.interest.entity.SearchObject;
import com.creditharmony.fortune.back.interest.util.StaticMethodUtil;
import com.creditharmony.fortune.common.entity.Check;
import com.creditharmony.fortune.customer.entity.LoanApply;

/**
 * 已回息批量操作
 * @Class Name FinishBackInterestManager 
 * @author 李志伟
 * @Create In 2016年5月3日
 */
@Service
public class FinishBackInterestForEachManager {
	
	@Autowired
	private BackInterestCommonService backInterestCommonService;
	@Autowired
	private BackInterestCommonDao backInterestCommonDao;
	
	/**
	 * 日志对象
	 */
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	/**
	 * 批量回息退回
	 * 2016年1月25日
	 * by 李志伟
	 * @param map
	 */
	public String batchReturn(BackInterestPool bip, SearchObject so) {
		
		List<BackInterestPool> backiIdAndLendCodeList = new ArrayList<BackInterestPool>();
		Map<String,Object> map = new HashMap<String, Object>();
		
		String city = so.getAddrCity();
		if (StringUtils.isNotEmpty(city)) {
			String c = "%" + city.replace(",", "%|%") +"%";
			so.setAddrCity(c);
		}
		StaticMethodUtil.getCondition(so, map, StaticMethodUtil.getFinishBackInterestStatus());
		backiIdAndLendCodeList = backInterestCommonDao.findBackiIdAndLendCode(map);
		bip.setCheckExaminetype(YoN.FOU.value);
		BackInterestLog bil = StaticMethodUtil.madeApproval(bip, BacksmsState.YHXTH.value, "");
		
		String errorMesg = "";
		String userId = UserUtils.getUserId();
		String userName = UserUtils.getUser(userId).getName();
		for (BackInterestPool bbi : backiIdAndLendCodeList) {
			
			try {
				
				// 排他
				bbi.setVerState(StaticMethodUtil.getFinishBackInterestStatus());
				if(bip.getCheckExaminetype().equals(YoN.FOU.value)){
					bbi.setRebackFlag(YoN.SHI.value);
				}
				
				bil.setBackiId(bbi.getBackiId());
				bil.setId(IdGen.uuid());
				bbi.setBackMoneyStatus(bil.getDictBackiStatus());
				bbi.setReturnReason(bil.getCheckExamine());
				bbi.preUpdate();
				
				LoanApply la = backInterestCommonService.getLoanApplyMesg(bbi.getLendCode());
				// 如果这笔出借的状态为已完结，需要把状态更改成未完结
				if(la.getApplyEndStatus().equals(FinishState.YWJ.value)){
					LoanApply le = new LoanApply();
					le.setApplyCode(bbi.getLendCode());
					le.setApplyEndStatus(FinishState.WWJ.value);
					le.preUpdate();
					
					// 生成历史留痕
					Check ch = StaticMethodUtil.madeHistory(userId, userName, bbi.getLendCode(), bil.getType(), FortuneLogNode.INTEREST_FINISH.getName(), bbi.getBackiId(), GlobalConstant.YHX_LIST+""+GlobalConstant.BRANCH+""+bil.getType()
							+";"+ GlobalConstant.LEND_STATE_UPDATE +";" + GlobalConstant.UPDATE_ST + BacksmsState.getBacksmsState(bil.getDictBackiStatus()));
					errorMesg += backInterestCommonService.doSubmit(bbi, bil, ch, le);
				}else{
					
					// 生成历史留痕
					Check ch = StaticMethodUtil.madeHistory(userId, userName, bbi.getLendCode(), bil.getType(), FortuneLogNode.INTEREST_FINISH.getName(), bbi.getBackiId(), GlobalConstant.YHX_LIST+""+GlobalConstant.BRANCH+""+bil.getType()
							+";"+GlobalConstant.UPDATE_ST + BacksmsState.getBacksmsState(bil.getDictBackiStatus()));
					errorMesg += backInterestCommonService.doSubmit(bbi, bil, ch);
				}
			} catch (Exception e) {
				
				errorMesg += "【出借编号】："+bbi.getLendCode()+"批量提交失败<br/>"+e.getMessage();
				logger.debug(e.getMessage());
				logger.error("更新失败:"+e.getMessage(), e);
				backInterestCommonService.addExceptionMesgs(bbi.getBackiId(), e, "[回息编号]"+bbi.getBackiId()+"[批量回息退回失败]", FortuneLogNode.INTEREST_FINISH.getCode());
			}
		}
		if(("").equals(errorMesg)){
			return "true";
		}
		return errorMesg;
	}
}
