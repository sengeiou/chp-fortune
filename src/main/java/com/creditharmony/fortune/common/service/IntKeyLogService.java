package com.creditharmony.fortune.common.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.creditharmony.core.service.CoreManager;
import com.creditharmony.fortune.common.dao.IntKeyLogDao;
import com.creditharmony.fortune.common.entity.IntKeyLog;

/**
 * 三网异常操作业务类
 */
@Service
public class IntKeyLogService extends CoreManager<IntKeyLogDao, IntKeyLog> {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	public void log(Exception e, String node, String remark) {
		IntKeyLog intKeyLog = new IntKeyLog(e, node, remark);
		super.save(intKeyLog);
	}
}