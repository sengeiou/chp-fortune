package com.creditharmony.fortune.teleSale.dao;

import java.util.List;
import java.util.Map;

import com.creditharmony.core.mybatis.paginator.domain.PageBounds;
import com.creditharmony.core.mybatis.paginator.domain.PageList;
import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.FortuneBatisDao;
import com.creditharmony.fortune.common.entity.UserRoleOrgEx;
import com.creditharmony.fortune.teleSale.entity.CustomerPool;
import com.creditharmony.fortune.teleSale.entity.ex.CustomerPoolEx;

/**
 * 电销中心模块，公共池DAO
 * @Class Name CustomerPoolDao
 * @author 肖长伟
 * @Create In 2016年2月3日
 */
@FortuneBatisDao
public interface CustomerPoolDao extends CrudDao<CustomerPool> {
	
	/**
	 * 新增
	 */
    int insert(CustomerPool record);

    /**
     * 根据主键，查公共池数据
     * 2016年2月3日
     * By 肖长伟
     * @param id
     * @return
     */
    CustomerPool selectByPrimaryKey(String id);
    
    /**
     * 根据条件分页查找电销公共池
     * 2016年2月3日
     * By 肖长伟
     * @param map
     * @param pageBounds
     * @return
     */
    PageList<CustomerPool> selectList(Map<String,Object> map, PageBounds pageBounds);

    /**
     * 更新公共池表数据
     */
    int update(CustomerPool record);
    
    int updateCustomerByCode(CustomerPool customerPool);
    
    /**
     * 查找理财经理，用于公共池为客户分配理财经理
     * 2016年2月3日
     * By 肖长伟
     * @param params
     * @param pageBounds
     * @return
     */
	PageList<UserRoleOrgEx> findManager(Map<String, Object> params, PageBounds pageBounds);
	
	/**
	 * 查找电销人员，用于为客户分配理财经理
	 * 2016年2月3日
	 * By 肖长伟
	 * @param params
	 * @param pageBounds
	 * @return
	 */
	PageList<UserRoleOrgEx> findTeleManager(Map<String, Object> params, PageBounds pageBounds);

	/**
	 * 电销客户咨询列表数据
	 * 2016年2月16日
	 * By 肖长伟
	 * @param page
	 * @param params
	 * @return
	 */
	PageList<CustomerPoolEx> getDistibuteCustomers(Map<String, Object> params, PageBounds pageBounds);

	/**
	 * 根据id数组，获取公共池数据
	 * 2016年2月17日
	 * By 肖长伟
	 * @param params
	 * @return
	 */
	List<CustomerPool> getCustomerPoolByIds(Map<String, Object> params);

	/**
	 * 根据条件查询公共池数据
	 * 2016年2月17日
	 * By 肖长伟
	 * @param customerPool
	 * @return
	 */
	List<CustomerPool> selectList(CustomerPool customerPool);
	
	/**
	 * 查出没有进入公共池的客户，或已分配过的客户
	 * 2016年2月19日
	 * By 肖长伟
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getCustomer4Job(Map<String, Object> params);
	
	/**
	 * 获取已完结申请的客户
	 * 2016年2月19日
	 * By 肖长伟
	 * @param params  custCode
	 * @return
	 */
	List<Map<String, Object>> getFinishApply4Job(Map<String, Object> params);
	
	/**
	 * 获取咨询
	 * 2016年2月19日
	 * By 肖长伟
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getAsk4Job(Map<String, Object> params);
	
	/**
	 * 获取公共池批处理整体数据
	 * 2016年2月19日
	 * By 肖长伟
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getCustomerPoolJobData(Map<String, Object> params);

	/**
	 * 获取机构下的用户
	 * 2016年2月29日
	 * By 肖长伟
	 * @param orgList
	 * @return
	 */
	List<Map<String, Object>> getUsersByOrgIds(List<String> orgList);
	
}