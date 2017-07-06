package com.creditharmony.fortune.deduct.job;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.core.deduct.TaskService;
import com.creditharmony.core.deduct.bean.in.DeductReq;
import com.creditharmony.core.deduct.bean.out.DeResult;
import com.creditharmony.core.deduct.type.ResultType;
import com.creditharmony.core.fortune.type.DeductDataSendState;
import com.creditharmony.core.fortune.type.FortuneLogLevel;
import com.creditharmony.core.fortune.type.FortuneLogNode;
import com.creditharmony.fortune.common.FortuneExceptionUtil;
import com.creditharmony.fortune.deduct.entity.FortuneDeductReq;
import com.creditharmony.fortune.deduct.service.DeductReqManager;

/**
 * 划扣执行
 * @Class Name BespokeDeductService
 * @author 韩龙
 * @Create In 2016年2月1日
 */
@Service
@Lazy(false)
public class DeductReqService {
	
	/**
	 * 初始化logger对象
	 */
	protected Logger logger = LoggerFactory.getLogger(DeductReqService.class);
	
	@Autowired
	private DeductReqManager deductReqManager;
	
	/**
	 * 划扣发送
	 * 2016年2月1日
	 * By 韩龙
	 */
	@Scheduled(cron = "0 0/18 * * * ?")
	public void start(){
		logger.debug("划扣发送已启动。");
		FortuneDeductReq fortuneDeductReq = new FortuneDeductReq();
		fortuneDeductReq.setStatus(DeductDataSendState.COMMITSB.value);
		List<FortuneDeductReq> deductReqList = deductReqManager.findList(fortuneDeductReq);
		if(deductReqList == null || deductReqList.size()==0){
			logger.debug("划扣发送本次扫描为空");
			return;
		}
		logger.debug("划扣发送本次扫描" + deductReqList.size() + "条");
		for (FortuneDeductReq req : deductReqList) {
			logger.debug("划扣发送内容为:"+req.toString());
			DeductReq deductReq = new DeductReq();
			BeanUtils.copyProperties(req, deductReq);
			try {
				logger.debug("划扣发送----->开始");
				sendDeductReq(deductReq,req);
				logger.debug("划扣发送----->结束");
			} catch (Exception e) {
				logger.error("发送出错信息:" + e.getMessage(),e);
				deductReqManager.updateStatus(deductReq,req,DeductDataSendState.FSSB.value);
				FortuneExceptionUtil.insertException(FortuneExceptionUtil.newException(e, deductReq.getBusinessId(),
						FortuneLogNode.DEDUCT_THEDAY.getCode(), FortuneLogLevel.RED.value, 
						"发送划扣到第三平台失败,失败信息为:"+e.getMessage()));
			}
		}
		
	}
	
	/**
	 * 发送划扣
	 * 2016年4月8日
	 * By 韩龙
	 * @param deductReq
	 */
	@Transactional(value = "fortuneTransactionManager", readOnly = false)
	public void sendDeductReq(DeductReq deductReq,FortuneDeductReq req){
		logger.debug("向第三方发送划扣信息------开始");
		/*DeResult deResult = */
		deductReq.setRemarks(deductReq.getBusinessId()+"_划扣");
		logger.debug("向第三方发送划扣信息增加任务");
		DeResult deResult = TaskService.addTask(deductReq);
		logger.debug("向第三方发送划扣信息增加任务结束");
		if(ResultType.ADD_SUCCESS.getCode().equals(deResult.getReCode())){
			logger.debug("提交发送划扣信息-------开始");
			DeResult res = TaskService.commit(deductReq);
			if(ResultType.ADD_SUCCESS.getCode().equals(res.getReCode())){
				logger.debug("提交发送划扣信息-------结束");
				deductReqManager.updateStatus(deductReq,req,DeductDataSendState.COMMITCG.value);
				logger.debug("更新状态为："+DeductDataSendState.getDeductDataSendState(DeductDataSendState.COMMITCG.value));
				logger.debug("向第三方发送划扣信息------结束");
			}else{
				deductReqManager.updateStatus(deductReq,req,DeductDataSendState.COMMITSB.value);
			}
		}else{
			logger.debug("发送失败回滚-----开始");
			deductReqManager.updateStatus(deductReq,req,DeductDataSendState.FSSB.value);
			logger.debug("发送失败回滚-----结束");
		}
	}
}
