package com.creditharmony.fortune.borrow.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.creditharmony.fortune.borrow.dao.BorrowCancelDao;
import com.creditharmony.fortune.borrow.service.BorrowCancelManager;

/**
 * 当出借到期日与系统当前时间相同时，将撤销列表待替换状态更新成已替换Job
 * 2016年12月22日
 * @author 常亚运
 *
 */
@Service
@Lazy(false)
public class UpdateReplaceStatusJob {
	
	/**
	 * 日志对象
	 */
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	
	@Autowired
	private BorrowCancelDao borrowCancelDao;
	
	@Scheduled(cron = "0 00 01 * * ?")
	public void start() {
		logger.info("************************修改状态开始*********************");
		borrowCancelDao.updateTopageReplaceStatus();
		logger.info("************************修改状态结束*********************");
	}
}
