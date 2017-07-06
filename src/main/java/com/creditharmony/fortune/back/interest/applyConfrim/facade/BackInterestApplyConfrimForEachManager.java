package com.creditharmony.fortune.back.interest.applyConfrim.facade;

import java.math.BigDecimal;
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
import com.creditharmony.core.users.util.UserUtils;
import com.creditharmony.fortune.back.interest.common.dao.BackInterestCommonDao;
import com.creditharmony.fortune.back.interest.common.service.BackInterestCommonService;
import com.creditharmony.fortune.back.interest.contants.GlobalConstant;
import com.creditharmony.fortune.back.interest.entity.BackInterestLog;
import com.creditharmony.fortune.back.interest.entity.BackInterestPool;
import com.creditharmony.fortune.back.interest.entity.SearchObject;
import com.creditharmony.fortune.back.interest.util.StaticMethodUtil;
import com.creditharmony.fortune.back.interest.util.UploadFileCheck;
import com.creditharmony.fortune.common.entity.Check;
import com.creditharmony.fortune.template.entity.backInterest.UploadMoney;

/**
 * 批量回息申请确认
 * @Class Name BackInterestApplyConfrimForEachManager 
 * @author 李志伟
 * @Create In 2016年4月29日
 */
@Service
public class BackInterestApplyConfrimForEachManager {
	
	/**
	 * 日志对象
	 */
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private BackInterestCommonDao backInterestCommonDao;
	@Autowired
	private BackInterestCommonService backInterestCommonService;
	
	/**
	 * 批量回息申请确认
	 * 2015年12月7日
	 * by 李志伟
	 * @param bip
	 * @param so
	 * @return
	 */
	public String bacthApplyConfrim(BackInterestPool bip, SearchObject so) {
		
		List<BackInterestPool> backiIdAndLendCodeList = new ArrayList<BackInterestPool>();
		Map<String,Object> map = new HashMap<String, Object>();
		String city = so.getAddrCity();
		if (StringUtils.isNotEmpty(city)) {
			String c = "%" + city.replace(",", "%|%") +"%";
			so.setAddrCity(c);
		}
		
		String userId = UserUtils.getUserId();
		String userName = UserUtils.getUser(userId).getName();
		
		StaticMethodUtil.getCondition(so, map, StaticMethodUtil.getBackInterestApplyConfrimStatus());
		//gaoxu  2017-3-25 13:08:01  区别到期回息与非到期回息
		map.put("notinterestReturn", "1");
		backiIdAndLendCodeList = backInterestCommonDao.findBackiIdAndLendCode(map);
		BackInterestLog bil  = StaticMethodUtil.madeApproval(bip, BacksmsState.DHXSQQRTH.value, BacksmsState.DHXSP.value);
		
		
		String errorMesg = "";
		for (BackInterestPool bbi : backiIdAndLendCodeList) {
			
			// 排他条件
			bbi.setVerState(StaticMethodUtil.getBackInterestApplyConfrimStatus());
			
			// 生成历史留痕
			Check ch = StaticMethodUtil.madeHistory(userId, userName, bbi.getLendCode(), bil.getType(), FortuneLogNode.INTEREST_APPLYCONFRIM.getName(), bbi.getBackiId(), GlobalConstant.DHXSQQR_LIST + "" + GlobalConstant.BRANCH+""+bil.getType() + ";" +
					GlobalConstant.UPDATE_ST + BacksmsState.getBacksmsState(bil.getDictBackiStatus()));
			
			bil.setBackiId(bbi.getBackiId());
			bil.setId(IdGen.uuid());
			bbi.setBackMoneyStatus(bil.getDictBackiStatus());
			bbi.setBackMoneyDay(bip.getBackMoneyDay());
			bbi.setReturnReason(bil.getCheckExamine());
			bbi.setBackRealMoney(bip.getBackRealMoney());
			bbi.preUpdate();
			try {
				errorMesg += backInterestCommonService.doSubmit(bbi, bil, ch);
			
			} catch (Exception e) {
				errorMesg += "[出借编号]："+bbi.getLendCode()+"[批量提交回息申请确认失败]<br/>";
				e.printStackTrace();
				logger.error("批量回息申请确认提交失败", e);
				backInterestCommonService.addExceptionMesgs(bbi.getBackiId(), e, "[回息编号]"+bbi.getBackiId()+"[批量提交回息申请确认失败]", FortuneLogNode.INTEREST_APPLYCONFRIM.getCode());
			}
		}
		if(("").equals(errorMesg)){
			return "true";
		}
		return errorMesg;
	}
	
	/**
	 * 上传回息金额
	 * 2016年5月19日
	 * by 李志伟
	 * @param list
	 * @return
	 */
	public String uploadMoney(List<UploadMoney> list){
		
		String mesg = "";
		BackInterestPool pool = new BackInterestPool();
		String userId = UserUtils.getUserId();
		String userName = UserUtils.getUser(userId).getName();
		
		for (UploadMoney im : list) {
			BackInterestPool bpb = backInterestCommonDao.getBackInterestMesgById(im.getBackiId());
			boolean chek = UploadFileCheck.uploadMoneyFileCheck(im);
			if(chek){
				continue;
			}
			
			if(StringUtils.isEmpty(im.getBackiId()) || null == im.getBackRealMoney()){
				mesg += "文件中存在序列号为空或当期应回金额为空的数据，系统无法进行处理</br>";
				continue;
			}
			pool.setBackiId(im.getBackiId());
			pool.setLendCode(im.getLendCode());
			pool.setVerState(StaticMethodUtil.getBackInterestApplyConfrimStatus());
			pool.setVerTime(bpb.getVerTime());
			pool.setBackRealMoney(new BigDecimal(im.getBackRealMoney()));
			pool.preUpdate();
			Check ch = StaticMethodUtil.madeHistory(userId, userName, im.getLendCode(), GlobalConstant.UPD, FortuneLogNode.INTEREST_APPLYCONFRIM.getName(), pool.getBackiId(), GlobalConstant.DHXSQQR_LIST +""+ GlobalConstant.UPDATE_MONEY+";"+GlobalConstant.UPDATE_MN+":"+ pool.getBackRealMoney());
			
			try {
				mesg += backInterestCommonService.uploadBackInterestMoney(pool, ch);
			} catch (Exception e) {
				mesg += "[出借编号]"+im.getLendCode()+"[待回息申请确认上传金额失败]<br/>"+e.getMessage();
				logger.error("待回息申请确认上传回息金额失败", e);
				e.printStackTrace();
				backInterestCommonService.addExceptionMesgs(pool.getBackiId(), e, "[回息编号]"+pool.getBackiId()+"[待回息申请确认上传金额失败]", FortuneLogNode.INTEREST_APPLYCONFRIM.getCode());
			}
		}
		return mesg;
	}
}