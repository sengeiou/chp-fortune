package com.creditharmony.fortune.common.dao;

import java.util.List;

import com.creditharmony.core.mybatis.paginator.domain.PageBounds;
import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.FortuneBatisDao;
import com.creditharmony.fortune.common.entity.FortuneException;

/**
 * 异常操作dao层
 * 
 * @Class Name FortuneExceptionDao
 * @author 孙凯文
 * @Create In 2016年5月3日
 */
@FortuneBatisDao
public interface FortuneExceptionDao extends CrudDao<FortuneException> {
	/**
	 * 分页查询异常日志
	 * 2016年5月3日
	 * @author 孙凯文
	 * @param page
	 * @return List<FortuneException>
	 */
	public List<FortuneException> findListWithPage(PageBounds page);
}
