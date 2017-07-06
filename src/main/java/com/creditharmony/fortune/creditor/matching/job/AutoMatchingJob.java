package com.creditharmony.fortune.creditor.matching.job;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.creditharmony.core.common.type.UseFlag;
import com.creditharmony.core.fortune.type.ProductCode;
import com.creditharmony.fortune.creditor.config.auto.matching.dao.AutoMatchingDao;
import com.creditharmony.fortune.creditor.config.auto.matching.entity.CreditorperAutoConfig;
import com.creditharmony.fortune.creditor.config.auto.matching.service.AutoMatchingManager;
import com.creditharmony.fortune.creditor.matching.dao.MatchingCreditorDao;
import com.creditharmony.fortune.creditor.matching.dao.TempAutoMatchingCreditorDao;

/**
 * 债权匹配--自动匹配job
 * @Class Name AutoMatchingJob
 * @author 柳慧
 * @Create In 2016年2月16日
 */
@Service
@Lazy(false)
public class AutoMatchingJob {

	/**
	 * 日志对象
	 */
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private TempAutoMatchingCreditorDao tempAutoMatchingCreditorDao;
	
	@Autowired
	private AutoMatchingManager autoMatchingManager;
	
	@Autowired
	private AutoMatchingDao autoMatchingDao;
	
	@Autowired
	private MatchingCreditorDao matchingCreditorDao;// 待推荐Dao
	/**  
	 * 自动匹配入口
	 * 2016年2月16日
	 * By 柳慧
	 */
	@Scheduled(cron = "0 30 22 * * ?")
	public void start(){
		logger.info("************************自动匹配开始*********************");
		Integer count = tempAutoMatchingCreditorDao.getcount();
		// 如果有数据就不再圈数据
		if(count != null && count >0 ){
			logger.info("************************自动匹配没有匹配完本次不执行*********************");
			return;
		}
		/*// 删除所有待推荐自动匹配的信息
		autoMatchingManager.deleteAll();*/
		// 删除所有待推荐自动匹配的信息
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("status", UseFlag.QY.value);
		autoMatchingDao.updateByParam(param);
		// 查询自动匹配的规则集合
		List<CreditorperAutoConfig>  autoConfigLs = autoMatchingDao.findAll(param);
		if(autoConfigLs!= null && autoConfigLs.size()> 0){
			for(CreditorperAutoConfig cac : autoConfigLs){
				if(cac.getProductCode().equals(ProductCode.YMY.value)){ // 月满盈不放入自动匹配
					continue;
				}
				autoMatchingManager.insertAutoMatching(cac);
			}
		
		}
		logger.info("*************************自动匹配结束*********************");
	}
	
}
