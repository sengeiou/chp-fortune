package com.creditharmony.fortune.maintenance.triple.dao;

import java.util.Map;

import com.creditharmony.core.mybatis.paginator.domain.PageBounds;
import com.creditharmony.core.mybatis.paginator.domain.PageList;
import com.creditharmony.core.persistence.annotation.FortuneBatisDao;
import com.creditharmony.fortune.triple.system.entity.IntCustomerBean;
import com.creditharmony.fortune.triple.system.entity.IntEmployeeBean;
import com.creditharmony.fortune.triple.system.entity.IntOrgBean;

/**
 * 三网维护发送历史维护
 * @Class Name TripleSendDao
 * @author 周俊
 * @Create In 2016年5月20日
 */
@FortuneBatisDao
public interface TripleSendDao{

	/**
	 * 查询客户理财经理变更
	 * 2016年5月20日
	 * By 周俊
	 * @param map
	 * @param pageBounds
	 * @return
	 */
	public PageList<IntCustomerBean> findManagerChange(Map<String, Object> map, PageBounds pageBounds);
	
	/**
	 * 查询组织机构变更
	 * 2016年5月20日
	 * By 周俊
	 * @param map
	 * @param pageBounds
	 * @return
	 */
	public PageList<IntOrgBean> findOrgChange(Map<String, Object> map, PageBounds pageBounds);
	
	/**
	 * 查询用户同步
	 * 2016年5月20日
	 * By 周俊
	 * @param map
	 * @param pageBounds
	 * @return
	 */
	public PageList<IntEmployeeBean> findUserSynchro(Map<String, Object> map, PageBounds pageBounds);
	
}
