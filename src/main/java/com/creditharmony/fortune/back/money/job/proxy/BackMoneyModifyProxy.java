package com.creditharmony.fortune.back.money.job.proxy;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.creditharmony.common.util.SpringContextHolder;
import com.creditharmony.core.fortune.service.ProductProfitService;
import com.creditharmony.fortune.back.money.job.UpdateService;
import com.creditharmony.fortune.back.money.job.entity.ProductProfit;

/**
 * 收益调整联动更新回款金额
 * @Class Name BackMoneyModifyProxy
 * @author 陈广鹏
 * @Create In 2016年12月27日
 */
@Component
public class BackMoneyModifyProxy implements ProductProfitService {
	
	private Logger logger = LoggerFactory.getLogger(BackMoneyModifyProxy.class); 
	
	private UpdateService updateService = SpringContextHolder.getBean(UpdateService.class);

	@Override
	public boolean updateBackMoney(String productProfitId) {
		ProductProfit profit = updateService.getProductProfit(productProfitId);
		if (profit ==null) {
			logger.error("没有找到对应的收益调整信息，收益调整id："+productProfitId);
			return false;
		}
		try {
			updateService.updateByProfitConfig(profit);
			logger.info("收益调整联动调整回款更新处理完毕，收益调整id："+productProfitId);
		} catch (Exception e) {
			logger.info("收益调整联动调整回款更新处理失败，收益调整id："+productProfitId);
			logger.error(ExceptionUtils.getFullStackTrace(e));
			return false;
		}
		return true;
	}

	@Override
	public String testHello(String name) {
		String result = "hello, " + name +"!!!";
		System.out.println(result);
		return result;
	}

}
