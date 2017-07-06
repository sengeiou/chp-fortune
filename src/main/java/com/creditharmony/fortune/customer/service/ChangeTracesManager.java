package com.creditharmony.fortune.customer.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.service.CoreManager;
import com.creditharmony.fortune.customer.dao.ChangeTracesDao;
import com.creditharmony.fortune.customer.entity.ChangeTraces;

/**
 * 修改历史
 * 
 * @Class Name ChangeTracesManager
 * @author 孙凯文
 * @Create In 2015年11月30日
 */
@Service
@Transactional(readOnly = true)
public class ChangeTracesManager extends CoreManager<ChangeTracesDao, ChangeTraces> {

	/**
	 * 查询修改历史
	 * 2015年12月2日
	 * By 孙凯文
	 * @param page
	 * @param entity
	 * @return
	 */
	public Page<ChangeTraces> findPage(Page<ChangeTraces> page,
			ChangeTraces entity) {
		entity.setPage(page);
		return page.setList(super.dao.findPage(entity));
	}
}
