package com.creditharmony.fortune.delivery.facade;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditharmony.core.service.CoreManager;
import com.creditharmony.fortune.delivery.dao.TripleAchievementDeliveryDao;
import com.creditharmony.fortune.delivery.service.TripleAchievementDeliveryManager;
import com.creditharmony.fortune.triple.system.entity.ext.IntDeliveryEx;

/**
 * @Class Name TripleAchievementDeliveryFacade
 * @author 胡体勇
 * @Create In 2016年4月28日
 */
@Service
public class TripleAchievementDeliveryFacade extends CoreManager<TripleAchievementDeliveryDao, IntDeliveryEx> {
	
	@Autowired
	private TripleAchievementDeliveryManager manager;
	
	/**
	 * 批量业绩交割
	 * 2016年2月24日
	 * By 胡体勇
	 * @param code
	 * @return
	 */
	public int batchTripleAchievementDelivery(String code){
		int a = 0;
		try {
			 a = manager.batchTripleAchievementDelivery(code);
			 return a;
		} catch (Exception e) {
			this.logger.error("批量业绩交割异常！", e);
			return a;
		}
		
	}
}
