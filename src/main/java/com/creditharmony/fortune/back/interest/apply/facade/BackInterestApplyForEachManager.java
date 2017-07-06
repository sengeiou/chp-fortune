package com.creditharmony.fortune.back.interest.apply.facade;

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

/**
 * 批量回息申请
 * @Class Name BackInterestApplyForEachManager 
 * @author 李志伟
 * @Create In 2016年4月29日
 */
@Service
public class BackInterestApplyForEachManager {
	
	/**
	 * 日志对象
	 */
	protected Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private BackInterestCommonDao backInterestCommonDao;
	@Autowired
	private BackInterestCommonService backInterestCommonService;
	
	/**
	 * 批量回息申请拼装条件
	 * 2016年4月8日
	 * by 李志伟
	 * @param bip
	 * @param so
	 * @return 
	 */
	public String assemblyCondition(BackInterestPool bip, SearchObject so){
		
		List<BackInterestPool> backiIdAndLendCodeList = new ArrayList<BackInterestPool>();
		Map<String,Object> map = new HashMap<String, Object>();
		
		String city = so.getAddrCity();
		if (StringUtils.isNotEmpty(city)) {
			String c = "%" + city.replace(",", "%|%") +"%";
			so.setAddrCity(c);
		}
		StaticMethodUtil.getCondition(so, map, StaticMethodUtil.getBackInterestApplyStatus());
		//gaoxu  2017-3-25 13:08:01  区别到期回息与非到期回息
		map.put("notinterestReturn", "1");
		backiIdAndLendCodeList = backInterestCommonDao.findBackiIdAndLendCode(map);
		
		String errorMesg = "";
		bip.setCheckExaminetype(YoN.SHI.value);
		BackInterestLog bil = StaticMethodUtil.madeApproval(bip, "" , BacksmsState.DHXSQQR.value);
		String userId = UserUtils.getUserId();
		String userName = UserUtils.getUser(userId).getName();
		
		for (BackInterestPool bbi : backiIdAndLendCodeList) {
			
			// 排他条件
			bbi.setVerState(StaticMethodUtil.getBackInterestApplyStatus());
			// 生成历史留痕
			Check ch = StaticMethodUtil.madeHistory(userId, userName, bbi.getLendCode(), bil.getType(), FortuneLogNode.INTEREST_APPLY.getName(), bbi.getBackiId(), 
					GlobalConstant.DHXSQ_LIST+""+GlobalConstant.BRANCH + bil.getType() + ";" + GlobalConstant.UPDATE_ST+""+BacksmsState.getBacksmsState(bil.getDictBackiStatus()));
			
			// 获取平台标识
			String platFlag = backInterestCommonService.getPlatFlag(bbi.getPlatFlag());
			if(StringUtils.isNotEmpty(platFlag)){
				bbi.setPlatFlag(platFlag);
			}
			
			bil.setBackiId(bbi.getBackiId());
			bil.setId(IdGen.uuid());
			bbi.setBackMoneyStatus(bil.getDictBackiStatus());
			bbi.setBackMoneyDay(bip.getBackMoneyDay());
			bbi.setReturnReason(bil.getCheckExamine());
			bbi.preUpdate();
			try {
				errorMesg += backInterestCommonService.doSubmit(bbi, bil, ch);
			
			} catch (Exception e) {
				errorMesg += "[出借编号]"+bbi.getLendCode()+"[批量提交回息申请失败]<br/>";
				logger.error("[批量提交回息申请失败:]"+e.getMessage(), e);
				backInterestCommonService.addExceptionMesgs(bbi.getBackiId(), e, "[回息编号]"+bbi.getBackiId()+"[批量提交回息申请失败]", FortuneLogNode.INTEREST_APPLY.getCode());
			}
		}
		if(("").equals(errorMesg)){
			return "true";
		}
		return errorMesg;
	}
}
