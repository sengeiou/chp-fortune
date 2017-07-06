package com.creditharmony.fortune.creditor.matching.dao;

import java.util.List;
import java.util.Map;

import com.creditharmony.core.mybatis.paginator.domain.PageBounds;
import com.creditharmony.core.mybatis.paginator.domain.PageList;
import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.FortuneBatisDao;
import com.creditharmony.fortune.creditor.matching.entity.CreditorperUpper;

/**
 * 债权人借款上限 Dao
 * @Class Name CreditorperUpperDao
 * @author 柳慧
 * @Create In 2016年1月27日
 */
@FortuneBatisDao
public interface CreditorperUpperDao extends CrudDao<CreditorperUpper> {

	/**
	 * 通过主键软删除
	 * 2016年1月28日
	 * By 柳慧
	 * @param deleteIds
	 */
	public int  deleteByIds(String[] deleteIds);
	
	/**
	 * 分页功能
	 * 2016年1月28日
	 * By 柳慧
	 * @param tempex
	 * @param pageBounds
	 * @return
	 */
	public PageList<CreditorperUpper> findList(Map<String, Object> tempex,
			PageBounds pageBounds);

	/**
	 * 根据条件查出所有
	 * 2016年1月28日
	 * By 柳慧
	 * @param tempex
	 * @return 
	 */
	public List<CreditorperUpper> findList(Map<String, Object> tempex);

	public int updateStatusByIds(String[] updateIds);

	public int updateBatchStatusByIds(Map<String, Object> map);
}