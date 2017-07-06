package com.creditharmony.fortune.back.interest.approval.facade;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditharmony.common.util.IdGen;
import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.fortune.type.BacksmsState;
import com.creditharmony.core.fortune.type.FortuneLogNode;
import com.creditharmony.core.fortune.type.PayMent;
import com.creditharmony.core.fortune.type.YoN;
import com.creditharmony.core.users.util.UserUtils;
import com.creditharmony.fortune.back.interest.common.dao.BackInterestCommonDao;
import com.creditharmony.fortune.back.interest.common.service.BackInterestCommonService;
import com.creditharmony.fortune.back.interest.contants.GlobalConstant;
import com.creditharmony.fortune.back.interest.entity.BackInterestLog;
import com.creditharmony.fortune.back.interest.entity.BackInterestPool;
import com.creditharmony.fortune.back.interest.entity.SearchObject;
import com.creditharmony.fortune.back.interest.entity.TelphoneMessage;
import com.creditharmony.fortune.back.interest.util.StaticMethodUtil;
import com.creditharmony.fortune.common.entity.Check;

/**
 * 批量回息审批
 * @Class Name BackInterestApprovalForEach 
 * @author 李志伟
 * @Create In 2016年4月29日
 */
@Service
public class BackInterestApprovalForEachManager {
	
	/**
	 * 日志对象
	 */
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private BackInterestCommonDao backInterestCommonDao;
	@Autowired
	private BackInterestCommonService backInterestCommonService;
	
	/**
	 * 批量回息审批
	 * 2015年12月7日
	 * by 李志伟
	 * @param map 出借编号和回息状态
	 */
	public String bacthApproval(BackInterestPool bip, SearchObject so) {
		
		List<BackInterestPool> backiIdAndLendCodeList = new ArrayList<BackInterestPool>();
		Map<String,Object> map = new HashMap<String, Object>();
		
		String city = so.getAddrCity();
		if (StringUtils.isNotEmpty(city)) {
			String c = "%" + city.replace(",", "%|%") +"%";
			so.setAddrCity(c);
		}
		StaticMethodUtil.getCondition(so, map, StaticMethodUtil.getBackInterestApprovalStatus());
		
		backiIdAndLendCodeList = backInterestCommonDao.findBackiIdAndLendCode(map);
		BackInterestLog bil = StaticMethodUtil.madeApproval(bip, BacksmsState.DHXSPTH.value, BacksmsState.DHX.value);
		String userId = UserUtils.getUserId();
		String userName = UserUtils.getUser(userId).getName();
		String errorMesg = "";

		for (BackInterestPool bbi : backiIdAndLendCodeList) {
			
			// 排他条件(bbi中默认含有verTime排他信息)
			bbi.setVerState(StaticMethodUtil.getBackInterestApprovalStatus());
			
			// 生成历史留痕
			Check ch = StaticMethodUtil.madeHistory(userId, userName, bbi.getLendCode(), bil.getType(), FortuneLogNode.INTEREST_APPROVAL.getName(), bbi.getBackiId(),
					GlobalConstant.DHXSP_LIST+""+GlobalConstant.BRANCH + bil.getType() +";"+GlobalConstant.UPDATE_ST + BacksmsState.getBacksmsState(bil.getDictBackiStatus()));
			
			bil.setBackiId(bbi.getBackiId());
			bil.setId(IdGen.uuid());
			bbi.setBackMoneyStatus(bil.getDictBackiStatus());
			bbi.setReturnReason(bil.getCheckExamine());
			bbi.preUpdate();
			
			try {
				/*
				 *  如果短信状态(退回重放标识)为空或者短信状态的值为2，那么证明此数据没有发送过短信，不包括人为修改数据库值。
				 *  如果退回重放标识的值为1，不发送短信
				 *  只有审批通过以后发送短信，切记就是付款方式为资金托管的回息数据不发送短信
				 */
				if((StringUtils.isEmpty(bbi.getSmsStatus()) || !bbi.getSmsStatus().equals(YoN.SHI.value)) 
							&& bip.getCheckExaminetype().equals(YoN.SHI.value) && !bbi.getApplyPay().equals(PayMent.ZJTG.value)){
					
					TelphoneMessage tel = backInterestCommonService.getCustomerMesg(bbi.getBackiId());
					errorMesg += backInterestCommonService.doSubmit(bbi, bil, ch, tel);
				}else{
					errorMesg += backInterestCommonService.doSubmit(bbi, bil, ch);
				}
				
			} catch (Exception e) {
				
				errorMesg += "[出借编号]"+bbi.getLendCode()+"[批量提交待回息审批失败]<br/>"+e.getMessage();
				logger.error("[批量回息审批提交失败]"+bbi.getBackiId(), e);
				e.printStackTrace();
				backInterestCommonService.addExceptionMesgs(bbi.getBackiId(), e, "[回息编号]"+bbi.getBackiId()+"[批量提交待回息审批失败]", FortuneLogNode.INTEREST_APPROVAL.getCode());
			}
		}
		if(("").equals(errorMesg)){
			return "true";
		}
		return errorMesg;
	}
}