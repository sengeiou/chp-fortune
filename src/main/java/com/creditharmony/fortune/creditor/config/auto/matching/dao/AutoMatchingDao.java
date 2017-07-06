package com.creditharmony.fortune.creditor.config.auto.matching.dao;

import java.util.List;
import java.util.Map;

import com.creditharmony.core.mybatis.paginator.domain.PageBounds;
import com.creditharmony.core.mybatis.paginator.domain.PageList;
import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.FortuneBatisDao;
import com.creditharmony.fortune.creditor.config.auto.matching.entity.CreditorperAutoConfig;
/**
 * 自动匹配配置Dao
 * @Class Name AutoMatchingDao
 * @author 朱杰
 * @Create In 2015年12月24日
 */
@FortuneBatisDao
public interface AutoMatchingDao extends CrudDao<CreditorperAutoConfig>{
	
	/**
	 * 自动匹配列表获取
	 * 2015年12月24日
	 * By 朱杰
	 * @param map
	 * @param pageBounds
	 * @return
	 */
	public PageList<CreditorperAutoConfig> findPages(Map<String, Object> map,PageBounds pageBounds);
	
	/**
	 * 删除
	 * 2015年12月30日
	 * By 朱杰
	 * @param deleteIds
	 * @return
	 */
	public int deleteByIds(String[] deleteIds);
	
	/**
	 * 状态更新
	 * 2015年12月30日
	 * By 朱杰
	 * @param updateIds
	 * @return
	 */
	public int updateStatusByIds(String[] updateIds);
	
	/**
	 * 根据查询条件 获取集合
	 * 2016年2月17日
	 * By 柳慧
	 * @param param
	 * @return
	 */
	public List<CreditorperAutoConfig> findAll(Map<String, Object> param);
	
	/**
	 * 修改自动匹配设置 
	 * 2016年5月15日
	 * By 柳慧
	 * @param param
	 */
	public void updateByParam(Map<String, Object> param);

	public void updateAutoMatching();

	/**
	 * 批量启用停用
	 * 2016年09月22日
	 * By 陈晓强
	 * @param map
	 * @return
	 */
	public int updateBatchStatusByIds(Map<String, Object> map);
}
