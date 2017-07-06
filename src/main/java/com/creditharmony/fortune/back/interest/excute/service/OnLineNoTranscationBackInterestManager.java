package com.creditharmony.fortune.back.interest.excute.service;

import java.math.BigDecimal;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.common.type.BackResult;
import com.creditharmony.core.deduct.TaskService;
import com.creditharmony.core.deduct.bean.in.DeductReq;
import com.creditharmony.core.deduct.bean.out.DeResult;
import com.creditharmony.core.deduct.type.DeductFlagType;
import com.creditharmony.core.deduct.type.DeductType;
import com.creditharmony.core.deduct.type.DeductWays;
import com.creditharmony.core.deduct.type.ResultType;
import com.creditharmony.core.fortune.type.BackInterestPlat;
import com.creditharmony.core.fortune.type.BacksmsState;
import com.creditharmony.core.fortune.type.DeductDataSendState;
import com.creditharmony.core.fortune.type.FortuneLogNode;
import com.creditharmony.core.fortune.type.PayMent;
import com.creditharmony.fortune.back.interest.common.dao.BackInterestCommonDao;
import com.creditharmony.fortune.back.interest.common.service.BackInterestCommonService;
import com.creditharmony.fortune.back.interest.contants.GlobalConstant;
import com.creditharmony.fortune.back.interest.entity.BackInterestPool;
import com.creditharmony.fortune.back.interest.entity.LineBackInterestObj;
import com.creditharmony.fortune.back.interest.util.StaticMethodUtil;
import com.creditharmony.fortune.common.entity.Check;
import com.creditharmony.fortune.deduct.common.DeductUtils;
import com.creditharmony.fortune.deduct.entity.FortuneDeductReq;
import com.creditharmony.fortune.deduct.service.DeductReqManager;
import com.creditharmony.fortune.platform.service.PlatformGotoRuleManager;

/**
 * 非金账户回息
 * @Class Name ExcuteBackInterestManager 
 * @author 李志伟
 * @Create In 2015年12月23日
 */
@Service
public class OnLineNoTranscationBackInterestManager{

	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private DeductReqManager deductReqManager;
	@Autowired
	private PlatformGotoRuleManager platformGotoRuleManager;
	@Autowired
	private BackInterestCommonService backInterestCommonService;
	@Autowired
	private BackInterestCommonDao backInterestCommonDao;
	
	/**
	 * (非金账户)线上回息入口_2
	 * 2016年2月15日
	 * by 李志伟
	 * @param eb
	 * @param userId
	 * @param userName
	 * @return
	 */
	@Transactional(value = "fortuneTransactionManager", readOnly = false)
	public String noTranscationBackInterest(LineBackInterestObj eb, String userId, String userName) {
		
		BackInterestPool bip = new BackInterestPool();
		bip.setVerState(StaticMethodUtil.getExcuteBackInterestStatus());
		bip.setVerTime(eb.getVerTime());
		bip.setBackiId(eb.getBackiId());
		BackInterestPool bp = backInterestCommonDao.forUpdate(bip);
		if(null == bp){
			return eb.getLendCode()+":已被其他人操作过<br/>";
		}
		
		// 剔除金账户的数据
		if (StringUtils.isNotEmpty((eb.getApplyPay()))
				&& !eb.getApplyPay().equals(PayMent.ZJTG.value)) {
			
			BigDecimal backRealMoney = eb.getBackRealMoney()==null ? BigDecimal.ZERO : eb.getBackRealMoney();
			BigDecimal successMoney = eb.getSuccessMoney()==null ? BigDecimal.ZERO : eb.getSuccessMoney();
			BigDecimal bd = backRealMoney.subtract(successMoney);
			
			int ct = bd.compareTo(BigDecimal.ZERO);
			if(ct==1){// 需要回息的金额大于0
			
				String s = StaticMethodUtil.validates(eb);
				if(s.equals("")){
		
					FortuneDeductReq fortuneDeductReq = new FortuneDeductReq();
					DeResult deResult = new DeResult();
					DeductReq drp = new DeductReq();
					drp = setDeductReqInfo(eb);// 拼装参数
					String val = TaskService.validate(drp);// 信息验证
					
					if (StringUtils.isNotEmpty(val)) {
						return  val + "。<br/>";
					}
					
					try{
						return deductAndUpdatebackmInfoEach(eb, 
								deResult, fortuneDeductReq, drp, userId, userName);
					
					} catch (Exception ex) {
						
						logger.error("Commit失败：", ex);
						backInterestCommonService.addExceptionMesgs(eb.getBackiId(), ex, "出借【" + eb.getLendCode()
								+ "】发送第三方平台失败", FortuneLogNode.INTEREST_EXCUTE.getCode());
						try {
							deductReqManager.updateStatus(drp, fortuneDeductReq,
									DeductDataSendState.COMMITSB.value);
						} catch (Exception e) {
							logger.error("updateStatus失败：", e);
							backInterestCommonService.addExceptionMesgs(eb.getBackiId(), ex, "出借【" + eb.getLendCode()
									+ "】updateStatus失败", FortuneLogNode.INTEREST_EXCUTE.getCode());
						}
						return "出借【" + eb.getLendCode()
								+ "】发送第三方平台失败：<br/>" + ex.getMessage();
						
					}
				}else{
					return  s;
				}
			}else if(ct== 0){// 等于
				return "[出借编号]:" + eb.getLendCode() + "的回息金额为0<br/>";
			}else if(ct==-1){// 小于
				return "[出借编号]:" + eb.getLendCode() + "的回息金额为负数<br/>";
			}
		}else{
			return "[出借编号]"+eb.getLendCode()+"[付款方式是资金托管，不支持非金账户线上回息]</br>";
		}
		return "";
	}
	
	/**
	 * (非金账户)线上回息(向第三方发送数据)__3
	 * 2016年2月15日
	 * by 李志伟
	 * @param view
	 * @param list
	 * @param count
	 * @param userId
	 * @param userName
	 * @return
	 */
	public String deductAndUpdatebackmInfoEach(LineBackInterestObj eb, DeResult deResult, 
			FortuneDeductReq fortuneDeductReq, DeductReq dr, String userId, String userName) {

		String message="";
		// 平台金额校验
		message += DeductUtils.platformCheck(eb.getPlatformId(), dr, eb.getLendCode());
		if(StringUtils.isNotEmpty(message)){
			return message;
		}
		
		try {
			//划扣记录保存
			deductReqManager.addDeductReq(dr, fortuneDeductReq);
			// 添加划扣任务
			deResult = TaskService.addTask(dr);
			if (!ResultType.ADD_SUCCESS.getCode().equals(deResult.getReCode())) {
				message += deResult.getReMge() + "。<br/>";
				return message;
			}
			
			// -------------更新数据库数据---------------
			BackInterestPool pool = new BackInterestPool();
			pool.setBackiId(eb.getBackiId());
			pool.setBackMoneyStatus(BacksmsState.DHXQR.value);
			pool.setPlatformId(eb.getPlatformId());
			pool.setBackResult(BackResult.DELLING.value);
			pool.setBackMoneyDay(new Date());
			pool.setLendCode(eb.getLendCode());
			pool.setVerState(StaticMethodUtil.getExcuteBackInterestStatus());
			pool.setVerTime(eb.getVerTime());
			
			pool.setModifyBy(userName);
			pool.setModifyTime(new Date());
			
			Check ch = StaticMethodUtil.madeHistory(userId, userName, pool.getLendCode(), GlobalConstant.COMMIT,  FortuneLogNode.INTEREST_EXCUTE.getName(), pool.getBackiId(),
					GlobalConstant.DHX_LIST+""+BackInterestPlat.getBackInterestPlat(eb.getPlatformId())+""+ GlobalConstant.ONLINE + ";" + GlobalConstant.SEND_MONEY + dr.getAmount() + ";" + GlobalConstant.UPDATE_ST + BacksmsState.getBacksmsState(BacksmsState.DHXQR.value));
			
			backInterestCommonService.lineSubmit(pool, ch);
			message += deductReqManager.commit(dr,fortuneDeductReq, deResult, eb.getLendCode());
		
		} catch (Exception e) {
			
			logger.error("提交划扣任务commit失败：" + e.getMessage(), e);
			deductReqManager.updateStatus(dr, fortuneDeductReq, DeductDataSendState.COMMITSB.value);
			e.printStackTrace();
			message += "[出借编号]" + eb.getLendCode()+ "[线上回息提交失败]</br>";
			backInterestCommonService.addExceptionMesgs(eb.getBackiId(), e, message, FortuneLogNode.INTEREST_EXCUTE.getCode());
		}
		return message;
	}
	
	/**
	 * 组装线上回息数据
	 * 2016年2月15日
	 * by 李志伟
	 * @param view
	 * @param eb
	 * @return
	 */
	private DeductReq setDeductReqInfo(LineBackInterestObj eb) {
		
		DeductReq drp = new DeductReq();
		drp.setBusinessId(eb.getBackiId());// 主键
		drp.setBillDate(eb.getCurrentBillday());// 账单日
		drp.setAccountName(eb.getAccountName());// 账户名称
		drp.setAccountNo(eb.getAccountNo());// 账号
		BigDecimal suceessMoney = new BigDecimal("0");
		if (eb.getSuccessMoney() != null) {
			suceessMoney = eb.getSuccessMoney();
		}
		BigDecimal backRealMoney = new BigDecimal("0");
		if (eb.getBackRealMoney() != null) {
			backRealMoney = eb.getBackRealMoney();
		}
		
		drp.setAmount(backRealMoney.subtract(suceessMoney));// 金额
		drp.setBankCity(eb.getCity());// 城市
		drp.setBankId(eb.getAccountBank());// 银行ID
		drp.setBankName(eb.getAccountBranch());// 支行名
		drp.setBankProv(eb.getProvince());// 省

		drp.setAccountType(eb.getAccountcardOrbooklet());// 卡或折
		drp.setSysId(DeductWays.CF_03.getCode());// 业务标识ID
		drp.setRequestId(eb.getBackiId());// 回息ID
		drp.setDeductFlag(DeductFlagType.PAY.getCode());// 划扣标志
														// ：0，代收；1，代付
		drp.setIdType(DeductUtils.isNull(eb
				.getDictCustomerCertType()));// 证件类型
		drp.setIdNo(DeductUtils.isNull(eb.getCustomerCertNum()));// 证件编号
		drp.setMobile(DeductUtils.isNull(eb
				.getCustomerMobilephone()));// 手机号
		drp.setRemarks(eb.getLendCode()+"_回息_"+StaticMethodUtil.parseDate(eb.getCurrentBillday(), GlobalConstant.yyyyMMdd));
		
		drp.setRule(eb.getPlatformId() + ":"
				+ DeductType.BATCH.getCode());// 划扣规则
		drp.setVerTime(eb.getVerTime());// 版本时间
		// 下列设置值针对于卡联平台
		/*
			下方代码不要放在支行名上面，以防止其他平台导出支行名被覆盖成空字符串
		 */
		if(eb.getPlatformId().equals(BackInterestPlat.KL.value)){
			drp.setBusObjType(GlobalConstant.FOR_PRIVATE);
			drp.setBranchCode(eb.getBankCode());
			drp.setBankName("");
		}
		return drp;
	}
}