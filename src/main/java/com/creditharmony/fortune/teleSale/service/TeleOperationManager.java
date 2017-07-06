package com.creditharmony.fortune.teleSale.service;

import org.springframework.stereotype.Service;

import com.creditharmony.core.service.CoreManager;
import com.creditharmony.fortune.teleSale.dao.TeleOperationDao;
import com.creditharmony.fortune.teleSale.entity.TeleOperation;

/**
 * 电销中心，为客户分配理财经理的记录Manager
 * @Class Name TeleOperationManager
 * @author 肖长伟
 * @Create In 2016年2月3日
 */
@Service
public class TeleOperationManager extends CoreManager<TeleOperationDao, TeleOperation> {
	
	/**
	 * 新增分配理财经理记录
	 * 2016年2月3日
	 * By 肖长伟
	 * @param teleOpration
	 */
	public void insertTeleOperation(TeleOperation teleOpration) {
		teleOpration.preInsert();
		super.dao.insert(teleOpration);
	}
	
	
	
}
