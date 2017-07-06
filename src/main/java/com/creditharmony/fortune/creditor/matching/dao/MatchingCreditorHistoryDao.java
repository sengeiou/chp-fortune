/**
 * @Probject Name: chp-fortune
 * @Path: com.creditharmony.fortune.creditor.matching.dao.MatchingCreditorHistoryDao.java
 * @Create By 胡体勇
 * @Create In 2015年12月10日 下午6:50:39
 */
package com.creditharmony.fortune.creditor.matching.dao;

import java.util.List;

import com.creditharmony.core.mybatis.paginator.domain.PageBounds;
import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.FortuneBatisDao;
import com.creditharmony.fortune.creditor.matching.entity.ext.CreditorTradeEx;

/**
 * 已推荐债权查看列表
 * @Class Name creditorSendHistoryDao
 * @author 胡体勇
 * @Create In 2015年12月10日
 */
@FortuneBatisDao
public interface MatchingCreditorHistoryDao extends CrudDao<CreditorTradeEx> {
	
	/**
	 * 已推荐债权查看列表
	 * 2015年12月10日
	 * By 胡体勇
	 * @param creditorTradeEx
	 * @param pageBounds
	 * @return
	 */
	public List<CreditorTradeEx> findList(CreditorTradeEx creditorTradeEx,PageBounds pageBounds);

}
