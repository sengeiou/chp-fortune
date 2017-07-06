package com.creditharmony.fortune.maintenance.triple.dao;

import java.util.List;
import java.util.Map;

import com.creditharmony.core.mybatis.paginator.domain.PageBounds;
import com.creditharmony.core.persistence.annotation.FortuneBatisDao;
import com.creditharmony.fortune.triple.system.entity.ext.IntDeliveryEx;

/**
 * 三网维护交割维护
 * @Class Name TripleMaintainDao
 * @author 周俊
 * @Create In 2016年5月21日
 */
@FortuneBatisDao
public interface TripleMaintainDao {
	
	/**
	 * 查询交割维护列表
	 * 2016年5月21日
	 * By 周俊
	 * @param pageBounds
	 * @return
	 */
	public List<IntDeliveryEx> findList(IntDeliveryEx intDeliveryEx,PageBounds pageBounds);

	/**
	 * 查询交割履历列表
	 * 2016年5月21日
	 * By 周俊
	 * @param pageBounds
	 * @return
	 */
	public List<IntDeliveryEx> findDeliveryRecord(Map<String, Object> map,PageBounds pageBounds);
	
	/**
	 * 查询发送履历列表
	 * 2016年5月21日
	 * By 周俊
	 * @param pageBounds
	 * @return
	 */
	public List<IntDeliveryEx> findSendRecord(Map<String, Object> map,PageBounds pageBounds);
	
	/**
	 * 查询操作履历列表
	 * 2016年5月21日
	 * By 周俊
	 * @param pageBounds
	 * @return
	 */
	public List<IntDeliveryEx> findOperateRecord(Map<String, Object> map,PageBounds pageBounds);
	
	/**
	 * 查询app遗漏数据
	 * 2016年6月30日
	 * By 胡体勇
	 * @return
	 */
	public List<IntDeliveryEx> findSyncDate();
	
	public IntDeliveryEx findEmpCode(String id);
	
	public int updateOsType(IntDeliveryEx IntDeliveryEx);
}
