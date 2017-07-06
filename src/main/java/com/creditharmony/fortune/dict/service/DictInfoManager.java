package com.creditharmony.fortune.dict.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.core.service.CoreManager;
import com.creditharmony.fortune.dict.dao.DictInfoDao;
import com.creditharmony.fortune.dict.entity.DictInfo;

/**
 * 字典服务
 * @Class Name DictInfoManager
 * @author 陈伟东
 * @Create In 2015年12月28日
 */
@Service
@Transactional(value = "fortuneTransactionManager",readOnly = false)
public class DictInfoManager extends CoreManager<DictInfoDao, DictInfo> {
	
	public DictInfo getDictInfo(String id){
		return dao.get(id);
	}

	public void saveDictInfo(DictInfo dictInfo){
		dao.insert(dictInfo);
	}
	
	public void update(DictInfo dictInfo){
		dao.update(dictInfo);
	}
}
