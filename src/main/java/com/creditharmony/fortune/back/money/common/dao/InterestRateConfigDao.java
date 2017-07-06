package com.creditharmony.fortune.back.money.common.dao;

import java.util.Map;

import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.FortuneBatisDao;
import com.creditharmony.fortune.back.money.common.entity.InterestRateConfig;
import com.creditharmony.fortune.back.money.common.view.ResultView;
import com.creditharmony.fortune.back.money.job.entity.ProductProfit;

/**
 * 补息利率配置Dao
 * @Class Name InterestRateConfigDao
 * @author 陈广鹏
 * @Create In 2016年11月9日
 */
@FortuneBatisDao
public interface InterestRateConfigDao extends CrudDao<InterestRateConfig> {

	/**
	 * 获取补息利率
	 * 2016年11月9日
	 * By 陈广鹏
	 * @param map
	 * @return
	 */
	InterestRateConfig getProperRate(Map<String, Object> map);

	/**
	 * 获取月息通利率
	 * @param xhbMap
	 * @return
	 */
	ProductProfit getYxtOrXhyzProperRate(Map<String, Object> xhbMap);

	ProductProfit getProperRateCX(String value);

	/**
	 * 历史回息总金额
	 * @param lendCode
	 * @return
	 */
	ResultView countInterest(String lendCode);

	

}
