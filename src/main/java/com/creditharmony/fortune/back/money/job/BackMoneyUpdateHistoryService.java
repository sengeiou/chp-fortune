package com.creditharmony.fortune.back.money.job;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.creditharmony.fortune.back.money.job.entity.ProductProfit;
import com.creditharmony.fortune.common.entity.Check;


@Service
@Lazy(false)
public class BackMoneyUpdateHistoryService {
	
	@Autowired
	private UpdateService service;
	
	private Logger logger = LoggerFactory.getLogger(BackMoneyUpdateHistoryService.class); 
	
	/**
	 * <p>回款数据修正，只执行一次</p>
	 * 2016年12月21日<br/>
	 * By 陈广鹏
	 */
//	@Scheduled(cron = "0 1 0 * 01 ?")
	public void start(){
		logger.info("=============== 处理标识检查 ==================");
		// 处理标识检查
		Check check = service.getCheckFlag();
		if (check ==null || check.getOperateNode().equals("2")) {
			logger.info("========= 已有处理记录，不再处理 ===============");
			return;
		}
		int count = service.updateCheckFlag();
		if (count < 1 ) {
			logger.info("========= 已有处理记录，不再处理 ===============");
			return;
		}
		logger.info("=============== 处理标识检查通过 ===============");
		// 处理标识检查通过
		
		// 正常生成计算
		logger.info("=============== step1 start ==================");
		service.updateBackMoney();
		logger.info("=============== step1 finished ===============");
		
		// 根据收益调整数据进行修正
		List<ProductProfit> list = service.getProductProfitList();
		logger.info("读取到 " + list.size() + " 条收益调整信息");
		logger.info("=============== step2 start ==================");
		for (ProductProfit profit : list) {
			try {
				service.updateByProfitConfig(profit);
			} catch (Exception e) {
				logger.info(e.getMessage());
				e.printStackTrace();
			}
		}
		logger.info("=============== step2 finished ===============");
	}

}
