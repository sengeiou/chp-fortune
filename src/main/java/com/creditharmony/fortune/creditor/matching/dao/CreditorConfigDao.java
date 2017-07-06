
package com.creditharmony.fortune.creditor.matching.dao;

import java.util.List;
import java.util.Map;

import com.creditharmony.core.mybatis.paginator.domain.PageBounds;
import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.FortuneBatisDao;
import com.creditharmony.fortune.creditor.matching.entity.CreditorConfig;

/**
 * 错期匹配
 * @Class Name CreditorConfig
 * @author 胡体勇
 * @Create In 2015年12月21日
 */
@FortuneBatisDao
public interface CreditorConfigDao extends CrudDao<CreditorConfig> {
	/**
	 * 错期匹配分页列表
	 * 2015年12月21日
	 * By 胡体勇
	 * @param creditorConfig
	 * @param pageBounds
	 * @return
	 */
	public List<CreditorConfig> findList(CreditorConfig creditorConfig,PageBounds pageBounds);

    /**
     * 新增错期匹配
     * 2015年12月21日
     * By 胡体勇
     * @param creditorConfig
     * @return
     */
	public int add(CreditorConfig creditorConfig);
	
	/**
	 * 修改错期匹配的状态
	 * 2015年12月21日
	 * By 胡体勇
	 * @param creditorConfig
	 * @return
	 */
	public int updateStatus(CreditorConfig creditorConfig);
	
	/**
	 * 通过账单日查询错期集合
	 * 2015年12月26日
	 * By 柳慧
	 * @param creditorConfig 账单日
	 * @return
	 */
	public List<CreditorConfig> findByMatchingBillDay(CreditorConfig creditorConfig);
	
	/**
	 * 添加时查询是否存在重复数据
	 * 2016年1月13日
	 * By 胡体勇
	 * @param creditorConfig
	 * @return
	 */
	public List<CreditorConfig> checkRepeat(CreditorConfig creditorConfig);

	/**
	 * 批量停用启用
	 * 2016年09月22日
	 * By 陈晓强
	 * @param map
	 * @return
	 */
	public int updateBatchStatusByIds(Map<String, Object> map);
}
