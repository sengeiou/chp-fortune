package com.creditharmony.fortune.remind.dao;

import java.util.List;
import java.util.Map;

import com.creditharmony.core.mybatis.paginator.domain.PageBounds;
import com.creditharmony.core.persistence.CrudDao;
import com.creditharmony.core.persistence.annotation.FortuneBatisDao;
import com.creditharmony.fortune.remind.entity.ext.SmsSendListEx;

/**
 * 消息提醒dao
 * @Class Name RemindDao
 * @author 韩龙
 * @Create In 2015年11月27日
 */
@FortuneBatisDao
public interface RemindDao extends CrudDao<SmsSendListEx> {
	
	/**
	 * 带条件分页查询
	 * 2015年12月8日
	 * By 韩龙
	 * @param entity
	 * @param pageBounds
	 * @return
	 */
	public List<SmsSendListEx> findList(SmsSendListEx entity, PageBounds pageBounds);
	
	/**
	 * 带条件map分页查询
	 * 2015年12月28日
	 * By 韩龙
	 * @param map
	 * @param pageBounds
	 * @return
	 */
	public List<SmsSendListEx> findList(Map<String,Object> map, PageBounds pageBounds);
	
	/**
	 * 
	 * 2015年12月28日
	 * By 韩龙
	 * @param map
	 * @param pageBounds
	 * @return
	 */
	/**
	 * 带条件查询导出
	 * 2015年12月28日
	 * By 韩龙
	 * @param map
	 * @return
	 */
	public List<SmsSendListEx> findList(Map<String,Object> map);
	
	/**
	 * 消息提醒详细
	 * 2015年11月27日
	 * By 韩龙
	 * @param parameter
	 * @return
	 */
	public SmsSendListEx getRemindDetail(Map<String, Object> parameter);
	
	/**
	 * 出借金额计算
	 * 2016年2月15日
	 * By 韩龙
	 * @param parameter
	 * @return
	 */
	public Map<String,String> getTotalLendMoney(SmsSendListEx smsSendListEx);
	
	/**
	 * 参数对象分页查询（三个月内到期提醒）
	 * 2016年10月12日
	 * By liusl
	 * @param map
	 * @param pageBounds
	 * @return
	 */
	public List<SmsSendListEx> findThreeMonthPage(SmsSendListEx entity, PageBounds pageBounds);
	
	/**
	 * 出借金额计算（三个月内到期提醒）
	 * 2016年10月12日
	 * By liusl
	 * @param smsSendListEx
	 * @return
	 */
	public Map<String,String> getTotalLendMoneyNew(SmsSendListEx smsSendListEx);
	
}