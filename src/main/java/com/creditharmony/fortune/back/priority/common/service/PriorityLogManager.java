package com.creditharmony.fortune.back.priority.common.service;


import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditharmony.common.util.IdGen;
import com.creditharmony.fortune.back.priority.common.dao.PriorityLogDao;
import com.creditharmony.fortune.back.priority.common.view.PriorityBackLog;


/**
 * 优先回款审批审批纪录
 * @Class Name PriorityDetailDao
 * @author 郭强
 * @Create In 2017年3月28日
 */
@Service
public class PriorityLogManager {
	
	//private Logger logger = LoggerFactory.getLogger(PriorityLogManager.class);
	@Autowired
	private  PriorityLogDao priorityLogDao;
	
	/**
	 * 增加审批纪录
	 */
	public int  addPriorityLog(PriorityBackLog  log){
		Date date = new Date();
		if(log.getApplyTime()==null){
			log.setApplyTime(date);
		}
		if(log.getCreateTime()==null){
			log.setCreateTime(date);
		}
		log.setId(IdGen.uuid());
		if(log.getModifyTime()==null){
			log.setModifyTime(date);
		}
		return  priorityLogDao.addPriorityLog(log);
	}
	
	
}
