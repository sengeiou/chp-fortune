package com.creditharmony.fortune.deduct.job;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.common.util.DateUtils;
import com.creditharmony.common.util.StringUtils;
import com.creditharmony.core.deduct.TaskService;
import com.creditharmony.core.deduct.bean.in.DeductReq;
import com.creditharmony.core.deduct.bean.out.DeResult;
import com.creditharmony.core.deduct.type.ResultType;
import com.creditharmony.core.exception.ServiceException;
import com.creditharmony.core.fortune.type.DeductDataSendState;
import com.creditharmony.core.fortune.type.DeductState;
import com.creditharmony.core.fortune.type.FortuneLogLevel;
import com.creditharmony.core.fortune.type.FortuneLogNode;
import com.creditharmony.fortune.common.FortuneExceptionUtil;
import com.creditharmony.fortune.deduct.common.DeductUtils;
import com.creditharmony.fortune.deduct.dao.DeductBespokeDao;
import com.creditharmony.fortune.deduct.entity.DeductBespoke;
import com.creditharmony.fortune.deduct.entity.DeductPool;
import com.creditharmony.fortune.deduct.entity.FortuneDeductReq;
import com.creditharmony.fortune.deduct.entity.ThedayDeductPool;
import com.creditharmony.fortune.deduct.service.DeductManager;
import com.creditharmony.fortune.deduct.service.DeductReqManager;
import com.creditharmony.fortune.deduct.service.TheDayDeductManager;
import com.google.common.collect.Lists;

/**
 * 预约划扣订时执行
 * @Class Name BespokeDeductService
 * @author 韩龙
 * @Create In 2016年2月1日
 */
@Service
@Lazy(false)
public class BespokeDeductService {
	
	/**
	 * 初始化logger对象
	 */
	protected Logger logger = LoggerFactory.getLogger(BespokeDeductService.class);
	
	@Autowired
	private DeductBespokeDao deductBespokeDao ;//= SpringContextHolder.getBean(DeductBespokeDao.class);
	
	@Autowired
	private TheDayDeductManager theDayDeductManager;
	
	@Autowired
	private DeductManager deductManager;
	
	@Autowired
	private DeductReqManager deductReqManager;
	
	/**
	 * 预约划扣发送
	 * 2016年2月1日
	 * By 韩龙
	 */
	@Scheduled(cron = "0 0/1 * * * ?")
	public void start(){
		DeductBespoke  db = new DeductBespoke();
		db.setDictDeductStatus(DeductState.YYY.value);
		logger.debug("预约划扣发送.......");
		List<DeductBespoke> deductBespokeList = deductBespokeDao.findList(db);
		if(deductBespokeList == null || deductBespokeList.size() == 0){
			logger.debug("未扫描到预约任务.......");
			return;
		}
		logger.debug("本次扫描到" + deductBespokeList.size() + "条预约任务.......");
		for (DeductBespoke deductBespoke : deductBespokeList) {
			// 判断是否是分天划扣
			if( deductBespoke.getDayDeductId() !=null ){
				logger.debug("分天预约任务分天id:"+ deductBespoke.getDayDeductId());
				/*ThedayDeductPool tdp = new ThedayDeductPool();
				tdp.setDeductApplyId(deductBespoke.getForeginId());
				tdp.setDictDeductStatus(DeductState.HKCLZ.value);*/
				List<String> statusList = Lists.newArrayList();
				statusList.add(DeductState.HKCLZ.value);
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("deductApplyId", deductBespoke.getForeginId());
				map.put("deductStatusList", statusList);
				List<ThedayDeductPool> list = theDayDeductManager.checkProcessingStatus(map);
				// 当前分天划扣是否有正在处理中的，如果有没有回盘结果的本次不执行
				if(list != null && list.size() > 0){
					logger.debug("当前有未回盘结果，不执行发送操作，当前分天id："+ deductBespoke.getDayDeductId());
					continue;
				}
				// 判断当前时间是否在预约时间段中，如果不在则不执行
				if(deductBespoke.getExecutionTimeSegment() !=null && 
						!executionTime(deductBespoke.getExecutionTimeSegment())){
					continue;
				}
				// 拼装请求对象
				try {
					sendDeductReq(deductBespoke);
				} catch (Exception e) {
					logger.error("发送分天划扣到第三平台失败,失败信息为:"+e.getMessage(),e);
					FortuneExceptionUtil.insertException(FortuneExceptionUtil.newException(e, deductBespoke.getForeginId(),
							FortuneLogNode.DEDUCT_BALANCE.getCode(), FortuneLogLevel.RED.value, 
							"发送分天划扣到第三平台失败,失败信息为:"+e.getMessage()));
				}
				logger.debug("发送分天划扣到第三平台");
				continue;
			}
			// 判断当前时间是否在预约时间段中，如果不在则不执行
			if(deductBespoke.getExecutionTimeSegment() !=null && 
					!executionTime(deductBespoke.getExecutionTimeSegment())){
				logger.debug("没有到设定时间段不发送，不执行发送操作.......");
				continue;
			}
			// 拼装请求对象
			try {
				sendDeductReq(deductBespoke);
			} catch (Exception e) {
				logger.error("发送单天划扣到第三平台失败,失败信息为："+e.getMessage(),e);
				FortuneExceptionUtil.insertException(FortuneExceptionUtil.newException(e, deductBespoke.getForeginId(),
						FortuneLogNode.DEDUCT_THEDAY.getCode(), FortuneLogLevel.RED.value, 
						"发送分天划扣到第三平台失败,失败信息为:"+e.getMessage()));
			}
			logger.debug("发送划扣到第三平台");
		}
	}
	
	/**
	 * 更新状态
	 * 2016年4月12日
	 * By 韩龙
	 * @param deductBespokeList
	 */
	@Transactional(value = "fortuneTransactionManager",readOnly=false)
	public void updateStatus(List<DeductBespoke> deductBespokeList){
		logger.debug("更新状态------开始");
		for (DeductBespoke deductBespoke : deductBespokeList) {
			// 修改状态防止下次扫描到
			deductBespoke.setDictDeductStatus("100");
			deductBespokeDao.update(deductBespoke);
		}
		logger.debug("更新状态------开始");
	}
	
	/**
	 * 发送预约划扣
	 * 2016年3月14日
	 * By 韩龙
	 * @param deductBespoke
	 */
	@Transactional(value = "fortuneTransactionManager",readOnly=false,
			propagation=Propagation.REQUIRES_NEW)
	public void sendDeductReq(DeductBespoke deductBespoke){
		logger.debug("发送预约划扣信息为：" + deductBespoke.toString());
		// 拼装请求对象
		DeductReq deductReq = DeductUtils.header(deductBespoke);
		logger.debug("预约划扣发送成功");
		FortuneDeductReq fortuneDeductReq = new FortuneDeductReq();
		logger.debug("保存预约划扣记录");
		deductReqManager.addDeductReq(deductReq, fortuneDeductReq);
		if(deductBespoke.getDayDeductId() != null){
			deductReq.setRemarks(deductBespoke.getLendCode()+"_分天划扣");
		}else{
			deductReq.setRemarks(deductBespoke.getLendCode()+"_划扣");
		}
		DeResult deResult = TaskService.addTask(deductReq);
		if(!deResult.getReCode().equals(ResultType.ADD_SUCCESS.getCode())){
			logger.debug("预约发送划扣返回message：" + deResult.getReMge());
			if(StringUtils.isBlank(deductBespoke.getDayDeductId())){
				logger.debug("单天预约发送划扣失败返回message：" + deResult.getReMge());
				DeductPool dp = new DeductPool();
				dp.setApplyCode(deductBespoke.getLendCode());
				dp.setDictDeductStatus(DeductState.HKSB.value);
				dp.setFailReason(deResult.getReMge());
				dp.setDealTime(DateUtils.getDate("yyyy-MM-dd"));
				logger.debug("单天预约发送划扣失败更新状态为："+DeductState.getDeductState(DeductState.HKSB.value));
				deductManager.update(dp);
			}else{
				logger.debug("分天预约发送划扣失败返回message：" + deResult.getReMge());
				ThedayDeductPool tdp = new ThedayDeductPool();
				tdp.setId(deductBespoke.getDayDeductId());
				tdp.setFailReason(deResult.getReMge());
				tdp.setDeductTime(new Date());
				logger.debug("分天预约发送划扣失败更新状态为："+DeductState.getDeductState(DeductState.HKSB.value));
				tdp.setDictDeductStatus(DeductState.HKSB.value);
				theDayDeductManager.save(tdp);
			}
		}else{
			logger.debug("修改预约划扣状态为：" + DeductState.getDeductState(DeductState.HKCLZ.value));
			deductBespoke.setDictDeductStatus(DeductState.HKCLZ.value);
			deductBespokeDao.update(deductBespoke);
			logger.debug("预约划扣发送提交------开始");
			String reString = "";
			try {
				reString = deductReqManager.commit(deductReq, fortuneDeductReq, deResult, deductBespoke.getForeginId());
				logger.debug("预约划扣提交成功" + reString);
			} catch (Exception e) {
				logger.error("提交划扣任务commit失败：" + e.getMessage(), e);
				deductReqManager.updateStatus(deductReq, fortuneDeductReq, DeductDataSendState.COMMITSB.value);
				throw new ServiceException("提交划扣任务commit失败：" + e.getMessage(),e);
			}
		}
		
	}
	
	/**
	 * 判断当前时间是否在预约时间段中
	 * 2016年2月3日
	 * By 韩龙
	 * @param executionTimeSegment
	 * @return
	 */
	public boolean executionTime(String executionTimeSegment){
		boolean deductF = false;
		Calendar calendar = Calendar.getInstance();
		// 获取当前小时
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		// 获取时间段
		String [] executionTimeSegments = executionTimeSegment.split(",");
		// 判断当前时间是否在预约时间段中
		for (String ets : executionTimeSegments) {
			if(hour >= Integer.valueOf(ets)){
				deductF = true;
				break;
			}
		}
		return deductF;
	}
	
//	public static void main(String[] args) {
//		BespokeDeductService b = new BespokeDeductService();
//		System.out.println(b.executionTime("17"));
//	}
}
