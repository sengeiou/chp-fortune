package com.creditharmony.fortune.deduct.proxy;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.creditharmony.bpm.utils.SpringUtil;
import com.creditharmony.common.util.IdGen;
import com.creditharmony.common.util.SpringContextHolder;
import com.creditharmony.core.deduct.bean.out.FortuneDeductResult;
import com.creditharmony.core.deduct.type.ResultType;
import com.creditharmony.core.fortune.type.DeductState;
import com.creditharmony.core.fortune.type.FortuneLogLevel;
import com.creditharmony.core.fortune.type.FortuneLogNode;
import com.creditharmony.core.mapper.JsonMapper;
import com.creditharmony.fortune.common.FortuneExceptionUtil;
import com.creditharmony.fortune.common.dao.FortuneExceptionDao;
import com.creditharmony.fortune.common.entity.FortuneException;
import com.creditharmony.fortune.customer.dao.LoanApplyDao;
import com.creditharmony.fortune.customer.entity.LoanApply;
import com.creditharmony.fortune.deduct.common.DeductUtils;
import com.creditharmony.fortune.deduct.entity.DeductPool;
import com.creditharmony.fortune.deduct.entity.ThedayDeductPool;
import com.creditharmony.fortune.deduct.facade.CommonFacade;
import com.creditharmony.fortune.deduct.remote.DeductResultServiceMq;
import com.creditharmony.fortune.deduct.service.DeductManager;
import com.creditharmony.fortune.deduct.service.DeductResultMQManager;
import com.creditharmony.fortune.deduct.service.TheDayDeductManager;
import com.creditharmony.fortune.triple.system.entity.ext.IntDeliveryEx;
import com.creditharmony.fortune.triple.system.facade.SendTripleInfoFacade;

/**
 * 划扣结果返回实现接口
 * @Class Name DeductResultProxy
 * @author 韩龙
 * @Create In 2016年1月31日
 */
public class DeductResultProxy implements DeductResultServiceMq {
	
	/**
	 * 初始化logger对象
	 */
	protected Logger logger = LoggerFactory.getLogger(DeductResultProxy.class);
	
	
	private DeductResultMQManager deductResultMQManager = SpringContextHolder.getBean(DeductResultMQManager.class);
	// 交割
	private SendTripleInfoFacade sendTripleInfoFacade = SpringContextHolder.getBean(SendTripleInfoFacade.class);
	// 划扣公共
	private CommonFacade commonFacade = SpringContextHolder.getBean(CommonFacade.class);
	// 划扣业务Manager
	private DeductManager deductManager = SpringContextHolder.getBean(DeductManager.class);
	// 分天划扣业务Manager
	private TheDayDeductManager theDayDeductManager = SpringContextHolder.getBean(TheDayDeductManager.class);
	// 出借
	private LoanApplyDao loanApplyDao  = SpringContextHolder.getBean(LoanApplyDao.class);
	
	/**
	 * 返回单个结果
	 */
	public boolean execute(FortuneDeductResult fortuneDeductResult) {
/*		List<FortuneDeductResult> list = Lists.newArrayList();
		list.add(fortuneDeductResult);
		deductResultMQManager.saveFortuneDeductResult(list);
		logger.debug("execute 执行划扣结果更新..........");*/
		boolean result = false;
		// 判断参数是否为空
	/*	if(fortuneDeductResult != null){
			//系统功能ID
			String deductSysIdType = fortuneDeductResult.getDeductSysIdType();
			//财富回款
			if(DeductWays.CF_02.getCode().equals(deductSysIdType)){
				result=listManager.updateBackMonyResult(fortuneDeductResult);
			}else if(DeductWays.CF_03.getCode().equals(deductSysIdType)){
				result=excuteBackInterestManager.updateBackInterestResult(fortuneDeductResult);//财富回息
			}else  if(DeductWays.CF_01.getCode().equals(deductSysIdType)){
				result = deductResultMQManager.header(fortuneDeductResult);//财富划扣
			}
		}*/
		return result;
	}

	/**
	 * 返回结果集合
	 */
	public void executeBatch(List<FortuneDeductResult> fortuneDeductResultList) {
		for (FortuneDeductResult fortuneDeductResult : fortuneDeductResultList) {
			logger.debug("保存MQ返回的结果信息：" + JsonMapper.toJsonString(fortuneDeductResult));
			try {
				deductResultMQManager.saveFortuneDeductResult(fortuneDeductResult);
			} catch (Exception e) {
				FortuneException forException = new FortuneException();
				forException.setLoanCode(fortuneDeductResult.getLendCode());
				forException.setMessage(e.getMessage());
				forException.setStackTrace(ExceptionUtils.getFullStackTrace(e));
				forException.setNode("receivedMQ");
				forException.setImportantLevel(FortuneLogLevel.RED.value);
				forException.setRemark("MQ返回的结果保存失败");
				
//				forException.preInsert();
				forException.setId(IdGen.uuid());
				forException.setCreateBy("MQ");
				forException.setCreateTime(new Date());
				forException.setModifyBy("MQ");
				forException.setModifyTime(new Date());
				FortuneExceptionDao forDao = SpringUtil.getCtx().getBean(FortuneExceptionDao.class);
				forDao.insert(forException);
				logger.debug("保存MQ返回的结果保存失败：" + e.getMessage());
			}
		}
		executeMqBatch(fortuneDeductResultList);
	}

	/**
	 * 跑漏返回结果集合
	 */
	public void executeMqBatch(List<FortuneDeductResult> fortuneDeductResultList) {
		logger.debug("executeBatch 执行划扣结果更新..........");
		// 判断参数是否为空
		if(fortuneDeductResultList != null ){
			// 遍历结果集合
			for (FortuneDeductResult fortuneDeductResult : fortuneDeductResultList) {
				if(fortuneDeductResult != null){
					StringBuffer smsFlag = new StringBuffer();
				    try {
				    	deductResultMQManager.executeBatch(fortuneDeductResult,smsFlag);
					} catch (Exception e) {
						logger.error("财富划扣更新失败,出借编号【"+fortuneDeductResult.getLendCode()+"】;",e);
						deductResultMQManager.updateDeductResult(fortuneDeductResult);
						FortuneExceptionUtil.insertExceptionBatch(FortuneExceptionUtil.newException(e, fortuneDeductResult.getLendCode(), 
								FortuneLogNode.DEDUCT_BALANCE.getCode(),
								FortuneLogLevel.YELLOW.value, e.getMessage()));
					}
				    // 发送短信
				    if(smsFlag.equals("true")){
				    	deductAfater(fortuneDeductResult);
				    }
				}
			}
		}
	}
	
	/**
	 * 划扣成功发短信
	 * 2016年5月28日
	 * By 韩龙
	 * @param fortuneDeductResult
	 */
	public void deductAfater(FortuneDeductResult fortuneDeductResult){
		String lendCode = "";
		String custCode = "";
		String status  = "" ;
		try {
			if (fortuneDeductResult.getTheDayId() == null) {
				lendCode = DeductUtils.isNull(fortuneDeductResult.getLendCode());
				DeductPool dp = new DeductPool();
				dp.setApplyCode(lendCode);
				dp = deductManager.get(dp);
				custCode = dp.getCustCode();
			}else{
				ThedayDeductPool tdp = new ThedayDeductPool();
				String theDayId = fortuneDeductResult.getTheDayId();
				tdp.setId(theDayId);
				tdp = theDayDeductManager.get(tdp);
				if(theDayDeductManager.checkTheDayList(tdp.getDeductApplyId())){
					return;
				}
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("id", theDayId);
				Map<String,String> result = theDayDeductManager.getTheDaysById(map);
				if(result == null){
					return;
				}
				// 获取出借编号
				lendCode = result.get("lend_code");
				DeductPool dp = new DeductPool();
				dp.setApplyCode(lendCode);
				dp = deductManager.get(dp);
				// 获取客户编号
				custCode = dp.getCustCode();
				
			}
			if(ResultType.POXY_SUCCESS.getCode().equals(fortuneDeductResult.getDeductResultCode())){
				LoanApply loanApply = new LoanApply();
				loanApply.setApplyCode(lendCode);
				loanApply = loanApplyDao.get(loanApply);
				status = DeductState.HKCG.value;
				// 三网检验首单
				sendTripleInfoFacade.tripleInfo(custCode, loanApply.getApplyCode());
				// 发送短信
				commonFacade.smsInfo(lendCode, status, loanApply);
				// 制作文件信息
				commonFacade.makeFileInfo(lendCode);
			}
		} catch (Exception e) {
			this.logger.error("MQ发送短信失败,出借编号【"+lendCode+"】",e);
		}
	}
}
