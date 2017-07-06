package com.creditharmony.fortune.back.interest.apply.facade;

import java.math.BigDecimal;
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
import com.creditharmony.core.fortune.type.BackIsInterest;
import com.creditharmony.core.fortune.type.BacksmsState;
import com.creditharmony.core.fortune.type.FortuneLogNode;
import com.creditharmony.core.fortune.type.YoN;
import com.creditharmony.core.users.util.UserUtils;
import com.creditharmony.fortune.back.interest.common.dao.BackInterestCommonDao;
import com.creditharmony.fortune.back.interest.common.dao.BackReturnInterestCommonDao;
import com.creditharmony.fortune.back.interest.common.service.BackInterestCommonService;
import com.creditharmony.fortune.back.interest.common.service.BackReturnInterestCommonService;
import com.creditharmony.fortune.back.interest.contants.GlobalConstant;
import com.creditharmony.fortune.back.interest.entity.BackInterestLog;
import com.creditharmony.fortune.back.interest.entity.BackInterestPool;
import com.creditharmony.fortune.back.interest.entity.SearchObject;
import com.creditharmony.fortune.back.interest.util.StaticMethodUtil;
import com.creditharmony.fortune.back.interest.util.UploadFileCheck;
import com.creditharmony.fortune.common.entity.Check;
import com.creditharmony.fortune.template.entity.backInterest.UploadIsInterest;
import com.creditharmony.fortune.template.entity.backInterest.UploadMoney;

/**
 * 批量回息申请
 * @Class Name BackInterestApplyForEachManager 
 * @author 李志伟
 * @Create In 2016年4月29日
 */
@Service
public class BackReturnInterestApplyForEachManager {
	
	/**
	 * 日志对象
	 */
	protected Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private BackReturnInterestCommonDao backReturnInterestCommonDao;
	@Autowired
	private BackReturnInterestCommonService backReturnInterestCommonService;
	
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
		//到期回息专用区分数据  gaoxu   2017-3-23 14:10:38
				map.put("interestReturn", "1");
		backiIdAndLendCodeList = backReturnInterestCommonDao.findBackiIdAndLendCode(map);
		
		String errorMesg = "";
		bip.setCheckExaminetype(YoN.SHI.value);
		BackInterestLog bil = StaticMethodUtil.madeApproval(bip, "" , BacksmsState.DHXSQQR.value);
		String userId = UserUtils.getUserId();
		String userName = UserUtils.getUser(userId).getName();
		
		for (BackInterestPool bbi : backiIdAndLendCodeList) {
			
			// 排他条件
			bbi.setVerState(StaticMethodUtil.getBackInterestApplyStatus());
			// 生成历史留痕
			Check ch = StaticMethodUtil.madeHistory(userId, userName, bbi.getLendCode(), bil.getType(), FortuneLogNode.RETURN_INTEREST_APPLY.getName(), bbi.getBackiId(), 
					GlobalConstant.RETURN_DHXSQ_LIST+""+GlobalConstant.BRANCH + bil.getType() + ";" + GlobalConstant.UPDATE_ST+""+BacksmsState.getBacksmsState(bil.getDictBackiStatus()));
			
			// 获取平台标识
			String platFlag = backReturnInterestCommonService.getPlatFlag(bbi.getBackiId());
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
				errorMesg += backReturnInterestCommonService.doSubmit(bbi, bil, ch);
			
			} catch (Exception e) {
				errorMesg += "[出借编号]"+bbi.getLendCode()+"[批量提交到期回息申请失败]<br/>";
				logger.error("[出借编号]"+bbi.getLendCode()+"[到期回息id]"+bbi.getBackiId()+"[批量提交到期回息申请失败:]"+e.getMessage(), e);
				backReturnInterestCommonService.addExceptionMesgs(bbi.getBackiId(), e, "[回息编号]"+bbi.getBackiId()+"[批量提交到期回息申请失败]", FortuneLogNode.RETURN_INTEREST_APPLY.getCode());
			}
		}
		if(("").equals(errorMesg)){
			return "true";
		}
		return errorMesg;
	}
	
	
	/**
	 * 上传是否回息
	 * 2016年5月19日
	 * by 李志伟
	 * @param list
	 * @return
	 */
	public String uploadIsInterestReturn(List<UploadIsInterest> list){
		
		String mesg = "";
		BackInterestPool pool = new BackInterestPool();
		String userId = UserUtils.getUserId();
		String userName = UserUtils.getUser(userId).getName();
		
		for (UploadIsInterest im : list) {
			BackInterestPool bpb = backReturnInterestCommonDao.getBackInterestMesgById(im.getBackiId());
			if(bpb==null||bpb.getVerTime()==null){
				mesg += "[出借编号]"+im.getLendCode()+"[对应的序列号（"+im.getBackiId()+"）在系统中不存在，到期待回息申请上传是否回息失败]<br/>";
				continue;
			}
			boolean chek = UploadFileCheck.uploadIsInterestFileCheck(im);
			if(chek){
				continue;
			}
			
			if(StringUtils.isEmpty(im.getBackiId()) || null == im.getIsInterest()){
				mesg += "文件中存在序列号为空或当期应回金额为空的数据，系统无法进行处理</br>";
				continue;
			}
			pool.setBackiId(im.getBackiId());
			pool.setLendCode(im.getLendCode());
			pool.setVerState(StaticMethodUtil.getBackInterestApplyStatus());
			pool.setVerTime(bpb.getVerTime());
			if("是".equals(im.getIsInterest())){
				pool.setIsInterest(BackIsInterest.YES.value);
			}else{
				pool.setIsInterest(BackIsInterest.NO.value);
			}
			pool.preUpdate();
			Check ch = StaticMethodUtil.madeHistory(userId, userName, im.getLendCode(), GlobalConstant.UPD, FortuneLogNode.RETURN_INTEREST_APPLY.getName(), pool.getBackiId(), GlobalConstant.RETURN_DHXSQ_LIST +""+ GlobalConstant.RETURN_UPDATE_ISINTEREST+";"+GlobalConstant.RETURN_UPDATE_IS+":"+ im.getIsInterest());
			
			try {
				String uploadBackInterestIsInterest = backReturnInterestCommonService.uploadBackInterestIsInterest(pool, ch);
				mesg += uploadBackInterestIsInterest;
			} catch (Exception e) {
				mesg += "[出借编号]"+im.getLendCode()+"[序列号]"+im.getBackiId()+"[到期待回息申请上传是否回息失败]<br/>";
				logger.error("[出借编号]"+im.getLendCode()+"[序列号]"+im.getBackiId()+"[到期待回息申请上传是否回息失败]<br/>", e);
				e.printStackTrace();
				backReturnInterestCommonService.addExceptionMesgs(pool.getBackiId(), e, "[回息编号]"+pool.getBackiId()+"[到期待回息申请上传是否回息失败]", FortuneLogNode.RETURN_INTEREST_APPLY.getCode());
			}
		}
		return mesg;
	}
}
