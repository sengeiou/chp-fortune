package com.creditharmony.fortune.deduct.facade;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditharmony.core.deduct.bean.in.DeductReq;
import com.creditharmony.core.fortune.type.DeductState;
import com.creditharmony.core.fortune.type.FortuneLogLevel;
import com.creditharmony.core.fortune.type.FortuneLogNode;
import com.creditharmony.core.service.CoreManager;
import com.creditharmony.fortune.common.FortuneExceptionUtil;
import com.creditharmony.fortune.common.service.CheckManager;
import com.creditharmony.fortune.constants.ThreadPoolConstant;
import com.creditharmony.fortune.deduct.dao.TheDayDeductDao;
import com.creditharmony.fortune.deduct.entity.ThedayDeductPool;
import com.creditharmony.fortune.deduct.entity.ext.BaseExportModel;
import com.creditharmony.fortune.deduct.entity.ext.DeductPoolEx;
import com.creditharmony.fortune.deduct.service.DeductReqManager;
import com.creditharmony.fortune.deduct.service.TheDayDeductManager;
import com.google.common.collect.Lists;

/**
 * 划扣业务Service
 * @author 韩龙
 * @Create In 2015年11月20日 
 * @version 3.0
 */
@Service
public class TheDayDeductFacade extends CoreManager<TheDayDeductDao, ThedayDeductPool>{
	
	@Autowired
	private CheckManager checkManager;
	
	@Autowired
	private DeductReqManager deductReqManager;
	
	@Autowired
	private TheDayDeductManager theDayDeductManager;
	
	private final ExecutorService executor = Executors.newFixedThreadPool(ThreadPoolConstant.FORTUNE_POOL_NUM);
	
	/**
	 * 线上分天划扣发送数据
	 * 2016年1月26日
	 * By 韩龙
	 * @param deductPoolExt
	 * @param ids
	 */
	/*public List<String> sendDeduct(DeductPoolEx deductPoolExt, String ids,String type) {
		List<String> massge = Lists.newArrayList();
		if (ids == null) {
			return null;
		}
		String[] applyCodes = ids.split(",");
		for (String str : applyCodes) {
			ThedayDeductPool tdp = new ThedayDeductPool();
			String[] strArray = str.split("_");
			String code = strArray[0];
			String verTime = strArray[1];
			tdp.setId(code);
			tdp.setVerTime(verTime);
			tdp = dao.get(tdp);
			// 排它
			if(tdp == null){
				String info = "出借编号【" + code + "】:已被其它业务人员处理过";
				logger.debug(info);
				massge.add(info);
				continue;
			}
			if(type.equals(DeductState.ECXSHK.value)){
				//二次线上划扣
				logger.debug(DeductState.getDeductState(type));
				tdp.setDictDeductStatus(DeductState.ECXSHK.value);
			}else{
				//线上划扣
				logger.debug(DeductState.getDeductState(type));
				tdp.setDictDeductStatus(DeductState.XSHK.value);
			}
			
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("theDayId", code);
			BaseExportModel baseExportModel = super.dao.getBaseExportModel(map);
			if(baseExportModel == null){
				continue;
			}
			// 组装划扣请求参数
			DeductReq recReq = deductReqManager.setDeductReqInfo(baseExportModel,null,"_分天划扣");
			// 转成分
			int big = new BigDecimal(baseExportModel.getDeductMoney()).intValue();
			// 重新设置划扣规则
			recReq.setRule(tdp.getJumpRule());
			recReq.setAmount(new BigDecimal(big));
			recReq.setRefId(code);
			String messge = TaskService.validate(recReq);
			if(messge.length() > 0){
				logger.debug(DeductState.getDeductState(type) + "失败原因:" + messge);
				massge.add(messge);
			}else{
				FortuneDeductReq fortuneDeductReq = new FortuneDeductReq();
				deductReqManager.addDeductReq(recReq, fortuneDeductReq);
				// 更新划扣申请池状态
				deductApplyDao.update(dp);
				// 更新分天划扣表状态
				super.dao.update(tdp);
			}
			try {
				theDayDeductManager.addTask(massge, code, tdp, recReq);
				// 全程流痕
				checkManager.addCheck(recReq.getBusinessId(),"分天划扣线上划扣", 
						"线上划扣",recReq.getRefId(),FortuneLogNode.DEDUCT_THEDAY);
			} catch (Exception e) {
				massge.add(e.getMessage());
				logger.error("提交划扣任务并保存划扣记录失败：" + e.getMessage(), e);
				FortuneExceptionUtil.insertException(FortuneExceptionUtil.newException(e, code,
						FortuneLogNode.DEDUCT_THEDAY.getCode(), FortuneLogLevel.RED.value, 
						"提交划扣任务并保存划扣记录失败"));
			}
		}
		return massge;
	}*/
	
	/**
	 * 线上分天划扣发送数据
	 * 2016年1月26日
	 * By 韩龙
	 * @param deductPoolExt
	 * @param ids
	 */
	public List<String> sendDeductThread(DeductPoolEx deductPoolExt, String ids,final String type) {
		List<String> massge = Lists.newArrayList();
		if (ids == null) {
			return massge;
		}
		String[] applyCodes = ids.split(",");
		CompletionService<String> completionService = new ExecutorCompletionService<String>(executor);
		for (final String str : applyCodes) {
			// 开启线程
			completionService.submit(new Callable<String>() {
				@Override
				public String call() throws Exception {
					StringBuffer returnStr = new StringBuffer();
					ThedayDeductPool tdp = new ThedayDeductPool();
					String[] strArray = str.split("_");
					String code = strArray[0];
					String verTime = strArray[1];
					tdp.setId(code);
					tdp.setVerTime(verTime);
					tdp = dao.get(tdp);
					// 排它
					if(tdp == null){
						String info = "出借编号【" + code + "】:已被其它业务人员处理过";
						logger.debug(info);
						returnStr.append(info);
						return returnStr.toString();
					}
					if(type.equals(DeductState.ECXSHK.value)){
						//二次线上划扣
						logger.debug(DeductState.getDeductState(type));
						tdp.setDictDeductStatus(DeductState.ECXSHK.value);
					}else{
						//线上划扣
						logger.debug(DeductState.getDeductState(type));
						tdp.setDictDeductStatus(DeductState.XSHK.value);
					}
					
					Map<String,Object> map = new HashMap<String,Object>();
					map.put("theDayId", code);
					BaseExportModel baseExportModel = dao.getBaseExportModel(map);
					if(baseExportModel == null){
						logger.debug("获取出借基本银信息或银行卡信息为空");
						returnStr.append("获取出借基本银信息或银行卡信息为空");
						return returnStr.toString();
					}
					// 组装划扣请求参数
					DeductReq recReq = deductReqManager.setDeductReqInfo(baseExportModel,null,"_分天划扣");
					// 转成分
					int big = new BigDecimal(baseExportModel.getDeductMoney()).intValue();
					// 重新设置划扣规则
					recReq.setRule(tdp.getJumpRule());
					recReq.setAmount(new BigDecimal(big));
					recReq.setRefId(code);
		/*			String messge = TaskService.validate(recReq);
					if(messge.length() > 0){
						logger.debug(DeductState.getDeductState(type) + "失败原因:" + messge);
						massge.add(messge);
					}else{
						FortuneDeductReq fortuneDeductReq = new FortuneDeductReq();
						deductReqManager.addDeductReq(recReq, fortuneDeductReq);
						// 更新划扣申请池状态
						deductApplyDao.update(dp);
						// 更新分天划扣表状态
						super.dao.update(tdp);
					}*/
					try {
						theDayDeductManager.addTask(returnStr, code, tdp, recReq);
						// 全程流痕
						checkManager.addCheck(recReq.getBusinessId(),"分天划扣线上划扣", 
								"线上划扣",recReq.getRefId(),FortuneLogNode.DEDUCT_THEDAY);
					} catch (Exception e) {
						returnStr.append(e.getMessage());
						logger.error("提交划扣任务并保存划扣记录失败：" + e.getMessage(), e);
						FortuneExceptionUtil.insertException(FortuneExceptionUtil.newException(e, code,
								FortuneLogNode.DEDUCT_THEDAY.getCode(), FortuneLogLevel.RED.value, 
								"提交划扣任务并保存划扣记录失败"));
					}
					return returnStr.toString();
				}
			});
		}
		// 组装返回结果
		for(int i =0; i< applyCodes.length; i++){
			try{
				Future<String> future = completionService.take();
				if(future.get() != null && !"".equals(future.get())){
					massge.add(future.get());
				}
			}catch(Exception e){
				logger.error("completionService.take()失败",e);
			}				
		}
		return massge;
	}
}

