package com.creditharmony.fortune.deduct.job;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.creditharmony.core.deduct.bean.out.FortuneDeductResult;
import com.creditharmony.core.fortune.type.YoN;
import com.creditharmony.fortune.deduct.dao.DeductResultDao;
import com.creditharmony.fortune.deduct.entity.DeductResult;
import com.creditharmony.fortune.deduct.proxy.DeductResultProxy;
import com.google.common.collect.Lists;

/**
 * 扫描MQ反回信息表
 * @Class Name DeductResultService
 * @author 陈广鹏
 * @Create In 2016年4月1日
 */
@Service
@Lazy(false)
public class DeductResultService {
	
	/**
	 * 初始化logger对象
	 */
	protected Logger logger = LoggerFactory.getLogger(DeductResultService.class);
	
	@Autowired
	private DeductResultDao deductResultDao;
	
	/**
	 * 开始
	 * 2016年4月1日
	 * By 陈广鹏
	 */
	@Scheduled(cron = "0 0/2 * * * ?")
	public void start(){
		logger.debug("扫描MQ返回数据表------>开始");
		List<FortuneDeductResult> newList = Lists.newArrayList();
		// 获取接收到没有处理的结果
		DeductResult deductResult = new DeductResult();
		deductResult.setFailFlag(YoN.SHI.value);
		List<DeductResult> deductResultList = deductResultDao.findList(deductResult);
		if(deductResultList != null){
			logger.debug("扫描到" + deductResultList.size() + "条");
			for (DeductResult deductResult2 : deductResultList) {
				FortuneDeductResult fortuneDeductResult = new FortuneDeductResult();
				BeanUtils.copyProperties(deductResult2, fortuneDeductResult);
				fortuneDeductResult.setTheDayId(deductResult2.getThedayId());
				newList.add(fortuneDeductResult);
			}
			DeductResultProxy deductResultProxy = new DeductResultProxy();
			logger.debug("执行更新------开始");
			deductResultProxy.executeMqBatch(newList);
			logger.debug("执行更新------结束");
		}else{
			logger.debug("扫描MQ返回数据表没有数据");
		}
		logger.debug("扫描MQ返回数据表------>结束");
	}
}
