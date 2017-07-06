package com.creditharmony.fortune.common.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.creditharmony.core.common.util.PageUtil;
import com.creditharmony.core.mybatis.paginator.domain.PageBounds;
import com.creditharmony.core.mybatis.paginator.domain.PageList;
import com.creditharmony.core.persistence.Page;
import com.creditharmony.core.service.CoreManager;
import com.creditharmony.fortune.common.dao.FortuneExceptionDao;
import com.creditharmony.fortune.common.entity.FortuneException;

/**
 * 异常操作业务类
 * 
 * @Class Name FortuneExceptionService
 * @author 孙凯文
 * @Create In 2016年5月3日
 */
@Service
public class FortuneExceptionService extends
		CoreManager<FortuneExceptionDao, FortuneException> {

	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	/**
	 * 分页查询异常日志
	 * 2016年5月3日
	 * @author 孙凯文
	 * @param page
	 * @return Page<FortuneException>
	 */
	public Page<FortuneException> findListWithPage(Page<FortuneException> page) {
		PageBounds pageBounds = new PageBounds(page.getPageNo(),
				page.getPageSize());
		PageList<FortuneException> pageList = (PageList<FortuneException>) super.dao
				.findListWithPage(pageBounds);
		PageUtil.convertPage(pageList, page);
		return page;
	}
	
	public void save(FortuneException entity){
		try{
			super.save(entity);
		}catch(Exception e){
			logger.error("异常信息保存出错",e);
		}
	}
}
